package com.etsy.android.ui.local.marketdetails;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;

/* renamed from: com.etsy.android.ui.local.marketdetails.d */
class LocalDetailsFooterDisclaimerViewHolder extends ViewHolder {
    View f3110a;
    TextView f3111b;

    public LocalDetailsFooterDisclaimerViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        super(layoutInflater.inflate(2130903183, viewGroup, false));
        this.f3110a = this.itemView.findViewById(2131755599);
        this.f3111b = (TextView) this.itemView.findViewById(2131755600);
    }

    public void m4353a(boolean z) {
        this.f3110a.setVisibility(z ? 8 : 0);
        this.f3111b.setText(z ? R.store_items_pictured_disclaimer : R.event_items_pictured_disclaimer);
    }
}
