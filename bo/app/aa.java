package bo.app;

import com.appboy.enums.AppStore;

public final /* synthetic */ class aa {
    public static final /* synthetic */ int[] f10a;

    static {
        f10a = new int[AppStore.values().length];
        try {
            f10a[AppStore.GOOGLE_PLAY_STORE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f10a[AppStore.KINDLE_STORE.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
    }
}
