package com.etsy.android.lib.util.p013b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.text.format.DateUtils;
import com.appboy.Constants;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.Attendee;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.LocalMarketAttendeeSchedule;
import com.etsy.android.lib.models.LocalMarketAttendeeSchedule.Day;
import com.etsy.android.lib.models.LocalStore;
import com.etsy.android.lib.models.datatypes.TimeRange;
import com.etsy.android.lib.util.aa;
import com.etsy.android.lib.util.bh;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.lib.util.b.a */
public class LocalMarketFormatter {
    private static final String f2012a;

    static {
        f2012a = EtsyDebug.m1891a(LocalMarketFormatter.class);
    }

    public static String m3300a(LocalMarket localMarket) {
        if (localMarket == null) {
            return StringUtils.EMPTY;
        }
        Iterable arrayList = new ArrayList();
        arrayList.add(localMarket.getVenueName());
        CharSequence trim = (localMarket.getAddress1() + " " + localMarket.getAddress2()).trim();
        if (!TextUtils.isEmpty(trim)) {
            arrayList.add(trim);
        }
        if (!TextUtils.isEmpty(localMarket.getCity())) {
            arrayList.add(localMarket.getCity());
        }
        if (!TextUtils.isEmpty(localMarket.getState())) {
            arrayList.add(localMarket.getState());
        }
        return TextUtils.join(", ", arrayList);
    }

    public static String m3303b(LocalMarket localMarket) {
        if (localMarket == null || localMarket.getStartDate() == null) {
            return StringUtils.EMPTY;
        }
        long time = localMarket.getEndDate().getTime();
        if (!localMarket.isOneDayEvent()) {
            time += Constants.LOCATION_UPDATE_TIME_INTERVAL_DEFAULT_MS;
        }
        return LocalMarketFormatter.m3294a(localMarket.getStartDate().getTime(), time, 65552, "UTC");
    }

    private static String m3294a(long j, long j2, int i, String str) {
        return DateUtils.formatDateRange(null, new Formatter(new StringBuilder(50), Locale.getDefault()), j, j2, i, str).toString();
    }

    public static String m3302a(TimeRange timeRange, boolean z) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(z ? "E" : "EEEE");
        Calendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        gregorianCalendar.set(10, 0);
        gregorianCalendar.set(12, 0);
        gregorianCalendar.set(13, 0);
        gregorianCalendar.set(14, 0);
        gregorianCalendar.set(7, timeRange.getStartDay().getWeekIndex());
        String format = simpleDateFormat.format(gregorianCalendar.getTime());
        if (timeRange.isOneDay()) {
            return format;
        }
        Calendar gregorianCalendar2 = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        gregorianCalendar2.set(10, 0);
        gregorianCalendar2.set(12, 0);
        gregorianCalendar2.set(13, 0);
        gregorianCalendar2.set(14, 0);
        gregorianCalendar2.set(7, timeRange.getEndDay().getWeekIndex());
        return String.format("%s \u2013 %s", new Object[]{format, simpleDateFormat.format(gregorianCalendar2.getTime())});
    }

    public static String m3298a(Context context, Date date, Date date2) {
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(context);
        return String.format("%s \u2013 %s", new Object[]{timeFormat.format(date), timeFormat.format(date2)}).toLowerCase(Locale.US);
    }

    @SuppressLint({"NewApi"})
    public static String m3301a(LocalStore localStore) {
        if (localStore == null || TextUtils.isEmpty(localStore.getPhoneNumber())) {
            return StringUtils.EMPTY;
        }
        if (!aa.m3170d()) {
            return PhoneNumberUtils.formatNumber(localStore.getPhoneNumber());
        }
        String iSO3Country;
        if (localStore.getAddress() == null || localStore.getAddress().getCountry() == null || !bh.m3343b(localStore.getAddress().getCountry().getIsoCountryCode())) {
            iSO3Country = Locale.getDefault().getISO3Country();
        } else {
            iSO3Country = localStore.getAddress().getCountry().getIsoCountryCode();
        }
        return PhoneNumberUtils.formatNumber(localStore.getPhoneNumber(), iSO3Country);
    }

    public static String m3299a(@NonNull Context context, @NonNull List<TimeRange> list) {
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(context);
        StringBuilder stringBuilder = new StringBuilder();
        for (TimeRange timeRange : list) {
            String format = timeFormat.format(timeRange.getStartTime().getTime());
            String format2 = timeFormat.format(timeRange.getEndTime().getTime());
            if (timeRange.isOneDay()) {
                stringBuilder.append(String.format("%s: %s \u2014 %s", new Object[]{context.getString(timeRange.getStartDayString()), format, format2}));
            } else {
                String string = context.getString(timeRange.getStartDayString());
                String string2 = context.getString(timeRange.getEndDayString());
                stringBuilder.append(String.format("%s \u2014 %s: %s \u2014 %s", new Object[]{string, string2, format, format2}));
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    public static String m3296a(@NonNull Context context, LocalMarket localMarket) {
        if (localMarket == null) {
            return StringUtils.EMPTY;
        }
        return localMarket.getDescription() + "\n\n" + (localMarket.isWholesaleStore() ? StringUtils.EMPTY : LocalMarketFormatter.m3303b(localMarket) + "\n") + LocalMarketFormatter.m3299a(context, localMarket.getScheduleRollups()) + "\n\n" + localMarket.getDetailsUrl();
    }

    public static String m3297a(@NonNull Context context, @NonNull LocalMarketAttendeeSchedule localMarketAttendeeSchedule) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d");
        StringBuilder stringBuilder = new StringBuilder();
        String[] stringArray = context.getResources().getStringArray(R.weekdays_pluralized);
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(context);
        for (Day day : localMarketAttendeeSchedule.getDays()) {
            if (localMarketAttendeeSchedule.isDaysOfWeekType()) {
                stringBuilder.append(stringArray[day.getDay().getWeekIndex()]);
            } else {
                stringBuilder.append(simpleDateFormat.format(day.getSpecificDate()));
            }
            stringBuilder.append(": ");
            stringBuilder.append(String.format("%s \u2013 %s", new Object[]{timeFormat.format(day.getFrom()), timeFormat.format(day.getTo())}));
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    public static String m3295a(@NonNull Context context, Attendee attendee, LocalMarket localMarket) {
        if (localMarket == null || attendee == null) {
            return StringUtils.EMPTY;
        }
        return String.format("\"%s\"", new Object[]{attendee.getComment()}) + "\n\n" + LocalMarketFormatter.m3303b(localMarket) + "\n" + LocalMarketFormatter.m3297a(context, attendee.getSchedule()) + "\n\n" + attendee.getShopUrl() + "\n\n" + localMarket.getDetailsUrl();
    }
}
