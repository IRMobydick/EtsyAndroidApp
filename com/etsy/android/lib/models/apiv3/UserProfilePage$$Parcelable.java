package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.core.EtsyMoney$$PackageHelper;
import com.etsy.android.lib.models.Attendee;
import com.etsy.android.lib.models.Attendee$$PackageHelper;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.BaseModelImage$$PackageHelper;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.Country$$PackageHelper;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.ListingImage$$PackageHelper;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.LocalMarket$$PackageHelper;
import com.etsy.android.lib.models.LocalMarketAttendeeSchedule;
import com.etsy.android.lib.models.LocalMarketAttendeeSchedule$$PackageHelper;
import com.etsy.android.lib.models.LocalMarketAttendeeSchedule$Day$$PackageHelper;
import com.etsy.android.lib.models.LocalMarketAttendeeSchedule.Day;
import com.etsy.android.lib.models.LocalMarketCard;
import com.etsy.android.lib.models.LocalMarketCard$$PackageHelper;
import com.etsy.android.lib.models.LocalStore;
import com.etsy.android.lib.models.LocalStore$$PackageHelper;
import com.etsy.android.lib.models.LocalStoreImage;
import com.etsy.android.lib.models.ScheduleExpanded;
import com.etsy.android.lib.models.ScheduleExpanded$$PackageHelper;
import com.etsy.android.lib.models.ShortenedUrl;
import com.etsy.android.lib.models.UserAddress;
import com.etsy.android.lib.models.UserAddress$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image.Source;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import com.etsy.android.lib.models.datatypes.TimeRange;
import com.etsy.android.lib.models.datatypes.TimeRange$$PackageHelper;
import com.etsy.android.lib.models.enums.WeekDay;
import com.etsy.android.lib.util.ar;
import com.etsy.android.lib.util.au;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.parceler.ax;

public class UserProfilePage$$Parcelable implements android.os.Parcelable, ax<UserProfilePage> {
    public static final bs CREATOR;
    private UserProfilePage userProfilePage$$0;

    static {
        CREATOR = new bs();
    }

    public UserProfilePage$$Parcelable(Parcel parcel) {
        UserProfilePage userProfilePage;
        if (parcel.readInt() == -1) {
            userProfilePage = null;
        } else {
            userProfilePage = readcom_etsy_android_lib_models_apiv3_UserProfilePage(parcel);
        }
        this.userProfilePage$$0 = userProfilePage;
    }

    public UserProfilePage$$Parcelable(UserProfilePage userProfilePage) {
        this.userProfilePage$$0 = userProfilePage;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.userProfilePage$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_UserProfilePage(this.userProfilePage$$0, parcel, i);
    }

