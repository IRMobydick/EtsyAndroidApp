package com.etsy.android.lib.models.apiv3.cart;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import org.parceler.Parcel;

@Parcel
public class PaymentMethod extends BaseFieldModel {
    public static final String TYPE_ANDROID_PAY = "android_pay";
    public static final String TYPE_BANKTRANSFER = "bank_transfer";
    public static final String TYPE_CC = "cc";
    public static final String TYPE_CHECK = "check";
    public static final String TYPE_IDEAL = "ideal";
    public static final String TYPE_MONEYORDER = "money_order";
    public static final String TYPE_PAYPAL = "paypal";
    public static final String TYPE_SOFORT = "sofort";
    private static final long serialVersionUID = 5118382707515483788L;
    protected String mDisplayValue;
    protected boolean mEnabled;
    protected boolean mSelected;
    protected String mValue;

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (ResponseConstants.DISPLAY_VALUE.equals(str)) {
            this.mDisplayValue = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.VALUE.equals(str)) {
            this.mValue = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.SELECTED.equals(str)) {
            this.mSelected = jsonParser.getBooleanValue();
        } else if (!ResponseConstants.ENABLED.equals(str)) {
            return false;
        } else {
            this.mEnabled = jsonParser.getBooleanValue();
        }
        return true;
    }

    public String getDisplayValue() {
        return this.mDisplayValue;
    }

    public boolean isPayPal() {
        return TYPE_PAYPAL.equals(this.mValue);
    }

    public boolean isCreditCard() {
        return TYPE_CC.equals(this.mValue);
    }

    public boolean isAndroidPay() {
        return TYPE_ANDROID_PAY.equals(this.mValue);
    }

    public boolean isSelected() {
        return this.mSelected;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }
}
