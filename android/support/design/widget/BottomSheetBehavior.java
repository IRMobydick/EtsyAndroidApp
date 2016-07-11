package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.design.C0000R;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.appboy.Constants;
import com.google.android.gms.gcm.Task;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

public class BottomSheetBehavior<V extends View> extends Behavior<V> {
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    public static final int STATE_COLLAPSED = 4;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;
    private int mActivePointerId;
    private BottomSheetCallback mCallback;
    private final Callback mDragCallback;
    private boolean mHideable;
    private boolean mIgnoreEvents;
    private int mInitialY;
    private int mLastNestedScrollDy;
    private int mMaxOffset;
    private float mMaximumVelocity;
    private int mMinOffset;
    private boolean mNestedScrolled;
    private WeakReference<View> mNestedScrollingChildRef;
    private int mParentHeight;
    private int mPeekHeight;
    private int mState;
    private boolean mTouchingScrollingChild;
    private VelocityTracker mVelocityTracker;
    private ViewDragHelper mViewDragHelper;
    private WeakReference<V> mViewRef;

    /* renamed from: android.support.design.widget.BottomSheetBehavior.1 */
    class C00071 extends Callback {
        C00071() {
        }

        public boolean tryCaptureView(View view, int i) {
            if (BottomSheetBehavior.this.mState == BottomSheetBehavior.STATE_DRAGGING || BottomSheetBehavior.this.mTouchingScrollingChild) {
                return false;
            }
            if (BottomSheetBehavior.this.mState == BottomSheetBehavior.STATE_EXPANDED && BottomSheetBehavior.this.mActivePointerId == i) {
                View view2 = (View) BottomSheetBehavior.this.mNestedScrollingChildRef.get();
                if (view2 != null && ViewCompat.canScrollVertically(view2, -1)) {
                    return false;
                }
            }
            boolean z = (BottomSheetBehavior.this.mViewRef == null || BottomSheetBehavior.this.mViewRef.get() != view) ? false : BottomSheetBehavior.STATE_DRAGGING;
            return z;
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            BottomSheetBehavior.this.dispatchOnSlide(i2);
        }

        public void onViewDragStateChanged(int i) {
            if (i == BottomSheetBehavior.STATE_DRAGGING) {
                BottomSheetBehavior.this.setStateInternal(BottomSheetBehavior.STATE_DRAGGING);
            }
        }

        public void onViewReleased(View view, float f, float f2) {
            int access$700;
            int i = BottomSheetBehavior.STATE_EXPANDED;
            if (f2 < 0.0f) {
                access$700 = BottomSheetBehavior.this.mMinOffset;
            } else if (BottomSheetBehavior.this.mHideable && BottomSheetBehavior.this.shouldHide(view, f2)) {
                access$700 = BottomSheetBehavior.this.mParentHeight;
                i = BottomSheetBehavior.STATE_HIDDEN;
            } else if (f2 == 0.0f) {
                int top = view.getTop();
                if (Math.abs(top - BottomSheetBehavior.this.mMinOffset) < Math.abs(top - BottomSheetBehavior.this.mMaxOffset)) {
                    access$700 = BottomSheetBehavior.this.mMinOffset;
                } else {
                    access$700 = BottomSheetBehavior.this.mMaxOffset;
                    i = BottomSheetBehavior.STATE_COLLAPSED;
                }
            } else {
                access$700 = BottomSheetBehavior.this.mMaxOffset;
                i = BottomSheetBehavior.STATE_COLLAPSED;
            }
            if (BottomSheetBehavior.this.mViewDragHelper.settleCapturedViewAt(view.getLeft(), access$700)) {
                BottomSheetBehavior.this.setStateInternal(BottomSheetBehavior.STATE_SETTLING);
                ViewCompat.postOnAnimation(view, new SettleRunnable(view, i));
                return;
            }
            BottomSheetBehavior.this.setStateInternal(i);
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            return MathUtils.constrain(i, BottomSheetBehavior.this.mMinOffset, BottomSheetBehavior.this.mHideable ? BottomSheetBehavior.this.mParentHeight : BottomSheetBehavior.this.mMaxOffset);
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            return view.getLeft();
        }

