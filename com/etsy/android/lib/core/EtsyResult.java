package com.etsy.android.lib.core;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.adjust.sdk.Constants;
import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.http.HttpResult;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.EtsyError;
import com.etsy.android.lib.models.ModelFactory;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.foresee.mobileReplay.http.FsServiceClientImpl;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.etsy.android.lib.core.x */
public class EtsyResult<Result extends BaseModel> extends HttpResult {
    private static final String f1389f;
    protected int f1390a;
    protected int f1391b;
    protected int f1392c;
    protected String f1393d;
    protected String f1394e;
    private boolean f1395g;
    @NonNull
    private final List<Result> f1396h;
    @NonNull
    private final List<EtsyError> f1397i;
    @Nullable
    private Class<Result> f1398j;
    private long f1399k;
    @Deprecated
    private boolean f1400l;

    static {
        f1389f = EtsyDebug.m1891a(EtsyResult.class);
    }

    public EtsyResult(@Nullable VolleyError volleyError, @Nullable Class<Result> cls) {
        super(volleyError);
        this.f1396h = new ArrayList(0);
        this.f1397i = new ArrayList(0);
        m1044b((Class) cls);
        if (volleyError == null || !m1059j() || volleyError.networkResponse == null) {
            m1062m();
        } else {
            m1061l();
        }
    }

    public EtsyResult(@NonNull NetworkResponse networkResponse, @Nullable Class<Result> cls) {
        super(networkResponse);
        this.f1396h = new ArrayList(0);
        this.f1397i = new ArrayList(0);
        m1044b((Class) cls);
        m1061l();
    }

    @Deprecated
    protected EtsyResult(@NonNull NetworkResponse networkResponse) {
        super(networkResponse);
        this.f1396h = new ArrayList(0);
        this.f1397i = new ArrayList(0);
        m1044b(null);
    }

    @Deprecated
    protected EtsyResult(@NonNull EtsyResultBatch etsyResultBatch, @NonNull Class<Result> cls) {
        super(etsyResultBatch.m1038s());
        this.f1396h = new ArrayList(0);
        this.f1397i = new ArrayList(0);
        m1044b((Class) cls);
    }

    @CallSuper
    private void m1044b(@Nullable Class<Result> cls) {
        this.f1398j = cls;
        this.f1390a = m1036q();
        this.f1391b = -1;
        this.f1392c = -1;
        try {
            this.f1394e = EtsyApplication.get().getString(R.no_internet);
        } catch (IllegalStateException e) {
            this.f1394e = "Connection Failed";
        }
    }

    public boolean m1049a() {
        return this.f1395g;
    }

    public boolean m1051b() {
        return this.f1395g;
    }

    @Nullable
    public String m1052c() {
        return this.f1394e;
    }

    public List<EtsyError> m1053d() {
        return this.f1397i;
    }

    public int m1054e() {
        return this.f1391b;
    }

    public int m1055f() {
        return this.f1392c;
    }

    public List<Result> m1056g() {
        return this.f1396h;
    }

    public Result m1057h() {
        if (this.f1396h.size() < 1) {
            return null;
        }
        return (BaseModel) this.f1396h.get(0);
    }

    public boolean m1058i() {
        return this.f1396h.size() > 0;
    }

    protected boolean m1059j() {
        return false;
    }

    @Deprecated
    public boolean m1060k() {
        return this.f1400l;
    }

