package com.etsy.android.ui.cardview.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.apiv3.SearchGroup;
import com.etsy.android.uikit.util.TabletSupportUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ListingFullImageView;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.etsy.android.uikit.viewholder.a.b;
import java.util.List;

public class SearchGroupViewHolder extends BaseViewHolder<SearchGroup> {
    private final float WIDE_GROUP_IMAGE_ASPECT_LANDSCAPE;
    private final float WIDE_GROUP_IMAGE_ASPECT_PORTRAIT;
    private final ImageBatch mImageBatch;
    private final ListingFullImageView mImageView;
    private boolean mIsLandscape;
    private b mItemClickHandler;
    private final TextView mTitle;

    /* renamed from: com.etsy.android.ui.cardview.viewholders.SearchGroupViewHolder.1 */
    class C05671 extends TrackingOnClickListener {
        final /* synthetic */ SearchGroup f2312a;
        final /* synthetic */ SearchGroupViewHolder f2313b;

        C05671(SearchGroupViewHolder searchGroupViewHolder, SearchGroup searchGroup) {
            this.f2313b = searchGroupViewHolder;
            this.f2312a = searchGroup;
        }

        public void onViewClick(View view) {
            if (this.f2313b.mItemClickHandler != null) {
                this.f2313b.mItemClickHandler.a(this.f2312a);
            }
        }
    }

    public SearchGroupViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, b bVar, ImageBatch imageBatch) {
        super(layoutInflater.inflate(2130903300, viewGroup, z));
        this.WIDE_GROUP_IMAGE_ASPECT_LANDSCAPE = 0.45f;
        this.WIDE_GROUP_IMAGE_ASPECT_PORTRAIT = 0.38f;
        this.mItemClickHandler = bVar;
        this.mImageBatch = imageBatch;
        this.mTitle = (TextView) findViewById(R.txt_title);
        this.mImageView = (ListingFullImageView) findViewById(R.image);
        this.mIsLandscape = new TabletSupportUtil(getRootView().getContext()).m5624d();
    }

    public void bind(SearchGroup searchGroup) {
        this.mTitle.setText(searchGroup.getName());
        List images = searchGroup.getImages();
        if (images != null && images.size() > 0) {
            ListingImage listingImage = (ListingImage) images.get(0);
            float f = ListingFullImageView.ASPECT_RATIO_STANDARD;
            if (searchGroup.isWide() == 1 || (this.mIsLandscape && searchGroup.isWide() == 2)) {
                f = this.mIsLandscape ? 0.45f : 0.38f;
            }
            this.mImageView.setHeightRatio(f);
            this.mImageView.setImageInfo(listingImage, this.mImageBatch);
            this.itemView.setOnClickListener(new C05671(this, searchGroup));
        }
    }

    public void recycle() {
        this.mImageView.setImageDrawable(null);
    }
}
