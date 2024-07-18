package com.example.application.web.elements;

import com.example.core.platform.elements.WebElements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginElements extends WebElements {

    public LoginElements() {
        super();
    }

    @FindBy(id = "login-submit")
    public static WebElement loginButton;

    @FindBy(id = "email")
    public static WebElement emailInput;

    @FindBy(id = "password")
    public static WebElement passwordInput;
}
