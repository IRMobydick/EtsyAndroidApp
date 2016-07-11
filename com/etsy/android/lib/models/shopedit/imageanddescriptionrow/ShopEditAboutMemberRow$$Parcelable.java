package com.etsy.android.lib.models.shopedit.imageanddescriptionrow;

import android.os.Parcel;
import android.widget.ImageView.ScaleType;
import com.etsy.android.lib.models.ShopAboutMember;
import com.etsy.android.lib.models.ShopAboutMember$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.apiv3.Image$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image$Source$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image.Source;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import com.etsy.android.lib.util.as;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class ShopEditAboutMemberRow$$Parcelable implements android.os.Parcelable, ax<ShopEditAboutMemberRow> {
    public static final ShopEditAboutMemberRow$$Parcelable CREATOR;
    private ShopEditAboutMemberRow shopEditAboutMemberRow$$0;

    static {
        CREATOR = new ShopEditAboutMemberRow$$Parcelable();
    }

    public ShopEditAboutMemberRow$$Parcelable(Parcel parcel) {
        ShopEditAboutMemberRow shopEditAboutMemberRow;
        if (parcel.readInt() == -1) {
            shopEditAboutMemberRow = null;
        } else {
            shopEditAboutMemberRow = m2906xbf478fae(parcel);
        }
        this.shopEditAboutMemberRow$$0 = shopEditAboutMemberRow;
    }

    public ShopEditAboutMemberRow$$Parcelable(ShopEditAboutMemberRow shopEditAboutMemberRow) {
        this.shopEditAboutMemberRow$$0 = shopEditAboutMemberRow;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopEditAboutMemberRow$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        m2908xff3225b7(this.shopEditAboutMemberRow$$0, parcel, i);
    }

    private ShopEditAboutMemberRow m2906xbf478fae(Parcel parcel) {
        ShopEditImageAndDescriptionRow shopEditImageAndDescriptionRow;
        ShopAboutMember shopAboutMember = null;
        ShopEditAboutMemberRow shopEditAboutMemberRow = new ShopEditAboutMemberRow();
        if (parcel.readInt() == -1) {
            shopEditImageAndDescriptionRow = null;
        } else {
            shopEditImageAndDescriptionRow = m2907x2e9da6b5(parcel);
        }
        shopEditAboutMemberRow.mImageAndDescriptionRow = shopEditImageAndDescriptionRow;
        if (parcel.readInt() != -1) {
            shopAboutMember = readcom_etsy_android_lib_models_ShopAboutMember(parcel);
        }
        shopEditAboutMemberRow.mShopAboutMember = shopAboutMember;
        return shopEditAboutMemberRow;
    }

    private ShopEditImageAndDescriptionRow m2907x2e9da6b5(Parcel parcel) {
        Image image;
        ShopEditImageAndDescriptionRow shopEditImageAndDescriptionRow = new ShopEditImageAndDescriptionRow();
        shopEditImageAndDescriptionRow.mScaleType = (ScaleType) parcel.readSerializable();
        shopEditImageAndDescriptionRow.mHint = new as().m3265a(parcel);
        shopEditImageAndDescriptionRow.mLoading = parcel.readInt() == 1;
        shopEditImageAndDescriptionRow.mImageShape = parcel.readInt();
        shopEditImageAndDescriptionRow.mDescription = new as().m3265a(parcel);
        if (parcel.readInt() == -1) {
            image = null;
        } else {
            image = readcom_etsy_android_lib_models_apiv3_Image(parcel);
        }
        shopEditImageAndDescriptionRow.mImage = image;
        shopEditImageAndDescriptionRow.mHeightRatio = parcel.readFloat();
        return shopEditImageAndDescriptionRow;
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

    private ShopAboutMember readcom_etsy_android_lib_models_ShopAboutMember(Parcel parcel) {
        EtsyId etsyId;
        Image image = null;
        ShopAboutMember shopAboutMember = new ShopAboutMember();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        ShopAboutMember$$PackageHelper.accessShopAboutMember$FS$mId(shopAboutMember, etsyId);
        ShopAboutMember$$PackageHelper.accessShopAboutMember$FS$mBio(shopAboutMember, parcel.readString());
        ShopAboutMember$$PackageHelper.accessShopAboutMember$FS$mImageUrl190x190(shopAboutMember, parcel.readString());
        ShopAboutMember$$PackageHelper.accessShopAboutMember$FS$mName(shopAboutMember, parcel.readString());
        ShopAboutMember$$PackageHelper.accessShopAboutMember$FS$mImageUrl90x90(shopAboutMember, parcel.readString());
        if (parcel.readInt() != -1) {
            image = readcom_etsy_android_lib_models_apiv3_Image(parcel);
        }
        ShopAboutMember$$PackageHelper.accessShopAboutMember$FS$mImage(shopAboutMember, image);
        ShopAboutMember$$PackageHelper.accessShopAboutMember$FS$mRole(shopAboutMember, parcel.readString());
        return shopAboutMember;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private void m2908xff3225b7(ShopEditAboutMemberRow shopEditAboutMemberRow, Parcel parcel, int i) {
        if (shopEditAboutMemberRow.mImageAndDescriptionRow == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            m2909x33fa23be(shopEditAboutMemberRow.mImageAndDescriptionRow, parcel, i);
        }
        if (shopEditAboutMemberRow.mShopAboutMember == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_ShopAboutMember(shopEditAboutMemberRow.mShopAboutMember, parcel, i);
    }

    private void m2909x33fa23be(ShopEditImageAndDescriptionRow shopEditImageAndDescriptionRow, Parcel parcel, int i) {
        parcel.writeSerializable(shopEditImageAndDescriptionRow.mScaleType);
        new as().m3266a(shopEditImageAndDescriptionRow.mHint, parcel);
        parcel.writeInt(shopEditImageAndDescriptionRow.mLoading ? 1 : 0);
        parcel.writeInt(shopEditImageAndDescriptionRow.mImageShape);
        new as().m3266a(shopEditImageAndDescriptionRow.mDescription, parcel);
        if (shopEditImageAndDescriptionRow.mImage == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Image(shopEditImageAndDescriptionRow.mImage, parcel, i);
        }
        parcel.writeFloat(shopEditImageAndDescriptionRow.mHeightRatio);
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

    private void writecom_etsy_android_lib_models_ShopAboutMember(ShopAboutMember shopAboutMember, Parcel parcel, int i) {
        if (ShopAboutMember$$PackageHelper.accessShopAboutMember$FG$mId(shopAboutMember) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(ShopAboutMember$$PackageHelper.accessShopAboutMember$FG$mId(shopAboutMember), parcel, i);
        }
        parcel.writeString(ShopAboutMember$$PackageHelper.accessShopAboutMember$FG$mBio(shopAboutMember));
        parcel.writeString(ShopAboutMember$$PackageHelper.accessShopAboutMember$FG$mImageUrl190x190(shopAboutMember));
        parcel.writeString(ShopAboutMember$$PackageHelper.accessShopAboutMember$FG$mName(shopAboutMember));
        parcel.writeString(ShopAboutMember$$PackageHelper.accessShopAboutMember$FG$mImageUrl90x90(shopAboutMember));
        if (ShopAboutMember$$PackageHelper.accessShopAboutMember$FG$mImage(shopAboutMember) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Image(ShopAboutMember$$PackageHelper.accessShopAboutMember$FG$mImage(shopAboutMember), parcel, i);
        }
        parcel.writeString(ShopAboutMember$$PackageHelper.accessShopAboutMember$FG$mRole(shopAboutMember));
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    public int describeContents() {
        return 0;
    }

    public ShopEditAboutMemberRow getParcel() {
        return this.shopEditAboutMemberRow$$0;
    }
}
