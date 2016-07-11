package com.etsy.android.lib.models.shopshare;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;

public class ShopShareStory extends BaseModel {
    private static final long serialVersionUID = 5241891660626532050L;
    protected EtsyId mActivityId;
    protected ShopShareStoryObject mShopShareStoryObject;
    protected ShopShareStorySubject mShopShareStorySubject;

    public ShopShareStory() {
        this.mActivityId = new EtsyId();
    }

    public EtsyId getActivityId() {
        return this.mActivityId;
    }

    public ShopShareStoryObject getShopShareStoryObject() {
        return this.mShopShareStoryObject;
    }

    public ShopShareStorySubject getShopShareStorySubject() {
        return this.mShopShareStorySubject;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.END_ARRAY) {
                String currentName = jsonParser.getCurrentName();
                jsonParser.nextToken();
                if (!(jsonParser.getCurrentToken() == JsonToken.VALUE_NULL || currentName == null)) {
                    Object obj = -1;
                    switch (currentName.hashCode()) {
                        case -1867885268:
                            if (currentName.equals(ResponseConstants.SUBJECT)) {
                                obj = 1;
                                break;
                            }
                            break;
                        case -1023368385:
                            if (currentName.equals(ResponseConstants.OBJECT)) {
                                obj = null;
                                break;
                            }
                            break;
                        case -917278645:
                            if (currentName.equals(ResponseConstants.ACTIVITY_ID)) {
                                obj = 2;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case Task.NETWORK_STATE_CONNECTED /*0*/:
                            this.mShopShareStoryObject = (ShopShareStoryObject) BaseModel.parseObject(jsonParser, ShopShareStoryObject.class);
                            break;
                        case Task.NETWORK_STATE_UNMETERED /*1*/:
                            this.mShopShareStorySubject = (ShopShareStorySubject) BaseModel.parseObject(jsonParser, ShopShareStorySubject.class);
                            break;
                        case Task.NETWORK_STATE_ANY /*2*/:
                            this.mActivityId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                            break;
                        default:
                            jsonParser.skipChildren();
                            break;
                    }
                }
            }
        }
    }
}
