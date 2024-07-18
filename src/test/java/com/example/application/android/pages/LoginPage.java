package com.example.application.android.pages;

import com.example.application.android.elements.LoginElements;

import static com.example.core.actions.MobileActions.*;

public class LoginPage {

    public LoginPage() {
        new LoginElements();
    }

    public void clickLoginButton() {
        clickOn(LoginElements.loginButton);
    }

    public void typeEmailInput(String email) {
        sendKeysTo(email, LoginElements.emailInput);
    }

    public void typePasswordInput(String password) {
        sendKeysTo(password, LoginElements.passwordInput);
    }
}
