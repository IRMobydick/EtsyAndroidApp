package com.etsy.android.uikit.share;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.shopedit.ShopVideoShareData;
import com.etsy.android.lib.util.bh;
import com.etsy.android.uikit.nav.NavBase;
import org.parceler.Parcels;

public class ShareShopAboutVideoFragment extends SocialShareBrokerFragment {
    protected void readArguments(@NonNull Bundle bundle) {
        super.readArguments(bundle);
        ShopVideoShareData shopVideoShareData = (ShopVideoShareData) Parcels.m7495a(bundle.getParcelable("shop_video_share_data"));
        if (shopVideoShareData == null || !bh.m3340a(shopVideoShareData.getShareUrl())) {
            NavBase.m4675b(getActivity()).m4679f();
            return;
        }
        this.mUrl = shopVideoShareData.getShareUrl();
        this.mText = getString(R.shop_about_video_share_text, this.mUrl);
    }

    public View onCreateHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        ((TextView) onCreateView.findViewById(R.social_share_title)).setText(R.share_shop_about_video_prompt);
        return onCreateView;
    }
}
