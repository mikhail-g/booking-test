package com.hariachyi.automation.pages;

import com.hariachyi.automation.widgets.header.HeaderWidget;
import com.hariachyi.automation.widgets.home_page.WelcomePopup;
import com.hariachyi.automation.widgets.search_form.SearchForm;
import lombok.Getter;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

@Getter
@DefaultUrl("https://www.booking.com/")
public class HomePage extends PageObject {

    @FindBy(id = "top")
    HeaderWidget header;

    @FindBy(css = "#frm")
    SearchForm searchForm;

    @FindBy(css = "#frm.-has-emk-subscribe-bar-below,#frm.-small")
    SearchForm searchFormExpanded;

    @FindBy(className = "ge-welcome-lightbox-container")
    WelcomePopup welcomePopup;

//    public SearchForm getSearchForm() {
//        if (searchFormExpanded.isPresent()) {
//            return searchFormExpanded;
//        }
//        return searchForm;
//    }


}
