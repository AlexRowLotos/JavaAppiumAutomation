package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;

public class ListDetailsPageObject extends MainPageObject {

    private static final String
        ARTICLE_ELEMENT_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='(SUBSTRING)']";

    public ListDetailsPageObject(AppiumDriver driver){
        super(driver);
    }

    private static String getArticleElement(String substring) {
        return ARTICLE_ELEMENT_TPL.replace("(SUBSTRING)",substring);
    }

    public void deleteArticle(String name) {
        this.swipeElemendToLeft(getArticleElement(name), "no selected article");
    }

    public void openArticle(String name) {
        this.waitForElementPresentAndClick(getArticleElement(name), "no selected article",5);
    }

    public boolean isArticlePresent(String name) {
       return !this.waitForElementNotPresent(getArticleElement(name), "article present",5);
    }
}