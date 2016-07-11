package com.foresee.mobileReplay.session;

import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.view.WindowManager;
import com.foresee.mobileReplay.data.SessionRepository;
import com.foresee.mobileReplay.http.BlacklistCheck;
import com.foresee.mobileReplay.jobQueue.JobQueueService;
import com.google.inject.Inject;
import com.google.inject.name.Named;

class SessionManager14 extends SessionManager implements ActivityLifecycleCallbacks {
    @Inject
    public SessionManager14(Application application, SessionRepository sessionRepository, JobQueueService jobQueueService, BlacklistCheck blacklistCheck, @Named("downSampleRatio") float f, WindowManager windowManager) {
        super(application, sessionRepository, jobQueueService, blacklistCheck, f, windowManager);
    }
}
