package com.hariachyi.automation.steps;

import com.hariachyi.automation.pages.HomePage;
import com.hariachyi.automation.pages.SearchResultPage;
import com.hariachyi.automation.widgets.search_form.SearchForm;
import com.hariachyi.automation.widgets.search_form.guests.GuestsRow;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.WebElementFacade;
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
        WebElementFacade destinationField = homePage.getSearchForm()
                .getDestinationField();
        destinationField.click();
        destinationField.type(destination);
        homePage.getSearchForm()
                .getFirstDestinationSuggestion()
                .waitUntilClickable()
                .click();

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
        int[] dateIndexes = parseDateIndexes(month, day);
        SearchForm searchForm = homePage.getSearchForm();
        if (!searchForm.getCalendarWidget().isCurrentlyVisible()) {
            searchForm.getDatePicker()
                    .click();
        }
        searchForm.getCalendarWidget()
                .selectDate(dateIndexes[0], dateIndexes[1]);
    }

    @Step
    public void sets_check_out_month_and_day(String month, String day) {
        log.info("Setting Check Out Month '{}' and day '{}'", month, day);
        int[] dateIndexes = parseDateIndexes(month, day);
        SearchForm searchForm = homePage.getSearchForm();
        if (!searchForm.getCalendarWidget().isCurrentlyVisible()) {
            searchForm.getDatePicker()
                    .click();
        }
        searchForm.getCalendarWidget()
                .selectDate(dateIndexes[0], dateIndexes[1]);
    }

    @Step
    public void sets_adults(int adultsNumber) {
        log.info("Setting number of adults '{}'", adultsNumber);
        expandGuestsMenu();
        GuestsRow adultsRow = homePage.getSearchForm()
                .getGuestsWidget()
                .getAdultsRow();
        setQuantity(adultsNumber, adultsRow);
    }

    @Step
    public void sets_children_and_years(int... childrenYears) {
        int childrenNumber = childrenYears.length;
        log.info("Setting number of children '{}'", childrenNumber);
        expandGuestsMenu();
        GuestsRow childrenRow = homePage.getSearchForm()
                .getGuestsWidget()
                .getChildrenRow();
        setQuantity(childrenNumber, childrenRow);

        IntStream.range(0, childrenNumber).forEach(child -> {
            int childAge = childrenYears[child];
            log.info("Setting age '{}' year(s) old for child# '{}'", childAge, child);
            homePage.getSearchForm()
                    .getGuestsWidget()
                    .getChildAgeDropDownsList()
                    .get(child)
                    .selectByValue(String.valueOf(childAge));
        });
    }

    @Step
    public void sets_rooms(int roomsNumber) {
        log.info("Setting number of rooms '{}'", roomsNumber);
        expandGuestsMenu();
        GuestsRow roomsRow = homePage.getSearchForm()
                .getGuestsWidget()
                .getRoomsRow();
        setQuantity(roomsNumber, roomsRow);
    }

    private void setQuantity(int adultsNumber, GuestsRow guestsRow) {
        int currentQuantity = Integer.parseInt(guestsRow.getQuantity().getText());
        int toAdd = adultsNumber - currentQuantity;
        WebElementFacade button;
        button = toAdd >= 0 ? guestsRow.getAddButton() : guestsRow.getSubtractButton();
        toAdd = Math.abs(toAdd);
        while (toAdd-- != 0) {
            button.click();
        }
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

    @Step
    public void closeCookieWarning() {
        homePage.getCookieAcceptButton()
                .waitUntilClickable()
                .click();
    }

    @Step
    private void expandGuestsMenu() {
        if (!homePage.getSearchForm()
                .getGuestsButton()
                .getAttribute("aria-expanded")
                .equals("true")) {
            homePage.getSearchForm()
                    .getGuestsButton()
                    .click();
        }
    }
}