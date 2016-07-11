package com.etsy.android.lib.models.apiv3.cart;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import org.parceler.Parcel;

@Parcel
public class PaymentAddCoupon extends BaseFieldModel {
    private static final long serialVersionUID = 3134518244101605247L;
    protected String mCode;
    protected String mError;

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (ResponseConstants.CODE.equals(str)) {
            this.mCode = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.ERROR_MESSAGE.equals(str)) {
            this.mError = BaseModel.parseString(jsonParser);
        }
        return true;
    }

    @Nullable
    public String getCode() {
        return this.mCode;
    }

    @Nullable
    public String getError() {
        return this.mError;
    }
}
