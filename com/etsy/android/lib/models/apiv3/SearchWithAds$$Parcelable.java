package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.core.EtsyMoney$$PackageHelper;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.BaseModelImage$$PackageHelper;
import com.etsy.android.lib.models.EtsyArray;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.ListingImage$$PackageHelper;
import com.etsy.android.lib.models.apiv3.FacetCountListMap.MarketplaceFacets;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import org.parceler.ax;

public class SearchWithAds$$Parcelable implements android.os.Parcelable, ax<SearchWithAds> {
    public static final at CREATOR;
    private SearchWithAds searchWithAds$$0;

    static {
        CREATOR = new at();
    }

    public SearchWithAds$$Parcelable(Parcel parcel) {
        SearchWithAds searchWithAds;
        if (parcel.readInt() == -1) {
            searchWithAds = null;
        } else {
            searchWithAds = readcom_etsy_android_lib_models_apiv3_SearchWithAds(parcel);
        }
        this.searchWithAds$$0 = searchWithAds;
    }

    public SearchWithAds$$Parcelable(SearchWithAds searchWithAds) {
        this.searchWithAds$$0 = searchWithAds;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.searchWithAds$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_SearchWithAds(this.searchWithAds$$0, parcel, i);
    }

    private SearchWithAds readcom_etsy_android_lib_models_apiv3_SearchWithAds(Parcel parcel) {
        FacetCountListMap facetCountListMap;
        List list;
        ArrayList arrayList;
        int i;
        Object obj;
        int i2 = 0;
        ListingCard listingCard = null;
        SearchWithAds searchWithAds = new SearchWithAds();
        searchWithAds.mUserSearchResultCount = parcel.readInt();
        searchWithAds.mCount = parcel.readInt();
        searchWithAds.mShopSearchResultCount = parcel.readInt();
        if (parcel.readInt() == -1) {
            facetCountListMap = null;
        } else {
            facetCountListMap = readcom_etsy_android_lib_models_apiv3_FacetCountListMap(parcel);
        }
        searchWithAds.mFacetCounts = facetCountListMap;
        searchWithAds.mSuggestedShop = (SuggestedShop) parcel.readSerializable();
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_ListingCard(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        searchWithAds.mListingCardList = list;
        searchWithAds.mSpellingCorrection = parcel.readString();
        i = parcel.readInt();
        if (i < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            while (i2 < i) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_SearchGroup(parcel);
                }
                arrayList.add(obj);
                i2++;
            }
            obj = arrayList;
        }
        searchWithAds.mSearchGroups = list;
        if (parcel.readInt() != -1) {
            listingCard = readcom_etsy_android_lib_models_apiv3_ListingCard(parcel);
        }
        searchWithAds.mAnchorListing = listingCard;
        return searchWithAds;
    }

    private FacetCountListMap readcom_etsy_android_lib_models_apiv3_FacetCountListMap(Parcel parcel) {
        MarketplaceFacets marketplaceFacets;
        List list = null;
        FacetCountListMap facetCountListMap = new FacetCountListMap();
        if (parcel.readInt() == -1) {
            marketplaceFacets = null;
        } else {
            marketplaceFacets = m2377xfbbf7fac(parcel);
        }
        facetCountListMap.mMarketplaceFacets = marketplaceFacets;
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_FacetCount(parcel);
                }
                arrayList.add(obj);
            }
            Object obj2 = arrayList;
        }
        facetCountListMap.mCategoryFacets = list;
        return facetCountListMap;
    }

    private MarketplaceFacets m2377xfbbf7fac(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        MarketplaceFacets marketplaceFacets = new MarketplaceFacets();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        marketplaceFacets.mHasHandmade = z;
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        marketplaceFacets.mHasVintage = z2;
        return marketplaceFacets;
    }

    private FacetCount readcom_etsy_android_lib_models_apiv3_FacetCount(Parcel parcel) {
        List list = null;
        FacetCount facetCount = new FacetCount();
        facetCount.mId = parcel.readString();
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_FacetCount(parcel);
                }
                arrayList.add(obj);
            }
            Object obj2 = arrayList;
        }
        facetCount.mChildren = list;
        facetCount.mCount = parcel.readInt();
        facetCount.mName = parcel.readString();
        return facetCount;
    }

    private ListingCard readcom_etsy_android_lib_models_apiv3_ListingCard(Parcel parcel) {
        boolean z;
        EtsyId etsyId;
        EtsyMoney etsyMoney;
        EtsyId etsyId2 = null;
        boolean z2 = true;
        ListingCard listingCard = new ListingCard();
        listingCard.mHasError = parcel.readInt() == 1;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        listingCard.mIsSoldOut = z;
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        listingCard.mListingId = etsyId;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        listingCard.mIsAd = z;
        if (parcel.readInt() == -1) {
            etsyMoney = null;
        } else {
            etsyMoney = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        listingCard.mPrice = etsyMoney;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        listingCard.mHasCollections = z;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        listingCard.mIsFundOnEtsyCampaign = z;
        listingCard.mQuantity = parcel.readInt();
        listingCard.mServerFormattedPrice = parcel.readString();
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        listingCard.mIsFavorite = z2;
        listingCard.mViewType = parcel.readInt();
        listingCard.mPriceUnformatted = parcel.readDouble();
        listingCard.mListingImage = (BaseModelImage) parcel.readSerializable();
        listingCard.mFundOnEtsyCampaign = (FundOnEtsyCampaign) parcel.readParcelable(SearchWithAds$$Parcelable.class.getClassLoader());
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        listingCard.mShopId = etsyId2;
        listingCard.mTitle = parcel.readString();
        listingCard.mUrl = parcel.readString();
        listingCard.mShopAverageRating = parcel.readFloat();
        listingCard.mProlistLoggingKey = parcel.readString();
        listingCard.mShopName = parcel.readString();
        listingCard.mShopTotalRatingCount = parcel.readInt();
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

    private SearchGroup readcom_etsy_android_lib_models_apiv3_SearchGroup(Parcel parcel) {
        List list;
        List list2 = null;
        SearchGroup searchGroup = new SearchGroup();
        searchGroup.mFilterValues = (EtsyArray) parcel.readParcelable(SearchWithAds$$Parcelable.class.getClassLoader());
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
        searchGroup.mImages = list;
        int readInt2 = parcel.readInt();
        if (readInt2 >= 0) {
            list2 = new ArrayList();
            for (int i2 = 0; i2 < readInt2; i2++) {
                list2.add(parcel.readString());
            }
        }
        searchGroup.mListingIds = list2;
        searchGroup.mCount = parcel.readLong();
        searchGroup.mName = parcel.readString();
        searchGroup.mWide = parcel.readInt();
        return searchGroup;
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

    private void writecom_etsy_android_lib_models_apiv3_SearchWithAds(SearchWithAds searchWithAds, Parcel parcel, int i) {
        parcel.writeInt(searchWithAds.mUserSearchResultCount);
        parcel.writeInt(searchWithAds.mCount);
        parcel.writeInt(searchWithAds.mShopSearchResultCount);
        if (searchWithAds.mFacetCounts == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_FacetCountListMap(searchWithAds.mFacetCounts, parcel, i);
        }
        parcel.writeSerializable(searchWithAds.mSuggestedShop);
        if (searchWithAds.mListingCardList == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(searchWithAds.mListingCardList.size());
            for (ListingCard listingCard : searchWithAds.mListingCardList) {
                if (listingCard == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_ListingCard(listingCard, parcel, i);
                }
            }
        }
        parcel.writeString(searchWithAds.mSpellingCorrection);
        if (searchWithAds.mSearchGroups == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(searchWithAds.mSearchGroups.size());
            for (SearchGroup searchGroup : searchWithAds.mSearchGroups) {
                if (searchGroup == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_SearchGroup(searchGroup, parcel, i);
                }
            }
        }
        if (searchWithAds.mAnchorListing == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_ListingCard(searchWithAds.mAnchorListing, parcel, i);
    }

    private void writecom_etsy_android_lib_models_apiv3_FacetCountListMap(FacetCountListMap facetCountListMap, Parcel parcel, int i) {
        if (facetCountListMap.mMarketplaceFacets == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            m2378x648ccf03(facetCountListMap.mMarketplaceFacets, parcel, i);
        }
        if (facetCountListMap.mCategoryFacets == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(facetCountListMap.mCategoryFacets.size());
        for (FacetCount facetCount : facetCountListMap.mCategoryFacets) {
            if (facetCount == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                writecom_etsy_android_lib_models_apiv3_FacetCount(facetCount, parcel, i);
            }
        }
    }

    private void m2378x648ccf03(MarketplaceFacets marketplaceFacets, Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        if (marketplaceFacets.mHasHandmade) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (!marketplaceFacets.mHasVintage) {
            i3 = 0;
        }
        parcel.writeInt(i3);
    }

    private void writecom_etsy_android_lib_models_apiv3_FacetCount(FacetCount facetCount, Parcel parcel, int i) {
        parcel.writeString(facetCount.mId);
        if (facetCount.mChildren == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(facetCount.mChildren.size());
            for (FacetCount facetCount2 : facetCount.mChildren) {
                if (facetCount2 == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_FacetCount(facetCount2, parcel, i);
                }
            }
        }
        parcel.writeInt(facetCount.mCount);
        parcel.writeString(facetCount.mName);
    }

    private void writecom_etsy_android_lib_models_apiv3_ListingCard(ListingCard listingCard, Parcel parcel, int i) {
        int i2;
        int i3 = 0;
        parcel.writeInt(listingCard.mHasError ? 1 : 0);
        if (listingCard.mIsSoldOut) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (listingCard.mListingId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(listingCard.mListingId, parcel, i);
        }
        if (listingCard.mIsAd) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (listingCard.mPrice == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(listingCard.mPrice, parcel, i);
        }
        if (listingCard.mHasCollections) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (listingCard.mIsFundOnEtsyCampaign) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeInt(listingCard.mQuantity);
        parcel.writeString(listingCard.mServerFormattedPrice);
        if (listingCard.mIsFavorite) {
            i3 = 1;
        }
        parcel.writeInt(i3);
        parcel.writeInt(listingCard.mViewType);
        parcel.writeDouble(listingCard.mPriceUnformatted);
        parcel.writeSerializable(listingCard.mListingImage);
        parcel.writeParcelable(listingCard.mFundOnEtsyCampaign, i);
        if (listingCard.mShopId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(listingCard.mShopId, parcel, i);
        }
        parcel.writeString(listingCard.mTitle);
        parcel.writeString(listingCard.mUrl);
        parcel.writeFloat(listingCard.mShopAverageRating);
        parcel.writeString(listingCard.mProlistLoggingKey);
        parcel.writeString(listingCard.mShopName);
        parcel.writeInt(listingCard.mShopTotalRatingCount);
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

    private void writecom_etsy_android_lib_models_apiv3_SearchGroup(SearchGroup searchGroup, Parcel parcel, int i) {
        parcel.writeParcelable(searchGroup.mFilterValues, i);
        if (searchGroup.mImages == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(searchGroup.mImages.size());
            for (ListingImage listingImage : searchGroup.mImages) {
                if (listingImage == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_ListingImage(listingImage, parcel, i);
                }
            }
        }
        if (searchGroup.mListingIds == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(searchGroup.mListingIds.size());
            for (String writeString : searchGroup.mListingIds) {
                parcel.writeString(writeString);
            }
        }
        parcel.writeLong(searchGroup.mCount);
        parcel.writeString(searchGroup.mName);
        parcel.writeInt(searchGroup.mWide);
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

    public SearchWithAds getParcel() {
        return this.searchWithAds$$0;
    }
}
