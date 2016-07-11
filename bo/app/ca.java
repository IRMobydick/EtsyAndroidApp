package bo.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.appboy.Constants;
import com.appboy.services.C0380a;
import com.appboy.support.AppboyLogger;

public class ca implements cf<dj> {
    private static final String f216a;
    private final Context f217b;
    private final boolean f218c;
    private final SharedPreferences f219d;

    static {
        f216a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, ca.class.getName()});
    }

    public ca(Context context) {
        this.f217b = context.getApplicationContext();
        this.f218c = m87c();
        this.f219d = context.getSharedPreferences("com.appboy.managers.connected_device_storage", 0);
    }

    private boolean m87c() {
        try {
            boolean z;
            if (Class.forName("com.appboy.services.AppboyWearableListenerService", false, ca.class.getClassLoader()) != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                return fi.m347a(this.f217b, C0380a.class);
            }
            AppboyLogger.m666i(f216a, "AppboyWearableListenerService not found on path. Service not available.");
            return false;
        } catch (Exception e) {
            AppboyLogger.m666i(f216a, "AppboyWearableListenerService not found on path. Service not available.");
            return false;
        } catch (NoClassDefFoundError e2) {
            AppboyLogger.m666i(f216a, "AppboyWearableListenerService not found on path. Service not available.");
            return false;
        } catch (Throwable th) {
            AppboyLogger.m666i(f216a, "AppboyWearableListenerService not found on path. Service not available.");
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.util.List<bo.app.dj> m88a() {
        /*
        r9 = this;
        monitor-enter(r9);
        r0 = r9.f219d;	 Catch:{ all -> 0x0059 }
        r2 = r0.edit();	 Catch:{ all -> 0x0059 }
        r3 = new java.util.ArrayList;	 Catch:{ all -> 0x0059 }
        r3.<init>();	 Catch:{ all -> 0x0059 }
        r0 = r9.f219d;	 Catch:{ all -> 0x0059 }
        r0 = r0.getAll();	 Catch:{ all -> 0x0059 }
        r0 = r0.keySet();	 Catch:{ all -> 0x0059 }
        r4 = r0.iterator();	 Catch:{ all -> 0x0059 }
    L_0x001a:
        r0 = r4.hasNext();	 Catch:{ all -> 0x0059 }
        if (r0 == 0) goto L_0x005c;
    L_0x0020:
        r0 = r4.next();	 Catch:{ all -> 0x0059 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0059 }
        r1 = r9.f219d;	 Catch:{ all -> 0x0059 }
        r5 = 0;
        r5 = r1.getString(r0, r5);	 Catch:{ all -> 0x0059 }
        r1 = bo.app.fj.m354c(r5);	 Catch:{ all -> 0x0059 }
        if (r1 != 0) goto L_0x001a;
    L_0x0033:
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0040 }
        r1.<init>(r5);	 Catch:{ JSONException -> 0x0040 }
        r1 = bo.app.dj.m194a(r1);	 Catch:{ JSONException -> 0x0040 }
        r3.add(r1);	 Catch:{ JSONException -> 0x0040 }
        goto L_0x001a;
    L_0x0040:
        r1 = move-exception;
        r6 = f216a;	 Catch:{ all -> 0x0059 }
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0059 }
        r8 = "JSON error while pulling connected device from storage: ";
        r7.<init>(r8);	 Catch:{ all -> 0x0059 }
        r5 = r7.append(r5);	 Catch:{ all -> 0x0059 }
        r5 = r5.toString();	 Catch:{ all -> 0x0059 }
        com.appboy.support.AppboyLogger.m665e(r6, r5, r1);	 Catch:{ all -> 0x0059 }
        r2.remove(r0);	 Catch:{ all -> 0x0059 }
        goto L_0x001a;
    L_0x0059:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
    L_0x005c:
        r2.apply();	 Catch:{ all -> 0x0059 }
        monitor-exit(r9);
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.ca.a():java.util.List<bo.app.dj>");
    }

    private synchronized void m86a(dj djVar) {
        Editor edit = this.f219d.edit();
        edit.putString(djVar.f325a.f332a, djVar.m195a().toString());
        edit.apply();
    }

    public final void m90b() {
        if (this.f218c) {
            AppboyLogger.m666i(f216a, "Starting AppboyWearableListenerService.");
            this.f217b.startService(new Intent().setClass(this.f217b, C0380a.class));
            return;
        }
        AppboyLogger.m666i(f216a, "Appboy Wearable service is not available. Declare <service android:name=\"com.appboy.services.AppboyWearableListenerService\"/> in your appboy.xml to enable Appboy wearable service. See the Droidboy manifest for an example");
    }
}
