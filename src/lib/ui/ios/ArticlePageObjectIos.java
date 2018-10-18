package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class ArticlePageObjectIos extends ArticlePageObject {

  static {
      TITLE_ID = "xpath:(//XCUIElementTypeOther/XCUIElementTypeStaticText)[1]";
      SUBTITLE_TEXT = "xpath://XCUIElementTypeStaticText[contains(@name,'(SUBSTRING)')]";
      FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
      MORE_OPTIONS_MENU = "xpath://XCUIElementTypeButton[@name='Share']";
      ADD_TO_READING_LIST_OPTION = "xpath://XCUIElementTypeOther[@name='ActivityListView']//XCUIElementTypeButton[@name='Add to reading list']";
      ADD_ARTICLE_BUTTON = "xpath://XCUIElementTypeButton[@name='Add']";
      ONBOARDING_BUTTON = "id:org.wikipedia:id/onboarding_button";
      DIRECTORY_NAME_INPUT = "xpath:(//XCUIElementTypeTextField)[1]";
      OK_BUTTON = "xpath:(//XCUIElementTypeTextField)[1]/ancestor::XCUIElementTypeOther/XCUIElementTypeButton";
      RETURN_BUTTON = "xpath://XCUIElementTypeButton[contains(@name,'Back')]";
      EXISTED_LIST_TPL = "xpath://XCUIElementTypeLink[@label='(SUBSTRING)']/parent::XCUIElementTypeCell";
  }

    public ArticlePageObjectIos(AppiumDriver driver){
        super(driver);
    }
}