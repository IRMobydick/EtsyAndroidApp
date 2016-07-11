package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.core.http.body.BaseHttpBody;
import com.etsy.android.lib.core.http.body.FormBody;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.url.p009a.EtsyV3Urls;
import com.etsy.android.lib.models.apiv3.editable.ShopListingForm;
import com.etsy.android.lib.models.datatypes.EtsyId;

public class ListingEditRequestBuilder {
    public static EtsyApiV3Request<ShopListingForm> getListing(EtsyId etsyId, EtsyId etsyId2) {
        return (EtsyApiV3Request) EtsyApiV3Request.m1454a(ShopListingForm.class, EtsyV3Urls.m1510b(etsyId, etsyId2)).m1393d();
    }

    public static EtsyApiV3Request<ShopListingForm> putListing(EtsyId etsyId, EtsyId etsyId2, FormBody formBody) {
        return (EtsyApiV3Request) ((EtsyApiV3Request) ((EtsyApiV3Request) EtsyApiV3Request.m1454a(ShopListingForm.class, EtsyV3Urls.m1512c(etsyId, etsyId2)).m1382a(2)).m1383a((BaseHttpBody) formBody)).m1393d();
    }

    public static EtsyApiV3Request<ShopListingForm> deleteListing(EtsyId etsyId, EtsyId etsyId2) {
        return (EtsyApiV3Request) ((EtsyApiV3Request) EtsyApiV3Request.m1454a(ShopListingForm.class, EtsyV3Urls.m1514d(etsyId, etsyId2)).m1382a(3)).m1393d();
    }

    public static EtsyApiV3Request<ShopListingForm> copyListing(EtsyId etsyId, EtsyId etsyId2) {
        return (EtsyApiV3Request) ((EtsyApiV3Request) EtsyApiV3Request.m1454a(ShopListingForm.class, EtsyV3Urls.m1516e(etsyId, etsyId2)).m1382a(1)).m1393d();
    }

    public static EtsyApiV3Request<ShopListingForm> renewListing(EtsyId etsyId, EtsyId etsyId2) {
        return (EtsyApiV3Request) ((EtsyApiV3Request) EtsyApiV3Request.m1454a(ShopListingForm.class, EtsyV3Urls.m1518f(etsyId, etsyId2)).m1382a(1)).m1393d();
    }

    public static EtsyApiV3Request<ShopListingForm> deactivateListing(EtsyId etsyId, EtsyId etsyId2) {
        return (EtsyApiV3Request) ((EtsyApiV3Request) EtsyApiV3Request.m1454a(ShopListingForm.class, EtsyV3Urls.m1520g(etsyId, etsyId2)).m1382a(2)).m1393d();
    }

    public static EtsyApiV3Request<ShopListingForm> createListing(EtsyId etsyId, FormBody formBody) {
        return (EtsyApiV3Request) ((EtsyApiV3Request) ((EtsyApiV3Request) EtsyApiV3Request.m1454a(ShopListingForm.class, EtsyV3Urls.m1519g(etsyId)).m1382a(1)).m1383a((BaseHttpBody) formBody)).m1393d();
    }
}
