import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

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


    @Test
    public void testMultipleConditionsSearch() throws Exception {
        String[] correctResult_1 = {
                "Java",
                "Island of Indonesia"
        };

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(correctResult_1[0]);
        List<WebElement> target_results = searchPageObject.getSearchResultsList();

        searchPageObject.waitForElementByTitleAndDescription(correctResult_1[0], correctResult_1[1]);

        for(int i=0; i<3; i++) {
            assertTrue("element "  + i + " is not correct search result", target_results.get(i).getText().equals(correctResult_1[0]));
        }

    }

}
