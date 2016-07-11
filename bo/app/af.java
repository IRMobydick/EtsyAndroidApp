package bo.app;

import com.appboy.enums.Gender;

public final /* synthetic */ class af {
    public static final /* synthetic */ int[] f54a;

    static {
        f54a = new int[Gender.values().length];
        try {
            f54a[Gender.MALE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f54a[Gender.FEMALE.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
    }
}
