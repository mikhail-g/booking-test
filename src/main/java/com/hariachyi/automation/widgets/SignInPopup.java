package com.hariachyi.automation.widgets;

import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WidgetObject;

@ImplementedBy(SignInPopupImpl.class)
public interface SignInPopup extends WidgetObject {

    void close();
}
