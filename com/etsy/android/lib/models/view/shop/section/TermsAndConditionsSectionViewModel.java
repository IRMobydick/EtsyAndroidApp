package com.etsy.android.lib.models.view.shop.section;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.apiv3.ShopV3;
import com.etsy.android.uikit.util.shop.ShopHomeRouter;
import com.etsy.android.uikit.viewholder.TextViewHolder;
import com.etsy.android.uikit.viewholder.i;
import com.etsy.android.uikit.viewholder.j;

public class TermsAndConditionsSectionViewModel implements i {
    private final j mClickListener;
    private final String mLinkText;

    /* renamed from: com.etsy.android.lib.models.view.shop.section.TermsAndConditionsSectionViewModel.1 */
    class C04971 implements j {
        final /* synthetic */ ShopHomeRouter f1921a;
        final /* synthetic */ String f1922b;
        final /* synthetic */ TermsAndConditionsSectionViewModel f1923c;

        C04971(TermsAndConditionsSectionViewModel termsAndConditionsSectionViewModel, ShopHomeRouter shopHomeRouter, String str) {
            this.f1923c = termsAndConditionsSectionViewModel;
            this.f1921a = shopHomeRouter;
            this.f1922b = str;
        }

        public void m2971a(TextViewHolder textViewHolder) {
            this.f1921a.navigateToTermsAndConditions(this.f1922b);
        }
    }

    public TermsAndConditionsSectionViewModel(@NonNull Resources resources, @NonNull String str, @NonNull ShopV3 shopV3, @NonNull ShopHomeRouter shopHomeRouter) {
        this.mLinkText = resources.getString(R.terms_and_conditions_link, new Object[]{shopV3.getShopName()});
        this.mClickListener = new C04971(this, shopHomeRouter, str);
    }

    public CharSequence getText() {
        return this.mLinkText;
    }

    public j getListener() {
        return this.mClickListener;
    }
}
