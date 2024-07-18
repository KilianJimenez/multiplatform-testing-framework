package com.example.application.ios.flows;

import com.example.application.ios.pages.LoginPage;
import com.example.business.flows.LoginBusinessFlow;

public class LoginFlowIOS extends LoginBusinessFlow {

    LoginPage loginPage;

    public LoginFlowIOS() {
        this.loginPage = new LoginPage();
    }

    @Override
    public void doLogin(String email, String password) {
        loginPage.typeEmailInput(email);
        loginPage.typePasswordInput(password);
        loginPage.clickOnSubmitLogin();
    }
}
