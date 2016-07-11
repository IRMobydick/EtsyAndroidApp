package com.google.android.gms.internal;

import android.os.Parcel;
import android.util.Base64;
import com.adjust.sdk.Constants;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.commons.lang3.StringUtils;

@jw
class gr {
    final AdRequestParcel f4973a;
    final String f4974b;
    final int f4975c;

    gr(AdRequestParcel adRequestParcel, String str, int i) {
        this.f4973a = adRequestParcel;
        this.f4974b = str;
        this.f4975c = i;
    }

    gr(gp gpVar) {
        this(gpVar.m6609a(), gpVar.m6614c(), gpVar.m6613b());
    }

    gr(String str) {
        String[] split = str.split("\u0000");
        if (split.length != 3) {
            throw new IOException("Incorrect field count for QueueSeed.");
        }
        Parcel obtain = Parcel.obtain();
        try {
            this.f4974b = new String(Base64.decode(split[0], 0), Constants.ENCODING);
            this.f4975c = Integer.parseInt(split[1]);
            byte[] decode = Base64.decode(split[2], 0);
            obtain.unmarshall(decode, 0, decode.length);
            obtain.setDataPosition(0);
            this.f4973a = (AdRequestParcel) AdRequestParcel.CREATOR.createFromParcel(obtain);
            obtain.recycle();
        } catch (IllegalArgumentException e) {
            throw new IOException("Malformed QueueSeed encoding.");
        } catch (Throwable th) {
            obtain.recycle();
        }
    }

    String m6620a() {
        String encodeToString;
        Parcel obtain = Parcel.obtain();
        try {
            encodeToString = Base64.encodeToString(this.f4974b.getBytes(Constants.ENCODING), 0);
            String num = Integer.toString(this.f4975c);
            this.f4973a.writeToParcel(obtain, 0);
            String encodeToString2 = Base64.encodeToString(obtain.marshall(), 0);
            encodeToString = new StringBuilder(((String.valueOf(encodeToString).length() + 2) + String.valueOf(num).length()) + String.valueOf(encodeToString2).length()).append(encodeToString).append("\u0000").append(num).append("\u0000").append(encodeToString2).toString();
            return encodeToString;
        } catch (UnsupportedEncodingException e) {
            encodeToString = "QueueSeed encode failed because UTF-8 is not available.";
            C1129c.m6188b(encodeToString);
            return StringUtils.EMPTY;
        } finally {
            obtain.recycle();
        }
    }
}
