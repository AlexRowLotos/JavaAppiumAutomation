package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ListDetailsPageObject extends MainPageObject {

    private static final String
        ARTICLE_ELEMENT_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='(SUBSTRING)']";


    public ListDetailsPageObject(AppiumDriver driver){
        super(driver);
    }


    private static String getArticleElement(String substring) {
        return  ARTICLE_ELEMENT_TPL.replace("(SUBSTRING)",substring);
    }

    public void deleteArticle(String name) {
        this.swipeElemendToLeft(By.xpath(getArticleElement(name)), "no selected article");
    }

    public void openArticle(String name) {
        this.waitForElementPresentAndClick(By.xpath(getArticleElement(name)), "no selected article",5);
    }

    public boolean isArticlePresent(String name) {
       return !this.waitForElementNotPresent(By.xpath(getArticleElement(name)), "article present",5);
    }

}
