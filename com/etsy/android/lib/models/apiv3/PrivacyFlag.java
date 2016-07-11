package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.requests.EtsyRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class PrivacyFlag extends BaseModel {
    private static final long serialVersionUID = -1736461258020701046L;
    protected boolean mEnabled;
    protected String mLabel;
    protected String mLanguage;

    public PrivacyFlag() {
        this.mLabel = StringUtils.EMPTY;
        this.mLanguage = StringUtils.EMPTY;
    }

    public PrivacyFlag(PrivacyFlag privacyFlag) {
        this.mLabel = StringUtils.EMPTY;
        this.mLanguage = StringUtils.EMPTY;
        this.mLabel = privacyFlag.getLabel();
        this.mEnabled = privacyFlag.isEnabled();
        this.mLanguage = privacyFlag.getLanguage();
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -1613589672:
                        if (currentName.equals(EtsyRequest.PARAM_LANGUAGE)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case -1609594047:
                        if (currentName.equals(ResponseConstants.ENABLED)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 102727412:
                        if (currentName.equals(ResponseConstants.LABEL)) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mLabel = BaseModel.parseString(jsonParser);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mEnabled = jsonParser.getValueAsBoolean();
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mLanguage = BaseModel.parseString(jsonParser);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    @NonNull
    public String getLabel() {
        return this.mLabel;
    }

    public void setLabel(String str) {
        this.mLabel = str;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
    }

    @JsonIgnore
    public String getLanguage() {
        return this.mLanguage;
    }

    public boolean equals(Object obj) {
        return (obj instanceof PrivacyFlag) && ObjectUtils.equals(this.mLabel, ((PrivacyFlag) obj).getLabel()) && ObjectUtils.equals(Boolean.valueOf(this.mEnabled), Boolean.valueOf(((PrivacyFlag) obj).isEnabled()));
    }

    public int hashCode() {
        return (this.mEnabled ? 1 : 0) + ((ObjectUtils.hashCode(this.mLabel) + 527) * 31);
    }
}
