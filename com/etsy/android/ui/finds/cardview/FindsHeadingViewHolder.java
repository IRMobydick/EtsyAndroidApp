package com.etsy.android.ui.finds.cardview;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.etsy.android.lib.models.finds.FindsHeadingModule;
import com.etsy.android.uikit.view.GuardianTextView;
import com.etsy.android.uikit.viewholder.BaseViewHolder;

public class FindsHeadingViewHolder extends BaseViewHolder<FindsHeadingModule> {
    private final GuardianTextView mHeadingTextView;

    public FindsHeadingViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super(layoutInflater.inflate(2130903180, viewGroup, false));
        this.mHeadingTextView = (GuardianTextView) findViewById(2131755597);
    }

    public void bind(FindsHeadingModule findsHeadingModule) {
        this.mHeadingTextView.setText(findsHeadingModule.getText());
    }
}
