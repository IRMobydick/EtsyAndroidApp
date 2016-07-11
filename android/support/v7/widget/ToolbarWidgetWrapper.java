package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.appcompat.C0234R;
import android.support.v7.view.menu.ActionMenuItem;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window.Callback;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.etsy.android.uikit.view.FullImageView;
import com.google.android.gms.gcm.Task;

public class ToolbarWidgetWrapper implements DecorToolbar {
    private static final int AFFECTS_LOGO_MASK = 3;
    private static final long DEFAULT_FADE_DURATION_MS = 200;
    private static final String TAG = "ToolbarWidgetWrapper";
    private ActionMenuPresenter mActionMenuPresenter;
    private View mCustomView;
    private int mDefaultNavigationContentDescription;
    private Drawable mDefaultNavigationIcon;
    private int mDisplayOpts;
    private final AppCompatDrawableManager mDrawableManager;
    private CharSequence mHomeDescription;
    private Drawable mIcon;
    private Drawable mLogo;
    private boolean mMenuPrepared;
    private Drawable mNavIcon;
    private int mNavigationMode;
    private Spinner mSpinner;
    private CharSequence mSubtitle;
    private View mTabView;
    private CharSequence mTitle;
    private boolean mTitleSet;
    private Toolbar mToolbar;
    private Callback mWindowCallback;

    /* renamed from: android.support.v7.widget.ToolbarWidgetWrapper.1 */
    class C03231 implements OnClickListener {
        final ActionMenuItem mNavItem;

        C03231() {
            this.mNavItem = new ActionMenuItem(ToolbarWidgetWrapper.this.mToolbar.getContext(), 0, 16908332, 0, 0, ToolbarWidgetWrapper.this.mTitle);
        }

        public void onClick(View view) {
            if (ToolbarWidgetWrapper.this.mWindowCallback != null && ToolbarWidgetWrapper.this.mMenuPrepared) {
                ToolbarWidgetWrapper.this.mWindowCallback.onMenuItemSelected(0, this.mNavItem);
            }
        }
    }

    /* renamed from: android.support.v7.widget.ToolbarWidgetWrapper.2 */
    class C03242 extends ViewPropertyAnimatorListenerAdapter {
        private boolean mCanceled;
        final /* synthetic */ int val$visibility;

        C03242(int i) {
            this.val$visibility = i;
            this.mCanceled = false;
        }

        public void onAnimationStart(View view) {
            ToolbarWidgetWrapper.this.mToolbar.setVisibility(0);
        }

        public void onAnimationEnd(View view) {
            if (!this.mCanceled) {
                ToolbarWidgetWrapper.this.mToolbar.setVisibility(this.val$visibility);
            }
        }

        public void onAnimationCancel(View view) {
            this.mCanceled = true;
        }
    }

    public ToolbarWidgetWrapper(Toolbar toolbar, boolean z) {
        this(toolbar, z, C0234R.string.abc_action_bar_up_description, C0234R.drawable.abc_ic_ab_back_mtrl_am_alpha);
    }

