package com.etsy.android.uikit.navigationview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import com.etsy.android.lib.R;
import com.google.android.gms.gcm.Task;

public class EtsyNavigationView extends ScrimInsetsFrameLayout {
    private static final int[] CHECKED_STATE_SET;
    private static final int[] DISABLED_STATE_SET;
    private static final int PRESENTER_NAVIGATION_VIEW_ID = 1;
    private EtsyNavigationView mListener;
    private int mMaxWidth;
    private final MenuBuilder mMenu;
    private MenuInflater mMenuInflater;
    private final EtsyNavigationMenuPresenter mPresenter;

    /* renamed from: com.etsy.android.uikit.navigationview.EtsyNavigationView.1 */
    class C09381 implements Callback {
        final /* synthetic */ EtsyNavigationView f3997a;

        C09381(EtsyNavigationView etsyNavigationView) {
            this.f3997a = etsyNavigationView;
        }

        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            return this.f3997a.mListener != null && this.f3997a.mListener.m3467a(menuItem);
        }

        public void onMenuModeChange(MenuBuilder menuBuilder) {
        }
    }

    public class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        public Bundle menuState;

        /* renamed from: com.etsy.android.uikit.navigationview.EtsyNavigationView.SavedState.1 */
        final class C09391 implements Creator {
            C09391() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m5369a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m5370a(i);
            }

            public SavedState m5369a(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] m5370a(int i) {
                return new SavedState[i];
            }
        }

        static {
            CREATOR = new C09391();
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.menuState = parcel.readBundle();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(@NonNull Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.menuState);
        }
    }

    static {
        int[] iArr = new int[PRESENTER_NAVIGATION_VIEW_ID];
        iArr[0] = 16842912;
        CHECKED_STATE_SET = iArr;
        iArr = new int[PRESENTER_NAVIGATION_VIEW_ID];
        iArr[0] = -16842910;
        DISABLED_STATE_SET = iArr;
    }

    public EtsyNavigationView(Context context) {
        this(context, null);
    }

    public EtsyNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EtsyNavigationView(Context context, AttributeSet attributeSet, int i) {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        super(context, attributeSet, i);
        this.mMenu = new MenuBuilder(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.NavigationView, i, R.Widget_Design_NavigationView);
        setBackgroundDrawable(obtainStyledAttributes.getDrawable(R.NavigationView_android_background));
        if (obtainStyledAttributes.hasValue(R.NavigationView_elevation)) {
            ViewCompat.setElevation(this, (float) obtainStyledAttributes.getDimensionPixelSize(R.NavigationView_elevation, 0));
        }
        ViewCompat.setFitsSystemWindows(this, obtainStyledAttributes.getBoolean(R.NavigationView_android_fitsSystemWindows, false));
        this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(R.NavigationView_android_maxWidth, 0);
        if (obtainStyledAttributes.hasValue(R.NavigationView_itemIconTint)) {
            colorStateList = obtainStyledAttributes.getColorStateList(R.NavigationView_itemIconTint);
        } else {
            colorStateList = createDefaultColorStateList(16842808);
        }
        if (obtainStyledAttributes.hasValue(R.NavigationView_itemTextColor)) {
            colorStateList2 = obtainStyledAttributes.getColorStateList(R.NavigationView_itemTextColor);
        } else {
            colorStateList2 = createDefaultColorStateList(16842806);
        }
        Drawable drawable = obtainStyledAttributes.getDrawable(R.NavigationView_itemBackground);
        if (obtainStyledAttributes.hasValue(R.NavigationView_menu)) {
            inflateMenu(obtainStyledAttributes.getResourceId(R.NavigationView_menu, 0));
        }
        this.mMenu.setCallback(new C09381(this));
        this.mPresenter = new EtsyNavigationMenuPresenter();
        this.mPresenter.setId(PRESENTER_NAVIGATION_VIEW_ID);
        this.mPresenter.initForMenu(context, this.mMenu);
        this.mPresenter.setItemIconTintList(colorStateList);
        this.mPresenter.setItemTextColor(colorStateList2);
        this.mPresenter.setItemBackground(drawable);
        this.mMenu.addMenuPresenter(this.mPresenter);
        addView((View) this.mPresenter.getMenuView(this));
        if (obtainStyledAttributes.hasValue(R.NavigationView_headerLayout)) {
            inflateHeaderView(obtainStyledAttributes.getResourceId(R.NavigationView_headerLayout, 0));
        }
        obtainStyledAttributes.recycle();
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.menuState = new Bundle();
        this.mMenu.savePresenterStates(savedState.menuState);
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mMenu.restorePresenterStates(savedState.menuState);
    }

    public void setNavigationItemSelectedListener(EtsyNavigationView etsyNavigationView) {
        this.mListener = etsyNavigationView;
    }

    protected void onMeasure(int i, int i2) {
        switch (MeasureSpec.getMode(i)) {
            case RtlSpacingHelper.UNDEFINED /*-2147483648*/:
                i = MeasureSpec.makeMeasureSpec(Math.min(MeasureSpec.getSize(i), this.mMaxWidth), 1073741824);
                break;
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                i = MeasureSpec.makeMeasureSpec(this.mMaxWidth, 1073741824);
                break;
        }
        super.onMeasure(i, i2);
    }

    public void inflateMenu(int i) {
        getMenuInflater().inflate(i, this.mMenu);
    }

    public Menu getMenu() {
        return this.mMenu;
    }

    public View inflateHeaderView(@LayoutRes int i) {
        return this.mPresenter.inflateHeaderView(i);
    }

    public void addHeaderView(@NonNull View view) {
        this.mPresenter.addHeaderView(view);
    }

    public void removeHeaderView(@NonNull View view) {
        this.mPresenter.removeHeaderView(view);
    }

    @Nullable
    public ColorStateList getItemIconTintList() {
        return this.mPresenter.getItemTintList();
    }

    public void setItemIconTintList(@Nullable ColorStateList colorStateList) {
        this.mPresenter.setItemIconTintList(colorStateList);
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.mPresenter.getItemTextColor();
    }

    public void setItemTextColor(@Nullable ColorStateList colorStateList) {
        this.mPresenter.setItemTextColor(colorStateList);
    }

    public Drawable getItemBackground() {
        return this.mPresenter.getItemBackground();
    }

    public void setItemBackgroundResource(@DrawableRes int i) {
        setItemBackground(ContextCompat.getDrawable(getContext(), i));
    }

    public void setItemBackground(Drawable drawable) {
        this.mPresenter.setItemBackground(drawable);
    }

    private MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            this.mMenuInflater = new SupportMenuInflater(getContext());
        }
        return this.mMenuInflater;
    }

    private ColorStateList createDefaultColorStateList(int i) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateList = getResources().getColorStateList(typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(R.colorPrimary, typedValue, true)) {
            return null;
        }
        int i2 = typedValue.data;
        int defaultColor = colorStateList.getDefaultColor();
        return new ColorStateList(new int[][]{DISABLED_STATE_SET, CHECKED_STATE_SET, EMPTY_STATE_SET}, new int[]{colorStateList.getColorForState(DISABLED_STATE_SET, defaultColor), i2, defaultColor});
    }
}
