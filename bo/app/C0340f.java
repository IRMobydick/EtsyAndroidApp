package bo.app;

import com.appboy.enums.inappmessage.MessageType;

/* renamed from: bo.app.f */
public final /* synthetic */ class C0340f {
    public static final /* synthetic */ int[] f435a;

    static {
        f435a = new int[MessageType.values().length];
        try {
            f435a[MessageType.FULL.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f435a[MessageType.MODAL.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f435a[MessageType.SLIDEUP.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            f435a[MessageType.HTML_FULL.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}
