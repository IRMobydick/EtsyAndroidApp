package com.etsy.android.lib.models.interfaces;

import com.etsy.android.lib.models.datatypes.EtsyId;
import java.io.Serializable;

public interface BasicShopLike extends Serializable {
    double getAverageRating();

    int getNumRatings();

    EtsyId getShopId();

    String getShopName();

    EtsyId getUserId();
}
