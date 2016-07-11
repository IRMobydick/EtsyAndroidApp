package com.etsy.android.lib.models.interfaces;

import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.SearchAdsMetadata;
import com.etsy.android.lib.models.datatypes.EtsyId;

public interface ListingLike extends BasicListingLike {
    BaseModelImage getListingImage();

    SearchAdsMetadata getSearchAdsMetadata();

    EtsyId getShopId();

    String getShopName();

    boolean hasCollections();

    boolean isAd();

    boolean isFavorite();

    void setHasCollections(boolean z);

    void setIsFavorite(boolean z);
}
