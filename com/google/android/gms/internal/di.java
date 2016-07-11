package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.etsy.android.lib.models.finds.FindsModule;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.common.util.zzs;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(14)
@jw
public class di extends Thread {
    private boolean f4768a;
    private boolean f4769b;
    private boolean f4770c;
    private final Object f4771d;
    private final zzcf f4772e;
    private final dh f4773f;
    private final jv f4774g;
    private final int f4775h;
    private final int f4776i;
    private final int f4777j;
    private final int f4778k;
    private final int f4779l;

    public di(zzcf com_google_android_gms_internal_zzcf, dh dhVar, jv jvVar) {
        this.f4768a = false;
        this.f4769b = false;
        this.f4770c = false;
        this.f4772e = com_google_android_gms_internal_zzcf;
        this.f4773f = dhVar;
        this.f4774g = jvVar;
        this.f4771d = new Object();
        this.f4776i = ((Integer) dz.f4812L.m6433c()).intValue();
        this.f4777j = ((Integer) dz.f4813M.m6433c()).intValue();
        this.f4778k = ((Integer) dz.f4814N.m6433c()).intValue();
        this.f4779l = ((Integer) dz.f4815O.m6433c()).intValue();
        this.f4775h = ((Integer) dz.f4816P.m6433c()).intValue();
        setName("ContentFetchTask");
    }

