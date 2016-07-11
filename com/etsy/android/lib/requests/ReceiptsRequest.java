package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.Receipt;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class ReceiptsRequest extends EtsyRequest<Receipt> {
    public static final String STATUS_ALL = "all";
    public static final String STATUS_COMPLETED = "completed";
    public static final String STATUS_OPEN = "open";
    private static final long serialVersionUID = -1782733545383096172L;

    public ReceiptsRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, Receipt.class);
    }

    public static ReceiptsRequest getReceipt(EtsyId etsyId) {
        return new ReceiptsRequest("/receipts/" + etsyId.getId(), RequestMethod.GET);
    }

    public static ReceiptsRequest findAllShopReceiptsForStatus(String str) {
        return new ReceiptsRequest("/shops/__SELF__/receipts/" + str, RequestMethod.GET);
    }

    public static ReceiptsRequest searchShopReceipts() {
        return new ReceiptsRequest("/shops/__SELF__/receipts/search", RequestMethod.GET);
    }

    public static ReceiptsRequest updateReceipt(EtsyId etsyId) {
        return new ReceiptsRequest("/receipts/" + etsyId.getId(), RequestMethod.PUT);
    }

    public static ReceiptsRequest submitReceiptShippingInformation(String str) {
        return new ReceiptsRequest("/shops/__SELF__/receipts/" + str + "/tracking_internal", RequestMethod.POST);
    }
}
