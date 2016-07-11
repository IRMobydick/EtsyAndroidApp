package com.google.android.gms.ads.internal.util.client;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.internal.jw;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@jw
/* renamed from: com.google.android.gms.ads.internal.util.client.d */
public class C1130d implements b {
    @Nullable
    private final String f4673a;

    public C1130d() {
        this(null);
    }

    public C1130d(@Nullable String str) {
        this.f4673a = str;
    }

    @WorkerThread
    public void m6194a(String str) {
        String valueOf;
        HttpURLConnection httpURLConnection;
        try {
            String str2 = "Pinging URL: ";
            valueOf = String.valueOf(str);
            C1129c.m6185a(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            C1089r.m5951a().m6176a(true, httpURLConnection, this.f4673a);
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode < Callback.DEFAULT_DRAG_ANIMATION_DURATION || responseCode >= 300) {
                C1129c.m6192d(new StringBuilder(String.valueOf(str).length() + 65).append("Received non-success response code ").append(responseCode).append(" from pinging URL: ").append(str).toString());
            }
            httpURLConnection.disconnect();
        } catch (IndexOutOfBoundsException e) {
            valueOf = String.valueOf(e.getMessage());
            C1129c.m6192d(new StringBuilder((String.valueOf(str).length() + 32) + String.valueOf(valueOf).length()).append("Error while parsing ping URL: ").append(str).append(". ").append(valueOf).toString());
        } catch (IOException e2) {
            valueOf = String.valueOf(e2.getMessage());
            C1129c.m6192d(new StringBuilder((String.valueOf(str).length() + 27) + String.valueOf(valueOf).length()).append("Error while pinging URL: ").append(str).append(". ").append(valueOf).toString());
        } catch (RuntimeException e3) {
            valueOf = String.valueOf(e3.getMessage());
            C1129c.m6192d(new StringBuilder((String.valueOf(str).length() + 27) + String.valueOf(valueOf).length()).append("Error while pinging URL: ").append(str).append(". ").append(valueOf).toString());
        } catch (Throwable th) {
            httpURLConnection.disconnect();
        }
    }
}
