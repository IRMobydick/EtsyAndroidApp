package com.etsy.android.lib.models.shopedit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.uikit.viewholder.i;
import com.etsy.android.uikit.viewholder.j;
import org.parceler.Parcel;

@Parcel
public class ShopEditSectionTextRow implements ShopEditPresentationItem, i {
    @NonNull
    CharSequence mTitle;
    int mViewType;

    ShopEditSectionTextRow() {
    }

    public ShopEditSectionTextRow(@NonNull CharSequence charSequence, int i) {
        this.mTitle = charSequence;
        this.mViewType = i;
    }

    @Nullable
    public j getListener() {
        return null;
    }

    @Nullable
    public CharSequence getText() {
        return this.mTitle;
    }

    public int getViewType() {
        return this.mViewType;
    }

    public void restoreComplexStateIfNecessary(@NonNull Context context) {
    }
}
