package bo.app;

import com.appboy.Constants;
import com.appboy.models.InAppMessageBase;
import com.appboy.models.MessageButton;
import com.appboy.models.outgoing.AppboyProperties;
import com.appboy.support.AppboyLogger;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.SearchCategoryRedirectPage;
import com.foresee.mobileReplay.perfLog.PerfDbContentProvider;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import org.json.JSONArray;
import org.json.JSONObject;

public final class da implements cp {
    private static final String f282a;
    private final ae f283b;
    private final JSONObject f284c;
    private final double f285d;

    public final /* synthetic */ Object forJsonPut() {
        return m177g();
    }

    static {
        f282a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, da.class.getName()});
    }

    private da(ae aeVar, JSONObject jSONObject) {
        this(aeVar, jSONObject, fd.m334b());
    }

    private da(ae aeVar, JSONObject jSONObject, double d) {
        if (aeVar.f53r == null || jSONObject == null) {
            throw new NullPointerException();
        }
        this.f283b = aeVar;
        this.f284c = jSONObject;
        this.f285d = d;
    }

    public static da m153a(String str, AppboyProperties appboyProperties) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(PerfDbContentProvider.COL_N, fj.m349a(str));
        if (appboyProperties != null && appboyProperties.size() > 0) {
            jSONObject.put(Constants.APPBOY_PUSH_PRIORITY_KEY, appboyProperties.forJsonPut());
        }
        return new da(ae.CUSTOM_EVENT, jSONObject);
    }

    public static da m158a(String str, String str2, BigDecimal bigDecimal, int i, AppboyProperties appboyProperties) {
        BigDecimal a = fb.m328a(bigDecimal);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", str);
        jSONObject.put("c", str2);
        jSONObject.put(Constants.APPBOY_PUSH_PRIORITY_KEY, a.doubleValue());
        jSONObject.put(SearchCategoryRedirectPage.PARAM_QUERY, i);
        if (appboyProperties != null && appboyProperties.size() > 0) {
            jSONObject.put("pr", appboyProperties.forJsonPut());
        }
        return new da(ae.PURCHASE, jSONObject);
    }

    public static da m150a(dg dgVar) {
        return new da(ae.LOCATION_RECORDED, dgVar.m190a());
    }

    public static da m160a(Throwable th, cy cyVar) {
        String b = m166b(th, cyVar);
        StringBuilder append = new StringBuilder(b).append("\n").append(m162a(th));
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("e", append.toString());
        return new da(ae.INTERNAL_ERROR, jSONObject);
    }

    public static da m149a(bm bmVar, cy cyVar) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("nop", true);
        String b = m166b((Throwable) bmVar, cyVar);
        jSONObject.put("e", "\n" + m162a((Throwable) bmVar));
        return new da(ae.INTERNAL_ERROR, jSONObject);
    }

    public static da m151a(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("new", str);
        return new da(ae.USER_TRANSITION, jSONObject);
    }

    public static da m154a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("old", str);
        jSONObject.put("new", str2);
        return new da(ae.USER_TRANSITION, jSONObject);
    }

    public static da m164b(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Constants.APPBOY_PUSH_CAMPAIGN_ID_KEY, str);
        return new da(ae.PUSH_NOTIFICATION_TRACKING, jSONObject);
    }

    public static da m165b(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Constants.APPBOY_PUSH_CAMPAIGN_ID_KEY, str);
        jSONObject.put(Constants.APPBOY_PUSH_CONTENT_KEY, str2);
        return new da(ae.PUSH_NOTIFICATION_ACTION_TRACKING, jSONObject);
    }

    public static da m168c(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(str);
        jSONObject.put("ids", jSONArray);
        return new da(ae.CARD_IMPRESSION, jSONObject);
    }

    public static da m170d(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(str);
        jSONObject.put("ids", jSONArray);
        return new da(ae.CARD_CLICK, jSONObject);
    }

    public static da m169c(String str, String str2) {
        return new da(ae.INAPP_MESSAGE_IMPRESSION, m167b(str, str2, null));
    }

    public static da m171d(String str, String str2) {
        return new da(ae.INAPP_MESSAGE_CLICK, m167b(str, str2, null));
    }

    public static da m156a(String str, String str2, MessageButton messageButton) {
        return new da(ae.INAPP_MESSAGE_BUTTON_CLICK, m167b(str, str2, messageButton != null ? String.valueOf(messageButton.getId()) : null));
    }

    public static da m157a(String str, String str2, String str3) {
        return new da(ae.INAPP_MESSAGE_BUTTON_CLICK, m167b(str, str2, str3));
    }

    private static JSONObject m167b(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        if (!fj.m353b(str)) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(str);
            jSONObject.put("campaign_ids", jSONArray);
        }
        if (!fj.m353b(str2)) {
            jSONArray = new JSONArray();
            jSONArray.put(str2);
            jSONObject.put("card_ids", jSONArray);
        }
        if (!fj.m353b(str3)) {
            jSONObject.put("bid", str3);
        }
        return jSONObject;
    }

    public static da m172e() {
        return m173e("feed_displayed");
    }

    public static da m175f() {
        return m173e("feedback_displayed");
    }

    private static da m173e(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(PerfDbContentProvider.COL_N, str);
        return new da(ae.INTERNAL, jSONObject);
    }

    public static da m152a(String str, int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ResponseConstants.KEY, str);
        jSONObject.put(ResponseConstants.VALUE, i);
        return new da(ae.INCREMENT, jSONObject);
    }

    public static da m174e(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ResponseConstants.KEY, str);
        jSONObject.put(ResponseConstants.VALUE, str2);
        return new da(ae.ADD_TO_CUSTOM_ATTRIBUTE_ARRAY, jSONObject);
    }

    public static da m176f(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ResponseConstants.KEY, str);
        jSONObject.put(ResponseConstants.VALUE, str2);
        return new da(ae.REMOVE_FROM_CUSTOM_ATTRIBUTE_ARRAY, jSONObject);
    }

    public static da m159a(String str, String[] strArr) {
        JSONArray jSONArray = strArr == null ? null : new JSONArray();
        if (strArr != null) {
            for (Object put : strArr) {
                jSONArray.put(put);
            }
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ResponseConstants.KEY, str);
        if (strArr == null) {
            jSONObject.put(ResponseConstants.VALUE, JSONObject.NULL);
        } else {
            jSONObject.put(ResponseConstants.VALUE, jSONArray);
        }
        return new da(ae.SET_CUSTOM_ATTRIBUTE_ARRAY, jSONObject);
    }

    public static da m155a(String str, String str2, double d) {
        ae a = ae.m17a(str);
        if (a != null) {
            return new da(a, new JSONObject(str2), d);
        }
        throw new IllegalArgumentException(String.format("Cannot parse eventType %s", new Object[]{str}));
    }

    public static da m161a(JSONObject jSONObject) {
        String string = jSONObject.getString(PerfDbContentProvider.COL_N);
        ae a = ae.m17a(jSONObject.getString(PerfDbContentProvider.COL_N));
        if (a != null) {
            return new da(a, jSONObject.getJSONObject(Constants.APPBOY_PUSH_NOTIFICATION_SOUND_DEFAULT_VALUE), jSONObject.getDouble(Constants.APPBOY_PUSH_TITLE_KEY));
        }
        throw new IllegalArgumentException(String.format("Cannot parse eventType %s", new Object[]{string}));
    }

    public final ae m179b() {
        return this.f283b;
    }

    public final JSONObject m180c() {
        return this.f284c;
    }

    public final double m178a() {
        return this.f285d;
    }

    public final String m181d() {
        return m177g().toString();
    }

    private static String m166b(Throwable th, cy cyVar) {
        StringBuilder stringBuilder = new StringBuilder();
        String th2 = th.toString();
        if (th2.length() > InAppMessageBase.INAPP_MESSAGE_DURATION_DEFAULT_MILLIS) {
            th2 = th2.substring(0, InAppMessageBase.INAPP_MESSAGE_DURATION_DEFAULT_MILLIS);
        }
        stringBuilder.append("exception_class: ").append(th2).append(",");
        stringBuilder.append("session_id: ").append(cyVar != null ? cyVar.toString() : null);
        return stringBuilder.toString();
    }

    private static String m162a(Throwable th) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String obj = stringWriter.toString();
        if (obj.length() > InAppMessageBase.INAPP_MESSAGE_DURATION_DEFAULT_MILLIS) {
            return obj.substring(0, InAppMessageBase.INAPP_MESSAGE_DURATION_DEFAULT_MILLIS);
        }
        return obj;
    }

    public static boolean m163a(cp cpVar) {
        return cpVar.m126b() == ae.INTERNAL_ERROR && cpVar.m127c().optBoolean("nop", false);
    }

    private JSONObject m177g() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(PerfDbContentProvider.COL_N, this.f283b.f53r);
            jSONObject.put(Constants.APPBOY_PUSH_NOTIFICATION_SOUND_DEFAULT_VALUE, this.f284c);
            jSONObject.put(Constants.APPBOY_PUSH_TITLE_KEY, this.f285d);
        } catch (Throwable e) {
            AppboyLogger.m665e(f282a, "Caught exception creating Appboy event Json.", e);
        }
        return jSONObject;
    }
}
