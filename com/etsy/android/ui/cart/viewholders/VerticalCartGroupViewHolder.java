package com.etsy.android.ui.cart.viewholders;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.etsy.android.lib.models.apiv3.cart.CartGroup;
import com.etsy.android.ui.cart.MultiShopCartGroupAdapter;
import com.etsy.android.ui.cart.ab;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class VerticalCartGroupViewHolder extends BaseViewHolder<CartGroup> {
    private final MultiShopCartGroupAdapter mAdapter;
    private final RecyclerView mRecyclerView;

    public VerticalCartGroupViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, ab abVar) {
        super(layoutInflater.inflate(2130903355, viewGroup, false));
        this.mRecyclerView = (RecyclerView) this.itemView;
        this.mRecyclerView.setRecycledViewPool(abVar.m3735a());
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(viewGroup.getContext()));
        this.mAdapter = new MultiShopCartGroupAdapter(abVar);
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    public void bind(CartGroup cartGroup) {
        this.mAdapter.clearData();
        this.mAdapter.addCartGroupItems(cartGroup.getItems(), getAdapterPosition());
        this.mAdapter.addCartGroupItems(cartGroup.getPaymentItems(), getAdapterPosition());
    }
}
