package com.etsy.android.uikit.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.etsy.android.lib.R;

public class ReadStateRelativeLayout extends RelativeLayout {
    public static final int[] READ_STATE;
    boolean mIsRead;

    static {
        READ_STATE = new int[]{R.state_read};
    }

    public ReadStateRelativeLayout(Context context) {
        super(context);
    }

    public ReadStateRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.mIsRead) {
            mergeDrawableStates(onCreateDrawableState, READ_STATE);
        }
        return onCreateDrawableState;
    }

    public void setRead(boolean z) {
        this.mIsRead = z;
        refreshDrawableState();
    }
}
