package com.hariachyi.automation.widgets.search_page;

import com.hariachyi.automation.model.SearchResultDto;
import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WidgetObject;

import java.math.BigDecimal;

@ImplementedBy(SearchItemWidgetImpl.class)
public interface SearchItemWidget extends WidgetObject {

    BigDecimal getReviewScore();

    BigDecimal getTotalPrice();

    String getHotelName();

    SearchResultDto getSearchResultDto();
}
