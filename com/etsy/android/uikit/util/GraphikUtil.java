package com.etsy.android.uikit.util;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.StringRes;
import android.text.style.MetricAffectingSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.i;
import uk.co.chrisjenx.calligraphy.n;

/* renamed from: com.etsy.android.uikit.util.l */
public class GraphikUtil {
    private static GraphikUtil f4160a;
    private boolean f4161b;

    public GraphikUtil() {
        this.f4161b = false;
    }

    private static GraphikUtil m5549c() {
        if (f4160a == null) {
            f4160a = new GraphikUtil();
        }
        return f4160a;
    }

    public static void m5545a() {
        GraphikUtil.m5549c().f4161b = true;
    }

    public static boolean m5548b() {
        return GraphikUtil.m5549c().f4161b && EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.cg);
    }

    public static boolean m5547a(TextView textView, @StringRes int i) {
        if (!GraphikUtil.m5548b()) {
            return false;
        }
        i.a(textView.getContext(), textView, textView.getResources().getString(i));
        return true;
    }

    public static boolean m5546a(Context context, Paint paint, @StringRes int i) {
        if (!GraphikUtil.m5548b()) {
            return false;
        }
        paint.setTypeface(n.a(context.getAssets(), context.getString(i)));
        return true;
    }

    public static MetricAffectingSpan m5544a(Context context) {
        if (GraphikUtil.m5548b()) {
            return new CalligraphyTypefaceSpan(n.a(context.getAssets(), context.getString(R.typeface_bold)));
        }
        return new StyleSpan(1);
    }
}
