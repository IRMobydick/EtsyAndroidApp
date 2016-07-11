package com.google.android.gms.internal;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog.Builder;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Debug;
import android.os.Debug.MemoryInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import com.adjust.sdk.Constants;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.uikit.view.FullImageView;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.ClientApi;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzo;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@jw
public class lt {
    public static final Handler f5423a;
    private final Object f5424b;
    private boolean f5425c;
    private String f5426d;
    private boolean f5427e;
    private gx f5428f;

    static {
        f5423a = new zzjx(Looper.getMainLooper());
    }

    public lt() {
        this.f5424b = new Object();
        this.f5425c = true;
        this.f5427e = false;
    }

    private JSONArray m7076a(Collection<?> collection) {
        JSONArray jSONArray = new JSONArray();
        for (Object a : collection) {
            m7078a(jSONArray, a);
        }
        return jSONArray;
    }

    private JSONObject m7077a(Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            m7079a(jSONObject, str, bundle.get(str));
        }
        return jSONObject;
    }

    private void m7078a(JSONArray jSONArray, Object obj) {
        if (obj instanceof Bundle) {
            jSONArray.put(m7077a((Bundle) obj));
        } else if (obj instanceof Map) {
            jSONArray.put(m7098a((Map) obj));
        } else if (obj instanceof Collection) {
            jSONArray.put(m7076a((Collection) obj));
        } else if (obj instanceof Object[]) {
            jSONArray.put(m7097a((Object[]) obj));
        } else {
            jSONArray.put(obj);
        }
    }

    private void m7079a(JSONObject jSONObject, String str, Object obj) {
        if (obj instanceof Bundle) {
            jSONObject.put(str, m7077a((Bundle) obj));
        } else if (obj instanceof Map) {
            jSONObject.put(str, m7098a((Map) obj));
        } else if (obj instanceof Collection) {
            if (str == null) {
                str = "null";
            }
            jSONObject.put(str, m7076a((Collection) obj));
        } else if (obj instanceof Object[]) {
            jSONObject.put(str, m7076a(Arrays.asList((Object[]) obj)));
        } else {
            jSONObject.put(str, obj);
        }
    }

    private boolean m7080a(KeyguardManager keyguardManager) {
        return keyguardManager == null ? false : keyguardManager.inKeyguardRestrictedInputMode();
    }

    private boolean m7081a(PowerManager powerManager) {
        return powerManager == null || powerManager.isScreenOn();
    }

    private Bitmap m7083c(@NonNull View view) {
        try {
            int width = view.getWidth();
            int height = view.getHeight();
            if (width == 0 || height == 0) {
                C1129c.m6192d("Width or height of view is zero");
                return null;
            }
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.RGB_565);
            Canvas canvas = new Canvas(createBitmap);
            view.layout(0, 0, width, height);
            view.draw(canvas);
            return createBitmap;
        } catch (Throwable e) {
            C1129c.m6189b("Fail to capture the webview", e);
            return null;
        }
    }

    private Bitmap m7084d(@NonNull View view) {
        Bitmap drawingCache;
        Throwable e;
        try {
            boolean isDrawingCacheEnabled = view.isDrawingCacheEnabled();
            view.setDrawingCacheEnabled(true);
            drawingCache = view.getDrawingCache();
            drawingCache = drawingCache != null ? Bitmap.createBitmap(drawingCache) : null;
            try {
                view.setDrawingCacheEnabled(isDrawingCacheEnabled);
            } catch (RuntimeException e2) {
                e = e2;
                C1129c.m6189b("Fail to capture the web view", e);
                return drawingCache;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            drawingCache = null;
            e = th;
            C1129c.m6189b("Fail to capture the web view", e);
            return drawingCache;
        }
        return drawingCache;
    }

    private boolean m7085m(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        return powerManager == null ? false : powerManager.isScreenOn();
    }

    public Bitmap m7086a(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return createBitmap;
    }

    public DisplayMetrics m7087a(WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public PopupWindow m7088a(View view, int i, int i2, boolean z) {
        return new PopupWindow(view, i, i2, z);
    }

    public gx m7089a(Context context, VersionInfoParcel versionInfoParcel) {
        gx gxVar;
        synchronized (this.f5424b) {
            if (this.f5428f == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                this.f5428f = new gx(context, versionInfoParcel, (String) dz.f4828b.m6433c());
            }
            gxVar = this.f5428f;
        }
        return gxVar;
    }

    public String m7090a(Context context, View view, AdSizeParcel adSizeParcel) {
        String str = null;
        if (((Boolean) dz.aa.m6433c()).booleanValue()) {
            try {
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(ResponseConstants.WIDTH, adSizeParcel.width);
                jSONObject2.put(ResponseConstants.HEIGHT, adSizeParcel.height);
                jSONObject.put("size", jSONObject2);
                jSONObject.put("activity", m7134f(context));
                if (!adSizeParcel.zzvt) {
                    JSONArray jSONArray = new JSONArray();
                    while (view != null) {
                        ViewParent parent = view.getParent();
                        if (parent != null) {
                            int i = -1;
                            if (parent instanceof ViewGroup) {
                                i = ((ViewGroup) parent).indexOfChild(view);
                            }
                            JSONObject jSONObject3 = new JSONObject();
                            jSONObject3.put(FindsModule.FIELD_TYPE, parent.getClass().getName());
                            jSONObject3.put("index_of_child", i);
                            jSONArray.put(jSONObject3);
                        }
                        View view2 = (parent == null || !(parent instanceof View)) ? null : (View) parent;
                        view = view2;
                    }
                    if (jSONArray.length() > 0) {
                        jSONObject.put("parents", jSONArray);
                    }
                }
                str = jSONObject.toString();
            } catch (Throwable e) {
                C1129c.m6193d("Fail to get view hierarchy json", e);
            }
        }
        return str;
    }

    public String m7091a(Context context, bu buVar, String str) {
        if (buVar != null) {
            try {
                Uri parse = Uri.parse(str);
                if (buVar.d(parse)) {
                    parse = buVar.b(parse, context);
                }
                str = parse.toString();
            } catch (Exception e) {
            }
        }
        return str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String m7092a(android.content.Context r6, java.lang.String r7) {
        /*
        r5 = this;
        r1 = r5.f5424b;
        monitor-enter(r1);
        r0 = r5.f5426d;	 Catch:{ all -> 0x005b }
        if (r0 == 0) goto L_0x000b;
    L_0x0007:
        r0 = r5.f5426d;	 Catch:{ all -> 0x005b }
        monitor-exit(r1);	 Catch:{ all -> 0x005b }
    L_0x000a:
        return r0;
    L_0x000b:
        r0 = com.google.android.gms.ads.internal.C1101o.m6043g();	 Catch:{ Exception -> 0x00af }
        r0 = r0.m7149a(r6);	 Catch:{ Exception -> 0x00af }
        r5.f5426d = r0;	 Catch:{ Exception -> 0x00af }
    L_0x0015:
        r0 = r5.f5426d;	 Catch:{ all -> 0x005b }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x005b }
        if (r0 == 0) goto L_0x006a;
    L_0x001d:
        r0 = com.google.android.gms.ads.internal.client.C1089r.m5951a();	 Catch:{ all -> 0x005b }
        r0 = r0.m6180b();	 Catch:{ all -> 0x005b }
        if (r0 != 0) goto L_0x0064;
    L_0x0027:
        r0 = 0;
        r5.f5426d = r0;	 Catch:{ all -> 0x005b }
        r0 = f5423a;	 Catch:{ all -> 0x005b }
        r2 = new com.google.android.gms.internal.lt$2;	 Catch:{ all -> 0x005b }
        r2.<init>(r5, r6);	 Catch:{ all -> 0x005b }
        r0.post(r2);	 Catch:{ all -> 0x005b }
    L_0x0034:
        r0 = r5.f5426d;	 Catch:{ all -> 0x005b }
        if (r0 != 0) goto L_0x006a;
    L_0x0038:
        r0 = r5.f5424b;	 Catch:{ InterruptedException -> 0x003e }
        r0.wait();	 Catch:{ InterruptedException -> 0x003e }
        goto L_0x0034;
    L_0x003e:
        r0 = move-exception;
        r0 = r5.m7120b();	 Catch:{ all -> 0x005b }
        r5.f5426d = r0;	 Catch:{ all -> 0x005b }
        r2 = "Interrupted, use default user agent: ";
        r0 = r5.f5426d;	 Catch:{ all -> 0x005b }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x005b }
        r3 = r0.length();	 Catch:{ all -> 0x005b }
        if (r3 == 0) goto L_0x005e;
    L_0x0053:
        r0 = r2.concat(r0);	 Catch:{ all -> 0x005b }
    L_0x0057:
        com.google.android.gms.ads.internal.util.client.C1129c.m6192d(r0);	 Catch:{ all -> 0x005b }
        goto L_0x0034;
    L_0x005b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x005b }
        throw r0;
    L_0x005e:
        r0 = new java.lang.String;	 Catch:{ all -> 0x005b }
        r0.<init>(r2);	 Catch:{ all -> 0x005b }
        goto L_0x0057;
    L_0x0064:
        r0 = r5.m7126c(r6);	 Catch:{ Exception -> 0x00a7 }
        r5.f5426d = r0;	 Catch:{ Exception -> 0x00a7 }
    L_0x006a:
        r0 = r5.f5426d;	 Catch:{ all -> 0x005b }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x005b }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x005b }
        r3 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x005b }
        r3 = r3.length();	 Catch:{ all -> 0x005b }
        r3 = r3 + 11;
        r4 = java.lang.String.valueOf(r7);	 Catch:{ all -> 0x005b }
        r4 = r4.length();	 Catch:{ all -> 0x005b }
        r3 = r3 + r4;
        r2.<init>(r3);	 Catch:{ all -> 0x005b }
        r0 = r2.append(r0);	 Catch:{ all -> 0x005b }
        r2 = " (Mobile; ";
        r0 = r0.append(r2);	 Catch:{ all -> 0x005b }
        r0 = r0.append(r7);	 Catch:{ all -> 0x005b }
        r2 = ")";
        r0 = r0.append(r2);	 Catch:{ all -> 0x005b }
        r0 = r0.toString();	 Catch:{ all -> 0x005b }
        r5.f5426d = r0;	 Catch:{ all -> 0x005b }
        r0 = r5.f5426d;	 Catch:{ all -> 0x005b }
        monitor-exit(r1);	 Catch:{ all -> 0x005b }
        goto L_0x000a;
    L_0x00a7:
        r0 = move-exception;
        r0 = r5.m7120b();	 Catch:{ all -> 0x005b }
        r5.f5426d = r0;	 Catch:{ all -> 0x005b }
        goto L_0x006a;
    L_0x00af:
        r0 = move-exception;
        goto L_0x0015;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.lt.a(android.content.Context, java.lang.String):java.lang.String");
    }

    public String m7093a(no noVar, String str) {
        return m7091a(noVar.getContext(), noVar.m7264n(), str);
    }

    public String m7094a(InputStreamReader inputStreamReader) {
        StringBuilder stringBuilder = new StringBuilder(AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD);
        char[] cArr = new char[ItemAnimator.FLAG_MOVED];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read == -1) {
                return stringBuilder.toString();
            }
            stringBuilder.append(cArr, 0, read);
        }
    }

    public String m7095a(String str) {
        return Uri.parse(str).buildUpon().query(null).build().toString();
    }

    public Map<String, String> m7096a(Uri uri) {
        if (uri == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String str : C1101o.m6043g().m7151a(uri)) {
            hashMap.put(str, uri.getQueryParameter(str));
        }
        return hashMap;
    }

    JSONArray m7097a(Object[] objArr) {
        JSONArray jSONArray = new JSONArray();
        for (Object a : objArr) {
            m7078a(jSONArray, a);
        }
        return jSONArray;
    }

    public JSONObject m7098a(Map<String, ?> map) {
        String valueOf;
        try {
            JSONObject jSONObject = new JSONObject();
            for (String valueOf2 : map.keySet()) {
                m7079a(jSONObject, valueOf2, map.get(valueOf2));
            }
            return jSONObject;
        } catch (ClassCastException e) {
            String str = "Could not convert map to JSON: ";
            valueOf2 = String.valueOf(e.getMessage());
            throw new JSONException(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
        }
    }

    public void m7099a(Activity activity, OnGlobalLayoutListener onGlobalLayoutListener) {
        Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            window.getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public void m7100a(Activity activity, OnScrollChangedListener onScrollChangedListener) {
        Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            window.getDecorView().getViewTreeObserver().addOnScrollChangedListener(onScrollChangedListener);
        }
    }

    public void m7101a(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (Throwable th) {
            intent.addFlags(268435456);
            context.startActivity(intent);
        }
    }

    public void m7102a(Context context, String str, WebSettings webSettings) {
        webSettings.setUserAgentString(m7092a(context, str));
    }

    public void m7103a(Context context, String str, String str2) {
        List arrayList = new ArrayList();
        arrayList.add(str2);
        m7105a(context, str, arrayList);
    }

    public void m7104a(Context context, @Nullable String str, String str2, Bundle bundle, boolean z) {
        if (z) {
            bundle.putString("device", C1101o.m6041e().m7133e());
            bundle.putString("eids", TextUtils.join(",", dz.m6443a()));
        }
        C1089r.m5951a().m6173a(context, str, str2, bundle, z, new 3(this, context, str));
    }

    public void m7105a(Context context, String str, List<String> list) {
        for (String mqVar : list) {
            Future future = (Future) new mq(context, str, mqVar).zzhs();
        }
    }

    public void m7106a(Context context, String str, boolean z, HttpURLConnection httpURLConnection) {
        m7107a(context, str, z, httpURLConnection, false);
    }

    public void m7107a(Context context, String str, boolean z, HttpURLConnection httpURLConnection, boolean z2) {
        httpURLConnection.setConnectTimeout(Constants.SOCKET_TIMEOUT);
        httpURLConnection.setInstanceFollowRedirects(z);
        httpURLConnection.setReadTimeout(Constants.SOCKET_TIMEOUT);
        httpURLConnection.setRequestProperty("User-Agent", m7092a(context, str));
        httpURLConnection.setUseCaches(z2);
    }

    public void m7108a(Context context, List<String> list) {
        if (!(context instanceof Activity) || TextUtils.isEmpty(bo.a((Activity) context))) {
            return;
        }
        if (list == null) {
            lo.m7056e("Cannot ping urls: empty list.");
        } else if (em.m6484a(context)) {
            em emVar = new em();
            emVar.m6487a(new 1(this, list, emVar, context));
            emVar.m6488b((Activity) context);
        } else {
            lo.m7056e("Cannot ping url because custom tabs is not supported");
        }
    }

    public void m7109a(Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
        } else {
            f5423a.post(runnable);
        }
    }

    public void m7110a(List<String> list, String str) {
        for (String mqVar : list) {
            Future future = (Future) new mq(mqVar, str).zzhs();
        }
    }

    public boolean m7111a() {
        return this.f5425c;
    }

    public boolean m7112a(Context context) {
        Intent intent = new Intent();
        intent.setClassName(context, AdActivity.CLASS_NAME);
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, AccessibilityNodeInfoCompat.ACTION_CUT);
        if (resolveActivity == null || resolveActivity.activityInfo == null) {
            C1129c.m6192d("Could not find com.google.android.gms.ads.AdActivity, please make sure it is declared in AndroidManifest.xml.");
            return false;
        }
        boolean z;
        String str = "com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".";
        if ((resolveActivity.activityInfo.configChanges & 16) == 0) {
            C1129c.m6192d(String.format(str, new Object[]{"keyboard"}));
            z = false;
        } else {
            z = true;
        }
        if ((resolveActivity.activityInfo.configChanges & 32) == 0) {
            C1129c.m6192d(String.format(str, new Object[]{"keyboardHidden"}));
            z = false;
        }
        if ((resolveActivity.activityInfo.configChanges & AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) == 0) {
            C1129c.m6192d(String.format(str, new Object[]{"orientation"}));
            z = false;
        }
        if ((resolveActivity.activityInfo.configChanges & AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY) == 0) {
            C1129c.m6192d(String.format(str, new Object[]{"screenLayout"}));
            z = false;
        }
        if ((resolveActivity.activityInfo.configChanges & AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY) == 0) {
            C1129c.m6192d(String.format(str, new Object[]{"uiMode"}));
            z = false;
        }
        if ((resolveActivity.activityInfo.configChanges & AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT) == 0) {
            C1129c.m6192d(String.format(str, new Object[]{"screenSize"}));
            z = false;
        }
        if ((resolveActivity.activityInfo.configChanges & ItemAnimator.FLAG_MOVED) != 0) {
            return z;
        }
        C1129c.m6192d(String.format(str, new Object[]{"smallestScreenSize"}));
        return false;
    }

    public boolean m7113a(PackageManager packageManager, String str, String str2) {
        return packageManager.checkPermission(str2, str) == 0;
    }

    public boolean m7114a(View view, Context context) {
        KeyguardManager keyguardManager = null;
        Context applicationContext = context.getApplicationContext();
        PowerManager powerManager = applicationContext != null ? (PowerManager) applicationContext.getSystemService("power") : null;
        Object systemService = context.getSystemService("keyguard");
        if (systemService != null && (systemService instanceof KeyguardManager)) {
            keyguardManager = (KeyguardManager) systemService;
        }
        return m7115a(view, powerManager, keyguardManager);
    }

    public boolean m7115a(View view, PowerManager powerManager, KeyguardManager keyguardManager) {
        boolean z = C1101o.m6041e().m7111a() || !m7080a(keyguardManager);
        return view.getVisibility() == 0 && view.isShown() && m7081a(powerManager) && z && (!((Boolean) dz.at.m6433c()).booleanValue() || view.getLocalVisibleRect(new Rect()) || view.getGlobalVisibleRect(new Rect()));
    }

    public boolean m7116a(ClassLoader classLoader, Class<?> cls, String str) {
        boolean z = false;
        try {
            z = cls.isAssignableFrom(Class.forName(str, false, classLoader));
        } catch (Throwable th) {
        }
        return z;
    }

    public int[] m7117a(Activity activity) {
        Window window = activity.getWindow();
        if (window == null || window.findViewById(16908290) == null) {
            return m7135f();
        }
        return new int[]{window.findViewById(16908290).getWidth(), window.findViewById(16908290).getHeight()};
    }

    public int m7118b(@Nullable View view) {
        if (view == null) {
            return -1;
        }
        ViewParent parent = view.getParent();
        while (parent != null && !(parent instanceof AdapterView)) {
            parent = parent.getParent();
        }
        return parent == null ? -1 : ((AdapterView) parent).getPositionForView(view);
    }

    public int m7119b(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            String valueOf = String.valueOf(e);
            C1129c.m6192d(new StringBuilder(String.valueOf(valueOf).length() + 22).append("Could not parse value:").append(valueOf).toString());
            return 0;
        }
    }

    String m7120b() {
        StringBuffer stringBuffer = new StringBuffer(AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
        stringBuffer.append("Mozilla/5.0 (Linux; U; Android");
        if (VERSION.RELEASE != null) {
            stringBuffer.append(" ").append(VERSION.RELEASE);
        }
        stringBuffer.append("; ").append(Locale.getDefault());
        if (Build.DEVICE != null) {
            stringBuffer.append("; ").append(Build.DEVICE);
            if (Build.DISPLAY != null) {
                stringBuffer.append(" Build/").append(Build.DISPLAY);
            }
        }
        stringBuffer.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return stringBuffer.toString();
    }

    public void m7121b(Activity activity, OnScrollChangedListener onScrollChangedListener) {
        Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            window.getDecorView().getViewTreeObserver().removeOnScrollChangedListener(onScrollChangedListener);
        }
    }

    public void m7122b(Context context, String str, String str2, Bundle bundle, boolean z) {
        if (((Boolean) dz.ax.m6433c()).booleanValue()) {
            m7104a(context, str, str2, bundle, z);
        }
    }

    public boolean m7123b(Context context) {
        if (this.f5427e) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        context.getApplicationContext().registerReceiver(new lu(this, null), intentFilter);
        this.f5427e = true;
        return true;
    }

    public int[] m7124b(Activity activity) {
        int[] a = m7117a(activity);
        return new int[]{C1089r.m5951a().m6178b((Context) activity, a[0]), C1089r.m5951a().m6178b((Context) activity, a[1])};
    }

    public String m7125c() {
        return UUID.randomUUID().toString();
    }

    protected String m7126c(Context context) {
        return new WebView(context).getSettings().getUserAgentString();
    }

    public boolean m7127c(String str) {
        return TextUtils.isEmpty(str) ? false : str.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)");
    }

    public int[] m7128c(Activity activity) {
        Window window = activity.getWindow();
        if (window == null || window.findViewById(16908290) == null) {
            return m7135f();
        }
        return new int[]{window.findViewById(16908290).getTop(), window.findViewById(16908290).getBottom()};
    }

    public Builder m7129d(Context context) {
        return new Builder(context);
    }

    public String m7130d() {
        UUID randomUUID = UUID.randomUUID();
        byte[] toByteArray = BigInteger.valueOf(randomUUID.getLeastSignificantBits()).toByteArray();
        byte[] toByteArray2 = BigInteger.valueOf(randomUUID.getMostSignificantBits()).toByteArray();
        String bigInteger = new BigInteger(1, toByteArray).toString();
        for (int i = 0; i < 2; i++) {
            try {
                MessageDigest instance = MessageDigest.getInstance(Constants.MD5);
                instance.update(toByteArray);
                instance.update(toByteArray2);
                Object obj = new byte[8];
                System.arraycopy(instance.digest(), 0, obj, 0, 8);
                bigInteger = new BigInteger(1, obj).toString();
            } catch (NoSuchAlgorithmException e) {
            }
        }
        return bigInteger;
    }

    public int[] m7131d(Activity activity) {
        int[] c = m7128c(activity);
        return new int[]{C1089r.m5951a().m6178b((Context) activity, c[0]), C1089r.m5951a().m6178b((Context) activity, c[1])};
    }

    public dr m7132e(Context context) {
        return new dr(context);
    }

    public String m7133e() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        return str2.startsWith(str) ? str2 : new StringBuilder((String.valueOf(str).length() + 1) + String.valueOf(str2).length()).append(str).append(" ").append(str2).toString();
    }

    public String m7134f(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                return null;
            }
            List runningTasks = activityManager.getRunningTasks(1);
            if (!(runningTasks == null || runningTasks.isEmpty())) {
                RunningTaskInfo runningTaskInfo = (RunningTaskInfo) runningTasks.get(0);
                if (!(runningTaskInfo == null || runningTaskInfo.topActivity == null)) {
                    return runningTaskInfo.topActivity.getClassName();
                }
            }
            return null;
        } catch (Exception e) {
        }
    }

    protected int[] m7135f() {
        return new int[]{0, 0};
    }

    public float m7136g() {
        zzo a = C1101o.m6057u().m6027a();
        return (a == null || !a.zzbT()) ? FullImageView.ASPECT_RATIO_SQUARE : a.zzbS();
    }

    public boolean m7137g(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null || keyguardManager == null) {
                return false;
            }
            List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    if (runningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode() && m7085m(context)) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    public Bitmap m7138h(Context context) {
        if (!(context instanceof Activity)) {
            return null;
        }
        Bitmap d;
        try {
            if (((Boolean) dz.bc.m6433c()).booleanValue()) {
                Window window = ((Activity) context).getWindow();
                if (window != null) {
                    d = m7084d(window.getDecorView().getRootView());
                }
                d = null;
            } else {
                d = m7083c(((Activity) context).getWindow().getDecorView());
            }
        } catch (Throwable e) {
            C1129c.m6189b("Fail to capture screen shot", e);
        }
        return d;
    }

    public boolean m7139h() {
        zzo a = C1101o.m6057u().m6027a();
        return a != null ? a.zzbU() : false;
    }

    public AudioManager m7140i(Context context) {
        return (AudioManager) context.getSystemService("audio");
    }

    public Bundle m7141i() {
        Bundle bundle = new Bundle();
        try {
            Parcelable memoryInfo = new MemoryInfo();
            Debug.getMemoryInfo(memoryInfo);
            bundle.putParcelable("debug_memory_info", memoryInfo);
            Runtime runtime = Runtime.getRuntime();
            bundle.putLong("runtime_free_memory", runtime.freeMemory());
            bundle.putLong("runtime_max_memory", runtime.maxMemory());
            bundle.putLong("runtime_total_memory", runtime.totalMemory());
        } catch (Throwable e) {
            C1129c.m6193d("Unable to gather memory stats", e);
        }
        return bundle;
    }

    public float m7142j(Context context) {
        AudioManager i = m7140i(context);
        if (i == null) {
            return 0.0f;
        }
        int streamMaxVolume = i.getStreamMaxVolume(3);
        return streamMaxVolume != 0 ? ((float) i.getStreamVolume(3)) / ((float) streamMaxVolume) : 0.0f;
    }

    public int m7143k(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        return applicationInfo == null ? 0 : applicationInfo.targetSdkVersion;
    }

    public boolean m7144l(Context context) {
        try {
            context.getClassLoader().loadClass(ClientApi.class.getName());
            return false;
        } catch (ClassNotFoundException e) {
            return true;
        }
    }
}
