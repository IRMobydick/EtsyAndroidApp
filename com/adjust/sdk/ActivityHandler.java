package com.adjust.sdk;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import com.etsy.android.ui.nav.NotificationActivity;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

public class ActivityHandler extends HandlerThread implements IActivityHandler {
    private static final String ACTIVITY_STATE_NAME = "Activity state";
    private static final String ADJUST_PREFIX = "adjust_";
    private static final String ATTRIBUTION_NAME = "Attribution";
    private static long SESSION_INTERVAL = 0;
    private static long SUBSESSION_INTERVAL = 0;
    private static long TIMER_INTERVAL = 0;
    private static long TIMER_START = 0;
    private static final String TIME_TRAVEL = "Time travel!";
    private ActivityState activityState;
    private AdjustConfig adjustConfig;
    private AdjustAttribution attribution;
    private IAttributionHandler attributionHandler;
    private DeviceInfo deviceInfo;
    private boolean enabled;
    private ILogger logger;
    private boolean offline;
    private IPackageHandler packageHandler;
    private SessionHandler sessionHandler;
    private TimerCycle timer;

    /* renamed from: com.adjust.sdk.ActivityHandler.1 */
    class C03611 implements Runnable {
        C03611() {
        }

        public void run() {
            ActivityHandler.this.adjustConfig.onAttributionChangedListener.onAttributionChanged(ActivityHandler.this.attribution);
        }
    }

    /* renamed from: com.adjust.sdk.ActivityHandler.2 */
    class C03622 implements Runnable {
        C03622() {
        }

        public void run() {
            ActivityHandler.this.timerFired();
        }
    }

    class ReferrerClickTime {
        long clickTime;
        String referrer;

        ReferrerClickTime(String str, long j) {
            this.referrer = str;
            this.clickTime = j;
        }
    }

    final class SessionHandler extends Handler {
        private static final int BASE_ADDRESS = 72630;
        private static final int DEEP_LINK = 72636;
        private static final int END = 72633;
        private static final int EVENT = 72634;
        private static final int FINISH_TRACKING = 72635;
        private static final int INIT = 72631;
        private static final int SEND_REFERRER = 72637;
        private static final int START = 72632;
        private static final int TIMER_FIRED = 72639;
        private static final int UPDATE_STATUS = 72638;
        private final WeakReference<ActivityHandler> sessionHandlerReference;

        protected SessionHandler(Looper looper, ActivityHandler activityHandler) {
            super(looper);
            this.sessionHandlerReference = new WeakReference(activityHandler);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            ActivityHandler activityHandler = (ActivityHandler) this.sessionHandlerReference.get();
            if (activityHandler != null) {
                switch (message.arg1) {
                    case INIT /*72631*/:
                        activityHandler.initInternal();
                    case START /*72632*/:
                        activityHandler.startInternal();
                    case END /*72633*/:
                        activityHandler.endInternal();
                    case EVENT /*72634*/:
                        activityHandler.trackEventInternal((AdjustEvent) message.obj);
                    case FINISH_TRACKING /*72635*/:
                        activityHandler.finishedTrackingActivityInternal((JSONObject) message.obj);
                    case DEEP_LINK /*72636*/:
                        UrlClickTime urlClickTime = (UrlClickTime) message.obj;
                        activityHandler.readOpenUrlInternal(urlClickTime.url, urlClickTime.clickTime);
                    case SEND_REFERRER /*72637*/:
                        ReferrerClickTime referrerClickTime = (ReferrerClickTime) message.obj;
                        activityHandler.sendReferrerInternal(referrerClickTime.referrer, referrerClickTime.clickTime);
                    case UPDATE_STATUS /*72638*/:
                        activityHandler.updateStatusInternal();
                    case TIMER_FIRED /*72639*/:
                        activityHandler.timerFiredInternal();
                    default:
                }
            }
        }
    }

    class UrlClickTime {
        long clickTime;
        Uri url;

        UrlClickTime(Uri uri, long j) {
            this.url = uri;
            this.clickTime = j;
        }
    }

    private ActivityHandler(AdjustConfig adjustConfig) {
        super(Constants.LOGTAG, 1);
        setDaemon(true);
        start();
        this.logger = AdjustFactory.getLogger();
        this.sessionHandler = new SessionHandler(getLooper(), this);
        this.enabled = true;
        init(adjustConfig);
        Message obtain = Message.obtain();
        obtain.arg1 = 72631;
        this.sessionHandler.sendMessage(obtain);
    }

