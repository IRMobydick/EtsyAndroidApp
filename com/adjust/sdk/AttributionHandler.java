package com.adjust.sdk;

import android.net.Uri;
import android.net.Uri.Builder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.json.JSONObject;

public class AttributionHandler implements IAttributionHandler {
    private IActivityHandler activityHandler;
    private ActivityPackage attributionPackage;
    private boolean hasListener;
    private ILogger logger;
    private boolean paused;
    private ScheduledExecutorService scheduler;
    private TimerOnce timer;

    /* renamed from: com.adjust.sdk.AttributionHandler.1 */
    class C03641 implements Runnable {
        C03641() {
        }

        public void run() {
            AttributionHandler.this.getAttributionInternal();
        }
    }

    /* renamed from: com.adjust.sdk.AttributionHandler.2 */
    class C03652 implements Runnable {
        final /* synthetic */ JSONObject val$jsonResponse;

        C03652(JSONObject jSONObject) {
            this.val$jsonResponse = jSONObject;
        }

        public void run() {
            AttributionHandler.this.checkAttributionInternal(this.val$jsonResponse);
        }
    }

    public AttributionHandler(IActivityHandler iActivityHandler, ActivityPackage activityPackage, boolean z, boolean z2) {
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.logger = AdjustFactory.getLogger();
        if (this.scheduler != null) {
            this.timer = new TimerOnce(this.scheduler, new C03641());
        } else {
            this.logger.error("Timer not initialized, attribution handler is disabled", new Object[0]);
        }
        init(iActivityHandler, activityPackage, z, z2);
    }

    public void init(IActivityHandler iActivityHandler, ActivityPackage activityPackage, boolean z, boolean z2) {
        this.activityHandler = iActivityHandler;
        this.attributionPackage = activityPackage;
        this.paused = z;
        this.hasListener = z2;
    }

    public void getAttribution() {
        getAttribution(0);
    }

    public void checkAttribution(JSONObject jSONObject) {
        this.scheduler.submit(new C03652(jSONObject));
    }

    public void pauseSending() {
        this.paused = true;
    }

    public void resumeSending() {
        this.paused = false;
    }

    private void getAttribution(long j) {
        if (this.timer.getFireIn() <= j) {
            if (j != 0) {
                this.logger.debug("Waiting to query attribution in %d milliseconds", Long.valueOf(j));
            }
            this.timer.startIn(j);
        }
    }

    private void checkAttributionInternal(JSONObject jSONObject) {
        if (jSONObject != null) {
            AdjustAttribution fromJson = AdjustAttribution.fromJson(jSONObject.optJSONObject("attribution"));
            long optLong = jSONObject.optLong("ask_in", -1);
            if (optLong < 0) {
                this.activityHandler.tryUpdateAttribution(fromJson);
                this.activityHandler.setAskingAttribution(false);
                return;
            }
            this.activityHandler.setAskingAttribution(true);
            getAttribution(optLong);
        }
    }

    private void getAttributionInternal() {
        if (!this.hasListener) {
            return;
        }
        if (this.paused) {
            this.logger.debug("Attribution handler is paused", new Object[0]);
            return;
        }
        this.logger.verbose("%s", this.attributionPackage.getExtendedString());
        try {
            checkAttributionInternal(Util.readHttpResponse(Util.createGETHttpsURLConnection(buildUri(this.attributionPackage.getPath(), this.attributionPackage.getParameters()).toString(), this.attributionPackage.getClientSdk())));
        } catch (Exception e) {
            this.logger.error("Failed to get attribution (%s)", e.getMessage());
        }
    }

    private Uri buildUri(String str, Map<String, String> map) {
        Builder builder = new Builder();
        builder.scheme(Constants.SCHEME);
        builder.authority(Constants.AUTHORITY);
        builder.appendPath(str);
        for (Entry entry : map.entrySet()) {
            builder.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        return builder.build();
    }
}
