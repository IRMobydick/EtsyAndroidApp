package com.etsy.android.ui.local.marketdetails;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.LocalMarketCard;
import com.etsy.android.ui.cardview.viewholders.LocalInStoreEventViewHolder;
import com.etsy.android.uikit.adapter.PaddedColumnRecyclerViewAdapter;
import com.etsy.android.uikit.util.TabletSupportUtil;
import com.etsy.android.uikit.viewholder.TextViewHolder;
import com.google.android.gms.gcm.Task;

public class LocalInStoreEventsAdapter extends PaddedColumnRecyclerViewAdapter<LocalMarketCard> {
    private static final int VIEW_TYPE_EVENT = 0;
    private LocalInStoreEventViewHolder mMarketListener;

    public LocalInStoreEventsAdapter(Context context, ImageBatch imageBatch, @NonNull LocalInStoreEventViewHolder localInStoreEventViewHolder) {
        super(context, imageBatch, context.getResources().getInteger(2131558428), new TabletSupportUtil(context).m5626f(), true);
        this.mMarketListener = localInStoreEventViewHolder;
    }

    protected int getCoreItemViewType(int i) {
        return 0;
    }

    public int getSpanSize(int i, int i2) {
        switch (getItemViewType(i)) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                return i2 / this.mColumns;
            default:
                return super.getSpanSize(i, i2);
        }
    }

    public ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
        return new TextViewHolder(this.mInflater, viewGroup, 2130903471, R.title);
    }

    protected ViewHolder onCreateLeftTitleItemViewHolder(ViewGroup viewGroup, int i) {
        return new TextViewHolder(this.mInflater, viewGroup, 2130903469, R.title);
    }

    protected ViewHolder onCreateCoreItemViewHolder(ViewGroup viewGroup, int i) {
        return new LocalInStoreEventViewHolder(this.mInflater, viewGroup, this.mMarketListener);
    }

    public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
        TextView textView = ((TextViewHolder) viewHolder).getTextView();
        if (this.mShowLeftTitleColumn) {
            textView.setVisibility(8);
        } else {
            textView.setText(this.mContext.getResources().getString(R.upcoming_in_store_events));
        }
    }

    protected void bindLeftTitleItemViewType(ViewHolder viewHolder, int i) {
        ((TextViewHolder) viewHolder).getTextView().setText(this.mContext.getResources().getString(R.upcoming_in_store_events));
    }

    protected void bindCoreItemViewType(ViewHolder viewHolder, int i) {
        ((LocalInStoreEventViewHolder) viewHolder).bind(this.mImageBatch, (LocalMarketCard) getItem(i));
    }
}
