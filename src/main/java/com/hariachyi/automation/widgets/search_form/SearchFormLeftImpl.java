package com.hariachyi.automation.widgets.search_form;

import com.hariachyi.automation.widgets.search_form.calendar.CalendarWidget;
import lombok.Getter;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObjectImpl;
import net.thucydides.core.webelements.Checkbox;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.util.List;

@Getter
public class SearchFormLeftImpl extends WidgetObjectImpl implements SearchFormLeft {

    @FindBy(id = "ss")
    WebElementFacade destinationField;

    @FindBy(css = ".region_second_line ul>li")
    WebElementFacade firstDestinationSuggestion;

    @FindBy(css = "[data-mode='checkin']")
    WebElementFacade checkInDatePicker;

    @FindBy(css = "[data-mode='checkout']")
    WebElementFacade checkOutDatePicker;

    @FindBy(css = ".bui-calendar")
    CalendarWidget calendarWidget;

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

    public SearchFormLeftImpl(PageObject page, ElementLocator locator, WebElement webElement, long timeoutInMilliseconds) {
        super(page, locator, webElement, timeoutInMilliseconds);
    }

    public SearchFormLeftImpl(PageObject page, ElementLocator locator, long timeoutInMilliseconds) {
        super(page, locator, timeoutInMilliseconds);
    }

    public Checkbox getForWorkCheckBox() {
        return new Checkbox(forWorkCheckBox);
    }

}
