package lib.ui.factories;
import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.android.ArticlePageObjectAndroid;
import lib.ui.ios.ArticlePageObjectIos;


public class ArticlePageObjectFactory {
    public static ArticlePageObject getPage(AppiumDriver driver){
        if(Platform.getInstance().isAndroid()) {
            return new ArticlePageObjectAndroid(driver);
        }
        else {
            return new ArticlePageObjectIos(driver);
        }
    }
}
