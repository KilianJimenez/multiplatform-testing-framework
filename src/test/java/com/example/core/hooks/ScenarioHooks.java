package com.example.core.hooks;

import com.example.core.Framework;
import com.example.core.drivers.CustomAndroidDriver;
import com.example.core.drivers.CustomIOSDriver;
import com.example.core.drivers.CustomWebDriver;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.UUID;

import static com.example.core.platform.Platform.*;

public class ScenarioHooks {

    public static final Logger logger = LoggerFactory.getLogger(ScenarioHooks.class.getName());

    private static final long NANO_TO_SECONDS = 1000000000;
    private long timeExecutingTask = 0;

    private static final String SCREENSHOT_LOCATION = "src/test/logs/screenshots";

    @Before(order = 1)
    public void logBeforeScenario(Scenario scenario) {
        timeExecutingTask = System.nanoTime();
        logger.info("Entering in scenario |{}|", scenario.getName());
    }

    @After(order = 3)
    public void embedScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                if(Framework.getPlatform() == WEB) {
                    logWebScreenShot("SnapShot for failed scenario: " + scenario.getName(), CustomWebDriver.driver);
                }else if (Framework.getPlatform() == ANDROID) {
                    logMobileScreenShot("SnapShot for failed scenario: " + scenario.getName(), CustomAndroidDriver.driver);
                }else if(Framework.getPlatform() == IOS) {
                    logMobileScreenShot("SnapShot for failed scenario: " + scenario.getName(), CustomIOSDriver.driver);
                }
            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            }
        }
    }

    @After(order = 2)
    public void logAfterScenario(Scenario scenario) {
        long taskTimeInSeconds = (System.nanoTime() - timeExecutingTask) / NANO_TO_SECONDS;
        String msg = "Exiting scenario |" + scenario.getName() + "| with status \"" + scenario.getStatus() + "\" in " + taskTimeInSeconds + " seconds";
        if (scenario.isFailed()) {
            logger.error(msg);
        } else {
            logger.info(msg);
        }
    }

    @After(order = 1)
    public void deleteCookiesAfterScenario() {

    }

    private void logWebScreenShot(String stepName, WebDriver driver) {
        String filename = UUID.randomUUID().toString();
        logger.info(filename + " : " + stepName);
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileChannel srcChannel = new FileInputStream(src).getChannel();
            File dst = new File(SCREENSHOT_LOCATION, filename + ".png");
            FileChannel dstChannel = new FileOutputStream(dst).getChannel();
            dstChannel.transferFrom(srcChannel, 0, srcChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logMobileScreenShot(String stepName, AppiumDriver driver) {
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        String filename = UUID.randomUUID().toString();
        logger.info(filename + " : " + stepName);
        File targetFile = new File(SCREENSHOT_LOCATION + filename + ".jpg");
        try {
            FileUtils.copyFile(srcFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
