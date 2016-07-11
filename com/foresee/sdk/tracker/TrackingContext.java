package com.foresee.sdk.tracker;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.WindowManager;
import com.etsy.android.lib.models.AppBuild;
import com.foresee.mobileReplay.recorder.ScreenRecorder;
import com.foresee.sdk.configuration.Configuration;
import com.foresee.sdk.configuration.MeasureConfiguration;
import com.foresee.sdk.events.EventPublisherImpl;
import com.foresee.sdk.events.LifecycleEvent;
import com.foresee.sdk.events.LifecycleEvent.EventType;
import com.foresee.sdk.events.TriggerEventObserver;
import com.foresee.sdk.tracker.app.DefaultStringsProvider;
import com.foresee.sdk.tracker.app.DefaultTrackerLogger;
import com.foresee.sdk.tracker.app.DefaultTrackerViewPresenter;
import com.foresee.sdk.tracker.app.TrackerLoader;
import com.foresee.sdk.tracker.services.NotificationServiceImpl;
import com.foresee.sdk.tracker.services.TrackerServiceClient;
import com.foresee.sdk.tracker.util.FsrSettings;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrackingContext implements PersistenceProvider, TrackerEventListener {
    private static TrackingContext instance;
    private String apiKey;
    private Application appContext;
    private Configuration configuration;
    private Activity currentActivity;
    private int mCancelButtonId;
    private int mErrorTextId;
    private int mExitLayoutId;
    private int mInputFieldId;
    private int mInviteLayoutId;
    private int mOptInBtnId;
    private int mOptOutBtnId;
    private int mSendSurveyButtonId;
    private int mXCloseId;
    private List<WeakReference<TriggerEventObserver>> observers;
    private StringsProvider stringsProvider;
    private Tracker tracker;
    private String version;

    protected TrackingContext() {
        this.observers = new ArrayList();
    }

    public static TrackingContext Instance() {
        if (instance != null) {
            return instance;
        }
        Log.e("FORESEE_TRIGGER", "Tracking context has not been initialized. Please call TrackingContext.start() to initialize the TrackingContext");
        throw new RuntimeException("Tracking context has not been initialized. Please call TrackingContext.start() to initialize the TrackingContext before calling TrackingContext.Instance()");
    }

    public static boolean start(Application application, Configuration configuration) {
        return finishSimpleStart(application, configuration);
    }

    public void setLayoutIds(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.mXCloseId = i3;
        this.mInviteLayoutId = i;
        this.mOptInBtnId = i4;
        this.mOptOutBtnId = i5;
        this.mExitLayoutId = i2;
        this.mInputFieldId = i6;
        this.mErrorTextId = i7;
        this.mSendSurveyButtonId = i8;
        this.mCancelButtonId = i9;
    }

    public int getInviteLayoutId() {
        return this.mInviteLayoutId;
    }

    public int getExitLayoutId() {
        return this.mExitLayoutId;
    }

    public int getXCloseId() {
        return this.mXCloseId;
    }

    public int getOptInBtnId() {
        return this.mOptInBtnId;
    }

    public int getOptOutBtnId() {
        return this.mOptOutBtnId;
    }

    public int getInputFieldId() {
        return this.mInputFieldId;
    }

    public int getErrorTextId() {
        return this.mErrorTextId;
    }

    public int getSendSurveyButtonId() {
        return this.mSendSurveyButtonId;
    }

    public int getCancelButtonId() {
        return this.mCancelButtonId;
    }

    private static boolean finishSimpleStart(Application application, Configuration configuration) {
        if (configuration == null) {
            Log.e("FORESEE_TRIGGER", "Configuration not loaded");
            return false;
        } else if (instance == null) {
            instance = new TrackingContext();
            instance.privateInitialize(application, configuration, new DefaultTrackerLogger(configuration.getClientId(), configuration.getLoggingUrl()), new DefaultStringsProvider(application));
            return true;
        } else {
            Log.w("FORESEE_TRIGGER", "An instance of TrackingContext has already been started. Please use TrackingContext.Instance() to obtain a reference to the TrackingContext.");
            return false;
        }
    }

    public void initialize(Application application, Configuration configuration) {
        privateInitialize(application, configuration, new DefaultTrackerLogger(configuration.getClientId(), configuration.getLoggingUrl()), new DefaultStringsProvider(application));
    }

    private void privateInitialize(Application application, Configuration configuration, ITrackerLogger iTrackerLogger, StringsProvider stringsProvider) {
        this.appContext = application;
        this.configuration = configuration;
        this.apiKey = configuration.getClientId();
        this.stringsProvider = stringsProvider;
        try {
            PersistedState LoadState = TrackerLoader.LoadState(this.appContext);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            Display defaultDisplay = ((WindowManager) application.getSystemService("window")).getDefaultDisplay();
            defaultDisplay.getMetrics(displayMetrics);
            Pair physicalScreenSize = getPhysicalScreenSize(displayMetrics);
            FsrSettings fsrSettings = new FsrSettings();
            configuration.addQueryStringParam("device", AppBuild.ANDROID_PLATFORM).addQueryStringParam("appRevision", fsrSettings.getAppRevision()).addCpp("trigger_version", fsrSettings.getTriggerVersion()).addCpp("os", AppBuild.ANDROID_PLATFORM).addCpp("os_version", VERSION.RELEASE).addCpp("model_name", Build.MODEL).addCpp("brand_name", Build.MANUFACTURER).addCpp("resolution_width", Integer.valueOf(defaultDisplay.getWidth())).addCpp("resolution_height", Integer.valueOf(defaultDisplay.getHeight())).addCpp("screen_width", (Integer) physicalScreenSize.first).addCpp("screen_height", (Integer) physicalScreenSize.second);
            ITrackerLogger iTrackerLogger2 = iTrackerLogger;
            this.tracker = new Tracker(application, LoadState, configuration, this, iTrackerLogger2, new TrackerServiceClient(this.apiKey, configuration.getServiceUrl(), (ConnectivityManager) application.getSystemService("connectivity")), this.stringsProvider, fsrSettings);
            this.tracker.initializeWithState(LoadState);
            this.tracker.setNotificationService(new NotificationServiceImpl(application));
            this.tracker.setTrackerEventListener(this);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void setDeclineDate(Date date) {
        this.tracker.setDeclineDate(date);
    }

    public void setLaunchDate(Date date) {
        this.tracker.setLaunchDate(date);
    }

    public void setRespondentId(String str) {
        this.tracker.setRespondentId(str);
    }

    public void checkState() {
        this.tracker.checkState();
    }

    public void resetAll() {
        this.tracker.reset();
        new EventPublisherImpl(this.appContext).publishEvent(new LifecycleEvent(EventType.STATE_UPDATED));
    }

    public void persistState(PersistedState persistedState) {
        try {
            TrackerLoader.SaveState(this.appContext, persistedState);
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage());
            throw new RuntimeException();
        }
    }

    public void applicationLaunched() {
        this.tracker.applicationLaunched();
    }

    public void applicationExited() {
        this.tracker.applicationExited();
    }

    public void onNetworkDisconnected(Activity activity) {
        this.tracker.onNetworkDisconnected();
        showErrorDialog(activity, this.stringsProvider.getStringByKey("couldNotLoadSurveyTitle"), this.stringsProvider.getStringByKey("surveyRequestNotSent"));
    }

    public StringsProvider getStringsProvider() {
        return this.stringsProvider;
    }

    public void setStringsProvider(StringsProvider stringsProvider) {
        this.stringsProvider = stringsProvider;
    }

    public void incrementSignificantEventsCountWithKey(String str) {
        this.tracker.incrementSignificantEventsCountWithKey(str);
    }

    public void completeSurvey() {
        this.tracker.completeSurvey();
    }

    public void abortSurvey() {
        this.tracker.abortSurvey();
    }

    public void declineInvitation() {
        this.tracker.declineInvitation();
    }

    public void acceptInvitation() {
        this.tracker.acceptInvitation();
    }

    public void acceptedLocalNotification() {
        this.tracker.acceptLocalNotification();
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public void triggerInvitation(String str) {
        this.tracker.triggerInvitation(str);
        new EventPublisherImpl(this.appContext).publishEvent(new LifecycleEvent(EventType.INVITE_PRESENTED));
    }

    public void triggerSurvey(String str) {
        this.tracker.triggerSurvey(str);
    }

    public String getSurveyUrlBase() {
        return this.configuration.getSurveyUrlBase();
    }

    public PersistedState getState() {
        return this.tracker.getState();
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    private Pair<Integer, Integer> getPhysicalScreenSize(DisplayMetrics displayMetrics) {
        return new Pair(Integer.valueOf((int) Math.round((((double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi)) * 2.54d) * ScreenRecorder.ORIENTATION_SAMPLING_THRESHOLD)), Integer.valueOf((int) Math.round((((double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi)) * 2.54d) * ScreenRecorder.ORIENTATION_SAMPLING_THRESHOLD)));
    }

    private void showErrorDialog(Activity activity, String str, String str2) {
        new Builder(activity).setMessage(str2).setIcon(17301543).setTitle(str).setNeutralButton(this.stringsProvider.getAlertButtonText(), new 1(this, activity)).show();
    }

    public void onActivityStarted(Activity activity) {
        this.currentActivity = null;
        notifyObservers(new 2(this, activity));
    }

    public void onActivityResumed(Activity activity) {
        this.currentActivity = activity;
        this.tracker.setViewPresenter(new DefaultTrackerViewPresenter(this.currentActivity));
        notifyObservers(new 3(this, activity));
    }

    public void onActivityPaused(Activity activity) {
        notifyObservers(new 4(this, activity));
    }

    public void addObserver(TriggerEventObserver triggerEventObserver) {
        this.observers.add(new WeakReference(triggerEventObserver));
    }

    public boolean removeObserver(TriggerEventObserver triggerEventObserver) {
        for (WeakReference weakReference : this.observers) {
            TriggerEventObserver triggerEventObserver2 = (TriggerEventObserver) weakReference.get();
            if (triggerEventObserver2 != null && triggerEventObserver2 == triggerEventObserver) {
                return this.observers.remove(weakReference);
            }
        }
        return false;
    }

    private void notifyObservers(TriggerObserverNotification triggerObserverNotification) {
        for (WeakReference weakReference : this.observers) {
            TriggerEventObserver triggerEventObserver = (TriggerEventObserver) weakReference.get();
            if (triggerEventObserver != null) {
                triggerObserverNotification.execute(triggerEventObserver);
            }
        }
    }

    public void onInvitePresented(MeasureConfiguration measureConfiguration) {
        notifyObservers(new 5(this, measureConfiguration.getSurveyId()));
    }

    public void onInviteAccepted(MeasureConfiguration measureConfiguration) {
        notifyObservers(new 6(this, measureConfiguration.getSurveyId()));
    }

    public void onInviteDeclined(MeasureConfiguration measureConfiguration) {
        notifyObservers(new 7(this, measureConfiguration.getSurveyId()));
    }

    public void onSurveyCompleted(MeasureConfiguration measureConfiguration) {
        notifyObservers(new 8(this, measureConfiguration.getSurveyId()));
    }

    public void onSurveyAborted(MeasureConfiguration measureConfiguration) {
        notifyObservers(new 9(this, measureConfiguration.getSurveyId()));
    }

    public void onSamplingCheckCompleted(MeasureConfiguration measureConfiguration, boolean z) {
    }

    public void onReactivated() {
        notifyObservers(new 10(this));
    }
}
