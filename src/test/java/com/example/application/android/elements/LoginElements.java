package com.example.application.android.elements;

import com.example.core.platform.elements.AndroidElements;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginElements extends AndroidElements {

    public LoginElements() {
        super();
    }

    @AndroidFindBy(id = "your_login_button_id")
    public static WebElement loginButton;

    @AndroidFindBy(id = "your_email_input_id")
    public static WebElement emailInput;

    @AndroidFindBy(id = "your_password_input_id")
    public static WebElement passwordInput;
}
