package com.hariachyi.automation.pages;

import com.hariachyi.automation.model.SearchResultDto;
import com.hariachyi.automation.widgets.search_page.SearchItemWidget;
import lombok.Getter;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@DefaultUrl("https://www.booking.com/searchresults")
public class SearchResultPage extends PageObject {

    @FindBy(className = "sr_item")
    List<SearchItemWidget> searchItemWidgetList;


    public List<SearchResultDto> getSearchResultDtoList() {
        return searchItemWidgetList.stream()
                .map(SearchItemWidget::getSearchResultDto)
                .collect(Collectors.toList());
    }
}
