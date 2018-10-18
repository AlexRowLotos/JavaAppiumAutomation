import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {

    @Test
    public void testMultipleConditionsSearch() throws Exception {
        String[] correctResults = {
                "Java",
                "Java (programming language)",
                "JavaScript"
        };

        SearchPageObject searchPageObject = SearchPageObjectFactory.getPage(driver);

        searchPageObject.skipOnboardingForIos();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(correctResults[0]);
        List<WebElement> target_results = searchPageObject.getSearchResultsList();
        assertTrue(target_results.size() > 2);

        for(int i=0; i<3; i++) {
            assertTrue("element "  + i + " is not correct search result", target_results.get(i).getText().contains(correctResults[i]));
        }
    }
}