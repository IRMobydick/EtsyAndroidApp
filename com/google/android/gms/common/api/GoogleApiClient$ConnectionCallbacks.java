package com.google.android.gms.common.api;

import android.os.Bundle;
import android.support.annotation.Nullable;

public interface GoogleApiClient$ConnectionCallbacks {
    public static final int CAUSE_NETWORK_LOST = 2;
    public static final int CAUSE_SERVICE_DISCONNECTED = 1;

    void onConnected(@Nullable Bundle bundle);

    void onConnectionSuspended(int i);
}
