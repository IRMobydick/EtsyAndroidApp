package com.etsy.android.uikit.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

public class InstantAutoCompleteTextView extends AutoCompleteTextView {

    /* renamed from: com.etsy.android.uikit.view.InstantAutoCompleteTextView.1 */
    class C10191 implements Runnable {
        final /* synthetic */ InstantAutoCompleteTextView f4215a;

        C10191(InstantAutoCompleteTextView instantAutoCompleteTextView) {
            this.f4215a = instantAutoCompleteTextView;
        }

        public void run() {
            this.f4215a.performFiltering(this.f4215a.getText(), 0);
            this.f4215a.showDropDown();
        }
    }

    public InstantAutoCompleteTextView(Context context) {
        super(context);
    }

    public InstantAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public InstantAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean enoughToFilter() {
        return true;
    }

    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (z && getAdapter() != null) {
            Context context = getContext();
            if ((context instanceof Activity) && !((Activity) context).isFinishing()) {
                post(new C10191(this));
            }
        }
    }
}
