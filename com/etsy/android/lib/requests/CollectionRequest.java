package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import com.etsy.android.lib.util.bh;
import com.etsy.android.ui.user.auth.RegisterFragment;
import java.util.HashMap;

public class CollectionRequest extends EtsyRequest<Collection> {
    public CollectionRequest(String str, RequestMethod requestMethod, Class<Collection> cls) {
        super(str, requestMethod, cls);
    }

    public static CollectionRequest createCollection(String str, boolean z) {
        String str2 = "/collections";
        RequestMethod requestMethod = RequestMethod.POST;
        Object hashMap = new HashMap();
        hashMap.put(ResponseConstants.NAME, str);
        if (z) {
            hashMap.put("privacy_level", RegisterFragment.GENDER_NAME_PRIVATE);
        } else {
            hashMap.put("privacy_level", Collection.PRIVACY_LEVEL_PUBLIC);
        }
        CollectionRequest collectionRequest = new CollectionRequest(str2, requestMethod, Collection.class);
        collectionRequest.addParams(hashMap);
        return collectionRequest;
    }

    public static CollectionRequest editCollection(String str, String str2, boolean z) {
        String str3 = "/collections/" + str;
        RequestMethod requestMethod = RequestMethod.PUT;
        Object hashMap = new HashMap();
        if (bh.m3340a(str2)) {
            hashMap.put(ResponseConstants.NAME, str2);
        }
        if (z) {
            hashMap.put("privacy_level", RegisterFragment.GENDER_NAME_PRIVATE);
        } else {
            hashMap.put("privacy_level", Collection.PRIVACY_LEVEL_PUBLIC);
        }
        CollectionRequest collectionRequest = new CollectionRequest(str3, requestMethod, Collection.class);
        collectionRequest.addParams(hashMap);
        return collectionRequest;
    }

    public static CollectionRequest deleteCollection(String str) {
        return new CollectionRequest("/collections/" + str, RequestMethod.DELETE, Collection.class);
    }
}
