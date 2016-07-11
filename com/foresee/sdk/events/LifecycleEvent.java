package com.foresee.sdk.events;

import android.content.Intent;
import org.apache.commons.lang3.StringUtils;

public class LifecycleEvent {
    public static final String ACTION = "com.foresee.sdk.lifecycleEvent";
    public static final String EXTRA_EVENT_DATA = "EVENT_DATA";
    public static final String EXTRA_EVENT_TYPE = "EVENT_TYPE";
    private String data;
    private EventType eventType;

    public enum EventType {
        SESSION_STARTED("SESSION_STARTED"),
        SESSION_ENDED("SESSION_ENDED"),
        INVITE_PRESENTED("INVITE_PRESENTED"),
        INVITE_ACCEPTED("INVITE_ACCEPTED"),
        INVITE_DECLINED("INVITE_DECLINED"),
        STATE_UPDATED("STATE_UPDATED"),
        DEBUG("DEBUG"),
        STORAGE_EXCEEDED("STORAGE_EXCEEDED"),
        STORAGE_ERROR("STORAGE_ERROR"),
        POOLING_DENIED("POOLING_DENIED"),
        UNDEFINED("UNDEFINED");
        
        private String value;

        private EventType(String str) {
            this.value = str;
        }

        public static EventType fromValue(String str) {
            for (EventType eventType : values()) {
                if (eventType.value.equals(str)) {
                    return eventType;
                }
            }
            return UNDEFINED;
        }

        public String value() {
            return this.value;
        }

        public boolean equals(String str) {
            return this.value.equals(str);
        }
    }

    public LifecycleEvent(EventType eventType) {
        this.eventType = eventType;
    }

    public LifecycleEvent(EventType eventType, String str) {
        this.eventType = eventType;
        this.data = str;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public Intent toIntent() {
        Intent intent = new Intent(ACTION);
        intent.putExtra(EXTRA_EVENT_TYPE, this.eventType.value);
        if (!(this.data == null || this.data.equals(StringUtils.EMPTY))) {
            intent.putExtra(EXTRA_EVENT_DATA, this.data);
        }
        return intent;
    }
}
