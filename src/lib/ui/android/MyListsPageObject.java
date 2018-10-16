package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    private static final String
        LIST_ELEMENT_TPL = "xpath://*[@name='(SUBSTRING)']";

    public MyListsPageObject(AppiumDriver driver){
        super(driver);
    }

    private static String getListElement(String substring) {
        return LIST_ELEMENT_TPL.replace("(SUBSTRING)",substring);
    }

    public void openList(String name) {
        this.waitForElementPresentAndClick(getListElement(name),"no list with selected name", 5);
    }
}
