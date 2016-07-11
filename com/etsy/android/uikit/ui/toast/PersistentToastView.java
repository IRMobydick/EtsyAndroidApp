package com.etsy.android.uikit.ui.toast;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.view.FullImageView;

public class PersistentToastView extends RelativeLayout {
    private boolean mAllowDismiss;
    private boolean mAnimating;
    private boolean mIsFirstMeasure;
    private int mPopupHeight;
    protected PersistentToastPopup mToast;
    protected View mToastPopupContainer;

    /* renamed from: com.etsy.android.uikit.ui.toast.PersistentToastView.1 */
    class C09981 implements Runnable {
        final /* synthetic */ long f4118a;
        final /* synthetic */ PersistentToastView f4119b;

        /* renamed from: com.etsy.android.uikit.ui.toast.PersistentToastView.1.1 */
        class C09971 extends AnimatorListenerAdapter {
            final /* synthetic */ C09981 f4117a;

            C09971(C09981 c09981) {
                this.f4117a = c09981;
            }

            public void onAnimationEnd(Animator animator) {
                this.f4117a.f4119b.mAnimating = false;
                this.f4117a.f4119b.setVisibility(0);
            }
        }

        C09981(PersistentToastView persistentToastView, long j) {
            this.f4119b = persistentToastView;
            this.f4118a = j;
        }

        public void run() {
            this.f4119b.animate().cancel();
            this.f4119b.animate().translationY(0.0f).alpha(FullImageView.ASPECT_RATIO_SQUARE).setDuration(this.f4118a).setListener(new C09971(this));
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.toast.PersistentToastView.2 */
    class C09992 extends AnimatorListenerAdapter {
        final /* synthetic */ PersistentToastView f4120a;

        C09992(PersistentToastView persistentToastView) {
            this.f4120a = persistentToastView;
        }

        public void onAnimationEnd(Animator animator) {
            this.f4120a.mAnimating = false;
            this.f4120a.setVisibility(8);
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.toast.PersistentToastView.3 */
    class C10003 implements Runnable {
        final /* synthetic */ PersistentToastView f4121a;

        C10003(PersistentToastView persistentToastView) {
            this.f4121a = persistentToastView;
        }

        public void run() {
            this.f4121a.mAllowDismiss = true;
        }
    }

    public PersistentToastView(Context context) {
        super(context);
        this.mAnimating = false;
        this.mIsFirstMeasure = true;
    }

    public PersistentToastView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAnimating = false;
        this.mIsFirstMeasure = true;
    }

    public PersistentToastView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAnimating = false;
        this.mIsFirstMeasure = true;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mToastPopupContainer = findViewById(R.popup_container);
    }

    void setPersistentToastPopup(PersistentToastPopup persistentToastPopup) {
        this.mToast = persistentToastPopup;
    }

    protected void setActionClickListener(OnClickListener onClickListener) {
    }

    protected void setDismissClickListener(OnClickListener onClickListener) {
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mAllowDismiss) {
            this.mToast.m5360d();
        }
        return false;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mPopupHeight = findViewById(R.popup_container).getMeasuredHeight();
        if (this.mIsFirstMeasure) {
            hide();
            this.mIsFirstMeasure = false;
        }
    }

    public void show() {
        setAlpha(FullImageView.ASPECT_RATIO_SQUARE);
        setTranslationY(0.0f);
        onViewShown();
    }

    public void hide() {
        setAlpha(0.0f);
        setTranslationY((float) this.mPopupHeight);
        this.mAllowDismiss = false;
    }

    public void animateIn(long j) {
        if (!this.mAnimating) {
            this.mAnimating = true;
            post(new C09981(this, j));
            onViewShown();
        }
    }

    public void animateOut(long j) {
        if (!this.mAnimating) {
            this.mAnimating = true;
            animate().cancel();
            animate().translationY((float) this.mPopupHeight).alpha(0.0f).setDuration(j).setListener(new C09992(this));
            this.mAllowDismiss = false;
        }
    }

    private void onViewShown() {
        postDelayed(new C10003(this), 2000);
    }
}
