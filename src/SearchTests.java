import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testCancelSearch() {
        String search_word = "Java";

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(search_word);
        searchPageObject.waitForSearchResult("Java");
        searchPageObject.clickCancelSearch();

        assertTrue(searchPageObject.isSearchEmpty(search_word));
    }

    @Test
    public void testCheckArticleTitle()  {
        String[] words = {
                "Java (programming language)",
                "Ruby (programming language)"
        };

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(words[0]);
        searchPageObject.waitForSearchResult(words[0]);
        searchPageObject.clickByArticleWithSubstring(words[0]);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        assertTrue(articlePageObject.assertTitleIsPresent());
    }
}
