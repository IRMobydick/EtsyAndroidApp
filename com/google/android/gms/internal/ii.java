package com.google.android.gms.internal;

import android.app.AlertDialog.Builder;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.f;
import java.util.Map;

@jw
public class ii extends im {
    private final Map<String, String> f5123a;
    private final Context f5124b;

    public ii(no noVar, Map<String, String> map) {
        super(noVar, "storePicture");
        this.f5123a = map;
        this.f5124b = noVar.m7256f();
    }

    Request m6780a(String str, String str2) {
        Request request = new Request(Uri.parse(str));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, str2);
        C1101o.m6043g().m7155a(request);
        return request;
    }

    String m6781a(String str) {
        return Uri.parse(str).getLastPathSegment();
    }

    public void m6782a() {
        if (this.f5124b == null) {
            m6758b("Activity context is not available");
        } else if (C1101o.m6041e().m7132e(this.f5124b).m6419c()) {
            String str = (String) this.f5123a.get("iurl");
            if (TextUtils.isEmpty(str)) {
                m6758b("Image url cannot be empty.");
            } else if (URLUtil.isValidUrl(str)) {
                String a = m6781a(str);
                if (C1101o.m6041e().m7127c(a)) {
                    Resources o = C1101o.m6044h().m7040o();
                    Builder d = C1101o.m6041e().m7129d(this.f5124b);
                    d.setTitle(o != null ? o.getString(f.store_picture_title) : "Save image");
                    d.setMessage(o != null ? o.getString(f.store_picture_message) : "Allow Ad to store image in Picture gallery?");
                    d.setPositiveButton(o != null ? o.getString(f.accept) : "Accept", new 1(this, str, a));
                    d.setNegativeButton(o != null ? o.getString(f.decline) : "Decline", new 2(this));
                    d.create().show();
                    return;
                }
                r1 = "Image type not recognized: ";
                str = String.valueOf(a);
                m6758b(str.length() != 0 ? r1.concat(str) : new String(r1));
            } else {
                r1 = "Invalid image url: ";
                str = String.valueOf(str);
                m6758b(str.length() != 0 ? r1.concat(str) : new String(r1));
            }
        } else {
            m6758b("Feature is not supported by the device.");
        }
    }
}
