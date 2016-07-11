package com.google.android.gms.ads.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import com.adjust.sdk.Constants;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.h.1;
import com.google.android.gms.ads.internal.h.2;
import com.google.android.gms.ads.internal.h.3;
import com.google.android.gms.ads.internal.h.4;
import com.google.android.gms.ads.internal.h.5;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.fk;
import com.google.android.gms.internal.hn;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.lb;
import com.google.android.gms.internal.no;
import com.google.android.gms.internal.zzdj;
import com.google.android.gms.internal.zzdj.zza;
import com.google.android.gms.internal.zzge;
import com.google.android.gms.internal.zzgf;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

@jw
/* renamed from: com.google.android.gms.ads.internal.h */
public class C1098h {
    @Nullable
    public static View m6005a(@Nullable lb lbVar) {
        if (lbVar == null) {
            C1129c.m6188b("AdState is null");
            return null;
        } else if (C1098h.m6025b(lbVar) && lbVar.f5327b != null) {
            return lbVar.f5327b.m7247b();
        } else {
            try {
                zzd view = lbVar.f5341p != null ? lbVar.f5341p.getView() : null;
                if (view != null) {
                    return (View) zze.zzx(view);
                }
                C1129c.m6192d("View in mediation adapter is null.");
                return null;
            } catch (Throwable e) {
                C1129c.m6193d("Could not get View from mediation adapter.", e);
                return null;
            }
        }
    }

    private static com.google.android.gms.ads.internal.formats.zzd m6006a(zzge com_google_android_gms_internal_zzge) {
        return new com.google.android.gms.ads.internal.formats.zzd(com_google_android_gms_internal_zzge.getHeadline(), com_google_android_gms_internal_zzge.getImages(), com_google_android_gms_internal_zzge.getBody(), com_google_android_gms_internal_zzge.zzeN(), com_google_android_gms_internal_zzge.getCallToAction(), com_google_android_gms_internal_zzge.getStarRating(), com_google_android_gms_internal_zzge.getStore(), com_google_android_gms_internal_zzge.getPrice(), null, com_google_android_gms_internal_zzge.getExtras());
    }

    private static com.google.android.gms.ads.internal.formats.zze m6007a(zzgf com_google_android_gms_internal_zzgf) {
        return new com.google.android.gms.ads.internal.formats.zze(com_google_android_gms_internal_zzgf.getHeadline(), com_google_android_gms_internal_zzgf.getImages(), com_google_android_gms_internal_zzgf.getBody(), com_google_android_gms_internal_zzgf.zzeR(), com_google_android_gms_internal_zzgf.getCallToAction(), com_google_android_gms_internal_zzgf.getAdvertiser(), null, com_google_android_gms_internal_zzgf.getExtras());
    }

    static fk m6008a(@Nullable zzge com_google_android_gms_internal_zzge, @Nullable zzgf com_google_android_gms_internal_zzgf, c cVar) {
        return new 5(com_google_android_gms_internal_zzge, cVar, com_google_android_gms_internal_zzgf);
    }

    static fk m6009a(CountDownLatch countDownLatch) {
        return new 3(countDownLatch);
    }

