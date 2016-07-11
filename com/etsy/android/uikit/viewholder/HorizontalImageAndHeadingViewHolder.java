package com.etsy.android.uikit.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.uikit.text.ClickableSpanTouchListener;
import com.google.android.gms.gcm.Task;

public class HorizontalImageAndHeadingViewHolder extends BaseViewHolder<d> {
    private final TextView mHeading;
    private final ImageView mImage;
    private final ImageBatch mImageBatch;
    private final int mImageSize;

    public HorizontalImageAndHeadingViewHolder(@NonNull View view, @NonNull ImageBatch imageBatch) {
        super(view);
        this.mImage = (ImageView) view.findViewById(R.image);
        TextView textView = (TextView) view.findViewById(R.heading);
        textView.setOnTouchListener(new ClickableSpanTouchListener());
        this.mHeading = textView;
        this.mImageBatch = imageBatch;
        this.mImageSize = (int) view.getResources().getDimension(R.shop2_horizontal_circular_image_size);
    }

    public void bind(@NonNull d dVar) {
        OnClickListener headingClickListener = dVar.getHeadingClickListener();
        this.mImage.setOnClickListener(headingClickListener);
        switch (dVar.getType()) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                this.mImageBatch.m1575b(dVar.getImageUrl(), this.mImage, this.mImageSize, BaseModelImage.DEFAULT_LOADING_COLOR);
                break;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                this.mImage.setImageDrawable(dVar.getDrawable());
                break;
        }
        ((LayoutParams) this.mHeading.getLayoutParams()).gravity = dVar.getHeadingGravity();
        this.mHeading.setText(dVar.getHeading());
        this.mHeading.setOnClickListener(headingClickListener);
        this.itemView.setBackgroundColor(dVar.getBackgroundColor());
    }

    public void recycle() {
        this.mImage.setImageDrawable(null);
        this.mHeading.setText(null);
    }
}
