package com.foresee.sdk.events;

import android.app.Application;

public class EventPublisherImpl implements EventPublisher {
    private Application application;

    public EventPublisherImpl(Application application) {
        this.application = application;
    }

    public void publishEvent(LifecycleEvent lifecycleEvent) {
        this.application.sendBroadcast(lifecycleEvent.toIntent());
    }
}
