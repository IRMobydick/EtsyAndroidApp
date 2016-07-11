package com.etsy.android.uikit.adapter;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import com.etsy.android.lib.core.img.ImageDownloadListener;

/* renamed from: com.etsy.android.uikit.adapter.l */
class ImagesPagerAdapter extends ImageDownloadListener {
    private View f3937a;
    private View f3938b;
    private boolean f3939c;

    public ImagesPagerAdapter(ImageView imageView, View view, View view2, Boolean bool) {
        super(imageView);
        this.f3937a = view;
        this.f3938b = view2;
        this.f3939c = bool.booleanValue();
    }

    public void m5321a(Bitmap bitmap, boolean z) {
        super.m1579a(bitmap, z);
        this.f3937a.setVisibility(8);
        if (this.f3939c && this.f3938b != null) {
            this.f3938b.setVisibility(0);
        }
    }
}
