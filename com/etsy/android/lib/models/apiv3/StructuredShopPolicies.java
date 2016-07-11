package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class StructuredShopPolicies extends BaseModel {
    private static final long serialVersionUID = -3325270444200620201L;
    protected String mAdditionalTermsAndConditions;
    protected StructuredShopCharLimits mCharacterLimits;
    protected StructuredShopPayments mPayments;
    protected EtsyId mPoliciesId;
    protected StructuredShopPrivacy mPrivacy;
    protected StructuredShopRefunds mRefunds;
    protected StructuredShopShipping mShipping;
    protected boolean mShopInGermany;
    protected Date mUpdateDate;

    public StructuredShopPolicies() {
        this.mPoliciesId = new EtsyId();
        this.mCharacterLimits = new StructuredShopCharLimits();
        this.mAdditionalTermsAndConditions = StringUtils.EMPTY;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -1734561918:
                        if (currentName.equals(ResponseConstants.SHOP_IN_GERMANY)) {
                            obj = 7;
                            break;
                        }
                        break;
                    case -1573828319:
                        if (currentName.equals(ResponseConstants.CHAR_LIMITS)) {
                            obj = 8;
                            break;
                        }
                        break;
                    case -1158353680:
                        if (currentName.equals(ResponseConstants.TERMS_AND_CONDITIONS)) {
                            obj = 5;
                            break;
                        }
                        break;
                    case -573930140:
                        if (currentName.equals(ResponseConstants.UPDATE_DATE)) {
                            obj = 6;
                            break;
                        }
                        break;
                    case -516235858:
                        if (currentName.equals(ResponseConstants.SHIPPING)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case -314498168:
                        if (currentName.equals(ResponseConstants.PRIVACY)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 1085542395:
                        if (currentName.equals(ResponseConstants.REFUNDS)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case 1382682413:
                        if (currentName.equals(ResponseConstants.PAYMENTS)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case 1713061482:
                        if (currentName.equals(ResponseConstants.POLICIES_ID)) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mPoliciesId.setId(BaseModel.parseString(jsonParser));
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mPrivacy = (StructuredShopPrivacy) BaseModel.parseObject(jsonParser, StructuredShopPrivacy.class);
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mPayments = (StructuredShopPayments) BaseModel.parseObject(jsonParser, StructuredShopPayments.class);
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mRefunds = (StructuredShopRefunds) BaseModel.parseObject(jsonParser, StructuredShopRefunds.class);
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.mShipping = (StructuredShopShipping) BaseModel.parseObject(jsonParser, StructuredShopShipping.class);
                        break;
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        this.mAdditionalTermsAndConditions = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                        this.mUpdateDate = BaseModel.parseIntoDate(jsonParser);
                        break;
                    case CommonStatusCodes.NETWORK_ERROR /*7*/:
                        this.mShopInGermany = jsonParser.getValueAsBoolean();
                        break;
                    case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                        this.mCharacterLimits = (StructuredShopCharLimits) BaseModel.parseObject(jsonParser, StructuredShopCharLimits.class);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    public StructuredShopShipping getShipping() {
        return this.mShipping;
    }

    public void setShipping(StructuredShopShipping structuredShopShipping) {
        this.mShipping = structuredShopShipping;
    }

    public StructuredShopPayments getPayments() {
        return this.mPayments;
    }

    public StructuredShopRefunds getRefunds() {
        return this.mRefunds;
    }

    public void setRefunds(StructuredShopRefunds structuredShopRefunds) {
        this.mRefunds = structuredShopRefunds;
    }

    public StructuredShopPrivacy getPrivacy() {
        return this.mPrivacy;
    }

    public void setPrivacy(StructuredShopPrivacy structuredShopPrivacy) {
        this.mPrivacy = structuredShopPrivacy;
    }

    public Date getLastUpdatedDate() {
        return this.mUpdateDate;
    }

    public boolean shopInGermany() {
        return this.mShopInGermany;
    }

    @NonNull
    public StructuredShopCharLimits getCharacterLimits() {
        return this.mCharacterLimits;
    }

    @NonNull
    public String getTermsAndConditions() {
        return this.mAdditionalTermsAndConditions;
    }
}
