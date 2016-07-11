package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class User extends BaseModel {
    protected static final long serialVersionUID = 8791766047665382345L;
    protected Image mAvatar;
    protected int mAwaitingFeedbackCount;
    protected String mEmail;
    protected List<FavoriteListing> mFavoriteListings;
    protected int mFollowerCount;
    protected int mFollowingCount;
    protected String mLoginName;
    protected UserProfile mProfile;
    protected PublicKey mPublicKey;
    protected List<Shop> mShops;
    protected List<UserAddress> mUserAddresses;
    protected EtsyId mUserId;

    public class PublicKey extends BaseModel {
        String mKeyId;
        String mKeyString;

        public String getKeyString() {
            return this.mKeyString;
        }

        public String getKeyId() {
            return this.mKeyId;
        }

        public void parseData(JsonParser jsonParser) {
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String currentName = jsonParser.getCurrentName();
                jsonParser.nextToken();
                if (ResponseConstants.KEY.equals(currentName)) {
                    this.mKeyString = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.KEY_ID.equals(currentName)) {
                    this.mKeyId = BaseModel.parseStringIdOrNumericValue(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public User() {
        this.mLoginName = StringUtils.EMPTY;
        this.mEmail = StringUtils.EMPTY;
        this.mUserId = new EtsyId();
        this.mShops = new ArrayList(0);
        this.mFavoriteListings = new ArrayList(0);
        this.mUserAddresses = new ArrayList(0);
    }

    public EtsyId getUserId() {
        return this.mUserId;
    }

    public String getLoginName() {
        return this.mLoginName;
    }

    public void setLoginName(String str) {
        this.mLoginName = str;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public void setEmail(String str) {
        this.mEmail = str;
    }

    public int getFollowerCount() {
        return this.mFollowerCount;
    }

    public int getFollowingCount() {
        return this.mFollowingCount;
    }

    public int getAwaitingFeedbackCount() {
        return this.mAwaitingFeedbackCount;
    }

    public List<Shop> getShops() {
        return this.mShops;
    }

    public UserProfile getProfile() {
        return this.mProfile;
    }

    public void setProfile(UserProfile userProfile) {
        this.mProfile = userProfile;
    }

    public List<FavoriteListing> getFavoriteListings() {
        return this.mFavoriteListings;
    }

    public List<UserAddress> getUserAddresses() {
        return this.mUserAddresses;
    }

    public PublicKey getPublicKey() {
        return this.mPublicKey;
    }

    public Shop getMainShop() {
        if (this.mShops.size() > 0) {
            return (Shop) this.mShops.get(0);
        }
        return null;
    }

    public List<Listing> getFavoritesAsListings() {
        return new ArrayList(this.mFavoriteListings);
    }

    public Image getAvatar() {
        return this.mAvatar;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.USER_ID.equals(currentName)) {
                    this.mUserId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.LOGIN_NAME.equals(currentName)) {
                    this.mLoginName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.PRIMARY_EMAIL.equals(currentName)) {
                    this.mEmail = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.FOLLOWER_COUNT.equals(currentName)) {
                    this.mFollowerCount = jsonParser.getValueAsInt();
                } else if (ResponseConstants.FOLLOWING_COUNT.equals(currentName)) {
                    this.mFollowingCount = jsonParser.getValueAsInt();
                } else if (ResponseConstants.AWAITING_FEEDBACK_COUNT.equals(currentName)) {
                    this.mAwaitingFeedbackCount = jsonParser.getValueAsInt();
                } else if (Includes.PROFILE.equals(currentName)) {
                    this.mProfile = (UserProfile) BaseModel.parseObject(jsonParser, UserProfile.class);
                } else if (Includes.FAVORITE_LISTINGS.equals(currentName)) {
                    this.mFavoriteListings = BaseModel.parseArray(jsonParser, FavoriteListing.class);
                } else if (Includes.SHOPS.equals(currentName)) {
                    this.mShops = BaseModel.parseArray(jsonParser, Shop.class);
                } else if (Includes.ADDRESSES.equals(currentName)) {
                    this.mUserAddresses = BaseModel.parseArray(jsonParser, UserAddress.class);
                } else if (ResponseConstants.USER_PUB_KEY.equals(currentName)) {
                    PublicKey publicKey = new PublicKey();
                    publicKey.parseData(jsonParser);
                    if (!(TextUtils.isEmpty(publicKey.getKeyId()) || TextUtils.isEmpty(publicKey.getKeyString()))) {
                        this.mPublicKey = publicKey;
                    }
                } else if (ResponseConstants.AVATAR.equals(currentName)) {
                    this.mAvatar = (Image) BaseModel.parseObject(jsonParser, Image.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.TARGET_USER_ID, this.mUserId.getId());
        return hashMap;
    }
}
