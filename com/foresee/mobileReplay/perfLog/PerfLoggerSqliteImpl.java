package com.foresee.mobileReplay.perfLog;

import android.util.Log;
import com.google.inject.Inject;

public class PerfLoggerSqliteImpl implements PerfLogger {
    @Inject
    private PerfDataRepository repo;

    public void logOperation(String str, String str2, long j) {
        try {
            this.repo.open();
            this.repo.insertOperation(str, str2, j);
            Log.v("FORESEE_PERF_LOG", String.format("Operation (%s) logged at %d ms", new Object[]{str2, Long.valueOf(j)}));
            this.repo.close();
        } catch (Throwable e) {
            Log.e("FORESEE_PERF_LOG", e.getMessage(), e);
        }
    }
}
