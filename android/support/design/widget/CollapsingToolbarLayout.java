package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.design.C0000R;
import android.support.design.widget.AppBarLayout.OnOffsetChangedListener;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.appboy.support.ValidationUtils;
import com.google.android.gms.gcm.Task;

public class CollapsingToolbarLayout extends FrameLayout {
    private static final int SCRIM_ANIMATION_DURATION = 600;
    private final CollapsingTextHelper mCollapsingTextHelper;
    private boolean mCollapsingTitleEnabled;
    private Drawable mContentScrim;
    private int mCurrentOffset;
    private boolean mDrawCollapsingTitle;
    private View mDummyView;
    private int mExpandedMarginBottom;
    private int mExpandedMarginEnd;
    private int mExpandedMarginStart;
    private int mExpandedMarginTop;
    private WindowInsetsCompat mLastInsets;
    private OnOffsetChangedListener mOnOffsetChangedListener;
    private boolean mRefreshToolbar;
    private int mScrimAlpha;
    private ValueAnimatorCompat mScrimAnimator;
    private boolean mScrimsAreShown;
    private Drawable mStatusBarScrim;
    private final Rect mTmpRect;
    private Toolbar mToolbar;
    private View mToolbarDirectChild;
    private int mToolbarId;

    /* renamed from: android.support.design.widget.CollapsingToolbarLayout.1 */
    class C00111 implements OnApplyWindowInsetsListener {
        C00111() {
        }

        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            return CollapsingToolbarLayout.this.setWindowInsets(windowInsetsCompat);
        }
    }

    /* renamed from: android.support.design.widget.CollapsingToolbarLayout.2 */
    class C00122 implements AnimatorUpdateListener {
        C00122() {
        }

        public void onAnimationUpdate(ValueAnimatorCompat valueAnimatorCompat) {
            CollapsingToolbarLayout.this.setScrimAlpha(valueAnimatorCompat.getAnimatedIntValue());
        }
    }

    public class LayoutParams extends android.widget.FrameLayout.LayoutParams {
        public static final int COLLAPSE_MODE_OFF = 0;
        public static final int COLLAPSE_MODE_PARALLAX = 2;
        public static final int COLLAPSE_MODE_PIN = 1;
        private static final float DEFAULT_PARALLAX_MULTIPLIER = 0.5f;
        int mCollapseMode;
        float mParallaxMult;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mCollapseMode = COLLAPSE_MODE_OFF;
            this.mParallaxMult = DEFAULT_PARALLAX_MULTIPLIER;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0000R.styleable.CollapsingAppBarLayout_LayoutParams);
            this.mCollapseMode = obtainStyledAttributes.getInt(C0000R.styleable.CollapsingAppBarLayout_LayoutParams_layout_collapseMode, COLLAPSE_MODE_OFF);
            setParallaxMultiplier(obtainStyledAttributes.getFloat(C0000R.styleable.f0xad49a364, DEFAULT_PARALLAX_MULTIPLIER));
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.mCollapseMode = COLLAPSE_MODE_OFF;
            this.mParallaxMult = DEFAULT_PARALLAX_MULTIPLIER;
        }

        public LayoutParams(int i, int i2, int i3) {
            super(i, i2, i3);
            this.mCollapseMode = COLLAPSE_MODE_OFF;
            this.mParallaxMult = DEFAULT_PARALLAX_MULTIPLIER;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.mCollapseMode = COLLAPSE_MODE_OFF;
            this.mParallaxMult = DEFAULT_PARALLAX_MULTIPLIER;
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.mCollapseMode = COLLAPSE_MODE_OFF;
            this.mParallaxMult = DEFAULT_PARALLAX_MULTIPLIER;
        }

        public LayoutParams(android.widget.FrameLayout.LayoutParams layoutParams) {
            super(layoutParams);
            this.mCollapseMode = COLLAPSE_MODE_OFF;
            this.mParallaxMult = DEFAULT_PARALLAX_MULTIPLIER;
        }

        public void setCollapseMode(int i) {
            this.mCollapseMode = i;
        }

        public int getCollapseMode() {
            return this.mCollapseMode;
        }

        public void setParallaxMultiplier(float f) {
            this.mParallaxMult = f;
        }

        public float getParallaxMultiplier() {
            return this.mParallaxMult;
        }
    }

    class OffsetUpdateListener implements OnOffsetChangedListener {
        private OffsetUpdateListener() {
        }

        public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
            int systemWindowInsetTop;
            boolean z = false;
            CollapsingToolbarLayout.this.mCurrentOffset = i;
            if (CollapsingToolbarLayout.this.mLastInsets != null) {
                systemWindowInsetTop = CollapsingToolbarLayout.this.mLastInsets.getSystemWindowInsetTop();
            } else {
                systemWindowInsetTop = 0;
            }
            int totalScrollRange = appBarLayout.getTotalScrollRange();
            int childCount = CollapsingToolbarLayout.this.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = CollapsingToolbarLayout.this.getChildAt(i2);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                ViewOffsetHelper access$500 = CollapsingToolbarLayout.getViewOffsetHelper(childAt);
                switch (layoutParams.mCollapseMode) {
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        if ((CollapsingToolbarLayout.this.getHeight() - systemWindowInsetTop) + i < childAt.getHeight()) {
                            break;
                        }
                        access$500.setTopAndBottomOffset(-i);
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        access$500.setTopAndBottomOffset(Math.round(layoutParams.mParallaxMult * ((float) (-i))));
                        break;
                    default:
                        break;
                }
            }
            if (!(CollapsingToolbarLayout.this.mContentScrim == null && CollapsingToolbarLayout.this.mStatusBarScrim == null)) {
                CollapsingToolbarLayout collapsingToolbarLayout = CollapsingToolbarLayout.this;
                if (CollapsingToolbarLayout.this.getHeight() + i < CollapsingToolbarLayout.this.getScrimTriggerOffset() + systemWindowInsetTop) {
                    z = true;
                }
                collapsingToolbarLayout.setScrimsShown(z);
            }
            if (CollapsingToolbarLayout.this.mStatusBarScrim != null && systemWindowInsetTop > 0) {
                ViewCompat.postInvalidateOnAnimation(CollapsingToolbarLayout.this);
            }
            CollapsingToolbarLayout.this.mCollapsingTextHelper.setExpansionFraction(((float) Math.abs(i)) / ((float) ((CollapsingToolbarLayout.this.getHeight() - ViewCompat.getMinimumHeight(CollapsingToolbarLayout.this)) - systemWindowInsetTop)));
            if (Math.abs(i) == totalScrollRange) {
                ViewCompat.setElevation(appBarLayout, appBarLayout.getTargetElevation());
            } else {
                ViewCompat.setElevation(appBarLayout, 0.0f);
            }
        }
    }

    public CollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRefreshToolbar = true;
        this.mTmpRect = new Rect();
        ThemeUtils.checkAppCompatTheme(context);
        this.mCollapsingTextHelper = new CollapsingTextHelper(this);
        this.mCollapsingTextHelper.setTextSizeInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0000R.styleable.CollapsingToolbarLayout, i, C0000R.style.Widget_Design_CollapsingToolbar);
        this.mCollapsingTextHelper.setExpandedTextGravity(obtainStyledAttributes.getInt(C0000R.styleable.CollapsingToolbarLayout_expandedTitleGravity, 8388691));
        this.mCollapsingTextHelper.setCollapsedTextGravity(obtainStyledAttributes.getInt(C0000R.styleable.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(C0000R.styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
        this.mExpandedMarginBottom = dimensionPixelSize;
        this.mExpandedMarginEnd = dimensionPixelSize;
        this.mExpandedMarginTop = dimensionPixelSize;
        this.mExpandedMarginStart = dimensionPixelSize;
        if (obtainStyledAttributes.hasValue(C0000R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart)) {
            this.mExpandedMarginStart = obtainStyledAttributes.getDimensionPixelSize(C0000R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart, 0);
        }
        if (obtainStyledAttributes.hasValue(C0000R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd)) {
            this.mExpandedMarginEnd = obtainStyledAttributes.getDimensionPixelSize(C0000R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd, 0);
        }
        if (obtainStyledAttributes.hasValue(C0000R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop)) {
            this.mExpandedMarginTop = obtainStyledAttributes.getDimensionPixelSize(C0000R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop, 0);
        }
        if (obtainStyledAttributes.hasValue(C0000R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom)) {
            this.mExpandedMarginBottom = obtainStyledAttributes.getDimensionPixelSize(C0000R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom, 0);
        }
        this.mCollapsingTitleEnabled = obtainStyledAttributes.getBoolean(C0000R.styleable.CollapsingToolbarLayout_titleEnabled, true);
        setTitle(obtainStyledAttributes.getText(C0000R.styleable.CollapsingToolbarLayout_title));
        this.mCollapsingTextHelper.setExpandedTextAppearance(C0000R.style.TextAppearance_Design_CollapsingToolbar_Expanded);
        this.mCollapsingTextHelper.setCollapsedTextAppearance(C0000R.style.TextAppearance_AppCompat_Widget_ActionBar_Title);
        if (obtainStyledAttributes.hasValue(C0000R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance)) {
            this.mCollapsingTextHelper.setExpandedTextAppearance(obtainStyledAttributes.getResourceId(C0000R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance, 0));
        }
        if (obtainStyledAttributes.hasValue(C0000R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance)) {
            this.mCollapsingTextHelper.setCollapsedTextAppearance(obtainStyledAttributes.getResourceId(C0000R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance, 0));
        }
        setContentScrim(obtainStyledAttributes.getDrawable(C0000R.styleable.CollapsingToolbarLayout_contentScrim));
        setStatusBarScrim(obtainStyledAttributes.getDrawable(C0000R.styleable.CollapsingToolbarLayout_statusBarScrim));
        this.mToolbarId = obtainStyledAttributes.getResourceId(C0000R.styleable.CollapsingToolbarLayout_toolbarId, -1);
        obtainStyledAttributes.recycle();
        setWillNotDraw(false);
        ViewCompat.setOnApplyWindowInsetsListener(this, new C00111());
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            if (this.mOnOffsetChangedListener == null) {
                this.mOnOffsetChangedListener = new OffsetUpdateListener();
            }
            ((AppBarLayout) parent).addOnOffsetChangedListener(this.mOnOffsetChangedListener);
        }
        ViewCompat.requestApplyInsets(this);
    }

    protected void onDetachedFromWindow() {
        ViewParent parent = getParent();
        if (this.mOnOffsetChangedListener != null && (parent instanceof AppBarLayout)) {
            ((AppBarLayout) parent).removeOnOffsetChangedListener(this.mOnOffsetChangedListener);
        }
        super.onDetachedFromWindow();
    }

    private WindowInsetsCompat setWindowInsets(WindowInsetsCompat windowInsetsCompat) {
        if (this.mLastInsets != windowInsetsCompat) {
            this.mLastInsets = windowInsetsCompat;
            requestLayout();
        }
        return windowInsetsCompat.consumeSystemWindowInsets();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        ensureToolbar();
        if (this.mToolbar == null && this.mContentScrim != null && this.mScrimAlpha > 0) {
            this.mContentScrim.mutate().setAlpha(this.mScrimAlpha);
            this.mContentScrim.draw(canvas);
        }
        if (this.mCollapsingTitleEnabled && this.mDrawCollapsingTitle) {
            this.mCollapsingTextHelper.draw(canvas);
        }
        if (this.mStatusBarScrim != null && this.mScrimAlpha > 0) {
            int systemWindowInsetTop = this.mLastInsets != null ? this.mLastInsets.getSystemWindowInsetTop() : 0;
            if (systemWindowInsetTop > 0) {
                this.mStatusBarScrim.setBounds(0, -this.mCurrentOffset, getWidth(), systemWindowInsetTop - this.mCurrentOffset);
                this.mStatusBarScrim.mutate().setAlpha(this.mScrimAlpha);
                this.mStatusBarScrim.draw(canvas);
            }
        }
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        ensureToolbar();
        if (view == this.mToolbar && this.mContentScrim != null && this.mScrimAlpha > 0) {
            this.mContentScrim.mutate().setAlpha(this.mScrimAlpha);
            this.mContentScrim.draw(canvas);
        }
        return super.drawChild(canvas, view, j);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.mContentScrim != null) {
            this.mContentScrim.setBounds(0, 0, i, i2);
        }
    }

    private void ensureToolbar() {
        if (this.mRefreshToolbar) {
            this.mToolbar = null;
            this.mToolbarDirectChild = null;
            if (this.mToolbarId != -1) {
                this.mToolbar = (Toolbar) findViewById(this.mToolbarId);
                if (this.mToolbar != null) {
                    this.mToolbarDirectChild = findDirectChild(this.mToolbar);
                }
            }
            if (this.mToolbar == null) {
                Toolbar toolbar;
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = getChildAt(i);
                    if (childAt instanceof Toolbar) {
                        toolbar = (Toolbar) childAt;
                        break;
                    }
                }
                toolbar = null;
                this.mToolbar = toolbar;
            }
            updateDummyView();
            this.mRefreshToolbar = false;
        }
    }

    private View findDirectChild(View view) {
        CollapsingToolbarLayout parent = view.getParent();
        View view2 = view;
        while (parent != this && parent != null) {
            if (parent instanceof View) {
                view2 = parent;
            }
            parent = parent.getParent();
        }
        return view2;
    }

    private void updateDummyView() {
        if (!(this.mCollapsingTitleEnabled || this.mDummyView == null)) {
            ViewParent parent = this.mDummyView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.mDummyView);
            }
        }
        if (this.mCollapsingTitleEnabled && this.mToolbar != null) {
            if (this.mDummyView == null) {
                this.mDummyView = new View(getContext());
            }
            if (this.mDummyView.getParent() == null) {
                this.mToolbar.addView(this.mDummyView, -1, -1);
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        ensureToolbar();
        super.onMeasure(i, i2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6 = 1;
        int i7 = 0;
        super.onLayout(z, i, i2, i3, i4);
        if (this.mCollapsingTitleEnabled && this.mDummyView != null) {
            boolean z2 = ViewCompat.isAttachedToWindow(this.mDummyView) && this.mDummyView.getVisibility() == 0;
            this.mDrawCollapsingTitle = z2;
            if (this.mDrawCollapsingTitle) {
                if (this.mToolbarDirectChild == null || this.mToolbarDirectChild == this) {
                    i5 = 0;
                } else {
                    i5 = ((LayoutParams) this.mToolbarDirectChild.getLayoutParams()).bottomMargin;
                }
                ViewGroupUtils.getDescendantRect(this, this.mDummyView, this.mTmpRect);
                this.mCollapsingTextHelper.setCollapsedBounds(this.mTmpRect.left, (i4 - this.mTmpRect.height()) - i5, this.mTmpRect.right, i4 - i5);
                if (ViewCompat.getLayoutDirection(this) != 1) {
                    i6 = 0;
                }
                CollapsingTextHelper collapsingTextHelper = this.mCollapsingTextHelper;
                i5 = i6 != 0 ? this.mExpandedMarginEnd : this.mExpandedMarginStart;
                int i8 = this.mTmpRect.bottom + this.mExpandedMarginTop;
                int i9 = i3 - i;
                if (i6 != 0) {
                    i6 = this.mExpandedMarginStart;
                } else {
                    i6 = this.mExpandedMarginEnd;
                }
                collapsingTextHelper.setExpandedBounds(i5, i8, i9 - i6, (i4 - i2) - this.mExpandedMarginBottom);
                this.mCollapsingTextHelper.recalculate();
            }
        }
        i5 = getChildCount();
        while (i7 < i5) {
            View childAt = getChildAt(i7);
            if (!(this.mLastInsets == null || ViewCompat.getFitsSystemWindows(childAt))) {
                int systemWindowInsetTop = this.mLastInsets.getSystemWindowInsetTop();
                if (childAt.getTop() < systemWindowInsetTop) {
                    ViewCompat.offsetTopAndBottom(childAt, systemWindowInsetTop);
                }
            }
            getViewOffsetHelper(childAt).onViewLayout();
            i7++;
        }
        if (this.mToolbar != null) {
            if (this.mCollapsingTitleEnabled && TextUtils.isEmpty(this.mCollapsingTextHelper.getText())) {
                this.mCollapsingTextHelper.setText(this.mToolbar.getTitle());
            }
            if (this.mToolbarDirectChild == null || this.mToolbarDirectChild == this) {
                setMinimumHeight(getHeightWithMargins(this.mToolbar));
            } else {
                setMinimumHeight(getHeightWithMargins(this.mToolbarDirectChild));
            }
        }
    }

    private static int getHeightWithMargins(@NonNull View view) {
        android.view.ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof MarginLayoutParams)) {
            return view.getHeight();
        }
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
        return marginLayoutParams.bottomMargin + (view.getHeight() + marginLayoutParams.topMargin);
    }

    private static ViewOffsetHelper getViewOffsetHelper(View view) {
        ViewOffsetHelper viewOffsetHelper = (ViewOffsetHelper) view.getTag(C0000R.id.view_offset_helper);
        if (viewOffsetHelper != null) {
            return viewOffsetHelper;
        }
        viewOffsetHelper = new ViewOffsetHelper(view);
        view.setTag(C0000R.id.view_offset_helper, viewOffsetHelper);
        return viewOffsetHelper;
    }

    public void setTitle(@Nullable CharSequence charSequence) {
        this.mCollapsingTextHelper.setText(charSequence);
    }

    @Nullable
    public CharSequence getTitle() {
        return this.mCollapsingTitleEnabled ? this.mCollapsingTextHelper.getText() : null;
    }

    public void setTitleEnabled(boolean z) {
        if (z != this.mCollapsingTitleEnabled) {
            this.mCollapsingTitleEnabled = z;
            updateDummyView();
            requestLayout();
        }
    }

    public boolean isTitleEnabled() {
        return this.mCollapsingTitleEnabled;
    }

    public void setScrimsShown(boolean z) {
        boolean z2 = ViewCompat.isLaidOut(this) && !isInEditMode();
        setScrimsShown(z, z2);
    }

    public void setScrimsShown(boolean z, boolean z2) {
        int i = ValidationUtils.APPBOY_STRING_MAX_LENGTH;
        if (this.mScrimsAreShown != z) {
            if (z2) {
                if (!z) {
                    i = 0;
                }
                animateScrim(i);
            } else {
                if (!z) {
                    i = 0;
                }
                setScrimAlpha(i);
            }
            this.mScrimsAreShown = z;
        }
    }

    private void animateScrim(int i) {
        ensureToolbar();
        if (this.mScrimAnimator == null) {
            this.mScrimAnimator = ViewUtils.createAnimator();
            this.mScrimAnimator.setDuration(SCRIM_ANIMATION_DURATION);
            this.mScrimAnimator.setInterpolator(i > this.mScrimAlpha ? AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR : AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
            this.mScrimAnimator.setUpdateListener(new C00122());
        } else if (this.mScrimAnimator.isRunning()) {
            this.mScrimAnimator.cancel();
        }
        this.mScrimAnimator.setIntValues(this.mScrimAlpha, i);
        this.mScrimAnimator.start();
    }

    private void setScrimAlpha(int i) {
        if (i != this.mScrimAlpha) {
            if (!(this.mContentScrim == null || this.mToolbar == null)) {
                ViewCompat.postInvalidateOnAnimation(this.mToolbar);
            }
            this.mScrimAlpha = i;
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setContentScrim(@Nullable Drawable drawable) {
        Drawable drawable2 = null;
        if (this.mContentScrim != drawable) {
            if (this.mContentScrim != null) {
                this.mContentScrim.setCallback(null);
            }
            if (drawable != null) {
                drawable2 = drawable.mutate();
            }
            this.mContentScrim = drawable2;
            if (this.mContentScrim != null) {
                this.mContentScrim.setBounds(0, 0, getWidth(), getHeight());
                this.mContentScrim.setCallback(this);
                this.mContentScrim.setAlpha(this.mScrimAlpha);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setContentScrimColor(@ColorInt int i) {
        setContentScrim(new ColorDrawable(i));
    }

    public void setContentScrimResource(@DrawableRes int i) {
        setContentScrim(ContextCompat.getDrawable(getContext(), i));
    }

    @Nullable
    public Drawable getContentScrim() {
        return this.mContentScrim;
    }

    public void setStatusBarScrim(@Nullable Drawable drawable) {
        Drawable drawable2 = null;
        if (this.mStatusBarScrim != drawable) {
            if (this.mStatusBarScrim != null) {
                this.mStatusBarScrim.setCallback(null);
            }
            if (drawable != null) {
                drawable2 = drawable.mutate();
            }
            this.mStatusBarScrim = drawable2;
            if (this.mStatusBarScrim != null) {
                if (this.mStatusBarScrim.isStateful()) {
                    this.mStatusBarScrim.setState(getDrawableState());
                }
                DrawableCompat.setLayoutDirection(this.mStatusBarScrim, ViewCompat.getLayoutDirection(this));
                this.mStatusBarScrim.setVisible(getVisibility() == 0, false);
                this.mStatusBarScrim.setCallback(this);
                this.mStatusBarScrim.setAlpha(this.mScrimAlpha);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        int i = 0;
        Drawable drawable = this.mStatusBarScrim;
        if (drawable != null && drawable.isStateful()) {
            i = 0 | drawable.setState(drawableState);
        }
        drawable = this.mContentScrim;
        if (drawable != null && drawable.isStateful()) {
            i |= drawable.setState(drawableState);
        }
        if (i != 0) {
            invalidate();
        }
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mContentScrim || drawable == this.mStatusBarScrim;
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        if (!(this.mStatusBarScrim == null || this.mStatusBarScrim.isVisible() == z)) {
            this.mStatusBarScrim.setVisible(z, false);
        }
        if (this.mContentScrim != null && this.mContentScrim.isVisible() != z) {
            this.mContentScrim.setVisible(z, false);
        }
    }

    public void setStatusBarScrimColor(@ColorInt int i) {
        setStatusBarScrim(new ColorDrawable(i));
    }

    public void setStatusBarScrimResource(@DrawableRes int i) {
        setStatusBarScrim(ContextCompat.getDrawable(getContext(), i));
    }

    @Nullable
    public Drawable getStatusBarScrim() {
        return this.mStatusBarScrim;
    }

    public void setCollapsedTitleTextAppearance(@StyleRes int i) {
        this.mCollapsingTextHelper.setCollapsedTextAppearance(i);
    }

    public void setCollapsedTitleTextColor(@ColorInt int i) {
        this.mCollapsingTextHelper.setCollapsedTextColor(i);
    }

    public void setCollapsedTitleGravity(int i) {
        this.mCollapsingTextHelper.setCollapsedTextGravity(i);
    }

    public int getCollapsedTitleGravity() {
        return this.mCollapsingTextHelper.getCollapsedTextGravity();
    }

    public void setExpandedTitleTextAppearance(@StyleRes int i) {
        this.mCollapsingTextHelper.setExpandedTextAppearance(i);
    }

    public void setExpandedTitleColor(@ColorInt int i) {
        this.mCollapsingTextHelper.setExpandedTextColor(i);
    }

    public void setExpandedTitleGravity(int i) {
        this.mCollapsingTextHelper.setExpandedTextGravity(i);
    }

    public int getExpandedTitleGravity() {
        return this.mCollapsingTextHelper.getExpandedTextGravity();
    }

    public void setCollapsedTitleTypeface(@Nullable Typeface typeface) {
        this.mCollapsingTextHelper.setCollapsedTypeface(typeface);
    }

    @NonNull
    public Typeface getCollapsedTitleTypeface() {
        return this.mCollapsingTextHelper.getCollapsedTypeface();
    }

    public void setExpandedTitleTypeface(@Nullable Typeface typeface) {
        this.mCollapsingTextHelper.setExpandedTypeface(typeface);
    }

    @NonNull
    public Typeface getExpandedTitleTypeface() {
        return this.mCollapsingTextHelper.getExpandedTypeface();
    }

    public void setExpandedTitleMargin(int i, int i2, int i3, int i4) {
        this.mExpandedMarginStart = i;
        this.mExpandedMarginTop = i2;
        this.mExpandedMarginEnd = i3;
        this.mExpandedMarginBottom = i4;
        requestLayout();
    }

    public int getExpandedTitleMarginStart() {
        return this.mExpandedMarginStart;
    }

    public void setExpandedTitleMarginStart(int i) {
        this.mExpandedMarginStart = i;
        requestLayout();
    }

    public int getExpandedTitleMarginTop() {
        return this.mExpandedMarginTop;
    }

    public void setExpandedTitleMarginTop(int i) {
        this.mExpandedMarginTop = i;
        requestLayout();
    }

    public int getExpandedTitleMarginEnd() {
        return this.mExpandedMarginEnd;
    }

    public void setExpandedTitleMarginEnd(int i) {
        this.mExpandedMarginEnd = i;
        requestLayout();
    }

    public int getExpandedTitleMarginBottom() {
        return this.mExpandedMarginBottom;
    }

    public void setExpandedTitleMarginBottom(int i) {
        this.mExpandedMarginBottom = i;
        requestLayout();
    }

    final int getScrimTriggerOffset() {
        return ViewCompat.getMinimumHeight(this) * 2;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(super.generateDefaultLayoutParams());
    }

    public android.widget.FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected android.widget.FrameLayout.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }
}
