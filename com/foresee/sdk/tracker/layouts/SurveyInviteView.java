package com.foresee.sdk.tracker.layouts;

import android.content.Context;
import com.foresee.sdk.tracker.StringsProvider;
import com.foresee.sdk.tracker.layouts.InviteView.BodyView;

public class SurveyInviteView extends InviteView {
    public SurveyInviteView(Context context, InviteViewParams inviteViewParams, String str, SurveyInviteClickHandler surveyInviteClickHandler, StringsProvider stringsProvider) {
        super(context, inviteViewParams, str, surveyInviteClickHandler, stringsProvider);
    }

    protected String getAcceptButtonText(StringsProvider stringsProvider) {
        return stringsProvider.getAcceptButtonText();
    }

    protected String getDeclineButtonText(StringsProvider stringsProvider) {
        return stringsProvider.getDeclineButtonText();
    }

    protected BodyView getBodyView(Context context, StringsProvider stringsProvider) {
        return new InviteBodyView(context, this.viewParams, stringsProvider.getInviteTitle(), stringsProvider.getInviteText());
    }
}
