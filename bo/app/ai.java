package bo.app;

import com.appboy.enums.NotificationSubscriptionType;

public final /* synthetic */ class ai {
    public static final /* synthetic */ int[] f67a;

    static {
        f67a = new int[NotificationSubscriptionType.values().length];
        try {
            f67a[NotificationSubscriptionType.UNSUBSCRIBED.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f67a[NotificationSubscriptionType.SUBSCRIBED.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f67a[NotificationSubscriptionType.OPTED_IN.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
