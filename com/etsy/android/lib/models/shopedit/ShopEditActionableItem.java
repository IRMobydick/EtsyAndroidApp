package com.etsy.android.lib.models.shopedit;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;

public interface ShopEditActionableItem {
    void editActionInitiated(int i, @NonNull ShopEditDelegate shopEditDelegate, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter);

    boolean isActionEnabled();

    void updateWithEditResult(@NonNull ShopEditDelegate shopEditDelegate, @NonNull RecyclerView recyclerView, @NonNull BaseRecyclerViewAdapter<ShopEditPresentationItem> baseRecyclerViewAdapter, int i, int i2, @NonNull Intent intent, @NonNull Context context, int i3);
}
