import lib.CoreTestCase;

import lib.ui.ArticlePageObject;
import lib.ui.ListDetailsPageObject;
import lib.ui.MyListsPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;



public class ArticleTests extends CoreTestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testAddTwoArticles() throws InterruptedException {
        String[] words = {
                "Java (programming language)",
                "Ruby (programming language)"
        };

        String myList = "OOP";

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(words[0]);
        searchPageObject.waitForSearchResult(words[0]);
        searchPageObject.clickByArticleWithSubstring(words[0]);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        articlePageObject.waitForTitleElemet();
        articlePageObject.addArticleToMyNewList(myList);
        articlePageObject.returnToSearch();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(words[1]);
        searchPageObject.waitForSearchResult(words[1]);
        searchPageObject.clickByArticleWithSubstring(words[1]);

        articlePageObject.waitForTitleElemet();
        articlePageObject.addArticleToExistingList(myList);
        articlePageObject.returnToSearch();

        searchPageObject.goToMyLists();
        Thread.sleep(1000);
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openList(myList);

        ListDetailsPageObject listDetailsPageObject = new ListDetailsPageObject(driver);
        listDetailsPageObject.deleteArticle(words[1]);

        assertFalse(listDetailsPageObject.isArticlePresent(words[1]));

        listDetailsPageObject.openArticle(words[0]);
        articlePageObject.assertArticleTitleIs(words[0]);
    }


}
