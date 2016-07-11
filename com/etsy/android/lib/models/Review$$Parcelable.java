package com.etsy.android.lib.models;

import android.os.Parcel;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.core.EtsyMoney$$PackageHelper;
import com.etsy.android.lib.models.apiv3.AppreciationPhoto;
import com.etsy.android.lib.models.apiv3.AppreciationPhoto$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.FormattedMoney;
import com.etsy.android.lib.models.apiv3.FormattedMoney$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Money;
import com.etsy.android.lib.models.apiv3.Money$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Offering;
import com.etsy.android.lib.models.apiv3.Offering$$PackageHelper;
import com.etsy.android.lib.models.apiv3.OfferingOption;
import com.etsy.android.lib.models.apiv3.OfferingOption$$PackageHelper;
import com.etsy.android.lib.models.apiv3.OfferingPrice;
import com.etsy.android.lib.models.apiv3.OfferingPrice$$PackageHelper;
import com.etsy.android.lib.models.apiv3.OfferingRangeSelect;
import com.etsy.android.lib.models.apiv3.OfferingRangeSelect$$PackageHelper;
import com.etsy.android.lib.models.apiv3.OfferingResponse;
import com.etsy.android.lib.models.apiv3.OfferingResponse$$PackageHelper;
import com.etsy.android.lib.models.apiv3.OfferingSelect;
import com.etsy.android.lib.models.apiv3.OfferingSelect$$PackageHelper;
import com.etsy.android.lib.models.apiv3.OfferingUi;
import com.etsy.android.lib.models.apiv3.OfferingUi$$PackageHelper;
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

public class Review$$Parcelable implements android.os.Parcelable, ax<Review> {
    public static final aa CREATOR;
    private Review review$$18;

    static {
        CREATOR = new aa();
    }

    public Review$$Parcelable(Parcel parcel) {
        Review review;
        if (parcel.readInt() == -1) {
            review = null;
        } else {
            review = readcom_etsy_android_lib_models_Review(parcel);
        }
        this.review$$18 = review;
    }

    public Review$$Parcelable(Review review) {
        this.review$$18 = review;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.review$$18 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_Review(this.review$$18, parcel, i);
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
        review.mListingImage = listingImage;
        review.mTranslatedContent = parcel.readString();
        if (parcel.readInt() == -1) {
            listing = null;
        } else {
            listing = readcom_etsy_android_lib_models_Listing(parcel);
        }
        review.mListing = listing;
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        review.mListingId = etsyId;
        if (parcel.readInt() == -1) {
            appreciationPhoto = null;
        } else {
            appreciationPhoto = readcom_etsy_android_lib_models_apiv3_AppreciationPhoto(parcel);
        }
        review.mAppreciationPhoto = appreciationPhoto;
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        review.mTransactionId = etsyId;
        review.mListingImageUrl = parcel.readString();
        if (parcel.readInt() == -1) {
            reviewResponse = null;
        } else {
            reviewResponse = readcom_etsy_android_lib_models_ReviewResponse(parcel);
        }
        review.mResponse = reviewResponse;
        review.mReviewMessage = parcel.readString();
        review.mIsListingDisplayable = parcel.readInt() == 1;
        if (parcel.readInt() != -1) {
            machineTranslationViewState = readcom_etsy_android_uikit_util_MachineTranslationViewState(parcel);
        }
        review.mReviewTranslationState = machineTranslationViewState;
        review.mListingTitle = parcel.readString();
        review.mRating = parcel.readInt();
        review.mReviewLanguage = parcel.readString();
        return review;
    }

