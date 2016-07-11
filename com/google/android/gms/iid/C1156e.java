package com.google.android.gms.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.adjust.sdk.Constants;
import com.etsy.android.lib.convos.Draft;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.iid.e.1;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/* renamed from: com.google.android.gms.iid.e */
public class C1156e {
    static String f4723a;
    static int f4724b;
    static int f4725c;
    static int f4726d;
    Context f4727e;
    Map<String, Object> f4728f;
    Messenger f4729g;
    Messenger f4730h;
    MessengerCompat f4731i;
    PendingIntent f4732j;
    long f4733k;
    long f4734l;
    int f4735m;
    int f4736n;
    long f4737o;

    static {
        f4723a = null;
        f4724b = 0;
        f4725c = 0;
        f4726d = 0;
    }

    public C1156e(Context context) {
        this.f4728f = new HashMap();
        this.f4727e = context;
    }

    public static String m6311a(Context context) {
        ApplicationInfo applicationInfo;
        if (f4723a != null) {
            return f4723a;
        }
        f4724b = Process.myUid();
        PackageManager packageManager = context.getPackageManager();
        for (ResolveInfo resolveInfo : packageManager.queryIntentServices(new Intent("com.google.android.c2dm.intent.REGISTER"), 0)) {
            if (packageManager.checkPermission("com.google.android.c2dm.permission.RECEIVE", resolveInfo.serviceInfo.packageName) == 0) {
                try {
                    ApplicationInfo applicationInfo2 = packageManager.getApplicationInfo(resolveInfo.serviceInfo.packageName, 0);
                    Log.w("InstanceID/Rpc", "Found " + applicationInfo2.uid);
                    f4725c = applicationInfo2.uid;
                    f4723a = resolveInfo.serviceInfo.packageName;
                    return f4723a;
                } catch (NameNotFoundException e) {
                }
            } else {
                String valueOf = String.valueOf(resolveInfo.serviceInfo.packageName);
                String valueOf2 = String.valueOf("com.google.android.c2dm.intent.REGISTER");
                Log.w("InstanceID/Rpc", new StringBuilder((String.valueOf(valueOf).length() + 56) + String.valueOf(valueOf2).length()).append("Possible malicious package ").append(valueOf).append(" declares ").append(valueOf2).append(" without permission").toString());
            }
        }
        Log.w("InstanceID/Rpc", "Failed to resolve REGISTER intent, falling back");
        try {
            applicationInfo = packageManager.getApplicationInfo(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE, 0);
            f4723a = applicationInfo.packageName;
            f4725c = applicationInfo.uid;
            return f4723a;
        } catch (NameNotFoundException e2) {
            try {
                applicationInfo = packageManager.getApplicationInfo("com.google.android.gsf", 0);
                f4723a = applicationInfo.packageName;
                f4725c = applicationInfo.uid;
                return f4723a;
            } catch (NameNotFoundException e3) {
                Log.w("InstanceID/Rpc", "Both Google Play Services and legacy GSF package are missing");
                return null;
            }
        }
    }

    static String m6312a(KeyPair keyPair, String... strArr) {
        String str = null;
        try {
            byte[] bytes = TextUtils.join("\n", strArr).getBytes(Constants.ENCODING);
            try {
                PrivateKey privateKey = keyPair.getPrivate();
                Signature instance = Signature.getInstance(privateKey instanceof RSAPrivateKey ? "SHA256withRSA" : "SHA256withECDSA");
                instance.initSign(privateKey);
                instance.update(bytes);
                str = C1153a.m6300a(instance.sign());
            } catch (Throwable e) {
                Log.e("InstanceID/Rpc", "Unable to sign registration request", e);
            }
        } catch (Throwable e2) {
            Log.e("InstanceID/Rpc", "Unable to encode string", e2);
        }
        return str;
    }

    private void m6313a(Object obj) {
        synchronized (getClass()) {
            for (String str : this.f4728f.keySet()) {
                Object obj2 = this.f4728f.get(str);
                this.f4728f.put(str, obj);
                m6314a(obj2, obj);
            }
        }
    }

