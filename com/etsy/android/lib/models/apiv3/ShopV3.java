package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShopAboutMember;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.BasicShopLike;
import com.etsy.android.lib.models.interfaces.ShopShareable;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.uikit.ui.shop.ShopHomeAdapter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.foresee.mobileReplay.recorder.ScreenRecorder;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ShopV3 extends BaseModel implements BasicShopLike, ShopShareable {
    public static final int BRANDING_OPTION_BANNER = 1;
    public static final int BRANDING_OPTION_LARGE_BANNER = 2;
    public static final int BRANDING_OPTION_NO_BRANDING = 0;
    public static final String MODULE_ABOUT = "module_about";
    public static final String MODULE_FEATURED_ITEMS = "module_featured_items";
    public static final String MODULE_LOCAL = "module_local";
    public static final String PAGE_SOLD_ITEMS = "page_sold_items";
    private static final long serialVersionUID = 4017847212818190079L;
    protected boolean mAboutInfoExists;
    protected boolean mAcceptsBankTransfers;
    protected boolean mAcceptsChecks;
    protected boolean mAcceptsDirectCheckout;
    protected boolean mAcceptsGiftCards;
    protected boolean mAcceptsMoneyOrders;
    protected boolean mAcceptsOther;
    protected boolean mAcceptsPayPal;
    protected int mActiveListingCount;
    protected double mAverageRating;
    protected Image mBanner;
    protected String mBannerUrl;
    protected int mBrandingOption;
    protected String mCity;
    protected String mCountryCode;
    protected Date mCreateDate;
    protected String mCurrencyCode;
    protected int mDigitalListingCount;
    protected int mFavoritesCount;
    protected String mGoogleAnalyticsPropertyId;
    protected boolean mHasAbout;
    protected boolean mHasAboutPage;
    protected boolean mHasBanner;
    protected boolean mHasCurrencyCode;
    protected boolean mHasIcon;
    protected boolean mHasLanguagePreferences;
    protected boolean mHasLargeBanner;
    protected boolean mHasOwners;
    protected boolean mHasPrivateReceiptInfo;
    protected String mHeadline;
    protected boolean mIsOpen;
    protected boolean mIsUsingStructuredPolicies;
    protected boolean mIsVacation;
    protected Image mLargeBanner;
    protected double mLatitude;
    protected boolean mListingRearrangeEnabled;
    protected String mLocation;
    protected double mLongitude;
    protected String mMessage;
    protected String mMessageToBuyers;
    protected Date mMessageUpdateDate;
    protected List<String> mModules;
    protected String mName;
    protected String mOnboardingStatus;
    protected Date mOpenDate;
    protected User mOwner;
    protected String mPullQuote;
    protected String mRegion;
    protected String mSellerAvatarUrl;
    protected String mSellerName;
    protected ShopIconV3 mShopIcon;
    protected EtsyId mShopId;
    protected List<Language> mShopLanguages;
    protected String mShopName;
    protected String mShopUrl;
    protected int mSoldCount;
    protected String mStatus;
    protected String mStory;
    protected String mStoryHeadline;
    protected String mStoryLeadingParagraph;
    protected int mTotalRatingCount;
    protected Date mUpdateDate;
    protected String mUrl;
    protected EtsyId mUserId;
    protected String mVacationAutoReply;
    protected String mVacationMessage;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ShopHomeBrandingOption {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ShopHomeModule {
    }

    public ShopV3() {
        this.mShopId = new EtsyId();
        this.mUserId = new EtsyId();
        this.mUrl = StringUtils.EMPTY;
        this.mSellerAvatarUrl = StringUtils.EMPTY;
        this.mSellerName = StringUtils.EMPTY;
        this.mShopName = StringUtils.EMPTY;
        this.mShopUrl = StringUtils.EMPTY;
        this.mStatus = StringUtils.EMPTY;
        this.mCity = StringUtils.EMPTY;
        this.mRegion = StringUtils.EMPTY;
        this.mName = StringUtils.EMPTY;
        this.mHeadline = StringUtils.EMPTY;
        this.mMessage = StringUtils.EMPTY;
        this.mMessageToBuyers = StringUtils.EMPTY;
        this.mBannerUrl = StringUtils.EMPTY;
        this.mStoryHeadline = StringUtils.EMPTY;
        this.mStoryLeadingParagraph = StringUtils.EMPTY;
        this.mStory = StringUtils.EMPTY;
        this.mPullQuote = StringUtils.EMPTY;
        this.mLocation = StringUtils.EMPTY;
        this.mCountryCode = StringUtils.EMPTY;
        this.mCurrencyCode = StringUtils.EMPTY;
        this.mGoogleAnalyticsPropertyId = StringUtils.EMPTY;
        this.mOnboardingStatus = StringUtils.EMPTY;
        this.mVacationMessage = StringUtils.EMPTY;
        this.mVacationAutoReply = StringUtils.EMPTY;
        this.mModules = new ArrayList(BRANDING_OPTION_NO_BRANDING);
        this.mShopLanguages = new ArrayList(BRANDING_OPTION_NO_BRANDING);
    }

    public boolean aboutInfoExists() {
        return this.mAboutInfoExists;
    }

    public boolean acceptsBankTransfers() {
        return this.mAcceptsBankTransfers;
    }

    public boolean acceptsChecks() {
        return this.mAcceptsChecks;
    }

    public boolean acceptsDirectCheckout() {
        return this.mAcceptsDirectCheckout;
    }

    public boolean acceptsGiftCards() {
        return this.mAcceptsGiftCards;
    }

    public boolean acceptsMoneyOrders() {
        return this.mAcceptsMoneyOrders;
    }

    public boolean acceptsOther() {
        return this.mAcceptsOther;
    }

    public boolean acceptsPayPal() {
        return this.mAcceptsPayPal;
    }

    public int getActiveListingCount() {
        return this.mActiveListingCount;
    }

    public int getDigitalListingCount() {
        return this.mDigitalListingCount;
    }

    public boolean hasOnlyDigitalListings() {
        return getDigitalListingCount() > 0 && getActiveListingCount() == getDigitalListingCount();
    }

    public double getAverageRating() {
        return this.mAverageRating;
    }

    public Image getBanner() {
        return this.mBanner;
    }

    public Image getLargeBanner() {
        return this.mLargeBanner;
    }

    public String getBannerUrl() {
        return this.mBannerUrl;
    }

    public String getCity() {
        return this.mCity;
    }

    public String getCountryCode() {
        return this.mCountryCode;
    }

    public Date getCreateDate() {
        return this.mCreateDate;
    }

    public String getCurrencyCode() {
        return this.mCurrencyCode;
    }

    public boolean isUsingStructuredPolicies() {
        return this.mIsUsingStructuredPolicies;
    }

    public int getFavoritesCount() {
        return this.mFavoritesCount;
    }

    public String getGoogleAnalyticsPropertyId() {
        return this.mGoogleAnalyticsPropertyId;
    }

    public boolean hasAbout() {
        return this.mHasAbout;
    }

    public boolean hasAboutPage() {
        return this.mHasAboutPage;
    }

    public boolean hasBanner() {
        return this.mHasBanner;
    }

    public boolean hasLargeBanner() {
        return this.mHasLargeBanner;
    }

    public boolean hasCurrencyCode() {
        return this.mHasCurrencyCode;
    }

    public boolean hasIcon() {
        return this.mHasIcon;
    }

    public boolean hasLanguagePreferences() {
        return this.mHasLanguagePreferences;
    }

    public boolean hasOwners() {
        return this.mHasOwners;
    }

    public String getHeadline() {
        return this.mHeadline;
    }

    public boolean isOpen() {
        return this.mIsOpen;
    }

    public boolean isVacation() {
        return this.mIsVacation;
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    public String getLocation() {
        return this.mLocation;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public String getMessageToBuyers() {
        return this.mMessageToBuyers;
    }

    public String getName() {
        return this.mName;
    }

    public String getOnboardingStatus() {
        return this.mOnboardingStatus;
    }

    public User getOwner() {
        return this.mOwner;
    }

    public String getPullQuote() {
        return this.mPullQuote;
    }

    public String getRegion() {
        return this.mRegion;
    }

    public String getSellerAvatarUrl() {
        return this.mSellerAvatarUrl;
    }

    public String getSellerName() {
        return this.mSellerName;
    }

    public ShopIconV3 getShopIcon() {
        return this.mShopIcon;
    }

    public EtsyId getShopId() {
        return this.mShopId;
    }

    public String getShopName() {
        return this.mShopName;
    }

    public String getShopUrl() {
        return this.mShopUrl;
    }

    public int getSoldCount() {
        return this.mSoldCount;
    }

    public String getStatus() {
        return this.mStatus;
    }

    public String getStory() {
        return this.mStory;
    }

    public String getStoryHeadline() {
        return this.mStoryHeadline;
    }

    public String getStoryLeadingParagraph() {
        return this.mStoryLeadingParagraph;
    }

    public int getTotalRatingCount() {
        return this.mTotalRatingCount;
    }

    public Date getUpdateDate() {
        return this.mUpdateDate;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public EtsyId getUserId() {
        return this.mUserId;
    }

    public Date getMessageUpdateDate() {
        return this.mMessageUpdateDate;
    }

    public int getBrandingOption() {
        return this.mBrandingOption;
    }

    public List<Language> getShopLanguages() {
        return this.mShopLanguages;
    }

    public String getShareImageUrl(int i) {
        return this.mShopIcon.getUrl();
    }

    public List<String> getModules() {
        return this.mModules;
    }

    public String getShareUrl() {
        return this.mUrl;
    }

    public String getShopHeadline() {
        return this.mHeadline;
    }

    public String getVacationMessage() {
        return this.mVacationMessage;
    }

    public String getVacationAutoReply() {
        return this.mVacationAutoReply;
    }

    public boolean shouldShowLargeBanner() {
        return this.mBrandingOption == BRANDING_OPTION_LARGE_BANNER && this.mLargeBanner != null;
    }

    public boolean isListingRearrangeEnabled() {
        return this.mListingRearrangeEnabled;
    }

    public int getNumRatings() {
        return this.mTotalRatingCount;
    }

    @NonNull
    public Date getOpenDate() {
        if (this.mOpenDate != null && this.mOpenDate.getTime() > 0) {
            return this.mOpenDate;
        }
        if (this.mCreateDate != null) {
            return this.mCreateDate;
        }
        return new Date(0);
    }

    public boolean hasPrivateReceiptInfo() {
        return this.mHasPrivateReceiptInfo;
    }

    public static int getBrandingOption(int i) {
        switch (i) {
            case BRANDING_OPTION_BANNER /*1*/:
                return BRANDING_OPTION_BANNER;
            case BRANDING_OPTION_LARGE_BANNER /*2*/:
                return BRANDING_OPTION_LARGE_BANNER;
            default:
                return BRANDING_OPTION_NO_BRANDING;
        }
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -2103918028:
                        if (currentName.equals(ResponseConstants.SHOP_NAME)) {
                            obj = 8;
                            break;
                        }
                        break;
                    case -2066209957:
                        if (currentName.equals(ResponseConstants.ABOUT_INFO_EXISTS)) {
                            obj = 45;
                            break;
                        }
                        break;
                    case -1942440317:
                        if (currentName.equals(ResponseConstants.HAS_PRIVATE_RECEIPT_INFO)) {
                            obj = 65;
                            break;
                        }
                        break;
                    case -1851403988:
                        if (currentName.equals(ResponseConstants.MESSAGE_TO_BUYERS)) {
                            obj = 18;
                            break;
                        }
                        break;
                    case -1825173825:
                        if (currentName.equals(ResponseConstants.ACCEPTS_CHECKS)) {
                            obj = 40;
                            break;
                        }
                        break;
                    case -1691267301:
                        if (currentName.equals(ResponseConstants.REARRANGE_ENABLED)) {
                            obj = 63;
                            break;
                        }
                        break;
                    case -1470450249:
                        if (currentName.equals(ResponseConstants.ACCEPTS_OTHER)) {
                            obj = 41;
                            break;
                        }
                        break;
                    case -1458851513:
                        if (currentName.equals(ResponseConstants.ACCEPTS_PAYPAL)) {
                            obj = 42;
                            break;
                        }
                        break;
                    case -1396342996:
                        if (currentName.equals(ResponseConstants.BANNER)) {
                            obj = 54;
                            break;
                        }
                        break;
                    case -1371821962:
                        if (currentName.equals(ResponseConstants.HAS_CURRENCY_CODE)) {
                            obj = 35;
                            break;
                        }
                        break;
                    case -1294951259:
                        if (currentName.equals(ResponseConstants.VACATION_MESSAGE)) {
                            obj = 60;
                            break;
                        }
                        break;
                    case -1115058732:
                        if (currentName.equals(ResponseConstants.HEADLINE)) {
                            obj = 16;
                            break;
                        }
                        break;
                    case -1092501209:
                        if (currentName.equals(ResponseConstants.FAVORITES_COUNT)) {
                            obj = 23;
                            break;
                        }
                        break;
                    case -1031340580:
                        if (currentName.equals(ResponseConstants.BANNER_URL)) {
                            obj = 25;
                            break;
                        }
                        break;
                    case -934795532:
                        if (currentName.equals(EtsyRequest.PARAM_REGION)) {
                            obj = 14;
                            break;
                        }
                        break;
                    case -905989715:
                        if (currentName.equals(ResponseConstants.DIGITAL_LISTING_COUNT)) {
                            obj = 20;
                            break;
                        }
                        break;
                    case -892481550:
                        if (currentName.equals(ResponseConstants.STATUS)) {
                            obj = 10;
                            break;
                        }
                        break;
                    case -676204014:
                        if (currentName.equals(ResponseConstants.SHOP_LANGUAGES)) {
                            obj = 58;
                            break;
                        }
                        break;
                    case -573930140:
                        if (currentName.equals(ResponseConstants.UPDATE_DATE)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case -494058223:
                        if (currentName.equals(ResponseConstants.CREATE_DATE)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case -361924039:
                        if (currentName.equals(ResponseConstants.BRANDING_OPTION)) {
                            obj = 62;
                            break;
                        }
                        break;
                    case -344955738:
                        if (currentName.equals(ResponseConstants.SHOP_URL)) {
                            obj = 9;
                            break;
                        }
                        break;
                    case -147132913:
                        if (currentName.equals(ResponseConstants.USER_ID)) {
                            obj = BRANDING_OPTION_BANNER;
                            break;
                        }
                        break;
                    case -84191303:
                        if (currentName.equals(ResponseConstants.VACATION_AUTOREPLY)) {
                            obj = 61;
                            break;
                        }
                        break;
                    case 106911:
                        if (currentName.equals(ResponseConstants.LAT)) {
                            obj = 11;
                            break;
                        }
                        break;
                    case 107339:
                        if (currentName.equals(ResponseConstants.LON)) {
                            obj = 12;
                            break;
                        }
                        break;
                    case 116079:
                        if (currentName.equals(ResponseConstants.URL)) {
                            obj = BRANDING_OPTION_LARGE_BANNER;
                            break;
                        }
                        break;
                    case 3053931:
                        if (currentName.equals(ResponseConstants.CITY)) {
                            obj = 13;
                            break;
                        }
                        break;
                    case 3226745:
                        if (currentName.equals(ResponseConstants.ICON)) {
                            obj = 56;
                            break;
                        }
                        break;
                    case 3373707:
                        if (currentName.equals(ResponseConstants.NAME)) {
                            obj = 15;
                            break;
                        }
                        break;
                    case 53296552:
                        if (currentName.equals(ResponseConstants.HAS_ABOUT)) {
                            obj = 30;
                            break;
                        }
                        break;
                    case 106164915:
                        if (currentName.equals(ShopAboutMember.OWNER_ROLE)) {
                            obj = 57;
                            break;
                        }
                        break;
                    case 107824392:
                        if (currentName.equals(ResponseConstants.ACCEPTS_DIRECT_CHECKOUT)) {
                            obj = 37;
                            break;
                        }
                        break;
                    case 108067080:
                        if (currentName.equals(ResponseConstants.TOTAL_RATING_COUNT)) {
                            obj = 53;
                            break;
                        }
                        break;
                    case 109770997:
                        if (currentName.equals(ResponseConstants.STORY)) {
                            obj = 28;
                            break;
                        }
                        break;
                    case 140505854:
                        if (currentName.equals(ResponseConstants.HAS_ICON)) {
                            obj = 50;
                            break;
                        }
                        break;
                    case 210311733:
                        if (currentName.equals(ResponseConstants.HAS_LARGE_BANNER)) {
                            obj = 49;
                            break;
                        }
                        break;
                    case 265775609:
                        if (currentName.equals(ResponseConstants.ACCEPTS_BANK_TRANSFERS)) {
                            obj = 43;
                            break;
                        }
                        break;
                    case 475124249:
                        if (currentName.equals(ResponseConstants.SELLER_AVATAR)) {
                            obj = 6;
                            break;
                        }
                        break;
                    case 703243755:
                        if (currentName.equals(ResponseConstants.ACCEPTS_GIFT_CARD)) {
                            obj = 38;
                            break;
                        }
                        break;
                    case 727431652:
                        if (currentName.equals(ResponseConstants.SOLD_COUNT)) {
                            obj = 51;
                            break;
                        }
                        break;
                    case 749610694:
                        if (currentName.equals(ResponseConstants.HAS_ABOUT_PAGE)) {
                            obj = 31;
                            break;
                        }
                        break;
                    case 954925063:
                        if (currentName.equals(ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE)) {
                            obj = 17;
                            break;
                        }
                        break;
                    case 1077372678:
                        if (currentName.equals(ResponseConstants.GOOGLE_ANALYTICS_PROPERTY_ID)) {
                            obj = 46;
                            break;
                        }
                        break;
                    case 1108728155:
                        if (currentName.equals(ResponseConstants.CURRENCY_CODE)) {
                            obj = 34;
                            break;
                        }
                        break;
                    case 1227433863:
                        if (currentName.equals(ResponseConstants.MODULES)) {
                            obj = 64;
                            break;
                        }
                        break;
                    case 1247787851:
                        if (currentName.equals(ResponseConstants.SELLER_NAME)) {
                            obj = 7;
                            break;
                        }
                        break;
                    case 1346120344:
                        if (currentName.equals(ResponseConstants.ACCEPTS_MONEY_ORDERS)) {
                            obj = 39;
                            break;
                        }
                        break;
                    case 1358074518:
                        if (currentName.equals(ResponseConstants.ONBOARDING_STATUS)) {
                            obj = 47;
                            break;
                        }
                        break;
                    case 1481071862:
                        if (currentName.equals(ResponseConstants.COUNTRY_CODE)) {
                            obj = 33;
                            break;
                        }
                        break;
                    case 1545855107:
                        if (currentName.equals(ResponseConstants.OPEN_DATE)) {
                            obj = 21;
                            break;
                        }
                        break;
                    case 1675802800:
                        if (currentName.equals(ResponseConstants.LARGE_BANNER)) {
                            obj = 55;
                            break;
                        }
                        break;
                    case 1679861873:
                        if (currentName.equals(ResponseConstants.HAS_BANNER)) {
                            obj = 48;
                            break;
                        }
                        break;
                    case 1731047790:
                        if (currentName.equals(ResponseConstants.IS_USING_STRUCTURED_POLICIES)) {
                            obj = 22;
                            break;
                        }
                        break;
                    case 1736706526:
                        if (currentName.equals(ResponseConstants.STORY_HEADLINE)) {
                            obj = 26;
                            break;
                        }
                        break;
                    case 1750359266:
                        if (currentName.equals(ResponseConstants.HAS_LANGUGAE_PREFERENCES)) {
                            obj = 36;
                            break;
                        }
                        break;
                    case 1857491244:
                        if (currentName.equals(ResponseConstants.MESSAGE_UPDATE_DATE)) {
                            obj = 59;
                            break;
                        }
                        break;
                    case 1874097746:
                        if (currentName.equals(ResponseConstants.IS_VACATION)) {
                            obj = 24;
                            break;
                        }
                        break;
                    case 1880344578:
                        if (currentName.equals(ResponseConstants.PULL_QUOTE)) {
                            obj = 29;
                            break;
                        }
                        break;
                    case 1901043637:
                        if (currentName.equals(ResponseConstants.LOCATION)) {
                            obj = 32;
                            break;
                        }
                        break;
                    case 1903626779:
                        if (currentName.equals(ResponseConstants.ACTIVE_LISTING_COUNT)) {
                            obj = 19;
                            break;
                        }
                        break;
                    case 2031429119:
                        if (currentName.equals(ResponseConstants.AVERAGE_RATING)) {
                            obj = 52;
                            break;
                        }
                        break;
                    case 2067081988:
                        if (currentName.equals(ResponseConstants.SHOP_ID)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 2072350053:
                        if (currentName.equals(ResponseConstants.HAS_OWNERS)) {
                            obj = 44;
                            break;
                        }
                        break;
                    case 2082110527:
                        if (currentName.equals(ResponseConstants.IS_OPEN)) {
                            obj = 5;
                            break;
                        }
                        break;
                    case 2138246827:
                        if (currentName.equals(ResponseConstants.STORY_LEADING_PARAGRAPH)) {
                            obj = 27;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case BRANDING_OPTION_NO_BRANDING /*0*/:
                        this.mShopId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                        break;
                    case BRANDING_OPTION_BANNER /*1*/:
                        this.mUserId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                        break;
                    case BRANDING_OPTION_LARGE_BANNER /*2*/:
                        this.mUrl = BaseModel.parseStringURL(jsonParser);
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mCreateDate = BaseModel.parseIntoDate(jsonParser);
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.mUpdateDate = BaseModel.parseIntoDate(jsonParser);
                        break;
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        this.mIsOpen = jsonParser.getValueAsBoolean();
                        break;
                    case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                        this.mSellerAvatarUrl = BaseModel.parseStringURL(jsonParser);
                        break;
                    case CommonStatusCodes.NETWORK_ERROR /*7*/:
                        this.mSellerName = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                        this.mShopName = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SERVICE_INVALID /*9*/:
                        this.mShopUrl = BaseModel.parseStringURL(jsonParser);
                        break;
                    case CommonStatusCodes.DEVELOPER_ERROR /*10*/:
                        this.mStatus = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.LICENSE_CHECK_FAILED /*11*/:
                        this.mLatitude = jsonParser.getValueAsDouble();
                        break;
                    case ShopHomeAdapter.TYPE_BUTTON_BLUE_WITH_DESCRIPTION /*12*/:
                        this.mLongitude = jsonParser.getValueAsDouble();
                        break;
                    case CommonStatusCodes.ERROR /*13*/:
                        this.mCity = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.INTERRUPTED /*14*/:
                        this.mRegion = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.TIMEOUT /*15*/:
                        this.mName = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.CANCELED /*16*/:
                        this.mHeadline = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.API_NOT_CONNECTED /*17*/:
                        this.mMessage = BaseModel.parseString(jsonParser);
                        break;
                    case ConnectionResult.SERVICE_UPDATING /*18*/:
                        this.mMessageToBuyers = BaseModel.parseString(jsonParser);
                        break;
                    case ConnectionResult.SERVICE_MISSING_PERMISSION /*19*/:
                        this.mActiveListingCount = jsonParser.getValueAsInt();
                        break;
                    case ConnectionResult.RESTRICTED_PROFILE /*20*/:
                        this.mDigitalListingCount = jsonParser.getValueAsInt();
                        break;
                    case R.Toolbar_navigationContentDescription /*21*/:
                        this.mOpenDate = BaseModel.parseIntoDate(jsonParser);
                        break;
                    case ShopHomeAdapter.TYPE_REVIEW_RATING /*22*/:
                        this.mIsUsingStructuredPolicies = jsonParser.getValueAsBoolean();
                        break;
                    case ShopHomeAdapter.TYPE_REVIEW_APPRECIATION_PHOTO /*23*/:
                        this.mFavoritesCount = jsonParser.getValueAsInt();
                        break;
                    case ShopHomeAdapter.TYPE_REVIEW_MESSAGE /*24*/:
                        this.mIsVacation = jsonParser.getValueAsBoolean();
                        break;
                    case ShopHomeAdapter.TYPE_REVIEW_LISTING_INFO /*25*/:
                        this.mBannerUrl = BaseModel.parseStringURL(jsonParser);
                        break;
                    case ShopHomeAdapter.TYPE_ACTION_BUTTONS /*26*/:
                        this.mStoryHeadline = BaseModel.parseString(jsonParser);
                        break;
                    case ShopHomeAdapter.TYPE_FEATURED_LISTINGS_EXTRA_SPACE /*27*/:
                        this.mStoryLeadingParagraph = BaseModel.parseString(jsonParser);
                        break;
                    case ShopHomeAdapter.TYPE_STRUCTURED_POLICIES_SHIPPING /*28*/:
                        this.mStory = BaseModel.parseString(jsonParser);
                        break;
                    case ShopHomeAdapter.TYPE_STRUCTURED_POLICIES_DOWNLOADS /*29*/:
                        this.mPullQuote = BaseModel.parseString(jsonParser);
                        break;
                    case ShopHomeAdapter.TYPE_STRUCTURED_POLICIES_PAYMENTS /*30*/:
                        this.mHasAbout = jsonParser.getValueAsBoolean();
                        break;
                    case ShopHomeAdapter.TYPE_STRUCTURED_POLICIES_REFUNDS /*31*/:
                        this.mHasAboutPage = jsonParser.getValueAsBoolean();
                        break;
                    case ShopHomeAdapter.TYPE_STRUCTURED_POLICIES_PRIVACY /*32*/:
                        this.mLocation = BaseModel.parseString(jsonParser);
                        break;
                    case ShopHomeAdapter.TYPE_SELLER_DETAILS /*33*/:
                        this.mCountryCode = BaseModel.parseString(jsonParser);
                        break;
                    case ShopHomeAdapter.TYPE_FAQ_CONTENT /*34*/:
                        this.mCurrencyCode = BaseModel.parseString(jsonParser);
                        break;
                    case ShopHomeAdapter.TYPE_EMPTY_LAYOUT /*35*/:
                        this.mHasCurrencyCode = jsonParser.getValueAsBoolean();
                        break;
                    case ShopHomeAdapter.TYPE_SECTION_EXTRA_SPACE /*36*/:
                        this.mHasLanguagePreferences = jsonParser.getValueAsBoolean();
                        break;
                    case ShopHomeAdapter.TYPE_SHOP_TERMS_AND_CONDITIONS_LINK /*37*/:
                        this.mAcceptsDirectCheckout = jsonParser.getValueAsBoolean();
                        break;
                    case ShopHomeAdapter.TYPE_LOADING_WITH_DESCRIPTION /*38*/:
                        this.mAcceptsGiftCards = jsonParser.getValueAsBoolean();
                        break;
                    case ShopHomeAdapter.TYPE_SHOP_FAQ_SUBSECTION_HEADING /*39*/:
                        this.mAcceptsMoneyOrders = jsonParser.getValueAsBoolean();
                        break;
                    case R.AppCompatTheme_textAppearanceLargePopupMenu /*40*/:
                        this.mAcceptsChecks = jsonParser.getValueAsBoolean();
                        break;
                    case R.AppCompatTheme_textAppearanceSmallPopupMenu /*41*/:
                        this.mAcceptsOther = jsonParser.getValueAsBoolean();
                        break;
                    case R.AppCompatTheme_dialogTheme /*42*/:
                        this.mAcceptsPayPal = jsonParser.getValueAsBoolean();
                        break;
                    case R.AppCompatTheme_dialogPreferredPadding /*43*/:
                        this.mAcceptsBankTransfers = jsonParser.getValueAsBoolean();
                        break;
                    case R.AppCompatTheme_listDividerAlertDialog /*44*/:
                        this.mHasOwners = jsonParser.getValueAsBoolean();
                        break;
                    case R.AppCompatTheme_actionDropDownStyle /*45*/:
                        this.mAboutInfoExists = jsonParser.getValueAsBoolean();
                        break;
                    case R.AppCompatTheme_dropdownListPreferredItemHeight /*46*/:
                        this.mGoogleAnalyticsPropertyId = BaseModel.parseString(jsonParser);
                        break;
                    case R.AppCompatTheme_spinnerDropDownItemStyle /*47*/:
                        this.mOnboardingStatus = BaseModel.parseString(jsonParser);
                        break;
                    case R.AppCompatTheme_homeAsUpIndicator /*48*/:
                        this.mHasBanner = jsonParser.getValueAsBoolean();
                        break;
                    case R.AppCompatTheme_actionButtonStyle /*49*/:
                        this.mHasLargeBanner = jsonParser.getValueAsBoolean();
                        break;
                    case ScreenRecorder.TOUCH_FREQ /*50*/:
                        this.mHasIcon = jsonParser.getValueAsBoolean();
                        break;
                    case R.AppCompatTheme_buttonBarButtonStyle /*51*/:
                        this.mSoldCount = jsonParser.getValueAsInt();
                        break;
                    case R.AppCompatTheme_selectableItemBackground /*52*/:
                        this.mAverageRating = jsonParser.getValueAsDouble();
                        break;
                    case R.AppCompatTheme_selectableItemBackgroundBorderless /*53*/:
                        this.mTotalRatingCount = jsonParser.getValueAsInt();
                        break;
                    case R.AppCompatTheme_borderlessButtonStyle /*54*/:
                        this.mBanner = (Image) BaseModel.parseObject(jsonParser, Image.class);
                        break;
                    case R.AppCompatTheme_dividerVertical /*55*/:
                        this.mLargeBanner = (Image) BaseModel.parseObject(jsonParser, Image.class);
                        break;
                    case R.AppCompatTheme_dividerHorizontal /*56*/:
                        this.mShopIcon = (ShopIconV3) BaseModel.parseObject(jsonParser, ShopIconV3.class);
                        break;
                    case R.AppCompatTheme_activityChooserViewStyle /*57*/:
                        this.mOwner = (User) BaseModel.parseObject(jsonParser, User.class);
                        break;
                    case R.AppCompatTheme_toolbarStyle /*58*/:
                        this.mShopLanguages = BaseModel.parseArray(jsonParser, Language.class);
                        break;
                    case R.AppCompatTheme_toolbarNavigationButtonStyle /*59*/:
                        this.mMessageUpdateDate = BaseModel.parseIntoDate(jsonParser);
                        break;
                    case R.AppCompatTheme_popupMenuStyle /*60*/:
                        this.mVacationMessage = BaseModel.parseString(jsonParser);
                        break;
                    case R.AppCompatTheme_popupWindowStyle /*61*/:
                        this.mVacationAutoReply = BaseModel.parseString(jsonParser);
                        break;
                    case R.AppCompatTheme_editTextColor /*62*/:
                        this.mBrandingOption = jsonParser.getValueAsInt();
                        break;
                    case R.AppCompatTheme_editTextBackground /*63*/:
                        this.mListingRearrangeEnabled = jsonParser.getValueAsBoolean();
                        break;
                    case R.AppCompatTheme_imageButtonStyle /*64*/:
                        this.mModules = BaseModel.parseStringArray(jsonParser);
                        break;
                    case R.AppCompatTheme_textAppearanceSearchResultTitle /*65*/:
                        this.mHasPrivateReceiptInfo = jsonParser.getValueAsBoolean();
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }
}
