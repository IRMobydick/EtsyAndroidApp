package com.etsy.android.lib.logger;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLog.LogType;
import java.util.HashMap;

public class ViewClickAnalyticsLog extends AnalyticsLog {

    public enum ViewAction {
        clicked,
        long_clicked,
        tapped,
        checked,
        unchecked
    }

    public ViewClickAnalyticsLog(@NonNull String str, @NonNull ViewAction viewAction, @Nullable HashMap<AnalyticsLogAttribute, Object> hashMap, @NonNull ad adVar) {
        super(LogType.VIEW_CLICK, m1803a(str, viewAction), false, hashMap);
        m1804a(str, viewAction, adVar);
    }

    protected void m1804a(@NonNull String str, @NonNull ViewAction viewAction, @NonNull ad adVar) {
        m1784a(AnalyticsLogAttribute.VIEW_NAME, str);
        m1784a(AnalyticsLogAttribute.VIEW_ACTION, viewAction.name());
        m1788b((AnalyticsTracker) adVar);
    }

    @NonNull
    protected static String m1803a(@NonNull String str, @NonNull ViewAction viewAction) {
        return str + "_" + viewAction.name();
    }
}
