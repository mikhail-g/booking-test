package com.hariachyi.automation.widgets.search_form;

import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObject;
import net.thucydides.core.webelements.Checkbox;

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

    Checkbox getForWorkCheckBox();

    WebElementFacade getSearchButton();

    CalendarWidget getCheckInCalendarWidget();

    CalendarWidget getCheckOutCalendarWidget();

    SearchForm expandGuestsMenu();

    WebElementFacade getFirstDestinationSuggestion();
}
