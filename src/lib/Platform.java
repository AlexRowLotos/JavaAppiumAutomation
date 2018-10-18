package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class Platform {
    private static final String IOSPlatform = "ios";
    private static final String AndroidPlatform = "android";

    protected AppiumDriver driver;
    private static String AppiumUrl = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;

    private Platform()
    {

    }

    public static Platform getInstance(){
        if(instance == null){
            instance = new Platform();
        }

        return instance;
    }

    public AppiumDriver getDriver() throws Exception {
        URL URL = new URL(AppiumUrl);

        if(this.isAndroid()) {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        }

        else if(this.isIOS()) {
            return new IOSDriver(URL, this.getIOSDesiredCapabilities());
        }

        else {
            throw new Exception("can't get correct driver");
        }
    }

    public boolean isAndroid() {
        return isPlatform(AndroidPlatform);
    }

    public boolean isIOS() {
        return isPlatform(IOSPlatform);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("orientation", "PORTRAIT"); //РЕШЕНИЕ ПРОБЛЕМЫ С  ОРИЕНТАЦИЕЙ
        capabilities.setCapability("app","/Users/evgenydylevsky/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("platformVersion","12.0");
        capabilities.setCapability("deviceName","iPhone SE");
        capabilities.setCapability("orientation", "PORTRAIT"); //РЕШЕНИЕ ПРОБЛЕМЫ С  ОРИЕНТАЦИЕЙ
        capabilities.setCapability("app","/Users/evgenydylevsky/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");

        return capabilities;
    }

    private Boolean isPlatform(String myPlatform){
        String platofrm = this.getPlatformVar();

        return myPlatform.equals(platofrm);
    }

    private String getPlatformVar() {
        return System.getenv("PLATFORM");
    }
}
