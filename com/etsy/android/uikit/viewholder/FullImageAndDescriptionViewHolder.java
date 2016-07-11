package com.etsy.android.uikit.viewholder;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.uikit.view.FullImageView;

public class FullImageAndDescriptionViewHolder extends BaseViewHolder<c> {
    public static final int IMAGE_TYPE_DRAWABLE = 2;
    public static final int IMAGE_TYPE_URL = 1;
    public final TextView mDescription;
    public final FullImageView mImage;
    public final ImageBatch mImageBatch;

    public FullImageAndDescriptionViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup, @LayoutRes int i, @IdRes int i2, @IdRes int i3, @NonNull ImageBatch imageBatch) {
        super(layoutInflater.inflate(i, viewGroup, false));
        this.mImage = (FullImageView) this.itemView.findViewById(i2);
        this.mDescription = (TextView) this.itemView.findViewById(i3);
        this.mImageBatch = imageBatch;
    }

    public void bind(c cVar) {
        this.mImage.cleanUp();
        this.mImage.setScaleType(cVar.getScaleType());
        this.mImage.setHeightRatio(cVar.getHeightRatio());
        if (cVar.getImageType() == IMAGE_TYPE_DRAWABLE) {
            this.mImage.setImageDrawable(cVar.getDrawable());
        } else {
            this.mImage.setImageInfo(cVar.getImage(), this.mImageBatch, cVar.getImageShape());
        }
        this.mDescription.setText(cVar.getDescription());
        this.mDescription.setHint(cVar.getHint());
    }
}
