package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.core.EtsyMoney$$PackageHelper;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.BaseModelImage$$PackageHelper;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.Country$$PackageHelper;
import com.etsy.android.lib.models.FavoriteListing;
import com.etsy.android.lib.models.FundOnEtsyCampaign;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.Listing$$PackageHelper;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.ListingImage$$PackageHelper;
import com.etsy.android.lib.models.ListingOption;
import com.etsy.android.lib.models.ListingUpdate;
import com.etsy.android.lib.models.ListingVideos;
import com.etsy.android.lib.models.Manufacturer;
import com.etsy.android.lib.models.Manufacturer$$PackageHelper;
import com.etsy.android.lib.models.Option;
import com.etsy.android.lib.models.Option$$PackageHelper;
import com.etsy.android.lib.models.PaymentTemplate;
import com.etsy.android.lib.models.SearchAdsMetadata;
import com.etsy.android.lib.models.ShippingInfo;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.User$$PackageHelper;
import com.etsy.android.lib.models.User.PublicKey;
import com.etsy.android.lib.models.UserAddress;
import com.etsy.android.lib.models.UserAddress$$PackageHelper;
import com.etsy.android.lib.models.UserProfile;
import com.etsy.android.lib.models.Variation;
import com.etsy.android.lib.models.Variation$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image.Source;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import com.etsy.android.lib.util.au;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import org.parceler.ax;

public class ShopV3$$Parcelable implements android.os.Parcelable, ax<ShopV3> {
    public static final bd CREATOR;
    private ShopV3 shopV3$$0;

    static {
        CREATOR = new bd();
    }

    public ShopV3$$Parcelable(Parcel parcel) {
        ShopV3 shopV3;
        if (parcel.readInt() == -1) {
            shopV3 = null;
        } else {
            shopV3 = readcom_etsy_android_lib_models_apiv3_ShopV3(parcel);
        }
        this.shopV3$$0 = shopV3;
    }

    public ShopV3$$Parcelable(ShopV3 shopV3) {
        this.shopV3$$0 = shopV3;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopV3$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_ShopV3(this.shopV3$$0, parcel, i);
    }

