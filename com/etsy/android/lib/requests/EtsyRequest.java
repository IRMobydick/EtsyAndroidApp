package com.etsy.android.lib.requests;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.android.volley.Request.Priority;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.util.CurrencyUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.scribe.e.b;

public class EtsyRequest<Result extends BaseModel> implements Serializable {
    private static final String BAR_SEPARATED_STRING = "%s|%s|%s";
    public static final String CONTENT_ENCODING = "utf-8";
    public static final int DEFAULT_TIMEOUT = 10000;
    private static final String ENCODING_GZIP = "gzip";
    private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    private static final String HEADER_DETECTED_LOCALE = "X-Detected-Locale";
    private static final String HEADER_ETSY_DEVICE = "X-Etsy-Device";
    private static final String HEADER_USER_AGENT = "User-agent";
    public static final String HEADER_X_EXPERIMENTAL_API = "X-Experimental-Api";
    public static final String JSON_CONTENT_TYPE;
    public static final String PARAM_CURRENCY = "currency";
    public static final String PARAM_LANGUAGE = "language";
    public static final String PARAM_REGION = "region";
    private static final String TAG;
    public static final String URL_FORM_CONTENT_TYPE;
    private static final long serialVersionUID = 2715154933950012215L;
    private String mBodyParameters;
    private boolean mCachable;
    private String mContentType;
    private String mEndpoint;
    private EndpointType mEndpointType;
    private boolean mHasQuestionMark;
    private HashMap<String, String> mHeaders;
    private boolean mIsBespoke;
    private boolean mIsNextLink;
    private String mOverrideLanguage;
    private byte[] mPayload;
    private Priority mPriority;
    private RequestMethod mRequestMethod;
    private String mRequestParameters;
    private String mRequestUrl;
    private Class<Result> mResponseClass;
    private float mRetryBackOffMultiplier;
    private int mRetryCount;
    private boolean mSigned;
    private int mTimeout;
    private APIv3Scope mV3Scope;

    public enum APIv3Scope {
        PUBLIC("/public"),
        MEMBER("/member"),
        SHOP("/shop");
        
        private String mName;
        private String mPath;

        private APIv3Scope(String str) {
            this.mPath = str;
        }

        public String toString() {
            return this.mPath;
        }

        public void setIdentifier(String str) {
            this.mName = str;
        }

        public String getIdentifier() {
            return this.mName;
        }
    }

    public enum EndpointType {
        API,
        APIv3,
        ETSY,
        I_CAN_HAZ_TOKEN
    }

    public enum RequestMethod {
        GET,
        PUT,
        POST,
        DELETE
    }

    static {
        TAG = EtsyDebug.m1891a(EtsyRequest.class);
        JSON_CONTENT_TYPE = String.format(Locale.US, "application/json; charset=%s", new Object[]{CONTENT_ENCODING});
        URL_FORM_CONTENT_TYPE = String.format(Locale.US, "application/x-www-form-urlencoded", new Object[0]);
    }

    public EtsyRequest() {
        this.mIsBespoke = false;
        this.mContentType = "application/x-www-form-urlencoded";
        this.mPriority = Priority.HIGH;
        this.mCachable = false;
        this.mHasQuestionMark = false;
    }

    public EtsyRequest(String str, RequestMethod requestMethod, Class<Result> cls, EndpointType endpointType) {
        this(str, requestMethod, cls, endpointType, null);
    }

    public EtsyRequest(String str, RequestMethod requestMethod, Class<Result> cls) {
        this(str, requestMethod, cls, EndpointType.API, null);
    }

    public EtsyRequest(String str, RequestMethod requestMethod, Class<Result> cls, EndpointType endpointType, String str2) {
        this.mIsBespoke = false;
        this.mContentType = "application/x-www-form-urlencoded";
        this.mPriority = Priority.HIGH;
        this.mCachable = false;
        this.mHasQuestionMark = false;
        this.mRequestUrl = str;
        this.mRequestMethod = requestMethod;
        this.mResponseClass = cls;
        this.mHasQuestionMark = this.mRequestUrl.contains("?");
        this.mRequestParameters = StringUtils.EMPTY;
        this.mBodyParameters = StringUtils.EMPTY;
        this.mSigned = true;
        this.mV3Scope = APIv3Scope.PUBLIC;
        this.mIsNextLink = false;
        this.mOverrideLanguage = str2;
        this.mEndpointType = endpointType;
        updateEndpointForType();
        this.mTimeout = DEFAULT_TIMEOUT;
        this.mHeaders = getDefaultHeaders();
        if (this.mEndpointType != EndpointType.APIv3) {
            addParams(getDefaultParams());
        }
    }

