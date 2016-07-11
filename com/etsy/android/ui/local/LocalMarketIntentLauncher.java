package com.etsy.android.ui.local;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract.Events;
import android.support.annotation.NonNull;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.models.Attendee;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.LocalMarketAttendeeSchedule;
import com.etsy.android.lib.models.LocalMarketAttendeeSchedule.Day;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.TimeRange;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.util.ah;
import com.etsy.android.lib.util.bh;
import com.etsy.android.lib.util.p013b.LocalMarketFormatter;
import java.util.Calendar;

/* renamed from: com.etsy.android.ui.local.q */
public class LocalMarketIntentLauncher {
    private static final String f3166a;

    static {
        f3166a = EtsyDebug.m1891a(LocalMarketIntentLauncher.class);
    }

    public static void m4398a(Activity activity, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(String.format("tel:%s", new Object[]{str.trim()})));
        ah.m3222a(activity, intent);
    }

    public static void m4395a(Activity activity, @NonNull ad adVar, String str) {
        String format = String.format(adVar.m1864f().m883b(EtsyConfigKeys.ab), new Object[]{Uri.encode(str)});
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(format));
        intent.setFlags(268435456);
        ah.m3222a(activity, intent);
    }

    public static void m4397a(Activity activity, LocalMarket localMarket) {
        if (localMarket != null) {
            Intent putExtra = new Intent("android.intent.action.INSERT").setData(Events.CONTENT_URI).putExtra("beginTime", LocalMarketIntentLauncher.m4393a(localMarket).getTimeInMillis()).putExtra("endTime", LocalMarketIntentLauncher.m4399b(localMarket).getTimeInMillis());
            String str = "allDay";
            boolean z = !localMarket.isOneDayEvent() || localMarket.getScheduleRollups().size() == 0;
            ah.m3222a(activity, putExtra.putExtra(str, z).putExtra(FindsModule.FIELD_TITLE, localMarket.getTitle()).putExtra(ResponseConstants.DESCRIPTION, LocalMarketFormatter.m3296a((Context) activity, localMarket)).putExtra("eventLocation", LocalMarketFormatter.m3300a(localMarket)));
        }
    }

    public static void m4396a(Activity activity, Attendee attendee, LocalMarket localMarket) {
        boolean z = false;
        if (localMarket != null && attendee != null) {
            String string = activity.getResources().getString(R.seller_at_event, new Object[]{attendee.getShopName(), localMarket.getTitle()});
            if (bh.m3343b(localMarket.getCity())) {
                string = string + ", " + localMarket.getCity();
            }
            Intent putExtra = new Intent("android.intent.action.INSERT").setData(Events.CONTENT_URI).putExtra("beginTime", LocalMarketIntentLauncher.m4394a(attendee.getSchedule(), localMarket).getTimeInMillis()).putExtra("endTime", LocalMarketIntentLauncher.m4400b(attendee.getSchedule(), localMarket).getTimeInMillis());
            String str = "allDay";
            if (!localMarket.isOneDayEvent() || localMarket.getScheduleRollups().size() == 0) {
                z = true;
            }
            ah.m3222a(activity, putExtra.putExtra(str, z).putExtra(FindsModule.FIELD_TITLE, string).putExtra(ResponseConstants.DESCRIPTION, LocalMarketFormatter.m3295a((Context) activity, attendee, localMarket)).putExtra("eventLocation", LocalMarketFormatter.m3300a(localMarket)));
        }
    }

    private static Calendar m4393a(LocalMarket localMarket) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(localMarket.getStartDate());
        TimeRange timeRangeForDay = localMarket.getTimeRangeForDay(instance);
        if (timeRangeForDay == null) {
            instance.set(11, 0);
            instance.set(12, 0);
        } else {
            instance.set(11, timeRangeForDay.getStartTime().get(11));
            instance.set(12, timeRangeForDay.getStartTime().get(12));
        }
        return instance;
    }

    private static Calendar m4399b(LocalMarket localMarket) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(localMarket.getEndDate());
        TimeRange timeRangeForDay = localMarket.getTimeRangeForDay(instance);
        if (timeRangeForDay == null) {
            instance.set(11, 23);
            instance.set(12, 59);
        } else {
            instance.set(11, timeRangeForDay.getEndTime().get(11));
            instance.set(12, timeRangeForDay.getEndTime().get(12));
        }
        return instance;
    }

    private static Calendar m4394a(LocalMarketAttendeeSchedule localMarketAttendeeSchedule, @NonNull LocalMarket localMarket) {
        if (localMarketAttendeeSchedule == null || localMarketAttendeeSchedule.getDays().size() < 1 || (localMarketAttendeeSchedule.isDaysOfWeekType() && !localMarket.isOneDayEvent())) {
            return LocalMarketIntentLauncher.m4393a(localMarket);
        }
        Calendar a;
        Day day = (Day) localMarketAttendeeSchedule.getDays().get(0);
        if (localMarketAttendeeSchedule.isDaysOfWeekType()) {
            a = LocalMarketIntentLauncher.m4393a(localMarket);
        } else {
            a = Calendar.getInstance();
            a.setTime(day.getSpecificDate());
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(day.getFrom());
        a.set(11, instance.get(11));
        a.set(12, instance.get(12));
        return a;
    }

    private static Calendar m4400b(LocalMarketAttendeeSchedule localMarketAttendeeSchedule, @NonNull LocalMarket localMarket) {
        if (localMarketAttendeeSchedule == null || localMarketAttendeeSchedule.getDays().size() < 1 || (localMarketAttendeeSchedule.isDaysOfWeekType() && !localMarket.isOneDayEvent())) {
            return LocalMarketIntentLauncher.m4399b(localMarket);
        }
        Calendar b;
        Day day = (Day) localMarketAttendeeSchedule.getDays().get(0);
        if (localMarketAttendeeSchedule.isDaysOfWeekType()) {
            b = LocalMarketIntentLauncher.m4399b(localMarket);
        } else {
            b = Calendar.getInstance();
            b.setTime(day.getSpecificDate());
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(day.getTo());
        b.set(11, instance.get(11));
        b.set(12, instance.get(12));
        return b;
    }
}
