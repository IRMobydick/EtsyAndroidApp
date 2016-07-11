package com.appboy.events;

import com.appboy.models.outgoing.Feedback;

public final class SubmitFeedbackSucceeded {
    private final Feedback f898a;

    public SubmitFeedbackSucceeded(Feedback feedback) {
        this.f898a = feedback;
    }

    public final Feedback getFeedback() {
        return this.f898a;
    }
}
