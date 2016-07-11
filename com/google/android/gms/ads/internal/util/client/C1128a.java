package com.google.android.gms.ads.internal.util.client;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.support.annotation.Nullable;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.adjust.sdk.Constants;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.b;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.e;
import com.google.android.gms.ads.i;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.a.1;
import com.google.android.gms.ads.search.SearchAdView;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.common.zzc;
import com.google.android.gms.internal.jw;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

@jw
/* renamed from: com.google.android.gms.ads.internal.util.client.a */
public class C1128a {
    public static final Handler f4666a;
    private static final String f4667b;
    private static final String f4668c;
    private static final String f4669d;
    private static final String f4670e;
    private static final String f4671f;
    private static final String f4672g;

    static {
        f4666a = new Handler(Looper.getMainLooper());
        f4667b = AdView.class.getName();
        f4668c = i.class.getName();
        f4669d = PublisherAdView.class.getName();
        f4670e = e.class.getName();
        f4671f = SearchAdView.class.getName();
        f4672g = b.class.getName();
    }

    private void m6165a(ViewGroup viewGroup, AdSizeParcel adSizeParcel, String str, int i, int i2) {
        if (viewGroup.getChildCount() == 0) {
            Context context = viewGroup.getContext();
            View textView = new TextView(context);
            textView.setGravity(17);
            textView.setText(str);
            textView.setTextColor(i);
            textView.setBackgroundColor(i2);
            View frameLayout = new FrameLayout(context);
            frameLayout.setBackgroundColor(i);
            int a = m6166a(context, 3);
            frameLayout.addView(textView, new LayoutParams(adSizeParcel.widthPixels - a, adSizeParcel.heightPixels - a, 17));
            viewGroup.addView(frameLayout, adSizeParcel.widthPixels, adSizeParcel.heightPixels);
        }
    }

    public int m6166a(Context context, int i) {
        return m6167a(context.getResources().getDisplayMetrics(), i);
    }

