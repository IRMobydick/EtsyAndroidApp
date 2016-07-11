package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.foresee.mobileReplay.perfLog.PerfDbContentProvider;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class OfferingRangeSelect extends BaseFieldModel {
    private static final long serialVersionUID = -8404457298185839947L;
    protected boolean mEnabled;
    protected String mLabel;
    protected int mMax;
    protected int mMin;
    protected int mStep;

    public OfferingRangeSelect() {
        this.mLabel = StringUtils.EMPTY;
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
            case 107876:
                if (str.equals(PerfDbContentProvider.COL_MAX)) {
                    z = true;
                    break;
                }
                break;
            case 108114:
                if (str.equals(PerfDbContentProvider.COL_MIN)) {
                    z = true;
                    break;
                }
                break;
            case 3540684:
                if (str.equals(ResponseConstants.STEP)) {
                    z = true;
                    break;
                }
                break;
            case 102727412:
                if (str.equals(ResponseConstants.LABEL)) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                this.mLabel = BaseModel.parseString(jsonParser);
                break;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                this.mEnabled = jsonParser.getValueAsBoolean();
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                this.mMin = jsonParser.getValueAsInt();
                break;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                this.mMax = jsonParser.getValueAsInt();
                break;
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                this.mStep = jsonParser.getValueAsInt();
                break;
            default:
                return false;
        }
        return true;
    }

    @NonNull
    public String getLabel() {
        return this.mLabel;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public int getMin() {
        return this.mMin;
    }

    public int getMax() {
        return this.mMax;
    }

    public int getStep() {
        return this.mStep;
    }

    public Integer[] getIntegerSequence() {
        List arrayList = new ArrayList();
        if (this.mMin <= this.mMax && this.mStep >= 0 && (this.mStep > 0 || this.mMin == this.mMax)) {
            int i = this.mMin;
            while (i < this.mMax) {
                arrayList.add(Integer.valueOf(i));
                i += this.mStep;
            }
            arrayList.add(Integer.valueOf(this.mMax));
        }
        return (Integer[]) arrayList.toArray(new Integer[arrayList.size()]);
    }
}
