package com.hariachyi.automation.widgets.search_form;

import com.hariachyi.automation.widgets.search_form.calendar.CalendarWidget;
import com.hariachyi.automation.widgets.search_form.guests.GuestsWidget;
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
public class SearchFormImpl extends WidgetObjectImpl implements SearchForm {

    @FindBy(id = "ss")
    WebElementFacade destinationField;

    @FindBy(css = ".region_second_line ul>li")
    WebElementFacade firstDestinationSuggestion;

    @FindBy(className = "xp__dates__checkin")
    WebElementFacade datePicker;

    @FindBy(css = ".bui-calendar")
    CalendarWidget calendarWidget;

    @FindBy(id = "xp__guests__toggle")
    WebElementFacade guestsButton;

    @FindBy(className = "xp__guests__inputs")
    GuestsWidget guestsWidget;

    //    @FindBy(name = "sb_travel_purpose")
    @FindBy(className = "xp__travel-purpose")
    WebElementFacade forWorkCheckBox;

    @FindBy(className = "sb-searchbox__button")
    WebElementFacade searchButton;


    @FindBy(id = "group_adults")
    WebElementFacade adultsDropDown;

    @FindBy(id = "group_children")
    WebElementFacade childrenDropDown;

    @FindBy(css = "[name='age']")
    List<WebElementFacade> childAgeDropDownsList;

    @FindBy(id = "no_rooms")
    WebElementFacade roomDropDown;

    public SearchFormImpl(PageObject page, ElementLocator locator, WebElement webElement, long timeoutInMilliseconds) {
        super(page, locator, webElement, timeoutInMilliseconds);
    }

    public SearchFormImpl(PageObject page, ElementLocator locator, long timeoutInMilliseconds) {
        super(page, locator, timeoutInMilliseconds);
    }

    public Checkbox getForWorkCheckBox() {
        return new Checkbox(forWorkCheckBox);
    }

    public SearchForm expandGuestsMenu() {
        if (guestsWidget.isCurrentlyVisible() &&
                guestsWidget.getAttribute("class").contains("hidden")) {
            guestsButton.click();
        }
        return this;
    }
}
