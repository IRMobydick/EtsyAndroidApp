package com.appboy.models.outgoing;

import bo.app.db;
import bo.app.fj;
import com.appboy.Constants;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.etsy.android.lib.models.ResponseConstants;
import org.json.JSONObject;

public final class Feedback implements IPutIntoJson<JSONObject> {
    private static final String f1020a;
    private final String f1021b;
    private final String f1022c;
    private final boolean f1023d;
    private final Environment f1024e;
    private final db f1025f;

    static {
        f1020a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, Feedback.class.getName()});
    }

    public Feedback(String str, String str2, boolean z, Environment environment, db dbVar) {
        if (fj.m354c(str)) {
            throw new IllegalArgumentException("Message cannot be null or blank");
        }
        this.f1021b = str;
        this.f1022c = str2;
        this.f1023d = z;
        this.f1024e = environment;
        this.f1025f = dbVar;
    }

    public final String getMessage() {
        return this.f1021b;
    }

    public final String getReplyToEmail() {
        return this.f1022c;
    }

    public final boolean isReportingABug() {
        return this.f1023d;
    }

    public final JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE, fj.m349a(this.f1021b));
            jSONObject.put("reply_to", this.f1022c);
            jSONObject.put("is_bug", this.f1023d);
            if (this.f1025f != null) {
                jSONObject.put("device", this.f1025f.m183a());
            }
            if (this.f1024e != null) {
                jSONObject.put("environment", this.f1024e.forStatelessJsonPut());
            }
        } catch (Throwable e) {
            AppboyLogger.m665e(f1020a, "Caught exception creating feedback Json.", e);
        }
        return jSONObject;
    }
}
