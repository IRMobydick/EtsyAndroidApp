package com.etsy.android.uikit.util.shop;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout.Tab;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.ShopAboutVideo;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.uikit.viewholder.a.a;

/* renamed from: com.etsy.android.uikit.util.shop.a */
public interface ShopHomeRouter {
    void changeTabSelectionIfNecessary();

    void didClickRelatedLink(@NonNull String str);

    void didClickVacationSubscribeButton(boolean z);

    void didHideAnnouncement();

    void didViewAnnouncement();

    @Nullable
    a getListingCardClickListener();

    @Nullable
    com.etsy.android.uikit.viewholder.a.a.a getStructuredPoliciesViewClickListener();

    void navigateToAppreciationPhotoPage(@NonNull EtsyId etsyId);

    void navigateToLocalMarket(@NonNull LocalMarket localMarket);

    void navigateToReviewsPage();

    void navigateToShopAboutVideo(@NonNull ShopAboutVideo shopAboutVideo);

    void navigateToTabSection(@NonNull Tab tab, boolean z);

    void navigateToTabSection(String str, boolean z);

    void navigateToTermsAndConditions(@NonNull String str);

    void navigateToUserProfile(@NonNull EtsyId etsyId);

    void onShopFavorerStatusChanged(boolean z);

    void showConvoComposeModal();
}
