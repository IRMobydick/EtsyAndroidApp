package com.etsy.android.lib.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/* compiled from: KeyboardUtils */
public class ai {

    /* renamed from: com.etsy.android.lib.util.ai.1 */
    final class KeyboardUtils implements Runnable {
        final /* synthetic */ View f2002a;
        final /* synthetic */ InputMethodManager f2003b;

        KeyboardUtils(View view, InputMethodManager inputMethodManager) {
            this.f2002a = view;
            this.f2003b = inputMethodManager;
        }

        public void run() {
            this.f2002a.clearFocus();
            this.f2002a.requestFocus();
            this.f2003b.showSoftInput(this.f2002a, 0);
        }
    }

    public static void m3224a(Context context) {
        if (context != null) {
            m3226b(context).toggleSoftInput(0, 0);
        }
    }

    public static boolean m3225a(Context context, View view) {
        if (context == null || view == null) {
            return false;
        }
        return m3226b(context).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static InputMethodManager m3226b(Context context) {
        return (InputMethodManager) context.getSystemService("input_method");
    }

    public static void m3227b(Context context, View view) {
        view.postDelayed(new KeyboardUtils(view, m3226b(context)), 200);
    }
}
