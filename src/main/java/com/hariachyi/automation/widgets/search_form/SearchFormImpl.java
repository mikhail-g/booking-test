package com.hariachyi.automation.widgets.search_form;

import lombok.Getter;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObjectImpl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.util.List;

@Getter
public class SearchFormImpl extends WidgetObjectImpl implements SearchForm {

    @FindBy(id = "ss")
    WebElementFacade destinationField;

    @FindBy(className = "c2-wrapper-s-checkin")
    WebElementFacade checkInDatePicker;

    @FindBy(className = "c2-wrapper-s-checkout")
    WebElementFacade checkOutDatePicker;

    @FindBy(id = "group_adults")
    WebElementFacade adultsDropDown;

    @FindBy(id = "group_children")
    WebElementFacade childrenDropDown;

    @FindBy(css = "[name='age']")
    List<WebElementFacade> childAgeDropDownsList;

    @FindBy(id = "no_rooms")
    WebElementFacade roomDropDown;

    @FindBy(name = "sb_travel_purpose")
    WebElementFacade forWorkCheckBox;

    @FindBy(className = "sb-searchbox__button")
    WebElementFacade searchButton;

    @FindBy(css = ".xp__dates__checkin .c2-calendar,.--checkin-field .c2-calendar")
    CalendarWidget checkInCalendarWidget;

    @FindBy(css = ".xp__dates__checkout .c2-calendar,.--checkout-field .c2-calendar")
    CalendarWidget checkOutCalendarWidget;

    @FindBy(className = "xp__guests")
    WebElementFacade guestsButton;

    public SearchFormImpl(PageObject page, ElementLocator locator, WebElement webElement, long timeoutInMilliseconds) {
        super(page, locator, webElement, timeoutInMilliseconds);
    }

    public SearchFormImpl(PageObject page, ElementLocator locator, long timeoutInMilliseconds) {
        super(page, locator, timeoutInMilliseconds);
    }
}
