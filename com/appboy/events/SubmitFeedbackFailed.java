package com.appboy.events;

import com.appboy.models.ResponseError;
import com.appboy.models.outgoing.Feedback;

public final class SubmitFeedbackFailed {
    private final Feedback f896a;
    private final ResponseError f897b;

    public SubmitFeedbackFailed(Feedback feedback, ResponseError responseError) {
        this.f896a = feedback;
        this.f897b = responseError;
    }

    public final Feedback getFeedback() {
        return this.f896a;
    }

    public final ResponseError getError() {
        return this.f897b;
    }
}
