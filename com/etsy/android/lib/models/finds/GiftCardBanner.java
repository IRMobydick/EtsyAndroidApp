package com.etsy.android.lib.models.finds;

import com.etsy.android.lib.config.EtsyFeatureFlags;
import com.etsy.android.uikit.cardview.ICardViewElement;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class GiftCardBanner extends FindsModule {
    public int getViewType() {
        return 37;
    }

    public String getCta() {
        return this.mCta;
    }

    protected void setFromGeneric(FindsModule findsModule) {
        this.mText = findsModule.getText();
        this.mGiftCardBannerImage = findsModule.getGiftCardBannerImages();
        this.mCta = findsModule.getCta();
    }

    public List<? extends ICardViewElement> getCardViewElements() {
        List arrayList = new ArrayList();
        if (EtsyFeatureFlags.m915b()) {
            arrayList.add(this);
        }
        return arrayList;
    }
}
