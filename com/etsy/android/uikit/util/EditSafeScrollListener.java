package com.etsy.android.uikit.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.EditText;
import com.etsy.android.lib.util.ai;
import java.lang.ref.WeakReference;

public class EditSafeScrollListener implements OnScrollListener {
    WeakReference<Activity> mActivityRef;

    public EditSafeScrollListener(Activity activity) {
        this.mActivityRef = null;
        this.mActivityRef = new WeakReference(activity);
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        Context context = null;
        if (this.mActivityRef != null) {
            context = (Activity) this.mActivityRef.get();
        }
        if (context != null && 1 == i) {
            View currentFocus = context.getCurrentFocus();
            if (currentFocus != null && (currentFocus instanceof EditText)) {
                currentFocus.clearFocus();
                ai.m3225a(context, currentFocus);
            }
        }
    }
}
