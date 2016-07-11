package com.google.ads.mediation;

import com.google.android.gms.ads.internal.util.client.C1129c;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@Deprecated
public abstract class MediationServerParameters {
    protected void m5810a() {
    }

    public void m5811a(Map<String, String> map) {
        String str;
        Map hashMap = new HashMap();
        for (Field field : getClass().getFields()) {
            C1072m c1072m = (C1072m) field.getAnnotation(C1072m.class);
            if (c1072m != null) {
                hashMap.put(c1072m.m5844a(), field);
            }
        }
        if (hashMap.isEmpty()) {
            C1129c.m6192d("No server options fields detected. To suppress this message either add a field with the @Parameter annotation, or override the load() method.");
        }
        for (Entry entry : map.entrySet()) {
            Field field2 = (Field) hashMap.remove(entry.getKey());
            if (field2 != null) {
                try {
                    field2.set(this, entry.getValue());
                } catch (IllegalAccessException e) {
                    str = (String) entry.getKey();
                    C1129c.m6192d(new StringBuilder(String.valueOf(str).length() + 49).append("Server option \"").append(str).append("\" could not be set: Illegal Access").toString());
                } catch (IllegalArgumentException e2) {
                    str = (String) entry.getKey();
                    C1129c.m6192d(new StringBuilder(String.valueOf(str).length() + 43).append("Server option \"").append(str).append("\" could not be set: Bad Type").toString());
                }
            } else {
                str = (String) entry.getKey();
                String str2 = (String) entry.getValue();
                C1129c.m6185a(new StringBuilder((String.valueOf(str).length() + 31) + String.valueOf(str2).length()).append("Unexpected server option: ").append(str).append(" = \"").append(str2).append("\"").toString());
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field22 : hashMap.values()) {
            if (((C1072m) field22.getAnnotation(C1072m.class)).m5845b()) {
                String str3 = "Required server option missing: ";
                str2 = String.valueOf(((C1072m) field22.getAnnotation(C1072m.class)).m5844a());
                C1129c.m6192d(str2.length() != 0 ? str3.concat(str2) : new String(str3));
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(((C1072m) field22.getAnnotation(C1072m.class)).m5844a());
            }
        }
        if (stringBuilder.length() > 0) {
            String str4 = "Required server option(s) missing: ";
            str = String.valueOf(stringBuilder.toString());
            throw new MappingException(str.length() != 0 ? str4.concat(str) : new String(str4));
        }
        m5810a();
    }
}
