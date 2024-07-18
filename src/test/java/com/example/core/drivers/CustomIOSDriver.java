package com.example.core.drivers;

import com.example.core.Framework;
import com.example.core.profile.ProfileManager;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

import static com.example.core.Framework.profileCapabilities;

public class CustomIOSDriver extends CustomDriver {

    private static final String BUNDLEID = "";
    private static final String PLATFORM_NAME = "ios";
    private static final String AUTOMATION_NAME = "XCUITest";
    private static final String APPLICATION_NAME = "Apiumhub";
    private static final String REMOTE_LOCATION = "remote";


    public static IOSDriver driver;
    private static final String LOCAL_URL = "http://127.0.0.1:4723/wd/hub";

    public static final Logger logger = LoggerFactory.getLogger(CustomIOSDriver.class.getName());

    public CustomIOSDriver() {
        if (ProfileManager.getProfileProperties(Framework.profile, "location").equals(REMOTE_LOCATION)) {

            /*
            * Set iOS capabilities in remote simulator in BrowserStack, Sauce Labs, etc.
            * Also set URL to access the application remotely.
            * */

        } else {
            URL url = null;
            try {
                url = new URL(LOCAL_URL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            DesiredCapabilities iOScapabilities = new DesiredCapabilities();
            Map<String, String> loadedCaps = profileCapabilities();
            iOScapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
            iOScapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, loadedCaps.get("platform_version"));
            iOScapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, loadedCaps.get("device_name"));
            iOScapabilities.setCapability(MobileCapabilityType.UDID, loadedCaps.get("udid"));
            iOScapabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, BUNDLEID);
            iOScapabilities.setCapability(IOSMobileCapabilityType.USE_NEW_WDA, true);
            iOScapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATION_NAME);
            iOScapabilities.setCapability("noReset", true);
            iOScapabilities.setCapability(MobileCapabilityType.APP, APPLICATION_NAME);
            driver = new IOSDriver(url, iOScapabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(120));
        }
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
