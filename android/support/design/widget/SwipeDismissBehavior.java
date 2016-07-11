package android.support.design.widget;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.etsy.android.uikit.view.FullImageView;
import com.google.android.gms.common.api.CommonStatusCodes;

public class SwipeDismissBehavior<V extends View> extends Behavior<V> {
    private static final float DEFAULT_ALPHA_END_DISTANCE = 0.5f;
    private static final float DEFAULT_ALPHA_START_DISTANCE = 0.0f;
    private static final float DEFAULT_DRAG_DISMISS_THRESHOLD = 0.5f;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    public static final int SWIPE_DIRECTION_ANY = 2;
    public static final int SWIPE_DIRECTION_END_TO_START = 1;
    public static final int SWIPE_DIRECTION_START_TO_END = 0;
    private float mAlphaEndSwipeDistance;
    private float mAlphaStartSwipeDistance;
    private final Callback mDragCallback;
    private float mDragDismissThreshold;
    private boolean mIgnoreEvents;
    private OnDismissListener mListener;
    private float mSensitivity;
    private boolean mSensitivitySet;
    private int mSwipeDirection;
    private ViewDragHelper mViewDragHelper;

    public interface OnDismissListener {
        void onDismiss(View view);

        void onDragStateChanged(int i);
    }

    /* renamed from: android.support.design.widget.SwipeDismissBehavior.1 */
    class C00361 extends Callback {
        private static final int INVALID_POINTER_ID = -1;
        private int mActivePointerId;
        private int mOriginalCapturedViewLeft;

        C00361() {
            this.mActivePointerId = INVALID_POINTER_ID;
        }

        public boolean tryCaptureView(View view, int i) {
            return this.mActivePointerId == INVALID_POINTER_ID && SwipeDismissBehavior.this.canSwipeDismissView(view);
        }

        public void onViewCaptured(View view, int i) {
            this.mActivePointerId = i;
            this.mOriginalCapturedViewLeft = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        public void onViewDragStateChanged(int i) {
            if (SwipeDismissBehavior.this.mListener != null) {
                SwipeDismissBehavior.this.mListener.onDragStateChanged(i);
            }
        }

        public void onViewReleased(View view, float f, float f2) {
            this.mActivePointerId = INVALID_POINTER_ID;
            int width = view.getWidth();
            boolean z = false;
            if (shouldDismiss(view, f)) {
                width = view.getLeft() < this.mOriginalCapturedViewLeft ? this.mOriginalCapturedViewLeft - width : this.mOriginalCapturedViewLeft + width;
                z = SwipeDismissBehavior.SWIPE_DIRECTION_END_TO_START;
            } else {
                width = this.mOriginalCapturedViewLeft;
            }
            if (SwipeDismissBehavior.this.mViewDragHelper.settleCapturedViewAt(width, view.getTop())) {
                ViewCompat.postOnAnimation(view, new SettleRunnable(view, z));
            } else if (z && SwipeDismissBehavior.this.mListener != null) {
                SwipeDismissBehavior.this.mListener.onDismiss(view);
            }
        }

        private boolean shouldDismiss(View view, float f) {
            if (f != SwipeDismissBehavior.DEFAULT_ALPHA_START_DISTANCE) {
                boolean z = ViewCompat.getLayoutDirection(view) == SwipeDismissBehavior.SWIPE_DIRECTION_END_TO_START ? true : SwipeDismissBehavior.STATE_IDLE;
                if (SwipeDismissBehavior.this.mSwipeDirection == SwipeDismissBehavior.SWIPE_DIRECTION_ANY) {
                    return true;
                }
                if (SwipeDismissBehavior.this.mSwipeDirection == 0) {
                    if (z) {
                        if (f >= SwipeDismissBehavior.DEFAULT_ALPHA_START_DISTANCE) {
                            return false;
                        }
                        return true;
                    } else if (f <= SwipeDismissBehavior.DEFAULT_ALPHA_START_DISTANCE) {
                        return false;
                    } else {
                        return true;
                    }
                } else if (SwipeDismissBehavior.this.mSwipeDirection != SwipeDismissBehavior.SWIPE_DIRECTION_END_TO_START) {
                    return false;
                } else {
                    if (z) {
                        if (f <= SwipeDismissBehavior.DEFAULT_ALPHA_START_DISTANCE) {
                            return false;
                        }
                        return true;
                    } else if (f >= SwipeDismissBehavior.DEFAULT_ALPHA_START_DISTANCE) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            if (Math.abs(view.getLeft() - this.mOriginalCapturedViewLeft) < Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.mDragDismissThreshold)) {
                return false;
            }
            return true;
        }

        public int getViewHorizontalDragRange(View view) {
            return view.getWidth();
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            int width;
            int i3;
            Object obj = ViewCompat.getLayoutDirection(view) == SwipeDismissBehavior.SWIPE_DIRECTION_END_TO_START ? SwipeDismissBehavior.SWIPE_DIRECTION_END_TO_START : null;
            if (SwipeDismissBehavior.this.mSwipeDirection == 0) {
                if (obj != null) {
                    width = this.mOriginalCapturedViewLeft - view.getWidth();
                    i3 = this.mOriginalCapturedViewLeft;
                } else {
                    width = this.mOriginalCapturedViewLeft;
                    i3 = this.mOriginalCapturedViewLeft + view.getWidth();
                }
            } else if (SwipeDismissBehavior.this.mSwipeDirection != SwipeDismissBehavior.SWIPE_DIRECTION_END_TO_START) {
                width = this.mOriginalCapturedViewLeft - view.getWidth();
                i3 = this.mOriginalCapturedViewLeft + view.getWidth();
            } else if (obj != null) {
                width = this.mOriginalCapturedViewLeft;
                i3 = this.mOriginalCapturedViewLeft + view.getWidth();
            } else {
                width = this.mOriginalCapturedViewLeft - view.getWidth();
                i3 = this.mOriginalCapturedViewLeft;
            }
            return SwipeDismissBehavior.clamp(width, i, i3);
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            return view.getTop();
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            float width = ((float) this.mOriginalCapturedViewLeft) + (((float) view.getWidth()) * SwipeDismissBehavior.this.mAlphaStartSwipeDistance);
            float width2 = ((float) this.mOriginalCapturedViewLeft) + (((float) view.getWidth()) * SwipeDismissBehavior.this.mAlphaEndSwipeDistance);
            if (((float) i) <= width) {
                ViewCompat.setAlpha(view, FullImageView.ASPECT_RATIO_SQUARE);
            } else if (((float) i) >= width2) {
                ViewCompat.setAlpha(view, SwipeDismissBehavior.DEFAULT_ALPHA_START_DISTANCE);
            } else {
                ViewCompat.setAlpha(view, SwipeDismissBehavior.clamp((float) SwipeDismissBehavior.DEFAULT_ALPHA_START_DISTANCE, FullImageView.ASPECT_RATIO_SQUARE - SwipeDismissBehavior.fraction(width, width2, (float) i), (float) FullImageView.ASPECT_RATIO_SQUARE));
            }
        }
    }

    class SettleRunnable implements Runnable {
        private final boolean mDismiss;
        private final View mView;

        SettleRunnable(View view, boolean z) {
            this.mView = view;
            this.mDismiss = z;
        }

        public void run() {
            if (SwipeDismissBehavior.this.mViewDragHelper != null && SwipeDismissBehavior.this.mViewDragHelper.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.mView, this);
            } else if (this.mDismiss && SwipeDismissBehavior.this.mListener != null) {
                SwipeDismissBehavior.this.mListener.onDismiss(this.mView);
            }
        }
    }

