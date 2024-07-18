package com.example.application.web.pages;

import com.example.application.web.elements.LoginElements;

import static com.example.application.web.elements.LoginElements.*;
import static com.example.core.actions.WebActions.*;

public class LoginPage {

    public LoginPage() {
        new LoginElements();
    }


    public void openURL(String url) {
        openPage(url);

    }

    public void clickLoginButton() {
        clickOn(loginButton);
    }

    public void typeEmailInput(String email) {
        sendKeysTo(email, emailInput);
    }

    public void typePasswordInput(String password) {
        sendKeysTo(password, passwordInput);
    }
}
