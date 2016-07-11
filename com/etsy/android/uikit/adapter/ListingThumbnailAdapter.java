package com.etsy.android.uikit.adapter;

import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.ListingImage;

public class ListingThumbnailAdapter extends BaseModelArrayAdapter<BaseModelImage> {
    private static final String TAG;
    private final int mThumbHeight;
    private final int mThumbWidth;

    static {
        TAG = EtsyDebug.m1891a(ListingThumbnailAdapter.class);
    }

    public ListingThumbnailAdapter(FragmentActivity fragmentActivity, ImageBatch imageBatch) {
        super(fragmentActivity, R.list_item_listing_thumbnail, imageBatch);
        Resources resources = fragmentActivity.getResources();
        this.mThumbHeight = resources.getDimensionPixelSize(R.listing_thumbnail_height);
        this.mThumbWidth = resources.getDimensionPixelSize(R.listing_thumbnail_width);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2 = 0;
        if (view == null) {
            view = getLayoutInflater().inflate(getLayoutId(), viewGroup, false);
        }
        ImageView imageView = (ImageView) view.findViewById(R.img_thumbnail);
        BaseModelImage baseModelImage = (BaseModelImage) getItem(i);
        if (baseModelImage != null) {
            if (baseModelImage instanceof ListingImage) {
                i2 = baseModelImage.getImageColor();
            }
            getImageBatch().m1571a(baseModelImage.getImageUrl().replace("570xN", "170x135"), imageView, this.mThumbWidth, this.mThumbHeight, i2);
        }
        return view;
    }
}
