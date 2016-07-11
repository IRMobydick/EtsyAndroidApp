package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.url.p009a.EtsyV3Urls;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class NotificationOptOutBannerSetting extends BaseModel {
    private static final long serialVersionUID = 4635777384166102045L;
    protected String mBannerDisableLink;
    protected boolean mBannerShouldShow;
    protected String mBannerText;

    public NotificationOptOutBannerSetting() {
        this.mBannerText = StringUtils.EMPTY;
        this.mBannerDisableLink = StringUtils.EMPTY;
    }

    public boolean getBannerShouldShow() {
        return this.mBannerShouldShow;
    }

    public String getBannerText() {
        return this.mBannerText;
    }

    public String getBannerDisableLink() {
        return this.mBannerDisableLink;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.BANNER_ENABLED.equals(currentName)) {
                    this.mBannerShouldShow = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.BANNER_TEXT.equals(currentName)) {
                    this.mBannerText = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.DISABLE_LINK.equals(currentName)) {
                    this.mBannerDisableLink = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public static EtsyApiV3Request<NotificationOptOutBannerSetting> createRequestBuilder(String str, String str2) {
        return new EtsyApiV3Request(NotificationOptOutBannerSetting.class, EtsyV3Urls.m1504a(str, str2));
    }
}
