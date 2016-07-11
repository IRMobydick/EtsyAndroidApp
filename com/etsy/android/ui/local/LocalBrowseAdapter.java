package com.etsy.android.ui.local;

import android.content.Context;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.viewholder.EmptyHolder;

/* renamed from: com.etsy.android.ui.local.b */
class LocalBrowseAdapter extends EmptyHolder {
    public LocalBrowseAdapter(Context context) {
        super(context);
        this.itemView.setBackgroundColor(context.getResources().getColor(R.background_main_v2));
    }
}
