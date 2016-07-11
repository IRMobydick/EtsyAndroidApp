package com.etsy.android.lib.models.view.shop;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.util.p013b.LocalMarketFormatter;
import com.etsy.android.lib.util.p013b.LocalMarketsUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.shop.ShopHomeRouter;
import com.etsy.android.uikit.viewholder.d;

public class ShopHomeLocalMarketViewModel implements d {
    private final TrackingOnClickListener mClickListener;
    private final CharSequence mDescription;
    private final Drawable mIconDrawable;
    private final LocalMarket mLocalMarket;

    /* renamed from: com.etsy.android.lib.models.view.shop.ShopHomeLocalMarketViewModel.1 */
    class C04931 extends TrackingOnClickListener {
        final /* synthetic */ ShopHomeRouter f1909a;
        final /* synthetic */ LocalMarket f1910b;
        final /* synthetic */ ShopHomeLocalMarketViewModel f1911c;

        C04931(ShopHomeLocalMarketViewModel shopHomeLocalMarketViewModel, ShopHomeRouter shopHomeRouter, LocalMarket localMarket) {
            this.f1911c = shopHomeLocalMarketViewModel;
            this.f1909a = shopHomeRouter;
            this.f1910b = localMarket;
        }

        public void onViewClick(View view) {
            this.f1909a.navigateToLocalMarket(this.f1910b);
        }
    }

    public ShopHomeLocalMarketViewModel(@NonNull LocalMarket localMarket, @NonNull ShopHomeRouter shopHomeRouter, @NonNull Context context) {
        this.mLocalMarket = localMarket;
        this.mIconDrawable = LocalMarketsUtil.m3304a(localMarket, context.getResources());
        this.mDescription = configureLocalMarketDescription(localMarket, context);
        this.mClickListener = new C04931(this, shopHomeRouter, localMarket);
    }

    private static CharSequence configureLocalMarketDescription(@NonNull LocalMarket localMarket, @NonNull Context context) {
        String title = localMarket.getTitle();
        String b = LocalMarketFormatter.m3303b(localMarket);
        String a = LocalMarketFormatter.m3300a(localMarket);
        CharSequence spannableString = new SpannableString(title + "\n" + b + "\n" + a + "\n\n" + localMarket.getDescription().trim());
        int length = title.length();
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.blue)), 0, length, 33);
        spannableString.setSpan(new BackgroundColorSpan(ContextCompat.getColor(context, R.white)), 0, length, 33);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.text_mid_grey)), length, ((b.length() + length) + a.length()) + 3, 33);
        return spannableString;
    }

    public String getImageUrl() {
        return null;
    }

    public Drawable getDrawable() {
        return this.mIconDrawable;
    }

    public CharSequence getHeading() {
        return this.mDescription;
    }

    public int getBackgroundColor() {
        return -1;
    }

    public int getHeadingGravity() {
        return 48;
    }

    public int getType() {
        return 1;
    }

    @Nullable
    public TrackingOnClickListener getHeadingClickListener() {
        return this.mClickListener;
    }
}
