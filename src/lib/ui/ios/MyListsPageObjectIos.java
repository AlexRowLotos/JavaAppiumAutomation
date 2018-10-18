package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class MyListsPageObjectIos extends MyListsPageObject {

    static {
        READING_LIST_TAB = "xpath://XCUIElementTypeButton[@name='Reading lists']";
        LIST_ELEMENT_TPL = "xpath://XCUIElementTypeCell/XCUIElementTypeLink[contains(@name,'(SUBSTRING)')]";
    }

    public MyListsPageObjectIos(AppiumDriver driver){
        super(driver);
    }
}
