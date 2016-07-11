package com.etsy.android.lib.requests.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.ad;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShopAbout.Link;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.Map;

public class ShopAboutLinksRequest extends EtsyRequest<Link> {
    private static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(ShopAboutLinksRequest.class);
    }

    public ShopAboutLinksRequest(String str, RequestMethod requestMethod, EndpointType endpointType) {
        super(str, requestMethod, Link.class, endpointType);
    }

    public static ShopAboutLinksRequest update(@NonNull EtsyId etsyId, Map<String, Object> map) {
        ShopAboutLinksRequest shopAboutLinksRequest = new ShopAboutLinksRequest("/about/links", RequestMethod.POST, EndpointType.APIv3);
        APIv3Scope.SHOP.setIdentifier(etsyId.getId());
        shopAboutLinksRequest.setV3Scope(APIv3Scope.SHOP);
        shopAboutLinksRequest.setContentType(JSON_CONTENT_TYPE);
        try {
            shopAboutLinksRequest.addBodyParam(ResponseConstants.LINKS, ad.m1081a().m1083b().writeValueAsString(map));
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "Failed to parse shop about links map to Json", e);
        }
        return shopAboutLinksRequest;
    }
}
