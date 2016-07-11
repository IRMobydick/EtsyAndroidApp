package com.foresee.sdk.tracker.app;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.foresee.sdk.configuration.Configuration;
import com.foresee.sdk.events.EventPublisherImpl;
import com.foresee.sdk.events.LifecycleEvent;
import com.foresee.sdk.events.LifecycleEvent.EventType;
import com.foresee.sdk.tracker.TrackingContext;
import com.foresee.sdk.tracker.layouts.InviteViewParams;
import com.foresee.sdk.tracker.layouts.OnExitDetailsView;
import com.foresee.sdk.tracker.layouts.SurveyInviteClickHandler;
import com.foresee.sdk.tracker.layouts.SurveyInviteView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OnExitSurveyInviteActivity extends SurveyInviteActivity {
    private Configuration configuration;
    private OnExitDetailsView detailsView;
    protected EditText etsyEditText;
    protected TextView etsyErrorView;
    protected ScrollView etsyView;
    private Logger logger;
    private OnExitInviteState state;

    /* renamed from: com.foresee.sdk.tracker.app.OnExitSurveyInviteActivity.1 */
    class C10491 implements OnClickListener {
        C10491() {
        }

        public void onClick(View view) {
            OnExitSurveyInviteActivity.this.onDecline();
        }
    }

    /* renamed from: com.foresee.sdk.tracker.app.OnExitSurveyInviteActivity.2 */
    class C10502 implements OnClickListener {
        C10502() {
        }

        public void onClick(View view) {
            OnExitSurveyInviteActivity.this.onAccept();
        }
    }

    /* renamed from: com.foresee.sdk.tracker.app.OnExitSurveyInviteActivity.3 */
    class C10513 implements OnClickListener {
        C10513() {
        }

        public void onClick(View view) {
            OnExitSurveyInviteActivity.this.onAccept();
        }
    }

    /* renamed from: com.foresee.sdk.tracker.app.OnExitSurveyInviteActivity.4 */
    class C10524 implements OnClickListener {
        C10524() {
        }

        public void onClick(View view) {
            OnExitSurveyInviteActivity.this.onDecline();
        }
    }

    /* renamed from: com.foresee.sdk.tracker.app.OnExitSurveyInviteActivity.5 */
    class C10535 implements OnEditorActionListener {
        C10535() {
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i != 4) {
                return false;
            }
            OnExitSurveyInviteActivity.this.onAccept();
            return true;
        }
    }

    /* renamed from: com.foresee.sdk.tracker.app.OnExitSurveyInviteActivity.6 */
    class C10546 implements OnClickListener {
        C10546() {
        }

        public void onClick(View view) {
            OnExitSurveyInviteActivity.this.onDecline();
        }
    }

    interface OnExitInviteState {
        void onAccept(Activity activity, SurveyInviteClickHandler surveyInviteClickHandler);

        void onDecline(SurveyInviteClickHandler surveyInviteClickHandler);
    }

    class LocalNotificationInvitedState implements OnExitInviteState {
        private LocalNotificationInvitedState() {
        }

        public void onAccept(Activity activity, SurveyInviteClickHandler surveyInviteClickHandler) {
            TrackingContext.Instance().acceptInvitation();
            OnExitSurveyInviteActivity.this.finish();
        }

        public void onDecline(SurveyInviteClickHandler surveyInviteClickHandler) {
            TrackingContext.Instance().declineInvitation();
            OnExitSurveyInviteActivity.this.finish();
        }
    }

    class RemoteNotificationInvitedState implements OnExitInviteState {
        private RemoteNotificationInvitedState() {
        }

        public void onAccept(Activity activity, SurveyInviteClickHandler surveyInviteClickHandler) {
            if (OnExitSurveyInviteActivity.this.areEtsyLayoutIdsSet()) {
                OnExitSurveyInviteActivity.this.setEtsyView(TrackingContext.Instance().getExitLayoutId());
                OnExitSurveyInviteActivity.this.wireUpSecondModalButtons(OnExitSurveyInviteActivity.this.etsyView);
            } else {
                InviteViewParams inviteViewParams = new InviteViewParams(OnExitSurveyInviteActivity.this.getWindowManager().getDefaultDisplay(), OnExitSurveyInviteActivity.this.getResources().getConfiguration());
                OnExitSurveyInviteActivity.this.detailsView = new OnExitDetailsView(activity, inviteViewParams, OnExitSurveyInviteActivity.this.configuration.getCustomLogoPath(), surveyInviteClickHandler, OnExitSurveyInviteActivity.this.stringsProvider);
                OnExitSurveyInviteActivity.this.inviteView = OnExitSurveyInviteActivity.this.detailsView;
                OnExitSurveyInviteActivity.this.setContentView(OnExitSurveyInviteActivity.this.inviteView);
            }
            TrackingContext.Instance().acceptInvitation();
            OnExitSurveyInviteActivity.this.state = new RemoteNotificationDetailsPresented(OnExitSurveyInviteActivity.this, null);
        }

        public void onDecline(SurveyInviteClickHandler surveyInviteClickHandler) {
            TrackingContext.Instance().declineInvitation();
            new EventPublisherImpl(OnExitSurveyInviteActivity.this.getApplication()).publishEvent(new LifecycleEvent(EventType.INVITE_DECLINED));
            OnExitSurveyInviteActivity.this.finish();
        }
    }

    public OnExitSurveyInviteActivity() {
        this.logger = LoggerFactory.m7499a("FORESEE_TRIGGER");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.configuration = (Configuration) getIntent().getSerializableExtra("CONTEXT_CONFIG");
        if (this.configuration.shouldUseLocalNotification()) {
            this.state = new LocalNotificationInvitedState();
        } else {
            this.state = new RemoteNotificationInvitedState();
        }
        if (areEtsyLayoutIdsSet()) {
            setEtsyView(getIntent().getIntExtra("ETSY_MODAL_1", -1));
            wireUpFirstModalButtons(this.etsyView);
        }
    }

    protected void setEtsyView(int i) {
        Log.d("FORESEE_SDK_COMMON", "setEtsyView set via OnExitSurveyInviteActivity subclass = " + i);
        this.etsyView = (ScrollView) getLayoutInflater().inflate(i, null);
        setContentView(this.etsyView);
    }

    protected void setForeSeeView(Configuration configuration) {
        if (!areEtsyLayoutIdsSet()) {
            super.setForeSeeView(configuration);
        }
    }

    private void wireUpFirstModalButtons(ScrollView scrollView) {
        wireUpXToCloseButton();
        ((TextView) scrollView.findViewById(TrackingContext.Instance().getOptOutBtnId())).setOnClickListener(new C10491());
        ((Button) scrollView.findViewById(TrackingContext.Instance().getOptInBtnId())).setOnClickListener(new C10502());
    }

    protected void wireUpSecondModalButtons(ScrollView scrollView) {
        wireUpXToCloseButton();
        ((Button) scrollView.findViewById(TrackingContext.Instance().getSendSurveyButtonId())).setOnClickListener(new C10513());
        ((TextView) scrollView.findViewById(TrackingContext.Instance().getCancelButtonId())).setOnClickListener(new C10524());
        int inputFieldId = TrackingContext.Instance().getInputFieldId();
        Log.d("FORESEE_SDK_COMMON", "editText view Id  = " + inputFieldId);
        this.etsyEditText = (EditText) scrollView.findViewById(inputFieldId);
        this.etsyEditText.setOnEditorActionListener(new C10535());
    }

    private void wireUpXToCloseButton() {
        ((ImageButton) findViewById(TrackingContext.Instance().getXCloseId())).setOnClickListener(new C10546());
    }

    protected SurveyInviteView getInviteView(Configuration configuration, SurveyInviteClickHandler surveyInviteClickHandler) {
        this.configuration = configuration;
        SurveyInviteView inviteView = super.getInviteView(configuration, surveyInviteClickHandler);
        if (configuration.shouldUseLocalNotification()) {
            inviteView.setBody(this.stringsProvider.getOnExitLocalNotificationText());
            this.state = new LocalNotificationInvitedState();
        } else {
            inviteView.setBody(this.stringsProvider.getOnExitInviteText());
            this.state = new RemoteNotificationInvitedState();
        }
        return inviteView;
    }

    public void onAccept() {
        this.state.onAccept(this, this);
    }

    public void onDecline() {
        this.state.onDecline(this);
    }

    private String getUserAgent() {
        if (VERSION.SDK_INT >= 17) {
            return WebSettings.getDefaultUserAgent(this);
        }
        return new WebView(this).getSettings().getUserAgentString();
    }
}
