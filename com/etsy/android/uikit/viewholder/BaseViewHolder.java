package com.etsy.android.uikit.viewholder;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import com.etsy.android.lib.logger.AnalyticsTracker;
import com.etsy.android.uikit.util.AdHocEventCompatBuilder;

public class BaseViewHolder<T> extends ViewHolder {
    protected AnalyticsTracker mViewTracker;

    public BaseViewHolder(View view) {
        super(view);
        this.mViewTracker = AdHocEventCompatBuilder.m5508c(view);
    }

    public View getRootView() {
        return this.itemView;
    }

    public View findViewById(int i) {
        return this.itemView.findViewById(i);
    }

    public void bind(T t) {
    }

    public void recycle() {
    }
}
