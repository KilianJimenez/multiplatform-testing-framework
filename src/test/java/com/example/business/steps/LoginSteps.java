package com.example.business.steps;

import com.example.business.flows.LoginBusinessFlow;
import io.cucumber.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginSteps {

    public static final Logger logger = LoggerFactory.getLogger(LoginSteps.class.getName());

    private final LoginBusinessFlow loginBusinessFlow;

    public LoginSteps() {
        this.loginBusinessFlow = LoginBusinessFlow.loadPlatformFlow();
    }

    @Given("I login as user with email {string} and password {string}")
    public void iLoginAsUserWithEmailAndPassword(String email, String password) {
        loginBusinessFlow.doLogin(email, password);
        logger.info("Authenticated with email [{}] and password [{}].", email, password);
    }
}
