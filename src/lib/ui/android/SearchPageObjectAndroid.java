package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class SearchPageObjectAndroid extends SearchPageObject {

    static {
        SEARCH_INPUT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]";
        SEARCH_RESULT_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='(SUBSTRING)']";
        SEARCH_RESULTS_ITEMS = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        NAVIGATE_MY_LISTS = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
        SEARCH_RESULT_DOUBLE_TPL = "xpath://*[android.widget.TextView[@index=0 and @text='(SUBSTRING_1)'] and android.widget.TextView[@index=1 and @text='(SUBSTRING_2)']]";
    }

    public SearchPageObjectAndroid(AppiumDriver driver)
    {
        super(driver);
    }
}