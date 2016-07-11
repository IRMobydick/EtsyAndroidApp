package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import org.parceler.Parcel;

@Parcel
public class OfferingResponse extends BaseFieldModel {
    private static final long serialVersionUID = 8693379461099167307L;
    protected OfferingPrice mMaxPrice;
    protected int mMaxQuantity;
    protected OfferingPrice mMinPrice;
    protected int mMinQuantity;
    protected Offering mOffering;
    protected OfferingUi mUi;

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        boolean z = true;
        switch (str.hashCode()) {
            case -768536570:
                if (str.equals(ResponseConstants.OFFERING)) {
                    z = true;
                    break;
                }
                break;
            case -326778618:
                if (str.equals(ResponseConstants.MAX_QUANTITY)) {
                    z = true;
                    break;
                }
                break;
            case -237166930:
                if (str.equals(ResponseConstants.MAX_PRICE)) {
                    z = true;
                    break;
                }
                break;
            case 3732:
                if (str.equals(ResponseConstants.UI)) {
                    z = false;
                    break;
                }
                break;
            case 147647448:
                if (str.equals(ResponseConstants.MIN_QUANTITY)) {
                    z = true;
                    break;
                }
                break;
            case 535311644:
                if (str.equals(ResponseConstants.MIN_PRICE)) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                this.mUi = (OfferingUi) BaseModel.parseObject(jsonParser, OfferingUi.class);
                break;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                this.mOffering = (Offering) BaseModel.parseObject(jsonParser, Offering.class);
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                this.mMinQuantity = jsonParser.getValueAsInt();
                break;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                this.mMaxQuantity = jsonParser.getValueAsInt();
                break;
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                this.mMinPrice = (OfferingPrice) BaseModel.parseObject(jsonParser, OfferingPrice.class);
                break;
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                this.mMaxPrice = (OfferingPrice) BaseModel.parseObject(jsonParser, OfferingPrice.class);
                break;
            default:
                return false;
        }
        return true;
    }

    public OfferingUi getUi() {
        return this.mUi;
    }

    @Nullable
    public Offering getOffering() {
        return this.mOffering;
    }

    public int getMinQuantity() {
        return this.mMinQuantity;
    }

    public int getMaxQuantity() {
        return this.mMaxQuantity;
    }

    public OfferingPrice getMinPrice() {
        return this.mMinPrice;
    }

    public OfferingPrice getMaxPrice() {
        return this.mMaxPrice;
    }
}
