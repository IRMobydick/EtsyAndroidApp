package com.etsy.android.lib.models.shopshare;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;

public class ShopShareStorySubject extends BaseModel {
    private static final long serialVersionUID = 5241897827532880L;
    protected EtsyId mOwnerId;
    protected int mOwnerType;
    protected ShopShareShop mShopShareShop;

    public ShopShareShop getShopShareShop() {
        return this.mShopShareShop;
    }

    public EtsyId getOwnerId() {
        return this.mOwnerId;
    }

    public int getOwnerType() {
        return this.mOwnerType;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (!(jsonParser.getCurrentToken() == JsonToken.VALUE_NULL || currentName == null)) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -1489595877:
                        if (currentName.equals(ResponseConstants.OBJECT_ID)) {
                            obj = null;
                            break;
                        }
                        break;
                    case -853090240:
                        if (currentName.equals(ActivityFeedEntity.TYPE_ID)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 3076010:
                        if (currentName.equals(ActivityFeedEntity.DATA)) {
                            obj = 2;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mOwnerId = new EtsyId(jsonParser.getLongValue());
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mOwnerType = jsonParser.getIntValue();
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mShopShareShop = (ShopShareShop) BaseModel.parseObject(jsonParser, ShopShareShop.class);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }
}
