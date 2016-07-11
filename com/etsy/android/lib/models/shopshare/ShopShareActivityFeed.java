package com.etsy.android.lib.models.shopshare;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.List;

public class ShopShareActivityFeed extends BaseModel {
    private static final long serialVersionUID = 52418916605442050L;
    protected boolean mHasMoreActivity;
    protected List<ShopShareFeed> mShopShareFeeds;

    public ShopShareActivityFeed() {
        this.mShopShareFeeds = new ArrayList();
        this.mHasMoreActivity = false;
    }

    public boolean hasMoreActivity() {
        return this.mHasMoreActivity;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (!(jsonParser.getCurrentToken() == JsonToken.VALUE_NULL || currentName == null)) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -1585928236:
                        if (currentName.equals(ResponseConstants.HAS_MORE_ACTIVITY)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 3138974:
                        if (currentName.equals(ResponseConstants.FEED)) {
                            obj = 1;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mHasMoreActivity = jsonParser.getValueAsBoolean();
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mShopShareFeeds = BaseModel.parseArray(jsonParser, ShopShareFeed.class);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    public List<ShopShareCard> getShopShareCards() {
        List<ShopShareCard> arrayList = new ArrayList();
        for (ShopShareFeed shopShareStories : this.mShopShareFeeds) {
            for (ShopShareStory shopShareStory : shopShareStories.getShopShareStories()) {
                ShopShareStoryObject shopShareStoryObject = shopShareStory.getShopShareStoryObject();
                ShopShareStorySubject shopShareStorySubject = shopShareStory.getShopShareStorySubject();
                if (!(shopShareStoryObject == null || shopShareStorySubject == null)) {
                    ShopShareCard shopShareCard = shopShareStoryObject.getShopShareCard();
                    ShopShareShop shopShareShop = shopShareStorySubject.getShopShareShop();
                    if (!(shopShareCard == null || shopShareShop == null)) {
                        shopShareCard.setActivityId(shopShareStory.getActivityId());
                        shopShareCard.setOwnerId(shopShareStorySubject.getOwnerId());
                        shopShareCard.setOwnerType(shopShareStorySubject.getOwnerType());
                        shopShareCard.setShopDisplayName(shopShareShop.getShopDisplayName());
                        shopShareCard.setShopAvatarUrl(shopShareShop.getShopAvatarUrl());
                        arrayList.add(shopShareCard);
                    }
                }
            }
        }
        return arrayList;
    }
}
