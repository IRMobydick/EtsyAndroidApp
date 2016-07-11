package com.etsy.android.uikit.share;

import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.models.interfaces.ShopLike;
import com.etsy.android.lib.util.bh;
import com.etsy.android.uikit.view.RatingIconView;

public class SocialShareShopBrokerFragment extends SocialShareBrokerFragment {
    private ShopLike mShop;

    protected void readArguments(@NonNull Bundle bundle) {
        super.readArguments(bundle);
        this.mSubject = getString(R.share_shop_subject);
        this.mShop = (ShopLike) bundle.getSerializable(ActivityFeedEntity.SHOP);
        if (this.mShop == null) {
            EtsyDebug.m1894a(new IllegalArgumentException("No shop specified"));
            return;
        }
        this.mUrl = this.mShop.getUrl();
        this.mText = getString(R.social_share_shop_message, this.mShop.getUrl());
        if (this.mShop != null && !this.mShop.getCardListings().isEmpty()) {
            BaseModelImage listingImage = ((ListingLike) this.mShop.getCardListings().get(0)).getListingImage();
            if (listingImage != null && !TextUtils.isEmpty(listingImage.getFullSizedImage())) {
                this.mImageUrl = listingImage.getFullSizedImage();
            }
        }
    }

    public View onCreateHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.fragment_social_share_shop_header, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        showShop(view);
        ((TextView) view.findViewById(R.social_share_title)).setText(R.share_prompt_shop_message);
    }

    public void showShop(View view) {
        if (view != null) {
            TextView textView = (TextView) view.findViewById(R.shop_owner_name);
            TextView textView2 = (TextView) view.findViewById(R.shop_description);
            ImageView imageView = (ImageView) view.findViewById(R.shop_avatar);
            RatingIconView ratingIconView = (RatingIconView) view.findViewById(R.rating);
            if (this.mShop == null) {
                textView.setVisibility(8);
                textView2.setVisibility(8);
                imageView.setVisibility(8);
                ratingIconView.setVisibility(8);
                return;
            }
            textView.setVisibility(0);
            textView2.setVisibility(0);
            imageView.setVisibility(0);
            ratingIconView.setVisibility(0);
            textView.setText(this.mShop.getShopName());
            if (TextUtils.isEmpty(this.mShop.getHeadline())) {
                textView2.setVisibility(8);
            } else {
                textView2.setText(this.mShop.getHeadline());
            }
            ratingIconView.setRating((float) this.mShop.getAverageRating());
            String avatarUrl = this.mShop.getAvatarUrl();
            if (bh.m3340a(avatarUrl)) {
                int dimensionPixelSize = getResources().getDimensionPixelSize(R.shop_avatar);
                getImageBatch().m1576b(avatarUrl, imageView, getResources().getDimensionPixelSize(R.gen_avatar_corners_large), dimensionPixelSize, dimensionPixelSize);
                return;
            }
            imageView.setVisibility(8);
        }
    }

    public void onIntentItemClick(int i) {
        ResolveInfo resolveInfo = (ResolveInfo) this.mAdapter.getItem(i);
        if (ShareBrokerFragment.isTwitter(resolveInfo)) {
            this.mText = getString(R.share_shop_twitter, this.mShop.getShopName(), this.mUrl);
        } else if (ShareBrokerFragment.isGmail(resolveInfo)) {
            this.mSubject = getString(R.share_shop_email_subject);
            this.mText = getString(R.share_shop_email_message, this.mUrl);
        } else if (ShareBrokerFragment.isPinterest(resolveInfo) || ShareBrokerFragment.isGooglePlus(resolveInfo)) {
            this.mText = getString(R.share_shop_pinterest, this.mShop.getShopName());
        }
        super.onIntentItemClick(i);
    }
}
