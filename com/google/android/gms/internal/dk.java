package com.google.android.gms.internal;

import com.adjust.sdk.Constants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@jw
public abstract class dk {
    private static MessageDigest f4783b;
    protected Object f4784a;

    static {
        f4783b = null;
    }

    public dk() {
        this.f4784a = new Object();
    }

    protected MessageDigest m6394a() {
        MessageDigest messageDigest;
        synchronized (this.f4784a) {
            if (f4783b != null) {
                messageDigest = f4783b;
            } else {
                for (int i = 0; i < 2; i++) {
                    try {
                        f4783b = MessageDigest.getInstance(Constants.MD5);
                    } catch (NoSuchAlgorithmException e) {
                    }
                }
                messageDigest = f4783b;
            }
        }
        return messageDigest;
    }

    abstract byte[] m6395a(String str);
}