    protected void m1061l() {
        Throwable e;
        if (super.m1034b() || m1059j()) {
            this.f1393d = m1035c("Content-Type");
            if (FsServiceClientImpl.CONTENT_TYPE.equalsIgnoreCase(this.f1393d)) {
                try {
                    if (EtsyDebug.m1909b() && m1040u() != null) {
                        EtsyDebug.m1904b(new String(m1040u(), Charset.forName(Constants.ENCODING)));
                    }
                    this.f1395g = super.m1034b();
                    EtsyDebug.m1912c(f1389f, "before parsing: mWasSuccess = true. code: " + super.m1036q());
                    m1064o();
                    m1047a(this.f1398j);
                    return;
                } catch (NullPointerException e2) {
                    e = e2;
                    EtsyDebug.m1917d(f1389f, "Error", e);
                    return;
                } catch (OutOfMemoryError e3) {
                    e = e3;
                    EtsyDebug.m1917d(f1389f, "Error", e);
                    return;
                } catch (Throwable e4) {
                    EtsyDebug.m1897a(f1389f, "Error parsing total count header", e4);
                    return;
                }
            } else if (this.f1390a == 204 || this.f1390a == 205) {
                this.f1395g = true;
                EtsyDebug.m1912c(f1389f, "no content: mWasSuccess = true. code: " + super.m1036q());
                return;
            } else {
                return;
            }
        }
        m1062m();
    }

    @CallSuper
    protected void m1062m() {
        this.f1395g = false;
        this.f1400l = true;
        EtsyDebug.m1917d(f1389f, "Volley error : parseError", m1037r());
        EtsyDebug.m1920e(f1389f, "Error", toString());
        if (m1038s() != null && m1040u() != null) {
            if (m1041v() != null) {
                try {
                    this.f1394e = new String(m1040u(), HttpHeaderParser.parseCharset(m1041v()));
                } catch (Throwable e) {
                    EtsyDebug.m1917d(f1389f, "parseError UnsupportedEncodingException parsing error response", e);
                }
            } else {
                this.f1394e = new String(m1040u(), Charset.forName(Constants.ENCODING));
            }
            EtsyDebug.m1920e(f1389f, "Error", this.f1394e);
        }
    }

    protected void m1048a(String str) {
        this.f1395g = false;
        this.f1394e = str;
    }

