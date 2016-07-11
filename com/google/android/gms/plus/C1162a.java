package com.google.android.gms.plus;

import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

/* renamed from: com.google.android.gms.plus.a */
public interface C1162a {
    PendingResult<Status> m7454a(GoogleApiClient googleApiClient);

    @Deprecated
    void m7455b(GoogleApiClient googleApiClient);

    @RequiresPermission("android.permission.GET_ACCOUNTS")
    String m7456c(GoogleApiClient googleApiClient);
}
