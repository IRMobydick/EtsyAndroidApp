package com.appboy.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Process;
import com.appboy.Appboy;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;

public class AppboyDataSyncService extends IntentService {
    private static final String f1035a;

    static {
        f1035a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyDataSyncService.class.getName()});
    }

    public AppboyDataSyncService() {
        super(AppboyDataSyncService.class.getName());
    }

    public void onCreate() {
        super.onCreate();
    }

    protected void onHandleIntent(Intent intent) {
        Process.setThreadPriority(10);
        if (intent == null) {
            AppboyLogger.m670w(f1035a, "AppboyDataSyncService received null intent, doing nothing.");
            return;
        }
        String action = intent.getAction();
        if (action == null) {
            AppboyLogger.m670w(f1035a, "AppboyDataSyncService received intent with null action, doing nothing.");
        } else if (action.contains(getApplicationContext().getPackageName() + Constants.APPBOY_REQUEST_SYNC_INTENT_SUFFIX)) {
            action = f1035a;
            Appboy.getInstance(getApplicationContext()).requestImmediateDataFlush();
        } else {
            AppboyLogger.m670w(f1035a, "AppboyDataSyncService received unknown intent, doing nothing.");
        }
    }
}
