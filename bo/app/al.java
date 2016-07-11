package bo.app;

import com.appboy.enums.SocialNetwork;

public final /* synthetic */ class al {
    public static final /* synthetic */ int[] f75a;

    static {
        f75a = new int[SocialNetwork.values().length];
        try {
            f75a[SocialNetwork.FACEBOOK.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f75a[SocialNetwork.TWITTER.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f75a[SocialNetwork.GOOGLE.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
