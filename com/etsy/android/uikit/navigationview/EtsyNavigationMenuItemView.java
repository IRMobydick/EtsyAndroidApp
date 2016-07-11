package com.etsy.android.uikit.navigationview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView.ItemView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.uikit.view.BadgeDrawable;
import com.etsy.android.uikit.view.BadgeDrawable.CircleType;
import org.apache.commons.lang3.StringUtils;

public class EtsyNavigationMenuItemView extends LinearLayout implements ItemView {
    private static final int BADGE_MAX = 99;
    public static final String BADGE_NEW_KEY = "NEW";
    private static final int[] CHECKED_STATE_SET;
    private static final String TAG;
    private int mBadgeCount;
    private BadgeDrawable mBadgeDrawable;
    private ImageView mBadgeIconView;
    private String mBadgeText;
    private TextView mBadgeTextView;
    private int mIconSize;
    private ColorStateList mIconTintList;
    private MenuItemImpl mItemData;
    private TextView mTextView;

    static {
        TAG = EtsyDebug.m1891a(EtsyNavigationMenuItemView.class);
        CHECKED_STATE_SET = new int[]{16842912};
    }

    public EtsyNavigationMenuItemView(Context context) {
        this(context, null);
    }

    public EtsyNavigationMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EtsyNavigationMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        setOrientation(0);
        LayoutInflater.from(context).inflate(R.etsy_navigation_item_content, this, true);
        this.mTextView = (TextView) findViewById(R.etsy_menu_text);
        this.mBadgeIconView = (ImageView) findViewById(R.etsy_menu_badge_icon);
        this.mBadgeTextView = (TextView) findViewById(R.etsy_menu_badge_text);
        Resources resources = getResources();
        this.mIconSize = resources.getDimensionPixelSize(R.design_navigation_icon_size);
        int identifier = resources.getIdentifier("navigation_view_bg", ResponseConstants.COLOR, getContext().getApplicationContext().getPackageName());
        this.mBadgeDrawable = new BadgeDrawable(getContext(), R.navigation_view_item_color, identifier);
        this.mBadgeTextView.setTextColor(resources.getColor(identifier));
    }

    public void initialize(MenuItemImpl menuItemImpl, int i) {
        this.mItemData = menuItemImpl;
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            setBackgroundDrawable(createDefaultBackground());
        }
        this.mBadgeCount = 0;
        this.mBadgeText = StringUtils.EMPTY;
        setCheckable(menuItemImpl.isCheckable());
        setChecked(menuItemImpl.isChecked());
        setEnabled(menuItemImpl.isEnabled());
        setTitle(menuItemImpl.getTitle());
        setIcon(menuItemImpl.getIcon());
        updateBadge();
    }

    private StateListDrawable createDefaultBackground() {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(R.colorControlHighlight, typedValue, true)) {
            return null;
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(CHECKED_STATE_SET, new ColorDrawable(typedValue.data));
        stateListDrawable.addState(EMPTY_STATE_SET, new ColorDrawable(0));
        return stateListDrawable;
    }

    public MenuItemImpl getItemData() {
        return this.mItemData;
    }

    public void setTitle(CharSequence charSequence) {
        String charSequence2 = charSequence.toString();
        int length = charSequence2.length() - 1;
        if (length <= 0 || charSequence2.charAt(length) != '#') {
            this.mTextView.setText(charSequence);
            return;
        }
        int indexOf = charSequence2.indexOf(35);
        if (indexOf != length) {
            this.mTextView.setText(charSequence2.substring(0, indexOf));
            charSequence2 = charSequence2.substring(indexOf + 1, length);
            if (charSequence2.equals(BADGE_NEW_KEY)) {
                this.mBadgeText = BADGE_NEW_KEY;
                return;
            }
            try {
                this.mBadgeCount = Integer.parseInt(charSequence2);
            } catch (Throwable e) {
                EtsyDebug.m1917d(TAG, "setTitle badge count error", e);
            }
        }
    }

    public void setCheckable(boolean z) {
        refreshDrawableState();
    }

    public void setChecked(boolean z) {
        refreshDrawableState();
    }

    public void setShortcut(boolean z, char c) {
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            drawable = DrawableCompat.wrap(drawable).mutate();
            drawable.setBounds(0, 0, this.mIconSize, this.mIconSize);
        }
        TextViewCompat.setCompoundDrawablesRelative(this.mTextView, drawable, null, null, null);
    }

    private void updateBadge() {
        if (this.mBadgeCount > 0 || !TextUtils.isEmpty(this.mBadgeText)) {
            if (this.mBadgeCount > 0) {
                Drawable drawable = this.mBadgeDrawable;
                drawable.setCircleType(CircleType.CIRCLE);
                drawable.setBounds(0, 0, this.mIconSize, this.mIconSize);
                if (this.mBadgeCount > BADGE_MAX) {
                    drawable.setBadgeText(getContext().getString(R.badge_max));
                } else {
                    drawable.setCount(this.mBadgeCount);
                }
                this.mBadgeIconView.setImageDrawable(drawable);
                this.mBadgeIconView.setVisibility(0);
                this.mBadgeTextView.setText(StringUtils.EMPTY);
                this.mBadgeTextView.setVisibility(8);
                return;
            } else if (!TextUtils.isEmpty(this.mBadgeText)) {
                this.mBadgeIconView.setImageDrawable(null);
                this.mBadgeIconView.setVisibility(8);
                this.mBadgeTextView.setText(this.mBadgeText);
                this.mBadgeTextView.setVisibility(0);
                return;
            }
        }
        this.mBadgeIconView.setImageDrawable(null);
        this.mBadgeIconView.setVisibility(8);
        this.mBadgeTextView.setText(StringUtils.EMPTY);
        this.mBadgeTextView.setVisibility(8);
    }

    public boolean prefersCondensedTitle() {
        return false;
    }

    public boolean showsIcon() {
        return true;
    }

    protected int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.mItemData != null && this.mItemData.isCheckable() && this.mItemData.isChecked()) {
            mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    void setTextColor(ColorStateList colorStateList) {
        if (this.mTextView != null) {
            this.mTextView.setTextColor(colorStateList);
        }
    }

    void setIconTintList(ColorStateList colorStateList) {
        this.mIconTintList = colorStateList;
        if (this.mItemData != null) {
            setIcon(this.mItemData.getIcon());
        }
    }
}
