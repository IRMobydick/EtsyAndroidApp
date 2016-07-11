package com.etsy.android.uikit.adapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.core.img.ImageDownloadRequest;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class ClickableImagesPagerAdapter<T extends BaseModelImage> extends ImagesPagerAdapter<T> {
    private ClickableImagesPagerAdapter mOnImageClickListener;

    /* renamed from: com.etsy.android.uikit.adapter.ClickableImagesPagerAdapter.1 */
    class C09241 extends TrackingOnClickListener {
        final /* synthetic */ int f3916a;
        final /* synthetic */ ClickableImagesPagerAdapter f3917b;

        C09241(ClickableImagesPagerAdapter clickableImagesPagerAdapter, int i) {
            this.f3917b = clickableImagesPagerAdapter;
            this.f3916a = i;
        }

        public void onViewClick(View view) {
            if (this.f3917b.mOnImageClickListener != null) {
                this.f3917b.mOnImageClickListener.onImageClick(this.f3916a);
            }
        }
    }

    public ClickableImagesPagerAdapter(Activity activity, @NonNull AnalyticsTracker analyticsTracker, @LayoutRes int i, ImageBatch imageBatch, ClickableImagesPagerAdapter clickableImagesPagerAdapter) {
        super(activity, analyticsTracker, i, imageBatch);
        this.mOnImageClickListener = clickableImagesPagerAdapter;
    }

    public void setOnImageClickListener(ClickableImagesPagerAdapter clickableImagesPagerAdapter) {
        this.mOnImageClickListener = clickableImagesPagerAdapter;
    }

    protected ImageView setUpViewAndReturnImageView(View view, int i) {
        view.setOnClickListener(new C09241(this, i));
        ImageView upViewAndReturnImageView = super.setUpViewAndReturnImageView(view, i);
        upViewAndReturnImageView.setContentDescription(((Activity) this.mActivity.get()).getResources().getString(R.listing_image_content_description));
        return upViewAndReturnImageView;
    }

    protected ImageDownloadRequest getImageDownloadRequest(String str, View view, ImageView imageView, View view2, int i, T t) {
        ImageDownloadRequest imageDownloadRequest = super.getImageDownloadRequest(str, view, imageView, view2, i, t);
        imageDownloadRequest.m1602d(t.getImageColor());
        return imageDownloadRequest;
    }
}
