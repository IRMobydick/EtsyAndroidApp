package com.etsy.android.uikit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.shopshare.ShareAnnotation;
import com.etsy.android.lib.util.fonts.StandardFontIcon;

/* renamed from: com.etsy.android.uikit.adapter.c */
class AnnotationListAdapter extends ViewHolder {
    final /* synthetic */ AnnotationListAdapter f3924a;
    private final ImageView f3925b;
    private final TextView f3926c;
    private final IconView f3927d;
    private final View f3928e;

    public AnnotationListAdapter(AnnotationListAdapter annotationListAdapter, View view) {
        this.f3924a = annotationListAdapter;
        super(view);
        this.f3925b = (ImageView) view.findViewById(R.listing_image);
        this.f3926c = (TextView) view.findViewById(R.listing_title);
        this.f3927d = (IconView) view.findViewById(R.delete_icon);
        this.f3928e = view.findViewById(R.delete_button);
    }

    public void m5307a(AnnotationListAdapter annotationListAdapter, int i, Context context, ImageBatch imageBatch) {
        Listing b = annotationListAdapter.m5297b();
        ShareAnnotation a = annotationListAdapter.m5296a();
        if (b != null) {
            this.f3926c.setText(b.getTitle());
            ListingImage image = b.getImage();
            if (image != null) {
                imageBatch.m1568a(image.getImageUrlForPixelWidth(context.getResources().getDimensionPixelSize(R.shop_share_annotation_listing_image_size)), this.f3925b);
            }
        }
        if (this.f3924a.mOnClickListener != null) {
            this.f3927d.setIcon(StandardFontIcon.DELETE);
            this.f3927d.setColor(context.getResources().getColor(R.light_grey));
            this.f3928e.setOnClickListener(this.f3924a.mOnClickListener);
            if (a != null) {
                this.f3928e.setTag(Long.valueOf(a.getShareAnnotationId()));
                return;
            }
            return;
        }
        this.f3927d.setVisibility(4);
        this.f3928e.setVisibility(4);
    }
}
