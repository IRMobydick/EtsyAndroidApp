package com.google.android.gms.internal;

import android.graphics.Bitmap;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@jw
public class mp {
    Map<Integer, Bitmap> f5448a;
    private AtomicInteger f5449b;

    public mp() {
        this.f5448a = new ConcurrentHashMap();
        this.f5449b = new AtomicInteger(0);
    }

    public int m7185a(Bitmap bitmap) {
        if (bitmap == null) {
            C1129c.m6185a("Bitmap is null. Skipping putting into the Memory Map.");
            return -1;
        }
        this.f5448a.put(Integer.valueOf(this.f5449b.get()), bitmap);
        return this.f5449b.getAndIncrement();
    }

    public Bitmap m7186a(Integer num) {
        return (Bitmap) this.f5448a.get(num);
    }

    public void m7187b(Integer num) {
        this.f5448a.remove(num);
    }
}
