package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class OfferingOption extends BaseFieldModel {
    private static final long serialVersionUID = 6388231929113965279L;
    protected FormattedMoney mDisplayValue;
    protected boolean mEnabled;
    protected boolean mSelected;
    protected EtsyId mValue;

    public OfferingOption() {
        this.mValue = new EtsyId();
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        boolean z = true;
        switch (str.hashCode()) {
            case -1609594047:
                if (str.equals(ResponseConstants.ENABLED)) {
                    z = true;
                    break;
                }
                break;
            case -1464536140:
                if (str.equals(ResponseConstants.DISPLAY_VALUE)) {
                    z = false;
                    break;
                }
                break;
            case 111972721:
                if (str.equals(ResponseConstants.VALUE)) {
                    z = true;
                    break;
                }
                break;
            case 1191572123:
                if (str.equals(ResponseConstants.SELECTED)) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                this.mDisplayValue = (FormattedMoney) BaseModel.parseObject(jsonParser, FormattedMoney.class);
                break;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                this.mValue.setId(BaseModel.parseString(jsonParser));
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                this.mEnabled = jsonParser.getValueAsBoolean();
                break;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                this.mSelected = jsonParser.getValueAsBoolean();
                break;
            default:
                return false;
        }
        return true;
    }

    public FormattedMoney getDisplayValue() {
        return this.mDisplayValue;
    }

    @NonNull
    public String getFormattedDisplayValue() {
        return this.mDisplayValue != null ? this.mDisplayValue.toString() : StringUtils.EMPTY;
    }

    @NonNull
    public EtsyId getValue() {
        return this.mValue;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public boolean isSelected() {
        return this.mSelected;
    }

    public void setSelected(boolean z) {
        this.mSelected = z;
    }

    public boolean equals(Object obj) {
        return (obj instanceof OfferingOption) && ObjectUtils.equals(this.mDisplayValue, ((OfferingOption) obj).getDisplayValue()) && ObjectUtils.equals(this.mValue, ((OfferingOption) obj).getValue()) && ObjectUtils.equals(Boolean.valueOf(this.mEnabled), Boolean.valueOf(((OfferingOption) obj).isEnabled())) && ObjectUtils.equals(Boolean.valueOf(this.mSelected), Boolean.valueOf(((OfferingOption) obj).isSelected()));
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = (((ObjectUtils.hashCode(this.mDisplayValue) + 527) * 31) + ObjectUtils.hashCode(this.mValue)) * 31;
        if (this.mEnabled) {
            i = 0;
        } else {
            i = 1;
        }
        i = (i + hashCode) * 31;
        if (!this.mSelected) {
            i2 = 1;
        }
        return i + i2;
    }
}
