package com.example.application.ios.pages;

import com.example.application.ios.elements.LoginElements;

import static com.example.core.actions.MobileActions.*;

public class LoginPage {

    public LoginPage() {
        new LoginElements();
    }

    public void typeEmailInput(String email) {
        sendKeysTo(email, LoginElements.emailInput);
        sleep(3000);
    }

    public void typePasswordInput(String password) {
        sendKeysTo(password, LoginElements.passwordInput);
    }

    public void clickOnSubmitLogin() {
        clickOn(LoginElements.loginOption);
    }
}
