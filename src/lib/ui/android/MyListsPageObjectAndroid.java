package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class MyListsPageObjectAndroid extends MyListsPageObject {

    static {
        LIST_ELEMENT_TPL = "xpath://*[@text='(SUBSTRING)']";
    }

    public MyListsPageObjectAndroid(AppiumDriver driver){
        super(driver);
    }
}
