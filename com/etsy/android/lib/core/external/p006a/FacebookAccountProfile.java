package com.etsy.android.lib.core.external.p006a;

import com.etsy.android.lib.core.external.ExternalAccountProfile;
import com.etsy.android.lib.core.external.ExternalAccountProfile.Gender;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShopAbout.Link;
import com.etsy.android.ui.user.auth.RegisterFragment;
import com.facebook.Profile;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

/* renamed from: com.etsy.android.lib.core.external.a.b */
public class FacebookAccountProfile extends ExternalAccountProfile {
    private final Profile f1493a;
    private final JSONObject f1494b;

    public FacebookAccountProfile(Profile profile, JSONObject jSONObject) {
        if (profile == null) {
            throw new IllegalArgumentException("Profile cannot be null");
        } else if (profile.getId() == null) {
            throw new IllegalArgumentException("Profile cannot be anonymous");
        } else {
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            this.f1493a = profile;
            this.f1494b = jSONObject;
        }
    }

    public String m1253a() {
        return Link.FACEBOOK_TITLE;
    }

    public String m1255b() {
        return this.f1493a.getId();
    }

    public ExternalAccountProfile m1256c() {
        return new ExternalAccountProfile(this.f1493a.getFirstName(), this.f1493a.getLastName());
    }

    public String m1257d() {
        return this.f1493a.getName();
    }

    public String m1258e() {
        return this.f1494b.optString(ResponseConstants.EMAIL, StringUtils.EMPTY);
    }

    public Gender m1259f() {
        String optString = this.f1494b.optString("gender", null);
        if (optString == null) {
            return Gender.UNKNOWN;
        }
        Object obj = -1;
        switch (optString.hashCode()) {
            case -1278174388:
                if (optString.equals(RegisterFragment.GENDER_NAME_FEMALE)) {
                    obj = null;
                    break;
                }
                break;
            case 3343885:
                if (optString.equals(RegisterFragment.GENDER_NAME_MALE)) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                return Gender.FEMALE;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return Gender.MALE;
            default:
                return Gender.OTHER;
        }
    }

    public String m1260g() {
        return this.f1494b.optString("user_birthday", StringUtils.EMPTY);
    }

    public String m1254a(int i) {
        return this.f1493a.getProfilePictureUri(i, i).toString();
    }
}
