package com.etsy.android.lib.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class GiftCardDesign extends BaseModel {
    public static final int GIFT_CARD_DESIGN_ETSY_LOGO = 4;
    private static final long serialVersionUID = 799080527396563997L;
    private final String DESIGN_ID;
    private final String URL_150X119;
    private final String URL_280X166;
    private final String URL_560X332;
    private final String URL_69X69;
    private final String URL_BANNER;
    protected int mId;
    protected String mUrl150x119;
    protected String mUrl280x166;
    protected String mUrl560x332;
    protected String mUrl69x69;
    protected String mUrlBanner;

    public GiftCardDesign() {
        this.URL_69X69 = "url_69x69";
        this.URL_150X119 = "url_150x119";
        this.URL_560X332 = "url_560x332";
        this.URL_280X166 = "url_280x166";
        this.URL_BANNER = "url_banner";
        this.DESIGN_ID = "design_id";
        this.mUrl69x69 = StringUtils.EMPTY;
        this.mUrl150x119 = StringUtils.EMPTY;
        this.mUrl560x332 = StringUtils.EMPTY;
        this.mUrl280x166 = StringUtils.EMPTY;
        this.mUrlBanner = StringUtils.EMPTY;
    }

    public String getUrl69x69() {
        return this.mUrl69x69;
    }

    public String getUrl150x119() {
        return this.mUrl150x119;
    }

    public String getUrl280x166() {
        return this.mUrl280x166;
    }

    public String getUrl560x332() {
        return this.mUrl560x332;
    }

    public String getUrlBanner() {
        return this.mUrlBanner;
    }

    public int getId() {
        return this.mId;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("url_69x69".equals(currentName)) {
                    this.mUrl69x69 = BaseModel.parseStringURL(jsonParser);
                } else if ("url_150x119".equals(currentName)) {
                    this.mUrl150x119 = BaseModel.parseStringURL(jsonParser);
                } else if ("url_560x332".equals(currentName)) {
                    this.mUrl560x332 = BaseModel.parseStringURL(jsonParser);
                } else if ("url_280x166".equals(currentName)) {
                    this.mUrl280x166 = BaseModel.parseStringURL(jsonParser);
                } else if ("url_banner".equals(currentName)) {
                    this.mUrlBanner = BaseModel.parseStringURL(jsonParser);
                } else if ("design_id".equals(currentName)) {
                    this.mId = jsonParser.getIntValue();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
