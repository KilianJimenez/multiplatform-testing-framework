package com.example.business.flows;

import com.example.application.android.flows.LoginFlowAndroid;
import com.example.application.ios.flows.LoginFlowIOS;
import com.example.application.web.flows.LoginFlowWeb;
import com.example.core.Framework;

public abstract class LoginBusinessFlow {
    public static LoginBusinessFlow loadPlatformFlow() {
        switch (Framework.getPlatform()) {
            case ANDROID:
                return new LoginFlowAndroid();
            case IOS:
                return new LoginFlowIOS();
            case WEB:
            default:
                return new LoginFlowWeb();
        }
    }

    public abstract void doLogin(String email, String password);
}
