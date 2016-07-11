package bo.app;

import android.util.Log;

public final class ip {
    private static volatile boolean f771a;
    private static volatile boolean f772b;

    static {
        f771a = false;
        f772b = true;
    }

    public static void m566a(boolean z) {
        f771a = z;
    }

    public static void m564a(String str, Object... objArr) {
        if (f771a) {
            m563a(3, null, str, objArr);
        }
    }

    public static void m567b(String str, Object... objArr) {
        m563a(4, null, str, objArr);
    }

    public static void m568c(String str, Object... objArr) {
        m563a(5, null, str, objArr);
    }

    public static void m565a(Throwable th) {
        m563a(6, th, null, new Object[0]);
    }

    public static void m569d(String str, Object... objArr) {
        m563a(6, null, str, objArr);
    }

    private static void m563a(int i, Throwable th, String str, Object... objArr) {
        if (f772b) {
            String format;
            if (objArr.length > 0) {
                format = String.format(str, objArr);
            } else {
                format = str;
            }
            if (th != null) {
                if (format == null) {
                    format = th.getMessage();
                }
                String stackTraceString = Log.getStackTraceString(th);
                format = String.format("%1$s\n%2$s", new Object[]{format, stackTraceString});
            }
            Log.println(i, gn.f589a, format);
        }
    }
}
