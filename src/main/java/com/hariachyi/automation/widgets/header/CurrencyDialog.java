package com.hariachyi.automation.widgets.header;

import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObject;

import java.util.List;

@ImplementedBy(CurrencyDialogImpl.class)
public interface CurrencyDialog extends WidgetObject {

    List<WebElementFacade> getCurrencyCodeList();
}
