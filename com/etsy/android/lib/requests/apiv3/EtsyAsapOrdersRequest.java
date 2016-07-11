package com.etsy.android.lib.requests.apiv3;

import android.content.Context;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.Receipt;
import com.etsy.android.lib.models.apiv3.CountResult;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import java.util.HashMap;
import java.util.Map;

public class EtsyAsapOrdersRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    public static final String ORDER_FIELDS = "receipt_id,grandtotal,currency_code,was_paid,was_shipped,creation_tsz,shipped_tsz,estimated_shipped_tsz,shipments,is_in_person";
    public static final String ORDER_INCLUDES = "Transactions(transaction_id,quantity,is_gift_card,title,price,currency_code,variations,is_digital)/MainImage(url_170x135)";

    public EtsyAsapOrdersRequest(String str, RequestMethod requestMethod, Class cls, EndpointType endpointType) {
        super(str, requestMethod, cls, endpointType);
    }

    public static EtsyAsapOrdersRequest<CountResult> ordersCount(Context context) {
        String str = "/receipts/local-delivery/count";
        EtsyAsapOrdersRequest<CountResult> etsyAsapOrdersRequest = new EtsyAsapOrdersRequest("/receipts/local-delivery/count", RequestMethod.GET, CountResult.class, EndpointType.APIv3);
        APIv3Scope.SHOP.setIdentifier(SharedPreferencesUtility.m3152k(context).getId());
        etsyAsapOrdersRequest.setV3Scope(APIv3Scope.SHOP);
        return etsyAsapOrdersRequest;
    }

    public static EtsyAsapOrdersRequest<Receipt> list() {
        String str = "/shops/__SELF__/receipts/local-delivery";
        EtsyAsapOrdersRequest<Receipt> etsyAsapOrdersRequest = new EtsyAsapOrdersRequest("/shops/__SELF__/receipts/local-delivery", RequestMethod.GET, Receipt.class, EndpointType.API);
        Map hashMap = new HashMap();
        hashMap.put("fields", ORDER_FIELDS);
        hashMap.put("includes", ORDER_INCLUDES);
        etsyAsapOrdersRequest.addParams(hashMap);
        return etsyAsapOrdersRequest;
    }
}
