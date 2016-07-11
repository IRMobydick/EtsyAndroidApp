package com.etsy.android.lib.models.finds;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.finds.q */
final class GiftCardBanner$$Parcelable implements Creator<GiftCardBanner$$Parcelable> {
    private GiftCardBanner$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2767a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2768a(i);
    }

    public GiftCardBanner$$Parcelable m2767a(Parcel parcel) {
        return new GiftCardBanner$$Parcelable(parcel);
    }

    public GiftCardBanner$$Parcelable[] m2768a(int i) {
        return new GiftCardBanner$$Parcelable[i];
    }
}
models.ListingImage$$PackageHelper;
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
import com.etsy.android.lib.models.ScheduleExpanded;
import com.etsy.android.lib.models.ScheduleExpanded$$PackageHelper;
import com.etsy.android.lib.models.SearchAdsMetadata;
import com.etsy.android.lib.models.ShippingInfo;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.TaxonomyNode$$PackageHelper;
import com.etsy.android.lib.models.UserAddress;
import com.etsy.android.lib.models.UserAddress$$PackageHelper;
import com.etsy.android.lib.models.Variation;
import com.etsy.android.lib.models.Variation$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.FindsCard;
import com.etsy.android.lib.models.apiv3.FindsCard$$PackageHelper;
import com.etsy.android.lib.models.apiv3.FormattedMoney;
import com.etsy.android.lib.models.apiv3.FormattedMoney$$PackageHelper;
import com.etsy.android.lib.models.apiv3.FundOnEtsyCampaign;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.apiv3.Image$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image$Source$$PackageHelper;
import com.etsy.android.lib.models.apiv3.Image.Source;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.apiv3.ListingCard$$PackageHelper;
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
import com.etsy.android.lib.models.finds.FindsCrossLinkModule.Page;
import com.etsy.android.lib.models.finds.FindsTwoTitledListingsModule.Footer;
import com.etsy.android.lib.models.finds.FindsTwoTitledListingsModule.Header;
import com.etsy.android.lib.models.finds.FindsTwoTitledListingsModule.Section;
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

public class GiftCardBanner$$Parcelable implements android.os.Parcelable, ax<GiftCardBanner> {
    public static final GiftCardBanner$$Parcelable CREATOR;
    private GiftCardBanner giftCardBanner$$0;

    static {
        CREATOR = new GiftCardBanner$$Parcelable();
    }

    public GiftCardBanner$$Parcelable(Parcel parcel) {
        GiftCardBanner giftCardBanner;
        if (parcel.readInt() == -1) {
            giftCardBanner = null;
        } else {
            giftCardBanner = readcom_etsy_android_lib_models_finds_GiftCardBanner(parcel);
        }
        this.giftCardBanner$$0 = giftCardBanner;
    }

    public GiftCardBanner$$Parcelable(GiftCardBanner giftCardBanner) {
        this.giftCardBanner$$0 = giftCardBanner;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.giftCardBanner$$0 == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_finds_GiftCardBanner(this.giftCardBanner$$0, parcel, i);
    }

    private GiftCardBanner readcom_etsy_android_lib_models_finds_GiftCardBanner(Parcel parcel) {
        List list;
        ArrayList arrayList;
        int i;
        Object obj;
        int i2 = 0;
        List list2 = null;
        GiftCardBanner giftCardBanner = new GiftCardBanner();
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_finds_FindsCrossLinkModule$Page(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        giftCardBanner.mPages = list;
        giftCardBanner.mType = parcel.readString();
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
        giftCardBanner.mShops = list;
        giftCardBanner.mText = parcel.readString();
        giftCardBanner.mCta = parcel.readString();
        giftCardBanner.mTitle = parcel.readString();
        readInt = parcel.readInt();
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
        giftCardBanner.mListings = list;
        readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = m2730x9a940b4(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        giftCardBanner.mSections = list;
        giftCardBanner.mGiftCardBannerImage = (GiftCardBannerImage) parcel.readSerializable();
        i = parcel.readInt();
        if (i >= 0) {
            arrayList = new ArrayList();
            while (i2 < i) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_finds_FindsSearchCategory(parcel);
                }
                arrayList.add(obj);
                i2++;
            }
            Object obj2 = arrayList;
        }
        giftCardBanner.mCategories = list2;
        return giftCardBanner;
    }

