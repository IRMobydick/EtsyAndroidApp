package android.support.design.widget;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import com.etsy.android.uikit.view.FullImageView;

class ValueAnimatorCompatImplEclairMr1 extends Impl {
    private static final int DEFAULT_DURATION = 200;
    private static final int HANDLER_DELAY = 10;
    private static final Handler sHandler;
    private float mAnimatedFraction;
    private int mDuration;
    private final float[] mFloatValues;
    private final int[] mIntValues;
    private Interpolator mInterpolator;
    private boolean mIsRunning;
    private AnimatorListenerProxy mListener;
    private final Runnable mRunnable;
    private long mStartTime;
    private AnimatorUpdateListenerProxy mUpdateListener;

    /* renamed from: android.support.design.widget.ValueAnimatorCompatImplEclairMr1.1 */
    class C00471 implements Runnable {
        C00471() {
        }

        public void run() {
            ValueAnimatorCompatImplEclairMr1.this.update();
        }
    }

    ValueAnimatorCompatImplEclairMr1() {
        this.mIntValues = new int[2];
        this.mFloatValues = new float[2];
        this.mDuration = DEFAULT_DURATION;
        this.mRunnable = new C00471();
    }

    static {
        sHandler = new Handler(Looper.getMainLooper());
    }

    public void start() {
        if (!this.mIsRunning) {
            if (this.mInterpolator == null) {
                this.mInterpolator = new AccelerateDecelerateInterpolator();
            }
            this.mStartTime = SystemClock.uptimeMillis();
            this.mIsRunning = true;
            if (this.mListener != null) {
                this.mListener.onAnimationStart();
            }
            sHandler.postDelayed(this.mRunnable, 10);
        }
    }

    public boolean isRunning() {
        return this.mIsRunning;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    public void setListener(AnimatorListenerProxy animatorListenerProxy) {
        this.mListener = animatorListenerProxy;
    }

    public void setUpdateListener(AnimatorUpdateListenerProxy animatorUpdateListenerProxy) {
        this.mUpdateListener = animatorUpdateListenerProxy;
    }

    public void setIntValues(int i, int i2) {
        this.mIntValues[0] = i;
        this.mIntValues[1] = i2;
    }

    public int getAnimatedIntValue() {
        return AnimationUtils.lerp(this.mIntValues[0], this.mIntValues[1], getAnimatedFraction());
    }

    public void setFloatValues(float f, float f2) {
        this.mFloatValues[0] = f;
        this.mFloatValues[1] = f2;
    }

    public float getAnimatedFloatValue() {
        return AnimationUtils.lerp(this.mFloatValues[0], this.mFloatValues[1], getAnimatedFraction());
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public void cancel() {
        this.mIsRunning = false;
        sHandler.removeCallbacks(this.mRunnable);
        if (this.mListener != null) {
            this.mListener.onAnimationCancel();
        }
    }

    public float getAnimatedFraction() {
        return this.mAnimatedFraction;
    }

    public void end() {
        if (this.mIsRunning) {
            this.mIsRunning = false;
            sHandler.removeCallbacks(this.mRunnable);
            this.mAnimatedFraction = FullImageView.ASPECT_RATIO_SQUARE;
            if (this.mUpdateListener != null) {
                this.mUpdateListener.onAnimationUpdate();
            }
            if (this.mListener != null) {
                this.mListener.onAnimationEnd();
            }
        }
    }

    public long getDuration() {
        return (long) this.mDuration;
    }

    private void update() {
        if (this.mIsRunning) {
            float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.mStartTime)) / ((float) this.mDuration);
            if (this.mInterpolator != null) {
                uptimeMillis = this.mInterpolator.getInterpolation(uptimeMillis);
            }
            this.mAnimatedFraction = uptimeMillis;
            if (this.mUpdateListener != null) {
                this.mUpdateListener.onAnimationUpdate();
            }
            if (SystemClock.uptimeMillis() >= this.mStartTime + ((long) this.mDuration)) {
                this.mIsRunning = false;
                if (this.mListener != null) {
                    this.mListener.onAnimationEnd();
                }
            }
        }
        if (this.mIsRunning) {
            sHandler.postDelayed(this.mRunnable, 10);
        }
    }
}
