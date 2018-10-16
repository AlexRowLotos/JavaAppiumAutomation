package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
        TITLE_ID = "id:org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
        MORE_OPTIONS_MENU = "xpath://android.widget.ImageView[@content-desc='More options']",
        ADD_TO_READING_LIST_OPTION = "xpath://*[@text='Add to reading list']",
        ONBOARDING_BUTTON = "id:org.wikipedia:id/onboarding_button",
        DIRECTORY_NAME_INPUT = "id:org.wikipedia:id/text_input",
        OK_BUTTON = "xpath://*[@text='OK']",
        RETURN_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
        EXISTED_LIST_TPL = "xpath://*[@class='android.widget.LinearLayout']//*[@text='(SUBSTRING)']";

    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    public WebElement waitForTitleElemet() {
        return this.waitForElementPresent(TITLE_ID, "no title of article",5);
    }

    public String getArticleTitle() {
        return waitForTitleElemet().getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(FOOTER_ELEMENT, "no footer", 20);
    }


    public void openMoreOptions() throws InterruptedException {
        this.waitForElementPresentAndClick(MORE_OPTIONS_MENU, "There is no more option menu", 5);
        Thread.sleep(1000);
    }

    public void clickAddToReadingListOption() {
        this.waitForElementPresentAndClick(ADD_TO_READING_LIST_OPTION, "there is no option", 5);
    }

    public void closeOnboarding() {
        this.waitForElementPresentAndClick(ONBOARDING_BUTTON, "there is no onboarding button", 5);
    }

    public void enterListName(String name) {
        this.waitForElementAndClear(DIRECTORY_NAME_INPUT,"there is no name input for clear", 5);
        this.waitForElementPresentAndSendKeys(DIRECTORY_NAME_INPUT, name,"there is no name input", 5);
        this.waitForElementPresentAndClick(OK_BUTTON, "there is no confrim name button", 5);
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
        this.waitForElementPresentAndClick(getExistingListElement(name), "no list",5);
    }

    public void returnToSearch() {
        this.waitForElementPresentAndClick(RETURN_BUTTON, "no return button", 5);
    }

    private static String getExistingListElement(String substring) {
        return  EXISTED_LIST_TPL.replace("(SUBSTRING)",substring);
    }

    public boolean assertArticleTitleIs(String name) {
        String articleTitle = this.getArticleTitle();
        return articleTitle.equals(name);
    }

    public boolean assertTitleIsPresent() {

        return assertElementPresent(TITLE_ID);
    }
}