    private Page readcom_etsy_android_lib_models_finds_FindsCrossLinkModule$Page(Parcel parcel) {
        List list;
        ListingImage listingImage;
        boolean z;
        EtsyId etsyId = null;
        FindsCard page = new Page();
        page.mType = parcel.readInt();
        FindsCard$$PackageHelper.accessFindsCard$FS$mViewType(page, parcel.readInt());
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
        FindsCard$$PackageHelper.accessFindsCard$FS$mImages(page, list);
        if (parcel.readInt() == -1) {
            listingImage = null;
        } else {
            listingImage = readcom_etsy_android_lib_models_ListingImage(parcel);
        }
        FindsCard$$PackageHelper.accessFindsCard$FS$mImg(page, listingImage);
        FindsCard$$PackageHelper.accessFindsCard$FS$mSlug(page, parcel.readString());
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        FindsCard$$PackageHelper.accessFindsCard$FS$mIsPublic(page, z);
        FindsCard$$PackageHelper.accessFindsCard$FS$mLanguage(page, parcel.readString());
        FindsCard$$PackageHelper.accessFindsCard$FS$mTitle(page, parcel.readString());
        FindsCard$$PackageHelper.accessFindsCard$FS$mUrl(page, parcel.readString());
        if (parcel.readInt() != -1) {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        FindsCard$$PackageHelper.accessFindsCard$FS$mFindsPageId(page, etsyId);
        return page;
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
                arrayList.add((LocalStoreImage) parcel.readParcelable(GiftCardBanner$$Parcelable.class.getClassLoader()));
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
                list2.add((LocalStoreImage) parcel.readParcelable(GiftCardBanner$$Parcelable.class.getClassLoader()));
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
        ListingCard$$PackageHelper.accessListingCard$FS$mFundOnEtsyCampaign(listingCard, (FundOnEtsyCampaign) parcel.readParcelable(GiftCardBanner$$Parcelable.class.getClassLoader()));
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

    private Rating readcom_etsy_android_lib_models_apiv3_Rating(Parcel parcel) {
        Rating rating = new Rating();
        Rating$$PackageHelper.accessRating$FS$mStars(rating, parcel.readDouble());
        Rating$$PackageHelper.accessRating$FS$mSellerFeedbackCount(rating, parcel.readInt());
        Rating$$PackageHelper.accessRating$FS$mCount(rating, parcel.readInt());
        Rating$$PackageHelper.accessRating$FS$mRating(rating, parcel.readDouble());
        return rating;
    }

    private Section m2730x9a940b4(Parcel parcel) {
        Header header;
        Footer footer;
        List list = null;
        Section section = new Section();
        if (parcel.readInt() == -1) {
            header = null;
        } else {
            header = m2728x58e42ade(parcel);
        }
        section.mHeader = header;
        if (parcel.readInt() == -1) {
            footer = null;
        } else {
            footer = m2727x560dfdec(parcel);
        }
        section.mFooter = footer;
        int readInt = parcel.readInt();
        if (readInt >= 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                Object obj;
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = m2729x9f127c53(parcel);
                }
                arrayList.add(obj);
            }
            Object obj2 = arrayList;
        }
        section.mListings = list;
        return section;
    }

