package com.hariachyi.automation.widgets.search_form.guests;

import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObject;

@ImplementedBy(GuestsRowImpl.class)
public interface GuestsRow extends WidgetObject {

    WebElementFacade getSubtractButton();

    WebElementFacade getQuantity();

    WebElementFacade getAddButton();
}
