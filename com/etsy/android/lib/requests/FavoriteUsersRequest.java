package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.FavoriteUser;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class FavoriteUsersRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    private static final long serialVersionUID = 2824419824712854829L;

    public FavoriteUsersRequest(String str, RequestMethod requestMethod, Class<Result> cls) {
        super(str, requestMethod, cls);
    }

    public static FavoriteUsersRequest<FavoriteUser> findAllUserFavoriteUsers(EtsyId etsyId) {
        return new FavoriteUsersRequest("/users/" + etsyId.getId() + "/favorites/users", RequestMethod.GET, FavoriteUser.class);
    }

    public static FavoriteUsersRequest<FavoriteUser> findMyFavoriteUsers() {
        return new FavoriteUsersRequest("/users/__SELF__/favorites/users", RequestMethod.GET, FavoriteUser.class);
    }

    public static FavoriteUsersRequest<FavoriteUser> findUserFavoriteUsers(EtsyId etsyId) {
        return new FavoriteUsersRequest("/users/__SELF__/favorites/users/" + etsyId.getId(), RequestMethod.GET, FavoriteUser.class);
    }

    public static FavoriteUsersRequest<EmptyResult> createUserFavoriteUsers(EtsyId etsyId) {
        return new FavoriteUsersRequest("/users/__SELF__/favorites/users/" + etsyId.getId(), RequestMethod.POST, EmptyResult.class);
    }

    public static FavoriteUsersRequest<EmptyResult> deleteUserFavoriteUsers(EtsyId etsyId) {
        return new FavoriteUsersRequest("/users/__SELF__/favorites/users/" + etsyId.getId(), RequestMethod.DELETE, EmptyResult.class);
    }
}
