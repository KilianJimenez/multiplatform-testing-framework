package com.example.application.android.flows;

import com.example.application.android.pages.LoginPage;
import com.example.business.flows.LoginBusinessFlow;

public class LoginFlowAndroid extends LoginBusinessFlow {

    LoginPage loginPage;

    public LoginFlowAndroid() {
        this.loginPage = new LoginPage();
    }

    @Override
    public void doLogin(String email, String password) {
        loginPage.clickLoginButton();
        loginPage.typeEmailInput(email);
        loginPage.typePasswordInput(password);
        loginPage.clickLoginButton();
    }
}
