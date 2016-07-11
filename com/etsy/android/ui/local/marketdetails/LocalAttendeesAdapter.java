package com.etsy.android.ui.local.marketdetails;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.PluralsRes;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.Attendee;
import com.etsy.android.ui.cardview.viewholders.LocalAttendeeShopViewHolder;
import com.etsy.android.uikit.adapter.PaddedColumnRecyclerViewAdapter;
import com.etsy.android.uikit.util.TabletSupportUtil;
import com.etsy.android.uikit.viewholder.TextViewHolder;
import com.foresee.mobileReplay.recorder.ScreenRecorder;
import com.google.android.gms.gcm.Task;

/* renamed from: com.etsy.android.ui.local.marketdetails.a */
class LocalAttendeesAdapter extends PaddedColumnRecyclerViewAdapter<Attendee> {
    private int f3106a;
    private LocalAttendeeShopViewHolder f3107b;

    protected LocalAttendeesAdapter(Context context, ImageBatch imageBatch, @NonNull LocalAttendeeShopViewHolder localAttendeeShopViewHolder) {
        super(context, imageBatch, context.getResources().getInteger(2131558425), new TabletSupportUtil(context).m5626f(), true);
        this.f3107b = localAttendeeShopViewHolder;
    }

    public void m4350a(@PluralsRes int i) {
        this.f3106a = i;
        addHeader(ScreenRecorder.ORIENTATION_SAMPLING_FREQ);
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

    protected ViewHolder onCreateLeftTitleItemViewHolder(ViewGroup viewGroup, int i) {
        return new TextViewHolder(this.mInflater, viewGroup, 2130903469, R.title);
    }

    protected ViewHolder onCreateCoreItemViewHolder(ViewGroup viewGroup, int i) {
        return new LocalAttendeeShopViewHolder(this.mInflater, viewGroup, this.f3107b);
    }

    public ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
        return new TextViewHolder(this.mInflater, viewGroup, 2130903471, R.title);
    }

    public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
        TextView textView = ((TextViewHolder) viewHolder).getTextView();
        if (this.mShowLeftTitleColumn) {
            textView.setVisibility(8);
            return;
        }
        textView.setText(this.mContext.getResources().getQuantityString(this.f3106a, this.mItems.size(), new Object[]{Integer.valueOf(this.mItems.size())}));
    }

    protected void bindLeftTitleItemViewType(ViewHolder viewHolder, int i) {
        ((TextViewHolder) viewHolder).getTextView().setText(this.mContext.getResources().getQuantityString(this.f3106a, this.mItems.size(), new Object[]{Integer.valueOf(this.mItems.size())}));
    }

    protected void bindCoreItemViewType(ViewHolder viewHolder, int i) {
        ((LocalAttendeeShopViewHolder) viewHolder).bind(this.mImageBatch, (Attendee) getItem(i));
    }
}
