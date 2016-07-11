package com.etsy.android.lib.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.lib.models.apiv3.FAQ;
import com.etsy.android.lib.models.apiv3.FAQs;
import com.etsy.android.lib.models.apiv3.SellerDetails;
import com.etsy.android.lib.models.apiv3.ShopIcon;
import com.etsy.android.lib.models.apiv3.StructuredShopPolicies;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.models.interfaces.ShopLike;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Shop extends BaseModel implements ShopLike {
    public static final String FIELD_HAS_TAX_PREFERENCES = "has_tax_preferences";
    public static final String STATUS_ACTIVE = "active";
    public static final String STATUS_FROZEN = "frozen";
    private static final long serialVersionUID = -373382326299503630L;
    private ShopAbout mAbout;
    private String mAnnouncement;
    private double mAverageRating;
    private Date mCreationDate;
    private String mCurrencyCode;
    private CustomShopsState mCustomShopsState;
    private int mDigitalListingCount;
    private FAQs mFaqs;
    private List<Listing> mFeatured;
    private boolean mHasOnboardedStructuredPolicies;
    private boolean mHasPrivateReceiptInfo;
    private boolean mHasTaxPreferences;
    private boolean mHasUnstructuredPolicies;
    private String mImageUrl760x100;
    private boolean mInPersonDisplayable;
    private String mInPersonReaderEligibility;
    private String mInPersonSellState;
    private boolean mIsOnVacation;
    private boolean mIsUsingStructuredPolicies;
    private List<String> mLanguages;
    private int mListingActiveCount;
    private List<Listing> mListings;
    private String mLoginName;
    private int mNumFavorers;
    private int mNumRatings;
    private List<PaymentTemplate> mPaymentInfo;
    private String mPolicyAdditional;
    private String mPolicyPayment;
    private String mPolicyRefunds;
    private String mPolicyShipping;
    private String mPolicyWelcome;
    private List<ReceiptReview> mReceiptReviews;
    private String mSaleMessage;
    private List<ShopSection> mSections;
    private SellerDetails mSellerDetails;
    private ShopIcon mShopIcon;
    private EtsyId mShopId;
    private String mShopName;
    private String mShopOwnerName;
    private String mStatus;
    private StructuredShopPolicies mStructuredPolicies;
    private String mTitle;
    private LocalMarket mUpcomingLocalEvent;
    private String mUrl;
    private User mUser;
    private EtsyId mUserId;
    private String mVacationAutoReply;
    private String mVacationMessage;

    public enum CustomShopsState {
        DISABLED(0),
        ENABLED(1),
        DELETED(2),
        PREVIEW(3),
        TRIAL(4),
        DC_DISABLED(5);
        
        private int state;

        private CustomShopsState(int i) {
            this.state = i;
        }

        public int getStateInt() {
            return this.state;
        }

        public boolean shouldShowCustomShopsContent() {
            return this == TRIAL || this == ENABLED;
        }

        public static CustomShopsState resolveCustomShopSate(int i) {
            switch (i) {
                case Task.NETWORK_STATE_CONNECTED /*0*/:
                    return DISABLED;
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    return ENABLED;
                case Task.NETWORK_STATE_ANY /*2*/:
                    return DELETED;
                case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                    return PREVIEW;
                case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                    return TRIAL;
                case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                    return DC_DISABLED;
                default:
                    return DISABLED;
            }
        }
    }

    public Shop() {
        this.mStatus = StringUtils.EMPTY;
        this.mTitle = StringUtils.EMPTY;
        this.mShopName = StringUtils.EMPTY;
        this.mAnnouncement = StringUtils.EMPTY;
        this.mLoginName = StringUtils.EMPTY;
        this.mShopOwnerName = StringUtils.EMPTY;
        this.mUrl = StringUtils.EMPTY;
        this.mImageUrl760x100 = StringUtils.EMPTY;
        this.mVacationMessage = StringUtils.EMPTY;
        this.mVacationAutoReply = StringUtils.EMPTY;
        this.mSaleMessage = StringUtils.EMPTY;
        this.mCurrencyCode = StringUtils.EMPTY;
        this.mPolicyWelcome = StringUtils.EMPTY;
        this.mPolicyPayment = StringUtils.EMPTY;
        this.mPolicyShipping = StringUtils.EMPTY;
        this.mPolicyRefunds = StringUtils.EMPTY;
        this.mPolicyAdditional = StringUtils.EMPTY;
        this.mInPersonDisplayable = false;
        initLists();
    }

    public Shop(String str) {
        this.mStatus = StringUtils.EMPTY;
        this.mTitle = StringUtils.EMPTY;
        this.mShopName = StringUtils.EMPTY;
        this.mAnnouncement = StringUtils.EMPTY;
        this.mLoginName = StringUtils.EMPTY;
        this.mShopOwnerName = StringUtils.EMPTY;
        this.mUrl = StringUtils.EMPTY;
        this.mImageUrl760x100 = StringUtils.EMPTY;
        this.mVacationMessage = StringUtils.EMPTY;
        this.mVacationAutoReply = StringUtils.EMPTY;
        this.mSaleMessage = StringUtils.EMPTY;
        this.mCurrencyCode = StringUtils.EMPTY;
        this.mPolicyWelcome = StringUtils.EMPTY;
        this.mPolicyPayment = StringUtils.EMPTY;
        this.mPolicyShipping = StringUtils.EMPTY;
        this.mPolicyRefunds = StringUtils.EMPTY;
        this.mPolicyAdditional = StringUtils.EMPTY;
        this.mInPersonDisplayable = false;
        this.mShopName = str;
        initLists();
    }

    private void initLists() {
        this.mShopId = new EtsyId();
        this.mUserId = new EtsyId();
        this.mListings = new ArrayList(0);
        this.mFeatured = new ArrayList(0);
        this.mPaymentInfo = new ArrayList(0);
        this.mSections = new ArrayList(0);
        this.mReceiptReviews = new ArrayList(0);
        this.mLanguages = new ArrayList(0);
        this.mFaqs = new FAQs();
    }

    public LocalMarket getUpcomingLocalEvent() {
        return this.mUpcomingLocalEvent;
    }

    public boolean hasUpcomingLocalEvent() {
        return this.mUpcomingLocalEvent != null;
    }

    public List<? extends ListingLike> getCardListings() {
        if (getFeaturedListings().size() <= 0) {
            return new ArrayList(getListings());
        }
        List<? extends ListingLike> arrayList = new ArrayList(getListings());
        for (Listing remove : getFeaturedListings()) {
            arrayList.remove(remove);
        }
        arrayList.addAll(0, getFeaturedListings());
        return arrayList;
    }

    public String getLocation() {
        if (getUser() == null || getUser().getProfile() == null) {
            return StringUtils.EMPTY;
        }
        return bh.m3335a(getUser().getProfile());
    }

    public String getAvatarUrl() {
        if (getUser() == null || getUser().getProfile() == null) {
            return StringUtils.EMPTY;
        }
        return getUser().getProfile().getImageUrl75x75();
    }

    public boolean isActive() {
        return STATUS_ACTIVE.equalsIgnoreCase(this.mStatus);
    }

    public boolean isFrozen() {
        return STATUS_FROZEN.equalsIgnoreCase(this.mStatus);
    }

    public boolean hasPolicies() {
        return bh.m3340a(this.mPolicyWelcome) || bh.m3340a(this.mPolicyPayment) || bh.m3340a(this.mPolicyShipping) || bh.m3340a(this.mPolicyRefunds) || bh.m3340a(this.mPolicyAdditional);
    }

    public boolean hasAnnouncement() {
        return bh.m3340a(this.mAnnouncement);
    }

    public boolean hasAbout() {
        return this.mAbout != null && (bh.m3340a(this.mAbout.getStoryHeadline()) || bh.m3340a(this.mAbout.getStory()) || this.mAbout.getMembers().size() > 0);
    }

    public EtsyId getShopId() {
        return this.mShopId;
    }

    public EtsyId getUserId() {
        return this.mUserId;
    }

    public String getStatus() {
        return this.mStatus;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getHeadline() {
        return this.mTitle;
    }

    public String getShopName() {
        return this.mShopName;
    }

    public String getAnnouncement() {
        return this.mAnnouncement;
    }

    public int getListingActiveCount() {
        return this.mListingActiveCount;
    }

    public int getDigitalListingCount() {
        return this.mDigitalListingCount;
    }

    public boolean hasOnlyDigitalListings() {
        return getDigitalListingCount() > 0 && getListingActiveCount() == getDigitalListingCount();
    }

    public boolean isOnVacation() {
        return this.mIsOnVacation;
    }

    public String getVacationMessage() {
        return this.mVacationMessage;
    }

    public String getVacationAutoReply() {
        return this.mVacationAutoReply;
    }

    public String getLoginName() {
        return this.mLoginName;
    }

    public String getShopOwnerName() {
        return this.mShopOwnerName;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getImageUrl760x100() {
        return this.mImageUrl760x100;
    }

    public String getPolicyWelcome() {
        return this.mPolicyWelcome;
    }

    public String getPolicyPayment() {
        return this.mPolicyPayment;
    }

    public String getPolicyShipping() {
        return this.mPolicyShipping;
    }

    public String getPolicyRefunds() {
        return this.mPolicyRefunds;
    }

    public String getPolicyAdditional() {
        return this.mPolicyAdditional;
    }

    public int getNumFavorers() {
        return this.mNumFavorers;
    }

    public User getUser() {
        return this.mUser;
    }

    public ShopAbout getAbout() {
        return this.mAbout;
    }

    public List<Listing> getListings() {
        return this.mListings;
    }

    public List<Listing> getFeaturedListings() {
        return this.mFeatured;
    }

    public List<PaymentTemplate> getPaymentInfo() {
        return this.mPaymentInfo;
    }

    public List<ShopSection> getSections() {
        return this.mSections;
    }

    public List<ReceiptReview> getReceiptReviews() {
        return this.mReceiptReviews;
    }

    public int getNumRatings() {
        return this.mNumRatings;
    }

    public double getAverageRating() {
        return this.mAverageRating;
    }

    public Date getShopOpenDate() {
        return this.mCreationDate;
    }

    public String getMessageToBuyers() {
        return this.mSaleMessage;
    }

    public String getCurrencyCode() {
        return this.mCurrencyCode;
    }

    public boolean isInPersonDisplayable() {
        return this.mInPersonDisplayable;
    }

    public String getInPersonSellState() {
        return this.mInPersonSellState;
    }

    public String getInPersonReaderEligibility() {
        return this.mInPersonReaderEligibility;
    }

    public boolean hasTaxPreferences() {
        return this.mHasTaxPreferences;
    }

    public ShopIcon getShopIcon() {
        return this.mShopIcon;
    }

    public String getIconUrl(int i) {
        if (this.mShopIcon != null) {
            return this.mShopIcon.getImageUrlForPixelWidth(i);
        }
        return null;
    }

    public String getIconUrlOrDefault(int i) {
        if (this.mShopIcon != null) {
            return this.mShopIcon.getImageUrlForPixelWidth(i);
        }
        return ShopIcon.getDefaultShopUrlForPixelWidth(i);
    }

    public boolean hasRatings() {
        return this.mNumRatings > 0;
    }

    public boolean acceptsGiftCards() {
        for (PaymentTemplate allowCc : this.mPaymentInfo) {
            if (allowCc.getAllowCc()) {
                return true;
            }
        }
        return false;
    }

    public String getFirstShopLanguageCode() {
        if (this.mLanguages.size() > 0) {
            return (String) this.mLanguages.get(0);
        }
        return StringUtils.EMPTY;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.SHOP_ID.equals(currentName)) {
                    this.mShopId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.USER_ID.equals(currentName)) {
                    this.mUserId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.STATUS.equals(currentName)) {
                    this.mStatus = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.IS_VACATION.equals(currentName)) {
                    this.mIsOnVacation = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.VACATION_MESSAGE.equals(currentName)) {
                    this.mVacationMessage = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.VACATION_AUTOREPLY.equals(currentName)) {
                    this.mVacationAutoReply = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.SALE_MESSAGE.equals(currentName)) {
                    this.mSaleMessage = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.LISTING_ACTIVE_COUNT.equals(currentName)) {
                    this.mListingActiveCount = jsonParser.getValueAsInt();
                } else if (ResponseConstants.DIGITAL_LISTING_COUNT.equals(currentName)) {
                    this.mDigitalListingCount = jsonParser.getValueAsInt();
                } else if (Includes.UPCOMING_LOCAL_EVENT.equals(currentName)) {
                    this.mUpcomingLocalEvent = (LocalMarket) BaseModel.parseObject(jsonParser, LocalMarket.class);
                } else if (FindsModule.FIELD_TITLE.equals(currentName)) {
                    this.mTitle = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SHOP_NAME.equals(currentName)) {
                    this.mShopName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.ANNOUNCEMENT.equals(currentName)) {
                    this.mAnnouncement = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.LOGIN_NAME.equals(currentName)) {
                    this.mLoginName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.NAME.equals(currentName)) {
                    this.mShopOwnerName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.URL.equals(currentName)) {
                    this.mUrl = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.IMAGE_URL_760X100.equals(currentName)) {
                    this.mImageUrl760x100 = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.POLICY_WELCOME.equals(currentName)) {
                    this.mPolicyWelcome = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.POLICY_PAYMENT.equals(currentName)) {
                    this.mPolicyPayment = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.POLICY_REFUNDS.equals(currentName)) {
                    this.mPolicyRefunds = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.POLICY_SHIPPING.equals(currentName)) {
                    this.mPolicyShipping = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.POLICY_ADDITIONAL.equals(currentName)) {
                    this.mPolicyAdditional = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.CURRENCY_CODE.equals(currentName)) {
                    this.mCurrencyCode = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.NUM_FAVORERS.equals(currentName)) {
                    this.mNumFavorers = jsonParser.getValueAsInt();
                } else if (ResponseConstants.TOTAL_RATING_COUNT.equals(currentName)) {
                    this.mNumRatings = jsonParser.getValueAsInt();
                } else if (ResponseConstants.AVERAGE_RATING.equals(currentName)) {
                    this.mAverageRating = jsonParser.getValueAsDouble();
                } else if (ResponseConstants.CREATION_TSZ.equals(currentName)) {
                    this.mCreationDate = BaseModel.parseIntoDate(jsonParser);
                } else if (ResponseConstants.LANGUAGES.equals(currentName)) {
                    this.mLanguages = BaseModel.parseStringArray(jsonParser);
                } else if (FIELD_HAS_TAX_PREFERENCES.equals(currentName)) {
                    this.mHasTaxPreferences = jsonParser.getValueAsBoolean();
                } else if (Includes.USER.equals(currentName)) {
                    this.mUser = (User) BaseModel.parseObject(jsonParser, User.class);
                } else if (Includes.ABOUT.equals(currentName)) {
                    this.mAbout = (ShopAbout) BaseModel.parseObject(jsonParser, ShopAbout.class);
                } else if (Includes.LISTINGS.equals(currentName)) {
                    this.mListings = BaseModel.parseArray(jsonParser, Listing.class);
                } else if (Includes.DISPLAYED_FEATURED_LISTINGS.equals(currentName)) {
                    this.mFeatured = BaseModel.parseArray(jsonParser, Listing.class);
                } else if (Includes.PAYMENT_TEMPLATES.equals(currentName)) {
                    this.mPaymentInfo = BaseModel.parseArray(jsonParser, PaymentTemplate.class);
                } else if (Includes.SECTIONS.equals(currentName)) {
                    this.mSections = BaseModel.parseArray(jsonParser, ShopSection.class);
                } else if (Includes.REVIEWS.equals(currentName)) {
                    this.mReceiptReviews = BaseModel.parseArray(jsonParser, ReceiptReview.class);
                } else if (ResponseConstants.IN_PERSON_DISPLAYABLE.equals(currentName)) {
                    this.mInPersonDisplayable = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.IN_PERSON_SELL_STATE.equals(currentName)) {
                    this.mInPersonSellState = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.IN_PERSON_READER_ELIGIBILITY.equals(currentName)) {
                    this.mInPersonReaderEligibility = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SHOP_ICON_URL_FULL.equals(currentName)) {
                    this.mShopIcon = new ShopIcon(BaseModel.parseStringURL(jsonParser));
                } else if (ResponseConstants.IS_USING_STRUCTURED_POLICIES.equals(currentName)) {
                    this.mIsUsingStructuredPolicies = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.HAS_ONBOARDED_STRUCTURED_POLICIES.equals(currentName)) {
                    this.mHasOnboardedStructuredPolicies = jsonParser.getValueAsBoolean();
                } else if (Includes.STRUCTURED_POLICIES.equals(currentName)) {
                    this.mStructuredPolicies = (StructuredShopPolicies) BaseModel.parseObject(jsonParser, StructuredShopPolicies.class);
                } else if (ResponseConstants.HAS_UNSTRUCTURED_POLICIES.equals(currentName)) {
                    this.mHasUnstructuredPolicies = jsonParser.getValueAsBoolean();
                } else if (Includes.SELLER_DETAILS.equals(currentName)) {
                    this.mSellerDetails = (SellerDetails) BaseModel.parseObject(jsonParser, SellerDetails.class);
                } else if (Includes.FAQ.equals(currentName)) {
                    this.mFaqs.clear();
                    this.mFaqs.addAll(BaseModel.parseArray(jsonParser, FAQ.class));
                } else if (ResponseConstants.HAS_PRIVATE_RECEIPT_INFO.equals(currentName)) {
                    this.mHasPrivateReceiptInfo = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.CUSTOM_SHOPS_STATE.equals(currentName)) {
                    this.mCustomShopsState = CustomShopsState.resolveCustomShopSate(jsonParser.getValueAsInt());
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.SHOP_ID, this.mShopId.getId());
        return hashMap;
    }

    public boolean isUsingStructuredPolicies() {
        return this.mIsUsingStructuredPolicies;
    }

    public void setIsUsingStructuredPolicies(boolean z) {
        this.mIsUsingStructuredPolicies = z;
    }

    public boolean hasOnboardedStructuredPolicies() {
        return this.mHasOnboardedStructuredPolicies;
    }

    public boolean hasUnstructuredPolicies() {
        return this.mHasUnstructuredPolicies;
    }

    @Nullable
    public StructuredShopPolicies getStructuredPolicies() {
        return this.mStructuredPolicies;
    }

    public void setStructuredPolicies(StructuredShopPolicies structuredShopPolicies) {
        this.mStructuredPolicies = structuredShopPolicies;
    }

    public boolean hasPrivateReceiptInfo() {
        return this.mHasPrivateReceiptInfo;
    }

    @Nullable
    public CustomShopsState getCustomShopsState() {
        return this.mCustomShopsState;
    }

    @Nullable
    public SellerDetails getSellerDetails() {
        return this.mSellerDetails;
    }

    public void setSellerDetails(SellerDetails sellerDetails) {
        this.mSellerDetails = sellerDetails;
    }

    @NonNull
    public FAQs getFAQs() {
        return this.mFaqs;
    }

    @Nullable
    public FAQ findFaqById(EtsyId etsyId) {
        Iterator it = this.mFaqs.iterator();
        while (it.hasNext()) {
            FAQ faq = (FAQ) it.next();
            if (faq.getFaqId().equals(etsyId)) {
                return faq;
            }
        }
        return null;
    }
}
