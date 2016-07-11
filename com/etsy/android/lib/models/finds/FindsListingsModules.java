package com.etsy.android.lib.models.finds;

import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.uikit.cardview.ICardViewElement;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class FindsListingsModules extends FindsModule {
    protected void setFromGeneric(FindsModule findsModule) {
        super.setFromGeneric(findsModule);
        this.mListings = findsModule.mListings;
    }

    public List<? extends ICardViewElement> getCardViewElements() {
        return getListings();
    }

    public List<ListingCard> getListings() {
        return this.mListings;
    }

    public int getViewType() {
        return 4;
    }
}
