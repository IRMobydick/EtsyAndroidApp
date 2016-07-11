package com.etsy.android.ui.cardview.viewholders;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.cardview.ICardViewElement;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class LoadingCardViewHolder extends BaseViewHolder<ICardViewElement> {
    public LoadingCardViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super(layoutInflater.inflate(R.list_item_loading, viewGroup, false));
    }
}
