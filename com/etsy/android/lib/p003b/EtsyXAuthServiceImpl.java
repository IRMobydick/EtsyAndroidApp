package com.etsy.android.lib.p003b;

import com.android.volley.BuildConfig;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.core.ar;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.p010a.EtsyGraphite;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.requests.EtsyRequest;
import com.google.android.gms.gcm.Task;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.UUID;
import org.scribe.c.b;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.model.a;
import org.scribe.model.c;
import org.scribe.model.h;

/* renamed from: com.etsy.android.lib.b.b */
public class EtsyXAuthServiceImpl implements b {
    private static final String f1143a;
    private a f1144b;
    private org.scribe.a.a.b f1145c;
    private long f1146d;

    /* renamed from: com.etsy.android.lib.b.b.1 */
    /* synthetic */ class EtsyXAuthServiceImpl {
        static final /* synthetic */ int[] f1142a;

        static {
            f1142a = new int[SignatureType.values().length];
            try {
                f1142a[SignatureType.Header.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1142a[SignatureType.QueryString.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    static {
        f1143a = EtsyDebug.m1891a(EtsyXAuthServiceImpl.class);
    }

    public EtsyXAuthServiceImpl(org.scribe.a.a.b bVar, a aVar) {
        this.f1145c = bVar;
        this.f1144b = aVar;
    }

    public void m819a(long j) {
        this.f1146d = j - EtsyXAuthServiceImpl.m809b();
        EtsyDebug.m1906b("oauth_informServerTime", String.format("serverTime=%d, serverTimeOffset=%d", new Object[]{Long.valueOf(j), Long.valueOf(this.f1146d)}));
    }

    private static long m809b() {
        return System.currentTimeMillis() / 1000;
    }

    public ar m812a(String str, String str2) {
        this.f1144b.a("obtaining access token from " + this.f1145c.a());
        return m805a(m806a(str, str2, null, null, null, null, null, null, false));
    }

    public ar m814a(String str, String str2, String str3, String str4) {
        this.f1144b.a("obtaining access token from " + this.f1145c.a());
        return m805a(m806a(str, null, str2, str3, null, str4, null, null, false));
    }

    public ar m815a(String str, String str2, String str3, String str4, String str5) {
        this.f1144b.a("obtaining access token with link external from " + this.f1145c.a());
        return m805a(m806a(str, str2, str3, str4, null, str5, null, null, false));
    }

    public ar m813a(String str, String str2, String str3) {
        this.f1144b.a("obtaining access 2 factor token from " + this.f1145c.a());
        return m805a(m806a(str, null, null, null, null, null, str2, str3, false));
    }

    public ar m816a(String str, String str2, String str3, String str4, String str5, String str6) {
        this.f1144b.a("obtaining access 2 factor token from " + this.f1145c.a());
        return m805a(m806a(str, null, str3, str4, null, str5, str2, str6, false));
    }

    public ar m820b(String str, String str2) {
        this.f1144b.a("resending 2 factor token " + this.f1145c.a());
        return m805a(m806a(str, null, null, null, null, null, null, str2, true));
    }

    private c m807a(String str, String str2, boolean z) {
        String a = this.f1145c.a();
        if (this.f1144b.e()) {
            a = a + "?scope=" + this.f1144b.d();
        }
        c cVar = new c(this.f1145c.b(), a);
        cVar.b("User-agent", InstallInfo.m919a().m935l());
        cVar.c("x_auth_username", str);
        if (str2 != null) {
            cVar.c("workflow_key", str2);
        }
        if (z) {
            cVar.c("resend_token", "1");
        }
        cVar.c("x_auth_mode", "client_auth");
        cVar.c("device_type", AppBuild.ANDROID_PLATFORM);
        cVar.c("environment_id", "1");
        cVar.c("device_udid", InstallInfo.m919a().m925b());
        cVar.c("device_version", InstallInfo.m919a().m929f());
        cVar.c("guest_cart_id", InstallInfo.m919a().m928e());
        cVar.c(EtsyRequest.PARAM_LANGUAGE, Locale.getDefault().getLanguage());
        if (EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.cD)) {
            cVar.c("x_auth_two_factor_support_enabled", "1");
        }
        return cVar;
    }

    private c m806a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z) {
        c a = m807a(str, str8, z);
        if (str2 != null) {
            a.c("x_auth_password", str2);
        }
        if (str4 != null) {
            a.c("x_auth_external_account_id", str4);
        }
        if (str3 != null) {
            a.c("x_auth_external_account_type_name", str3);
        }
        if (str5 != null) {
            a.c("x_auth_external_auth_code", str5);
        }
        if (str6 != null) {
            a.c("x_auth_external_auth_token", str6);
        }
        if (str7 != null) {
            a.c("x_auth_two_factor_token", str7);
        }
        m808a(a, org.scribe.model.b.a);
        m811b(a);
        return a;
    }

    public ar m817a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        this.f1144b.a("obtaining (via registration) access token from " + this.f1145c.a());
        String a = this.f1145c.a();
        if (this.f1144b.e()) {
            a = a + "?scope=" + this.f1144b.d();
        }
        c cVar = new c(this.f1145c.b(), a);
        cVar.b("User-agent", InstallInfo.m919a().m935l());
        cVar.c("x_auth_username", str);
        if (str2 != null) {
            cVar.c("x_auth_password", str2);
        }
        if (str10 != null) {
            cVar.c("x_auth_external_account_id", str10);
        }
        if (str9 != null) {
            cVar.c("x_auth_external_account_type_name", str9);
        }
        if (str11 != null) {
            cVar.c("x_auth_external_auth_token", str11);
        }
        cVar.c("x_auth_email", str3);
        cVar.c("x_auth_first_name", str4);
        cVar.c("x_auth_last_name", str5);
        cVar.c("x_auth_gender", str6);
        cVar.c("guest_cart_id", InstallInfo.m919a().m928e());
        if (str7 != null) {
            cVar.c("x_auth_external_account_birthday", str7);
        }
        if (str8 != null) {
            cVar.c("x_auth_external_account_avatar_url", str8);
        }
        cVar.c("x_auth_mode", "client_registration");
        cVar.c("environment_id", "1");
        cVar.c("device_udid", InstallInfo.m919a().m925b());
        cVar.c("device_version", InstallInfo.m919a().m929f());
        m808a(cVar, org.scribe.model.b.a);
        m811b(cVar);
        return m805a(cVar);
    }

    private ar m805a(c cVar) {
        ar arVar = new ar();
        EtsyGraphite.m1807a("xauth.attempt");
        try {
            h b = cVar.b();
            this.f1144b.a("Response body is: " + b.b());
            this.f1144b.a("Response code is: " + b.d());
            arVar.m1147a(this.f1145c, b);
        } catch (Throwable e) {
            EtsyDebug.m1917d(f1143a, "parseResponse OAuthException", e);
        }
        if (arVar.m1151e()) {
            EtsyGraphite.m1807a("xauth.success");
        } else {
            EtsyGraphite.m1807a("xauth.error");
        }
        return arVar;
    }

    private void m808a(c cVar, Token token) {
        cVar.a("oauth_timestamp", String.valueOf(EtsyXAuthServiceImpl.m809b() + this.f1146d));
        cVar.a("oauth_nonce", UUID.randomUUID().toString());
        cVar.a("oauth_consumer_key", this.f1144b.a());
        cVar.a("oauth_signature_method", this.f1145c.f().a());
        cVar.a("oauth_version", m818a());
        if (token != null) {
            cVar.a("oauth_signature", m810b(cVar, token));
        }
        this.f1144b.a("appended additional OAuth parameters: " + org.scribe.e.a.a(cVar.a()));
    }

    private String m810b(c cVar, Token token) {
        this.f1144b.a("generating signature...");
        String a = this.f1145c.d().a(cVar);
        String a2 = this.f1145c.f().a(a, this.f1144b.b(), token.getSecret());
        this.f1144b.a("base string is: " + a);
        this.f1144b.a("signature is: " + a2);
        return a2;
    }

    private void m811b(c cVar) {
        switch (EtsyXAuthServiceImpl.f1142a[this.f1144b.c().ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                this.f1144b.a("using Http Header signature");
                cVar.b("Authorization", this.f1145c.e().a(cVar));
            case Task.NETWORK_STATE_ANY /*2*/:
                this.f1144b.a("using Querystring signature");
                for (Entry entry : cVar.a().entrySet()) {
                    cVar.d((String) entry.getKey(), (String) entry.getValue());
                }
            default:
        }
    }

    public String m818a() {
        return BuildConfig.VERSION_NAME;
    }
}
