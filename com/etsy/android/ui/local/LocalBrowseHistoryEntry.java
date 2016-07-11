package com.etsy.android.ui.local;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.apache.commons.lang3.StringUtils;

class LocalBrowseHistoryEntry implements Parcelable {
    public static final Creator<LocalBrowseHistoryEntry> CREATOR;
    private final int f3020a;
    private final String f3021b;
    private final LocalSearchQuery f3022c;
    private final String f3023d;

    /* renamed from: com.etsy.android.ui.local.LocalBrowseHistoryEntry.1 */
    final class C07291 implements Creator<LocalBrowseHistoryEntry> {
        C07291() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m4281a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m4282a(i);
        }

        public LocalBrowseHistoryEntry m4281a(Parcel parcel) {
            return new LocalBrowseHistoryEntry(parcel);
        }

        public LocalBrowseHistoryEntry[] m4282a(int i) {
            return new LocalBrowseHistoryEntry[i];
        }
    }

    public LocalBrowseHistoryEntry(int i, String str, String str2) {
        this.f3020a = i;
        this.f3021b = str;
        this.f3022c = null;
        this.f3023d = str2;
    }

    public LocalBrowseHistoryEntry(int i, LocalSearchQuery localSearchQuery, String str) {
        this.f3020a = i;
        this.f3021b = StringUtils.EMPTY;
        this.f3022c = localSearchQuery;
        this.f3023d = str;
    }

    public int m4283a() {
        return this.f3020a;
    }

    public String m4284b() {
        return this.f3021b;
    }

    public LocalSearchQuery m4285c() {
        return this.f3022c;
    }

    public String m4286d() {
        return this.f3023d;
    }

    public LocalBrowseHistoryEntry(Parcel parcel) {
        this.f3020a = parcel.readInt();
        this.f3021b = parcel.readString();
        this.f3022c = (LocalSearchQuery) parcel.readParcelable(LocalSearchQuery.class.getClassLoader());
        this.f3023d = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3020a);
        parcel.writeString(this.f3021b);
        parcel.writeParcelable(this.f3022c, i);
        parcel.writeString(this.f3023d);
    }

    static {
        CREATOR = new C07291();
    }
}
