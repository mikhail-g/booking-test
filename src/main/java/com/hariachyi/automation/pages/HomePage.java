package com.hariachyi.automation.pages;

import com.hariachyi.automation.widgets.HeaderWidget;
import com.hariachyi.automation.widgets.SearchForm;
import com.hariachyi.automation.widgets.WelcomePopup;
import lombok.Getter;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

@Getter
@DefaultUrl("https://www.booking.com/")
public class HomePage extends PageObject {

    @FindBy(id = "top")
    HeaderWidget header;

    @FindBy(id = "frm")
    SearchForm searchForm;

    @FindBy(className = "ge-welcome-lightbox-container")
    WelcomePopup welcomePopup;

}
