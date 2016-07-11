package bo.app;

import com.adjust.sdk.Constants;
import java.math.BigInteger;
import java.security.MessageDigest;

public final class ge implements gc {
    public final String m422a(String str) {
        return new BigInteger(m421a(str.getBytes())).abs().toString(36);
    }

    private static byte[] m421a(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            MessageDigest instance = MessageDigest.getInstance(Constants.MD5);
            instance.update(bArr);
            bArr2 = instance.digest();
        } catch (Throwable e) {
            ip.m565a(e);
        }
        return bArr2;
    }
}
