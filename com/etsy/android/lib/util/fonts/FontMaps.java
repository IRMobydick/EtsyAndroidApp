package com.etsy.android.lib.util.fonts;

import android.util.Pair;
import com.etsy.android.iconsy.FontMap;
import com.etsy.android.iconsy.FontSets;
import com.etsy.android.lib.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum FontMaps implements FontMap {
    FONT_MAPS;

    public void init() {
        Map hashMap = new HashMap();
        hashMap.put(EtsyFontIcons.class, "ss-etsy.ttf");
        hashMap.put(StandardFontIcon.class, "ss-standard.ttf");
        FontSets.m770a(hashMap);
        List arrayList = new ArrayList();
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_activity), EtsyFontIcons.ACTIVITY));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_browse), EtsyFontIcons.BROWSE));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_cancel), EtsyFontIcons.DELETE));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_cart), EtsyFontIcons.CART));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_cart_empty), EtsyFontIcons.CART_EMPTY));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_checked), EtsyFontIcons.CHECKED));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_check), EtsyFontIcons.CHECK));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_conversations), EtsyFontIcons.CONVERSATIONS));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_e), EtsyFontIcons.ETSY_E));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_globe), EtsyFontIcons.GLOBE));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_gift_card), EtsyFontIcons.GIFT_CARD));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_help), EtsyFontIcons.HELP));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_plus), EtsyFontIcons.PLUS));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_reply), EtsyFontIcons.REPLY));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_search), EtsyFontIcons.SEARCH));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_settings), EtsyFontIcons.SETTINGS));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_share), EtsyFontIcons.SHARE));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_trash), EtsyFontIcons.TRASH));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_unchecked), EtsyFontIcons.UNCHECKED));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_user_profile), EtsyFontIcons.USER_PROFILE));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_write), EtsyFontIcons.WRITE));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_snippets), EtsyFontIcons.SNIPPETS));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_items), EtsyFontIcons.ITEMS));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_heart), EtsyFontIcons.HEART));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_location), EtsyFontIcons.LOCATION));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_action), EtsyFontIcons.ACTION));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_unlock), EtsyFontIcons.UNLOCK));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_database_disk), StandardFontIcon.DATABASE_DISK));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_camera), StandardFontIcon.CAMERA));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_delete), StandardFontIcon.DELETE));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_ellipses), StandardFontIcon.ELLIPSES));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_ellipses_chat), StandardFontIcon.ELLIPSES_CHAT));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_grid), StandardFontIcon.GRID));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_paperclip), StandardFontIcon.PAPERCLIP));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_plus), StandardFontIcon.PLUS));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_public), StandardFontIcon.PUBLIC));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_private), StandardFontIcon.PRIVATE));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_picture), StandardFontIcon.PICTURE));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_send), StandardFontIcon.SEND));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_play), StandardFontIcon.PLAY));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_users), StandardFontIcon.USERS));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_alert), StandardFontIcon.ALERT));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_up_arrow), StandardFontIcon.UP_ARROW));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_navigate_left), StandardFontIcon.NAVIGATE_LEFT));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_navigate_right), StandardFontIcon.NAVIGATE_RIGHT));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_clock), StandardFontIcon.CLOCK));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_info), StandardFontIcon.INFO));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_link), StandardFontIcon.LINK));
        arrayList.add(new Pair(Integer.valueOf(R.ic_standard_cell), StandardFontIcon.CELL));
        arrayList.add(new Pair(Integer.valueOf(R.ic_etsy_microphone), StandardFontIcon.MICROPHONE));
        FontSets.m769a(arrayList);
    }
}