    public void init(AdjustConfig adjustConfig) {
        this.adjustConfig = adjustConfig;
    }

    public static ActivityHandler getInstance(AdjustConfig adjustConfig) {
        if (adjustConfig == null) {
            AdjustFactory.getLogger().error("AdjustConfig missing", new Object[0]);
            return null;
        } else if (adjustConfig.isValid()) {
            if (adjustConfig.processName != null) {
                int myPid = Process.myPid();
                ActivityManager activityManager = (ActivityManager) adjustConfig.context.getSystemService("activity");
                if (activityManager == null) {
                    return null;
                }
                for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                    if (runningAppProcessInfo.pid == myPid) {
                        if (!runningAppProcessInfo.processName.equalsIgnoreCase(adjustConfig.processName)) {
                            AdjustFactory.getLogger().info("Skipping initialization in background process (%s)", runningAppProcessInfo.processName);
                            return null;
                        }
                    }
                }
            }
            return new ActivityHandler(adjustConfig);
        } else {
            AdjustFactory.getLogger().error("AdjustConfig not initialized correctly", new Object[0]);
            return null;
        }
    }

    public void trackSubsessionStart() {
        Message obtain = Message.obtain();
        obtain.arg1 = 72632;
        this.sessionHandler.sendMessage(obtain);
    }

    public void trackSubsessionEnd() {
        Message obtain = Message.obtain();
        obtain.arg1 = 72633;
        this.sessionHandler.sendMessage(obtain);
    }

    public void trackEvent(AdjustEvent adjustEvent) {
        if (this.activityState == null) {
            trackSubsessionStart();
        }
        Message obtain = Message.obtain();
        obtain.arg1 = 72634;
        obtain.obj = adjustEvent;
        this.sessionHandler.sendMessage(obtain);
    }

    public void finishedTrackingActivity(JSONObject jSONObject) {
        if (jSONObject != null) {
            Message obtain = Message.obtain();
            obtain.arg1 = 72635;
            obtain.obj = jSONObject;
            this.sessionHandler.sendMessage(obtain);
        }
    }

    public void setEnabled(boolean z) {
        if (hasChangedState(isEnabled(), z, "Adjust already enabled", "Adjust already disabled")) {
            this.enabled = z;
            if (this.activityState == null) {
                trackSubsessionStart();
            } else {
                this.activityState.enabled = z;
                writeActivityState();
            }
            updateStatus(!z, "Pausing package handler and attribution handler to disable the SDK", "Package and attribution handler remain paused due to the SDK is offline", "Resuming package handler and attribution handler to enabled the SDK");
        }
    }

    private void updateStatus(boolean z, String str, String str2, String str3) {
        if (z) {
            this.logger.info(str, new Object[0]);
            trackSubsessionEnd();
        } else if (paused()) {
            this.logger.info(str2, new Object[0]);
        } else {
            this.logger.info(str3, new Object[0]);
            trackSubsessionStart();
        }
    }

    private boolean hasChangedState(boolean z, boolean z2, String str, String str2) {
        if (z != z2) {
            return true;
        }
        if (z) {
            this.logger.debug(str, new Object[0]);
            return false;
        }
        this.logger.debug(str2, new Object[0]);
        return false;
    }

    public void setOfflineMode(boolean z) {
        if (hasChangedState(this.offline, z, "Adjust already in offline mode", "Adjust already in online mode")) {
            this.offline = z;
            if (this.activityState == null) {
                trackSubsessionStart();
            }
            updateStatus(z, "Pausing package and attribution handler to put in offline mode", "Package and attribution handler remain paused because the SDK is disabled", "Resuming package handler and attribution handler to put in online mode");
        }
    }

    public boolean isEnabled() {
        if (this.activityState != null) {
            return this.activityState.enabled;
        }
        return this.enabled;
    }

    public void readOpenUrl(Uri uri, long j) {
        Message obtain = Message.obtain();
        obtain.arg1 = 72636;
        obtain.obj = new UrlClickTime(uri, j);
        this.sessionHandler.sendMessage(obtain);
    }

    public boolean tryUpdateAttribution(AdjustAttribution adjustAttribution) {
        if (adjustAttribution == null || adjustAttribution.equals(this.attribution)) {
            return false;
        }
        saveAttribution(adjustAttribution);
        launchAttributionListener();
        return true;
    }

    private void saveAttribution(AdjustAttribution adjustAttribution) {
        this.attribution = adjustAttribution;
        writeAttribution();
    }

    private void launchAttributionListener() {
        if (this.adjustConfig.onAttributionChangedListener != null) {
            new Handler(this.adjustConfig.context.getMainLooper()).post(new C03611());
        }
    }

    public void setAskingAttribution(boolean z) {
        this.activityState.askingAttribution = z;
        writeActivityState();
    }

    public ActivityPackage getAttributionPackage() {
        return new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, System.currentTimeMillis()).buildAttributionPackage();
    }

    public void sendReferrer(String str, long j) {
        Message obtain = Message.obtain();
        obtain.arg1 = 72637;
        obtain.obj = new ReferrerClickTime(str, j);
        this.sessionHandler.sendMessage(obtain);
    }

    private void updateStatus() {
        Message obtain = Message.obtain();
        obtain.arg1 = 72638;
        this.sessionHandler.sendMessage(obtain);
    }

    private void timerFired() {
        Message obtain = Message.obtain();
        obtain.arg1 = 72639;
        this.sessionHandler.sendMessage(obtain);
    }

    private void initInternal() {
        TIMER_INTERVAL = AdjustFactory.getTimerInterval();
        TIMER_START = AdjustFactory.getTimerStart();
        SESSION_INTERVAL = AdjustFactory.getSessionInterval();
        SUBSESSION_INTERVAL = AdjustFactory.getSubsessionInterval();
        this.deviceInfo = new DeviceInfo(this.adjustConfig.context, this.adjustConfig.sdkPrefix);
        if (AdjustConfig.ENVIRONMENT_PRODUCTION.equals(this.adjustConfig.environment)) {
            this.logger.setLogLevel(LogLevel.ASSERT);
        } else {
            this.logger.setLogLevel(this.adjustConfig.logLevel);
        }
        if (this.adjustConfig.eventBufferingEnabled.booleanValue()) {
            this.logger.info("Event buffering is enabled", new Object[0]);
        }
        if (Util.getPlayAdId(this.adjustConfig.context) == null) {
            this.logger.info("Unable to get Google Play Services Advertising ID at start time", new Object[0]);
        }
        if (this.adjustConfig.defaultTracker != null) {
            this.logger.info("Default tracker: '%s'", this.adjustConfig.defaultTracker);
        }
        if (this.adjustConfig.referrer != null) {
            sendReferrer(this.adjustConfig.referrer, this.adjustConfig.referrerClickTime);
        }
        readAttribution();
        readActivityState();
        this.packageHandler = AdjustFactory.getPackageHandler(this, this.adjustConfig.context, paused());
        this.attributionHandler = AdjustFactory.getAttributionHandler(this, getAttributionPackage(), paused(), this.adjustConfig.hasListener());
        this.timer = new TimerCycle(new C03622(), TIMER_START, TIMER_INTERVAL);
    }

    private void startInternal() {
        if (this.activityState == null || this.activityState.enabled) {
            updateStatusInternal();
            processSession();
            checkAttributionState();
            startTimer();
        }
    }

    private void processSession() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.activityState == null) {
            this.activityState = new ActivityState();
            this.activityState.sessionCount = 1;
            transferSessionPackage(currentTimeMillis);
            this.activityState.resetSessionAttributes(currentTimeMillis);
            this.activityState.enabled = this.enabled;
            writeActivityState();
            return;
        }
        long j = currentTimeMillis - this.activityState.lastActivity;
        if (j < 0) {
            this.logger.error(TIME_TRAVEL, new Object[0]);
            this.activityState.lastActivity = currentTimeMillis;
            writeActivityState();
        } else if (j > SESSION_INTERVAL) {
            r4 = this.activityState;
            r4.sessionCount++;
            this.activityState.lastInterval = j;
            transferSessionPackage(currentTimeMillis);
            this.activityState.resetSessionAttributes(currentTimeMillis);
            writeActivityState();
        } else if (j > SUBSESSION_INTERVAL) {
            r4 = this.activityState;
            r4.subsessionCount++;
            r4 = this.activityState;
            r4.sessionLength = j + r4.sessionLength;
            this.activityState.lastActivity = currentTimeMillis;
            writeActivityState();
            this.logger.info("Started subsession %d of session %d", Integer.valueOf(this.activityState.subsessionCount), Integer.valueOf(this.activityState.sessionCount));
        }
    }

    private void checkAttributionState() {
        if (!checkActivityState(this.activityState) || this.activityState.subsessionCount <= 1) {
            return;
        }
        if (this.attribution == null || this.activityState.askingAttribution) {
            this.attributionHandler.getAttribution();
        }
    }

    private void endInternal() {
        this.packageHandler.pauseSending();
        this.attributionHandler.pauseSending();
        stopTimer();
        if (updateActivityState(System.currentTimeMillis())) {
            writeActivityState();
        }
    }

    private void trackEventInternal(AdjustEvent adjustEvent) {
        if (checkActivityState(this.activityState) && isEnabled() && checkEvent(adjustEvent)) {
            long currentTimeMillis = System.currentTimeMillis();
            ActivityState activityState = this.activityState;
            activityState.eventCount++;
            updateActivityState(currentTimeMillis);
            this.packageHandler.addPackage(new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, currentTimeMillis).buildEventPackage(adjustEvent));
            if (this.adjustConfig.eventBufferingEnabled.booleanValue()) {
                this.logger.info("Buffered event %s", r0.getSuffix());
            } else {
                this.packageHandler.sendFirstPackage();
            }
            writeActivityState();
        }
    }

    private void finishedTrackingActivityInternal(JSONObject jSONObject) {
        if (jSONObject != null) {
            launchDeeplinkMain(jSONObject.optString(NotificationActivity.ETSY_DEEPLINK_PARAM, null));
            this.attributionHandler.checkAttribution(jSONObject);
        }
    }

    private void sendReferrerInternal(String str, long j) {
        ActivityPackage buildQueryStringClickPackage = buildQueryStringClickPackage(str, Constants.REFTAG, j);
        if (buildQueryStringClickPackage != null) {
            this.packageHandler.addPackage(buildQueryStringClickPackage);
        }
    }

    private void readOpenUrlInternal(Uri uri, long j) {
        if (uri != null) {
            ActivityPackage buildQueryStringClickPackage = buildQueryStringClickPackage(uri.getQuery(), NotificationActivity.ETSY_DEEPLINK_PARAM, j);
            if (buildQueryStringClickPackage != null) {
                this.packageHandler.addPackage(buildQueryStringClickPackage);
            }
        }
    }

    private ActivityPackage buildQueryStringClickPackage(String str, String str2, long j) {
        Object obj = null;
        if (str == null) {
            return null;
        }
        Map linkedHashMap = new LinkedHashMap();
        AdjustAttribution adjustAttribution = new AdjustAttribution();
        for (String readQueryString : str.split("&")) {
            if (readQueryString(readQueryString, linkedHashMap, adjustAttribution)) {
                obj = 1;
            }
        }
        if (obj == null) {
            return null;
        }
        String str3 = (String) linkedHashMap.remove(Constants.REFTAG);
        PackageBuilder packageBuilder = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, System.currentTimeMillis());
        packageBuilder.extraParameters = linkedHashMap;
        packageBuilder.attribution = adjustAttribution;
        packageBuilder.reftag = str3;
        if (str2 == Constants.REFTAG) {
            packageBuilder.referrer = str;
        }
        return packageBuilder.buildClickPackage(str2, j);
    }

    private boolean readQueryString(String str, Map<String, String> map, AdjustAttribution adjustAttribution) {
        String[] split = str.split("=");
        if (split.length != 2) {
            return false;
        }
        String str2 = split[0];
        if (!str2.startsWith(ADJUST_PREFIX)) {
            return false;
        }
        String str3 = split[1];
        if (str3.length() == 0) {
            return false;
        }
        str2 = str2.substring(ADJUST_PREFIX.length());
        if (str2.length() == 0) {
            return false;
        }
        if (!trySetAttribution(adjustAttribution, str2, str3)) {
            map.put(str2, str3);
        }
        return true;
    }

    private boolean trySetAttribution(AdjustAttribution adjustAttribution, String str, String str2) {
        if (str.equals("tracker")) {
            adjustAttribution.trackerName = str2;
            return true;
        } else if (str.equals("campaign")) {
            adjustAttribution.campaign = str2;
            return true;
        } else if (str.equals("adgroup")) {
            adjustAttribution.adgroup = str2;
            return true;
        } else if (!str.equals("creative")) {
            return false;
        } else {
            adjustAttribution.creative = str2;
            return true;
        }
    }

    private void updateStatusInternal() {
        updateAttributionHandlerStatus();
        updatePackageHandlerStatus();
    }

    private void updateAttributionHandlerStatus() {
        if (paused()) {
            this.attributionHandler.pauseSending();
        } else {
            this.attributionHandler.resumeSending();
        }
    }

    private void updatePackageHandlerStatus() {
        if (paused()) {
            this.packageHandler.pauseSending();
        } else {
            this.packageHandler.resumeSending();
        }
    }

    private void launchDeeplinkMain(String str) {
        if (str != null) {
            int i;
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(268435456);
            if (this.adjustConfig.context.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
                i = 1;
            } else {
                i = 0;
            }
            if (i == 0) {
                this.logger.error("Unable to open deep link (%s)", str);
                return;
            }
            this.logger.info("Open deep link (%s)", str);
            this.adjustConfig.context.startActivity(intent);
        }
    }

    private boolean updateActivityState(long j) {
        if (!checkActivityState(this.activityState)) {
            return false;
        }
        long j2 = j - this.activityState.lastActivity;
        if (j2 > SESSION_INTERVAL) {
            return false;
        }
        this.activityState.lastActivity = j;
        if (j2 < 0) {
            this.logger.error(TIME_TRAVEL, new Object[0]);
        } else {
            ActivityState activityState = this.activityState;
            activityState.sessionLength += j2;
            activityState = this.activityState;
            activityState.timeSpent = j2 + activityState.timeSpent;
        }
        return true;
    }

    public static boolean deleteActivityState(Context context) {
        return context.deleteFile(Constants.ACTIVITY_STATE_FILENAME);
    }

    public static boolean deleteAttribution(Context context) {
        return context.deleteFile(Constants.ATTRIBUTION_FILENAME);
    }

    private void transferSessionPackage(long j) {
        this.packageHandler.addPackage(new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, j).buildSessionPackage());
        this.packageHandler.sendFirstPackage();
    }

    private void startTimer() {
        if (!paused()) {
            this.timer.start();
        }
    }

    private void stopTimer() {
        this.timer.suspend();
    }

    private void timerFiredInternal() {
        if (paused()) {
            stopTimer();
            return;
        }
        this.logger.debug("Session timer fired", new Object[0]);
        this.packageHandler.sendFirstPackage();
        if (updateActivityState(System.currentTimeMillis())) {
            writeActivityState();
        }
    }

    private void readActivityState() {
        this.activityState = (ActivityState) Util.readObject(this.adjustConfig.context, Constants.ACTIVITY_STATE_FILENAME, ACTIVITY_STATE_NAME);
    }

    private void readAttribution() {
        this.attribution = (AdjustAttribution) Util.readObject(this.adjustConfig.context, Constants.ATTRIBUTION_FILENAME, ATTRIBUTION_NAME);
    }

    private synchronized void writeActivityState() {
        Util.writeObject(this.activityState, this.adjustConfig.context, Constants.ACTIVITY_STATE_FILENAME, ACTIVITY_STATE_NAME);
    }

    private void writeAttribution() {
        Util.writeObject(this.attribution, this.adjustConfig.context, Constants.ATTRIBUTION_FILENAME, ATTRIBUTION_NAME);
    }

    private boolean checkEvent(AdjustEvent adjustEvent) {
        if (adjustEvent == null) {
            this.logger.error("Event missing", new Object[0]);
            return false;
        } else if (adjustEvent.isValid()) {
            return true;
        } else {
            this.logger.error("Event not initialized correctly", new Object[0]);
            return false;
        }
    }

    private boolean checkActivityState(ActivityState activityState) {
        if (activityState != null) {
            return true;
        }
        this.logger.error("Missing activity state.", new Object[0]);
        return false;
    }

    private boolean paused() {
        return this.offline || !isEnabled();
    }
}
