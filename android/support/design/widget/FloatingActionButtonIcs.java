package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import com.etsy.android.uikit.view.FullImageView;

class FloatingActionButtonIcs extends FloatingActionButtonEclairMr1 {
    private boolean mIsHiding;

    /* renamed from: android.support.design.widget.FloatingActionButtonIcs.1 */
    class C00191 extends AnimatorListenerAdapter {
        private boolean mCancelled;
        final /* synthetic */ boolean val$fromUser;
        final /* synthetic */ InternalVisibilityChangedListener val$listener;

        C00191(boolean z, InternalVisibilityChangedListener internalVisibilityChangedListener) {
            this.val$fromUser = z;
            this.val$listener = internalVisibilityChangedListener;
        }

        public void onAnimationStart(Animator animator) {
            FloatingActionButtonIcs.this.mIsHiding = true;
            this.mCancelled = false;
            FloatingActionButtonIcs.this.mView.internalSetVisibility(0, this.val$fromUser);
        }

        public void onAnimationCancel(Animator animator) {
            FloatingActionButtonIcs.this.mIsHiding = false;
            this.mCancelled = true;
        }

        public void onAnimationEnd(Animator animator) {
            FloatingActionButtonIcs.this.mIsHiding = false;
            if (!this.mCancelled) {
                FloatingActionButtonIcs.this.mView.internalSetVisibility(8, this.val$fromUser);
                if (this.val$listener != null) {
                    this.val$listener.onHidden();
                }
            }
        }
    }

    /* renamed from: android.support.design.widget.FloatingActionButtonIcs.2 */
    class C00202 extends AnimatorListenerAdapter {
        final /* synthetic */ boolean val$fromUser;
        final /* synthetic */ InternalVisibilityChangedListener val$listener;

        C00202(boolean z, InternalVisibilityChangedListener internalVisibilityChangedListener) {
            this.val$fromUser = z;
            this.val$listener = internalVisibilityChangedListener;
        }

        public void onAnimationStart(Animator animator) {
            FloatingActionButtonIcs.this.mView.internalSetVisibility(0, this.val$fromUser);
        }

        public void onAnimationEnd(Animator animator) {
            if (this.val$listener != null) {
                this.val$listener.onShown();
            }
        }
    }

    FloatingActionButtonIcs(VisibilityAwareImageButton visibilityAwareImageButton, ShadowViewDelegate shadowViewDelegate) {
        super(visibilityAwareImageButton, shadowViewDelegate);
    }

    boolean requirePreDrawListener() {
        return true;
    }

    void onPreDraw() {
        updateFromViewRotation(this.mView.getRotation());
    }

    void hide(@Nullable InternalVisibilityChangedListener internalVisibilityChangedListener, boolean z) {
        if (this.mIsHiding || this.mView.getVisibility() != 0) {
            if (internalVisibilityChangedListener != null) {
                internalVisibilityChangedListener.onHidden();
            }
        } else if (!ViewCompat.isLaidOut(this.mView) || this.mView.isInEditMode()) {
            this.mView.internalSetVisibility(8, z);
            if (internalVisibilityChangedListener != null) {
                internalVisibilityChangedListener.onHidden();
            }
        } else {
            this.mView.animate().cancel();
            this.mView.animate().scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setDuration(200).setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setListener(new C00191(z, internalVisibilityChangedListener));
        }
    }

    void show(@Nullable InternalVisibilityChangedListener internalVisibilityChangedListener, boolean z) {
        if (!this.mIsHiding && this.mView.getVisibility() == 0) {
            return;
        }
        if (!ViewCompat.isLaidOut(this.mView) || this.mView.isInEditMode()) {
            this.mView.internalSetVisibility(0, z);
            this.mView.setAlpha(FullImageView.ASPECT_RATIO_SQUARE);
            this.mView.setScaleY(FullImageView.ASPECT_RATIO_SQUARE);
            this.mView.setScaleX(FullImageView.ASPECT_RATIO_SQUARE);
            if (internalVisibilityChangedListener != null) {
                internalVisibilityChangedListener.onShown();
                return;
            }
            return;
        }
        this.mView.animate().cancel();
        if (this.mView.getVisibility() != 0) {
            this.mView.setAlpha(0.0f);
            this.mView.setScaleY(0.0f);
            this.mView.setScaleX(0.0f);
        }
        this.mView.animate().scaleX(FullImageView.ASPECT_RATIO_SQUARE).scaleY(FullImageView.ASPECT_RATIO_SQUARE).alpha(FullImageView.ASPECT_RATIO_SQUARE).setDuration(200).setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setListener(new C00202(z, internalVisibilityChangedListener));
    }

    private void updateFromViewRotation(float f) {
        if (this.mShadowDrawable != null) {
            this.mShadowDrawable.setRotation(-f);
        }
        if (this.mBorderDrawable != null) {
            this.mBorderDrawable.setRotation(-f);
        }
    }
}
