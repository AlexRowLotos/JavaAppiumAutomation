package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    private static final String IOSPlatform = "ios";
    private static final String AndroidPlatform = "android";

    protected AppiumDriver driver;
    private static String AppiumUrl = "http://127.0.0.1:4723/wd/hub";

    @Before
    public void setUp() throws Exception {
        super.setUp();

        DesiredCapabilities capabilities = this.getDesiredCapabilitiesByPlatformEnv();
        driver = this.getDriverByPlatformEnv(AppiumUrl,capabilities);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();

        driver.quit();
    }

    private DesiredCapabilities getDesiredCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if(platform.equals(AndroidPlatform)){
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("deviceName","AndroidTestDevice");
            capabilities.setCapability("platformVersion","6.0");
            capabilities.setCapability("automationName","Appium");
            capabilities.setCapability("appPackage","org.wikipedia");
            capabilities.setCapability("appActivity",".main.MainActivity");
            capabilities.setCapability("orientation", "PORTRAIT"); //РЕШЕНИЕ ПРОБЛЕМЫ С  ОРИЕНТАЦИЕЙ
            capabilities.setCapability("app","/Users/evgenydylevsky/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
        }
        else if(platform.equals(IOSPlatform)) {
            capabilities.setCapability("platformName","iOS");
            capabilities.setCapability("platformVersion","12.0");
            capabilities.setCapability("deviceName","iPhone SE");
            capabilities.setCapability("orientation", "PORTRAIT"); //РЕШЕНИЕ ПРОБЛЕМЫ С  ОРИЕНТАЦИЕЙ
            capabilities.setCapability("app","/Users/evgenydylevsky/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        }
        else {
            throw new Exception("Can't get platform capabilities");
        }

        return capabilities;
    }

    private AppiumDriver getDriverByPlatformEnv(String appiumUrl, DesiredCapabilities capabilities) throws Exception {
        String platform = System.getenv("PLATFORM");
        AppiumDriver env_driver;

        if(platform.equals(IOSPlatform)){
            env_driver = (AppiumDriver) new IOSDriver(new URL(appiumUrl),capabilities);
        }
        else if(platform.equals(AndroidPlatform)){
            env_driver=(AppiumDriver) new AndroidDriver(new URL(appiumUrl),capabilities);
        }
        else{
            throw new Exception("Can't get platform driver");
        }

        return env_driver;
    }
}