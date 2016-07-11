package com.etsy.android.lib.models.shopshare;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;

public class ShopShareStoryObject extends BaseModel {
    private static final long serialVersionUID = 5241897986532050L;
    protected ShopShareCard mShopShareCard;

    public ShopShareCard getShopShareCard() {
        return this.mShopShareCard;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (!(jsonParser.getCurrentToken() == JsonToken.VALUE_NULL || currentName == null)) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case 3076010:
                        if (currentName.equals(ActivityFeedEntity.DATA)) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        ShareItem shareItem = (ShareItem) BaseModel.parseObject(jsonParser, ShareItem.class);
                        this.mShopShareCard = new ShopShareCard();
                        this.mShopShareCard.setShareItem(shareItem);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }
}
