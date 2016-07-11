package bo.app;

import com.appboy.configuration.XmlAppConfigurationProvider;
import com.foresee.mobileReplay.http.FsServiceClientImpl;
import java.util.HashMap;
import java.util.Map;

/* renamed from: bo.app.g */
public final class C0341g implements C0338j {
    public String f525a;
    private final cg f526b;
    private final XmlAppConfigurationProvider f527c;
    private String f528d;
    private String f529e;

    public C0341g(XmlAppConfigurationProvider xmlAppConfigurationProvider, cg cgVar) {
        this.f527c = xmlAppConfigurationProvider;
        this.f526b = cgVar;
    }

    public final Map<String, String> m416a() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("Accept-Encoding", "gzip, deflate");
        hashMap.put("Content-Type", FsServiceClientImpl.CONTENT_TYPE);
        if (m415c() != null) {
            hashMap.put("X-Appboy-Api-Key", m415c());
        }
        if (!fj.m354c(this.f525a)) {
            hashMap.put("X-Appboy-User-Identifier", this.f525a);
        }
        hashMap.put("X-Appboy-Device-Identifier", m414b());
        return hashMap;
    }

    private synchronized String m414b() {
        if (this.f528d == null) {
            this.f528d = this.f526b.m94e();
        }
        return this.f528d;
    }

    private synchronized String m415c() {
        if (this.f529e == null) {
            this.f529e = this.f527c.getAppboyApiKey().toString();
        }
        return this.f529e;
    }
}
