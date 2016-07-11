package com.appboy.events;

import com.appboy.models.IInAppMessage;

public final class InAppMessageEvent {
    private final IInAppMessage f894a;
    private final String f895b;

    public InAppMessageEvent(IInAppMessage iInAppMessage, String str) {
        this.f895b = str;
        if (iInAppMessage == null) {
            throw new NullPointerException();
        }
        this.f894a = iInAppMessage;
    }

    public final IInAppMessage getInAppMessage() {
        return this.f894a;
    }

    public final String getUserId() {
        return this.f895b;
    }
}
