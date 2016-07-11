package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import com.google.android.gms.common.internal.zzaa;

@jw
public class dr {
    private final Context f4790a;

    public dr(Context context) {
        zzaa.zzb((Object) context, (Object) "Context can not be null");
        this.f4790a = context;
    }

    public static boolean m6415e() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public boolean m6416a() {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:"));
        return m6417a(intent);
    }

    public boolean m6417a(Intent intent) {
        zzaa.zzb((Object) intent, (Object) "Intent can not be null");
        return !this.f4790a.getPackageManager().queryIntentActivities(intent, 0).isEmpty();
    }

    public boolean m6418b() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("sms:"));
        return m6417a(intent);
    }

    public boolean m6419c() {
        return m6415e() && this.f4790a.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    public boolean m6420d() {
        return true;
    }

    @TargetApi(14)
    public boolean m6421f() {
        return VERSION.SDK_INT >= 14 && m6417a(new Intent("android.intent.action.INSERT").setType("vnd.android.cursor.dir/event"));
    }
}
