package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.MainPageObject;

public class ListDetailsPageObject extends MainPageObject {
    public static String
            ARTICLE_ELEMENT_TPL,
            EDIT_BUTTON,
            REMOVE_BUTTON;

    public ListDetailsPageObject(AppiumDriver driver){
        super(driver);
    }

    private static String getArticleElement(String substring) {
        return ARTICLE_ELEMENT_TPL.replace("(SUBSTRING)",substring);
    }

    public void deleteArticle(String name) {
        if(Platform.getInstance().isAndroid()){
            this.swipeElemendToLeft(getArticleElement(name), "no selected article");
        }
        else {
            this.waitForElementPresentAndClick(EDIT_BUTTON, "bo edit button",5);
            this.waitForElementPresentAndClick(getArticleElement(name), "no selected article",5);
            this.waitForElementPresentAndClick(REMOVE_BUTTON,"no selected remove button", 5);
        }
    }

    public void openArticle(String name) {
        this.waitForElementPresentAndClick(getArticleElement(name), "no selected article",5);
    }

    public boolean isArticlePresent(String name) {
        return !this.waitForElementNotPresent(getArticleElement(name), "article present",5);
    }
}