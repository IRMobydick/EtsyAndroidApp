package com.google.android.gms.gcm;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.google.android.gms.common.api.CommonStatusCodes;
import java.util.Iterator;

public abstract class GcmListenerService extends Service {
    private int zzaSJ;
    private int zzaSK;
    private final Object zzpp;

    /* renamed from: com.google.android.gms.gcm.GcmListenerService.1 */
    class C11481 implements Runnable {
        final /* synthetic */ Intent f4704a;
        final /* synthetic */ GcmListenerService f4705b;

        C11481(GcmListenerService gcmListenerService, Intent intent) {
            this.f4705b = gcmListenerService;
            this.f4704a = intent;
        }

        public void run() {
            this.f4705b.zzm(this.f4704a);
        }
    }

    /* renamed from: com.google.android.gms.gcm.GcmListenerService.2 */
    class C11492 extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ Intent f4706a;
        final /* synthetic */ GcmListenerService f4707b;

        C11492(GcmListenerService gcmListenerService, Intent intent) {
            this.f4707b = gcmListenerService;
            this.f4706a = intent;
        }

        protected Void m6280a(Void... voidArr) {
            this.f4707b.zzm(this.f4706a);
            return null;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m6280a((Void[]) objArr);
        }
    }

    public GcmListenerService() {
        this.zzpp = new Object();
        this.zzaSK = 0;
    }

    private void zzBL() {
        synchronized (this.zzpp) {
            this.zzaSK--;
            if (this.zzaSK == 0) {
                zzhE(this.zzaSJ);
            }
        }
    }

    @TargetApi(11)
    private void zzl(Intent intent) {
        if (VERSION.SDK_INT >= 11) {
            AsyncTask.THREAD_POOL_EXECUTOR.execute(new C11481(this, intent));
        } else {
            new C11492(this, intent).execute(new Void[0]);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzm(android.content.Intent r5) {
        /*
        r4 = this;
        r1 = r5.getAction();	 Catch:{ all -> 0x003d }
        r0 = -1;
        r2 = r1.hashCode();	 Catch:{ all -> 0x003d }
        switch(r2) {
            case 366519424: goto L_0x002f;
            default: goto L_0x000c;
        };	 Catch:{ all -> 0x003d }
    L_0x000c:
        switch(r0) {
            case 0: goto L_0x0039;
            default: goto L_0x000f;
        };	 Catch:{ all -> 0x003d }
    L_0x000f:
        r1 = "GcmListenerService";
        r2 = "Unknown intent action: ";
        r0 = r5.getAction();	 Catch:{ all -> 0x003d }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x003d }
        r3 = r0.length();	 Catch:{ all -> 0x003d }
        if (r3 == 0) goto L_0x0042;
    L_0x0021:
        r0 = r2.concat(r0);	 Catch:{ all -> 0x003d }
    L_0x0025:
        android.util.Log.d(r1, r0);	 Catch:{ all -> 0x003d }
    L_0x0028:
        r4.zzBL();	 Catch:{ all -> 0x003d }
        android.support.v4.content.WakefulBroadcastReceiver.completeWakefulIntent(r5);
        return;
    L_0x002f:
        r2 = "com.google.android.c2dm.intent.RECEIVE";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x003d }
        if (r1 == 0) goto L_0x000c;
    L_0x0037:
        r0 = 0;
        goto L_0x000c;
    L_0x0039:
        r4.zzn(r5);	 Catch:{ all -> 0x003d }
        goto L_0x0028;
    L_0x003d:
        r0 = move-exception;
        android.support.v4.content.WakefulBroadcastReceiver.completeWakefulIntent(r5);
        throw r0;
    L_0x0042:
        r0 = new java.lang.String;	 Catch:{ all -> 0x003d }
        r0.<init>(r2);	 Catch:{ all -> 0x003d }
        goto L_0x0025;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.gcm.GcmListenerService.zzm(android.content.Intent):void");
    }

    private void zzn(Intent intent) {
        String stringExtra = intent.getStringExtra("message_type");
        if (stringExtra == null) {
            stringExtra = "gcm";
        }
        Object obj = -1;
        switch (stringExtra.hashCode()) {
            case -2062414158:
                if (stringExtra.equals("deleted_messages")) {
                    obj = 1;
                    break;
                }
                break;
            case 102161:
                if (stringExtra.equals("gcm")) {
                    obj = null;
                    break;
                }
                break;
            case 814694033:
                if (stringExtra.equals("send_error")) {
                    obj = 3;
                    break;
                }
                break;
            case 814800675:
                if (stringExtra.equals("send_event")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                zzo(intent);
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                onDeletedMessages();
            case Task.NETWORK_STATE_ANY /*2*/:
                onMessageSent(intent.getStringExtra("google.message_id"));
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                onSendError(intent.getStringExtra("google.message_id"), intent.getStringExtra(BaseMessage.TYPE_ERROR));
            default:
                String str = "GcmListenerService";
                String str2 = "Received message with unknown type: ";
                stringExtra = String.valueOf(stringExtra);
                Log.w(str, stringExtra.length() != 0 ? str2.concat(stringExtra) : new String(str2));
        }
    }

    private void zzo(Intent intent) {
        Bundle extras = intent.getExtras();
        extras.remove("message_type");
        extras.remove("android.support.content.wakelockid");
        if (zza.m6286a(extras)) {
            if (zza.m6290b((Context) this)) {
                zza.m6289b(extras);
            } else {
                zza.m6282a((Context) this).m6294c(extras);
                return;
            }
        }
        String string = extras.getString(ResponseConstants.FROM);
        extras.remove(ResponseConstants.FROM);
        zzz(extras);
        onMessageReceived(string, extras);
    }

    static void zzz(Bundle bundle) {
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && str.startsWith("google.c.")) {
                it.remove();
            }
        }
    }

    public final IBinder onBind(Intent intent) {
        return null;
    }

    public void onDeletedMessages() {
    }

    public void onMessageReceived(String str, Bundle bundle) {
    }

    public void onMessageSent(String str) {
    }

    public void onSendError(String str, String str2) {
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        synchronized (this.zzpp) {
            this.zzaSJ = i2;
            this.zzaSK++;
        }
        if (intent == null) {
            zzBL();
            return 2;
        }
        zzl(intent);
        return 3;
    }

    boolean zzhE(int i) {
        return stopSelfResult(i);
    }
}
