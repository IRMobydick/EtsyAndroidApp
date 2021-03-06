package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.core.EtsyMoney$$PackageHelper;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.BaseModelImage$$PackageHelper;
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
import com.etsy.android.lib.models.ReceiptReview;
import com.etsy.android.lib.models.ReceiptReview$$PackageHelper;
import com.etsy.android.lib.models.Review;
import com.etsy.android.lib.models.Review$$PackageHelper;
import com.etsy.android.lib.models.ReviewResponse;
import com.etsy.android.lib.models.ReviewResponse$$PackageHelper;
import com.etsy.android.lib.models.SearchAdsMetadata;
import com.etsy.android.lib.models.ShippingInfo;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.ShortenedUrl;
import com.etsy.android.lib.models.Variation;
import com.etsy.android.lib.models.Variation$$PackageHelper;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import com.etsy.android.lib.util.au;
import com.etsy.android.uikit.util.MachineTranslationViewState;
import com.etsy.android.uikit.util.MachineTranslationViewState$$PackageHelper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import org.parceler.ax;

public class ShopReviewsResult$$Parcelable implements android.os.Parcelable, ax<ShopReviewsResult> {
    public static final bc CREATOR;
    private ShopReviewsResult shopReviewsResult$$0;

    static {
        CREATOR = new bc();
    }

    public ShopReviewsResult$$Parcelable(Parcel parcel) {
        ShopReviewsResult shopReviewsResult;
        if (parcel.readInt() == -1) {
            shopReviewsResult = null;
        } else {
            shopReviewsResult = readcom_etsy_android_lib_models_apiv3_ShopReviewsResult(parcel);
        }
        this.shopReviewsResult$$0 = shopReviewsResult;
    }

    public ShopReviewsResult$$Parcelable(ShopReviewsResult shopReviewsResult) {
        this.shopReviewsResult$$0 = shopReviewsResult;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.shopReviewsResult$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_ShopReviewsResult(this.shopReviewsResult$$0, parcel, i);
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

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
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
        Listing$$PackageHelper.accessListing$FS$mMostRecentUpdate(listing, (ListingUpdate) parcel.readParcelable(ShopReviewsResult$$Parcelable.class.getClassLoader()));
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
                arrayList.add((ListingOption) parcel.readParcelable(ShopReviewsResult$$Parcelable.class.getClassLoader()));
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
        Listing$$PackageHelper.accessListing$FS$mFundOnEtsyCampaign(listing, (FundOnEtsyCampaign) parcel.readParcelable(ShopReviewsResult$$Parcelable.class.getClassLoader()));
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

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
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

    private void writecom_etsy_android_uikit_util_MachineTranslationViewState(MachineTranslationViewState machineTranslationViewState, Parcel parcel, int i) {
        parcel.writeInt(MachineTranslationViewState$$PackageHelper.m5558b(machineTranslationViewState));
        parcel.writeInt(MachineTranslationViewState$$PackageHelper.m5557a(machineTranslationViewState) ? 1 : 0);
    }

    public int describeContents() {
        return 0;
    }

    public ShopReviewsResult getParcel() {
        return this.shopReviewsResult$$0;
    }
}
