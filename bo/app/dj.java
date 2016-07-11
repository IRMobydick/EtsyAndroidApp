package bo.app;

import com.appboy.Constants;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import org.json.JSONObject;

public class dj implements IPutIntoJson<JSONObject> {
    private static final String f324b;
    public final dl f325a;
    private final Integer f326c;
    private final String f327d;
    private final String f328e;
    private final dm f329f;

    public /* synthetic */ Object forJsonPut() {
        return m195a();
    }

    static {
        f324b = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, dj.class.getName()});
    }

    public static dj m194a(JSONObject jSONObject) {
        dl dlVar = null;
        String str = null;
        dm dmVar = null;
        String str2 = null;
        Integer num = null;
        for (am ordinal : am.values()) {
            JSONObject optJSONObject;
            switch (dk.f330a[ordinal.ordinal()]) {
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    optJSONObject = jSONObject.optJSONObject(am.DEVICE_IDENTIFIERS.f82f);
                    if (optJSONObject == null) {
                        break;
                    }
                    dlVar = dl.m196a(optJSONObject);
                    break;
                case Task.NETWORK_STATE_ANY /*2*/:
                    optJSONObject = jSONObject.optJSONObject(am.DISPLAY.f82f);
                    if (optJSONObject == null) {
                        break;
                    }
                    dmVar = dm.m198b(optJSONObject);
                    break;
                case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                    if (!jSONObject.has(am.ANDROID_VERSION.f82f)) {
                        break;
                    }
                    num = Integer.valueOf(jSONObject.optInt(am.ANDROID_VERSION.f82f));
                    break;
                case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                    str2 = fj.m355d(jSONObject.optString(am.MODEL.f82f));
                    break;
                case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                    str = fj.m355d(jSONObject.optString(am.DEVICE_TYPE.f82f));
                    break;
                default:
                    AppboyLogger.m664e(f324b, String.format("Unknown key encountered in WearDevice createFromJson %s", new Object[]{r7[r0]}));
                    break;
            }
        }
        return new dj(num, str, str2, dmVar, dlVar);
    }

    private dj(Integer num, String str, String str2, dm dmVar, dl dlVar) {
        this.f326c = num;
        this.f327d = str;
        this.f328e = str2;
        this.f329f = dmVar;
        this.f325a = dlVar;
    }

    public final JSONObject m195a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(am.ANDROID_VERSION.f82f, this.f326c);
            jSONObject.putOpt(am.MODEL.f82f, this.f328e);
            jSONObject.putOpt(am.DEVICE_TYPE.f82f, this.f327d);
            if (this.f329f != null) {
                jSONObject.putOpt(am.DISPLAY.f82f, this.f329f.m199a());
            }
            if (this.f325a != null) {
                jSONObject.putOpt(am.DEVICE_IDENTIFIERS.f82f, this.f325a.m197a());
            }
        } catch (Throwable e) {
            AppboyLogger.m665e(f324b, "Caught exception creating wear device Json.", e);
        }
        return jSONObject;
    }
}
