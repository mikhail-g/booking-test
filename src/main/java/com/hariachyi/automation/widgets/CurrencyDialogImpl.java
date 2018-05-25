package com.hariachyi.automation.widgets;

import lombok.Getter;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObjectImpl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.util.List;

@Getter
public class CurrencyDialogImpl extends WidgetObjectImpl implements CurrencyDialog {

    @FindBy(css = "a[data-currency='EUR']")
    WebElementFacade currencyEuro;

    @FindBy(css = "#current_currency_foldout .selsymbol")
    List<WebElementFacade> currencyCodeList;

    public CurrencyDialogImpl(PageObject page, ElementLocator locator, WebElement webElement, long timeoutInMilliseconds) {
        super(page, locator, webElement, timeoutInMilliseconds);
    }

    public CurrencyDialogImpl(PageObject page, ElementLocator locator, long timeoutInMilliseconds) {
        super(page, locator, timeoutInMilliseconds);
    }
}
