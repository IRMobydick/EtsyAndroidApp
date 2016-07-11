package com.google.android.gms.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.util.zzx;
import java.io.File;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/* renamed from: com.google.android.gms.iid.f */
public class C1157f {
    SharedPreferences f4738a;
    Context f4739b;

    public C1157f(Context context) {
        this(context, "com.google.android.gms.appid");
    }

    public C1157f(Context context, String str) {
        this.f4739b = context;
        this.f4738a = context.getSharedPreferences(str, 4);
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("-no-backup");
        m6330g(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    private String m6329b(String str, String str2, String str3) {
        String valueOf = String.valueOf("|T|");
        return new StringBuilder((((String.valueOf(str).length() + 1) + String.valueOf(valueOf).length()) + String.valueOf(str2).length()) + String.valueOf(str3).length()).append(str).append(valueOf).append(str2).append("|").append(str3).toString();
    }

    private void m6330g(String str) {
        File file = new File(zzx.getNoBackupFilesDir(this.f4739b), str);
        if (!file.exists()) {
            try {
                if (file.createNewFile() && !m6337a()) {
                    Log.i("InstanceID/Store", "App restored, clearing state");
                    InstanceIDListenerService.zza(this.f4739b, this);
                }
            } catch (IOException e) {
                if (Log.isLoggable("InstanceID/Store", 3)) {
                    String str2 = "InstanceID/Store";
                    String str3 = "Error creating file in no backup dir: ";
                    String valueOf = String.valueOf(e.getMessage());
                    Log.d(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                }
            }
        }
    }

    synchronized String m6331a(String str) {
        return this.f4738a.getString(str, null);
    }

    synchronized String m6332a(String str, String str2) {
        SharedPreferences sharedPreferences;
        String valueOf;
        sharedPreferences = this.f4738a;
        valueOf = String.valueOf("|S|");
        return sharedPreferences.getString(new StringBuilder(((String.valueOf(str).length() + 0) + String.valueOf(valueOf).length()) + String.valueOf(str2).length()).append(str).append(valueOf).append(str2).toString(), null);
    }

    public synchronized String m6333a(String str, String str2, String str3) {
        return this.f4738a.getString(m6329b(str, str2, str3), null);
    }

    synchronized KeyPair m6334a(String str, long j) {
        KeyPair a;
        a = c.a();
        Editor edit = this.f4738a.edit();
        m6335a(edit, str, "|P|", C1153a.m6300a(a.getPublic().getEncoded()));
        m6335a(edit, str, "|K|", C1153a.m6300a(a.getPrivate().getEncoded()));
        m6335a(edit, str, "cre", Long.toString(j));
        edit.commit();
        return a;
    }

    synchronized void m6335a(Editor editor, String str, String str2, String str3) {
        String valueOf = String.valueOf("|S|");
        editor.putString(new StringBuilder(((String.valueOf(str).length() + 0) + String.valueOf(valueOf).length()) + String.valueOf(str2).length()).append(str).append(valueOf).append(str2).toString(), str3);
    }

    public synchronized void m6336a(String str, String str2, String str3, String str4, String str5) {
        String b = m6329b(str, str2, str3);
        Editor edit = this.f4738a.edit();
        edit.putString(b, str4);
        edit.putString("appVersion", str5);
        edit.putString("lastToken", Long.toString(System.currentTimeMillis() / 1000));
        edit.commit();
    }

    public boolean m6337a() {
        return this.f4738a.getAll().isEmpty();
    }

    public synchronized void m6338b() {
        this.f4738a.edit().clear().commit();
    }

    public synchronized void m6339b(String str) {
        Editor edit = this.f4738a.edit();
        for (String str2 : this.f4738a.getAll().keySet()) {
            if (str2.startsWith(str)) {
                edit.remove(str2);
            }
        }
        edit.commit();
    }

    public KeyPair m6340c(String str) {
        return m6343f(str);
    }

    void m6341d(String str) {
        m6339b(String.valueOf(str).concat("|"));
    }

    public void m6342e(String str) {
        m6339b(String.valueOf(str).concat("|T|"));
    }

    KeyPair m6343f(String str) {
        Object e;
        String a = m6332a(str, "|P|");
        String a2 = m6332a(str, "|K|");
        if (a2 == null) {
            return null;
        }
        try {
            byte[] decode = Base64.decode(a, 8);
            byte[] decode2 = Base64.decode(a2, 8);
            KeyFactory instance = KeyFactory.getInstance("RSA");
            return new KeyPair(instance.generatePublic(new X509EncodedKeySpec(decode)), instance.generatePrivate(new PKCS8EncodedKeySpec(decode2)));
        } catch (InvalidKeySpecException e2) {
            e = e2;
            a = String.valueOf(e);
            Log.w("InstanceID/Store", new StringBuilder(String.valueOf(a).length() + 19).append("Invalid key stored ").append(a).toString());
            InstanceIDListenerService.zza(this.f4739b, this);
            return null;
        } catch (NoSuchAlgorithmException e3) {
            e = e3;
            a = String.valueOf(e);
            Log.w("InstanceID/Store", new StringBuilder(String.valueOf(a).length() + 19).append("Invalid key stored ").append(a).toString());
            InstanceIDListenerService.zza(this.f4739b, this);
            return null;
        }
    }
}
