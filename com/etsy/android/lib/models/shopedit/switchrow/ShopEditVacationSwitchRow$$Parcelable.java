package com.etsy.android.lib.models.shopedit.switchrow;

import android.os.Parcel;
import com.etsy.android.lib.util.as;
import org.parceler.ax;

public class ShopEditVacationSwitchRow$$Parcelable implements android.os.Parcelable, ax<ShopEditVacationSwitchRow> {
    public static final ShopEditVacationSwitchRow$$Parcelable CREATOR;
    private ShopEditVacationSwitchRow shopEditVacationSwitchRow$$0;

    static {
        CREATOR = new ShopEditVacationSwitchRow$$Parcelable();
    }

    public ShopEditVacationSwitchRow$$Parcelable(Parcel parcel) {
        ShopEditVacationSwitchRow shopEditVacationSwitchRow;
        if (parcel.readInt() == -1) {
            shopEditVacationSwitchRow = null;
        } else {
            shopEditVacationSwitchRow = m2952xfe55c986(parcel);
        }
        this.shopEditVacationSwitchRow$$0 = shopEditVacationSwitchRow;
    }

    public ShopEditVacationSwitchRow$$Parcelable(ShopEditVacationSwitchRow shopEditVacationSwitchRow) {
        this.shopEditVacationSwitchRow$$0 = shopEditVacationSwitchRow;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopEditVacationSwitchRow$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        m2953xea79a6cf(this.shopEditVacationSwitchRow$$0, parcel, i);
    }

    private ShopEditVacationSwitchRow m2952xfe55c986(Parcel parcel) {
        boolean z = true;
        CharSequence a = new as().m3265a(parcel);
        if (parcel.readInt() != 1) {
            z = false;
        }
        return new ShopEditVacationSwitchRow(a, z);
    }

    private void m2953xea79a6cf(ShopEditVacationSwitchRow shopEditVacationSwitchRow, Parcel parcel, int i) {
        new as().m3266a(shopEditVacationSwitchRow.mTitle, parcel);
        parcel.writeInt(shopEditVacationSwitchRow.mToggledOn ? 1 : 0);
    }

    public int describeContents() {
        return 0;
    }

    public ShopEditVacationSwitchRow getParcel() {
        return this.shopEditVacationSwitchRow$$0;
    }
}
