package com.google.android.gms.internal;

import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public class rm extends zznv<Status> {
    @Deprecated
    public rm(Looper looper) {
        super(looper);
    }

    public rm(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    protected Status m7338a(Status status) {
        return status;
    }

    protected /* synthetic */ Result zzc(Status status) {
        return m7338a(status);
    }
}
