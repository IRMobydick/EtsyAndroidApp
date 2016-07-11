package com.etsy.android.lib.models.apiv3.cart;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.apiv3.cart.l */
final class SavedCart$$Parcelable implements Creator<SavedCart$$Parcelable> {
    private SavedCart$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2511a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2512a(i);
    }

    public SavedCart$$Parcelable m2511a(Parcel parcel) {
        return new SavedCart$$Parcelable(parcel);
    }

    public SavedCart$$Parcelable[] m2512a(int i) {
        return new SavedCart$$Parcelable[i];
    }
}
t$$PackageHelper;
import com.etsy.android.lib.models.LocalMarketAttendeeSchedule;
import com.etsy.android.lib.models.LocalMarketAttendeeSchedule$$PackageHelper;
import com.etsy.android.lib.models.LocalMarketAttendeeSchedule$Day$$PackageHelper;
import com.etsy.android.lib.models.LocalMarketAttendeeSchedule.Day;
import com.etsy.android.lib.models.LocalMarketCard;
import com.etsy.android.lib.models.LocalMarketCard$$PackageHelper;
import com.etsy.android.lib.models.LocalStore;
import com.etsy.android.lib.models.LocalStore$$PackageHelper;
import com.etsy.android.lib.models.LocalStoreImage;
import com.etsy.android.lib.models.Option;
import com.etsy.android.lib.models.Option$$PackageHelper;
import com.etsy.android.lib.models.ScheduleExpanded;
import com.etsy.android.lib.models.ScheduleExpanded$$PackageHelper;
import com.etsy.android.lib.models.UserAddress;
import com.etsy.android.lib.models.UserAddress$$PackageHelper;
import com.etsy.android.lib.models.Variation;
import com.etsy.android.lib.models.Variation$$PackageHelper;
import com.etsy.android.lib.models.apiv3.FundOnEtsyCampaign;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.apiv3.Image$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image$Source$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image.Source;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.apiv3.ListingCard$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Money;
import com.etsy.android.lib.models.apiv3.Money$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Rating;
import com.etsy.android.lib.models.apiv3.Rating$$PackageHelper;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.apiv3.ShopCard$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Team;
import com.etsy.android.lib.models.apiv3.Team$$PackageHelper;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import com.etsy.android.lib.models.datatypes.TimeRange;
import com.etsy.android.lib.models.datatypes.TimeRange$$PackageHelper;
import com.etsy.android.lib.models.enums.WeekDay;
import com.etsy.android.lib.models.viewstate.CartViewState;
import com.etsy.android.lib.models.viewstate.CartViewState$$PackageHelper;
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

public class SavedCart$$Parcelable implements android.os.Parcelable, ax<SavedCart> {
    public static final SavedCart$$Parcelable CREATOR;
    private SavedCart savedCart$$0;

    static {
        CREATOR = new SavedCart$$Parcelable();
    }

    public SavedCart$$Parcelable(Parcel parcel) {
        SavedCart savedCart;
        if (parcel.readInt() == -1) {
            savedCart = null;
        } else {
            savedCart = readcom_etsy_android_lib_models_apiv3_cart_SavedCart(parcel);
        }
        this.savedCart$$0 = savedCart;
    }

    public SavedCart$$Parcelable(SavedCart savedCart) {
        this.savedCart$$0 = savedCart;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.savedCart$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_cart_SavedCart(this.savedCart$$0, parcel, i);
    }

    private SavedCart readcom_etsy_android_lib_models_apiv3_cart_SavedCart(Parcel parcel) {
        CartViewState cartViewState;
        EtsyId etsyId;
        CartListing cartListing;
        ShopCard shopCard = null;
        SavedCart savedCart = new SavedCart();
        if (parcel.readInt() == -1) {
            cartViewState = null;
        } else {
            cartViewState = readcom_etsy_android_lib_models_viewstate_CartViewState(parcel);
        }
        savedCart.mCartViewState = cartViewState;
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        savedCart.mCartId = etsyId;
        if (parcel.readInt() == -1) {
            cartListing = null;
        } else {
            cartListing = readcom_etsy_android_lib_models_apiv3_cart_CartListing(parcel);
        }
        savedCart.mCartListing = cartListing;
        if (parcel.readInt() != -1) {
            shopCard = readcom_etsy_android_lib_models_apiv3_ShopCard(parcel);
        }
        savedCart.mShopCard = shopCard;
        return savedCart;
    }

