package com.etsy.android.lib.requests;

import com.appboy.Constants;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

@Deprecated
public class EtsyRequestBatch extends EtsyRequest<EmptyResult> {
    private static final RequestMethod REQUEST_TYPE;
    private static final String TAG;
    private static final String URL = "/batch";
    private static final long serialVersionUID = 4709349492582443215L;
    private boolean mCommitted;
    private Map<String, EtsyRequest<?>> mRequests;

    static {
        TAG = EtsyDebug.m1891a(EtsyRequestBatch.class);
        REQUEST_TYPE = RequestMethod.POST;
    }

    public EtsyRequestBatch() {
        super(URL, REQUEST_TYPE, EmptyResult.class);
        this.mRequests = new HashMap();
    }

    public void addRequest(String str, EtsyRequest<?> etsyRequest) {
        if (etsyRequest != null) {
            this.mRequests.put(str, etsyRequest);
        }
    }

    public boolean isCommitted() {
        return this.mCommitted;
    }

    public void commitBatch() {
        if (!this.mCommitted) {
            try {
                JSONArray jSONArray = new JSONArray();
                for (Entry entry : this.mRequests.entrySet()) {
                    EtsyRequest etsyRequest = (EtsyRequest) entry.getValue();
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(ResponseConstants.METHOD, etsyRequest.getRequestMethod());
                    jSONObject.put(Constants.APPBOY_PUSH_DEEP_LINK_KEY, etsyRequest.getUrlWithParams());
                    jSONObject.put(ResponseConstants.NAME, entry.getKey());
                    jSONArray.put(jSONObject);
                }
                Map hashMap = new HashMap();
                hashMap.put("commands", jSONArray.toString());
                addParams(hashMap);
                this.mCommitted = true;
            } catch (Throwable e) {
                EtsyDebug.m1917d(TAG, "getJsonBody json error:", e);
            }
        }
    }

    public Set<String> getResponseNames() {
        return this.mRequests.keySet();
    }

    public HashMap<String, Class<?>> getResponseTypeMap() {
        HashMap<String, Class<?>> hashMap = new HashMap(this.mRequests.size());
        for (Entry entry : this.mRequests.entrySet()) {
            Class responseClass = ((EtsyRequest) entry.getValue()).getResponseClass();
            if (responseClass != null) {
                hashMap.put(entry.getKey(), responseClass);
            }
        }
        return hashMap;
    }
}
