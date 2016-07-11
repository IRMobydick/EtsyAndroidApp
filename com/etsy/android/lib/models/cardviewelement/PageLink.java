package com.etsy.android.lib.models.cardviewelement;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;

public abstract class PageLink extends BaseModel {
    private static final long serialVersionUID = 7501388225293459805L;
    protected String linkTitle;
    protected String mEventName;
    protected String mPageTitle;

    public PageLink() {
        this.mEventName = "homescreen_landing_page_generic";
        this.mPageTitle = StringUtils.EMPTY;
        this.linkTitle = StringUtils.EMPTY;
    }

    public String getLinkTitle() {
        return this.linkTitle;
    }

    public String getPageTitle() {
        return this.mPageTitle;
    }

    public String getEventName() {
        return this.mEventName;
    }

    public void setPageTitle(String str) {
        this.mPageTitle = str;
    }

    public void setEventName(String str) {
        this.mEventName = str;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL && parseField(jsonParser, currentName)) {
                jsonParser.skipChildren();
            }
        }
    }

    protected boolean parseField(JsonParser jsonParser, String str) {
        if (ResponseConstants.EVENT_NAME.equals(str)) {
            this.mEventName = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.LINK_TITLE.equals(str)) {
            this.linkTitle = BaseModel.parseString(jsonParser);
        }
        return jsonParser.getCurrentName().equals(str);
    }
}
