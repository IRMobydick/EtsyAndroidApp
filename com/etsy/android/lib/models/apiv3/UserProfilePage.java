package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class UserProfilePage extends BaseModel {
    protected List<AppreciationPhoto> mAppreciationPhotos;
    protected List<Collection> mCollections;
    protected List<ShopCard> mFavoriteShops;
    protected List<TreasuryV3> mFavoriteTreasuries;
    protected ShopCard mShopCard;
    protected UserProfileV3 mUserProfile;

    public UserProfilePage() {
        this.mCollections = new ArrayList(0);
        this.mFavoriteShops = new ArrayList(0);
        this.mFavoriteTreasuries = new ArrayList(0);
        this.mAppreciationPhotos = new ArrayList(0);
    }

    public UserProfileV3 getUserProfile() {
        return this.mUserProfile;
    }

    public ShopCard getShopCard() {
        return this.mShopCard;
    }

    public List<Collection> getCollections() {
        return this.mCollections;
    }

    public List<ShopCard> getFavoriteShops() {
        return this.mFavoriteShops;
    }

    public List<TreasuryV3> getFavoriteTreasuries() {
        return this.mFavoriteTreasuries;
    }

    public List<AppreciationPhoto> getAppreciationPhotos() {
        return this.mAppreciationPhotos;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -2104245575:
                        if (currentName.equals(ResponseConstants.SHOP_CARD)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case -1420489766:
                        if (currentName.equals(ResponseConstants.FAVORITE_SHOPS)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case -127074012:
                        if (currentName.equals(ResponseConstants.FAVORITE_TREASURIES)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case 833974661:
                        if (currentName.equals(ResponseConstants.APPRECIATION_PHOTOS)) {
                            obj = 5;
                            break;
                        }
                        break;
                    case 1216225589:
                        if (currentName.equals(ResponseConstants.USER_PROFILE)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 1853891989:
                        if (currentName.equals(ResponseConstants.COLLECTIONS)) {
                            obj = 2;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mUserProfile = (UserProfileV3) BaseModel.parseObject(jsonParser, UserProfileV3.class);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mShopCard = (ShopCard) BaseModel.parseObject(jsonParser, ShopCard.class);
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mCollections = BaseModel.parseArray(jsonParser, Collection.class);
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mFavoriteShops = BaseModel.parseArray(jsonParser, ShopCard.class);
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.mFavoriteTreasuries = BaseModel.parseArray(jsonParser, TreasuryV3.class);
                        break;
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        this.mAppreciationPhotos = BaseModel.parseArray(jsonParser, AppreciationPhoto.class);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }
}
