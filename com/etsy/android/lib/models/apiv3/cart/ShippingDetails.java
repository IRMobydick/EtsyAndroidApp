package com.etsy.android.lib.models.apiv3.cart;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class ShippingDetails extends BaseFieldModel {
    protected String mProcessingTimeText;
    protected String mSelectedOptionId;
    protected List<ShippingOption> mShippingOptions;

    public ShippingDetails() {
        this.mShippingOptions = new ArrayList();
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (ResponseConstants.PROCESSING_TIME_TEXT.equals(str)) {
            this.mProcessingTimeText = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.OPTIONS.equals(str)) {
            this.mShippingOptions = BaseModel.parseArray(jsonParser, ShippingOption.class);
        } else if (!ResponseConstants.SELECTED_OPTION_ID.equals(str)) {
            return false;
        } else {
            this.mSelectedOptionId = BaseModel.parseStringIdOrNumericValue(jsonParser);
        }
        return true;
    }

    public String getProcessingTimeText() {
        return this.mProcessingTimeText;
    }

    public List<ShippingOption> getShippingOptions() {
        return this.mShippingOptions;
    }

    public String getSelectedOptionId() {
        return this.mSelectedOptionId;
    }
}