    public ToolbarWidgetWrapper(Toolbar toolbar, boolean z, int i, int i2) {
        this.mNavigationMode = 0;
        this.mDefaultNavigationContentDescription = 0;
        this.mToolbar = toolbar;
        this.mTitle = toolbar.getTitle();
        this.mSubtitle = toolbar.getSubtitle();
        this.mTitleSet = this.mTitle != null;
        this.mNavIcon = toolbar.getNavigationIcon();
        if (z) {
            TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(toolbar.getContext(), null, C0234R.styleable.ActionBar, C0234R.attr.actionBarStyle, 0);
            CharSequence text = obtainStyledAttributes.getText(C0234R.styleable.ActionBar_title);
            if (!TextUtils.isEmpty(text)) {
                setTitle(text);
            }
            text = obtainStyledAttributes.getText(C0234R.styleable.ActionBar_subtitle);
            if (!TextUtils.isEmpty(text)) {
                setSubtitle(text);
            }
            Drawable drawable = obtainStyledAttributes.getDrawable(C0234R.styleable.ActionBar_logo);
            if (drawable != null) {
                setLogo(drawable);
            }
            drawable = obtainStyledAttributes.getDrawable(C0234R.styleable.ActionBar_icon);
            if (this.mNavIcon == null && drawable != null) {
                setIcon(drawable);
            }
            drawable = obtainStyledAttributes.getDrawable(C0234R.styleable.ActionBar_homeAsUpIndicator);
            if (drawable != null) {
                setNavigationIcon(drawable);
            }
            setDisplayOptions(obtainStyledAttributes.getInt(C0234R.styleable.ActionBar_displayOptions, 0));
            int resourceId = obtainStyledAttributes.getResourceId(C0234R.styleable.ActionBar_customNavigationLayout, 0);
            if (resourceId != 0) {
                setCustomView(LayoutInflater.from(this.mToolbar.getContext()).inflate(resourceId, this.mToolbar, false));
                setDisplayOptions(this.mDisplayOpts | 16);
            }
            resourceId = obtainStyledAttributes.getLayoutDimension(C0234R.styleable.ActionBar_height, 0);
            if (resourceId > 0) {
                LayoutParams layoutParams = this.mToolbar.getLayoutParams();
                layoutParams.height = resourceId;
                this.mToolbar.setLayoutParams(layoutParams);
            }
            resourceId = obtainStyledAttributes.getDimensionPixelOffset(C0234R.styleable.ActionBar_contentInsetStart, -1);
            int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(C0234R.styleable.ActionBar_contentInsetEnd, -1);
            if (resourceId >= 0 || dimensionPixelOffset >= 0) {
                this.mToolbar.setContentInsetsRelative(Math.max(resourceId, 0), Math.max(dimensionPixelOffset, 0));
            }
            resourceId = obtainStyledAttributes.getResourceId(C0234R.styleable.ActionBar_titleTextStyle, 0);
            if (resourceId != 0) {
                this.mToolbar.setTitleTextAppearance(this.mToolbar.getContext(), resourceId);
            }
            resourceId = obtainStyledAttributes.getResourceId(C0234R.styleable.ActionBar_subtitleTextStyle, 0);
            if (resourceId != 0) {
                this.mToolbar.setSubtitleTextAppearance(this.mToolbar.getContext(), resourceId);
            }
            int resourceId2 = obtainStyledAttributes.getResourceId(C0234R.styleable.ActionBar_popupTheme, 0);
            if (resourceId2 != 0) {
                this.mToolbar.setPopupTheme(resourceId2);
            }
            obtainStyledAttributes.recycle();
        } else {
            this.mDisplayOpts = detectDisplayOptions();
        }
        this.mDrawableManager = AppCompatDrawableManager.get();
        setDefaultNavigationContentDescription(i);
        this.mHomeDescription = this.mToolbar.getNavigationContentDescription();
        setDefaultNavigationIcon(this.mDrawableManager.getDrawable(getContext(), i2));
        this.mToolbar.setNavigationOnClickListener(new C03231());
    }

    public void setDefaultNavigationContentDescription(int i) {
        if (i != this.mDefaultNavigationContentDescription) {
            this.mDefaultNavigationContentDescription = i;
            if (TextUtils.isEmpty(this.mToolbar.getNavigationContentDescription())) {
                setNavigationContentDescription(this.mDefaultNavigationContentDescription);
            }
        }
    }

    public void setDefaultNavigationIcon(Drawable drawable) {
        if (this.mDefaultNavigationIcon != drawable) {
            this.mDefaultNavigationIcon = drawable;
            updateNavigationIcon();
        }
    }

    private int detectDisplayOptions() {
        if (this.mToolbar.getNavigationIcon() != null) {
            return 15;
        }
        return 11;
    }

    public ViewGroup getViewGroup() {
        return this.mToolbar;
    }

    public Context getContext() {
        return this.mToolbar.getContext();
    }

    public boolean hasExpandedActionView() {
        return this.mToolbar.hasExpandedActionView();
    }

    public void collapseActionView() {
        this.mToolbar.collapseActionView();
    }

    public void setWindowCallback(Callback callback) {
        this.mWindowCallback = callback;
    }

    public void setWindowTitle(CharSequence charSequence) {
        if (!this.mTitleSet) {
            setTitleInt(charSequence);
        }
    }

    public CharSequence getTitle() {
        return this.mToolbar.getTitle();
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitleSet = true;
        setTitleInt(charSequence);
    }

    private void setTitleInt(CharSequence charSequence) {
        this.mTitle = charSequence;
        if ((this.mDisplayOpts & 8) != 0) {
            this.mToolbar.setTitle(charSequence);
        }
    }

    public CharSequence getSubtitle() {
        return this.mToolbar.getSubtitle();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.mSubtitle = charSequence;
        if ((this.mDisplayOpts & 8) != 0) {
            this.mToolbar.setSubtitle(charSequence);
        }
    }

    public void initProgress() {
        Log.i(TAG, "Progress display unsupported");
    }

    public void initIndeterminateProgress() {
        Log.i(TAG, "Progress display unsupported");
    }

    public boolean hasIcon() {
        return this.mIcon != null;
    }

    public boolean hasLogo() {
        return this.mLogo != null;
    }

