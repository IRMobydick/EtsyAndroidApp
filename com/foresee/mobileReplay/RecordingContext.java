package com.foresee.mobileReplay;

import android.app.Activity;
import android.app.Application;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.foresee.mobileReplay.session.Disabled;
import com.foresee.mobileReplay.session.SessionManager;
import com.foresee.mobileReplay.session.SessionManagerFactory;
import com.foresee.sdk.events.ReplayEventObserver;
import com.google.inject.Module;
import com.google.inject.Singleton;
import java.lang.ref.WeakReference;
import java.util.List;
import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;

@Singleton
public class RecordingContext implements RecordingModule {
    private static RecordingContext instance;
    private Application context;
    private RoboInjector injector;
    private boolean maskingDebugEnabled;
    private boolean perfLoggingEnabled;
    private SessionManager sessionManager;

    RecordingContext() {
        this(false, false);
    }

    RecordingContext(boolean z, boolean z2) {
        this.perfLoggingEnabled = z;
        setMaskingDebugEnabled(z2);
    }

    public static RecordingContext instance() {
        return instance(false, false);
    }

    public static RecordingContext instance(boolean z, boolean z2) {
        if (instance == null) {
            instance = new RecordingContext(z, z2);
        }
        return instance;
    }

    public void startSession() {
        this.sessionManager.startSession(this.context);
    }

    public void endSession() {
        this.sessionManager.endSession();
    }

    public void startRecording() {
        this.sessionManager.startRecording();
    }

    public void stopRecording() {
        this.sessionManager.stopRecording();
    }

    public void onApplicationCreated(Application application, String str) {
        this.context = application;
        RoboGuice.setBaseApplicationInjector(application, RoboGuice.DEFAULT_STAGE, new Module[]{RoboGuice.newDefaultRoboModule(application), new MobileReplayModule(this.perfLoggingEnabled)});
        this.injector = RoboGuice.getInjector(application);
        this.sessionManager = ((SessionManagerFactory) this.injector.getInstance(SessionManagerFactory.class)).createManager(str);
        this.sessionManager.onApplicationCreated();
        this.sessionManager.setMaskingDebugEnabled(this.maskingDebugEnabled);
    }

    public void onActivityStarted(Activity activity) {
        this.sessionManager.onActivityStarted(activity);
    }

    public void onActivityResumed(Activity activity) {
        this.sessionManager.onActivityResumed(activity);
    }

    public void onActivityPaused(Activity activity) {
        this.sessionManager.onActivityPaused(activity);
    }

    public void onActivityDestroyed(Activity activity) {
        this.sessionManager.onActivityDestroyed(activity);
    }

    public void onFragmentViewCreated(View view) {
        this.sessionManager.onFragmentViewCreated(view);
    }

    public void submitSessionGroup() {
        this.sessionManager.submit();
    }

    public String getSessionGroupId() {
        return this.sessionManager.getSessionGroupId();
    }

    public void reactivate() {
        this.sessionManager.reactivate();
    }

    public void deactivateRecording() {
        this.sessionManager.deactivateRecording();
    }

    public boolean isPendingReactivation() {
        return this.sessionManager.isPendingReactivation();
    }

    public boolean isRecording() {
        return this.sessionManager.isRecording();
    }

    public List<WeakReference<View>> getMaskedViews() {
        return this.sessionManager.getMaskedViews();
    }

    public void unmaskView(View view) {
        this.sessionManager.unmaskView(view);
    }

    public void maskView(View view) {
        this.sessionManager.maskView(view);
    }

    public void logPageChange(String str) {
        this.sessionManager.logPageChange(str);
    }

    public void setCaptureRate(int i) {
        this.sessionManager.setCaptureRate(i);
    }

    public void resetCaptureRate() {
        this.sessionManager.resetCaptureRate();
    }

    public boolean isEnabled() {
        return !(this.sessionManager.getState() instanceof Disabled);
    }

    public void enableRecording() {
        this.sessionManager.enableRecording();
    }

    public void disableRecording() {
        this.sessionManager.disableRecording();
    }

    public void submissionComplete() {
        this.sessionManager.submissionComplete();
    }

    public void onNetworkConnected() {
        this.sessionManager.onNetworkConnected();
    }

    public void addObserver(ReplayEventObserver replayEventObserver) {
        this.sessionManager.addObserver(replayEventObserver);
    }

    public void registerWebView(WebView webView) {
        this.sessionManager.registerWebView(webView);
    }

    public void deregisterWebView(WebView webView) {
        this.sessionManager.deregisterWebView(webView);
    }

    public void registerWebView(WebView webView, WebViewClient webViewClient) {
        this.sessionManager.registerWebView(webView, webViewClient);
    }

    public void onInterfaceActivity() {
        this.sessionManager.onInterfaceActivity();
    }

    public void setMaskingDebugEnabled(boolean z) {
        this.maskingDebugEnabled = z;
        if (this.sessionManager != null) {
            this.sessionManager.setMaskingDebugEnabled(z);
        }
    }
}
