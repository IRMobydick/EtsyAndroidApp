package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.core.EtsyMoney$$PackageHelper;
import com.etsy.android.lib.models.Attendee;
import com.etsy.android.lib.models.Attendee$$PackageHelper;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.BaseModelImage$$PackageHelper;
import com.etsy.android.lib.models.BaseModelImageWrapper;
import com.etsy.android.lib.models.BaseModelImageWrapper$$PackageHelper;
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
import com.etsy.android.lib.models.Manufacturer;
import com.etsy.android.lib.models.Manufacturer$$PackageHelper;
import com.etsy.android.lib.models.Option;
import com.etsy.android.lib.models.Option$$PackageHelper;
import com.etsy.android.lib.models.PaymentTemplate;
import com.etsy.android.lib.models.ReceiptReview;
import com.etsy.android.lib.models.ReceiptReview$$PackageHelper;
import com.etsy.android.lib.models.Review;
import com.etsy.android.lib.models.Review$$PackageHelper;
import com.etsy.android.lib.models.ReviewResponse;
import com.etsy.android.lib.models.ReviewResponse$$PackageHelper;
import com.etsy.android.lib.models.ScheduleExpanded;
import com.etsy.android.lib.models.ScheduleExpanded$$PackageHelper;
import com.etsy.android.lib.models.SearchAdsMetadata;
import com.etsy.android.lib.models.ShippingInfo;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.ShopAbout;
import com.etsy.android.lib.models.ShopAbout$$PackageHelper;
import com.etsy.android.lib.models.ShopAbout.RelatedLinks;
import com.etsy.android.lib.models.ShopAboutImage;
import com.etsy.android.lib.models.ShopAboutImage$$PackageHelper;
import com.etsy.android.lib.models.ShopAboutMember;
import com.etsy.android.lib.models.ShopAboutMember$$PackageHelper;
import com.etsy.android.lib.models.ShopAboutVideo;
import com.etsy.android.lib.models.ShopAboutVideo$$PackageHelper;
import com.etsy.android.lib.models.ShopSection;
import com.etsy.android.lib.models.ShopSection$$PackageHelper;
import com.etsy.android.lib.models.ShortenedUrl;
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
import com.etsy.android.lib.models.datatypes.TimeRange;
import com.etsy.android.lib.models.datatypes.TimeRange$$PackageHelper;
import com.etsy.android.lib.models.enums.WeekDay;
import com.etsy.android.lib.util.ar;
import com.etsy.android.lib.util.as;
import com.etsy.android.lib.util.au;
import com.etsy.android.uikit.util.MachineTranslationViewState;
import com.etsy.android.uikit.util.MachineTranslationViewState$$PackageHelper;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.parceler.ax;

public class ShopHomePage$$Parcelable implements android.os.Parcelable, ax<ShopHomePage> {
    public static final ax CREATOR;
    private ShopHomePage shopHomePage$$0;

    static {
        CREATOR = new ax();
    }

    public ShopHomePage$$Parcelable(Parcel parcel) {
        ShopHomePage shopHomePage;
        if (parcel.readInt() == -1) {
            shopHomePage = null;
        } else {
            shopHomePage = readcom_etsy_android_lib_models_apiv3_ShopHomePage(parcel);
        }
        this.shopHomePage$$0 = shopHomePage;
    }

    public ShopHomePage$$Parcelable(ShopHomePage shopHomePage) {
        this.shopHomePage$$0 = shopHomePage;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopHomePage$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_ShopHomePage(this.shopHomePage$$0, parcel, i);
    }

    private ShopHomePage readcom_etsy_android_lib_models_apiv3_ShopHomePage(Parcel parcel) {
        FAQs fAQs;
        List list;
        ArrayList arrayList;
        int i;
        Object obj;
        ShopV3 shopV3;
        StructuredShopPolicies structuredShopPolicies;
        ShopAbout shopAbout;
        SellerDetails sellerDetails;
        ShopReviewsResult shopReviewsResult;
        int i2 = 0;
        ShopPolicy shopPolicy = null;
        ShopHomePage shopHomePage = new ShopHomePage();
        if (parcel.readInt() == -1) {
            fAQs = null;
        } else {
            fAQs = readcom_etsy_android_lib_models_apiv3_FAQs(parcel);
        }
        shopHomePage.mFaqs = fAQs;
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
        shopHomePage.mFeaturedListings = list;
        if (parcel.readInt() == -1) {
            shopV3 = null;
        } else {
            shopV3 = readcom_etsy_android_lib_models_apiv3_ShopV3(parcel);
        }
        shopHomePage.mShop = shopV3;
        readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_LocalMarket(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        shopHomePage.mLocalMarkets = list;
        if (parcel.readInt() == -1) {
            structuredShopPolicies = null;
        } else {
            structuredShopPolicies = readcom_etsy_android_lib_models_apiv3_StructuredShopPolicies(parcel);
        }
        shopHomePage.mStructuredShopPolicies = structuredShopPolicies;
        if (parcel.readInt() == -1) {
            shopAbout = null;
        } else {
            shopAbout = readcom_etsy_android_lib_models_ShopAbout(parcel);
        }
        shopHomePage.mShopAbout = shopAbout;
        readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_ShopSection(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        shopHomePage.mShopSections = list;
        if (parcel.readInt() == -1) {
            sellerDetails = null;
        } else {
            sellerDetails = readcom_etsy_android_lib_models_apiv3_SellerDetails(parcel);
        }
        shopHomePage.mSellerDetails = sellerDetails;
        if (parcel.readInt() == -1) {
            shopReviewsResult = null;
        } else {
            shopReviewsResult = readcom_etsy_android_lib_models_apiv3_ShopReviewsResult(parcel);
        }
        shopHomePage.mShopReviews = shopReviewsResult;
        readInt = parcel.readInt();
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
        shopHomePage.mManufacturers = list;
        i = parcel.readInt();
        if (i < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            while (i2 < i) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_apiv3_ListingCard(parcel);
                }
                arrayList.add(obj);
                i2++;
            }
            obj = arrayList;
        }
        shopHomePage.mShopListings = list;
        if (parcel.readInt() != -1) {
            shopPolicy = readcom_etsy_android_lib_models_apiv3_ShopPolicy(parcel);
        }
        shopHomePage.mShopPolicy = shopPolicy;
        return shopHomePage;
    }

    private FAQs readcom_etsy_android_lib_models_apiv3_FAQs(Parcel parcel) {
        MachineTranslationViewState machineTranslationViewState;
        FAQs fAQs = new FAQs();
        if (parcel.readInt() == -1) {
            machineTranslationViewState = null;
        } else {
            machineTranslationViewState = readcom_etsy_android_uikit_util_MachineTranslationViewState(parcel);
        }
        fAQs.mTranslationState = machineTranslationViewState;
        return fAQs;
    }

