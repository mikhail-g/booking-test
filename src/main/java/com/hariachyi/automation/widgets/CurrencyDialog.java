package com.hariachyi.automation.widgets;

import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObject;

import java.util.List;

@ImplementedBy(CurrencyDialogImpl.class)
public interface CurrencyDialog extends WidgetObject {

    WebElementFacade getCurrencyEuro();

    List<WebElementFacade> getCurrencyCodeList();
}
