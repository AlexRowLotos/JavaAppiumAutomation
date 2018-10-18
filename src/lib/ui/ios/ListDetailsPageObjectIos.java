package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ListDetailsPageObject;

public class ListDetailsPageObjectIos extends ListDetailsPageObject {

    static {
        ARTICLE_ELEMENT_TPL = "xpath://XCUIElementTypeCell/XCUIElementTypeLink[contains(@name,'(SUBSTRING)')]";
        EDIT_BUTTON = "xpath://XCUIElementTypeButton[contains(@name,'Edit')]";
        REMOVE_BUTTON = "xpath://XCUIElementTypeButton[contains(@name,'Remove')]";
    }

    public ListDetailsPageObjectIos(AppiumDriver driver){
        super(driver);
    }
}