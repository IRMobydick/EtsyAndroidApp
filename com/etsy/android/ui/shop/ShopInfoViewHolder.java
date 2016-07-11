package com.etsy.android.ui.shop;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.apiv3.AppreciationPhotoFeature;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ClickableImageView;
import com.etsy.android.uikit.view.RatingIconView;

public class ShopInfoViewHolder extends ViewHolder {
    private Activity mContext;
    private View mDivider;
    private ClickableImageView mShopIcon;
    private TextView mShopLocation;
    private TextView mShopName;
    private TextView mShopRatingCount;
    private RatingIconView mShopRatingView;

    /* renamed from: com.etsy.android.ui.shop.ShopInfoViewHolder.1 */
    class C08101 extends TrackingOnClickListener {
        final /* synthetic */ AppreciationPhotoFeature f3394a;
        final /* synthetic */ ShopInfoViewHolder f3395b;

        C08101(ShopInfoViewHolder shopInfoViewHolder, AppreciationPhotoFeature appreciationPhotoFeature) {
            this.f3395b = shopInfoViewHolder;
            this.f3394a = appreciationPhotoFeature;
        }

        public void onViewClick(View view) {
            Nav.m4681a(this.f3395b.mContext).m4501b(this.f3394a.getShopId());
        }
    }

    public ShopInfoViewHolder(View view, Activity activity) {
        super(view);
        this.mShopName = (TextView) view.findViewById(R.shop_name);
        this.mShopLocation = (TextView) view.findViewById(2131755346);
        this.mShopIcon = (ClickableImageView) view.findViewById(R.shop_icon);
        this.mShopRatingView = (RatingIconView) view.findViewById(R.shop_rating);
        this.mShopRatingCount = (TextView) view.findViewById(R.rating_count);
        this.mDivider = view.findViewById(R.divider);
        this.mContext = activity;
        Resources resources = activity.getResources();
        IconDrawable c = IconDrawable.m775a(resources).m780a(EtsyFontIcons.LOCATION).m781b((int) R.light_grey).m782c(R.text_medium);
        this.mShopLocation.setCompoundDrawablePadding(resources.getDimensionPixelSize(R.fixed_small_medium));
        this.mShopLocation.setCompoundDrawablesWithIntrinsicBounds(c.m777a(), null, null, null);
    }

    public void bind(AppreciationPhotoFeature appreciationPhotoFeature, ImageBatch imageBatch) {
        this.mShopName.setText(appreciationPhotoFeature.getShopName());
        imageBatch.m1568a(appreciationPhotoFeature.getShopIconUrl(), this.mShopIcon);
        CharSequence shopLocation = appreciationPhotoFeature.getShopLocation();
        if (TextUtils.isEmpty(shopLocation)) {
            this.mShopLocation.setVisibility(8);
            if (this.mDivider != null) {
                this.mDivider.setVisibility(8);
            }
        } else {
            this.mShopLocation.setText(shopLocation);
            this.mShopLocation.setVisibility(0);
            if (this.mDivider != null) {
                this.mDivider.setVisibility(0);
                this.mDivider.getLayoutParams().height = this.mShopLocation.getLineHeight();
            }
        }
        this.mShopRatingView.setRating(appreciationPhotoFeature.getShopAverageRating());
        this.mShopRatingCount.setText("(" + appreciationPhotoFeature.getShopTotalRatings() + ")");
        this.mShopIcon.setOnClickListener(new C08101(this, appreciationPhotoFeature));
    }
}