    private ListingImage readcom_etsy_android_lib_models_ListingImage(Parcel parcel) {
        EtsyId etsyId;
        ListingImage listingImage = new ListingImage();
        listingImage.mFullWidth = parcel.readInt();
        listingImage.mHue = parcel.readInt();
        listingImage.mPostCalculatedColor = parcel.readInt();
        listingImage.mSaturation = parcel.readInt();
        listingImage.mHexColor = parcel.readInt();
        listingImage.mRank = parcel.readInt();
        listingImage.mFullHeight = parcel.readInt();
        listingImage.mRed = parcel.readInt();
        listingImage.mBlue = parcel.readInt();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        listingImage.mImageId = etsyId;
        listingImage.mGreen = parcel.readInt();
        listingImage.mUrl570xN = parcel.readString();
        listingImage.mUrl224xN = parcel.readString();
        listingImage.PORTRAIT_HEIGHT_RATIO = parcel.readDouble();
        listingImage.mUrl680x540 = parcel.readString();
        listingImage.mUrl75x75 = parcel.readString();
        listingImage.mUrl170x135 = parcel.readString();
        listingImage.mUrlFullxFull = parcel.readString();
        listingImage.mUrl300x300 = parcel.readString();
        listingImage.mUrl340x270 = parcel.readString();
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
        listing.mBuyerDisplayPrice = etsyMoney;
        listing.mShop = (Shop) parcel.readSerializable();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        listing.mListingId = etsyId;
        listing.mPaymentInfo = (PaymentTemplate) parcel.readSerializable();
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
        listing.mManufacturers = list;
        listing.mCreationDate = (Date) parcel.readSerializable();
        listing.mListingVideos = (ListingVideos) parcel.readSerializable();
        listing.mConvertedPrice = parcel.readString();
        listing.mViews = parcel.readInt();
        listing.mDescription = parcel.readString();
        listing.mQuantity = parcel.readInt();
        listing.mProcessingMax = parcel.readInt();
        listing.mProcessingMin = parcel.readInt();
        listing.mNumFavorers = parcel.readInt();
        listing.mState = parcel.readString();
        listing.mOriginalLanguage = parcel.readString();
        i = parcel.readInt();
        if (i < 0) {
            list = null;
        } else {
            list = new ArrayList();
            for (int i4 = 0; i4 < i; i4++) {
                list.add(parcel.readString());
            }
        }
        listing.mOverview = list;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        listing.mIsPrivate = z;
        listing.mConvertedCurrencyCode = parcel.readString();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        listing.mIsVATInclusive = z;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        listing.mIsDigitalDownload = z;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        listing.mShouldAutoRenew = z;
        listing.mSearchAdsMetadata = (SearchAdsMetadata) parcel.readSerializable();
        if (parcel.readInt() == -1) {
            offeringResponse = null;
        } else {
            offeringResponse = readcom_etsy_android_lib_models_apiv3_OfferingResponse(parcel);
        }
        listing.mOfferings = offeringResponse;
        listing.mLanguage = parcel.readString();
        listing.mMostRecentUpdate = (ListingUpdate) parcel.readParcelable(Review$$Parcelable.class.getClassLoader());
        if (parcel.readInt() == -1) {
            etsyMoney = null;
        } else {
            etsyMoney = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        listing.mPrice = etsyMoney;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        listing.mHasCollections = z;
        readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                arrayList.add((ListingOption) parcel.readParcelable(Review$$Parcelable.class.getClassLoader()));
            }
            obj = arrayList;
        }
        listing.mOptions = list;
        if (parcel.readInt() < 0) {
            bool = null;
        } else {
            bool = Boolean.valueOf(parcel.readInt() == 1);
        }
        listing.mIsFundOnEtsyCampaign = bool;
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        listing.mIsFavorite = z2;
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
        listing.mImages = list;
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
        listing.mShippingInfo = list;
        listing.mFundOnEtsyCampaign = (FundOnEtsyCampaign) parcel.readParcelable(Review$$Parcelable.class.getClassLoader());
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
        listing.mCollections = list;
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
        listing.mVariations = list;
        listing.mUrl = parcel.readString();
        listing.mTitle = parcel.readString();
        listing.mTransparentPriceMessage = parcel.readString();
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        listing.mUserId = etsyId2;
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
        manufacturer.mLocation = parcel.readString();
        manufacturer.mName = parcel.readString();
        manufacturer.mDescription = parcel.readString();
        return manufacturer;
    }

    private OfferingResponse readcom_etsy_android_lib_models_apiv3_OfferingResponse(Parcel parcel) {
        OfferingUi offeringUi;
        OfferingPrice offeringPrice;
        Offering offering = null;
        OfferingResponse offeringResponse = new OfferingResponse();
        OfferingResponse$$PackageHelper.accessOfferingResponse$FS$mMinQuantity(offeringResponse, parcel.readInt());
        if (parcel.readInt() == -1) {
            offeringUi = null;
        } else {
            offeringUi = readcom_etsy_android_lib_models_apiv3_OfferingUi(parcel);
        }
        OfferingResponse$$PackageHelper.accessOfferingResponse$FS$mUi(offeringResponse, offeringUi);
        if (parcel.readInt() == -1) {
            offeringPrice = null;
        } else {
            offeringPrice = readcom_etsy_android_lib_models_apiv3_OfferingPrice(parcel);
        }
        OfferingResponse$$PackageHelper.accessOfferingResponse$FS$mMinPrice(offeringResponse, offeringPrice);
        if (parcel.readInt() == -1) {
            offeringPrice = null;
        } else {
            offeringPrice = readcom_etsy_android_lib_models_apiv3_OfferingPrice(parcel);
        }
        OfferingResponse$$PackageHelper.accessOfferingResponse$FS$mMaxPrice(offeringResponse, offeringPrice);
        if (parcel.readInt() != -1) {
            offering = readcom_etsy_android_lib_models_apiv3_Offering(parcel);
        }
        OfferingResponse$$PackageHelper.accessOfferingResponse$FS$mOffering(offeringResponse, offering);
        OfferingResponse$$PackageHelper.accessOfferingResponse$FS$mMaxQuantity(offeringResponse, parcel.readInt());
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
        OfferingUi$$PackageHelper.accessOfferingUi$FS$mPrice(offeringUi, formattedMoney);
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
        OfferingUi$$PackageHelper.accessOfferingUi$FS$mSelects(offeringUi, list);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        OfferingUi$$PackageHelper.accessOfferingUi$FS$mHasVariableQuantity(offeringUi, z);
        if (parcel.readInt() != -1) {
            offeringRangeSelect = readcom_etsy_android_lib_models_apiv3_OfferingRangeSelect(parcel);
        }
        OfferingUi$$PackageHelper.accessOfferingUi$FS$mQuantity(offeringUi, offeringRangeSelect);
        return offeringUi;
    }

    private FormattedMoney readcom_etsy_android_lib_models_apiv3_FormattedMoney(Parcel parcel) {
        List list = null;
        FormattedMoney formattedMoney = new FormattedMoney();
        FormattedMoney$$PackageHelper.accessFormattedMoney$FS$mFormatString(formattedMoney, parcel.readString());
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
        FormattedMoney$$PackageHelper.accessFormattedMoney$FS$mArguments(formattedMoney, list);
        return formattedMoney;
    }

    private Money readcom_etsy_android_lib_models_apiv3_Money(Parcel parcel) {
        Money money = new Money();
        Money$$PackageHelper.accessMoney$FS$mDivisor(money, parcel.readInt());
        Money$$PackageHelper.accessMoney$FS$mCurrencyCode(money, parcel.readString());
        Money$$PackageHelper.accessMoney$FS$mAmount(money, parcel.readString());
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
        OfferingSelect$$PackageHelper.accessOfferingSelect$FS$mDefaultOption(offeringSelect, offeringOption);
        OfferingSelect$$PackageHelper.accessOfferingSelect$FS$mPrompt(offeringSelect, parcel.readString());
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
        OfferingSelect$$PackageHelper.accessOfferingSelect$FS$mOptions(offeringSelect, list);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        OfferingSelect$$PackageHelper.accessOfferingSelect$FS$mEnabled(offeringSelect, z);
        OfferingSelect$$PackageHelper.accessOfferingSelect$FS$mLabel(offeringSelect, parcel.readString());
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
        OfferingOption$$PackageHelper.accessOfferingOption$FS$mValue(offeringOption, etsyId);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        OfferingOption$$PackageHelper.accessOfferingOption$FS$mSelected(offeringOption, z);
        if (parcel.readInt() != -1) {
            formattedMoney = readcom_etsy_android_lib_models_apiv3_FormattedMoney(parcel);
        }
        OfferingOption$$PackageHelper.accessOfferingOption$FS$mDisplayValue(offeringOption, formattedMoney);
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        OfferingOption$$PackageHelper.accessOfferingOption$FS$mEnabled(offeringOption, z2);
        return offeringOption;
    }

    private OfferingRangeSelect readcom_etsy_android_lib_models_apiv3_OfferingRangeSelect(Parcel parcel) {
        boolean z = true;
        OfferingRangeSelect offeringRangeSelect = new OfferingRangeSelect();
        OfferingRangeSelect$$PackageHelper.accessOfferingRangeSelect$FS$mMax(offeringRangeSelect, parcel.readInt());
        OfferingRangeSelect$$PackageHelper.accessOfferingRangeSelect$FS$mMin(offeringRangeSelect, parcel.readInt());
        if (parcel.readInt() != 1) {
            z = false;
        }
        OfferingRangeSelect$$PackageHelper.accessOfferingRangeSelect$FS$mEnabled(offeringRangeSelect, z);
        OfferingRangeSelect$$PackageHelper.accessOfferingRangeSelect$FS$mStep(offeringRangeSelect, parcel.readInt());
        OfferingRangeSelect$$PackageHelper.accessOfferingRangeSelect$FS$mLabel(offeringRangeSelect, parcel.readString());
        return offeringRangeSelect;
    }

    private OfferingPrice readcom_etsy_android_lib_models_apiv3_OfferingPrice(Parcel parcel) {
        OfferingPrice offeringPrice = new OfferingPrice();
        OfferingPrice$$PackageHelper.accessOfferingPrice$FS$mCurrencyFormattedShort(offeringPrice, parcel.readString());
        OfferingPrice$$PackageHelper.accessOfferingPrice$FS$mCurrencyCode(offeringPrice, parcel.readString());
        OfferingPrice$$PackageHelper.accessOfferingPrice$FS$mCurrencyFormattedRaw(offeringPrice, parcel.readString());
        OfferingPrice$$PackageHelper.accessOfferingPrice$FS$mCurrencyFormattedLong(offeringPrice, parcel.readString());
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
        Offering$$PackageHelper.accessOffering$FS$mOfferingId(offering, etsyId);
        if (parcel.readInt() == -1) {
            offeringPrice = null;
        } else {
            offeringPrice = readcom_etsy_android_lib_models_apiv3_OfferingPrice(parcel);
        }
        Offering$$PackageHelper.accessOffering$FS$mPrice(offering, offeringPrice);
        Offering$$PackageHelper.accessOffering$FS$mQuantity(offering, parcel.readInt());
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        Offering$$PackageHelper.accessOffering$FS$mProductId(offering, etsyId2);
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
        variation.mIsValid = z;
        if (parcel.readInt() == -1) {
            option = null;
        } else {
            option = readcom_etsy_android_lib_models_Option(parcel);
        }
        variation.mSelectedOption = option;
        variation.mFormattedValue = parcel.readString();
        variation.mFormattedName = parcel.readString();
        variation.mPropertyId = parcel.readLong();
        variation.mValueId = parcel.readLong();
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
        variation.mOptions = list;
        return variation;
    }

    private Option readcom_etsy_android_lib_models_Option(Parcel parcel) {
        EtsyMoney etsyMoney;
        EtsyMoney etsyMoney2 = null;
        Option option = new Option();
        option.mVariationPropertyId = parcel.readLong();
        option.mFormattedValue = parcel.readString();
        option.mValueId = parcel.readLong();
        if (parcel.readInt() == -1) {
            etsyMoney = null;
        } else {
            etsyMoney = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        option.mPrice = etsyMoney;
        if (parcel.readInt() == -1) {
            etsyMoney = null;
        } else {
            etsyMoney = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        option.mPriceDiff = etsyMoney;
        if (parcel.readInt() != -1) {
            etsyMoney2 = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        option.mConvertedPrice = etsyMoney2;
        option.mIsAvailable = parcel.readInt() == 1;
        return option;
    }

    private AppreciationPhoto readcom_etsy_android_lib_models_apiv3_AppreciationPhoto(Parcel parcel) {
        EtsyId etsyId;
        EtsyId etsyId2 = null;
        AppreciationPhoto appreciationPhoto = new AppreciationPhoto();
        AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FS$mShortenedShareUrl(appreciationPhoto, (ShortenedUrl) parcel.readSerializable());
        AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FS$mCreateDate(appreciationPhoto, new au().m3269a(parcel));
        AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FS$mStatus(appreciationPhoto, parcel.readString());
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FS$mTransactionId(appreciationPhoto, etsyId);
        AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FS$mIsSellerApproved(appreciationPhoto, parcel.readInt() == 1);
        AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FS$mListingTitle(appreciationPhoto, parcel.readString());
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FS$mImageId(appreciationPhoto, etsyId2);
        AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FS$mSellerAvatarUrl(appreciationPhoto, parcel.readString());
        AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FS$mShopName(appreciationPhoto, parcel.readString());
        appreciationPhoto.mUrl570xN = parcel.readString();
        appreciationPhoto.mUrl224xN = parcel.readString();
        appreciationPhoto.PORTRAIT_HEIGHT_RATIO = parcel.readDouble();
        appreciationPhoto.mUrl680x540 = parcel.readString();
        appreciationPhoto.mUrl75x75 = parcel.readString();
        appreciationPhoto.mUrl170x135 = parcel.readString();
        appreciationPhoto.mUrlFullxFull = parcel.readString();
        appreciationPhoto.mUrl300x300 = parcel.readString();
        appreciationPhoto.mUrl340x270 = parcel.readString();
        return appreciationPhoto;
    }

    private ReviewResponse readcom_etsy_android_lib_models_ReviewResponse(Parcel parcel) {
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.mResponseMessage = parcel.readString();
        reviewResponse.mCreateDate = (Date) parcel.readSerializable();
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

    private void writecom_etsy_android_lib_models_Review(Review review, Parcel parcel, int i) {
        if (review.mListingImage == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_ListingImage(review.mListingImage, parcel, i);
        }
        parcel.writeString(review.mTranslatedContent);
        if (review.mListing == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_Listing(review.mListing, parcel, i);
        }
        if (review.mListingId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(review.mListingId, parcel, i);
        }
        if (review.mAppreciationPhoto == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_AppreciationPhoto(review.mAppreciationPhoto, parcel, i);
        }
        if (review.mTransactionId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(review.mTransactionId, parcel, i);
        }
        parcel.writeString(review.mListingImageUrl);
        if (review.mResponse == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_ReviewResponse(review.mResponse, parcel, i);
        }
        parcel.writeString(review.mReviewMessage);
        parcel.writeInt(review.mIsListingDisplayable ? 1 : 0);
        if (review.mReviewTranslationState == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_uikit_util_MachineTranslationViewState(review.mReviewTranslationState, parcel, i);
        }
        parcel.writeString(review.mListingTitle);
        parcel.writeInt(review.mRating);
        parcel.writeString(review.mReviewLanguage);
    }

    private void writecom_etsy_android_lib_models_ListingImage(ListingImage listingImage, Parcel parcel, int i) {
        parcel.writeInt(listingImage.mFullWidth);
        parcel.writeInt(listingImage.mHue);
        parcel.writeInt(listingImage.mPostCalculatedColor);
        parcel.writeInt(listingImage.mSaturation);
        parcel.writeInt(listingImage.mHexColor);
        parcel.writeInt(listingImage.mRank);
        parcel.writeInt(listingImage.mFullHeight);
        parcel.writeInt(listingImage.mRed);
        parcel.writeInt(listingImage.mBlue);
        if (listingImage.mImageId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(listingImage.mImageId, parcel, i);
        }
        parcel.writeInt(listingImage.mGreen);
        parcel.writeString(listingImage.mUrl570xN);
        parcel.writeString(listingImage.mUrl224xN);
        parcel.writeDouble(listingImage.PORTRAIT_HEIGHT_RATIO);
        parcel.writeString(listingImage.mUrl680x540);
        parcel.writeString(listingImage.mUrl75x75);
        parcel.writeString(listingImage.mUrl170x135);
        parcel.writeString(listingImage.mUrlFullxFull);
        parcel.writeString(listingImage.mUrl300x300);
        parcel.writeString(listingImage.mUrl340x270);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    private void writecom_etsy_android_lib_models_Listing(Listing listing, Parcel parcel, int i) {
        int i2;
        int i3 = 0;
        if (listing.mBuyerDisplayPrice == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(listing.mBuyerDisplayPrice, parcel, i);
        }
        parcel.writeSerializable(listing.mShop);
        if (listing.mListingId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(listing.mListingId, parcel, i);
        }
        parcel.writeSerializable(listing.mPaymentInfo);
        if (listing.mManufacturers == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(listing.mManufacturers.size());
            for (Manufacturer manufacturer : listing.mManufacturers) {
                if (manufacturer == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_Manufacturer(manufacturer, parcel, i);
                }
            }
        }
        parcel.writeSerializable(listing.mCreationDate);
        parcel.writeSerializable(listing.mListingVideos);
        parcel.writeString(listing.mConvertedPrice);
        parcel.writeInt(listing.mViews);
        parcel.writeString(listing.mDescription);
        parcel.writeInt(listing.mQuantity);
        parcel.writeInt(listing.mProcessingMax);
        parcel.writeInt(listing.mProcessingMin);
        parcel.writeInt(listing.mNumFavorers);
        parcel.writeString(listing.mState);
        parcel.writeString(listing.mOriginalLanguage);
        if (listing.mOverview == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(listing.mOverview.size());
            for (String writeString : listing.mOverview) {
                parcel.writeString(writeString);
            }
        }
        if (listing.mIsPrivate) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeString(listing.mConvertedCurrencyCode);
        if (listing.mIsVATInclusive) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (listing.mIsDigitalDownload) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (listing.mShouldAutoRenew) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeSerializable(listing.mSearchAdsMetadata);
        if (listing.mOfferings == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_OfferingResponse(listing.mOfferings, parcel, i);
        }
        parcel.writeString(listing.mLanguage);
        parcel.writeParcelable(listing.mMostRecentUpdate, i);
        if (listing.mPrice == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(listing.mPrice, parcel, i);
        }
        if (listing.mHasCollections) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (listing.mOptions == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(listing.mOptions.size());
            for (ListingOption writeParcelable : listing.mOptions) {
                parcel.writeParcelable(writeParcelable, i);
            }
        }
        if (listing.mIsFundOnEtsyCampaign == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(listing.mIsFundOnEtsyCampaign.booleanValue() ? 1 : 0);
        }
        if (listing.mIsFavorite) {
            i3 = 1;
        }
        parcel.writeInt(i3);
        if (listing.mImages == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(listing.mImages.size());
            for (ListingImage listingImage : listing.mImages) {
                if (listingImage == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_ListingImage(listingImage, parcel, i);
                }
            }
        }
        if (listing.mShippingInfo == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(listing.mShippingInfo.size());
            for (ShippingInfo writeSerializable : listing.mShippingInfo) {
                parcel.writeSerializable(writeSerializable);
            }
        }
        parcel.writeParcelable(listing.mFundOnEtsyCampaign, i);
        if (listing.mCollections == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(listing.mCollections.size());
            for (Collection writeSerializable2 : listing.mCollections) {
                parcel.writeSerializable(writeSerializable2);
            }
        }
        if (listing.mVariations == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(listing.mVariations.size());
            for (Variation variation : listing.mVariations) {
                if (variation == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_Variation(variation, parcel, i);
                }
            }
        }
        parcel.writeString(listing.mUrl);
        parcel.writeString(listing.mTitle);
        parcel.writeString(listing.mTransparentPriceMessage);
        if (listing.mUserId == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(listing.mUserId, parcel, i);
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
        parcel.writeString(manufacturer.mLocation);
        parcel.writeString(manufacturer.mName);
        parcel.writeString(manufacturer.mDescription);
    }

    private void writecom_etsy_android_lib_models_apiv3_OfferingResponse(OfferingResponse offeringResponse, Parcel parcel, int i) {
        parcel.writeInt(OfferingResponse$$PackageHelper.accessOfferingResponse$FG$mMinQuantity(offeringResponse));
        if (OfferingResponse$$PackageHelper.accessOfferingResponse$FG$mUi(offeringResponse) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_OfferingUi(OfferingResponse$$PackageHelper.accessOfferingResponse$FG$mUi(offeringResponse), parcel, i);
        }
        if (OfferingResponse$$PackageHelper.accessOfferingResponse$FG$mMinPrice(offeringResponse) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_OfferingPrice(OfferingResponse$$PackageHelper.accessOfferingResponse$FG$mMinPrice(offeringResponse), parcel, i);
        }
        if (OfferingResponse$$PackageHelper.accessOfferingResponse$FG$mMaxPrice(offeringResponse) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_OfferingPrice(OfferingResponse$$PackageHelper.accessOfferingResponse$FG$mMaxPrice(offeringResponse), parcel, i);
        }
        if (OfferingResponse$$PackageHelper.accessOfferingResponse$FG$mOffering(offeringResponse) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Offering(OfferingResponse$$PackageHelper.accessOfferingResponse$FG$mOffering(offeringResponse), parcel, i);
        }
        parcel.writeInt(OfferingResponse$$PackageHelper.accessOfferingResponse$FG$mMaxQuantity(offeringResponse));
    }

    private void writecom_etsy_android_lib_models_apiv3_OfferingUi(OfferingUi offeringUi, Parcel parcel, int i) {
        if (OfferingUi$$PackageHelper.accessOfferingUi$FG$mPrice(offeringUi) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_FormattedMoney(OfferingUi$$PackageHelper.accessOfferingUi$FG$mPrice(offeringUi), parcel, i);
        }
        if (OfferingUi$$PackageHelper.accessOfferingUi$FG$mSelects(offeringUi) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(OfferingUi$$PackageHelper.accessOfferingUi$FG$mSelects(offeringUi).size());
            for (OfferingSelect offeringSelect : OfferingUi$$PackageHelper.accessOfferingUi$FG$mSelects(offeringUi)) {
                if (offeringSelect == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_OfferingSelect(offeringSelect, parcel, i);
                }
            }
        }
        parcel.writeInt(OfferingUi$$PackageHelper.accessOfferingUi$FG$mHasVariableQuantity(offeringUi) ? 1 : 0);
        if (OfferingUi$$PackageHelper.accessOfferingUi$FG$mQuantity(offeringUi) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_OfferingRangeSelect(OfferingUi$$PackageHelper.accessOfferingUi$FG$mQuantity(offeringUi), parcel, i);
    }

    private void writecom_etsy_android_lib_models_apiv3_FormattedMoney(FormattedMoney formattedMoney, Parcel parcel, int i) {
        parcel.writeString(FormattedMoney$$PackageHelper.accessFormattedMoney$FG$mFormatString(formattedMoney));
        if (FormattedMoney$$PackageHelper.accessFormattedMoney$FG$mArguments(formattedMoney) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(FormattedMoney$$PackageHelper.accessFormattedMoney$FG$mArguments(formattedMoney).size());
        for (Money money : FormattedMoney$$PackageHelper.accessFormattedMoney$FG$mArguments(formattedMoney)) {
            if (money == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                writecom_etsy_android_lib_models_apiv3_Money(money, parcel, i);
            }
        }
    }

    private void writecom_etsy_android_lib_models_apiv3_Money(Money money, Parcel parcel, int i) {
        parcel.writeInt(Money$$PackageHelper.accessMoney$FG$mDivisor(money));
        parcel.writeString(Money$$PackageHelper.accessMoney$FG$mCurrencyCode(money));
        parcel.writeString(Money$$PackageHelper.accessMoney$FG$mAmount(money));
    }

    private void writecom_etsy_android_lib_models_apiv3_OfferingSelect(OfferingSelect offeringSelect, Parcel parcel, int i) {
        if (OfferingSelect$$PackageHelper.accessOfferingSelect$FG$mDefaultOption(offeringSelect) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_OfferingOption(OfferingSelect$$PackageHelper.accessOfferingSelect$FG$mDefaultOption(offeringSelect), parcel, i);
        }
        parcel.writeString(OfferingSelect$$PackageHelper.accessOfferingSelect$FG$mPrompt(offeringSelect));
        if (OfferingSelect$$PackageHelper.accessOfferingSelect$FG$mOptions(offeringSelect) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(OfferingSelect$$PackageHelper.accessOfferingSelect$FG$mOptions(offeringSelect).size());
            for (OfferingOption offeringOption : OfferingSelect$$PackageHelper.accessOfferingSelect$FG$mOptions(offeringSelect)) {
                if (offeringOption == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_OfferingOption(offeringOption, parcel, i);
                }
            }
        }
        parcel.writeInt(OfferingSelect$$PackageHelper.accessOfferingSelect$FG$mEnabled(offeringSelect) ? 1 : 0);
        parcel.writeString(OfferingSelect$$PackageHelper.accessOfferingSelect$FG$mLabel(offeringSelect));
    }

    private void writecom_etsy_android_lib_models_apiv3_OfferingOption(OfferingOption offeringOption, Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        if (OfferingOption$$PackageHelper.accessOfferingOption$FG$mValue(offeringOption) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(OfferingOption$$PackageHelper.accessOfferingOption$FG$mValue(offeringOption), parcel, i);
        }
        if (OfferingOption$$PackageHelper.accessOfferingOption$FG$mSelected(offeringOption)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (OfferingOption$$PackageHelper.accessOfferingOption$FG$mDisplayValue(offeringOption) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_FormattedMoney(OfferingOption$$PackageHelper.accessOfferingOption$FG$mDisplayValue(offeringOption), parcel, i);
        }
        if (!OfferingOption$$PackageHelper.accessOfferingOption$FG$mEnabled(offeringOption)) {
            i3 = 0;
        }
        parcel.writeInt(i3);
    }

    private void writecom_etsy_android_lib_models_apiv3_OfferingRangeSelect(OfferingRangeSelect offeringRangeSelect, Parcel parcel, int i) {
        parcel.writeInt(OfferingRangeSelect$$PackageHelper.accessOfferingRangeSelect$FG$mMax(offeringRangeSelect));
        parcel.writeInt(OfferingRangeSelect$$PackageHelper.accessOfferingRangeSelect$FG$mMin(offeringRangeSelect));
        parcel.writeInt(OfferingRangeSelect$$PackageHelper.accessOfferingRangeSelect$FG$mEnabled(offeringRangeSelect) ? 1 : 0);
        parcel.writeInt(OfferingRangeSelect$$PackageHelper.accessOfferingRangeSelect$FG$mStep(offeringRangeSelect));
        parcel.writeString(OfferingRangeSelect$$PackageHelper.accessOfferingRangeSelect$FG$mLabel(offeringRangeSelect));
    }

    private void writecom_etsy_android_lib_models_apiv3_OfferingPrice(OfferingPrice offeringPrice, Parcel parcel, int i) {
        parcel.writeString(OfferingPrice$$PackageHelper.accessOfferingPrice$FG$mCurrencyFormattedShort(offeringPrice));
        parcel.writeString(OfferingPrice$$PackageHelper.accessOfferingPrice$FG$mCurrencyCode(offeringPrice));
        parcel.writeString(OfferingPrice$$PackageHelper.accessOfferingPrice$FG$mCurrencyFormattedRaw(offeringPrice));
        parcel.writeString(OfferingPrice$$PackageHelper.accessOfferingPrice$FG$mCurrencyFormattedLong(offeringPrice));
    }

    private void writecom_etsy_android_lib_models_apiv3_Offering(Offering offering, Parcel parcel, int i) {
        if (Offering$$PackageHelper.accessOffering$FG$mOfferingId(offering) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(Offering$$PackageHelper.accessOffering$FG$mOfferingId(offering), parcel, i);
        }
        if (Offering$$PackageHelper.accessOffering$FG$mPrice(offering) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_OfferingPrice(Offering$$PackageHelper.accessOffering$FG$mPrice(offering), parcel, i);
        }
        parcel.writeInt(Offering$$PackageHelper.accessOffering$FG$mQuantity(offering));
        if (Offering$$PackageHelper.accessOffering$FG$mProductId(offering) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(Offering$$PackageHelper.accessOffering$FG$mProductId(offering), parcel, i);
    }

    private void writecom_etsy_android_lib_models_Variation(Variation variation, Parcel parcel, int i) {
        parcel.writeInt(variation.mIsValid ? 1 : 0);
        if (variation.mSelectedOption == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_Option(variation.mSelectedOption, parcel, i);
        }
        parcel.writeString(variation.mFormattedValue);
        parcel.writeString(variation.mFormattedName);
        parcel.writeLong(variation.mPropertyId);
        parcel.writeLong(variation.mValueId);
        if (variation.mOptions == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(variation.mOptions.size());
        for (Option option : variation.mOptions) {
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
        parcel.writeLong(option.mVariationPropertyId);
        parcel.writeString(option.mFormattedValue);
        parcel.writeLong(option.mValueId);
        if (option.mPrice == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(option.mPrice, parcel, i);
        }
        if (option.mPriceDiff == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(option.mPriceDiff, parcel, i);
        }
        if (option.mConvertedPrice == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(option.mConvertedPrice, parcel, i);
        }
        if (!option.mIsAvailable) {
            i2 = 0;
        }
        parcel.writeInt(i2);
    }

    private void writecom_etsy_android_lib_models_apiv3_AppreciationPhoto(AppreciationPhoto appreciationPhoto, Parcel parcel, int i) {
        parcel.writeSerializable(AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FG$mShortenedShareUrl(appreciationPhoto));
        new au().m3270a(AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FG$mCreateDate(appreciationPhoto), parcel);
        parcel.writeString(AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FG$mStatus(appreciationPhoto));
        if (AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FG$mTransactionId(appreciationPhoto) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FG$mTransactionId(appreciationPhoto), parcel, i);
        }
        parcel.writeInt(AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FG$mIsSellerApproved(appreciationPhoto) ? 1 : 0);
        parcel.writeString(AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FG$mListingTitle(appreciationPhoto));
        if (AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FG$mImageId(appreciationPhoto) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FG$mImageId(appreciationPhoto), parcel, i);
        }
        parcel.writeString(AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FG$mSellerAvatarUrl(appreciationPhoto));
        parcel.writeString(AppreciationPhoto$$PackageHelper.accessAppreciationPhoto$FG$mShopName(appreciationPhoto));
        parcel.writeString(appreciationPhoto.mUrl570xN);
        parcel.writeString(appreciationPhoto.mUrl224xN);
        parcel.writeDouble(appreciationPhoto.PORTRAIT_HEIGHT_RATIO);
        parcel.writeString(appreciationPhoto.mUrl680x540);
        parcel.writeString(appreciationPhoto.mUrl75x75);
        parcel.writeString(appreciationPhoto.mUrl170x135);
        parcel.writeString(appreciationPhoto.mUrlFullxFull);
        parcel.writeString(appreciationPhoto.mUrl300x300);
        parcel.writeString(appreciationPhoto.mUrl340x270);
    }

    private void writecom_etsy_android_lib_models_ReviewResponse(ReviewResponse reviewResponse, Parcel parcel, int i) {
        parcel.writeString(reviewResponse.mResponseMessage);
        parcel.writeSerializable(reviewResponse.mCreateDate);
    }

    private void writecom_etsy_android_uikit_util_MachineTranslationViewState(MachineTranslationViewState machineTranslationViewState, Parcel parcel, int i) {
        parcel.writeInt(MachineTranslationViewState$$PackageHelper.m5558b(machineTranslationViewState));
        parcel.writeInt(MachineTranslationViewState$$PackageHelper.m5557a(machineTranslationViewState) ? 1 : 0);
    }

    public int describeContents() {
        return 0;
    }

    public Review getParcel() {
        return this.review$$18;
    }
}
