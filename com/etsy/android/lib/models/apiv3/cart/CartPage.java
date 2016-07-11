package com.etsy.android.lib.models.apiv3.cart;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.EtsyAssociativeArray;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.cardviewelement.Page;
import com.etsy.android.lib.models.editable.EditableListing;
import com.fasterxml.jackson.core.JsonParser;
import org.parceler.Parcel;

@Parcel
public class CartPage extends Page {
    private static final long serialVersionUID = -7817382980234215930L;

    protected boolean parseField(JsonParser jsonParser, String str) {
        if (!ResponseConstants.METADATA.equals(str)) {
            return super.parseField(jsonParser, str);
        }
        this.mMetadata = BaseModel.parseObject(jsonParser, EtsyAssociativeArray.class);
        return true;
    }

    public int getSavedCount() {
        return getCount(ResponseConstants.SAVED_COUNT);
    }

    public int getCartCount() {
        return getCount(ResponseConstants.CART_COUNT);
    }

    private int getCount(String str) {
        if (this.mMetadata != null) {
            return Integer.parseInt(((EtsyAssociativeArray) this.mMetadata).optString(str, EditableListing.LISTING_ID_DEVICE_DRAFT));
        }
        return 0;
    }

    public boolean isSavedEnabled() {
        if (this.mMetadata != null) {
            return Boolean.parseBoolean(((EtsyAssociativeArray) this.mMetadata).getString(ResponseConstants.HAS_SAVED));
        }
        return false;
    }
}
