package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ListDetailsPageObject;
import lib.ui.android.ListDetailsPageObjectAndroid;
import lib.ui.ios.ListDetailsPageObjectIos;

public class ListDetailsPageObjectFactory {
    public static ListDetailsPageObject getPage(AppiumDriver driver){
        if(Platform.getInstance().isAndroid()) {
            return new ListDetailsPageObjectAndroid(driver);
        }
        else {
            return new ListDetailsPageObjectIos(driver);
        }
    }
}
