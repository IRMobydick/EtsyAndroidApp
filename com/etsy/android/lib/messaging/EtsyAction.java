package com.etsy.android.lib.messaging;

import com.etsy.android.lib.models.ResponseConstants;
import java.util.HashMap;

public enum EtsyAction {
    VIEW("com.etsy.android.action.VIEW", null),
    VIEW_FEED("com.etsy.android.action.VIEW", ResponseConstants.FEED),
    FOLLOW("com.etsy.android.action.FOLLOW", "follow"),
    CONTACT_USER("com.etsy.android.action.CONTACT", "contact"),
    FAVORITE("com.etsy.android.action.FAVORITE", "favorite"),
    ADD_TO_CART("com.etsy.android.action.ADD_TO_CART", "add_to_cart"),
    MANAGE_ITEM_COLLECTIONS("com.etsy.android.action.ADD_TO_COLLECTION", "manage_item_collections"),
    PURCHASE("com.etsy.android.action.PURCHASE", "purchase"),
    STATE_CHANGE("com.etsy.android.STATE_CHANGE", "state_changed"),
    FOLLOW_CAMPAIGN("com.etsy.android.action.FOLLOW_CAMPAIGN", "follow_campaign"),
    VIEW_ORDER("com.etsy.android.action.VIEW_ORDER", "view_order"),
    VIEW_PURCHASES("com.etsy.android.action.VIEW_PURCHASES", "view_purchases"),
    VIEW_CONVO("com.etsy.android.action.VIEW_CONVO", "view_convo"),
    SUBSCRIBE_VACATION_NOTIFICATION("com.etsy.android.action.SUBSCRIBE_VACATION_NOTIFICATION", "subscribe_vacation_notification"),
    CART_COUNTS_UPDATED("com.etsy.android.action.CART_COUNTS_UPDATED", "cart_counts_updated");
    
    public static final String ACTION_TYPE_NAME = "etsy_action_type";
    public static final String STATE_COLLECTIONS = "col";
    public static final String STATE_FAVORITE = "fav";
    public static final String STATE_ID = "id";
    private static final HashMap<String, EtsyAction> f1853a;
    private static final HashMap<String, EtsyAction> f1854b;
    private String action;
    private String name;

    static {
        f1853a = new HashMap();
        f1854b = new HashMap();
        EtsyAction[] values = values();
        int length = values.length;
        int i;
        while (i < length) {
            EtsyAction etsyAction = values[i];
            f1853a.put(etsyAction.action, etsyAction);
            f1854b.put(etsyAction.name, etsyAction);
            i++;
        }
    }

    private EtsyAction(String str, String str2) {
        this.action = str;
        this.name = str2;
    }

    public String getName() {
        return this.name;
    }

    public String getAction() {
        return this.action;
    }

    public static EtsyAction fromName(String str) {
        return (EtsyAction) f1854b.get(str);
    }

    public static EtsyAction fromAction(String str) {
        return (EtsyAction) f1853a.get(str);
    }
}
