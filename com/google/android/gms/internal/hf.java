package com.google.android.gms.internal;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONObject;

@jw
public class hf implements he {
    private final hd f4985a;
    private final HashSet<SimpleEntry<String, fk>> f4986b;

    public hf(hd hdVar) {
        this.f4985a = hdVar;
        this.f4986b = new HashSet();
    }

    public void m6652a() {
        Iterator it = this.f4986b.iterator();
        while (it.hasNext()) {
            SimpleEntry simpleEntry = (SimpleEntry) it.next();
            String str = "Unregistering eventhandler: ";
            String valueOf = String.valueOf(((fk) simpleEntry.getValue()).toString());
            lo.m7056e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.f4985a.b((String) simpleEntry.getKey(), (fk) simpleEntry.getValue());
        }
        this.f4986b.clear();
    }

    public void m6653a(String str, fk fkVar) {
        this.f4985a.a(str, fkVar);
        this.f4986b.add(new SimpleEntry(str, fkVar));
    }

    public void m6654a(String str, String str2) {
        this.f4985a.a(str, str2);
    }

    public void m6655a(String str, JSONObject jSONObject) {
        this.f4985a.a(str, jSONObject);
    }

    public void m6656b(String str, fk fkVar) {
        this.f4985a.b(str, fkVar);
        this.f4986b.remove(new SimpleEntry(str, fkVar));
    }

    public void m6657b(String str, JSONObject jSONObject) {
        this.f4985a.b(str, jSONObject);
    }
}
