package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.ChannelItem;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.Map;

public class ChannelRequest extends EtsyRequest<ChannelItem> {
    private static final String IMAGE_INCLUDES_SQUARE = "Images(url_170x135,red,green,blue):1";
    private static final String INCLUDES = "Listings(state,listing_id,price,currency_code,converted_price,converted_currency_code,title)/Images(url_570xN,full_height,full_width,red,green,blue):1,Listings(listing_id)/Shop(shop_id,shop_name),User(user_id)/Profile(first_name,last_name,login_name,image_url_75x75),User(user_id)/FavoriteListings(state,listing_id,price,currency_code,converted_price,converted_currency_code,title):active:3/Listing(listing_id)/Images(url_170x135,red,green,blue):1,Shop(user_id,shop_id,shop_name,listing_active_count)/Listings(listing_id):active:3/Images(url_170x135,red,green,blue):1,Shop(shop_id)/User(user_id)/Profile(image_url_75x75),Treasury(id,title)/Listings(listing_id):3/Images(url_170x135,red,green,blue):1,BlogPost(post_id,blog_name,image,title,summary,type,shop_id,video_url)";
    private static final String SHOP_INCLUDES = "Listings(listing_id,price,currency_code,converted_price,converted_currency_code,title)/Images(url_170x135,red,green,blue):1,User(user_id)/Profile(first_name,last_name,login_name,image_url_75x75),Shop(shop_id)/User(user_id)/Profile(image_url_75x75),Shop(user_id,shop_id,shop_name),Treasury(id,title),Receipt(receipt_id)/Transactions(transaction_id,listing_id,title,price,currency_code,is_quick_listing)/MainImage(url_170x135,red,green,blue),Feedback/AppreciationPhoto";
    private static final long serialVersionUID = 4561879751861513013L;

    public ChannelRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ChannelItem.class);
    }

    public static ChannelRequest getActivityItems(long j) {
        ChannelRequest channelRequest = new ChannelRequest("/channels/activity/items", RequestMethod.GET);
        channelRequest.addParams(getChannelItemParams(j));
        return channelRequest;
    }

    public static ChannelRequest getShopActivity(long j) {
        ChannelRequest channelRequest = new ChannelRequest("/channels/shop_activity/items", RequestMethod.GET);
        channelRequest.addParams(getShopChannelItemParams(j));
        return channelRequest;
    }

    public static ChannelRequest getPopularItems(long j) {
        ChannelRequest channelRequest = new ChannelRequest("/channels/popular/items", RequestMethod.GET);
        channelRequest.addParams(getChannelItemParams(j));
        return channelRequest;
    }

    private static Map<String, String> getChannelItemParams(long j) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("includes", INCLUDES);
        hashMap.put("synchronous", "1");
        hashMap.put("limit", "21");
        if (j > 0) {
            hashMap.put(ResponseConstants.LAST_ID, String.valueOf(j));
        }
        return hashMap;
    }

    private static Map<String, String> getShopChannelItemParams(long j) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("includes", SHOP_INCLUDES);
        hashMap.put("synchronous", "1");
        hashMap.put("limit", "21");
        if (j > 0) {
            hashMap.put(ResponseConstants.LAST_ID, String.valueOf(j));
        }
        return hashMap;
    }
}
