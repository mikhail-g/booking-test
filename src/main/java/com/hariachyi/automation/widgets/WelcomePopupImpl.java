package com.hariachyi.automation.widgets;

import lombok.Getter;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObjectImpl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;

@Getter
public class WelcomePopupImpl extends WidgetObjectImpl implements WelcomePopup {

    @FindBy(className = "modal-mask-closeBtn")
    WebElementFacade closeButton;

    public WelcomePopupImpl(net.serenitybdd.core.pages.PageObject page, ElementLocator locator, WebElement webElement, long timeoutInMilliseconds) {
        super(page, locator, webElement, timeoutInMilliseconds);
    }

    public WelcomePopupImpl(net.serenitybdd.core.pages.PageObject page, ElementLocator locator, long timeoutInMilliseconds) {
        super(page, locator, timeoutInMilliseconds);
    }
}
