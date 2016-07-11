package android.support.design.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.C0000R;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;
import android.support.v4.util.Pools.SynchronizedPool;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.AppCompatDrawableManager;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.etsy.android.uikit.view.FullImageView;
import com.foresee.sdk.configuration.MeasureConfiguration;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class TabLayout extends HorizontalScrollView {
    private static final int ANIMATION_DURATION = 300;
    private static final int DEFAULT_GAP_TEXT_ICON = 8;
    private static final int DEFAULT_HEIGHT = 48;
    private static final int DEFAULT_HEIGHT_WITH_TEXT_ICON = 72;
    private static final int FIXED_WRAP_GUTTER_MIN = 16;
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_FILL = 0;
    private static final int INVALID_WIDTH = -1;
    public static final int MODE_FIXED = 1;
    public static final int MODE_SCROLLABLE = 0;
    private static final int MOTION_NON_ADJACENT_OFFSET = 24;
    private static final int TAB_MIN_WIDTH_MARGIN = 56;
    private static final Pool<Tab> sTabPool;
    private int mContentInsetStart;
    private int mMode;
    private OnTabSelectedListener mOnTabSelectedListener;
    private TabLayoutOnPageChangeListener mPageChangeListener;
    private PagerAdapter mPagerAdapter;
    private DataSetObserver mPagerAdapterObserver;
    private final int mRequestedTabMaxWidth;
    private final int mRequestedTabMinWidth;
    private ValueAnimatorCompat mScrollAnimator;
    private final int mScrollableTabMinWidth;
    private Tab mSelectedTab;
    private final int mTabBackgroundResId;
    private int mTabGravity;
    private int mTabMaxWidth;
    private int mTabPaddingBottom;
    private int mTabPaddingEnd;
    private int mTabPaddingStart;
    private int mTabPaddingTop;
    private final SlidingTabStrip mTabStrip;
    private int mTabTextAppearance;
    private ColorStateList mTabTextColors;
    private float mTabTextMultiLineSize;
    private float mTabTextSize;
    private final Pool<TabView> mTabViewPool;
    private final ArrayList<Tab> mTabs;
    private ViewPager mViewPager;

    /* renamed from: android.support.design.widget.TabLayout.1 */
    class C00371 implements AnimatorUpdateListener {
        C00371() {
        }

        public void onAnimationUpdate(ValueAnimatorCompat valueAnimatorCompat) {
            TabLayout.this.scrollTo(valueAnimatorCompat.getAnimatedIntValue(), TabLayout.MODE_SCROLLABLE);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public interface OnTabSelectedListener {
        void onTabReselected(Tab tab);

        void onTabSelected(Tab tab);

        void onTabUnselected(Tab tab);
    }

    class PagerAdapterObserver extends DataSetObserver {
        private PagerAdapterObserver() {
        }

        public void onChanged() {
            TabLayout.this.populateFromPagerAdapter();
        }

        public void onInvalidated() {
            TabLayout.this.populateFromPagerAdapter();
        }
    }

    class SlidingTabStrip extends LinearLayout {
        private ValueAnimatorCompat mIndicatorAnimator;
        private int mIndicatorLeft;
        private int mIndicatorRight;
        private int mSelectedIndicatorHeight;
        private final Paint mSelectedIndicatorPaint;
        private int mSelectedPosition;
        private float mSelectionOffset;

        /* renamed from: android.support.design.widget.TabLayout.SlidingTabStrip.1 */
        class C00381 implements AnimatorUpdateListener {
            final /* synthetic */ int val$startLeft;
            final /* synthetic */ int val$startRight;
            final /* synthetic */ int val$targetLeft;
            final /* synthetic */ int val$targetRight;

            C00381(int i, int i2, int i3, int i4) {
                this.val$startLeft = i;
                this.val$targetLeft = i2;
                this.val$startRight = i3;
                this.val$targetRight = i4;
            }

            public void onAnimationUpdate(ValueAnimatorCompat valueAnimatorCompat) {
                float animatedFraction = valueAnimatorCompat.getAnimatedFraction();
                SlidingTabStrip.this.setIndicatorPosition(AnimationUtils.lerp(this.val$startLeft, this.val$targetLeft, animatedFraction), AnimationUtils.lerp(this.val$startRight, this.val$targetRight, animatedFraction));
            }
        }

        /* renamed from: android.support.design.widget.TabLayout.SlidingTabStrip.2 */
        class C00392 extends AnimatorListenerAdapter {
            final /* synthetic */ int val$position;

            C00392(int i) {
                this.val$position = i;
            }

            public void onAnimationEnd(ValueAnimatorCompat valueAnimatorCompat) {
                SlidingTabStrip.this.mSelectedPosition = this.val$position;
                SlidingTabStrip.this.mSelectionOffset = 0.0f;
            }
        }

        SlidingTabStrip(Context context) {
            super(context);
            this.mSelectedPosition = TabLayout.INVALID_WIDTH;
            this.mIndicatorLeft = TabLayout.INVALID_WIDTH;
            this.mIndicatorRight = TabLayout.INVALID_WIDTH;
            setWillNotDraw(false);
            this.mSelectedIndicatorPaint = new Paint();
        }

        void setSelectedIndicatorColor(int i) {
            if (this.mSelectedIndicatorPaint.getColor() != i) {
                this.mSelectedIndicatorPaint.setColor(i);
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void setSelectedIndicatorHeight(int i) {
            if (this.mSelectedIndicatorHeight != i) {
                this.mSelectedIndicatorHeight = i;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        boolean childrenNeedLayout() {
            int childCount = getChildCount();
            for (int i = TabLayout.MODE_SCROLLABLE; i < childCount; i += TabLayout.MODE_FIXED) {
                if (getChildAt(i).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        void setIndicatorPositionFromTabPosition(int i, float f) {
            if (this.mIndicatorAnimator != null && this.mIndicatorAnimator.isRunning()) {
                this.mIndicatorAnimator.cancel();
            }
            this.mSelectedPosition = i;
            this.mSelectionOffset = f;
            updateIndicatorPosition();
        }

        float getIndicatorPosition() {
            return ((float) this.mSelectedPosition) + this.mSelectionOffset;
        }

        protected void onMeasure(int i, int i2) {
            boolean z = false;
            super.onMeasure(i, i2);
            if (MeasureSpec.getMode(i) == 1073741824 && TabLayout.this.mMode == TabLayout.MODE_FIXED && TabLayout.this.mTabGravity == TabLayout.MODE_FIXED) {
                int childCount = getChildCount();
                int i3 = TabLayout.MODE_SCROLLABLE;
                int i4 = TabLayout.MODE_SCROLLABLE;
                while (i3 < childCount) {
                    int max;
                    View childAt = getChildAt(i3);
                    if (childAt.getVisibility() == 0) {
                        max = Math.max(i4, childAt.getMeasuredWidth());
                    } else {
                        max = i4;
                    }
                    i3 += TabLayout.MODE_FIXED;
                    i4 = max;
                }
                if (i4 > 0) {
                    if (i4 * childCount <= getMeasuredWidth() - (TabLayout.this.dpToPx(TabLayout.FIXED_WRAP_GUTTER_MIN) * 2)) {
                        i3 = TabLayout.MODE_SCROLLABLE;
                        while (i3 < childCount) {
                            boolean z2;
                            LayoutParams layoutParams = (LayoutParams) getChildAt(i3).getLayoutParams();
                            if (layoutParams.width == i4 && layoutParams.weight == 0.0f) {
                                z2 = z;
                            } else {
                                layoutParams.width = i4;
                                layoutParams.weight = 0.0f;
                                z2 = TabLayout.MODE_FIXED;
                            }
                            i3 += TabLayout.MODE_FIXED;
                            z = z2;
                        }
                    } else {
                        TabLayout.this.mTabGravity = TabLayout.MODE_SCROLLABLE;
                        TabLayout.this.updateTabViews(false);
                        z = true;
                    }
                    if (z) {
                        super.onMeasure(i, i2);
                    }
                }
            }
        }

        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            if (this.mIndicatorAnimator == null || !this.mIndicatorAnimator.isRunning()) {
                updateIndicatorPosition();
                return;
            }
            this.mIndicatorAnimator.cancel();
            animateIndicatorToPosition(this.mSelectedPosition, Math.round(((float) this.mIndicatorAnimator.getDuration()) * (FullImageView.ASPECT_RATIO_SQUARE - this.mIndicatorAnimator.getAnimatedFraction())));
        }

        private void updateIndicatorPosition() {
            int i;
            int i2;
            View childAt = getChildAt(this.mSelectedPosition);
            if (childAt == null || childAt.getWidth() <= 0) {
                i = TabLayout.INVALID_WIDTH;
                i2 = TabLayout.INVALID_WIDTH;
            } else {
                i2 = childAt.getLeft();
                i = childAt.getRight();
                if (this.mSelectionOffset > 0.0f && this.mSelectedPosition < getChildCount() + TabLayout.INVALID_WIDTH) {
                    View childAt2 = getChildAt(this.mSelectedPosition + TabLayout.MODE_FIXED);
                    i2 = (int) ((((float) i2) * (FullImageView.ASPECT_RATIO_SQUARE - this.mSelectionOffset)) + (this.mSelectionOffset * ((float) childAt2.getLeft())));
                    i = (int) ((((float) i) * (FullImageView.ASPECT_RATIO_SQUARE - this.mSelectionOffset)) + (((float) childAt2.getRight()) * this.mSelectionOffset));
                }
            }
            setIndicatorPosition(i2, i);
        }

        private void setIndicatorPosition(int i, int i2) {
            if (i != this.mIndicatorLeft || i2 != this.mIndicatorRight) {
                this.mIndicatorLeft = i;
                this.mIndicatorRight = i2;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void animateIndicatorToPosition(int i, int i2) {
            if (this.mIndicatorAnimator != null && this.mIndicatorAnimator.isRunning()) {
                this.mIndicatorAnimator.cancel();
            }
            Object obj = ViewCompat.getLayoutDirection(this) == TabLayout.MODE_FIXED ? TabLayout.MODE_FIXED : null;
            View childAt = getChildAt(i);
            if (childAt == null) {
                updateIndicatorPosition();
                return;
            }
            int i3;
            int i4;
            int left = childAt.getLeft();
            int right = childAt.getRight();
            if (Math.abs(i - this.mSelectedPosition) <= TabLayout.MODE_FIXED) {
                i3 = this.mIndicatorLeft;
                i4 = this.mIndicatorRight;
            } else {
                int access$2100 = TabLayout.this.dpToPx(TabLayout.MOTION_NON_ADJACENT_OFFSET);
                if (i < this.mSelectedPosition) {
                    if (obj != null) {
                        i4 = left - access$2100;
                        i3 = i4;
                    } else {
                        i4 = right + access$2100;
                        i3 = i4;
                    }
                } else if (obj != null) {
                    i4 = right + access$2100;
                    i3 = i4;
                } else {
                    i4 = left - access$2100;
                    i3 = i4;
                }
            }
            if (i3 != left || i4 != right) {
                ValueAnimatorCompat createAnimator = ViewUtils.createAnimator();
                this.mIndicatorAnimator = createAnimator;
                createAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                createAnimator.setDuration(i2);
                createAnimator.setFloatValues(0.0f, FullImageView.ASPECT_RATIO_SQUARE);
                createAnimator.setUpdateListener(new C00381(i3, left, i4, right));
                createAnimator.setListener(new C00392(i));
                createAnimator.start();
            }
        }

        public void draw(Canvas canvas) {
            super.draw(canvas);
            if (this.mIndicatorLeft >= 0 && this.mIndicatorRight > this.mIndicatorLeft) {
                canvas.drawRect((float) this.mIndicatorLeft, (float) (getHeight() - this.mSelectedIndicatorHeight), (float) this.mIndicatorRight, (float) getHeight(), this.mSelectedIndicatorPaint);
            }
        }
    }

    public final class Tab {
        public static final int INVALID_POSITION = -1;
        private CharSequence mContentDesc;
        private View mCustomView;
        private Drawable mIcon;
        private TabLayout mParent;
        private int mPosition;
        private Object mTag;
        private CharSequence mText;
        private TabView mView;

        private Tab() {
            this.mPosition = INVALID_POSITION;
        }

        @Nullable
        public Object getTag() {
            return this.mTag;
        }

        @NonNull
        public Tab setTag(@Nullable Object obj) {
            this.mTag = obj;
            return this;
        }

        @Nullable
        public View getCustomView() {
            return this.mCustomView;
        }

        @NonNull
        public Tab setCustomView(@Nullable View view) {
            this.mCustomView = view;
            updateView();
            return this;
        }

        @NonNull
        public Tab setCustomView(@LayoutRes int i) {
            return setCustomView(LayoutInflater.from(this.mView.getContext()).inflate(i, this.mView, false));
        }

        @Nullable
        public Drawable getIcon() {
            return this.mIcon;
        }

        public int getPosition() {
            return this.mPosition;
        }

        void setPosition(int i) {
            this.mPosition = i;
        }

        @Nullable
        public CharSequence getText() {
            return this.mText;
        }

        @NonNull
        public Tab setIcon(@Nullable Drawable drawable) {
            this.mIcon = drawable;
            updateView();
            return this;
        }

        @NonNull
        public Tab setIcon(@DrawableRes int i) {
            if (this.mParent != null) {
                return setIcon(AppCompatDrawableManager.get().getDrawable(this.mParent.getContext(), i));
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        @NonNull
        public Tab setText(@Nullable CharSequence charSequence) {
            this.mText = charSequence;
            updateView();
            return this;
        }

        @NonNull
        public Tab setText(@StringRes int i) {
            if (this.mParent != null) {
                return setText(this.mParent.getResources().getText(i));
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public void select() {
            if (this.mParent == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            this.mParent.selectTab(this);
        }

        public boolean isSelected() {
            if (this.mParent != null) {
                return this.mParent.getSelectedTabPosition() == this.mPosition;
            } else {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
        }

        @NonNull
        public Tab setContentDescription(@StringRes int i) {
            if (this.mParent != null) {
                return setContentDescription(this.mParent.getResources().getText(i));
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        @NonNull
        public Tab setContentDescription(@Nullable CharSequence charSequence) {
            this.mContentDesc = charSequence;
            updateView();
            return this;
        }

        @Nullable
        public CharSequence getContentDescription() {
            return this.mContentDesc;
        }

        private void updateView() {
            if (this.mView != null) {
                this.mView.update();
            }
        }

        private void reset() {
            this.mParent = null;
            this.mView = null;
            this.mTag = null;
            this.mIcon = null;
            this.mText = null;
            this.mContentDesc = null;
            this.mPosition = INVALID_POSITION;
            this.mCustomView = null;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TabGravity {
    }

    public class TabLayoutOnPageChangeListener implements OnPageChangeListener {
        private int mPreviousScrollState;
        private int mScrollState;
        private final WeakReference<TabLayout> mTabLayoutRef;

        public TabLayoutOnPageChangeListener(TabLayout tabLayout) {
            this.mTabLayoutRef = new WeakReference(tabLayout);
        }

        public void onPageScrollStateChanged(int i) {
            this.mPreviousScrollState = this.mScrollState;
            this.mScrollState = i;
        }

        public void onPageScrolled(int i, float f, int i2) {
            boolean z = false;
            TabLayout tabLayout = (TabLayout) this.mTabLayoutRef.get();
            if (tabLayout != null) {
                boolean z2 = this.mScrollState != 2 || this.mPreviousScrollState == TabLayout.MODE_FIXED;
                if (!(this.mScrollState == 2 && this.mPreviousScrollState == 0)) {
                    z = true;
                }
                tabLayout.setScrollPosition(i, f, z2, z);
            }
        }

        public void onPageSelected(int i) {
            TabLayout tabLayout = (TabLayout) this.mTabLayoutRef.get();
            if (tabLayout != null && tabLayout.getSelectedTabPosition() != i) {
                boolean z = this.mScrollState == 0 || (this.mScrollState == 2 && this.mPreviousScrollState == 0);
                tabLayout.selectTab(tabLayout.getTabAt(i), z);
            }
        }

        private void reset() {
            this.mScrollState = TabLayout.MODE_SCROLLABLE;
            this.mPreviousScrollState = TabLayout.MODE_SCROLLABLE;
        }
    }

    class TabView extends LinearLayout implements OnLongClickListener {
        private ImageView mCustomIconView;
        private TextView mCustomTextView;
        private View mCustomView;
        private int mDefaultMaxLines;
        private ImageView mIconView;
        private Tab mTab;
        private TextView mTextView;

        public TabView(Context context) {
            super(context);
            this.mDefaultMaxLines = 2;
            if (TabLayout.this.mTabBackgroundResId != 0) {
                setBackgroundDrawable(AppCompatDrawableManager.get().getDrawable(context, TabLayout.this.mTabBackgroundResId));
            }
            ViewCompat.setPaddingRelative(this, TabLayout.this.mTabPaddingStart, TabLayout.this.mTabPaddingTop, TabLayout.this.mTabPaddingEnd, TabLayout.this.mTabPaddingBottom);
            setGravity(17);
            setOrientation(TabLayout.MODE_FIXED);
            setClickable(true);
        }

        public boolean performClick() {
            boolean performClick = super.performClick();
            if (this.mTab == null) {
                return performClick;
            }
            this.mTab.select();
            return true;
        }

        public void setSelected(boolean z) {
            Object obj = isSelected() != z ? TabLayout.MODE_FIXED : null;
            super.setSelected(z);
            if (obj != null && z) {
                sendAccessibilityEvent(4);
                if (this.mTextView != null) {
                    this.mTextView.setSelected(z);
                }
                if (this.mIconView != null) {
                    this.mIconView.setSelected(z);
                }
            }
        }

        @TargetApi(14)
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(android.support.v7.app.ActionBar.Tab.class.getName());
        }

        @TargetApi(14)
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(android.support.v7.app.ActionBar.Tab.class.getName());
        }

        public void onMeasure(int i, int i2) {
            int i3 = TabLayout.MODE_FIXED;
            int size = MeasureSpec.getSize(i);
            int mode = MeasureSpec.getMode(i);
            int access$1400 = TabLayout.this.getTabMaxWidth();
            if (access$1400 > 0 && (mode == 0 || size > access$1400)) {
                i = MeasureSpec.makeMeasureSpec(TabLayout.this.mTabMaxWidth, RtlSpacingHelper.UNDEFINED);
            }
            super.onMeasure(i, i2);
            if (this.mTextView != null) {
                getResources();
                float access$1600 = TabLayout.this.mTabTextSize;
                size = this.mDefaultMaxLines;
                if (this.mIconView != null && this.mIconView.getVisibility() == 0) {
                    size = TabLayout.MODE_FIXED;
                } else if (this.mTextView != null && this.mTextView.getLineCount() > TabLayout.MODE_FIXED) {
                    access$1600 = TabLayout.this.mTabTextMultiLineSize;
                }
                float textSize = this.mTextView.getTextSize();
                int lineCount = this.mTextView.getLineCount();
                int maxLines = TextViewCompat.getMaxLines(this.mTextView);
                if (access$1600 != textSize || (maxLines >= 0 && size != maxLines)) {
                    if (TabLayout.this.mMode == TabLayout.MODE_FIXED && access$1600 > textSize && lineCount == TabLayout.MODE_FIXED) {
                        Layout layout = this.mTextView.getLayout();
                        if (layout == null || approximateLineWidth(layout, TabLayout.MODE_SCROLLABLE, access$1600) > ((float) layout.getWidth())) {
                            i3 = TabLayout.MODE_SCROLLABLE;
                        }
                    }
                    if (i3 != 0) {
                        this.mTextView.setTextSize(TabLayout.MODE_SCROLLABLE, access$1600);
                        this.mTextView.setMaxLines(size);
                        super.onMeasure(i, i2);
                    }
                }
            }
        }

        private void setTab(@Nullable Tab tab) {
            if (tab != this.mTab) {
                this.mTab = tab;
                update();
            }
        }

        private void reset() {
            setTab(null);
            setSelected(false);
        }

        final void update() {
            Tab tab = this.mTab;
            View customView = tab != null ? tab.getCustomView() : null;
            if (customView != null) {
                TabView parent = customView.getParent();
                if (parent != this) {
                    if (parent != null) {
                        parent.removeView(customView);
                    }
                    addView(customView);
                }
                this.mCustomView = customView;
                if (this.mTextView != null) {
                    this.mTextView.setVisibility(TabLayout.DEFAULT_GAP_TEXT_ICON);
                }
                if (this.mIconView != null) {
                    this.mIconView.setVisibility(TabLayout.DEFAULT_GAP_TEXT_ICON);
                    this.mIconView.setImageDrawable(null);
                }
                this.mCustomTextView = (TextView) customView.findViewById(16908308);
                if (this.mCustomTextView != null) {
                    this.mDefaultMaxLines = TextViewCompat.getMaxLines(this.mCustomTextView);
                }
                this.mCustomIconView = (ImageView) customView.findViewById(16908294);
            } else {
                if (this.mCustomView != null) {
                    removeView(this.mCustomView);
                    this.mCustomView = null;
                }
                this.mCustomTextView = null;
                this.mCustomIconView = null;
            }
            if (this.mCustomView == null) {
                if (this.mIconView == null) {
                    ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(C0000R.layout.design_layout_tab_icon, this, false);
                    addView(imageView, TabLayout.MODE_SCROLLABLE);
                    this.mIconView = imageView;
                }
                if (this.mTextView == null) {
                    TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(C0000R.layout.design_layout_tab_text, this, false);
                    addView(textView);
                    this.mTextView = textView;
                    this.mDefaultMaxLines = TextViewCompat.getMaxLines(this.mTextView);
                }
                this.mTextView.setTextAppearance(getContext(), TabLayout.this.mTabTextAppearance);
                if (TabLayout.this.mTabTextColors != null) {
                    this.mTextView.setTextColor(TabLayout.this.mTabTextColors);
                }
                updateTextAndIcon(this.mTextView, this.mIconView);
            } else if (this.mCustomTextView != null || this.mCustomIconView != null) {
                updateTextAndIcon(this.mCustomTextView, this.mCustomIconView);
            }
        }

        private void updateTextAndIcon(@Nullable TextView textView, @Nullable ImageView imageView) {
            CharSequence text;
            CharSequence contentDescription;
            boolean z;
            Drawable icon = this.mTab != null ? this.mTab.getIcon() : null;
            if (this.mTab != null) {
                text = this.mTab.getText();
            } else {
                text = null;
            }
            if (this.mTab != null) {
                contentDescription = this.mTab.getContentDescription();
            } else {
                contentDescription = null;
            }
            if (imageView != null) {
                if (icon != null) {
                    imageView.setImageDrawable(icon);
                    imageView.setVisibility(TabLayout.MODE_SCROLLABLE);
                    setVisibility(TabLayout.MODE_SCROLLABLE);
                } else {
                    imageView.setVisibility(TabLayout.DEFAULT_GAP_TEXT_ICON);
                    imageView.setImageDrawable(null);
                }
                imageView.setContentDescription(contentDescription);
            }
            if (TextUtils.isEmpty(text)) {
                z = false;
            } else {
                z = TabLayout.MODE_FIXED;
            }
            if (textView != null) {
                if (z) {
                    textView.setText(text);
                    textView.setVisibility(TabLayout.MODE_SCROLLABLE);
                    setVisibility(TabLayout.MODE_SCROLLABLE);
                } else {
                    textView.setVisibility(TabLayout.DEFAULT_GAP_TEXT_ICON);
                    textView.setText(null);
                }
                textView.setContentDescription(contentDescription);
            }
            if (imageView != null) {
                int access$2100;
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) imageView.getLayoutParams();
                if (z && imageView.getVisibility() == 0) {
                    access$2100 = TabLayout.this.dpToPx(TabLayout.DEFAULT_GAP_TEXT_ICON);
                } else {
                    access$2100 = TabLayout.MODE_SCROLLABLE;
                }
                if (access$2100 != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = access$2100;
                    imageView.requestLayout();
                }
            }
            if (z || TextUtils.isEmpty(contentDescription)) {
                setOnLongClickListener(null);
                setLongClickable(false);
                return;
            }
            setOnLongClickListener(this);
        }

        public boolean onLongClick(View view) {
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            Context context = getContext();
            int width = getWidth();
            int height = getHeight();
            int i = context.getResources().getDisplayMetrics().widthPixels;
            Toast makeText = Toast.makeText(context, this.mTab.getContentDescription(), TabLayout.MODE_SCROLLABLE);
            makeText.setGravity(49, (iArr[TabLayout.MODE_SCROLLABLE] + (width / 2)) - (i / 2), height);
            makeText.show();
            return true;
        }

        public Tab getTab() {
            return this.mTab;
        }

        private float approximateLineWidth(Layout layout, int i, float f) {
            return layout.getLineWidth(i) * (f / layout.getPaint().getTextSize());
        }
    }

    public class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {
        private final ViewPager mViewPager;

        public ViewPagerOnTabSelectedListener(ViewPager viewPager) {
            this.mViewPager = viewPager;
        }

        public void onTabSelected(Tab tab) {
            this.mViewPager.setCurrentItem(tab.getPosition());
        }

        public void onTabUnselected(Tab tab) {
        }

        public void onTabReselected(Tab tab) {
        }
    }

    static {
        sTabPool = new SynchronizedPool(FIXED_WRAP_GUTTER_MIN);
    }

    public TabLayout(Context context) {
        this(context, null);
    }

    public TabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, MODE_SCROLLABLE);
    }

    public TabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTabs = new ArrayList();
        this.mTabMaxWidth = MeasureConfiguration.DISABLED;
        this.mTabViewPool = new SimplePool(12);
        ThemeUtils.checkAppCompatTheme(context);
        setHorizontalScrollBarEnabled(false);
        this.mTabStrip = new SlidingTabStrip(context);
        super.addView(this.mTabStrip, MODE_SCROLLABLE, new FrameLayout.LayoutParams(-2, INVALID_WIDTH));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0000R.styleable.TabLayout, i, C0000R.style.Widget_Design_TabLayout);
        this.mTabStrip.setSelectedIndicatorHeight(obtainStyledAttributes.getDimensionPixelSize(C0000R.styleable.TabLayout_tabIndicatorHeight, MODE_SCROLLABLE));
        this.mTabStrip.setSelectedIndicatorColor(obtainStyledAttributes.getColor(C0000R.styleable.TabLayout_tabIndicatorColor, MODE_SCROLLABLE));
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(C0000R.styleable.TabLayout_tabPadding, MODE_SCROLLABLE);
        this.mTabPaddingBottom = dimensionPixelSize;
        this.mTabPaddingEnd = dimensionPixelSize;
        this.mTabPaddingTop = dimensionPixelSize;
        this.mTabPaddingStart = dimensionPixelSize;
        this.mTabPaddingStart = obtainStyledAttributes.getDimensionPixelSize(C0000R.styleable.TabLayout_tabPaddingStart, this.mTabPaddingStart);
        this.mTabPaddingTop = obtainStyledAttributes.getDimensionPixelSize(C0000R.styleable.TabLayout_tabPaddingTop, this.mTabPaddingTop);
        this.mTabPaddingEnd = obtainStyledAttributes.getDimensionPixelSize(C0000R.styleable.TabLayout_tabPaddingEnd, this.mTabPaddingEnd);
        this.mTabPaddingBottom = obtainStyledAttributes.getDimensionPixelSize(C0000R.styleable.TabLayout_tabPaddingBottom, this.mTabPaddingBottom);
        this.mTabTextAppearance = obtainStyledAttributes.getResourceId(C0000R.styleable.TabLayout_tabTextAppearance, C0000R.style.TextAppearance_Design_Tab);
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(this.mTabTextAppearance, C0000R.styleable.TextAppearance);
        try {
            this.mTabTextSize = (float) obtainStyledAttributes2.getDimensionPixelSize(C0000R.styleable.TextAppearance_android_textSize, MODE_SCROLLABLE);
            this.mTabTextColors = obtainStyledAttributes2.getColorStateList(C0000R.styleable.TextAppearance_android_textColor);
            if (obtainStyledAttributes.hasValue(C0000R.styleable.TabLayout_tabTextColor)) {
                this.mTabTextColors = obtainStyledAttributes.getColorStateList(C0000R.styleable.TabLayout_tabTextColor);
            }
            if (obtainStyledAttributes.hasValue(C0000R.styleable.TabLayout_tabSelectedTextColor)) {
                this.mTabTextColors = createColorStateList(this.mTabTextColors.getDefaultColor(), obtainStyledAttributes.getColor(C0000R.styleable.TabLayout_tabSelectedTextColor, MODE_SCROLLABLE));
            }
            this.mRequestedTabMinWidth = obtainStyledAttributes.getDimensionPixelSize(C0000R.styleable.TabLayout_tabMinWidth, INVALID_WIDTH);
            this.mRequestedTabMaxWidth = obtainStyledAttributes.getDimensionPixelSize(C0000R.styleable.TabLayout_tabMaxWidth, INVALID_WIDTH);
            this.mTabBackgroundResId = obtainStyledAttributes.getResourceId(C0000R.styleable.TabLayout_tabBackground, MODE_SCROLLABLE);
            this.mContentInsetStart = obtainStyledAttributes.getDimensionPixelSize(C0000R.styleable.TabLayout_tabContentStart, MODE_SCROLLABLE);
            this.mMode = obtainStyledAttributes.getInt(C0000R.styleable.TabLayout_tabMode, MODE_FIXED);
            this.mTabGravity = obtainStyledAttributes.getInt(C0000R.styleable.TabLayout_tabGravity, MODE_SCROLLABLE);
            obtainStyledAttributes.recycle();
            Resources resources = getResources();
            this.mTabTextMultiLineSize = (float) resources.getDimensionPixelSize(C0000R.dimen.design_tab_text_size_2line);
            this.mScrollableTabMinWidth = resources.getDimensionPixelSize(C0000R.dimen.design_tab_scrollable_min_width);
            applyModeAndGravity();
        } finally {
            obtainStyledAttributes2.recycle();
        }
    }

    public void setSelectedTabIndicatorColor(@ColorInt int i) {
        this.mTabStrip.setSelectedIndicatorColor(i);
    }

    public void setSelectedTabIndicatorHeight(int i) {
        this.mTabStrip.setSelectedIndicatorHeight(i);
    }

    public void setScrollPosition(int i, float f, boolean z) {
        setScrollPosition(i, f, z, true);
    }

    private void setScrollPosition(int i, float f, boolean z, boolean z2) {
        int round = Math.round(((float) i) + f);
        if (round >= 0 && round < this.mTabStrip.getChildCount()) {
            if (z2) {
                this.mTabStrip.setIndicatorPositionFromTabPosition(i, f);
            }
            if (this.mScrollAnimator != null && this.mScrollAnimator.isRunning()) {
                this.mScrollAnimator.cancel();
            }
            scrollTo(calculateScrollXForTab(i, f), MODE_SCROLLABLE);
            if (z) {
                setSelectedTabView(round);
            }
        }
    }

    private float getScrollPosition() {
        return this.mTabStrip.getIndicatorPosition();
    }

    public void addTab(@NonNull Tab tab) {
        addTab(tab, this.mTabs.isEmpty());
    }

    public void addTab(@NonNull Tab tab, int i) {
        addTab(tab, i, this.mTabs.isEmpty());
    }

    public void addTab(@NonNull Tab tab, boolean z) {
        if (tab.mParent != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        addTabView(tab, z);
        configureTab(tab, this.mTabs.size());
        if (z) {
            tab.select();
        }
    }

    public void addTab(@NonNull Tab tab, int i, boolean z) {
        if (tab.mParent != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        addTabView(tab, i, z);
        configureTab(tab, i);
        if (z) {
            tab.select();
        }
    }

    private void addTabFromItemView(@NonNull TabItem tabItem) {
        Tab newTab = newTab();
        if (tabItem.mText != null) {
            newTab.setText(tabItem.mText);
        }
        if (tabItem.mIcon != null) {
            newTab.setIcon(tabItem.mIcon);
        }
        if (tabItem.mCustomLayout != 0) {
            newTab.setCustomView(tabItem.mCustomLayout);
        }
        addTab(newTab);
    }

    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        this.mOnTabSelectedListener = onTabSelectedListener;
    }

    @NonNull
    public Tab newTab() {
        Tab tab = (Tab) sTabPool.acquire();
        if (tab == null) {
            tab = new Tab();
        }
        tab.mParent = this;
        tab.mView = createTabView(tab);
        return tab;
    }

    public int getTabCount() {
        return this.mTabs.size();
    }

    @Nullable
    public Tab getTabAt(int i) {
        return (Tab) this.mTabs.get(i);
    }

    public int getSelectedTabPosition() {
        return this.mSelectedTab != null ? this.mSelectedTab.getPosition() : INVALID_WIDTH;
    }

    public void removeTab(Tab tab) {
        if (tab.mParent != this) {
            throw new IllegalArgumentException("Tab does not belong to this TabLayout.");
        }
        removeTabAt(tab.getPosition());
    }

    public void removeTabAt(int i) {
        int position = this.mSelectedTab != null ? this.mSelectedTab.getPosition() : MODE_SCROLLABLE;
        removeTabViewAt(i);
        Tab tab = (Tab) this.mTabs.remove(i);
        if (tab != null) {
            tab.reset();
            sTabPool.release(tab);
        }
        int size = this.mTabs.size();
        for (int i2 = i; i2 < size; i2 += MODE_FIXED) {
            ((Tab) this.mTabs.get(i2)).setPosition(i2);
        }
        if (position == i) {
            selectTab(this.mTabs.isEmpty() ? null : (Tab) this.mTabs.get(Math.max(MODE_SCROLLABLE, i + INVALID_WIDTH)));
        }
    }

    public void removeAllTabs() {
        for (int childCount = this.mTabStrip.getChildCount() + INVALID_WIDTH; childCount >= 0; childCount += INVALID_WIDTH) {
            removeTabViewAt(childCount);
        }
        Iterator it = this.mTabs.iterator();
        while (it.hasNext()) {
            Tab tab = (Tab) it.next();
            it.remove();
            tab.reset();
            sTabPool.release(tab);
        }
        this.mSelectedTab = null;
    }

    public void setTabMode(int i) {
        if (i != this.mMode) {
            this.mMode = i;
            applyModeAndGravity();
        }
    }

    public int getTabMode() {
        return this.mMode;
    }

    public void setTabGravity(int i) {
        if (this.mTabGravity != i) {
            this.mTabGravity = i;
            applyModeAndGravity();
        }
    }

    public int getTabGravity() {
        return this.mTabGravity;
    }

    public void setTabTextColors(@Nullable ColorStateList colorStateList) {
        if (this.mTabTextColors != colorStateList) {
            this.mTabTextColors = colorStateList;
            updateAllTabs();
        }
    }

    @Nullable
    public ColorStateList getTabTextColors() {
        return this.mTabTextColors;
    }

    public void setTabTextColors(int i, int i2) {
        setTabTextColors(createColorStateList(i, i2));
    }

    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        if (!(this.mViewPager == null || this.mPageChangeListener == null)) {
            this.mViewPager.removeOnPageChangeListener(this.mPageChangeListener);
        }
        if (viewPager != null) {
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter == null) {
                throw new IllegalArgumentException("ViewPager does not have a PagerAdapter set");
            }
            this.mViewPager = viewPager;
            if (this.mPageChangeListener == null) {
                this.mPageChangeListener = new TabLayoutOnPageChangeListener(this);
            }
            this.mPageChangeListener.reset();
            viewPager.addOnPageChangeListener(this.mPageChangeListener);
            setOnTabSelectedListener(new ViewPagerOnTabSelectedListener(viewPager));
            setPagerAdapter(adapter, true);
            return;
        }
        this.mViewPager = null;
        setOnTabSelectedListener(null);
        setPagerAdapter(null, true);
    }

    @Deprecated
    public void setTabsFromPagerAdapter(@Nullable PagerAdapter pagerAdapter) {
        setPagerAdapter(pagerAdapter, false);
    }

    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    private int getTabScrollRange() {
        return Math.max(MODE_SCROLLABLE, ((this.mTabStrip.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    private void setPagerAdapter(@Nullable PagerAdapter pagerAdapter, boolean z) {
        if (!(this.mPagerAdapter == null || this.mPagerAdapterObserver == null)) {
            this.mPagerAdapter.unregisterDataSetObserver(this.mPagerAdapterObserver);
        }
        this.mPagerAdapter = pagerAdapter;
        if (z && pagerAdapter != null) {
            if (this.mPagerAdapterObserver == null) {
                this.mPagerAdapterObserver = new PagerAdapterObserver();
            }
            pagerAdapter.registerDataSetObserver(this.mPagerAdapterObserver);
        }
        populateFromPagerAdapter();
    }

    private void populateFromPagerAdapter() {
        removeAllTabs();
        if (this.mPagerAdapter != null) {
            int i;
            int count = this.mPagerAdapter.getCount();
            for (i = MODE_SCROLLABLE; i < count; i += MODE_FIXED) {
                addTab(newTab().setText(this.mPagerAdapter.getPageTitle(i)), false);
            }
            if (this.mViewPager != null && count > 0) {
                i = this.mViewPager.getCurrentItem();
                if (i != getSelectedTabPosition() && i < getTabCount()) {
                    selectTab(getTabAt(i));
                    return;
                }
                return;
            }
            return;
        }
        removeAllTabs();
    }

    private void updateAllTabs() {
        int size = this.mTabs.size();
        for (int i = MODE_SCROLLABLE; i < size; i += MODE_FIXED) {
            ((Tab) this.mTabs.get(i)).updateView();
        }
    }

    private TabView createTabView(@NonNull Tab tab) {
        TabView tabView = this.mTabViewPool != null ? (TabView) this.mTabViewPool.acquire() : null;
        if (tabView == null) {
            tabView = new TabView(getContext());
        }
        tabView.setTab(tab);
        tabView.setFocusable(true);
        tabView.setMinimumWidth(getTabMinWidth());
        return tabView;
    }

    private void configureTab(Tab tab, int i) {
        tab.setPosition(i);
        this.mTabs.add(i, tab);
        int size = this.mTabs.size();
        for (int i2 = i + MODE_FIXED; i2 < size; i2 += MODE_FIXED) {
            ((Tab) this.mTabs.get(i2)).setPosition(i2);
        }
    }

    private void addTabView(Tab tab, boolean z) {
        View access$200 = tab.mView;
        this.mTabStrip.addView(access$200, createLayoutParamsForTabs());
        if (z) {
            access$200.setSelected(true);
        }
    }

    private void addTabView(Tab tab, int i, boolean z) {
        View access$200 = tab.mView;
        this.mTabStrip.addView(access$200, i, createLayoutParamsForTabs());
        if (z) {
            access$200.setSelected(true);
        }
    }

    public void addView(View view) {
        addViewInternal(view);
    }

    public void addView(View view, int i) {
        addViewInternal(view);
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        addViewInternal(view);
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        addViewInternal(view);
    }

    private void addViewInternal(View view) {
        if (view instanceof TabItem) {
            addTabFromItemView((TabItem) view);
            return;
        }
        throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
    }

    private LayoutParams createLayoutParamsForTabs() {
        LayoutParams layoutParams = new LayoutParams(-2, INVALID_WIDTH);
        updateTabViewLayoutParams(layoutParams);
        return layoutParams;
    }

    private void updateTabViewLayoutParams(LayoutParams layoutParams) {
        if (this.mMode == MODE_FIXED && this.mTabGravity == 0) {
            layoutParams.width = MODE_SCROLLABLE;
            layoutParams.weight = FullImageView.ASPECT_RATIO_SQUARE;
            return;
        }
        layoutParams.width = -2;
        layoutParams.weight = 0.0f;
    }

    private int dpToPx(int i) {
        return Math.round(getResources().getDisplayMetrics().density * ((float) i));
    }

    protected void onMeasure(int i, int i2) {
        int i3 = MODE_FIXED;
        int dpToPx = (dpToPx(getDefaultHeight()) + getPaddingTop()) + getPaddingBottom();
        switch (MeasureSpec.getMode(i2)) {
            case RtlSpacingHelper.UNDEFINED /*-2147483648*/:
                i2 = MeasureSpec.makeMeasureSpec(Math.min(dpToPx, MeasureSpec.getSize(i2)), 1073741824);
                break;
            case MODE_SCROLLABLE /*0*/:
                i2 = MeasureSpec.makeMeasureSpec(dpToPx, 1073741824);
                break;
        }
        dpToPx = MeasureSpec.getSize(i);
        if (MeasureSpec.getMode(i) != 0) {
            if (this.mRequestedTabMaxWidth > 0) {
                dpToPx = this.mRequestedTabMaxWidth;
            } else {
                dpToPx -= dpToPx(TAB_MIN_WIDTH_MARGIN);
            }
            this.mTabMaxWidth = dpToPx;
        }
        super.onMeasure(i, i2);
        if (getChildCount() == MODE_FIXED) {
            View childAt = getChildAt(MODE_SCROLLABLE);
            switch (this.mMode) {
                case MODE_SCROLLABLE /*0*/:
                    if (childAt.getMeasuredWidth() >= getMeasuredWidth()) {
                        dpToPx = MODE_SCROLLABLE;
                        break;
                    } else {
                        dpToPx = MODE_FIXED;
                        break;
                    }
                case MODE_FIXED /*1*/:
                    if (childAt.getMeasuredWidth() == getMeasuredWidth()) {
                        i3 = MODE_SCROLLABLE;
                    }
                    dpToPx = i3;
                    break;
                default:
                    dpToPx = MODE_SCROLLABLE;
                    break;
            }
            if (dpToPx != 0) {
                childAt.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom(), childAt.getLayoutParams().height));
            }
        }
    }

    private void removeTabViewAt(int i) {
        TabView tabView = (TabView) this.mTabStrip.getChildAt(i);
        this.mTabStrip.removeViewAt(i);
        if (tabView != null) {
            tabView.reset();
            this.mTabViewPool.release(tabView);
        }
        requestLayout();
    }

    private void animateToTab(int i) {
        if (i != INVALID_WIDTH) {
            if (getWindowToken() == null || !ViewCompat.isLaidOut(this) || this.mTabStrip.childrenNeedLayout()) {
                setScrollPosition(i, 0.0f, true);
                return;
            }
            int scrollX = getScrollX();
            int calculateScrollXForTab = calculateScrollXForTab(i, 0.0f);
            if (scrollX != calculateScrollXForTab) {
                if (this.mScrollAnimator == null) {
                    this.mScrollAnimator = ViewUtils.createAnimator();
                    this.mScrollAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                    this.mScrollAnimator.setDuration(ANIMATION_DURATION);
                    this.mScrollAnimator.setUpdateListener(new C00371());
                }
                this.mScrollAnimator.setIntValues(scrollX, calculateScrollXForTab);
                this.mScrollAnimator.start();
            }
            this.mTabStrip.animateIndicatorToPosition(i, ANIMATION_DURATION);
        }
    }

    private void setSelectedTabView(int i) {
        int childCount = this.mTabStrip.getChildCount();
        if (i < childCount && !this.mTabStrip.getChildAt(i).isSelected()) {
            for (int i2 = MODE_SCROLLABLE; i2 < childCount; i2 += MODE_FIXED) {
                boolean z;
                View childAt = this.mTabStrip.getChildAt(i2);
                if (i2 == i) {
                    z = true;
                } else {
                    z = false;
                }
                childAt.setSelected(z);
            }
        }
    }

    void selectTab(Tab tab) {
        selectTab(tab, true);
    }

    void selectTab(Tab tab, boolean z) {
        if (this.mSelectedTab != tab) {
            if (z) {
                int position = tab != null ? tab.getPosition() : INVALID_WIDTH;
                if (position != INVALID_WIDTH) {
                    setSelectedTabView(position);
                }
                if ((this.mSelectedTab == null || this.mSelectedTab.getPosition() == INVALID_WIDTH) && position != INVALID_WIDTH) {
                    setScrollPosition(position, 0.0f, true);
                } else {
                    animateToTab(position);
                }
            }
            if (!(this.mSelectedTab == null || this.mOnTabSelectedListener == null)) {
                this.mOnTabSelectedListener.onTabUnselected(this.mSelectedTab);
            }
            this.mSelectedTab = tab;
            if (this.mSelectedTab != null && this.mOnTabSelectedListener != null) {
                this.mOnTabSelectedListener.onTabSelected(this.mSelectedTab);
            }
        } else if (this.mSelectedTab != null) {
            if (this.mOnTabSelectedListener != null) {
                this.mOnTabSelectedListener.onTabReselected(this.mSelectedTab);
            }
            animateToTab(tab.getPosition());
        }
    }

    private int calculateScrollXForTab(int i, float f) {
        int i2 = MODE_SCROLLABLE;
        if (this.mMode != 0) {
            return MODE_SCROLLABLE;
        }
        int width;
        View childAt = this.mTabStrip.getChildAt(i);
        View childAt2 = i + MODE_FIXED < this.mTabStrip.getChildCount() ? this.mTabStrip.getChildAt(i + MODE_FIXED) : null;
        if (childAt != null) {
            width = childAt.getWidth();
        } else {
            width = MODE_SCROLLABLE;
        }
        if (childAt2 != null) {
            i2 = childAt2.getWidth();
        }
        return ((((int) ((((float) (i2 + width)) * f) * 0.5f)) + childAt.getLeft()) + (childAt.getWidth() / 2)) - (getWidth() / 2);
    }

    private void applyModeAndGravity() {
        int max;
        if (this.mMode == 0) {
            max = Math.max(MODE_SCROLLABLE, this.mContentInsetStart - this.mTabPaddingStart);
        } else {
            max = MODE_SCROLLABLE;
        }
        ViewCompat.setPaddingRelative(this.mTabStrip, max, MODE_SCROLLABLE, MODE_SCROLLABLE, MODE_SCROLLABLE);
        switch (this.mMode) {
            case MODE_SCROLLABLE /*0*/:
                this.mTabStrip.setGravity(GravityCompat.START);
                break;
            case MODE_FIXED /*1*/:
                this.mTabStrip.setGravity(MODE_FIXED);
                break;
        }
        updateTabViews(true);
    }

    private void updateTabViews(boolean z) {
        for (int i = MODE_SCROLLABLE; i < this.mTabStrip.getChildCount(); i += MODE_FIXED) {
            View childAt = this.mTabStrip.getChildAt(i);
            childAt.setMinimumWidth(getTabMinWidth());
            updateTabViewLayoutParams((LayoutParams) childAt.getLayoutParams());
            if (z) {
                childAt.requestLayout();
            }
        }
    }

    private static ColorStateList createColorStateList(int i, int i2) {
        r0 = new int[2][];
        int[] iArr = new int[]{SELECTED_STATE_SET, i2};
        r0[MODE_FIXED] = EMPTY_STATE_SET;
        iArr[MODE_FIXED] = i;
        return new ColorStateList(r0, iArr);
    }

    private int getDefaultHeight() {
        Object obj;
        int size = this.mTabs.size();
        for (int i = MODE_SCROLLABLE; i < size; i += MODE_FIXED) {
            Tab tab = (Tab) this.mTabs.get(i);
            if (tab != null && tab.getIcon() != null && !TextUtils.isEmpty(tab.getText())) {
                obj = MODE_FIXED;
                break;
            }
        }
        obj = MODE_SCROLLABLE;
        if (obj != null) {
            return DEFAULT_HEIGHT_WITH_TEXT_ICON;
        }
        return DEFAULT_HEIGHT;
    }

    private int getTabMinWidth() {
        if (this.mRequestedTabMinWidth != INVALID_WIDTH) {
            return this.mRequestedTabMinWidth;
        }
        return this.mMode == 0 ? this.mScrollableTabMinWidth : MODE_SCROLLABLE;
    }

    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    private int getTabMaxWidth() {
        return this.mTabMaxWidth;
    }
}
