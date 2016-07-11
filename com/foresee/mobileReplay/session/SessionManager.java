package com.foresee.mobileReplay.session;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.SharedPreferences.Editor;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.etsy.android.lib.models.AppBuild;
import com.foresee.mobileReplay.ActivityLifecycleHandler;
import com.foresee.mobileReplay.annotations.RecordDisabled;
import com.foresee.mobileReplay.data.SessionRepository;
import com.foresee.mobileReplay.domain.DeviceInfoEventData;
import com.foresee.mobileReplay.domain.Session;
import com.foresee.mobileReplay.domain.SessionEvent;
import com.foresee.mobileReplay.domain.SessionStartEventData;
import com.foresee.mobileReplay.http.BlacklistCallback;
import com.foresee.mobileReplay.http.BlacklistCheck;
import com.foresee.mobileReplay.imageDiff.DefaultDiffingParamStrategy;
import com.foresee.mobileReplay.jobQueue.EndSessionJob;
import com.foresee.mobileReplay.jobQueue.JobQueueService;
import com.foresee.mobileReplay.recorder.EventPublisher;
import com.foresee.mobileReplay.recorder.FsOrientationEventListener;
import com.foresee.mobileReplay.recorder.ScreenRecorder;
import com.foresee.mobileReplay.recorder.WebViewRegistry;
import com.foresee.mobileReplay.recorder.WebViewRegistryImpl;
import com.foresee.mobileReplay.recorder.WhitelistMaskingPolicy;
import com.foresee.mobileReplay.visualizer.MaskingVisualizationController;
import com.foresee.sdk.configuration.MaskingConfig;
import com.foresee.sdk.configuration.MaskingConfigurationLoader;
import com.foresee.sdk.events.LifecycleEvent.EventType;
import com.foresee.sdk.events.ReplayEventObserver;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.UUID;

public class SessionManager implements ActivityLifecycleHandler, BlacklistCallback, SessionStateContext, UncaughtExceptionHandler {
    public static final String EVENT_SUBMISSION_COMPLETE = "SUBMISSION_COMPLETE";
    @Inject
    ActivityManager activityManager;
    private BlacklistCheck blacklistCheck;
    private String cid;
    private Application context;
    private Activity currentActivity;
    private UncaughtExceptionHandler defaultHandler;
    private Timer inactivityTimer;
    private JobQueueService jobQueueService;
    private MaskingConfig maskingConfig;
    private MaskingVisualizationController maskingVisualizer;
    private List<WeakReference<ReplayEventObserver>> observers;
    private FsOrientationEventListener orientationListener;
    private BroadcastReceiver poolingIntentReceiver;
    private ScreenRecorder recorder;
    private ReplaySessionState replaySessionState;
    @Inject
    SensorManager sensorManager;
    private Session sessionData;
    private String sessionGroupId;
    private String sessionId;
    private SessionRepository sessionRepository;
    private BroadcastReceiver storageIntentReceiver;
    private WebViewRegistry webViewRegistry;
    @Inject
    WindowManager windowManager;

    public void setMaskingDebugEnabled(boolean z) {
        this.recorder.setMaskingDebugEnabled(z);
    }

