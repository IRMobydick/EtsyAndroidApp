package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.ObjectUtils;

public class PrivacyFlags extends BaseModel {
    private static final long serialVersionUID = 1904584598858626743L;
    protected PrivacyFlag mCommunication;
    protected PrivacyFlag mFulfillment;
    protected PrivacyFlag mLegal;
    protected PrivacyFlag mOther;

    public PrivacyFlags() {
        this.mCommunication = new PrivacyFlag();
        this.mFulfillment = new PrivacyFlag();
        this.mLegal = new PrivacyFlag();
        this.mOther = new PrivacyFlag();
    }

    public PrivacyFlags(PrivacyFlags privacyFlags) {
        this.mCommunication = new PrivacyFlag();
        this.mFulfillment = new PrivacyFlag();
        this.mLegal = new PrivacyFlag();
        this.mOther = new PrivacyFlag();
        this.mCommunication = new PrivacyFlag(privacyFlags.getCommunication());
        this.mFulfillment = new PrivacyFlag(privacyFlags.getFulfillment());
        this.mLegal = new PrivacyFlag(privacyFlags.getLegal());
        this.mOther = new PrivacyFlag(privacyFlags.getOther());
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -1035284522:
                        if (currentName.equals(ResponseConstants.COMMUNICATION)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 102851257:
                        if (currentName.equals(ResponseConstants.LEGAL)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case 106069776:
                        if (currentName.equals(ResponseConstants.OTHER)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case 1512395230:
                        if (currentName.equals(ResponseConstants.FULFILLMENT)) {
                            obj = 1;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mCommunication = (PrivacyFlag) BaseModel.parseObject(jsonParser, PrivacyFlag.class);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mFulfillment = (PrivacyFlag) BaseModel.parseObject(jsonParser, PrivacyFlag.class);
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mLegal = (PrivacyFlag) BaseModel.parseObject(jsonParser, PrivacyFlag.class);
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mOther = (PrivacyFlag) BaseModel.parseObject(jsonParser, PrivacyFlag.class);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    @NonNull
    public PrivacyFlag getCommunication() {
        return this.mCommunication;
    }

    @NonNull
    public PrivacyFlag getFulfillment() {
        return this.mFulfillment;
    }

    @NonNull
    public PrivacyFlag getLegal() {
        return this.mLegal;
    }

    @NonNull
    public PrivacyFlag getOther() {
        PrivacyFlag privacyFlag = this.mOther;
        boolean z = this.mOther.isEnabled() && bh.m3343b(this.mOther.getLabel());
        privacyFlag.setEnabled(z);
        return this.mOther;
    }

    public boolean equals(Object obj) {
        return (obj instanceof PrivacyFlags) && ObjectUtils.equals(this.mCommunication, ((PrivacyFlags) obj).getCommunication()) && ObjectUtils.equals(this.mFulfillment, ((PrivacyFlags) obj).getFulfillment()) && ObjectUtils.equals(this.mLegal, ((PrivacyFlags) obj).getLegal()) && ObjectUtils.equals(this.mOther, ((PrivacyFlags) obj).getOther());
    }

    public int hashCode() {
        return ((((((ObjectUtils.hashCode(this.mCommunication) + 527) * 31) + ObjectUtils.hashCode(this.mFulfillment)) * 31) + ObjectUtils.hashCode(this.mLegal)) * 31) + ObjectUtils.hashCode(this.mOther);
    }
}
