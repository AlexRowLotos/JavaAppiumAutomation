import lib.CoreTestCase;

import lib.ui.ios.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class IosArticleTest extends CoreTestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testAddTwoArticles() throws InterruptedException {
        String[] words = {
                "Java (programming language)",
                "C Sharp (programming language)"
        };

        String[] words_subtitles = {
                "Object-oriented programming language",
                "Multi-paradigm (object-oriented) programming language"
        };

        String myList = "OOP";
        OnboardingPageObjectIos onboardingPageObject = new OnboardingPageObjectIos(driver);
        SearchPageObjectIos searchPageObject = new SearchPageObjectIos(driver);

        if(onboardingPageObject.isOnboardingPresent()){
            onboardingPageObject.skipOnboarding();
        }

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(words[0]);
        searchPageObject.waitForSearchResult(words[0]);
        searchPageObject.clickByArticleWithSubstring(words[0]);

        ArticlePageObjectIos articlePageObject = new ArticlePageObjectIos(driver);

        articlePageObject.addArticleToMyNewList(myList);
        articlePageObject.returnToSearch();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(words[1]);
        searchPageObject.waitForSearchResult(words[1]);
        searchPageObject.clickByArticleWithSubstring(words[1]);

        articlePageObject.addArticleToExistingList(myList);
        articlePageObject.returnToSearch();

        searchPageObject.goToMyLists();
        Thread.sleep(1000);
        MyListsPageObjectIos myListsPageObject = new MyListsPageObjectIos(driver);
        myListsPageObject.openReadingListsTab();
        myListsPageObject.openList(myList);

        ListDetailsPageObjectIos listDetailsPageObject = new ListDetailsPageObjectIos(driver);
        listDetailsPageObject.deleteArticle(words[1]);

        assertFalse(listDetailsPageObject.isArticlePresent(words[1]));

        listDetailsPageObject.openArticle(words[0]);
        articlePageObject.assertSubtitleIsPresent(words[0]);
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }
}