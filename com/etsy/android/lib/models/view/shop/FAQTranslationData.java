package com.etsy.android.lib.models.view.shop;

import com.etsy.android.lib.models.apiv3.FAQs;
import com.etsy.android.uikit.util.shop.ShopHomeStateManager;
import com.etsy.android.uikit.viewholder.TextViewHolder;
import com.etsy.android.uikit.viewholder.j;
import com.etsy.android.uikit.viewholder.m;

public class FAQTranslationData extends m {

    /* renamed from: com.etsy.android.lib.models.view.shop.FAQTranslationData.1 */
    class C04921 implements j {
        final /* synthetic */ ShopHomeStateManager f1906a;
        final /* synthetic */ FAQs f1907b;
        final /* synthetic */ FAQTranslationData f1908c;

        C04921(FAQTranslationData fAQTranslationData, ShopHomeStateManager shopHomeStateManager, FAQs fAQs) {
            this.f1908c = fAQTranslationData;
            this.f1906a = shopHomeStateManager;
            this.f1907b = fAQs;
        }

        public void m2970a(TextViewHolder textViewHolder) {
            this.f1906a.m5580a(this.f1907b, this.f1908c);
        }
    }

    public FAQTranslationData(CharSequence charSequence, FAQs fAQs, ShopHomeStateManager shopHomeStateManager) {
        super(charSequence, fAQs);
        this.mListener = new C04921(this, shopHomeStateManager, fAQs);
    }
}
