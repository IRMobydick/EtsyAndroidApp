package com.google.android.gms.iid;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.iid.zzb.zza;

public class MessengerCompat implements Parcelable {
    public static final Creator<MessengerCompat> CREATOR;
    Messenger zzaUn;
    zzb zzaUo;

    /* renamed from: com.google.android.gms.iid.MessengerCompat.1 */
    final class C11521 implements Creator<MessengerCompat> {
        C11521() {
        }

        public MessengerCompat m6295a(Parcel parcel) {
            IBinder readStrongBinder = parcel.readStrongBinder();
            return readStrongBinder != null ? new MessengerCompat(readStrongBinder) : null;
        }

        public MessengerCompat[] m6296a(int i) {
            return new MessengerCompat[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m6295a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m6296a(i);
        }
    }

    static {
        CREATOR = new C11521();
    }

    public MessengerCompat(Handler handler) {
        if (VERSION.SDK_INT >= 21) {
            this.zzaUn = new Messenger(handler);
        } else {
            this.zzaUo = new C1154b(this, handler);
        }
    }

    public MessengerCompat(IBinder iBinder) {
        if (VERSION.SDK_INT >= 21) {
            this.zzaUn = new Messenger(iBinder);
        } else {
            this.zzaUo = zza.zzcl(iBinder);
        }
    }

    public static int zzc(Message message) {
        return VERSION.SDK_INT >= 21 ? zzd(message) : message.arg2;
    }

    @TargetApi(21)
    private static int zzd(Message message) {
        return message.sendingUid;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj != null) {
            try {
                z = getBinder().equals(((MessengerCompat) obj).getBinder());
            } catch (ClassCastException e) {
            }
        }
        return z;
    }

    public IBinder getBinder() {
        return this.zzaUn != null ? this.zzaUn.getBinder() : this.zzaUo.asBinder();
    }

    public int hashCode() {
        return getBinder().hashCode();
    }

    public void send(Message message) {
        if (this.zzaUn != null) {
            this.zzaUn.send(message);
        } else {
            this.zzaUo.send(message);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.zzaUn != null) {
            parcel.writeStrongBinder(this.zzaUn.getBinder());
        } else {
            parcel.writeStrongBinder(this.zzaUo.asBinder());
        }
    }
}
