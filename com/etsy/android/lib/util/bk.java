package com.etsy.android.lib.util;

import android.content.Context;
import com.etsy.android.lib.R;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/* compiled from: TimeFormattingUtil */
public class bk {
    public static int m3348a(long j) {
        return (int) (j / 86400000);
    }

    public static Calendar m3351a(Calendar calendar) {
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar;
    }

    public static CharSequence m3350a(Context context, Long l, Long l2) {
        Object obj;
        Long valueOf = Long.valueOf(Math.abs(l2.longValue() - l.longValue()));
        Long valueOf2 = Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(valueOf.longValue()));
        Long valueOf3 = Long.valueOf(TimeUnit.MILLISECONDS.toHours(valueOf.longValue()));
        Long valueOf4 = Long.valueOf(TimeUnit.MILLISECONDS.toDays(valueOf.longValue()));
        Long valueOf5 = Long.valueOf(valueOf4.longValue() / ((long) Math.round(7.0f)));
        CharSequence subSequence = context.getResources().getString(R.minute).subSequence(0, 1);
        Object subSequence2 = context.getResources().getString(R.hour).subSequence(0, 1);
        CharSequence subSequence3 = context.getResources().getString(R.day).subSequence(0, 1);
        if (valueOf3.longValue() >= valueOf2.longValue() || valueOf3.longValue() == 0) {
            CharSequence charSequence = subSequence;
            Long l3 = valueOf2;
        } else {
            obj = valueOf3;
        }
        if (valueOf4.longValue() < obj.longValue() && valueOf4.longValue() != 0) {
            subSequence2 = subSequence3;
            obj = valueOf4;
        }
        if (valueOf5.longValue() < obj.longValue() && valueOf5.longValue() != 0) {
            subSequence2 = context.getResources().getQuantityString(R.weeks_nt2, valueOf5.intValue()).subSequence(0, 1);
            obj = valueOf5;
        }
        return String.valueOf(obj) + subSequence2;
    }

    public static CharSequence m3349a(Context context, Long l) {
        return m3350a(context, l, Long.valueOf(Calendar.getInstance().getTimeInMillis()));
    }
}
