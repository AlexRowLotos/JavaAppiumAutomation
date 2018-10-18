package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ListDetailsPageObject;

public class ListDetailsPageObjectAndroid extends ListDetailsPageObject {

    static {
        ARTICLE_ELEMENT_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='(SUBSTRING)']";
    }

    public ListDetailsPageObjectAndroid(AppiumDriver driver){
        super(driver);
    }
}