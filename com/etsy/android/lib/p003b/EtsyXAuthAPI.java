package com.etsy.android.lib.p003b;

import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import org.scribe.a.a.b;
import org.scribe.model.Verb;
import org.scribe.model.a;

/* renamed from: com.etsy.android.lib.b.a */
public class EtsyXAuthAPI extends b {
    public /* synthetic */ org.scribe.c.b m803b(a aVar) {
        return m801a(aVar);
    }

    public String m802a() {
        return EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.cA) + "/access_token";
    }

    public Verb m804b() {
        return Verb.POST;
    }

    public EtsyXAuthServiceImpl m801a(a aVar) {
        return new EtsyXAuthServiceImpl(this, aVar);
    }
}
