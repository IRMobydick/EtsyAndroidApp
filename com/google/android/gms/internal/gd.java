package com.google.android.gms.internal;

import android.content.Context;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.ads.internal.util.client.C1128a;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.gcm.Task;
import java.lang.ref.WeakReference;
import java.util.Map;

@jw
public abstract class gd implements Releasable {
    protected Context f4946a;
    protected String f4947b;
    protected WeakReference<no> f4948c;

    public gd(no noVar) {
        this.f4946a = noVar.getContext();
        this.f4947b = C1101o.m6041e().m7092a(this.f4946a, noVar.m7265o().afmaVersion);
        this.f4948c = new WeakReference(noVar);
    }

    private void m6566a(String str, Map<String, String> map) {
        no noVar = (no) this.f4948c.get();
        if (noVar != null) {
            noVar.m7244a(str, (Map) map);
        }
    }

    private String m6567c(String str) {
        String str2 = "internal";
        Object obj = -1;
        switch (str.hashCode()) {
            case -1396664534:
                if (str.equals("badUrl")) {
                    obj = 6;
                    break;
                }
                break;
            case -1347010958:
                if (str.equals("inProgress")) {
                    obj = 2;
                    break;
                }
                break;
            case -918817863:
                if (str.equals("downloadTimeout")) {
                    obj = 7;
                    break;
                }
                break;
            case -659376217:
                if (str.equals("contentLengthMissing")) {
                    obj = 3;
                    break;
                }
                break;
            case -642208130:
                if (str.equals("playerFailed")) {
                    obj = 1;
                    break;
                }
                break;
            case -354048396:
                if (str.equals("sizeExceeded")) {
                    obj = 8;
                    break;
                }
                break;
            case -32082395:
                if (str.equals("externalAbort")) {
                    obj = 9;
                    break;
                }
                break;
            case 96784904:
                if (str.equals(BaseMessage.TYPE_ERROR)) {
                    obj = null;
                    break;
                }
                break;
            case 580119100:
                if (str.equals("expireFailed")) {
                    obj = 5;
                    break;
                }
                break;
            case 725497484:
                if (str.equals("noCacheDir")) {
                    obj = 4;
                    break;
                }
                break;
        }
        switch (obj) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
            case Task.NETWORK_STATE_UNMETERED /*1*/:
            case Task.NETWORK_STATE_ANY /*2*/:
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return "internal";
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                return "io";
            case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
            case CommonStatusCodes.NETWORK_ERROR /*7*/:
                return "network";
            case CommonStatusCodes.INTERNAL_ERROR /*8*/:
            case CommonStatusCodes.SERVICE_INVALID /*9*/:
                return "policy";
            default:
                return str2;
        }
    }

    public abstract void m6568a();

    protected void m6569a(String str, String str2, int i) {
        C1128a.f4666a.post(new 2(this, str, str2, i));
    }

    protected void m6570a(String str, String str2, int i, int i2, boolean z) {
        C1128a.f4666a.post(new 1(this, str, str2, i, i2, z));
    }

    protected void m6571a(String str, String str2, String str3, String str4) {
        C1128a.f4666a.post(new 3(this, str, str2, str3, str4));
    }

    public abstract boolean m6572a(String str);

    protected String m6573b(String str) {
        return C1089r.m5951a().m6169a(str);
    }

    public void release() {
    }
}
