package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;

public class CoreTestCase extends TestCase {
    protected AppiumDriver driver;
    protected Platform Platform;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        driver = Platform.getInstance().getDriver();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();

        driver.quit();
    }
}