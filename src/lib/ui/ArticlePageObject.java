package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

        public static String
                TITLE_ID,
                SUBTITLE_TEXT,
                FOOTER_ELEMENT,
                MORE_OPTIONS_MENU,
                ADD_TO_READING_LIST_OPTION,
                ADD_ARTICLE_BUTTON,
                ONBOARDING_BUTTON,
                DIRECTORY_NAME_INPUT,
                OK_BUTTON,
                RETURN_BUTTON,
                EXISTED_LIST_TPL;

        public ArticlePageObject(AppiumDriver driver){
            super(driver);
        }

        private static String getSubtitleElement(String substring) {
            return SUBTITLE_TEXT.replace("(SUBSTRING)",substring);
        }

        private static String getTitleElement(String substring) {
            return TITLE_ID.replace("(SUBSTRING)",substring);
        }

        public WebElement waitForSubtitleElement(String subtitle) {
            return this.waitForElementPresent(getSubtitleElement(subtitle), "no title of article",5);
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
            if(Platform.getInstance().isIOS()) {
                this.waitForElementPresentAndClick(ADD_ARTICLE_BUTTON, "there is no add button", 5);
            }
            else {
                this.waitForElementAndClear(DIRECTORY_NAME_INPUT,"there is no name input", 5);
            }
            this.waitForElementPresentAndSendKeys(DIRECTORY_NAME_INPUT, name,"there is no name input", 5);
            this.waitForElementPresentAndClick(OK_BUTTON, "there is no confrim name button", 5);
        }

        public void addArticleToMyNewList(String listName) throws InterruptedException {
            openMoreOptions();
            clickAddToReadingListOption();

            if(Platform.getInstance().isAndroid()){
                closeOnboarding();
            }

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

    public boolean assertTitleIsPresent(String title) {

        return getArticleTitle().equals(title);
    }
}
