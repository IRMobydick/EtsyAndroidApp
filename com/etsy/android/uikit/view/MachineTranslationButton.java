package com.etsy.android.uikit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconDrawable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.util.bh;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.etsy.android.uikit.util.MachineTranslationViewState;

public class MachineTranslationButton extends RelativeLayout {
    protected int mActiveButtonColor;
    private String mDisclaimerText;
    protected IconDrawable mGlobeIcon;
    protected int mInactiveButtonColor;
    protected float mTextSize;
    protected TextView mTranslateButton;
    protected TextView mTranslationDisclaimer;
    protected TextView mTranslationErrorMessage;
    protected LoadingIndicatorView mTranslationLoadingSpinner;

    public MachineTranslationButton(Context context) {
        super(context);
        init(context);
        initFromAttrs(context, null);
    }

    public MachineTranslationButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
        initFromAttrs(context, attributeSet);
    }

    public MachineTranslationButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
        initFromAttrs(context, attributeSet);
    }

    @TargetApi(21)
    public MachineTranslationButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
        initFromAttrs(context, attributeSet);
    }

    protected void init(Context context) {
        this.mTextSize = (float) context.getResources().getDimensionPixelSize(R.text_medium);
        this.mActiveButtonColor = context.getResources().getColor(R.blue);
        this.mInactiveButtonColor = context.getResources().getColor(R.blue);
    }

    protected void initFromAttrs(Context context, AttributeSet attributeSet) {
        inflate(context, R.machine_translation_one_click, this);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.MachineTranslationOneClickView, 0, 0);
        if (obtainStyledAttributes != null) {
            try {
                this.mTextSize = (float) obtainStyledAttributes.getDimensionPixelSize(R.MachineTranslationOneClickView_textSize, (int) this.mTextSize);
                this.mActiveButtonColor = obtainStyledAttributes.getColor(R.MachineTranslationOneClickView_activeButtonColor, this.mActiveButtonColor);
                this.mInactiveButtonColor = obtainStyledAttributes.getColor(R.MachineTranslationOneClickView_inactiveButtonColor, this.mInactiveButtonColor);
                this.mDisclaimerText = obtainStyledAttributes.getString(R.MachineTranslationOneClickView_disclaimerText);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    protected void onFinishInflate() {
        this.mTranslateButton = (TextView) findViewById(R.translate_button);
        this.mTranslationDisclaimer = (TextView) findViewById(R.translation_disclaimer);
        if (this.mDisclaimerText != null) {
            this.mTranslationDisclaimer.setText(this.mDisclaimerText);
        }
        this.mTranslationLoadingSpinner = (LoadingIndicatorView) findViewById(R.loading_translation_spinner);
        this.mTranslationErrorMessage = (TextView) findViewById(R.machine_translation_error);
        scaleElements();
        this.mGlobeIcon = IconDrawable.m775a(getResources()).m780a(EtsyFontIcons.GLOBE).m779a(this.mActiveButtonColor).m778a(getResources().getFraction(R.machine_translation_globe_scale, 1, 1) * this.mTextSize).m777a();
        int intrinsicWidth = this.mGlobeIcon.getIntrinsicWidth();
        this.mGlobeIcon.setGravity(80);
        this.mGlobeIcon.setBounds(0, 0, intrinsicWidth, intrinsicWidth);
        this.mTranslateButton.setCompoundDrawables(this.mGlobeIcon, null, null, null);
        setTranslateButtonColor(this.mActiveButtonColor);
        super.onFinishInflate();
    }

    public void showSpinner() {
        this.mTranslationLoadingSpinner.setVisibility(0);
    }

    public void hideSpinner() {
        this.mTranslationLoadingSpinner.setVisibility(8);
    }

    public void showErrorMessage() {
        this.mTranslationErrorMessage.setVisibility(0);
    }

    public void hideErrorMessage() {
        this.mTranslationErrorMessage.setVisibility(8);
    }

    public void showDisclaimer() {
        this.mTranslationDisclaimer.setVisibility(0);
    }

    public void setTranslatedState() {
        setTranslateButtonColor(this.mInactiveButtonColor);
        this.mTranslateButton.setText(getResources().getString(R.untranslate));
        if (bh.m3343b(this.mDisclaimerText)) {
            this.mTranslationDisclaimer.setVisibility(0);
        }
    }

    public void setUntranslatedState() {
        setTranslateButtonColor(this.mActiveButtonColor);
        this.mTranslateButton.setText(R.machine_translation_one_click_translate);
        this.mTranslationDisclaimer.setVisibility(8);
    }

    public void configureForState(@NonNull MachineTranslationViewState machineTranslationViewState) {
        if (!machineTranslationViewState.hasLoadedTranslation() || machineTranslationViewState.isShowingOriginal()) {
            setUntranslatedState();
        } else {
            setTranslatedState();
        }
        if (machineTranslationViewState.isLoadingTranslation()) {
            showSpinner();
        } else {
            hideSpinner();
        }
        if (machineTranslationViewState.errorOccurredLoadingTranslation()) {
            showErrorMessage();
        } else {
            hideErrorMessage();
        }
    }

    protected void setTranslateButtonColor(int i) {
        this.mTranslateButton.setTextColor(i);
        this.mGlobeIcon.setColor(i);
    }

    protected void scaleElements() {
        this.mTranslateButton.setTextSize(0, this.mTextSize);
        this.mTranslationErrorMessage.setTextSize(0, this.mTextSize);
        this.mTranslationLoadingSpinner.getLayoutParams().height = (int) this.mTextSize;
        this.mTranslationDisclaimer.setTextSize(0, this.mTextSize);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mTranslateButton.setOnClickListener(onClickListener);
    }
}
