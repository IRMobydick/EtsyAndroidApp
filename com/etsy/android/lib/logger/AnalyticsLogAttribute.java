package com.etsy.android.lib.logger;

import java.util.Locale;

public enum AnalyticsLogAttribute {
    BROWSER_ID((String) Accessibility.PRIVATE),
    EPOCH_MS((String) Accessibility.PRIVATE),
    EVENT_LOGGER((String) Accessibility.PRIVATE),
    EVENT_NAME((String) Accessibility.PRIVATE),
    EVENT_SOURCE((String) Accessibility.PRIVATE),
    GUID((String) Accessibility.PRIVATE),
    PRIMARY_EVENT((String) Accessibility.PRIVATE),
    PAGE_GUID((String) Accessibility.PRIVATE),
    PARENT_PAGE_GUID((String) Accessibility.PRIVATE),
    CONTEXT_NAME((String) Accessibility.PRIVATE),
    ORIENTATION((String) Accessibility.PRIVATE),
    IS_SURFACED((String) Accessibility.PRIVATE),
    IS_RESUME((String) Accessibility.PRIVATE),
    VIEW_NAME((String) Accessibility.PRIVATE),
    VIEW_ACTION((String) Accessibility.PRIVATE),
    ANDROID_ID((String) Accessibility.PRIVATE),
    APP_VERSION((String) Accessibility.PRIVATE),
    APP_NAME((String) Accessibility.PRIVATE),
    DEVICE_SYSTEM_NAME((String) Accessibility.PRIVATE),
    DEVICE_SYSTEM_VERSION((String) Accessibility.PRIVATE),
    HARDWARE_PLATFORM_STRING((String) Accessibility.PRIVATE),
    HARDWARE_PLATFORM((String) Accessibility.PRIVATE),
    HARDWARE_MANUFACTURER((String) Accessibility.PRIVATE),
    HARDWARE_MODEL((String) Accessibility.PRIVATE),
    DEVICE_RESOLUTION((String) Accessibility.PRIVATE),
    ACCEPT_LANGUAGES("accept-languages", Accessibility.PRIVATE),
    TIME_ZONE((String) Accessibility.PRIVATE),
    REGION((String) Accessibility.PRIVATE),
    APP_INITIAL_START_TIME((String) Accessibility.PRIVATE),
    APP_START_TIME((String) Accessibility.PRIVATE),
    APP_FOREGROUND_TIME((String) Accessibility.PRIVATE),
    USER_ID((String) Accessibility.PRIVATE),
    IS_ADMIN("isAdmin", Accessibility.PRIVATE),
    AB((String) Accessibility.PRIVATE),
    TARGET_USER_ID,
    TARGET_USERNAME,
    USER_NAME,
    USER_ADDRESS_ID,
    LISTING_ID,
    LISTING_IDS,
    LISTINGS_COUNT,
    LISTING_OPTION_ID,
    VARIATION_PROPERTY_ID,
    IMAGE_ID,
    COUNTRY_ID,
    LANGUAGE,
    CURRENCY,
    CATEGORY_SEGMENT,
    CATEGORY_ID,
    CART_ID,
    CART_IDS,
    RECEIPT_ID,
    TRANSACTION_ID,
    SHIPPING_OPTION_ID,
    POSTAL_CODE,
    RECEIPT_SHIPPING_ID,
    SHIPPING_STATUS,
    USER_NOTE_ID,
    PAYMENT_ID,
    PAYMENT_METHOD,
    HAS_TRANSACTION_REVIEW("hasTransactionReview"),
    RATING,
    DISCOUNT_RATE,
    DISCOUNT_AMOUNT,
    COUPON_CODE,
    QUANTITY,
    QUICK_LISTING_ID,
    QUICK_LISTING_TITLE,
    QUICK_LISTING_PRICE,
    QUICK_LISTING_HAS_IMAGE,
    QUICK_LISTING_IS_TAXABLE,
    TRANSACTION_TOTAL,
    TRANSACTION_SUBTOTAL,
    TRANSACTION_DISCOUNT,
    TRANSACTION_TAX,
    SHOP_ID,
    SHOP_IDS,
    TAX_PROFILE_ID,
    OPTION_VALUE_ID,
    SHOP_SECTION_ID,
    SHIPPING_TEMPLATE_ID,
    SHIPPING_TEMPLATE_ENTRY_ID,
    MEMBER_ID,
    CARRIER_NAME,
    ACCEPTED_STRUCTURED_POLICIES,
    SHARE_ID,
    ANNOTATION_OBJECT_ID,
    STATS_SOURCE_TYPE,
    COLLECTION_KEY,
    COLLECTION_NAME,
    PRIVACY_LEVEL,
    TREASURY_ID,
    CONVO_ID,
    SNIPPET_ID,
    TEAM_ID,
    NOTIFICATION_ID,
    LOC,
    NOTIFICATION_TYPE,
    LANDING_PAGE_TYPE,
    AB_URI,
    AB_TITLE,
    AB_CONTENT,
    URL,
    SI_TIME,
    SI_UUID,
    SI_TRIGGER,
    SI_CHANNEL,
    SI_PAGE,
    SI_OBJECT_ID,
    LOCAL_MARKET_ID,
    LOCAL_MARKET_TYPE,
    LOCAL_BROWSE_LANDING_PAGE,
    LAT,
    LON,
    QUERY,
    TAXONOMY_NODE_ID,
    SEARCH_TYPE,
    CATEGORY,
    TOTAL_RESULTS,
    FINDS_PAGE_ID,
    FINDS_PAGE_PUBLISHED_ID,
    FINDS_PAGE_SLUG,
    PROMO_VERSION,
    UPGRADE_APP_BUILD_ID,
    POSITION,
    CHANNEL_ITEM_TYPE,
    GOOGLE_ERROR_CODE,
    FLOW_TYPE,
    ERROR_TYPE,
    REFERRER("ref"),
    ACTIVITY_RESULT_CODE,
    FEATURED_LISTING_IDS,
    MODULE_ABOUT_ENABLED,
    MODULE_FEATURED_ITEMS_ENABLED,
    MODULE_LOCAL_ENABLED,
    PAGE_SOLD_ITEMS_ENABLED,
    BRANDING_TYPE,
    SORT_OPTION_ID;
    
    private Accessibility mAccessibility;
    private String mAttributeName;

    public enum Accessibility {
        public static final Accessibility PRIVATE;
        public static final Accessibility PUBLIC;
        private static final /* synthetic */ Accessibility[] f1724a;

        private Accessibility(String str, int i) {
        }

        public static Accessibility valueOf(String str) {
            return (Accessibility) Enum.valueOf(Accessibility.class, str);
        }

        public static Accessibility[] values() {
            return (Accessibility[]) f1724a.clone();
        }

        static {
            PRIVATE = new Accessibility("PRIVATE", 0);
            PUBLIC = new Accessibility("PUBLIC", 1);
            f1724a = new Accessibility[]{PRIVATE, PUBLIC};
        }
    }

    private AnalyticsLogAttribute(Accessibility accessibility) {
        this.mAttributeName = name().toLowerCase(Locale.US);
        this.mAccessibility = accessibility;
    }

    private AnalyticsLogAttribute(String str) {
        this(r2, r3, str, Accessibility.PUBLIC);
    }

    private AnalyticsLogAttribute(String str, Accessibility accessibility) {
        this.mAttributeName = str;
        this.mAccessibility = accessibility;
    }

    public String toString() {
        return this.mAttributeName;
    }

    public boolean isPrivate() {
        return this.mAccessibility == Accessibility.PRIVATE;
    }
}
