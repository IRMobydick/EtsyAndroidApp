package com.etsy.android.ui.cart.viewholders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.SimpleKVPCartGroupItem;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class PaymentOptionsDividerViewHolder extends BaseViewHolder<CartGroupItem> {
    private final TextView mText;

    public PaymentOptionsDividerViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super(layoutInflater.inflate(2130903348, viewGroup, false));
        this.mText = (TextView) findViewById(2131756065);
    }

    public void bind(CartGroupItem cartGroupItem) {
        CharSequence text = ((SimpleKVPCartGroupItem) cartGroupItem.getData()).getText();
        if (TextUtils.isEmpty(text)) {
            this.mText.setVisibility(4);
            return;
        }
        this.mText.setVisibility(0);
        this.mText.setText(text);
    }
}
