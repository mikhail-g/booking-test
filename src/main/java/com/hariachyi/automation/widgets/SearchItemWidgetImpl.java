package com.hariachyi.automation.widgets;

import lombok.Getter;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObjectImpl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class SearchItemWidgetImpl extends WidgetObjectImpl implements SearchItemWidget {

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

    public BigDecimal getTotalPrice() {
        String regex = "\\d[0-9,.]+";
        String text = totalPriceMessage.getText();
        Matcher matcher = Pattern.compile(regex, Pattern.MULTILINE).matcher(text);
        if (!matcher.find()) {
            throw new RuntimeException(String.format("No Total Price is found in '%s'", text));
        }
        BigDecimal totalPrice = new BigDecimal(matcher.group(0));
        if (matcher.find()) {
            throw new RuntimeException(String.format("More then one Total Price is found in '%s'", text));
        }
        return totalPrice;
    }
}
