package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.z;
import com.google.android.gms.internal.zzaiy;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.google.firebase.a */
public class C1166a {
    static final Map<String, C1166a> f5562a;
    private static final List<String> f5563b;
    private static final List<String> f5564c;
    private static final List<String> f5565d;
    private static final Set<String> f5566e;
    private static final Object f5567f;
    private final Context f5568g;
    private final String f5569h;
    private final c f5570i;
    private final AtomicBoolean f5571j;
    private final AtomicBoolean f5572k;
    private final List<Object> f5573l;
    private final List<b> f5574m;
    private final List<Object> f5575n;

    static {
        f5563b = Arrays.asList(new String[]{"com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId"});
        f5564c = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
        f5565d = Arrays.asList(new String[]{"com.google.android.gms.measurement.AppMeasurement"});
        f5566e = Collections.emptySet();
        f5567f = new Object();
        f5562a = new ArrayMap();
    }

    protected C1166a(Context context, String str, c cVar) {
        this.f5571j = new AtomicBoolean(true);
        this.f5572k = new AtomicBoolean();
        this.f5573l = new CopyOnWriteArrayList();
        this.f5574m = new CopyOnWriteArrayList();
        this.f5575n = new CopyOnWriteArrayList();
        this.f5568g = (Context) zzaa.zzz(context);
        this.f5569h = zzaa.zzdl(str);
        this.f5570i = (c) zzaa.zzz(cVar);
    }

    public static C1166a m7468a(Context context) {
        c a = c.a(context);
        return a == null ? null : C1166a.m7469a(context, a);
    }

    public static C1166a m7469a(Context context, c cVar) {
        return C1166a.m7470a(context, cVar, "[DEFAULT]");
    }

    public static C1166a m7470a(Context context, c cVar, String str) {
        Object c1166a;
        z a = z.a(context);
        C1166a.m7474b(context);
        String a2 = C1166a.m7471a(str);
        Object applicationContext = context.getApplicationContext();
        synchronized (f5567f) {
            zzaa.zza(!f5562a.containsKey(a2), new StringBuilder(String.valueOf(a2).length() + 33).append("FirebaseApp name ").append(a2).append(" already exists!").toString());
            zzaa.zzb(applicationContext, (Object) "Application context cannot be null.");
            c1166a = new C1166a(applicationContext, a2, cVar);
            f5562a.put(a2, c1166a);
        }
        a.a(c1166a);
        C1166a.m7472a(C1166a.class, c1166a, f5563b);
        if (c1166a.m7479c()) {
            C1166a.m7472a(C1166a.class, c1166a, f5564c);
            C1166a.m7472a(Context.class, c1166a.m7477a(), f5565d);
        }
        return c1166a;
    }

    private static String m7471a(@NonNull String str) {
        return str.trim();
    }

    private static <T> void m7472a(Class<T> cls, T t, Iterable<String> iterable) {
        String valueOf;
        for (String valueOf2 : iterable) {
            try {
                Class cls2 = Class.forName(valueOf2);
                Method method = cls2.getMethod("getInstance", new Class[]{cls});
                if ((method.getModifiers() & 9) == 9) {
                    method.invoke(null, new Object[]{t});
                }
                String valueOf3 = String.valueOf(cls2);
                Log.d("FirebaseApp", new StringBuilder(String.valueOf(valueOf3).length() + 13).append("Initialized ").append(valueOf3).append(".").toString());
            } catch (ClassNotFoundException e) {
                if (f5566e.contains(valueOf2)) {
                    throw new IllegalStateException(String.valueOf(valueOf2).concat(" is missing, but is required. Check if it has been removed by Proguard."));
                }
                Log.d("FirebaseApp", String.valueOf(valueOf2).concat(" is not linked. Skipping initialization."));
            } catch (NoSuchMethodException e2) {
                throw new IllegalStateException(String.valueOf(valueOf2).concat("#getInstance has been removed by Proguard. Add keep rule to prevent it."));
            } catch (Throwable e3) {
                Log.wtf("FirebaseApp", "Firebase API initialization failure.", e3);
            } catch (Throwable e4) {
                String str = "FirebaseApp";
                String str2 = "Failed to initialize ";
                valueOf2 = String.valueOf(valueOf2);
                Log.wtf(str, valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), e4);
            }
        }
    }

    public static void m7473a(boolean z) {
        synchronized (f5567f) {
            Iterator it = new ArrayList(f5562a.values()).iterator();
            while (it.hasNext()) {
                C1166a c1166a = (C1166a) it.next();
                if (c1166a.f5571j.get()) {
                    c1166a.m7475b(z);
                }
            }
        }
    }

    @TargetApi(14)
    private static void m7474b(Context context) {
        if (zzs.zzva() && (context.getApplicationContext() instanceof Application)) {
            zzaiy.zza((Application) context.getApplicationContext());
        }
    }

    private void m7475b(boolean z) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        for (b a : this.f5574m) {
            a.a(z);
        }
    }

    private void m7476d() {
        zzaa.zza(!this.f5572k.get(), (Object) "FirebaseApp was deleted");
    }

    @NonNull
    public Context m7477a() {
        m7476d();
        return this.f5568g;
    }

    @NonNull
    public String m7478b() {
        m7476d();
        return this.f5569h;
    }

    public boolean m7479c() {
        return "[DEFAULT]".equals(m7478b());
    }

    public boolean equals(Object obj) {
        return !(obj instanceof C1166a) ? false : this.f5569h.equals(((C1166a) obj).m7478b());
    }

    public int hashCode() {
        return this.f5569h.hashCode();
    }

    public String toString() {
        return zzz.zzy(this).zzg(ResponseConstants.NAME, this.f5569h).zzg(ResponseConstants.OPTIONS, this.f5570i).toString();
    }
}
