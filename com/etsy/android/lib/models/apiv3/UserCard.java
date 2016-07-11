package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class UserCard extends BaseModel {
    private static final String CASUAL_NAME_OR_LOGIN_NAME = "casual_name_or_login_name";
    private static final String FAVORITE_LISTINGS_COUNT = "favoritelistings_count";
    private static final String MASKED_EMAIL = "masked_email";
    private static final String REAL_LOGIN_NAME_OR_LOGIN_NAME = "real_name_or_login_name";
    private static final String RECENT_FAVORITES = "recent_favorites";
    private static final long serialVersionUID = -2150555699254286624L;
    protected String mAvatarUrl;
    protected String mCasualNameOrLogin;
    protected int mFavoritesCount;
    protected int mFollowersCount;
    protected String mLocationDescription;
    protected String mLoginName;
    protected String mMaskedEmail;
    protected String mRealNameOrLogin;
    protected List<ListingCard> mRecentFavorites;
    protected EtsyId mUserId;

    public UserCard() {
        this.mUserId = new EtsyId();
    }

    public EtsyId getUserId() {
        return this.mUserId;
    }

    public String getLoginName() {
        return this.mLoginName;
    }

    public String getAvatarUrl() {
        return this.mAvatarUrl;
    }

    public String getRealNameOrLoginName() {
        return this.mRealNameOrLogin;
    }

    public String getCasualNameOrLoginName() {
        return this.mCasualNameOrLogin;
    }

    public int getFollowersCount() {
        return this.mFollowersCount;
    }

    public int getFavoritesCount() {
        return this.mFavoritesCount;
    }

    public List<ListingCard> getRecentFavorites() {
        return this.mRecentFavorites;
    }

    public String getLocationDescription() {
        return this.mLocationDescription;
    }

    public String getMaskedEmail() {
        return this.mMaskedEmail;
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
                } else if (ResponseConstants.AVATAR_URL.equals(currentName)) {
                    this.mAvatarUrl = BaseModel.parseStringURL(jsonParser);
                } else if (REAL_LOGIN_NAME_OR_LOGIN_NAME.equals(currentName)) {
                    this.mRealNameOrLogin = BaseModel.parseString(jsonParser);
                } else if (CASUAL_NAME_OR_LOGIN_NAME.equals(currentName)) {
                    this.mCasualNameOrLogin = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.FOLLOWER_COUNT.equals(currentName)) {
                    this.mFollowersCount = jsonParser.getIntValue();
                } else if (RECENT_FAVORITES.equals(currentName)) {
                    this.mRecentFavorites = BaseModel.parseArray(jsonParser, ListingCard.class);
                } else if (FAVORITE_LISTINGS_COUNT.equals(currentName)) {
                    this.mFavoritesCount = jsonParser.getIntValue();
                } else if (ResponseConstants.LOCATION.equals(currentName)) {
                    this.mLocationDescription = BaseModel.parseString(jsonParser);
                } else if (MASKED_EMAIL.equals(currentName)) {
                    this.mMaskedEmail = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
