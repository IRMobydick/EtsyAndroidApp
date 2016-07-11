package bo.app;

import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.google.android.gms.gcm.Task;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public final class fd {
    private static final String f441a;
    private static final Locale f442b;
    private static final TimeZone f443c;

    static {
        f441a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, fd.class.getName()});
        f442b = Locale.US;
        f443c = TimeZone.getTimeZone("UTC");
    }

    public static long m330a() {
        return System.currentTimeMillis() / 1000;
    }

    public static double m334b() {
        return ((double) System.currentTimeMillis()) / 1000.0d;
    }

    public static long m335c() {
        return System.currentTimeMillis();
    }

    public static String m331a(Date date, ab abVar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.setTimeZone(f443c);
        switch (fe.f444a[abVar.ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                simpleDateFormat.applyPattern(ab.SHORT.f14c);
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                simpleDateFormat.applyPattern(ab.LONG.f14c);
                break;
            default:
                AppboyLogger.m670w(f441a, "Unsupported date format. Defaulting to " + ab.LONG.f14c);
                simpleDateFormat.applyPattern(ab.LONG.f14c);
                break;
        }
        return simpleDateFormat.format(date);
    }

    public static Date m332a(int i, int i2, int i3) {
        Calendar gregorianCalendar = new GregorianCalendar(i, i2, i3, 0, 0, 0);
        gregorianCalendar.setTimeZone(f443c);
        return gregorianCalendar.getTime();
    }

    public static Date m333a(long j) {
        return new Date(1000 * j);
    }
}
