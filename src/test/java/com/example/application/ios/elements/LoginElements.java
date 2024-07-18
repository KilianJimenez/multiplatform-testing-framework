package com.example.application.ios.elements;

import com.example.core.platform.elements.IOSElements;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class LoginElements extends IOSElements {
    public LoginElements() {
        super();
    }

    @iOSXCUITFindBy(id = "EmailIdentifier")
    public static WebElement emailInput;

    @iOSXCUITFindBy(id = "PasswordIdentifier")
    public static WebElement passwordInput;

    @iOSXCUITFindBy(id = "LoginIdentifier")
    public static WebElement loginOption;
}
