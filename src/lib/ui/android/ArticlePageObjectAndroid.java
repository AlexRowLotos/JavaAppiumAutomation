package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class ArticlePageObjectAndroid extends ArticlePageObject {

    static {
        TITLE_ID = "id:org.wikipedia:id/view_page_title_text";
        SUBTITLE_TEXT = "xpath://XCUIElementTypeStaticText[contains(@name,'(SUBSTRING)')]";
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
        MORE_OPTIONS_MENU = "xpath://android.widget.ImageView[@content-desc='More options']";
        ADD_TO_READING_LIST_OPTION = "xpath://*[@text='Add to reading list']";
        ADD_ARTICLE_BUTTON = "id:org.wikipedia:id/create_button";
        ONBOARDING_BUTTON = "id:org.wikipedia:id/onboarding_button";
        DIRECTORY_NAME_INPUT = "id:org.wikipedia:id/text_input";
        OK_BUTTON = "xpath://*[@text='OK']";
        RETURN_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        EXISTED_LIST_TPL = "xpath://*[@class='android.widget.LinearLayout']//*[@text='(SUBSTRING)']";
    }

    public ArticlePageObjectAndroid(AppiumDriver driver){
        super(driver);
    }
}