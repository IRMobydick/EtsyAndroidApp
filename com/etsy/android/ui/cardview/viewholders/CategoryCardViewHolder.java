package com.etsy.android.ui.cardview.viewholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.BrowseImageUrl;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.Segment;
import com.etsy.android.lib.models.apiv3.SearchGroup;
import com.etsy.android.lib.util.ab;
import com.etsy.android.ui.cardview.CardViewHolderFactory;
import com.etsy.android.uikit.cardview.ICardViewElement;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.etsy.android.uikit.viewholder.a.b;
import java.util.List;

public class CategoryCardViewHolder extends BaseViewHolder<ICardViewElement> {
    private static final float FEED_IMAGE_HEIGHT_RATIO = 0.75f;
    public View mBorder;
    private final ImageBatch mImageBatch;
    public int mImageHeight;
    public ImageView mImageView;
    public int mImageWidth;
    private final b mItemClickHandler;
    public TextView mTextShopName;
    public TextView mTextTitle;

    /* renamed from: com.etsy.android.ui.cardview.viewholders.CategoryCardViewHolder.1 */
    class C05421 extends TrackingOnClickListener {
        final /* synthetic */ Segment f2264a;
        final /* synthetic */ CategoryCardViewHolder f2265b;

        C05421(CategoryCardViewHolder categoryCardViewHolder, Segment segment) {
            this.f2265b = categoryCardViewHolder;
            this.f2264a = segment;
        }

        public void onViewClick(View view) {
            if (this.f2265b.mItemClickHandler != null) {
                this.f2265b.mItemClickHandler.a(this.f2264a);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.cardview.viewholders.CategoryCardViewHolder.2 */
    class C05432 extends TrackingOnClickListener {
        final /* synthetic */ SearchGroup f2266a;
        final /* synthetic */ CategoryCardViewHolder f2267b;

        C05432(CategoryCardViewHolder categoryCardViewHolder, SearchGroup searchGroup) {
            this.f2267b = categoryCardViewHolder;
            this.f2266a = searchGroup;
        }

        public void onViewClick(View view) {
            if (this.f2267b.mItemClickHandler != null) {
                this.f2267b.mItemClickHandler.a(this.f2266a);
            }
        }
    }

    public CategoryCardViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, b bVar, ImageBatch imageBatch, boolean z) {
        super(layoutInflater.inflate(2130903288, viewGroup, false));
        this.mImageBatch = imageBatch;
        this.mItemClickHandler = bVar;
        this.mBorder = findViewById(2131755934);
        this.mTextTitle = (TextView) findViewById(R.txt_title);
        this.mImageView = (ImageView) findViewById(2131755936);
        this.mTextShopName = (TextView) findViewById(2131755688);
        Context context = this.itemView.getContext();
        if (z) {
            this.mImageWidth = context.getResources().getDimensionPixelSize(2131361895);
        } else {
            this.mImageWidth = new ab(getRootView().getContext()).m3182e() / CardViewHolderFactory.m3646a(layoutInflater.getContext());
        }
        this.mImageHeight = (int) (((float) this.mImageWidth) * FEED_IMAGE_HEIGHT_RATIO);
        this.mImageView.getLayoutParams().height = this.mImageHeight;
        this.mImageView.getLayoutParams().width = this.mImageWidth;
        this.itemView.getLayoutParams().width = this.mImageWidth;
    }

    public void bind(ICardViewElement iCardViewElement) {
        if (iCardViewElement instanceof Segment) {
            Segment segment = (Segment) iCardViewElement;
            super.bind(segment);
            this.mTextTitle.setText(segment.getDisplayName());
            this.mImageBatch.m1570a(new BrowseImageUrl(segment.getImageUrl()).getImageUrlForPixelWidth(this.mImageWidth), this.mImageView, this.mImageWidth, this.mImageHeight);
            this.itemView.setOnClickListener(new C05421(this, segment));
        } else if (iCardViewElement instanceof SearchGroup) {
            SearchGroup searchGroup = (SearchGroup) iCardViewElement;
            this.mTextTitle.setText(searchGroup.getName());
            List images = searchGroup.getImages();
            if (!(images == null || images.isEmpty())) {
                ListingImage listingImage = (ListingImage) images.get(0);
                this.mImageBatch.m1571a(listingImage.getImageUrl(), this.mImageView, this.mImageWidth, this.mImageHeight, listingImage.getImageColor());
            }
            this.itemView.setOnClickListener(new C05432(this, searchGroup));
        }
    }

    public void recycle() {
        this.mImageView.setImageDrawable(null);
    }
}
