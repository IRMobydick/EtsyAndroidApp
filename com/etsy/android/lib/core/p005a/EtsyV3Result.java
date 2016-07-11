package com.etsy.android.lib.core.p005a;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.ad;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.lib.core.a.a */
public class EtsyV3Result<Result extends BaseModel> extends EtsyResult<Result> {
    private static final String f1401f;
    private String f1402g;
    private boolean f1403h;

    static {
        f1401f = EtsyDebug.m1891a(EtsyV3Result.class);
    }

    public EtsyV3Result(@Nullable VolleyError volleyError) {
        this(volleyError, null);
    }

    public EtsyV3Result(@Nullable VolleyError volleyError, @Nullable Class<Result> cls) {
        super(volleyError, (Class) cls);
        if (this.f1402g == null) {
            this.f1402g = StringUtils.EMPTY;
        }
    }

    public EtsyV3Result(@NonNull NetworkResponse networkResponse, @Nullable Class<Result> cls) {
        super(networkResponse, (Class) cls);
        if (this.f1402g == null) {
            this.f1402g = StringUtils.EMPTY;
        }
    }

    protected boolean m1067j() {
        return this.a == 409;
    }

    protected void m1069o() {
        Map v = m1041v();
        if (v == null) {
            EtsyDebug.m1919e(f1401f, "NULL HEADERS IN v3");
            return;
        }
        if (EtsyDebug.m1903a()) {
            for (Entry entry : v.entrySet()) {
                EtsyDebug.m1912c(f1401f, "Header: " + ((String) entry.getKey()) + ": " + ((String) entry.getValue()));
            }
        }
        if (v.containsKey("X-Total-Count")) {
            this.c = Integer.parseInt((String) v.get("X-Total-Count"));
        }
        if (v.containsKey("X-Filtered-Listings")) {
            this.f1403h = Integer.parseInt((String) v.get("X-Filtered-Listings")) == 1;
        }
        if (v.containsKey("Link")) {
            this.f1402g = new PageLinksParser((String) v.get("Link")).m1071a();
            EtsyDebug.m1912c(f1401f, "NEXT LINK: " + this.f1402g + ", result: " + hashCode());
        }
    }

    protected void m1066a(JsonParser jsonParser, @Nullable Class<Result> cls) {
        EtsyDebug.m1906b(f1401f, "Parsing Api v3 result");
        m1050b(jsonParser, cls);
        this.b = m1056g().size();
    }

    protected void m1068m() {
        super.m1062m();
        if (m1040u() != null) {
            m1065a(m1040u());
        }
    }

    private void m1065a(byte[] bArr) {
        JsonParser jsonParser = null;
        try {
            jsonParser = ad.m1081a().m1082a(bArr);
            jsonParser.nextToken();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String currentName = jsonParser.getCurrentName();
                jsonParser.nextToken();
                if (BaseMessage.TYPE_ERROR.equals(currentName) || ResponseConstants.ERROR_MESSAGE.equals(currentName) || "error_msg".equals(currentName)) {
                    this.e = jsonParser.getValueAsString();
                    continue;
                } else {
                    jsonParser.skipChildren();
                    continue;
                }
                if (currentName == null) {
                    break;
                }
            }
            if (jsonParser != null && !jsonParser.isClosed()) {
                try {
                    jsonParser.close();
                } catch (Throwable e) {
                    EtsyDebug.m1913c(f1401f, "couldn't close JsonParser - ignoring", e);
                }
            }
        } catch (Throwable e2) {
            EtsyDebug.m1917d(f1401f, "parseResponseData NEW error", e2);
            if (jsonParser != null && !jsonParser.isClosed()) {
                try {
                    jsonParser.close();
                } catch (Throwable e22) {
                    EtsyDebug.m1913c(f1401f, "couldn't close JsonParser - ignoring", e22);
                }
            }
        } catch (Throwable th) {
            if (!(jsonParser == null || jsonParser.isClosed())) {
                try {
                    jsonParser.close();
                } catch (Throwable e3) {
                    EtsyDebug.m1913c(f1401f, "couldn't close JsonParser - ignoring", e3);
                }
            }
        }
    }

    public String m1070p() {
        return this.f1402g;
    }
}
