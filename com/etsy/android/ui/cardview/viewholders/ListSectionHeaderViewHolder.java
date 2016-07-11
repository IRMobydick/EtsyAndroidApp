package com.etsy.android.ui.cardview.viewholders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.models.cardviewelement.BasicSectionHeader;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class ListSectionHeaderViewHolder extends BaseViewHolder<BasicSectionHeader> {
    TextView mTextSubtitle;
    TextView mTextTitle;

    public ListSectionHeaderViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super(layoutInflater.inflate(2130903303, viewGroup, false));
        this.mTextTitle = (TextView) findViewById(2131755965);
        this.mTextSubtitle = (TextView) findViewById(2131755966);
    }

    public void bind(BasicSectionHeader basicSectionHeader) {
        if (TextUtils.isEmpty(basicSectionHeader.getTitle())) {
            this.mTextTitle.setVisibility(8);
        } else {
            this.mTextTitle.setText(basicSectionHeader.getTitle());
            this.mTextTitle.setVisibility(0);
        }
        if (TextUtils.isEmpty(basicSectionHeader.getSubtitle())) {
            this.mTextSubtitle.setVisibility(8);
            return;
        }
        this.mTextSubtitle.setVisibility(0);
        this.mTextSubtitle.setText(basicSectionHeader.getSubtitle());
    }
}
