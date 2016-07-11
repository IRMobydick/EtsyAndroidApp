package com.google.android.gms.iid;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import org.apache.commons.lang3.StringUtils;

public class InstanceIDListenerService extends Service {
    static String ACTION;
    private static String zzaSR;
    private static String zzaUi;
    private static String zzaUj;
    MessengerCompat zzaUg;
    BroadcastReceiver zzaUh;
    int zzaUk;
    int zzaUl;

    /* renamed from: com.google.android.gms.iid.InstanceIDListenerService.1 */
    class C11501 extends Handler {
        final /* synthetic */ InstanceIDListenerService f4710a;

        C11501(InstanceIDListenerService instanceIDListenerService, Looper looper) {
            this.f4710a = instanceIDListenerService;
            super(looper);
        }

        public void handleMessage(Message message) {
            this.f4710a.zza(message, MessengerCompat.zzc(message));
        }
    }

    /* renamed from: com.google.android.gms.iid.InstanceIDListenerService.2 */
    class C11512 extends BroadcastReceiver {
        final /* synthetic */ InstanceIDListenerService f4711a;

        C11512(InstanceIDListenerService instanceIDListenerService) {
            this.f4711a = instanceIDListenerService;
        }

        public void onReceive(Context context, Intent intent) {
            if (Log.isLoggable("InstanceID", 3)) {
                intent.getStringExtra("registration_id");
                String valueOf = String.valueOf(intent.getExtras());
                Log.d("InstanceID", new StringBuilder(String.valueOf(valueOf).length() + 46).append("Received GSF callback using dynamic receiver: ").append(valueOf).toString());
            }
            this.f4711a.zzn(intent);
            this.f4711a.stop();
        }
    }

    static {
        ACTION = "action";
        zzaUi = "google.com/iid";
        zzaUj = "CMD";
        zzaSR = "gcm.googleapis.com/refresh";
    }

    public InstanceIDListenerService() {
        this.zzaUg = new MessengerCompat(new C11501(this, Looper.getMainLooper()));
        this.zzaUh = new C11512(this);
    }

    static void zza(Context context, C1157f c1157f) {
        c1157f.m6338b();
        Intent intent = new Intent("com.google.android.gms.iid.InstanceID");
        intent.putExtra(zzaUj, "RST");
        intent.setPackage(context.getPackageName());
        context.startService(intent);
    }

    private void zza(Message message, int i) {
        C1156e.m6311a((Context) this);
        getPackageManager();
        if (i == C1156e.f4725c || i == C1156e.f4724b) {
            zzn((Intent) message.obj);
            return;
        }
        int i2 = C1156e.f4724b;
        Log.w("InstanceID", "Message from unexpected caller " + i + " mine=" + i2 + " appid=" + C1156e.f4725c);
    }

    static void zzaW(Context context) {
        Intent intent = new Intent("com.google.android.gms.iid.InstanceID");
        intent.setPackage(context.getPackageName());
        intent.putExtra(zzaUj, "SYNC");
        context.startService(intent);
    }

    public IBinder onBind(Intent intent) {
        return (intent == null || !"com.google.android.gms.iid.InstanceID".equals(intent.getAction())) ? null : this.zzaUg.getBinder();
    }

    public void onCreate() {
        IntentFilter intentFilter = new IntentFilter("com.google.android.c2dm.intent.REGISTRATION");
        intentFilter.addCategory(getPackageName());
        registerReceiver(this.zzaUh, intentFilter, "com.google.android.c2dm.permission.RECEIVE", null);
    }

    public void onDestroy() {
        unregisterReceiver(this.zzaUh);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        zzhN(i2);
        if (intent == null) {
            stop();
            return 2;
        }
        try {
            if ("com.google.android.gms.iid.InstanceID".equals(intent.getAction())) {
                if (VERSION.SDK_INT <= 18) {
                    Intent intent2 = (Intent) intent.getParcelableExtra("GSF");
                    if (intent2 != null) {
                        startService(intent2);
                        return 1;
                    }
                }
                zzn(intent);
            }
            stop();
            if (intent.getStringExtra(ResponseConstants.FROM) != null) {
                WakefulBroadcastReceiver.completeWakefulIntent(intent);
            }
            return 2;
        } finally {
            stop();
        }
    }

    public void onTokenRefresh() {
    }

    void stop() {
        synchronized (this) {
            this.zzaUk--;
            if (this.zzaUk == 0) {
                stopSelf(this.zzaUl);
            }
            if (Log.isLoggable("InstanceID", 3)) {
                int i = this.zzaUk;
                Log.d("InstanceID", "Stop " + i + " " + this.zzaUl);
            }
        }
    }

    public void zzas(boolean z) {
        onTokenRefresh();
    }

    void zzhN(int i) {
        synchronized (this) {
            this.zzaUk++;
            if (i > this.zzaUl) {
                this.zzaUl = i;
            }
        }
    }

    public void zzn(Intent intent) {
        C1153a c;
        String stringExtra = intent.getStringExtra("subtype");
        if (stringExtra == null) {
            c = C1153a.m6302c(this);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("subtype", stringExtra);
            c = C1153a.m6298a(this, bundle);
        }
        String stringExtra2 = intent.getStringExtra(zzaUj);
        if (intent.getStringExtra(BaseMessage.TYPE_ERROR) == null && intent.getStringExtra("registration_id") == null) {
            if (Log.isLoggable("InstanceID", 3)) {
                String valueOf = String.valueOf(intent.getExtras());
                Log.d("InstanceID", new StringBuilder(((String.valueOf(stringExtra).length() + 18) + String.valueOf(stringExtra2).length()) + String.valueOf(valueOf).length()).append("Service command ").append(stringExtra).append(" ").append(stringExtra2).append(" ").append(valueOf).toString());
            }
            if (intent.getStringExtra("unregistered") != null) {
                C1157f d = c.m6308d();
                if (stringExtra == null) {
                    stringExtra = StringUtils.EMPTY;
                }
                d.m6342e(stringExtra);
                c.m6309e().m6328d(intent);
                return;
            } else if (zzaSR.equals(intent.getStringExtra(ResponseConstants.FROM))) {
                c.m6308d().m6342e(stringExtra);
                zzas(false);
                return;
            } else if ("RST".equals(stringExtra2)) {
                c.m6307c();
                zzas(true);
                return;
            } else if ("RST_FULL".equals(stringExtra2)) {
                if (!c.m6308d().m6337a()) {
                    c.m6308d().m6338b();
                    zzas(true);
                    return;
                }
                return;
            } else if ("SYNC".equals(stringExtra2)) {
                c.m6308d().m6342e(stringExtra);
                zzas(false);
                return;
            } else if (!"PING".equals(stringExtra2)) {
                return;
            } else {
                return;
            }
        }
        if (Log.isLoggable("InstanceID", 3)) {
            stringExtra2 = "InstanceID";
            String str = "Register result in service ";
            stringExtra = String.valueOf(stringExtra);
            Log.d(stringExtra2, stringExtra.length() != 0 ? str.concat(stringExtra) : new String(str));
        }
        c.m6309e().m6328d(intent);
    }
}