    dj m6380a(View view, dg dgVar) {
        int i = 0;
        if (view == null) {
            return new dj(this, 0, 0);
        }
        boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            CharSequence text = ((TextView) view).getText();
            if (TextUtils.isEmpty(text)) {
                return new dj(this, 0, 0);
            }
            dgVar.m6368b(text.toString(), globalVisibleRect);
            return new dj(this, 1, 0);
        } else if ((view instanceof WebView) && !(view instanceof no)) {
            dgVar.m6372f();
            return m6387a((WebView) view, dgVar, globalVisibleRect) ? new dj(this, 0, 1) : new dj(this, 0, 0);
        } else if (!(view instanceof ViewGroup)) {
            return new dj(this, 0, 0);
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            int i2 = 0;
            int i3 = 0;
            while (i < viewGroup.getChildCount()) {
                dj a = m6380a(viewGroup.getChildAt(i), dgVar);
                i3 += a.f4780a;
                i2 += a.f4781b;
                i++;
            }
            return new dj(this, i3, i2);
        }
    }

    public void m6381a() {
        synchronized (this.f4771d) {
            if (this.f4768a) {
                C1129c.m6185a("Content hash thread already started, quiting...");
                return;
            }
            this.f4768a = true;
            start();
        }
    }

    void m6382a(Activity activity) {
        if (activity != null) {
            View view = null;
            if (!(activity.getWindow() == null || activity.getWindow().getDecorView() == null)) {
                view = activity.getWindow().getDecorView().findViewById(16908290);
            }
            if (view != null) {
                m6386a(view);
            }
        }
    }

    void m6383a(dg dgVar, WebView webView, String str, boolean z) {
        dgVar.m6371e();
        try {
            if (!TextUtils.isEmpty(str)) {
                String optString = new JSONObject(str).optString(FindsModule.FIELD_TEXT);
                if (TextUtils.isEmpty(webView.getTitle())) {
                    dgVar.m6365a(optString, z);
                } else {
                    String valueOf = String.valueOf(webView.getTitle());
                    dgVar.m6365a(new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(optString).length()).append(valueOf).append("\n").append(optString).toString(), z);
                }
            }
            if (dgVar.m6366a()) {
                this.f4773f.m6378b(dgVar);
            }
        } catch (JSONException e) {
            C1129c.m6185a("Json string may be malformed.");
        } catch (Throwable th) {
            C1129c.m6186a("Failed to get webview content.", th);
            this.f4774g.m6884a(th, true);
        }
    }

    boolean m6384a(RunningAppProcessInfo runningAppProcessInfo) {
        return runningAppProcessInfo.importance == 100;
    }

    boolean m6385a(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        return powerManager == null ? false : powerManager.isScreenOn();
    }

    boolean m6386a(View view) {
        if (view == null) {
            return false;
        }
        view.post(new 1(this, view));
        return true;
    }

    @TargetApi(19)
    boolean m6387a(WebView webView, dg dgVar, boolean z) {
        if (!zzs.zzve()) {
            return false;
        }
        dgVar.m6372f();
        webView.post(new 2(this, dgVar, webView, z));
        return true;
    }

    void m6388b(View view) {
        try {
            dg dgVar = new dg(this.f4776i, this.f4777j, this.f4778k, this.f4779l);
            dj a = m6380a(view, dgVar);
            dgVar.m6373g();
            if (a.f4780a != 0 || a.f4781b != 0) {
                if (a.f4781b != 0 || dgVar.m6375i() != 0) {
                    if (a.f4781b != 0 || !this.f4773f.m6377a(dgVar)) {
                        this.f4773f.m6379c(dgVar);
                    }
                }
            }
        } catch (Throwable e) {
            C1129c.m6189b("Exception in fetchContentOnUIThread", e);
            this.f4774g.m6884a(e, true);
        }
    }

    boolean m6389b() {
        try {
            Context context = this.f4772e.getContext();
            if (context == null) {
                return false;
            }
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
                    if (m6384a(runningAppProcessInfo) && !keyguardManager.inKeyguardRestrictedInputMode() && m6385a(context)) {
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

    public dg m6390c() {
        return this.f4773f.m6376a();
    }

    public void m6391d() {
        synchronized (this.f4771d) {
            this.f4769b = false;
            this.f4771d.notifyAll();
            C1129c.m6185a("ContentFetchThread: wakeup");
        }
    }

    public void m6392e() {
        synchronized (this.f4771d) {
            this.f4769b = true;
            C1129c.m6185a("ContentFetchThread: paused, mPause = " + this.f4769b);
        }
    }

    public boolean m6393f() {
        return this.f4769b;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r3 = this;
    L_0x0000:
        r0 = r3.m6389b();	 Catch:{ Throwable -> 0x0036 }
        if (r0 == 0) goto L_0x0043;
    L_0x0006:
        r0 = r3.f4772e;	 Catch:{ Throwable -> 0x0036 }
        r0 = r0.getActivity();	 Catch:{ Throwable -> 0x0036 }
        if (r0 != 0) goto L_0x0032;
    L_0x000e:
        r0 = "ContentFetchThread: no activity. Sleeping.";
        com.google.android.gms.ads.internal.util.client.C1129c.m6185a(r0);	 Catch:{ Throwable -> 0x0036 }
        r3.m6392e();	 Catch:{ Throwable -> 0x0036 }
    L_0x0016:
        r0 = r3.f4775h;	 Catch:{ Throwable -> 0x0036 }
        r0 = r0 * 1000;
        r0 = (long) r0;	 Catch:{ Throwable -> 0x0036 }
        java.lang.Thread.sleep(r0);	 Catch:{ Throwable -> 0x0036 }
    L_0x001e:
        r1 = r3.f4771d;
        monitor-enter(r1);
    L_0x0021:
        r0 = r3.f4769b;	 Catch:{ all -> 0x004e }
        if (r0 == 0) goto L_0x004c;
    L_0x0025:
        r0 = "ContentFetchTask: waiting";
        com.google.android.gms.ads.internal.util.client.C1129c.m6185a(r0);	 Catch:{ InterruptedException -> 0x0030 }
        r0 = r3.f4771d;	 Catch:{ InterruptedException -> 0x0030 }
        r0.wait();	 Catch:{ InterruptedException -> 0x0030 }
        goto L_0x0021;
    L_0x0030:
        r0 = move-exception;
        goto L_0x0021;
    L_0x0032:
        r3.m6382a(r0);	 Catch:{ Throwable -> 0x0036 }
        goto L_0x0016;
    L_0x0036:
        r0 = move-exception;
        r1 = "Error in ContentFetchTask";
        com.google.android.gms.ads.internal.util.client.C1129c.m6189b(r1, r0);
        r1 = r3.f4774g;
        r2 = 1;
        r1.m6884a(r0, r2);
        goto L_0x001e;
    L_0x0043:
        r0 = "ContentFetchTask: sleeping";
        com.google.android.gms.ads.internal.util.client.C1129c.m6185a(r0);	 Catch:{ Throwable -> 0x0036 }
        r3.m6392e();	 Catch:{ Throwable -> 0x0036 }
        goto L_0x0016;
    L_0x004c:
        monitor-exit(r1);	 Catch:{ all -> 0x004e }
        goto L_0x0000;
    L_0x004e:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x004e }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.di.run():void");
    }
}
