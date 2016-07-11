package com.foresee.mobileReplay.jobQueue;

import android.app.Application;
import android.content.Intent;
import com.foresee.mobileReplay.data.SessionRepository;
import com.foresee.mobileReplay.perfLog.OperationTimer;
import com.foresee.sdk.json.CustomJsonProcessor;
import com.google.gson.e;
import com.google.gson.g;
import com.google.inject.Inject;
import com.google.inject.Injector;
import roboguice.service.RoboIntentService;

public class JobQueueIntentService extends RoboIntentService {
    public static final String EVENTS_FILE = "jobQueueEvents.json";
    public static final String EXTRA_SERIALIZED_JOB = "EXTRA_SERIALIZED_JOB";
    private static e gson;
    @Inject
    Application application;
    @Inject
    Injector injector;
    @Inject
    SessionRepository sessionRepository;

    static {
        gson = new g().a(QueueableJob.class, new CustomJsonProcessor()).a();
    }

    public JobQueueIntentService() {
        super("JobQueueIntentService");
    }

    public void onCreate() {
        super.onCreate();
    }

    protected void onHandleIntent(Intent intent) {
        String stringExtra = intent.getStringExtra(EXTRA_SERIALIZED_JOB);
        if (stringExtra != null || !stringExtra.isEmpty()) {
            QueueableJob deserializeJob = deserializeJob(stringExtra);
            this.injector.injectMembers(deserializeJob);
            if (deserializeJob != null) {
                OperationTimer operationTimer = new OperationTimer(deserializeJob.getShortDescription(), deserializeJob.getIdentifier());
                operationTimer.start();
                deserializeJob.execute(this.application, this.sessionRepository);
                operationTimer.end();
                operationTimer.post(getApplicationContext());
            }
        }
    }

    public static QueueableJob deserializeJob(String str) {
        return (QueueableJob) gson.a(str, QueueableJob.class);
    }

    public static String serializeJob(QueueableJob queueableJob) {
        return gson.a(queueableJob);
    }
}
