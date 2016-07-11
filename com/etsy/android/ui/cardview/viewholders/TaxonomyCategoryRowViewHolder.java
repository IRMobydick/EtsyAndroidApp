package com.etsy.android.ui.cardview.viewholders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.Segment;
import com.etsy.android.lib.models.apiv3.TaxonomyCategory;
import com.etsy.android.ui.search.v2.an;
import com.etsy.android.uikit.cardview.ICardViewElement;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.etsy.android.uikit.viewholder.a.b;
import java.util.List;

public class TaxonomyCategoryRowViewHolder extends BaseViewHolder<ICardViewElement> {
    b<ICardViewElement> mClickListener;
    private int mIconSize;
    ImageBatch mImageBatch;
    private ImageView mImageView;
    private TextView mTextView;

    /* renamed from: com.etsy.android.ui.cardview.viewholders.TaxonomyCategoryRowViewHolder.1 */
    class C05771 extends TrackingOnClickListener {
        final /* synthetic */ Segment f2335a;
        final /* synthetic */ TaxonomyCategoryRowViewHolder f2336b;

        C05771(TaxonomyCategoryRowViewHolder taxonomyCategoryRowViewHolder, ITrackedObject[] iTrackedObjectArr, Segment segment) {
            this.f2336b = taxonomyCategoryRowViewHolder;
            this.f2335a = segment;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (this.f2336b.mClickListener != null) {
                this.f2336b.mClickListener.a(this.f2335a);
            }
        }
    }

    /* renamed from: com.etsy.android.ui.cardview.viewholders.TaxonomyCategoryRowViewHolder.2 */
    class C05782 extends TrackingOnClickListener {
        final /* synthetic */ TaxonomyCategory f2337a;
        final /* synthetic */ TaxonomyCategoryRowViewHolder f2338b;

        C05782(TaxonomyCategoryRowViewHolder taxonomyCategoryRowViewHolder, ITrackedObject[] iTrackedObjectArr, TaxonomyCategory taxonomyCategory) {
            this.f2338b = taxonomyCategoryRowViewHolder;
            this.f2337a = taxonomyCategory;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (this.f2338b.mClickListener != null) {
                this.f2338b.mClickListener.a(this.f2337a);
            }
        }
    }

    public TaxonomyCategoryRowViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, b<ICardViewElement> bVar, ImageBatch imageBatch) {
        super(layoutInflater.inflate(2130903364, viewGroup, z));
        this.mClickListener = bVar;
        this.mImageBatch = imageBatch;
        this.mImageView = (ImageView) this.itemView.findViewById(2131755898);
        this.mTextView = (TextView) this.itemView.findViewById(2131755899);
        this.mIconSize = viewGroup.getResources().getDimensionPixelSize(2131362142);
    }

    public void bind(ICardViewElement iCardViewElement) {
        ImageView imageView = this.mImageView;
        int i = this.mIconSize;
        if (iCardViewElement instanceof Segment) {
            Segment segment = (Segment) iCardViewElement;
            this.mTextView.setText(segment.getDisplayName());
            this.mImageBatch.m1570a(segment.getImageUrl(), imageView, i, i);
            this.itemView.setOnClickListener(new C05771(this, new ITrackedObject[]{segment}, segment));
        } else if (iCardViewElement instanceof TaxonomyCategory) {
            TaxonomyCategory taxonomyCategory = (TaxonomyCategory) iCardViewElement;
            this.mTextView.setText(taxonomyCategory.getName());
            imageView.setImageDrawable(null);
            Object path = taxonomyCategory.getPath();
            if (!TextUtils.isEmpty(path)) {
                int a = an.m4887a(path);
                if (a != R.bg_empty_image) {
                    imageView.setImageResource(a);
                    ((MarginLayoutParams) imageView.getLayoutParams()).setMargins(0, 0, 0, 0);
                    imageView.getLayoutParams().width = -2;
                    imageView.getLayoutParams().height = -2;
                }
            }
            List images = taxonomyCategory.getImages();
            if (images != null && images.size() > 0) {
                ListingImage listingImage = (ListingImage) images.get(0);
                this.mImageBatch.m1571a(listingImage.getImageUrlForPixelWidth(i), imageView, i, i, listingImage.getImageColor());
            }
            this.itemView.setOnClickListener(new C05782(this, new ITrackedObject[]{taxonomyCategory}, taxonomyCategory));
        }
    }

    public void recycle() {
        this.mImageView.setImageDrawable(null);
    }
}
