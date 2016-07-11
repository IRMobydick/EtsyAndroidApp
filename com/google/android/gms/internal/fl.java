package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import java.io.BufferedOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@jw
public class fl implements fk {
    private final Context f4915a;
    private final VersionInfoParcel f4916b;

    public fl(Context context, VersionInfoParcel versionInfoParcel) {
        this.f4915a = context;
        this.f4916b = versionInfoParcel;
    }

    protected fn m6521a(JSONObject jSONObject) {
        URL url;
        String optString = jSONObject.optString("http_request_id");
        String optString2 = jSONObject.optString(ResponseConstants.URL);
        String optString3 = jSONObject.optString("post_body", null);
        try {
            url = new URL(optString2);
        } catch (Throwable e) {
            C1129c.m6189b("Error constructing http request.", e);
            url = null;
        }
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("headers");
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(new fm(optJSONObject.optString(ResponseConstants.KEY), optJSONObject.optString(ResponseConstants.VALUE)));
            }
        }
        return new fn(optString, url, arrayList, optString3);
    }

    protected fo m6522a(fn fnVar) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) fnVar.m6529b().openConnection();
            C1101o.m6041e().m7106a(this.f4915a, this.f4916b.afmaVersion, false, httpURLConnection);
            Iterator it = fnVar.m6530c().iterator();
            while (it.hasNext()) {
                fm fmVar = (fm) it.next();
                httpURLConnection.addRequestProperty(fmVar.m6526a(), fmVar.m6527b());
            }
            if (!TextUtils.isEmpty(fnVar.m6531d())) {
                httpURLConnection.setDoOutput(true);
                byte[] bytes = fnVar.m6531d().getBytes();
                httpURLConnection.setFixedLengthStreamingMode(bytes.length);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.close();
            }
            List arrayList = new ArrayList();
            if (httpURLConnection.getHeaderFields() != null) {
                for (Entry entry : httpURLConnection.getHeaderFields().entrySet()) {
                    for (String fmVar2 : (List) entry.getValue()) {
                        arrayList.add(new fm((String) entry.getKey(), fmVar2));
                    }
                }
            }
            return new fo(this, true, new fp(fnVar.m6528a(), httpURLConnection.getResponseCode(), arrayList, C1101o.m6041e().m7094a(new InputStreamReader(httpURLConnection.getInputStream()))), null);
        } catch (Exception e) {
            return new fo(this, false, null, e.toString());
        }
    }

    protected JSONObject m6523a(fp fpVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("http_request_id", fpVar.m6535a());
            if (fpVar.m6538d() != null) {
                jSONObject.put("body", fpVar.m6538d());
            }
            JSONArray jSONArray = new JSONArray();
            for (fm fmVar : fpVar.m6537c()) {
                jSONArray.put(new JSONObject().put(ResponseConstants.KEY, fmVar.m6526a()).put(ResponseConstants.VALUE, fmVar.m6527b()));
            }
            jSONObject.put("headers", jSONArray);
            jSONObject.put("response_code", fpVar.m6536b());
        } catch (Throwable e) {
            C1129c.m6189b("Error constructing JSON for http response.", e);
        }
        return jSONObject;
    }

    public JSONObject m6524a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject();
            Object obj = StringUtils.EMPTY;
            try {
                obj = jSONObject.optString("http_request_id");
                fo a = m6522a(m6521a(jSONObject));
                if (a.m6534c()) {
                    jSONObject2.put(ResponseConstants.RESPONSE, m6523a(a.m6533b()));
                    jSONObject2.put(BaseMessage.TYPE_SUCCESS, true);
                    return jSONObject2;
                }
                jSONObject2.put(ResponseConstants.RESPONSE, new JSONObject().put("http_request_id", obj));
                jSONObject2.put(BaseMessage.TYPE_SUCCESS, false);
                jSONObject2.put(ResponseConstants.REASON, a.m6532a());
                return jSONObject2;
            } catch (Exception e) {
                try {
                    jSONObject2.put(ResponseConstants.RESPONSE, new JSONObject().put("http_request_id", obj));
                    jSONObject2.put(BaseMessage.TYPE_SUCCESS, false);
                    jSONObject2.put(ResponseConstants.REASON, e.toString());
                    return jSONObject2;
                } catch (JSONException e2) {
                    return jSONObject2;
                }
            }
        } catch (JSONException e3) {
            C1129c.m6188b("The request is not a valid JSON.");
            try {
                return new JSONObject().put(BaseMessage.TYPE_SUCCESS, false);
            } catch (JSONException e4) {
                return new JSONObject();
            }
        }
    }

    public void m6525a(no noVar, Map<String, String> map) {
        ls.m7070a(new 1(this, map, noVar));
    }
}
