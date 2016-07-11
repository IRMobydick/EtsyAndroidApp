package com.etsy.android.lib.models.shopedit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.models.ShopAbout;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ShopVideoShareData {
    @NonNull
    String mShareUrl;

    ShopVideoShareData() {
        this.mShareUrl = StringUtils.EMPTY;
    }

    public ShopVideoShareData(@Nullable ShopAbout shopAbout) {
        this.mShareUrl = StringUtils.EMPTY;
        if (shopAbout != null) {
            this.mShareUrl = shopAbout.getUrl();
        }
    }

    @NonNull
    public String getShareUrl() {
        return this.mShareUrl;
    }
}
