package bo.app;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;

public final class fi {
    public static boolean m347a(Context context, Class<?> cls) {
        return context.getPackageManager().queryIntentServices(new Intent().setClass(context, cls), AccessibilityNodeInfoCompat.ACTION_CUT).size() > 0;
    }
}
