package com.example.core.platform.elements;

import com.example.core.drivers.CustomWebDriver;
import org.openqa.selenium.support.PageFactory;

public class WebElements {
    public WebElements() {
        PageFactory.initElements(CustomWebDriver.driver, this);
    }
}
