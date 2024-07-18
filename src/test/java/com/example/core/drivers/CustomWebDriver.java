package com.example.core.drivers;

import com.example.core.Framework;
import com.example.core.profile.ProfileManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.example.core.Framework.profileCapabilities;

public class CustomWebDriver extends CustomDriver {

    static Logger logger = LoggerFactory.getLogger(CustomWebDriver.class.getName());

    private static final String CHROME_OPTIONS = "chrome_options";
    private static final String REMOTE_LOCATION = "remote";

    public static WebDriver driver;

    public CustomWebDriver() {
        DesiredCapabilities caps = new DesiredCapabilities();

        if (ProfileManager.getProfileProperties(Framework.profile, "location").equals(REMOTE_LOCATION)) {

            /*
             * Set web capabilities in remote browser in BrowserStack, Sauce Labs, etc.
             * Also set URL to access the application remotely.
             * */

        } else {
            WebDriverManager.chromedriver().setup();
            Map<String, String> loadedCaps = profileCapabilities();
            List<String> chrome_options = new ArrayList<>(Arrays.asList(loadedCaps.get(CHROME_OPTIONS).split("\"([^\"]*)\"")));
            ChromeOptions chromeOptions = new ChromeOptions();
            for (String option : chrome_options) {
                chromeOptions.addArguments(option);
            }
            driver = new ChromeDriver(chromeOptions);
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(120));
        }
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
