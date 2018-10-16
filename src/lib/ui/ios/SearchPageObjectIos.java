package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObjectIos extends MainPageObject {

    private static final String
        SEARCH_INPUT_ELEMENT = "xpath://*[contains(@name,'Search Wikipedia')]",
        SEARCH_INPUT = SEARCH_INPUT_ELEMENT,
        SEARCH_RESULT_TPL = "xpath://XCUIElementTypeImage[@name='search']/ancestor::XCUIElementTypeOther//XCUIElementTypeCell/*[contains(@name,'(SUBSTRING)')]",
        SEARCH_RESULTS_ITEMS = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']",
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeButton[@name='Close']",
        NAVIGATE_MY_LISTS = "xpath://XCUIElementTypeButton[@name='Saved']",
        SEARCH_RESULT_DOUBLE_TPL = "xpath://*[android.widget.TextView[@index=0 and @text='(SUBSTRING_1)'] and android.widget.TextView[@index=1 and @text='(SUBSTRING_2)']]";




    public SearchPageObjectIos(AppiumDriver driver){
        super(driver);
    }

    private static String getSearchResultElement(String substring) {
        return SEARCH_RESULT_TPL.replace("(SUBSTRING)",substring);
    }

    private static String getSearchResultElementByTwoConditions(String substring_1, String substring_2) {
        return SEARCH_RESULT_DOUBLE_TPL.replace("(SUBSTRING_1)",substring_1).replace("(SUBSTRING_2)",substring_2);
    }

    public void initSearchInput() {
        this.waitForElementPresentAndClick(SEARCH_INPUT_ELEMENT,
                "Can't find and search element", 5);
        this.waitForElementPresent(SEARCH_INPUT_ELEMENT,
                "Can't find search after click", 5);
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "no cancel button", 5);
    }

    public void waitForCancelButtonToDisaappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "no cancel button", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementPresentAndClick(SEARCH_CANCEL_BUTTON, "no cacncel button", 5);
    }

    public void typeSearchText(String search_line) {
        this.waitForElementPresentAndSendKeys(SEARCH_INPUT, search_line,"can't type into search input",5);
    }

    public void waitForSearchResult(String search_string) {
        this.waitForElementPresent(getSearchResultElement(search_string), "there are no search results", 5);
    }

    public List<WebElement> waitForSearchResults(String search_string) {
       return this.waitForElementsPresent(getSearchResultElement(search_string), "there are no search results", 5);
    }

    public void clickByArticleWithSubstring(String substring) {
        this.waitForElementPresentAndClick(getSearchResultElement(substring),"there are no search results", 5);
    }

    public boolean isSearchEmpty(String substring) {
        return this.waitForElementNotPresent(getSearchResultElement(substring),"there are search results", 5 );
    }

    public void goToMyLists() {
        this.waitForElementPresentAndClick(NAVIGATE_MY_LISTS, "no my lists menu",5);
    }

    public WebElement waitForElementByTitleAndDescription(String title, String description) {
        return this.waitForElementPresent(getSearchResultElementByTwoConditions(title,description), "results do not contain both correct parameters title and description" + title + description  , 10);
    }

    public  List<WebElement> getSearchResultsList() {
        return this.waitForElementsPresent(SEARCH_RESULTS_ITEMS, "no search results", 5);
    }
}