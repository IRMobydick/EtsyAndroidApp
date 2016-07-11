package com.etsy.android.ui.finds.cardview;

import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.ui.cardview.p014a.ShopCardClickHandler;
import com.etsy.android.ui.cardview.viewholders.ShopCardViewHolder;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.GuardianTextView;
import com.etsy.android.uikit.view.ImageViewWithAspectRatio;
import com.etsy.android.uikit.view.RatingIconView;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class GridShopCardViewHolder extends BaseViewHolder<ShopCard> {
    private ImageViewWithAspectRatio[] imageViews;
    private ShopCardClickHandler mClickListener;
    private ImageView mFavButton;
    private ImageBatch mImageBatch;
    private GridLayout mImageGrid;
    private TextView mItemCount;
    private ShopCardViewHolder mReviewViewHolder;
    private ImageView mShopAvatar;
    private GuardianTextView mShopName;

    /* renamed from: com.etsy.android.ui.finds.cardview.GridShopCardViewHolder.1 */
    class C06981 extends TrackingOnClickListener {
        final /* synthetic */ ShopCard f2978a;
        final /* synthetic */ GridShopCardViewHolder f2979b;

        C06981(GridShopCardViewHolder gridShopCardViewHolder, ITrackedObject[] iTrackedObjectArr, ShopCard shopCard) {
            this.f2979b = gridShopCardViewHolder;
            this.f2978a = shopCard;
            super(iTrackedObjectArr);
        }

        public void onViewClick(View view) {
            if (this.f2979b.mClickListener != null) {
                this.f2979b.mClickListener.m3619a(this.f2978a, this.f2979b.mFavButton, this.f2978a.isFavorite());
            }
        }
    }

    /* renamed from: com.etsy.android.ui.finds.cardview.GridShopCardViewHolder.2 */
    class C06992 extends TrackingOnClickListener {
        final /* synthetic */ ShopCard f2980a;
        final /* synthetic */ GridShopCardViewHolder f2981b;

        C06992(GridShopCardViewHolder gridShopCardViewHolder, ShopCard shopCard) {
            this.f2981b = gridShopCardViewHolder;
            this.f2980a = shopCard;
        }

        public void onViewClick(View view) {
            if (this.f2981b.mClickListener != null) {
                this.f2981b.mClickListener.m3618a(this.f2980a);
            }
        }
    }

    public GridShopCardViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, ShopCardClickHandler shopCardClickHandler, ImageBatch imageBatch, boolean z) {
        super(layoutInflater.inflate(2130903282, viewGroup, false));
        this.imageViews = new ImageViewWithAspectRatio[]{(ImageViewWithAspectRatio) findViewById(2131755920), (ImageViewWithAspectRatio) findViewById(2131755921), (ImageViewWithAspectRatio) findViewById(2131755922), (ImageViewWithAspectRatio) findViewById(2131755923)};
        this.mImageBatch = imageBatch;
        this.mShopAvatar = (ImageView) findViewById(R.shop_avatar);
        this.mShopName = (GuardianTextView) findViewById(R.shop_name);
        this.mReviewViewHolder = new ShopCardViewHolder(layoutInflater.getContext(), (RatingIconView) findViewById(R.rating), (TextView) findViewById(2131755926));
        this.mFavButton = (ImageView) findViewById(R.btn_fav);
        this.mItemCount = (TextView) findViewById(2131755924);
        this.mClickListener = shopCardClickHandler;
        this.mImageGrid = (GridLayout) findViewById(2131755919);
        if (z) {
            this.mImageGrid.setRowCount(2);
            this.mImageGrid.setColumnCount(2);
            getRootView().getLayoutParams().width = this.itemView.getResources().getDimensionPixelSize(2131361898);
        }
    }

    public void bind(ShopCard shopCard) {
        int i = 0;
        while (i < shopCard.getCardListings().size() && i < this.imageViews.length) {
            this.imageViews[i].setImageInfo(((ListingLike) shopCard.getCardListings().get(i)).getListingImage(), this.mImageBatch);
            this.imageViews[i].setUseStandardRatio(true);
            i++;
        }
        this.mShopName.setText(shopCard.getShopName());
        this.mReviewViewHolder.m3661a(shopCard);
        this.mFavButton.setImageResource(shopCard.isFavorite() ? R.ic_listing_favorite_selector : R.ic_listing_unfavorite_selector);
        this.mFavButton.setOnClickListener(new C06981(this, new ITrackedObject[]{shopCard}, shopCard));
        this.mImageBatch.m1568a(shopCard.getAvatarUrl(), this.mShopAvatar);
        this.mItemCount.setText(getRootView().getContext().getResources().getQuantityString(R.item_lowercase_quantity, r0, new Object[]{Integer.valueOf(shopCard.getActiveListingCount())}));
        getRootView().setOnClickListener(new C06992(this, shopCard));
    }

    public void recycle() {
        for (ImageViewWithAspectRatio imageDrawable : this.imageViews) {
            imageDrawable.setImageDrawable(null);
        }
        this.mShopAvatar.setImageDrawable(null);
    }
}
