package com.etsy.android.ui.cardview.viewholders;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.models.cardviewelement.BasicSectionHeader;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class LeftAlignedAllCapsHeaderViewHolder extends BaseViewHolder<BasicSectionHeader> {
    private TextView mTextView;

    public LeftAlignedAllCapsHeaderViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super(layoutInflater.inflate(2130903270, viewGroup, false));
        this.mTextView = (TextView) this.itemView.findViewById(2131755900);
    }

    public void bind(BasicSectionHeader basicSectionHeader) {
        this.mTextView.setText(basicSectionHeader.getTitle());
    }
}
