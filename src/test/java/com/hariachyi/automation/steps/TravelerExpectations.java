package com.hariachyi.automation.steps;

import com.hariachyi.automation.model.SearchResultDto;
import com.hariachyi.automation.pages.SearchResultPage;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.hamcrest.Matcher;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.*;

@Slf4j
public class TravelerExpectations extends ScenarioSteps {

    private SearchResultPage searchResultPage;

    @Step
    public void search_result_should_contain_item_with(String priceLoverThan, String reviewScoreMoreThan) {
        log.info("Verifying search result list");
        BigDecimal maxPrice = new BigDecimal(priceLoverThan);
        BigDecimal minScore = new BigDecimal(reviewScoreMoreThan);
        List<SearchResultDto> searchResultDtoList = searchResultPage.getSearchResultDtoList();

        Matcher<Iterable<? super Object>> hotelMatcher = allOf(
                hasProperty("totalPrice", both(lessThan(maxPrice)).and(not(BigDecimal.ZERO))),
                hasProperty("reviewScore", greaterThan(minScore)
                ));

        Assert.assertThat("There is no item with Review Score > 8.0 and Price < 200 EURO in search results!",
                searchResultDtoList, hasItem(hotelMatcher));

        SearchResultDto searchResultDto = searchResultDtoList.stream()
                .filter(hotelMatcher::matches).findFirst().orElse(SearchResultDto.empty());
        log.info("The Hotel that satisfies to all the conditions is {}", searchResultDto);
    }
}
