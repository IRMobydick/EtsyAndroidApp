package com.facebook;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Base64;
import com.adjust.sdk.Constants;
import com.facebook.internal.BoltsMeasurementEventListener;
import com.facebook.internal.ah;
import com.facebook.internal.aq;
import com.facebook.internal.av;
import com.facebook.l.1;
import com.facebook.l.2;
import com.facebook.l.3;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: com.facebook.l */
public final class FacebookSdk {
    private static final String f4315a;
    private static final HashSet<LoggingBehavior> f4316b;
    private static volatile Executor f4317c;
    private static volatile String f4318d;
    private static volatile String f4319e;
    private static volatile String f4320f;
    private static volatile int f4321g;
    private static volatile String f4322h;
    private static AtomicLong f4323i;
    private static volatile boolean f4324j;
    private static boolean f4325k;
    private static File f4326l;
    private static Context f4327m;
    private static int f4328n;
    private static final Object f4329o;
    private static final BlockingQueue<Runnable> f4330p;
    private static final ThreadFactory f4331q;
    private static Boolean f4332r;

    static {
        f4315a = FacebookSdk.class.getCanonicalName();
        f4316b = new HashSet(Arrays.asList(new LoggingBehavior[]{LoggingBehavior.DEVELOPER_ERRORS}));
        f4322h = "facebook.com";
        f4323i = new AtomicLong(65536);
        f4324j = false;
        f4325k = false;
        f4328n = 64206;
        f4329o = new Object();
        f4330p = new LinkedBlockingQueue(10);
        f4331q = new 1();
        f4332r = Boolean.valueOf(false);
    }

    public static synchronized void m5771a(Context context) {
        synchronized (FacebookSdk.class) {
            if (!f4332r.booleanValue()) {
                av.a(context, "applicationContext");
                av.b(context, false);
                av.a(context, false);
                f4327m = context.getApplicationContext();
                FacebookSdk.m5779c(f4327m);
                aq.a(f4327m, f4318d);
                ah.m5759b();
                BoltsMeasurementEventListener.getInstance(f4327m);
                f4326l = f4327m.getCacheDir();
                FacebookSdk.m5782d().execute(new FutureTask(new 2()));
                f4332r = Boolean.valueOf(true);
            }
        }
    }

    public static synchronized boolean m5773a() {
        boolean booleanValue;
        synchronized (FacebookSdk.class) {
            booleanValue = f4332r.booleanValue();
        }
        return booleanValue;
    }

    public static boolean m5774a(LoggingBehavior loggingBehavior) {
        boolean z;
        synchronized (f4316b) {
            z = FacebookSdk.m5776b() && f4316b.contains(loggingBehavior);
        }
        return z;
    }

    public static boolean m5776b() {
        return f4324j;
    }

    public static boolean m5780c() {
        return f4325k;
    }

    public static Executor m5782d() {
        synchronized (f4329o) {
            if (f4317c == null) {
                Executor n = FacebookSdk.m5792n();
                if (n == null) {
                    n = new ThreadPoolExecutor(5, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS, 1, TimeUnit.SECONDS, f4330p, f4331q);
                }
                f4317c = n;
            }
        }
        return f4317c;
    }

    public static String m5783e() {
        return f4322h;
    }

    public static Context m5784f() {
        av.b();
        return f4327m;
    }

    private static Executor m5792n() {
        try {
            try {
                Object obj = AsyncTask.class.getField("THREAD_POOL_EXECUTOR").get(null);
                if (obj == null) {
                    return null;
                }
                if (obj instanceof Executor) {
                    return (Executor) obj;
                }
                return null;
            } catch (IllegalAccessException e) {
                return null;
            }
        } catch (NoSuchFieldException e2) {
            return null;
        }
    }

    public static void m5772a(Context context, String str) {
        FacebookSdk.m5782d().execute(new 3(context.getApplicationContext(), str));
    }

