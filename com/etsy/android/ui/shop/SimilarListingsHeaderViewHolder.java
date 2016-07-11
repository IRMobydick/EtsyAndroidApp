package com.etsy.android.ui.shop;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.LinearLayout;

public class SimilarListingsHeaderViewHolder extends ViewHolder {
    public SimilarListingsHeaderViewHolder(View view) {
        super(view);
    }

    public void bind(boolean z, boolean z2) {
        LinearLayout linearLayout = (LinearLayout) this.itemView;
        if (z) {
            linearLayout.setShowDividers(1);
        } else {
            linearLayout.setShowDividers(0);
        }
        if (z2) {
            linearLayout.setGravity(17);
        } else {
            linearLayout.setGravity(16);
        }
    }
}
