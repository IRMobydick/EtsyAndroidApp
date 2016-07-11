package com.appboy.ui.inappmessage.listeners;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewPropertyAnimator;
import com.appboy.Constants;
import com.etsy.android.uikit.view.FullImageView;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

public class SwipeDismissTouchListener implements OnTouchListener {
    private long mAnimationTime;
    private DismissCallbacks mCallbacks;
    private float mDownX;
    private float mDownY;
    private int mMaxFlingVelocity;
    private int mMinFlingVelocity;
    private int mSlop;
    private boolean mSwiping;
    private int mSwipingSlop;
    private Object mToken;
    private float mTranslationX;
    private VelocityTracker mVelocityTracker;
    private View mView;
    private int mViewWidth;

    public interface DismissCallbacks {
        boolean canDismiss(Object obj);

        void onDismiss(View view, Object obj);
    }

    /* renamed from: com.appboy.ui.inappmessage.listeners.SwipeDismissTouchListener.1 */
    class C04221 extends AnimatorListenerAdapter {
        C04221() {
        }

        public void onAnimationEnd(Animator animator) {
            SwipeDismissTouchListener.this.performDismiss();
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.listeners.SwipeDismissTouchListener.2 */
    class C04232 extends AnimatorListenerAdapter {
        final /* synthetic */ LayoutParams val$lp;
        final /* synthetic */ int val$originalHeight;

        C04232(LayoutParams layoutParams, int i) {
            this.val$lp = layoutParams;
            this.val$originalHeight = i;
        }

        public void onAnimationEnd(Animator animator) {
            SwipeDismissTouchListener.this.mCallbacks.onDismiss(SwipeDismissTouchListener.this.mView, SwipeDismissTouchListener.this.mToken);
            SwipeDismissTouchListener.this.mView.setAlpha(FullImageView.ASPECT_RATIO_SQUARE);
            SwipeDismissTouchListener.this.mView.setTranslationX(0.0f);
            this.val$lp.height = this.val$originalHeight;
            SwipeDismissTouchListener.this.mView.setLayoutParams(this.val$lp);
        }
    }

    /* renamed from: com.appboy.ui.inappmessage.listeners.SwipeDismissTouchListener.3 */
    class C04243 implements AnimatorUpdateListener {
        final /* synthetic */ LayoutParams val$lp;

        C04243(LayoutParams layoutParams) {
            this.val$lp = layoutParams;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.val$lp.height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            SwipeDismissTouchListener.this.mView.setLayoutParams(this.val$lp);
        }
    }

    public SwipeDismissTouchListener(View view, Object obj, DismissCallbacks dismissCallbacks) {
        this.mViewWidth = 1;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(view.getContext());
        this.mSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity() * 16;
        this.mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mAnimationTime = (long) view.getContext().getResources().getInteger(17694720);
        this.mView = view;
        this.mToken = obj;
        this.mCallbacks = dismissCallbacks;
    }

    @TargetApi(12)
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z = true;
        motionEvent.offsetLocation(this.mTranslationX, 0.0f);
        if (this.mViewWidth < 2) {
            this.mViewWidth = this.mView.getWidth();
        }
        float rawX;
        float xVelocity;
        switch (motionEvent.getActionMasked()) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                this.mDownX = motionEvent.getRawX();
                this.mDownY = motionEvent.getRawY();
                if (!this.mCallbacks.canDismiss(this.mToken)) {
                    return false;
                }
                this.mVelocityTracker = VelocityTracker.obtain();
                this.mVelocityTracker.addMovement(motionEvent);
                return false;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                if (this.mVelocityTracker == null) {
                    return false;
                }
                boolean z2;
                rawX = motionEvent.getRawX() - this.mDownX;
                this.mVelocityTracker.addMovement(motionEvent);
                this.mVelocityTracker.computeCurrentVelocity(Constants.APPBOY_MINIMUM_NOTIFICATION_DURATION_MILLIS);
                xVelocity = this.mVelocityTracker.getXVelocity();
                float abs = Math.abs(xVelocity);
                float abs2 = Math.abs(this.mVelocityTracker.getYVelocity());
                if (Math.abs(rawX) > ((float) (this.mViewWidth / 2)) && this.mSwiping) {
                    z2 = rawX > 0.0f;
                } else if (((float) this.mMinFlingVelocity) > abs || abs > ((float) this.mMaxFlingVelocity) || abs2 >= abs || abs2 >= abs || !this.mSwiping) {
                    z2 = false;
                    z = false;
                } else {
                    boolean z3;
                    if (xVelocity < 0.0f) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    z2 = z3 == ((rawX > 0.0f ? 1 : (rawX == 0.0f ? 0 : -1)) < 0);
                    if (this.mVelocityTracker.getXVelocity() <= 0.0f) {
                        z = false;
                    }
                    boolean z4 = z;
                    z = z2;
                    z2 = z4;
                }
                if (z) {
                    ViewPropertyAnimator animate = this.mView.animate();
                    if (z2) {
                        rawX = (float) this.mViewWidth;
                    } else {
                        rawX = (float) (-this.mViewWidth);
                    }
                    animate.translationX(rawX).alpha(0.0f).setDuration(this.mAnimationTime).setListener(new C04221());
                } else if (this.mSwiping) {
                    this.mView.animate().translationX(0.0f).alpha(FullImageView.ASPECT_RATIO_SQUARE).setDuration(this.mAnimationTime).setListener(null);
                }
                this.mVelocityTracker.recycle();
                this.mVelocityTracker = null;
                this.mTranslationX = 0.0f;
                this.mDownX = 0.0f;
                this.mDownY = 0.0f;
                this.mSwiping = false;
                return false;
            case Task.NETWORK_STATE_ANY /*2*/:
                if (this.mVelocityTracker == null) {
                    return false;
                }
                this.mVelocityTracker.addMovement(motionEvent);
                xVelocity = motionEvent.getRawX() - this.mDownX;
                rawX = motionEvent.getRawY() - this.mDownY;
                if (Math.abs(xVelocity) > ((float) this.mSlop) && Math.abs(rawX) < Math.abs(xVelocity) / 2.0f) {
                    int i;
                    this.mSwiping = true;
                    if (xVelocity > 0.0f) {
                        i = this.mSlop;
                    } else {
                        i = -this.mSlop;
                    }
                    this.mSwipingSlop = i;
                    this.mView.getParent().requestDisallowInterceptTouchEvent(true);
                    MotionEvent obtain = MotionEvent.obtain(motionEvent);
                    obtain.setAction((motionEvent.getActionIndex() << 8) | 3);
                    this.mView.onTouchEvent(obtain);
                    obtain.recycle();
                }
                if (!this.mSwiping) {
                    return false;
                }
                this.mTranslationX = xVelocity;
                this.mView.setTranslationX(xVelocity - ((float) this.mSwipingSlop));
                return true;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                if (this.mVelocityTracker == null) {
                    return false;
                }
                this.mView.animate().translationX(0.0f).alpha(FullImageView.ASPECT_RATIO_SQUARE).setDuration(this.mAnimationTime).setListener(null);
                this.mVelocityTracker.recycle();
                this.mVelocityTracker = null;
                this.mTranslationX = 0.0f;
                this.mDownX = 0.0f;
                this.mDownY = 0.0f;
                this.mSwiping = false;
                return false;
            default:
                return false;
        }
    }

    @TargetApi(12)
    public void performDismiss() {
        LayoutParams layoutParams = this.mView.getLayoutParams();
        ValueAnimator duration = ValueAnimator.ofInt(new int[]{this.mView.getHeight(), 1}).setDuration(this.mAnimationTime);
        duration.addListener(new C04232(layoutParams, r1));
        duration.addUpdateListener(new C04243(layoutParams));
        duration.start();
    }
}
