package com.etsy.android.lib.models.homescreen;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.homescreen.d */
final class SeasonalSegmentCard$$Parcelable implements Creator<SeasonalSegmentCard$$Parcelable> {
    private SeasonalSegmentCard$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2786a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2787a(i);
    }

    public SeasonalSegmentCard$$Parcelable m2786a(Parcel parcel) {
        return new SeasonalSegmentCard$$Parcelable(parcel);
    }

    public SeasonalSegmentCard$$Parcelable[] m2787a(int i) {
        return new SeasonalSegmentCard$$Parcelable[i];
    }
}
ss SeasonalSegmentCard$$Parcelable implements android.os.Parcelable, ax<SeasonalSegmentCard> {
    public static final SeasonalSegmentCard$$Parcelable CREATOR;
    private SeasonalSegmentCard seasonalSegmentCard$$0;

    static {
        CREATOR = new SeasonalSegmentCard$$Parcelable();
    }

    public SeasonalSegmentCard$$Parcelable(Parcel parcel) {
        SeasonalSegmentCard seasonalSegmentCard;
        if (parcel.readInt() == -1) {
            seasonalSegmentCard = null;
        } else {
            seasonalSegmentCard = readcom_etsy_android_lib_models_homescreen_SeasonalSegmentCard(parcel);
        }
        this.seasonalSegmentCard$$0 = seasonalSegmentCard;
    }

    public SeasonalSegmentCard$$Parcelable(SeasonalSegmentCard seasonalSegmentCard) {
        this.seasonalSegmentCard$$0 = seasonalSegmentCard;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.seasonalSegmentCard$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_homescreen_SeasonalSegmentCard(this.seasonalSegmentCard$$0, parcel, i);
    }

    private SeasonalSegmentCard readcom_etsy_android_lib_models_homescreen_SeasonalSegmentCard(Parcel parcel) {
        boolean z;
        List list = null;
        Segment seasonalSegmentCard = new SeasonalSegmentCard();
        seasonalSegmentCard.mLinkTitle = parcel.readString();
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_ListingCard(parcel);
                }
                arrayList.add(obj);
            }
            Object obj2 = arrayList;
        }
        seasonalSegmentCard.mListingCards = list;
        Segment$$PackageHelper.accessSegment$FS$mImageUrl(seasonalSegmentCard, parcel.readString());
        Segment$$PackageHelper.accessSegment$FS$mListingId(seasonalSegmentCard, parcel.readInt());
        Segment$$PackageHelper.accessSegment$FS$mShopId(seasonalSegmentCard, parcel.readInt());
        Segment$$PackageHelper.accessSegment$FS$mWeight(seasonalSegmentCard, parcel.readInt());
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        Segment$$PackageHelper.accessSegment$FS$mHasChildren(seasonalSegmentCard, z);
        Segment$$PackageHelper.accessSegment$FS$mStandaloneText(seasonalSegmentCard, parcel.readString());
        Segment$$PackageHelper.accessSegment$FS$mName(seasonalSegmentCard, parcel.readString());
        Segment$$PackageHelper.accessSegment$FS$mPath(seasonalSegmentCard, parcel.readString());
        Segment$$PackageHelper.accessSegment$FS$mShopName(seasonalSegmentCard, parcel.readString());
        return seasonalSegmentCard;
    }

    private ListingCard readcom_etsy_android_lib_models_apiv3_ListingCard(Parcel parcel) {
        boolean z;
        EtsyId etsyId;
        EtsyMoney etsyMoney;
        EtsyId etsyId2 = null;
        boolean z2 = true;
        ListingCard listingCard = new ListingCard();
        ListingCard$$PackageHelper.accessListingCard$FS$mHasError(listingCard, parcel.readInt() == 1);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mIsSoldOut(listingCard, z);
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mListingId(listingCard, etsyId);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mIsAd(listingCard, z);
        if (parcel.readInt() == -1) {
            etsyMoney = null;
        } else {
            etsyMoney = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mPrice(listingCard, etsyMoney);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mHasCollections(listingCard, z);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mIsFundOnEtsyCampaign(listingCard, z);
        ListingCard$$PackageHelper.accessListingCard$FS$mQuantity(listingCard, parcel.readInt());
        ListingCard$$PackageHelper.accessListingCard$FS$mServerFormattedPrice(listingCard, parcel.readString());
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mIsFavorite(listingCard, z2);
        ListingCard$$PackageHelper.accessListingCard$FS$mViewType(listingCard, parcel.readInt());
        ListingCard$$PackageHelper.accessListingCard$FS$mPriceUnformatted(listingCard, parcel.readDouble());
        ListingCard$$PackageHelper.accessListingCard$FS$mListingImage(listingCard, (BaseModelImage) parcel.readSerializable());
        ListingCard$$PackageHelper.accessListingCard$FS$mFundOnEtsyCampaign(listingCard, (FundOnEtsyCampaign) parcel.readParcelable(SeasonalSegmentCard$$Parcelable.class.getClassLoader()));
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mShopId(listingCard, etsyId2);
        ListingCard$$PackageHelper.accessListingCard$FS$mTitle(listingCard, parcel.readString());
        ListingCard$$PackageHelper.accessListingCard$FS$mUrl(listingCard, parcel.readString());
        ListingCard$$PackageHelper.accessListingCard$FS$mShopAverageRating(listingCard, parcel.readFloat());
        ListingCard$$PackageHelper.accessListingCard$FS$mProlistLoggingKey(listingCard, parcel.readString());
        ListingCard$$PackageHelper.accessListingCard$FS$mShopName(listingCard, parcel.readString());
        ListingCard$$PackageHelper.accessListingCard$FS$mShopTotalRatingCount(listingCard, parcel.readInt());
        return listingCard;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private EtsyMoney readcom_etsy_android_lib_core_EtsyMoney(Parcel parcel) {
        Integer num;
        EtsyMoney etsyMoney = new EtsyMoney((BigDecimal) parcel.readSerializable(), (Currency) parcel.readSerializable());
        if (parcel.readInt() < 0) {
            num = null;
        } else {
            num = Integer.valueOf(parcel.readInt());
        }
        EtsyMoney$$PackageHelper.m1649a(etsyMoney, num);
        return etsyMoney;
    }

    private void writecom_etsy_android_lib_models_homescreen_SeasonalSegmentCard(SeasonalSegmentCard seasonalSegmentCard, Parcel parcel, int i) {
        parcel.writeString(seasonalSegmentCard.mLinkTitle);
        if (seasonalSegmentCard.mListingCards == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(seasonalSegmentCard.mListingCards.size());
            for (ListingCard listingCard : seasonalSegmentCard.mListingCards) {
                if (listingCard == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_ListingCard(listingCard, parcel, i);
                }
            }
        }
        parcel.writeString(Segment$$PackageHelper.accessSegment$FG$mImageUrl(seasonalSegmentCard));
        parcel.writeInt(Segment$$PackageHelper.accessSegment$FG$mListingId(seasonalSegmentCard));
        parcel.writeInt(Segment$$PackageHelper.accessSegment$FG$mShopId(seasonalSegmentCard));
        parcel.writeInt(Segment$$PackageHelper.accessSegment$FG$mWeight(seasonalSegmentCard));
        parcel.writeInt(Segment$$PackageHelper.accessSegment$FG$mHasChildren(seasonalSegmentCard) ? 1 : 0);
        parcel.writeString(Segment$$PackageHelper.accessSegment$FG$mStandaloneText(seasonalSegmentCard));
        parcel.writeString(Segment$$PackageHelper.accessSegment$FG$mName(seasonalSegmentCard));
        parcel.writeString(Segment$$PackageHelper.accessSegment$FG$mPath(seasonalSegmentCard));
        parcel.writeString(Segment$$PackageHelper.accessSegment$FG$mShopName(seasonalSegmentCard));
    }

    private void writecom_etsy_android_lib_models_apiv3_ListingCard(ListingCard listingCard, Parcel parcel, int i) {
        int i2;
        int i3 = 0;
        parcel.writeInt(ListingCard$$PackageHelper.accessListingCard$FG$mHasError(listingCard) ? 1 : 0);
        if (ListingCard$$PackageHelper.accessListingCard$FG$mIsSoldOut(listingCard)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (ListingCard$$PackageHelper.accessListingCard$FG$mListingId(listingCard) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(ListingCard$$PackageHelper.accessListingCard$FG$mListingId(listingCard), parcel, i);
        }
        if (ListingCard$$PackageHelper.accessListingCard$FG$mIsAd(listingCard)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (ListingCard$$PackageHelper.accessListingCard$FG$mPrice(listingCard) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(ListingCard$$PackageHelper.accessListingCard$FG$mPrice(listingCard), parcel, i);
        }
        if (ListingCard$$PackageHelper.accessListingCard$FG$mHasCollections(listingCard)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (ListingCard$$PackageHelper.accessListingCard$FG$mIsFundOnEtsyCampaign(listingCard)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeInt(ListingCard$$PackageHelper.accessListingCard$FG$mQuantity(listingCard));
        parcel.writeString(ListingCard$$PackageHelper.accessListingCard$FG$mServerFormattedPrice(listingCard));
        if (ListingCard$$PackageHelper.accessListingCard$FG$mIsFavorite(listingCard)) {
            i3 = 1;
        }
        parcel.writeInt(i3);
        parcel.writeInt(ListingCard$$PackageHelper.accessListingCard$FG$mViewType(listingCard));
        parcel.writeDouble(ListingCard$$PackageHelper.accessListingCard$FG$mPriceUnformatted(listingCard));
        parcel.writeSerializable(ListingCard$$PackageHelper.accessListingCard$FG$mListingImage(listingCard));
        parcel.writeParcelable(ListingCard$$PackageHelper.accessListingCard$FG$mFundOnEtsyCampaign(listingCard), i);
        if (ListingCard$$PackageHelper.accessListingCard$FG$mShopId(listingCard) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(ListingCard$$PackageHelper.accessListingCard$FG$mShopId(listingCard), parcel, i);
        }
        parcel.writeString(ListingCard$$PackageHelper.accessListingCard$FG$mTitle(listingCard));
        parcel.writeString(ListingCard$$PackageHelper.accessListingCard$FG$mUrl(listingCard));
        parcel.writeFloat(ListingCard$$PackageHelper.accessListingCard$FG$mShopAverageRating(listingCard));
        parcel.writeString(ListingCard$$PackageHelper.accessListingCard$FG$mProlistLoggingKey(listingCard));
        parcel.writeString(ListingCard$$PackageHelper.accessListingCard$FG$mShopName(listingCard));
        parcel.writeInt(ListingCard$$PackageHelper.accessListingCard$FG$mShopTotalRatingCount(listingCard));
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    private void writecom_etsy_android_lib_core_EtsyMoney(EtsyMoney etsyMoney, Parcel parcel, int i) {
        parcel.writeSerializable(etsyMoney.getAmount());
        parcel.writeSerializable(etsyMoney.getCurrency());
        if (EtsyMoney$$PackageHelper.m1648a(etsyMoney) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        parcel.writeInt(EtsyMoney$$PackageHelper.m1648a(etsyMoney).intValue());
    }

    public int describeContents() {
        return 0;
    }

    public SeasonalSegmentCard getParcel() {
        return this.seasonalSegmentCard$$0;
    }
}
