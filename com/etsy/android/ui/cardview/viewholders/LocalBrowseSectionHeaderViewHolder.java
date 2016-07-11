package com.etsy.android.ui.cardview.viewholders;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.apiv3.LocalBrowseModule;

public class LocalBrowseSectionHeaderViewHolder extends ViewHolder {
    private TextView mTitle;

    public LocalBrowseSectionHeaderViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super(layoutInflater.inflate(2130903470, viewGroup, false));
        this.mTitle = (TextView) this.itemView.findViewById(R.title);
    }

    public void bind(LocalBrowseModule localBrowseModule) {
        if (localBrowseModule != null) {
            this.mTitle.setText(localBrowseModule.getTitle());
        }
    }
}