    private static String m6011a(Bitmap bitmap) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap == null) {
            C1129c.m6192d("Bitmap is null. Returning empty string");
            return StringUtils.EMPTY;
        }
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        String valueOf = String.valueOf("data:image/png;base64,");
        encodeToString = String.valueOf(encodeToString);
        return encodeToString.length() != 0 ? valueOf.concat(encodeToString) : new String(valueOf);
    }

    static String m6012a(zzdj com_google_android_gms_internal_zzdj) {
        if (com_google_android_gms_internal_zzdj == null) {
            C1129c.m6192d("Image is null. Returning empty string");
            return StringUtils.EMPTY;
        }
        try {
            Uri uri = com_google_android_gms_internal_zzdj.getUri();
            if (uri != null) {
                return uri.toString();
            }
        } catch (RemoteException e) {
            C1129c.m6192d("Unable to get image uri. Trying data uri next");
        }
        return C1098h.m6022b(com_google_android_gms_internal_zzdj);
    }

    public static void m6014a(@Nullable lb lbVar, c cVar) {
        zzgf com_google_android_gms_internal_zzgf = null;
        if (lbVar != null && C1098h.m6025b(lbVar)) {
            no noVar = lbVar.f5327b;
            Object b = noVar != null ? noVar.m7247b() : null;
            if (b == null) {
                C1129c.m6192d("AdWebView is null");
                return;
            }
            try {
                List list = lbVar.f5340o != null ? lbVar.f5340o.f5001o : null;
                if (list == null || list.isEmpty()) {
                    C1129c.m6192d("No template ids present in mediation response");
                    return;
                }
                zzge zzfI = lbVar.f5341p != null ? lbVar.f5341p.zzfI() : null;
                if (lbVar.f5341p != null) {
                    com_google_android_gms_internal_zzgf = lbVar.f5341p.zzfJ();
                }
                if (list.contains("2") && zzfI != null) {
                    zzfI.zzl(zze.zzD(b));
                    if (!zzfI.getOverrideImpressionRecording()) {
                        zzfI.recordImpression();
                    }
                    noVar.m7262l().zza("/nativeExpressViewClicked", C1098h.m6008a(zzfI, null, cVar));
                } else if (!list.contains("1") || com_google_android_gms_internal_zzgf == null) {
                    C1129c.m6192d("No matching template id and mapper");
                } else {
                    com_google_android_gms_internal_zzgf.zzl(zze.zzD(b));
                    if (!com_google_android_gms_internal_zzgf.getOverrideImpressionRecording()) {
                        com_google_android_gms_internal_zzgf.recordImpression();
                    }
                    noVar.m7262l().zza("/nativeExpressViewClicked", C1098h.m6008a(null, com_google_android_gms_internal_zzgf, cVar));
                }
            } catch (Throwable e) {
                C1129c.m6193d("Error occurred while recording impression and registering for clicks", e);
            }
        }
    }

    private static void m6016a(no noVar, com.google.android.gms.ads.internal.formats.zzd com_google_android_gms_ads_internal_formats_zzd, String str) {
        noVar.m7262l().zza(new 1(com_google_android_gms_ads_internal_formats_zzd, str, noVar));
    }

    private static void m6017a(no noVar, com.google.android.gms.ads.internal.formats.zze com_google_android_gms_ads_internal_formats_zze, String str) {
        noVar.m7262l().zza(new 2(com_google_android_gms_ads_internal_formats_zze, str, noVar));
    }

    private static void m6018a(no noVar, CountDownLatch countDownLatch) {
        noVar.m7262l().zza("/nativeExpressAssetsLoaded", C1098h.m6009a(countDownLatch));
        noVar.m7262l().zza("/nativeExpressAssetsLoadingFailed", C1098h.m6020b(countDownLatch));
    }

    public static boolean m6019a(no noVar, hn hnVar, CountDownLatch countDownLatch) {
        boolean z = false;
        try {
            z = C1098h.m6026b(noVar, hnVar, countDownLatch);
        } catch (Throwable e) {
            C1129c.m6193d("Unable to invoke load assets", e);
        } catch (RuntimeException e2) {
            countDownLatch.countDown();
            throw e2;
        }
        if (!z) {
            countDownLatch.countDown();
        }
        return z;
    }

    static fk m6020b(CountDownLatch countDownLatch) {
        return new 4(countDownLatch);
    }

    @Nullable
    private static zzdj m6021b(Object obj) {
        return obj instanceof IBinder ? zza.zzy((IBinder) obj) : null;
    }

    private static String m6022b(zzdj com_google_android_gms_internal_zzdj) {
        try {
            zzd zzeM = com_google_android_gms_internal_zzdj.zzeM();
            if (zzeM == null) {
                C1129c.m6192d("Drawable is null. Returning empty string");
                return StringUtils.EMPTY;
            }
            Drawable drawable = (Drawable) zze.zzx(zzeM);
            if (drawable instanceof BitmapDrawable) {
                return C1098h.m6011a(((BitmapDrawable) drawable).getBitmap());
            }
            C1129c.m6192d("Drawable is not an instance of BitmapDrawable. Returning empty string");
            return StringUtils.EMPTY;
        } catch (RemoteException e) {
            C1129c.m6192d("Unable to get drawable. Returning empty string");
            return StringUtils.EMPTY;
        }
    }

    private static JSONObject m6023b(Bundle bundle, String str) {
        JSONObject jSONObject = new JSONObject();
        if (bundle == null || TextUtils.isEmpty(str)) {
            return jSONObject;
        }
        JSONObject jSONObject2 = new JSONObject(str);
        Iterator keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            if (bundle.containsKey(str2)) {
                if (ResponseConstants.IMAGE.equals(jSONObject2.getString(str2))) {
                    Object obj = bundle.get(str2);
                    if (obj instanceof Bitmap) {
                        jSONObject.put(str2, C1098h.m6011a((Bitmap) obj));
                    } else {
                        C1129c.m6192d("Invalid type. An image type extra should return a bitmap");
                    }
                } else if (bundle.get(str2) instanceof Bitmap) {
                    C1129c.m6192d("Invalid asset type. Bitmap should be returned only for image type");
                } else {
                    jSONObject.put(str2, String.valueOf(bundle.get(str2)));
                }
            }
        }
        return jSONObject;
    }

    private static void m6024b(no noVar) {
        OnClickListener C = noVar.m7234C();
        if (C != null) {
            C.onClick(noVar.m7247b());
        }
    }

    public static boolean m6025b(@Nullable lb lbVar) {
        return (lbVar == null || !lbVar.f5339n || lbVar.f5340o == null || lbVar.f5340o.f4998l == null) ? false : true;
    }

    private static boolean m6026b(no noVar, hn hnVar, CountDownLatch countDownLatch) {
        View b = noVar.m7247b();
        if (b == null) {
            C1129c.m6192d("AdWebView is null");
            return false;
        }
        b.setVisibility(4);
        List list = hnVar.f5037b.f5001o;
        if (list == null || list.isEmpty()) {
            C1129c.m6192d("No template ids present in mediation response");
            return false;
        }
        C1098h.m6018a(noVar, countDownLatch);
        zzge zzfI = hnVar.f5038c.zzfI();
        zzgf zzfJ = hnVar.f5038c.zzfJ();
        if (list.contains("2") && zzfI != null) {
            C1098h.m6016a(noVar, C1098h.m6006a(zzfI), hnVar.f5037b.f5000n);
        } else if (!list.contains("1") || zzfJ == null) {
            C1129c.m6192d("No matching template id and mapper");
            return false;
        } else {
            C1098h.m6017a(noVar, C1098h.m6007a(zzfJ), hnVar.f5037b.f5000n);
        }
        String str = hnVar.f5037b.f4998l;
        String str2 = hnVar.f5037b.f4999m;
        if (str2 != null) {
            noVar.loadDataWithBaseURL(str2, str, "text/html", Constants.ENCODING, null);
        } else {
            noVar.loadData(str, "text/html", Constants.ENCODING);
        }
        return true;
    }
}
