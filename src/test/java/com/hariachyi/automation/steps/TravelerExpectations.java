package com.hariachyi.automation.steps;

import com.hariachyi.automation.model.SearchResultDto;
import com.hariachyi.automation.pages.SearchResultPage;
import com.hariachyi.automation.widgets.search_page.SearchItemWidget;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.hamcrest.Matcher;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;

@Slf4j
public class TravelerExpectations extends ScenarioSteps {

    private SearchResultPage searchResultPage;

    public String getHotelName(SearchItemWidget searchItemWidget) {
        return searchItemWidget.getHotelName().getText();
    }

    /**
     * @param searchItemWidget search item widget
     * @return Review Score {@code BigDecimal} or {@code BigDecimal.ZERO} if there is no reviewScore for a
     * search result
     */
    public BigDecimal getReviewScore(SearchItemWidget searchItemWidget) {
        BigDecimal result = BigDecimal.ZERO;
        if (searchItemWidget.getReviewScore().isCurrentlyVisible()) {
            result = new BigDecimal(searchItemWidget.getReviewScore().getText().replace(",", "."));
        }
        return result;
    }

    /**
     * @return Total Price {@code BigDecimal} or {@code BigDecimal.ZERO} if there is no totalPriceMessage for a
     * search result
     */
    public BigDecimal getTotalPrice(SearchItemWidget searchItemWidget) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if (searchItemWidget.getTotalPrice().isCurrentlyVisible()) {
            String text = searchItemWidget.getTotalPrice().getText();
            String regex = "\\d[0-9,.]+";
            java.util.regex.Matcher matcher = Pattern.compile(regex, Pattern.MULTILINE).matcher(text);
            if (!matcher.find()) {
                throw new RuntimeException(String.format("No Total Price is found in '%s'", text));
            }
            totalPrice = new BigDecimal(matcher.group(0));
            if (matcher.find()) {
                throw new RuntimeException(String.format("More then one Total Price is found in '%s'", text));
            }
        }
        return totalPrice;
    }

    public List<SearchResultDto> getSearchResultDtoList() {
        return searchResultPage.getSearchItemWidgetList().stream()
                .map(this::getSearchResultDto)
                .collect(Collectors.toList());
    }

    public SearchResultDto getSearchResultDto(SearchItemWidget searchItemWidget) {
        String name = getHotelName(searchItemWidget);
        BigDecimal price = getTotalPrice(searchItemWidget);
        BigDecimal score = getReviewScore(searchItemWidget);
        log.info("Received item: name '{}', price '{}', score '{}'", name, price, score);
        return SearchResultDto.of(name, price, score);
    }

    @Step
    public void search_result_should_contain_item_with(String priceLoverThan, String reviewScoreMoreThan) {
        log.info("Verifying search result list");
        BigDecimal maxPrice = new BigDecimal(priceLoverThan);
        BigDecimal minScore = new BigDecimal(reviewScoreMoreThan);
        List<SearchResultDto> searchResultDtoList = getSearchResultDtoList();

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
