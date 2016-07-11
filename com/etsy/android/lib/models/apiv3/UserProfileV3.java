package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.uikit.ui.shop.ShopHomeAdapter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class UserProfileV3 extends BaseModel {
    protected int mAppreciationPhotoCount;
    protected boolean mAreFavoriteListingsPublic;
    protected boolean mAreFavoriteShopsPublic;
    protected boolean mAreFavoriteTreasuriesPublic;
    protected String mAvatarUrl;
    protected String mBio;
    protected String mDisplayName;
    protected int mFavoriteListingsCount;
    protected int mFavoriteShopsCount;
    protected int mFavoriteTreasuriesCount;
    protected String mFirstName;
    protected int mFollowerCount;
    protected int mFollowingCount;
    protected boolean mIsAdmin;
    protected boolean mIsFollowing;
    protected boolean mIsSeller;
    protected String mLocation;
    protected String mLoginName;
    protected int mTransactionsSoldCount;
    protected EtsyId mUserId;

    public UserProfileV3() {
        this.mUserId = new EtsyId();
        this.mDisplayName = StringUtils.EMPTY;
        this.mFirstName = StringUtils.EMPTY;
        this.mLoginName = StringUtils.EMPTY;
        this.mBio = StringUtils.EMPTY;
        this.mLocation = StringUtils.EMPTY;
        this.mAvatarUrl = StringUtils.EMPTY;
    }

    public EtsyId getUserId() {
        return this.mUserId;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    public String getBio() {
        return this.mBio;
    }

    public String getLocation() {
        return this.mLocation;
    }

    public String getAvatarUrl() {
        return this.mAvatarUrl;
    }

    public int getFollowerCount() {
        return this.mFollowerCount;
    }

    public int getFollowingCount() {
        return this.mFollowingCount;
    }

    public boolean areFavoriteListingsPublic() {
        return this.mAreFavoriteTreasuriesPublic;
    }

    public int getFavoriteListingsCount() {
        return this.mFavoriteListingsCount;
    }

    public boolean areFavoriteShopsPublic() {
        return this.mAreFavoriteShopsPublic;
    }

    public int getFavoriteShopsCount() {
        return this.mFavoriteShopsCount;
    }

    public boolean areFavoriteTreasuriesPublic() {
        return this.mAreFavoriteTreasuriesPublic;
    }

    public int getFavoriteTreasuriesCount() {
        return this.mFavoriteTreasuriesCount;
    }

    public boolean isSeller() {
        return this.mIsSeller;
    }

    public int getTransactionsSoldCount() {
        return this.mTransactionsSoldCount;
    }

    public boolean isAdmin() {
        return this.mIsAdmin;
    }

    public boolean isFollowing() {
        return this.mIsFollowing;
    }

    public void setIsFollowing(boolean z) {
        this.mIsFollowing = z;
    }

    public int getAppreciationPhotoCount() {
        return this.mAppreciationPhotoCount;
    }

    public String getLoginName() {
        return this.mLoginName;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -2107390546:
                        if (currentName.equals(ResponseConstants.FOLLOWER_COUNT)) {
                            obj = 7;
                            break;
                        }
                        break;
                    case -1767320287:
                        if (currentName.equals(ResponseConstants.ARE_FAVORITE_LISTINGS_PUBLIC)) {
                            obj = 9;
                            break;
                        }
                        break;
                    case -1747812959:
                        if (currentName.equals(ResponseConstants.LOGIN_NAME)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case -1702439027:
                        if (currentName.equals(ResponseConstants.FAVORITE_TREASURIES_COUNT)) {
                            obj = 14;
                            break;
                        }
                        break;
                    case -1055898661:
                        if (currentName.equals(ResponseConstants.FAVORITE_LISTINGS_COUNT)) {
                            obj = 10;
                            break;
                        }
                        break;
                    case -545190468:
                        if (currentName.equals(ActivityFeedEntity.IS_FOLLOWING)) {
                            obj = 19;
                            break;
                        }
                        break;
                    case -451477821:
                        if (currentName.equals(ResponseConstants.ARE_FAVORITE_SHOPS_PUBLIC)) {
                            obj = 11;
                            break;
                        }
                        break;
                    case -441975756:
                        if (currentName.equals(ResponseConstants.IS_SELLER)) {
                            obj = 15;
                            break;
                        }
                        break;
                    case -402824823:
                        if (currentName.equals(ResponseConstants.AVATAR_URL)) {
                            obj = 6;
                            break;
                        }
                        break;
                    case -175782225:
                        if (currentName.equals(ResponseConstants.ARE_FAVORITE_TREASURIES_PUBLIC)) {
                            obj = 13;
                            break;
                        }
                        break;
                    case -160985414:
                        if (currentName.equals(ResponseConstants.FIRST_NAME)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case -147132913:
                        if (currentName.equals(ResponseConstants.USER_ID)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 97544:
                        if (currentName.equals(ResponseConstants.BIO)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case 107637754:
                        if (currentName.equals(ResponseConstants.IS_ADMIN)) {
                            obj = 16;
                            break;
                        }
                        break;
                    case 458536417:
                        if (currentName.equals(ResponseConstants.FOLLOWING_COUNT)) {
                            obj = 8;
                            break;
                        }
                        break;
                    case 830742078:
                        if (currentName.equals(ResponseConstants.APPRECIATION_PHOTO_COUNT)) {
                            obj = 18;
                            break;
                        }
                        break;
                    case 1313826277:
                        if (currentName.equals(ResponseConstants.TRANSACTION_SOLD_COUNT)) {
                            obj = 17;
                            break;
                        }
                        break;
                    case 1615086568:
                        if (currentName.equals(ActivityFeedSubject.DISPLAY_NAME)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 1901043637:
                        if (currentName.equals(ResponseConstants.LOCATION)) {
                            obj = 5;
                            break;
                        }
                        break;
                    case 1976152945:
                        if (currentName.equals(ResponseConstants.FAVORITE_SHOPS_COUNT)) {
                            obj = 12;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mUserId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mDisplayName = BaseModel.parseString(jsonParser);
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mLoginName = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mFirstName = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.mBio = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        this.mLocation = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                        this.mAvatarUrl = BaseModel.parseStringURL(jsonParser);
                        break;
                    case CommonStatusCodes.NETWORK_ERROR /*7*/:
                        this.mFollowerCount = jsonParser.getValueAsInt();
                        break;
                    case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                        this.mFollowingCount = jsonParser.getValueAsInt();
                        break;
                    case CommonStatusCodes.SERVICE_INVALID /*9*/:
                        this.mAreFavoriteListingsPublic = jsonParser.getValueAsBoolean();
                        break;
                    case CommonStatusCodes.DEVELOPER_ERROR /*10*/:
                        this.mFavoriteListingsCount = jsonParser.getValueAsInt();
                        break;
                    case CommonStatusCodes.LICENSE_CHECK_FAILED /*11*/:
                        this.mAreFavoriteShopsPublic = jsonParser.getValueAsBoolean();
                        break;
                    case ShopHomeAdapter.TYPE_BUTTON_BLUE_WITH_DESCRIPTION /*12*/:
                        this.mFavoriteShopsCount = jsonParser.getValueAsInt();
                        break;
                    case CommonStatusCodes.ERROR /*13*/:
                        this.mAreFavoriteTreasuriesPublic = jsonParser.getValueAsBoolean();
                        break;
                    case CommonStatusCodes.INTERRUPTED /*14*/:
                        this.mFavoriteTreasuriesCount = jsonParser.getValueAsInt();
                        break;
                    case CommonStatusCodes.TIMEOUT /*15*/:
                        this.mIsSeller = jsonParser.getValueAsBoolean();
                        break;
                    case CommonStatusCodes.CANCELED /*16*/:
                        this.mIsAdmin = jsonParser.getValueAsBoolean();
                        break;
                    case CommonStatusCodes.API_NOT_CONNECTED /*17*/:
                        this.mTransactionsSoldCount = jsonParser.getValueAsInt();
                        break;
                    case ConnectionResult.SERVICE_UPDATING /*18*/:
                        this.mAppreciationPhotoCount = jsonParser.getValueAsInt();
                        break;
                    case ConnectionResult.SERVICE_MISSING_PERMISSION /*19*/:
                        this.mIsFollowing = jsonParser.getValueAsBoolean();
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }
}
