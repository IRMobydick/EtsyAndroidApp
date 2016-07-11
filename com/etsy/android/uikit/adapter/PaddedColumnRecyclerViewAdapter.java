package com.etsy.android.uikit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.uikit.viewholder.EmptyHolder;

public abstract class PaddedColumnRecyclerViewAdapter<T> extends BaseRecyclerViewAdapter<T> {
    protected static final int VIEW_TYPE_EMPTY_COLUMN = 601;
    protected static final int VIEW_TYPE_TITLE_COLUMN = 600;
    protected final int mColumns;
    private boolean mPadOutToFillRows;
    protected final boolean mShowLeftTitleColumn;

    protected abstract void bindCoreItemViewType(ViewHolder viewHolder, int i);

    protected abstract void bindLeftTitleItemViewType(ViewHolder viewHolder, int i);

    protected abstract int getCoreItemViewType(int i);

    protected abstract ViewHolder onCreateCoreItemViewHolder(ViewGroup viewGroup, int i);

    protected abstract ViewHolder onCreateLeftTitleItemViewHolder(ViewGroup viewGroup, int i);

    protected PaddedColumnRecyclerViewAdapter(Context context, ImageBatch imageBatch, int i, boolean z, boolean z2) {
        super(context, imageBatch);
        boolean z3 = z && i < 2;
        EtsyDebug.m1902a(z3, new IllegalArgumentException("If using LeftTitleColumnRecyclerViewAdapter with left column enabled, must have at least 2 columns. Using 1 column will fail to show any items."));
        this.mColumns = i;
        this.mShowLeftTitleColumn = z;
        this.mPadOutToFillRows = z2;
    }

    protected int getListItemViewType(int i) {
        int headerCount = i - getHeaderCount();
        if (this.mShowLeftTitleColumn && headerCount == 0) {
            return VIEW_TYPE_TITLE_COLUMN;
        }
        if (this.mShowLeftTitleColumn && headerCount % this.mColumns == 0) {
            return VIEW_TYPE_EMPTY_COLUMN;
        }
        if (!this.mPadOutToFillRows || headerCount < this.mItems.size() + getExtraLeftColumnCountForItemsSize(this.mItems.size())) {
            return getCoreItemViewType(i);
        }
        return VIEW_TYPE_EMPTY_COLUMN;
    }

    public int getDataItemCount() {
        int size = this.mItems.size();
        if (this.mShowLeftTitleColumn) {
            size += getExtraLeftColumnCountForItemsSize(size);
        }
        if (!this.mPadOutToFillRows || size <= 0 || size % this.mColumns <= 0) {
            return size;
        }
        return (this.mColumns + size) - (size - (((int) Math.floor((double) (size / this.mColumns))) * this.mColumns));
    }

    private int getExtraLeftColumnCountForItemsSize(int i) {
        if (!this.mShowLeftTitleColumn) {
            return 0;
        }
        int i2 = this.mColumns - 1;
        int i3 = i / i2;
        if (i % i2 > 0) {
            return i3 + 1;
        }
        return i3;
    }

    private int getExtraLeftColumnCountForPosition(int i) {
        if (!this.mShowLeftTitleColumn) {
            return 0;
        }
        int headerCount = i - getHeaderCount();
        int i2 = headerCount / this.mColumns;
        if (headerCount % this.mColumns > 0) {
            return i2 + 1;
        }
        return i2;
    }

    public int getSpanSize(int i, int i2) {
        switch (getItemViewType(i)) {
            case VIEW_TYPE_TITLE_COLUMN /*600*/:
            case VIEW_TYPE_EMPTY_COLUMN /*601*/:
                return i2 / this.mColumns;
            default:
                return super.getSpanSize(i, i2);
        }
    }

    public T getItem(int i) {
        if (getHeaderCount() > 0 && i < getHeaderCount()) {
            return null;
        }
        if (getFooterCount() > 0 && i > (getDataItemCount() + getHeaderCount()) - 1) {
            return null;
        }
        if (!this.mShowLeftTitleColumn && !this.mPadOutToFillRows) {
            return this.mItems.get(i - getHeaderCount());
        }
        switch (getItemViewType(i)) {
            case VIEW_TYPE_TITLE_COLUMN /*600*/:
            case VIEW_TYPE_EMPTY_COLUMN /*601*/:
                return null;
            default:
                return super.getItem(i - getExtraLeftColumnCountForPosition(i));
        }
    }

    protected ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case VIEW_TYPE_TITLE_COLUMN /*600*/:
                return onCreateLeftTitleItemViewHolder(viewGroup, i);
            case VIEW_TYPE_EMPTY_COLUMN /*601*/:
                return new EmptyHolder(this.mContext);
            default:
                return onCreateCoreItemViewHolder(viewGroup, i);
        }
    }

    protected void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case VIEW_TYPE_TITLE_COLUMN /*600*/:
                bindLeftTitleItemViewType(viewHolder, i);
            case VIEW_TYPE_EMPTY_COLUMN /*601*/:
            default:
                bindCoreItemViewType(viewHolder, i);
        }
    }
}
