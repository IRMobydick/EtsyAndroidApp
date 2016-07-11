package com.etsy.android.uikit.navigationview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import com.etsy.android.lib.R;

public class EtsyNavigationMenuPresenter implements MenuPresenter, OnItemClickListener {
    private static final String STATE_HIERARCHY = "android:menu:list";
    private EtsyNavigationMenuPresenter mAdapter;
    private Callback mCallback;
    private LinearLayout mHeader;
    private ColorStateList mIconTintList;
    private int mId;
    private Drawable mItemBackground;
    private LayoutInflater mLayoutInflater;
    private MenuBuilder mMenu;
    private EtsyNavigationMenuView mMenuView;
    private int mPaddingSeparator;
    private int mPaddingTopDefault;
    private ColorStateList mTextColor;

    public void initForMenu(Context context, MenuBuilder menuBuilder) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mMenu = menuBuilder;
        Resources resources = context.getResources();
        this.mPaddingTopDefault = resources.getDimensionPixelOffset(R.design_navigation_padding_top_default);
        this.mPaddingSeparator = resources.getDimensionPixelOffset(R.design_navigation_separator_vertical_padding);
    }

    public MenuView getMenuView(ViewGroup viewGroup) {
        if (this.mMenuView == null) {
            this.mMenuView = (EtsyNavigationMenuView) this.mLayoutInflater.inflate(R.etsy_navigation_menu, viewGroup, false);
            if (this.mAdapter == null) {
                this.mAdapter = new EtsyNavigationMenuPresenter(this);
            }
            this.mHeader = (LinearLayout) this.mLayoutInflater.inflate(R.design_navigation_item_header, this.mMenuView, false);
            this.mMenuView.addHeaderView(this.mHeader);
            this.mMenuView.setAdapter(this.mAdapter);
            this.mMenuView.setOnItemClickListener(this);
        }
        return this.mMenuView;
    }

    public void updateMenuView(boolean z) {
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        return false;
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        if (this.mCallback != null) {
            this.mCallback.onCloseMenu(menuBuilder, z);
        }
    }

    public boolean flagActionItems() {
        return false;
    }

    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        SparseArray sparseArray = new SparseArray();
        if (this.mMenuView != null) {
            this.mMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray(STATE_HIERARCHY, sparseArray);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SparseArray sparseParcelableArray = ((Bundle) parcelable).getSparseParcelableArray(STATE_HIERARCHY);
        if (sparseParcelableArray != null) {
            this.mMenuView.restoreHierarchyState(sparseParcelableArray);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - this.mMenuView.getHeaderViewsCount();
        if (headerViewsCount >= 0) {
            this.mMenu.performItemAction(this.mAdapter.m5373a(headerViewsCount).m5379d(), this, 0);
        }
    }

    public View inflateHeaderView(@LayoutRes int i) {
        View inflate = this.mLayoutInflater.inflate(i, this.mHeader, false);
        addHeaderView(inflate);
        return inflate;
    }

    public void addHeaderView(@NonNull View view) {
        this.mHeader.addView(view);
        this.mMenuView.setPadding(0, 0, 0, this.mMenuView.getPaddingBottom());
    }

    public void removeHeaderView(@NonNull View view) {
        this.mHeader.removeView(view);
        if (this.mHeader.getChildCount() == 0) {
            this.mMenuView.setPadding(0, this.mPaddingTopDefault, 0, this.mMenuView.getPaddingBottom());
        }
    }

    @Nullable
    public ColorStateList getItemTintList() {
        return this.mIconTintList;
    }

    public void setItemIconTintList(@Nullable ColorStateList colorStateList) {
        this.mIconTintList = colorStateList;
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.mTextColor;
    }

    public void setItemTextColor(@Nullable ColorStateList colorStateList) {
        this.mTextColor = colorStateList;
    }

    public Drawable getItemBackground() {
        return this.mItemBackground;
    }

    public void setItemBackground(Drawable drawable) {
        this.mItemBackground = drawable;
    }
}