    private Header m2728x58e42ade(Parcel parcel) {
        List list;
        ArrayList arrayList;
        int i;
        Object obj;
        int i2 = 0;
        List list2 = null;
        Header header = new Header();
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_finds_FindsCrossLinkModule$Page(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        header.mPages = list;
        header.mType = parcel.readString();
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
        header.mShops = list;
        header.mText = parcel.readString();
        header.mCta = parcel.readString();
        header.mTitle = parcel.readString();
        readInt = parcel.readInt();
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
        header.mListings = list;
        readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = m2730x9a940b4(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        header.mSections = list;
        header.mGiftCardBannerImage = (GiftCardBannerImage) parcel.readSerializable();
        i = parcel.readInt();
        if (i >= 0) {
            arrayList = new ArrayList();
            while (i2 < i) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_finds_FindsSearchCategory(parcel);
                }
                arrayList.add(obj);
                i2++;
            }
            Object obj2 = arrayList;
        }
        header.mCategories = list2;
        return header;
    }

    private FindsSearchCategory readcom_etsy_android_lib_models_finds_FindsSearchCategory(Parcel parcel) {
        Listing listing;
        FindsUrl findsUrl = null;
        FindsSearchCategory findsSearchCategory = new FindsSearchCategory();
        if (parcel.readInt() == -1) {
            listing = null;
        } else {
            listing = readcom_etsy_android_lib_models_Listing(parcel);
        }
        findsSearchCategory.mListing = listing;
        if (parcel.readInt() != -1) {
            findsUrl = readcom_etsy_android_lib_models_finds_FindsUrl(parcel);
        }
        findsSearchCategory.mSearchUrl = findsUrl;
        findsSearchCategory.mUrl = parcel.readString();
        findsSearchCategory.mTitle = parcel.readString();
        return findsSearchCategory;
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
        Listing$$PackageHelper.accessListing$FS$mMostRecentUpdate(listing, (ListingUpdate) parcel.readParcelable(GiftCardBanner$$Parcelable.class.getClassLoader()));
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
                arrayList.add((ListingOption) parcel.readParcelable(GiftCardBanner$$Parcelable.class.getClassLoader()));
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
        Listing$$PackageHelper.accessListing$FS$mFundOnEtsyCampaign(listing, (com.etsy.android.lib.models.FundOnEtsyCampaign) parcel.readParcelable(GiftCardBanner$$Parcelable.class.getClassLoader()));
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

    private FindsUrl readcom_etsy_android_lib_models_finds_FindsUrl(Parcel parcel) {
        TaxonomyNode taxonomyNode;
        EtsyId etsyId = null;
        boolean z = true;
        FindsUrl findsUrl = new FindsUrl();
        findsUrl.mMinPrice = parcel.readString();
        if (parcel.readInt() != 1) {
            z = false;
        }
        findsUrl.mHasCategoryLandingPage = z;
        if (parcel.readInt() == -1) {
            taxonomyNode = null;
        } else {
            taxonomyNode = readcom_etsy_android_lib_models_TaxonomyNode(parcel);
        }
        findsUrl.mTaxonomyNode = taxonomyNode;
        findsUrl.mMarketplace = parcel.readString();
        if (parcel.readInt() != -1) {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        findsUrl.mAnchorListingId = etsyId;
        findsUrl.mQuery = parcel.readString();
        findsUrl.mMaxPrice = parcel.readString();
        return findsUrl;
    }

    private TaxonomyNode readcom_etsy_android_lib_models_TaxonomyNode(Parcel parcel) {
        List list;
        EtsyId etsyId;
        boolean z;
        TaxonomyNode taxonomyNode = null;
        TaxonomyNode taxonomyNode2 = new TaxonomyNode();
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
                    obj = readcom_etsy_android_lib_models_TaxonomyNode(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        TaxonomyNode$$PackageHelper.accessTaxonomyNode$FS$mChildren(taxonomyNode2, list);
        TaxonomyNode$$PackageHelper.accessTaxonomyNode$FS$mParentPath(taxonomyNode2, parcel.readString());
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        TaxonomyNode$$PackageHelper.accessTaxonomyNode$FS$mTaxonomyNodeId(taxonomyNode2, etsyId);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        TaxonomyNode$$PackageHelper.accessTaxonomyNode$FS$mIsSuppliesTopLevel(taxonomyNode2, z);
        TaxonomyNode$$PackageHelper.accessTaxonomyNode$FS$mName(taxonomyNode2, parcel.readString());
        TaxonomyNode$$PackageHelper.accessTaxonomyNode$FS$mPath(taxonomyNode2, parcel.readString());
        if (parcel.readInt() != -1) {
            taxonomyNode = readcom_etsy_android_lib_models_TaxonomyNode(parcel);
        }
        TaxonomyNode$$PackageHelper.accessTaxonomyNode$FS$mParent(taxonomyNode2, taxonomyNode);
        TaxonomyNode$$PackageHelper.accessTaxonomyNode$FS$mLongName(taxonomyNode2, parcel.readString());
        return taxonomyNode2;
    }

    private Footer m2727x560dfdec(Parcel parcel) {
        FindsUrl findsUrl;
        List list;
        ArrayList arrayList;
        int i;
        Object obj;
        int i2 = 0;
        List list2 = null;
        Footer footer = new Footer();
        if (parcel.readInt() == -1) {
            findsUrl = null;
        } else {
            findsUrl = readcom_etsy_android_lib_models_finds_FindsUrl(parcel);
        }
        footer.mTitleLink = findsUrl;
        footer.mUrl = parcel.readString();
        footer.mBottomText = parcel.readString();
        int readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_finds_FindsCrossLinkModule$Page(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        footer.mPages = list;
        footer.mType = parcel.readString();
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
        footer.mShops = list;
        footer.mText = parcel.readString();
        footer.mCta = parcel.readString();
        footer.mTitle = parcel.readString();
        readInt = parcel.readInt();
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
        footer.mListings = list;
        readInt = parcel.readInt();
        if (readInt < 0) {
            list = null;
        } else {
            arrayList = new ArrayList();
            for (i = 0; i < readInt; i++) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = m2730x9a940b4(parcel);
                }
                arrayList.add(obj);
            }
            obj = arrayList;
        }
        footer.mSections = list;
        footer.mGiftCardBannerImage = (GiftCardBannerImage) parcel.readSerializable();
        i = parcel.readInt();
        if (i >= 0) {
            arrayList = new ArrayList();
            while (i2 < i) {
                if (parcel.readInt() == -1) {
                    obj = null;
                } else {
                    obj = readcom_etsy_android_lib_models_finds_FindsSearchCategory(parcel);
                }
                arrayList.add(obj);
                i2++;
            }
            Object obj2 = arrayList;
        }
        footer.mCategories = list2;
        return footer;
    }

    private FindsTwoTitledListingsModule.Listing m2729x9f127c53(Parcel parcel) {
        boolean z;
        EtsyId etsyId;
        EtsyMoney etsyMoney;
        EtsyId etsyId2 = null;
        boolean z2 = true;
        ListingCard listing = new FindsTwoTitledListingsModule.Listing();
        ListingCard$$PackageHelper.accessListingCard$FS$mHasError(listing, parcel.readInt() == 1);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mIsSoldOut(listing, z);
        if (parcel.readInt() == -1) {
            etsyId = null;
        } else {
            etsyId = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mListingId(listing, etsyId);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mIsAd(listing, z);
        if (parcel.readInt() == -1) {
            etsyMoney = null;
        } else {
            etsyMoney = readcom_etsy_android_lib_core_EtsyMoney(parcel);
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mPrice(listing, etsyMoney);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mHasCollections(listing, z);
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mIsFundOnEtsyCampaign(listing, z);
        ListingCard$$PackageHelper.accessListingCard$FS$mQuantity(listing, parcel.readInt());
        ListingCard$$PackageHelper.accessListingCard$FS$mServerFormattedPrice(listing, parcel.readString());
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mIsFavorite(listing, z2);
        ListingCard$$PackageHelper.accessListingCard$FS$mViewType(listing, parcel.readInt());
        ListingCard$$PackageHelper.accessListingCard$FS$mPriceUnformatted(listing, parcel.readDouble());
        ListingCard$$PackageHelper.accessListingCard$FS$mListingImage(listing, (BaseModelImage) parcel.readSerializable());
        ListingCard$$PackageHelper.accessListingCard$FS$mFundOnEtsyCampaign(listing, (FundOnEtsyCampaign) parcel.readParcelable(GiftCardBanner$$Parcelable.class.getClassLoader()));
        if (parcel.readInt() != -1) {
            etsyId2 = readcom_etsy_android_lib_models_datatypes_EtsyId(parcel);
        }
        ListingCard$$PackageHelper.accessListingCard$FS$mShopId(listing, etsyId2);
        ListingCard$$PackageHelper.accessListingCard$FS$mTitle(listing, parcel.readString());
        ListingCard$$PackageHelper.accessListingCard$FS$mUrl(listing, parcel.readString());
        ListingCard$$PackageHelper.accessListingCard$FS$mShopAverageRating(listing, parcel.readFloat());
        ListingCard$$PackageHelper.accessListingCard$FS$mProlistLoggingKey(listing, parcel.readString());
        ListingCard$$PackageHelper.accessListingCard$FS$mShopName(listing, parcel.readString());
        ListingCard$$PackageHelper.accessListingCard$FS$mShopTotalRatingCount(listing, parcel.readInt());
        return listing;
    }

    private void writecom_etsy_android_lib_models_finds_GiftCardBanner(GiftCardBanner giftCardBanner, Parcel parcel, int i) {
        if (giftCardBanner.mPages == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(giftCardBanner.mPages.size());
            for (Page page : giftCardBanner.mPages) {
                if (page == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_finds_FindsCrossLinkModule$Page(page, parcel, i);
                }
            }
        }
        parcel.writeString(giftCardBanner.mType);
        if (giftCardBanner.mShops == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(giftCardBanner.mShops.size());
            for (ShopCard shopCard : giftCardBanner.mShops) {
                if (shopCard == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_ShopCard(shopCard, parcel, i);
                }
            }
        }
        parcel.writeString(giftCardBanner.mText);
        parcel.writeString(giftCardBanner.mCta);
        parcel.writeString(giftCardBanner.mTitle);
        if (giftCardBanner.mListings == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(giftCardBanner.mListings.size());
            for (ListingCard listingCard : giftCardBanner.mListings) {
                if (listingCard == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_ListingCard(listingCard, parcel, i);
                }
            }
        }
        if (giftCardBanner.mSections == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(giftCardBanner.mSections.size());
            for (Section section : giftCardBanner.mSections) {
                if (section == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    m2734xba85dc3d(section, parcel, i);
                }
            }
        }
        parcel.writeSerializable(giftCardBanner.mGiftCardBannerImage);
        if (giftCardBanner.mCategories == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(giftCardBanner.mCategories.size());
        for (FindsSearchCategory findsSearchCategory : giftCardBanner.mCategories) {
            if (findsSearchCategory == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                writecom_etsy_android_lib_models_finds_FindsSearchCategory(findsSearchCategory, parcel, i);
            }
        }
    }

    private void writecom_etsy_android_lib_models_finds_FindsCrossLinkModule$Page(Page page, Parcel parcel, int i) {
        parcel.writeInt(page.mType);
        parcel.writeInt(FindsCard$$PackageHelper.accessFindsCard$FG$mViewType(page));
        if (FindsCard$$PackageHelper.accessFindsCard$FG$mImages(page) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(FindsCard$$PackageHelper.accessFindsCard$FG$mImages(page).size());
            for (ListingImage listingImage : FindsCard$$PackageHelper.accessFindsCard$FG$mImages(page)) {
                if (listingImage == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_ListingImage(listingImage, parcel, i);
                }
            }
        }
        if (FindsCard$$PackageHelper.accessFindsCard$FG$mImg(page) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_ListingImage(FindsCard$$PackageHelper.accessFindsCard$FG$mImg(page), parcel, i);
        }
        parcel.writeString(FindsCard$$PackageHelper.accessFindsCard$FG$mSlug(page));
        parcel.writeInt(FindsCard$$PackageHelper.accessFindsCard$FG$mIsPublic(page) ? 1 : 0);
        parcel.writeString(FindsCard$$PackageHelper.accessFindsCard$FG$mLanguage(page));
        parcel.writeString(FindsCard$$PackageHelper.accessFindsCard$FG$mTitle(page));
        parcel.writeString(FindsCard$$PackageHelper.accessFindsCard$FG$mUrl(page));
        if (FindsCard$$PackageHelper.accessFindsCard$FG$mFindsPageId(page) == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        writecom_etsy_android_lib_models_datatypes_EtsyId(FindsCard$$PackageHelper.accessFindsCard$FG$mFindsPageId(page), parcel, i);
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

    private void writecom_etsy_android_lib_models_apiv3_Rating(Rating rating, Parcel parcel, int i) {
        parcel.writeDouble(Rating$$PackageHelper.accessRating$FG$mStars(rating));
        parcel.writeInt(Rating$$PackageHelper.accessRating$FG$mSellerFeedbackCount(rating));
        parcel.writeInt(Rating$$PackageHelper.accessRating$FG$mCount(rating));
        parcel.writeDouble(Rating$$PackageHelper.accessRating$FG$mRating(rating));
    }

    private void m2734xba85dc3d(Section section, Parcel parcel, int i) {
        if (section.mHeader == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            m2732xc1b17a35(section.mHeader, parcel, i);
        }
        if (section.mFooter == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            m2731xbedb4d43(section.mFooter, parcel, i);
        }
        if (section.mListings == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(section.mListings.size());
        for (FindsTwoTitledListingsModule.Listing listing : section.mListings) {
            if (listing == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                m2733x4fef17dc(listing, parcel, i);
            }
        }
    }

    private void m2732xc1b17a35(Header header, Parcel parcel, int i) {
        if (header.mPages == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(header.mPages.size());
            for (Page page : header.mPages) {
                if (page == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_finds_FindsCrossLinkModule$Page(page, parcel, i);
                }
            }
        }
        parcel.writeString(header.mType);
        if (header.mShops == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(header.mShops.size());
            for (ShopCard shopCard : header.mShops) {
                if (shopCard == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_ShopCard(shopCard, parcel, i);
                }
            }
        }
        parcel.writeString(header.mText);
        parcel.writeString(header.mCta);
        parcel.writeString(header.mTitle);
        if (header.mListings == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(header.mListings.size());
            for (ListingCard listingCard : header.mListings) {
                if (listingCard == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_ListingCard(listingCard, parcel, i);
                }
            }
        }
        if (header.mSections == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(header.mSections.size());
            for (Section section : header.mSections) {
                if (section == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    m2734xba85dc3d(section, parcel, i);
                }
            }
        }
        parcel.writeSerializable(header.mGiftCardBannerImage);
        if (header.mCategories == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(header.mCategories.size());
        for (FindsSearchCategory findsSearchCategory : header.mCategories) {
            if (findsSearchCategory == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                writecom_etsy_android_lib_models_finds_FindsSearchCategory(findsSearchCategory, parcel, i);
            }
        }
    }

    private void writecom_etsy_android_lib_models_finds_FindsSearchCategory(FindsSearchCategory findsSearchCategory, Parcel parcel, int i) {
        if (findsSearchCategory.mListing == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_Listing(findsSearchCategory.mListing, parcel, i);
        }
        if (findsSearchCategory.mSearchUrl == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_finds_FindsUrl(findsSearchCategory.mSearchUrl, parcel, i);
        }
        parcel.writeString(findsSearchCategory.mUrl);
        parcel.writeString(findsSearchCategory.mTitle);
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

    private void writecom_etsy_android_lib_models_Manufacturer(Manufacturer manufacturer, Parcel parcel, int i) {
        parcel.writeString(Manufacturer$$PackageHelper.accessManufacturer$FG$mLocation(manufacturer));
        parcel.writeString(Manufacturer$$PackageHelper.accessManufacturer$FG$mName(manufacturer));
        parcel.writeString(Manufacturer$$PackageHelper.accessManufacturer$FG$mDescription(manufacturer));
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

    private void writecom_etsy_android_lib_models_finds_FindsUrl(FindsUrl findsUrl, Parcel parcel, int i) {
        parcel.writeString(findsUrl.mMinPrice);
        parcel.writeInt(findsUrl.mHasCategoryLandingPage ? 1 : 0);
        if (findsUrl.mTaxonomyNode == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_TaxonomyNode(findsUrl.mTaxonomyNode, parcel, i);
        }
        parcel.writeString(findsUrl.mMarketplace);
        if (findsUrl.mAnchorListingId == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(findsUrl.mAnchorListingId, parcel, i);
        }
        parcel.writeString(findsUrl.mQuery);
        parcel.writeString(findsUrl.mMaxPrice);
    }

    private void writecom_etsy_android_lib_models_TaxonomyNode(TaxonomyNode taxonomyNode, Parcel parcel, int i) {
        if (TaxonomyNode$$PackageHelper.accessTaxonomyNode$FG$mChildren(taxonomyNode) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(TaxonomyNode$$PackageHelper.accessTaxonomyNode$FG$mChildren(taxonomyNode).size());
            for (TaxonomyNode taxonomyNode2 : TaxonomyNode$$PackageHelper.accessTaxonomyNode$FG$mChildren(taxonomyNode)) {
                if (taxonomyNode2 == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_TaxonomyNode(taxonomyNode2, parcel, i);
                }
            }
        }
        parcel.writeString(TaxonomyNode$$PackageHelper.accessTaxonomyNode$FG$mParentPath(taxonomyNode));
        if (TaxonomyNode$$PackageHelper.accessTaxonomyNode$FG$mTaxonomyNodeId(taxonomyNode) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(TaxonomyNode$$PackageHelper.accessTaxonomyNode$FG$mTaxonomyNodeId(taxonomyNode), parcel, i);
        }
        parcel.writeInt(TaxonomyNode$$PackageHelper.accessTaxonomyNode$FG$mIsSuppliesTopLevel(taxonomyNode) ? 1 : 0);
        parcel.writeString(TaxonomyNode$$PackageHelper.accessTaxonomyNode$FG$mName(taxonomyNode));
        parcel.writeString(TaxonomyNode$$PackageHelper.accessTaxonomyNode$FG$mPath(taxonomyNode));
        if (TaxonomyNode$$PackageHelper.accessTaxonomyNode$FG$mParent(taxonomyNode) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_TaxonomyNode(TaxonomyNode$$PackageHelper.accessTaxonomyNode$FG$mParent(taxonomyNode), parcel, i);
        }
        parcel.writeString(TaxonomyNode$$PackageHelper.accessTaxonomyNode$FG$mLongName(taxonomyNode));
    }

    private void m2731xbedb4d43(Footer footer, Parcel parcel, int i) {
        if (footer.mTitleLink == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_finds_FindsUrl(footer.mTitleLink, parcel, i);
        }
        parcel.writeString(footer.mUrl);
        parcel.writeString(footer.mBottomText);
        if (footer.mPages == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(footer.mPages.size());
            for (Page page : footer.mPages) {
                if (page == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_finds_FindsCrossLinkModule$Page(page, parcel, i);
                }
            }
        }
        parcel.writeString(footer.mType);
        if (footer.mShops == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(footer.mShops.size());
            for (ShopCard shopCard : footer.mShops) {
                if (shopCard == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_ShopCard(shopCard, parcel, i);
                }
            }
        }
        parcel.writeString(footer.mText);
        parcel.writeString(footer.mCta);
        parcel.writeString(footer.mTitle);
        if (footer.mListings == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(footer.mListings.size());
            for (ListingCard listingCard : footer.mListings) {
                if (listingCard == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    writecom_etsy_android_lib_models_apiv3_ListingCard(listingCard, parcel, i);
                }
            }
        }
        if (footer.mSections == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(footer.mSections.size());
            for (Section section : footer.mSections) {
                if (section == null) {
                    parcel.writeInt(-1);
                } else {
                    parcel.writeInt(1);
                    m2734xba85dc3d(section, parcel, i);
                }
            }
        }
        parcel.writeSerializable(footer.mGiftCardBannerImage);
        if (footer.mCategories == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(footer.mCategories.size());
        for (FindsSearchCategory findsSearchCategory : footer.mCategories) {
            if (findsSearchCategory == null) {
                parcel.writeInt(-1);
            } else {
                parcel.writeInt(1);
                writecom_etsy_android_lib_models_finds_FindsSearchCategory(findsSearchCategory, parcel, i);
            }
        }
    }

    private void m2733x4fef17dc(FindsTwoTitledListingsModule.Listing listing, Parcel parcel, int i) {
        int i2;
        int i3 = 0;
        parcel.writeInt(ListingCard$$PackageHelper.accessListingCard$FG$mHasError(listing) ? 1 : 0);
        if (ListingCard$$PackageHelper.accessListingCard$FG$mIsSoldOut(listing)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (ListingCard$$PackageHelper.accessListingCard$FG$mListingId(listing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(ListingCard$$PackageHelper.accessListingCard$FG$mListingId(listing), parcel, i);
        }
        if (ListingCard$$PackageHelper.accessListingCard$FG$mIsAd(listing)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (ListingCard$$PackageHelper.accessListingCard$FG$mPrice(listing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_core_EtsyMoney(ListingCard$$PackageHelper.accessListingCard$FG$mPrice(listing), parcel, i);
        }
        if (ListingCard$$PackageHelper.accessListingCard$FG$mHasCollections(listing)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (ListingCard$$PackageHelper.accessListingCard$FG$mIsFundOnEtsyCampaign(listing)) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeInt(ListingCard$$PackageHelper.accessListingCard$FG$mQuantity(listing));
        parcel.writeString(ListingCard$$PackageHelper.accessListingCard$FG$mServerFormattedPrice(listing));
        if (ListingCard$$PackageHelper.accessListingCard$FG$mIsFavorite(listing)) {
            i3 = 1;
        }
        parcel.writeInt(i3);
        parcel.writeInt(ListingCard$$PackageHelper.accessListingCard$FG$mViewType(listing));
        parcel.writeDouble(ListingCard$$PackageHelper.accessListingCard$FG$mPriceUnformatted(listing));
        parcel.writeSerializable(ListingCard$$PackageHelper.accessListingCard$FG$mListingImage(listing));
        parcel.writeParcelable(ListingCard$$PackageHelper.accessListingCard$FG$mFundOnEtsyCampaign(listing), i);
        if (ListingCard$$PackageHelper.accessListingCard$FG$mShopId(listing) == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            writecom_etsy_android_lib_models_datatypes_EtsyId(ListingCard$$PackageHelper.accessListingCard$FG$mShopId(listing), parcel, i);
        }
        parcel.writeString(ListingCard$$PackageHelper.accessListingCard$FG$mTitle(listing));
        parcel.writeString(ListingCard$$PackageHelper.accessListingCard$FG$mUrl(listing));
        parcel.writeFloat(ListingCard$$PackageHelper.accessListingCard$FG$mShopAverageRating(listing));
        parcel.writeString(ListingCard$$PackageHelper.accessListingCard$FG$mProlistLoggingKey(listing));
        parcel.writeString(ListingCard$$PackageHelper.accessListingCard$FG$mShopName(listing));
        parcel.writeInt(ListingCard$$PackageHelper.accessListingCard$FG$mShopTotalRatingCount(listing));
    }

    public int describeContents() {
        return 0;
    }

    public GiftCardBanner getParcel() {
        return this.giftCardBanner$$0;
    }
}
