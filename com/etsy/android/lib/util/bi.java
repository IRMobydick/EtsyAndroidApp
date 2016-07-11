package com.etsy.android.lib.util;

import android.widget.TextView;
import java.util.HashMap;

/* compiled from: TextViewErrorMapper */
public class bi {
    private HashMap<String, TextView> f2030a;

    private bi() {
        this.f2030a = new HashMap();
    }

    public void m3345a(String str, TextView textView) {
        this.f2030a.put(str, textView);
    }

    public TextView m3344a(String str, String str2) {
        if (!this.f2030a.containsKey(str)) {
            return null;
        }
        TextView textView = (TextView) this.f2030a.get(str);
        textView.setError(str2);
        return textView;
    }
}
