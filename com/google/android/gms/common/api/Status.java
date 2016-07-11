package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;

public final class Status extends AbstractSafeParcelable implements Result {
    public static final Creator<Status> CREATOR;
    public static final Status zzalA;
    public static final Status zzalB;
    public static final Status zzalw;
    public static final Status zzalx;
    public static final Status zzaly;
    public static final Status zzalz;
    private final PendingIntent mPendingIntent;
    private final int mVersionCode;
    private final int zzahG;
    private final String zzakk;

    static {
        zzalw = new Status(0);
        zzalx = new Status(14);
        zzaly = new Status(8);
        zzalz = new Status(15);
        zzalA = new Status(16);
        zzalB = new Status(17);
        CREATOR = new zzf();
    }

    public Status(int i) {
        this(i, null);
    }

    Status(int i, int i2, String str, PendingIntent pendingIntent) {
        this.mVersionCode = i;
        this.zzahG = i2;
        this.zzakk = str;
        this.mPendingIntent = pendingIntent;
    }

    public Status(int i, String str) {
        this(1, i, str, null);
    }

    public Status(int i, String str, PendingIntent pendingIntent) {
        this(1, i, str, pendingIntent);
    }

    private String zzry() {
        return this.zzakk != null ? this.zzakk : CommonStatusCodes.getStatusCodeString(this.zzahG);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.mVersionCode == status.mVersionCode && this.zzahG == status.zzahG && zzz.equal(this.zzakk, status.zzakk) && zzz.equal(this.mPendingIntent, status.mPendingIntent);
    }

    public PendingIntent getResolution() {
        return this.mPendingIntent;
    }

    public Status getStatus() {
        return this;
    }

    public int getStatusCode() {
        return this.zzahG;
    }

    @Nullable
    public String getStatusMessage() {
        return this.zzakk;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public boolean hasResolution() {
        return this.mPendingIntent != null;
    }

    public int hashCode() {
        return zzz.hashCode(new Object[]{Integer.valueOf(this.mVersionCode), Integer.valueOf(this.zzahG), this.zzakk, this.mPendingIntent});
    }

    public boolean isCanceled() {
        return this.zzahG == 16;
    }

    public boolean isInterrupted() {
        return this.zzahG == 14;
    }

    public boolean isSuccess() {
        return this.zzahG <= 0;
    }

    public void startResolutionForResult(Activity activity, int i) {
        if (hasResolution()) {
            activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), i, null, 0, 0, 0);
        }
    }

    public String toString() {
        return zzz.zzy(this).zzg("statusCode", zzry()).zzg("resolution", this.mPendingIntent).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }

    PendingIntent zzrx() {
        return this.mPendingIntent;
    }
}
