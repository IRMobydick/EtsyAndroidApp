package bo.app;

import com.etsy.android.lib.models.finds.FindsModule;

public enum am {
    ANDROID_VERSION("android_version"),
    MODEL("model"),
    DISPLAY("display"),
    DEVICE_IDENTIFIERS("device_identifiers"),
    DEVICE_TYPE(FindsModule.FIELD_TYPE);
    
    public final String f82f;

    private am(String str) {
        this.f82f = str;
    }
}
