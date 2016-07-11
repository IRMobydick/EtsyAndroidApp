package com.yozio.android;

import android.content.Context;
import android.content.Intent;
import com.yozio.android.b.b;
import java.util.HashMap;

/* renamed from: com.yozio.android.a */
public class Yozio {
    public static boolean f5577a;
    public static int f5578b;
    public static int f5579c;

    static {
        f5577a = true;
        f5578b = 15000;
        f5579c = 15000;
    }

    public static void m7486a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("!!!!!!!!!!!!!!!!!! Context cannot be null.");
        }
        b.a().a(context);
    }

    public static void m7487a(Context context, String str, HashMap<String, Object> hashMap) {
        b.a().a(context, str, hashMap);
    }

    public static HashMap<String, Object> m7485a(Intent intent) {
        return b.a().b(intent);
    }
}
