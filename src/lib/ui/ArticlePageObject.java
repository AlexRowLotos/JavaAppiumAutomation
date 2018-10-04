package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
        TITLE_ID = "org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "//*[@text='View page in browser']",
        MORE_OPTIONS_MENU = "//android.widget.ImageView[@content-desc='More options']",
        ADD_TO_READING_LIST_OPTION = "//*[@text='Add to reading list']",
        ONBOARDING_BUTTON = "org.wikipedia:id/onboarding_button",
        DIRECTORY_NAME_INPUT = "org.wikipedia:id/text_input",
        OK_BUTTON = "//*[@text='OK']",
        RETURN_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
        EXISTED_LIST_TPL = "//*[@class='android.widget.LinearLayout']//*[@text='(SUBSTRING)']";




    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    public WebElement waitForTitleElemet() {
        return this.waitForElementPresent(By.id(TITLE_ID), "no title of article",5);
    }

    public String getArticleTitle() {
        return waitForTitleElemet().getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "no footer", 20);
    }


    public void openMoreOptions() throws InterruptedException {
        this.waitForElementPresentAndClick(By.xpath(MORE_OPTIONS_MENU), "There is no more option menu", 5);
        Thread.sleep(1000);
    }

    public void clickAddToReadingListOption() {
        this.waitForElementPresentAndClick(By.xpath(ADD_TO_READING_LIST_OPTION), "there is no option", 5);
    }

    public void closeOnboarding() {
        this.waitForElementPresentAndClick(By.id(ONBOARDING_BUTTON), "there is no onboarding button", 5);
    }

    public void enterListName(String name) {
        this.waitForElementAndClear(By.id(DIRECTORY_NAME_INPUT),"there is no name input for clear", 5);
        this.waitForElementPresentAndSendKeys(By.id(DIRECTORY_NAME_INPUT), name,"there is no name input", 5);
        this.waitForElementPresentAndClick(By.xpath(OK_BUTTON), "there is no confrim name button", 5);
    }

    public void addArticleToMyNewList(String listName) throws InterruptedException {
        openMoreOptions();
        clickAddToReadingListOption();
        closeOnboarding();
        enterListName(listName);
    }

    public void addArticleToExistingList(String name) throws InterruptedException{
        openMoreOptions();
        clickAddToReadingListOption();
        this.waitForElementPresentAndClick(By.xpath(getExistingListElement(name)), "no list",5);
    }

    public void returnToSearch() {
        this.waitForElementPresentAndClick(By.xpath(RETURN_BUTTON), "no return button", 5);
    }

    private static String getExistingListElement(String substring) {
        return  EXISTED_LIST_TPL.replace("(SUBSTRING)",substring);
    }

    public boolean assertArticleTitleIs(String name) {
        String articleTitle = this.getArticleTitle();
        return articleTitle.equals(name);
    }

    public boolean assertTitleIsPresent() {
        return assertElementPresent(By.id(TITLE_ID));
    }
}
