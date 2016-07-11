package com.etsy.android.uikit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.ImageBatch;
import com.hannesdorfmann.adapterdelegates2.b;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewItemAdapter extends BaseRecyclerViewAdapter<Object> {
    protected b<List<Object>> delegatesManager;
    protected EndlessRecyclerViewAdapter mScrollLoadTriggerListener;

    public RecyclerViewItemAdapter(Context context, ImageBatch imageBatch) {
        this(context, imageBatch, new b());
    }

    public RecyclerViewItemAdapter(Context context, ImageBatch imageBatch, @NonNull b<List<Object>> bVar) {
        super(context, imageBatch);
        this.delegatesManager = bVar;
    }

    public ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        return this.delegatesManager.a(viewGroup, i);
    }

    public ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
        return this.delegatesManager.a(viewGroup, i);
    }

    public ViewHolder onCreateFooterViewHolder(ViewGroup viewGroup, int i) {
        return this.delegatesManager.a(viewGroup, i);
    }

    public void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        this.delegatesManager.a(this.mItems, i, viewHolder);
    }

    public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
        this.delegatesManager.a(new ArrayList(this.mHeaders), i, viewHolder);
    }

    public void onBindFooterViewHolder(ViewHolder viewHolder, int i) {
        this.delegatesManager.a(new ArrayList(this.mFooters), i, viewHolder);
    }

    public int getListItemViewType(int i) {
        if (this.mHeaders.size() > 0 && i < this.mHeaders.size()) {
            return this.delegatesManager.a(new ArrayList(this.mHeaders), i);
        }
        if (this.mFooters.size() <= 0 || i <= (getDataItemCount() - 1) + this.mHeaders.size()) {
            return this.delegatesManager.a(this.mItems, i);
        }
        return this.delegatesManager.a(new ArrayList(this.mFooters), i);
    }

    public int getItemCount() {
        return this.mItems == null ? 0 : (this.mItems.size() + this.mFooters.size()) + this.mHeaders.size();
    }

    protected final void onPostBindViewHolder(int i) {
        if (this.mScrollLoadTriggerListener != null && i == getItemCount() - this.mScrollLoadTriggerListener.getLoadTriggerPosition()) {
            this.mScrollLoadTriggerListener.onScrolledToLoadTrigger();
        }
    }

    public void setScrollLoadTriggerListener(EndlessRecyclerViewAdapter endlessRecyclerViewAdapter) {
        this.mScrollLoadTriggerListener = endlessRecyclerViewAdapter;
    }
}
