package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.ShippingInfo;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.Map;

public class ShippingInfoRequest extends EtsyRequest<ShippingInfo> {
    private static final String FIELDS = "shipping_info_id,origin_country_id,destination_country_id,currency_code,primary_cost,secondary_cost,listing_id,region_id,origin_country_name,destination_country_name,converted_primary_cost,converted_secondary_cost,converted_currency_code";
    private static final String INCLUDES = "DestinationCountry,OriginCountry,Region";
    private static final long serialVersionUID = -4920262228307435102L;

    public ShippingInfoRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ShippingInfo.class);
    }

    public static ShippingInfoRequest findAllShippingInfoForListing(EtsyId etsyId) {
        ShippingInfoRequest shippingInfoRequest = new ShippingInfoRequest("/listings/" + etsyId.getId() + "/shipping/info", RequestMethod.GET);
        Map hashMap = new HashMap();
        hashMap.put("fields", FIELDS);
        hashMap.put("includes", INCLUDES);
        shippingInfoRequest.addParams(hashMap);
        return shippingInfoRequest;
    }
}
