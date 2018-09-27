import com.gargoylesoftware.htmlunit.javascript.host.Touch;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.apache.xpath.operations.Bool;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

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
        capabilities.setCapability("orientation", "PORTRAIT"); //РЕШЕНИЕ ПРОБЛЕМЫ С  ОРИЕНТАЦИЕЙ
        capabilities.setCapability("app","/Users/evgenydylevsky/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }


    private int getAmountOfSearchElements(By by ) {

        List elements = driver.findElements(by);
        return elements.size();
    }


    @Test
    public void saveTwoArticlesTest2() throws InterruptedException {
        String[] words = {
                "Java (programming language)",
                "Ruby (programming language)"
        };

        //====== Поиск и открытие статьи
        waitForElementPresentAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "There is no search",
                5);

        waitForElementPresentAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"),
                words[1],
                "There is no search field",
                5);

        waitForElementPresentAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + words[1] + "']"),
                "There is no expected search result",
                5);
        driver.rotate(ScreenOrientation.LANDSCAPE);
        Thread.sleep(1000);
        By titleSelector = By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']");

        Assert.assertTrue("Title is not present", false); //assertElementPresent(titleSelector));

    }

    @Test
    public void saveTwoArticlesTest() throws InterruptedException {
        String[] words = {
                "Java (programming language)",
                "Ruby (programming language)"
        };

        //====== Поиск и открытие статьи
        waitForElementPresentAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "There is no search",
                5);

        waitForElementPresentAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"),
                words[0],
                "There is no search field",
                5);

        waitForElementPresentAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + words[0] + "']"),
                "There is no expected search result",
                5);

        By titleSelector = By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']");

        Assert.assertFalse("Title is not present",assertElementPresent(titleSelector));
    }



    private boolean assertElementPresent(By by) {
      Boolean result = !driver.findElements(by).isEmpty();

      return result;
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

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes){
        int already_swiped = 0;
         while(driver.findElements(by).size() == 0) {

             if(already_swiped > max_swipes) {
                 waitForElementPresent(by, "cannot find via swipe" + error_message, 5);
                 return;
             }

             swipeUpQuick();
             ++already_swiped;
         }
    }

    protected void swipeElemendToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y)/2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction()
                .waitAction(150)
                .moveTo(left_x,middle_y)
                .release()
                .perform();
    }

    private String getAttributeValueOfElement(By by, String attribute) {
        WebElement element = waitForElementPresent(by, "Can't parse attribute");
        String attributeValue = element.getAttribute(attribute);

        return attributeValue;
    }
}
