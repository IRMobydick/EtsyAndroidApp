package com.appboy.models.outgoing;

import bo.app.ab;
import bo.app.fd;
import bo.app.fj;
import com.appboy.Constants;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.appboy.support.ValidationUtils;
import java.util.Date;
import java.util.Iterator;
import org.json.JSONObject;

public final class AppboyProperties implements IPutIntoJson<JSONObject> {
    private static final String f994a;
    private JSONObject f995b;

    static {
        f994a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyProperties.class.getName()});
    }

    public AppboyProperties() {
        this.f995b = new JSONObject();
    }

    public AppboyProperties(JSONObject jSONObject) {
        this.f995b = new JSONObject();
        this.f995b = jSONObject;
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            if (m655a(str)) {
                try {
                    if (jSONObject.get(str) instanceof String) {
                        if (!m656b(jSONObject.getString(str))) {
                            this.f995b.remove(str);
                        }
                    } else if (jSONObject.get(str) == JSONObject.NULL) {
                        this.f995b.remove(str);
                    }
                } catch (Throwable e) {
                    AppboyLogger.m665e(f994a, "Caught json exception validating property with key name: " + str, e);
                }
            } else {
                this.f995b.remove(str);
            }
        }
    }

    public final AppboyProperties addProperty(String str, int i) {
        if (m655a(str)) {
            try {
                this.f995b.put(ValidationUtils.ensureAppboyFieldLength(str), i);
            } catch (Throwable e) {
                AppboyLogger.m665e(f994a, "Caught json exception trying to add property.", e);
            }
        }
        return this;
    }

    public final AppboyProperties addProperty(String str, double d) {
        if (m655a(str)) {
            try {
                this.f995b.put(ValidationUtils.ensureAppboyFieldLength(str), d);
            } catch (Throwable e) {
                AppboyLogger.m665e(f994a, "Caught json exception trying to add property.", e);
            }
        }
        return this;
    }

    public final AppboyProperties addProperty(String str, boolean z) {
        if (m655a(str)) {
            try {
                this.f995b.put(ValidationUtils.ensureAppboyFieldLength(str), z);
            } catch (Throwable e) {
                AppboyLogger.m665e(f994a, "Caught json exception trying to add property.", e);
            }
        }
        return this;
    }

    public final AppboyProperties addProperty(String str, Date date) {
        if (m655a(str) && date != null) {
            try {
                this.f995b.put(ValidationUtils.ensureAppboyFieldLength(str), fd.m331a(date, ab.LONG));
            } catch (Throwable e) {
                AppboyLogger.m665e(f994a, "Caught json exception trying to add property.", e);
            }
        }
        return this;
    }

    public final AppboyProperties addProperty(String str, String str2) {
        if (m655a(str) && m656b(str2)) {
            try {
                this.f995b.put(ValidationUtils.ensureAppboyFieldLength(str), ValidationUtils.ensureAppboyFieldLength(str2));
            } catch (Throwable e) {
                AppboyLogger.m665e(f994a, "Caught json exception trying to add property.", e);
            }
        }
        return this;
    }

    public final int size() {
        return this.f995b.length();
    }

    private static boolean m655a(String str) {
        if (fj.m354c(str)) {
            AppboyLogger.m670w(f994a, "The Appboy property key cannot be null or contain only whitespaces. Not adding property.");
            return false;
        } else if (!str.startsWith("$")) {
            return true;
        } else {
            AppboyLogger.m670w(f994a, "The leading character in the key string may not be '$'. Not adding property.");
            return false;
        }
    }

    private static boolean m656b(String str) {
        if (!fj.m354c(str)) {
            return true;
        }
        AppboyLogger.m670w(f994a, "The Appboy property value cannot be null or contain only whitespaces. Not adding property.");
        return false;
    }

    public final JSONObject forJsonPut() {
        return this.f995b;
    }
}
