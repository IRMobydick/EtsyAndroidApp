package com.etsy.android.lib.toolbar;

import com.adjust.sdk.Constants;
import com.android.volley.NetworkResponse;
import com.appboy.support.ValidationUtils;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdminToolbarNetworkResponse implements Serializable {
    private String mHeadersString;
    private byte[] mRawJson;
    private int mStatusCode;
    private String mUrl;

    public AdminToolbarNetworkResponse(String str, NetworkResponse networkResponse) {
        this.mUrl = StringUtils.EMPTY;
        try {
            this.mUrl = URLDecoder.decode(str, Constants.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.mRawJson = networkResponse.data;
        this.mStatusCode = networkResponse.statusCode;
        StringBuilder stringBuilder = new StringBuilder(ValidationUtils.APPBOY_STRING_MAX_LENGTH);
        stringBuilder.append("<b>Headers:</b><br/>");
        for (String str2 : networkResponse.headers.keySet()) {
            stringBuilder.append("<b>");
            stringBuilder.append(str2);
            stringBuilder.append(":</b>");
            stringBuilder.append((String) networkResponse.headers.get(str2));
            stringBuilder.append("<br/>");
        }
        this.mHeadersString = stringBuilder.toString();
    }

    public String getUrl() {
        return this.mUrl;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public String getHeadersString() {
        return this.mHeadersString;
    }

    public String getPrettyJsonString() {
        Exception e;
        if (this.mRawJson == null) {
            return StringUtils.EMPTY;
        }
        try {
            return new JSONObject(new String(this.mRawJson, Constants.ENCODING)).toString(4);
        } catch (JSONException e2) {
            e = e2;
            e.printStackTrace();
            try {
                return new JSONArray(new String(this.mRawJson, Constants.ENCODING)).toString(4);
            } catch (JSONException e3) {
                e = e3;
                e.printStackTrace();
                try {
                    return new String(this.mRawJson, Constants.ENCODING);
                } catch (UnsupportedEncodingException e4) {
                    e4.printStackTrace();
                    return StringUtils.EMPTY;
                }
            } catch (UnsupportedEncodingException e5) {
                e = e5;
                e.printStackTrace();
                return new String(this.mRawJson, Constants.ENCODING);
            }
        } catch (UnsupportedEncodingException e6) {
            e = e6;
            e.printStackTrace();
            return new JSONArray(new String(this.mRawJson, Constants.ENCODING)).toString(4);
        }
    }
}
