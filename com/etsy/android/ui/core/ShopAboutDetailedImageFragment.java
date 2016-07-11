package com.etsy.android.ui.core;

import android.os.Bundle;
import com.etsy.android.lib.logger.ITrackingView;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShopAboutVideo;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.nav.EtsyActivityNavigator;
import com.etsy.android.ui.nav.Nav;
import com.etsy.android.uikit.adapter.FullImagesPagerAdapter;
import com.etsy.android.uikit.nav.ActivityNavigator.AnimationMode;
import org.parceler.Parcels;

public class ShopAboutDetailedImageFragment extends DetailedImageFragment {
    private EtsyId mShopId;
    private ShopAboutVideo mVideo;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mVideo = (ShopAboutVideo) Parcels.m7495a(arguments.getParcelable("shop_about_video"));
        this.mShopId = (EtsyId) Parcels.m7495a(arguments.getParcelable(ResponseConstants.SHOP_ID));
        if (this.mVideo != null) {
            addImage(0, this.mVideo.getThumbnail());
        }
    }

    public void onImageClick(int i) {
        if (i == 0 && this.mVideo != null) {
            ((EtsyActivityNavigator) Nav.m4681a(this.mActivity).m4446a(AnimationMode.FADE_IN_OUT).m3013a((ITrackingView) this)).m4504b(this.mShopId, this.mVideo.getVideoUrl());
        }
    }

    protected FullImagesPagerAdapter createAdapter() {
        FullImagesPagerAdapter createAdapter = super.createAdapter();
        createAdapter.setHasVideo(this.mVideo != null);
        return createAdapter;
    }
}
