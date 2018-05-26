package com.hariachyi.automation.pages;

import com.hariachyi.automation.widgets.SearchItemWidget;
import lombok.Getter;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

import java.util.List;

@Getter
@DefaultUrl("https://www.booking.com/searchresults")
public class SearchResultPage extends PageObject {

    @FindBy(className = "sr_item")
    List<SearchItemWidget> searchItemWidgetList;
}
