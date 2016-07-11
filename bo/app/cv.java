package bo.app;

import com.appboy.enums.inappmessage.MessageType;

final /* synthetic */ class cv {
    static final /* synthetic */ int[] f267a;

    static {
        f267a = new int[MessageType.values().length];
        try {
            f267a[MessageType.FULL.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f267a[MessageType.MODAL.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f267a[MessageType.SLIDEUP.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            f267a[MessageType.HTML_FULL.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}
