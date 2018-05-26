package com.hariachyi.automation.tests;

import com.hariachyi.automation.steps.TravelerSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class BookingTest {

    @Managed
    WebDriver driver;

    @Steps
    private TravelerSteps traveler;

    @Test
    public void propertySearch() {
        traveler.opens_home_page();
        traveler.sets_currency("€");
        traveler.sets_language("English (US)");
        traveler.sets_destination("Málaga, Andalucía, Spain");
        traveler.sets_check_in_month_and_day("current", "last");
        traveler.sets_check_out_month_and_day("next", "first");
        traveler.sets_adults("1");
        traveler.sets_children_and_years(5);
        traveler.clicks_search_button();
        traveler.sets_rooms(2);
        traveler.checks_im_traveling_for_work(true);
        traveler.clicks_search_button();
        traveler.asserts_property();
    }

    /*
    1.	Open https://www.booking.com/
    2.	Choose:
        o	Currency: ‘Euro’
        o	Language: ‘English (US)’
    3.	Complete property search details as follows:
        o	Destination: Málaga, Andalucía, Spain
        o	Check-in: last day of current month
        o	Check-out: first day of next month
        o	1 adult
        o	1 child 5 years old
        o	Search
    4.	Search results panel – refine search
        o	2 rooms
        o	I'm traveling for work: check
    5.	Click on ‘Search’ button
    6.	Assert that there is a property with both
        o	a review mark of higher than ‘8.0’ and
        o	price under ‘200’ EUR
    7.	Use Console.log to report the name of the first property found
    */
}
