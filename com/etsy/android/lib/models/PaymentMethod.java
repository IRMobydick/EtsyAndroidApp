package com.etsy.android.lib.models;

import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethod extends BaseModel {
    private static final String TYPE_ANDROID_PAY = "android_pay";
    private static final String TYPE_GOOGLE_WALLET = "google";
    private static final long serialVersionUID = 1675529418136702018L;
    private final List<String> mDetails;
    private boolean mIsDirectCheckout;
    private String mName;
    private String mType;

    public PaymentMethod() {
        this.mIsDirectCheckout = false;
        this.mDetails = new ArrayList(0);
    }

    public String getType() {
        return this.mType;
    }

    public String getName() {
        return this.mName;
    }

    public boolean isDirectCheckout() {
        return this.mIsDirectCheckout;
    }

    public boolean isAndroidPay() {
        return TYPE_ANDROID_PAY.equals(this.mType);
    }

    public boolean isGoogleWallet() {
        return TYPE_GOOGLE_WALLET.equals(this.mType);
    }

    public List<String> getDetails() {
        return this.mDetails;
    }

    public String toString() {
        return this.mType;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case 3373707:
                        if (currentName.equals(ResponseConstants.NAME)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 3575610:
                        if (currentName.equals(FindsModule.FIELD_TYPE)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 100490164:
                        if (currentName.equals("is_dc")) {
                            obj = 2;
                            break;
                        }
                        break;
                    case 1557721666:
                        if (currentName.equals("details")) {
                            obj = 3;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mType = BaseModel.parseString(jsonParser);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mName = BaseModel.parseString(jsonParser);
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mIsDirectCheckout = jsonParser.getValueAsBoolean();
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        parseDetails(jsonParser);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    private void parseDetails(JsonParser jsonParser) {
        if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                    this.mDetails.add(BaseModel.parseString(jsonParser));
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof PaymentMethod) && ((PaymentMethod) obj).mType.equals(this.mType);
    }

    public int hashCode() {
        return this.mType.hashCode();
    }
}
