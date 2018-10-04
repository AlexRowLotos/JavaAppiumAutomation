package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    private static final String
        LIST_ELEMENT_TPL = "//*[@text='(SUBSTRING)']";

    public MyListsPageObject(AppiumDriver driver){
        super(driver);
    }

    private static String getListElement(String substring) {
        return  LIST_ELEMENT_TPL.replace("(SUBSTRING)",substring);
    }

    public void openList(String name) {
        this.waitForElementPresentAndClick(By.xpath(getListElement(name)),"no list with selected name", 5);
    }
}
