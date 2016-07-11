package com.etsy.android.lib.models.view.shop;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.Review;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.uikit.util.MachineTranslationViewState;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.shop.ShopHomeRouter;
import com.etsy.android.uikit.util.shop.ShopHomeStateManager;
import com.etsy.android.uikit.viewholder.a.a;
import com.etsy.android.uikit.viewholder.d;
import com.etsy.android.uikit.viewholder.i;
import com.etsy.android.uikit.viewholder.j;
import com.etsy.android.uikit.viewholder.shop.f;

public class ShopHomeReviewViewModel implements d, i, f {
    private final String mAvatarUrl;
    private final TrackingOnClickListener mClickListener;
    private final CharSequence mHeading;
    private final a mListingClickListener;
    private final Review mReview;
    private final MachineTranslationViewState mReviewTranslationState;
    private final ShopHomeStateManager mStateManager;

    /* renamed from: com.etsy.android.lib.models.view.shop.ShopHomeReviewViewModel.1 */
    class C04951 extends TrackingOnClickListener {
        final /* synthetic */ ShopHomeRouter f1915a;
        final /* synthetic */ EtsyId f1916b;
        final /* synthetic */ ShopHomeReviewViewModel f1917c;

        C04951(ShopHomeReviewViewModel shopHomeReviewViewModel, ShopHomeRouter shopHomeRouter, EtsyId etsyId) {
            this.f1917c = shopHomeReviewViewModel;
            this.f1915a = shopHomeRouter;
            this.f1916b = etsyId;
        }

        public void onViewClick(View view) {
            this.f1915a.navigateToUserProfile(this.f1916b);
        }
    }

    public ShopHomeReviewViewModel(@NonNull Review review, @NonNull EtsyId etsyId, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull ShopHomeRouter shopHomeRouter, @NonNull ShopHomeStateManager shopHomeStateManager, @NonNull Context context) {
        this.mReview = review;
        this.mReviewTranslationState = this.mReview.getTranslationState();
        this.mAvatarUrl = str2;
        this.mListingClickListener = shopHomeRouter.getListingCardClickListener();
        CharSequence string = context.getString(R.shop_home_review_heading, new Object[]{str, str3});
        int indexOf = string.indexOf(str);
        CharSequence spannableString = new SpannableString(string);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.blue)), indexOf, str.length() + indexOf, 33);
        this.mHeading = spannableString;
        this.mClickListener = new C04951(this, shopHomeRouter, etsyId);
        this.mStateManager = shopHomeStateManager;
    }

    public Review getReview() {
        return this.mReview;
    }

    @Nullable
    public Drawable getDrawable() {
        return null;
    }

    @Nullable
    public CharSequence getHeading() {
        return this.mHeading;
    }

    @Nullable
    public String getImageUrl() {
        return this.mAvatarUrl;
    }

    public int getType() {
        return 0;
    }

    public int getBackgroundColor() {
        return -1;
    }

    public int getHeadingGravity() {
        return 16;
    }

    public j getListener() {
        return null;
    }

    public CharSequence getText() {
        return this.mReview.getReviewMessage();
    }

    public ListingLike getListing() {
        return this.mReview.getListing();
    }

    public a getListingClickListener() {
        return this.mListingClickListener;
    }

    public ShopHomeStateManager getStateManager() {
        return this.mStateManager;
    }

    @Nullable
    public BaseModelImage getDisplayImage() {
        return this.mReview.getListingImage();
    }

    public String getDisplayTitle() {
        return this.mReview.getListingTitle();
    }

    @Nullable
    public TrackingOnClickListener getHeadingClickListener() {
        return this.mClickListener;
    }

    public MachineTranslationViewState getTranslationState() {
        return this.mReviewTranslationState;
    }

    public void setTranslatedReviewMessage(String str) {
        this.mReview.setTranslatedReviewMessage(str);
        this.mReviewTranslationState.setSuccessLoadingTranslation();
    }
}
