package com.etsy.android.lib.models;

import android.os.Parcel;
import com.etsy.android.lib.models.ShopAbout.Link;
import org.parceler.ax;

public class ShopAbout$Link$$Parcelable implements android.os.Parcelable, ax<Link> {
    public static final af CREATOR;
    private Link link$$0;

    static {
        CREATOR = new af();
    }

    public ShopAbout$Link$$Parcelable(Parcel parcel) {
        Link link;
        if (parcel.readInt() == -1) {
            link = null;
        } else {
            link = readcom_etsy_android_lib_models_ShopAbout$Link(parcel);
        }
        this.link$$0 = link;
    }

    public ShopAbout$Link$$Parcelable(Link link) {
        this.link$$0 = link;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.link$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_ShopAbout$Link(this.link$$0, parcel, i);
    }

    private Link readcom_etsy_android_lib_models_ShopAbout$Link(Parcel parcel) {
        Link link = new Link();
        link.mUrl = parcel.readString();
        link.mTitle = parcel.readString();
        return link;
    }

    private void writecom_etsy_android_lib_models_ShopAbout$Link(Link link, Parcel parcel, int i) {
        parcel.writeString(link.mUrl);
        parcel.writeString(link.mTitle);
    }

    public int describeContents() {
        return 0;
    }

    public Link getParcel() {
        return this.link$$0;
    }
}
