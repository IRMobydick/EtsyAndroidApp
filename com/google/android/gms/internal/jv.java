package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Looper;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@jw
public class jv implements UncaughtExceptionHandler {
    private UncaughtExceptionHandler f5207a;
    private UncaughtExceptionHandler f5208b;
    private Context f5209c;
    private VersionInfoParcel f5210d;

    public jv(Context context, VersionInfoParcel versionInfoParcel, UncaughtExceptionHandler uncaughtExceptionHandler, UncaughtExceptionHandler uncaughtExceptionHandler2) {
        this.f5207a = uncaughtExceptionHandler;
        this.f5208b = uncaughtExceptionHandler2;
        this.f5209c = context;
        this.f5210d = versionInfoParcel;
    }

    public static jv m6880a(Context context, Thread thread, VersionInfoParcel versionInfoParcel) {
        if (context == null || thread == null || versionInfoParcel == null) {
            return null;
        }
        if (!m6881a(context)) {
            return null;
        }
        UncaughtExceptionHandler uncaughtExceptionHandler = thread.getUncaughtExceptionHandler();
        UncaughtExceptionHandler jvVar = new jv(context, versionInfoParcel, uncaughtExceptionHandler, Thread.getDefaultUncaughtExceptionHandler());
        if (uncaughtExceptionHandler != null && (uncaughtExceptionHandler instanceof jv)) {
            return (jv) uncaughtExceptionHandler;
        }
        try {
            thread.setUncaughtExceptionHandler(jvVar);
            return jvVar;
        } catch (Throwable e) {
            C1129c.m6191c("Fail to set UncaughtExceptionHandler.", e);
            return null;
        }
    }

    private static boolean m6881a(Context context) {
        return ((Boolean) dz.f4833g.m6433c()).booleanValue();
    }

    private Throwable m6882b(Throwable th) {
        if (((Boolean) dz.f4834h.m6433c()).booleanValue()) {
            return th;
        }
        LinkedList linkedList = new LinkedList();
        while (th != null) {
            linkedList.push(th);
            th = th.getCause();
        }
        Throwable th2 = null;
        while (!linkedList.isEmpty()) {
            Throwable th3;
            Throwable th4 = (Throwable) linkedList.pop();
            StackTraceElement[] stackTrace = th4.getStackTrace();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new StackTraceElement(th4.getClass().getName(), "<filtered>", "<filtered>", 1));
            int i = 0;
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (m6885a(stackTraceElement.getClassName())) {
                    arrayList.add(stackTraceElement);
                    i = 1;
                } else if (m6887b(stackTraceElement.getClassName())) {
                    arrayList.add(stackTraceElement);
                } else {
                    arrayList.add(new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1));
                }
            }
            if (i != 0) {
                th3 = th2 == null ? new Throwable(th4.getMessage()) : new Throwable(th4.getMessage(), th2);
                th3.setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]));
            } else {
                th3 = th2;
            }
            th2 = th3;
        }
        return th2;
    }

    String m6883a(Class cls, Throwable th, boolean z) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return new Builder().scheme(Constants.SCHEME).path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter(ResponseConstants.ID, "gmob-apps-report-exception").appendQueryParameter("os", VERSION.RELEASE).appendQueryParameter("api", String.valueOf(VERSION.SDK_INT)).appendQueryParameter("device", C1101o.m6041e().m7133e()).appendQueryParameter("js", this.f5210d.afmaVersion).appendQueryParameter("appid", this.f5209c.getApplicationContext().getPackageName()).appendQueryParameter("exceptiontype", cls.getName()).appendQueryParameter("stacktrace", stringWriter.toString()).appendQueryParameter("eids", TextUtils.join(",", dz.m6443a())).appendQueryParameter("trapped", String.valueOf(z)).toString();
    }

    public void m6884a(Throwable th, boolean z) {
        if (m6881a(this.f5209c)) {
            Throwable b = m6882b(th);
            if (b != null) {
                Class cls = th.getClass();
                List arrayList = new ArrayList();
                arrayList.add(m6883a(cls, b, z));
                C1101o.m6041e().m7110a(arrayList, C1101o.m6044h().m7033h());
            }
        }
    }

    protected boolean m6885a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.startsWith((String) dz.f4835i.m6433c())) {
            return true;
        }
        try {
            return Class.forName(str).isAnnotationPresent(jw.class);
        } catch (Throwable e) {
            Throwable th = e;
            String str2 = "Fail to check class type for class ";
            String valueOf = String.valueOf(str);
            C1129c.m6186a(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
            return false;
        }
    }

    protected boolean m6886a(Throwable th) {
        boolean z = true;
        if (th == null) {
            return false;
        }
        boolean z2 = false;
        boolean z3 = false;
        while (th != null) {
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                if (m6885a(stackTraceElement.getClassName())) {
                    z3 = true;
                }
                if (getClass().getName().equals(stackTraceElement.getClassName())) {
                    z2 = true;
                }
            }
            th = th.getCause();
        }
        if (!z3 || r2) {
            z = false;
        }
        return z;
    }

    protected boolean m6887b(String str) {
        return TextUtils.isEmpty(str) ? false : str.startsWith("android.") || str.startsWith("java.");
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (m6886a(th)) {
            if (Looper.getMainLooper().getThread() != thread) {
                m6884a(th, true);
                return;
            }
            m6884a(th, false);
        }
        if (this.f5207a != null) {
            this.f5207a.uncaughtException(thread, th);
        } else if (this.f5208b != null) {
            this.f5208b.uncaughtException(thread, th);
        }
    }
}
