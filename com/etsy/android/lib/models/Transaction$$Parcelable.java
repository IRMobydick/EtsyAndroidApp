package com.etsy.android.lib.models;

import android.os.Parcel;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.core.EtsyMoney$$PackageHelper;
import com.etsy.android.lib.models.User.PublicKey;
import com.etsy.android.lib.models.apiv3.AppreciationPhoto;
import com.etsy.android.lib.models.apiv3.AppreciationPhoto$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.FormattedMoney;
import com.etsy.android.lib.models.apiv3.FormattedMoney$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.apiv3.Image$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image$Source$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image.Source;
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

public class Transaction$$Parcelable implements android.os.Parcelable, ax<Transaction> {
    public static final ap CREATOR;
    private Transaction transaction$$4;

    static {
        CREATOR = new ap();
    }

    public Transaction$$Parcelable(Parcel parcel) {
        Transaction transaction;
        if (parcel.readInt() == -1) {
            transaction = null;
        } else {
            transaction = readcom_etsy_android_lib_models_Transaction(parcel);
        }
        this.transaction$$4 = transaction;
    }

    public Transaction$$Parcelable(Transaction transaction) {
        this.transaction$$4 = transaction;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.transaction$$4 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_Transaction(this.transaction$$4, parcel, i);
    }

