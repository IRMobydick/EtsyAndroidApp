package com.etsy.android.lib.core.external.p007b;

import android.net.Uri;
import com.etsy.android.lib.core.external.ExternalAccountProfile;
import com.etsy.android.lib.core.external.ExternalAccountProfile.Gender;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.plus.a.a.a;
import com.google.android.gms.plus.a.a.f;
import com.google.android.gms.plus.a.a.g;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.lib.core.external.b.c */
public class GoogleAccountProfile extends ExternalAccountProfile {
    private final String f1516a;
    private final a f1517b;

    public GoogleAccountProfile(String str, a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("Person cannot be null");
        } else if (aVar.hasId()) {
            this.f1516a = str;
            this.f1517b = aVar;
        } else {
            throw new IllegalArgumentException("Person cannot be anonymous");
        }
    }

    public String m1294a() {
        return "google";
    }

    public String m1296b() {
        return this.f1517b.getId();
    }

    public ExternalAccountProfile m1297c() {
        g name = this.f1517b.getName();
        if (name == null) {
            return null;
        }
        return new ExternalAccountProfile(name.getGivenName(), name.getFamilyName());
    }

    public String m1298d() {
        return this.f1517b.getDisplayName();
    }

    public String m1299e() {
        return this.f1516a;
    }

    public Gender m1300f() {
        switch (this.f1517b.getGender()) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                return Gender.MALE;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return Gender.FEMALE;
            case Task.NETWORK_STATE_ANY /*2*/:
                return Gender.OTHER;
            default:
                return Gender.UNKNOWN;
        }
    }

    public String m1301g() {
        return this.f1517b.getBirthday();
    }

    public String m1295a(int i) {
        f image = this.f1517b.getImage();
        if (image == null) {
            return null;
        }
        return Uri.parse(image.getUrl()).buildUpon().appendQueryParameter("sz", StringUtils.EMPTY + i).toString();
    }
}
