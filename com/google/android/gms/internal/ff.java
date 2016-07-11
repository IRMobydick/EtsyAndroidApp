package com.google.android.gms.internal;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.Map;

@jw
public final class ff implements fk {
    private final fg f4898a;

    public ff(fg fgVar) {
        this.f4898a = fgVar;
    }

    public void m6514a(no noVar, Map<String, String> map) {
        String str = (String) map.get(ResponseConstants.NAME);
        if (str == null) {
            C1129c.m6192d("App event with no name parameter.");
        } else {
            this.f4898a.onAppEvent(str, (String) map.get(BaseMessage.TYPE_INFO));
        }
    }
}
