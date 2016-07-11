package com.etsy.android.lib.models.view.shop.section;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.util.Linkify;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.ShopAbout;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.GraphikUtil;

public class ShopHomeAboutSectionViewModel extends ShopHomeBaseSectionViewModel {
    private CharSequence mAboutContent;
    private int mNumFavorers;
    private ShopAbout mShopAbout;

    public ShopHomeAboutSectionViewModel(@NonNull Context context, @NonNull ShopAbout shopAbout, @NonNull CharSequence charSequence, int i) {
        super(charSequence);
        Resources resources = context.getResources();
        Object trim = shopAbout.getStoryHeadline().trim();
        Spanned spannableStringBuilder = new SpannableStringBuilder();
        if (!TextUtils.isEmpty(trim)) {
            spannableStringBuilder.append(trim);
            int length = trim.length();
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(resources.getDimensionPixelSize(R.text_large)), 0, length, 33);
            spannableStringBuilder.setSpan(GraphikUtil.m5544a(context), 0, length, 33);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.text_dark_grey)), 0, length, 33);
        }
        CharSequence trim2 = shopAbout.getStory().trim();
        if (!TextUtils.isEmpty(trim2)) {
            spannableStringBuilder.append("\n\n").append(trim2);
        }
        Linkify.addLinks(spannableStringBuilder, 1);
        this.mAboutContent = EtsyLinkify.m5481a(context, spannableStringBuilder, true, true, null);
        this.mShopAbout = shopAbout;
        this.mNumFavorers = i;
    }

    public CharSequence getText() {
        return this.mAboutContent;
    }

    public ShopAbout getShopAbout() {
        return this.mShopAbout;
    }

    public int getNumFavorers() {
        return this.mNumFavorers;
    }
}
