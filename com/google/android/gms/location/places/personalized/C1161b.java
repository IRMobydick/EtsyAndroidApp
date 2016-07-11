package com.google.android.gms.location.places.personalized;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzd;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.location.places.k;

@Deprecated
/* renamed from: com.google.android.gms.location.places.personalized.b */
public final class C1161b extends zzd<PlaceUserData> implements Result {
    private final Status f5551a;

    public C1161b(DataHolder dataHolder) {
        this(dataHolder, k.b(dataHolder.getStatusCode()));
    }

    private C1161b(DataHolder dataHolder, Status status) {
        super(dataHolder, PlaceUserData.CREATOR);
        boolean z = dataHolder == null || dataHolder.getStatusCode() == status.getStatusCode();
        zzaa.zzaj(z);
        this.f5551a = status;
    }

    public Status getStatus() {
        return this.f5551a;
    }
}
