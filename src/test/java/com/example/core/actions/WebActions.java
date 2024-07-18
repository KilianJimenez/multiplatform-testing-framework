package com.example.core.actions;

import com.example.core.drivers.CustomWebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class WebActions {

    public static WebDriver driver = CustomWebDriver.driver;

    public static void openPage(String pageUrl) {
        driver.get(pageUrl);
    }

    public static void clickOn(WebElement element) {
        element.click();
    }

    public static void clickOnByXpath(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        if (!isDisplayed(element))
            scrollToJS(element);
        element.click();
    }


    public static void acceptCookies() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('bpc-cookie-banner').shadowRoot.querySelector('.bpc-cookie-accept-button').click()");
    }

    public static void sendKeysTo(String keys, WebElement element) {
        element.sendKeys(keys);
    }

    public static boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }

    public static void scrollToJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollTo(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }
}
