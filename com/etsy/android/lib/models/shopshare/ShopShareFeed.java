package com.etsy.android.lib.models.shopshare;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class ShopShareFeed extends BaseModel {
    private static final long serialVersionUID = 5241891660245532050L;
    protected List<ShopShareStory> mShopShareStories;

    public ShopShareFeed() {
        this.mShopShareStories = new ArrayList();
    }

    public List<ShopShareStory> getShopShareStories() {
        return this.mShopShareStories;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.END_ARRAY) {
                String currentName = jsonParser.getCurrentName();
                jsonParser.nextToken();
                if (!(jsonParser.getCurrentToken() == JsonToken.VALUE_NULL || currentName == null)) {
                    Object obj = -1;
                    switch (currentName.hashCode()) {
                        case -1884266413:
                            if (currentName.equals(ResponseConstants.STORIES)) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case Task.NETWORK_STATE_CONNECTED /*0*/:
                            if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY && jsonParser.nextToken() != JsonToken.END_ARRAY) {
                                this.mShopShareStories = BaseModel.parseArray(jsonParser, ShopShareStory.class);
                                break;
                            }
                        default:
                            jsonParser.skipChildren();
                            break;
                    }
                }
            }
        }
    }
}
