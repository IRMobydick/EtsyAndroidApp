package com.appboy.models;

import android.graphics.Bitmap;
import android.net.Uri;
import bo.app.ce;
import bo.app.da;
import bo.app.fg;
import bo.app.fj;
import com.appboy.Constants;
import com.appboy.enums.inappmessage.ClickAction;
import com.appboy.enums.inappmessage.DismissType;
import com.appboy.support.AppboyLogger;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public abstract class InAppMessageBase implements IInAppMessage, IPutIntoJson<JSONObject> {
    public static final int INAPP_MESSAGE_DURATION_DEFAULT_MILLIS = 5000;
    public static final int INAPP_MESSAGE_DURATION_MIN_MILLIS = 999;
    public static final String TYPE = "type";
    protected static final String f906a;
    protected String f907b;
    protected String f908c;
    protected ce f909d;
    private String f910e;
    private Map<String, String> f911f;
    private boolean f912g;
    private boolean f913h;
    private ClickAction f914i;
    private Uri f915j;
    private DismissType f916k;
    private int f917l;
    private JSONObject f918m;
    private boolean f919n;
    private boolean f920o;
    private int f921p;
    private int f922q;
    private int f923r;
    private int f924s;
    private String f925t;
    private String f926u;
    private Bitmap f927v;
    private boolean f928w;

    static {
        f906a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, InAppMessageBase.class.getName()});
    }

    protected InAppMessageBase() {
        this.f912g = true;
        this.f913h = true;
        this.f914i = ClickAction.NONE;
        this.f916k = DismissType.AUTO_DISMISS;
        this.f917l = INAPP_MESSAGE_DURATION_DEFAULT_MILLIS;
        this.f919n = false;
        this.f920o = false;
        this.f921p = 0;
        this.f922q = 0;
        this.f923r = 0;
        this.f924s = 0;
        this.f928w = false;
    }

    public InAppMessageBase(JSONObject jSONObject, ce ceVar) {
        this(jSONObject.optString(ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE), fg.m341a(jSONObject.optJSONObject("extras"), new HashMap()), (ClickAction) fg.m339a(jSONObject, "click_action", ClickAction.class, ClickAction.NONE), jSONObject.optString(Constants.APPBOY_PUSH_DEEP_LINK_KEY), jSONObject.optInt("bg_color"), jSONObject.optInt("icon_color"), jSONObject.optInt("icon_bg_color"), jSONObject.optInt("text_color"), jSONObject.optString(ResponseConstants.ICON), jSONObject.optString(ResponseConstants.IMAGE_URL), (DismissType) fg.m339a(jSONObject, "message_close", DismissType.class, DismissType.AUTO_DISMISS), jSONObject.optInt("duration"), jSONObject.optString("campaign_id"), jSONObject.optString("card_id"), jSONObject, ceVar);
    }

    private InAppMessageBase(String str, Map<String, String> map, ClickAction clickAction, String str2, int i, int i2, int i3, int i4, String str3, String str4, DismissType dismissType, int i5, String str5, String str6, JSONObject jSONObject, ce ceVar) {
        this.f912g = true;
        this.f913h = true;
        this.f914i = ClickAction.NONE;
        this.f916k = DismissType.AUTO_DISMISS;
        this.f917l = INAPP_MESSAGE_DURATION_DEFAULT_MILLIS;
        this.f919n = false;
        this.f920o = false;
        this.f921p = 0;
        this.f922q = 0;
        this.f923r = 0;
        this.f924s = 0;
        this.f928w = false;
        this.f910e = str;
        this.f911f = map;
        this.f912g = true;
        this.f913h = true;
        this.f914i = clickAction;
        if (this.f914i == ClickAction.URI && !fj.m354c(str2)) {
            this.f915j = Uri.parse(str2);
        }
        if (dismissType == DismissType.SWIPE) {
            this.f916k = DismissType.MANUAL;
        } else {
            this.f916k = dismissType;
        }
        setDurationInMilliseconds(i5);
        this.f921p = i;
        this.f923r = i2;
        this.f924s = i3;
        this.f922q = i4;
        this.f925t = str3;
        this.f926u = str4;
        this.f907b = str5;
        this.f908c = str6;
        this.f918m = jSONObject;
        this.f919n = false;
        this.f920o = false;
        this.f909d = ceVar;
    }

    public String getMessage() {
        return this.f910e;
    }

    public Map<String, String> getExtras() {
        return this.f911f;
    }

    public int getDurationInMilliseconds() {
        return this.f917l;
    }

    public int getBackgroundColor() {
        return this.f921p;
    }

    public int getIconColor() {
        return this.f923r;
    }

    public int getIconBackgroundColor() {
        return this.f924s;
    }

    public int getMessageTextColor() {
        return this.f922q;
    }

    public String getIcon() {
        return this.f925t;
    }

    public String getImageUrl() {
        return this.f926u;
    }

    public boolean getAnimateIn() {
        return this.f912g;
    }

    public boolean getAnimateOut() {
        return this.f913h;
    }

    public ClickAction getClickAction() {
        return this.f914i;
    }

    public Uri getUri() {
        return this.f915j;
    }

    public Bitmap getBitmap() {
        return this.f927v;
    }

    public DismissType getDismissType() {
        return this.f916k;
    }

    public String getCampaignId() {
        return this.f907b;
    }

    public String getCardId() {
        return this.f908c;
    }

    public boolean getImageDownloadSuccessful() {
        return this.f928w;
    }

    public void setMessage(String str) {
        this.f910e = str;
    }

    public void setAnimateIn(boolean z) {
        this.f912g = z;
    }

    public void setAnimateOut(boolean z) {
        this.f913h = z;
    }

    public boolean setClickAction(ClickAction clickAction) {
        if (clickAction != ClickAction.URI) {
            this.f914i = clickAction;
            this.f915j = null;
            return true;
        }
        AppboyLogger.m664e(f906a, "A non-null URI is required in order to set the message ClickAction to URI.");
        return false;
    }

    public boolean setClickAction(ClickAction clickAction, Uri uri) {
        if (uri == null && clickAction == ClickAction.URI) {
            AppboyLogger.m664e(f906a, "A non-null URI is required in order to set the message ClickAction to URI.");
            return false;
        } else if (uri == null || clickAction != ClickAction.URI) {
            return setClickAction(clickAction);
        } else {
            this.f914i = clickAction;
            this.f915j = uri;
            return true;
        }
    }

    public void setDismissType(DismissType dismissType) {
        this.f916k = dismissType;
    }

    public void setDurationInMilliseconds(int i) {
        if (i < INAPP_MESSAGE_DURATION_MIN_MILLIS) {
            this.f917l = INAPP_MESSAGE_DURATION_DEFAULT_MILLIS;
            AppboyLogger.m670w(f906a, "Requested in-app message duration " + i + " is lower than the minimum of 999. Defaulting to " + this.f917l + " milliseconds.");
            return;
        }
        this.f917l = i;
        String str = f906a;
        new StringBuilder("Set in-app message duration to ").append(this.f917l).append(" milliseconds.");
    }

    public void setBackgroundColor(int i) {
        this.f921p = i;
    }

    public void setIconColor(int i) {
        this.f923r = i;
    }

    public void setIconBackgroundColor(int i) {
        this.f924s = i;
    }

    public void setMessageTextColor(int i) {
        this.f922q = i;
    }

    public void setIcon(String str) {
        this.f925t = str;
    }

    public void setImageUrl(String str) {
        this.f926u = str;
    }

    public void setBitmap(Bitmap bitmap) {
        this.f927v = bitmap;
    }

    public void setImageDownloadSuccessful(boolean z) {
        this.f928w = z;
    }

    public boolean logImpression() {
        if ((fj.m353b(this.f907b) && fj.m353b(this.f908c)) || this.f919n) {
            return false;
        }
        if (this.f909d == null) {
            AppboyLogger.m664e(f906a, "Cannot log an in-app message impression because the AppboyManager is null.");
            return false;
        }
        try {
            this.f909d.m57a(da.m169c(this.f907b, this.f908c));
            this.f919n = true;
            return true;
        } catch (Throwable e) {
            this.f909d.m56a(e);
            return false;
        }
    }

    public boolean logClick() {
        if (fj.m353b(this.f907b) && fj.m353b(this.f908c)) {
            String str = f906a;
            return false;
        } else if (this.f920o) {
            AppboyLogger.m666i(f906a, "Click already logged for this in-app message. Ignoring.");
            return false;
        } else if (this.f909d == null) {
            AppboyLogger.m664e(f906a, "Cannot log a in-app message click because the AppboyManager is null.");
            return false;
        } else {
            try {
                this.f909d.m57a(da.m171d(this.f907b, this.f908c));
                this.f920o = true;
                return true;
            } catch (Throwable e) {
                this.f909d.m56a(e);
                return false;
            }
        }
    }

    public JSONObject forJsonPut() {
        return this.f918m;
    }
}
