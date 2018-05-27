package com.hariachyi.automation.widgets.header;

import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObject;

@ImplementedBy(HeaderWidgetImpl.class)
public interface HeaderWidget extends WidgetObject {


    WebElementFacade getCurrencyLink();

    CurrencyDialog getCurrencyDialog();

    WebElementFacade getLanguageLink();

    LanguageDialog getLanguageDialog();

    SignInPopup getSignInPopup();
}
