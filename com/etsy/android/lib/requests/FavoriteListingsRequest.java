package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.FavoriteListing;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class FavoriteListingsRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    public static final int PRIVATE_ERROR_CODE = 403;
    private static final long serialVersionUID = 3396932859342834864L;

    public FavoriteListingsRequest(String str, RequestMethod requestMethod, Class<Result> cls) {
        super(str, requestMethod, cls);
    }

    public static FavoriteListingsRequest<FavoriteListing> findAllUserFavoriteListings(String str) {
        return new FavoriteListingsRequest("/users/" + str + "/favorites/listings", RequestMethod.GET, FavoriteListing.class);
    }

    public static FavoriteListingsRequest<FavoriteListing> findUserFavoriteListings(String str, EtsyId etsyId) {
        return new FavoriteListingsRequest("/users/" + str + "/favorites/listings/" + etsyId.getId(), RequestMethod.GET, FavoriteListing.class);
    }

    public static FavoriteListingsRequest<EmptyResult> createUserFavoriteListings(String str, EtsyId etsyId) {
        return new FavoriteListingsRequest("/users/" + str + "/favorites/listings/" + etsyId.getId(), RequestMethod.POST, EmptyResult.class);
    }

    public static FavoriteListingsRequest<EmptyResult> deleteUserFavoriteListings(String str, EtsyId etsyId) {
        return new FavoriteListingsRequest("/users/" + str + "/favorites/listings/" + etsyId.getId(), RequestMethod.DELETE, EmptyResult.class);
    }
}
