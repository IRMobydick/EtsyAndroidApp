package com.etsy.android.lib.models.view.shop;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.ReviewResponse;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.shop.ShopHomeRouter;
import com.etsy.android.uikit.viewholder.d;
import java.text.SimpleDateFormat;

public class ShopHomeReviewResponseViewData implements d {
    private final String mAvatarUrl;
    private final TrackingOnClickListener mClickListener;
    private final CharSequence mTextContent;

    /* renamed from: com.etsy.android.lib.models.view.shop.ShopHomeReviewResponseViewData.1 */
    class C04941 extends TrackingOnClickListener {
        final /* synthetic */ ShopHomeRouter f1912a;
        final /* synthetic */ EtsyId f1913b;
        final /* synthetic */ ShopHomeReviewResponseViewData f1914c;

        C04941(ShopHomeReviewResponseViewData shopHomeReviewResponseViewData, ShopHomeRouter shopHomeRouter, EtsyId etsyId) {
            this.f1914c = shopHomeReviewResponseViewData;
            this.f1912a = shopHomeRouter;
            this.f1913b = etsyId;
        }

        public void onViewClick(View view) {
            this.f1912a.navigateToUserProfile(this.f1913b);
        }
    }

    public ShopHomeReviewResponseViewData(@NonNull Context context, @NonNull SimpleDateFormat simpleDateFormat, @NonNull ShopHomeRouter shopHomeRouter, @NonNull EtsyId etsyId, @NonNull String str, @NonNull String str2, @NonNull ReviewResponse reviewResponse) {
        String string = context.getString(R.shop_home_review_response_heading, new Object[]{str, simpleDateFormat.format(reviewResponse.getCreateDate())});
        int indexOf = string.indexOf(str);
        CharSequence spannableString = new SpannableString(string + "\n\n" + reviewResponse.getMessage());
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.blue)), indexOf, str.length() + indexOf, 33);
        this.mTextContent = spannableString;
        this.mAvatarUrl = str2;
        this.mClickListener = new C04941(this, shopHomeRouter, etsyId);
    }

    @Nullable
    public CharSequence getHeading() {
        return this.mTextContent;
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

    @ColorInt
    public int getBackgroundColor() {
        return -1;
    }

    public int getHeadingGravity() {
        return 16;
    }

    @Nullable
    public TrackingOnClickListener getHeadingClickListener() {
        return this.mClickListener;
    }
}
