package com.etsy.android.lib.util;

import android.animation.LayoutTransition;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.view.FullImageView;

/* compiled from: UIUtils */
public class bl {
    public static void m3357a(View view) {
        m3358a(view, 0.6f);
        view.setEnabled(false);
    }

    public static void m3366b(View view) {
        m3358a(view, (float) FullImageView.ASPECT_RATIO_SQUARE);
        view.setEnabled(true);
    }

    @TargetApi(16)
    public static void m3360a(ViewGroup viewGroup) {
        if (viewGroup != null) {
            LayoutTransition layoutTransition = viewGroup.getLayoutTransition();
            if (layoutTransition != null) {
                layoutTransition.enableTransitionType(4);
            }
        }
    }

    public static void m3358a(View view, float f) {
        view.setAlpha(f);
    }

    @TargetApi(16)
    public static void m3359a(View view, Drawable drawable) {
        if (aa.m3167a()) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void m3354a(int i, View view) {
        m3363a(view.getContext().getResources().getString(i), view);
    }

    public static void m3363a(String str, View view) {
        Context context = view.getContext();
        if (context != null && !TextUtils.isEmpty(str)) {
            Toast makeText = Toast.makeText(context, str, 0);
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            makeText.setGravity(51, (view.getWidth() / 2) + iArr[0], iArr[1] + (view.getHeight() / 2));
            makeText.show();
        }
    }

    public static void m3367b(String str, @Nullable View view) {
        if (view != null) {
            Snackbar make = Snackbar.make(view, (CharSequence) str, 0);
            make.getView().setBackgroundResource(R.bg_red);
            make.show();
        }
    }

    public static void m3355a(Context context, int i) {
        if (context != null && i != 0) {
            Toast makeText = Toast.makeText(context, i, 0);
            makeText.setGravity(17, 0, 0);
            makeText.show();
        }
    }

    public static void m3365b(Context context, int i) {
        if (context != null && i != 0) {
            Toast.makeText(context, i, 0).show();
        }
    }

    public static void m3356a(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            Toast.makeText(context, str, 0).show();
        }
    }

    public static void m3362a(TextView textView, String str, String str2, int i) {
        textView.setText(str, BufferType.SPANNABLE);
        Spannable spannable = (Spannable) textView.getText();
        int lastIndexOf = str.lastIndexOf(str2);
        spannable.setSpan(new ForegroundColorSpan(i), lastIndexOf, str2.length() + lastIndexOf, 33);
    }

    public static void m3361a(TextView textView, @StringRes int i, @StringRes int i2, @ColorRes int i3) {
        String string = textView.getResources().getString(i);
        String string2 = textView.getResources().getString(i2);
        m3362a(textView, string + " " + string2, string2, textView.getResources().getColor(i3));
    }

    public static ProgressDialog m3364b(Context context, String str) {
        if (context == null) {
            return null;
        }
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(0);
        progressDialog.setMessage(str);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    public static int m3353a(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new TypedValue().data, new int[]{R.colorAccent});
        int color = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        return color;
    }

    public static int m3352a(Activity activity, int i) {
        return m3368c(activity, i);
    }

    public static int m3368c(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.resourceId;
    }
}
