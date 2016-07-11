package com.etsy.android.uikit.util;

import android.annotation.TargetApi;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnDrawListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import com.etsy.android.lib.util.aa;

/* renamed from: com.etsy.android.uikit.util.x */
public class ViewTreeObserverHelper {
    public static void m5636a(ViewTreeObserver viewTreeObserver, OnGlobalLayoutListener onGlobalLayoutListener) {
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    @TargetApi(16)
    public static void m5639b(ViewTreeObserver viewTreeObserver, OnGlobalLayoutListener onGlobalLayoutListener) {
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            if (aa.m3167a()) {
                viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
            } else {
                viewTreeObserver.removeGlobalOnLayoutListener(onGlobalLayoutListener);
            }
        }
    }

    public static void m5637a(ViewTreeObserver viewTreeObserver, OnPreDrawListener onPreDrawListener) {
        if (viewTreeObserver != null) {
            viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
        }
    }

    @TargetApi(16)
    public static void m5635a(ViewTreeObserver viewTreeObserver, OnDrawListener onDrawListener) {
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnDrawListener(onDrawListener);
        }
    }

    @TargetApi(16)
    public static void m5638b(ViewTreeObserver viewTreeObserver, OnDrawListener onDrawListener) {
        if (viewTreeObserver != null) {
            viewTreeObserver.removeOnDrawListener(onDrawListener);
        }
    }
}
