package com.etsy.android.lib.models.interfaces;

import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.uikit.cardview.ICardViewElement;
import java.io.Serializable;

public interface BasicListingLike extends ICardViewElement, Serializable {
    EtsyId getListingId();

    BaseModelImage getListingImage();

    EtsyMoney getPrice();

    String getTitle();

    String getUrl();

    boolean isAd();

    boolean isFundOnEtsyCampaign();
}
