package com.facebook.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.util.Log;
import com.adjust.sdk.Constants;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/* compiled from: NativeAppCallAttachmentStore */
public final class af {
    private static final String f4308a;
    private static File f4309b;

    static {
        f4308a = af.class.getName();
    }

    private af() {
    }

    public static ag m5727a(UUID uuid, Bitmap bitmap) {
        av.a(uuid, "callId");
        av.a(bitmap, "attachmentBitmap");
        return new ag(uuid, bitmap, null, null);
    }

    public static ag m5728a(UUID uuid, Uri uri) {
        av.a(uuid, "callId");
        av.a(uri, "attachmentUri");
        return new ag(uuid, null, uri, null);
    }

    private static void m5733a(Bitmap bitmap, File file) {
        Closeable fileOutputStream = new FileOutputStream(file);
        try {
            bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
        } finally {
            aq.a(fileOutputStream);
        }
    }

    private static void m5734a(Uri uri, boolean z, File file) {
        InputStream openInputStream;
        Closeable fileOutputStream = new FileOutputStream(file);
        if (z) {
            openInputStream = FacebookSdk.m5784f().getContentResolver().openInputStream(uri);
        } else {
            try {
                openInputStream = new FileInputStream(uri.getPath());
            } catch (Throwable th) {
                aq.a(fileOutputStream);
            }
        }
        aq.a(openInputStream, fileOutputStream);
        aq.a(fileOutputStream);
    }

    public static void m5735a(Collection<ag> collection) {
        if (collection != null && collection.size() != 0) {
            if (f4309b == null) {
                m5738c();
            }
            m5737b();
            List<File> arrayList = new ArrayList();
            try {
                for (ag agVar : collection) {
                    if (ag.a(agVar)) {
                        File a = m5731a(ag.b(agVar), ag.c(agVar), true);
                        arrayList.add(a);
                        if (ag.d(agVar) != null) {
                            m5733a(ag.d(agVar), a);
                        } else if (ag.e(agVar) != null) {
                            m5734a(ag.e(agVar), ag.f(agVar), a);
                        }
                    }
                }
            } catch (Throwable e) {
                Throwable th = e;
                Log.e(f4308a, "Got unexpected exception:" + th);
                for (File delete : arrayList) {
                    try {
                        delete.delete();
                    } catch (Exception e2) {
                    }
                }
                throw new FacebookException(th);
            }
        }
    }

    public static void m5736a(UUID uuid) {
        File a = m5732a(uuid, false);
        if (a != null) {
            aq.a(a);
        }
    }

    public static File m5730a(UUID uuid, String str) {
        if (aq.a(str) || uuid == null) {
            throw new FileNotFoundException();
        }
        try {
            return m5731a(uuid, str, false);
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    static synchronized File m5729a() {
        File file;
        synchronized (af.class) {
            if (f4309b == null) {
                f4309b = new File(FacebookSdk.m5784f().getCacheDir(), "com.facebook.NativeAppCallAttachmentStore.files");
            }
            file = f4309b;
        }
        return file;
    }

    static File m5737b() {
        File a = m5729a();
        a.mkdirs();
        return a;
    }

    static File m5732a(UUID uuid, boolean z) {
        if (f4309b == null) {
            return null;
        }
        File file = new File(f4309b, uuid.toString());
        if (!z || file.exists()) {
            return file;
        }
        file.mkdirs();
        return file;
    }

    static File m5731a(UUID uuid, String str, boolean z) {
        File a = m5732a(uuid, z);
        if (a == null) {
            return null;
        }
        try {
            return new File(a, URLEncoder.encode(str, Constants.ENCODING));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static void m5738c() {
        aq.a(m5729a());
    }
}
