package com.google.android.gms.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;

/* renamed from: com.google.android.gms.iid.d */
class C1155d implements zzb {
    private IBinder f4722a;

    C1155d(IBinder iBinder) {
        this.f4722a = iBinder;
    }

    public IBinder asBinder() {
        return this.f4722a;
    }

    public void send(Message message) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.iid.IMessengerCompat");
            if (message != null) {
                obtain.writeInt(1);
                message.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.f4722a.transact(1, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }
}