    @Inject
    public SessionManager(Application application, SessionRepository sessionRepository, JobQueueService jobQueueService, BlacklistCheck blacklistCheck, @Named("downSampleRatio") float f, WindowManager windowManager) {
        this.inactivityTimer = new Timer();
        this.observers = new ArrayList();
        this.context = application;
        this.sessionRepository = sessionRepository;
        this.replaySessionState = sessionRepository.retrieveSessionState();
        this.jobQueueService = jobQueueService;
        this.maskingConfig = (MaskingConfig) MaskingConfigurationLoader.getInstance().loadFromConfigFile(application);
        if (this.maskingConfig != null) {
            this.webViewRegistry = new WebViewRegistryImpl(this.maskingConfig.getWebViewMaskingConfig());
        } else {
            this.webViewRegistry = new WebViewRegistryImpl();
        }
        this.recorder = new ScreenRecorder(sessionRepository, jobQueueService, f, new WhitelistMaskingPolicy(this.webViewRegistry), new DefaultDiffingParamStrategy(windowManager));
        this.blacklistCheck = blacklistCheck;
        this.storageIntentReceiver = new 1(this);
        this.poolingIntentReceiver = new 2(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(EventType.POOLING_DENIED.value());
        this.context.registerReceiver(this.poolingIntentReceiver, intentFilter);
    }

    private void resetTimer() {
        if (this.inactivityTimer != null) {
            this.inactivityTimer.cancel();
        }
        this.inactivityTimer = new Timer();
    }

    public MaskingVisualizationController getMaskingVisualizer() {
        if (this.maskingVisualizer == null) {
            this.maskingVisualizer = new MaskingVisualizationController(this.context);
        }
        return this.maskingVisualizer;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        Log.d("FORESEE_CAPTURE", String.format("SessionManager.onActivityStarted: %s (session state =  %s) %s", new Object[]{activity.getLocalClassName(), this.replaySessionState.toString(), toString()}));
        this.replaySessionState.onApplicationStarted(activity.getApplication(), this);
    }

    public void onActivityResumed(Activity activity) {
        resetTimer();
        this.replaySessionState.onActivityResumed(activity, this);
        this.currentActivity = activity;
    }

    public void onActivityPaused(Activity activity) {
        Application application = activity.getApplication();
        this.replaySessionState.onActivityPaused(activity, this);
        this.currentActivity = null;
        if (activity.hasWindowFocus() || activity.isFinishing()) {
            this.inactivityTimer.schedule(new 3(this, application, this), 2000);
        }
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onFragmentViewCreated(View view) {
        this.recorder.fragmentViewCreated(view);
    }

    public void setState(ReplaySessionState replaySessionState) {
        Log.d("FORESEE_CAPTURE", String.format("Entering %s state (from %s)", new Object[]{replaySessionState.getClass().getName(), this.replaySessionState.getClass().getName()}));
        this.replaySessionState.onExitedState(this);
        this.replaySessionState = replaySessionState;
        replaySessionState.onEnteredState(this);
        if (replaySessionState.shouldPersist()) {
            this.sessionRepository.persistSessionState(this.replaySessionState);
        }
    }

    public ReplaySessionState getState() {
        return this.replaySessionState;
    }

    public void requestBeginSession() {
        this.blacklistCheck.checkBlacklistService(this.cid, this);
    }

    public void blacklistServiceCallback(boolean z) {
        this.replaySessionState.onBlacklistReturned(this, z);
    }

    public void blacklistUnavailable() {
        Log.d("FORESEE_BLACKLIST", "Blacklist service unavailable");
        this.replaySessionState.onBlacklistUnreachable(this);
    }

    public void setCid(String str) {
        this.cid = str;
    }

    public String getSessionGroupId() {
        return this.sessionGroupId;
    }

    public boolean beginSession() {
        if (this.sessionRepository.requestSessionStart(true)) {
            this.sessionGroupId = this.sessionRepository.retrieveSessionGroupId();
            this.sessionData = initializeSession();
            this.sessionId = this.sessionData.getSessionId();
            Log.d("FORESEE_DATA_CAPS", "Sufficient space for recording: starting session " + this.sessionId);
            Display defaultDisplay = ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay();
            DeviceInfoEventData deviceInfoEventData = new DeviceInfoEventData(AppBuild.ANDROID_PLATFORM, Build.MODEL, "Android", VERSION.SDK, defaultDisplay.getWidth(), defaultDisplay.getHeight(), this.activityManager.getMemoryClass());
            long time = new Date().getTime();
            this.recorder.publishSessionEvent(this.sessionGroupId, this.sessionId, new SessionEvent(deviceInfoEventData, time));
            this.recorder.publishSessionEvent(this.sessionGroupId, this.sessionId, new SessionEvent(new SessionStartEventData(UUID.randomUUID().toString()), time));
            this.context.registerReceiver(new 4(this), new IntentFilter(EVENT_SUBMISSION_COMPLETE));
            registerOrientationListener(this.recorder);
            return true;
        }
        Log.d("FORESEE_DATA_CAPS", "Insufficient space for recording: session cancelled");
        return false;
    }

    public void endSession() {
        this.recorder.stopRecording();
        new Handler(Looper.getMainLooper()).post(new 5(this));
        unregisterOrientationListener();
    }

    public void abortSession() {
        this.sessionRepository.updateSessionGroup(this.sessionData);
        this.recorder.persistSessionEvents();
        this.jobQueueService.enqueueJob(new EndSessionJob(this.sessionGroupId, this.sessionId));
    }

    public void detach(Activity activity) {
        if (activity != null && !activity.getClass().isAnnotationPresent(RecordDisabled.class)) {
            Log.d("FORESEE_CAPTURE", String.format("Detaching from activity: %s", new Object[]{activity.getLocalClassName()}));
            this.recorder.detach(activity);
        }
    }

    public void attach(Activity activity) {
        if (!activity.getClass().isAnnotationPresent(RecordDisabled.class)) {
            Log.d("FORESEE_CAPTURE", String.format("Attaching to activity: %s", new Object[]{activity.getLocalClassName()}));
            this.recorder.attach(activity, this.sessionGroupId, this.sessionData.getSessionId());
        }
    }

    public void startRecording() {
        this.recorder.startRecording();
    }

    public void stopRecording() {
        this.recorder.stopRecording();
    }

    public void onApplicationCreated() {
        this.defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void startSession(Application application) {
        Log.d("FORESEE_CAPTURE", "SessionManager.startSession");
        this.replaySessionState.onSessionStarted(application, this);
    }

    public void reactivate() {
        this.replaySessionState.onReactivated(this, this.currentActivity);
    }

    private Session initializeSession() {
        return new Session(UUID.randomUUID().toString(), new Date().getTime());
    }

    public void submit() {
        this.replaySessionState.onSubmit(this);
    }

    public void submitSessionGroup() {
        startSubmission();
    }

    private void startSubmission() {
        new Handler(Looper.getMainLooper()).post(new 6(this));
    }

    public void resumeSubmissionQueue() {
        new Handler(Looper.getMainLooper()).post(new 7(this));
    }

    public void initSessionGroup() {
        Editor edit = this.context.getSharedPreferences("sessionGroupData.json", 0).edit();
        Log.d("FORESEE_CAPTURE", "Initialising session group");
        this.sessionGroupId = UUID.randomUUID().toString();
        edit.putString("groupId", this.sessionGroupId);
        this.sessionRepository.removeSessionGroup(this.sessionGroupId);
        edit.commit();
    }

    public void registerStorageReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(EventType.STORAGE_EXCEEDED.value());
        intentFilter.addAction(EventType.STORAGE_ERROR.value());
        this.context.registerReceiver(this.storageIntentReceiver, intentFilter);
    }

    public void unregisterStorageReceiver() {
        try {
            this.context.unregisterReceiver(this.storageIntentReceiver);
        } catch (Exception e) {
        }
    }

    public void onReactivated() {
        notify(new 8(this));
    }

    public void onSubmissionCompleted() {
        notify(new 9(this));
    }

    public void onInterfaceActivity() {
        this.recorder.onInterfaceActivity();
    }

    public void deactivateRecording() {
        this.replaySessionState.onDeactivate(this);
    }

    public void onDeactivateRecording() {
        new DeactivateRecordingAsyncTask(this, this.sessionGroupId, null).execute(new Void[0]);
    }

    public void deleteAllSessionData() {
        Log.d("FORESEE_DATA_CAPS", "Deleting all data");
        this.sessionRepository.deleteData();
    }

    public void submissionComplete() {
        this.replaySessionState.onSubmissionComplete(this);
    }

    public void onNetworkConnected() {
        this.replaySessionState.onNetworkConnected(this);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        th.printStackTrace(new PrintWriter(new StringWriter()));
        Log.d("FORESEE_CAPTURE", String.format("Uncaught:\n%s", new Object[]{r0.toString()}));
        this.replaySessionState.onApplicationCrash(this);
        if (this.defaultHandler != null) {
            this.defaultHandler.uncaughtException(thread, th);
        }
    }

    private void registerOrientationListener(EventPublisher eventPublisher) {
        Sensor defaultSensor = this.sensorManager.getDefaultSensor(2);
        Sensor defaultSensor2 = this.sensorManager.getDefaultSensor(1);
        if (defaultSensor == null || defaultSensor2 == null) {
            Log.w("FORESEE_CAPTURE", "Device does not have sensors necessary to capture orientation changes");
            return;
        }
        this.orientationListener = new FsOrientationEventListener(this.sessionGroupId, this.sessionId, eventPublisher);
        this.sensorManager.registerListener(this.orientationListener, defaultSensor, 3);
        this.sensorManager.registerListener(this.orientationListener, defaultSensor2, 3);
    }

    private void unregisterOrientationListener() {
        this.sensorManager.unregisterListener(this.orientationListener);
    }

    public boolean isPendingReactivation() {
        return this.replaySessionState instanceof PendingReactivation;
    }

    public boolean isRecording() {
        return this.replaySessionState instanceof Recording;
    }

    public void addObserver(ReplayEventObserver replayEventObserver) {
        this.observers.add(new WeakReference(replayEventObserver));
    }

    public boolean removeObserver(ReplayEventObserver replayEventObserver) {
        for (WeakReference weakReference : this.observers) {
            ReplayEventObserver replayEventObserver2 = (ReplayEventObserver) weakReference.get();
            if (replayEventObserver2 != null && replayEventObserver2 == replayEventObserver) {
                return this.observers.remove(weakReference);
            }
        }
        return false;
    }

    private void notify(ReplayEventNotification replayEventNotification) {
        for (WeakReference weakReference : this.observers) {
            ReplayEventObserver replayEventObserver = (ReplayEventObserver) weakReference.get();
            if (replayEventObserver != null) {
                replayEventNotification.execute(replayEventObserver);
            }
        }
    }

    public List<WeakReference<View>> getMaskedViews() {
        return this.recorder.getMaskedViews();
    }

    public void unmaskView(View view) {
        this.recorder.unmaskView(view);
    }

    public void maskView(View view) {
        this.recorder.maskView(view);
    }

    public void logPageChange(String str) {
        this.recorder.publishPageEvent(str);
    }

    public void setCaptureRate(int i) {
        this.recorder.setCaptureRate(i);
    }

    public void resetCaptureRate() {
        this.recorder.resetCaptureRate();
    }

    public void enableRecording() {
        this.replaySessionState.onEnable(this);
    }

    public void disableRecording() {
        this.replaySessionState.onDisabled(this.context, this);
    }

    public void registerWebView(WebView webView) {
        this.webViewRegistry.registerWebView(webView);
    }

    public void deregisterWebView(WebView webView) {
        this.webViewRegistry.deregisterWebView(webView);
    }

    public void registerWebView(WebView webView, WebViewClient webViewClient) {
        this.webViewRegistry.registerWebView(webView, webViewClient);
    }
}
