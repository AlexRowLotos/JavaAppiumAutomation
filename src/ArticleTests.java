import lib.CoreTestCase;

import lib.ui.ArticlePageObject;
import lib.ui.ListDetailsPageObject;
import lib.ui.MyListsPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.ListDetailsPageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.ios.OnboardingPageObjectIos;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {


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

        if(Platform.getInstance().isIOS()){
            OnboardingPageObjectIos onboardingPageObject = new OnboardingPageObjectIos(driver);
            onboardingPageObject.skipOnboarding();
        }

        SearchPageObject searchPageObject = SearchPageObjectFactory.getPage(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(words[0]);
        searchPageObject.waitForSearchResult(words[0]);
        searchPageObject.clickByArticleWithSubstring(words[0]);

        ArticlePageObject articlePageObject =  ArticlePageObjectFactory.getPage(driver);

        articlePageObject.waitForTitleElemet();
        articlePageObject.addArticleToMyNewList(myList);
        articlePageObject.returnToSearch();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(words[1]);
        searchPageObject.waitForSearchResult(words[1]);
        searchPageObject.clickByArticleWithSubstring(words[1]);

        if(Platform.getInstance().isAndroid()) {
            articlePageObject.waitForTitleElemet();
        }

        articlePageObject.addArticleToExistingList(myList);
        articlePageObject.returnToSearch();

        searchPageObject.goToMyLists();
        Thread.sleep(1000);

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.getPage(driver);

        if(Platform.getInstance().isIOS()) {
            myListsPageObject.openReadingListsTab();
        }

        myListsPageObject.openList(myList);

        ListDetailsPageObject listDetailsPageObject = ListDetailsPageObjectFactory.getPage(driver);
        listDetailsPageObject.deleteArticle(words[1]);

        assertFalse(listDetailsPageObject.isArticlePresent(words[1]));

        listDetailsPageObject.openArticle(words[0]);


        if(Platform.getInstance().isIOS()){
            articlePageObject.assertSubtitleIsPresent(words_subtitles[0]);
        }
        else {
            articlePageObject.assertTitleIsPresent(words[0]);
        }
    }
}