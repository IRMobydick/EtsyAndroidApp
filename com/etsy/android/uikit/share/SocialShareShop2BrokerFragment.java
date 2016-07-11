package com.etsy.android.uikit.share;

import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.interfaces.ShopShareable;
import com.etsy.android.lib.util.bh;
import org.parceler.Parcels;

public class SocialShareShop2BrokerFragment extends SocialShareBrokerFragment {
    private int mIconContainerSize;
    private int mIconCornerRadius;
    private ShopShareable mShop;

    protected void readArguments(@NonNull Bundle bundle) {
        super.readArguments(bundle);
        this.mShop = (ShopShareable) Parcels.m7495a(bundle.getParcelable("shop2"));
        if (this.mShop == null) {
            EtsyDebug.m1894a(new IllegalArgumentException("No shop specified"));
            return;
        }
        this.mUrl = this.mShop.getShareUrl();
        this.mText = getString(R.social_share_shop_message, this.mUrl);
        Resources resources = getResources();
        this.mIconContainerSize = resources.getDimensionPixelSize(R.social_share_shop_icon_size);
        this.mIconCornerRadius = resources.getDimensionPixelSize(R.gen_avatar_corners_large);
        this.mImageUrl = this.mShop.getShareImageUrl(this.mIconContainerSize);
    }

    public View onCreateHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.fragment_social_share_shop2_header, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        ((TextView) view.findViewById(R.social_share_title)).setText(R.share_prompt_shop_message);
        ((TextView) view.findViewById(R.shop_name)).setText(this.mShop.getShopName());
        TextView textView = (TextView) view.findViewById(R.headline);
        String shopHeadline = this.mShop.getShopHeadline();
        if (bh.m3343b(shopHeadline)) {
            textView.setVisibility(0);
            textView.setText(shopHeadline);
        } else {
            textView.setVisibility(8);
        }
        ImageView imageView = (ImageView) view.findViewById(R.shop_icon);
        if (imageView != null) {
            getImageBatch().m1576b(this.mImageUrl, imageView, this.mIconCornerRadius, this.mIconContainerSize, this.mIconContainerSize);
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
