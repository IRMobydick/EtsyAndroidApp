package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;
import org.parceler.Parcel;

@Parcel
public class StructuredShopCharLimits extends BaseModel {
    private static final long serialVersionUID = -44975806744768436L;
    protected int mPrivacyPolicyOtherLimit;

    public StructuredShopCharLimits() {
        this.mPrivacyPolicyOtherLimit = 750;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -660169446:
                        if (currentName.equals(ResponseConstants.PRIVACY_POLICY_OTHER)) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mPrivacyPolicyOtherLimit = jsonParser.getValueAsInt();
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    public int getPrivacyOtherLimit() {
        return this.mPrivacyPolicyOtherLimit;
    }
}
