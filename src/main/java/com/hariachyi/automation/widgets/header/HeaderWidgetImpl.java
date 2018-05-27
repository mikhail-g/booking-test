package com.hariachyi.automation.widgets.header;

import lombok.Getter;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObjectImpl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

@Getter
public class HeaderWidgetImpl extends WidgetObjectImpl implements HeaderWidget {

    @FindBy(className = "uc_currency")
    WebElementFacade currencyLink;

    @FindBy(id = "current_currency")
    CurrencyDialog currencyDialog;

    @FindBy(className = "uc_language")
    WebElementFacade languageLink;

    @FindBy(id = "current_language")
    LanguageDialog languageDialog;

    @FindBy(className = "header-signin-prompt")
    SignInPopup signInPopup;

    public HeaderWidgetImpl(PageObject page, ElementLocator locator, WebElement webElement, long timeoutInMilliseconds) {
        super(page, locator, webElement, timeoutInMilliseconds);
    }

    public HeaderWidgetImpl(PageObject page, ElementLocator locator, long timeoutInMilliseconds) {
        super(page, locator, timeoutInMilliseconds);
    }
}
