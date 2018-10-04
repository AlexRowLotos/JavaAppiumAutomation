package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
        SEARCH_INPUT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
        SEARCH_INPUT = "//*[contains(@text,'Search…')]",
        SEARCH_RESULT_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='(SUBSTRING)']",
        SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
        NAVIGATE_MY_LISTS = "//android.widget.FrameLayout[@content-desc='My lists']";



    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    private static String getSearchResultElement(String substring) {
        return  SEARCH_RESULT_TPL.replace("(SUBSTRING)",substring);
    }

    public void initSearchInput() {
        this.waitForElementPresentAndClick(By.xpath(SEARCH_INPUT_ELEMENT),
                "Can't find and search element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INPUT_ELEMENT),
                "Can't find search after click", 5);
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "no cancel button", 5);
    }

    public void waitForCancelButtonToDisaappear(){
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "no cancel button", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementPresentAndClick(By.id(SEARCH_CANCEL_BUTTON), "no cacncel button", 5);
    }

    public void typeSearchText(String search_line) {
        this.waitForElementPresentAndSendKeys(By.xpath(SEARCH_INPUT), search_line,"can't type into search input",5);
    }

    public void waitForSearchResult(String search_string) {
        this.waitForElementPresent(By.xpath(getSearchResultElement(search_string)), "there are no search results", 5);
    }

    public void clickByArticleWithSubstring(String substring) {
        this.waitForElementPresentAndClick(By.xpath(getSearchResultElement(substring)), "there are no search results", 5);
    }

    public boolean isSearchEmpty(String substring) {
        return this.waitForElementNotPresent(By.xpath(getSearchResultElement(substring)), "there are search results", 5 );
    }

    public void goToMyLists() {
        this.waitForElementPresentAndClick(By.xpath(NAVIGATE_MY_LISTS), "no my lists menu",5);
    }
}