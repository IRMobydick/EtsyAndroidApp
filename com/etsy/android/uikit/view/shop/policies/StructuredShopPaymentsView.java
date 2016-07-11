package com.etsy.android.uikit.view.shop.policies;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.apiv3.StructuredShopPayments;
import com.etsy.android.lib.util.PaymentMethod;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.etsy.android.uikit.view.PaymentMethodsView;

public class StructuredShopPaymentsView extends StructuredShopPoliciesView {
    private PaymentMethodsView mPaymentMethodsView;
    private TextView mTxtOtherOptionsInfo;
    private TextView mTxtSecureOptionsInfo;
    private TextView mTxtSubheadOtherOptions;
    private TextView mTxtSubheadPaymentMethods;
    private TextView mTxtSubheadSecureOptions;

    public StructuredShopPaymentsView(Context context) {
        super(context);
    }

    public StructuredShopPaymentsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public StructuredShopPaymentsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public StructuredShopPaymentsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    protected void init(Context context, LinearLayout linearLayout) {
        inflate(context, R.view_structured_shop_payments, linearLayout);
        this.mTxtSubheadSecureOptions = (TextView) findViewById(R.txt_subhead_secure_options);
        this.mTxtSecureOptionsInfo = (TextView) findViewById(R.txt_secure_options_info);
        this.mPaymentMethodsView = (PaymentMethodsView) findViewById(R.payment_methods);
        this.mTxtSubheadPaymentMethods = (TextView) findViewById(R.txt_subhead_payment_methods);
        this.mTxtSubheadOtherOptions = (TextView) findViewById(R.txt_subhead_other_options);
        this.mTxtOtherOptionsInfo = (TextView) findViewById(R.txt_other_options_info);
    }

    public void setStructuredShopPayments(@NonNull StructuredShopPayments structuredShopPayments, @Nullable StructuredShopPoliciesView structuredShopPoliciesView) {
        boolean z;
        boolean z2 = true;
        Resources resources = getResources();
        boolean z3 = !structuredShopPayments.getProtectedPaymentMethods().isEmpty();
        if (z3) {
            this.mTxtSubheadSecureOptions.setVisibility(0);
            this.mPaymentMethodsView.setVisibility(0);
            this.mTxtSecureOptionsInfo.setVisibility(0);
            this.mTxtSubheadOtherOptions.setVisibility(0);
            this.mTxtSubheadPaymentMethods.setVisibility(8);
            this.mTxtSubheadSecureOptions.setCompoundDrawablesWithIntrinsicBounds(IconDrawable.m775a(resources).m780a(EtsyFontIcons.LOCK).m779a(resources.getColor(R.text_dark_grey)).m778a((float) resources.getDimensionPixelSize(R.text_medium)).m777a(), null, null, null);
            PaymentMethodsView paymentMethodsView = this.mPaymentMethodsView;
            boolean z4 = structuredShopPayments.acceptsDirectCheckout() || structuredShopPayments.acceptsPayPal();
            paymentMethodsView.setCreditCardsVisible(z4);
            paymentMethodsView = this.mPaymentMethodsView;
            if (structuredShopPayments.acceptsDirectCheckout() || structuredShopPayments.acceptsPayPal()) {
                z4 = true;
            } else {
                z4 = false;
            }
            paymentMethodsView.setPayPalVisible(z4);
            this.mPaymentMethodsView.setGiftCardsVisible(structuredShopPayments.acceptsDirectCheckout());
        } else {
            this.mTxtSubheadSecureOptions.setVisibility(8);
            this.mPaymentMethodsView.setVisibility(8);
            this.mTxtSecureOptionsInfo.setVisibility(8);
            this.mTxtSubheadOtherOptions.setVisibility(8);
            this.mTxtSubheadPaymentMethods.setVisibility(0);
        }
        if (structuredShopPayments.getManualPaymentMethods().isEmpty()) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            this.mTxtOtherOptionsInfo.setVisibility(0);
            CharSequence spannableStringBuilder = new SpannableStringBuilder(Html.fromHtml(resources.getString(R.structured_payment_other_options_info)));
            for (String paymentMethod : structuredShopPayments.getManualPaymentMethods()) {
                spannableStringBuilder.append("\n\n").append(Html.fromHtml("&#8226; ")).append(resources.getString(PaymentMethod.getPaymentMethod(paymentMethod).getDisplayLabel()));
            }
            this.mTxtOtherOptionsInfo.setText(spannableStringBuilder);
            linkifyContactShopUrlSpan(this.mTxtOtherOptionsInfo, structuredShopPoliciesView);
        } else {
            this.mTxtSubheadOtherOptions.setVisibility(8);
            this.mTxtOtherOptionsInfo.setVisibility(8);
        }
        if (!(z3 && z)) {
            z2 = false;
        }
        setContentCollapsible(z2);
    }
}
