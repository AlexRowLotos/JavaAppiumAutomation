package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;

public class MyListsPageObjectIos extends MainPageObject {

    private static final String
        READING_LIST_TAB = "xpath://XCUIElementTypeButton[@name='Reading lists']",
        LIST_ELEMENT_TPL = "xpath://XCUIElementTypeCell/XCUIElementTypeLink[contains(@name,'(SUBSTRING)')]";


    public MyListsPageObjectIos(AppiumDriver driver){
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
