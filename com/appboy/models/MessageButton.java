package com.appboy.models;

import android.net.Uri;
import bo.app.fg;
import bo.app.fj;
import com.appboy.Constants;
import com.appboy.enums.inappmessage.ClickAction;
import com.appboy.support.AppboyLogger;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import org.json.JSONObject;

public class MessageButton implements IPutIntoJson<JSONObject> {
    private static final String f940a;
    private JSONObject f941b;
    private int f942c;
    private ClickAction f943d;
    private Uri f944e;
    private String f945f;
    private int f946g;
    private int f947h;

    static {
        f940a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, MessageButton.class.getName()});
    }

    public MessageButton() {
        this.f942c = -1;
        this.f943d = ClickAction.NONE;
        this.f946g = 0;
        this.f947h = 0;
    }

    public MessageButton(JSONObject jSONObject) {
        this(jSONObject, jSONObject.optInt(ResponseConstants.ID, -1), (ClickAction) fg.m339a(jSONObject, "click_action", ClickAction.class, ClickAction.NEWS_FEED), jSONObject.optString(Constants.APPBOY_PUSH_DEEP_LINK_KEY), jSONObject.optString(FindsModule.FIELD_TEXT), jSONObject.optInt("bg_color"), jSONObject.optInt("text_color"));
    }

    private MessageButton(JSONObject jSONObject, int i, ClickAction clickAction, String str, String str2, int i2, int i3) {
        this.f942c = -1;
        this.f943d = ClickAction.NONE;
        this.f946g = 0;
        this.f947h = 0;
        this.f941b = jSONObject;
        this.f942c = i;
        this.f943d = clickAction;
        if (this.f943d == ClickAction.URI && !fj.m354c(str)) {
            this.f944e = Uri.parse(str);
        }
        this.f945f = str2;
        this.f946g = i2;
        this.f947h = i3;
    }

    public int getId() {
        return this.f942c;
    }

    public ClickAction getClickAction() {
        return this.f943d;
    }

    public Uri getUri() {
        return this.f944e;
    }

    public String getText() {
        return this.f945f;
    }

    public int getBackgroundColor() {
        return this.f946g;
    }

    public int getTextColor() {
        return this.f947h;
    }

    public boolean setClickAction(ClickAction clickAction) {
        if (clickAction != ClickAction.URI) {
            this.f943d = clickAction;
            this.f944e = null;
            return true;
        }
        AppboyLogger.m664e(f940a, "A non-null URI is required in order to set the button ClickAction to URI.");
        return false;
    }

    public boolean setClickAction(ClickAction clickAction, Uri uri) {
        if (uri == null && clickAction == ClickAction.URI) {
            AppboyLogger.m664e(f940a, "A non-null URI is required in order to set the button ClickAction to URI.");
            return false;
        } else if (uri == null || clickAction != ClickAction.URI) {
            return setClickAction(clickAction);
        } else {
            this.f943d = clickAction;
            this.f944e = uri;
            return true;
        }
    }

    public void setBackgroundColor(int i) {
        this.f946g = i;
    }

    public void setTextColor(int i) {
        this.f947h = i;
    }

    public void setText(String str) {
        this.f945f = str;
    }

    public JSONObject forJsonPut() {
        return this.f941b;
    }
}
