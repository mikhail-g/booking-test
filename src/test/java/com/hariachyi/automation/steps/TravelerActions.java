package com.hariachyi.automation.steps;

import com.hariachyi.automation.pages.HomePage;
import com.hariachyi.automation.pages.SearchResultPage;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.time.LocalDate;
import java.util.stream.IntStream;

@Slf4j
public class TravelerActions extends ScenarioSteps {

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

    @Step
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

    @Step
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

    @Step
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

    @Step
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

    @Step
    public void sets_adults(int adultsNumber) {
        log.info("Setting number of adults '{}'", adultsNumber);
        homePage.getSearchForm()
                .expandGuestsMenu()
                .getAdultsDropDown()
                .selectByValue(String.valueOf(adultsNumber));
    }

    @Step
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

    @Step
    public void sets_rooms(int roomsNumber) {
        log.info("Setting number of rooms '{}'", roomsNumber);
        homePage.getSearchForm()
                .expandGuestsMenu()
                .getRoomDropDown()
                .selectByValue(String.valueOf(roomsNumber));
    }

    @Step
    public void checks_im_traveling_for_work(boolean state) {
        log.info("Setting i'm traveling for work check-box state '{}'", state);
        homePage.getSearchForm()
                .getForWorkCheckBox()
                .setChecked(state);
    }

    @Step
    public void clicks_search_button() {
        log.info("Clicking Search button");
        homePage.getSearchForm()
                .getSearchButton()
                .click();
    }
}