package com.etsy.android.lib.core;

import android.support.annotation.NonNull;
import com.android.volley.NetworkResponse;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import java.util.Map;

@Deprecated
/* renamed from: com.etsy.android.lib.core.y */
public class EtsyResultBatch<Result extends BaseModel> extends EtsyResult<Result> {
    private static final String f1670f;
    private Map<String, EtsyResult<?>> f1671g;
    private Map<String, Class<?>> f1672h;

    static {
        f1670f = EtsyDebug.m1891a(EtsyResultBatch.class);
    }

    public EtsyResultBatch(@NonNull NetworkResponse networkResponse, Map<String, Class<?>> map) {
        super(networkResponse);
        this.f1672h = map;
        this.f1671g = new HashMap();
        m1061l();
    }

    public EtsyResult<?> m1709b(String str) {
        return (EtsyResult) this.f1671g.get(str);
    }

    public int m1710p() {
        return this.f1671g.size();
    }

    protected void m1708a(Class<Result> cls) {
        JsonParser jsonParser = null;
        jsonParser = ad.m1081a().m1082a(m1040u());
        jsonParser.nextToken();
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (ResponseConstants.COUNT.equals(currentName)) {
                this.b = jsonParser.getValueAsInt();
                this.c = this.b;
            } else {
                try {
                    if (ResponseConstants.RESULTS.equals(currentName)) {
                        m1707a(jsonParser);
                    } else {
                        jsonParser.skipChildren();
                    }
                } catch (Throwable e) {
                    EtsyDebug.m1917d(f1670f, "parseResponseData NEW error", e);
                    if (jsonParser != null && !jsonParser.isClosed()) {
                        try {
                            jsonParser.close();
                            return;
                        } catch (Throwable e2) {
                            EtsyDebug.m1913c(f1670f, "couldn't close JsonParser - ignoring", e2);
                            return;
                        }
                    }
                    return;
                } catch (Throwable th) {
                    if (!(jsonParser == null || jsonParser.isClosed())) {
                        try {
                            jsonParser.close();
                        } catch (Throwable e3) {
                            EtsyDebug.m1913c(f1670f, "couldn't close JsonParser - ignoring", e3);
                        }
                    }
                }
            }
        }
        if (jsonParser != null && !jsonParser.isClosed()) {
            try {
                jsonParser.close();
            } catch (Throwable e22) {
                EtsyDebug.m1913c(f1670f, "couldn't close JsonParser - ignoring", e22);
            }
        }
    }

    private void m1707a(JsonParser jsonParser) {
        if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String currentName = jsonParser.getCurrentName();
                jsonParser.nextToken();
                if (this.f1672h.containsKey(currentName) && jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                    EtsyResult etsyResult = new EtsyResult(this, (Class) this.f1672h.get(currentName));
                    etsyResult.m1046a(jsonParser, (Class) this.f1672h.get(currentName));
                    this.f1671g.put(currentName, etsyResult);
                }
            }
        }
    }
}
