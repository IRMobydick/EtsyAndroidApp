package com.etsy.android.lib.models.view.shop;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.ShopAboutMember;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.d;

public class ShopAboutMemberViewModel implements d {
    private static String sDefaultUrl;
    private final CharSequence mHeading;
    private final ShopAboutMember mShopAboutMember;

    private static String getDefaultAvatarUrl() {
        if (sDefaultUrl == null) {
            sDefaultUrl = EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.cv) + ShopAboutMember.DEFAULT_IMAGE_AVATAR_PATH_190;
        }
        return sDefaultUrl;
    }

    public ShopAboutMemberViewModel(@NonNull ShopAboutMember shopAboutMember, @NonNull Context context) {
        String name = shopAboutMember.getName();
        int length = name.length();
        CharSequence spannableString = new SpannableString((name + "\n" + shopAboutMember.getRole().replaceAll(",", "$0 ") + "\n\n" + shopAboutMember.getBio()).trim());
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.text_dark_grey)), 0, length, 33);
        this.mHeading = spannableString;
        this.mShopAboutMember = shopAboutMember;
    }

    public CharSequence getHeading() {
        return this.mHeading;
    }

    public String getImageUrl() {
        String a = ImageBatch.m1557a(90, 90, this.mShopAboutMember.getImage());
        if (TextUtils.isEmpty(a)) {
            return getDefaultAvatarUrl();
        }
        return a;
    }

    public ShopAboutMember getShopAboutMember() {
        return this.mShopAboutMember;
    }

    public Drawable getDrawable() {
        return null;
    }

    public int getBackgroundColor() {
        return -1;
    }

    public int getHeadingGravity() {
        return 48;
    }

    public int getType() {
        return 0;
    }

    @Nullable
    public TrackingOnClickListener getHeadingClickListener() {
        return null;
    }
}
