package com.etsy.android.lib.models;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class StatsRevenues$$Parcelable implements android.os.Parcelable, ax<StatsRevenues> {
    public static final an CREATOR;
    private StatsRevenues statsRevenues$$0;

    static {
        CREATOR = new an();
    }

    public StatsRevenues$$Parcelable(Parcel parcel) {
        StatsRevenues statsRevenues;
        if (parcel.readInt() == -1) {
            statsRevenues = null;
        } else {
            statsRevenues = readcom_etsy_android_lib_models_StatsRevenues(parcel);
        }
        this.statsRevenues$$0 = statsRevenues;
    }

    public StatsRevenues$$Parcelable(StatsRevenues statsRevenues) {
        this.statsRevenues$$0 = statsRevenues;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.statsRevenues$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_StatsRevenues(this.statsRevenues$$0, parcel, i);
    }

    private StatsRevenues readcom_etsy_android_lib_models_StatsRevenues(Parcel parcel) {
        List list = null;
        StatsRevenues statsRevenues = new StatsRevenues();
        statsRevenues.mCode = parcel.readString();
        statsRevenues.mCount = parcel.readDouble();
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_RevenueItem(parcel);
                }
                arrayList.add(obj);
            }
            Object obj2 = arrayList;
        }
        statsRevenues.mRevenueItems = list;
        return statsRevenues;
    }

    private RevenueItem readcom_etsy_android_lib_models_RevenueItem(Parcel parcel) {
        RevenueItem revenueItem = new RevenueItem();
        revenueItem.mCount = parcel.readDouble();
        revenueItem.mCurrencyCode = parcel.readString();
        revenueItem.mTimestamp = parcel.readLong();
        return revenueItem;
    }

    private void writecom_etsy_android_lib_models_StatsRevenues(StatsRevenues statsRevenues, Parcel parcel, int i) {
        parcel.writeString(statsRevenues.mCode);
        parcel.writeDouble(statsRevenues.mCount);
        if (statsRevenues.mRevenueItems == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(statsRevenues.mRevenueItems.size());
        for (RevenueItem revenueItem : statsRevenues.mRevenueItems) {
            if (revenueItem == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                writecom_etsy_android_lib_models_RevenueItem(revenueItem, parcel, i);
            }
        }
    }

    private void writecom_etsy_android_lib_models_RevenueItem(RevenueItem revenueItem, Parcel parcel, int i) {
        parcel.writeDouble(revenueItem.mCount);
        parcel.writeString(revenueItem.mCurrencyCode);
        parcel.writeLong(revenueItem.mTimestamp);
    }

    public int describeContents() {
        return 0;
    }

    public StatsRevenues getParcel() {
        return this.statsRevenues$$0;
    }
}
