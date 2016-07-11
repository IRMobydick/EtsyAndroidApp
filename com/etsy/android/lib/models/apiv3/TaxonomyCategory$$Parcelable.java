package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.BaseModelImage$$PackageHelper;
import com.etsy.android.lib.models.EtsyArray;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.ListingImage$$PackageHelper;
import com.etsy.android.lib.models.cardviewelement.PageLink;
import com.etsy.android.lib.models.cardviewelement.PageLink$$PackageHelper;
import com.etsy.android.lib.models.cardviewelement.SearchPageLink;
import com.etsy.android.lib.models.cardviewelement.SearchPageLink$$PackageHelper;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import java.util.ArrayList;
import java.util.List;
import org.parceler.ax;

public class TaxonomyCategory$$Parcelable implements android.os.Parcelable, ax<TaxonomyCategory> {
    public static final bm CREATOR;
    private TaxonomyCategory taxonomyCategory$$0;

    static {
        CREATOR = new bm();
    }

    public TaxonomyCategory$$Parcelable(Parcel parcel) {
        TaxonomyCategory taxonomyCategory;
        if (parcel.readInt() == -1) {
            taxonomyCategory = null;
        } else {
            taxonomyCategory = readcom_etsy_android_lib_models_apiv3_TaxonomyCategory(parcel);
        }
        this.taxonomyCategory$$0 = taxonomyCategory;
    }

    public TaxonomyCategory$$Parcelable(TaxonomyCategory taxonomyCategory) {
        this.taxonomyCategory$$0 = taxonomyCategory;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.taxonomyCategory$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_TaxonomyCategory(this.taxonomyCategory$$0, parcel, i);
    }

