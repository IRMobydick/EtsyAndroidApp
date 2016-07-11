package com.etsy.android.lib.models.apiv3.cart;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import org.parceler.Parcel;

@Parcel
public class PaymentAppliedCoupon extends BaseFieldModel {
    protected String mCouponCode;
    protected String mDescription;

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (ResponseConstants.DESCRIPTION.equals(str)) {
            this.mDescription = BaseModel.parseString(jsonParser);
        } else if (!ResponseConstants.CODE.equals(str)) {
            return false;
        } else {
            this.mCouponCode = BaseModel.parseString(jsonParser);
        }
        return true;
    }

    public String getCouponCode() {
        return this.mCouponCode;
    }

    public String getDescription() {
        return this.mDescription;
    }
}
