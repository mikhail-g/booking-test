package com.hariachyi.automation.tests.e2e;

import com.hariachyi.automation.steps.TravelerActions;
import com.hariachyi.automation.steps.TravelerExpectations;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

/**
 * <p>The task is to develop UI test automation of the following scenario:</p>
 * <ol>
 * <li>Open https://www.booking.com/</li>
 * <li>Choose:</li>
 * <ul>
 * <li>Currency: ‘Euro’</li>
 * <li>Language: ‘English (US)’</li>
 * </ul>
 * <li>Complete property search details as follows:</li>
 * <ul>
 * <li>Destination: Málaga, Andalucía, Spain</li>
 * <li>Check-in: last day of current month</li>
 * <li>Check-out: first day of next month</li>
 * <li>1 adult</li>
 * <li>1 child 5 years old</li>
 * <li>Search</li>
 * </ul>
 * <li>Search results panel – refine search</li>
 * <ul>
 * <li>2 rooms</li>
 * <li>I'm traveling for work: check</li>
 * </ul>
 * <li>Click on ‘Search’ button</li>
 * <li>Assert that there is a property with both</li>
 * <ul>
 * <li>a review mark of higher than ‘8.0’ and</li>
 * <li>price under ‘200’ EUR</li>
 * </ul>
 * <li>Use Console.log to report the name of the first property found</li>
 * </ol>
 */
@RunWith(SerenityRunner.class)
public class BookingTest {

    @Managed
    WebDriver driver;

    @Steps
    private TravelerActions traveler;
    @Steps
    private TravelerExpectations expect;

    @Test
    public void propertySearch() {
        traveler.opens_home_page();
        traveler.closeCookieWarning();
        traveler.sets_currency("€");
        traveler.sets_language("English (US)");
        traveler.sets_destination("Málaga, Andalucía, Spain");
        traveler.sets_check_in_month_and_day("current", "last");
        traveler.sets_check_out_month_and_day("next", "first");
        traveler.sets_adults(1);
        traveler.sets_children_and_years(5);
        traveler.sets_rooms(2);
        traveler.checks_im_traveling_for_work(true);
        traveler.clicks_search_button();
        expect.search_result_should_contain_item_with("200", "8.0");
    }

    @Test
    public void propertySearchOnlyDestination() {
        traveler.opens_home_page();
        traveler.closeCookieWarning();
        traveler.sets_destination("Málaga, Andalucía, Spain");
        traveler.clicks_search_button();
        expect.search_result_should_contain_item_with("200", "8.0");
    }

    @Test
    public void propertySearchDestinationAndDate() {
        traveler.opens_home_page();
        traveler.closeCookieWarning();
        traveler.sets_destination("Málaga, Andalucía, Spain");
        traveler.sets_check_in_month_and_day("current", "last");
        traveler.sets_check_out_month_and_day("next", "first");
        traveler.clicks_search_button();
        expect.search_result_should_contain_item_with("200", "8.0");
    }
}