package com.etsy.android.ui.local;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseBaseHeaderViewHolder;
import com.etsy.android.uikit.adapter.SectionedRecyclerViewAdapter;

/* renamed from: com.etsy.android.ui.local.f */
class LocalBrowseListFragment extends SectionedRecyclerViewAdapter {
    final /* synthetic */ LocalBrowseListFragment f3068a;

    public LocalBrowseListFragment(LocalBrowseListFragment localBrowseListFragment, Context context, ImageBatch imageBatch) {
        this.f3068a = localBrowseListFragment;
        super(context, imageBatch);
    }

    public ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
        return new LocalBrowseListFragment(this.f3068a, this.mInflater, viewGroup);
    }

    public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
        ((LocalBrowseBaseHeaderViewHolder) viewHolder).bind(true);
    }
}
