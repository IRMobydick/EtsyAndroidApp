package com.etsy.android.lib.models.interfaces;

import com.etsy.android.lib.models.LocalMarket;
import java.util.List;

public interface ShopLike extends BasicShopLike {
    String getAvatarUrl();

    List<? extends ListingLike> getCardListings();

    String getHeadline();

    String getIconUrl(int i);

    String getLocation();

    LocalMarket getUpcomingLocalEvent();

    String getUrl();

    boolean hasRatings();

    boolean hasUpcomingLocalEvent();
}
