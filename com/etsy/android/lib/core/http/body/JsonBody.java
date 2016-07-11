package com.etsy.android.lib.core.http.body;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.requests.HttpUtil;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONStringer;

/* renamed from: com.etsy.android.lib.core.http.body.c */
public class JsonBody extends ParamBody<JsonBody, JsonBody> {
    public /* synthetic */ ParamBody m1358b(String str, String str2) {
        return m1354a(str, str2);
    }

    public /* synthetic */ ParamBody m1359b(String str, List list) {
        return m1355a(str, list);
    }

    protected /* synthetic */ ParamBody m1361d() {
        return m1360c();
    }

    protected /* synthetic */ ParamBody m1362e() {
        return m1357b();
    }

    public JsonBody m1354a(@Nullable String str, @Nullable String str2) {
        HttpUtil.addQueryParamAsList(this.a, str, str2, true);
        return m1357b();
    }

    public JsonBody m1355a(@Nullable String str, @NonNull List<String> list) {
        HttpUtil.addQueryParamAsList(this.a, str, (List) list, true);
        return m1357b();
    }

    @NonNull
    protected byte[] m1356a() {
        try {
            JSONStringer jSONStringer = new JSONStringer();
            jSONStringer.object();
            for (Entry entry : this.a.entrySet()) {
                jSONStringer.key((String) entry.getKey());
                if (((List) entry.getValue()).size() > 1) {
                    jSONStringer.value(new JSONArray((Collection) entry.getValue()));
                } else if (((List) entry.getValue()).size() == 1) {
                    jSONStringer.value(((List) entry.getValue()).get(0));
                } else {
                    jSONStringer.value(null);
                }
            }
            jSONStringer.endObject();
            return jSONStringer.toString().getBytes(Charset.forName(BaseHttpBody.getDefaultCharSet()));
        } catch (JSONException e) {
            EtsyDebug.m1919e(JsonBody.f1544a, "buildBody: " + e);
            return new byte[0];
        }
    }

    protected JsonBody m1357b() {
        return this;
    }

    protected JsonBody m1360c() {
        return new JsonBody();
    }
}
