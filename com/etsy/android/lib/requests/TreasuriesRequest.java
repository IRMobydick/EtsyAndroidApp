package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.FavoriteTreasury;
import com.etsy.android.lib.models.Treasury;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class TreasuriesRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    private static final long serialVersionUID = 975157317015733867L;

    public TreasuriesRequest(String str, RequestMethod requestMethod, Class<Result> cls) {
        super(str, requestMethod, cls);
    }

    public static TreasuriesRequest<Treasury> getTreasury(String str) {
        if (str != null) {
            return new TreasuriesRequest("/treasuries/" + str, RequestMethod.GET, Treasury.class);
        }
        throw new IllegalArgumentException("Treasury key can not be null for treasury request");
    }

    public static FavoriteUsersRequest<FavoriteTreasury> findMyFavoriteTreasuries() {
        return new FavoriteUsersRequest("/users/__SELF__/favorites/treasuries", RequestMethod.GET, FavoriteTreasury.class);
    }

    public static FavoriteUsersRequest<FavoriteTreasury> findAllUserFavoriteTreasuries(EtsyId etsyId) {
        return new FavoriteUsersRequest("/users/" + etsyId.getId() + "/favorites/treasuries", RequestMethod.GET, FavoriteTreasury.class);
    }

    public static FavoriteUsersRequest<FavoriteTreasury> findUserFavoriteTreasury(String str) {
        return new FavoriteUsersRequest("/users/__SELF__/favorites/treasuries/" + str, RequestMethod.GET, FavoriteTreasury.class);
    }

    public static FavoriteUsersRequest<EmptyResult> createUserFavoriteTreasury(String str) {
        return new FavoriteUsersRequest("/users/__SELF__/favorites/treasuries/" + str, RequestMethod.POST, EmptyResult.class);
    }

    public static FavoriteUsersRequest<EmptyResult> deleteUserFavoriteTreasury(String str) {
        return new FavoriteUsersRequest("/users/__SELF__/favorites/treasuries/" + str, RequestMethod.DELETE, EmptyResult.class);
    }
}
