package com.hariachyi.automation.steps;

import com.hariachyi.automation.model.SearchResult;
import com.hariachyi.automation.pages.HomePage;
import com.hariachyi.automation.pages.SearchResultPage;
import com.hariachyi.automation.widgets.header.SignInPopup;
import com.hariachyi.automation.widgets.search_page.SearchItemWidget;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.*;

@Slf4j
public class TravelerSteps extends ScenarioSteps {

    private HomePage homePage;
    private SearchResultPage searchResultPage;

    @Step
    public void opens_home_page() {
        log.info("Opening Home Page...");
        homePage.open();
        SignInPopup signInPopup = homePage.getHeader()
                .getSignInPopup();
        if (signInPopup.isPresent()) {
            log.info("Closing SignIn popup...");
            signInPopup.getCloseButton().click();
        }
    }

    @Step
    public void sets_currency(String currencyCode) {
        log.info("Setting currency '{}'", currencyCode);
        homePage.getHeader()
                .getCurrencyLink()
                .click();
        homePage.getHeader()
                .getCurrencyDialog()
                .getCurrencyCodeList()
                .stream().filter(symbol -> currencyCode.equals(symbol.getText())).findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("There is no currencyCode with " +
                        "code '%s' in the Currency Dialog!", currencyCode)))
                .click();
    }

    public void sets_language(String language) {
        log.info("Setting language '{}'", language);
        homePage.getHeader()
                .getLanguageLink()
                .click();
        homePage.getHeader()
                .getLanguageDialog()
                .getLanguageList()
                .stream().filter(item -> language.equals(item.getText())).findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("There is no language with '%s' in the Language" +
                        " Dialog!", language)))
                .click();
    }

    public void sets_destination(String destination) {
        log.info("Setting destination '{}'", destination);
        homePage.getSearchForm()
                .getDestinationField()
                .type(destination);
    }

    private int[] parseDateIndexes(String month, String day) {
        int monthIndex = ("next".equals(month.toLowerCase())) ? 1 : 0;
        LocalDate now = LocalDate.now();
        LocalDate targetMonth = now.withMonth(now.getMonth().plus(monthIndex).getValue());
        int dayIndex = ("last".equals(day.toLowerCase())) ? targetMonth.lengthOfMonth() - 1 : 0;
        return new int[]{monthIndex, dayIndex};
    }

    public void sets_check_in_month_and_day(String month, String day) {
        log.info("Setting Check In Month '{}' and day '{}'", month, day);
        homePage.getSearchForm()
                .getCheckInDatePicker()
                .click();
        int[] dateIndexes = parseDateIndexes(month, day);
        homePage.getSearchForm()
                .getCheckInCalendarWidget()
                .selectDate(dateIndexes[0], dateIndexes[1]);
    }

    public void sets_check_out_month_and_day(String month, String day) {
        log.info("Setting Check Out Month '{}' and day '{}'", month, day);
        homePage.getSearchForm()
                .getCheckOutDatePicker()
                .click();
        int[] dateIndexes = parseDateIndexes(month, day);
        homePage.getSearchForm()
                .getCheckOutCalendarWidget()
                .selectDate(dateIndexes[0], dateIndexes[1]);
    }

    public void sets_adults(String adultsNumber) {
        log.info("Setting number of adults '{}'", adultsNumber);
        expandGuestsMenu();
        homePage.getSearchForm()
                .getAdultsDropDown()
                .selectByValue(adultsNumber);
    }

    private void expandGuestsMenu() {
        WebElementFacade guestsButton = homePage.getSearchForm().getGuestsButton();
        if (guestsButton.isPresent()) {
            guestsButton.click();
        }
    }

    public void sets_children_and_years(int... childrenYears) {
        int childrenNumber = childrenYears.length;
        log.info("Setting number of children '{}'", childrenNumber);
        homePage.getSearchForm()
                .getChildrenDropDown()
                .selectByValue(String.valueOf(childrenNumber));

        IntStream.range(0, childrenNumber).forEach(child -> {
            int childAge = childrenYears[child];
            log.info("Setting age '{}' year(s) old for child# '{}'", childAge, child);
            homePage.getSearchForm()
                    .getChildAgeDropDownsList()
                    .get(child)
                    .selectByValue(String.valueOf(childAge));
        });
    }

    public void sets_rooms(int roomsNumber) {
        log.info("Setting number of rooms '{}'", roomsNumber);
        homePage.getSearchForm()
                .getRoomDropDown()
                .selectByValue(String.valueOf(roomsNumber));
    }

    public void checks_im_traveling_for_work(boolean state) {
        log.info("Setting i'm traveling for work check-box state '{}'", state);
        homePage.getSearchForm()
                .getForWorkCheckBox()
                .click();
    }

    public void clicks_search_button() {
        log.info("Clicking Search button");
        homePage.getSearchForm()
                .getSearchButton()
                .click();
    }

    public void asserts_property() {
        log.info("Asserting property");
        BigDecimal EURO_200 = new BigDecimal("200");
        BigDecimal SCORE_8_0 = new BigDecimal("8.0");
        SearchResult emptyResult = SearchResult.of(BigDecimal.ZERO, BigDecimal.ZERO);
        List<SearchItemWidget> searchItemWidgetList = searchResultPage.getSearchItemWidgetList();
        log.info("Search results size: {}", searchItemWidgetList.size());
        List<SearchResult> searchResultList = searchItemWidgetList.stream()
                .map(item -> {
                    log.info("Received item: name '{}', price '{}', score '{}'", item.getHotelName().getText(), item.getTotalPrice(), item.getReviewScore());
                    return SearchResult.of(item.getTotalPrice(), item.getReviewScore());
                }).collect(Collectors.toList());

        searchResultList.forEach(item -> log.info("{}", item));
        Assert.assertThat("There is no item with Review Score > 8.0 and Price < 200 EURO in search results!",
                searchResultList, hasItem(allOf(hasProperty("totalPrice", lessThan(EURO_200)), hasProperty("reviewScore", greaterThan(SCORE_8_0)))));

        SearchResult searchResult = searchResultList.stream()
                .filter(item -> item.getReviewScore().compareTo(SCORE_8_0) > 0)
                .filter(item -> item.getTotalPrice().compareTo(EURO_200) < 0)
                .findFirst().orElse(emptyResult);
        Assert.assertThat("There is no item with Review Score > 8.0 and Price < 200 EURO in search results!",
                searchResult, is(not(emptyResult)));
        log.info("The Hotel that satisfies to all the conditions is {}", searchResult);
    }
}