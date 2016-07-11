package com.etsy.android.lib.models.shopedit.brandingimagerow;

import android.os.Parcel;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.apiv3.Image$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image$Source$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image.Source;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class ShopEditIconRow$$Parcelable implements android.os.Parcelable, ax<ShopEditIconRow> {
    public static final ShopEditIconRow$$Parcelable CREATOR;
    private ShopEditIconRow shopEditIconRow$$0;

    static {
        CREATOR = new ShopEditIconRow$$Parcelable();
    }

    public ShopEditIconRow$$Parcelable(Parcel parcel) {
        ShopEditIconRow shopEditIconRow;
        if (parcel.readInt() == -1) {
            shopEditIconRow = null;
        } else {
            shopEditIconRow = m2820x134b8f46(parcel);
        }
        this.shopEditIconRow$$0 = shopEditIconRow;
    }

    public ShopEditIconRow$$Parcelable(ShopEditIconRow shopEditIconRow) {
        this.shopEditIconRow$$0 = shopEditIconRow;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopEditIconRow$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        m2821x7c18de9d(this.shopEditIconRow$$0, parcel, i);
    }

    private ShopEditIconRow m2820x134b8f46(Parcel parcel) {
        Image image;
        ShopEditIconRow shopEditIconRow = new ShopEditIconRow();
        shopEditIconRow.mImageType = parcel.readInt();
        if (parcel.readInt() == -1) {
            image = null;
        } else {
            image = readcom_etsy_android_lib_models_apiv3_Image(parcel);
        }
        shopEditIconRow.mImage = image;
        return shopEditIconRow;
    }

    private Image readcom_etsy_android_lib_models_apiv3_Image(Parcel parcel) {
        List list = null;
        String readString = parcel.readString();
        String readString2 = parcel.readString();
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_Image$Source(parcel);
                }
                arrayList.add(obj);
            }
            Object obj2 = arrayList;
        }
        return new Image(readString, readString2, list);
    }

    private Source readcom_etsy_android_lib_models_apiv3_Image$Source(Parcel parcel) {
        Source source = new Source();
        Image$Source$$PackageHelper.accessImage$Source$FS$height(source, parcel.readInt());
        Image$Source$$PackageHelper.accessImage$Source$FS$mUrl(source, parcel.readString());
        Image$Source$$PackageHelper.accessImage$Source$FS$width(source, parcel.readInt());
        return source;
    }

    private void m2821x7c18de9d(ShopEditIconRow shopEditIconRow, Parcel parcel, int i) {
        parcel.writeInt(shopEditIconRow.mImageType);
        if (shopEditIconRow.mImage == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_Image(shopEditIconRow.mImage, parcel, i);
    }

    private void writecom_etsy_android_lib_models_apiv3_Image(Image image, Parcel parcel, int i) {
        parcel.writeString(Image$$PackageHelper.accessImage$FG$mKey(image));
        parcel.writeString(Image$$PackageHelper.accessImage$FG$mUrl(image));
        if (Image$$PackageHelper.accessImage$FG$mSources(image) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(Image$$PackageHelper.accessImage$FG$mSources(image).size());
        for (Source source : Image$$PackageHelper.accessImage$FG$mSources(image)) {
            if (source == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                writecom_etsy_android_lib_models_apiv3_Image$Source(source, parcel, i);
            }
        }
    }

    private void writecom_etsy_android_lib_models_apiv3_Image$Source(Source source, Parcel parcel, int i) {
        parcel.writeInt(Image$Source$$PackageHelper.accessImage$Source$FG$height(source));
        parcel.writeString(Image$Source$$PackageHelper.accessImage$Source$FG$mUrl(source));
        parcel.writeInt(Image$Source$$PackageHelper.accessImage$Source$FG$width(source));
    }

    public int describeContents() {
        return 0;
    }

    public ShopEditIconRow getParcel() {
        return this.shopEditIconRow$$0;
    }
}
