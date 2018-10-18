package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.MyListsPageObject;
import lib.ui.android.MyListsPageObjectAndroid;
import lib.ui.ios.MyListsPageObjectIos;

public class MyListsPageObjectFactory {

    public static MyListsPageObject getPage(AppiumDriver driver){
        if(Platform.getInstance().isAndroid()) {
            return new MyListsPageObjectAndroid(driver);
        }
        else {
            return new MyListsPageObjectIos(driver);
        }
    }
}
