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
public class CartGroup extends BaseFieldModel {
    private static final long serialVersionUID = 7464898652187627153L;
    protected List<CartGroupItem> mItems;
    protected List<CartGroupItem> mPaymentItems;

    public CartGroup() {
        this.mItems = new ArrayList();
        this.mPaymentItems = new ArrayList();
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (ResponseConstants.ITEMS.equals(str)) {
            insertCartGroupItems(jsonParser, this.mItems);
        } else if (!ResponseConstants.PAYMENT_ITEMS.equals(str)) {
            return false;
        } else {
            insertCartGroupItems(jsonParser, this.mPaymentItems);
        }
        return true;
    }

    private void insertCartGroupItems(JsonParser jsonParser, List<CartGroupItem> list) {
        for (CartGroupItem cartGroupItem : BaseModel.parseArray(jsonParser, CartGroupItem.class)) {
            if (cartGroupItem.getViewType() != -1) {
                list.add(cartGroupItem);
            }
        }
    }

    public List<CartGroupItem> getItems() {
        return this.mItems;
    }

    public List<CartGroupItem> getPaymentItems() {
        return this.mPaymentItems;
    }

    public int getViewType() {
        return 17;
    }
}
