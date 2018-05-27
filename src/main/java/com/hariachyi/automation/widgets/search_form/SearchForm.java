package com.hariachyi.automation.widgets.search_form;

import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObject;

import java.util.List;

@ImplementedBy(SearchFormImpl.class)
public interface SearchForm extends WidgetObject {

    WebElementFacade getDestinationField();

    WebElementFacade getCheckInDatePicker();

    WebElementFacade getCheckOutDatePicker();

    WebElementFacade getAdultsDropDown();

    WebElementFacade getChildrenDropDown();

    List<WebElementFacade> getChildAgeDropDownsList();

    WebElementFacade getRoomDropDown();

    WebElementFacade getForWorkCheckBox();

    WebElementFacade getSearchButton();

    CalendarWidget getCheckInCalendarWidget();

    CalendarWidget getCheckOutCalendarWidget();

    WebElementFacade getGuestsButton();
}
