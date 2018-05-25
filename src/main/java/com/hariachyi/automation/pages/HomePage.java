package com.hariachyi.automation.pages;

import com.hariachyi.automation.widgets.CurrencyDialog;
import com.hariachyi.automation.widgets.LanguageDialog;
import com.hariachyi.automation.widgets.SignInPopup;
import com.hariachyi.automation.widgets.WelcomePopup;
import lombok.Getter;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;

@Getter
@DefaultUrl("https://www.booking.com/")
public class HomePage extends PageObject {

    @FindBy(className = "ge-welcome-lightbox-container")
    WelcomePopup welcomePopup;

    @FindBy(className = "uc_currency")
    WebElementFacade currencyLink;

    @FindBy(className = "uc_language")
    WebElementFacade languageLink;

    @FindBy(id = "current_currency")
    CurrencyDialog currencyDialog;

    @FindBy(id = "current_language")
    LanguageDialog languageDialog;

    @FindBy(className = "header-signin-prompt")
    SignInPopup signInPopup;

}
