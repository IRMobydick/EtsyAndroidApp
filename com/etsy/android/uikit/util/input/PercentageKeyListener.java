package com.etsy.android.uikit.util.input;

import android.support.v4.view.InputDeviceCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.NumberKeyListener;
import org.apache.commons.lang3.StringUtils;

public class PercentageKeyListener extends NumberKeyListener {
    private static final char[] ACCEPTED;
    private static final char[] ACCEPTED_NO_DECIMAL;
    private static final char PERCENT = '%';
    private boolean mNoDecimal;

    static {
        ACCEPTED = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', PERCENT};
        ACCEPTED_NO_DECIMAL = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', PERCENT};
    }

    protected char[] getAcceptedChars() {
        return this.mNoDecimal ? ACCEPTED_NO_DECIMAL : ACCEPTED;
    }

    private static boolean isDecimalPointChar(char c) {
        return c == '.';
    }

    private static boolean isPercentageChar(char c) {
        return c == PERCENT;
    }

    public int getInputType() {
        return InputDeviceCompat.SOURCE_MOUSE;
    }

    public void setNoDecimal(boolean z) {
        this.mNoDecimal = z;
    }

    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        int i5;
        SpannableStringBuilder spannableStringBuilder;
        CharSequence filter = super.filter(charSequence, i, i2, spanned, i3, i4);
        if (filter != null) {
            i = 0;
            i2 = filter.length();
            charSequence = filter;
        }
        int i6 = -1;
        int length = spanned.length();
        for (i5 = 0; i5 < i3; i5++) {
            char charAt = spanned.charAt(i5);
            if (isDecimalPointChar(charAt)) {
                i6 = i5;
            } else if (isPercentageChar(charAt)) {
                return StringUtils.EMPTY;
            }
        }
        int i7 = i6;
        i6 = -1;
        for (i5 = i4; i5 < length; i5++) {
            charAt = spanned.charAt(i5);
            if (isDecimalPointChar(charAt)) {
                i7 = i5;
            } else if (isPercentageChar(charAt)) {
                i6 = i5;
            }
        }
        SpannableStringBuilder spannableStringBuilder2 = null;
        length = i7;
        for (int i8 = i2 - 1; i8 >= i; i8--) {
            char charAt2 = charSequence.charAt(i8);
            Object obj = null;
            if (isPercentageChar(charAt2)) {
                obj = 1;
            } else if (isDecimalPointChar(charAt2)) {
                if (length >= 0) {
                    obj = 1;
                } else {
                    length = i8;
                }
            }
            if (obj != null) {
                if (i2 == i + 1) {
                    return StringUtils.EMPTY;
                }
                if (spannableStringBuilder2 == null) {
                    spannableStringBuilder2 = new SpannableStringBuilder(charSequence, i, i2);
                }
                spannableStringBuilder2.delete(i8 - i, (i8 + 1) - i);
            }
        }
        if (i6 == -1) {
            if (spannableStringBuilder2 == null) {
                spannableStringBuilder2 = new SpannableStringBuilder(charSequence, i, i2);
            }
            spannableStringBuilder2.append(PERCENT);
            spannableStringBuilder = spannableStringBuilder2;
        } else {
            spannableStringBuilder = spannableStringBuilder2;
        }
        if (spannableStringBuilder != null) {
            return spannableStringBuilder;
        }
        return filter == null ? null : filter;
    }
}
