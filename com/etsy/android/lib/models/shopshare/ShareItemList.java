package com.etsy.android.lib.models.shopshare;

import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.models.BaseModel;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.List;

public class ShareItemList extends BaseModel {
    private List<ShareItem> mShareItems;

    public ShareItemList() {
        this.mShareItems = new ArrayList();
    }

    public List<ShareItem> getShopShares() {
        return this.mShareItems;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -903566220:
                        if (currentName.equals("shares")) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mShareItems = BaseModel.parseArray(jsonParser, ShareItem.class);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    public static EtsyApiV3Request<ShareItemList> createRequestBuilder(String str) {
        return new EtsyApiV3Request(ShareItemList.class, String.format("/etsyapps/v3/bespoke/shop/%s/shares", new Object[]{str}));
    }
}
