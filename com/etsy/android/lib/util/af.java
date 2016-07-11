package com.etsy.android.lib.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.etsy.android.lib.logger.EtsyDebug;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Random;

/* compiled from: FileUtils */
public class af {
    private static final String f1998a;
    private static final Random f1999b;

    static {
        f1998a = EtsyDebug.m1891a(af.class);
        f1999b = new Random();
    }

    public static void m3201a(@NonNull File file, @NonNull String str, boolean z) {
        Throwable e;
        OutputStreamWriter outputStreamWriter = null;
        OutputStreamWriter outputStreamWriter2;
        try {
            outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream(file), Charset.forName(Constants.ENCODING));
            try {
                outputStreamWriter2.write(str);
                outputStreamWriter2.close();
                if (outputStreamWriter2 != null) {
                    try {
                        outputStreamWriter2.close();
                    } catch (Throwable e2) {
                        EtsyDebug.m1917d(f1998a, "Error closing file: " + file.getName(), e2);
                    }
                }
            } catch (IOException e3) {
                try {
                    EtsyDebug.m1919e(f1998a, "Error writing file: " + file.getName());
                    if (outputStreamWriter2 != null) {
                        try {
                            outputStreamWriter2.close();
                        } catch (Throwable e22) {
                            EtsyDebug.m1917d(f1998a, "Error closing file: " + file.getName(), e22);
                        }
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    outputStreamWriter = outputStreamWriter2;
                    e22 = th2;
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                        } catch (Throwable th3) {
                            EtsyDebug.m1917d(f1998a, "Error closing file: " + file.getName(), th3);
                        }
                    }
                    throw e22;
                }
            }
        } catch (IOException e4) {
            outputStreamWriter2 = null;
            EtsyDebug.m1919e(f1998a, "Error writing file: " + file.getName());
            if (outputStreamWriter2 != null) {
                outputStreamWriter2.close();
            }
        } catch (Throwable th4) {
            e22 = th4;
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
            throw e22;
        }
    }

    public static boolean m3203a(InputStream inputStream, File file) {
        return m3204a(m3205a(inputStream), file);
    }

    private static boolean m3204a(byte[] bArr, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath());
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            EtsyDebug.m1919e(f1998a, "exception writing stream to file");
            return false;
        }
    }

    private static byte[] m3205a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (IOException e) {
                EtsyDebug.m1919e(f1998a, "exception getting bytes for stream");
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static File m3198a(Context context, String str) {
        File file = new File(m3211c(context), str);
        return m3202a(file) ? file : null;
    }

    public static boolean m3202a(File file) {
        boolean exists = file.exists();
        if (exists) {
            return exists;
        }
        return file.mkdirs();
    }

    public static File m3197a(Context context) {
        return m3208b(m3206b(context));
    }

    public static File m3208b(File file) {
        File file2 = new File(file, m3199a());
        return m3213c(file2) ? file2 : null;
    }

    private static boolean m3213c(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (Throwable e) {
                EtsyDebug.m1907b(f1998a, "File not created", e);
            }
        }
        return file.exists();
    }

    public static File m3207b(Context context, String str) {
        File b = m3206b(context);
        if (b.exists() || b.mkdirs()) {
            File file = new File(b, m3200a(str));
            m3213c(file);
            return file;
        }
        EtsyDebug.m1912c(f1998a, "failed to create image directory");
        return null;
    }

    public static String m3200a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "etsyImage.jpg";
        }
        if (str.contains(".jpg")) {
            return str;
        }
        return str + ".jpg";
    }

    public static String m3199a() {
        return System.currentTimeMillis() + (f1999b.nextInt() % com.appboy.Constants.APPBOY_MINIMUM_NOTIFICATION_DURATION_MILLIS) + ".jpg";
    }

    private static File m3211c(Context context) {
        return m3209b() ? context.getExternalCacheDir() : context.getCacheDir();
    }

    public static File m3206b(@NonNull Context context) {
        if (m3209b()) {
            if (VERSION.SDK_INT < 22 || aw.m3273a(context, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Etsy");
            }
            for (File file : context.getExternalMediaDirs()) {
                if (file != null && new StatFs(file.getPath()).getAvailableBytes() > 15000000) {
                    return file;
                }
            }
        }
        return new File(context.getFilesDir(), "Etsy");
    }

    public static boolean m3209b() {
        return Environment.getExternalStorageState().equals("mounted") || !Environment.isExternalStorageRemovable();
    }

    public static boolean m3210b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String toLowerCase = str.toLowerCase(Locale.US);
        if (toLowerCase.endsWith(".jpg") || toLowerCase.endsWith(".jpeg") || toLowerCase.endsWith(".png") || toLowerCase.endsWith(".webp")) {
            return true;
        }
        return false;
    }

    public static void m3212c(Context context, String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            context.startActivity(intent);
        } catch (Throwable e) {
            EtsyDebug.m1917d(f1998a, "No application found to open url: " + str, e);
        }
    }

    public static Uri m3196a(Context context, int i) {
        return Uri.parse("android.resource://" + context.getPackageName() + "/" + i);
    }
}