    public int m6167a(DisplayMetrics displayMetrics, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, displayMetrics);
    }

    public String m6168a(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        String string = contentResolver == null ? null : Secure.getString(contentResolver, "android_id");
        if (string == null || m6177a()) {
            string = "emulator";
        }
        return m6169a(string);
    }

    public String m6169a(String str) {
        int i = 0;
        while (i < 2) {
            try {
                MessageDigest.getInstance(Constants.MD5).update(str.getBytes());
                return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, r1.digest())});
            } catch (NoSuchAlgorithmException e) {
                i++;
            }
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    java.lang.String m6170a(java.lang.String r6, java.lang.String r7, int r8) {
        /*
        r5 = this;
        r2 = new java.util.StringTokenizer;
        r2.<init>(r6, r7);
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r0 = r8 + -1;
        if (r8 <= 0) goto L_0x0038;
    L_0x000e:
        r1 = r2.hasMoreElements();
        if (r1 == 0) goto L_0x0038;
    L_0x0014:
        r1 = r2.nextToken();
        r3.append(r1);
    L_0x001b:
        r1 = r0 + -1;
        if (r0 <= 0) goto L_0x0034;
    L_0x001f:
        r0 = r2.hasMoreElements();
        if (r0 == 0) goto L_0x0034;
    L_0x0025:
        r0 = ".";
        r0 = r3.append(r0);
        r4 = r2.nextToken();
        r0.append(r4);
        r0 = r1;
        goto L_0x001b;
    L_0x0034:
        r6 = r3.toString();
    L_0x0038:
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.util.client.a.a(java.lang.String, java.lang.String, int):java.lang.String");
    }

    @Nullable
    public String m6171a(StackTraceElement[] stackTraceElementArr, String str) {
        String className;
        for (int i = 0; i + 1 < stackTraceElementArr.length; i++) {
            StackTraceElement stackTraceElement = stackTraceElementArr[i];
            String className2 = stackTraceElement.getClassName();
            if ("loadAd".equalsIgnoreCase(stackTraceElement.getMethodName()) && (f4667b.equalsIgnoreCase(className2) || f4668c.equalsIgnoreCase(className2) || f4669d.equalsIgnoreCase(className2) || f4670e.equalsIgnoreCase(className2) || f4671f.equalsIgnoreCase(className2) || f4672g.equalsIgnoreCase(className2))) {
                className = stackTraceElementArr[i + 1].getClassName();
                break;
            }
        }
        className = null;
        if (str != null) {
            CharSequence a = m6170a(str, ".", 3);
            if (!(className == null || className.contains(a))) {
                return className;
            }
        }
        return null;
    }

    public void m6172a(Context context, @Nullable String str, String str2, Bundle bundle, boolean z) {
        m6173a(context, str, str2, bundle, z, new 1(this));
    }

    public void m6173a(Context context, @Nullable String str, String str2, Bundle bundle, boolean z, b bVar) {
        if (z) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                applicationContext = context;
            }
            bundle.putString("os", VERSION.RELEASE);
            bundle.putString("api", String.valueOf(VERSION.SDK_INT));
            bundle.putString("appid", applicationContext.getPackageName());
            if (str == null) {
                str = zzc.zzqV().zzae(context) + "." + 9080000;
            }
            bundle.putString("js", str);
        }
        Builder appendQueryParameter = new Builder().scheme(Constants.SCHEME).path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter(ResponseConstants.ID, str2);
        for (String str3 : bundle.keySet()) {
            appendQueryParameter.appendQueryParameter(str3, bundle.getString(str3));
        }
        bVar.a(appendQueryParameter.toString());
    }

    public void m6174a(ViewGroup viewGroup, AdSizeParcel adSizeParcel, String str) {
        m6165a(viewGroup, adSizeParcel, str, (int) ViewCompat.MEASURED_STATE_MASK, -1);
    }

    public void m6175a(ViewGroup viewGroup, AdSizeParcel adSizeParcel, String str, String str2) {
        C1129c.m6192d(str2);
        m6165a(viewGroup, adSizeParcel, str, (int) SupportMenu.CATEGORY_MASK, (int) ViewCompat.MEASURED_STATE_MASK);
    }

    public void m6176a(boolean z, HttpURLConnection httpURLConnection, @Nullable String str) {
        httpURLConnection.setConnectTimeout(Constants.SOCKET_TIMEOUT);
        httpURLConnection.setInstanceFollowRedirects(z);
        httpURLConnection.setReadTimeout(Constants.SOCKET_TIMEOUT);
        if (str != null) {
            httpURLConnection.setRequestProperty("User-Agent", str);
        }
        httpURLConnection.setUseCaches(false);
    }

    public boolean m6177a() {
        return Build.DEVICE.startsWith("generic");
    }

    public int m6178b(Context context, int i) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return m6179b(displayMetrics, i);
    }

    public int m6179b(DisplayMetrics displayMetrics, int i) {
        return Math.round(((float) i) / displayMetrics.density);
    }

    public boolean m6180b() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public boolean m6181b(Context context) {
        return zzc.zzqV().isGooglePlayServicesAvailable(context) == 0;
    }

    public boolean m6182c(Context context) {
        if (context.getResources().getConfiguration().orientation != 2) {
            return false;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return ((int) (((float) displayMetrics.heightPixels) / displayMetrics.density)) < 600;
    }

    public boolean m6183d(Context context) {
        int i;
        int i2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (zzs.zzvc()) {
            defaultDisplay.getRealMetrics(displayMetrics);
            i = displayMetrics.heightPixels;
            i2 = displayMetrics.widthPixels;
        } else {
            try {
                i = ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                i2 = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
            } catch (Exception e) {
                return false;
            }
        }
        defaultDisplay.getMetrics(displayMetrics);
        boolean z = displayMetrics.heightPixels == i && displayMetrics.widthPixels == i2;
        return z;
    }

    public int m6184e(Context context) {
        int identifier = context.getResources().getIdentifier("navigation_bar_width", "dimen", AppBuild.ANDROID_PLATFORM);
        return identifier > 0 ? context.getResources().getDimensionPixelSize(identifier) : 0;
    }
}
