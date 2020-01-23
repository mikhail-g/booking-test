package com.hariachyi.automation.widgets.search_form.calendar;

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

    @FindBy(className = "bui-calendar__dates")
    List<MonthWidget> monthWidgetList;

    @FindBy(className = "bui-calendar__control--prev")
    WebElementFacade earlierButton;

    @FindBy(className = "bui-calendar__control--next")
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
        for (int i = 2; i <= monthIndex; i++) {
            log.info("Clicking next month button");
            furtherButton.click();
        }
        List<WebElementFacade> cellList = this.monthWidgetList.get(monthIndex)
                .getDayList();
        cellList.get(dayIndex).click();
    }
}
