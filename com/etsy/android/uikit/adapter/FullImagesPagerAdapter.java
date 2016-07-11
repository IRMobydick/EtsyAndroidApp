package com.etsy.android.uikit.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.TouchImageView;
import java.util.List;

public class FullImagesPagerAdapter extends ClickableImagesPagerAdapter<BaseModelImage> {
    private TouchImageView[] mZoomViews;

    /* renamed from: com.etsy.android.uikit.adapter.FullImagesPagerAdapter.1 */
    class C09261 extends TrackingOnClickListener {
        final /* synthetic */ String f3918a;
        final /* synthetic */ FullImagesPagerAdapter f3919b;

        C09261(FullImagesPagerAdapter fullImagesPagerAdapter, AnalyticsLogAttribute analyticsLogAttribute, Object obj, String str) {
            this.f3919b = fullImagesPagerAdapter;
            this.f3918a = str;
            super(analyticsLogAttribute, obj);
        }

        public void onViewClick(View view) {
            ((FullImagesPagerAdapter) this.f3919b.mActivity.get()).onViewUnsupportedImage(this.f3918a);
        }
    }

    public FullImagesPagerAdapter(Activity activity, ImageBatch imageBatch, @NonNull ad adVar, ClickableImagesPagerAdapter clickableImagesPagerAdapter) {
        super(activity, adVar, R.imageview_loading_zoom, imageBatch, clickableImagesPagerAdapter);
    }

    public void resetZoomAtPosition(int i) {
        if (i >= 0 && this.mZoomViews.length > i && this.mZoomViews[i] != null) {
            this.mZoomViews[i].resetZoom();
        }
    }

    public void setImages(List list) {
        int i = 0;
        if (list != null) {
            i = list.size();
        }
        this.mZoomViews = new TouchImageView[i];
        super.setImages(list);
    }

    protected ImageView setUpViewAndReturnImageView(View view, int i) {
        ImageView upViewAndReturnImageView = super.setUpViewAndReturnImageView(view, i);
        if (upViewAndReturnImageView instanceof TouchImageView) {
            this.mZoomViews[i] = (TouchImageView) upViewAndReturnImageView;
        }
        return upViewAndReturnImageView;
    }

    protected void onUnsupportedImageShown(View view, View view2, String str) {
        super.onUnsupportedImageShown(view, view2, str);
        view.findViewById(R.unsupported_view).setVisibility(0);
        View findViewById = view.findViewById(R.view_btn);
        if (this.mActivity.get() instanceof FullImagesPagerAdapter) {
            findViewById.setOnClickListener(new C09261(this, AnalyticsLogAttribute.URL, str, str));
        } else {
            findViewById.setVisibility(8);
        }
    }
}
