package com.google.android.gms.ads.internal.purchase;

import android.text.TextUtils;
import android.util.Base64;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.internal.jw;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.X509EncodedKeySpec;

@jw
/* renamed from: com.google.android.gms.ads.internal.purchase.f */
public class C1116f {
    public static PublicKey m6106a(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (Throwable e2) {
            C1129c.m6188b("Invalid key specification.");
            throw new IllegalArgumentException(e2);
        }
    }

    public static boolean m6107a(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3)) {
            return C1116f.m6108a(C1116f.m6106a(str), str2, str3);
        }
        C1129c.m6188b("Purchase verification failed: missing data.");
        return false;
    }

    public static boolean m6108a(PublicKey publicKey, String str, String str2) {
        try {
            Signature instance = Signature.getInstance("SHA1withRSA");
            instance.initVerify(publicKey);
            instance.update(str.getBytes());
            if (instance.verify(Base64.decode(str2, 0))) {
                return true;
            }
            C1129c.m6188b("Signature verification failed.");
            return false;
        } catch (NoSuchAlgorithmException e) {
            C1129c.m6188b("NoSuchAlgorithmException.");
            return false;
        } catch (InvalidKeyException e2) {
            C1129c.m6188b("Invalid key specification.");
            return false;
        } catch (SignatureException e3) {
            C1129c.m6188b("Signature exception.");
            return false;
        }
    }
}
