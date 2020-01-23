package com.hariachyi.automation.widgets.search_form.guests;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObjectImpl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;

@Slf4j
@Getter
public class GuestsRowImpl extends WidgetObjectImpl implements GuestsRow {

    @FindBy(className = "bui-stepper__add-button")
    WebElementFacade addButton;

    @FindBy(className = "bui-stepper__subtract-button")
    WebElementFacade subtractButton;

    @FindBy(className = "bui-stepper__display")
    WebElementFacade quantity;

    public GuestsRowImpl(PageObject page, ElementLocator locator, WebElement webElement, long timeoutInMilliseconds) {
        super(page, locator, webElement, timeoutInMilliseconds);
    }

    public GuestsRowImpl(PageObject page, ElementLocator locator, long timeoutInMilliseconds) {
        super(page, locator, timeoutInMilliseconds);
    }
}
