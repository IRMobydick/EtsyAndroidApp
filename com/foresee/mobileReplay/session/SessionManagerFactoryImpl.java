package com.foresee.mobileReplay.session;

import android.app.Application;
import android.os.Build.VERSION;
import com.google.inject.Inject;
import com.google.inject.Injector;
import roboguice.RoboGuice;

public class SessionManagerFactoryImpl implements SessionManagerFactory {
    private Injector injector;

    @Inject
    public SessionManagerFactoryImpl(Application application) {
        this.injector = RoboGuice.getBaseApplicationInjector(application);
    }

    public SessionManager createManager(String str) {
        SessionManager sessionManager;
        if (VERSION.SDK_INT >= 14) {
            sessionManager = (SessionManager) this.injector.getInstance(SessionManager14.class);
        } else {
            sessionManager = (SessionManager) this.injector.getInstance(SessionManager.class);
        }
        sessionManager.setCid(str);
        return sessionManager;
    }
}
