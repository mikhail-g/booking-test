package com.hariachyi.automation.widgets.search_page;

import com.hariachyi.automation.model.SearchResultDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObjectImpl;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Getter
public class SearchItemWidgetImpl extends WidgetObjectImpl implements SearchItemWidget {

    @FindBy(className = "sr-hotel__name")
    WebElementFacade hotelName;

    @FindBy(className = "review-score-badge")
    WebElementFacade reviewScore;

    @FindBy(className = "totalPrice")
    WebElementFacade totalPriceMessage;

    public SearchItemWidgetImpl(PageObject page, ElementLocator locator, WebElement webElement, long timeoutInMilliseconds) {
        super(page, locator, webElement, timeoutInMilliseconds);
    }

    public SearchItemWidgetImpl(PageObject page, ElementLocator locator, long timeoutInMilliseconds) {
        super(page, locator, timeoutInMilliseconds);
    }

    @Override
    public String getHotelName() {
        return hotelName.getText();
    }

    /**
     * @return Review Score {@code BigDecimal} or {@code BigDecimal.ZERO} if there is no reviewScore for a
     * search result
     */
    @Override
    public BigDecimal getReviewScore() {
        BigDecimal result = BigDecimal.ZERO;
        if (reviewScore.isCurrentlyVisible()) {
            result = new BigDecimal(reviewScore.getText().replace(",", "."));
        }
        return result;
    }

    /**
     * @return Total Price {@code BigDecimal} or {@code BigDecimal.ZERO} if there is no totalPriceMessage for a
     * search result
     */
    @Override
    public BigDecimal getTotalPrice() {
        ((JavascriptExecutor) getPage().getDriver()).executeScript("arguments[0].scrollIntoView(true);", hotelName);
        BigDecimal totalPrice = BigDecimal.ZERO;
        if (totalPriceMessage.isCurrentlyVisible()) {
            String text = totalPriceMessage.getText();
            String regex = "\\d[0-9,.]+";
            Matcher matcher = Pattern.compile(regex, Pattern.MULTILINE).matcher(text);
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

    @Override
    public SearchResultDto getSearchResultDto() {
        String name = getHotelName();
        BigDecimal price = getTotalPrice();
        BigDecimal score = getReviewScore();
        log.info("Received item: name '{}', price '{}', score '{}'", name, price, score);
        return SearchResultDto.of(name, price, score);
    }
}
