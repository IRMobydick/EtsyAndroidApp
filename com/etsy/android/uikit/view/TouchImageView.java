package com.etsy.android.uikit.view;

import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;

/* renamed from: com.etsy.android.uikit.view.v */
class TouchImageView extends SimpleOnScaleGestureListener {
    final /* synthetic */ TouchImageView f4284a;

    private TouchImageView(TouchImageView touchImageView) {
        this.f4284a = touchImageView;
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        this.f4284a.mode = 2;
        return true;
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        float f = this.f4284a.saveScale;
        TouchImageView touchImageView = this.f4284a;
        touchImageView.saveScale *= scaleFactor;
        if (this.f4284a.saveScale > this.f4284a.maxScale) {
            this.f4284a.saveScale = this.f4284a.maxScale;
            scaleFactor = this.f4284a.maxScale / f;
        } else if (this.f4284a.saveScale < this.f4284a.minScale) {
            this.f4284a.saveScale = this.f4284a.minScale;
            scaleFactor = this.f4284a.minScale / f;
        }
        if (this.f4284a.origWidth * this.f4284a.saveScale <= ((float) this.f4284a.viewWidth) || this.f4284a.origHeight * this.f4284a.saveScale <= ((float) this.f4284a.viewHeight)) {
            this.f4284a.matrix.postScale(scaleFactor, scaleFactor, (float) (this.f4284a.viewWidth / 2), (float) (this.f4284a.viewHeight / 2));
        } else {
            this.f4284a.matrix.postScale(scaleFactor, scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
        }
        this.f4284a.fixTrans();
        return true;
    }
}
