package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.ObjectUtils;

public class NonRefundableItem extends BaseModel {
    private static final long serialVersionUID = -2427758889766752627L;
    protected String mName;
    protected boolean mNonRefundable;
    protected String mType;

    public NonRefundableItem(NonRefundableItem nonRefundableItem) {
        this.mType = nonRefundableItem.getType();
        this.mName = nonRefundableItem.getName();
        this.mNonRefundable = nonRefundableItem.isNonRefundable();
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -1588014716:
                        if (currentName.equals(ResponseConstants.NON_REFUNDABLE)) {
                            obj = 2;
                            break;
                        }
                        break;
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
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mType = BaseModel.parseStringPreserveHTMLEscapeEncoding(jsonParser);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mName = BaseModel.parseString(jsonParser);
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mNonRefundable = jsonParser.getValueAsBoolean();
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    public String getType() {
        return this.mType;
    }

    public boolean isNonRefundable() {
        return this.mNonRefundable;
    }

    public void setNonRefundable(boolean z) {
        this.mNonRefundable = z;
    }

    public String getName() {
        return this.mName;
    }

    public boolean equals(Object obj) {
        return (obj instanceof NonRefundableItem) && this.mType.equals(((NonRefundableItem) obj).getType()) && this.mName.equals(((NonRefundableItem) obj).getName()) && this.mNonRefundable == ((NonRefundableItem) obj).isNonRefundable();
    }

    public int hashCode() {
        return (((((this.mNonRefundable ? 0 : 1) + 527) * 31) + ObjectUtils.hashCode(this.mType)) * 31) + ObjectUtils.hashCode(this.mName);
    }
}
