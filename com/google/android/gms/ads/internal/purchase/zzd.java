package com.google.android.gms.ads.internal.purchase;

import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.zzhg.zza;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@jw
public class zzd extends zza {
    private Context mContext;
    private String zzJq;
    private ArrayList<String> zzJr;
    private String zztK;

    public zzd(String str, ArrayList<String> arrayList, Context context, String str2) {
        this.zzJq = str;
        this.zzJr = arrayList;
        this.zztK = str2;
        this.mContext = context;
    }

    public String getProductId() {
        return this.zzJq;
    }

    public void recordPlayBillingResolution(int i) {
        if (i == 0) {
            zzhm();
        }
        HashMap hashMap = new HashMap();
        hashMap.put("google_play_status", String.valueOf(i));
        hashMap.put("sku", this.zzJq);
        hashMap.put(ResponseConstants.STATUS, String.valueOf(zzG(i)));
        List linkedList = new LinkedList();
        Iterator it = this.zzJr.iterator();
        while (it.hasNext()) {
            linkedList.add(zza((String) it.next(), hashMap));
        }
        C1101o.m6041e().m7105a(this.mContext, this.zztK, linkedList);
    }

    public void recordResolution(int i) {
        if (i == 1) {
            zzhm();
        }
        HashMap hashMap = new HashMap();
        hashMap.put(ResponseConstants.STATUS, String.valueOf(i));
        hashMap.put("sku", this.zzJq);
        List linkedList = new LinkedList();
        Iterator it = this.zzJr.iterator();
        while (it.hasNext()) {
            linkedList.add(zza((String) it.next(), hashMap));
        }
        C1101o.m6041e().m7105a(this.mContext, this.zztK, linkedList);
    }

    protected int zzG(int i) {
        return i == 0 ? 1 : i == 1 ? 2 : i == 4 ? 3 : 0;
    }

    protected String zza(String str, HashMap<String, String> hashMap) {
        String str2;
        String packageName = this.mContext.getPackageName();
        String str3 = StringUtils.EMPTY;
        try {
            str2 = this.mContext.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (Throwable e) {
            C1129c.m6193d("Error to retrieve app version", e);
            str2 = str3;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - C1101o.m6044h().m7029d().m7044a();
        for (String str32 : hashMap.keySet()) {
            str = str.replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", new Object[]{str32}), String.format("$1%s$2", new Object[]{hashMap.get(str32)}));
        }
        return str.replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", new Object[]{"sessionid"}), String.format("$1%s$2", new Object[]{Uri.encode(C1101o.m6044h().m7011a())})).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", new Object[]{"appid"}), String.format("$1%s$2", new Object[]{Uri.encode(packageName)})).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", new Object[]{"osversion"}), String.format("$1%s$2", new Object[]{Uri.encode(String.valueOf(VERSION.SDK_INT))})).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", new Object[]{"sdkversion"}), String.format("$1%s$2", new Object[]{Uri.encode(this.zztK)})).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", new Object[]{"appversion"}), String.format("$1%s$2", new Object[]{Uri.encode(str2)})).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", new Object[]{"timestamp"}), String.format("$1%s$2", new Object[]{Uri.encode(String.valueOf(elapsedRealtime))})).replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", new Object[]{"[^@]+"}), String.format("$1%s$2", new Object[]{StringUtils.EMPTY})).replaceAll("@@", "@");
    }

    void zzhm() {
        try {
            this.mContext.getClassLoader().loadClass("com.google.ads.conversiontracking.IAPConversionReporter").getDeclaredMethod("reportWithProductId", new Class[]{Context.class, String.class, String.class, Boolean.TYPE}).invoke(null, new Object[]{this.mContext, this.zzJq, StringUtils.EMPTY, Boolean.valueOf(true)});
        } catch (ClassNotFoundException e) {
            C1129c.m6192d("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        } catch (NoSuchMethodException e2) {
            C1129c.m6192d("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        } catch (Throwable e3) {
            C1129c.m6193d("Fail to report a conversion.", e3);
        }
    }
}
