package com.adjust.sdk;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TimerOnce {
    private Runnable command;
    private boolean isRunning;
    private ScheduledExecutorService scheduler;
    private ScheduledFuture waitingTask;

    public TimerOnce(ScheduledExecutorService scheduledExecutorService, Runnable runnable) {
        this.scheduler = scheduledExecutorService;
        this.command = runnable;
        this.isRunning = false;
    }

    public void startIn(long j) {
        if (this.waitingTask != null) {
            this.waitingTask.cancel(false);
        }
        this.waitingTask = this.scheduler.schedule(this.command, j, TimeUnit.MILLISECONDS);
    }

    public long getFireIn() {
        if (this.waitingTask == null) {
            return 0;
        }
        return this.waitingTask.getDelay(TimeUnit.MILLISECONDS);
    }
}
