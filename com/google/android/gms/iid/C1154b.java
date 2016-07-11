package com.google.android.gms.iid;

import android.os.Binder;
import android.os.Handler;
import android.os.Message;
import com.google.android.gms.iid.zzb.zza;

/* renamed from: com.google.android.gms.iid.b */
final class C1154b extends zza {
    Handler f4720a;
    final /* synthetic */ MessengerCompat f4721b;

    C1154b(MessengerCompat messengerCompat, Handler handler) {
        this.f4721b = messengerCompat;
        this.f4720a = handler;
    }

    public void send(Message message) {
        message.arg2 = Binder.getCallingUid();
        this.f4720a.dispatchMessage(message);
    }
}
