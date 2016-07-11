package com.google.android.gms.internal;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.google.android.gms.ads.internal.util.client.C1129c;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

@jw
public class im {
    private final no f5092a;
    private final String f5093b;

    public im(no noVar) {
        this(noVar, StringUtils.EMPTY);
    }

    public im(no noVar, String str) {
        this.f5092a = noVar;
        this.f5093b = str;
    }

    public void m6755a(int i, int i2, int i3, int i4) {
        try {
            this.f5092a.b("onSizeChanged", new JSONObject().put(EtsyDialogFragment.OPT_X_BUTTON, i).put("y", i2).put(ResponseConstants.WIDTH, i3).put(ResponseConstants.HEIGHT, i4));
        } catch (Throwable e) {
            C1129c.m6189b("Error occured while dispatching size change.", e);
        }
    }

    public void m6756a(int i, int i2, int i3, int i4, float f, int i5) {
        try {
            this.f5092a.b("onScreenInfoChanged", new JSONObject().put(ResponseConstants.WIDTH, i).put(ResponseConstants.HEIGHT, i2).put("maxSizeWidth", i3).put("maxSizeHeight", i4).put("density", (double) f).put("rotation", i5));
        } catch (Throwable e) {
            C1129c.m6189b("Error occured while obtaining screen information.", e);
        }
    }

    public void m6757b(int i, int i2, int i3, int i4) {
        try {
            this.f5092a.b("onDefaultPositionReceived", new JSONObject().put(EtsyDialogFragment.OPT_X_BUTTON, i).put("y", i2).put(ResponseConstants.WIDTH, i3).put(ResponseConstants.HEIGHT, i4));
        } catch (Throwable e) {
            C1129c.m6189b("Error occured while dispatching default position.", e);
        }
    }

    public void m6758b(String str) {
        try {
            this.f5092a.b("onError", new JSONObject().put(ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE, str).put("action", this.f5093b));
        } catch (Throwable e) {
            C1129c.m6189b("Error occurred while dispatching error event.", e);
        }
    }

    public void m6759c(String str) {
        try {
            this.f5092a.b("onReadyEventReceived", new JSONObject().put("js", str));
        } catch (Throwable e) {
            C1129c.m6189b("Error occured while dispatching ready Event.", e);
        }
    }

    public void m6760d(String str) {
        try {
            this.f5092a.b("onStateChanged", new JSONObject().put(ResponseConstants.STATE, str));
        } catch (Throwable e) {
            C1129c.m6189b("Error occured while dispatching state change.", e);
        }
    }
}
