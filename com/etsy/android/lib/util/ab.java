package com.etsy.android.lib.util;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import com.etsy.android.lib.R;

/* compiled from: DisplayUtil */
public class ab {
    private static float f1992c;
    private static float f1993d;
    private final DisplayMetrics f1994a;
    private final WindowManager f1995b;

    static {
        f1992c = 0.0f;
        f1993d = 0.0f;
    }

    public static void m3175a(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        f1992c = displayMetrics.density;
        f1993d = (float) displayMetrics.densityDpi;
    }

    public static float m3171a() {
        return f1992c;
    }

    public ab(Context context) {
        this.f1995b = (WindowManager) context.getSystemService("window");
        this.f1994a = new DisplayMetrics();
        this.f1995b.getDefaultDisplay().getMetrics(this.f1994a);
        if (f1992c == 0.0f) {
            m3175a(context);
        }
    }

    public void m3179b() {
        this.f1995b.getDefaultDisplay().getMetrics(this.f1994a);
    }

    public int m3180c() {
        return Math.min(this.f1994a.widthPixels, this.f1994a.heightPixels);
    }

    public int m3181d() {
        return Math.max(this.f1994a.widthPixels, this.f1994a.heightPixels);
    }

    public int m3182e() {
        return this.f1994a.widthPixels;
    }

    public int m3183f() {
        return this.f1994a.heightPixels;
    }

    public float m3178a(float f) {
        return (((float) this.f1994a.densityDpi) / 160.0f) * f;
    }

    public boolean m3184g() {
        return m3183f() < m3182e();
    }

    public static int m3177b(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.actionBarSize, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, displayMetrics);
        }
        return 0;
    }

    public static void m3176a(View view, int i, int i2, int i3, int i4) {
        if (view.getLayoutParams() instanceof MarginLayoutParams) {
            ((MarginLayoutParams) view.getLayoutParams()).setMargins(i, i2, i3, i4);
            view.requestLayout();
        }
    }

    public static int m3172a(int i) {
        if (f1992c != 0.0f) {
            return (int) (((float) i) / f1992c);
        }
        return i;
    }

    public static int m3174a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        int parseColor;
        if (str.charAt(0) == '#') {
            parseColor = Color.parseColor(str);
        } else {
            try {
                parseColor = Color.parseColor("#" + str);
            } catch (IllegalArgumentException e) {
                parseColor = -1;
            }
        }
        if (parseColor == -1) {
            return parseColor;
        }
        return m3173a(Color.red(parseColor), Color.green(parseColor), Color.blue(parseColor));
    }

    public static int m3173a(int i, int i2, int i3) {
        return Color.rgb((int) ((((float) i) * 0.33f) + 170.84999f), (int) ((((float) i2) * 0.33f) + 170.84999f), (int) ((((float) i3) * 0.33f) + 170.84999f));
    }
}
