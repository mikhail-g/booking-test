package com.hariachyi.automation.widgets;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObjectImpl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.util.List;

@Slf4j
@Getter
public class CalendarWidgetImpl extends WidgetObjectImpl implements CalendarWidget {

    @FindBy(className = "c2-month")
    List<MonthWidget> monthWidgetList;

    @FindBy(className = "c2-button-earlier")
    WebElementFacade earlierButton;

    @FindBy(className = "c2-button-further")
    WebElementFacade furtherButton;

    public CalendarWidgetImpl(PageObject page, ElementLocator locator, WebElement webElement, long timeoutInMilliseconds) {
        super(page, locator, webElement, timeoutInMilliseconds);
    }

    public CalendarWidgetImpl(PageObject page, ElementLocator locator, long timeoutInMilliseconds) {
        super(page, locator, timeoutInMilliseconds);
    }

    @Override
    public void selectDate(int monthIndex, int dayIndex) {
        log.info("Setting calendar with month index '{}' and dayIndex '{}'", monthIndex, dayIndex);
        List<WebElementFacade> cellList = this.monthWidgetList.get(monthIndex)
                .getDayList();
        cellList.get(dayIndex).click();
    }
}
