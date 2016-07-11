package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.CollectionSocialShare;
import com.etsy.android.lib.models.apiv3.SocialShare;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.ArrayList;
import java.util.Iterator;

public class SocialShareRequest<T extends SocialShare> extends EtsyRequest<T> {
    private static final long serialVersionUID = -8974072169720157759L;

    public SocialShareRequest(String str, Class<T> cls, RequestMethod requestMethod) {
        super(str, requestMethod, cls, EndpointType.APIv3);
    }

    public static SocialShareRequest<SocialShare> favoriteListings(EtsyId etsyId) {
        SocialShareRequest<SocialShare> socialShareRequest = new SocialShareRequest("/favorite-listing-with-share", SocialShare.class, RequestMethod.POST);
        socialShareRequest.setV3Scope(APIv3Scope.MEMBER);
        socialShareRequest.setV3Bespoke(true);
        socialShareRequest.addBodyParam(ResponseConstants.LISTING_ID, etsyId.getId());
        return socialShareRequest;
    }

    public static SocialShareRequest<SocialShare> favoriteShop(EtsyId etsyId) {
        SocialShareRequest<SocialShare> socialShareRequest = new SocialShareRequest("/favorite-shop-with-share", SocialShare.class, RequestMethod.POST);
        socialShareRequest.setV3Scope(APIv3Scope.MEMBER);
        socialShareRequest.setV3Bespoke(true);
        socialShareRequest.addBodyParam(ResponseConstants.SHOP_ID, etsyId.getId());
        return socialShareRequest;
    }

    public static SocialShareRequest<CollectionSocialShare> updateListingForCollections(EtsyId etsyId, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        SocialShareRequest<CollectionSocialShare> socialShareRequest = new SocialShareRequest("/collections/listing/" + etsyId.getId(), CollectionSocialShare.class, RequestMethod.POST);
        socialShareRequest.setV3Scope(APIv3Scope.MEMBER);
        socialShareRequest.setV3Bespoke(true);
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (stringBuilder.length() != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(str);
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            str = (String) it2.next();
            if (stringBuilder2.length() != 0) {
                stringBuilder2.append(",");
            }
            stringBuilder2.append(str);
        }
        socialShareRequest.addBodyParam("add_to_collection_keys", stringBuilder.toString());
        socialShareRequest.addBodyParam("remove_from_collection_keys", stringBuilder2.toString());
        return socialShareRequest;
    }
}