    public void setIcon(int i) {
        setIcon(i != 0 ? this.mDrawableManager.getDrawable(getContext(), i) : null);
    }

    public void setIcon(Drawable drawable) {
        this.mIcon = drawable;
        updateToolbarLogo();
    }

    public void setLogo(int i) {
        setLogo(i != 0 ? this.mDrawableManager.getDrawable(getContext(), i) : null);
    }

    public void setLogo(Drawable drawable) {
        this.mLogo = drawable;
        updateToolbarLogo();
    }

    private void updateToolbarLogo() {
        Drawable drawable = null;
        if ((this.mDisplayOpts & 2) != 0) {
            drawable = (this.mDisplayOpts & 1) != 0 ? this.mLogo != null ? this.mLogo : this.mIcon : this.mIcon;
        }
        this.mToolbar.setLogo(drawable);
    }

    public boolean canShowOverflowMenu() {
        return this.mToolbar.canShowOverflowMenu();
    }

    public boolean isOverflowMenuShowing() {
        return this.mToolbar.isOverflowMenuShowing();
    }

    public boolean isOverflowMenuShowPending() {
        return this.mToolbar.isOverflowMenuShowPending();
    }

    public boolean showOverflowMenu() {
        return this.mToolbar.showOverflowMenu();
    }

    public boolean hideOverflowMenu() {
        return this.mToolbar.hideOverflowMenu();
    }

    public void setMenuPrepared() {
        this.mMenuPrepared = true;
    }

    public void setMenu(Menu menu, MenuPresenter.Callback callback) {
        if (this.mActionMenuPresenter == null) {
            this.mActionMenuPresenter = new ActionMenuPresenter(this.mToolbar.getContext());
            this.mActionMenuPresenter.setId(C0234R.id.action_menu_presenter);
        }
        this.mActionMenuPresenter.setCallback(callback);
        this.mToolbar.setMenu((MenuBuilder) menu, this.mActionMenuPresenter);
    }

    public void dismissPopupMenus() {
        this.mToolbar.dismissPopupMenus();
    }

    public int getDisplayOptions() {
        return this.mDisplayOpts;
    }

