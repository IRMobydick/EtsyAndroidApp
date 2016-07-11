package com.foresee.mobileReplay.services;

import android.content.Intent;
import com.foresee.mobileReplay.perfLog.PerfLogger;
import com.google.inject.Inject;
import roboguice.service.RoboIntentService;

public class PerfLogService extends RoboIntentService {
    public static final String EXTRA_OPERATION_DURATION = "OPERATION_DURATION";
    public static final String EXTRA_OPERATION_TYPE = "OPERATION_TYPE";
    public static final String EXTRA_SESSION_ID = "SESSION_ID";
    @Inject
    private PerfLogger logger;

    public PerfLogService() {
        super("PerfLogService");
    }

    public PerfLogService(PerfLogger perfLogger) {
        super("PerfLogService");
        this.logger = perfLogger;
    }

    protected void onHandleIntent(Intent intent) {
        handleIntent(intent);
    }

    void handleIntent(Intent intent) {
        this.logger.logOperation(intent.getStringExtra(EXTRA_SESSION_ID), intent.getStringExtra(EXTRA_OPERATION_TYPE), intent.getLongExtra(EXTRA_OPERATION_DURATION, -1));
    }
}
