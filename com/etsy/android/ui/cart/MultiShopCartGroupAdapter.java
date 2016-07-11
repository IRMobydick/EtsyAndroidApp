package com.etsy.android.ui.cart;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.ViewGroup;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.IMultiShopCartElement;
import com.etsy.android.uikit.cardview.IBaseRecyclerViewElement;
import com.etsy.android.uikit.viewholder.BaseViewHolder;
import java.util.ArrayList;
import java.util.List;

public class MultiShopCartGroupAdapter extends Adapter<BaseViewHolder<IMultiShopCartElement>> {
    protected ArrayList<IMultiShopCartElement> mItems;
    protected ab mViewHolderFactory;

    public MultiShopCartGroupAdapter(ab abVar) {
        this.mItems = new ArrayList();
        this.mViewHolderFactory = abVar;
    }

    public void addCartGroupItems(List<CartGroupItem> list, int i) {
        int size = this.mItems.size();
        for (CartGroupItem cartGroupItem : list) {
            cartGroupItem.setCartAdapterPosition(i);
            this.mItems.add(cartGroupItem);
        }
        notifyItemRangeInserted(size, list.size());
    }

    public BaseViewHolder<IMultiShopCartElement> onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.mViewHolderFactory.m3736a(viewGroup, i);
    }

    public void onBindViewHolder(BaseViewHolder<IMultiShopCartElement> baseViewHolder, int i) {
        baseViewHolder.bind(this.mItems.get(i));
    }

    public int getItemViewType(int i) {
        return this.mViewHolderFactory.m3642a((IBaseRecyclerViewElement) (IMultiShopCartElement) this.mItems.get(i));
    }

    public int getItemCount() {
        return this.mItems.size();
    }

    public void clearData() {
        this.mItems.clear();
        notifyItemRangeRemoved(0, this.mItems.size());
    }
}