    public String getUrl() {
        return this.mEndpoint + this.mRequestUrl;
    }

    public String getRequestUrl() {
        return this.mRequestUrl;
    }

    public void setRequestUrl(String str) {
        this.mRequestUrl = str;
    }

    public void setViewTracker(@Nullable ad adVar) {
        if (adVar != null) {
            this.mHeaders.put("X-Page-GUID", adVar.m1843a());
        }
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.mRequestMethod = requestMethod;
    }

    public RequestMethod getRequestMethod() {
        return this.mRequestMethod;
    }

    public Class<Result> getResponseClass() {
        return this.mResponseClass;
    }

    public byte[] getPayload() {
        return this.mPayload;
    }

    public void setPayload(byte[] bArr) {
        this.mPayload = bArr;
    }

    public void addHeader(String str, String str2) {
        this.mHeaders.put(str, str2);
    }

    public void addHeaders(@NonNull Map<String, String> map) {
        this.mHeaders.putAll(map);
    }

    @JsonIgnore
    public String getUrlWithParams() {
        if (this.mIsNextLink) {
            return this.mRequestUrl;
        }
        return this.mRequestUrl + this.mRequestParameters;
    }

    public int getTimeout() {
        return this.mTimeout;
    }

    public void setTimeout(int i) {
        this.mTimeout = i;
    }

    public boolean isSigned() {
        return this.mSigned;
    }

    public void setSigned(boolean z) {
        this.mSigned = z;
    }

    public String getContentType() {
        return this.mContentType;
    }

    public void setContentType(String str) {
        this.mContentType = str;
    }

    public int getRetryCount() {
        return this.mRetryCount;
    }

    public void setRetryCount(int i) {
        this.mRetryCount = i;
    }

    public float getRetryBackOffMultiplier() {
        return this.mRetryBackOffMultiplier;
    }

    public void setRetryBackOffMultiplier(float f) {
        this.mRetryBackOffMultiplier = f;
    }

    public void setResponseClass(Class<Result> cls) {
        this.mResponseClass = cls;
    }

    public String getRequestParameters() {
        return this.mRequestParameters;
    }

    public void setRequestParameters(String str) {
        this.mRequestParameters = str;
    }

    public String getBodyParameters() {
        return this.mBodyParameters;
    }

    public void setBodyParameters(String str) {
        this.mBodyParameters = str;
    }

    public String getAllParameters() {
        return concatUrlEncodedParams(this.mRequestParameters, this.mBodyParameters, false);
    }

    public void addParams(Map<String, String> map) {
        this.mRequestParameters = concatUrlEncodedParams(this.mRequestParameters, createUrlEncodingParams(map), !this.mHasQuestionMark);
    }