    public SwipeDismissBehavior() {
        this.mSensitivity = DEFAULT_ALPHA_START_DISTANCE;
        this.mSwipeDirection = SWIPE_DIRECTION_ANY;
        this.mDragDismissThreshold = DEFAULT_DRAG_DISMISS_THRESHOLD;
        this.mAlphaStartSwipeDistance = DEFAULT_ALPHA_START_DISTANCE;
        this.mAlphaEndSwipeDistance = DEFAULT_DRAG_DISMISS_THRESHOLD;
        this.mDragCallback = new C00361();
    }

    public void setListener(OnDismissListener onDismissListener) {
        this.mListener = onDismissListener;
    }

    public void setSwipeDirection(int i) {
        this.mSwipeDirection = i;
    }

    public void setDragDismissDistance(float f) {
        this.mDragDismissThreshold = clamp((float) DEFAULT_ALPHA_START_DISTANCE, f, (float) FullImageView.ASPECT_RATIO_SQUARE);
    }

    public void setStartAlphaSwipeDistance(float f) {
        this.mAlphaStartSwipeDistance = clamp((float) DEFAULT_ALPHA_START_DISTANCE, f, (float) FullImageView.ASPECT_RATIO_SQUARE);
    }

    public void setEndAlphaSwipeDistance(float f) {
        this.mAlphaEndSwipeDistance = clamp((float) DEFAULT_ALPHA_START_DISTANCE, f, (float) FullImageView.ASPECT_RATIO_SQUARE);
    }

    public void setSensitivity(float f) {
        this.mSensitivity = f;
        this.mSensitivitySet = true;
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case SWIPE_DIRECTION_END_TO_START /*1*/:
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                if (this.mIgnoreEvents) {
                    this.mIgnoreEvents = false;
                    return false;
                }
                break;
            default:
                boolean z;
                if (coordinatorLayout.isPointInChildBounds(v, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                    z = false;
                } else {
                    z = true;
                }
                this.mIgnoreEvents = z;
                break;
        }
        if (this.mIgnoreEvents) {
            return false;
        }
        ensureViewDragHelper(coordinatorLayout);
        return this.mViewDragHelper.shouldInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (this.mViewDragHelper == null) {
            return false;
        }
        this.mViewDragHelper.processTouchEvent(motionEvent);
        return true;
    }

    public boolean canSwipeDismissView(@NonNull View view) {
        return true;
    }

    private void ensureViewDragHelper(ViewGroup viewGroup) {
        if (this.mViewDragHelper == null) {
            this.mViewDragHelper = this.mSensitivitySet ? ViewDragHelper.create(viewGroup, this.mSensitivity, this.mDragCallback) : ViewDragHelper.create(viewGroup, this.mDragCallback);
        }
    }

    private static float clamp(float f, float f2, float f3) {
        return Math.min(Math.max(f, f2), f3);
    }

    private static int clamp(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    public int getDragState() {
        return this.mViewDragHelper != null ? this.mViewDragHelper.getViewDragState() : STATE_IDLE;
    }

    static float fraction(float f, float f2, float f3) {
        return (f3 - f) / (f2 - f);
    }
}
