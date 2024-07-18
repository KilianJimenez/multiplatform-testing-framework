package com.example.application.web.flows;

import com.example.application.web.pages.LoginPage;
import com.example.business.flows.LoginBusinessFlow;

public class LoginFlowWeb extends LoginBusinessFlow {
    LoginPage loginPage;

    public LoginFlowWeb() {
        this.loginPage = new LoginPage();
    }

    @Override
    public void doLogin(String email, String password) {
        loginPage.typeEmailInput(email);
        loginPage.typePasswordInput(password);
        loginPage.clickLoginButton();
    }
}
