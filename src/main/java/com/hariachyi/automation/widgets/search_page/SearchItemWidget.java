package com.hariachyi.automation.widgets.search_page;

import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObject;

@ImplementedBy(SearchItemWidgetImpl.class)
public interface SearchItemWidget extends WidgetObject {

    WebElementFacade getReviewScore();

    WebElementFacade getTotalPrice();

    WebElementFacade getHotelName();
}
