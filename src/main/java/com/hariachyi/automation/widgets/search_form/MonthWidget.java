package com.hariachyi.automation.widgets.search_form;

import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObject;

import java.util.List;

@ImplementedBy(MonthWidgetImpl.class)
public interface MonthWidget extends WidgetObject {

    List<WebElementFacade> getDayList();
}
