package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import java.util.ArrayList;
import java.util.List;

public class CollectionSocialShare extends SocialShare {
    private static final long serialVersionUID = 4820113850858105442L;
    private List<String> mFailedCollectionKeys;

    public CollectionSocialShare() {
        this.mFailedCollectionKeys = new ArrayList();
    }

    protected void parseModel(JsonParser jsonParser, String str) {
        if (ResponseConstants.FAILED_COLLECTION_KEYS.equals(str)) {
            this.mFailedCollectionKeys = BaseModel.parseStringArray(jsonParser);
        } else {
            super.parseModel(jsonParser, str);
        }
    }

    public List<String> getFailedCollectionKeys() {
        return this.mFailedCollectionKeys;
    }
}
