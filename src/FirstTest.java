import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/evgenydylevsky/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkSearchlaceholderText() {
        waitForElementPresentAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search field is not present",
                5);
        waitIsPlaceholderSearchPresent("Can't find placeholder 'Search…'",
                5);
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutValue) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutValue);
        wait.withMessage(error_message);

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(error_message);

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresentAndClick(By by, String error_message, long timeoutValue) {
        WebElement element = waitForElementPresent(by , error_message, timeoutValue);
        element.click();

        return element;
    }

    private WebElement waitForElementPresentAndSendKeys(By by, String value, String error_message, long timeoutValue) {
        WebElement element = waitForElementPresent(by, error_message, timeoutValue);
        element.sendKeys(value);

        return element;
    }

    private boolean waitForElementNotPresent(By by, String  error_message, long timeoutValue) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutValue);
        wait.withMessage(error_message);

        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutValue) {
        WebElement element = waitForElementPresent(by, error_message, timeoutValue);
        element.clear();

        return element;
    }

    //Метод для проверки наличия плейсходера в поле поиска
    private Boolean waitIsPlaceholderSearchPresent(String error_message, long timeoutValue) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutValue);
        wait.withMessage(error_message);
        By by = (By.id("org.wikipedia:id/search_src_text"));

        return wait.until(ExpectedConditions.attributeContains(by,"text", "Search…"));
    }
}