    static com.facebook.GraphResponse m5775b(android.content.Context r14, java.lang.String r15) {
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.l.b(android.content.Context, java.lang.String):com.facebook.GraphResponse. bs: [B:3:0x0007, B:11:0x005d]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:57)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
        /*
        r12 = 0;
        r1 = 0;
        if (r14 == 0) goto L_0x0007;
    L_0x0005:
        if (r15 != 0) goto L_0x0021;
    L_0x0007:
        r0 = new java.lang.IllegalArgumentException;	 Catch:{ Exception -> 0x000f }
        r2 = "Both context and applicationId must be non-null";	 Catch:{ Exception -> 0x000f }
        r0.<init>(r2);	 Catch:{ Exception -> 0x000f }
        throw r0;	 Catch:{ Exception -> 0x000f }
    L_0x000f:
        r0 = move-exception;
        r2 = r0;
        r0 = "Facebook-publish";
        com.facebook.internal.aq.a(r0, r2);
        r0 = new com.facebook.GraphResponse;
        r3 = new com.facebook.FacebookRequestError;
        r3.<init>(r1, r2);
        r0.<init>(r1, r1, r3);
    L_0x0020:
        return r0;
    L_0x0021:
        r0 = com.facebook.internal.b.a(r14);	 Catch:{ Exception -> 0x000f }
        r2 = "com.facebook.sdk.attributionTracking";	 Catch:{ Exception -> 0x000f }
        r3 = 0;	 Catch:{ Exception -> 0x000f }
        r2 = r14.getSharedPreferences(r2, r3);	 Catch:{ Exception -> 0x000f }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x000f }
        r3.<init>();	 Catch:{ Exception -> 0x000f }
        r3 = r3.append(r15);	 Catch:{ Exception -> 0x000f }
        r4 = "ping";	 Catch:{ Exception -> 0x000f }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x000f }
        r3 = r3.toString();	 Catch:{ Exception -> 0x000f }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x000f }
        r4.<init>();	 Catch:{ Exception -> 0x000f }
        r4 = r4.append(r15);	 Catch:{ Exception -> 0x000f }
        r5 = "json";	 Catch:{ Exception -> 0x000f }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x000f }
        r4 = r4.toString();	 Catch:{ Exception -> 0x000f }
        r6 = 0;	 Catch:{ Exception -> 0x000f }
        r6 = r2.getLong(r3, r6);	 Catch:{ Exception -> 0x000f }
        r5 = 0;	 Catch:{ Exception -> 0x000f }
        r5 = r2.getString(r4, r5);	 Catch:{ Exception -> 0x000f }
        r8 = com.facebook.internal.AppEventsLoggerUtility.GraphAPIActivityType.MOBILE_INSTALL_EVENT;	 Catch:{ JSONException -> 0x00a6 }
        r9 = com.facebook.appevents.AppEventsLogger.d(r14);	 Catch:{ JSONException -> 0x00a6 }
        r10 = com.facebook.FacebookSdk.m5778b(r14);	 Catch:{ JSONException -> 0x00a6 }
        r0 = com.facebook.internal.AppEventsLoggerUtility.a(r8, r0, r9, r10, r14);	 Catch:{ JSONException -> 0x00a6 }
        r8 = "%s/activities";	 Catch:{ Exception -> 0x000f }
        r9 = 1;	 Catch:{ Exception -> 0x000f }
        r9 = new java.lang.Object[r9];	 Catch:{ Exception -> 0x000f }
        r10 = 0;	 Catch:{ Exception -> 0x000f }
        r9[r10] = r15;	 Catch:{ Exception -> 0x000f }
        r8 = java.lang.String.format(r8, r9);	 Catch:{ Exception -> 0x000f }
        r9 = 0;	 Catch:{ Exception -> 0x000f }
        r10 = 0;	 Catch:{ Exception -> 0x000f }
        r8 = com.facebook.GraphRequest.a(r9, r8, r0, r10);	 Catch:{ Exception -> 0x000f }
        r0 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r0 == 0) goto L_0x00bc;
    L_0x0081:
        if (r5 == 0) goto L_0x00e1;
    L_0x0083:
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00af }
        r0.<init>(r5);	 Catch:{ JSONException -> 0x00af }
    L_0x0088:
        r2 = r0;
    L_0x0089:
        if (r2 != 0) goto L_0x00b2;
    L_0x008b:
        r0 = "true";	 Catch:{ Exception -> 0x000f }
        r2 = 0;	 Catch:{ Exception -> 0x000f }
        r3 = new com.facebook.s;	 Catch:{ Exception -> 0x000f }
        r4 = 1;	 Catch:{ Exception -> 0x000f }
        r4 = new com.facebook.GraphRequest[r4];	 Catch:{ Exception -> 0x000f }
        r5 = 0;	 Catch:{ Exception -> 0x000f }
        r4[r5] = r8;	 Catch:{ Exception -> 0x000f }
        r3.<init>(r4);	 Catch:{ Exception -> 0x000f }
        r0 = com.facebook.GraphResponse.a(r0, r2, r3);	 Catch:{ Exception -> 0x000f }
        r2 = 0;	 Catch:{ Exception -> 0x000f }
        r0 = r0.get(r2);	 Catch:{ Exception -> 0x000f }
        r0 = (com.facebook.GraphResponse) r0;	 Catch:{ Exception -> 0x000f }
        goto L_0x0020;	 Catch:{ Exception -> 0x000f }
    L_0x00a6:
        r0 = move-exception;	 Catch:{ Exception -> 0x000f }
        r2 = new com.facebook.FacebookException;	 Catch:{ Exception -> 0x000f }
        r3 = "An error occurred while publishing install.";	 Catch:{ Exception -> 0x000f }
        r2.<init>(r3, r0);	 Catch:{ Exception -> 0x000f }
        throw r2;	 Catch:{ Exception -> 0x000f }
    L_0x00af:
        r0 = move-exception;	 Catch:{ Exception -> 0x000f }
        r2 = r1;	 Catch:{ Exception -> 0x000f }
        goto L_0x0089;	 Catch:{ Exception -> 0x000f }
    L_0x00b2:
        r0 = new com.facebook.GraphResponse;	 Catch:{ Exception -> 0x000f }
        r3 = 0;	 Catch:{ Exception -> 0x000f }
        r4 = 0;	 Catch:{ Exception -> 0x000f }
        r5 = 0;	 Catch:{ Exception -> 0x000f }
        r0.<init>(r3, r4, r5, r2);	 Catch:{ Exception -> 0x000f }
        goto L_0x0020;	 Catch:{ Exception -> 0x000f }
    L_0x00bc:
        r0 = r8.g();	 Catch:{ Exception -> 0x000f }
        r2 = r2.edit();	 Catch:{ Exception -> 0x000f }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x000f }
        r2.putLong(r3, r6);	 Catch:{ Exception -> 0x000f }
        r3 = r0.b();	 Catch:{ Exception -> 0x000f }
        if (r3 == 0) goto L_0x00dc;	 Catch:{ Exception -> 0x000f }
    L_0x00d1:
        r3 = r0.b();	 Catch:{ Exception -> 0x000f }
        r3 = r3.toString();	 Catch:{ Exception -> 0x000f }
        r2.putString(r4, r3);	 Catch:{ Exception -> 0x000f }
    L_0x00dc:
        r2.apply();	 Catch:{ Exception -> 0x000f }
        goto L_0x0020;
    L_0x00e1:
        r0 = r1;
        goto L_0x0088;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.l.b(android.content.Context, java.lang.String):com.facebook.GraphResponse");
    }

    public static boolean m5778b(Context context) {
        av.b();
        return context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getBoolean("limitEventUsage", false);
    }

    public static long m5785g() {
        av.b();
        return f4323i.get();
    }

    static void m5779c(Context context) {
        if (context != null) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
                if (applicationInfo != null && applicationInfo.metaData != null) {
                    if (f4318d == null) {
                        Object obj = applicationInfo.metaData.get("com.facebook.sdk.ApplicationId");
                        if (obj instanceof String) {
                            f4318d = (String) obj;
                        } else if (obj instanceof Integer) {
                            f4318d = obj.toString();
                        }
                    }
                    if (f4319e == null) {
                        f4319e = applicationInfo.metaData.getString("com.facebook.sdk.ApplicationName");
                    }
                    if (f4320f == null) {
                        f4320f = applicationInfo.metaData.getString("com.facebook.sdk.ClientToken");
                    }
                    if (f4321g == 0) {
                        FacebookSdk.m5770a(applicationInfo.metaData.getInt("com.facebook.sdk.WebDialogTheme"));
                    }
                }
            } catch (NameNotFoundException e) {
            }
        }
    }

    public static String m5781d(Context context) {
        String str = null;
        av.b();
        if (context == null) {
            return str;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return str;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 64);
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr == null || signatureArr.length == 0) {
                return str;
            }
            try {
                MessageDigest instance = MessageDigest.getInstance(Constants.SHA1);
                instance.update(packageInfo.signatures[0].toByteArray());
                return Base64.encodeToString(instance.digest(), 9);
            } catch (NoSuchAlgorithmException e) {
                return str;
            }
        } catch (NameNotFoundException e2) {
            return str;
        }
    }

    public static String m5786h() {
        av.b();
        return f4318d;
    }

    public static String m5787i() {
        av.b();
        return f4319e;
    }

    public static String m5788j() {
        av.b();
        return f4320f;
    }

    public static int m5789k() {
        av.b();
        return f4321g;
    }

    public static void m5770a(int i) {
        f4321g = i;
    }

    public static File m5790l() {
        av.b();
        return f4326l;
    }

    public static int m5791m() {
        av.b();
        return f4328n;
    }

    public static boolean m5777b(int i) {
        return i >= f4328n && i < f4328n + 100;
    }
}
