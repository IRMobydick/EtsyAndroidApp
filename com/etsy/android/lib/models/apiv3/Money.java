package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.google.android.gms.gcm.Task;
import java.util.Currency;
import org.apache.commons.lang3.ObjectUtils;
import org.parceler.Parcel;

@Parcel
public class Money extends BaseFieldModel {
    private static final long serialVersionUID = 1230625102449803824L;
    protected String mAmount;
    protected String mCurrencyCode;
    protected int mDivisor;
    protected EtsyMoney mEtsyMoney;

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        boolean z = true;
        switch (str.hashCode()) {
            case -1413853096:
                if (str.equals(ResponseConstants.AMOUNT)) {
                    z = false;
                    break;
                }
                break;
            case 1108728155:
                if (str.equals(ResponseConstants.CURRENCY_CODE)) {
                    z = true;
                    break;
                }
                break;
            case 1674333342:
                if (str.equals(ResponseConstants.DIVISOR)) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                this.mAmount = BaseModel.parseString(jsonParser);
                break;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                this.mDivisor = jsonParser.getValueAsInt();
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                this.mCurrencyCode = BaseModel.parseString(jsonParser);
                break;
            default:
                return false;
        }
        return true;
    }

    public String getAmount() {
        return this.mAmount;
    }

    public int getDivisor() {
        return this.mDivisor;
    }

    public String getCurrencyCode() {
        return this.mCurrencyCode;
    }

    public String toString() {
        return asEtsyMoney().format();
    }

    public EtsyMoney asEtsyMoney() {
        if (this.mEtsyMoney == null) {
            this.mEtsyMoney = EtsyMoney.getInstanceWithAmountOfFraction(Currency.getInstance(this.mCurrencyCode), this.mAmount, this.mDivisor);
        }
        return this.mEtsyMoney;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Money) && ObjectUtils.equals(this.mAmount, ((Money) obj).getAmount()) && this.mDivisor == ((Money) obj).getDivisor() && ObjectUtils.equals(this.mCurrencyCode, ((Money) obj).getCurrencyCode());
    }

    public int hashCode() {
        return ((((ObjectUtils.hashCode(this.mAmount) + 527) * 31) + this.mDivisor) * 31) + ObjectUtils.hashCode(this.mCurrencyCode);
    }
}
