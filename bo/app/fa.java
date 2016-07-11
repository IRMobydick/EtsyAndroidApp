package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.appboy.Constants;
import com.appboy.enums.Gender;
import com.appboy.enums.Month;
import com.appboy.enums.NotificationSubscriptionType;
import com.appboy.models.outgoing.AttributionData;
import com.appboy.models.outgoing.FacebookUser;
import com.appboy.models.outgoing.TwitterUser;
import com.appboy.support.AppboyLogger;
import com.appboy.support.ValidationUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShopAbout.Link;
import com.etsy.android.lib.models.apiv3.StructuredShopShippingEstimate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class fa extends ek<di> {
    private static final String f436a;
    private static final Set<String> f437b;
    private final SharedPreferences f438c;
    private final ey f439d;

    public final /* synthetic */ Object m307a() {
        return m306c();
    }

    final /* synthetic */ void m313a(Object obj) {
        di diVar = (di) obj;
        if (diVar != null) {
            Editor edit = this.f438c.edit();
            JSONObject jSONObject = diVar.f323a;
            Map all = this.f438c.getAll();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (all.containsKey(str)) {
                    Object obj2 = all.get(str);
                    Object opt = jSONObject.opt(str);
                    if (opt == null) {
                        if (obj2 == null) {
                            edit.remove(str);
                        }
                    } else if (opt instanceof JSONObject) {
                        try {
                            if (iv.m573a(String.valueOf(obj2), opt.toString(), iw.NON_EXTENSIBLE).f783a) {
                                edit.remove(str);
                            }
                        } catch (Throwable e) {
                            AppboyLogger.m665e(f436a, "Caught exception confirming and unlocking Json objects.", e);
                        }
                    } else if (opt.equals(obj2)) {
                        edit.remove(str);
                    } else if (opt.equals(JSONObject.NULL)) {
                        edit.remove(str);
                    }
                }
            }
            edit.apply();
        }
    }

    static {
        f436a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, fa.class.getName()});
        f437b = new HashSet(Arrays.asList(new String[]{Link.TWITTER_TITLE, Link.FACEBOOK_TITLE, "ab_install_attribution"}));
    }

    public fa(Context context, ey eyVar) {
        this(context, null, null, eyVar);
    }

    public fa(Context context, String str, String str2, ey eyVar) {
        this.f438c = context.getSharedPreferences("com.appboy.storage.usercache" + fj.m352b(str, str2), 0);
        this.f439d = eyVar;
    }

    public final synchronized void m314a(String str) {
        Editor edit = this.f438c.edit();
        edit.putString(ResponseConstants.FIRST_NAME, str);
        edit.apply();
    }

    public final synchronized void m319b(String str) {
        Editor edit = this.f438c.edit();
        edit.putString(ResponseConstants.LAST_NAME, str);
        edit.apply();
    }

    public final synchronized boolean m320c(String str) {
        boolean z = false;
        synchronized (this) {
            if (str != null) {
                str = str.trim();
            }
            if (str == null || ValidationUtils.isValidEmailAddress(str)) {
                Editor edit = this.f438c.edit();
                edit.putString(ResponseConstants.EMAIL, str);
                edit.apply();
                z = true;
            } else {
                AppboyLogger.m670w(f436a, String.format("Email address is not valid: %s", new Object[]{str}));
            }
        }
        return z;
    }

    public final synchronized void m308a(Gender gender) {
        Editor edit = this.f438c.edit();
        if (gender == null) {
            edit.putString("gender", null);
        } else {
            edit.putString("gender", gender.forJsonPut());
        }
        edit.apply();
    }

    public final synchronized boolean m315a(int i, Month month, int i2) {
        boolean z;
        if (month == null) {
            AppboyLogger.m670w(f436a, "Month cannot be null.");
            z = false;
        } else {
            String a = fd.m331a(fd.m332a(i, month.getValue(), i2), ab.SHORT);
            Editor edit = this.f438c.edit();
            edit.putString("dob", a);
            edit.apply();
            z = true;
        }
        return z;
    }

    public final synchronized void m321d(String str) {
        Editor edit = this.f438c.edit();
        edit.putString(StructuredShopShippingEstimate.TYPE_COUNTRY, str);
        edit.apply();
    }

    public final synchronized void m322e(String str) {
        Editor edit = this.f438c.edit();
        edit.putString("home_city", str);
        edit.apply();
    }

    public final synchronized void m309a(NotificationSubscriptionType notificationSubscriptionType) {
        Editor edit = this.f438c.edit();
        if (notificationSubscriptionType == null) {
            edit.putString("email_subscribe", null);
        } else {
            edit.putString("email_subscribe", notificationSubscriptionType.forJsonPut());
        }
        edit.apply();
    }

    public final synchronized void m318b(NotificationSubscriptionType notificationSubscriptionType) {
        Editor edit = this.f438c.edit();
        if (notificationSubscriptionType == null) {
            edit.putString("push_subscribe", null);
        } else {
            edit.putString("push_subscribe", notificationSubscriptionType.forJsonPut());
        }
        edit.apply();
    }

    public final synchronized boolean m323f(String str) {
        boolean z = false;
        synchronized (this) {
            if (str != null) {
                str = str.trim();
            }
            if (str == null || ValidationUtils.isValidPhoneNumber(str)) {
                Editor edit = this.f438c.edit();
                edit.putString(ResponseConstants.PHONE, str);
                edit.apply();
                z = true;
            } else {
                AppboyLogger.m670w(f436a, String.format("Phone number contains invalid characters (allowed are digits, spaces, or any of the following +.-()): %s", new Object[]{str}));
            }
        }
        return z;
    }

    public final synchronized void m312a(TwitterUser twitterUser) {
        m305a(twitterUser.forJsonPut(), Link.TWITTER_TITLE);
    }

    public final synchronized void m311a(FacebookUser facebookUser) {
        m305a(facebookUser.forJsonPut(), Link.FACEBOOK_TITLE);
    }

    public final synchronized void m310a(AttributionData attributionData) {
        m305a(attributionData.forJsonPut(), "ab_install_attribution");
    }

    public final synchronized void m324g(String str) {
        Editor edit = this.f438c.edit();
        edit.putString(ResponseConstants.IMAGE_URL, str);
        edit.apply();
    }

    public final synchronized void m325h(String str) {
        Editor edit = this.f438c.edit();
        if (fj.m354c(str)) {
            edit.remove("piqid");
        } else {
            edit.putString("piqid", str);
        }
        edit.apply();
    }

    private void m305a(JSONObject jSONObject, String str) {
        JSONObject jSONObject2 = null;
        if (this.f438c.contains(str)) {
            try {
                jSONObject2 = new JSONObject(this.f438c.getString(str, StringUtils.EMPTY));
            } catch (Throwable e) {
                AppboyLogger.m671w(f436a, String.format("Failed to parse existing value for [%s], continuing without it.", new Object[]{str}), e);
            }
        }
        Editor edit = this.f438c.edit();
        if (jSONObject2 == null || jSONObject2.length() <= 0) {
            edit.putString(str, jSONObject.toString());
        } else {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                try {
                    jSONObject2.put(str2, jSONObject.get(str2));
                } catch (JSONException e2) {
                    AppboyLogger.m670w(f436a, String.format("Failed to fetch value for key [%s], ignoring.", new Object[]{str2}));
                }
            }
            edit.putString(str, jSONObject2.toString());
        }
        edit.apply();
    }

    public final synchronized boolean m317a(String str, Object obj) {
        boolean z = false;
        synchronized (this) {
            if (!ValidationUtils.isBlacklistedCustomAttributeKey(str, this.f439d.m302h())) {
                if (ValidationUtils.isValidCustomAttributeKey(str)) {
                    String ensureAppboyFieldLength = ValidationUtils.ensureAppboyFieldLength(str);
                    if (obj == null) {
                        AppboyLogger.m670w(f436a, "Custom attribute value cannot be null.");
                    } else {
                        Editor edit = this.f438c.edit();
                        if (obj instanceof Boolean) {
                            edit.putBoolean(ensureAppboyFieldLength, ((Boolean) obj).booleanValue());
                        } else if (obj instanceof Integer) {
                            edit.putInt(ensureAppboyFieldLength, ((Integer) obj).intValue());
                        } else if (obj instanceof Float) {
                            edit.putFloat(ensureAppboyFieldLength, ((Float) obj).floatValue());
                        } else if (obj instanceof Long) {
                            edit.putLong(ensureAppboyFieldLength, ((Long) obj).longValue());
                        } else if (obj instanceof String) {
                            edit.putString(ensureAppboyFieldLength, ValidationUtils.ensureAppboyFieldLength((String) obj));
                        } else if (obj instanceof Date) {
                            edit.putString(ensureAppboyFieldLength, fd.m331a((Date) obj, ab.LONG));
                        } else {
                            AppboyLogger.m670w(f436a, "Unsupported custom attribute type");
                        }
                        edit.apply();
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    public final synchronized boolean m316a(String str, long j) {
        return m317a(str, fd.m333a(j));
    }

    public final synchronized boolean m326i(String str) {
        boolean z = false;
        synchronized (this) {
            if (str == null) {
                AppboyLogger.m670w(f436a, "Custom attribute key cannot be null.");
            } else if (ValidationUtils.isValidCustomAttributeKey(str)) {
                Editor edit = this.f438c.edit();
                edit.putString(str, "appboy_null_5a8579f5-079b-4681-a046-0f3c46a4ef58");
                edit.apply();
                z = true;
            }
        }
        return z;
    }

    private di m306c() {
        JSONObject jSONObject = new JSONObject(this.f438c.getAll());
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            try {
                if (jSONObject.get(str).equals("appboy_null_5a8579f5-079b-4681-a046-0f3c46a4ef58")) {
                    jSONObject.put(str, JSONObject.NULL);
                }
                if (str.equals("piqid") && cb.m99a()) {
                    jSONObject.put("piqid_changed", true);
                }
            } catch (JSONException e) {
                AppboyLogger.m670w(f436a, String.format("Failed to check outbound json key %s for null placeholders.", new Object[]{str}));
            }
        }
        for (String str2 : f437b) {
            try {
                if (this.f438c.contains(str2)) {
                    jSONObject.put(str2, new JSONObject(this.f438c.getString(str2, StringUtils.EMPTY)));
                }
            } catch (JSONException e2) {
                AppboyLogger.m670w(f436a, String.format("Failed to properly convert [%s] value to OutboundUser for export.", new Object[]{str2}));
            }
        }
        return new di(jSONObject);
    }
}
