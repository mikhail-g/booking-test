package com.hariachyi.automation.widgets.home_page;

import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObject;

@ImplementedBy(WelcomePopupImpl.class)
public interface WelcomePopup extends WidgetObject {

    WebElementFacade getCloseButton();
}
