package com.etsy.android.lib.models.datatypes;

import com.etsy.android.lib.models.enums.WeekDay;
import java.util.Calendar;

public class TimeRange$$PackageHelper {
    public static WeekDay accessTimeRange$FG$mEndDay(TimeRange timeRange) {
        return timeRange.mEndDay;
    }

    public static WeekDay accessTimeRange$FG$mStartDay(TimeRange timeRange) {
        return timeRange.mStartDay;
    }

    public static Calendar accessTimeRange$FG$mEndTime(TimeRange timeRange) {
        return timeRange.mEndTime;
    }

    public static Calendar accessTimeRange$FG$mStartTime(TimeRange timeRange) {
        return timeRange.mStartTime;
    }

    public static void accessTimeRange$FS$mEndDay(TimeRange timeRange, WeekDay weekDay) {
        timeRange.mEndDay = weekDay;
    }

    public static void accessTimeRange$FS$mStartDay(TimeRange timeRange, WeekDay weekDay) {
        timeRange.mStartDay = weekDay;
    }

    public static void accessTimeRange$FS$mEndTime(TimeRange timeRange, Calendar calendar) {
        timeRange.mEndTime = calendar;
    }

    public static void accessTimeRange$FS$mStartTime(TimeRange timeRange, Calendar calendar) {
        timeRange.mStartTime = calendar;
    }
}
