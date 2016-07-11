package com.etsy.android.uikit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.ImageBatch;

public abstract class TypedViewHolderRecyclerViewAdapter<T, K extends ViewHolder> extends BaseRecyclerViewAdapter<T> {
    protected abstract void onBindTypedViewHolder(K k, int i);

    protected abstract K onCreateListItemViewHolder(ViewGroup viewGroup, int i);

    protected TypedViewHolderRecyclerViewAdapter(Context context, ImageBatch imageBatch) {
        super(context, imageBatch);
    }

    protected final void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        onBindTypedViewHolder(viewHolder, i);
    }
}
