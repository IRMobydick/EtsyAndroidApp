package com.etsy.android.ui.core;

import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.models.ShopAboutVideo;
import com.etsy.android.ui.nav.Nav;
import org.parceler.Parcels;

public class ShopAboutDetailedImageActivity extends DetailedImageActivity {
    protected int getGraphikTheme() {
        return 2131428191;
    }

    protected boolean hasImages() {
        ShopAboutVideo shopAboutVideo = (ShopAboutVideo) Parcels.m7495a(getIntent().getParcelableExtra("shop_about_video"));
        return super.hasImages() || (shopAboutVideo != null && shopAboutVideo.videoIsReady());
    }

    protected void navToDetailedImageFragment() {
        Nav.m4682a((FragmentActivity) this).m4684b().m4623a(getIntent().getExtras()).m4668t();
    }
}
