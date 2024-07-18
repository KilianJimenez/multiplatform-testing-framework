package com.example.core.drivers;

import com.example.core.Framework;

public class CustomDriver {

    public static CustomDriver loadDriverByPlatform() {
        switch (Framework.getPlatform()) {
            case ANDROID:
                return new CustomAndroidDriver();
            case IOS:
                return new CustomIOSDriver();
            case WEB:
            default:
                return new CustomWebDriver();
        }
    }

    public void quit() {
        // Implemented on extended drivers
    }
}
