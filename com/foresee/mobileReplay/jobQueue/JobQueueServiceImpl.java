package com.foresee.mobileReplay.jobQueue;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import com.google.inject.Inject;

public class JobQueueServiceImpl implements JobQueueService {
    private Application application;

    @Inject
    public JobQueueServiceImpl(Application application) {
        this.application = application;
    }

    public void enqueueJob(QueueableJob queueableJob) {
        String serializeJob = JobQueueIntentService.serializeJob(queueableJob);
        if (serializeJob != null) {
            Intent intent = new Intent(this.application, JobQueueIntentService.class);
            intent.putExtra(JobQueueIntentService.EXTRA_SERIALIZED_JOB, serializeJob);
            this.application.startService(intent);
            return;
        }
        Log.e("FORESEE_CAPTURE", "Couldn't serialize queueable job");
    }
}
