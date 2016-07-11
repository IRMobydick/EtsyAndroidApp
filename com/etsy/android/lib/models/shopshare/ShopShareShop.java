package com.etsy.android.lib.models.shopshare;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ActivityFeedSubject;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.StringUtils;

public class ShopShareShop extends BaseModel {
    private static final long serialVersionUID = 5247897986532050L;
    protected String mShopAvatarUrl;
    protected String mShopDisplayName;

    public ShopShareShop() {
        this.mShopDisplayName = StringUtils.EMPTY;
        this.mShopAvatarUrl = StringUtils.EMPTY;
    }

    public String getShopDisplayName() {
        return this.mShopDisplayName;
    }

    public String getShopAvatarUrl() {
        return this.mShopAvatarUrl;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (!(jsonParser.getCurrentToken() == JsonToken.VALUE_NULL || currentName == null)) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -877823861:
                        if (currentName.equals(ResponseConstants.IMAGE_URL)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 1615086568:
                        if (currentName.equals(ActivityFeedSubject.DISPLAY_NAME)) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mShopDisplayName = BaseModel.parseString(jsonParser);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mShopAvatarUrl = BaseModel.parseStringURL(jsonParser);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }
}
