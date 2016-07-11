package com.etsy.android.ui.local;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseBaseHeaderViewHolder;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseMarketViewHolder;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseSectionFooterViewHolder;

/* renamed from: com.etsy.android.ui.local.r */
class LocalSearchListFragment extends LocalBrowseAdapter {
    final /* synthetic */ LocalSearchListFragment f3167a;

    public LocalSearchListFragment(LocalSearchListFragment localSearchListFragment, Context context, ImageBatch imageBatch, @NonNull LocalBrowseMarketViewHolder localBrowseMarketViewHolder, @NonNull LocalBrowseSectionFooterViewHolder localBrowseSectionFooterViewHolder) {
        this.f3167a = localSearchListFragment;
        super(context, imageBatch, localBrowseMarketViewHolder, localBrowseSectionFooterViewHolder);
    }

    public ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
        return new LocalBrowseBaseHeaderViewHolder(this.mInflater, viewGroup, this.f3167a.mHeaderListener);
    }

    public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
        ((LocalBrowseBaseHeaderViewHolder) viewHolder).bind(false);
    }
}
