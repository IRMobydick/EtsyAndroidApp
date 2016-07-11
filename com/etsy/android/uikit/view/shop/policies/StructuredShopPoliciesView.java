package com.etsy.android.uikit.view.shop.policies;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconView;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public abstract class StructuredShopPoliciesView extends RelativeLayout {
    protected static final String BULLET_POINT_AND_SPACE = "&#8226; ";
    @Nullable
    private View mBtnExpand;
    @Nullable
    private View mExpansionSection;
    private boolean mIsContentCollapsible;
    private boolean mIsExpanded;
    private boolean mSellerMode;

    /* renamed from: com.etsy.android.uikit.view.shop.policies.StructuredShopPoliciesView.1 */
    class C10381 extends TrackingOnClickListener {
        final /* synthetic */ StructuredShopPoliciesView f4275a;

        C10381(StructuredShopPoliciesView structuredShopPoliciesView) {
            this.f4275a = structuredShopPoliciesView;
        }

        public void onViewClick(View view) {
            this.f4275a.setExpanded(true);
        }
    }

    /* renamed from: com.etsy.android.uikit.view.shop.policies.StructuredShopPoliciesView.2 */
    class C10392 extends TrackingOnClickListener {
        final /* synthetic */ StructuredShopPoliciesView f4276a;
        final /* synthetic */ StructuredShopPoliciesView f4277b;

        C10392(StructuredShopPoliciesView structuredShopPoliciesView, StructuredShopPoliciesView structuredShopPoliciesView2) {
            this.f4277b = structuredShopPoliciesView;
            this.f4276a = structuredShopPoliciesView2;
        }

        public void onViewClick(View view) {
            this.f4276a.m4219a();
        }
    }

    class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        boolean f4278a;
        boolean f4279b;

        /* renamed from: com.etsy.android.uikit.view.shop.policies.StructuredShopPoliciesView.SavedState.1 */
        final class C10401 implements Creator<SavedState> {
            C10401() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m5670a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m5671a(i);
            }

            public SavedState m5670a(Parcel parcel) {
                return new SavedState(null);
            }

            public SavedState[] m5671a(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(@NonNull Parcel parcel, int i) {
            int i2;
            int i3 = 1;
            super.writeToParcel(parcel, i);
            if (this.f4278a) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            parcel.writeInt(i2);
            if (!this.f4279b) {
                i3 = 0;
            }
            parcel.writeInt(i3);
        }

        private SavedState(Parcel parcel) {
            boolean z;
            boolean z2 = true;
            super(parcel);
            if (parcel.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            this.f4278a = z;
            if (parcel.readInt() != 1) {
                z2 = false;
            }
            this.f4279b = z2;
        }

        static {
            CREATOR = new C10401();
        }
    }

    protected abstract void init(Context context, LinearLayout linearLayout);

    public StructuredShopPoliciesView(Context context) {
        super(context);
        this.mIsContentCollapsible = false;
        this.mIsExpanded = true;
        init(context, null, 0, 0);
    }

    public StructuredShopPoliciesView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsContentCollapsible = false;
        this.mIsExpanded = true;
        init(context, attributeSet, 0, 0);
    }

    public StructuredShopPoliciesView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIsContentCollapsible = false;
        this.mIsExpanded = true;
        init(context, attributeSet, i, 0);
    }

    @TargetApi(21)
    public StructuredShopPoliciesView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mIsContentCollapsible = false;
        this.mIsExpanded = true;
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        IconView iconView;
        inflate(context, R.view_structured_shop_policies, this);
        IconView iconView2 = (IconView) findViewById(R.edit_icon);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.content);
        init(context, linearLayout);
        this.mExpansionSection = linearLayout.findViewById(R.expansion_section);
        this.mBtnExpand = linearLayout.findViewById(R.btn_expand);
        if (linearLayout.findViewById(R.edit_icon) != null) {
            ((ViewGroup) iconView2.getParent()).removeView(iconView2);
            iconView = (IconView) linearLayout.findViewById(R.edit_icon);
        } else {
            iconView = iconView2;
        }
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.StructuredShopPoliciesView, i, i2);
            this.mSellerMode = obtainStyledAttributes.getBoolean(R.StructuredShopPoliciesView_editMode, false);
            iconView.setVisibility(this.mSellerMode ? 0 : 8);
            obtainStyledAttributes.recycle();
        }
    }

    public boolean isSellerMode() {
        return this.mSellerMode;
    }

    protected void setContentCollapsible(boolean z) {
        this.mIsContentCollapsible = z;
    }

    public boolean setExpanded(boolean z) {
        int i = 0;
        if ((!z && !this.mIsContentCollapsible) || this.mBtnExpand == null || this.mExpansionSection == null) {
            return false;
        }
        this.mBtnExpand.setVisibility(z ? 8 : 0);
        this.mBtnExpand.setOnClickListener(z ? null : new C10381(this));
        View view = this.mExpansionSection;
        if (!z) {
            i = 8;
        }
        view.setVisibility(i);
        this.mIsExpanded = z;
        return true;
    }

    protected void linkifyContactShopUrlSpan(TextView textView, @Nullable StructuredShopPoliciesView structuredShopPoliciesView) {
        int i = 0;
        if (structuredShopPoliciesView == null) {
            URLSpan[] urls = textView.getUrls();
            CharSequence spannableStringBuilder = new SpannableStringBuilder(textView.getText());
            int length = urls.length;
            while (i < length) {
                spannableStringBuilder.removeSpan(urls[i]);
                i++;
            }
            textView.setText(spannableStringBuilder);
            return;
        }
        EtsyLinkify.m5488a(textView, false, new C10392(this, structuredShopPoliciesView));
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f4278a = this.mIsContentCollapsible;
        savedState.f4279b = this.mIsExpanded;
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            setContentCollapsible(savedState.f4278a);
            setExpanded(savedState.f4279b);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }
}
