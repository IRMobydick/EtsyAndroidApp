package fs.org.apache.commons.codec.net;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.adjust.sdk.Constants;
import fs.org.apache.commons.codec.EncoderException;
import fs.org.apache.commons.codec.binary.StringUtils;
import java.io.ByteArrayOutputStream;
import java.util.BitSet;

public class URLCodec {
    protected static final BitSet WWW_FORM_URL;
    @Deprecated
    protected String charset;

    static {
        int i;
        WWW_FORM_URL = new BitSet(AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
        for (i = 97; i <= 122; i++) {
            WWW_FORM_URL.set(i);
        }
        for (i = 65; i <= 90; i++) {
            WWW_FORM_URL.set(i);
        }
        for (i = 48; i <= 57; i++) {
            WWW_FORM_URL.set(i);
        }
        WWW_FORM_URL.set(45);
        WWW_FORM_URL.set(95);
        WWW_FORM_URL.set(46);
        WWW_FORM_URL.set(42);
        WWW_FORM_URL.set(32);
    }

    public URLCodec() {
        this(Constants.ENCODING);
    }

    public URLCodec(String str) {
        this.charset = str;
    }

    public static final byte[] encodeUrl(BitSet bitSet, byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bitSet == null) {
            bitSet = WWW_FORM_URL;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i : bArr) {
            int i2;
            if (i2 < 0) {
                i2 += AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY;
            }
            if (bitSet.get(i2)) {
                if (i2 == 32) {
                    i2 = 43;
                }
                byteArrayOutputStream.write(i2);
            } else {
                byteArrayOutputStream.write(37);
                char toUpperCase = Character.toUpperCase(Character.forDigit((i2 >> 4) & 15, 16));
                char toUpperCase2 = Character.toUpperCase(Character.forDigit(i2 & 15, 16));
                byteArrayOutputStream.write(toUpperCase);
                byteArrayOutputStream.write(toUpperCase2);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] encode(byte[] bArr) {
        return encodeUrl(WWW_FORM_URL, bArr);
    }

    public String encode(String str, String str2) {
        if (str == null) {
            return null;
        }
        return StringUtils.newStringUsAscii(encode(str.getBytes(str2)));
    }

    public String encode(String str) {
        if (str == null) {
            return null;
        }
        try {
            return encode(str, getDefaultCharset());
        } catch (Throwable e) {
            throw new EncoderException(e.getMessage(), e);
        }
    }

    public String getDefaultCharset() {
        return this.charset;
    }
}
