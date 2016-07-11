package com.etsy.android.lib.models;

import android.os.Parcel;
import com.etsy.android.lib.models.datatypes.TimeRange;
import com.etsy.android.lib.models.datatypes.TimeRange$$PackageHelper;
import com.etsy.android.lib.models.enums.WeekDay;
import com.etsy.android.lib.util.ar;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;
import org.parceler.ax;

public class ScheduleExpanded$$Parcelable implements android.os.Parcelable, ax<ScheduleExpanded> {
    public static final ac CREATOR;
    private ScheduleExpanded scheduleExpanded$$12;

    static {
        CREATOR = new ac();
    }

    public ScheduleExpanded$$Parcelable(Parcel parcel) {
        ScheduleExpanded scheduleExpanded;
        if (parcel.readInt() == -1) {
            scheduleExpanded = null;
        } else {
            scheduleExpanded = readcom_etsy_android_lib_models_ScheduleExpanded(parcel);
        }
        this.scheduleExpanded$$12 = scheduleExpanded;
    }

    public ScheduleExpanded$$Parcelable(ScheduleExpanded scheduleExpanded) {
        this.scheduleExpanded$$12 = scheduleExpanded;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.scheduleExpanded$$12 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_ScheduleExpanded(this.scheduleExpanded$$12, parcel, i);
    }

    private ScheduleExpanded readcom_etsy_android_lib_models_ScheduleExpanded(Parcel parcel) {
        HashMap hashMap = null;
        ScheduleExpanded scheduleExpanded = new ScheduleExpanded();
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            HashMap hashMap2 = new HashMap();
            for (int i = 0; i < readInt; i++) {
                Object obj;
                WeekDay weekDay = (WeekDay) parcel.readSerializable();
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_datatypes_TimeRange(parcel);
                }
                hashMap2.put(weekDay, obj);
            }
            hashMap = hashMap2;
        }
        scheduleExpanded.mDailySchedule = hashMap;
        return scheduleExpanded;
    }

    private TimeRange readcom_etsy_android_lib_models_datatypes_TimeRange(Parcel parcel) {
        TimeRange timeRange = new TimeRange();
        TimeRange$$PackageHelper.accessTimeRange$FS$mStartDay(timeRange, (WeekDay) parcel.readSerializable());
        TimeRange$$PackageHelper.accessTimeRange$FS$mEndTime(timeRange, new ar().m3263a(parcel));
        TimeRange$$PackageHelper.accessTimeRange$FS$mEndDay(timeRange, (WeekDay) parcel.readSerializable());
        TimeRange$$PackageHelper.accessTimeRange$FS$mStartTime(timeRange, new ar().m3263a(parcel));
        return timeRange;
    }

    private void writecom_etsy_android_lib_models_ScheduleExpanded(ScheduleExpanded scheduleExpanded, Parcel parcel, int i) {
        if (scheduleExpanded.mDailySchedule == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(scheduleExpanded.mDailySchedule.size());
        for (Entry entry : scheduleExpanded.mDailySchedule.entrySet()) {
            parcel.writeSerializable((Serializable) entry.getKey());
            if (entry.getValue() == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                writecom_etsy_android_lib_models_datatypes_TimeRange((TimeRange) entry.getValue(), parcel, i);
            }
        }
    }

    private void writecom_etsy_android_lib_models_datatypes_TimeRange(TimeRange timeRange, Parcel parcel, int i) {
        parcel.writeSerializable(TimeRange$$PackageHelper.accessTimeRange$FG$mStartDay(timeRange));
        new ar().m3264a(TimeRange$$PackageHelper.accessTimeRange$FG$mEndTime(timeRange), parcel);
        parcel.writeSerializable(TimeRange$$PackageHelper.accessTimeRange$FG$mEndDay(timeRange));
        new ar().m3264a(TimeRange$$PackageHelper.accessTimeRange$FG$mStartTime(timeRange), parcel);
    }

    public int describeContents() {
        return 0;
    }

    public ScheduleExpanded getParcel() {
        return this.scheduleExpanded$$12;
    }
}
