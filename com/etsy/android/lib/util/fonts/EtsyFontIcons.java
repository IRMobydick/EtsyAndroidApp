package com.etsy.android.lib.util.fonts;

import com.etsy.android.iconsy.AbstractFontIcon;

public enum EtsyFontIcons implements AbstractFontIcon {
    PLUS("\ue600"),
    HYPHEN("\ue601"),
    QUOTE("\ue602"),
    ELLIPSIS("\ue603"),
    REPLY("\ue604"),
    REDIRECT("\ue605"),
    TRANSFER("\ue606"),
    STAR("\ue607"),
    PRINT("\ue608"),
    CLOCK("\ue609"),
    DELETE("\ue60a"),
    DIRECTUP("\ue60b"),
    PLAY("\ue60c"),
    DIRECT_RIGHT("\ue60d"),
    NAVIGATE_RIGHT("\ue60e"),
    DROPDOWN("\ue60f"),
    DIRECT_LEFT("\ue610"),
    NAVIGATE_LEFT("\ue611"),
    RECORD("\ue612"),
    HEART_EMPTY("\ue613"),
    HEART("\ue614"),
    FLAG("\ue615"),
    SETTINGS("\ue616"),
    WRITE("\ue617"),
    CHECK("\ue618"),
    HELP("\ue619"),
    EXPAND("\ue61a"),
    ZOOMIN("\ue61b"),
    MOVE("\ue61c"),
    TRASH("\ue61d"),
    HALF_STAR("\ue61e"),
    FOLLOW_USER("\ue61f"),
    FOLLOWING_USER("\ue620"),
    USER_PROFILE("\ue621"),
    CART("\ue622"),
    CART_EMPTY("\ue623"),
    ACTIVITY("\ue624"),
    DELIVERY("\ue625"),
    LOCATION("\ue626"),
    GRID("\ue627"),
    ROWS("\ue628"),
    THUMBNAILS("\ue629"),
    DOWNLOAD_CLOUD("\ue62a"),
    IMPORT("\ue62b"),
    LIST("\ue62c"),
    ACTION("\ue62d"),
    CONTRACT("\ue62e"),
    NAVIGATE_UP("\ue62f"),
    NAVIGATE_DOWN("\ue630"),
    SHARE("\ue631"),
    FACEBOOK("\ue632"),
    TWITTER("\ue633"),
    TUMBLR("\ue634"),
    YOUTUBE("\ue635"),
    VIMEO("\ue636"),
    INSTAGRAM("\ue637"),
    PINTEREST("\ue638"),
    ETSY_E("\ue639"),
    CUSTOMIZE("\ue63a"),
    FOLLOW_SHOP("\ue63b"),
    FOLLOWING_SHOP("\ue63c"),
    GIFT_CARD("\ue63d"),
    LIST_CHECK("\ue63e"),
    SNIPPETS("\ue63f"),
    ITEMS("\ue640"),
    FEATURED_ITEM("\ue641"),
    PROMOTED_ITEM("\ue642"),
    QUICK_LISTING("\ue643"),
    IOS_SHARE("\ue644"),
    ETSY_LOGO("\ue645"),
    GLOBE("\ue646"),
    GIFT("\ue647"),
    SHOP("\ue648"),
    TAG("\ue649"),
    BROWSE("\ue64a"),
    LIKE("\ue64b"),
    DISLIKE("\ue64c"),
    CONVERSATIONS("\ue64d"),
    CREDITCARD("\ue64e"),
    FOLDER("\ue64f"),
    CALENDAR("\ue650"),
    BAR_CHART("\ue651"),
    ORDERS("\ue652"),
    COMPOSE("\ue653"),
    SEARCH("\ue654"),
    LOCK("\ue655"),
    UNLOCK("\ue656"),
    CHECKED("\ue657"),
    UNCHECKED("\ue658"),
    RADIO("\ue659"),
    UNRADIO("\ue65a"),
    MAIL("\ue65b"),
    PICTURE("\ue65c"),
    CAMERA("\ue65d"),
    EMPTY_SEARCH("\ue65e"),
    EMPTY_FOLLOWERS("\ue65f"),
    EMPTY_LISTINGS_MANAGER("\ue660"),
    EMPTY_SHOP("\ue661"),
    EMPTY_CART("\ue662"),
    EMPTY_STATS("\ue663"),
    EMPTY_SNIPPETS("\ue664"),
    EMPTY_ACTIVITY_FIND_FRIENDS("\ue665"),
    INFO("\ue670"),
    HOME("\ue671"),
    YOU("\ue672"),
    LINK("\ue673"),
    GOOGLE("\ue66c"),
    SEARCH_SHOPS("\ue66d"),
    SEARCH_LISTINGS("\ue66e"),
    SEARCH_PEOPLE("\ue66f");
    
    private String mIcon;

    private EtsyFontIcons(String str) {
        this.mIcon = str;
    }

    public static String getTypefaceName() {
        return "ss-etsy.ttf";
    }

    public String toString() {
        return this.mIcon;
    }
}
