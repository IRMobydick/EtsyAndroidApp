package com.etsy.android.uikit.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import com.etsy.android.uikit.util.input.PercentageKeyListener;
import org.apache.commons.lang3.StringUtils;

public class PercentEditText extends ManagedSelectionEditText {
    private PercentageKeyListener mPercentKeyListener;

    /* renamed from: com.etsy.android.uikit.view.PercentEditText.1 */
    class C10211 implements ManagedSelectionEditText {
        final /* synthetic */ PercentEditText f4217a;

        C10211(PercentEditText percentEditText) {
            this.f4217a = percentEditText;
        }

        public void m5643a(int i, int i2) {
            int length = this.f4217a.getText().length();
            if (i2 > 1 && i2 == length) {
                this.f4217a.setSelection(length - 1);
            }
        }
    }

    /* renamed from: com.etsy.android.uikit.view.PercentEditText.2 */
    class C10222 implements TextWatcher {
        final /* synthetic */ PercentEditText f4218a;

        C10222(PercentEditText percentEditText) {
            this.f4218a = percentEditText;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (editable.toString().equals("%")) {
                this.f4218a.setText(StringUtils.EMPTY);
            }
        }
    }

    public PercentEditText(Context context) {
        super(context);
        init();
    }

    public PercentEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public PercentEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.mPercentKeyListener = new PercentageKeyListener();
        setKeyListener(this.mPercentKeyListener);
        setOnSelectionChangedListener(new C10211(this));
        addTextChangedListener(new C10222(this));
    }

    public void setNoDecimal(boolean z) {
        this.mPercentKeyListener.setNoDecimal(z);
    }

    public double getPercentDouble() {
        String replace = getText().toString().replace("%", StringUtils.EMPTY);
        if (replace.length() > 0) {
            return Double.parseDouble(replace);
        }
        return 0.0d;
    }

    public int getPercentInt() {
        String replace = getText().toString().replace("%", StringUtils.EMPTY);
        if (replace.length() > 0) {
            return Integer.parseInt(replace);
        }
        return 0;
    }
}
