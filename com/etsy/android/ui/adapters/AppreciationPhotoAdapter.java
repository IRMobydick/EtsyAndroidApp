package com.etsy.android.ui.adapters;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.apiv3.AppreciationPhoto;
import com.etsy.android.ui.user.profile.viewholders.AppreciationPhotoCardViewHolder;
import com.etsy.android.uikit.adapter.EndlessRecyclerViewAdapter;

public class AppreciationPhotoAdapter extends EndlessRecyclerViewAdapter<AppreciationPhoto> {
    public AppreciationPhotoAdapter(FragmentActivity fragmentActivity, ImageBatch imageBatch) {
        super(fragmentActivity, imageBatch);
    }

    protected int getListItemViewType(int i) {
        return 0;
    }

    public ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        return new AppreciationPhotoCardViewHolder(this.mInflater.inflate(2130903546, viewGroup, false), false, "appreciation_photo_list");
    }

    protected void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        ((AppreciationPhotoCardViewHolder) viewHolder).bind((AppreciationPhoto) this.mItems.get(i));
    }
}
