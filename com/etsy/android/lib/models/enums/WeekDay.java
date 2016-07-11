package com.etsy.android.lib.models.enums;

import com.etsy.android.lib.R;
import java.util.Calendar;

public enum WeekDay {
    MONDAY("mon", 2, R.monday_short),
    TUESDAY("tue", 3, R.tuesday_short),
    WEDNESDAY("wed", 4, R.wednesday_short),
    THURSDAY("thu", 5, R.thursday_short),
    FRIDAY("fri", 6, R.friday_short),
    SATURDAY("sat", 7, R.saturday_short),
    SUNDAY("sun", 1, R.sunday_short);
    
    private int mCalDay;
    private String mKey;
    private int mStringRes;

    private WeekDay(String str, int i, int i2) {
        this.mKey = str;
        this.mCalDay = i;
        this.mStringRes = i2;
    }

    public static WeekDay getDay(String str) {
        for (WeekDay weekDay : values()) {
            if (weekDay.mKey.equals(str)) {
                return weekDay;
            }
        }
        return null;
    }

    public static WeekDay getDay(int i) {
        return values()[i];
    }

    public static WeekDay getDay(Calendar calendar) {
        int i = calendar.get(7);
        for (WeekDay weekDay : values()) {
            if (weekDay.mCalDay == i) {
                return weekDay;
            }
        }
        return null;
    }

    @Deprecated
    public int getString() {
        return this.mStringRes;
    }

    public String getKey() {
        return this.mKey;
    }

    public int getWeekIndex() {
        return this.mCalDay;
    }
}
