package com.example.core.drivers;

import com.example.core.Framework;
import com.example.core.profile.ProfileManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

import static com.example.core.Framework.profileCapabilities;

public class CustomAndroidDriver extends CustomDriver {

    private final String APP_PACKAGE = "";
    private final String APP_ACTIVITY = "";
    private final String AUTOMATION_NAME_CAPABILITY = "automationName";
    private final String PLATFORM_NAME = "Android";
    private final String AUTOMATION_NAME_VALUE = "uiautomator2";
    private static final String REMOTE_LOCATION = "remote";

    public static AndroidDriver driver;
    private static final String LOCAL_URL = "http://0.0.0.0:4723/wd/hub";

    public static final Logger logger = LoggerFactory.getLogger(CustomAndroidDriver.class.getName());

    public CustomAndroidDriver() {
        if (ProfileManager.getProfileProperties(Framework.profile, "location").equals(REMOTE_LOCATION)) {

            /*
             * Set Android capabilities in remote simulator in BrowserStack, Sauce Labs, etc.
             * Also set URL to access the application remotely.
             * */

        } else {
            //Loads all capabilities
            Map<String, String> loadedCaps = profileCapabilities();

            URL url = null;
            try {
                url = new URL(LOCAL_URL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, loadedCaps.get("device_name"));
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, loadedCaps.get("platform_version"));
            capabilities.setCapability(MobileCapabilityType.UDID, loadedCaps.get("udid"));
            capabilities.setCapability(AndroidMobileCapabilityType.AVD, loadedCaps.get("avd"));
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
            capabilities.setCapability(AUTOMATION_NAME_CAPABILITY, AUTOMATION_NAME_VALUE);
            capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
            driver = new AndroidDriver(url, capabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(120));
        }
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
