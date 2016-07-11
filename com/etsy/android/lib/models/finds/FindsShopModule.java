package com.etsy.android.lib.models.finds;

import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.uikit.cardview.ICardViewElement;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class FindsShopModule extends FindsModule {
    protected void setFromGeneric(FindsModule findsModule) {
        super.setFromGeneric(findsModule);
        this.mShops = findsModule.mShops;
    }

    public List<ShopCard> getShops() {
        return this.mShops;
    }

    public List<? extends ICardViewElement> getCardViewElements() {
        return getShops();
    }

    public int getViewType() {
        return 5;
    }
}
