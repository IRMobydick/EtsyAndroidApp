package android.support.v4.animation;

import android.view.View;
import com.etsy.android.uikit.view.FullImageView;
import java.util.ArrayList;
import java.util.List;

class DonutAnimatorCompatProvider implements AnimatorProvider {

    class DonutFloatValueAnimator implements ValueAnimatorCompat {
        private long mDuration;
        private boolean mEnded;
        private float mFraction;
        List<AnimatorListenerCompat> mListeners;
        private Runnable mLoopRunnable;
        private long mStartTime;
        private boolean mStarted;
        View mTarget;
        List<AnimatorUpdateListenerCompat> mUpdateListeners;

        /* renamed from: android.support.v4.animation.DonutAnimatorCompatProvider.DonutFloatValueAnimator.1 */
        class C00591 implements Runnable {
            C00591() {
            }

            public void run() {
                float access$000 = (((float) (DonutFloatValueAnimator.this.getTime() - DonutFloatValueAnimator.this.mStartTime)) * FullImageView.ASPECT_RATIO_SQUARE) / ((float) DonutFloatValueAnimator.this.mDuration);
                if (access$000 > FullImageView.ASPECT_RATIO_SQUARE || DonutFloatValueAnimator.this.mTarget.getParent() == null) {
                    access$000 = FullImageView.ASPECT_RATIO_SQUARE;
                }
                DonutFloatValueAnimator.this.mFraction = access$000;
                DonutFloatValueAnimator.this.notifyUpdateListeners();
                if (DonutFloatValueAnimator.this.mFraction >= FullImageView.ASPECT_RATIO_SQUARE) {
                    DonutFloatValueAnimator.this.dispatchEnd();
                } else {
                    DonutFloatValueAnimator.this.mTarget.postDelayed(DonutFloatValueAnimator.this.mLoopRunnable, 16);
                }
            }
        }

        public DonutFloatValueAnimator() {
            this.mListeners = new ArrayList();
            this.mUpdateListeners = new ArrayList();
            this.mDuration = 200;
            this.mFraction = 0.0f;
            this.mStarted = false;
            this.mEnded = false;
            this.mLoopRunnable = new C00591();
        }

        private void notifyUpdateListeners() {
            for (int size = this.mUpdateListeners.size() - 1; size >= 0; size--) {
                ((AnimatorUpdateListenerCompat) this.mUpdateListeners.get(size)).onAnimationUpdate(this);
            }
        }

        public void setTarget(View view) {
            this.mTarget = view;
        }

        public void addListener(AnimatorListenerCompat animatorListenerCompat) {
            this.mListeners.add(animatorListenerCompat);
        }

        public void setDuration(long j) {
            if (!this.mStarted) {
                this.mDuration = j;
            }
        }

        public void start() {
            if (!this.mStarted) {
                this.mStarted = true;
                dispatchStart();
                this.mFraction = 0.0f;
                this.mStartTime = getTime();
                this.mTarget.postDelayed(this.mLoopRunnable, 16);
            }
        }

        private long getTime() {
            return this.mTarget.getDrawingTime();
        }

        private void dispatchStart() {
            for (int size = this.mListeners.size() - 1; size >= 0; size--) {
                ((AnimatorListenerCompat) this.mListeners.get(size)).onAnimationStart(this);
            }
        }

        private void dispatchEnd() {
            for (int size = this.mListeners.size() - 1; size >= 0; size--) {
                ((AnimatorListenerCompat) this.mListeners.get(size)).onAnimationEnd(this);
            }
        }

        private void dispatchCancel() {
            for (int size = this.mListeners.size() - 1; size >= 0; size--) {
                ((AnimatorListenerCompat) this.mListeners.get(size)).onAnimationCancel(this);
            }
        }

        public void cancel() {
            if (!this.mEnded) {
                this.mEnded = true;
                if (this.mStarted) {
                    dispatchCancel();
                }
                dispatchEnd();
            }
        }

        public void addUpdateListener(AnimatorUpdateListenerCompat animatorUpdateListenerCompat) {
            this.mUpdateListeners.add(animatorUpdateListenerCompat);
        }

        public float getAnimatedFraction() {
            return this.mFraction;
        }
    }

    DonutAnimatorCompatProvider() {
    }

    public ValueAnimatorCompat emptyValueAnimator() {
        return new DonutFloatValueAnimator();
    }

    public void clearInterpolator(View view) {
    }
}
