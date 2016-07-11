package com.etsy.android.lib.eventhorizon;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.logger.EtsyDebug;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.k;
import de.tavendo.autobahn.m;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"Registered"})
public class EventHorizonService extends Service {
    private k mConnection;
    private String mServerUrl;

    /* renamed from: com.etsy.android.lib.eventhorizon.EventHorizonService.1 */
    class C04701 extends m {
        final /* synthetic */ EventHorizonService f1705a;

        C04701(EventHorizonService eventHorizonService) {
            this.f1705a = eventHorizonService;
        }

        public void m1750a(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                EtsyDebug.m1906b(EventHorizonService.class.getName(), "payload" + str);
                EventHorizon.m1752a(jSONObject);
            } catch (JSONException e) {
                EtsyDebug.m1919e(EventHorizonActivity.class.getName(), e.toString());
            }
        }
    }

    public void onCreate() {
        super.onCreate();
        this.mServerUrl = EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.cB) + "?sample_rate=100";
        connect();
    }

    public void onDestroy() {
        disconnect();
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void connect() {
        this.mConnection = new k();
        try {
            this.mConnection.a(this.mServerUrl, new C04701(this));
            EtsyDebug.m1912c(EventHorizonService.class.getName(), "Connected successfully to " + this.mServerUrl);
        } catch (WebSocketException e) {
            EtsyDebug.m1919e(EventHorizonActivity.class.getName(), e.getLocalizedMessage());
        }
    }

    public void disconnect() {
        this.mConnection.a();
        EtsyDebug.m1912c(EventHorizonService.class.getName(), "Disconnected successfully from " + this.mServerUrl);
    }
}