    private CartViewState readcom_etsy_android_lib_models_viewstate_CartViewState(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        CartViewState cartViewState = new CartViewState();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        CartViewState$$PackageHelper.accessCartViewState$FS$mIsRequestingShippingCosts(cartViewState, z);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        CartViewState$$PackageHelper.accessCartViewState$FS$mIsLoading(cartViewState, z);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        CartViewState$$PackageHelper.accessCartViewState$FS$mIsEditingShippingCosts(cartViewState, z);
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        CartViewState$$PackageHelper.accessCartViewState$FS$mIsViewingShippingCosts(cartViewState, z2);
        return cartViewState;
    }

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
    }

    private CartListing readcom_etsy_android_lib_models_apiv3_cart_CartListing(Parcel parcel) {
        EtsyId etsyId;
        Money money;
        boolean z;
        List list;
        EtsyMoney etsyMoney;
        EtsyId etsyId2 = null;
        boolean z2 = true;
        ListingCard cartListing = new CartListing();
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        cartListing.mCartListingCustomizationId = etsyId;
        if (parcel.readInt() == -1) {
            money = null;
        } else {
            money = readcom_etsy_android_lib_models_apiv3_Money(parcel);
        }
        cartListing.mItemPrice = money;
        cartListing.mState = parcel.readInt();
        if (parcel.readInt() == -1) {
            money = null;
        } else {
            money = readcom_etsy_android_lib_models_apiv3_Money(parcel);
        }
        cartListing.mTotalPrice = money;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        cartListing.mIsGiftCard = z;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        cartListing.mIsDigital = z;
        cartListing.mPurchaseQuantity = parcel.readInt();
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
                    obj = readcom_etsy_android_lib_models_Variation(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        cartListing.mSelectedVariations = list;
        cartListing.mDescription = parcel.readString();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mHasError(cartListing, z);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mIsSoldOut(cartListing, z);
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mListingId(cartListing, etsyId);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mIsAd(cartListing, z);
        if (parcel.readInt() == -1) {
            etsyMoney = null;
        } else {
            etsyMoney = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mPrice(cartListing, etsyMoney);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mHasCollections(cartListing, z);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mIsFundOnEtsyCampaign(cartListing, z);
        ListingCard$$PackageHelper.accessListingCard$FS$mQuantity(cartListing, parcel.readInt());
        ListingCard$$PackageHelper.accessListingCard$FS$mServerFormattedPrice(cartListing, parcel.readString());
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mIsFavorite(cartListing, z2);
        ListingCard$$PackageHelper.accessListingCard$FS$mViewType(cartListing, parcel.readInt());
        ListingCard$$PackageHelper.accessListingCard$FS$mPriceUnformatted(cartListing, parcel.readDouble());
        ListingCard$$PackageHelper.accessListingCard$FS$mListingImage(cartListing, (BaseModelImage) parcel.readSerializable());
        ListingCard$$PackageHelper.accessListingCard$FS$mFundOnEtsyCampaign(cartListing, (FundOnEtsyCampaign) parcel.readParcelable(SavedCart$$Parcelable.class.getClassLoader()));
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mShopId(cartListing, etsyId2);
        ListingCard$$PackageHelper.accessListingCard$FS$mTitle(cartListing, parcel.readString());
        ListingCard$$PackageHelper.accessListingCard$FS$mUrl(cartListing, parcel.readString());
        ListingCard$$PackageHelper.accessListingCard$FS$mShopAverageRating(cartListing, parcel.readFloat());
        ListingCard$$PackageHelper.accessListingCard$FS$mProlistLoggingKey(cartListing, parcel.readString());
        ListingCard$$PackageHelper.accessListingCard$FS$mShopName(cartListing, parcel.readString());
        ListingCard$$PackageHelper.accessListingCard$FS$mShopTotalRatingCount(cartListing, parcel.readInt());
        return cartListing;
    }

    private Money readcom_etsy_android_lib_models_apiv3_Money(Parcel parcel) {
        Money money = new Money();
        Money$$PackageHelper.accessMoney$FS$mDivisor(money, parcel.readInt());
        Money$$PackageHelper.accessMoney$FS$mCurrencyCode(money, parcel.readString());
        Money$$PackageHelper.accessMoney$FS$mAmount(money, parcel.readString());
        return money;
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
        ShopCard$$PackageHelper.accessShopCard$FS$mIcon(shopCard, image);
        ShopCard$$PackageHelper.accessShopCard$FS$mLoginName(shopCard, parcel.readString());
        ShopCard$$PackageHelper.accessShopCard$FS$mLocation(shopCard, parcel.readString());
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        ShopCard$$PackageHelper.accessShopCard$FS$mIsVacation(shopCard, z);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        ShopCard$$PackageHelper.accessShopCard$FS$mHasIcon(shopCard, z);
        ShopCard$$PackageHelper.accessShopCard$FS$mHeadline(shopCard, parcel.readString());
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        ShopCard$$PackageHelper.accessShopCard$FS$mIsFavorite(shopCard, z2);
        ShopCard$$PackageHelper.accessShopCard$FS$mSellerAvatar(shopCard, parcel.readString());
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        ShopCard$$PackageHelper.accessShopCard$FS$mShopId(shopCard, etsyId);
        if (parcel.readInt() == -1) {
            localMarket = null;
        } else {
            localMarket = readcom_etsy_android_lib_models_LocalMarket(parcel);
        }
        ShopCard$$PackageHelper.accessShopCard$FS$mLocalEvent(shopCard, localMarket);
        ShopCard$$PackageHelper.accessShopCard$FS$mShopUrl(shopCard, parcel.readString());
        ShopCard$$PackageHelper.accessShopCard$FS$mActiveListingCount(shopCard, parcel.readInt());
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
        ShopCard$$PackageHelper.accessShopCard$FS$mDisplayListings(shopCard, list);
        ShopCard$$PackageHelper.accessShopCard$FS$mOpenDate(shopCard, new au().m3269a(parcel));
        if (parcel.readInt() == -1) {
            rating = null;
        } else {
            rating = readcom_etsy_android_lib_models_apiv3_Rating(parcel);
        }
        ShopCard$$PackageHelper.accessShopCard$FS$mRating(shopCard, rating);
        ShopCard$$PackageHelper.accessShopCard$FS$mShopName(shopCard, parcel.readString());
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        ShopCard$$PackageHelper.accessShopCard$FS$mUserId(shopCard, etsyId2);
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
        Image$Source$$PackageHelper.accessImage$Source$FS$height(source, parcel.readInt());
        Image$Source$$PackageHelper.accessImage$Source$FS$mUrl(source, parcel.readString());
        Image$Source$$PackageHelper.accessImage$Source$FS$width(source, parcel.readInt());
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
        Team$$PackageHelper.accessTeam$FS$mId(team, etsyId);
        Team$$PackageHelper.accessTeam$FS$mShortDescription(team, parcel.readString());
        Team$$PackageHelper.accessTeam$FS$mAvatarUrl(team, parcel.readString());
        Team$$PackageHelper.accessTeam$FS$mName(team, parcel.readString());
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
                arrayList.add((LocalStoreImage) parcel.readParcelable(SavedCart$$Parcelable.class.getClassLoader()));
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
                list2.add((LocalStoreImage) parcel.readParcelable(SavedCart$$Parcelable.class.getClassLoader()));
            }
        }
        LocalMarketCard$$PackageHelper.accessLocalMarketCard$FS$mStoreImages(localMarketCard, list2);
        return localMarketCard;
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
        ListingCard$$PackageHelper.accessListingCard$FS$mFundOnEtsyCampaign(listingCard, (FundOnEtsyCampaign) parcel.readParcelable(SavedCart$$Parcelable.class.getClassLoader()));
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

    private Rating readcom_etsy_android_lib_models_apiv3_Rating(Parcel parcel) {
        Rating rating = new Rating();
        Rating$$PackageHelper.accessRating$FS$mStars(rating, parcel.readDouble());
        Rating$$PackageHelper.accessRating$FS$mSellerFeedbackCount(rating, parcel.readInt());
        Rating$$PackageHelper.accessRating$FS$mCount(rating, parcel.readInt());
        Rating$$PackageHelper.accessRating$FS$mRating(rating, parcel.readDouble());
        return rating;
    }

    private void writecom_etsy_android_lib_models_apiv3_cart_SavedCart(SavedCart savedCart, Parcel parcel, int i) {
        if (savedCart.mCartViewState == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_viewstate_CartViewState(savedCart.mCartViewState, parcel, i);
        }
        if (savedCart.mCartId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(savedCart.mCartId, parcel, i);
        }
        if (savedCart.mCartListing == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_cart_CartListing(savedCart.mCartListing, parcel, i);
        }
        if (savedCart.mShopCard == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_ShopCard(savedCart.mShopCard, parcel, i);
    }

    private void writecom_etsy_android_lib_models_viewstate_CartViewState(CartViewState cartViewState, Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        if (CartViewState$$PackageHelper.accessCartViewState$FG$mIsRequestingShippingCosts(cartViewState)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (CartViewState$$PackageHelper.accessCartViewState$FG$mIsLoading(cartViewState)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (CartViewState$$PackageHelper.accessCartViewState$FG$mIsEditingShippingCosts(cartViewState)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (!CartViewState$$PackageHelper.accessCartViewState$FG$mIsViewingShippingCosts(cartViewState)) {
            i3 = 0;
        }
        parcel.writeInt(i3);
    }

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
    }

    private void writecom_etsy_android_lib_models_apiv3_cart_CartListing(CartListing cartListing, Parcel parcel, int i) {
        int i2;
        int i3 = 0;
        if (cartListing.mCartListingCustomizationId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(cartListing.mCartListingCustomizationId, parcel, i);
        }
        if (cartListing.mItemPrice == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Money(cartListing.mItemPrice, parcel, i);
        }
        parcel.writeInt(cartListing.mState);
        if (cartListing.mTotalPrice == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Money(cartListing.mTotalPrice, parcel, i);
        }
        if (cartListing.mIsGiftCard) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (cartListing.mIsDigital) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeInt(cartListing.mPurchaseQuantity);
        if (cartListing.mSelectedVariations == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(cartListing.mSelectedVariations.size());
            for (Variation variation : cartListing.mSelectedVariations) {
                if (variation == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_Variation(variation, parcel, i);
                }
            }
        }
        parcel.writeString(cartListing.mDescription);
        if (ListingCard$$PackageHelper.accessListingCard$FG$mHasError(cartListing)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (ListingCard$$PackageHelper.accessListingCard$FG$mIsSoldOut(cartListing)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (ListingCard$$PackageHelper.accessListingCard$FG$mListingId(cartListing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(ListingCard$$PackageHelper.accessListingCard$FG$mListingId(cartListing), parcel, i);
        }
        if (ListingCard$$PackageHelper.accessListingCard$FG$mIsAd(cartListing)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (ListingCard$$PackageHelper.accessListingCard$FG$mPrice(cartListing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(ListingCard$$PackageHelper.accessListingCard$FG$mPrice(cartListing), parcel, i);
        }
        if (ListingCard$$PackageHelper.accessListingCard$FG$mHasCollections(cartListing)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (ListingCard$$PackageHelper.accessListingCard$FG$mIsFundOnEtsyCampaign(cartListing)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeInt(ListingCard$$PackageHelper.accessListingCard$FG$mQuantity(cartListing));
        parcel.writeString(ListingCard$$PackageHelper.accessListingCard$FG$mServerFormattedPrice(cartListing));
        if (ListingCard$$PackageHelper.accessListingCard$FG$mIsFavorite(cartListing)) {
            i3 = 1;
        }
        parcel.writeInt(i3);
        parcel.writeInt(ListingCard$$PackageHelper.accessListingCard$FG$mViewType(cartListing));
        parcel.writeDouble(ListingCard$$PackageHelper.accessListingCard$FG$mPriceUnformatted(cartListing));
        parcel.writeSerializable(ListingCard$$PackageHelper.accessListingCard$FG$mListingImage(cartListing));
        parcel.writeParcelable(ListingCard$$PackageHelper.accessListingCard$FG$mFundOnEtsyCampaign(cartListing), i);
        if (ListingCard$$PackageHelper.accessListingCard$FG$mShopId(cartListing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(ListingCard$$PackageHelper.accessListingCard$FG$mShopId(cartListing), parcel, i);
        }
        parcel.writeString(ListingCard$$PackageHelper.accessListingCard$FG$mTitle(cartListing));
        parcel.writeString(ListingCard$$PackageHelper.accessListingCard$FG$mUrl(cartListing));
        parcel.writeFloat(ListingCard$$PackageHelper.accessListingCard$FG$mShopAverageRating(cartListing));
        parcel.writeString(ListingCard$$PackageHelper.accessListingCard$FG$mProlistLoggingKey(cartListing));
        parcel.writeString(ListingCard$$PackageHelper.accessListingCard$FG$mShopName(cartListing));
        parcel.writeInt(ListingCard$$PackageHelper.accessListingCard$FG$mShopTotalRatingCount(cartListing));
    }

    private void writecom_etsy_android_lib_models_apiv3_Money(Money money, Parcel parcel, int i) {
        parcel.writeInt(Money$$PackageHelper.accessMoney$FG$mDivisor(money));
        parcel.writeString(Money$$PackageHelper.accessMoney$FG$mCurrencyCode(money));
        parcel.writeString(Money$$PackageHelper.accessMoney$FG$mAmount(money));
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

    private void writecom_etsy_android_lib_models_apiv3_ShopCard(ShopCard shopCard, Parcel parcel, int i) {
        int i2;
        int i3 = 0;
        if (ShopCard$$PackageHelper.accessShopCard$FG$mIcon(shopCard) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Image(ShopCard$$PackageHelper.accessShopCard$FG$mIcon(shopCard), parcel, i);
        }
        parcel.writeString(ShopCard$$PackageHelper.accessShopCard$FG$mLoginName(shopCard));
        parcel.writeString(ShopCard$$PackageHelper.accessShopCard$FG$mLocation(shopCard));
        if (ShopCard$$PackageHelper.accessShopCard$FG$mIsVacation(shopCard)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (ShopCard$$PackageHelper.accessShopCard$FG$mHasIcon(shopCard)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeString(ShopCard$$PackageHelper.accessShopCard$FG$mHeadline(shopCard));
        if (ShopCard$$PackageHelper.accessShopCard$FG$mIsFavorite(shopCard)) {
            i3 = 1;
        }
        parcel.writeInt(i3);
        parcel.writeString(ShopCard$$PackageHelper.accessShopCard$FG$mSellerAvatar(shopCard));
        if (ShopCard$$PackageHelper.accessShopCard$FG$mShopId(shopCard) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(ShopCard$$PackageHelper.accessShopCard$FG$mShopId(shopCard), parcel, i);
        }
        if (ShopCard$$PackageHelper.accessShopCard$FG$mLocalEvent(shopCard) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_LocalMarket(ShopCard$$PackageHelper.accessShopCard$FG$mLocalEvent(shopCard), parcel, i);
        }
        parcel.writeString(ShopCard$$PackageHelper.accessShopCard$FG$mShopUrl(shopCard));
        parcel.writeInt(ShopCard$$PackageHelper.accessShopCard$FG$mActiveListingCount(shopCard));
        if (ShopCard$$PackageHelper.accessShopCard$FG$mDisplayListings(shopCard) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(ShopCard$$PackageHelper.accessShopCard$FG$mDisplayListings(shopCard).size());
            for (ListingCard listingCard : ShopCard$$PackageHelper.accessShopCard$FG$mDisplayListings(shopCard)) {
                if (listingCard == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_ListingCard(listingCard, parcel, i);
                }
            }
        }
        new au().m3270a(ShopCard$$PackageHelper.accessShopCard$FG$mOpenDate(shopCard), parcel);
        if (ShopCard$$PackageHelper.accessShopCard$FG$mRating(shopCard) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_apiv3_Rating(ShopCard$$PackageHelper.accessShopCard$FG$mRating(shopCard), parcel, i);
        }
        parcel.writeString(ShopCard$$PackageHelper.accessShopCard$FG$mShopName(shopCard));
        if (ShopCard$$PackageHelper.accessShopCard$FG$mUserId(shopCard) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(ShopCard$$PackageHelper.accessShopCard$FG$mUserId(shopCard), parcel, i);
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
        if (Team$$PackageHelper.accessTeam$FG$mId(team) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(Team$$PackageHelper.accessTeam$FG$mId(team), parcel, i);
        }
        parcel.writeString(Team$$PackageHelper.accessTeam$FG$mShortDescription(team));
        parcel.writeString(Team$$PackageHelper.accessTeam$FG$mAvatarUrl(team));
        parcel.writeString(Team$$PackageHelper.accessTeam$FG$mName(team));
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

    private void writecom_etsy_android_lib_models_apiv3_Rating(Rating rating, Parcel parcel, int i) {
        parcel.writeDouble(Rating$$PackageHelper.accessRating$FG$mStars(rating));
        parcel.writeInt(Rating$$PackageHelper.accessRating$FG$mSellerFeedbackCount(rating));
        parcel.writeInt(Rating$$PackageHelper.accessRating$FG$mCount(rating));
        parcel.writeDouble(Rating$$PackageHelper.accessRating$FG$mRating(rating));
    }

    public int describeContents() {
        return 0;
    }

    public SavedCart getParcel() {
        return this.savedCart$$0;
    }
}
