package com.etsy.android.lib.core.external.p006a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.etsy.android.lib.core.external.ExternalAccountHelper;
import com.etsy.android.lib.core.external.ExternalAccountListener;
import com.etsy.android.lib.core.external.ExternalAccountProfile;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.EtsyLogger;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.ExternalAccountUtil;
import com.etsy.android.lib.util.ExternalAccountUtil.AccountType;
import com.etsy.android.lib.util.ae;
import com.facebook.AccessToken;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.f;
import com.facebook.h;
import com.facebook.i;
import com.facebook.j;
import com.facebook.login.e;
import com.facebook.o;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

/* renamed from: com.etsy.android.lib.core.external.a.a */
public class FacebookAccountHelper extends ExternalAccountHelper {
    private static final String f1483c;
    private static final AccountType f1484d;
    private final List<String> f1485e;
    private final List<String> f1486f;
    private h f1487g;
    private f f1488h;
    private AccessToken f1489i;
    private j<com.facebook.login.h> f1490j;
    private JSONObject f1491k;
    private boolean f1492l;

    /* renamed from: com.etsy.android.lib.core.external.a.a.1 */
    class FacebookAccountHelper implements j<com.facebook.login.h> {
        final /* synthetic */ FacebookAccountHelper f1478a;

        /* renamed from: com.etsy.android.lib.core.external.a.a.1.1 */
        class FacebookAccountHelper implements o {
            final /* synthetic */ AccessToken f1476a;
            final /* synthetic */ FacebookAccountHelper f1477b;

            FacebookAccountHelper(FacebookAccountHelper facebookAccountHelper, AccessToken accessToken) {
                this.f1477b = facebookAccountHelper;
                this.f1476a = accessToken;
            }

            public void m1211a(JSONObject jSONObject, GraphResponse graphResponse) {
                ExternalAccountUtil.m3096a("oauth_profile_success", FacebookAccountHelper.f1484d);
                this.f1477b.f1478a.f1491k = jSONObject;
                this.f1477b.f1478a.m1236a(this.f1476a);
            }
        }

        FacebookAccountHelper(FacebookAccountHelper facebookAccountHelper) {
            this.f1478a = facebookAccountHelper;
        }

        public void m1214a(com.facebook.login.h hVar) {
            AccessToken a = hVar.a();
            this.f1478a.m1237a(a, new FacebookAccountHelper(this, a));
            ExternalAccountUtil.m3096a("oauth_accept", FacebookAccountHelper.f1484d);
        }

        public void m1212a() {
            this.f1478a.m1238a(false);
            Iterator it = this.f1478a.b.iterator();
            while (it.hasNext()) {
                ((ExternalAccountListener) it.next()).updateState(FacebookAccountHelper.f1484d);
            }
            ExternalAccountUtil.m3096a("oauth_refuse", FacebookAccountHelper.f1484d);
        }

        public void m1213a(FacebookException facebookException) {
            this.f1478a.m1238a(false);
            if ((facebookException instanceof FacebookAuthorizationException) && AccessToken.getCurrentAccessToken() != null) {
                e.a().b();
            }
            Iterator it = this.f1478a.b.iterator();
            while (it.hasNext()) {
                ExternalAccountListener externalAccountListener = (ExternalAccountListener) it.next();
                externalAccountListener.onError(FacebookAccountHelper.f1484d, facebookException);
                externalAccountListener.updateState(FacebookAccountHelper.f1484d);
            }
            ExternalAccountUtil.m3096a("oauth_error", FacebookAccountHelper.f1484d);
        }
    }

    /* renamed from: com.etsy.android.lib.core.external.a.a.2 */
    class FacebookAccountHelper extends f {
        final /* synthetic */ FacebookAccountHelper f1480a;

        /* renamed from: com.etsy.android.lib.core.external.a.a.2.1 */
        class FacebookAccountHelper implements o {
            final /* synthetic */ FacebookAccountHelper f1479a;

            FacebookAccountHelper(FacebookAccountHelper facebookAccountHelper) {
                this.f1479a = facebookAccountHelper;
            }

            public void m1216a(JSONObject jSONObject, GraphResponse graphResponse) {
                ExternalAccountUtil.m3096a("oauth_profile_success", FacebookAccountHelper.f1484d);
                this.f1479a.f1480a.f1491k = jSONObject;
            }
        }

