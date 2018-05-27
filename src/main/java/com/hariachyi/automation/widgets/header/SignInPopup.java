package com.hariachyi.automation.widgets.header;

import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObject;

@ImplementedBy(SignInPopupImpl.class)
public interface SignInPopup extends WidgetObject {

    WebElementFacade getCloseButton();
}
