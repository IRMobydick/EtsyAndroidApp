package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.google.android.gms.gcm.Task;
import org.parceler.Parcel;

@Parcel
public class ShopIconV3 extends Image {
    EtsyId mImageId;

    public ShopIconV3() {
        this.mImageId = new EtsyId();
    }

    public EtsyId getImageId() {
        return this.mImageId;
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        Object obj = -1;
        switch (str.hashCode()) {
            case -859601281:
                if (str.equals(ResponseConstants.IMAGE_ID)) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                this.mImageId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                return true;
            default:
                return super.parseField(jsonParser, str);
        }
    }
}