        public int getViewVerticalDragRange(View view) {
            if (BottomSheetBehavior.this.mHideable) {
                return BottomSheetBehavior.this.mParentHeight - BottomSheetBehavior.this.mMinOffset;
            }
            return BottomSheetBehavior.this.mMaxOffset - BottomSheetBehavior.this.mMinOffset;
        }
    }

    public abstract class BottomSheetCallback {
        public abstract void onSlide(@NonNull View view, float f);

        public abstract void onStateChanged(@NonNull View view, int i);
    }

    public class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        final int state;

        /* renamed from: android.support.design.widget.BottomSheetBehavior.SavedState.1 */
        final class C00081 implements Creator<SavedState> {
            C00081() {
            }

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.state = parcel.readInt();
        }

        public SavedState(Parcelable parcelable, int i) {
            super(parcelable);
            this.state = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.state);
        }

        static {
            CREATOR = new C00081();
        }
    }

    class SettleRunnable implements Runnable {
        private final int mTargetState;
        private final View mView;

        SettleRunnable(View view, int i) {
            this.mView = view;
            this.mTargetState = i;
        }

        public void run() {
            if (BottomSheetBehavior.this.mViewDragHelper == null || !BottomSheetBehavior.this.mViewDragHelper.continueSettling(true)) {
                BottomSheetBehavior.this.setStateInternal(this.mTargetState);
            } else {
                ViewCompat.postOnAnimation(this.mView, this);
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    public BottomSheetBehavior() {
        this.mState = STATE_COLLAPSED;
        this.mDragCallback = new C00071();
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mState = STATE_COLLAPSED;
        this.mDragCallback = new C00071();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0000R.styleable.BottomSheetBehavior_Params);
        setPeekHeight(obtainStyledAttributes.getDimensionPixelSize(C0000R.styleable.BottomSheetBehavior_Params_behavior_peekHeight, 0));
        setHideable(obtainStyledAttributes.getBoolean(C0000R.styleable.BottomSheetBehavior_Params_behavior_hideable, false));
        obtainStyledAttributes.recycle();
        this.mMaximumVelocity = (float) ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, v), this.mState);
    }

    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(coordinatorLayout, v, savedState.getSuperState());
        if (savedState.state == STATE_DRAGGING || savedState.state == STATE_SETTLING) {
            this.mState = STATE_COLLAPSED;
        } else {
            this.mState = savedState.state;
        }
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
        if (!(this.mState == STATE_DRAGGING || this.mState == STATE_SETTLING)) {
            if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(v)) {
                ViewCompat.setFitsSystemWindows(v, true);
            }
            coordinatorLayout.onLayoutChild(v, i);
        }
        this.mParentHeight = coordinatorLayout.getHeight();
        this.mMinOffset = Math.max(0, this.mParentHeight - v.getHeight());
        this.mMaxOffset = Math.max(this.mParentHeight - this.mPeekHeight, this.mMinOffset);
        if (this.mState == STATE_EXPANDED) {
            ViewCompat.offsetTopAndBottom(v, this.mMinOffset);
        } else if (this.mHideable && this.mState == STATE_HIDDEN) {
            ViewCompat.offsetTopAndBottom(v, this.mParentHeight);
        } else if (this.mState == STATE_COLLAPSED) {
            ViewCompat.offsetTopAndBottom(v, this.mMaxOffset);
        }
        if (this.mViewDragHelper == null) {
            this.mViewDragHelper = ViewDragHelper.create(coordinatorLayout, this.mDragCallback);
        }
        this.mViewRef = new WeakReference(v);
        this.mNestedScrollingChildRef = new WeakReference(findScrollingChild(v));
        return true;
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean z = true;
        if (!v.isShown()) {
            return false;
        }
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 0) {
            reset();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        switch (actionMasked) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                int x = (int) motionEvent.getX();
                this.mInitialY = (int) motionEvent.getY();
                View view = (View) this.mNestedScrollingChildRef.get();
                if (view != null && coordinatorLayout.isPointInChildBounds(view, x, this.mInitialY)) {
                    this.mActivePointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.mTouchingScrollingChild = true;
                }
                boolean z2 = this.mActivePointerId == -1 && !coordinatorLayout.isPointInChildBounds(v, x, this.mInitialY);
                this.mIgnoreEvents = z2;
                break;
            case STATE_DRAGGING /*1*/:
            case STATE_EXPANDED /*3*/:
                this.mTouchingScrollingChild = false;
                this.mActivePointerId = -1;
                if (this.mIgnoreEvents) {
                    this.mIgnoreEvents = false;
                    return false;
                }
                break;
        }
        if (!this.mIgnoreEvents && this.mViewDragHelper.shouldInterceptTouchEvent(motionEvent)) {
            return true;
        }
        view = (View) this.mNestedScrollingChildRef.get();
        if (actionMasked != STATE_SETTLING || view == null || this.mIgnoreEvents || this.mState == STATE_DRAGGING || coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY()) || Math.abs(((float) this.mInitialY) - motionEvent.getY()) <= ((float) this.mViewDragHelper.getTouchSlop())) {
            z = false;
        }
        return z;
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (!v.isShown()) {
            return false;
        }
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (this.mState == STATE_DRAGGING && actionMasked == 0) {
            return true;
        }
        this.mViewDragHelper.processTouchEvent(motionEvent);
        if (actionMasked == 0) {
            reset();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        if (actionMasked != STATE_SETTLING || this.mIgnoreEvents || Math.abs(((float) this.mInitialY) - motionEvent.getY()) <= ((float) this.mViewDragHelper.getTouchSlop())) {
            return true;
        }
        this.mViewDragHelper.captureChildView(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
        return true;
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i) {
        this.mLastNestedScrollDy = 0;
        this.mNestedScrolled = false;
        if ((i & STATE_SETTLING) != 0) {
            return true;
        }
        return false;
    }

    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr) {
        if (view == ((View) this.mNestedScrollingChildRef.get())) {
            int top = v.getTop();
            int i3 = top - i2;
            if (i2 > 0) {
                if (i3 < this.mMinOffset) {
                    iArr[STATE_DRAGGING] = top - this.mMinOffset;
                    ViewCompat.offsetTopAndBottom(v, -iArr[STATE_DRAGGING]);
                    setStateInternal(STATE_EXPANDED);
                } else {
                    iArr[STATE_DRAGGING] = i2;
                    ViewCompat.offsetTopAndBottom(v, -i2);
                    setStateInternal(STATE_DRAGGING);
                }
            } else if (i2 < 0 && !ViewCompat.canScrollVertically(view, -1)) {
                if (i3 <= this.mMaxOffset || this.mHideable) {
                    iArr[STATE_DRAGGING] = i2;
                    ViewCompat.offsetTopAndBottom(v, -i2);
                    setStateInternal(STATE_DRAGGING);
                } else {
                    iArr[STATE_DRAGGING] = top - this.mMaxOffset;
                    ViewCompat.offsetTopAndBottom(v, -iArr[STATE_DRAGGING]);
                    setStateInternal(STATE_COLLAPSED);
                }
            }
            dispatchOnSlide(v.getTop());
            this.mLastNestedScrollDy = i2;
            this.mNestedScrolled = true;
        }
    }

    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view) {
        int i = STATE_EXPANDED;
        if (v.getTop() == this.mMinOffset) {
            setStateInternal(STATE_EXPANDED);
        } else if (view == this.mNestedScrollingChildRef.get() && this.mNestedScrolled) {
            int i2;
            if (this.mLastNestedScrollDy > 0) {
                i2 = this.mMinOffset;
            } else if (this.mHideable && shouldHide(v, getYVelocity())) {
                i2 = this.mParentHeight;
                i = STATE_HIDDEN;
            } else if (this.mLastNestedScrollDy == 0) {
                int top = v.getTop();
                if (Math.abs(top - this.mMinOffset) < Math.abs(top - this.mMaxOffset)) {
                    i2 = this.mMinOffset;
                } else {
                    i2 = this.mMaxOffset;
                    i = STATE_COLLAPSED;
                }
            } else {
                i2 = this.mMaxOffset;
                i = STATE_COLLAPSED;
            }
            if (this.mViewDragHelper.smoothSlideViewTo(v, v.getLeft(), i2)) {
                setStateInternal(STATE_SETTLING);
                ViewCompat.postOnAnimation(v, new SettleRunnable(v, i));
            } else {
                setStateInternal(i);
            }
            this.mNestedScrolled = false;
        }
    }

    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2) {
        return view == this.mNestedScrollingChildRef.get() && (this.mState != STATE_EXPANDED || super.onNestedPreFling(coordinatorLayout, v, view, f, f2));
    }

    public final void setPeekHeight(int i) {
        this.mPeekHeight = Math.max(0, i);
        this.mMaxOffset = this.mParentHeight - i;
    }

    public final int getPeekHeight() {
        return this.mPeekHeight;
    }

    public void setHideable(boolean z) {
        this.mHideable = z;
    }

    public boolean isHideable() {
        return this.mHideable;
    }

    public void setBottomSheetCallback(BottomSheetCallback bottomSheetCallback) {
        this.mCallback = bottomSheetCallback;
    }

    public final void setState(int i) {
        if (i != this.mState) {
            if (this.mViewRef != null) {
                View view = (View) this.mViewRef.get();
                if (view != null) {
                    int i2;
                    if (i == STATE_COLLAPSED) {
                        i2 = this.mMaxOffset;
                    } else if (i == STATE_EXPANDED) {
                        i2 = this.mMinOffset;
                    } else if (this.mHideable && i == STATE_HIDDEN) {
                        i2 = this.mParentHeight;
                    } else {
                        throw new IllegalArgumentException("Illegal state argument: " + i);
                    }
                    setStateInternal(STATE_SETTLING);
                    if (this.mViewDragHelper.smoothSlideViewTo(view, view.getLeft(), i2)) {
                        ViewCompat.postOnAnimation(view, new SettleRunnable(view, i));
                    }
                }
            } else if (i == STATE_COLLAPSED || i == STATE_EXPANDED || (this.mHideable && i == STATE_HIDDEN)) {
                this.mState = i;
            }
        }
    }

    public final int getState() {
        return this.mState;
    }

    private void setStateInternal(int i) {
        if (this.mState != i) {
            this.mState = i;
            View view = (View) this.mViewRef.get();
            if (view != null && this.mCallback != null) {
                this.mCallback.onStateChanged(view, i);
            }
        }
    }

    private void reset() {
        this.mActivePointerId = -1;
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private boolean shouldHide(View view, float f) {
        if (view.getTop() >= this.mMaxOffset && Math.abs((((float) view.getTop()) + (HIDE_FRICTION * f)) - ((float) this.mMaxOffset)) / ((float) this.mPeekHeight) > HIDE_THRESHOLD) {
            return true;
        }
        return false;
    }

    private View findScrollingChild(View view) {
        if (view instanceof NestedScrollingChild) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i += STATE_DRAGGING) {
                View findScrollingChild = findScrollingChild(viewGroup.getChildAt(i));
                if (findScrollingChild != null) {
                    return findScrollingChild;
                }
            }
        }
        return null;
    }

    private float getYVelocity() {
        this.mVelocityTracker.computeCurrentVelocity(Constants.APPBOY_MINIMUM_NOTIFICATION_DURATION_MILLIS, this.mMaximumVelocity);
        return VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mActivePointerId);
    }

    private void dispatchOnSlide(int i) {
        View view = (View) this.mViewRef.get();
        if (view != null && this.mCallback != null) {
            if (i > this.mMaxOffset) {
                this.mCallback.onSlide(view, ((float) (this.mMaxOffset - i)) / ((float) this.mPeekHeight));
            } else {
                this.mCallback.onSlide(view, ((float) (this.mMaxOffset - i)) / ((float) (this.mMaxOffset - this.mMinOffset)));
            }
        }
    }

    public static <V extends View> BottomSheetBehavior<V> from(V v) {
        LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
            if (behavior instanceof BottomSheetBehavior) {
                return (BottomSheetBehavior) behavior;
            }
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
        }
        throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }
}
