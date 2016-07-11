package com.etsy.android.lib.models;

import android.os.Parcel;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.apiv3.Image$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image$Source$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image.Source;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class ShopAboutMember$$Parcelable implements android.os.Parcelable, ax<ShopAboutMember> {
    public static final ah CREATOR;
    private ShopAboutMember shopAboutMember$$8;

    static {
        CREATOR = new ah();
    }

    public ShopAboutMember$$Parcelable(Parcel parcel) {
        ShopAboutMember shopAboutMember;
        if (parcel.readInt() == -1) {
            shopAboutMember = null;
        } else {
            shopAboutMember = readcom_etsy_android_lib_models_ShopAboutMember(parcel);
        }
        this.shopAboutMember$$8 = shopAboutMember;
    }

    public ShopAboutMember$$Parcelable(ShopAboutMember shopAboutMember) {
        this.shopAboutMember$$8 = shopAboutMember;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopAboutMember$$8 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_ShopAboutMember(this.shopAboutMember$$8, parcel, i);
    }

    private ShopAboutMember readcom_etsy_android_lib_models_ShopAboutMember(Parcel parcel) {
        EtsyId etsyId;
        Image image = null;
        ShopAboutMember shopAboutMember = new ShopAboutMember();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        shopAboutMember.mId = etsyId;
        shopAboutMember.mBio = parcel.readString();
        shopAboutMember.mImageUrl190x190 = parcel.readString();
        shopAboutMember.mName = parcel.readString();
        shopAboutMember.mImageUrl90x90 = parcel.readString();
        if (parcel.readInt() != -1) {
            image = readcom_etsy_android_lib_models_apiv3_Image(parcel);
        }
        shopAboutMember.mImage = image;
        shopAboutMember.mRole = parcel.readString();
        return shopAboutMember;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
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

    private void writecom_etsy_android_lib_models_ShopAboutMember(ShopAboutMember shopAboutMember, Parcel parcel, int i) {
        if (shopAboutMember.mId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(shopAboutMember.mId, parcel, i);
        }
        parcel.writeString(shopAboutMember.mBio);
        parcel.writeString(shopAboutMember.mImageUrl190x190);
        parcel.writeString(shopAboutMember.mName);
        parcel.writeString(shopAboutMember.mImageUrl90x90);
        if (shopAboutMember.mImage == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Image(shopAboutMember.mImage, parcel, i);
        }
        parcel.writeString(shopAboutMember.mRole);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
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

    public ShopAboutMember getParcel() {
        return this.shopAboutMember$$8;
    }
}
