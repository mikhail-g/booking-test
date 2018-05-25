package com.hariachyi.automation.steps;

import com.hariachyi.automation.pages.HomePage;
import com.hariachyi.automation.widgets.SignInPopup;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class TravelerSteps extends ScenarioSteps {

    private HomePage homePage;

    @Step
    public void opens_home_page() {
        homePage.open();
        SignInPopup signInPopup = homePage.getSignInPopup();
        if (signInPopup.isPresent()) {
            signInPopup.close();
        }
    }

    @Step
    public void sets_currency(String currencyCode) {
        homePage.getCurrencyLink().click();
        homePage.getCurrencyDialog().getCurrencyCodeList()
                .stream().filter(symbol -> currencyCode.equals(symbol.getText())).findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("There is no currencyCode with " +
                        "code '%s' in the Currency Dialog!", currencyCode)))
                .click();
    }

    public void sets_language(String language) {
        homePage.getLanguageLink().click();
        homePage.getLanguageDialog().getLanguageList()
                .stream().filter(item -> language.equals(item.getText())).findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("There is no language with '%s' in the Language" +
                        " Dialog!", language)))
                .click();
    }
}
