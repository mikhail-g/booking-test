package com.hariachyi.automation.widgets.search_form.guests;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObjectImpl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.util.List;

@Slf4j
@Getter
public class GuestsWidgetImpl extends WidgetObjectImpl implements GuestsWidget {

    @FindBy(css = "[name='age']")
    List<WebElementFacade> childAgeDropDownsList;
    @FindBy(className = "sb-group__field-adults")
    private GuestsRow adultsRow;
    @FindBy(className = "sb-group-children")
    private GuestsRow childrenRow;
    @FindBy(className = "sb-group__field-rooms")
    private GuestsRow roomsRow;

    public GuestsWidgetImpl(PageObject page, ElementLocator locator, WebElement webElement, long timeoutInMilliseconds) {
        super(page, locator, webElement, timeoutInMilliseconds);
    }

    public GuestsWidgetImpl(PageObject page, ElementLocator locator, long timeoutInMilliseconds) {
        super(page, locator, timeoutInMilliseconds);
    }
}
