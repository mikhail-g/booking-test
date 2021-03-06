package com.hariachyi.automation.widgets.search_form;

import com.hariachyi.automation.widgets.search_form.calendar.CalendarWidget;
import com.hariachyi.automation.widgets.search_form.guests.GuestsWidget;
import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObject;
import net.thucydides.core.webelements.Checkbox;

import java.util.List;

@ImplementedBy(SearchFormImpl.class)
public interface SearchForm extends WidgetObject {

    WebElementFacade getDestinationField();

    WebElementFacade getFirstDestinationSuggestion();

    WebElementFacade getDatePicker();

    CalendarWidget getCalendarWidget();

    WebElementFacade getGuestsButton();

    GuestsWidget getGuestsWidget();

    WebElementFacade getSearchButton();

    Checkbox getForWorkCheckBox();


    SearchForm expandGuestsMenu();

    WebElementFacade getAdultsDropDown();

    WebElementFacade getChildrenDropDown();

    List<WebElementFacade> getChildAgeDropDownsList();

    WebElementFacade getRoomDropDown();
}
