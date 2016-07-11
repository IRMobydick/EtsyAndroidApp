package com.etsy.android.lib.models.view.shop.section;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.util.Linkify;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.apiv3.ShopPolicy;
import com.etsy.android.uikit.util.EtsyLinkify;
import java.util.ArrayList;
import java.util.List;

public class ShopHomePoliciesSectionViewModel extends ShopHomeBaseSectionViewModel {
    @NonNull
    private ShopPolicy mPolicy;
    @NonNull
    private final List<Pair<String, ? extends CharSequence>> mPolicySectionsInfo;
    private final String mTrimmedWelcomeMessage;

    public ShopHomePoliciesSectionViewModel(@NonNull CharSequence charSequence, @NonNull ShopPolicy shopPolicy, @NonNull Context context) {
        super(charSequence);
        Resources resources = context.getResources();
        List arrayList = new ArrayList(4);
        configurePolicySectionInfoList(context, resources.getString(R.payment_policy), shopPolicy.getPaymentPolicy(), arrayList);
        configurePolicySectionInfoList(context, resources.getString(R.shipping_policy), shopPolicy.getShippingPolicy(), arrayList);
        configurePolicySectionInfoList(context, resources.getString(R.refund_policy), shopPolicy.getRefundPolicy(), arrayList);
        configurePolicySectionInfoList(context, resources.getString(R.additional_information), shopPolicy.getAdditionalInformationMessage(), arrayList);
        this.mPolicySectionsInfo = arrayList;
        String welcomeMessage = shopPolicy.getWelcomeMessage();
        if (!TextUtils.isEmpty(welcomeMessage)) {
            welcomeMessage = welcomeMessage.trim();
        }
        this.mTrimmedWelcomeMessage = welcomeMessage;
        this.mPolicy = shopPolicy;
    }

    private static void configurePolicySectionInfoList(@NonNull Context context, @NonNull String str, @Nullable String str2, @NonNull List<Pair<String, ? extends CharSequence>> list) {
        if (str2 != null && str2.length() != 0) {
            CharSequence trim = str2.trim();
            if (trim.length() != 0) {
                Spanned spannableString = new SpannableString(trim);
                Linkify.addLinks(spannableString, 1);
                list.add(new Pair(str, EtsyLinkify.m5481a(context, spannableString, true, true, null)));
            }
        }
    }

    @NonNull
    public List<Pair<String, ? extends CharSequence>> getPolicySectionsInfo() {
        return this.mPolicySectionsInfo;
    }

    public CharSequence getText() {
        return this.mTrimmedWelcomeMessage;
    }
}
