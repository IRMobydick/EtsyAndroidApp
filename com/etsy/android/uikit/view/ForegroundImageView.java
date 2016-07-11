package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.etsy.android.lib.R;

public class ForegroundImageView extends ImageView {
    private Drawable foreground;

    public ForegroundImageView(Context context) {
        super(context);
        init(context, null);
    }

    public ForegroundImageView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    public ForegroundImageView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.ForegroundImageView);
            Drawable drawable = obtainStyledAttributes.getDrawable(R.ForegroundImageView_foreground);
            if (drawable != null) {
                setForeground(drawable);
            }
            obtainStyledAttributes.recycle();
        }
    }

    public void setForegroundResource(int i) {
        setForeground(getContext().getResources().getDrawable(i));
    }

    public void setForeground(Drawable drawable) {
        if (this.foreground != drawable) {
            if (this.foreground != null) {
                this.foreground.setCallback(null);
                unscheduleDrawable(this.foreground);
            }
            this.foreground = drawable;
            if (drawable != null) {
                drawable.setCallback(this);
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
            }
            requestLayout();
            invalidate();
        }
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.foreground;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.foreground != null) {
            this.foreground.jumpToCurrentState();
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.foreground != null && this.foreground.isStateful()) {
            this.foreground.setState(getDrawableState());
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.foreground != null) {
            this.foreground.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            invalidate();
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.foreground != null) {
            this.foreground.setBounds(0, 0, i, i2);
            invalidate();
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.foreground != null) {
            this.foreground.draw(canvas);
        }
    }
}
