package com.etsy.android.uikit.adapter;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.BaseModel;

@Deprecated
public abstract class CollapsibleBaseArrayAdapter<T extends BaseModel> extends BaseModelArrayAdapter<T> {
    static final int VIEW_COLLAPSED = 1;
    static final int VIEW_NORMAL = 0;
    private static final int VISIBLE_ITEMS_DEFAULT = 1;
    private boolean mIsCollapsed;
    private int mVisibleItems;

    public abstract View getCollapsedView(int i, View view, ViewGroup viewGroup);

    public abstract View getNormalView(int i, View view, ViewGroup viewGroup);

    public CollapsibleBaseArrayAdapter(FragmentActivity fragmentActivity, int i, ImageBatch imageBatch) {
        super(fragmentActivity, i, imageBatch);
        this.mIsCollapsed = false;
        this.mVisibleItems = VISIBLE_ITEMS_DEFAULT;
    }

    public void setVisibleItemsCount(int i) {
        this.mVisibleItems = i;
        notifyDataSetChanged();
    }

    public void setCollapsed(boolean z) {
        this.mIsCollapsed = z;
        notifyDataSetChanged();
    }

    public boolean isCollapsed() {
        return this.mIsCollapsed && isCollapsible();
    }

    public boolean isCollapsible() {
        return super.getCount() > this.mVisibleItems + VISIBLE_ITEMS_DEFAULT;
    }

    private boolean isCollapsedView(int i) {
        if (isCollapsed() && i == 0) {
            return true;
        }
        return false;
    }

    public boolean isValidPosition(int i) {
        return super.isValidPosition(i);
    }

    public int getCount() {
        if (isCollapsed()) {
            return this.mVisibleItems + VISIBLE_ITEMS_DEFAULT;
        }
        return super.getCount();
    }

    public int getTotalItemCount() {
        return super.getCount();
    }

    public int getCollapsedItemCount() {
        if (isCollapsed()) {
            return super.getCount() - this.mVisibleItems;
        }
        return VIEW_NORMAL;
    }

    public int getPosition(T t) {
        return super.getPosition(t);
    }

    public T getItem(int i) {
        if (!isCollapsed()) {
            return super.getItem(i);
        }
        switch (i) {
            case VIEW_NORMAL /*0*/:
                return null;
            default:
                return super.getItem((super.getCount() - 1) - (this.mVisibleItems - i));
        }
    }

    public int getItemViewType(int i) {
        return isCollapsedView(i) ? VISIBLE_ITEMS_DEFAULT : VIEW_NORMAL;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        switch (getItemViewType(i)) {
            case VISIBLE_ITEMS_DEFAULT /*1*/:
                return getCollapsedView(i, view, viewGroup);
            default:
                return getNormalView(i, view, viewGroup);
        }
    }
}
