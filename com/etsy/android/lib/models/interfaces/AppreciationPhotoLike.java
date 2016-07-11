package com.etsy.android.lib.models.interfaces;

import com.etsy.android.lib.models.ShortenedUrl;
import java.io.Serializable;

public interface AppreciationPhotoLike extends Serializable {
    String getListingTitle();

    String getShareImageUrl();

    String getShopName();

    ShortenedUrl getShortenedShareUrl();
}
