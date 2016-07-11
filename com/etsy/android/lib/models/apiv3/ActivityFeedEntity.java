package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class ActivityFeedEntity extends BaseModel {
    public static final String DATA = "data";
    public static final String DATASET = "dataset";
    public static final String IS_FAVORITED = "is_favorited";
    public static final String IS_FOLLOWING = "is_following";
    public static final String LISTING = "listing";
    public static final String SHOP = "shop";
    public static final String TREASURY = "treasury";
    public static final String TYPE_ID = "type_id";
    public static final String USER = "user";
    private static final long serialVersionUID = -2564040844165359558L;
    protected BaseModel mData;
    private boolean mInCollections;
    private boolean mIsFavorited;
    private boolean mIsFollowing;
    protected long mObjectId;
    protected String mType;
    protected int mTypeId;

    public String getType() {
        return this.mType;
    }

    public int getTypeId() {
        return this.mTypeId;
    }

    public long getObjectId() {
        return this.mObjectId;
    }

    public BaseModel getData() {
        return this.mData;
    }

    public boolean isFavorite() {
        return this.mIsFavorited;
    }

    public boolean isFollowing() {
        return this.mIsFollowing;
    }

    public boolean inCollections() {
        return this.mInCollections;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                parseField(jsonParser, currentName);
            }
        }
    }

    protected void parseField(JsonParser jsonParser, String str) {
        if (FindsModule.FIELD_TYPE.equals(str)) {
            this.mType = BaseModel.parseString(jsonParser);
        } else if (TYPE_ID.equals(str)) {
            this.mTypeId = jsonParser.getValueAsInt();
        } else if (ResponseConstants.OBJECT_ID.equals(str)) {
            this.mObjectId = jsonParser.getValueAsLong();
        } else if (LISTING.equals(str)) {
            this.mData = BaseModel.parseObject(jsonParser, ListingCard.class);
        } else if (USER.equals(str)) {
            this.mData = BaseModel.parseObject(jsonParser, UserCard.class);
        } else if (SHOP.equals(str)) {
            this.mData = BaseModel.parseObject(jsonParser, ShopFeedItem.class);
        } else if (TREASURY.equals(str)) {
            this.mData = BaseModel.parseObject(jsonParser, TreasuryV3.class);
        } else if (IS_FAVORITED.equals(str)) {
            this.mIsFavorited = jsonParser.getValueAsBoolean();
        } else if (IS_FOLLOWING.equals(str)) {
            this.mIsFollowing = jsonParser.getValueAsBoolean();
        } else if (ResponseConstants.IN_COLLECTIONS.equals(str)) {
            this.mInCollections = jsonParser.getValueAsBoolean();
        } else if (DATA.equals(str)) {
            parseField(jsonParser, this.mType);
        } else {
            jsonParser.skipChildren();
        }
    }
}
