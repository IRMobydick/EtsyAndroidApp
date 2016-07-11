package com.etsy.android.uikit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.bh;
import com.etsy.android.uikit.util.MachineTranslationViewState;
import org.apache.commons.lang3.StringUtils;

public class MachineTranslationOneClickView extends MachineTranslationButton {
    public TextView mTranslatedContent;

    public MachineTranslationOneClickView(Context context) {
        super(context);
    }

    public MachineTranslationOneClickView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MachineTranslationOneClickView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public MachineTranslationOneClickView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    protected void init(Context context) {
        super.init(context);
        this.mInactiveButtonColor = context.getResources().getColor(R.text_mid_grey);
    }

    protected void onFinishInflate() {
        this.mTranslatedContent = (TextView) findViewById(R.translated_content);
        super.onFinishInflate();
    }

    @Deprecated
    public void showButtonElements() {
        this.mTranslateButton.setVisibility(0);
    }

    @Deprecated
    public void hideAllElements() {
        this.mTranslateButton.setVisibility(8);
        this.mTranslationLoadingSpinner.setVisibility(8);
        this.mTranslationErrorMessage.setVisibility(8);
        this.mTranslatedContent.setVisibility(8);
    }

    public void setTranslatedStateWithString(String str) {
        setTranslatedState();
        this.mTranslatedContent.setVisibility(0);
        this.mTranslatedContent.setText(Html.fromHtml(str));
    }

    public void setTranslatedState() {
        super.setTranslatedState();
        this.mTranslateButton.setEnabled(false);
        this.mTranslateButton.setText(R.machine_translation_one_click_translated);
    }

    public void setUntranslatedState() {
        super.setUntranslatedState();
        this.mTranslatedContent.setText(StringUtils.EMPTY);
        this.mTranslatedContent.setVisibility(8);
        this.mTranslateButton.setEnabled(true);
    }

    public void setListingTranslationState(boolean z, String str) {
        int i;
        int i2;
        if (z) {
            i = R.machine_translation_disclaimer;
            i2 = R.machine_translation_listing_untranslate;
        } else {
            i = R.machine_translation_explainer;
            i2 = R.machine_translation_listing_translate;
        }
        this.mTranslationDisclaimer.setText(i);
        this.mTranslateButton.setText(getResources().getString(i2, new Object[]{aj.m3228a(str)}));
        this.mTranslatedContent.setVisibility(8);
    }

    public void configureForStateAndMessage(@NonNull MachineTranslationViewState machineTranslationViewState, @Nullable String str) {
        if (machineTranslationViewState.hasLoadedTranslation() && bh.m3343b(str)) {
            setTranslatedStateWithString(str);
        } else {
            setUntranslatedState();
        }
        super.configureForState(machineTranslationViewState);
    }

    protected void scaleElements() {
        super.scaleElements();
        this.mTranslatedContent.setTextSize(0, this.mTextSize);
    }
}