    private UserProfilePage readcom_etsy_android_lib_models_apiv3_UserProfilePage(Parcel parcel) {
        List list;
        ArrayList arrayList;
        int i;
        Object obj;
        UserProfileV3 userProfileV3;
        int i2 = 0;
        ShopCard shopCard = null;
        UserProfilePage userProfilePage = new UserProfilePage();
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_TreasuryV3(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        userProfilePage.mFavoriteTreasuries = list;
        readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_AppreciationPhoto(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        userProfilePage.mAppreciationPhotos = list;
        if (parcel.readInt() == -1) {
            userProfileV3 = null;
        } else {
            userProfileV3 = readcom_etsy_android_lib_models_apiv3_UserProfileV3(parcel);
        }
        userProfilePage.mUserProfile = userProfileV3;
        readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                arrayList.add((Collection) parcel.readSerializable());
            }
            obj = arrayList;
        }
        userProfilePage.mCollections = list;
        i = parcel.readInt();
        if (i < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            while (i2 < i) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_ShopCard(parcel);
                }
                arrayList.add(obj);
                i2++;
            }
            obj = arrayList;
        }
        userProfilePage.mFavoriteShops = list;
        if (parcel.readInt() != -1) {
            shopCard = readcom_etsy_android_lib_models_apiv3_ShopCard(parcel);
        }
        userProfilePage.mShopCard = shopCard;
        return userProfilePage;
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
        listingCard.mFundOnEtsyCampaign = (FundOnEtsyCampaign) parcel.readParcelable(UserProfilePage$$Parcelable.class.getClassLoader());
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

    private AppreciationPhoto readcom_etsy_android_lib_models_apiv3_AppreciationPhoto(Parcel parcel) {
        EtsyId etsyId;
        EtsyId etsyId2 = null;
        BaseModelImage appreciationPhoto = new AppreciationPhoto();
        appreciationPhoto.mShortenedShareUrl = (ShortenedUrl) parcel.readSerializable();
        appreciationPhoto.mCreateDate = new au().m3269a(parcel);
        appreciationPhoto.mStatus = parcel.readString();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        appreciationPhoto.mTransactionId = etsyId;
        appreciationPhoto.mIsSellerApproved = parcel.readInt() == 1;
        appreciationPhoto.mListingTitle = parcel.readString();
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        appreciationPhoto.mImageId = etsyId2;
        appreciationPhoto.mSellerAvatarUrl = parcel.readString();
        appreciationPhoto.mShopName = parcel.readString();
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl570xN(appreciationPhoto, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl224xN(appreciationPhoto, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$PORTRAIT_HEIGHT_RATIO(appreciationPhoto, parcel.readDouble());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl680x540(appreciationPhoto, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl75x75(appreciationPhoto, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl170x135(appreciationPhoto, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrlFullxFull(appreciationPhoto, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl300x300(appreciationPhoto, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl340x270(appreciationPhoto, parcel.readString());
        return appreciationPhoto;
    }

    private UserProfileV3 readcom_etsy_android_lib_models_apiv3_UserProfileV3(Parcel parcel) {
        boolean z;
        EtsyId etsyId;
        boolean z2 = true;
        UserProfileV3 userProfileV3 = new UserProfileV3();
        userProfileV3.mAppreciationPhotoCount = parcel.readInt();
        userProfileV3.mFavoriteListingsCount = parcel.readInt();
        userProfileV3.mLoginName = parcel.readString();
        userProfileV3.mLocation = parcel.readString();
        userProfileV3.mFollowingCount = parcel.readInt();
        userProfileV3.mAreFavoriteShopsPublic = parcel.readInt() == 1;
        userProfileV3.mFavoriteTreasuriesCount = parcel.readInt();
        userProfileV3.mBio = parcel.readString();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        userProfileV3.mAreFavoriteListingsPublic = z;
        userProfileV3.mAvatarUrl = parcel.readString();
        userProfileV3.mFirstName = parcel.readString();
        userProfileV3.mDisplayName = parcel.readString();
        userProfileV3.mFavoriteShopsCount = parcel.readInt();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        userProfileV3.mIsFollowing = z;
        userProfileV3.mTransactionsSoldCount = parcel.readInt();
        userProfileV3.mFollowerCount = parcel.readInt();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        userProfileV3.mIsAdmin = z;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        userProfileV3.mIsSeller = z;
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        userProfileV3.mAreFavoriteTreasuriesPublic = z2;
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        userProfileV3.mUserId = etsyId;
        return userProfileV3;
    }

    private ShopCard readcom_etsy_android_lib_models_apiv3_ShopCard(Parcel parcel) {
        Image image;
        boolean z;
        EtsyId etsyId;
        LocalMarket localMarket;
        List list;
        Rating rating;
        int i = 0;
        boolean z2 = true;
        EtsyId etsyId2 = null;
        ShopCard shopCard = new ShopCard();
        if (parcel.readInt() == -1) {
            image = null;
        } else {
            image = readcom_etsy_android_lib_models_apiv3_Image(parcel);
        }
        shopCard.mIcon = image;
        shopCard.mLoginName = parcel.readString();
        shopCard.mLocation = parcel.readString();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopCard.mIsVacation = z;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopCard.mHasIcon = z;
        shopCard.mHeadline = parcel.readString();
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        shopCard.mIsFavorite = z2;
        shopCard.mSellerAvatar = parcel.readString();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        shopCard.mShopId = etsyId;
        if (parcel.readInt() == -1) {
            localMarket = null;
        } else {
            localMarket = readcom_etsy_android_lib_models_LocalMarket(parcel);
        }
        shopCard.mLocalEvent = localMarket;
        shopCard.mShopUrl = parcel.readString();
        shopCard.mActiveListingCount = parcel.readInt();
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            Object obj;
            ArrayList arrayList = new ArrayList();
            while (i < readInt) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_ListingCard(parcel);
                }
                arrayList.add(obj);
                i++;
            }
            obj = arrayList;
        }
        shopCard.mDisplayListings = list;
        shopCard.mOpenDate = new au().m3269a(parcel);
        if (parcel.readInt() == -1) {
            rating = null;
        } else {
            rating = readcom_etsy_android_lib_models_apiv3_Rating(parcel);
        }
        shopCard.mRating = rating;
        shopCard.mShopName = parcel.readString();
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        shopCard.mUserId = etsyId2;
        return shopCard;
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
        source.height = parcel.readInt();
        source.mUrl = parcel.readString();
        source.width = parcel.readInt();
        return source;
    }

    private LocalMarket readcom_etsy_android_lib_models_LocalMarket(Parcel parcel) {
        List list;
        ArrayList arrayList;
        int i;
        Object obj;
        EtsyId etsyId;
        Team team;
        LocalStore localStore;
        ScheduleExpanded scheduleExpanded;
        int i2 = 0;
        List list2 = null;
        LocalMarket localMarket = new LocalMarket();
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mCountryCode(localMarket, parcel.readString());
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mStartDate(localMarket, (Date) parcel.readSerializable());
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mEndDate(localMarket, (Date) parcel.readSerializable());
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mNextOpenText(localMarket, parcel.readString());
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mZip(localMarket, parcel.readString());
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mDescription(localMarket, parcel.readString());
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mType(localMarket, parcel.readString());
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mCity(localMarket, parcel.readString());
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mState(localMarket, parcel.readString());
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mLon(localMarket, parcel.readString());
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_Attendee(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mUpcomingAttendees(localMarket, list);
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mLocalMarketId(localMarket, etsyId);
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mLat(localMarket, parcel.readString());
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mTimezone(localMarket, parcel.readString());
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mDetailsUrl(localMarket, parcel.readString());
        if (parcel.readInt() == -1) {
            team = null;
        } else {
            team = readcom_etsy_android_lib_models_apiv3_Team(parcel);
        }
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mTeam(localMarket, team);
        readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_datatypes_TimeRange(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mScheduleRollups(localMarket, list);
        readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_Attendee(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mOtherAttendees(localMarket, list);
        readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_Attendee(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mFavoriteAttendees(localMarket, list);
        if (parcel.readInt() == -1) {
            localStore = null;
        } else {
            localStore = readcom_etsy_android_lib_models_LocalStore(parcel);
        }
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mStore(localMarket, localStore);
        if (parcel.readInt() == -1) {
            scheduleExpanded = null;
        } else {
            scheduleExpanded = readcom_etsy_android_lib_models_ScheduleExpanded(parcel);
        }
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mScheduleExpanded(localMarket, scheduleExpanded);
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mHappeningStatus(localMarket, parcel.readString());
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mAddress1(localMarket, parcel.readString());
        readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_ListingImage(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mImages(localMarket, list);
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mCountry(localMarket, parcel.readString());
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mAddress2(localMarket, parcel.readString());
        readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_Attendee(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mPastAttendees(localMarket, list);
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mTitle(localMarket, parcel.readString());
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mVenueName(localMarket, parcel.readString());
        i = parcel.readInt();
        if (i >= 0) {
            arrayList = new ArrayList();
            while (i2 < i) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_LocalMarketCard(parcel);
                }
                arrayList.add(obj);
                i2++;
            }
            Object obj2 = arrayList;
        }
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mChildLocalMarkets(localMarket, list2);
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mAndroidMapImageUrl(localMarket, parcel.readString());
        LocalMarket$$PackageHelper.accessLocalMarket$FS$mExternalUrl(localMarket, parcel.readString());
        return localMarket;
    }

    private Attendee readcom_etsy_android_lib_models_Attendee(Parcel parcel) {
        List list;
        EtsyId etsyId;
        LocalMarketAttendeeSchedule localMarketAttendeeSchedule;
        EtsyId etsyId2 = null;
        Attendee attendee = new Attendee();
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
        Attendee$$PackageHelper.accessAttendee$FS$mImages(attendee, list);
        Attendee$$PackageHelper.accessAttendee$FS$mAvatarUrl(attendee, parcel.readString());
        Attendee$$PackageHelper.accessAttendee$FS$mUpcomingStatus(attendee, parcel.readString());
        Attendee$$PackageHelper.accessAttendee$FS$mShopLocation(attendee, parcel.readString());
        Attendee$$PackageHelper.accessAttendee$FS$mComment(attendee, parcel.readString());
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        Attendee$$PackageHelper.accessAttendee$FS$mShopId(attendee, etsyId);
        Attendee$$PackageHelper.accessAttendee$FS$mShopUrl(attendee, parcel.readString());
        if (parcel.readInt() == -1) {
            localMarketAttendeeSchedule = null;
        } else {
            localMarketAttendeeSchedule = readcom_etsy_android_lib_models_LocalMarketAttendeeSchedule(parcel);
        }
        Attendee$$PackageHelper.accessAttendee$FS$mSchedule(attendee, localMarketAttendeeSchedule);
        Attendee$$PackageHelper.accessAttendee$FS$mUpcomingStatusLabel(attendee, parcel.readString());
        Attendee$$PackageHelper.accessAttendee$FS$mShopName(attendee, parcel.readString());
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        Attendee$$PackageHelper.accessAttendee$FS$mUserId(attendee, etsyId2);
        return attendee;
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

    private LocalMarketAttendeeSchedule readcom_etsy_android_lib_models_LocalMarketAttendeeSchedule(Parcel parcel) {
        List list = null;
        LocalMarketAttendeeSchedule localMarketAttendeeSchedule = new LocalMarketAttendeeSchedule();
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_LocalMarketAttendeeSchedule$Day(parcel);
                }
                arrayList.add(obj);
            }
            Object obj2 = arrayList;
        }
        LocalMarketAttendeeSchedule$$PackageHelper.accessLocalMarketAttendeeSchedule$FS$mDays(localMarketAttendeeSchedule, list);
        LocalMarketAttendeeSchedule$$PackageHelper.accessLocalMarketAttendeeSchedule$FS$mScheduleType(localMarketAttendeeSchedule, parcel.readString());
        return localMarketAttendeeSchedule;
    }

    private Day readcom_etsy_android_lib_models_LocalMarketAttendeeSchedule$Day(Parcel parcel) {
        Day day = new Day();
        LocalMarketAttendeeSchedule$Day$$PackageHelper.accessLocalMarketAttendeeSchedule$Day$FS$mDay(day, (WeekDay) parcel.readSerializable());
        LocalMarketAttendeeSchedule$Day$$PackageHelper.accessLocalMarketAttendeeSchedule$Day$FS$mSpecificDate(day, (Date) parcel.readSerializable());
        LocalMarketAttendeeSchedule$Day$$PackageHelper.accessLocalMarketAttendeeSchedule$Day$FS$mTo(day, (Date) parcel.readSerializable());
        LocalMarketAttendeeSchedule$Day$$PackageHelper.accessLocalMarketAttendeeSchedule$Day$FS$mFrom(day, (Date) parcel.readSerializable());
        return day;
    }

    private Team readcom_etsy_android_lib_models_apiv3_Team(Parcel parcel) {
        EtsyId etsyId;
        Team team = new Team();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        team.mId = etsyId;
        team.mShortDescription = parcel.readString();
        team.mAvatarUrl = parcel.readString();
        team.mName = parcel.readString();
        return team;
    }

    private TimeRange readcom_etsy_android_lib_models_datatypes_TimeRange(Parcel parcel) {
        TimeRange timeRange = new TimeRange();
        TimeRange$$PackageHelper.accessTimeRange$FS$mStartDay(timeRange, (WeekDay) parcel.readSerializable());
        TimeRange$$PackageHelper.accessTimeRange$FS$mEndTime(timeRange, new ar().m3263a(parcel));
        TimeRange$$PackageHelper.accessTimeRange$FS$mEndDay(timeRange, (WeekDay) parcel.readSerializable());
        TimeRange$$PackageHelper.accessTimeRange$FS$mStartTime(timeRange, new ar().m3263a(parcel));
        return timeRange;
    }

    private LocalStore readcom_etsy_android_lib_models_LocalStore(Parcel parcel) {
        List list;
        ArrayList arrayList;
        int i;
        EtsyId etsyId;
        UserAddress userAddress;
        List list2 = null;
        LocalStore localStore = new LocalStore();
        LocalStore$$PackageHelper.accessLocalStore$FS$mPhone(localStore, parcel.readString());
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                arrayList.add((LocalStoreImage) parcel.readParcelable(UserProfilePage$$Parcelable.class.getClassLoader()));
            }
            Object obj = arrayList;
        }
        LocalStore$$PackageHelper.accessLocalStore$FS$mImages(localStore, list);
        readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_ShopCard(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        LocalStore$$PackageHelper.accessLocalStore$FS$mStockedShops(localStore, list);
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        LocalStore$$PackageHelper.accessLocalStore$FS$mBuyerId(localStore, etsyId);
        LocalStore$$PackageHelper.accessLocalStore$FS$mAbout(localStore, parcel.readString());
        if (parcel.readInt() == -1) {
            userAddress = null;
        } else {
            userAddress = readcom_etsy_android_lib_models_UserAddress(parcel);
        }
        LocalStore$$PackageHelper.accessLocalStore$FS$mAddress(localStore, userAddress);
        int readInt2 = parcel.readInt();
        if (readInt2 >= 0) {
            list2 = new ArrayList();
            for (int i2 = 0; i2 < readInt2; i2++) {
                list2.add(parcel.readString());
            }
        }
        LocalStore$$PackageHelper.accessLocalStore$FS$mCategories(localStore, list2);
        LocalStore$$PackageHelper.accessLocalStore$FS$mWebsite(localStore, parcel.readString());
        LocalStore$$PackageHelper.accessLocalStore$FS$mBusinessName(localStore, parcel.readString());
        LocalStore$$PackageHelper.accessLocalStore$FS$mEmail(localStore, parcel.readString());
        return localStore;
    }

    private UserAddress readcom_etsy_android_lib_models_UserAddress(Parcel parcel) {
        Country country;
        EtsyId etsyId;
        boolean z = true;
        EtsyId etsyId2 = null;
        UserAddress userAddress = new UserAddress();
        if (parcel.readInt() != 1) {
            z = false;
        }
        UserAddress$$PackageHelper.accessUserAddress$FS$mIsDefaultShipping(userAddress, z);
        UserAddress$$PackageHelper.accessUserAddress$FS$mFirstLine(userAddress, parcel.readString());
        UserAddress$$PackageHelper.accessUserAddress$FS$mCity(userAddress, parcel.readString());
        if (parcel.readInt() == -1) {
            country = null;
        } else {
            country = readcom_etsy_android_lib_models_Country(parcel);
        }
        UserAddress$$PackageHelper.accessUserAddress$FS$mCountry(userAddress, country);
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        UserAddress$$PackageHelper.accessUserAddress$FS$mCountryId(userAddress, etsyId);
        UserAddress$$PackageHelper.accessUserAddress$FS$mState(userAddress, parcel.readString());
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        UserAddress$$PackageHelper.accessUserAddress$FS$mUserAddressId(userAddress, etsyId);
        UserAddress$$PackageHelper.accessUserAddress$FS$mSecondLine(userAddress, parcel.readString());
        UserAddress$$PackageHelper.accessUserAddress$FS$mName(userAddress, parcel.readString());
        UserAddress$$PackageHelper.accessUserAddress$FS$mZip(userAddress, parcel.readString());
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        UserAddress$$PackageHelper.accessUserAddress$FS$mUserId(userAddress, etsyId2);
        return userAddress;
    }

    private Country readcom_etsy_android_lib_models_Country(Parcel parcel) {
        boolean z = true;
        Country country = new Country();
        Country$$PackageHelper.accessCountry$FS$mIsoCountryCode(country, parcel.readString());
        Country$$PackageHelper.accessCountry$FS$mCountryId(country, parcel.readInt());
        if (parcel.readInt() != 1) {
            z = false;
        }
        Country$$PackageHelper.accessCountry$FS$mIsPrimary(country, z);
        Country$$PackageHelper.accessCountry$FS$mLongitude(country, parcel.readFloat());
        Country$$PackageHelper.accessCountry$FS$mName(country, parcel.readString());
        Country$$PackageHelper.accessCountry$FS$mLatitude(country, parcel.readFloat());
        Country$$PackageHelper.accessCountry$FS$mWorldBankCountryCode(country, parcel.readString());
        return country;
    }

    private ScheduleExpanded readcom_etsy_android_lib_models_ScheduleExpanded(Parcel parcel) {
        HashMap hashMap = null;
        ScheduleExpanded scheduleExpanded = new ScheduleExpanded();
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            HashMap hashMap2 = new HashMap();
            for (int i = 0; i < readInt; i++) {
                Object obj;
                WeekDay weekDay = (WeekDay) parcel.readSerializable();
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_datatypes_TimeRange(parcel);
                }
                hashMap2.put(weekDay, obj);
            }
            hashMap = hashMap2;
        }
        ScheduleExpanded$$PackageHelper.accessScheduleExpanded$FS$mDailySchedule(scheduleExpanded, hashMap);
        return scheduleExpanded;
    }

    private LocalMarketCard readcom_etsy_android_lib_models_LocalMarketCard(Parcel parcel) {
        List list;
        int i;
        EtsyId etsyId;
        List list2 = null;
        LocalMarketCard localMarketCard = new LocalMarketCard();
        LocalMarketCard$$PackageHelper.accessLocalMarketCard$FS$mType(localMarketCard, parcel.readString());
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            Object obj;
            ArrayList arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_ListingImage(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        LocalMarketCard$$PackageHelper.accessLocalMarketCard$FS$mListingImages(localMarketCard, list);
        LocalMarketCard$$PackageHelper.accessLocalMarketCard$FS$mDateSubtitle(localMarketCard, parcel.readString());
        LocalMarketCard$$PackageHelper.accessLocalMarketCard$FS$mLon(localMarketCard, parcel.readString());
        LocalMarketCard$$PackageHelper.accessLocalMarketCard$FS$mSellerCount(localMarketCard, parcel.readInt());
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        LocalMarketCard$$PackageHelper.accessLocalMarketCard$FS$mLocalMarketId(localMarketCard, etsyId);
        LocalMarketCard$$PackageHelper.accessLocalMarketCard$FS$mTitle(localMarketCard, parcel.readString());
        LocalMarketCard$$PackageHelper.accessLocalMarketCard$FS$mLocation(localMarketCard, parcel.readString());
        LocalMarketCard$$PackageHelper.accessLocalMarketCard$FS$mLat(localMarketCard, parcel.readString());
        i = parcel.readInt();
        if (i >= 0) {
            list2 = new ArrayList();
            for (int i2 = 0; i2 < i; i2++) {
                list2.add((LocalStoreImage) parcel.readParcelable(UserProfilePage$$Parcelable.class.getClassLoader()));
            }
        }
        LocalMarketCard$$PackageHelper.accessLocalMarketCard$FS$mStoreImages(localMarketCard, list2);
        return localMarketCard;
    }

    private Rating readcom_etsy_android_lib_models_apiv3_Rating(Parcel parcel) {
        Rating rating = new Rating();
        rating.mStars = parcel.readDouble();
        rating.mSellerFeedbackCount = parcel.readInt();
        rating.mCount = parcel.readInt();
        rating.mRating = parcel.readDouble();
        return rating;
    }

    private void writecom_etsy_android_lib_models_apiv3_UserProfilePage(UserProfilePage userProfilePage, Parcel parcel, int i) {
        if (userProfilePage.mFavoriteTreasuries == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(userProfilePage.mFavoriteTreasuries.size());
            for (TreasuryV3 treasuryV3 : userProfilePage.mFavoriteTreasuries) {
                if (treasuryV3 == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_TreasuryV3(treasuryV3, parcel, i);
                }
            }
        }
        if (userProfilePage.mAppreciationPhotos == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(userProfilePage.mAppreciationPhotos.size());
            for (AppreciationPhoto appreciationPhoto : userProfilePage.mAppreciationPhotos) {
                if (appreciationPhoto == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_AppreciationPhoto(appreciationPhoto, parcel, i);
                }
            }
        }
        if (userProfilePage.mUserProfile == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_UserProfileV3(userProfilePage.mUserProfile, parcel, i);
        }
        if (userProfilePage.mCollections == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(userProfilePage.mCollections.size());
            for (Collection writeSerializable : userProfilePage.mCollections) {
                parcel.writeSerializable(writeSerializable);
            }
        }
        if (userProfilePage.mFavoriteShops == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(userProfilePage.mFavoriteShops.size());
            for (ShopCard shopCard : userProfilePage.mFavoriteShops) {
                if (shopCard == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_ShopCard(shopCard, parcel, i);
                }
            }
        }
        if (userProfilePage.mShopCard == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_ShopCard(userProfilePage.mShopCard, parcel, i);
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

    private void writecom_etsy_android_lib_models_apiv3_AppreciationPhoto(AppreciationPhoto appreciationPhoto, Parcel parcel, int i) {
        parcel.writeSerializable(appreciationPhoto.mShortenedShareUrl);
        new au().m3270a(appreciationPhoto.mCreateDate, parcel);
        parcel.writeString(appreciationPhoto.mStatus);
        if (appreciationPhoto.mTransactionId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(appreciationPhoto.mTransactionId, parcel, i);
        }
        parcel.writeInt(appreciationPhoto.mIsSellerApproved ? 1 : 0);
        parcel.writeString(appreciationPhoto.mListingTitle);
        if (appreciationPhoto.mImageId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(appreciationPhoto.mImageId, parcel, i);
        }
        parcel.writeString(appreciationPhoto.mSellerAvatarUrl);
        parcel.writeString(appreciationPhoto.mShopName);
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl570xN(appreciationPhoto));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl224xN(appreciationPhoto));
        parcel.writeDouble(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$PORTRAIT_HEIGHT_RATIO(appreciationPhoto));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl680x540(appreciationPhoto));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl75x75(appreciationPhoto));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl170x135(appreciationPhoto));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrlFullxFull(appreciationPhoto));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl300x300(appreciationPhoto));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl340x270(appreciationPhoto));
    }

    private void writecom_etsy_android_lib_models_apiv3_UserProfileV3(UserProfileV3 userProfileV3, Parcel parcel, int i) {
        int i2;
        int i3 = 0;
        parcel.writeInt(userProfileV3.mAppreciationPhotoCount);
        parcel.writeInt(userProfileV3.mFavoriteListingsCount);
        parcel.writeString(userProfileV3.mLoginName);
        parcel.writeString(userProfileV3.mLocation);
        parcel.writeInt(userProfileV3.mFollowingCount);
        parcel.writeInt(userProfileV3.mAreFavoriteShopsPublic ? 1 : 0);
        parcel.writeInt(userProfileV3.mFavoriteTreasuriesCount);
        parcel.writeString(userProfileV3.mBio);
        if (userProfileV3.mAreFavoriteListingsPublic) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeString(userProfileV3.mAvatarUrl);
        parcel.writeString(userProfileV3.mFirstName);
        parcel.writeString(userProfileV3.mDisplayName);
        parcel.writeInt(userProfileV3.mFavoriteShopsCount);
        if (userProfileV3.mIsFollowing) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeInt(userProfileV3.mTransactionsSoldCount);
        parcel.writeInt(userProfileV3.mFollowerCount);
        if (userProfileV3.mIsAdmin) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (userProfileV3.mIsSeller) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (userProfileV3.mAreFavoriteTreasuriesPublic) {
            i3 = 1;
        }
        parcel.writeInt(i3);
        if (userProfileV3.mUserId == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(userProfileV3.mUserId, parcel, i);
    }

    private void writecom_etsy_android_lib_models_apiv3_ShopCard(ShopCard shopCard, Parcel parcel, int i) {
        int i2;
        int i3 = 0;
        if (shopCard.mIcon == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Image(shopCard.mIcon, parcel, i);
        }
        parcel.writeString(shopCard.mLoginName);
        parcel.writeString(shopCard.mLocation);
        if (shopCard.mIsVacation) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (shopCard.mHasIcon) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeString(shopCard.mHeadline);
        if (shopCard.mIsFavorite) {
            i3 = 1;
        }
        parcel.writeInt(i3);
        parcel.writeString(shopCard.mSellerAvatar);
        if (shopCard.mShopId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(shopCard.mShopId, parcel, i);
        }
        if (shopCard.mLocalEvent == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_LocalMarket(shopCard.mLocalEvent, parcel, i);
        }
        parcel.writeString(shopCard.mShopUrl);
        parcel.writeInt(shopCard.mActiveListingCount);
        if (shopCard.mDisplayListings == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(shopCard.mDisplayListings.size());
            for (ListingCard listingCard : shopCard.mDisplayListings) {
                if (listingCard == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_ListingCard(listingCard, parcel, i);
                }
            }
        }
        new au().m3270a(shopCard.mOpenDate, parcel);
        if (shopCard.mRating == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Rating(shopCard.mRating, parcel, i);
        }
        parcel.writeString(shopCard.mShopName);
        if (shopCard.mUserId == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(shopCard.mUserId, parcel, i);
    }

    private void writecom_etsy_android_lib_models_apiv3_Image(Image image, Parcel parcel, int i) {
        parcel.writeString(image.mKey);
        parcel.writeString(image.mUrl);
        if (image.mSources == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(image.mSources.size());
        for (Source source : image.mSources) {
            if (source == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                writecom_etsy_android_lib_models_apiv3_Image$Source(source, parcel, i);
            }
        }
    }

    private void writecom_etsy_android_lib_models_apiv3_Image$Source(Source source, Parcel parcel, int i) {
        parcel.writeInt(source.height);
        parcel.writeString(source.mUrl);
        parcel.writeInt(source.width);
    }

    private void writecom_etsy_android_lib_models_LocalMarket(LocalMarket localMarket, Parcel parcel, int i) {
        parcel.writeString(LocalMarket$$PackageHelper.accessLocalMarket$FG$mCountryCode(localMarket));
        parcel.writeSerializable(LocalMarket$$PackageHelper.accessLocalMarket$FG$mStartDate(localMarket));
        parcel.writeSerializable(LocalMarket$$PackageHelper.accessLocalMarket$FG$mEndDate(localMarket));
        parcel.writeString(LocalMarket$$PackageHelper.accessLocalMarket$FG$mNextOpenText(localMarket));
        parcel.writeString(LocalMarket$$PackageHelper.accessLocalMarket$FG$mZip(localMarket));
        parcel.writeString(LocalMarket$$PackageHelper.accessLocalMarket$FG$mDescription(localMarket));
        parcel.writeString(LocalMarket$$PackageHelper.accessLocalMarket$FG$mType(localMarket));
        parcel.writeString(LocalMarket$$PackageHelper.accessLocalMarket$FG$mCity(localMarket));
        parcel.writeString(LocalMarket$$PackageHelper.accessLocalMarket$FG$mState(localMarket));
        parcel.writeString(LocalMarket$$PackageHelper.accessLocalMarket$FG$mLon(localMarket));
        if (LocalMarket$$PackageHelper.accessLocalMarket$FG$mUpcomingAttendees(localMarket) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(LocalMarket$$PackageHelper.accessLocalMarket$FG$mUpcomingAttendees(localMarket).size());
            for (Attendee attendee : LocalMarket$$PackageHelper.accessLocalMarket$FG$mUpcomingAttendees(localMarket)) {
                if (attendee == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_Attendee(attendee, parcel, i);
                }
            }
        }
        if (LocalMarket$$PackageHelper.accessLocalMarket$FG$mLocalMarketId(localMarket) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(LocalMarket$$PackageHelper.accessLocalMarket$FG$mLocalMarketId(localMarket), parcel, i);
        }
        parcel.writeString(LocalMarket$$PackageHelper.accessLocalMarket$FG$mLat(localMarket));
        parcel.writeString(LocalMarket$$PackageHelper.accessLocalMarket$FG$mTimezone(localMarket));
        parcel.writeString(LocalMarket$$PackageHelper.accessLocalMarket$FG$mDetailsUrl(localMarket));
        if (LocalMarket$$PackageHelper.accessLocalMarket$FG$mTeam(localMarket) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Team(LocalMarket$$PackageHelper.accessLocalMarket$FG$mTeam(localMarket), parcel, i);
        }
        if (LocalMarket$$PackageHelper.accessLocalMarket$FG$mScheduleRollups(localMarket) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(LocalMarket$$PackageHelper.accessLocalMarket$FG$mScheduleRollups(localMarket).size());
            for (TimeRange timeRange : LocalMarket$$PackageHelper.accessLocalMarket$FG$mScheduleRollups(localMarket)) {
                if (timeRange == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_datatypes_TimeRange(timeRange, parcel, i);
                }
            }
        }
        if (LocalMarket$$PackageHelper.accessLocalMarket$FG$mOtherAttendees(localMarket) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(LocalMarket$$PackageHelper.accessLocalMarket$FG$mOtherAttendees(localMarket).size());
            for (Attendee attendee2 : LocalMarket$$PackageHelper.accessLocalMarket$FG$mOtherAttendees(localMarket)) {
                if (attendee2 == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_Attendee(attendee2, parcel, i);
                }
            }
        }
        if (LocalMarket$$PackageHelper.accessLocalMarket$FG$mFavoriteAttendees(localMarket) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(LocalMarket$$PackageHelper.accessLocalMarket$FG$mFavoriteAttendees(localMarket).size());
            for (Attendee attendee22 : LocalMarket$$PackageHelper.accessLocalMarket$FG$mFavoriteAttendees(localMarket)) {
                if (attendee22 == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_Attendee(attendee22, parcel, i);
                }
            }
        }
        if (LocalMarket$$PackageHelper.accessLocalMarket$FG$mStore(localMarket) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_LocalStore(LocalMarket$$PackageHelper.accessLocalMarket$FG$mStore(localMarket), parcel, i);
        }
        if (LocalMarket$$PackageHelper.accessLocalMarket$FG$mScheduleExpanded(localMarket) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_ScheduleExpanded(LocalMarket$$PackageHelper.accessLocalMarket$FG$mScheduleExpanded(localMarket), parcel, i);
        }
        parcel.writeString(LocalMarket$$PackageHelper.accessLocalMarket$FG$mHappeningStatus(localMarket));
        parcel.writeString(LocalMarket$$PackageHelper.accessLocalMarket$FG$mAddress1(localMarket));
        if (LocalMarket$$PackageHelper.accessLocalMarket$FG$mImages(localMarket) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(LocalMarket$$PackageHelper.accessLocalMarket$FG$mImages(localMarket).size());
            for (ListingImage listingImage : LocalMarket$$PackageHelper.accessLocalMarket$FG$mImages(localMarket)) {
                if (listingImage == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_ListingImage(listingImage, parcel, i);
                }
            }
        }
        parcel.writeString(LocalMarket$$PackageHelper.accessLocalMarket$FG$mCountry(localMarket));
        parcel.writeString(LocalMarket$$PackageHelper.accessLocalMarket$FG$mAddress2(localMarket));
        if (LocalMarket$$PackageHelper.accessLocalMarket$FG$mPastAttendees(localMarket) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(LocalMarket$$PackageHelper.accessLocalMarket$FG$mPastAttendees(localMarket).size());
            for (Attendee attendee222 : LocalMarket$$PackageHelper.accessLocalMarket$FG$mPastAttendees(localMarket)) {
                if (attendee222 == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_Attendee(attendee222, parcel, i);
                }
            }
        }
        parcel.writeString(LocalMarket$$PackageHelper.accessLocalMarket$FG$mTitle(localMarket));
        parcel.writeString(LocalMarket$$PackageHelper.accessLocalMarket$FG$mVenueName(localMarket));
        if (LocalMarket$$PackageHelper.accessLocalMarket$FG$mChildLocalMarkets(localMarket) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(LocalMarket$$PackageHelper.accessLocalMarket$FG$mChildLocalMarkets(localMarket).size());
            for (LocalMarketCard localMarketCard : LocalMarket$$PackageHelper.accessLocalMarket$FG$mChildLocalMarkets(localMarket)) {
                if (localMarketCard == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_LocalMarketCard(localMarketCard, parcel, i);
                }
            }
        }
        parcel.writeString(LocalMarket$$PackageHelper.accessLocalMarket$FG$mAndroidMapImageUrl(localMarket));
        parcel.writeString(LocalMarket$$PackageHelper.accessLocalMarket$FG$mExternalUrl(localMarket));
    }

    private void writecom_etsy_android_lib_models_Attendee(Attendee attendee, Parcel parcel, int i) {
        if (Attendee$$PackageHelper.accessAttendee$FG$mImages(attendee) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(Attendee$$PackageHelper.accessAttendee$FG$mImages(attendee).size());
            for (ListingImage listingImage : Attendee$$PackageHelper.accessAttendee$FG$mImages(attendee)) {
                if (listingImage == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_ListingImage(listingImage, parcel, i);
                }
            }
        }
        parcel.writeString(Attendee$$PackageHelper.accessAttendee$FG$mAvatarUrl(attendee));
        parcel.writeString(Attendee$$PackageHelper.accessAttendee$FG$mUpcomingStatus(attendee));
        parcel.writeString(Attendee$$PackageHelper.accessAttendee$FG$mShopLocation(attendee));
        parcel.writeString(Attendee$$PackageHelper.accessAttendee$FG$mComment(attendee));
        if (Attendee$$PackageHelper.accessAttendee$FG$mShopId(attendee) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(Attendee$$PackageHelper.accessAttendee$FG$mShopId(attendee), parcel, i);
        }
        parcel.writeString(Attendee$$PackageHelper.accessAttendee$FG$mShopUrl(attendee));
        if (Attendee$$PackageHelper.accessAttendee$FG$mSchedule(attendee) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_LocalMarketAttendeeSchedule(Attendee$$PackageHelper.accessAttendee$FG$mSchedule(attendee), parcel, i);
        }
        parcel.writeString(Attendee$$PackageHelper.accessAttendee$FG$mUpcomingStatusLabel(attendee));
        parcel.writeString(Attendee$$PackageHelper.accessAttendee$FG$mShopName(attendee));
        if (Attendee$$PackageHelper.accessAttendee$FG$mUserId(attendee) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(Attendee$$PackageHelper.accessAttendee$FG$mUserId(attendee), parcel, i);
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

    private void writecom_etsy_android_lib_models_LocalMarketAttendeeSchedule(LocalMarketAttendeeSchedule localMarketAttendeeSchedule, Parcel parcel, int i) {
        if (LocalMarketAttendeeSchedule$$PackageHelper.accessLocalMarketAttendeeSchedule$FG$mDays(localMarketAttendeeSchedule) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(LocalMarketAttendeeSchedule$$PackageHelper.accessLocalMarketAttendeeSchedule$FG$mDays(localMarketAttendeeSchedule).size());
            for (Day day : LocalMarketAttendeeSchedule$$PackageHelper.accessLocalMarketAttendeeSchedule$FG$mDays(localMarketAttendeeSchedule)) {
                if (day == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_LocalMarketAttendeeSchedule$Day(day, parcel, i);
                }
            }
        }
        parcel.writeString(LocalMarketAttendeeSchedule$$PackageHelper.accessLocalMarketAttendeeSchedule$FG$mScheduleType(localMarketAttendeeSchedule));
    }

    private void writecom_etsy_android_lib_models_LocalMarketAttendeeSchedule$Day(Day day, Parcel parcel, int i) {
        parcel.writeSerializable(LocalMarketAttendeeSchedule$Day$$PackageHelper.accessLocalMarketAttendeeSchedule$Day$FG$mDay(day));
        parcel.writeSerializable(LocalMarketAttendeeSchedule$Day$$PackageHelper.accessLocalMarketAttendeeSchedule$Day$FG$mSpecificDate(day));
        parcel.writeSerializable(LocalMarketAttendeeSchedule$Day$$PackageHelper.accessLocalMarketAttendeeSchedule$Day$FG$mTo(day));
        parcel.writeSerializable(LocalMarketAttendeeSchedule$Day$$PackageHelper.accessLocalMarketAttendeeSchedule$Day$FG$mFrom(day));
    }

    private void writecom_etsy_android_lib_models_apiv3_Team(Team team, Parcel parcel, int i) {
        if (team.mId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(team.mId, parcel, i);
        }
        parcel.writeString(team.mShortDescription);
        parcel.writeString(team.mAvatarUrl);
        parcel.writeString(team.mName);
    }

    private void writecom_etsy_android_lib_models_datatypes_TimeRange(TimeRange timeRange, Parcel parcel, int i) {
        parcel.writeSerializable(TimeRange$$PackageHelper.accessTimeRange$FG$mStartDay(timeRange));
        new ar().m3264a(TimeRange$$PackageHelper.accessTimeRange$FG$mEndTime(timeRange), parcel);
        parcel.writeSerializable(TimeRange$$PackageHelper.accessTimeRange$FG$mEndDay(timeRange));
        new ar().m3264a(TimeRange$$PackageHelper.accessTimeRange$FG$mStartTime(timeRange), parcel);
    }

    private void writecom_etsy_android_lib_models_LocalStore(LocalStore localStore, Parcel parcel, int i) {
        parcel.writeString(LocalStore$$PackageHelper.accessLocalStore$FG$mPhone(localStore));
        if (LocalStore$$PackageHelper.accessLocalStore$FG$mImages(localStore) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(LocalStore$$PackageHelper.accessLocalStore$FG$mImages(localStore).size());
            for (LocalStoreImage writeParcelable : LocalStore$$PackageHelper.accessLocalStore$FG$mImages(localStore)) {
                parcel.writeParcelable(writeParcelable, i);
            }
        }
        if (LocalStore$$PackageHelper.accessLocalStore$FG$mStockedShops(localStore) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(LocalStore$$PackageHelper.accessLocalStore$FG$mStockedShops(localStore).size());
            for (ShopCard shopCard : LocalStore$$PackageHelper.accessLocalStore$FG$mStockedShops(localStore)) {
                if (shopCard == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_ShopCard(shopCard, parcel, i);
                }
            }
        }
        if (LocalStore$$PackageHelper.accessLocalStore$FG$mBuyerId(localStore) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(LocalStore$$PackageHelper.accessLocalStore$FG$mBuyerId(localStore), parcel, i);
        }
        parcel.writeString(LocalStore$$PackageHelper.accessLocalStore$FG$mAbout(localStore));
        if (LocalStore$$PackageHelper.accessLocalStore$FG$mAddress(localStore) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_UserAddress(LocalStore$$PackageHelper.accessLocalStore$FG$mAddress(localStore), parcel, i);
        }
        if (LocalStore$$PackageHelper.accessLocalStore$FG$mCategories(localStore) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(LocalStore$$PackageHelper.accessLocalStore$FG$mCategories(localStore).size());
            for (String writeString : LocalStore$$PackageHelper.accessLocalStore$FG$mCategories(localStore)) {
                parcel.writeString(writeString);
            }
        }
        parcel.writeString(LocalStore$$PackageHelper.accessLocalStore$FG$mWebsite(localStore));
        parcel.writeString(LocalStore$$PackageHelper.accessLocalStore$FG$mBusinessName(localStore));
        parcel.writeString(LocalStore$$PackageHelper.accessLocalStore$FG$mEmail(localStore));
    }

    private void writecom_etsy_android_lib_models_UserAddress(UserAddress userAddress, Parcel parcel, int i) {
        parcel.writeInt(UserAddress$$PackageHelper.accessUserAddress$FG$mIsDefaultShipping(userAddress) ? 1 : 0);
        parcel.writeString(UserAddress$$PackageHelper.accessUserAddress$FG$mFirstLine(userAddress));
        parcel.writeString(UserAddress$$PackageHelper.accessUserAddress$FG$mCity(userAddress));
        if (UserAddress$$PackageHelper.accessUserAddress$FG$mCountry(userAddress) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_Country(UserAddress$$PackageHelper.accessUserAddress$FG$mCountry(userAddress), parcel, i);
        }
        if (UserAddress$$PackageHelper.accessUserAddress$FG$mCountryId(userAddress) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(UserAddress$$PackageHelper.accessUserAddress$FG$mCountryId(userAddress), parcel, i);
        }
        parcel.writeString(UserAddress$$PackageHelper.accessUserAddress$FG$mState(userAddress));
        if (UserAddress$$PackageHelper.accessUserAddress$FG$mUserAddressId(userAddress) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(UserAddress$$PackageHelper.accessUserAddress$FG$mUserAddressId(userAddress), parcel, i);
        }
        parcel.writeString(UserAddress$$PackageHelper.accessUserAddress$FG$mSecondLine(userAddress));
        parcel.writeString(UserAddress$$PackageHelper.accessUserAddress$FG$mName(userAddress));
        parcel.writeString(UserAddress$$PackageHelper.accessUserAddress$FG$mZip(userAddress));
        if (UserAddress$$PackageHelper.accessUserAddress$FG$mUserId(userAddress) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(UserAddress$$PackageHelper.accessUserAddress$FG$mUserId(userAddress), parcel, i);
    }

    private void writecom_etsy_android_lib_models_Country(Country country, Parcel parcel, int i) {
        parcel.writeString(Country$$PackageHelper.accessCountry$FG$mIsoCountryCode(country));
        parcel.writeInt(Country$$PackageHelper.accessCountry$FG$mCountryId(country));
        parcel.writeInt(Country$$PackageHelper.accessCountry$FG$mIsPrimary(country) ? 1 : 0);
        parcel.writeFloat(Country$$PackageHelper.accessCountry$FG$mLongitude(country));
        parcel.writeString(Country$$PackageHelper.accessCountry$FG$mName(country));
        parcel.writeFloat(Country$$PackageHelper.accessCountry$FG$mLatitude(country));
        parcel.writeString(Country$$PackageHelper.accessCountry$FG$mWorldBankCountryCode(country));
    }

    private void writecom_etsy_android_lib_models_ScheduleExpanded(ScheduleExpanded scheduleExpanded, Parcel parcel, int i) {
        if (ScheduleExpanded$$PackageHelper.accessScheduleExpanded$FG$mDailySchedule(scheduleExpanded) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(ScheduleExpanded$$PackageHelper.accessScheduleExpanded$FG$mDailySchedule(scheduleExpanded).size());
        for (Entry entry : ScheduleExpanded$$PackageHelper.accessScheduleExpanded$FG$mDailySchedule(scheduleExpanded).entrySet()) {
            parcel.writeSerializable((Serializable) entry.getKey());
            if (entry.getValue() == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                writecom_etsy_android_lib_models_datatypes_TimeRange((TimeRange) entry.getValue(), parcel, i);
            }
        }
    }

    private void writecom_etsy_android_lib_models_LocalMarketCard(LocalMarketCard localMarketCard, Parcel parcel, int i) {
        parcel.writeString(LocalMarketCard$$PackageHelper.accessLocalMarketCard$FG$mType(localMarketCard));
        if (LocalMarketCard$$PackageHelper.accessLocalMarketCard$FG$mListingImages(localMarketCard) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(LocalMarketCard$$PackageHelper.accessLocalMarketCard$FG$mListingImages(localMarketCard).size());
            for (ListingImage listingImage : LocalMarketCard$$PackageHelper.accessLocalMarketCard$FG$mListingImages(localMarketCard)) {
                if (listingImage == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_ListingImage(listingImage, parcel, i);
                }
            }
        }
        parcel.writeString(LocalMarketCard$$PackageHelper.accessLocalMarketCard$FG$mDateSubtitle(localMarketCard));
        parcel.writeString(LocalMarketCard$$PackageHelper.accessLocalMarketCard$FG$mLon(localMarketCard));
        parcel.writeInt(LocalMarketCard$$PackageHelper.accessLocalMarketCard$FG$mSellerCount(localMarketCard));
        if (LocalMarketCard$$PackageHelper.accessLocalMarketCard$FG$mLocalMarketId(localMarketCard) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(LocalMarketCard$$PackageHelper.accessLocalMarketCard$FG$mLocalMarketId(localMarketCard), parcel, i);
        }
        parcel.writeString(LocalMarketCard$$PackageHelper.accessLocalMarketCard$FG$mTitle(localMarketCard));
        parcel.writeString(LocalMarketCard$$PackageHelper.accessLocalMarketCard$FG$mLocation(localMarketCard));
        parcel.writeString(LocalMarketCard$$PackageHelper.accessLocalMarketCard$FG$mLat(localMarketCard));
        if (LocalMarketCard$$PackageHelper.accessLocalMarketCard$FG$mStoreImages(localMarketCard) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(LocalMarketCard$$PackageHelper.accessLocalMarketCard$FG$mStoreImages(localMarketCard).size());
        for (LocalStoreImage writeParcelable : LocalMarketCard$$PackageHelper.accessLocalMarketCard$FG$mStoreImages(localMarketCard)) {
            parcel.writeParcelable(writeParcelable, i);
        }
    }

    private void writecom_etsy_android_lib_models_apiv3_Rating(Rating rating, Parcel parcel, int i) {
        parcel.writeDouble(rating.mStars);
        parcel.writeInt(rating.mSellerFeedbackCount);
        parcel.writeInt(rating.mCount);
        parcel.writeDouble(rating.mRating);
    }

    public int describeContents() {
        return 0;
    }

    public UserProfilePage getParcel() {
        return this.userProfilePage$$0;
    }
}
