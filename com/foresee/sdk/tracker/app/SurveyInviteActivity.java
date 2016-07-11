package com.foresee.sdk.tracker.app;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.os.Bundle;
import com.foresee.sdk.configuration.Configuration;
import com.foresee.sdk.configuration.MeasureConfiguration;
import com.foresee.sdk.events.EventPublisherImpl;
import com.foresee.sdk.events.LifecycleEvent;
import com.foresee.sdk.events.LifecycleEvent.EventType;
import com.foresee.sdk.instrumentation.SessionReplayPage;
import com.foresee.sdk.tracker.StringsProvider;
import com.foresee.sdk.tracker.TrackingContext;
import com.foresee.sdk.tracker.layouts.InviteView;
import com.foresee.sdk.tracker.layouts.InviteViewParams;
import com.foresee.sdk.tracker.layouts.SurveyInviteClickHandler;
import com.foresee.sdk.tracker.layouts.SurveyInviteView;

public class SurveyInviteActivity extends Activity implements SessionReplayPage, SurveyInviteClickHandler {
    protected Configuration configuration;
    protected ConnectivityManager connectivityManager;
    protected InviteView inviteView;
    protected MeasureConfiguration measureConfiguration;
    protected StringsProvider stringsProvider;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.connectivityManager = (ConnectivityManager) getSystemService("connectivity");
        this.measureConfiguration = (MeasureConfiguration) getIntent().getSerializableExtra("MEASURE_CONFIG");
        this.configuration = (Configuration) getIntent().getSerializableExtra("CONTEXT_CONFIG");
        setForeSeeView(this.configuration);
        this.stringsProvider = TrackingContext.Instance().getStringsProvider();
        onCreated();
        getWindow().setSoftInputMode(32);
    }

    protected void setForeSeeView(Configuration configuration) {
        this.inviteView = getInviteView(configuration, this);
        setContentView(this.inviteView);
    }

    protected SurveyInviteView getInviteView(Configuration configuration, SurveyInviteClickHandler surveyInviteClickHandler) {
        return new SurveyInviteView(this, new InviteViewParams(getWindowManager().getDefaultDisplay(), getResources().getConfiguration()), configuration.getCustomLogoPath(), this, this.stringsProvider);
    }

    public void onCreated() {
    }

    protected void onResume() {
        super.onResume();
        TrackingContext.Instance().onActivityResumed(this);
    }

    protected void onPause() {
        super.onPause();
        TrackingContext.Instance().onActivityPaused(this);
    }

    protected void onStart() {
        super.onStart();
        TrackingContext.Instance().onActivityStarted(this);
    }

    public void onBackPressed() {
        super.onBackPressed();
        TrackingContext.Instance().declineInvitation();
    }

    public void onConfigurationChanged(android.content.res.Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (!areEtsyLayoutIdsSet()) {
            this.inviteView.onOrientationChanged(configuration.orientation);
        }
    }

    public void onAccept() {
        if (this.connectivityManager.getActiveNetworkInfo() == null || !this.connectivityManager.getActiveNetworkInfo().isAvailable()) {
            TrackingContext.Instance().onNetworkDisconnected(this);
            return;
        }
        TrackingContext.Instance().acceptInvitation();
        new EventPublisherImpl(getApplication()).publishEvent(new LifecycleEvent(EventType.INVITE_ACCEPTED));
        finish();
    }

    public void onDecline() {
        TrackingContext.Instance().declineInvitation();
        new EventPublisherImpl(getApplication()).publishEvent(new LifecycleEvent(EventType.INVITE_DECLINED));
        finish();
    }

    public String sessionReplayPageName() {
        return "Survey invite";
    }

    public boolean areEtsyLayoutIdsSet() {
        return getIntent().hasExtra("ETSY_MODAL_1") && getIntent().hasExtra("ETSY_MODAL_2");
    }
}
