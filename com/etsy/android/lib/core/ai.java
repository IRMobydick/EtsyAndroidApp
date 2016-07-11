package com.etsy.android.lib.core;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/* compiled from: RequestQueueFactory */
public class ai {
    private static RequestQueue f1423a;

    public static RequestQueue m1096a(Context context) {
        if (f1423a == null) {
            f1423a = Volley.newRequestQueue(context, 26214400);
        }
        return f1423a;
    }
}
