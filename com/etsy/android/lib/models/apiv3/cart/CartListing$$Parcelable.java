package com.etsy.android.lib.models.apiv3.cart;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.apiv3.cart.d */
final class CartListing$$Parcelable implements Creator<CartListing$$Parcelable> {
    private CartListing$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2495a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2496a(i);
    }

    public CartListing$$Parcelable m2495a(Parcel parcel) {
        return new CartListing$$Parcelable(parcel);
    }

    public CartListing$$Parcelable[] m2496a(int i) {
        return new CartListing$$Parcelable[i];
    }
}
eHelper;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.datatypes.EtsyId$$PackageHelper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import org.parceler.ax;

public class CartListing$$Parcelable implements android.os.Parcelable, ax<CartListing> {
    public static final CartListing$$Parcelable CREATOR;
    private CartListing cartListing$$3;

    static {
        CREATOR = new CartListing$$Parcelable();
    }

    public CartListing$$Parcelable(Parcel parcel) {
        CartListing cartListing;
        if (parcel.readInt() == -1) {
            cartListing = null;
        } else {
            cartListing = readcom_etsy_android_lib_models_apiv3_cart_CartListing(parcel);
        }
        this.cartListing$$3 = cartListing;
    }

    public CartListing$$Parcelable(CartListing cartListing) {
        this.cartListing$$3 = cartListing;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.cartListing$$3 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_apiv3_cart_CartListing(this.cartListing$$3, parcel, i);
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
        ListingCard$$PackageHelper.accessListingCard$FS$mFundOnEtsyCampaign(cartListing, (FundOnEtsyCampaign) parcel.readParcelable(CartListing$$Parcelable.class.getClassLoader()));
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

    private EtsyId readcom_etsy_android_lib_models_datatypes_EtsyId(Parcel parcel) {
        EtsyId etsyId = new EtsyId();
        EtsyId$$PackageHelper.accessEtsyId$FS$mId(etsyId, parcel.readString());
        return etsyId;
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

    private void writecom_etsy_android_lib_models_datatypes_EtsyId(EtsyId etsyId, Parcel parcel, int i) {
        parcel.writeString(EtsyId$$PackageHelper.accessEtsyId$FG$mId(etsyId));
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

    public int describeContents() {
        return 0;
    }

    public CartListing getParcel() {
        return this.cartListing$$3;
    }
}
