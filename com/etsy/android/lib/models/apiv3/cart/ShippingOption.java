package com.etsy.android.lib.models.apiv3.cart;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.FormattedMoney;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ShippingOption extends BaseFieldModel {
    private static final long serialVersionUID = -5096706812116398703L;
    protected FormattedMoney mFormattedMoney;
    protected String mOptionId;

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (ResponseConstants.OPTION_ID.equals(str)) {
            this.mOptionId = BaseModel.parseStringIdOrNumericValue(jsonParser);
        } else if (ResponseConstants.FORMATTED_MONEY.equals(str)) {
            this.mFormattedMoney = (FormattedMoney) BaseModel.parseObject(jsonParser, FormattedMoney.class);
        }
        return false;
    }

    public String getTitle() {
        if (this.mFormattedMoney != null) {
            return this.mFormattedMoney.toString();
        }
        return StringUtils.EMPTY;
    }

    public String getOptionId() {
        return this.mOptionId;
    }

    public String toString() {
        return getTitle();
    }
}
