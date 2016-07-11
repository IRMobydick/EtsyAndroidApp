package com.etsy.android.ui.cart.viewholders;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.PaymentAppliedCoupon;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.etsy.android.uikit.viewholder.a.b;

public class PaymentAppliedCouponViewHolder extends BaseViewHolder<CartGroupItem> {
    private final TextView mTxtCouponCode;
    private final TextView mTxtDescription;
    private final TextView mTxtPriceDiscount;

    public PaymentAppliedCouponViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, b bVar) {
        super(layoutInflater.inflate(2130903338, viewGroup, false));
        this.mTxtCouponCode = (TextView) findViewById(2131756037);
        this.mTxtDescription = (TextView) findViewById(2131755949);
        this.mTxtPriceDiscount = (TextView) findViewById(2131756039);
    }

    public void bind(CartGroupItem cartGroupItem) {
        PaymentAppliedCoupon paymentAppliedCoupon = (PaymentAppliedCoupon) cartGroupItem.getData();
        this.mTxtCouponCode.setText(paymentAppliedCoupon.getCouponCode());
        this.mTxtDescription.setText(paymentAppliedCoupon.getDescription());
        this.mTxtPriceDiscount.setText("-$99,999.99");
    }
}
