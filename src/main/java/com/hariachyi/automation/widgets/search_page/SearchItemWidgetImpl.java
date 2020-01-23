package com.hariachyi.automation.widgets.search_page;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObjectImpl;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

@Slf4j
@Getter
public class SearchItemWidgetImpl extends WidgetObjectImpl implements SearchItemWidget {

    @FindBy(className = "sr-hotel__name")
    WebElementFacade hotelName;

    @FindBy(className = "bui-review-score__badge")
    WebElementFacade reviewScore;

    @FindBy(className = "bui-price-display__value")
    WebElementFacade totalPriceMessage;

    public SearchItemWidgetImpl(PageObject page, ElementLocator locator, WebElement webElement, long timeoutInMilliseconds) {
        super(page, locator, webElement, timeoutInMilliseconds);
    }

    public SearchItemWidgetImpl(PageObject page, ElementLocator locator, long timeoutInMilliseconds) {
        super(page, locator, timeoutInMilliseconds);
    }

    @Override
    public WebElementFacade getHotelName() {
        return hotelName;
    }

    /**
     * @return Review Score {@code BigDecimal} or {@code BigDecimal.ZERO} if there is no reviewScore for a
     * search result
     */
    @Override
    public WebElementFacade getReviewScore() {
        return reviewScore;
    }

    /**
     * @return Total Price {@code BigDecimal} or {@code BigDecimal.ZERO} if there is no totalPriceMessage for a
     * search result
     */
    @Override
    public WebElementFacade getTotalPrice() {
        ((JavascriptExecutor) getPage().getDriver()).executeScript("arguments[0].scrollIntoView(true);", hotelName);
        return totalPriceMessage;
    }
}
