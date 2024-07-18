package com.example.core.platform.elements;

import com.example.core.drivers.CustomAndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class AndroidElements {
    public AndroidElements() {
        PageFactory.initElements(new AppiumFieldDecorator(CustomAndroidDriver.driver), this);
    }
}
