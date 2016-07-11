package com.etsy.android.uikit.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.models.ListingImage;

public class ListingImagesPagerAdapter extends ClickableImagesPagerAdapter<ListingImage> {
    public ListingImagesPagerAdapter(Activity activity, ImageBatch imageBatch, @NonNull ad adVar, ClickableImagesPagerAdapter clickableImagesPagerAdapter) {
        super(activity, adVar, R.imageview_loading, imageBatch, clickableImagesPagerAdapter);
    }
}
