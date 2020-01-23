package com.hariachyi.automation.widgets.search_form;

import com.hariachyi.automation.widgets.search_form.calendar.CalendarWidget;
import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObject;
import net.thucydides.core.webelements.Checkbox;

import java.util.List;

@ImplementedBy(SearchFormLeftImpl.class)
public interface SearchFormLeft extends WidgetObject {

    WebElementFacade getDestinationField();

    WebElementFacade getFirstDestinationSuggestion();

    WebElementFacade getCheckInDatePicker();

    WebElementFacade getCheckOutDatePicker();

    CalendarWidget getCalendarWidget();

    WebElementFacade getAdultsDropDown();

    WebElementFacade getChildrenDropDown();

    List<WebElementFacade> getChildAgeDropDownsList();

    WebElementFacade getRoomDropDown();

    Checkbox getForWorkCheckBox();

    WebElementFacade getSearchButton();
}