    private MachineTranslationViewState readcom_etsy_android_uikit_util_MachineTranslationViewState(Parcel parcel) {
        boolean z = true;
        MachineTranslationViewState machineTranslationViewState = new MachineTranslationViewState();
        MachineTranslationViewState$$PackageHelper.m5555a(machineTranslationViewState, parcel.readInt());
        if (parcel.readInt() != 1) {
            z = false;
        }
        MachineTranslationViewState$$PackageHelper.m5556a(machineTranslationViewState, z);
        return machineTranslationViewState;
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
        listingCard.mFundOnEtsyCampaign = (FundOnEtsyCampaign) parcel.readParcelable(ShopHomePage$$Parcelable.class.getClassLoader());
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
        Listing$$PackageHelper.accessListing$FS$mMostRecentUpdate(favoriteListing, (ListingUpdate) parcel.readParcelable(ShopHomePage$$Parcelable.class.getClassLoader()));
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
                arrayList.add((ListingOption) parcel.readParcelable(ShopHomePage$$Parcelable.class.getClassLoader()));
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
        Listing$$PackageHelper.accessListing$FS$mFundOnEtsyCampaign(favoriteListing, (FundOnEtsyCampaign) parcel.readParcelable(ShopHomePage$$Parcelable.class.getClassLoader()));
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
                arrayList.add((LocalStoreImage) parcel.readParcelable(ShopHomePage$$Parcelable.class.getClassLoader()));
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

    private Rating readcom_etsy_android_lib_models_apiv3_Rating(Parcel parcel) {
        Rating rating = new Rating();
        rating.mStars = parcel.readDouble();
        rating.mSellerFeedbackCount = parcel.readInt();
        rating.mCount = parcel.readInt();
        rating.mRating = parcel.readDouble();
        return rating;
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
                list2.add((LocalStoreImage) parcel.readParcelable(ShopHomePage$$Parcelable.class.getClassLoader()));
            }
        }
        LocalMarketCard$$PackageHelper.accessLocalMarketCard$FS$mStoreImages(localMarketCard, list2);
        return localMarketCard;
    }

    private StructuredShopPolicies readcom_etsy_android_lib_models_apiv3_StructuredShopPolicies(Parcel parcel) {
        StructuredShopShipping structuredShopShipping;
        StructuredShopPrivacy structuredShopPrivacy;
        StructuredShopPayments structuredShopPayments;
        EtsyId etsyId;
        StructuredShopRefunds structuredShopRefunds;
        StructuredShopCharLimits structuredShopCharLimits = null;
        StructuredShopPolicies structuredShopPolicies = new StructuredShopPolicies();
        if (parcel.readInt() == -1) {
            structuredShopShipping = null;
        } else {
            structuredShopShipping = readcom_etsy_android_lib_models_apiv3_StructuredShopShipping(parcel);
        }
        structuredShopPolicies.mShipping = structuredShopShipping;
        if (parcel.readInt() == -1) {
            structuredShopPrivacy = null;
        } else {
            structuredShopPrivacy = readcom_etsy_android_lib_models_apiv3_StructuredShopPrivacy(parcel);
        }
        structuredShopPolicies.mPrivacy = structuredShopPrivacy;
        if (parcel.readInt() == -1) {
            structuredShopPayments = null;
        } else {
            structuredShopPayments = readcom_etsy_android_lib_models_apiv3_StructuredShopPayments(parcel);
        }
        structuredShopPolicies.mPayments = structuredShopPayments;
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        structuredShopPolicies.mPoliciesId = etsyId;
        structuredShopPolicies.mAdditionalTermsAndConditions = parcel.readString();
        if (parcel.readInt() == -1) {
            structuredShopRefunds = null;
        } else {
            structuredShopRefunds = readcom_etsy_android_lib_models_apiv3_StructuredShopRefunds(parcel);
        }
        structuredShopPolicies.mRefunds = structuredShopRefunds;
        structuredShopPolicies.mShopInGermany = parcel.readInt() == 1;
        if (parcel.readInt() != -1) {
            structuredShopCharLimits = readcom_etsy_android_lib_models_apiv3_StructuredShopCharLimits(parcel);
        }
        structuredShopPolicies.mCharacterLimits = structuredShopCharLimits;
        structuredShopPolicies.mUpdateDate = (Date) parcel.readSerializable();
        return structuredShopPolicies;
    }

    private StructuredShopShipping readcom_etsy_android_lib_models_apiv3_StructuredShopShipping(Parcel parcel) {
        List list = null;
        int i = 0;
        boolean z = true;
        StructuredShopShipping structuredShopShipping = new StructuredShopShipping();
        structuredShopShipping.mHasShippingUpgrades = parcel.readInt() == 1;
        structuredShopShipping.mProcessingTimeText = parcel.readString();
        if (parcel.readInt() != 1) {
            z = false;
        }
        structuredShopShipping.mShipsInternational = z;
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            while (i < readInt) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = m2379x10cd231a(parcel);
                }
                arrayList.add(obj);
                i++;
            }
            Object obj2 = arrayList;
        }
        structuredShopShipping.mEstimates = list;
        return structuredShopShipping;
    }

    private StructuredShopShippingEstimate m2379x10cd231a(Parcel parcel) {
        Integer num;
        StructuredShopShippingEstimate structuredShopShippingEstimate = new StructuredShopShippingEstimate();
        structuredShopShippingEstimate.mType = parcel.readString();
        if (parcel.readInt() < 0) {
            num = null;
        } else {
            num = Integer.valueOf(parcel.readInt());
        }
        structuredShopShippingEstimate.mCountryId = num;
        structuredShopShippingEstimate.mDisplayName = parcel.readString();
        structuredShopShippingEstimate.mMax = parcel.readInt();
        structuredShopShippingEstimate.mMin = parcel.readInt();
        structuredShopShippingEstimate.mUnit = parcel.readString();
        structuredShopShippingEstimate.mRegionCode = parcel.readString();
        return structuredShopShippingEstimate;
    }

    private StructuredShopPrivacy readcom_etsy_android_lib_models_apiv3_StructuredShopPrivacy(Parcel parcel) {
        MachineTranslationViewState machineTranslationViewState;
        StructuredShopPrivacy structuredShopPrivacy = new StructuredShopPrivacy();
        structuredShopPrivacy.mTranslatedOtherText = parcel.readString();
        structuredShopPrivacy.mHeader = parcel.readString();
        if (parcel.readInt() == -1) {
            machineTranslationViewState = null;
        } else {
            machineTranslationViewState = readcom_etsy_android_uikit_util_MachineTranslationViewState(parcel);
        }
        structuredShopPrivacy.mOtherTranslationState = machineTranslationViewState;
        structuredShopPrivacy.mFlags = (PrivacyFlags) parcel.readSerializable();
        return structuredShopPrivacy;
    }

    private StructuredShopPayments readcom_etsy_android_lib_models_apiv3_StructuredShopPayments(Parcel parcel) {
        List list;
        int i;
        boolean z;
        List list2 = null;
        int i2 = 0;
        StructuredShopPayments structuredShopPayments = new StructuredShopPayments();
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            list = new ArrayList();
            for (i = 0; i < readInt; i++) {
                list.add(parcel.readString());
            }
        }
        structuredShopPayments.mManualPaymentMethods = list;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        structuredShopPayments.mAcceptsDirectCheckout = z;
        i = parcel.readInt();
        if (i < 0) {
            list = null;
        } else {
            list = new ArrayList();
            for (int i3 = 0; i3 < i; i3++) {
                list.add(parcel.readString());
            }
        }
        structuredShopPayments.mProtectedPaymentMethods = list;
        int readInt2 = parcel.readInt();
        if (readInt2 >= 0) {
            list2 = new ArrayList();
            while (i2 < readInt2) {
                list2.add(parcel.readString());
                i2++;
            }
        }
        structuredShopPayments.mAcceptedPaymentMethods = list2;
        return structuredShopPayments;
    }

    private StructuredShopRefunds readcom_etsy_android_lib_models_apiv3_StructuredShopRefunds(Parcel parcel) {
        boolean z;
        List list;
        boolean z2 = true;
        StructuredShopRefunds structuredShopRefunds = new StructuredShopRefunds();
        structuredShopRefunds.mCancellationWindowType = parcel.readString();
        structuredShopRefunds.mAcceptedSummaryString = parcel.readString();
        structuredShopRefunds.mCancelWithinHours = parcel.readInt();
        structuredShopRefunds.mReturnWithinDays = parcel.readInt();
        structuredShopRefunds.mAcceptsCancellations = parcel.readInt() == 1;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        structuredShopRefunds.mExchanges = z;
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                arrayList.add((NonRefundableItem) parcel.readSerializable());
            }
            Object obj = arrayList;
        }
        structuredShopRefunds.mNonRefundableItems = list;
        structuredShopRefunds.mContactWithinDays = parcel.readInt();
        structuredShopRefunds.mNotAcceptedSummaryString = parcel.readString();
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        structuredShopRefunds.mAcceptsReturns = z2;
        return structuredShopRefunds;
    }

    private StructuredShopCharLimits readcom_etsy_android_lib_models_apiv3_StructuredShopCharLimits(Parcel parcel) {
        StructuredShopCharLimits structuredShopCharLimits = new StructuredShopCharLimits();
        structuredShopCharLimits.mPrivacyPolicyOtherLimit = parcel.readInt();
        return structuredShopCharLimits;
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
        ShopAbout$$PackageHelper.accessShopAbout$FS$mImages(shopAbout, list);
        ShopAbout$$PackageHelper.accessShopAbout$FS$mLinks(shopAbout, (RelatedLinks) parcel.readSerializable());
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
        ShopAbout$$PackageHelper.accessShopAbout$FS$mVideos(shopAbout, list);
        ShopAbout$$PackageHelper.accessShopAbout$FS$mUrl(shopAbout, parcel.readString());
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
        ShopAbout$$PackageHelper.accessShopAbout$FS$mMembers(shopAbout, list2);
        ShopAbout$$PackageHelper.accessShopAbout$FS$mStory(shopAbout, parcel.readString());
        ShopAbout$$PackageHelper.accessShopAbout$FS$mStoryHeadline(shopAbout, parcel.readString());
        return shopAbout;
    }

    private ShopAboutImage readcom_etsy_android_lib_models_ShopAboutImage(Parcel parcel) {
        List list;
        EtsyId etsyId;
        Image image = null;
        BaseModelImage shopAboutImage = new ShopAboutImage();
        ShopAboutImage$$PackageHelper.accessShopAboutImage$FS$mKey(shopAboutImage, parcel.readString());
        ShopAboutImage$$PackageHelper.accessShopAboutImage$FS$mImageUrl545xN(shopAboutImage, parcel.readString());
        ShopAboutImage$$PackageHelper.accessShopAboutImage$FS$mRank(shopAboutImage, parcel.readInt());
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
        ShopAboutImage$$PackageHelper.accessShopAboutImage$FS$mSources(shopAboutImage, list);
        ShopAboutImage$$PackageHelper.accessShopAboutImage$FS$mUrl(shopAboutImage, parcel.readString());
        ShopAboutImage$$PackageHelper.accessShopAboutImage$FS$mImageUrl178x178(shopAboutImage, parcel.readString());
        ShopAboutImage$$PackageHelper.accessShopAboutImage$FS$mImageUrl760xN(shopAboutImage, parcel.readString());
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        ShopAboutImage$$PackageHelper.accessShopAboutImage$FS$mImageId(shopAboutImage, etsyId);
        if (parcel.readInt() != -1) {
            image = readcom_etsy_android_lib_models_apiv3_Image(parcel);
        }
        ShopAboutImage$$PackageHelper.accessShopAboutImage$FS$mImage(shopAboutImage, image);
        ShopAboutImage$$PackageHelper.accessShopAboutImage$FS$mCaption(shopAboutImage, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl570xN(shopAboutImage, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl224xN(shopAboutImage, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$PORTRAIT_HEIGHT_RATIO(shopAboutImage, parcel.readDouble());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl680x540(shopAboutImage, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl75x75(shopAboutImage, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl170x135(shopAboutImage, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrlFullxFull(shopAboutImage, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl300x300(shopAboutImage, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl340x270(shopAboutImage, parcel.readString());
        return shopAboutImage;
    }

    private ShopAboutVideo readcom_etsy_android_lib_models_ShopAboutVideo(Parcel parcel) {
        EtsyId etsyId;
        BaseModelImageWrapper baseModelImageWrapper;
        EtsyId etsyId2 = null;
        ShopAboutVideo shopAboutVideo = new ShopAboutVideo();
        ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FS$mUrl(shopAboutVideo, parcel.readString());
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FS$mShopId(shopAboutVideo, etsyId);
        if (parcel.readInt() == -1) {
            baseModelImageWrapper = null;
        } else {
            baseModelImageWrapper = readcom_etsy_android_lib_models_BaseModelImageWrapper(parcel);
        }
        ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FS$mThumbnail(shopAboutVideo, baseModelImageWrapper);
        ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FS$mProviderStatus(shopAboutVideo, parcel.readString());
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FS$mVideoId(shopAboutVideo, etsyId2);
        return shopAboutVideo;
    }

    private BaseModelImageWrapper readcom_etsy_android_lib_models_BaseModelImageWrapper(Parcel parcel) {
        Image image;
        if (parcel.readInt() == -1) {
            image = null;
        } else {
            image = readcom_etsy_android_lib_models_apiv3_Image(parcel);
        }
        BaseModelImage baseModelImageWrapper = new BaseModelImageWrapper(image);
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl570xN(baseModelImageWrapper, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl224xN(baseModelImageWrapper, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$PORTRAIT_HEIGHT_RATIO(baseModelImageWrapper, parcel.readDouble());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl680x540(baseModelImageWrapper, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl75x75(baseModelImageWrapper, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl170x135(baseModelImageWrapper, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrlFullxFull(baseModelImageWrapper, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl300x300(baseModelImageWrapper, parcel.readString());
        BaseModelImage$$PackageHelper.accessBaseModelImage$FS$mUrl340x270(baseModelImageWrapper, parcel.readString());
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

    private ShopSection readcom_etsy_android_lib_models_ShopSection(Parcel parcel) {
        EtsyId etsyId;
        ShopSection shopSection = new ShopSection();
        ShopSection$$PackageHelper.accessShopSection$FS$mRank(shopSection, parcel.readInt());
        ShopSection$$PackageHelper.accessShopSection$FS$mListingActiveCount(shopSection, parcel.readInt());
        ShopSection$$PackageHelper.accessShopSection$FS$mTitle(shopSection, parcel.readString());
        ShopSection$$PackageHelper.accessShopSection$FS$mDisplayLabel(shopSection, new as().m3265a(parcel));
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        ShopSection$$PackageHelper.accessShopSection$FS$mShopSectionId(shopSection, etsyId);
        return shopSection;
    }

    private SellerDetails readcom_etsy_android_lib_models_apiv3_SellerDetails(Parcel parcel) {
        SellerDetails sellerDetails = new SellerDetails();
        sellerDetails.mPhone = parcel.readString();
        sellerDetails.mFormattedString = parcel.readString();
        sellerDetails.mCity = parcel.readString();
        sellerDetails.mCountry = parcel.readString();
        sellerDetails.mState = parcel.readString();
        sellerDetails.mFirstName = parcel.readString();
        sellerDetails.mAddressLine2 = parcel.readString();
        sellerDetails.mAddressLine1 = parcel.readString();
        sellerDetails.mPostalCode = parcel.readString();
        sellerDetails.mVatNumber = parcel.readString();
        sellerDetails.mLastName = parcel.readString();
        sellerDetails.mEmail = parcel.readString();
        return sellerDetails;
    }

    private ShopReviewsResult readcom_etsy_android_lib_models_apiv3_ShopReviewsResult(Parcel parcel) {
        List list = null;
        ShopReviewsResult shopReviewsResult = new ShopReviewsResult();
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_ReceiptReview(parcel);
                }
                arrayList.add(obj);
            }
            Object obj2 = arrayList;
        }
        shopReviewsResult.mReviews = list;
        shopReviewsResult.mCount = parcel.readInt();
        return shopReviewsResult;
    }

    private ReceiptReview readcom_etsy_android_lib_models_ReceiptReview(Parcel parcel) {
        List list;
        EtsyId etsyId;
        EtsyId etsyId2 = null;
        ReceiptReview receiptReview = new ReceiptReview();
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
                    obj = readcom_etsy_android_lib_models_Review(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        ReceiptReview$$PackageHelper.accessReceiptReview$FS$mReviews(receiptReview, list);
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        ReceiptReview$$PackageHelper.accessReceiptReview$FS$mReceiptId(receiptReview, etsyId);
        ReceiptReview$$PackageHelper.accessReceiptReview$FS$mDate(receiptReview, (Date) parcel.readSerializable());
        ReceiptReview$$PackageHelper.accessReceiptReview$FS$mUserLoginName(receiptReview, parcel.readString());
        ReceiptReview$$PackageHelper.accessReceiptReview$FS$mUserAvatarUrl(receiptReview, parcel.readString());
        ReceiptReview$$PackageHelper.accessReceiptReview$FS$mUserRealName(receiptReview, parcel.readString());
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        ReceiptReview$$PackageHelper.accessReceiptReview$FS$mUserId(receiptReview, etsyId2);
        return receiptReview;
    }

    private Review readcom_etsy_android_lib_models_Review(Parcel parcel) {
        ListingImage listingImage;
        Listing listing;
        EtsyId etsyId;
        AppreciationPhoto appreciationPhoto;
        ReviewResponse reviewResponse;
        MachineTranslationViewState machineTranslationViewState = null;
        Review review = new Review();
        if (parcel.readInt() == -1) {
            listingImage = null;
        } else {
            listingImage = readcom_etsy_android_lib_models_ListingImage(parcel);
        }
        Review$$PackageHelper.accessReview$FS$mListingImage(review, listingImage);
        Review$$PackageHelper.accessReview$FS$mTranslatedContent(review, parcel.readString());
        if (parcel.readInt() == -1) {
            listing = null;
        } else {
            listing = readcom_etsy_android_lib_models_Listing(parcel);
        }
        Review$$PackageHelper.accessReview$FS$mListing(review, listing);
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        Review$$PackageHelper.accessReview$FS$mListingId(review, etsyId);
        if (parcel.readInt() == -1) {
            appreciationPhoto = null;
        } else {
            appreciationPhoto = readcom_etsy_android_lib_models_apiv3_AppreciationPhoto(parcel);
        }
        Review$$PackageHelper.accessReview$FS$mAppreciationPhoto(review, appreciationPhoto);
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        Review$$PackageHelper.accessReview$FS$mTransactionId(review, etsyId);
        Review$$PackageHelper.accessReview$FS$mListingImageUrl(review, parcel.readString());
        if (parcel.readInt() == -1) {
            reviewResponse = null;
        } else {
            reviewResponse = readcom_etsy_android_lib_models_ReviewResponse(parcel);
        }
        Review$$PackageHelper.accessReview$FS$mResponse(review, reviewResponse);
        Review$$PackageHelper.accessReview$FS$mReviewMessage(review, parcel.readString());
        Review$$PackageHelper.accessReview$FS$mIsListingDisplayable(review, parcel.readInt() == 1);
        if (parcel.readInt() != -1) {
            machineTranslationViewState = readcom_etsy_android_uikit_util_MachineTranslationViewState(parcel);
        }
        Review$$PackageHelper.accessReview$FS$mReviewTranslationState(review, machineTranslationViewState);
        Review$$PackageHelper.accessReview$FS$mListingTitle(review, parcel.readString());
        Review$$PackageHelper.accessReview$FS$mRating(review, parcel.readInt());
        Review$$PackageHelper.accessReview$FS$mReviewLanguage(review, parcel.readString());
        return review;
    }

    private Listing readcom_etsy_android_lib_models_Listing(Parcel parcel) {
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
        Listing listing = new Listing();
        if (parcel.readInt() == -1) {
            etsyMoney = null;
        } else {
            etsyMoney = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        Listing$$PackageHelper.accessListing$FS$mBuyerDisplayPrice(listing, etsyMoney);
        Listing$$PackageHelper.accessListing$FS$mShop(listing, (Shop) parcel.readSerializable());
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        Listing$$PackageHelper.accessListing$FS$mListingId(listing, etsyId);
        Listing$$PackageHelper.accessListing$FS$mPaymentInfo(listing, (PaymentTemplate) parcel.readSerializable());
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
        Listing$$PackageHelper.accessListing$FS$mManufacturers(listing, list);
        Listing$$PackageHelper.accessListing$FS$mCreationDate(listing, (Date) parcel.readSerializable());
        Listing$$PackageHelper.accessListing$FS$mListingVideos(listing, (ListingVideos) parcel.readSerializable());
        Listing$$PackageHelper.accessListing$FS$mConvertedPrice(listing, parcel.readString());
        Listing$$PackageHelper.accessListing$FS$mViews(listing, parcel.readInt());
        Listing$$PackageHelper.accessListing$FS$mDescription(listing, parcel.readString());
        Listing$$PackageHelper.accessListing$FS$mQuantity(listing, parcel.readInt());
        Listing$$PackageHelper.accessListing$FS$mProcessingMax(listing, parcel.readInt());
        Listing$$PackageHelper.accessListing$FS$mProcessingMin(listing, parcel.readInt());
        Listing$$PackageHelper.accessListing$FS$mNumFavorers(listing, parcel.readInt());
        Listing$$PackageHelper.accessListing$FS$mState(listing, parcel.readString());
        Listing$$PackageHelper.accessListing$FS$mOriginalLanguage(listing, parcel.readString());
        i = parcel.readInt();
        if (i < 0) {
            list = null;
        } else {
            list = new ArrayList();
            for (int i4 = 0; i4 < i; i4++) {
                list.add(parcel.readString());
            }
        }
        Listing$$PackageHelper.accessListing$FS$mOverview(listing, list);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        Listing$$PackageHelper.accessListing$FS$mIsPrivate(listing, z);
        Listing$$PackageHelper.accessListing$FS$mConvertedCurrencyCode(listing, parcel.readString());
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        Listing$$PackageHelper.accessListing$FS$mIsVATInclusive(listing, z);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        Listing$$PackageHelper.accessListing$FS$mIsDigitalDownload(listing, z);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        Listing$$PackageHelper.accessListing$FS$mShouldAutoRenew(listing, z);
        Listing$$PackageHelper.accessListing$FS$mSearchAdsMetadata(listing, (SearchAdsMetadata) parcel.readSerializable());
        if (parcel.readInt() == -1) {
            offeringResponse = null;
        } else {
            offeringResponse = readcom_etsy_android_lib_models_apiv3_OfferingResponse(parcel);
        }
        Listing$$PackageHelper.accessListing$FS$mOfferings(listing, offeringResponse);
        Listing$$PackageHelper.accessListing$FS$mLanguage(listing, parcel.readString());
        Listing$$PackageHelper.accessListing$FS$mMostRecentUpdate(listing, (ListingUpdate) parcel.readParcelable(ShopHomePage$$Parcelable.class.getClassLoader()));
        if (parcel.readInt() == -1) {
            etsyMoney = null;
        } else {
            etsyMoney = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        Listing$$PackageHelper.accessListing$FS$mPrice(listing, etsyMoney);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        Listing$$PackageHelper.accessListing$FS$mHasCollections(listing, z);
        readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                arrayList.add((ListingOption) parcel.readParcelable(ShopHomePage$$Parcelable.class.getClassLoader()));
            }
            obj = arrayList;
        }
        Listing$$PackageHelper.accessListing$FS$mOptions(listing, list);
        if (parcel.readInt() < 0) {
            bool = null;
        } else {
            bool = Boolean.valueOf(parcel.readInt() == 1);
        }
        Listing$$PackageHelper.accessListing$FS$mIsFundOnEtsyCampaign(listing, bool);
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        Listing$$PackageHelper.accessListing$FS$mIsFavorite(listing, z2);
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
        Listing$$PackageHelper.accessListing$FS$mImages(listing, list);
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
        Listing$$PackageHelper.accessListing$FS$mShippingInfo(listing, list);
        Listing$$PackageHelper.accessListing$FS$mFundOnEtsyCampaign(listing, (FundOnEtsyCampaign) parcel.readParcelable(ShopHomePage$$Parcelable.class.getClassLoader()));
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
        Listing$$PackageHelper.accessListing$FS$mCollections(listing, list);
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
        Listing$$PackageHelper.accessListing$FS$mVariations(listing, list);
        Listing$$PackageHelper.accessListing$FS$mUrl(listing, parcel.readString());
        Listing$$PackageHelper.accessListing$FS$mTitle(listing, parcel.readString());
        Listing$$PackageHelper.accessListing$FS$mTransparentPriceMessage(listing, parcel.readString());
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        Listing$$PackageHelper.accessListing$FS$mUserId(listing, etsyId2);
        return listing;
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

    private ReviewResponse readcom_etsy_android_lib_models_ReviewResponse(Parcel parcel) {
        ReviewResponse reviewResponse = new ReviewResponse();
        ReviewResponse$$PackageHelper.accessReviewResponse$FS$mResponseMessage(reviewResponse, parcel.readString());
        ReviewResponse$$PackageHelper.accessReviewResponse$FS$mCreateDate(reviewResponse, (Date) parcel.readSerializable());
        return reviewResponse;
    }

    private ShopPolicy readcom_etsy_android_lib_models_apiv3_ShopPolicy(Parcel parcel) {
        boolean z = true;
        ShopPolicy shopPolicy = new ShopPolicy();
        shopPolicy.mWelcomeMessage = parcel.readString();
        shopPolicy.mAdditionalInformationMessage = parcel.readString();
        shopPolicy.mPaymentPolicy = parcel.readString();
        shopPolicy.mShippingPolicy = parcel.readString();
        shopPolicy.mRefundPolicy = parcel.readString();
        if (parcel.readInt() != 1) {
            z = false;
        }
        shopPolicy.mHasNoPolicies = z;
        shopPolicy.mUpdateDate = new au().m3269a(parcel);
        return shopPolicy;
    }

    private void writecom_etsy_android_lib_models_apiv3_ShopHomePage(ShopHomePage shopHomePage, Parcel parcel, int i) {
        if (shopHomePage.mFaqs == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_FAQs(shopHomePage.mFaqs, parcel, i);
        }
        if (shopHomePage.mFeaturedListings == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(shopHomePage.mFeaturedListings.size());
            for (ListingCard listingCard : shopHomePage.mFeaturedListings) {
                if (listingCard == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_ListingCard(listingCard, parcel, i);
                }
            }
        }
        if (shopHomePage.mShop == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_ShopV3(shopHomePage.mShop, parcel, i);
        }
        if (shopHomePage.mLocalMarkets == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(shopHomePage.mLocalMarkets.size());
            for (LocalMarket localMarket : shopHomePage.mLocalMarkets) {
                if (localMarket == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_LocalMarket(localMarket, parcel, i);
                }
            }
        }
        if (shopHomePage.mStructuredShopPolicies == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_StructuredShopPolicies(shopHomePage.mStructuredShopPolicies, parcel, i);
        }
        if (shopHomePage.mShopAbout == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_ShopAbout(shopHomePage.mShopAbout, parcel, i);
        }
        if (shopHomePage.mShopSections == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(shopHomePage.mShopSections.size());
            for (ShopSection shopSection : shopHomePage.mShopSections) {
                if (shopSection == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_ShopSection(shopSection, parcel, i);
                }
            }
        }
        if (shopHomePage.mSellerDetails == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_SellerDetails(shopHomePage.mSellerDetails, parcel, i);
        }
        if (shopHomePage.mShopReviews == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_ShopReviewsResult(shopHomePage.mShopReviews, parcel, i);
        }
        if (shopHomePage.mManufacturers == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(shopHomePage.mManufacturers.size());
            for (Manufacturer manufacturer : shopHomePage.mManufacturers) {
                if (manufacturer == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_Manufacturer(manufacturer, parcel, i);
                }
            }
        }
        if (shopHomePage.mShopListings == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(shopHomePage.mShopListings.size());
            for (ListingCard listingCard2 : shopHomePage.mShopListings) {
                if (listingCard2 == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_ListingCard(listingCard2, parcel, i);
                }
            }
        }
        if (shopHomePage.mShopPolicy == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_ShopPolicy(shopHomePage.mShopPolicy, parcel, i);
    }

    private void writecom_etsy_android_lib_models_apiv3_FAQs(FAQs fAQs, Parcel parcel, int i) {
        if (fAQs.mTranslationState == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_uikit_util_MachineTranslationViewState(fAQs.mTranslationState, parcel, i);
    }

    private void writecom_etsy_android_uikit_util_MachineTranslationViewState(MachineTranslationViewState machineTranslationViewState, Parcel parcel, int i) {
        parcel.writeInt(MachineTranslationViewState$$PackageHelper.m5558b(machineTranslationViewState));
        parcel.writeInt(MachineTranslationViewState$$PackageHelper.m5557a(machineTranslationViewState) ? 1 : 0);
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

    private void writecom_etsy_android_lib_models_apiv3_Rating(Rating rating, Parcel parcel, int i) {
        parcel.writeDouble(rating.mStars);
        parcel.writeInt(rating.mSellerFeedbackCount);
        parcel.writeInt(rating.mCount);
        parcel.writeDouble(rating.mRating);
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

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopPolicies(StructuredShopPolicies structuredShopPolicies, Parcel parcel, int i) {
        if (structuredShopPolicies.mShipping == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_StructuredShopShipping(structuredShopPolicies.mShipping, parcel, i);
        }
        if (structuredShopPolicies.mPrivacy == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_StructuredShopPrivacy(structuredShopPolicies.mPrivacy, parcel, i);
        }
        if (structuredShopPolicies.mPayments == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_StructuredShopPayments(structuredShopPolicies.mPayments, parcel, i);
        }
        if (structuredShopPolicies.mPoliciesId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(structuredShopPolicies.mPoliciesId, parcel, i);
        }
        parcel.writeString(structuredShopPolicies.mAdditionalTermsAndConditions);
        if (structuredShopPolicies.mRefunds == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_StructuredShopRefunds(structuredShopPolicies.mRefunds, parcel, i);
        }
        parcel.writeInt(structuredShopPolicies.mShopInGermany ? 1 : 0);
        if (structuredShopPolicies.mCharacterLimits == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_StructuredShopCharLimits(structuredShopPolicies.mCharacterLimits, parcel, i);
        }
        parcel.writeSerializable(structuredShopPolicies.mUpdateDate);
    }

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopShipping(StructuredShopShipping structuredShopShipping, Parcel parcel, int i) {
        int i2 = 0;
        parcel.writeInt(structuredShopShipping.mHasShippingUpgrades ? 1 : 0);
        parcel.writeString(structuredShopShipping.mProcessingTimeText);
        if (structuredShopShipping.mShipsInternational) {
            i2 = 1;
        }
        parcel.writeInt(i2);
        if (structuredShopShipping.mEstimates == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(structuredShopShipping.mEstimates.size());
        for (StructuredShopShippingEstimate structuredShopShippingEstimate : structuredShopShipping.mEstimates) {
            if (structuredShopShippingEstimate == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                m2380xdc035963(structuredShopShippingEstimate, parcel, i);
            }
        }
    }

    private void m2380xdc035963(StructuredShopShippingEstimate structuredShopShippingEstimate, Parcel parcel, int i) {
        parcel.writeString(structuredShopShippingEstimate.mType);
        if (structuredShopShippingEstimate.mCountryId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(structuredShopShippingEstimate.mCountryId.intValue());
        }
        parcel.writeString(structuredShopShippingEstimate.mDisplayName);
        parcel.writeInt(structuredShopShippingEstimate.mMax);
        parcel.writeInt(structuredShopShippingEstimate.mMin);
        parcel.writeString(structuredShopShippingEstimate.mUnit);
        parcel.writeString(structuredShopShippingEstimate.mRegionCode);
    }

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopPrivacy(StructuredShopPrivacy structuredShopPrivacy, Parcel parcel, int i) {
        parcel.writeString(structuredShopPrivacy.mTranslatedOtherText);
        parcel.writeString(structuredShopPrivacy.mHeader);
        if (structuredShopPrivacy.mOtherTranslationState == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_uikit_util_MachineTranslationViewState(structuredShopPrivacy.mOtherTranslationState, parcel, i);
        }
        parcel.writeSerializable(structuredShopPrivacy.mFlags);
    }

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopPayments(StructuredShopPayments structuredShopPayments, Parcel parcel, int i) {
        if (structuredShopPayments.mManualPaymentMethods == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(structuredShopPayments.mManualPaymentMethods.size());
            for (String writeString : structuredShopPayments.mManualPaymentMethods) {
                parcel.writeString(writeString);
            }
        }
        parcel.writeInt(structuredShopPayments.mAcceptsDirectCheckout ? 1 : 0);
        if (structuredShopPayments.mProtectedPaymentMethods == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(structuredShopPayments.mProtectedPaymentMethods.size());
            for (String writeString2 : structuredShopPayments.mProtectedPaymentMethods) {
                parcel.writeString(writeString2);
            }
        }
        if (structuredShopPayments.mAcceptedPaymentMethods == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(structuredShopPayments.mAcceptedPaymentMethods.size());
        for (String writeString22 : structuredShopPayments.mAcceptedPaymentMethods) {
            parcel.writeString(writeString22);
        }
    }

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopRefunds(StructuredShopRefunds structuredShopRefunds, Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeString(structuredShopRefunds.mCancellationWindowType);
        parcel.writeString(structuredShopRefunds.mAcceptedSummaryString);
        parcel.writeInt(structuredShopRefunds.mCancelWithinHours);
        parcel.writeInt(structuredShopRefunds.mReturnWithinDays);
        parcel.writeInt(structuredShopRefunds.mAcceptsCancellations ? 1 : 0);
        if (structuredShopRefunds.mExchanges) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (structuredShopRefunds.mNonRefundableItems == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(structuredShopRefunds.mNonRefundableItems.size());
            for (NonRefundableItem writeSerializable : structuredShopRefunds.mNonRefundableItems) {
                parcel.writeSerializable(writeSerializable);
            }
        }
        parcel.writeInt(structuredShopRefunds.mContactWithinDays);
        parcel.writeString(structuredShopRefunds.mNotAcceptedSummaryString);
        if (!structuredShopRefunds.mAcceptsReturns) {
            i3 = 0;
        }
        parcel.writeInt(i3);
    }

    private void writecom_etsy_android_lib_models_apiv3_StructuredShopCharLimits(StructuredShopCharLimits structuredShopCharLimits, Parcel parcel, int i) {
        parcel.writeInt(structuredShopCharLimits.mPrivacyPolicyOtherLimit);
    }

    private void writecom_etsy_android_lib_models_ShopAbout(ShopAbout shopAbout, Parcel parcel, int i) {
        if (ShopAbout$$PackageHelper.accessShopAbout$FG$mImages(shopAbout) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(ShopAbout$$PackageHelper.accessShopAbout$FG$mImages(shopAbout).size());
            for (ShopAboutImage shopAboutImage : ShopAbout$$PackageHelper.accessShopAbout$FG$mImages(shopAbout)) {
                if (shopAboutImage == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_ShopAboutImage(shopAboutImage, parcel, i);
                }
            }
        }
        parcel.writeSerializable(ShopAbout$$PackageHelper.accessShopAbout$FG$mLinks(shopAbout));
        if (ShopAbout$$PackageHelper.accessShopAbout$FG$mVideos(shopAbout) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(ShopAbout$$PackageHelper.accessShopAbout$FG$mVideos(shopAbout).size());
            for (ShopAboutVideo shopAboutVideo : ShopAbout$$PackageHelper.accessShopAbout$FG$mVideos(shopAbout)) {
                if (shopAboutVideo == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_ShopAboutVideo(shopAboutVideo, parcel, i);
                }
            }
        }
        parcel.writeString(ShopAbout$$PackageHelper.accessShopAbout$FG$mUrl(shopAbout));
        if (ShopAbout$$PackageHelper.accessShopAbout$FG$mMembers(shopAbout) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(ShopAbout$$PackageHelper.accessShopAbout$FG$mMembers(shopAbout).size());
            for (ShopAboutMember shopAboutMember : ShopAbout$$PackageHelper.accessShopAbout$FG$mMembers(shopAbout)) {
                if (shopAboutMember == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_ShopAboutMember(shopAboutMember, parcel, i);
                }
            }
        }
        parcel.writeString(ShopAbout$$PackageHelper.accessShopAbout$FG$mStory(shopAbout));
        parcel.writeString(ShopAbout$$PackageHelper.accessShopAbout$FG$mStoryHeadline(shopAbout));
    }

    private void writecom_etsy_android_lib_models_ShopAboutImage(ShopAboutImage shopAboutImage, Parcel parcel, int i) {
        parcel.writeString(ShopAboutImage$$PackageHelper.accessShopAboutImage$FG$mKey(shopAboutImage));
        parcel.writeString(ShopAboutImage$$PackageHelper.accessShopAboutImage$FG$mImageUrl545xN(shopAboutImage));
        parcel.writeInt(ShopAboutImage$$PackageHelper.accessShopAboutImage$FG$mRank(shopAboutImage));
        if (ShopAboutImage$$PackageHelper.accessShopAboutImage$FG$mSources(shopAboutImage) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(ShopAboutImage$$PackageHelper.accessShopAboutImage$FG$mSources(shopAboutImage).size());
            for (Source source : ShopAboutImage$$PackageHelper.accessShopAboutImage$FG$mSources(shopAboutImage)) {
                if (source == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_Image$Source(source, parcel, i);
                }
            }
        }
        parcel.writeString(ShopAboutImage$$PackageHelper.accessShopAboutImage$FG$mUrl(shopAboutImage));
        parcel.writeString(ShopAboutImage$$PackageHelper.accessShopAboutImage$FG$mImageUrl178x178(shopAboutImage));
        parcel.writeString(ShopAboutImage$$PackageHelper.accessShopAboutImage$FG$mImageUrl760xN(shopAboutImage));
        if (ShopAboutImage$$PackageHelper.accessShopAboutImage$FG$mImageId(shopAboutImage) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(ShopAboutImage$$PackageHelper.accessShopAboutImage$FG$mImageId(shopAboutImage), parcel, i);
        }
        if (ShopAboutImage$$PackageHelper.accessShopAboutImage$FG$mImage(shopAboutImage) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Image(ShopAboutImage$$PackageHelper.accessShopAboutImage$FG$mImage(shopAboutImage), parcel, i);
        }
        parcel.writeString(ShopAboutImage$$PackageHelper.accessShopAboutImage$FG$mCaption(shopAboutImage));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl570xN(shopAboutImage));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl224xN(shopAboutImage));
        parcel.writeDouble(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$PORTRAIT_HEIGHT_RATIO(shopAboutImage));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl680x540(shopAboutImage));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl75x75(shopAboutImage));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl170x135(shopAboutImage));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrlFullxFull(shopAboutImage));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl300x300(shopAboutImage));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl340x270(shopAboutImage));
    }

    private void writecom_etsy_android_lib_models_ShopAboutVideo(ShopAboutVideo shopAboutVideo, Parcel parcel, int i) {
        parcel.writeString(ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FG$mUrl(shopAboutVideo));
        if (ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FG$mShopId(shopAboutVideo) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FG$mShopId(shopAboutVideo), parcel, i);
        }
        if (ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FG$mThumbnail(shopAboutVideo) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_BaseModelImageWrapper(ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FG$mThumbnail(shopAboutVideo), parcel, i);
        }
        parcel.writeString(ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FG$mProviderStatus(shopAboutVideo));
        if (ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FG$mVideoId(shopAboutVideo) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(ShopAboutVideo$$PackageHelper.accessShopAboutVideo$FG$mVideoId(shopAboutVideo), parcel, i);
    }

    private void writecom_etsy_android_lib_models_BaseModelImageWrapper(BaseModelImageWrapper baseModelImageWrapper, Parcel parcel, int i) {
        if (BaseModelImageWrapper$$PackageHelper.accessBaseModelImageWrapper$FG$mImage(baseModelImageWrapper) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Image(BaseModelImageWrapper$$PackageHelper.accessBaseModelImageWrapper$FG$mImage(baseModelImageWrapper), parcel, i);
        }
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl570xN(baseModelImageWrapper));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl224xN(baseModelImageWrapper));
        parcel.writeDouble(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$PORTRAIT_HEIGHT_RATIO(baseModelImageWrapper));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl680x540(baseModelImageWrapper));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl75x75(baseModelImageWrapper));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl170x135(baseModelImageWrapper));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrlFullxFull(baseModelImageWrapper));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl300x300(baseModelImageWrapper));
        parcel.writeString(BaseModelImage$$PackageHelper.accessBaseModelImage$FG$mUrl340x270(baseModelImageWrapper));
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

    private void writecom_etsy_android_lib_models_ShopSection(ShopSection shopSection, Parcel parcel, int i) {
        parcel.writeInt(ShopSection$$PackageHelper.accessShopSection$FG$mRank(shopSection));
        parcel.writeInt(ShopSection$$PackageHelper.accessShopSection$FG$mListingActiveCount(shopSection));
        parcel.writeString(ShopSection$$PackageHelper.accessShopSection$FG$mTitle(shopSection));
        new as().m3266a(ShopSection$$PackageHelper.accessShopSection$FG$mDisplayLabel(shopSection), parcel);
        if (ShopSection$$PackageHelper.accessShopSection$FG$mShopSectionId(shopSection) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(ShopSection$$PackageHelper.accessShopSection$FG$mShopSectionId(shopSection), parcel, i);
    }

    private void writecom_etsy_android_lib_models_apiv3_SellerDetails(SellerDetails sellerDetails, Parcel parcel, int i) {
        parcel.writeString(sellerDetails.mPhone);
        parcel.writeString(sellerDetails.mFormattedString);
        parcel.writeString(sellerDetails.mCity);
        parcel.writeString(sellerDetails.mCountry);
        parcel.writeString(sellerDetails.mState);
        parcel.writeString(sellerDetails.mFirstName);
        parcel.writeString(sellerDetails.mAddressLine2);
        parcel.writeString(sellerDetails.mAddressLine1);
        parcel.writeString(sellerDetails.mPostalCode);
        parcel.writeString(sellerDetails.mVatNumber);
        parcel.writeString(sellerDetails.mLastName);
        parcel.writeString(sellerDetails.mEmail);
    }

    private void writecom_etsy_android_lib_models_apiv3_ShopReviewsResult(ShopReviewsResult shopReviewsResult, Parcel parcel, int i) {
        if (shopReviewsResult.mReviews == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(shopReviewsResult.mReviews.size());
            for (ReceiptReview receiptReview : shopReviewsResult.mReviews) {
                if (receiptReview == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_ReceiptReview(receiptReview, parcel, i);
                }
            }
        }
        parcel.writeInt(shopReviewsResult.mCount);
    }

    private void writecom_etsy_android_lib_models_ReceiptReview(ReceiptReview receiptReview, Parcel parcel, int i) {
        if (ReceiptReview$$PackageHelper.accessReceiptReview$FG$mReviews(receiptReview) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(ReceiptReview$$PackageHelper.accessReceiptReview$FG$mReviews(receiptReview).size());
            for (Review review : ReceiptReview$$PackageHelper.accessReceiptReview$FG$mReviews(receiptReview)) {
                if (review == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_Review(review, parcel, i);
                }
            }
        }
        if (ReceiptReview$$PackageHelper.accessReceiptReview$FG$mReceiptId(receiptReview) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(ReceiptReview$$PackageHelper.accessReceiptReview$FG$mReceiptId(receiptReview), parcel, i);
        }
        parcel.writeSerializable(ReceiptReview$$PackageHelper.accessReceiptReview$FG$mDate(receiptReview));
        parcel.writeString(ReceiptReview$$PackageHelper.accessReceiptReview$FG$mUserLoginName(receiptReview));
        parcel.writeString(ReceiptReview$$PackageHelper.accessReceiptReview$FG$mUserAvatarUrl(receiptReview));
        parcel.writeString(ReceiptReview$$PackageHelper.accessReceiptReview$FG$mUserRealName(receiptReview));
        if (ReceiptReview$$PackageHelper.accessReceiptReview$FG$mUserId(receiptReview) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(ReceiptReview$$PackageHelper.accessReceiptReview$FG$mUserId(receiptReview), parcel, i);
    }

    private void writecom_etsy_android_lib_models_Review(Review review, Parcel parcel, int i) {
        if (Review$$PackageHelper.accessReview$FG$mListingImage(review) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_ListingImage(Review$$PackageHelper.accessReview$FG$mListingImage(review), parcel, i);
        }
        parcel.writeString(Review$$PackageHelper.accessReview$FG$mTranslatedContent(review));
        if (Review$$PackageHelper.accessReview$FG$mListing(review) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_Listing(Review$$PackageHelper.accessReview$FG$mListing(review), parcel, i);
        }
        if (Review$$PackageHelper.accessReview$FG$mListingId(review) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(Review$$PackageHelper.accessReview$FG$mListingId(review), parcel, i);
        }
        if (Review$$PackageHelper.accessReview$FG$mAppreciationPhoto(review) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_AppreciationPhoto(Review$$PackageHelper.accessReview$FG$mAppreciationPhoto(review), parcel, i);
        }
        if (Review$$PackageHelper.accessReview$FG$mTransactionId(review) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(Review$$PackageHelper.accessReview$FG$mTransactionId(review), parcel, i);
        }
        parcel.writeString(Review$$PackageHelper.accessReview$FG$mListingImageUrl(review));
        if (Review$$PackageHelper.accessReview$FG$mResponse(review) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_ReviewResponse(Review$$PackageHelper.accessReview$FG$mResponse(review), parcel, i);
        }
        parcel.writeString(Review$$PackageHelper.accessReview$FG$mReviewMessage(review));
        parcel.writeInt(Review$$PackageHelper.accessReview$FG$mIsListingDisplayable(review) ? 1 : 0);
        if (Review$$PackageHelper.accessReview$FG$mReviewTranslationState(review) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_uikit_util_MachineTranslationViewState(Review$$PackageHelper.accessReview$FG$mReviewTranslationState(review), parcel, i);
        }
        parcel.writeString(Review$$PackageHelper.accessReview$FG$mListingTitle(review));
        parcel.writeInt(Review$$PackageHelper.accessReview$FG$mRating(review));
        parcel.writeString(Review$$PackageHelper.accessReview$FG$mReviewLanguage(review));
    }

    private void writecom_etsy_android_lib_models_Listing(Listing listing, Parcel parcel, int i) {
        int i2;
        int i3 = 0;
        if (Listing$$PackageHelper.accessListing$FG$mBuyerDisplayPrice(listing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(Listing$$PackageHelper.accessListing$FG$mBuyerDisplayPrice(listing), parcel, i);
        }
        parcel.writeSerializable(Listing$$PackageHelper.accessListing$FG$mShop(listing));
        if (Listing$$PackageHelper.accessListing$FG$mListingId(listing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(Listing$$PackageHelper.accessListing$FG$mListingId(listing), parcel, i);
        }
        parcel.writeSerializable(Listing$$PackageHelper.accessListing$FG$mPaymentInfo(listing));
        if (Listing$$PackageHelper.accessListing$FG$mManufacturers(listing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mManufacturers(listing).size());
            for (Manufacturer manufacturer : Listing$$PackageHelper.accessListing$FG$mManufacturers(listing)) {
                if (manufacturer == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_Manufacturer(manufacturer, parcel, i);
                }
            }
        }
        parcel.writeSerializable(Listing$$PackageHelper.accessListing$FG$mCreationDate(listing));
        parcel.writeSerializable(Listing$$PackageHelper.accessListing$FG$mListingVideos(listing));
        parcel.writeString(Listing$$PackageHelper.accessListing$FG$mConvertedPrice(listing));
        parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mViews(listing));
        parcel.writeString(Listing$$PackageHelper.accessListing$FG$mDescription(listing));
        parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mQuantity(listing));
        parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mProcessingMax(listing));
        parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mProcessingMin(listing));
        parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mNumFavorers(listing));
        parcel.writeString(Listing$$PackageHelper.accessListing$FG$mState(listing));
        parcel.writeString(Listing$$PackageHelper.accessListing$FG$mOriginalLanguage(listing));
        if (Listing$$PackageHelper.accessListing$FG$mOverview(listing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mOverview(listing).size());
            for (String writeString : Listing$$PackageHelper.accessListing$FG$mOverview(listing)) {
                parcel.writeString(writeString);
            }
        }
        if (Listing$$PackageHelper.accessListing$FG$mIsPrivate(listing)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeString(Listing$$PackageHelper.accessListing$FG$mConvertedCurrencyCode(listing));
        if (Listing$$PackageHelper.accessListing$FG$mIsVATInclusive(listing)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (Listing$$PackageHelper.accessListing$FG$mIsDigitalDownload(listing)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (Listing$$PackageHelper.accessListing$FG$mShouldAutoRenew(listing)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeSerializable(Listing$$PackageHelper.accessListing$FG$mSearchAdsMetadata(listing));
        if (Listing$$PackageHelper.accessListing$FG$mOfferings(listing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_OfferingResponse(Listing$$PackageHelper.accessListing$FG$mOfferings(listing), parcel, i);
        }
        parcel.writeString(Listing$$PackageHelper.accessListing$FG$mLanguage(listing));
        parcel.writeParcelable(Listing$$PackageHelper.accessListing$FG$mMostRecentUpdate(listing), i);
        if (Listing$$PackageHelper.accessListing$FG$mPrice(listing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(Listing$$PackageHelper.accessListing$FG$mPrice(listing), parcel, i);
        }
        if (Listing$$PackageHelper.accessListing$FG$mHasCollections(listing)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (Listing$$PackageHelper.accessListing$FG$mOptions(listing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mOptions(listing).size());
            for (ListingOption writeParcelable : Listing$$PackageHelper.accessListing$FG$mOptions(listing)) {
                parcel.writeParcelable(writeParcelable, i);
            }
        }
        if (Listing$$PackageHelper.accessListing$FG$mIsFundOnEtsyCampaign(listing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mIsFundOnEtsyCampaign(listing).booleanValue() ? 1 : 0);
        }
        if (Listing$$PackageHelper.accessListing$FG$mIsFavorite(listing)) {
            i3 = 1;
        }
        parcel.writeInt(i3);
        if (Listing$$PackageHelper.accessListing$FG$mImages(listing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mImages(listing).size());
            for (ListingImage listingImage : Listing$$PackageHelper.accessListing$FG$mImages(listing)) {
                if (listingImage == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_ListingImage(listingImage, parcel, i);
                }
            }
        }
        if (Listing$$PackageHelper.accessListing$FG$mShippingInfo(listing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mShippingInfo(listing).size());
            for (ShippingInfo writeSerializable : Listing$$PackageHelper.accessListing$FG$mShippingInfo(listing)) {
                parcel.writeSerializable(writeSerializable);
            }
        }
        parcel.writeParcelable(Listing$$PackageHelper.accessListing$FG$mFundOnEtsyCampaign(listing), i);
        if (Listing$$PackageHelper.accessListing$FG$mCollections(listing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mCollections(listing).size());
            for (Collection writeSerializable2 : Listing$$PackageHelper.accessListing$FG$mCollections(listing)) {
                parcel.writeSerializable(writeSerializable2);
            }
        }
        if (Listing$$PackageHelper.accessListing$FG$mVariations(listing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(Listing$$PackageHelper.accessListing$FG$mVariations(listing).size());
            for (Variation variation : Listing$$PackageHelper.accessListing$FG$mVariations(listing)) {
                if (variation == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_Variation(variation, parcel, i);
                }
            }
        }
        parcel.writeString(Listing$$PackageHelper.accessListing$FG$mUrl(listing));
        parcel.writeString(Listing$$PackageHelper.accessListing$FG$mTitle(listing));
        parcel.writeString(Listing$$PackageHelper.accessListing$FG$mTransparentPriceMessage(listing));
        if (Listing$$PackageHelper.accessListing$FG$mUserId(listing) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(Listing$$PackageHelper.accessListing$FG$mUserId(listing), parcel, i);
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

    private void writecom_etsy_android_lib_models_ReviewResponse(ReviewResponse reviewResponse, Parcel parcel, int i) {
        parcel.writeString(ReviewResponse$$PackageHelper.accessReviewResponse$FG$mResponseMessage(reviewResponse));
        parcel.writeSerializable(ReviewResponse$$PackageHelper.accessReviewResponse$FG$mCreateDate(reviewResponse));
    }

    private void writecom_etsy_android_lib_models_apiv3_ShopPolicy(ShopPolicy shopPolicy, Parcel parcel, int i) {
        parcel.writeString(shopPolicy.mWelcomeMessage);
        parcel.writeString(shopPolicy.mAdditionalInformationMessage);
        parcel.writeString(shopPolicy.mPaymentPolicy);
        parcel.writeString(shopPolicy.mShippingPolicy);
        parcel.writeString(shopPolicy.mRefundPolicy);
        parcel.writeInt(shopPolicy.mHasNoPolicies ? 1 : 0);
        new au().m3270a(shopPolicy.mUpdateDate, parcel);
    }

    public int describeContents() {
        return 0;
    }

    public ShopHomePage getParcel() {
        return this.shopHomePage$$0;
    }
}
