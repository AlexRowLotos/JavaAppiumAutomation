package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;


public class SearchPageObjectIos extends SearchPageObject {

    static {
        SEARCH_INPUT_ELEMENT = "xpath://*[contains(@name,'Search Wikipedia')]";
        SEARCH_INPUT = SEARCH_INPUT_ELEMENT;
        SEARCH_RESULT_TPL = "xpath://XCUIElementTypeImage[@name='search']/ancestor::XCUIElementTypeOther//XCUIElementTypeCell/*[contains(@name,'(SUBSTRING)')]";
        SEARCH_RESULTS_ITEMS = "xpath://XCUIElementTypeImage[@name='search']/ancestor::XCUIElementTypeOther//XCUIElementTypeCell/XCUIElementTypeLink";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeButton[@name='Close']";
        NAVIGATE_MY_LISTS = "xpath://XCUIElementTypeButton[@name='Saved']";
        SEARCH_RESULT_DOUBLE_TPL = "xpath://*[android.widget.TextView[@index=0 and @text='(SUBSTRING_1)'] and android.widget.TextView[@index=1 and @text='(SUBSTRING_2)']]";
    }

    public SearchPageObjectIos(AppiumDriver driver)
    {
        super(driver);
    }
}