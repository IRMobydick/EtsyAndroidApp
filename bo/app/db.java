package bo.app;

import com.appboy.Constants;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.etsy.android.uikit.ui.shop.ShopHomeAdapter;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class db implements cq, IPutIntoJson<JSONObject> {
    private static final String f286a;
    private final Integer f287b;
    private final String f288c;
    private final String f289d;
    private final String f290e;
    private final String f291f;
    private final String f292g;
    private String f293h;
    private final df f294i;
    private final dd f295j;
    private final String f296k;
    private final List<dj> f297l;

    public final /* synthetic */ Object forJsonPut() {
        return m183a();
    }

    static {
        f286a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, db.class.getName()});
    }

    public static db m182a(JSONObject jSONObject) {
        Integer num = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        df dfVar = null;
        dd ddVar = null;
        String str7 = null;
        List list = null;
        for (ad ordinal : ad.values()) {
            JSONObject optJSONObject;
            switch (dc.f298a[ordinal.ordinal()]) {
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                case Task.NETWORK_STATE_ANY /*2*/:
                    break;
                case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                    optJSONObject = jSONObject.optJSONObject(ad.LOCALE.f33m);
                    if (optJSONObject == null) {
                        break;
                    }
                    str5 = optJSONObject.optString(ad.LOCALE_COUNTRY.f33m);
                    str4 = optJSONObject.optString(ad.LOCALE_LANGUAGE.f33m);
                    break;
                case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                    str6 = fj.m355d(jSONObject.optString(ad.TIMEZONE.f33m));
                    break;
                case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                    optJSONObject = jSONObject.optJSONObject(ad.DEVICE_IDENTIFIERS.f33m);
                    if (optJSONObject == null) {
                        break;
                    }
                    ddVar = dd.m185a(optJSONObject);
                    break;
                case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                    optJSONObject = jSONObject.optJSONObject(ad.DISPLAY.f33m);
                    if (optJSONObject == null) {
                        break;
                    }
                    dfVar = df.m188a(optJSONObject);
                    break;
                case CommonStatusCodes.NETWORK_ERROR /*7*/:
                    if (!jSONObject.has(ad.ANDROID_VERSION.f33m)) {
                        break;
                    }
                    num = Integer.valueOf(jSONObject.optInt(ad.ANDROID_VERSION.f33m));
                    break;
                case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                    str = fj.m355d(jSONObject.optString(ad.ABI.f33m));
                    break;
                case CommonStatusCodes.SERVICE_INVALID /*9*/:
                    str2 = fj.m355d(jSONObject.optString(ad.CARRIER.f33m));
                    break;
                case CommonStatusCodes.DEVELOPER_ERROR /*10*/:
                    str3 = fj.m355d(jSONObject.optString(ad.MODEL.f33m));
                    break;
                case CommonStatusCodes.LICENSE_CHECK_FAILED /*11*/:
                    str7 = fj.m355d(jSONObject.optString(ad.PUSH_TOKEN.f33m));
                    break;
                case ShopHomeAdapter.TYPE_BUTTON_BLUE_WITH_DESCRIPTION /*12*/:
                    list = new ArrayList();
                    if (!jSONObject.has(ad.CONNECTED_DEVICES.f33m)) {
                        break;
                    }
                    JSONArray optJSONArray = jSONObject.optJSONArray(ad.CONNECTED_DEVICES.f33m);
                    if (optJSONArray == null) {
                        break;
                    }
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        list.add(dj.m194a(optJSONArray.getJSONObject(i)));
                    }
                    break;
                default:
                    AppboyLogger.m664e(f286a, String.format("Unknown key encountered in Device createFromJson %s", new Object[]{ordinal}));
                    break;
            }
        }
        return new db(num, str, str2, str3, str4, str5, str6, dfVar, ddVar, str7, list);
    }

    public db(Integer num, String str, String str2, String str3, String str4, String str5, String str6, df dfVar, dd ddVar, String str7, List<dj> list) {
        this.f287b = num;
        this.f288c = str;
        this.f289d = str2;
        this.f290e = str3;
        this.f291f = str4;
        this.f292g = str5;
        this.f293h = str6;
        this.f294i = dfVar;
        this.f295j = ddVar;
        this.f296k = str7;
        this.f297l = list;
    }

    public final JSONObject m183a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(ad.ANDROID_VERSION.f33m, this.f287b);
            jSONObject.putOpt(ad.ABI.f33m, this.f288c);
            jSONObject.putOpt(ad.CARRIER.f33m, this.f289d);
            jSONObject.putOpt(ad.MODEL.f33m, this.f290e);
            jSONObject.putOpt(ad.PUSH_TOKEN.f33m, this.f296k);
            if (!(this.f292g == null || this.f291f == null)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.putOpt(ad.LOCALE_LANGUAGE.f33m, this.f291f);
                jSONObject2.putOpt(ad.LOCALE_COUNTRY.f33m, this.f292g);
                jSONObject.put(ad.LOCALE.f33m, jSONObject2);
            }
            if (!fj.m354c(this.f293h)) {
                jSONObject.put(ad.TIMEZONE.f33m, this.f293h);
            }
            if (this.f294i != null) {
                jSONObject.putOpt(ad.DISPLAY.f33m, this.f294i.m189a());
            }
            if (this.f295j != null) {
                jSONObject.putOpt(ad.DEVICE_IDENTIFIERS.f33m, this.f295j.m186a());
            }
            if (!(this.f297l == null || this.f297l.isEmpty())) {
                JSONArray jSONArray = new JSONArray();
                for (dj a : this.f297l) {
                    jSONArray.put(a.m195a());
                }
                jSONObject.put(ad.CONNECTED_DEVICES.f33m, jSONArray);
            }
        } catch (Throwable e) {
            AppboyLogger.m665e(f286a, "Caught exception creating device Json.", e);
        }
        return jSONObject;
    }

    public final boolean m184c() {
        return m183a().length() == 0;
    }
}
