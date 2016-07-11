package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ShopPolicy extends BaseModel {
    protected String mAdditionalInformationMessage;
    protected boolean mHasNoPolicies;
    protected String mPaymentPolicy;
    protected String mRefundPolicy;
    protected String mShippingPolicy;
    @Nullable
    protected Date mUpdateDate;
    protected String mWelcomeMessage;

    public ShopPolicy() {
        this.mWelcomeMessage = StringUtils.EMPTY;
        this.mPaymentPolicy = StringUtils.EMPTY;
        this.mShippingPolicy = StringUtils.EMPTY;
        this.mRefundPolicy = StringUtils.EMPTY;
        this.mAdditionalInformationMessage = StringUtils.EMPTY;
    }

    @NonNull
    public String getAdditionalInformationMessage() {
        return this.mAdditionalInformationMessage;
    }

    @NonNull
    public String getPaymentPolicy() {
        return this.mPaymentPolicy;
    }

    @NonNull
    public String getRefundPolicy() {
        return this.mRefundPolicy;
    }

    @NonNull
    public String getShippingPolicy() {
        return this.mShippingPolicy;
    }

    @Nullable
    public Date getUpdateDate() {
        return this.mUpdateDate;
    }

    @NonNull
    public String getWelcomeMessage() {
        return this.mWelcomeMessage;
    }

    public boolean hasNoPolicies() {
        return this.mHasNoPolicies;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -1931413465:
                        if (currentName.equals(ResponseConstants.ADDITIONAL)) {
                            obj = 5;
                            break;
                        }
                        break;
                    case -786681338:
                        if (currentName.equals(ResponseConstants.PAYMENT)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case -573930140:
                        if (currentName.equals(ResponseConstants.UPDATE_DATE)) {
                            obj = null;
                            break;
                        }
                        break;
                    case -516235858:
                        if (currentName.equals(ResponseConstants.SHIPPING)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case 571463657:
                        if (currentName.equals(ResponseConstants.HAS_NO_POLICIES)) {
                            obj = 6;
                            break;
                        }
                        break;
                    case 1085542395:
                        if (currentName.equals(ResponseConstants.REFUNDS)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case 1233099618:
                        if (currentName.equals(ResponseConstants.WELCOME)) {
                            obj = 1;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mUpdateDate = BaseModel.parseIntoDate(jsonParser);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mWelcomeMessage = BaseModel.parseString(jsonParser);
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mPaymentPolicy = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mShippingPolicy = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.mRefundPolicy = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        this.mAdditionalInformationMessage = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                        this.mHasNoPolicies = jsonParser.getValueAsBoolean();
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    public boolean isEmpty() {
        return this.mAdditionalInformationMessage.isEmpty() && this.mWelcomeMessage.isEmpty() && this.mPaymentPolicy.isEmpty() && this.mRefundPolicy.isEmpty() && this.mShippingPolicy.isEmpty();
    }
}