    private TaxonomyCategory readcom_etsy_android_lib_models_apiv3_TaxonomyCategory(Parcel parcel) {
        SearchPageLink searchPageLink;
        EtsyId etsyId;
        List list;
        List list2 = null;
        TaxonomyCategory taxonomyCategory = new TaxonomyCategory();
        taxonomyCategory.mViewType = parcel.readInt();
        if (parcel.readInt() == -1) {
            searchPageLink = null;
        } else {
            searchPageLink = readcom_etsy_android_lib_models_cardviewelement_SearchPageLink(parcel);
        }
        taxonomyCategory.mPageLink = searchPageLink;
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        taxonomyCategory.mTaxonomyId = etsyId;
        taxonomyCategory.mPath = parcel.readString();
        taxonomyCategory.mFilterValues = (EtsyArray) parcel.readParcelable(TaxonomyCategory$$Parcelable.class.getClassLoader());
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
                    obj = readcom_etsy_android_lib_models_ListingImage(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        taxonomyCategory.mImages = list;
        int readInt2 = parcel.readInt();
        if (readInt2 >= 0) {
            list2 = new ArrayList();
            for (int i2 = 0; i2 < readInt2; i2++) {
                list2.add(parcel.readString());
            }
        }
        taxonomyCategory.mListingIds = list2;
        taxonomyCategory.mCount = parcel.readLong();
        taxonomyCategory.mName = parcel.readString();
        taxonomyCategory.mWide = parcel.readInt();
        return taxonomyCategory;
    }

    private SearchPageLink readcom_etsy_android_lib_models_cardviewelement_SearchPageLink(Parcel parcel) {
        EtsyId etsyId;
        boolean z = true;
        PageLink searchPageLink = new SearchPageLink();
        if (parcel.readInt() != 1) {
            z = false;
        }
        SearchPageLink$$PackageHelper.accessSearchPageLink$FS$mIsCategoryPage(searchPageLink, z);
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        SearchPageLink$$PackageHelper.accessSearchPageLink$FS$mTaxonomyId(searchPageLink, etsyId);
        PageLink$$PackageHelper.accessPageLink$FS$mEventName(searchPageLink, parcel.readString());
        PageLink$$PackageHelper.accessPageLink$FS$mPageTitle(searchPageLink, parcel.readString());
        PageLink$$PackageHelper.accessPageLink$FS$linkTitle(searchPageLink, parcel.readString());
        return searchPageLink;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private ListingImage readcom_etsy_android_lib_models_ListingImage(Parcel parcel) {
        EtsyId etsyId;
        BaseModelImage listingImage = new ListingImage();
        ListingImage$$PackageHelper.accessListingImage$FS$mFullWidth(listingImage, parcel.readInt());
        ListingImage$$PackageHelper.accessListingImage$FS$mHue(listingImage, parcel.readInt());
        ListingImage$$PackageHelper.accessListingImage$FS$mPostCalculatedColor(listingImage, parcel.readInt());
        ListingImage$$PackageHelper.accessListingImage$FS$mSaturation(listingImage, parcel.readInt());
        ListingImage$$PackageHelper.accessListingImage$FS$mHexColor(listingImage, parcel.readInt());
        ListingImage$$PackageHelper.accessListingImage$FS$mRank(listingImage, parcel.readInt());
        ListingImage$$PackageHelper.accessListingImage$FS$mFullHeight(listingImage, parcel.readInt());
        ListingImage$$PackageHelper.accessListingImage$FS$mRed(listingImage, parcel.readInt());
        ListingImage$$PackageHelper.accessListingImage$FS$mBlue(listingImage, parcel.readInt());
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        ListingImage$$PackageHelper.accessListingImage$FS$mImageId(listingImage, etsyId);
        ListingImage$$PackageHelper.accessListingImage$FS$mGreen(listingImage, parcel.readInt());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl570xN(listingImage, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl224xN(listingImage, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$PORTRAIT_HEIGHT_RATIO(listingImage, parcel.readDouble());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl680x540(listingImage, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl75x75(listingImage, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl170x135(listingImage, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrlFullxFull(listingImage, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl300x300(listingImage, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl340x270(listingImage, parcel.readString());
        return listingImage;
    }

    private void writecom_etsy_android_lib_models_apiv3_TaxonomyCategory(TaxonomyCategory taxonomyCategory, Parcel parcel, int i) {
        parcel.writeInt(taxonomyCategory.mViewType);
        if (taxonomyCategory.mPageLink == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_cardviewelement_SearchPageLink(taxonomyCategory.mPageLink, parcel, i);
        }
        if (taxonomyCategory.mTaxonomyId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(taxonomyCategory.mTaxonomyId, parcel, i);
        }
        parcel.writeString(taxonomyCategory.mPath);
        parcel.writeParcelable(taxonomyCategory.mFilterValues, i);
        if (taxonomyCategory.mImages == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(taxonomyCategory.mImages.size());
            for (ListingImage listingImage : taxonomyCategory.mImages) {
                if (listingImage == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_ListingImage(listingImage, parcel, i);
                }
            }
        }
        if (taxonomyCategory.mListingIds == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(taxonomyCategory.mListingIds.size());
            for (String writeString : taxonomyCategory.mListingIds) {
                parcel.writeString(writeString);
            }
        }
        parcel.writeLong(taxonomyCategory.mCount);
        parcel.writeString(taxonomyCategory.mName);
        parcel.writeInt(taxonomyCategory.mWide);
    }

    private void writecom_etsy_android_lib_models_cardviewelement_SearchPageLink(SearchPageLink searchPageLink, Parcel parcel, int i) {
        parcel.writeInt(SearchPageLink$$PackageHelper.accessSearchPageLink$FG$mIsCategoryPage(searchPageLink) ? 1 : 0);
        if (SearchPageLink$$PackageHelper.accessSearchPageLink$FG$mTaxonomyId(searchPageLink) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(SearchPageLink$$PackageHelper.accessSearchPageLink$FG$mTaxonomyId(searchPageLink), parcel, i);
        }
        parcel.writeString(PageLink$$PackageHelper.accessPageLink$FG$mEventName(searchPageLink));
        parcel.writeString(PageLink$$PackageHelper.accessPageLink$FG$mPageTitle(searchPageLink));
        parcel.writeString(PageLink$$PackageHelper.accessPageLink$FG$linkTitle(searchPageLink));
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    private void writecom_etsy_android_lib_models_ListingImage(ListingImage listingImage, Parcel parcel, int i) {
        parcel.writeInt(ListingImage$$PackageHelper.accessListingImage$FG$mFullWidth(listingImage));
        parcel.writeInt(ListingImage$$PackageHelper.accessListingImage$FG$mHue(listingImage));
        parcel.writeInt(ListingImage$$PackageHelper.accessListingImage$FG$mPostCalculatedColor(listingImage));
        parcel.writeInt(ListingImage$$PackageHelper.accessListingImage$FG$mSaturation(listingImage));
        parcel.writeInt(ListingImage$$PackageHelper.accessListingImage$FG$mHexColor(listingImage));
        parcel.writeInt(ListingImage$$PackageHelper.accessListingImage$FG$mRank(listingImage));
        parcel.writeInt(ListingImage$$PackageHelper.accessListingImage$FG$mFullHeight(listingImage));
        parcel.writeInt(ListingImage$$PackageHelper.accessListingImage$FG$mRed(listingImage));
        parcel.writeInt(ListingImage$$PackageHelper.accessListingImage$FG$mBlue(listingImage));
        if (ListingImage$$PackageHelper.accessListingImage$FG$mImageId(listingImage) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(ListingImage$$PackageHelper.accessListingImage$FG$mImageId(listingImage), parcel, i);
        }
        parcel.writeInt(ListingImage$$PackageHelper.accessListingImage$FG$mGreen(listingImage));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl570xN(listingImage));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl224xN(listingImage));
        parcel.writeDouble(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$PORTRAIT_HEIGHT_RATIO(listingImage));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl680x540(listingImage));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl75x75(listingImage));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl170x135(listingImage));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrlFullxFull(listingImage));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl300x300(listingImage));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl340x270(listingImage));
    }

    public int describeContents() {
        return 0;
    }

    public TaxonomyCategory getParcel() {
        return this.taxonomyCategory$$0;
    }
}
