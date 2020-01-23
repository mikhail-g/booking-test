package com.hariachyi.automation.widgets.search_form.guests;

import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObject;

import java.util.List;

@ImplementedBy(GuestsWidgetImpl.class)
public interface GuestsWidget extends WidgetObject {

    GuestsRow getAdultsRow();

    GuestsRow getChildrenRow();

    GuestsRow getRoomsRow();

    List<WebElementFacade> getChildAgeDropDownsList();
}