    private Transaction readcom_etsy_android_lib_models_Transaction(Parcel parcel) {
        EtsyId etsyId;
        boolean z;
        Review review;
        Image image;
        ListingImage listingImage;
        List list;
        User user;
        int i = 0;
        boolean z2 = true;
        GiftCardDesign giftCardDesign = null;
        Transaction transaction = new Transaction();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        transaction.mListingId = etsyId;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        transaction.mIsGiftCard = z;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        transaction.mIsDigital = z;
        transaction.mPrice = parcel.readDouble();
        if (parcel.readInt() == -1) {
            review = null;
        } else {
            review = readcom_etsy_android_lib_models_Review(parcel);
        }
        transaction.mReview = review;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        transaction.mIsQuickListing = z;
        if (parcel.readInt() == -1) {
            image = null;
        } else {
            image = readcom_etsy_android_lib_models_apiv3_Image(parcel);
        }
        transaction.mImage = image;
        transaction.mGiftCardInfo = (GiftCardInfo) parcel.readSerializable();
        transaction.mQuantity = parcel.readInt();
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        transaction.mIsFeedbackMutable = z2;
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        transaction.mBuyerUserId = etsyId;
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        transaction.mTransactionId = etsyId;
        transaction.mCurrencyCode = parcel.readString();
        if (parcel.readInt() == -1) {
            listingImage = null;
        } else {
            listingImage = readcom_etsy_android_lib_models_ListingImage(parcel);
        }
        transaction.mMainImage = listingImage;
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
                    obj = readcom_etsy_android_lib_models_Variation(parcel);
                }
                arrayList.add(obj);
                i++;
            }
            obj = arrayList;
        }
        transaction.mVariations = list;
        transaction.mTitle = parcel.readString();
        transaction.mFeedbackOpenDate = (Date) parcel.readSerializable();
        if (parcel.readInt() == -1) {
            user = null;
        } else {
            user = readcom_etsy_android_lib_models_User(parcel);
        }
        transaction.mSeller = user;
        if (parcel.readInt() != -1) {
            giftCardDesign = readcom_etsy_android_lib_models_GiftCardDesign(parcel);
        }
        transaction.mGiftCardDesign = giftCardDesign;
        return transaction;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
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
        listing.mMostRecentUpdate = (ListingUpdate) parcel.readParcelable(Transaction$$Parcelable.class.getClassLoader());
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
                arrayList.add((ListingOption) parcel.readParcelable(Transaction$$Parcelable.class.getClassLoader()));
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
        listing.mFundOnEtsyCampaign = (FundOnEtsyCampaign) parcel.readParcelable(Transaction$$Parcelable.class.getClassLoader());
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
        Image$Source$$PackageHelper.accessImage$Source$FS$height(source, parcel.readInt());
        Image$Source$$PackageHelper.accessImage$Source$FS$mUrl(source, parcel.readString());
        Image$Source$$PackageHelper.accessImage$Source$FS$width(source, parcel.readInt());
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
        user.mShops = list;
        user.mPublicKey = (PublicKey) parcel.readSerializable();
        user.mProfile = (UserProfile) parcel.readSerializable();
        user.mLoginName = parcel.readString();
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
        user.mFavoriteListings = list;
        user.mAwaitingFeedbackCount = parcel.readInt();
        if (parcel.readInt() == -1) {
            image = null;
        } else {
            image = readcom_etsy_android_lib_models_apiv3_Image(parcel);
        }
        user.mAvatar = image;
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
        user.mUserAddresses = list;
        user.mFollowerCount = parcel.readInt();
        user.mFollowingCount = parcel.readInt();
        user.mEmail = parcel.readString();
        if (parcel.readInt() != -1) {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        user.mUserId = etsyId;
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
        FavoriteListing favoriteListing = new FavoriteListing();
        if (parcel.readInt() == -1) {
            etsyMoney = null;
        } else {
            etsyMoney = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        favoriteListing.mBuyerDisplayPrice = etsyMoney;
        favoriteListing.mShop = (Shop) parcel.readSerializable();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        favoriteListing.mListingId = etsyId;
        favoriteListing.mPaymentInfo = (PaymentTemplate) parcel.readSerializable();
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
        favoriteListing.mManufacturers = list;
        favoriteListing.mCreationDate = (Date) parcel.readSerializable();
        favoriteListing.mListingVideos = (ListingVideos) parcel.readSerializable();
        favoriteListing.mConvertedPrice = parcel.readString();
        favoriteListing.mViews = parcel.readInt();
        favoriteListing.mDescription = parcel.readString();
        favoriteListing.mQuantity = parcel.readInt();
        favoriteListing.mProcessingMax = parcel.readInt();
        favoriteListing.mProcessingMin = parcel.readInt();
        favoriteListing.mNumFavorers = parcel.readInt();
        favoriteListing.mState = parcel.readString();
        favoriteListing.mOriginalLanguage = parcel.readString();
        i = parcel.readInt();
        if (i < 0) {
            list = null;
        } else {
            list = new ArrayList();
            for (int i4 = 0; i4 < i; i4++) {
                list.add(parcel.readString());
            }
        }
        favoriteListing.mOverview = list;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        favoriteListing.mIsPrivate = z;
        favoriteListing.mConvertedCurrencyCode = parcel.readString();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        favoriteListing.mIsVATInclusive = z;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        favoriteListing.mIsDigitalDownload = z;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        favoriteListing.mShouldAutoRenew = z;
        favoriteListing.mSearchAdsMetadata = (SearchAdsMetadata) parcel.readSerializable();
        if (parcel.readInt() == -1) {
            offeringResponse = null;
        } else {
            offeringResponse = readcom_etsy_android_lib_models_apiv3_OfferingResponse(parcel);
        }
        favoriteListing.mOfferings = offeringResponse;
        favoriteListing.mLanguage = parcel.readString();
        favoriteListing.mMostRecentUpdate = (ListingUpdate) parcel.readParcelable(Transaction$$Parcelable.class.getClassLoader());
        if (parcel.readInt() == -1) {
            etsyMoney = null;
        } else {
            etsyMoney = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        favoriteListing.mPrice = etsyMoney;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        favoriteListing.mHasCollections = z;
        readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                arrayList.add((ListingOption) parcel.readParcelable(Transaction$$Parcelable.class.getClassLoader()));
            }
            obj = arrayList;
        }
        favoriteListing.mOptions = list;
        if (parcel.readInt() < 0) {
            bool = null;
        } else {
            bool = Boolean.valueOf(parcel.readInt() == 1);
        }
        favoriteListing.mIsFundOnEtsyCampaign = bool;
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        favoriteListing.mIsFavorite = z2;
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
        favoriteListing.mImages = list;
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
        favoriteListing.mShippingInfo = list;
        favoriteListing.mFundOnEtsyCampaign = (FundOnEtsyCampaign) parcel.readParcelable(Transaction$$Parcelable.class.getClassLoader());
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
        favoriteListing.mCollections = list;
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
        favoriteListing.mVariations = list;
        favoriteListing.mUrl = parcel.readString();
        favoriteListing.mTitle = parcel.readString();
        favoriteListing.mTransparentPriceMessage = parcel.readString();
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        favoriteListing.mUserId = etsyId2;
        return favoriteListing;
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
        userAddress.mIsDefaultShipping = z;
        userAddress.mFirstLine = parcel.readString();
        userAddress.mCity = parcel.readString();
        if (parcel.readInt() == -1) {
            country = null;
        } else {
            country = readcom_etsy_android_lib_models_Country(parcel);
        }
        userAddress.mCountry = country;
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        userAddress.mCountryId = etsyId;
        userAddress.mState = parcel.readString();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        userAddress.mUserAddressId = etsyId;
        userAddress.mSecondLine = parcel.readString();
        userAddress.mName = parcel.readString();
        userAddress.mZip = parcel.readString();
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        userAddress.mUserId = etsyId2;
        return userAddress;
    }

    private Country readcom_etsy_android_lib_models_Country(Parcel parcel) {
        boolean z = true;
        Country country = new Country();
        country.mIsoCountryCode = parcel.readString();
        country.mCountryId = parcel.readInt();
        if (parcel.readInt() != 1) {
            z = false;
        }
        country.mIsPrimary = z;
        country.mLongitude = parcel.readFloat();
        country.mName = parcel.readString();
        country.mLatitude = parcel.readFloat();
        country.mWorldBankCountryCode = parcel.readString();
        return country;
    }

    private GiftCardDesign readcom_etsy_android_lib_models_GiftCardDesign(Parcel parcel) {
        GiftCardDesign giftCardDesign = new GiftCardDesign();
        giftCardDesign.mUrl150x119 = parcel.readString();
        giftCardDesign.mId = parcel.readInt();
        giftCardDesign.mUrl280x166 = parcel.readString();
        giftCardDesign.mUrl560x332 = parcel.readString();
        giftCardDesign.mUrl69x69 = parcel.readString();
        giftCardDesign.mUrlBanner = parcel.readString();
        return giftCardDesign;
    }

    private void writecom_etsy_android_lib_models_Transaction(Transaction transaction, Parcel parcel, int i) {
        int i2;
        int i3 = 0;
        if (transaction.mListingId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(transaction.mListingId, parcel, i);
        }
        if (transaction.mIsGiftCard) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (transaction.mIsDigital) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeDouble(transaction.mPrice);
        if (transaction.mReview == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_Review(transaction.mReview, parcel, i);
        }
        if (transaction.mIsQuickListing) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (transaction.mImage == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Image(transaction.mImage, parcel, i);
        }
        parcel.writeSerializable(transaction.mGiftCardInfo);
        parcel.writeInt(transaction.mQuantity);
        if (transaction.mIsFeedbackMutable) {
            i3 = 1;
        }
        parcel.writeInt(i3);
        if (transaction.mBuyerUserId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(transaction.mBuyerUserId, parcel, i);
        }
        if (transaction.mTransactionId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(transaction.mTransactionId, parcel, i);
        }
        parcel.writeString(transaction.mCurrencyCode);
        if (transaction.mMainImage == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_ListingImage(transaction.mMainImage, parcel, i);
        }
        if (transaction.mVariations == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(transaction.mVariations.size());
            for (Variation variation : transaction.mVariations) {
                if (variation == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_Variation(variation, parcel, i);
                }
            }
        }
        parcel.writeString(transaction.mTitle);
        parcel.writeSerializable(transaction.mFeedbackOpenDate);
        if (transaction.mSeller == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_User(transaction.mSeller, parcel, i);
        }
        if (transaction.mGiftCardDesign == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_GiftCardDesign(transaction.mGiftCardDesign, parcel, i);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
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

    private void writecom_etsy_android_lib_models_apiv3_Image$Source(Source source, Parcel parcel, int i) {
        parcel.writeInt(Image$Source$$PackageHelper.accessImage$Source$FG$height(source));
        parcel.writeString(Image$Source$$PackageHelper.accessImage$Source$FG$mUrl(source));
        parcel.writeInt(Image$Source$$PackageHelper.accessImage$Source$FG$width(source));
    }

    private void writecom_etsy_android_lib_models_User(User user, Parcel parcel, int i) {
        if (user.mShops == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(user.mShops.size());
            for (Shop writeSerializable : user.mShops) {
                parcel.writeSerializable(writeSerializable);
            }
        }
        parcel.writeSerializable(user.mPublicKey);
        parcel.writeSerializable(user.mProfile);
        parcel.writeString(user.mLoginName);
        if (user.mFavoriteListings == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(user.mFavoriteListings.size());
            for (FavoriteListing favoriteListing : user.mFavoriteListings) {
                if (favoriteListing == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_FavoriteListing(favoriteListing, parcel, i);
                }
            }
        }
        parcel.writeInt(user.mAwaitingFeedbackCount);
        if (user.mAvatar == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Image(user.mAvatar, parcel, i);
        }
        if (user.mUserAddresses == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(user.mUserAddresses.size());
            for (UserAddress userAddress : user.mUserAddresses) {
                if (userAddress == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_UserAddress(userAddress, parcel, i);
                }
            }
        }
        parcel.writeInt(user.mFollowerCount);
        parcel.writeInt(user.mFollowingCount);
        parcel.writeString(user.mEmail);
        if (user.mUserId == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(user.mUserId, parcel, i);
    }

    private void writecom_etsy_android_lib_models_FavoriteListing(FavoriteListing favoriteListing, Parcel parcel, int i) {
        int i2;
        int i3 = 0;
        if (favoriteListing.mBuyerDisplayPrice == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(favoriteListing.mBuyerDisplayPrice, parcel, i);
        }
        parcel.writeSerializable(favoriteListing.mShop);
        if (favoriteListing.mListingId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(favoriteListing.mListingId, parcel, i);
        }
        parcel.writeSerializable(favoriteListing.mPaymentInfo);
        if (favoriteListing.mManufacturers == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(favoriteListing.mManufacturers.size());
            for (Manufacturer manufacturer : favoriteListing.mManufacturers) {
                if (manufacturer == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_Manufacturer(manufacturer, parcel, i);
                }
            }
        }
        parcel.writeSerializable(favoriteListing.mCreationDate);
        parcel.writeSerializable(favoriteListing.mListingVideos);
        parcel.writeString(favoriteListing.mConvertedPrice);
        parcel.writeInt(favoriteListing.mViews);
        parcel.writeString(favoriteListing.mDescription);
        parcel.writeInt(favoriteListing.mQuantity);
        parcel.writeInt(favoriteListing.mProcessingMax);
        parcel.writeInt(favoriteListing.mProcessingMin);
        parcel.writeInt(favoriteListing.mNumFavorers);
        parcel.writeString(favoriteListing.mState);
        parcel.writeString(favoriteListing.mOriginalLanguage);
        if (favoriteListing.mOverview == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(favoriteListing.mOverview.size());
            for (String writeString : favoriteListing.mOverview) {
                parcel.writeString(writeString);
            }
        }
        if (favoriteListing.mIsPrivate) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeString(favoriteListing.mConvertedCurrencyCode);
        if (favoriteListing.mIsVATInclusive) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (favoriteListing.mIsDigitalDownload) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (favoriteListing.mShouldAutoRenew) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeSerializable(favoriteListing.mSearchAdsMetadata);
        if (favoriteListing.mOfferings == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_OfferingResponse(favoriteListing.mOfferings, parcel, i);
        }
        parcel.writeString(favoriteListing.mLanguage);
        parcel.writeParcelable(favoriteListing.mMostRecentUpdate, i);
        if (favoriteListing.mPrice == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(favoriteListing.mPrice, parcel, i);
        }
        if (favoriteListing.mHasCollections) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (favoriteListing.mOptions == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(favoriteListing.mOptions.size());
            for (ListingOption writeParcelable : favoriteListing.mOptions) {
                parcel.writeParcelable(writeParcelable, i);
            }
        }
        if (favoriteListing.mIsFundOnEtsyCampaign == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(favoriteListing.mIsFundOnEtsyCampaign.booleanValue() ? 1 : 0);
        }
        if (favoriteListing.mIsFavorite) {
            i3 = 1;
        }
        parcel.writeInt(i3);
        if (favoriteListing.mImages == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(favoriteListing.mImages.size());
            for (ListingImage listingImage : favoriteListing.mImages) {
                if (listingImage == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_ListingImage(listingImage, parcel, i);
                }
            }
        }
        if (favoriteListing.mShippingInfo == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(favoriteListing.mShippingInfo.size());
            for (ShippingInfo writeSerializable : favoriteListing.mShippingInfo) {
                parcel.writeSerializable(writeSerializable);
            }
        }
        parcel.writeParcelable(favoriteListing.mFundOnEtsyCampaign, i);
        if (favoriteListing.mCollections == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(favoriteListing.mCollections.size());
            for (Collection writeSerializable2 : favoriteListing.mCollections) {
                parcel.writeSerializable(writeSerializable2);
            }
        }
        if (favoriteListing.mVariations == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(favoriteListing.mVariations.size());
            for (Variation variation : favoriteListing.mVariations) {
                if (variation == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_Variation(variation, parcel, i);
                }
            }
        }
        parcel.writeString(favoriteListing.mUrl);
        parcel.writeString(favoriteListing.mTitle);
        parcel.writeString(favoriteListing.mTransparentPriceMessage);
        if (favoriteListing.mUserId == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(favoriteListing.mUserId, parcel, i);
    }

    private void writecom_etsy_android_lib_models_UserAddress(UserAddress userAddress, Parcel parcel, int i) {
        parcel.writeInt(userAddress.mIsDefaultShipping ? 1 : 0);
        parcel.writeString(userAddress.mFirstLine);
        parcel.writeString(userAddress.mCity);
        if (userAddress.mCountry == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_Country(userAddress.mCountry, parcel, i);
        }
        if (userAddress.mCountryId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(userAddress.mCountryId, parcel, i);
        }
        parcel.writeString(userAddress.mState);
        if (userAddress.mUserAddressId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(userAddress.mUserAddressId, parcel, i);
        }
        parcel.writeString(userAddress.mSecondLine);
        parcel.writeString(userAddress.mName);
        parcel.writeString(userAddress.mZip);
        if (userAddress.mUserId == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(userAddress.mUserId, parcel, i);
    }

    private void writecom_etsy_android_lib_models_Country(Country country, Parcel parcel, int i) {
        parcel.writeString(country.mIsoCountryCode);
        parcel.writeInt(country.mCountryId);
        parcel.writeInt(country.mIsPrimary ? 1 : 0);
        parcel.writeFloat(country.mLongitude);
        parcel.writeString(country.mName);
        parcel.writeFloat(country.mLatitude);
        parcel.writeString(country.mWorldBankCountryCode);
    }

    private void writecom_etsy_android_lib_models_GiftCardDesign(GiftCardDesign giftCardDesign, Parcel parcel, int i) {
        parcel.writeString(giftCardDesign.mUrl150x119);
        parcel.writeInt(giftCardDesign.mId);
        parcel.writeString(giftCardDesign.mUrl280x166);
        parcel.writeString(giftCardDesign.mUrl560x332);
        parcel.writeString(giftCardDesign.mUrl69x69);
        parcel.writeString(giftCardDesign.mUrlBanner);
    }

    public int describeContents() {
        return 0;
    }

    public Transaction getParcel() {
        return this.transaction$$4;
    }
}
