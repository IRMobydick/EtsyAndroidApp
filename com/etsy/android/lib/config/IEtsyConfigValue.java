package com.etsy.android.lib.config;

import android.support.annotation.NonNull;
import com.etsy.android.lib.config.EtsyConfigKey.Environment;

/* renamed from: com.etsy.android.lib.config.p */
public interface IEtsyConfigValue {
    @NonNull
    EtsyConfigOption m821a(Environment environment, boolean z);

    @NonNull
    String m822a();
}
