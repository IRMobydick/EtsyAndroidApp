package com.foresee.mobileReplay.recorder;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.foresee.mobileReplay.session.WebViewEventSource;
import com.foresee.mobileReplay.session.WebViewEventSource.EventListener;
import com.foresee.sdk.instrumentation.GlobalActivityListener;
import fs.org.json.JSONArray;
import fs.org.json.JSONException;
import fs.org.json.JSONObject;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebViewMaskingInterface implements WebViewEventSource {
    public static final String NAME = "masking";
    private List<WeakReference<EventListener>> eventListeners;
    private long lastReceived;
    private List<WeakReference<GlobalActivityListener>> scrollListeners;
    private WebView webView;

    public WebViewMaskingInterface(WebView webView) {
        this.eventListeners = new ArrayList();
        this.scrollListeners = new ArrayList();
        this.webView = webView;
        this.lastReceived = new Date().getTime();
    }

    @JavascriptInterface
    public void setMaskRect(String str) {
        Log.v("FORESEE_MASKING", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = jSONObject.getJSONObject("offset");
            Point point = new Point();
            point.x = jSONObject2.getInt("windowXOffset");
            point.y = jSONObject2.getInt("windowYOffset");
            JSONArray jSONArray = jSONObject.getJSONArray("masks");
            Rect[] rectArr = new Rect[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                int i2 = jSONObject3.getInt(EtsyDialogFragment.OPT_X_BUTTON);
                int i3 = jSONObject3.getInt("y");
                i3 += point.y;
                i2 += point.x;
                rectArr[i] = new Rect(i2, i3, jSONObject3.getInt("w") + i2, jSONObject3.getInt("h") + i3);
            }
            notifyListenersMasksUpdated(rectArr);
            this.lastReceived = new Date().getTime();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("FORESEE_MASKING", e.getMessage(), e);
        }
    }

    @JavascriptInterface
    public void log(String str) {
        Log.d("FORESEE_MASKING", "JS log: " + str);
    }

    @JavascriptInterface
    public void logVerbose(String str) {
        Log.v("FORESEE_MASKING", "JS log: " + str);
    }

    @JavascriptInterface
    public void logObject(String str) {
        try {
            Log.d("FORESEE_MASKING", "JsonObject received: " + new JSONObject(str).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addEventListener(EventListener eventListener) {
        this.eventListeners.add(new WeakReference(eventListener));
    }

    @JavascriptInterface
    public void onReady() {
        for (WeakReference weakReference : this.eventListeners) {
            EventListener eventListener = (EventListener) weakReference.get();
            if (eventListener != null) {
                eventListener.onWebviewReady(this.webView);
            } else {
                Log.d("FORESEE_MASKING", "onReady webview not found in map");
            }
        }
    }

    public void removeEventListener(EventListener eventListener) {
        for (WeakReference weakReference : this.eventListeners) {
            EventListener eventListener2 = (EventListener) weakReference.get();
            if (eventListener2 != null && eventListener2 == eventListener) {
                this.eventListeners.remove(weakReference);
                return;
            }
        }
    }

    private void notifyListenersMasksUpdated(Rect[] rectArr) {
        for (WeakReference weakReference : this.eventListeners) {
            EventListener eventListener = (EventListener) weakReference.get();
            if (eventListener != null) {
                eventListener.onMaskRectsUpdated(rectArr, this.webView);
            }
        }
    }
}
