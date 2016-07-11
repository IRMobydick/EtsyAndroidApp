package com.etsy.android.lib.messaging;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.UserNote;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.ui.homescreen.HomescreenTabsActivity;
import java.util.HashMap;
import java.util.Map;

public enum EtsyEntity {
    CONVO("conversation", 1, false),
    CONVO_DEEPLINK("conversations", -1, false),
    ORDERS("order", 2, false),
    LISTING(ActivityFeedEntity.LISTING, 3, false),
    LISTINGS_SIMILAR("similar", -1, true),
    SHOP(ActivityFeedEntity.SHOP, 4, true),
    SHOP_POLICY("policy", 5, true),
    PEOPLE("people", 6, true),
    TREASURY(ActivityFeedEntity.TREASURY, 7, true),
    RECEIPT(UserNote.SUBJECT_TYPE_RECEIPT, 8, false),
    CART("cart", 9, false),
    HOME("etsy.com", 10, true),
    HOMESCREEN("home", -1, false),
    EXPLORE_REVIEW("explore_review", 11, true),
    READ_REVIEW("read_review", 12, true),
    TRACK_ORDER("track_order", 15, false),
    HELP("help", -1, false),
    TEAMS("teams", -1, false),
    SEARCH("search", -1, true),
    TAXONOMY_CATEGORY("c", -1, true),
    MARKET(ResponseConstants.MARKET, -1, true),
    BROWSE("browse", -1, true),
    SHOP_ABOUT(ResponseConstants.ABOUT, -1, true),
    SHOP_REVIEWS(ResponseConstants.REVIEWS, -1, true),
    SHOP_LISTING_FAVORITES("shop_listing_favorites", 13, true),
    SHOP_FAVORITES("shop_favorites", 14, true),
    ALL_CONVOS("all_convos", -1, false),
    PURCHASES("purchases", -1, false),
    YOUR("your", -1, false),
    SALES("sales", -1, false),
    LOCAL_EVENT_DIRECT("local-market", -1, false),
    LOCAL_EVENT(NotificationCompatApi21.CATEGORY_EVENT, 17, false),
    LISTING_UPDATE("seller_posts_update", 19, false),
    LOCAL_STORE("store", -1, false),
    LOCAL(HomescreenTabsActivity.TAB_PATH_LOCAL, -1, false),
    COMPOSE_REVIEW("compose_review", -1, false),
    APPRECIATION_PHOTO_LANDING_PAGE("appreciation-photo", -1, false),
    FEATURED("featured", -1, true),
    EDITORS_FINDS("editors-finds", -1, true),
    SHOP_SHARE("shares", -1, false),
    LISTING_LANDING_PAGE("listing-landing-page", -1, false),
    SHOP_ABOUT_VIDEO("shop_about_video", 22, false),
    BUY_GIFT_CARD("buy-gift-card", -1, false),
    CREATE_GIFT_CARD("giftcards", -1, false);
    
    private static Map<String, EtsyEntity> f1856a;
    private static EtsyEntity[] f1857b;
    private boolean mAllowStringIds;
    private String mName;
    private int mPushNotificationId;

    static {
        f1856a = new HashMap();
        f1857b = new EtsyEntity[23];
        for (EtsyEntity etsyEntity : values()) {
            f1856a.put(etsyEntity.mName, etsyEntity);
            if (etsyEntity.mPushNotificationId >= 0) {
                f1857b[etsyEntity.mPushNotificationId] = etsyEntity;
            }
        }
    }

    private EtsyEntity(String str, int i, boolean z) {
        this.mName = str;
        this.mPushNotificationId = i;
        this.mAllowStringIds = z;
    }

    public String getName() {
        return this.mName;
    }

    public boolean allowsStringIds() {
        return this.mAllowStringIds;
    }

    public static EtsyEntity fromPushNotificationId(int i) {
        if (i <= 0 || i >= f1857b.length) {
            return HOME;
        }
        return f1857b[i];
    }

    public static EtsyEntity fromPushNotificationId(String str) {
        try {
            return fromPushNotificationId(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            return HOME;
        }
    }

    public static EtsyEntity fromString(String str) {
        return (EtsyEntity) f1856a.get(str);
    }
}