    public void m1063n() {
        String str = this.f1394e;
        JsonParser jsonParser = null;
        if (bh.m3340a(str)) {
            try {
                jsonParser = ad.m1081a().m1082a(str.getBytes(Charset.forName(Constants.ENCODING)));
                jsonParser.nextToken();
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    str = jsonParser.getCurrentName();
                    jsonParser.nextToken();
                    if ("errors".equals(str)) {
                        m1042a(jsonParser);
                    } else {
                        jsonParser.skipChildren();
                    }
                }
                if (jsonParser != null && !jsonParser.isClosed()) {
                    try {
                        jsonParser.close();
                    } catch (Throwable e) {
                        EtsyDebug.m1913c(f1389f, "couldn't close JsonParser - ignoring", e);
                    }
                }
            } catch (Throwable e2) {
                EtsyDebug.m1917d(f1389f, "parseJsonErrors NEW error", e2);
                if (jsonParser != null && !jsonParser.isClosed()) {
                    try {
                        jsonParser.close();
                    } catch (Throwable e22) {
                        EtsyDebug.m1913c(f1389f, "couldn't close JsonParser - ignoring", e22);
                    }
                }
            } catch (Throwable th) {
                if (!(jsonParser == null || jsonParser.isClosed())) {
                    try {
                        jsonParser.close();
                    } catch (Throwable e3) {
                        EtsyDebug.m1913c(f1389f, "couldn't close JsonParser - ignoring", e3);
                    }
                }
            }
        }
    }

    protected void m1064o() {
        if (EtsyDebug.m1903a()) {
            Map v = m1041v();
            if (v == null) {
                EtsyDebug.m1919e(f1389f, "NULL HEADERS IN v2");
                return;
            }
            for (Entry entry : v.entrySet()) {
                EtsyDebug.m1912c(f1389f, "Header: " + ((String) entry.getKey()) + ": " + ((String) entry.getValue()));
            }
        }
    }

    protected void m1047a(@Nullable Class<Result> cls) {
        JsonParser jsonParser = null;
        try {
            jsonParser = ad.m1081a().m1082a(m1040u());
            jsonParser.nextToken();
            m1046a(jsonParser, cls);
            if (jsonParser != null && !jsonParser.isClosed()) {
                try {
                    jsonParser.close();
                } catch (Throwable e) {
                    EtsyDebug.m1913c(f1389f, "couldn't close JsonParser - ignoring", e);
                }
            }
        } catch (Throwable e2) {
            EtsyDebug.m1917d(f1389f, "parseResponseData NEW error", e2);
            if (jsonParser != null && !jsonParser.isClosed()) {
                try {
                    jsonParser.close();
                } catch (Throwable e22) {
                    EtsyDebug.m1913c(f1389f, "couldn't close JsonParser - ignoring", e22);
                }
            }
        } catch (Throwable th) {
            if (!(jsonParser == null || jsonParser.isClosed())) {
                try {
                    jsonParser.close();
                } catch (Throwable e3) {
                    EtsyDebug.m1913c(f1389f, "couldn't close JsonParser - ignoring", e3);
                }
            }
        }
    }

    protected void m1046a(JsonParser jsonParser, @Nullable Class<Result> cls) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (ResponseConstants.COUNT.equals(currentName)) {
                this.f1392c = jsonParser.getValueAsInt();
            } else if (ResponseConstants.LAST_ID.equals(currentName)) {
                this.f1399k = jsonParser.getValueAsLong();
            } else if (ResponseConstants.CODE.equals(currentName)) {
                this.f1390a = jsonParser.getValueAsInt();
                this.f1395g = super.m1034b();
            } else if (BaseMessage.TYPE_ERROR.equals(currentName)) {
                m1048a(jsonParser.getValueAsString());
            } else if (ResponseConstants.RESULTS.equals(currentName)) {
                m1050b(jsonParser, cls);
                this.f1391b = this.f1396h.size();
            } else {
                jsonParser.skipChildren();
            }
        }
    }

    protected void m1050b(JsonParser jsonParser, Class<Result> cls) {
        if (cls == null) {
            EtsyDebug.m1916d(f1389f, "NO RESPONSE TYPE! - Skipping parseResponseResults");
        }
        if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT || jsonParser.getCurrentToken() == JsonToken.VALUE_NUMBER_INT) {
                    m1045c(jsonParser, cls);
                } else if (jsonParser.getCurrentToken() == null) {
                    return;
                }
            }
        } else if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
            EtsyDebug.m1916d(f1389f, "Json results were not array parsing as a single model " + cls);
            m1045c(jsonParser, cls);
        } else {
            EtsyDebug.m1916d(f1389f, "Received response with no start object. Skipping.");
            jsonParser.skipChildren();
        }
    }

    private void m1042a(JsonParser jsonParser) {
        if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT || jsonParser.getCurrentToken() == JsonToken.VALUE_NUMBER_INT) {
                    m1043b(jsonParser);
                } else if (jsonParser.getCurrentToken() == null) {
                    return;
                }
            }
        } else if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
            m1043b(jsonParser);
        } else {
            EtsyDebug.m1916d(f1389f, "Received response error with no start object. Skipping.");
            jsonParser.skipChildren();
        }
    }

    private void m1043b(JsonParser jsonParser) {
        EtsyError etsyError = (EtsyError) ModelFactory.create(jsonParser, EtsyError.class);
        if (etsyError != null) {
            this.f1397i.add(etsyError);
        } else {
            EtsyDebug.m1919e(f1389f, "Debug Warning: ModelFactory could not create EtsyError. ");
        }
    }

    private void m1045c(JsonParser jsonParser, Class<Result> cls) {
        if (cls == null) {
            EtsyDebug.m1919e(f1389f, "parseResultModel skipping - no result type");
            return;
        }
        BaseModel create = ModelFactory.create(jsonParser, cls);
        if (create != null) {
            this.f1396h.add(create);
        } else {
            EtsyDebug.m1919e(f1389f, "Debug Warning: ModelFactory could not create BaseModel. Return type was " + cls);
        }
    }
}