    public void setDisplayOptions(int i) {
        int i2 = this.mDisplayOpts ^ i;
        this.mDisplayOpts = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    updateNavigationIcon();
                    updateHomeAccessibility();
                } else {
                    this.mToolbar.setNavigationIcon(null);
                }
            }
            if ((i2 & AFFECTS_LOGO_MASK) != 0) {
                updateToolbarLogo();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.mToolbar.setTitle(this.mTitle);
                    this.mToolbar.setSubtitle(this.mSubtitle);
                } else {
                    this.mToolbar.setTitle(null);
                    this.mToolbar.setSubtitle(null);
                }
            }
            if ((i2 & 16) != 0 && this.mCustomView != null) {
                if ((i & 16) != 0) {
                    this.mToolbar.addView(this.mCustomView);
                } else {
                    this.mToolbar.removeView(this.mCustomView);
                }
            }
        }
    }

    public void setEmbeddedTabView(ScrollingTabContainerView scrollingTabContainerView) {
        if (this.mTabView != null && this.mTabView.getParent() == this.mToolbar) {
            this.mToolbar.removeView(this.mTabView);
        }
        this.mTabView = scrollingTabContainerView;
        if (scrollingTabContainerView != null && this.mNavigationMode == 2) {
            this.mToolbar.addView(this.mTabView, 0);
            Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) this.mTabView.getLayoutParams();
            layoutParams.width = -2;
            layoutParams.height = -2;
            layoutParams.gravity = 8388691;
            scrollingTabContainerView.setAllowCollapse(true);
        }
    }

    public boolean hasEmbeddedTabs() {
        return this.mTabView != null;
    }

    public boolean isTitleTruncated() {
        return this.mToolbar.isTitleTruncated();
    }

    public void setCollapsible(boolean z) {
        this.mToolbar.setCollapsible(z);
    }

    public void setHomeButtonEnabled(boolean z) {
    }

    public int getNavigationMode() {
        return this.mNavigationMode;
    }

    public void setNavigationMode(int i) {
        int i2 = this.mNavigationMode;
        if (i != i2) {
            switch (i2) {
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    if (this.mSpinner != null && this.mSpinner.getParent() == this.mToolbar) {
                        this.mToolbar.removeView(this.mSpinner);
                        break;
                    }
                case Task.NETWORK_STATE_ANY /*2*/:
                    if (this.mTabView != null && this.mTabView.getParent() == this.mToolbar) {
                        this.mToolbar.removeView(this.mTabView);
                        break;
                    }
            }
            this.mNavigationMode = i;
            switch (i) {
                case Task.NETWORK_STATE_CONNECTED /*0*/:
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    ensureSpinner();
                    this.mToolbar.addView(this.mSpinner, 0);
                case Task.NETWORK_STATE_ANY /*2*/:
                    if (this.mTabView != null) {
                        this.mToolbar.addView(this.mTabView, 0);
                        Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) this.mTabView.getLayoutParams();
                        layoutParams.width = -2;
                        layoutParams.height = -2;
                        layoutParams.gravity = 8388691;
                    }
                default:
                    throw new IllegalArgumentException("Invalid navigation mode " + i);
            }
        }
    }

    private void ensureSpinner() {
        if (this.mSpinner == null) {
            this.mSpinner = new AppCompatSpinner(getContext(), null, C0234R.attr.actionDropDownStyle);
            this.mSpinner.setLayoutParams(new Toolbar.LayoutParams(-2, -2, 8388627));
        }
    }

    public void setDropdownParams(SpinnerAdapter spinnerAdapter, OnItemSelectedListener onItemSelectedListener) {
        ensureSpinner();
        this.mSpinner.setAdapter(spinnerAdapter);
        this.mSpinner.setOnItemSelectedListener(onItemSelectedListener);
    }

    public void setDropdownSelectedPosition(int i) {
        if (this.mSpinner == null) {
            throw new IllegalStateException("Can't set dropdown selected position without an adapter");
        }
        this.mSpinner.setSelection(i);
    }

    public int getDropdownSelectedPosition() {
        return this.mSpinner != null ? this.mSpinner.getSelectedItemPosition() : 0;
    }

    public int getDropdownItemCount() {
        return this.mSpinner != null ? this.mSpinner.getCount() : 0;
    }

    public void setCustomView(View view) {
        if (!(this.mCustomView == null || (this.mDisplayOpts & 16) == 0)) {
            this.mToolbar.removeView(this.mCustomView);
        }
        this.mCustomView = view;
        if (view != null && (this.mDisplayOpts & 16) != 0) {
            this.mToolbar.addView(this.mCustomView);
        }
    }

    public View getCustomView() {
        return this.mCustomView;
    }

    public void animateToVisibility(int i) {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = setupAnimatorToVisibility(i, DEFAULT_FADE_DURATION_MS);
        if (viewPropertyAnimatorCompat != null) {
            viewPropertyAnimatorCompat.start();
        }
    }

    public ViewPropertyAnimatorCompat setupAnimatorToVisibility(int i, long j) {
        return ViewCompat.animate(this.mToolbar).alpha(i == 0 ? FullImageView.ASPECT_RATIO_SQUARE : 0.0f).setDuration(j).setListener(new C03242(i));
    }

    public void setNavigationIcon(Drawable drawable) {
        this.mNavIcon = drawable;
        updateNavigationIcon();
    }

    public void setNavigationIcon(int i) {
        setNavigationIcon(i != 0 ? AppCompatDrawableManager.get().getDrawable(getContext(), i) : null);
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        this.mHomeDescription = charSequence;
        updateHomeAccessibility();
    }

    public void setNavigationContentDescription(int i) {
        setNavigationContentDescription(i == 0 ? null : getContext().getString(i));
    }

    private void updateHomeAccessibility() {
        if ((this.mDisplayOpts & 4) == 0) {
            return;
        }
        if (TextUtils.isEmpty(this.mHomeDescription)) {
            this.mToolbar.setNavigationContentDescription(this.mDefaultNavigationContentDescription);
        } else {
            this.mToolbar.setNavigationContentDescription(this.mHomeDescription);
        }
    }

    private void updateNavigationIcon() {
        if ((this.mDisplayOpts & 4) != 0) {
            this.mToolbar.setNavigationIcon(this.mNavIcon != null ? this.mNavIcon : this.mDefaultNavigationIcon);
        }
    }

    public void saveHierarchyState(SparseArray<Parcelable> sparseArray) {
        this.mToolbar.saveHierarchyState(sparseArray);
    }

    public void restoreHierarchyState(SparseArray<Parcelable> sparseArray) {
        this.mToolbar.restoreHierarchyState(sparseArray);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.mToolbar.setBackgroundDrawable(drawable);
    }

    public int getHeight() {
        return this.mToolbar.getHeight();
    }

    public void setVisibility(int i) {
        this.mToolbar.setVisibility(i);
    }

    public int getVisibility() {
        return this.mToolbar.getVisibility();
    }

    public void setMenuCallbacks(MenuPresenter.Callback callback, MenuBuilder.Callback callback2) {
        this.mToolbar.setMenuCallbacks(callback, callback2);
    }

    public Menu getMenu() {
        return this.mToolbar.getMenu();
    }
}
