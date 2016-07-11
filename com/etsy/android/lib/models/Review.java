package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.apiv3.AppreciationPhoto;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.bh;
import com.etsy.android.uikit.ui.shop.ShopHomeAdapter;
import com.etsy.android.uikit.util.MachineTranslationViewState;
import com.etsy.android.uikit.view.MachineTranslationButton;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class Review extends BaseModel implements MachineTranslationButton {
    public static final int BAD_RATING = 3;
    public static final int MAX_RATING = 5;
    public static final int MIN_RATING = 1;
    public static final int MIN_WORDS = 5;
    private static final long serialVersionUID = 200103804358240206L;
    protected AppreciationPhoto mAppreciationPhoto;
    protected boolean mIsListingDisplayable;
    protected Listing mListing;
    protected EtsyId mListingId;
    protected ListingImage mListingImage;
    protected String mListingImageUrl;
    protected String mListingTitle;
    protected int mRating;
    protected ReviewResponse mResponse;
    protected String mReviewLanguage;
    protected String mReviewMessage;
    protected MachineTranslationViewState mReviewTranslationState;
    protected EtsyId mTransactionId;
    protected String mTranslatedContent;

    public Review() {
        this.mReviewMessage = StringUtils.EMPTY;
        this.mTranslatedContent = StringUtils.EMPTY;
        this.mListingTitle = StringUtils.EMPTY;
        this.mListingImageUrl = StringUtils.EMPTY;
        this.mReviewLanguage = StringUtils.EMPTY;
        this.mReviewTranslationState = new MachineTranslationViewState();
        this.mListingId = new EtsyId();
        this.mTransactionId = new EtsyId();
    }

    public Review(int i, String str) {
        this.mReviewMessage = StringUtils.EMPTY;
        this.mTranslatedContent = StringUtils.EMPTY;
        this.mListingTitle = StringUtils.EMPTY;
        this.mListingImageUrl = StringUtils.EMPTY;
        this.mReviewLanguage = StringUtils.EMPTY;
        this.mReviewTranslationState = new MachineTranslationViewState();
        this.mRating = i;
        this.mReviewMessage = str;
        this.mListingId = new EtsyId();
        this.mTransactionId = new EtsyId();
    }

    public EtsyId getTransactionId() {
        return this.mTransactionId;
    }

    public EtsyId getListingId() {
        return this.mListingId;
    }

    public int getRating() {
        return this.mRating;
    }

    public String getListingTitle() {
        return this.mListingTitle;
    }

    public String getListingImageUrl() {
        return this.mListingImageUrl;
    }

    public String getReviewMessage() {
        return this.mReviewMessage;
    }

    public String getTranslatedReviewMessage() {
        return this.mTranslatedContent;
    }

    public String getReviewLanguage() {
        return this.mReviewLanguage;
    }

    public boolean isTranslationEligible() {
        return aj.m3232a(getReviewMessage(), getReviewLanguage());
    }

    public Listing getListing() {
        return this.mListing;
    }

    public void setTranslatedReviewMessage(String str) {
        this.mTranslatedContent = str;
    }

    public MachineTranslationViewState getTranslationState() {
        return this.mReviewTranslationState;
    }

    public boolean isListingDisplayable() {
        return this.mIsListingDisplayable;
    }

    public boolean hasAppreciationPhoto() {
        return this.mAppreciationPhoto != null && this.mRating == MIN_WORDS && this.mAppreciationPhoto.isActive();
    }

    public AppreciationPhoto getAppreciationPhoto() {
        return this.mAppreciationPhoto;
    }

    public ReviewResponse getReviewResponse() {
        return this.mResponse;
    }

    public boolean hasResponse() {
        return this.mResponse != null && bh.m3340a(this.mResponse.getMessage());
    }

    public String getResponse() {
        if (hasResponse()) {
            return this.mResponse.getMessage();
        }
        return StringUtils.EMPTY;
    }

    public String getFormattedListingPrice() {
        if (this.mListing == null) {
            return null;
        }
        return this.mListing.getPrice().format();
    }

    @Nullable
    public ListingImage getListingImage() {
        return this.mListingImage;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -1613589672:
                        if (currentName.equals(EtsyRequest.PARAM_LANGUAGE)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case -1095576608:
                        if (currentName.equals(ResponseConstants.LISTING_IMAGE)) {
                            obj = 12;
                            break;
                        }
                        break;
                    case -1085518627:
                        if (currentName.equals(ResponseConstants.LISTING_TITLE)) {
                            obj = BAD_RATING;
                            break;
                        }
                        break;
                    case -1080895344:
                        if (currentName.equals(ResponseConstants.LISTING_IMAGE_URL)) {
                            obj = 6;
                            break;
                        }
                        break;
                    case -1059040948:
                        if (currentName.equals(ResponseConstants.IS_LISTING_DISPLAYABLE)) {
                            obj = 11;
                            break;
                        }
                        break;
                    case -938102371:
                        if (currentName.equals(ResponseConstants.RATING)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case -934348968:
                        if (currentName.equals(ResponseConstants.TRANSLATED_REVIEW)) {
                            obj = MIN_WORDS;
                            break;
                        }
                        break;
                    case -340323263:
                        if (currentName.equals(ResponseConstants.RESPONSE)) {
                            obj = 7;
                            break;
                        }
                        break;
                    case 181975684:
                        if (currentName.equals(ActivityFeedEntity.LISTING)) {
                            obj = 8;
                            break;
                        }
                        break;
                    case 988969142:
                        if (currentName.equals(ResponseConstants.LISTING_ID)) {
                            obj = MIN_RATING;
                            break;
                        }
                        break;
                    case 1010584092:
                        if (currentName.equals(ResponseConstants.TRANSACTION_ID)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 1060712215:
                        if (currentName.equals(Includes.APPRECIATION_PHOTO)) {
                            obj = 10;
                            break;
                        }
                        break;
                    case 1412375726:
                        if (currentName.equals(ResponseConstants.APPRECIATION_PHOTO)) {
                            obj = 9;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mTransactionId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                        break;
                    case MIN_RATING /*1*/:
                        this.mListingId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mRating = jsonParser.getValueAsInt();
                        break;
                    case BAD_RATING /*3*/:
                        this.mListingTitle = BaseModel.parseString(jsonParser).trim();
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.mReviewLanguage = BaseModel.parseString(jsonParser).trim();
                        break;
                    case MIN_WORDS /*5*/:
                        String parseString = BaseModel.parseString(jsonParser);
                        this.mReviewMessage = parseString != null ? parseString.trim() : StringUtils.EMPTY;
                        break;
                    case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                        this.mListingImageUrl = BaseModel.parseStringURL(jsonParser);
                        break;
                    case CommonStatusCodes.NETWORK_ERROR /*7*/:
                        this.mResponse = (ReviewResponse) BaseModel.parseObject(jsonParser, ReviewResponse.class);
                        break;
                    case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                        this.mListing = (Listing) BaseModel.parseObject(jsonParser, Listing.class);
                        break;
                    case CommonStatusCodes.SERVICE_INVALID /*9*/:
                    case CommonStatusCodes.DEVELOPER_ERROR /*10*/:
                        this.mAppreciationPhoto = (AppreciationPhoto) BaseModel.parseObject(jsonParser, AppreciationPhoto.class);
                        break;
                    case CommonStatusCodes.LICENSE_CHECK_FAILED /*11*/:
                        this.mIsListingDisplayable = jsonParser.getValueAsBoolean();
                        break;
                    case ShopHomeAdapter.TYPE_BUTTON_BLUE_WITH_DESCRIPTION /*12*/:
                        this.mListingImage = (ListingImage) BaseModel.parseObject(jsonParser, ListingImage.class);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(MIN_RATING);
        hashMap.put(AnalyticsLogAttribute.TRANSACTION_ID, this.mTransactionId.getId());
        return hashMap;
    }
}
