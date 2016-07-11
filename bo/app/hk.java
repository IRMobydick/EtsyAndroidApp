package bo.app;

import android.widget.ImageView;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

public final class hk {
    public static final int f719a;
    public static final int f720b;
    private static final /* synthetic */ int[] f721c;

    public static int[] m498a() {
        return (int[]) f721c.clone();
    }

    static {
        f719a = 1;
        f720b = 2;
        f721c = new int[]{f719a, f720b};
    }

    public static int m497a(ImageView imageView) {
        switch (hl.f722a[imageView.getScaleType().ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
            case Task.NETWORK_STATE_ANY /*2*/:
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                return f719a;
            default:
                return f720b;
        }
    }
}