    private void m6314a(Object obj, Object obj2) {
        if (obj instanceof ConditionVariable) {
            ((ConditionVariable) obj).open();
        }
        if (obj instanceof Messenger) {
            Messenger messenger = (Messenger) obj;
            Message obtain = Message.obtain();
            obtain.obj = obj2;
            try {
                messenger.send(obtain);
            } catch (RemoteException e) {
                String valueOf = String.valueOf(e);
                Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(valueOf).length() + 24).append("Failed to send response ").append(valueOf).toString());
            }
        }
    }

    private void m6315a(String str) {
        if ("com.google.android.gsf".equals(f4723a)) {
            this.f4735m++;
            if (this.f4735m >= 3) {
                if (this.f4735m == 3) {
                    this.f4736n = new Random().nextInt(com.appboy.Constants.APPBOY_MINIMUM_NOTIFICATION_DURATION_MILLIS) + com.appboy.Constants.APPBOY_MINIMUM_NOTIFICATION_DURATION_MILLIS;
                }
                this.f4736n *= 2;
                this.f4737o = SystemClock.elapsedRealtime() + ((long) this.f4736n);
                Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(str).length() + 31).append("Backoff due to ").append(str).append(" for ").append(this.f4736n).toString());
            }
        }
    }

    private void m6316a(String str, Object obj) {
        synchronized (getClass()) {
            Object obj2 = this.f4728f.get(str);
            this.f4728f.put(str, obj);
            m6314a(obj2, obj);
        }
    }

    private static int m6317b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(C1156e.m6311a(context), 0).versionCode;
        } catch (NameNotFoundException e) {
            return -1;
        }
    }

    private Intent m6318b(Bundle bundle, KeyPair keyPair) {
        Intent intent;
        ConditionVariable conditionVariable = new ConditionVariable();
        String b = C1156e.m6319b();
        synchronized (getClass()) {
            this.f4728f.put(b, conditionVariable);
        }
        m6324a(bundle, keyPair, b);
        conditionVariable.block(30000);
        synchronized (getClass()) {
            Object remove = this.f4728f.remove(b);
            if (remove instanceof Intent) {
                intent = (Intent) remove;
            } else if (remove instanceof String) {
                throw new IOException((String) remove);
            } else {
                String valueOf = String.valueOf(remove);
                Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(valueOf).length() + 12).append("No response ").append(valueOf).toString());
                throw new IOException("TIMEOUT");
            }
        }
        return intent;
    }

    public static synchronized String m6319b() {
        String num;
        synchronized (C1156e.class) {
            int i = f4726d;
            f4726d = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }

    Intent m6320a(Bundle bundle, KeyPair keyPair) {
        Intent b = m6318b(bundle, keyPair);
        return (b == null || !b.hasExtra("google.messenger")) ? b : m6318b(bundle, keyPair);
    }

    void m6321a() {
        if (this.f4729g == null) {
            C1156e.m6311a(this.f4727e);
            this.f4729g = new Messenger(new 1(this, Looper.getMainLooper()));
        }
    }

    synchronized void m6322a(Intent intent) {
        if (this.f4732j == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            this.f4732j = PendingIntent.getBroadcast(this.f4727e, 0, intent2, 0);
        }
        intent.putExtra("app", this.f4732j);
    }

    protected void m6323a(Intent intent, String str) {
        this.f4733k = SystemClock.elapsedRealtime();
        intent.putExtra("kid", new StringBuilder(String.valueOf(str).length() + 5).append("|ID|").append(str).append("|").toString());
        intent.putExtra("X-kid", new StringBuilder(String.valueOf(str).length() + 5).append("|ID|").append(str).append("|").toString());
        boolean equals = "com.google.android.gsf".equals(f4723a);
        String stringExtra = intent.getStringExtra("useGsf");
        if (stringExtra != null) {
            equals = "1".equals(stringExtra);
        }
        if (Log.isLoggable("InstanceID/Rpc", 3)) {
            String valueOf = String.valueOf(intent.getExtras());
            Log.d("InstanceID/Rpc", new StringBuilder(String.valueOf(valueOf).length() + 8).append("Sending ").append(valueOf).toString());
        }
        if (this.f4730h != null) {
            intent.putExtra("google.messenger", this.f4729g);
            Message obtain = Message.obtain();
            obtain.obj = intent;
            try {
                this.f4730h.send(obtain);
                return;
            } catch (RemoteException e) {
                if (Log.isLoggable("InstanceID/Rpc", 3)) {
                    Log.d("InstanceID/Rpc", "Messenger failed, fallback to startService");
                }
            }
        }
        if (equals) {
            Intent intent2 = new Intent("com.google.android.gms.iid.InstanceID");
            intent2.setPackage(this.f4727e.getPackageName());
            intent2.putExtra("GSF", intent);
            this.f4727e.startService(intent2);
            return;
        }
        intent.putExtra("google.messenger", this.f4729g);
        intent.putExtra("messenger2", "1");
        if (this.f4731i != null) {
            Message obtain2 = Message.obtain();
            obtain2.obj = intent;
            try {
                this.f4731i.send(obtain2);
                return;
            } catch (RemoteException e2) {
                if (Log.isLoggable("InstanceID/Rpc", 3)) {
                    Log.d("InstanceID/Rpc", "Messenger failed, fallback to startService");
                }
            }
        }
        this.f4727e.startService(intent);
    }

    void m6324a(Bundle bundle, KeyPair keyPair, String str) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.f4737o == 0 || elapsedRealtime > this.f4737o) {
            m6321a();
            if (f4723a == null) {
                throw new IOException("MISSING_INSTANCEID_SERVICE");
            }
            this.f4733k = SystemClock.elapsedRealtime();
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage(f4723a);
            bundle.putString("gmsv", Integer.toString(C1156e.m6317b(this.f4727e)));
            bundle.putString("osv", Integer.toString(VERSION.SDK_INT));
            bundle.putString("app_ver", Integer.toString(C1153a.m6297a(this.f4727e)));
            bundle.putString("app_ver_name", C1153a.m6301b(this.f4727e));
            bundle.putString("cliv", "1");
            bundle.putString("appid", C1153a.m6299a(keyPair));
            bundle.putString("pub2", C1153a.m6300a(keyPair.getPublic().getEncoded()));
            bundle.putString("sig", C1156e.m6312a(keyPair, this.f4727e.getPackageName(), r1));
            intent.putExtras(bundle);
            m6322a(intent);
            m6323a(intent, str);
            return;
        }
        elapsedRealtime = this.f4737o - elapsedRealtime;
        Log.w("InstanceID/Rpc", "Backoff mode, next request attempt: " + elapsedRealtime + " interval: " + this.f4736n);
        throw new IOException("RETRY_LATER");
    }

    public void m6325a(Message message) {
        if (message != null) {
            if (message.obj instanceof Intent) {
                Intent intent = (Intent) message.obj;
                intent.setExtrasClassLoader(MessengerCompat.class.getClassLoader());
                if (intent.hasExtra("google.messenger")) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                    if (parcelableExtra instanceof MessengerCompat) {
                        this.f4731i = (MessengerCompat) parcelableExtra;
                    }
                    if (parcelableExtra instanceof Messenger) {
                        this.f4730h = (Messenger) parcelableExtra;
                    }
                }
                m6328d((Intent) message.obj);
                return;
            }
            Log.w("InstanceID/Rpc", "Dropping invalid message");
        }
    }

    String m6326b(Intent intent) {
        if (intent == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        String stringExtra = intent.getStringExtra("registration_id");
        if (stringExtra == null) {
            stringExtra = intent.getStringExtra("unregistered");
        }
        intent.getLongExtra("Retry-After", 0);
        String valueOf;
        if (stringExtra != null) {
            if (stringExtra == null) {
                return stringExtra;
            }
            stringExtra = intent.getStringExtra(BaseMessage.TYPE_ERROR);
            if (stringExtra == null) {
                throw new IOException(stringExtra);
            }
            valueOf = String.valueOf(intent.getExtras());
            Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(valueOf).length() + 29).append("Unexpected response from GCM ").append(valueOf).toString(), new Throwable());
            throw new IOException("SERVICE_NOT_AVAILABLE");
        } else if (stringExtra == null) {
            return stringExtra;
        } else {
            stringExtra = intent.getStringExtra(BaseMessage.TYPE_ERROR);
            if (stringExtra == null) {
                valueOf = String.valueOf(intent.getExtras());
                Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(valueOf).length() + 29).append("Unexpected response from GCM ").append(valueOf).toString(), new Throwable());
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }
            throw new IOException(stringExtra);
        }
    }

    void m6327c(Intent intent) {
        String stringExtra = intent.getStringExtra(BaseMessage.TYPE_ERROR);
        if (stringExtra == null) {
            String valueOf = String.valueOf(intent.getExtras());
            Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(valueOf).length() + 49).append("Unexpected response, no error or registration id ").append(valueOf).toString());
            return;
        }
        if (Log.isLoggable("InstanceID/Rpc", 3)) {
            valueOf = "InstanceID/Rpc";
            String str = "Received InstanceID error ";
            String valueOf2 = String.valueOf(stringExtra);
            Log.d(valueOf, valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
        }
        if (stringExtra.startsWith("|")) {
            String[] split = stringExtra.split("\\|");
            if (!"ID".equals(split[1])) {
                String str2 = "InstanceID/Rpc";
                String str3 = "Unexpected structured response ";
                valueOf2 = String.valueOf(stringExtra);
                Log.w(str2, valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
            }
            if (split.length > 2) {
                valueOf2 = split[2];
                valueOf = split[3];
                if (valueOf.startsWith(Draft.IMAGE_DELIMITER)) {
                    valueOf = valueOf.substring(1);
                }
            } else {
                valueOf = "UNKNOWN";
                valueOf2 = null;
            }
            intent.putExtra(BaseMessage.TYPE_ERROR, valueOf);
        } else {
            valueOf2 = null;
            valueOf = stringExtra;
        }
        if (valueOf2 == null) {
            m6313a((Object) valueOf);
        } else {
            m6316a(valueOf2, (Object) valueOf);
        }
        long longExtra = intent.getLongExtra("Retry-After", 0);
        if (longExtra > 0) {
            this.f4734l = SystemClock.elapsedRealtime();
            this.f4736n = ((int) longExtra) * com.appboy.Constants.APPBOY_MINIMUM_NOTIFICATION_DURATION_MILLIS;
            this.f4737o = SystemClock.elapsedRealtime() + ((long) this.f4736n);
            Log.w("InstanceID/Rpc", "Explicit request from server to backoff: " + this.f4736n);
        } else if ("SERVICE_NOT_AVAILABLE".equals(valueOf) || "AUTHENTICATION_FAILED".equals(valueOf)) {
            m6315a(valueOf);
        }
    }

    public void m6328d(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            String stringExtra;
            String valueOf;
            if ("com.google.android.c2dm.intent.REGISTRATION".equals(action) || "com.google.android.gms.iid.InstanceID".equals(action)) {
                action = intent.getStringExtra("registration_id");
                stringExtra = action == null ? intent.getStringExtra("unregistered") : action;
                if (stringExtra == null) {
                    m6327c(intent);
                    return;
                }
                this.f4733k = SystemClock.elapsedRealtime();
                this.f4737o = 0;
                this.f4735m = 0;
                this.f4736n = 0;
                if (Log.isLoggable("InstanceID/Rpc", 3)) {
                    valueOf = String.valueOf(intent.getExtras());
                    Log.d("InstanceID/Rpc", new StringBuilder((String.valueOf(stringExtra).length() + 16) + String.valueOf(valueOf).length()).append("AppIDResponse: ").append(stringExtra).append(" ").append(valueOf).toString());
                }
                action = null;
                if (stringExtra.startsWith("|")) {
                    String[] split = stringExtra.split("\\|");
                    if (!"ID".equals(split[1])) {
                        String str = "InstanceID/Rpc";
                        String str2 = "Unexpected structured response ";
                        action = String.valueOf(stringExtra);
                        Log.w(str, action.length() != 0 ? str2.concat(action) : new String(str2));
                    }
                    stringExtra = split[2];
                    if (split.length > 4) {
                        if ("SYNC".equals(split[3])) {
                            InstanceIDListenerService.zzaW(this.f4727e);
                        } else if ("RST".equals(split[3])) {
                            InstanceIDListenerService.zza(this.f4727e, C1153a.m6302c(this.f4727e).m6308d());
                            intent.removeExtra("registration_id");
                            m6316a(stringExtra, (Object) intent);
                            return;
                        }
                    }
                    action = split[split.length - 1];
                    if (action.startsWith(Draft.IMAGE_DELIMITER)) {
                        action = action.substring(1);
                    }
                    intent.putExtra("registration_id", action);
                    action = stringExtra;
                }
                if (action == null) {
                    m6313a((Object) intent);
                } else {
                    m6316a(action, (Object) intent);
                }
            } else if (Log.isLoggable("InstanceID/Rpc", 3)) {
                stringExtra = "InstanceID/Rpc";
                valueOf = "Unexpected response ";
                action = String.valueOf(intent.getAction());
                Log.d(stringExtra, action.length() != 0 ? valueOf.concat(action) : new String(valueOf));
            }
        } else if (Log.isLoggable("InstanceID/Rpc", 3)) {
            Log.d("InstanceID/Rpc", "Unexpected response: null");
        }
    }
}
