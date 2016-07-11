package com.etsy.android.lib.core;

import android.content.Context;
import android.content.pm.Signature;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.etsy.android.lib.logger.EtsyDebug;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/* compiled from: PackageCheck */
public class af {
    public static boolean m1087a(Context context, String str) {
        Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
        try {
            byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
            JarFile jarFile = new JarFile(str);
            Certificate[] certificateArr = null;
            Enumeration entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) entries.nextElement();
                if (!(jarEntry.isDirectory() || jarEntry.getName().startsWith("META-INF/"))) {
                    Certificate[] a = m1089a(jarFile, jarEntry, bArr);
                    if (certificateArr != null) {
                        a = certificateArr;
                    }
                    certificateArr = a;
                }
            }
            jarFile.close();
            if (certificateArr != null && certificateArr.length > 0 && signatureArr != null && signatureArr.length > 0) {
                for (Signature toChars : signatureArr) {
                    String str2 = new String(toChars.toChars());
                    for (Certificate encoded : certificateArr) {
                        if (str2.equals(new String(m1088a(encoded.getEncoded())))) {
                            return true;
                        }
                    }
                }
            }
        } catch (Throwable e) {
            EtsyDebug.m1917d("TAG", "error getting package's certs", e);
        }
        return false;
    }

    private static Certificate[] m1089a(JarFile jarFile, JarEntry jarEntry, byte[] bArr) {
        try {
            InputStream inputStream = jarFile.getInputStream(jarEntry);
            do {
            } while (inputStream.read(bArr, 0, bArr.length) != -1);
            inputStream.close();
            return jarEntry.getCertificates();
        } catch (Throwable e) {
            EtsyDebug.m1913c("TAG", "Exception reading " + jarEntry.getName() + " in " + jarFile.getName(), e);
            return null;
        }
    }

    private static char[] m1088a(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[(length * 2)];
        for (int i = 0; i < length; i++) {
            byte b = bArr[i];
            int i2 = (b >> 4) & 15;
            cArr[i * 2] = (char) (i2 >= 10 ? (i2 + 97) - 10 : i2 + 48);
            i2 = b & 15;
            int i3 = (i * 2) + 1;
            if (i2 >= 10) {
                i2 = (i2 + 97) - 10;
            } else {
                i2 += 48;
            }
            cArr[i3] = (char) i2;
        }
        return cArr;
    }
}
