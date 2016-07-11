package com.etsy.android.lib.models;

import android.text.Html;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class ChannelItem extends BaseModel {
    private static final String TAG;
    public static final String TYPE_ADDED_TO_TREASURY = "AddedToTreasury";
    public static final String TYPE_FEEDBACK = "FeedbackReceived";
    public static final String TYPE_ITEM_PURCHASED = "ItemPurchased";
    public static final String TYPE_LISTING = "Listing";
    public static final String TYPE_SHOP = "Shop";
    public static final String TYPE_TREASURY = "Treasury";
    public static final String TYPE_USER = "User";
    private static final long serialVersionUID = 2711169116015674019L;
    private String mAction;
    private long mCreationTime;
    private boolean mIsFavorite;
    private boolean mIsInCollections;
    private EtsyId mItemId;
    private List<Listing> mListings;
    private Receipt mReceipt;
    private Review mReview;
    private Shop mShop;
    private String mSourceImage;
    private String mSourceName;
    private String mSourceSentence;
    private EtsyId mSourceUserId;
    private String mTitle;
    private Treasury mTreasury;
    private String mType;
    private User mUser;

    public enum ShopActivityItemType {
        FAVORITED_LISTING,
        PURCHASED_ITEM,
        FAVORITED_SHOP,
        ADDED_TO_TREASURY,
        LEFT_FEEDBACK,
        UNSUPPORTED;

        public static ShopActivityItemType getTypForJsonString(String str) {
            if (!bh.m3340a(str)) {
                return UNSUPPORTED;
            }
            String trim = str.trim();
            if (ChannelItem.TYPE_LISTING.equalsIgnoreCase(trim)) {
                return FAVORITED_LISTING;
            }
            if (ChannelItem.TYPE_ITEM_PURCHASED.equalsIgnoreCase(trim)) {
                return PURCHASED_ITEM;
            }
            if (ChannelItem.TYPE_SHOP.equalsIgnoreCase(trim)) {
                return FAVORITED_SHOP;
            }
            if (ChannelItem.TYPE_ADDED_TO_TREASURY.equalsIgnoreCase(trim)) {
                return ADDED_TO_TREASURY;
            }
            if (ChannelItem.TYPE_FEEDBACK.equalsIgnoreCase(trim)) {
                return LEFT_FEEDBACK;
            }
            return UNSUPPORTED;
        }
    }

    static {
        TAG = EtsyDebug.m1891a(ChannelItem.class);
    }

    public ChannelItem() {
        this.mType = StringUtils.EMPTY;
        this.mTitle = StringUtils.EMPTY;
        this.mAction = StringUtils.EMPTY;
        this.mSourceImage = StringUtils.EMPTY;
        this.mSourceName = StringUtils.EMPTY;
        this.mSourceSentence = StringUtils.EMPTY;
        this.mItemId = new EtsyId();
        this.mSourceUserId = new EtsyId();
        this.mListings = new ArrayList();
    }

    public EtsyId getItemId() {
        return this.mItemId;
    }

    public String getType() {
        return this.mType;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public EtsyId getSourceUserId() {
        return this.mSourceUserId;
    }

    public String getSourceImage() {
        return this.mSourceImage;
    }

    public String getSourceName() {
        return this.mSourceName;
    }

    public String getSourceSentence() {
        return this.mSourceSentence;
    }

    public String getAction() {
        return this.mAction;
    }

    public boolean isFavorite() {
        return this.mIsFavorite;
    }

    public boolean isInCollections() {
        return this.mIsInCollections;
    }

    public List<Listing> getListings() {
        return this.mListings;
    }

    public Treasury getTreasury() {
        return this.mTreasury;
    }

    public Shop getShop() {
        return this.mShop;
    }

    public User getUser() {
        return this.mUser;
    }

    public Receipt getReceipt() {
        return this.mReceipt;
    }

    public Review getReview() {
        return this.mReview;
    }

    public void setFavorite(boolean z) {
        this.mIsFavorite = z;
    }

    public long getCreationTime() {
        return this.mCreationTime;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("item_id".equals(currentName)) {
                    this.mItemId.setId(BaseModel.parseString(jsonParser));
                } else if (FindsModule.FIELD_TYPE.equals(currentName)) {
                    this.mType = BaseModel.parseString(jsonParser);
                } else if (FindsModule.FIELD_TITLE.equals(currentName)) {
                    this.mTitle = BaseModel.parseString(jsonParser);
                } else if ("action".equals(currentName)) {
                    this.mAction = BaseModel.parseString(jsonParser);
                } else if ("source_name".equals(currentName)) {
                    this.mSourceName = BaseModel.parseString(jsonParser);
                } else if ("source_image".equals(currentName)) {
                    this.mSourceImage = BaseModel.parseStringURL(jsonParser);
                } else if ("source_user_id".equals(currentName)) {
                    this.mSourceUserId.setId(BaseModel.parseString(jsonParser));
                } else if ("source_sentence".equals(currentName)) {
                    this.mSourceSentence = Html.fromHtml(BaseModel.parseStringPreserveHTMLEscapeEncoding(jsonParser).trim()).toString();
                } else if (ResponseConstants.IS_FAVORITE.equals(currentName)) {
                    this.mIsFavorite = jsonParser.getValueAsBoolean();
                } else if (ResponseConstants.IN_COLLECTIONS.equals(currentName)) {
                    this.mIsInCollections = jsonParser.getValueAsBoolean();
                } else if ("creation_time".equals(currentName)) {
                    this.mCreationTime = jsonParser.getValueAsLong() * 1000;
                } else if (Includes.LISTINGS.equals(currentName)) {
                    this.mListings = BaseModel.parseArray(jsonParser, Listing.class);
                } else if (TYPE_SHOP.equals(currentName)) {
                    this.mShop = (Shop) BaseModel.parseObject(jsonParser, Shop.class);
                } else if (TYPE_USER.equals(currentName)) {
                    this.mUser = (User) BaseModel.parseObject(jsonParser, User.class);
                } else if (TYPE_TREASURY.equals(currentName)) {
                    this.mTreasury = (Treasury) BaseModel.parseObject(jsonParser, Treasury.class);
                } else if ("Receipt".equals(currentName)) {
                    this.mReceipt = (Receipt) BaseModel.parseObject(jsonParser, Receipt.class);
                } else if ("Feedback".equals(currentName)) {
                    this.mReview = (Review) BaseModel.parseObject(jsonParser, Review.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
