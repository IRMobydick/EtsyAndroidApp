package com.etsy.android.lib.models.view.shop.section;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.models.apiv3.ShopV3;
import com.etsy.android.lib.models.apiv3.StructuredShopPolicies;
import com.etsy.android.uikit.util.MachineTranslationViewState;
import com.etsy.android.uikit.util.shop.ShopHomeStateManager;
import com.etsy.android.uikit.viewholder.a.a.a;
import org.apache.commons.lang3.StringUtils;

public class ShopHomeStructuredPoliciesSectionViewModel extends ShopHomeBaseSectionViewModel {
    private a mListener;
    private ShopV3 mShop;
    private final ShopHomeStateManager mStateManager;
    private StructuredShopPolicies mStructuredShopPolicies;

    public ShopHomeStructuredPoliciesSectionViewModel(@NonNull CharSequence charSequence, @NonNull StructuredShopPolicies structuredShopPolicies, @NonNull ShopV3 shopV3, @Nullable a aVar, @NonNull ShopHomeStateManager shopHomeStateManager) {
        super(charSequence);
        this.mStructuredShopPolicies = structuredShopPolicies;
        this.mShop = shopV3;
        this.mListener = aVar;
        this.mStateManager = shopHomeStateManager;
    }

    @NonNull
    public StructuredShopPolicies getStructuredShopPolicies() {
        return this.mStructuredShopPolicies;
    }

    @NonNull
    public ShopV3 getShop() {
        return this.mShop;
    }

    @Nullable
    public a getStructuredPoliciesViewClickListener() {
        return this.mListener;
    }

    public CharSequence getText() {
        return StringUtils.EMPTY;
    }

    public ShopHomeStateManager getStateManager() {
        return this.mStateManager;
    }

    public void setTranslatedOtherPolicyText(String str) {
        if (this.mStructuredShopPolicies.getPrivacy() != null) {
            this.mStructuredShopPolicies.getPrivacy().setOtherTranslation(str);
            this.mStructuredShopPolicies.getPrivacy().getTranslationState().setSuccessLoadingTranslation();
        }
    }

    @Nullable
    public String getTranslatedOtherPolicyText() {
        return this.mStructuredShopPolicies.getPrivacy() != null ? this.mStructuredShopPolicies.getPrivacy().getTranslatedOtherText() : null;
    }

    @NonNull
    public MachineTranslationViewState getOtherTranslationState() {
        return this.mStructuredShopPolicies.getPrivacy() != null ? this.mStructuredShopPolicies.getPrivacy().getTranslationState() : new MachineTranslationViewState();
    }
}
