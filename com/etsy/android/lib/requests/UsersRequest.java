package com.etsy.android.lib.requests;

import com.etsy.android.lib.core.http.request.EtsyApiV2Request;
import com.etsy.android.lib.core.http.url.p009a.EtsyV2Urls;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.Receipt;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class UsersRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    private static final long serialVersionUID = -1424044279750512005L;

    public UsersRequest(String str, RequestMethod requestMethod, Class<Result> cls) {
        super(str, requestMethod, cls);
    }

    public static UsersRequest<User> findAllUsers() {
        return new UsersRequest("/users", RequestMethod.GET, User.class);
    }

    public static UsersRequest<User> getSelf() {
        return new UsersRequest("/users/__SELF__", RequestMethod.GET, User.class);
    }

    public static EtsyApiV2Request<User> getSelf(String str, String str2) {
        return new EtsyApiV2Request(User.class, EtsyV2Urls.m1497a()).m1437b(str).m1439c(str2).m1444h();
    }

    public static EtsyApiV2Request<User> getUser(EtsyId etsyId, String str, String str2) {
        return new EtsyApiV2Request(User.class, EtsyV2Urls.m1498a(etsyId)).m1437b(str).m1439c(str2).m1444h();
    }

    public static UsersRequest<Receipt> findAllUserBuyerReceipts() {
        return new UsersRequest("/users/__SELF__/receipts", RequestMethod.GET, Receipt.class);
    }
}
