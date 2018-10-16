package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;
import org.openqa.selenium.WebElement;

public class ArticlePageObjectIos extends MainPageObject {

    private static final String
        SUBTITLE_TEXT = "xpath://XCUIElementTypeStaticText[contains(@name,'(SUBSTRING)')]",
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
        MORE_OPTIONS_MENU = "xpath://XCUIElementTypeButton[@name='Share']",
        ADD_TO_READING_LIST_OPTION = "xpath://XCUIElementTypeOther[@name='ActivityListView']//XCUIElementTypeButton[@name='Add to reading list']",
        ADD_ARTICLE_BUTTON = "xpath://XCUIElementTypeButton[@name='Add']",
        ONBOARDING_BUTTON = "id:org.wikipedia:id/onboarding_button",
        DIRECTORY_NAME_INPUT = "xpath:(//XCUIElementTypeTextField)[1]",
        OK_BUTTON = "xpath:(//XCUIElementTypeTextField)[1]/ancestor::XCUIElementTypeOther/XCUIElementTypeButton",
        RETURN_BUTTON = "xpath://XCUIElementTypeButton[contains(@name,'Back')]",
        EXISTED_LIST_TPL = "xpath://XCUIElementTypeLink[@label='(SUBSTRING)']/parent::XCUIElementTypeCell";

    public ArticlePageObjectIos(AppiumDriver driver){
        super(driver);
    }

    private static String getSubtitleElement(String substring) {
        return SUBTITLE_TEXT.replace("(SUBSTRING)",substring);
    }

    public WebElement waitForSubtitleElement(String subtitle) {
        return this.waitForElementPresent(getSubtitleElement(subtitle), "no title of article",5);
    }

    public String getArticleTitle(String subtitle) {
        return waitForSubtitleElement(subtitle).getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(FOOTER_ELEMENT, "no footer", 20);
    }

    public void openMoreOptions() throws InterruptedException {
        this.waitForElementPresentAndClick(MORE_OPTIONS_MENU, "There is no more option menu", 5);
        Thread.sleep(3000);
    }

    public void clickAddToReadingListOption() throws InterruptedException {
        this.waitForElementPresentAndClick(ADD_TO_READING_LIST_OPTION, "there is no option", 5);
        Thread.sleep(3000);
    }

    public void closeOnboarding() {
        this.waitForElementPresentAndClick(ONBOARDING_BUTTON, "there is no onboarding button", 5);
    }

    public void enterListName(String name) {
        this.waitForElementPresentAndClick(ADD_ARTICLE_BUTTON, "there is no add button", 5);
        this.waitForElementPresentAndSendKeys(DIRECTORY_NAME_INPUT, name,"there is no name input", 5);
        this.waitForElementPresentAndClick(OK_BUTTON, "there is no confrim name button", 5);
    }

    public void addArticleToMyNewList(String listName) throws InterruptedException {
        openMoreOptions();
        clickAddToReadingListOption();
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

    public boolean assertSubtitleIsPresent(String subtitle) {

        return assertElementPresent(getSubtitleElement(subtitle));
    }
}