    private ShopV3 readcom_etsy_android_lib_models_apiv3_ShopV3(Parcel parcel) {
        boolean z;
        EtsyId etsyId;
        ShopIconV3 shopIconV3;
        User user;
        Image image;
        List list;
        EtsyId etsyId2 = null;
        boolean z2 = true;
        ShopV3 shopV3 = new ShopV3();
        shopV3.mCountryCode = parcel.readString();
        shopV3.mGoogleAnalyticsPropertyId = parcel.readString();
        shopV3.mHasOwners = parcel.readInt() == 1;
        shopV3.mLocation = parcel.readString();
        shopV3.mBrandingOption = parcel.readInt();
        shopV3.mTotalRatingCount = parcel.readInt();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopV3.mHasAboutPage = z;
        shopV3.mOnboardingStatus = parcel.readString();
        shopV3.mFavoritesCount = parcel.readInt();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopV3.mHasIcon = z;
        shopV3.mCity = parcel.readString();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopV3.mIsUsingStructuredPolicies = z;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopV3.mHasCurrencyCode = z;
        shopV3.mVacationMessage = parcel.readString();
        shopV3.mSellerName = parcel.readString();
        shopV3.mCurrencyCode = parcel.readString();
        shopV3.mStory = parcel.readString();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopV3.mAcceptsChecks = z;
        shopV3.mLatitude = parcel.readDouble();
        shopV3.mCreateDate = new au().m3269a(parcel);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopV3.mAcceptsBankTransfers = z;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopV3.mAcceptsDirectCheckout = z;
        shopV3.mLongitude = parcel.readDouble();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopV3.mIsVacation = z;
        shopV3.mUpdateDate = new au().m3269a(parcel);
        shopV3.mHeadline = parcel.readString();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopV3.mAcceptsMoneyOrders = z;
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        shopV3.mShopId = etsyId;
        shopV3.mStoryHeadline = parcel.readString();
        shopV3.mShopUrl = parcel.readString();
        shopV3.mName = parcel.readString();
        shopV3.mBannerUrl = parcel.readString();
        shopV3.mAverageRating = parcel.readDouble();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopV3.mHasLanguagePreferences = z;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopV3.mIsOpen = z;
        shopV3.mVacationAutoReply = parcel.readString();
        if (parcel.readInt() == -1) {
            shopIconV3 = null;
        } else {
            shopIconV3 = readcom_etsy_android_lib_models_apiv3_ShopIconV3(parcel);
        }
        shopV3.mShopIcon = shopIconV3;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopV3.mAcceptsOther = z;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopV3.mAboutInfoExists = z;
        shopV3.mDigitalListingCount = parcel.readInt();
        if (parcel.readInt() == -1) {
            user = null;
        } else {
            user = readcom_etsy_android_lib_models_User(parcel);
        }
        shopV3.mOwner = user;
        shopV3.mMessageToBuyers = parcel.readString();
        shopV3.mMessageUpdateDate = (Date) parcel.readSerializable();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopV3.mHasBanner = z;
        if (parcel.readInt() == -1) {
            image = null;
        } else {
            image = readcom_etsy_android_lib_models_apiv3_Image(parcel);
        }
        shopV3.mLargeBanner = image;
        shopV3.mOpenDate = new au().m3269a(parcel);
        shopV3.mShopName = parcel.readString();
        if (parcel.readInt() == -1) {
            image = null;
        } else {
            image = readcom_etsy_android_lib_models_apiv3_Image(parcel);
        }
        shopV3.mBanner = image;
        shopV3.mStatus = parcel.readString();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopV3.mHasLargeBanner = z;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopV3.mListingRearrangeEnabled = z;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopV3.mAcceptsPayPal = z;
        shopV3.mPullQuote = parcel.readString();
        shopV3.mRegion = parcel.readString();
        shopV3.mStoryLeadingParagraph = parcel.readString();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopV3.mAcceptsGiftCards = z;
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            list = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                list.add(parcel.readString());
            }
        }
        shopV3.mModules = list;
        shopV3.mSoldCount = parcel.readInt();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        shopV3.mHasAbout = z;
        shopV3.mMessage = parcel.readString();
        shopV3.mUrl = parcel.readString();
        int readInt2 = parcel.readInt();
        if (readInt2 < 0) {
            list = null;
        } else {
            Object obj;
            ArrayList arrayList = new ArrayList();
            for (readInt = 0; readInt < readInt2; readInt++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_Language(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        shopV3.mShopLanguages = list;
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        shopV3.mHasPrivateReceiptInfo = z2;
        shopV3.mActiveListingCount = parcel.readInt();
        shopV3.mSellerAvatarUrl = parcel.readString();
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        shopV3.mUserId = etsyId2;
        return shopV3;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private ShopIconV3 readcom_etsy_android_lib_models_apiv3_ShopIconV3(Parcel parcel) {
        EtsyId etsyId;
        List list = null;
        ShopIconV3 shopIconV3 = new ShopIconV3();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        shopIconV3.mImageId = etsyId;
        shopIconV3.mKey = parcel.readString();
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
        shopIconV3.mSources = list;
        shopIconV3.mUrl = parcel.readString();
        return shopIconV3;
    }

    private Source readcom_etsy_android_lib_models_apiv3_Image$Source(Parcel parcel) {
        Source source = new Source();
        source.height = parcel.readInt();
        source.mUrl = parcel.readString();
        source.width = parcel.readInt();
        return source;
    }

    private User readcom_etsy_android_lib_models_User(Parcel parcel) {
        List list;
        ArrayList arrayList;
        int i;
        Image image;
        int i2 = 0;
        EtsyId etsyId = null;
        User user = new User();
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                arrayList.add((Shop) parcel.readSerializable());
            }
            Object obj = arrayList;
        }
        User$$PackageHelper.accessUser$FS$mShops(user, list);
        User$$PackageHelper.accessUser$FS$mPublicKey(user, (PublicKey) parcel.readSerializable());
        User$$PackageHelper.accessUser$FS$mProfile(user, (UserProfile) parcel.readSerializable());
        User$$PackageHelper.accessUser$FS$mLoginName(user, parcel.readString());
        readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_FavoriteListing(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        User$$PackageHelper.accessUser$FS$mFavoriteListings(user, list);
        User$$PackageHelper.accessUser$FS$mAwaitingFeedbackCount(user, parcel.readInt());
        if (parcel.readInt() == -1) {
            image = null;
        } else {
            image = readcom_etsy_android_lib_models_apiv3_Image(parcel);
        }
        User$$PackageHelper.accessUser$FS$mAvatar(user, image);
        i = parcel.readInt();
        if (i < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            while (i2 < i) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_UserAddress(parcel);
                }
                arrayList.add(obj);
                i2++;
            }
            obj = arrayList;
        }
        User$$PackageHelper.accessUser$FS$mUserAddresses(user, list);
        User$$PackageHelper.accessUser$FS$mFollowerCount(user, parcel.readInt());
        User$$PackageHelper.accessUser$FS$mFollowingCount(user, parcel.readInt());
        User$$PackageHelper.accessUser$FS$mEmail(user, parcel.readString());
        if (parcel.readInt() != -1) {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        User$$PackageHelper.accessUser$FS$mUserId(user, etsyId);
        return user;
    }

    private FavoriteListing readcom_etsy_android_lib_models_FavoriteListing(Parcel parcel) {
        EtsyMoney etsyMoney;
        EtsyId etsyId;
        List list;
        ArrayList arrayList;
        int i;
        Object obj;
        boolean z;
        OfferingResponse offeringResponse;
        Boolean bool;
        int i2;
        boolean z2 = true;
        int i3 = 0;
        EtsyId etsyId2 = null;
        Listing favoriteListing = new FavoriteListing();
        if (parcel.readInt() == -1) {
            etsyMoney = null;
        } else {
            etsyMoney = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        Listing$$PackageHelper.accessListing$FS$mBuyerDisplayPrice(favoriteListing, etsyMoney);
        Listing$$PackageHelper.accessListing$FS$mShop(favoriteListing, (Shop) parcel.readSerializable());
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        Listing$$PackageHelper.accessListing$FS$mListingId(favoriteListing, etsyId);
        Listing$$PackageHelper.accessListing$FS$mPaymentInfo(favoriteListing, (PaymentTemplate) parcel.readSerializable());
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_Manufacturer(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        Listing$$PackageHelper.accessListing$FS$mManufacturers(favoriteListing, list);
        Listing$$PackageHelper.accessListing$FS$mCreationDate(favoriteListing, (Date) parcel.readSerializable());
        Listing$$PackageHelper.accessListing$FS$mListingVideos(favoriteListing, (ListingVideos) parcel.readSerializable());
        Listing$$PackageHelper.accessListing$FS$mConvertedPrice(favoriteListing, parcel.readString());
        Listing$$PackageHelper.accessListing$FS$mViews(favoriteListing, parcel.readInt());
        Listing$$PackageHelper.accessListing$FS$mDescription(favoriteListing, parcel.readString());
        Listing$$PackageHelper.accessListing$FS$mQuantity(favoriteListing, parcel.readInt());
        Listing$$PackageHelper.accessListing$FS$mProcessingMax(favoriteListing, parcel.readInt());
        Listing$$PackageHelper.accessListing$FS$mProcessingMin(favoriteListing, parcel.readInt());
        Listing$$PackageHelper.accessListing$FS$mNumFavorers(favoriteListing, parcel.readInt());
        Listing$$PackageHelper.accessListing$FS$mState(favoriteListing, parcel.readString());
        Listing$$PackageHelper.accessListing$FS$mOriginalLanguage(favoriteListing, parcel.readString());
        i = parcel.readInt();
        if (i < 0) {
            list = null;
        } else {
            list = new ArrayList();
            for (int i4 = 0; i4 < i; i4++) {
                list.add(parcel.readString());
            }
        }
        Listing$$PackageHelper.accessListing$FS$mOverview(favoriteListing, list);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        Listing$$PackageHelper.accessListing$FS$mIsPrivate(favoriteListing, z);
        Listing$$PackageHelper.accessListing$FS$mConvertedCurrencyCode(favoriteListing, parcel.readString());
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        Listing$$PackageHelper.accessListing$FS$mIsVATInclusive(favoriteListing, z);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        Listing$$PackageHelper.accessListing$FS$mIsDigitalDownload(favoriteListing, z);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        Listing$$PackageHelper.accessListing$FS$mShouldAutoRenew(favoriteListing, z);
        Listing$$PackageHelper.accessListing$FS$mSearchAdsMetadata(favoriteListing, (SearchAdsMetadata) parcel.readSerializable());
        if (parcel.readInt() == -1) {
            offeringResponse = null;
        } else {
            offeringResponse = readcom_etsy_android_lib_models_apiv3_OfferingResponse(parcel);
        }
        Listing$$PackageHelper.accessListing$FS$mOfferings(favoriteListing, offeringResponse);
        Listing$$PackageHelper.accessListing$FS$mLanguage(favoriteListing, parcel.readString());
        Listing$$PackageHelper.accessListing$FS$mMostRecentUpdate(favoriteListing, (ListingUpdate) parcel.readParcelable(ShopV3$$Parcelable.class.getClassLoader()));
        if (parcel.readInt() == -1) {
            etsyMoney = null;
        } else {
            etsyMoney = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        Listing$$PackageHelper.accessListing$FS$mPrice(favoriteListing, etsyMoney);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        Listing$$PackageHelper.accessListing$FS$mHasCollections(favoriteListing, z);
        readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                arrayList.add((ListingOption) parcel.readParcelable(ShopV3$$Parcelable.class.getClassLoader()));
            }
            obj = arrayList;
        }
        Listing$$PackageHelper.accessListing$FS$mOptions(favoriteListing, list);
        if (parcel.readInt() < 0) {
            bool = null;
        } else {
            bool = Boolean.valueOf(parcel.readInt() == 1);
        }
        Listing$$PackageHelper.accessListing$FS$mIsFundOnEtsyCampaign(favoriteListing, bool);
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        Listing$$PackageHelper.accessListing$FS$mIsFavorite(favoriteListing, z2);
        i = parcel.readInt();
        if (i < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i2 = 0; i2 < i; i2++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_ListingImage(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        Listing$$PackageHelper.accessListing$FS$mImages(favoriteListing, list);
        i = parcel.readInt();
        if (i < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i2 = 0; i2 < i; i2++) {
                arrayList.add((ShippingInfo) parcel.readSerializable());
            }
            obj = arrayList;
        }
        Listing$$PackageHelper.accessListing$FS$mShippingInfo(favoriteListing, list);
        Listing$$PackageHelper.accessListing$FS$mFundOnEtsyCampaign(favoriteListing, (FundOnEtsyCampaign) parcel.readParcelable(ShopV3$$Parcelable.class.getClassLoader()));
        i = parcel.readInt();
        if (i < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i2 = 0; i2 < i; i2++) {
                arrayList.add((Collection) parcel.readSerializable());
            }
            obj = arrayList;
        }
        Listing$$PackageHelper.accessListing$FS$mCollections(favoriteListing, list);
        i2 = parcel.readInt();
        if (i2 < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            while (i3 < i2) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_Variation(parcel);
                }
                arrayList.add(obj);
                i3++;
            }
            obj = arrayList;
        }
        Listing$$PackageHelper.accessListing$FS$mVariations(favoriteListing, list);
        Listing$$PackageHelper.accessListing$FS$mUrl(favoriteListing, parcel.readString());
        Listing$$PackageHelper.accessListing$FS$mTitle(favoriteListing, parcel.readString());
        Listing$$PackageHelper.accessListing$FS$mTransparentPriceMessage(favoriteListing, parcel.readString());
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        Listing$$PackageHelper.accessListing$FS$mUserId(favoriteListing, etsyId2);
        return favoriteListing;
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

    private Manufacturer readcom_etsy_android_lib_models_Manufacturer(Parcel parcel) {
        Manufacturer manufacturer = new Manufacturer();
        Manufacturer$$PackageHelper.accessManufacturer$FS$mLocation(manufacturer, parcel.readString());
        Manufacturer$$PackageHelper.accessManufacturer$FS$mName(manufacturer, parcel.readString());
        Manufacturer$$PackageHelper.accessManufacturer$FS$mDescription(manufacturer, parcel.readString());
        return manufacturer;
    }

    private OfferingResponse readcom_etsy_android_lib_models_apiv3_OfferingResponse(Parcel parcel) {
        OfferingUi offeringUi;
        OfferingPrice offeringPrice;
        Offering offering = null;
        OfferingResponse offeringResponse = new OfferingResponse();
        offeringResponse.mMinQuantity = parcel.readInt();
        if (parcel.readInt() == -1) {
            offeringUi = null;
        } else {
            offeringUi = readcom_etsy_android_lib_models_apiv3_OfferingUi(parcel);
        }
        offeringResponse.mUi = offeringUi;
        if (parcel.readInt() == -1) {
            offeringPrice = null;
        } else {
            offeringPrice = readcom_etsy_android_lib_models_apiv3_OfferingPrice(parcel);
        }
        offeringResponse.mMinPrice = offeringPrice;
        if (parcel.readInt() == -1) {
            offeringPrice = null;
        } else {
            offeringPrice = readcom_etsy_android_lib_models_apiv3_OfferingPrice(parcel);
        }
        offeringResponse.mMaxPrice = offeringPrice;
        if (parcel.readInt() != -1) {
            offering = readcom_etsy_android_lib_models_apiv3_Offering(parcel);
        }
        offeringResponse.mOffering = offering;
        offeringResponse.mMaxQuantity = parcel.readInt();
        return offeringResponse;
    }

    private OfferingUi readcom_etsy_android_lib_models_apiv3_OfferingUi(Parcel parcel) {
        FormattedMoney formattedMoney;
        List list;
        boolean z;
        OfferingRangeSelect offeringRangeSelect = null;
        OfferingUi offeringUi = new OfferingUi();
        if (parcel.readInt() == -1) {
            formattedMoney = null;
        } else {
            formattedMoney = readcom_etsy_android_lib_models_apiv3_FormattedMoney(parcel);
        }
        offeringUi.mPrice = formattedMoney;
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
                    obj = readcom_etsy_android_lib_models_apiv3_OfferingSelect(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        offeringUi.mSelects = list;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        offeringUi.mHasVariableQuantity = z;
        if (parcel.readInt() != -1) {
            offeringRangeSelect = readcom_etsy_android_lib_models_apiv3_OfferingRangeSelect(parcel);
        }
        offeringUi.mQuantity = offeringRangeSelect;
        return offeringUi;
    }

    private FormattedMoney readcom_etsy_android_lib_models_apiv3_FormattedMoney(Parcel parcel) {
        List list = null;
        FormattedMoney formattedMoney = new FormattedMoney();
        formattedMoney.mFormatString = parcel.readString();
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_Money(parcel);
                }
                arrayList.add(obj);
            }
            Object obj2 = arrayList;
        }
        formattedMoney.mArguments = list;
        return formattedMoney;
    }

    private Money readcom_etsy_android_lib_models_apiv3_Money(Parcel parcel) {
        Money money = new Money();
        money.mDivisor = parcel.readInt();
        money.mCurrencyCode = parcel.readString();
        money.mAmount = parcel.readString();
        return money;
    }

    private OfferingSelect readcom_etsy_android_lib_models_apiv3_OfferingSelect(Parcel parcel) {
        OfferingOption offeringOption;
        boolean z;
        List list = null;
        OfferingSelect offeringSelect = new OfferingSelect();
        if (parcel.readInt() == -1) {
            offeringOption = null;
        } else {
            offeringOption = readcom_etsy_android_lib_models_apiv3_OfferingOption(parcel);
        }
        offeringSelect.mDefaultOption = offeringOption;
        offeringSelect.mPrompt = parcel.readString();
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_OfferingOption(parcel);
                }
                arrayList.add(obj);
            }
            Object obj2 = arrayList;
        }
        offeringSelect.mOptions = list;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        offeringSelect.mEnabled = z;
        offeringSelect.mLabel = parcel.readString();
        return offeringSelect;
    }

    private OfferingOption readcom_etsy_android_lib_models_apiv3_OfferingOption(Parcel parcel) {
        EtsyId etsyId;
        boolean z;
        FormattedMoney formattedMoney = null;
        boolean z2 = true;
        OfferingOption offeringOption = new OfferingOption();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        offeringOption.mValue = etsyId;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        offeringOption.mSelected = z;
        if (parcel.readInt() != -1) {
            formattedMoney = readcom_etsy_android_lib_models_apiv3_FormattedMoney(parcel);
        }
        offeringOption.mDisplayValue = formattedMoney;
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        offeringOption.mEnabled = z2;
        return offeringOption;
    }

    private OfferingRangeSelect readcom_etsy_android_lib_models_apiv3_OfferingRangeSelect(Parcel parcel) {
        boolean z = true;
        OfferingRangeSelect offeringRangeSelect = new OfferingRangeSelect();
        offeringRangeSelect.mMax = parcel.readInt();
        offeringRangeSelect.mMin = parcel.readInt();
        if (parcel.readInt() != 1) {
            z = false;
        }
        offeringRangeSelect.mEnabled = z;
        offeringRangeSelect.mStep = parcel.readInt();
        offeringRangeSelect.mLabel = parcel.readString();
        return offeringRangeSelect;
    }

    private OfferingPrice readcom_etsy_android_lib_models_apiv3_OfferingPrice(Parcel parcel) {
        OfferingPrice offeringPrice = new OfferingPrice();
        offeringPrice.mCurrencyFormattedShort = parcel.readString();
        offeringPrice.mCurrencyCode = parcel.readString();
        offeringPrice.mCurrencyFormattedRaw = parcel.readString();
        offeringPrice.mCurrencyFormattedLong = parcel.readString();
        return offeringPrice;
    }

    private Offering readcom_etsy_android_lib_models_apiv3_Offering(Parcel parcel) {
        EtsyId etsyId;
        OfferingPrice offeringPrice;
        EtsyId etsyId2 = null;
        Offering offering = new Offering();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        offering.mOfferingId = etsyId;
        if (parcel.readInt() == -1) {
            offeringPrice = null;
        } else {
            offeringPrice = readcom_etsy_android_lib_models_apiv3_OfferingPrice(parcel);
        }
        offering.mPrice = offeringPrice;
        offering.mQuantity = parcel.readInt();
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        offering.mProductId = etsyId2;
        return offering;
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

    private Variation readcom_etsy_android_lib_models_Variation(Parcel parcel) {
        Option option;
        boolean z = true;
        int i = 0;
        List list = null;
        Variation variation = new Variation();
        if (parcel.readInt() != 1) {
            z = false;
        }
        Variation$$PackageHelper.accessVariation$FS$mIsValid(variation, z);
        if (parcel.readInt() == -1) {
            option = null;
        } else {
            option = readcom_etsy_android_lib_models_Option(parcel);
        }
        Variation$$PackageHelper.accessVariation$FS$mSelectedOption(variation, option);
        Variation$$PackageHelper.accessVariation$FS$mFormattedValue(variation, parcel.readString());
        Variation$$PackageHelper.accessVariation$FS$mFormattedName(variation, parcel.readString());
        Variation$$PackageHelper.accessVariation$FS$mPropertyId(variation, parcel.readLong());
        Variation$$PackageHelper.accessVariation$FS$mValueId(variation, parcel.readLong());
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            while (i < readInt) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_Option(parcel);
                }
                arrayList.add(obj);
                i++;
            }
            Object obj2 = arrayList;
        }
        Variation$$PackageHelper.accessVariation$FS$mOptions(variation, list);
        return variation;
    }

    private Option readcom_etsy_android_lib_models_Option(Parcel parcel) {
        EtsyMoney etsyMoney;
        EtsyMoney etsyMoney2 = null;
        Option option = new Option();
        Option$$PackageHelper.accessOption$FS$mVariationPropertyId(option, parcel.readLong());
        Option$$PackageHelper.accessOption$FS$mFormattedValue(option, parcel.readString());
        Option$$PackageHelper.accessOption$FS$mValueId(option, parcel.readLong());
        if (parcel.readInt() == -1) {
            etsyMoney = null;
        } else {
            etsyMoney = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        Option$$PackageHelper.accessOption$FS$mPrice(option, etsyMoney);
        if (parcel.readInt() == -1) {
            etsyMoney = null;
        } else {
            etsyMoney = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        Option$$PackageHelper.accessOption$FS$mPriceDiff(option, etsyMoney);
        if (parcel.readInt() != -1) {
            etsyMoney2 = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        Option$$PackageHelper.accessOption$FS$mConvertedPrice(option, etsyMoney2);
        Option$$PackageHelper.accessOption$FS$mIsAvailable(option, parcel.readInt() == 1);
        return option;
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

    private Language readcom_etsy_android_lib_models_apiv3_Language(Parcel parcel) {
        Language language = new Language();
        language.mCode = parcel.readString();
        language.mName = parcel.readString();
        return language;
    }

    private void writecom_etsy_android_lib_models_apiv3_ShopV3(ShopV3 shopV3, Parcel parcel, int i) {
        int i2;
        int i3 = 0;
        parcel.writeString(shopV3.mCountryCode);
        parcel.writeString(shopV3.mGoogleAnalyticsPropertyId);
        parcel.writeInt(shopV3.mHasOwners ? 1 : 0);
        parcel.writeString(shopV3.mLocation);
        parcel.writeInt(shopV3.mBrandingOption);
        parcel.writeInt(shopV3.mTotalRatingCount);
        if (shopV3.mHasAboutPage) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeString(shopV3.mOnboardingStatus);
        parcel.writeInt(shopV3.mFavoritesCount);
        if (shopV3.mHasIcon) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeString(shopV3.mCity);
        if (shopV3.mIsUsingStructuredPolicies) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (shopV3.mHasCurrencyCode) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeString(shopV3.mVacationMessage);
        parcel.writeString(shopV3.mSellerName);
        parcel.writeString(shopV3.mCurrencyCode);
        parcel.writeString(shopV3.mStory);
        if (shopV3.mAcceptsChecks) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeDouble(shopV3.mLatitude);
        new au().m3270a(shopV3.mCreateDate, parcel);
        if (shopV3.mAcceptsBankTransfers) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (shopV3.mAcceptsDirectCheckout) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeDouble(shopV3.mLongitude);
        if (shopV3.mIsVacation) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        new au().m3270a(shopV3.mUpdateDate, parcel);
        parcel.writeString(shopV3.mHeadline);
        if (shopV3.mAcceptsMoneyOrders) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (shopV3.mShopId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(shopV3.mShopId, parcel, i);
        }
        parcel.writeString(shopV3.mStoryHeadline);
        parcel.writeString(shopV3.mShopUrl);
        parcel.writeString(shopV3.mName);
        parcel.writeString(shopV3.mBannerUrl);
        parcel.writeDouble(shopV3.mAverageRating);
        if (shopV3.mHasLanguagePreferences) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (shopV3.mIsOpen) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeString(shopV3.mVacationAutoReply);
        if (shopV3.mShopIcon == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_ShopIconV3(shopV3.mShopIcon, parcel, i);
        }
        if (shopV3.mAcceptsOther) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (shopV3.mAboutInfoExists) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeInt(shopV3.mDigitalListingCount);
        if (shopV3.mOwner == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_User(shopV3.mOwner, parcel, i);
        }
        parcel.writeString(shopV3.mMessageToBuyers);
        parcel.writeSerializable(shopV3.mMessageUpdateDate);
        if (shopV3.mHasBanner) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (shopV3.mLargeBanner == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Image(shopV3.mLargeBanner, parcel, i);
        }
        new au().m3270a(shopV3.mOpenDate, parcel);
        parcel.writeString(shopV3.mShopName);
        if (shopV3.mBanner == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Image(shopV3.mBanner, parcel, i);
        }
        parcel.writeString(shopV3.mStatus);
        if (shopV3.mHasLargeBanner) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (shopV3.mListingRearrangeEnabled) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (shopV3.mAcceptsPayPal) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeString(shopV3.mPullQuote);
        parcel.writeString(shopV3.mRegion);
        parcel.writeString(shopV3.mStoryLeadingParagraph);
        if (shopV3.mAcceptsGiftCards) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (shopV3.mModules == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(shopV3.mModules.size());
            for (String writeString : shopV3.mModules) {
                parcel.writeString(writeString);
            }
        }
        parcel.writeInt(shopV3.mSoldCount);
        if (shopV3.mHasAbout) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeString(shopV3.mMessage);
        parcel.writeString(shopV3.mUrl);
        if (shopV3.mShopLanguages == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(shopV3.mShopLanguages.size());
            for (Language language : shopV3.mShopLanguages) {
                if (language == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_Language(language, parcel, i);
                }
            }
        }
        if (shopV3.mHasPrivateReceiptInfo) {
            i3 = 1;
        }
        parcel.writeInt(i3);
        parcel.writeInt(shopV3.mActiveListingCount);
        parcel.writeString(shopV3.mSellerAvatarUrl);
        if (shopV3.mUserId == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(shopV3.mUserId, parcel, i);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    private void writecom_etsy_android_lib_models_apiv3_ShopIconV3(ShopIconV3 shopIconV3, Parcel parcel, int i) {
        if (shopIconV3.mImageId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(shopIconV3.mImageId, parcel, i);
        }
        parcel.writeString(shopIconV3.mKey);
        if (shopIconV3.mSources == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(shopIconV3.mSources.size());
            for (Source source : shopIconV3.mSources) {
                if (source == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_Image$Source(source, parcel, i);
                }
            }
        }
        parcel.writeString(shopIconV3.mUrl);
    }

    private void writecom_etsy_android_lib_models_apiv3_Image$Source(Source source, Parcel parcel, int i) {
        parcel.writeInt(source.height);
        parcel.writeString(source.mUrl);
        parcel.writeInt(source.width);
    }

    private void writecom_etsy_android_lib_models_User(User user, Parcel parcel, int i) {
        if (User$$PackageHelper.accessUser$FG$mShops(user) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(User$$PackageHelper.accessUser$FG$mShops(user).size());
            for (Shop writeSerializable : User$$PackageHelper.accessUser$FG$mShops(user)) {
                parcel.writeSerializable(writeSerializable);
            }
        }
        parcel.writeSerializable(User$$PackageHelper.accessUser$FG$mPublicKey(user));
        parcel.writeSerializable(User$$PackageHelper.accessUser$FG$mProfile(user));
        parcel.writeString(User$$PackageHelper.accessUser$FG$mLoginName(user));
        if (User$$PackageHelper.accessUser$FG$mFavoriteListings(user) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(User$$PackageHelper.accessUser$FG$mFavoriteListings(user).size());
            for (FavoriteListing favoriteListing : User$$PackageHelper.accessUser$FG$mFavoriteListings(user)) {
                if (favoriteListing == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_FavoriteListing(favoriteListing, parcel, i);
                }
            }
        }
        parcel.writeInt(User$$PackageHelper.accessUser$FG$mAwaitingFeedbackCount(user));
        if (User$$PackageHelper.accessUser$FG$mAvatar(user) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Image(User$$PackageHelper.accessUser$FG$mAvatar(user), parcel, i);
        }
        if (User$$PackageHelper.accessUser$FG$mUserAddresses(user) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(User$$PackageHelper.accessUser$FG$mUserAddresses(user).size());
            for (UserAddress userAddress : User$$PackageHelper.accessUser$FG$mUserAddresses(user)) {
                if (userAddress == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_UserAddress(userAddress, parcel, i);
                }
            }
        }
        parcel.writeInt(User$$PackageHelper.accessUser$FG$mFollowerCount(user));
        parcel.writeInt(User$$PackageHelper.accessUser$FG$mFollowingCount(user));
        parcel.writeString(User$$PackageHelper.accessUser$FG$mEmail(user));
        if (User$$PackageHelper.accessUser$FG$mUserId(user) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(User$$PackageHelper.accessUser$FG$mUserId(user), parcel, i);
    }

    private void writecom_etsy_android_lib_models_FavoriteListing(FavoriteListing favoriteListing, Parcel parcel, int i) {
        int i2;
        int i3 = 0;
        if (Listing$$PackageHelper.accessListing$FG$mBuyerDisplayPrice(favoriteListing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(Listing$$PackageHelper.accessListing$FG$mBuyerDisplayPrice(favoriteListing), parcel, i);
        }
        parcel.writeSerializable(Listing$$PackageHelper.accessListing$FG$mShop(favoriteListing));
        if (Listing$$PackageHelper.accessListing$FG$mListingId(favoriteListing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(Listing$$PackageHelper.accessListing$FG$mListingId(favoriteListing), parcel, i);
        }
        parcel.writeSerializable(Listing$$PackageHelper.accessListing$FG$mPaymentInfo(favoriteListing));
        if (Listing$$PackageHelper.accessListing$FG$mManufacturers(favoriteListing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mManufacturers(favoriteListing).size());
            for (Manufacturer manufacturer : Listing$$PackageHelper.accessListing$FG$mManufacturers(favoriteListing)) {
                if (manufacturer == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_Manufacturer(manufacturer, parcel, i);
                }
            }
        }
        parcel.writeSerializable(Listing$$PackageHelper.accessListing$FG$mCreationDate(favoriteListing));
        parcel.writeSerializable(Listing$$PackageHelper.accessListing$FG$mListingVideos(favoriteListing));
        parcel.writeString(Listing$$PackageHelper.accessListing$FG$mConvertedPrice(favoriteListing));
        parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mViews(favoriteListing));
        parcel.writeString(Listing$$PackageHelper.accessListing$FG$mDescription(favoriteListing));
        parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mQuantity(favoriteListing));
        parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mProcessingMax(favoriteListing));
        parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mProcessingMin(favoriteListing));
        parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mNumFavorers(favoriteListing));
        parcel.writeString(Listing$$PackageHelper.accessListing$FG$mState(favoriteListing));
        parcel.writeString(Listing$$PackageHelper.accessListing$FG$mOriginalLanguage(favoriteListing));
        if (Listing$$PackageHelper.accessListing$FG$mOverview(favoriteListing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mOverview(favoriteListing).size());
            for (String writeString : Listing$$PackageHelper.accessListing$FG$mOverview(favoriteListing)) {
                parcel.writeString(writeString);
            }
        }
        if (Listing$$PackageHelper.accessListing$FG$mIsPrivate(favoriteListing)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeString(Listing$$PackageHelper.accessListing$FG$mConvertedCurrencyCode(favoriteListing));
        if (Listing$$PackageHelper.accessListing$FG$mIsVATInclusive(favoriteListing)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (Listing$$PackageHelper.accessListing$FG$mIsDigitalDownload(favoriteListing)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (Listing$$PackageHelper.accessListing$FG$mShouldAutoRenew(favoriteListing)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeSerializable(Listing$$PackageHelper.accessListing$FG$mSearchAdsMetadata(favoriteListing));
        if (Listing$$PackageHelper.accessListing$FG$mOfferings(favoriteListing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_OfferingResponse(Listing$$PackageHelper.accessListing$FG$mOfferings(favoriteListing), parcel, i);
        }
        parcel.writeString(Listing$$PackageHelper.accessListing$FG$mLanguage(favoriteListing));
        parcel.writeParcelable(Listing$$PackageHelper.accessListing$FG$mMostRecentUpdate(favoriteListing), i);
        if (Listing$$PackageHelper.accessListing$FG$mPrice(favoriteListing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(Listing$$PackageHelper.accessListing$FG$mPrice(favoriteListing), parcel, i);
        }
        if (Listing$$PackageHelper.accessListing$FG$mHasCollections(favoriteListing)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (Listing$$PackageHelper.accessListing$FG$mOptions(favoriteListing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mOptions(favoriteListing).size());
            for (ListingOption writeParcelable : Listing$$PackageHelper.accessListing$FG$mOptions(favoriteListing)) {
                parcel.writeParcelable(writeParcelable, i);
            }
        }
        if (Listing$$PackageHelper.accessListing$FG$mIsFundOnEtsyCampaign(favoriteListing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mIsFundOnEtsyCampaign(favoriteListing).booleanValue() ? 1 : 0);
        }
        if (Listing$$PackageHelper.accessListing$FG$mIsFavorite(favoriteListing)) {
            i3 = 1;
        }
        parcel.writeInt(i3);
        if (Listing$$PackageHelper.accessListing$FG$mImages(favoriteListing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mImages(favoriteListing).size());
            for (ListingImage listingImage : Listing$$PackageHelper.accessListing$FG$mImages(favoriteListing)) {
                if (listingImage == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_ListingImage(listingImage, parcel, i);
                }
            }
        }
        if (Listing$$PackageHelper.accessListing$FG$mShippingInfo(favoriteListing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mShippingInfo(favoriteListing).size());
            for (ShippingInfo writeSerializable : Listing$$PackageHelper.accessListing$FG$mShippingInfo(favoriteListing)) {
                parcel.writeSerializable(writeSerializable);
            }
        }
        parcel.writeParcelable(Listing$$PackageHelper.accessListing$FG$mFundOnEtsyCampaign(favoriteListing), i);
        if (Listing$$PackageHelper.accessListing$FG$mCollections(favoriteListing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mCollections(favoriteListing).size());
            for (Collection writeSerializable2 : Listing$$PackageHelper.accessListing$FG$mCollections(favoriteListing)) {
                parcel.writeSerializable(writeSerializable2);
            }
        }
        if (Listing$$PackageHelper.accessListing$FG$mVariations(favoriteListing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mVariations(favoriteListing).size());
            for (Variation variation : Listing$$PackageHelper.accessListing$FG$mVariations(favoriteListing)) {
                if (variation == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_Variation(variation, parcel, i);
                }
            }
        }
        parcel.writeString(Listing$$PackageHelper.accessListing$FG$mUrl(favoriteListing));
        parcel.writeString(Listing$$PackageHelper.accessListing$FG$mTitle(favoriteListing));
        parcel.writeString(Listing$$PackageHelper.accessListing$FG$mTransparentPriceMessage(favoriteListing));
        if (Listing$$PackageHelper.accessListing$FG$mUserId(favoriteListing) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(Listing$$PackageHelper.accessListing$FG$mUserId(favoriteListing), parcel, i);
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

    private void writecom_etsy_android_lib_models_Manufacturer(Manufacturer manufacturer, Parcel parcel, int i) {
        parcel.writeString(Manufacturer$$PackageHelper.accessManufacturer$FG$mLocation(manufacturer));
        parcel.writeString(Manufacturer$$PackageHelper.accessManufacturer$FG$mName(manufacturer));
        parcel.writeString(Manufacturer$$PackageHelper.accessManufacturer$FG$mDescription(manufacturer));
    }

    private void writecom_etsy_android_lib_models_apiv3_OfferingResponse(OfferingResponse offeringResponse, Parcel parcel, int i) {
        parcel.writeInt(offeringResponse.mMinQuantity);
        if (offeringResponse.mUi == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_OfferingUi(offeringResponse.mUi, parcel, i);
        }
        if (offeringResponse.mMinPrice == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_OfferingPrice(offeringResponse.mMinPrice, parcel, i);
        }
        if (offeringResponse.mMaxPrice == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_OfferingPrice(offeringResponse.mMaxPrice, parcel, i);
        }
        if (offeringResponse.mOffering == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Offering(offeringResponse.mOffering, parcel, i);
        }
        parcel.writeInt(offeringResponse.mMaxQuantity);
    }

    private void writecom_etsy_android_lib_models_apiv3_OfferingUi(OfferingUi offeringUi, Parcel parcel, int i) {
        if (offeringUi.mPrice == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_FormattedMoney(offeringUi.mPrice, parcel, i);
        }
        if (offeringUi.mSelects == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(offeringUi.mSelects.size());
            for (OfferingSelect offeringSelect : offeringUi.mSelects) {
                if (offeringSelect == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_OfferingSelect(offeringSelect, parcel, i);
                }
            }
        }
        parcel.writeInt(offeringUi.mHasVariableQuantity ? 1 : 0);
        if (offeringUi.mQuantity == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_OfferingRangeSelect(offeringUi.mQuantity, parcel, i);
    }

    private void writecom_etsy_android_lib_models_apiv3_FormattedMoney(FormattedMoney formattedMoney, Parcel parcel, int i) {
        parcel.writeString(formattedMoney.mFormatString);
        if (formattedMoney.mArguments == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(formattedMoney.mArguments.size());
        for (Money money : formattedMoney.mArguments) {
            if (money == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                writecom_etsy_android_lib_models_apiv3_Money(money, parcel, i);
            }
        }
    }

    private void writecom_etsy_android_lib_models_apiv3_Money(Money money, Parcel parcel, int i) {
        parcel.writeInt(money.mDivisor);
        parcel.writeString(money.mCurrencyCode);
        parcel.writeString(money.mAmount);
    }

    private void writecom_etsy_android_lib_models_apiv3_OfferingSelect(OfferingSelect offeringSelect, Parcel parcel, int i) {
        if (offeringSelect.mDefaultOption == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_OfferingOption(offeringSelect.mDefaultOption, parcel, i);
        }
        parcel.writeString(offeringSelect.mPrompt);
        if (offeringSelect.mOptions == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(offeringSelect.mOptions.size());
            for (OfferingOption offeringOption : offeringSelect.mOptions) {
                if (offeringOption == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_OfferingOption(offeringOption, parcel, i);
                }
            }
        }
        parcel.writeInt(offeringSelect.mEnabled ? 1 : 0);
        parcel.writeString(offeringSelect.mLabel);
    }

    private void writecom_etsy_android_lib_models_apiv3_OfferingOption(OfferingOption offeringOption, Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        if (offeringOption.mValue == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(offeringOption.mValue, parcel, i);
        }
        if (offeringOption.mSelected) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (offeringOption.mDisplayValue == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_FormattedMoney(offeringOption.mDisplayValue, parcel, i);
        }
        if (!offeringOption.mEnabled) {
            i3 = 0;
        }
        parcel.writeInt(i3);
    }

    private void writecom_etsy_android_lib_models_apiv3_OfferingRangeSelect(OfferingRangeSelect offeringRangeSelect, Parcel parcel, int i) {
        parcel.writeInt(offeringRangeSelect.mMax);
        parcel.writeInt(offeringRangeSelect.mMin);
        parcel.writeInt(offeringRangeSelect.mEnabled ? 1 : 0);
        parcel.writeInt(offeringRangeSelect.mStep);
        parcel.writeString(offeringRangeSelect.mLabel);
    }

    private void writecom_etsy_android_lib_models_apiv3_OfferingPrice(OfferingPrice offeringPrice, Parcel parcel, int i) {
        parcel.writeString(offeringPrice.mCurrencyFormattedShort);
        parcel.writeString(offeringPrice.mCurrencyCode);
        parcel.writeString(offeringPrice.mCurrencyFormattedRaw);
        parcel.writeString(offeringPrice.mCurrencyFormattedLong);
    }

    private void writecom_etsy_android_lib_models_apiv3_Offering(Offering offering, Parcel parcel, int i) {
        if (offering.mOfferingId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(offering.mOfferingId, parcel, i);
        }
        if (offering.mPrice == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_OfferingPrice(offering.mPrice, parcel, i);
        }
        parcel.writeInt(offering.mQuantity);
        if (offering.mProductId == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(offering.mProductId, parcel, i);
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

    private void writecom_etsy_android_lib_models_Variation(Variation variation, Parcel parcel, int i) {
        parcel.writeInt(Variation$$PackageHelper.accessVariation$FG$mIsValid(variation) ? 1 : 0);
        if (Variation$$PackageHelper.accessVariation$FG$mSelectedOption(variation) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_Option(Variation$$PackageHelper.accessVariation$FG$mSelectedOption(variation), parcel, i);
        }
        parcel.writeString(Variation$$PackageHelper.accessVariation$FG$mFormattedValue(variation));
        parcel.writeString(Variation$$PackageHelper.accessVariation$FG$mFormattedName(variation));
        parcel.writeLong(Variation$$PackageHelper.accessVariation$FG$mPropertyId(variation));
        parcel.writeLong(Variation$$PackageHelper.accessVariation$FG$mValueId(variation));
        if (Variation$$PackageHelper.accessVariation$FG$mOptions(variation) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(Variation$$PackageHelper.accessVariation$FG$mOptions(variation).size());
        for (Option option : Variation$$PackageHelper.accessVariation$FG$mOptions(variation)) {
            if (option == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                writecom_etsy_android_lib_models_Option(option, parcel, i);
            }
        }
    }

    private void writecom_etsy_android_lib_models_Option(Option option, Parcel parcel, int i) {
        int i2 = 1;
        parcel.writeLong(Option$$PackageHelper.accessOption$FG$mVariationPropertyId(option));
        parcel.writeString(Option$$PackageHelper.accessOption$FG$mFormattedValue(option));
        parcel.writeLong(Option$$PackageHelper.accessOption$FG$mValueId(option));
        if (Option$$PackageHelper.accessOption$FG$mPrice(option) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(Option$$PackageHelper.accessOption$FG$mPrice(option), parcel, i);
        }
        if (Option$$PackageHelper.accessOption$FG$mPriceDiff(option) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(Option$$PackageHelper.accessOption$FG$mPriceDiff(option), parcel, i);
        }
        if (Option$$PackageHelper.accessOption$FG$mConvertedPrice(option) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(Option$$PackageHelper.accessOption$FG$mConvertedPrice(option), parcel, i);
        }
        if (!Option$$PackageHelper.accessOption$FG$mIsAvailable(option)) {
            i2 = 0;
        }
        parcel.writeInt(i2);
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

    private void writecom_etsy_android_lib_models_apiv3_Language(Language language, Parcel parcel, int i) {
        parcel.writeString(language.mCode);
        parcel.writeString(language.mName);
    }

    public int describeContents() {
        return 0;
    }

    public ShopV3 getParcel() {
        return this.shopV3$$0;
    }
}
