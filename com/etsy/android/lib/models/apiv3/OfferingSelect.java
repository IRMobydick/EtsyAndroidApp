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
public class OfferingSelect extends BaseFieldModel {
    private static final long serialVersionUID = 6972195848896814232L;
    protected OfferingOption mDefaultOption;
    protected boolean mEnabled;
    protected String mLabel;
    protected List<OfferingOption> mOptions;
    protected String mPrompt;

    public OfferingSelect() {
        this.mLabel = StringUtils.EMPTY;
        this.mPrompt = StringUtils.EMPTY;
        this.mOptions = new ArrayList();
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
            case -1249474914:
                if (str.equals(ResponseConstants.OPTIONS)) {
                    z = true;
                    break;
                }
                break;
            case -979805852:
                if (str.equals(ResponseConstants.PROMPT)) {
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
            case 2037797939:
                if (str.equals(ResponseConstants.DEFAULT_OPTION)) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                this.mLabel = BaseModel.parseString(jsonParser);
                break;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                this.mPrompt = BaseModel.parseString(jsonParser);
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                this.mEnabled = jsonParser.getValueAsBoolean();
                break;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                this.mOptions = BaseModel.parseArray(jsonParser, OfferingOption.class);
                break;
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                this.mDefaultOption = (OfferingOption) BaseModel.parseObject(jsonParser, OfferingOption.class);
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

    @NonNull
    public String getPrompt() {
        return this.mPrompt;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    @NonNull
    public List<OfferingOption> getOptions() {
        return this.mOptions;
    }

    public OfferingOption getDefaultOption() {
        return this.mDefaultOption;
    }

    public OfferingOption getSelectedOption() {
        for (OfferingOption offeringOption : this.mOptions) {
            if (offeringOption.isSelected()) {
                return offeringOption;
            }
        }
        return null;
    }

    public int getSelectedOptionIndex() {
        for (int i = 0; i < this.mOptions.size(); i++) {
            if (((OfferingOption) this.mOptions.get(i)).isSelected()) {
                return i;
            }
        }
        return -1;
    }

    public boolean hasSelectedOption() {
        return getSelectedOption() != null;
    }
}
