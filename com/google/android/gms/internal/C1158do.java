package com.google.android.gms.internal;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import com.appboy.support.ValidationUtils;
import java.security.MessageDigest;

@jw
/* renamed from: com.google.android.gms.internal.do */
public class C1158do extends dk {
    private MessageDigest f4789b;

    byte m6405a(int i) {
        return (byte) ((((i & ValidationUtils.APPBOY_STRING_MAX_LENGTH) ^ ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) >> 8)) ^ ((16711680 & i) >> 16)) ^ ((ViewCompat.MEASURED_STATE_MASK & i) >> 24));
    }

    public byte[] m6406a(String str) {
        byte[] bArr;
        int i = 4;
        byte[] a = m6407a(str.split(" "));
        this.f4789b = m6394a();
        synchronized (this.a) {
            if (this.f4789b == null) {
                bArr = new byte[0];
            } else {
                this.f4789b.reset();
                this.f4789b.update(a);
                Object digest = this.f4789b.digest();
                if (digest.length <= 4) {
                    i = digest.length;
                }
                bArr = new byte[i];
                System.arraycopy(digest, 0, bArr, 0, bArr.length);
            }
        }
        return bArr;
    }

    byte[] m6407a(String[] strArr) {
        int i = 0;
        if (strArr.length == 1) {
            return dn.m6402b(dn.m6399a(strArr[0]));
        }
        if (strArr.length < 5) {
            byte[] bArr = new byte[(strArr.length * 2)];
            for (int i2 = 0; i2 < strArr.length; i2++) {
                byte[] b = m6408b(dn.m6399a(strArr[i2]));
                bArr[i2 * 2] = b[0];
                bArr[(i2 * 2) + 1] = b[1];
            }
            return bArr;
        }
        byte[] bArr2 = new byte[strArr.length];
        while (i < strArr.length) {
            bArr2[i] = m6405a(dn.m6399a(strArr[i]));
            i++;
        }
        return bArr2;
    }

    byte[] m6408b(int i) {
        int i2 = (SupportMenu.USER_MASK & i) ^ ((SupportMenu.CATEGORY_MASK & i) >> 16);
        return new byte[]{(byte) i2, (byte) (i2 >> 8)};
    }
}
