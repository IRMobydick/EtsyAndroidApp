package com.etsy.android.lib.models.finds;

import com.etsy.android.uikit.cardview.ICardViewElement;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class FindsCategoryModule extends FindsModule {
    protected void setFromGeneric(FindsModule findsModule) {
        super.setFromGeneric(findsModule);
        this.mCategories = findsModule.mCategories;
    }

    public List<? extends ICardViewElement> getCardViewElements() {
        return getCategories();
    }

    public List<FindsSearchCategory> getCategories() {
        return this.mCategories;
    }
}