        FacebookAccountHelper(FacebookAccountHelper facebookAccountHelper) {
            this.f1480a = facebookAccountHelper;
        }

        protected void m1217a(AccessToken accessToken, AccessToken accessToken2) {
            this.f1480a.f1489i = accessToken2;
            this.f1480a.m1237a(accessToken2, new FacebookAccountHelper(this));
        }
    }

    static {
        f1483c = EtsyDebug.m1891a(FacebookAccountHelper.class);
        f1484d = AccountType.FACEBOOK;
    }

    public FacebookAccountHelper(Activity activity) {
        super(activity);
        this.f1485e = Arrays.asList(new String[]{"public_profile", ResponseConstants.EMAIL});
        this.f1486f = Arrays.asList(new String[]{"gender", ResponseConstants.EMAIL, "timezone"});
        this.f1492l = true;
        FacebookSdk.m5771a(activity.getApplicationContext());
        this.f1487g = i.a();
        this.f1489i = AccessToken.getCurrentAccessToken();
        this.f1490j = new FacebookAccountHelper(this);
        e.a().a(this.f1487g, this.f1490j);
    }

    public boolean m1246a(AccountType accountType) {
        return accountType == f1484d;
    }

    public ExternalAccountProfile m1242a() {
        try {
            return new FacebookAccountProfile(Profile.getCurrentProfile(), this.f1491k);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public void m1247b() {
        if (this.f1488h == null) {
            this.f1488h = new FacebookAccountHelper(this);
        }
        if (!this.f1488h.c()) {
            this.f1488h.a();
        }
        this.f1489i = null;
    }

    public void m1248c() {
        if (this.f1488h.c()) {
            this.f1488h.b();
        }
    }

    public void m1249d() {
        if (this.f1489i == null || this.f1489i.isExpired()) {
            m1238a(true);
            try {
                ExternalAccountUtil.m3096a("oauth_attempt", f1484d);
                e.a().a(this.a, this.f1485e);
            } catch (Exception e) {
                Exception exception = e;
                EtsyLogger.m1966a().m1986a(f1483c, exception.getMessage());
                Iterator it = this.b.iterator();
                while (it.hasNext()) {
                    ExternalAccountListener externalAccountListener = (ExternalAccountListener) it.next();
                    externalAccountListener.onError(f1484d, exception);
                    externalAccountListener.updateState(f1484d);
                }
            }
        } else {
            m1236a(this.f1489i);
        }
        Iterator it2 = this.b.iterator();
        while (it2.hasNext()) {
            ((ExternalAccountListener) it2.next()).updateState(f1484d);
        }
    }

    private void m1236a(AccessToken accessToken) {
        m1238a(false);
        this.f1489i = accessToken;
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((ExternalAccountListener) it.next()).onSignIn(f1484d);
        }
    }

    public void m1250e() {
        e.a().b();
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((ExternalAccountListener) it.next()).updateState(f1484d);
        }
    }

    public void m1251f() {
    }

    public void m1252g() {
    }

    public void m1245a(String str, ae aeVar) {
        aeVar.m3195a(null);
    }

    public void m1244a(ae aeVar, boolean z) {
        if (this.f1489i == null || this.f1489i.isExpired()) {
            aeVar.m3195a(null);
        } else {
            aeVar.m3195a(this.f1489i.getToken());
        }
    }

    public void m1243a(int i, int i2, Intent intent) {
        this.f1487g.a(i, i2, intent);
    }

    private void m1238a(boolean z) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((ExternalAccountListener) it.next()).onBlockingUI(f1484d, z);
        }
    }

    private void m1237a(AccessToken accessToken, o oVar) {
        if (accessToken == null) {
            oVar.a(null, null);
            return;
        }
        ExternalAccountUtil.m3096a("oauth_profile_attempt", f1484d);
        try {
            GraphRequest a = GraphRequest.a(accessToken, oVar);
            Bundle bundle = new Bundle();
            bundle.putString("fields", StringUtils.join(this.f1486f, ","));
            a.a(bundle);
            a.h();
        } catch (Exception e) {
            EtsyLogger.m1966a().m1986a(f1483c, e.getMessage());
            ExternalAccountUtil.m3096a("oauth_profile_error", f1484d);
            oVar.a(null, null);
        }
    }
}
