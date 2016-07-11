package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ShopVacation;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class ShopVacationRequest extends EtsyRequest<ShopVacation> {
    public ShopVacationRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ShopVacation.class, EndpointType.APIv3);
    }

    public ShopVacationRequest(String str, RequestMethod requestMethod, String str2) {
        super(str, requestMethod, ShopVacation.class, EndpointType.APIv3, str2);
    }

    public static ShopVacationRequest setVacation(EtsyId etsyId, Boolean bool) {
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        ShopVacationRequest shopVacationRequest = new ShopVacationRequest("/vacation", RequestMethod.POST);
        shopVacationRequest.setV3Scope(APIv3Scope.SHOP);
        shopVacationRequest.addBodyParam(ResponseConstants.IS_VACATION, bool.booleanValue() ? "true" : "false");
        return shopVacationRequest;
    }

    public static ShopVacationRequest updateVacation(EtsyId etsyId, String str) {
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        ShopVacationRequest shopVacationRequest = new ShopVacationRequest("/vacation", RequestMethod.POST, str);
        shopVacationRequest.setV3Scope(APIv3Scope.SHOP);
        return shopVacationRequest;
    }
}
