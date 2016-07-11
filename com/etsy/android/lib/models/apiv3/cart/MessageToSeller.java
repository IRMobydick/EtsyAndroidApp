package com.etsy.android.lib.models.apiv3.cart;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.requests.CartsRequest;
import com.fasterxml.jackson.core.JsonParser;
import org.parceler.Parcel;

@Parcel
public class MessageToSeller extends BaseFieldModel {
    protected String mHint;
    protected String mMessage;

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (CartsRequest.PARAM_NOTE_TO_SELLER.equals(str)) {
            this.mMessage = BaseModel.parseString(jsonParser);
        } else if (!ResponseConstants.HINT.equals(str)) {
            return false;
        } else {
            this.mHint = BaseModel.parseString(jsonParser);
        }
        return true;
    }

    public String getHint() {
        return this.mHint;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public void setMessage(String str) {
        this.mMessage = str;
    }
}
