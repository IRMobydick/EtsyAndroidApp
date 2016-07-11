package com.etsy.android.lib.util;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Base64;
import com.adjust.sdk.Constants;
import com.appboy.support.ValidationUtils;
import com.etsy.android.lib.logger.EtsyDebug;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.UUID;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: SecurityUtil */
public class ba {
    private static final String f2018a;

    static {
        f2018a = EtsyDebug.m1891a(ba.class);
    }

    public static String m3310a(String str) {
        MessageDigest instance = MessageDigest.getInstance(Constants.MD5);
        instance.update(String.valueOf(str).getBytes(Constants.ENCODING));
        byte[] digest = instance.digest();
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : digest) {
            stringBuilder.append(Integer.toString((b & ValidationUtils.APPBOY_STRING_MAX_LENGTH) + AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY, 16).substring(1));
        }
        return stringBuilder.toString();
    }

    public static String m3309a() {
        UUID randomUUID = UUID.randomUUID();
        ByteBuffer wrap = ByteBuffer.wrap(new byte[16]);
        wrap.putLong(randomUUID.getMostSignificantBits());
        wrap.putLong(randomUUID.getLeastSignificantBits());
        return Base64.encodeToString(wrap.array(), 11);
    }

    public static byte[] m3312a(String str, String str2, String str3) {
        byte[] bArr = new byte[0];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Key b = m3314b(str);
            byte[] a = m3311a(16);
            byte[] a2 = m3311a(32);
            byte[] a3 = m3311a(32);
            byteArrayOutputStream.reset();
            byteArrayOutputStream.write(a3);
            byteArrayOutputStream.write("MAC".getBytes(Constants.ENCODING));
            byte[] a4 = m3313a(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.reset();
            byteArrayOutputStream.write(a3);
            byteArrayOutputStream.write("encrypt".getBytes(Constants.ENCODING));
            a3 = m3313a(byteArrayOutputStream.toByteArray());
            byte[] a5 = m3311a(32);
            byteArrayOutputStream.reset();
            byteArrayOutputStream.write(str3.getBytes(Constants.ENCODING));
            byteArrayOutputStream.write(a2);
            byteArrayOutputStream.write(a);
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            Mac instance = Mac.getInstance("HmacSHA256");
            instance.init(new SecretKeySpec(a4, "HmacSHA256"));
            toByteArray = instance.doFinal(toByteArray);
            byteArrayOutputStream.reset();
            byteArrayOutputStream.write(str3.getBytes(Constants.ENCODING));
            byteArrayOutputStream.write(toByteArray);
            byte[] toByteArray2 = byteArrayOutputStream.toByteArray();
            if (toByteArray.length == 32 && a.length == 16 && a2.length == 32 && a5.length == 32 && a3.length == 32 && a4.length == 32) {
                AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(a);
                Key secretKeySpec = new SecretKeySpec(a3, "AES");
                Cipher instance2 = Cipher.getInstance("AES/CBC/PKCS5Padding");
                instance2.init(1, secretKeySpec, ivParameterSpec);
                toByteArray = instance2.doFinal(toByteArray2);
                byteArrayOutputStream.reset();
                byteArrayOutputStream.write(a3);
                byteArrayOutputStream.write(a);
                byteArrayOutputStream.write(a2);
                byteArrayOutputStream.write(a4);
                byteArrayOutputStream.write(a5);
                a = byteArrayOutputStream.toByteArray();
                Cipher instance3 = Cipher.getInstance("RSA/NONE/OAEPWithSHA1AndMGF1Padding", "BC");
                instance3.init(1, b);
                instance3.update(a);
                byte[] doFinal = instance3.doFinal(a);
                a = "|".getBytes(Constants.ENCODING);
                byteArrayOutputStream.reset();
                byteArrayOutputStream.write(Base64.encode(toByteArray, 2));
                byteArrayOutputStream.write(a);
                byteArrayOutputStream.write(Base64.encode(doFinal, 2));
                byteArrayOutputStream.write(a);
                byteArrayOutputStream.write(str2.getBytes(Constants.ENCODING));
                bArr = byteArrayOutputStream.toByteArray();
                return bArr;
            }
            throw new InstantiationException("One of the key lengths is incorrect");
        } catch (IOException e) {
            EtsyDebug.m1900a(f2018a, e);
        } catch (NoSuchAlgorithmException e2) {
            EtsyDebug.m1900a(f2018a, e2);
        } catch (InvalidKeyException e3) {
            EtsyDebug.m1900a(f2018a, e3);
        } catch (NoSuchPaddingException e4) {
            EtsyDebug.m1900a(f2018a, e4);
        } catch (IllegalBlockSizeException e5) {
            EtsyDebug.m1900a(f2018a, e5);
        } catch (BadPaddingException e6) {
            EtsyDebug.m1900a(f2018a, e6);
        } catch (InvalidKeySpecException e7) {
            EtsyDebug.m1900a(f2018a, e7);
        } catch (InvalidAlgorithmParameterException e8) {
            EtsyDebug.m1900a(f2018a, e8);
        } catch (NoSuchProviderException e9) {
            EtsyDebug.m1900a(f2018a, e9);
        }
    }

    static byte[] m3311a(int i) {
        byte[] bArr = new byte[i];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    static byte[] m3313a(byte[] bArr) {
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        instance.reset();
        instance.update(bArr);
        return instance.digest();
    }

    static PublicKey m3314b(String str) {
        String trim = str.trim();
        String str2 = "-----BEGIN PUBLIC KEY-----";
        String str3 = "-----END PUBLIC KEY-----";
        if (trim.startsWith(str2) && trim.endsWith(str3)) {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(trim.substring(str2.length(), trim.length() - str3.length()), 0)));
        }
        throw new IOException("PUBLIC KEY not found");
    }
}
