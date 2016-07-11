package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.zzo;
import com.google.android.gms.internal.jw;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;

@jw
public final class LargeParcelTeleporter extends AbstractSafeParcelable {
    public static final Creator<LargeParcelTeleporter> CREATOR;
    final int mVersionCode;
    ParcelFileDescriptor zzMq;
    private Parcelable zzMr;
    private boolean zzMs;

    static {
        CREATOR = new zzm();
    }

    LargeParcelTeleporter(int i, ParcelFileDescriptor parcelFileDescriptor) {
        this.mVersionCode = i;
        this.zzMq = parcelFileDescriptor;
        this.zzMr = null;
        this.zzMs = true;
    }

    public LargeParcelTeleporter(SafeParcelable safeParcelable) {
        this.mVersionCode = 1;
        this.zzMq = null;
        this.zzMr = safeParcelable;
        this.zzMs = false;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.zzMq == null) {
            Parcel obtain = Parcel.obtain();
            try {
                this.zzMr.writeToParcel(obtain, 0);
                byte[] marshall = obtain.marshall();
                this.zzMq = zzg(marshall);
            } finally {
                obtain.recycle();
            }
        }
        zzm.zza(this, parcel, i);
    }

    public <T extends SafeParcelable> T zza(Creator<T> creator) {
        if (this.zzMs) {
            if (this.zzMq == null) {
                C1129c.m6188b("File descriptor is empty, returning null.");
                return null;
            }
            Closeable dataInputStream = new DataInputStream(new AutoCloseInputStream(this.zzMq));
            try {
                byte[] bArr = new byte[dataInputStream.readInt()];
                dataInputStream.readFully(bArr, 0, bArr.length);
                zzo.zzb(dataInputStream);
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.unmarshall(bArr, 0, bArr.length);
                    obtain.setDataPosition(0);
                    this.zzMr = (SafeParcelable) creator.createFromParcel(obtain);
                    this.zzMs = false;
                } finally {
                    obtain.recycle();
                }
            } catch (Throwable e) {
                throw new IllegalStateException("Could not read from parcel file descriptor", e);
            } catch (Throwable th) {
                zzo.zzb(dataInputStream);
            }
        }
        return (SafeParcelable) this.zzMr;
    }

    protected <T> ParcelFileDescriptor zzg(byte[] bArr) {
        Throwable e;
        ParcelFileDescriptor parcelFileDescriptor = null;
        Closeable autoCloseOutputStream;
        try {
            ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
            autoCloseOutputStream = new AutoCloseOutputStream(createPipe[1]);
            try {
                new Thread(new 1(this, autoCloseOutputStream, bArr)).start();
                return createPipe[0];
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            e = e3;
            autoCloseOutputStream = parcelFileDescriptor;
            C1129c.m6189b("Error transporting the ad response", e);
            C1101o.m6044h().m7021a(e, true);
            zzo.zzb(autoCloseOutputStream);
            return parcelFileDescriptor;
        }
    }
}
