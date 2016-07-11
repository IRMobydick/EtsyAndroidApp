package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import org.json.JSONObject;

@jw
public class zzhe extends Handler {
    private final it zzIY;

    public zzhe(Context context) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this(new iu(context));
    }

    public zzhe(it itVar) {
        this.zzIY = itVar;
    }

    private void zzd(JSONObject jSONObject) {
        try {
            this.zzIY.a(jSONObject.getString("request_id"), jSONObject.getString("base_url"), jSONObject.getString("html"));
        } catch (Exception e) {
        }
    }

    public void handleMessage(Message message) {
        try {
            Bundle data = message.getData();
            if (data != null) {
                JSONObject jSONObject = new JSONObject(data.getString(ActivityFeedEntity.DATA));
                if ("fetch_html".equals(jSONObject.getString("message_name"))) {
                    zzd(jSONObject);
                }
            }
        } catch (Exception e) {
        }
    }
}
