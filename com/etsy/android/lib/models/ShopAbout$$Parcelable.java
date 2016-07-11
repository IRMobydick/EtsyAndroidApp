package com.etsy.android.lib.models;

import android.os.Parcel;
import com.etsy.android.lib.models.ShopAbout.RelatedLinks;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.apiv3.Image$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image$Source$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image.Source;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class ShopAbout$$Parcelable implements android.os.Parcelable, ax<ShopAbout> {
    public static final ae CREATOR;
    private ShopAbout shopAbout$$3;

    static {
        CREATOR = new ae();
    }

    public ShopAbout$$Parcelable(Parcel parcel) {
        ShopAbout shopAbout;
        if (parcel.readInt() == -1) {
            shopAbout = null;
        } else {
            shopAbout = readcom_etsy_android_lib_models_ShopAbout(parcel);
        }
        this.shopAbout$$3 = shopAbout;
    }

    public ShopAbout$$Parcelable(ShopAbout shopAbout) {
        this.shopAbout$$3 = shopAbout;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopAbout$$3 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_ShopAbout(this.shopAbout$$3, parcel, i);
    }

    private ShopAbout readcom_etsy_android_lib_models_ShopAbout(Parcel parcel) {
        List list;
        ArrayList arrayList;
        int i;
        Object obj;
        int i2 = 0;
        List list2 = null;
        ShopAbout shopAbout = new ShopAbout();
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_ShopAboutImage(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        shopAbout.mImages = list;
        shopAbout.mLinks = (RelatedLinks) parcel.readSerializable();
        readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_ShopAboutVideo(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        shopAbout.mVideos = list;
        shopAbout.mUrl = parcel.readString();
        i = parcel.readInt();
        if (i >= 0) {
            arrayList = new ArrayList();
            while (i2 < i) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_ShopAboutMember(parcel);
                }
                arrayList.add(obj);
                i2++;
            }
            Object obj2 = arrayList;
        }
        shopAbout.mMembers = list2;
        shopAbout.mStory = parcel.readString();
        shopAbout.mStoryHeadline = parcel.readString();
        return shopAbout;
    }

    private ShopAboutImage readcom_etsy_android_lib_models_ShopAboutImage(Parcel parcel) {
        List list;
        EtsyId etsyId;
        Image image = null;
        ShopAboutImage shopAboutImage = new ShopAboutImage();
        shopAboutImage.mKey = parcel.readString();
        shopAboutImage.mImageUrl545xN = parcel.readString();
        shopAboutImage.mRank = parcel.readInt();
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            Object obj;
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_Image$Source(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        shopAboutImage.mSources = list;
        shopAboutImage.mUrl = parcel.readString();
        shopAboutImage.mImageUrl178x178 = parcel.readString();
        shopAboutImage.mImageUrl760xN = parcel.readString();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        shopAboutImage.mImageId = etsyId;
        if (parcel.readInt() != -1) {
            image = readcom_etsy_android_lib_models_apiv3_Image(parcel);
        }
        shopAboutImage.mImage = image;
        shopAboutImage.mCaption = parcel.readString();
        shopAboutImage.mUrl570xN = parcel.readString();
        shopAboutImage.mUrl224xN = parcel.readString();
        shopAboutImage.PORTRAIT_HEIGHT_RATIO = parcel.readDouble();
        shopAboutImage.mUrl680x540 = parcel.readString();
        shopAboutImage.mUrl75x75 = parcel.readString();
        shopAboutImage.mUrl170x135 = parcel.readString();
        shopAboutImage.mUrlFullxFull = parcel.readString();
        shopAboutImage.mUrl300x300 = parcel.readString();
        shopAboutImage.mUrl340x270 = parcel.readString();
        return shopAboutImage;
    }

    private Source readcom_etsy_android_lib_models_apiv3_Image$Source(Parcel parcel) {
        Source source = new Source();
        Image$Source$$PackageHelper.accessImage$Source$FS$height(source, parcel.readInt());
        Image$Source$$PackageHelper.accessImage$Source$FS$mUrl(source, parcel.readString());
        Image$Source$$PackageHelper.accessImage$Source$FS$width(source, parcel.readInt());
        return source;
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

    private ShopAboutVideo readcom_etsy_android_lib_models_ShopAboutVideo(Parcel parcel) {
        EtsyId etsyId;
        BaseModelImageWrapper baseModelImageWrapper;
        EtsyId etsyId2 = null;
        ShopAboutVideo shopAboutVideo = new ShopAboutVideo();
        shopAboutVideo.mUrl = parcel.readString();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        shopAboutVideo.mShopId = etsyId;
        if (parcel.readInt() == -1) {
            baseModelImageWrapper = null;
        } else {
            baseModelImageWrapper = readcom_etsy_android_lib_models_BaseModelImageWrapper(parcel);
        }
        shopAboutVideo.mThumbnail = baseModelImageWrapper;
        shopAboutVideo.mProviderStatus = parcel.readString();
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        shopAboutVideo.mVideoId = etsyId2;
        return shopAboutVideo;
    }

    private BaseModelImageWrapper readcom_etsy_android_lib_models_BaseModelImageWrapper(Parcel parcel) {
        Image image;
        if (parcel.readInt() == -1) {
            image = null;
        } else {
            image = readcom_etsy_android_lib_models_apiv3_Image(parcel);
        }
        BaseModelImageWrapper baseModelImageWrapper = new BaseModelImageWrapper(image);
        baseModelImageWrapper.mUrl570xN = parcel.readString();
        baseModelImageWrapper.mUrl224xN = parcel.readString();
        baseModelImageWrapper.PORTRAIT_HEIGHT_RATIO = parcel.readDouble();
        baseModelImageWrapper.mUrl680x540 = parcel.readString();
        baseModelImageWrapper.mUrl75x75 = parcel.readString();
        baseModelImageWrapper.mUrl170x135 = parcel.readString();
        baseModelImageWrapper.mUrlFullxFull = parcel.readString();
        baseModelImageWrapper.mUrl300x300 = parcel.readString();
        baseModelImageWrapper.mUrl340x270 = parcel.readString();
        return baseModelImageWrapper;
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

    private void writecom_etsy_android_lib_models_ShopAbout(ShopAbout shopAbout, Parcel parcel, int i) {
        if (shopAbout.mImages == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(shopAbout.mImages.size());
            for (ShopAboutImage shopAboutImage : shopAbout.mImages) {
                if (shopAboutImage == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_ShopAboutImage(shopAboutImage, parcel, i);
                }
            }
        }
        parcel.writeSerializable(shopAbout.mLinks);
        if (shopAbout.mVideos == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(shopAbout.mVideos.size());
            for (ShopAboutVideo shopAboutVideo : shopAbout.mVideos) {
                if (shopAboutVideo == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_ShopAboutVideo(shopAboutVideo, parcel, i);
                }
            }
        }
        parcel.writeString(shopAbout.mUrl);
        if (shopAbout.mMembers == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(shopAbout.mMembers.size());
            for (ShopAboutMember shopAboutMember : shopAbout.mMembers) {
                if (shopAboutMember == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_ShopAboutMember(shopAboutMember, parcel, i);
                }
            }
        }
        parcel.writeString(shopAbout.mStory);
        parcel.writeString(shopAbout.mStoryHeadline);
    }

    private void writecom_etsy_android_lib_models_ShopAboutImage(ShopAboutImage shopAboutImage, Parcel parcel, int i) {
        parcel.writeString(shopAboutImage.mKey);
        parcel.writeString(shopAboutImage.mImageUrl545xN);
        parcel.writeInt(shopAboutImage.mRank);
        if (shopAboutImage.mSources == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(shopAboutImage.mSources.size());
            for (Source source : shopAboutImage.mSources) {
                if (source == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_Image$Source(source, parcel, i);
                }
            }
        }
        parcel.writeString(shopAboutImage.mUrl);
        parcel.writeString(shopAboutImage.mImageUrl178x178);
        parcel.writeString(shopAboutImage.mImageUrl760xN);
        if (shopAboutImage.mImageId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(shopAboutImage.mImageId, parcel, i);
        }
        if (shopAboutImage.mImage == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Image(shopAboutImage.mImage, parcel, i);
        }
        parcel.writeString(shopAboutImage.mCaption);
        parcel.writeString(shopAboutImage.mUrl570xN);
        parcel.writeString(shopAboutImage.mUrl224xN);
        parcel.writeDouble(shopAboutImage.PORTRAIT_HEIGHT_RATIO);
        parcel.writeString(shopAboutImage.mUrl680x540);
        parcel.writeString(shopAboutImage.mUrl75x75);
        parcel.writeString(shopAboutImage.mUrl170x135);
        parcel.writeString(shopAboutImage.mUrlFullxFull);
        parcel.writeString(shopAboutImage.mUrl300x300);
        parcel.writeString(shopAboutImage.mUrl340x270);
    }

    private void writecom_etsy_android_lib_models_apiv3_Image$Source(Source source, Parcel parcel, int i) {
        parcel.writeInt(Image$Source$$PackageHelper.accessImage$Source$FG$height(source));
        parcel.writeString(Image$Source$$PackageHelper.accessImage$Source$FG$mUrl(source));
        parcel.writeInt(Image$Source$$PackageHelper.accessImage$Source$FG$width(source));
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

    private void writecom_etsy_android_lib_models_ShopAboutVideo(ShopAboutVideo shopAboutVideo, Parcel parcel, int i) {
        parcel.writeString(shopAboutVideo.mUrl);
        if (shopAboutVideo.mShopId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(shopAboutVideo.mShopId, parcel, i);
        }
        if (shopAboutVideo.mThumbnail == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_BaseModelImageWrapper(shopAboutVideo.mThumbnail, parcel, i);
        }
        parcel.writeString(shopAboutVideo.mProviderStatus);
        if (shopAboutVideo.mVideoId == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(shopAboutVideo.mVideoId, parcel, i);
    }

    private void writecom_etsy_android_lib_models_BaseModelImageWrapper(BaseModelImageWrapper baseModelImageWrapper, Parcel parcel, int i) {
        if (baseModelImageWrapper.mImage == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Image(baseModelImageWrapper.mImage, parcel, i);
        }
        parcel.writeString(baseModelImageWrapper.mUrl570xN);
        parcel.writeString(baseModelImageWrapper.mUrl224xN);
        parcel.writeDouble(baseModelImageWrapper.PORTRAIT_HEIGHT_RATIO);
        parcel.writeString(baseModelImageWrapper.mUrl680x540);
        parcel.writeString(baseModelImageWrapper.mUrl75x75);
        parcel.writeString(baseModelImageWrapper.mUrl170x135);
        parcel.writeString(baseModelImageWrapper.mUrlFullxFull);
        parcel.writeString(baseModelImageWrapper.mUrl300x300);
        parcel.writeString(baseModelImageWrapper.mUrl340x270);
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

    public int describeContents() {
        return 0;
    }

    public ShopAbout getParcel() {
        return this.shopAbout$$3;
    }
}
