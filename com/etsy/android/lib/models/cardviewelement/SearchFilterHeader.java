package com.etsy.android.lib.models.cardviewelement;

import org.parceler.Parcel;

@Parcel
public class SearchFilterHeader extends BasicSectionHeader {
    protected String mActionTitle;

    public SearchFilterHeader(String str) {
        this.mActionTitle = str;
    }

    public String getActionTitle() {
        return this.mActionTitle;
    }

    public void setActionTitle(String str) {
        this.mActionTitle = str;
    }

    public int getViewType() {
        return 13;
    }
}
