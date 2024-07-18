package com.example.core.actions;

import com.example.core.Framework;
import com.example.core.drivers.CustomAndroidDriver;
import com.example.core.drivers.CustomIOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class MobileActions {

    public static Logger logger = LoggerFactory.getLogger(MobileActions.class.getName());

    public static RemoteWebDriver getDriver() {
        switch (Framework.getPlatform()) {
            case ANDROID:
            default:
                return CustomAndroidDriver.driver;
            case IOS:
                return CustomIOSDriver.driver;
        }
    }

    //// Clicks
    public static void clickOn(WebElement element) {
        element.click();
    }

    public static void clickOnByXpath(String xpath) {
        getDriver().findElement(By.xpath(xpath)).click();

    }


    /// Field Actions
    public static void sendKeysTo(String text, WebElement element) {
        element.sendKeys(text);
    }

    /**
     * Sends keys (string) to the location of the cursor
     *
     * @param sentText
     */

    public static void sendKeysToCursor(String sentText) {
        WebElement activeElement = (WebElement) getDriver().switchTo().activeElement();
        activeElement.sendKeys(sentText);

    }

    public static void listClear(List<WebElement> elements) {
        for (WebElement element : elements) {
            element.clear();
        }
    }

    public static void clear(WebElement element) {
        element.clear();
    }


    /// Element status/actions
    public static boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }

    /**
     * Checks the presence of an element in the screen
     *
     * @param element Mobile element
     * @return returns the presence
     */

    public static boolean isPresent(WebElement element) throws Exception {
        try {
            boolean presence = element.isDisplayed();
            logger.info("Checking presence of [" + element + "]. Is present: <" + presence + ">");
            return presence;
        } catch (Exception e) {
            throw new Exception("Element not found");
        }
    }

    /**
     * Gets text from specific element
     *
     * @param element WebElement
     * @return returns the text
     */

    public static String getText(WebElement element) {
        return element.getText();
    }

    /**
     * verification of element text to content input
     *
     * @param element WebElement
     * @param content content returned
     * @return true or false depending on element text is equal to the content
     */

    public static Boolean containsText(WebElement element, String content) {
        String text = element.getText();
        return text.contains(content);
    }


    /// Debug/Waits

    /**
     * Used for debugging - generates all elements present on screen the moment this function is called
     */

    public static void pageSource() {
        String pageSource = getDriver().getPageSource();
    }

    /**
     * Wait for element to be visible on screen depending on time given
     *
     * @param element  WebElement
     * @param waitTime Time to wait for element to appear
     */

    public static void waitForVisible(WebElement element, int waitTime) throws TimeoutException {
        try {
            long start = System.currentTimeMillis();
            logger.info("Waiting for visibility of element [{}] --> {} second", element.toString(), waitTime);
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofMillis(waitTime));
            wait.until(ExpectedConditions.visibilityOf(element));
            long stop = System.currentTimeMillis();
            logger.info("Actual wait time: " + (stop - start) + "ms");
        } catch (Exception ex) {
            throw new TimeoutException("element --> " + element.toString() + " not found!");
        }
    }

    public static void sleep(int timeMS) {
        try {
            Thread.sleep(timeMS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
