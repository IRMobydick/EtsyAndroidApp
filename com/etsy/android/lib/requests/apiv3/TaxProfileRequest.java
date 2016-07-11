package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.apiv3.ipp.TaxProfile;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class TaxProfileRequest extends EtsyRequest<TaxProfile> {
    public TaxProfileRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, TaxProfile.class, EndpointType.APIv3);
        setV3Scope(APIv3Scope.SHOP);
    }

    public static TaxProfileRequest getDefaultTaxProfile(EtsyId etsyId) {
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        return new TaxProfileRequest("/in-person/tax-preferences/default", RequestMethod.GET);
    }

    public static TaxProfileRequest getTaxProfiles(EtsyId etsyId) {
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        return new TaxProfileRequest("/in-person/tax-preferences", RequestMethod.GET);
    }

    public static TaxProfileRequest createTaxProfile(EtsyId etsyId) {
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        return new TaxProfileRequest("/in-person/tax-preferences", RequestMethod.POST);
    }

    public static TaxProfileRequest editTaxProfile(EtsyId etsyId, EtsyId etsyId2) {
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        return new TaxProfileRequest("/in-person/tax-preferences/" + etsyId2.getId(), RequestMethod.PUT);
    }

    public static TaxProfileRequest deleteTaxProfile(EtsyId etsyId, EtsyId etsyId2) {
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        return new TaxProfileRequest("/in-person/tax-preferences/" + etsyId2.getId(), RequestMethod.DELETE);
    }
}
