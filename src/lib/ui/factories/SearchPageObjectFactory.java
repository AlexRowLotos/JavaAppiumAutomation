package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.android.SearchPageObjectAndroid;
import lib.ui.ios.SearchPageObjectIos;

public class SearchPageObjectFactory {

    public static SearchPageObject getPage(AppiumDriver driver){
        if(Platform.getInstance().isAndroid()) {
            return new SearchPageObjectAndroid(driver);
        }
        else {
            return new SearchPageObjectIos(driver);
        }
    }
}
