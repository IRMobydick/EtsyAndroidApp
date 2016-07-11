package com.google.android.gms.ads.internal.overlay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.lo;

@jw
/* renamed from: com.google.android.gms.ads.internal.overlay.a */
public class C1102a {
    public boolean m6063a(Context context, Intent intent, l lVar) {
        try {
            String str = "Launching an intent: ";
            String valueOf = String.valueOf(intent.toURI());
            lo.m7056e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            C1101o.m6041e().m7101a(context, intent);
            if (lVar != null) {
                lVar.zzbj();
            }
            return true;
        } catch (ActivityNotFoundException e) {
            C1129c.m6192d(e.getMessage());
            return false;
        }
    }

    public boolean m6064a(Context context, AdLauncherIntentInfoParcel adLauncherIntentInfoParcel, l lVar) {
        if (adLauncherIntentInfoParcel == null) {
            C1129c.m6192d("No intent data for launcher overlay.");
            return false;
        } else if (adLauncherIntentInfoParcel.intent != null) {
            return m6063a(context, adLauncherIntentInfoParcel.intent, lVar);
        } else {
            Intent intent = new Intent();
            if (TextUtils.isEmpty(adLauncherIntentInfoParcel.url)) {
                C1129c.m6192d("Open GMSG did not contain a URL.");
                return false;
            }
            if (TextUtils.isEmpty(adLauncherIntentInfoParcel.mimeType)) {
                intent.setData(Uri.parse(adLauncherIntentInfoParcel.url));
            } else {
                intent.setDataAndType(Uri.parse(adLauncherIntentInfoParcel.url), adLauncherIntentInfoParcel.mimeType);
            }
            intent.setAction("android.intent.action.VIEW");
            if (!TextUtils.isEmpty(adLauncherIntentInfoParcel.packageName)) {
                intent.setPackage(adLauncherIntentInfoParcel.packageName);
            }
            if (!TextUtils.isEmpty(adLauncherIntentInfoParcel.zzGH)) {
                String[] split = adLauncherIntentInfoParcel.zzGH.split("/", 2);
                if (split.length < 2) {
                    String str = "Could not parse component name from open GMSG: ";
                    String valueOf = String.valueOf(adLauncherIntentInfoParcel.zzGH);
                    C1129c.m6192d(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    return false;
                }
                intent.setClassName(split[0], split[1]);
            }
            Object obj = adLauncherIntentInfoParcel.zzGI;
            if (!TextUtils.isEmpty(obj)) {
                int parseInt;
                try {
                    parseInt = Integer.parseInt(obj);
                } catch (NumberFormatException e) {
                    C1129c.m6192d("Could not parse intent flags.");
                    parseInt = 0;
                }
                intent.addFlags(parseInt);
            }
            return m6063a(context, intent, lVar);
        }
    }
}
