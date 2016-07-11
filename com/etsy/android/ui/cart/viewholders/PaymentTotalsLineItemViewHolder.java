package com.etsy.android.ui.cart.viewholders;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.SimpleKVPCartGroupItem;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.etsy.android.uikit.viewholder.a.b;

public class PaymentTotalsLineItemViewHolder extends BaseViewHolder<CartGroupItem> {
    private final TextView mTitle;
    private final TextView mValue;

    public PaymentTotalsLineItemViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, b bVar, boolean z) {
        super(layoutInflater.inflate(z ? 2130903341 : 2130903353, viewGroup, false));
        this.mTitle = (TextView) findViewById(R.txt_title);
        this.mValue = (TextView) findViewById(2131756057);
    }

    public void bind(CartGroupItem cartGroupItem) {
        SimpleKVPCartGroupItem simpleKVPCartGroupItem = (SimpleKVPCartGroupItem) cartGroupItem.getData();
        this.mTitle.setText(simpleKVPCartGroupItem.getTitle());
        if (simpleKVPCartGroupItem.getFormattedMoney() != null) {
            this.mValue.setText(simpleKVPCartGroupItem.getFormattedMoney().toString());
        } else if (simpleKVPCartGroupItem.getMoney() != null) {
            this.mValue.setText(simpleKVPCartGroupItem.getMoney().toString());
        }
    }
}
