package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;
import org.openqa.selenium.By;

public class OnboardingPageObjectIos extends MainPageObject {

    private static String
    SKIP_BUTTON = "xpath://*[contains(@name,'Skip')]";

    public OnboardingPageObjectIos(AppiumDriver driver)
    {
        super(driver);
    }

    public void skipOnboarding(){
        this.waitForElementPresentAndClick(SKIP_BUTTON, "no skip button", 5);
    }

    public boolean isOnboardingPresent()
    {
        return this.assertElementPresent(SKIP_BUTTON);
    }
}
