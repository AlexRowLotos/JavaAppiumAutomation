package lib.ui;

import io.appium.java_client.AppiumDriver;

public class MyListsPageObject extends MainPageObject {

    public static String
            READING_LIST_TAB,
            LIST_ELEMENT_TPL;

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openReadingListsTab(){
        this.waitForElementPresentAndClick(READING_LIST_TAB, "there is no reading lists tab", 5);
    }
    private static String getListElement(String substring) {
        return LIST_ELEMENT_TPL.replace("(SUBSTRING)",substring);
    }

    public void openList(String name) {
        this.waitForElementPresentAndClick(getListElement(name),"no list with selected name", 5);
    }
}