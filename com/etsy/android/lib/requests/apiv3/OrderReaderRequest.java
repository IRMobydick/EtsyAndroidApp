package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;

public class OrderReaderRequest extends EtsyRequest<EmptyResult> {
    private static final String REQUEST_URL = "/in-person/card-readers/requests";
    private static final long serialVersionUID = 2525005673183859475L;

    public class OrderReaderRequestBuilder {
        private HashMap<String, String> mBodyParams;
        private EtsyId mShopId;

        public static OrderReaderRequestBuilder create(EtsyId etsyId) {
            return new OrderReaderRequestBuilder(etsyId);
        }

        private OrderReaderRequestBuilder(EtsyId etsyId) {
            if (etsyId == null) {
                throw new IllegalArgumentException("shopId arg can not be null");
            }
            this.mShopId = etsyId;
            this.mBodyParams = new HashMap();
        }

        public OrderReaderRequestBuilder setShippingAddressId(String str) {
            this.mBodyParams.put(ResponseConstants.SHIPPING_ADDRESS_ID, str);
            return this;
        }

        public OrderReaderRequestBuilder setName(String str) {
            this.mBodyParams.put(ResponseConstants.NAME, str);
            return this;
        }

        public OrderReaderRequestBuilder setFirstLine(String str) {
            this.mBodyParams.put(ResponseConstants.FIRST_LINE, str);
            return this;
        }

        public OrderReaderRequestBuilder setSecondLine(String str) {
            this.mBodyParams.put(ResponseConstants.SECOND_LINE, str);
            return this;
        }

        public OrderReaderRequestBuilder setCity(String str) {
            this.mBodyParams.put(ResponseConstants.CITY, str);
            return this;
        }

        public OrderReaderRequestBuilder setState(String str) {
            this.mBodyParams.put(ResponseConstants.STATE, str);
            return this;
        }

        public OrderReaderRequestBuilder setZip(String str) {
            this.mBodyParams.put(ResponseConstants.ZIP, str);
            return this;
        }

        public OrderReaderRequestBuilder setCountryId(EtsyId etsyId) {
            this.mBodyParams.put(ResponseConstants.COUNTRY_ID, etsyId.getId());
            return this;
        }

        public OrderReaderRequest build() {
            OrderReaderRequest orderReaderRequest = new OrderReaderRequest(OrderReaderRequest.REQUEST_URL);
            orderReaderRequest.addBodyParams(this.mBodyParams);
            APIv3Scope.SHOP.setIdentifier(this.mShopId.toString());
            orderReaderRequest.setV3Scope(APIv3Scope.SHOP);
            orderReaderRequest.setV3Bespoke(true);
            return orderReaderRequest;
        }
    }

    public OrderReaderRequest(String str) {
        super(str, RequestMethod.POST, EmptyResult.class, EndpointType.APIv3);
    }
}
