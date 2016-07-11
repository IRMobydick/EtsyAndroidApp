package com.etsy.android.ui.local.marketdetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.uikit.adapter.PaddedColumnRecyclerViewAdapter;
import com.etsy.android.uikit.util.TabletSupportUtil;
import com.etsy.android.uikit.viewholder.TextViewHolder;
import com.google.android.gms.gcm.Task;

/* renamed from: com.etsy.android.ui.local.marketdetails.i */
class LocalProductCategoryAdapter extends PaddedColumnRecyclerViewAdapter<String> {
    private int f3156a;

    public LocalProductCategoryAdapter(Context context, ImageBatch imageBatch) {
        super(context, imageBatch, context.getResources().getInteger(2131558427), new TabletSupportUtil(context).m5626f(), true);
        this.f3156a = context.getResources().getInteger(2131558427);
    }

    public int getSpanSize(int i, int i2) {
        switch (getItemViewType(i)) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                return i2 / this.f3156a;
            default:
                return super.getSpanSize(i, i2);
        }
    }

    protected int getCoreItemViewType(int i) {
        return 0;
    }

    public ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
        return new TextViewHolder(this.mInflater, viewGroup, 2130903471, R.title);
    }

    protected ViewHolder onCreateLeftTitleItemViewHolder(ViewGroup viewGroup, int i) {
        return new TextViewHolder(this.mInflater, viewGroup, 2130903469, R.title);
    }

    protected ViewHolder onCreateCoreItemViewHolder(ViewGroup viewGroup, int i) {
        return new TextViewHolder(this.mInflater, viewGroup, 2130903335, R.text);
    }

    public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
        TextView textView = ((TextViewHolder) viewHolder).getTextView();
        if (this.mShowLeftTitleColumn) {
            textView.setVisibility(8);
        } else {
            textView.setText(R.this_store_sells);
        }
    }

    protected void bindLeftTitleItemViewType(ViewHolder viewHolder, int i) {
        ((TextViewHolder) viewHolder).getTextView().setText(R.this_store_sells);
    }

    protected void bindCoreItemViewType(ViewHolder viewHolder, int i) {
        ((TextViewHolder) viewHolder).getTextView().setText((CharSequence) getItem(i));
    }
}
