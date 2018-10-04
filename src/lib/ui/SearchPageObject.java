package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String
        SEARCH_INPUT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
        SEARCH_INPUT = "//*[contains(@text,'Search…')]",
        SEARCH_RESULT_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='(SUBSTRING)']",
        SEARCH_RESULTS_ITEMS = "//*[@resource-id='org.wikipedia:id/page_list_item_title']",
        SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
        NAVIGATE_MY_LISTS = "//android.widget.FrameLayout[@content-desc='My lists']",
        SEARCH_RESULT_DOUBLE_TPL = "//*[android.widget.TextView[@index=0 and @text='(SUBSTRING_1)'] and android.widget.TextView[@index=1 and @text='(SUBSTRING_2)']]";




    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    private static String getSearchResultElement(String substring) {
        return  SEARCH_RESULT_TPL.replace("(SUBSTRING)",substring);
    }

    private static String getSearchResultElementByTwoConditions(String substring_1, String substring_2) {
        return  SEARCH_RESULT_DOUBLE_TPL.replace("(SUBSTRING_1)",substring_1).replace("(SUBSTRING_2)",substring_2);
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

    public List<WebElement> waitForSearchResults(String search_string) {
       return this.waitForElementsPresent(By.xpath(getSearchResultElement(search_string)), "there are no search results", 5);
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

    public WebElement waitForElementByTitleAndDescription(String title, String description) {
        return this.waitForElementPresent(By.xpath(getSearchResultElementByTwoConditions(title,description)), "results do not contain both correct parameters title and description" + title + description  , 10);
    }

    public  List<WebElement> getSearchResultsList() {

        return this.waitForElementsPresent(By.xpath(SEARCH_RESULTS_ITEMS), "no search results", 5);

    }
}