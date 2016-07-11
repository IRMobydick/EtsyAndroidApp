package com.adjust.sdk;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TimerCycle {
    private Runnable command;
    private long cycleDelay;
    private long initialDelay;
    private boolean isPaused;
    private ScheduledExecutorService scheduler;
    private ScheduledFuture waitingTask;

    public TimerCycle(Runnable runnable, long j, long j2) {
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.command = runnable;
        this.initialDelay = j;
        this.cycleDelay = j2;
        this.isPaused = true;
    }

    public void start() {
        if (this.isPaused) {
            this.waitingTask = this.scheduler.scheduleWithFixedDelay(this.command, this.initialDelay, this.cycleDelay, TimeUnit.MILLISECONDS);
            this.isPaused = false;
        }
    }

    public void suspend() {
        if (!this.isPaused) {
            this.initialDelay = this.waitingTask.getDelay(TimeUnit.MILLISECONDS);
            this.waitingTask.cancel(false);
            this.isPaused = true;
        }
    }
}
