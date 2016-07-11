package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.ads.internal.C1101o;

@jw
public class zzjx extends Handler {
    public zzjx(Looper looper) {
        super(looper);
    }

    public void handleMessage(Message message) {
        try {
            super.handleMessage(message);
        } catch (Throwable e) {
            C1101o.m6044h().m7021a(e, false);
            throw e;
        }
    }
}
