package com.etsy.android.uikit.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import com.google.android.gms.gcm.Task;

public class LoadingIndicatorView extends ImageView {
    private ValueAnimator mDegreeAnimator;
    private int mDegreeOffset;
    private AnimatorListener mListener;
    private float mPivotX;
    private float mPivotY;

    /* renamed from: com.etsy.android.uikit.view.LoadingIndicatorView.1 */
    class C10201 implements AnimatorListener {
        final /* synthetic */ LoadingIndicatorView f4216a;

        C10201(LoadingIndicatorView loadingIndicatorView) {
            this.f4216a = loadingIndicatorView;
        }

        public void onAnimationStart(Animator animator) {
        }

        public void onAnimationEnd(Animator animator) {
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
            this.f4216a.mDegreeOffset = this.f4216a.mDegreeOffset + 1;
            if (this.f4216a.mDegreeOffset >= 9) {
                this.f4216a.mDegreeOffset = 1;
            }
            this.f4216a.invalidate();
        }
    }

    public LoadingIndicatorView(Context context) {
        super(context);
        this.mDegreeOffset = 0;
        this.mPivotX = 0.0f;
        this.mPivotY = 0.0f;
    }

    public LoadingIndicatorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDegreeOffset = 0;
        this.mPivotX = 0.0f;
        this.mPivotY = 0.0f;
    }

    public LoadingIndicatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDegreeOffset = 0;
        this.mPivotX = 0.0f;
        this.mPivotY = 0.0f;
    }

    @TargetApi(21)
    public LoadingIndicatorView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mDegreeOffset = 0;
        this.mPivotX = 0.0f;
        this.mPivotY = 0.0f;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mPivotX = (float) (getMeasuredWidth() / 2);
        this.mPivotY = (float) (getMeasuredHeight() / 2);
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (this.mDegreeAnimator != null) {
            switch (i) {
                case Task.NETWORK_STATE_CONNECTED /*0*/:
                    this.mDegreeAnimator.start();
                default:
                    this.mDegreeAnimator.cancel();
            }
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mDegreeAnimator = ValueAnimator.ofInt(new int[]{0, 1});
        this.mListener = new C10201(this);
        this.mDegreeAnimator.addListener(this.mListener);
        this.mDegreeAnimator.setDuration(75);
        this.mDegreeAnimator.setRepeatCount(-1);
        this.mDegreeAnimator.setInterpolator(new LinearInterpolator());
        if (getVisibility() == 0) {
            this.mDegreeAnimator.start();
        }
    }

    protected void onDetachedFromWindow() {
        this.mDegreeAnimator.cancel();
        this.mDegreeAnimator.removeListener(this.mListener);
        this.mListener = null;
        super.onDetachedFromWindow();
    }

    protected void onDraw(Canvas canvas) {
        canvas.rotate(45.0f * ((float) this.mDegreeOffset), this.mPivotX, this.mPivotY);
        super.onDraw(canvas);
    }
}
