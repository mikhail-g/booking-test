package com.hariachyi.automation.widgets;

import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WidgetObject;

import java.util.List;

@ImplementedBy(CalendarWidgetImpl.class)
public interface CalendarWidget extends WidgetObject {

    List<MonthWidget> getMonthWidgetList();

    void selectDate(int monthIndex, int dayIndex);

    net.serenitybdd.core.pages.WebElementFacade getEarlierButton();

    net.serenitybdd.core.pages.WebElementFacade getFurtherButton();
}
