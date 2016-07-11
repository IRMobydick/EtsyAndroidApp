package com.etsy.android.lib.util;

import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public enum NotificationType {
    UNKNOWN(StringUtils.EMPTY, 0),
    CONVO("convo", 1),
    SOLD_ORDER("sold_order", 2),
    BLAST("blast", 0),
    SHIPPING(ResponseConstants.SHIPPING, 4),
    SELLER_TIP("seller_tip", 0),
    ETSY_ANNOUNCEMENT("etsy_announcements", 0),
    FOLLOWING("following", 0),
    CONTACT_ON_ETSY("contact_on_etsy", 0),
    RESERVED_LISTING("reserved_listing", 0),
    BUYER_REVIEW_AVAILABLE("review_available", 10),
    BUYER_LEFT_REVIEW("buyer_leaves_review", 11),
    BUYER_FAVORITE("buyer_favorites_listing", 12),
    BUYER_FAVORITES_SHOP("buyer_favorites_shop", 12),
    BUYER_IPP_PURCHASE("bought_in_person", 14),
    NEARBY_LOCAL_MARKET("nearby_local_market", 17),
    SELLER_POSTS_UPDATE("seller_posts_update", 19),
    FUNDING_ENDED("funding_ended", 20),
    LD_PURCHASE_COMPLETE("ld_purchase_complete", 21),
    LD_PICKUP_STARTED("ld_pickup_started", 22),
    LD_DELIVERY_STARTED("ld_delivery_started", 23),
    LD_DELIVERY_COMPLETE_BUYER("ld_delivery_complete_buyer", 24),
    LD_DELIVERY_COMPLETE_SELLER("ld_delivery_complete_seller", 25),
    LD_PICKUP_SOON("ld_delivery_complete_buyer", 26),
    LD_PICKUP_DELAYED("ld_delivery_complete_seller", 27),
    LD_DELIVERY_SOON("ld_delivery_complete_seller", 28),
    LD_DELIVERY_DELAYED("ld_delivery_complete_seller", 29),
    ABOUT_VIDEO_UPLOAD("about_video_upload", 30),
    EVENT_HORIZON("event_horizon", 98),
    ADMIN_TOOLBAR("admin_toolbar", 99);
    
    private static Map<String, NotificationType> f1986a;
    private final int mId;
    private final String mName;

    static {
        f1986a = new HashMap();
        NotificationType[] values = values();
        int length = values.length;
        int i;
        while (i < length) {
            NotificationType notificationType = values[i];
            f1986a.put(notificationType.getName(), notificationType);
            i++;
        }
    }

    private NotificationType(String str, int i) {
        this.mName = str;
        this.mId = i;
    }

    public final String getName() {
        return this.mName;
    }

    public final int getId() {
        return this.mId;
    }

    public static NotificationType fromString(String str) {
        if (f1986a.containsKey(str)) {
            return (NotificationType) f1986a.get(str);
        }
        return UNKNOWN;
    }
}
