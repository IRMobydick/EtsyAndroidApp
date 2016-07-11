package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.datatypes.EtsyId;
import org.parceler.Parcel;

@Parcel
public class EditableUserAddressV3 extends UserAddressV3 {
    protected boolean mIsSelected;

    public boolean isSelected() {
        return this.mIsSelected;
    }

    public void setSelected(boolean z) {
        this.mIsSelected = z;
    }

    public EditableUserAddressV3() {
        this.mIsSelected = false;
    }

    public EditableUserAddressV3(UserAddressV3 userAddressV3) {
        this.mIsSelected = false;
        copyAddress(userAddressV3);
    }

    public void copyAddress(UserAddressV3 userAddressV3) {
        this.mUserId = userAddressV3.getUserId();
        this.mUserAddressId = userAddressV3.getUserAddressId();
        this.mIsAvailableForMarket = userAddressV3.isAvailableForMarket();
        this.mName = userAddressV3.getName();
        this.mPhone = userAddressV3.getPhone();
        updateAddressInfo(userAddressV3);
    }

    public void updateAddressInfo(UserAddressV3 userAddressV3) {
        this.mFirstLine = userAddressV3.getFirstLine();
        this.mSecondLine = userAddressV3.getSecondLine();
        this.mCity = userAddressV3.getCity();
        this.mState = userAddressV3.getState();
        this.mZip = userAddressV3.getZip();
        this.mCountryId = userAddressV3.getCountryId();
        this.mCountryName = userAddressV3.getCountryName();
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setCity(String str) {
        this.mCity = str;
    }

    public void setZip(String str) {
        this.mZip = str;
    }

    public void setState(String str) {
        this.mState = str;
    }

    public void setFirstLine(String str) {
        this.mFirstLine = str;
    }

    public void setSecondLine(String str) {
        this.mSecondLine = str;
    }

    public void setPhone(String str) {
        this.mPhone = str;
    }

    public void setCountryId(EtsyId etsyId) {
        this.mCountryId = etsyId;
    }
}
