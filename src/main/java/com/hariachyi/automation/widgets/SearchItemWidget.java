package com.hariachyi.automation.widgets;

import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObject;

import java.math.BigDecimal;

@ImplementedBy(SearchItemWidgetImpl.class)
public interface SearchItemWidget extends WidgetObject {

    WebElementFacade getReviewScore();

    WebElementFacade getTotalPriceMessage();

    BigDecimal getTotalPrice();
}
