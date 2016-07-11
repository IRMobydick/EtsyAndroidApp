package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class Transaction extends BaseModel {
    protected static final long serialVersionUID = 9088323166398192522L;
    protected EtsyId mBuyerUserId;
    protected String mCurrencyCode;
    protected Date mFeedbackOpenDate;
    protected GiftCardDesign mGiftCardDesign;
    protected GiftCardInfo mGiftCardInfo;
    protected Image mImage;
    protected boolean mIsDigital;
    protected boolean mIsFeedbackMutable;
    protected boolean mIsGiftCard;
    protected boolean mIsQuickListing;
    protected EtsyId mListingId;
    protected ListingImage mMainImage;
    protected double mPrice;
    protected int mQuantity;
    protected Review mReview;
    protected User mSeller;
    protected String mTitle;
    protected EtsyId mTransactionId;
    protected List<Variation> mVariations;

    public Transaction() {
        this.mTitle = StringUtils.EMPTY;
        this.mCurrencyCode = StringUtils.EMPTY;
        this.mTransactionId = new EtsyId();
        this.mBuyerUserId = new EtsyId();
        this.mListingId = new EtsyId();
        this.mVariations = new ArrayList(0);
    }

    public EtsyId getTransactionId() {
        return this.mTransactionId;
    }

    public EtsyId getBuyerUserId() {
        return this.mBuyerUserId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public double getPrice() {
        return this.mPrice;
    }

    public String getCurrencyCode() {
        return this.mCurrencyCode;
    }

    public int getQuantity() {
        return this.mQuantity;
    }

    public EtsyId getListingId() {
        return this.mListingId;
    }

    @Nullable
    @Deprecated
    public ListingImage getMainImage() {
        return this.mMainImage;
    }

    public Image getImage() {
        return this.mImage;
    }

    public boolean isGiftCard() {
        return this.mIsGiftCard;
    }

    public GiftCardInfo getGiftCardInfo() {
        return this.mGiftCardInfo;
    }

    public GiftCardDesign getGiftCardDesign() {
        return this.mGiftCardDesign;
    }

    public List<Variation> getVariations() {
        return this.mVariations;
    }

    public boolean isFeedbackMutable() {
        return this.mIsFeedbackMutable;
    }

    public Date getFeedbackOpenDate() {
        return this.mFeedbackOpenDate;
    }

    public Review getReview() {
        return this.mReview;
    }

    public boolean isDigitalDownload() {
        return this.mIsDigital;
    }

    public boolean isQuickListing() {
        return this.mIsQuickListing;
    }

    public User getSeller() {
        return this.mSeller;
    }

    public String getFormattedPrice() {
        return new EtsyMoney(getPrice(), getCurrencyCode()).format();
    }

    public boolean hasVariations() {
        return this.mVariations != null && this.mVariations.size() > 0;
    }

    public boolean hasFutureReviewDate() {
        return (this.mIsGiftCard || this.mFeedbackOpenDate == null || !this.mFeedbackOpenDate.after(new Date())) ? false : true;
    }

    public void setReview(Review review) {
        this.mReview = review;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.PRICE.equals(currentName)) {
                    this.mPrice = jsonParser.getValueAsDouble();
                } else if (ResponseConstants.CURRENCY_CODE.equals(currentName)) {
                    this.mCurrencyCode = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.QUANTITY.equals(currentName)) {
                    this.mQuantity = jsonParser.getValueAsInt();
                } else if (ResponseConstants.TRANSACTION_ID.equals(currentName)) {
                    this.mTransactionId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.LISTING_ID.equals(currentName)) {
                    this.mListingId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.IS_GIFT_CARD.equals(currentName)) {
                    this.mIsGiftCard = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.IS_FEEDBACK_MUTABLE.equals(currentName)) {
                    this.mIsFeedbackMutable = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.IS_DIGITAL.equals(currentName)) {
                    this.mIsDigital = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.IS_QUICK_LISTING.equals(currentName)) {
                    this.mIsQuickListing = jsonParser.getValueAsBoolean();
                } else if (FindsModule.FIELD_TITLE.equals(currentName)) {
                    this.mTitle = BaseModel.parseString(jsonParser);
                } else if (Includes.MAINIMAGE.equals(currentName)) {
                    this.mMainImage = (ListingImage) BaseModel.parseObject(jsonParser, ListingImage.class);
                } else if (ResponseConstants.IMAGE.equals(currentName)) {
                    this.mImage = (Image) BaseModel.parseObject(jsonParser, Image.class);
                } else if (ResponseConstants.VARIATIONS.equals(currentName)) {
                    this.mVariations = BaseModel.parseArray(jsonParser, Variation.class);
                } else if (ResponseConstants.FEEDBACK_OPEN_DATE.equals(currentName)) {
                    long valueAsLong = jsonParser.getValueAsLong();
                    Calendar instance = Calendar.getInstance();
                    instance.setTimeInMillis(valueAsLong * 1000);
                    this.mFeedbackOpenDate = instance.getTime();
                } else if (ResponseConstants.GIFT_CARD_INFO.equals(currentName)) {
                    if (jsonParser.getCurrentToken() != JsonToken.START_ARRAY) {
                        this.mGiftCardInfo = (GiftCardInfo) BaseModel.parseObject(jsonParser, GiftCardInfo.class);
                    } else if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                        this.mGiftCardInfo = (GiftCardInfo) BaseModel.parseObject(jsonParser, GiftCardInfo.class);
                        return;
                    }
                } else if (Includes.GIFT_CARD_DESIGN.equals(currentName)) {
                    this.mGiftCardDesign = (GiftCardDesign) BaseModel.parseObject(jsonParser, GiftCardDesign.class);
                } else if (Includes.USER_REVIEW.equals(currentName) || ResponseConstants.TRANSLATED_REVIEW.equals(currentName)) {
                    this.mReview = (Review) BaseModel.parseObject(jsonParser, Review.class);
                } else if (Includes.SELLER.equals(currentName)) {
                    this.mSeller = (User) BaseModel.parseObject(jsonParser, User.class);
                } else if (ResponseConstants.BUYER_USER_ID.equals(currentName)) {
                    this.mBuyerUserId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public int hashCode() {
        return this.mTransactionId.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Transaction) {
            return ((Transaction) obj).getTransactionId().equals(getTransactionId());
        }
        return super.equals(obj);
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.TRANSACTION_ID, this.mTransactionId.getId());
        return hashMap;
    }
}
