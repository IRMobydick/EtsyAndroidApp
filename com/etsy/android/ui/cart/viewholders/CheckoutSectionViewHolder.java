package com.etsy.android.ui.cart.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.SimpleKVPCartGroupItem;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import com.etsy.android.uikit.viewholder.a.b;

public class CheckoutSectionViewHolder extends BaseViewHolder<CartGroupItem> {
    private final Button mBtnCheckout;
    private final b mClickListener;

    /* renamed from: com.etsy.android.ui.cart.viewholders.CheckoutSectionViewHolder.1 */
    class C06061 extends TrackingOnClickListener {
        final /* synthetic */ CartGroupItem f2598a;
        final /* synthetic */ CheckoutSectionViewHolder f2599b;

        C06061(CheckoutSectionViewHolder checkoutSectionViewHolder, CartGroupItem cartGroupItem) {
            this.f2599b = checkoutSectionViewHolder;
            this.f2598a = cartGroupItem;
        }

        public void onViewClick(View view) {
            if (this.f2599b.mClickListener != null) {
                this.f2599b.mClickListener.a(this.f2598a);
            }
        }
    }

    public CheckoutSectionViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, b bVar) {
        super(layoutInflater.inflate(2130903340, viewGroup, false));
        this.mClickListener = bVar;
        this.mBtnCheckout = (Button) findViewById(2131756056);
    }

    public void bind(CartGroupItem cartGroupItem) {
        SimpleKVPCartGroupItem simpleKVPCartGroupItem = (SimpleKVPCartGroupItem) cartGroupItem.getData();
        if (simpleKVPCartGroupItem != null) {
            this.mBtnCheckout.setText(simpleKVPCartGroupItem.getButtonText());
        }
        this.mBtnCheckout.setOnClickListener(new C06061(this, cartGroupItem));
    }
}
