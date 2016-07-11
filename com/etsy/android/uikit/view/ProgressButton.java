package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.etsy.android.iconsy.AbstractFontIcon;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.util.GraphikUtil;

public class ProgressButton extends RelativeLayout {
    private View mActivityIndicator;
    private LoadingIndicatorView mActivityIndicatorView;
    protected TextView mText;

    public ProgressButton(Context context) {
        super(context);
        setUpView(context);
    }

    public ProgressButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setUpView(context);
        setAttributes(context, attributeSet);
    }

    public ProgressButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setUpView(context);
        setAttributes(context, attributeSet);
    }

    private void setUpView(Context context) {
        if (!isInEditMode()) {
            ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.view_progress_button, this, true);
            this.mText = (TextView) findViewById(R.text);
            this.mActivityIndicator = findViewById(R.activity_indicator);
            this.mActivityIndicatorView = (LoadingIndicatorView) findViewById(R.activity_indicator_view);
        }
    }

    private void setAttributes(Context context, AttributeSet attributeSet) {
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.ProgressButton, 0, 0);
            try {
                setText(obtainStyledAttributes.getString(R.ProgressButton_text));
                setDrawableLeft(obtainStyledAttributes.getResourceId(R.ProgressButton_drawableLeft, 0));
                this.mText.setTextAppearance(context, obtainStyledAttributes.getResourceId(R.ProgressButton_textStyleResource, 0));
                this.mActivityIndicatorView.setImageResource(obtainStyledAttributes.getResourceId(R.ProgressButton_activityIndicatorDrawable, R.spinner_blue));
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    public void setText(String str) {
        this.mText.setText(str);
    }

    public void setText(int i) {
        this.mText.setText(i);
    }

    public void setTextIsBold(boolean z) {
        if (!GraphikUtil.m5547a(this.mText, z ? R.typeface_bold : R.typeface_normal)) {
            this.mText.setTypeface(null, z ? 1 : 0);
        }
    }

    public void setDrawableLeft(int i) {
        this.mText.setCompoundDrawablesWithIntrinsicBounds(i, 0, 0, 0);
    }

    public void setDrawableLeft(Drawable drawable) {
        this.mText.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
    }

    public void setDrawableLeft(AbstractFontIcon abstractFontIcon, int i, float f) {
        setDrawableLeft(IconDrawable.m775a(getResources()).m780a(abstractFontIcon).m779a(i).m778a(f).m777a());
    }

    public void clearDrawables() {
        this.mText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    public void setDrawablePadding(int i) {
        this.mText.setCompoundDrawablePadding(i);
    }

    public void setTextColor(int i) {
        this.mText.setTextColor(i);
    }

    public void showLoading() {
        setEnabled(false);
        this.mText.setVisibility(8);
        this.mActivityIndicator.setVisibility(0);
    }

    public void hideLoading() {
        setEnabled(true);
        this.mText.setVisibility(0);
        this.mActivityIndicator.setVisibility(8);
    }
}
