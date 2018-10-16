package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {

        this.driver = driver;
    }


    public int getAmountOfSearchElements(String locator ) {

        List elements = driver.findElements(getLocatorString(locator));
        return elements.size();
    }

    public boolean assertElementPresent(String locator) {
        Boolean result = !driver.findElements(getLocatorString(locator)).isEmpty();

        return result;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutValue) {
        By by = this.getLocatorString(locator);

        WebDriverWait wait = new WebDriverWait(driver, timeoutValue);
        wait.withMessage(error_message);

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String error_message) {
        By by = this.getLocatorString(locator);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(error_message);

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(By by, String error_message) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(error_message);

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
//
//    public WebElement waitForElementPresentAndClick(By by, String error_message, long timeoutValue) {
//        WebElement element = waitForElementPresent(by, error_message, timeoutValue);
//        element.click();
//
//        return element;
//    }

    public WebElement waitForElementPresentAndClick(String locator, String error_message, long timeoutValue) {
        WebElement element = waitForElementPresent(locator , error_message, timeoutValue);
        element.click();

        return element;
    }

    public WebElement waitForElementPresentAndSendKeys(String locator, String value, String error_message, long timeoutValue) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutValue);
        element.sendKeys(value);

        return element;
    }

    public boolean waitForElementNotPresent(String locator, String  error_message, long timeoutValue) {
        By by = this.getLocatorString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutValue);
        wait.withMessage(error_message);

        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutValue) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutValue);
        element.clear();

        return element;
    }

    //Метод для проверки наличия плейсходера в поле поиска
    public Boolean waitIsPlaceholderSearchPresent(String error_message, long timeoutValue) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutValue);
        wait.withMessage(error_message);
        By by = (By.id("org.wikipedia:id/search_src_text"));

        return wait.until(ExpectedConditions.attributeContains(by,"text", "Search…"));
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(150)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes){
        int already_swiped = 0;
        while(driver.findElements(getLocatorString(locator)).size() == 0) {

            if(already_swiped > max_swipes) {
                waitForElementPresent(locator, "cannot find via swipe" + error_message, 5);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElemendToLeft(String locator, String error_message) {
        WebElement element = waitForElementPresent(locator, error_message, 10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y)/2;

        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(right_x, middle_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(150)))
                .moveTo(PointOption.point(left_x,middle_y))
                .release()
                .perform();
    }

    protected void swipeElemendToLeftFull(String locator, String error_message, int swipeCount) {
        WebElement element = waitForElementPresent(locator, error_message, 10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y)/2;

        TouchAction action = new TouchAction(driver);
        for(int i = 0; i<swipeCount; i++) {
            action
                    .press(PointOption.point(right_x, middle_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(150)))
                    .moveTo(PointOption.point(left_x, middle_y))
                    .release()
                    .perform();
        }
    }

    public String getAttributeValueOfElement(String locator, String attribute) {
        WebElement element = waitForElementPresent(locator, "Can't parse attribute");
        String attributeValue = element.getAttribute(attribute);

        return attributeValue;
    }


    public List<WebElement> waitForElementsPresent(String locator, String error_message, long timeoutValue) {
        By by = getLocatorString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutValue);
        wait.withMessage(error_message);

        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    private By getLocatorString(String locator_with_type) {

        String[] expoded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String byType = expoded_locator[0];
        String locator = expoded_locator[1];

        if (byType.equals("xpath")) {
            return By.xpath(locator);
        }
        else if(byType.equals("id")) {
            return By.id(locator);
        }
        else {
            throw new IllegalArgumentException("can't decide locator type");
        }
    }
}