package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;

public class ListDetailsPageObjectIos extends MainPageObject {

    private static final String
        ARTICLE_ELEMENT_TPL = "xpath://XCUIElementTypeCell/XCUIElementTypeLink[contains(@name,'(SUBSTRING)')]",
        EDIT_BUTTON= "xpath://XCUIElementTypeButton[contains(@name,'Edit')]",
        REMOVE_BUTTON= "xpath://XCUIElementTypeButton[contains(@name,'Remove')]";

    public ListDetailsPageObjectIos(AppiumDriver driver){
        super(driver);
    }

    private static String getArticleElement(String substring) {
        return ARTICLE_ELEMENT_TPL.replace("(SUBSTRING)",substring);
    }

    public void deleteArticle(String name) {
        this.waitForElementPresentAndClick(EDIT_BUTTON, "bo edit button",5);
        this.waitForElementPresentAndClick(getArticleElement(name), "no selected article",5);
        this.waitForElementPresentAndClick(REMOVE_BUTTON,"no selected remove button", 5);
    }

    public void openArticle(String name) {
        this.waitForElementPresentAndClick(getArticleElement(name), "no selected article",5);
    }

    public boolean isArticlePresent(String name) {
       return !this.waitForElementNotPresent(getArticleElement(name), "article present",5);
    }
}