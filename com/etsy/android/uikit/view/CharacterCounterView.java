package com.etsy.android.uikit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import com.etsy.android.lib.R;

@Deprecated
public class CharacterCounterView extends TextView implements TextWatcher {
    private static final String DEFAULT_FORMAT = "%d/%d";
    private static final int DEFAULT_MAX_CHARS = 100;
    private int mErrorColor;
    private int mMaxChars;
    private TextView mObservable;
    private int mOriginalColor;

    public CharacterCounterView(Context context) {
        super(context);
        init(null);
    }

    public CharacterCounterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.CharacterCounterView, 0, 0);
            try {
                this.mMaxChars = obtainStyledAttributes.getInteger(R.CharacterCounterView_maxChars, DEFAULT_MAX_CHARS);
                this.mOriginalColor = getCurrentTextColor();
                this.mErrorColor = getResources().getColor(R.design_textinput_error_color_light);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    public void setObservable(TextView textView) {
        if (this.mObservable != null) {
            this.mObservable.removeTextChangedListener(this);
        }
        this.mObservable = textView;
        this.mObservable.addTextChangedListener(this);
    }

    public void setMaxChars(int i) {
        this.mMaxChars = i;
    }

    public boolean isTooLong() {
        return this.mObservable != null && this.mObservable.length() > this.mMaxChars;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        int i = isTooLong() ? this.mErrorColor : this.mOriginalColor;
        CharSequence spannableStringBuilder = new SpannableStringBuilder(String.format(DEFAULT_FORMAT, new Object[]{Integer.valueOf(this.mMaxChars - editable.length()), Integer.valueOf(this.mMaxChars)}));
        spannableStringBuilder.setSpan(new ForegroundColorSpan(i), 0, String.valueOf(r1).length(), 17);
        setText(spannableStringBuilder);
    }
}
