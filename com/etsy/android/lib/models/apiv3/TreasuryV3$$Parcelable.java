package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.core.EtsyMoney$$PackageHelper;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import org.parceler.ax;

public class TreasuryV3$$Parcelable implements android.os.Parcelable, ax<TreasuryV3> {
    public static final bo CREATOR;
    private TreasuryV3 treasuryV3$$4;

    static {
        CREATOR = new bo();
    }

    public TreasuryV3$$Parcelable(Parcel parcel) {
        TreasuryV3 treasuryV3;
        if (parcel.readInt() == -1) {
            treasuryV3 = null;
        } else {
            treasuryV3 = readcom_etsy_android_lib_models_apiv3_TreasuryV3(parcel);
        }
        this.treasuryV3$$4 = treasuryV3;
    }

    public TreasuryV3$$Parcelable(TreasuryV3 treasuryV3) {
        this.treasuryV3$$4 = treasuryV3;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.treasuryV3$$4 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_TreasuryV3(this.treasuryV3$$4, parcel, i);
    }

    private TreasuryV3 readcom_etsy_android_lib_models_apiv3_TreasuryV3(Parcel parcel) {
        List list;
        EtsyId etsyId;
        UserCard userCard = null;
        TreasuryV3 treasuryV3 = new TreasuryV3();
        treasuryV3.mClicks = parcel.readInt();
        treasuryV3.mId = parcel.readString();
        treasuryV3.mOwnerName = parcel.readString();
        treasuryV3.mUrl = parcel.readString();
        treasuryV3.mTitle = parcel.readString();
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
                    obj = readcom_etsy_android_lib_models_apiv3_ListingCard(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        treasuryV3.mListings = list;
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        treasuryV3.mOwnerId = etsyId;
        treasuryV3.mListingsCount = parcel.readInt();
        treasuryV3.mViews = parcel.readInt();
        treasuryV3.mDescription = parcel.readString();
        if (parcel.readInt() != -1) {
            userCard = readcom_etsy_android_lib_models_apiv3_UserCard(parcel);
        }
        treasuryV3.mOwner = userCard;
        return treasuryV3;
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
        listingCard.mFundOnEtsyCampaign = (FundOnEtsyCampaign) parcel.readParcelable(TreasuryV3$$Parcelable.class.getClassLoader());
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

    private UserCard readcom_etsy_android_lib_models_apiv3_UserCard(Parcel parcel) {
        List list;
        EtsyId etsyId = null;
        UserCard userCard = new UserCard();
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
                    obj = readcom_etsy_android_lib_models_apiv3_ListingCard(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        userCard.mRecentFavorites = list;
        userCard.mFollowersCount = parcel.readInt();
        userCard.mAvatarUrl = parcel.readString();
        userCard.mLoginName = parcel.readString();
        userCard.mCasualNameOrLogin = parcel.readString();
        userCard.mRealNameOrLogin = parcel.readString();
        userCard.mMaskedEmail = parcel.readString();
        userCard.mLocationDescription = parcel.readString();
        userCard.mFavoritesCount = parcel.readInt();
        if (parcel.readInt() != -1) {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        userCard.mUserId = etsyId;
        return userCard;
    }

    private void writecom_etsy_android_lib_models_apiv3_TreasuryV3(TreasuryV3 treasuryV3, Parcel parcel, int i) {
        parcel.writeInt(treasuryV3.mClicks);
        parcel.writeString(treasuryV3.mId);
        parcel.writeString(treasuryV3.mOwnerName);
        parcel.writeString(treasuryV3.mUrl);
        parcel.writeString(treasuryV3.mTitle);
        if (treasuryV3.mListings == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(treasuryV3.mListings.size());
            for (ListingCard listingCard : treasuryV3.mListings) {
                if (listingCard == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_ListingCard(listingCard, parcel, i);
                }
            }
        }
        if (treasuryV3.mOwnerId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(treasuryV3.mOwnerId, parcel, i);
        }
        parcel.writeInt(treasuryV3.mListingsCount);
        parcel.writeInt(treasuryV3.mViews);
        parcel.writeString(treasuryV3.mDescription);
        if (treasuryV3.mOwner == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_UserCard(treasuryV3.mOwner, parcel, i);
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

    private void writecom_etsy_android_lib_models_apiv3_UserCard(UserCard userCard, Parcel parcel, int i) {
        if (userCard.mRecentFavorites == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(userCard.mRecentFavorites.size());
            for (ListingCard listingCard : userCard.mRecentFavorites) {
                if (listingCard == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_ListingCard(listingCard, parcel, i);
                }
            }
        }
        parcel.writeInt(userCard.mFollowersCount);
        parcel.writeString(userCard.mAvatarUrl);
        parcel.writeString(userCard.mLoginName);
        parcel.writeString(userCard.mCasualNameOrLogin);
        parcel.writeString(userCard.mRealNameOrLogin);
        parcel.writeString(userCard.mMaskedEmail);
        parcel.writeString(userCard.mLocationDescription);
        parcel.writeInt(userCard.mFavoritesCount);
        if (userCard.mUserId == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(userCard.mUserId, parcel, i);
    }

    public int describeContents() {
        return 0;
    }

    public TreasuryV3 getParcel() {
        return this.treasuryV3$$4;
    }
}
