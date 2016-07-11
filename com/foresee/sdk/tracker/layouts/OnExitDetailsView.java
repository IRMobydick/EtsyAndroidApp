package com.foresee.sdk.tracker.layouts;

import android.content.Context;
import android.widget.EditText;
import com.foresee.sdk.tracker.StringsProvider;
import com.foresee.sdk.tracker.layouts.InviteView.BodyView;

public class OnExitDetailsView extends InviteView {
    private OnExitInviteBodyView view;

    public OnExitDetailsView(Context context, InviteViewParams inviteViewParams, String str, SurveyInviteClickHandler surveyInviteClickHandler, StringsProvider stringsProvider) {
        super(context, inviteViewParams, str, surveyInviteClickHandler, stringsProvider);
    }

    protected BodyView getBodyView(Context context, StringsProvider stringsProvider) {
        if (this.view == null) {
            this.view = new OnExitInviteBodyView(this, context, stringsProvider.getOnExitDetailsTitle(), stringsProvider.getOnExitDetailsBody(), stringsProvider.getOnExitHintText(), this.callback);
        }
        return this.view;
    }

    protected String getAcceptButtonText(StringsProvider stringsProvider) {
        return stringsProvider.getOnExitDetailAcceptButtonText();
    }

    protected String getDeclineButtonText(StringsProvider stringsProvider) {
        return stringsProvider.getOnExitDetailDeclineButtonText();
    }

    public EditText getEditField() {
        return OnExitInviteBodyView.access$000(this.view);
    }

    public void setErrorText(String str) {
        OnExitInviteBodyView.access$100(this.view).setText(str);
        OnExitInviteBodyView.access$100(this.view).setVisibility(0);
    }

    public void clearError() {
        OnExitInviteBodyView.access$100(this.view).setText(null);
        OnExitInviteBodyView.access$100(this.view).setVisibility(8);
    }
}
