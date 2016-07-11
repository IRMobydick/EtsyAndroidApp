package com.etsy.android.lib.models.datatypes;

import android.os.Parcel;
import com.etsy.android.lib.models.enums.WeekDay;
import com.etsy.android.lib.util.ar;
import org.parceler.ax;

public class TimeRange$$Parcelable implements android.os.Parcelable, ax<TimeRange> {
    public static final TimeRange$$Parcelable CREATOR;
    private TimeRange timeRange$$112;

    static {
        CREATOR = new TimeRange$$Parcelable();
    }

    public TimeRange$$Parcelable(Parcel parcel) {
        TimeRange timeRange;
        if (parcel.readInt() == -1) {
            timeRange = null;
        } else {
            timeRange = readcom_etsy_android_lib_models_datatypes_TimeRange(parcel);
        }
        this.timeRange$$112 = timeRange;
    }

    public TimeRange$$Parcelable(TimeRange timeRange) {
        this.timeRange$$112 = timeRange;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.timeRange$$112 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_TimeRange(this.timeRange$$112, parcel, i);
    }

    private TimeRange readcom_etsy_android_lib_models_datatypes_TimeRange(Parcel parcel) {
        TimeRange timeRange = new TimeRange();
        timeRange.mStartDay = (WeekDay) parcel.readSerializable();
        timeRange.mEndTime = new ar().m3263a(parcel);
        timeRange.mEndDay = (WeekDay) parcel.readSerializable();
        timeRange.mStartTime = new ar().m3263a(parcel);
        return timeRange;
    }

    private void writecom_etsy_android_lib_models_datatypes_TimeRange(TimeRange timeRange, Parcel parcel, int i) {
        parcel.writeSerializable(timeRange.mStartDay);
        new ar().m3264a(timeRange.mEndTime, parcel);
        parcel.writeSerializable(timeRange.mEndDay);
        new ar().m3264a(timeRange.mStartTime, parcel);
    }

    public int describeContents() {
        return 0;
    }

    public TimeRange getParcel() {
        return this.timeRange$$112;
    }
}
