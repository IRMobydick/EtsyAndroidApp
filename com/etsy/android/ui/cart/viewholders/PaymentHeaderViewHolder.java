package com.etsy.android.ui.cart.viewholders;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.SimpleKVPCartGroupItem;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class PaymentHeaderViewHolder extends BaseViewHolder<CartGroupItem> {
    private final TextView mTitle;

    public PaymentHeaderViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super(layoutInflater.inflate(2130903347, viewGroup, false));
        this.mTitle = (TextView) findViewById(R.txt_title);
    }

    public void bind(CartGroupItem cartGroupItem) {
        this.mTitle.setText(((SimpleKVPCartGroupItem) cartGroupItem.getData()).getTitle());
    }
}
