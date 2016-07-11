package com.etsy.android.lib.logger;

import android.support.annotation.NonNull;
import com.etsy.android.lib.config.EtsyConfigOption;
import com.etsy.android.lib.logger.AnalyticsLog.LogType;
import java.util.Arrays;
import java.util.HashMap;

/* renamed from: com.etsy.android.lib.logger.f */
public class ConfigAnalyticsLog extends AnalyticsLog {
    public ConfigAnalyticsLog(@NonNull EtsyConfigOption etsyConfigOption, @NonNull AnalyticsTracker analyticsTracker) {
        super(LogType.CONFIG_FLAG, "config_flag_checked", false);
        m1788b(analyticsTracker);
        m1879a(etsyConfigOption);
    }

    private void m1879a(@NonNull EtsyConfigOption etsyConfigOption) {
        HashMap hashMap = new HashMap();
        hashMap.put(etsyConfigOption.m912j(), Arrays.asList(new String[]{etsyConfigOption.m904b(), etsyConfigOption.m913k()}));
        m1784a(AnalyticsLogAttribute.AB, hashMap);
    }
}
