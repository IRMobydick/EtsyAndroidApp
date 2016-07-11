package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@jw
public class eb {
    BlockingQueue<ei> f4858a;
    ExecutorService f4859b;
    LinkedHashMap<String, String> f4860c;
    Map<String, ee> f4861d;
    String f4862e;
    final Context f4863f;
    final String f4864g;
    private AtomicBoolean f4865h;
    private File f4866i;

    public eb(Context context, String str, String str2, Map<String, String> map) {
        this.f4860c = new LinkedHashMap();
        this.f4861d = new HashMap();
        this.f4863f = context;
        this.f4864g = str;
        this.f4862e = str2;
        this.f4865h = new AtomicBoolean(false);
        this.f4865h.set(((Boolean) dz.f4810J.m6433c()).booleanValue());
        if (this.f4865h.get()) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory != null) {
                this.f4866i = new File(externalStorageDirectory, "sdk_csi_data.txt");
            }
        }
        for (Entry entry : map.entrySet()) {
            this.f4860c.put((String) entry.getKey(), (String) entry.getValue());
        }
        this.f4858a = new ArrayBlockingQueue(30);
        this.f4859b = Executors.newSingleThreadExecutor();
        this.f4859b.execute(new 1(this));
        this.f4861d.put("action", ee.f4868b);
        this.f4861d.put("ad_format", ee.f4868b);
        this.f4861d.put("e", ee.f4869c);
    }

    private void m6451a() {
        while (true) {
            try {
                ei eiVar = (ei) this.f4858a.take();
                String c = eiVar.m6481c();
                if (!TextUtils.isEmpty(c)) {
                    m6454a(m6457a(this.f4860c, eiVar.m6482d()), c);
                }
            } catch (Throwable e) {
                C1129c.m6193d("CsiReporter:reporter interrupted", e);
                return;
            }
        }
    }

    private void m6453a(File file, String str) {
        FileOutputStream fileOutputStream;
        Throwable e;
        if (file != null) {
            try {
                fileOutputStream = new FileOutputStream(file, true);
                try {
                    fileOutputStream.write(str.getBytes());
                    fileOutputStream.write(10);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                            return;
                        } catch (Throwable e2) {
                            C1129c.m6193d("CsiReporter: Cannot close file: sdk_csi_data.txt.", e2);
                            return;
                        }
                    }
                    return;
                } catch (IOException e3) {
                    e2 = e3;
                    try {
                        C1129c.m6193d("CsiReporter: Cannot write to file: sdk_csi_data.txt.", e2);
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                                return;
                            } catch (Throwable e22) {
                                C1129c.m6193d("CsiReporter: Cannot close file: sdk_csi_data.txt.", e22);
                                return;
                            }
                        }
                        return;
                    } catch (Throwable th) {
                        e22 = th;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable e4) {
                                C1129c.m6193d("CsiReporter: Cannot close file: sdk_csi_data.txt.", e4);
                            }
                        }
                        throw e22;
                    }
                }
            } catch (IOException e5) {
                e22 = e5;
                fileOutputStream = null;
                C1129c.m6193d("CsiReporter: Cannot write to file: sdk_csi_data.txt.", e22);
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                    return;
                }
                return;
            } catch (Throwable th2) {
                e22 = th2;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw e22;
            }
        }
        C1129c.m6192d("CsiReporter: File doesn't exists. Cannot write CSI data to file.");
    }

    private void m6454a(Map<String, String> map, String str) {
        String a = m6456a(this.f4862e, map, str);
        if (this.f4865h.get()) {
            m6453a(this.f4866i, a);
        } else {
            C1101o.m6041e().m7103a(this.f4863f, this.f4864g, a);
        }
    }

    public ee m6455a(String str) {
        ee eeVar = (ee) this.f4861d.get(str);
        return eeVar != null ? eeVar : ee.f4867a;
    }

    String m6456a(String str, Map<String, String> map, @NonNull String str2) {
        Builder buildUpon = Uri.parse(str).buildUpon();
        for (Entry entry : map.entrySet()) {
            buildUpon.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        StringBuilder stringBuilder = new StringBuilder(buildUpon.build().toString());
        stringBuilder.append("&").append("it").append("=").append(str2);
        return stringBuilder.toString();
    }

    Map<String, String> m6457a(Map<String, String> map, @Nullable Map<String, String> map2) {
        Map<String, String> linkedHashMap = new LinkedHashMap(map);
        if (map2 == null) {
            return linkedHashMap;
        }
        for (Entry entry : map2.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) linkedHashMap.get(str);
            linkedHashMap.put(str, m6455a(str).m6465a(str2, (String) entry.getValue()));
        }
        return linkedHashMap;
    }

    public void m6458a(List<String> list) {
        if (list != null && !list.isEmpty()) {
            this.f4860c.put("e", TextUtils.join(",", list));
        }
    }

    public boolean m6459a(ei eiVar) {
        return this.f4858a.offer(eiVar);
    }
}
