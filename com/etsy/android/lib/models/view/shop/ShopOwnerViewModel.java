package com.etsy.android.lib.models.view.shop;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.apiv3.ShopV3;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.shop.ShopHomeRouter;
import com.etsy.android.uikit.viewholder.d;

public class ShopOwnerViewModel implements d {
    private final String mAvatarUrl;
    private final TrackingOnClickListener mClickListener;
    private final CharSequence mHeading;

    /* renamed from: com.etsy.android.lib.models.view.shop.ShopOwnerViewModel.1 */
    class C04961 extends TrackingOnClickListener {
        final /* synthetic */ ShopHomeRouter f1918a;
        final /* synthetic */ ShopV3 f1919b;
        final /* synthetic */ ShopOwnerViewModel f1920c;

        C04961(ShopOwnerViewModel shopOwnerViewModel, ShopHomeRouter shopHomeRouter, ShopV3 shopV3) {
            this.f1920c = shopOwnerViewModel;
            this.f1918a = shopHomeRouter;
            this.f1919b = shopV3;
        }

        public void onViewClick(View view) {
            this.f1918a.navigateToUserProfile(this.f1919b.getUserId());
        }
    }

    public ShopOwnerViewModel(@NonNull ShopHomeRouter shopHomeRouter, @NonNull ShopV3 shopV3, @NonNull Context context) {
        this.mAvatarUrl = shopV3.getSellerAvatarUrl();
        Resources resources = context.getResources();
        String string = resources.getString(R.shop_owner_heading);
        String sellerName = shopV3.getSellerName();
        int length = string.length();
        CharSequence spannableString = new SpannableString(string + "\n" + sellerName);
        spannableString.setSpan(new AbsoluteSizeSpan(resources.getDimensionPixelSize(R.text_small)), 0, length, 33);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.blue)), 0, length, 33);
        this.mHeading = spannableString;
        this.mClickListener = new C04961(this, shopHomeRouter, shopV3);
    }

    public int getBackgroundColor() {
        return 0;
    }

    @Nullable
    public CharSequence getHeading() {
        return this.mHeading;
    }

    @Nullable
    public String getImageUrl() {
        return this.mAvatarUrl;
    }

    @Nullable
    public Drawable getDrawable() {
        return null;
    }

    public int getType() {
        return 0;
    }

    public int getHeadingGravity() {
        return 16;
    }

    @Nullable
    public TrackingOnClickListener getHeadingClickListener() {
        return this.mClickListener;
    }
}
