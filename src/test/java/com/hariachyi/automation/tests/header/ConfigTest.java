package com.hariachyi.automation.tests.header;

import com.hariachyi.automation.steps.TravelerActions;
import com.hariachyi.automation.steps.TravelerExpectations;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class ConfigTest {

    @Managed
    WebDriver driver;

    @Steps
    private TravelerActions traveler;
    @Steps
    private TravelerExpectations expect;

    @Test
    public void cookieWarning() {
        traveler.opens_home_page();
        traveler.closeCookieWarning();
    }

    @Test
    public void currencySetting() {
        traveler.opens_home_page();
        traveler.sets_currency("€");
    }

    @Test
    public void languageSetting() {
        traveler.opens_home_page();
        traveler.sets_language("English (US)");
    }
}