package com.etsy.android.lib.models;

import com.etsy.android.lib.models.LocalMarketAttendeeSchedule.Day;
import com.etsy.android.lib.models.enums.WeekDay;
import java.util.Date;

public class LocalMarketAttendeeSchedule$Day$$PackageHelper {
    public static Date accessLocalMarketAttendeeSchedule$Day$FG$mFrom(Day day) {
        return day.mFrom;
    }

    public static Date accessLocalMarketAttendeeSchedule$Day$FG$mTo(Day day) {
        return day.mTo;
    }

    public static Date accessLocalMarketAttendeeSchedule$Day$FG$mSpecificDate(Day day) {
        return day.mSpecificDate;
    }

    public static WeekDay accessLocalMarketAttendeeSchedule$Day$FG$mDay(Day day) {
        return day.mDay;
    }

    public static void accessLocalMarketAttendeeSchedule$Day$FS$mFrom(Day day, Date date) {
        day.mFrom = date;
    }

    public static void accessLocalMarketAttendeeSchedule$Day$FS$mTo(Day day, Date date) {
        day.mTo = date;
    }

    public static void accessLocalMarketAttendeeSchedule$Day$FS$mSpecificDate(Day day, Date date) {
        day.mSpecificDate = date;
    }

    public static void accessLocalMarketAttendeeSchedule$Day$FS$mDay(Day day, WeekDay weekDay) {
        day.mDay = weekDay;
    }
}
