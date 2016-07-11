package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class OfferingPrice extends BaseFieldModel {
    private static final long serialVersionUID = 1557745291892004692L;
    protected String mCurrencyCode;
    protected String mCurrencyFormattedLong;
    protected String mCurrencyFormattedRaw;
    protected String mCurrencyFormattedShort;

    public OfferingPrice() {
        this.mCurrencyCode = StringUtils.EMPTY;
        this.mCurrencyFormattedRaw = StringUtils.EMPTY;
        this.mCurrencyFormattedLong = StringUtils.EMPTY;
        this.mCurrencyFormattedShort = StringUtils.EMPTY;
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        boolean z = true;
        switch (str.hashCode()) {
            case -651122057:
                if (str.equals(ResponseConstants.CURRENCY_FORMATTED_RAW)) {
                    z = true;
                    break;
                }
                break;
            case 1108728155:
                if (str.equals(ResponseConstants.CURRENCY_CODE)) {
                    z = false;
                    break;
                }
                break;
            case 1289887245:
                if (str.equals(ResponseConstants.CURRENCY_FORMATTED_LONG)) {
                    z = true;
                    break;
                }
                break;
            case 1338056459:
                if (str.equals(ResponseConstants.CURRENCY_FORMATTED_SHORT)) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                this.mCurrencyCode = BaseModel.parseString(jsonParser);
                break;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                this.mCurrencyFormattedRaw = BaseModel.parseString(jsonParser);
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                this.mCurrencyFormattedLong = BaseModel.parseString(jsonParser);
                break;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                this.mCurrencyFormattedShort = BaseModel.parseString(jsonParser);
                break;
            default:
                return false;
        }
        return true;
    }

    @NonNull
    public String getCurrencyCode() {
        return this.mCurrencyCode;
    }

    @NonNull
    public String getCurrencyFormattedRaw() {
        return this.mCurrencyFormattedRaw;
    }

    @NonNull
    public String getCurrencyFormattedLong() {
        return this.mCurrencyFormattedLong;
    }

    @NonNull
    public String getCurrencyFormattedShort() {
        return this.mCurrencyFormattedShort;
    }
}
