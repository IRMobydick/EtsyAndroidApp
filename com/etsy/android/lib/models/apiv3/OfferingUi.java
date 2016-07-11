package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class OfferingUi extends BaseFieldModel {
    private static final long serialVersionUID = -6968719256342293496L;
    protected boolean mHasVariableQuantity;
    protected FormattedMoney mPrice;
    protected OfferingRangeSelect mQuantity;
    protected List<OfferingSelect> mSelects;

    public OfferingUi() {
        this.mSelects = new ArrayList();
        this.mHasVariableQuantity = true;
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        boolean z = true;
        switch (str.hashCode()) {
            case -1285004149:
                if (str.equals(ResponseConstants.QUANTITY)) {
                    z = true;
                    break;
                }
                break;
            case -1035298679:
                if (str.equals(ResponseConstants.HAS_VARIABLE_QUANTITY)) {
                    z = true;
                    break;
                }
                break;
            case 106934601:
                if (str.equals(ResponseConstants.PRICE)) {
                    z = true;
                    break;
                }
                break;
            case 1978100471:
                if (str.equals(ResponseConstants.SELECTS)) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                this.mSelects = BaseModel.parseArray(jsonParser, OfferingSelect.class);
                break;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                this.mPrice = (FormattedMoney) BaseModel.parseObject(jsonParser, FormattedMoney.class);
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                this.mQuantity = (OfferingRangeSelect) BaseModel.parseObject(jsonParser, OfferingRangeSelect.class);
                break;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                this.mHasVariableQuantity = jsonParser.getValueAsBoolean();
                break;
            default:
                return false;
        }
        return true;
    }

    @NonNull
    public List<OfferingSelect> getSelects() {
        return this.mSelects;
    }

    public FormattedMoney getPrice() {
        return this.mPrice;
    }

    @NonNull
    public String getFormattedPrice() {
        return this.mPrice != null ? this.mPrice.toString() : StringUtils.EMPTY;
    }

    public OfferingRangeSelect getQuantity() {
        return this.mQuantity;
    }

    public boolean hasVariableQuantity() {
        return this.mHasVariableQuantity;
    }
}
