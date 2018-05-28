package com.hariachyi.automation.steps;

import com.hariachyi.automation.model.SearchResultDto;
import com.hariachyi.automation.pages.HomePage;
import com.hariachyi.automation.pages.SearchResultPage;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.hamcrest.Matcher;
import org.junit.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
                .orElseThrow(() -> new RuntimeException(
                        String.format("There is no currency '%s' in the Currency Dialog!", currencyCode)))
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
                .orElseThrow(() -> new RuntimeException(
                        String.format("There is no language '%s' in the Language Dialog!", language)))
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

    public void sets_adults(int adultsNumber) {
        log.info("Setting number of adults '{}'", adultsNumber);
        homePage.getSearchForm()
                .expandGuestsMenu()
                .getAdultsDropDown()
                .selectByValue(String.valueOf(adultsNumber));
    }

    public void sets_children_and_years(int... childrenYears) {
        int childrenNumber = childrenYears.length;
        log.info("Setting number of children '{}'", childrenNumber);
        homePage.getSearchForm()
                .expandGuestsMenu()
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
                .expandGuestsMenu()
                .getRoomDropDown()
                .selectByValue(String.valueOf(roomsNumber));
    }

    public void checks_im_traveling_for_work(boolean state) {
        log.info("Setting i'm traveling for work check-box state '{}'", state);
        homePage.getSearchForm()
                .getForWorkCheckBox()
                .setChecked(state);
    }

    public void clicks_search_button() {
        log.info("Clicking Search button");
        homePage.getSearchForm()
                .getSearchButton()
                .click();
    }

    public void search_result_should_contain_item_with(String priceLoverThan, String reviewScoreMoreThan) {
        log.info("Verifying search result list");
        BigDecimal maxPrice = new BigDecimal(priceLoverThan);
        BigDecimal minScore = new BigDecimal(reviewScoreMoreThan);
        List<SearchResultDto> searchResultDtoList = searchResultPage.getSearchResultDtoList();

        Matcher<Iterable<? super Object>> hotelMatcher = allOf(
                hasProperty("totalPrice", both(lessThan(maxPrice)).and(not(BigDecimal.ZERO))),
                hasProperty("reviewScore", greaterThan(minScore)
                ));

        Assert.assertThat("There is no item with Review Score > 8.0 and Price < 200 EURO in search results!",
                searchResultDtoList, hasItem(hotelMatcher));

        SearchResultDto searchResultDto = searchResultDtoList.stream()
                .filter(hotelMatcher::matches).findFirst().orElse(SearchResultDto.empty());
        log.info("The Hotel that satisfies to all the conditions is {}", searchResultDto);
    }
}