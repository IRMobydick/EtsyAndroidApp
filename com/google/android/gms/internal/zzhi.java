package com.google.android.gms.internal;

import android.content.Intent;
import android.os.IInterface;

public interface zzhi extends IInterface {
    void onActivityResult(int i, int i2, Intent intent);

    void onCreate();

    void onDestroy();
}
