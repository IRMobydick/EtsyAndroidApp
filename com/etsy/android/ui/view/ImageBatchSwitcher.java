package com.etsy.android.ui.view;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.etsy.android.lib.core.img.ImageDownloadListener;

/* renamed from: com.etsy.android.ui.view.a */
class ImageBatchSwitcher extends ImageDownloadListener {
    final /* synthetic */ ImageBatchSwitcher f3846a;

    public ImageBatchSwitcher(ImageBatchSwitcher imageBatchSwitcher) {
        this.f3846a = imageBatchSwitcher;
        super(null);
    }

    public void m5271a(Bitmap bitmap, boolean z) {
        super.m1579a(bitmap, z);
        this.f3846a.mBitmapDrawables.add(new BitmapDrawable(this.f3846a.getResources(), bitmap));
        if (this.f3846a.mBitmapDrawables.size() == 1) {
            this.f3846a.setImageDrawable((BitmapDrawable) this.f3846a.mBitmapDrawables.get(0));
            this.f3846a.mCurrentImage = 1;
        }
        this.f3846a.runLoop();
    }
}
