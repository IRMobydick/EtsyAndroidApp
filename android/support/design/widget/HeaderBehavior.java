package android.support.design.widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import com.appboy.Constants;
import com.foresee.sdk.configuration.MeasureConfiguration;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

abstract class HeaderBehavior<V extends View> extends ViewOffsetBehavior<V> {
    private static final int INVALID_POINTER = -1;
    private int mActivePointerId;
    private Runnable mFlingRunnable;
    private boolean mIsBeingDragged;
    private int mLastMotionY;
    private ScrollerCompat mScroller;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;

    class FlingRunnable implements Runnable {
        private final V mLayout;
        private final CoordinatorLayout mParent;

        FlingRunnable(CoordinatorLayout coordinatorLayout, V v) {
            this.mParent = coordinatorLayout;
            this.mLayout = v;
        }

        public void run() {
            if (this.mLayout != null && HeaderBehavior.this.mScroller != null) {
                if (HeaderBehavior.this.mScroller.computeScrollOffset()) {
                    HeaderBehavior.this.setHeaderTopBottomOffset(this.mParent, this.mLayout, HeaderBehavior.this.mScroller.getCurrY());
                    ViewCompat.postOnAnimation(this.mLayout, this);
                    return;
                }
                HeaderBehavior.this.onFlingFinished(this.mParent, this.mLayout);
            }
        }
    }

    public HeaderBehavior() {
        this.mActivePointerId = INVALID_POINTER;
        this.mTouchSlop = INVALID_POINTER;
    }

    public HeaderBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mActivePointerId = INVALID_POINTER;
        this.mTouchSlop = INVALID_POINTER;
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (this.mTouchSlop < 0) {
            this.mTouchSlop = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getAction() == 2 && this.mIsBeingDragged) {
            return true;
        }
        int y;
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                this.mIsBeingDragged = false;
                int x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();
                if (canDragView(v) && coordinatorLayout.isPointInChildBounds(v, x, y)) {
                    this.mLastMotionY = y;
                    this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                    ensureVelocityTracker();
                    break;
                }
            case Task.NETWORK_STATE_UNMETERED /*1*/:
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                this.mIsBeingDragged = false;
                this.mActivePointerId = INVALID_POINTER;
                if (this.mVelocityTracker != null) {
                    this.mVelocityTracker.recycle();
                    this.mVelocityTracker = null;
                    break;
                }
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                y = this.mActivePointerId;
                if (y != INVALID_POINTER) {
                    y = MotionEventCompat.findPointerIndex(motionEvent, y);
                    if (y != INVALID_POINTER) {
                        y = (int) MotionEventCompat.getY(motionEvent, y);
                        if (Math.abs(y - this.mLastMotionY) > this.mTouchSlop) {
                            this.mIsBeingDragged = true;
                            this.mLastMotionY = y;
                            break;
                        }
                    }
                }
                break;
        }
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.addMovement(motionEvent);
        }
        return this.mIsBeingDragged;
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (this.mTouchSlop < 0) {
            this.mTouchSlop = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                int y = (int) motionEvent.getY();
                if (coordinatorLayout.isPointInChildBounds(v, (int) motionEvent.getX(), y) && canDragView(v)) {
                    this.mLastMotionY = y;
                    this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                    ensureVelocityTracker();
                    break;
                }
                return false;
                break;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                if (this.mVelocityTracker != null) {
                    this.mVelocityTracker.addMovement(motionEvent);
                    this.mVelocityTracker.computeCurrentVelocity(Constants.APPBOY_MINIMUM_NOTIFICATION_DURATION_MILLIS);
                    fling(coordinatorLayout, v, -getScrollRangeForDragFling(v), 0, VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mActivePointerId));
                    break;
                }
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                if (findPointerIndex != INVALID_POINTER) {
                    findPointerIndex = (int) MotionEventCompat.getY(motionEvent, findPointerIndex);
                    int i = this.mLastMotionY - findPointerIndex;
                    if (!this.mIsBeingDragged && Math.abs(i) > this.mTouchSlop) {
                        this.mIsBeingDragged = true;
                        i = i > 0 ? i - this.mTouchSlop : i + this.mTouchSlop;
                    }
                    if (this.mIsBeingDragged) {
                        this.mLastMotionY = findPointerIndex;
                        scroll(coordinatorLayout, v, i, getMaxDragOffset(v), 0);
                        break;
                    }
                }
                return false;
                break;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                break;
        }
        this.mIsBeingDragged = false;
        this.mActivePointerId = INVALID_POINTER;
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.addMovement(motionEvent);
        }
        return true;
    }

    int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout, V v, int i) {
        return setHeaderTopBottomOffset(coordinatorLayout, v, i, RtlSpacingHelper.UNDEFINED, MeasureConfiguration.DISABLED);
    }

    int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3) {
        int topAndBottomOffset = getTopAndBottomOffset();
        if (i2 == 0 || topAndBottomOffset < i2 || topAndBottomOffset > i3) {
            return 0;
        }
        int constrain = MathUtils.constrain(i, i2, i3);
        if (topAndBottomOffset == constrain) {
            return 0;
        }
        setTopAndBottomOffset(constrain);
        return topAndBottomOffset - constrain;
    }

    int getTopBottomOffsetForScrollingSibling() {
        return getTopAndBottomOffset();
    }

    final int scroll(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3) {
        return setHeaderTopBottomOffset(coordinatorLayout, v, getTopBottomOffsetForScrollingSibling() - i, i2, i3);
    }

    final boolean fling(CoordinatorLayout coordinatorLayout, V v, int i, int i2, float f) {
        if (this.mFlingRunnable != null) {
            v.removeCallbacks(this.mFlingRunnable);
            this.mFlingRunnable = null;
        }
        if (this.mScroller == null) {
            this.mScroller = ScrollerCompat.create(v.getContext());
        }
        this.mScroller.fling(0, getTopAndBottomOffset(), 0, Math.round(f), 0, 0, i, i2);
        if (this.mScroller.computeScrollOffset()) {
            this.mFlingRunnable = new FlingRunnable(coordinatorLayout, v);
            ViewCompat.postOnAnimation(v, this.mFlingRunnable);
            return true;
        }
        onFlingFinished(coordinatorLayout, v);
        return false;
    }

    void onFlingFinished(CoordinatorLayout coordinatorLayout, V v) {
    }

    boolean canDragView(V v) {
        return false;
    }

    int getMaxDragOffset(V v) {
        return -v.getHeight();
    }

    int getScrollRangeForDragFling(V v) {
        return v.getHeight();
    }

    private void ensureVelocityTracker() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
    }
}
