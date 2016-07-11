package com.etsy.android.ui.cart.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.PaymentMethod;
import com.etsy.android.lib.models.apiv3.cart.PaymentOptions;
import com.etsy.android.ui.cart.p015a.CartGroupActionClickHandler;

public class PaymentOptionsViewHolder extends CartGroupItemViewHolder {
    private final RadioGroup mRadioGroupPaymentMethods;

    public PaymentOptionsViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, CartGroupActionClickHandler cartGroupActionClickHandler) {
        super(layoutInflater.inflate(2130903349, viewGroup, false), cartGroupActionClickHandler);
        this.mRadioGroupPaymentMethods = (RadioGroup) findViewById(2131756066);
    }

    public void bind(CartGroupItem cartGroupItem) {
        PaymentOptions paymentOptions = (PaymentOptions) cartGroupItem.getData();
        this.mRadioGroupPaymentMethods.removeAllViews();
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        for (PaymentMethod paymentMethod : paymentOptions.getPaymentMethods()) {
            View radioButton = new RadioButton(this.itemView.getContext());
            radioButton.setLayoutParams(layoutParams);
            if (paymentMethod.isPayPal()) {
                radioButton.setCompoundDrawablesWithIntrinsicBounds(this.itemView.getResources().getDrawable(2130838012), null, null, null);
            } else if (paymentMethod.isCreditCard()) {
                radioButton.setCompoundDrawablesWithIntrinsicBounds(this.itemView.getResources().getDrawable(2130838011), null, null, null);
            } else if (paymentMethod.isAndroidPay()) {
                radioButton.setText(paymentMethod.getDisplayValue());
            } else {
                radioButton.setText(paymentMethod.getDisplayValue());
            }
            radioButton.setChecked(paymentMethod.isSelected());
            radioButton.setEnabled(paymentMethod.isEnabled());
            this.mRadioGroupPaymentMethods.addView(radioButton);
        }
    }
}