    public void addBodyParams(Map<String, String> map) {
        this.mBodyParameters = concatUrlEncodedParams(this.mBodyParameters, createUrlEncodingParams(map), false);
        setContentType(URL_FORM_CONTENT_TYPE);
        try {
            setPayload(this.mBodyParameters.getBytes(CONTENT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            EtsyDebug.m1919e(TAG, StringUtils.EMPTY);
        }
    }

    public void addBodyParam(String str, String str2) {
        this.mBodyParameters = concatUrlEncodedParams(this.mBodyParameters, createUrlEncodingParam(str, str2), false);
        setContentType(URL_FORM_CONTENT_TYPE);
        try {
            setPayload(this.mBodyParameters.getBytes(CONTENT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            EtsyDebug.m1919e(TAG, StringUtils.EMPTY);
        }
    }

    private String createUrlEncodingParams(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        if (map.size() > 0) {
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                stringBuilder.append(createUrlEncodingParam((String) entry.getKey(), (String) entry.getValue()));
                if (it.hasNext()) {
                    stringBuilder.append("&");
                }
            }
        }
        return stringBuilder.toString();
    }

    private String createUrlEncodingParam(String str, String str2) {
        return str + "=" + b.a(str2);
    }

    private String concatUrlEncodedParams(String str, String str2, boolean z) {
        String str3 = StringUtils.EMPTY;
        String replace = str.replace(' ', '+');
        String replace2 = str2.replace(' ', '+');
        if (replace.length() != 0) {
            return replace.length() > 0 ? replace + "&" + replace2 : str3;
        } else {
            if (!z) {
                return replace2;
            }
            replace2 = "?" + replace2;
            this.mHasQuestionMark = true;
            return replace2;
        }
    }

    @JsonIgnore
    private Map<String, String> getDefaultParams() {
        Locale locale = Locale.getDefault();
        Map<String, String> hashMap = new HashMap();
        hashMap.put(PARAM_CURRENCY, CurrencyUtil.m3091i());
        hashMap.put(PARAM_REGION, locale.getCountry());
        if (TextUtils.isEmpty(this.mOverrideLanguage)) {
            hashMap.put(PARAM_LANGUAGE, locale.getLanguage());
        } else {
            EtsyDebug.m1906b(TAG, "Overriding device locale API language param with language: " + this.mOverrideLanguage);
            hashMap.put(PARAM_LANGUAGE, this.mOverrideLanguage);
        }
        return hashMap;
    }

    public Map<String, String> getHeaders() {
        return this.mHeaders;
    }

    private HashMap<String, String> getDefaultHeaders() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put(HEADER_USER_AGENT, InstallInfo.m919a().m935l());
        hashMap.put(HEADER_ETSY_DEVICE, InstallInfo.m919a().m925b());
        hashMap.put(HEADER_DETECTED_LOCALE, getDetectedLocaleHttpHeaderValue(this.mOverrideLanguage));
        hashMap.put(HEADER_ACCEPT_ENCODING, ENCODING_GZIP);
        if (EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bC)) {
            hashMap.put(HEADER_X_EXPERIMENTAL_API, "true");
        }
        return hashMap;
    }

    public String getEndpoint() {
        return this.mEndpoint;
    }

    public void setEndpoint(String str) {
        this.mEndpoint = str;
    }

    public EndpointType getEndpointType() {
        return this.mEndpointType;
    }

    private void updateEndpointForType() {
        if (this.mEndpointType == EndpointType.APIv3 && this.mIsNextLink) {
            this.mEndpoint = EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.cz);
        } else if (this.mEndpointType == EndpointType.APIv3) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.cy));
            if (this.mIsBespoke) {
                stringBuilder.append("/bespoke");
            }
            stringBuilder.append(this.mV3Scope.toString());
            if (this.mV3Scope.equals(APIv3Scope.SHOP)) {
                if (APIv3Scope.SHOP.getIdentifier() == null) {
                    throw new IllegalArgumentException("Attempting to use shop scope for API v3 without setting the APIv3Scope.SHOP identifier id");
                }
                stringBuilder.append("/");
                stringBuilder.append(APIv3Scope.SHOP.getIdentifier());
            }
            this.mEndpoint = stringBuilder.toString();
        } else if (this.mEndpointType == EndpointType.API) {
            this.mEndpoint = EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.cx);
        } else if (this.mEndpointType == EndpointType.ETSY) {
            this.mEndpoint = EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.cv);
        } else if (this.mEndpointType == EndpointType.I_CAN_HAZ_TOKEN) {
            this.mEndpoint = EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.cC);
        }
    }

    public void setIsNextLink(boolean z) {
        if (!z || this.mEndpointType == EndpointType.APIv3) {
            this.mIsNextLink = z;
            updateEndpointForType();
            return;
        }
        throw new IllegalStateException("A next link request can only be used with an APIv3 endpoint");
    }

    public void setV3Scope(APIv3Scope aPIv3Scope) {
        if (this.mEndpointType != EndpointType.APIv3) {
            throw new IllegalStateException("A v3 scope can only be used with an APIv3 endpoint");
        }
        this.mV3Scope = aPIv3Scope;
        updateEndpointForType();
    }

    public void setV3Bespoke(boolean z) {
        if (this.mEndpointType != EndpointType.APIv3) {
            throw new IllegalStateException("Bespoke endpoints are only available on APIv3");
        }
        this.mIsBespoke = z;
        updateEndpointForType();
    }

    public void setPriority(Priority priority) {
        this.mPriority = priority;
    }

    public Priority getPriority() {
        return this.mPriority;
    }

    public boolean isCachable() {
        return this.mCachable;
    }

    public void setCachable(boolean z) {
        this.mCachable = z;
    }

    public static String getDetectedLocaleHttpHeaderValue(String str) {
        Locale locale = Locale.getDefault();
        if (TextUtils.isEmpty(str)) {
            str = locale.getLanguage();
        } else {
            EtsyDebug.m1906b(TAG, "Overriding device language on APIv3 request with: " + str);
        }
        if (TextUtils.equals(locale.getCountry().toLowerCase(), "gb")) {
            str = str + "-" + locale.getCountry();
        }
        return String.format(BAR_SEPARATED_STRING, new Object[]{CurrencyUtil.m3091i(), str, locale.getCountry()});
    }
}
