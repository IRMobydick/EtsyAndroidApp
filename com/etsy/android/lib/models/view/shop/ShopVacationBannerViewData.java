package com.etsy.android.lib.models.view.shop;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.models.apiv3.ShopV3;
import com.etsy.android.uikit.util.shop.ShopHomeStateManager;

public class ShopVacationBannerViewData {
    private boolean mIsLoading;
    private boolean mShouldEnableSubscribeButton;
    private final String mSubscribeButtonTitle;
    private final String mUnSubscribeButtonTitle;
    private boolean mUserIsSubscribed;
    private final String mVacationMessage;
    private final String mVacationTitle;

    public ShopVacationBannerViewData(@NonNull ShopV3 shopV3, @NonNull ShopHomeStateManager shopHomeStateManager, @NonNull Resources resources) {
        this.mVacationMessage = shopV3.getVacationMessage();
        this.mVacationTitle = resources.getString(R.shop_home_vacation_title, new Object[]{shopV3.getShopName()});
        this.mSubscribeButtonTitle = resources.getString(R.shop_home_vacation_notify_message);
        this.mUnSubscribeButtonTitle = resources.getString(R.shop_home_vacation_dont_notify_message);
        this.mShouldEnableSubscribeButton = !shopV3.getUserId().equals(aj.m1101a().m1125k());
        if (shopHomeStateManager.m5612p()) {
            this.mUserIsSubscribed = shopHomeStateManager.m5610n();
            this.mIsLoading = false;
        } else if (this.mShouldEnableSubscribeButton && aj.m1101a().m1118d()) {
            this.mIsLoading = true;
        }
    }

    public boolean isUserSubscribed() {
        return this.mUserIsSubscribed;
    }

    public void setUserIsSubscribed(boolean z) {
        this.mUserIsSubscribed = z;
        this.mIsLoading = false;
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public void setLoading(boolean z) {
        this.mIsLoading = z;
    }

    public String getVacationTitle() {
        return this.mVacationTitle;
    }

    public String getVacationMessage() {
        return this.mVacationMessage;
    }

    public String getButtonTitle() {
        return this.mUserIsSubscribed ? this.mUnSubscribeButtonTitle : this.mSubscribeButtonTitle;
    }

    public boolean shouldEnableSubscribeButton() {
        return this.mShouldEnableSubscribeButton;
    }
}
