package com.etsy.android.uikit.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.logger.EtsyDebug;

/* renamed from: com.etsy.android.uikit.util.f */
public class CleanupUtil {
    private static final String f4156a;

    static {
        f4156a = EtsyDebug.m1891a(CleanupUtil.class);
    }

    public static void m5532a(View view) {
        if (EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.bv) && view != null) {
            try {
                if (view.getBackground() != null) {
                    view.getBackground().setCallback(null);
                }
                if (view instanceof AdapterView) {
                    if (!(view instanceof Spinner)) {
                        ((AdapterView) view).setOnItemClickListener(null);
                    }
                    ((AdapterView) view).setOnItemLongClickListener(null);
                    ((AdapterView) view).setOnItemSelectedListener(null);
                } else {
                    view.setOnTouchListener(null);
                    view.setOnClickListener(null);
                }
                if ((view instanceof ViewGroup) && !(view instanceof AdapterView)) {
                    int i = 0;
                    while (true) {
                        if (i < ((ViewGroup) view).getChildCount()) {
                            CleanupUtil.m5532a(((ViewGroup) view).getChildAt(i));
                            i++;
                        } else {
                            ((ViewGroup) view).removeAllViews();
                            return;
                        }
                    }
                }
            } catch (Throwable th) {
            }
        }
    }
}
