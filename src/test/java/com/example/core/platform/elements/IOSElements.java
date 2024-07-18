package com.example.core.platform.elements;

import com.example.core.drivers.CustomIOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class IOSElements {
    public IOSElements() {
        PageFactory.initElements(new AppiumFieldDecorator(CustomIOSDriver.driver), this);
    }
}
