package com.etsy.android.uikit.view;

import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

/* renamed from: com.etsy.android.uikit.view.o */
class TaggableImageView extends Callback {
    final /* synthetic */ TaggableImageView f4271a;

    private TaggableImageView(TaggableImageView taggableImageView) {
        this.f4271a = taggableImageView;
    }

    public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
        view.setX((float) i);
        view.setY((float) i2);
    }

    public void onViewCaptured(View view, int i) {
        view.setPressed(true);
        if (!this.f4271a.isReadOnly()) {
            this.f4271a.setIsTagDragging(true);
            this.f4271a.mAdapter.onStartDrag((ImageView) view);
            view.animate().setDuration(300).scaleX(1.3f).scaleY(1.3f).setListener(new TaggableImageView(null)).start();
        }
    }

    public void onViewReleased(View view, float f, float f2) {
        view.setLayoutParams(this.f4271a.getLayoutParamsAt((int) view.getX(), (int) view.getY()));
        view.setPressed(false);
        if (!this.f4271a.isReadOnly()) {
            this.f4271a.mAdapter.onEndDrag((ImageView) view);
            view.animate().setDuration(300).setInterpolator(new OvershootInterpolator(1.5f)).scaleX(FullImageView.ASPECT_RATIO_SQUARE).scaleY(FullImageView.ASPECT_RATIO_SQUARE).setListener(new TaggableImageView(null)).start();
            this.f4271a.setIsTagDragging(false);
            if (this.f4271a.mListener != null) {
                this.f4271a.mListener.m3664a(view, (TaggableImageView) this.f4271a.mAdapter.getItem(this.f4271a.mContainer.indexOfChild(view)), view.getLeft(), view.getTop());
            }
        }
    }

    public int clampViewPositionHorizontal(View view, int i, int i2) {
        return this.f4271a.getConstrainedTagXCoordinate(view.getWidth(), i);
    }

    public int clampViewPositionVertical(View view, int i, int i2) {
        return this.f4271a.getConstrainedTagYCoordinate(view.getHeight(), i);
    }

    public boolean tryCaptureView(View view, int i) {
        return true;
    }
}
