package com.etsy.android.ui.cart.viewholders;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.etsy.android.lib.models.apiv3.cart.CartGroup;
import com.etsy.android.ui.cart.MultiShopCartGroupAdapter;
import com.etsy.android.ui.cart.ab;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class HorizontalCartGroupViewHolder extends BaseViewHolder<CartGroup> {
    private final MultiShopCartGroupAdapter mLeftAdapter;
    private final RecyclerView mRecyclerViewLeft;
    private final RecyclerView mRecyclerViewRight;
    private final MultiShopCartGroupAdapter mRightAdapter;

    public HorizontalCartGroupViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, ab abVar) {
        super(layoutInflater.inflate(2130903342, viewGroup, false));
        this.mRecyclerViewLeft = (RecyclerView) findViewById(2131756058);
        this.mRecyclerViewRight = (RecyclerView) findViewById(2131756059);
        this.mRecyclerViewLeft.setRecycledViewPool(abVar.m3735a());
        this.mRecyclerViewRight.setRecycledViewPool(abVar.m3735a());
        this.mRecyclerViewLeft.setNestedScrollingEnabled(false);
        this.mRecyclerViewRight.setNestedScrollingEnabled(false);
        Context context = viewGroup.getContext();
        this.mRecyclerViewLeft.setLayoutManager(new LinearLayoutManager(context));
        this.mRecyclerViewRight.setLayoutManager(new LinearLayoutManager(context));
        this.mLeftAdapter = new MultiShopCartGroupAdapter(abVar);
        this.mRightAdapter = new MultiShopCartGroupAdapter(abVar);
        this.mRecyclerViewLeft.setAdapter(this.mLeftAdapter);
        this.mRecyclerViewRight.setAdapter(this.mRightAdapter);
    }

    public void bind(CartGroup cartGroup) {
        this.mLeftAdapter.clearData();
        this.mRightAdapter.clearData();
        this.mLeftAdapter.addCartGroupItems(cartGroup.getItems(), getAdapterPosition());
        this.mRightAdapter.addCartGroupItems(cartGroup.getPaymentItems(), getAdapterPosition());
    }
}
