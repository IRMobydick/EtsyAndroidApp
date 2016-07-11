package com.google.android.gms.gcm;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public abstract class Task implements Parcelable {
    public static final int EXTRAS_LIMIT_BYTES = 10240;
    public static final int NETWORK_STATE_ANY = 2;
    public static final int NETWORK_STATE_CONNECTED = 0;
    public static final int NETWORK_STATE_UNMETERED = 1;
    protected static final long UNINITIALIZED = -1;
    private final Bundle mExtras;
    private final String mTag;
    private final String zzaTr;
    private final boolean zzaTs;
    private final boolean zzaTt;
    private final int zzaTu;
    private final boolean zzaTv;
    private final g zzaTw;

    @Deprecated
    Task(Parcel parcel) {
        boolean z = true;
        Log.e("Task", "Constructing a Task object using a parcel.");
        this.zzaTr = parcel.readString();
        this.mTag = parcel.readString();
        this.zzaTs = parcel.readInt() == NETWORK_STATE_UNMETERED;
        if (parcel.readInt() != NETWORK_STATE_UNMETERED) {
            z = false;
        }
        this.zzaTt = z;
        this.zzaTu = NETWORK_STATE_ANY;
        this.zzaTv = false;
        this.zzaTw = g.a;
        this.mExtras = null;
    }

    Task(d dVar) {
        this.zzaTr = dVar.b;
        this.mTag = dVar.c;
        this.zzaTs = dVar.d;
        this.zzaTt = dVar.e;
        this.zzaTu = dVar.a;
        this.zzaTv = dVar.f;
        this.mExtras = dVar.h;
        this.zzaTw = dVar.g != null ? dVar.g : g.a;
    }

    private static boolean zzE(Object obj) {
        return (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof String) || (obj instanceof Boolean);
    }

    public static void zzH(Bundle bundle) {
        if (bundle != null) {
            Parcel obtain = Parcel.obtain();
            bundle.writeToParcel(obtain, NETWORK_STATE_CONNECTED);
            int dataSize = obtain.dataSize();
            if (dataSize > EXTRAS_LIMIT_BYTES) {
                obtain.recycle();
                String valueOf = String.valueOf("Extras exceeding maximum size(10240 bytes): ");
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 11).append(valueOf).append(dataSize).toString());
            }
            obtain.recycle();
            for (String str : bundle.keySet()) {
                if (!zzE(bundle.get(str))) {
                    throw new IllegalArgumentException("Only the following extra parameter types are supported: Integer, Long, Double, String, and Boolean. ");
                }
            }
        }
    }

    public static void zza(g gVar) {
        if (gVar != null) {
            int a = gVar.a();
            if (a == NETWORK_STATE_UNMETERED || a == 0) {
                int b = gVar.b();
                int c = gVar.c();
                if (a == 0 && b < 0) {
                    throw new IllegalArgumentException("InitialBackoffSeconds can't be negative: " + b);
                } else if (a == NETWORK_STATE_UNMETERED && b < 10) {
                    throw new IllegalArgumentException("RETRY_POLICY_LINEAR must have an initial backoff at least 10 seconds.");
                } else if (c < b) {
                    throw new IllegalArgumentException("MaximumBackoffSeconds must be greater than InitialBackoffSeconds: " + gVar.c());
                } else {
                    return;
                }
            }
            throw new IllegalArgumentException("Must provide a valid RetryPolicy: " + a);
        }
    }

    public int describeContents() {
        return NETWORK_STATE_CONNECTED;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public int getRequiredNetwork() {
        return this.zzaTu;
    }

    public boolean getRequiresCharging() {
        return this.zzaTv;
    }

    public String getServiceName() {
        return this.zzaTr;
    }

    public String getTag() {
        return this.mTag;
    }

    public boolean isPersisted() {
        return this.zzaTt;
    }

    public boolean isUpdateCurrent() {
        return this.zzaTs;
    }

    public void toBundle(Bundle bundle) {
        bundle.putString("tag", this.mTag);
        bundle.putBoolean("update_current", this.zzaTs);
        bundle.putBoolean("persisted", this.zzaTt);
        bundle.putString(NotificationCompatApi21.CATEGORY_SERVICE, this.zzaTr);
        bundle.putInt("requiredNetwork", this.zzaTu);
        bundle.putBoolean("requiresCharging", this.zzaTv);
        bundle.putBundle("retryStrategy", this.zzaTw.a(new Bundle()));
        bundle.putBundle("extras", this.mExtras);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = NETWORK_STATE_UNMETERED;
        parcel.writeString(this.zzaTr);
        parcel.writeString(this.mTag);
        parcel.writeInt(this.zzaTs ? NETWORK_STATE_UNMETERED : NETWORK_STATE_CONNECTED);
        if (!this.zzaTt) {
            i2 = NETWORK_STATE_CONNECTED;
        }
        parcel.writeInt(i2);
    }
}
