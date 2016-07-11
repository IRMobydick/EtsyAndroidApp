package com.etsy.android.lib.util.fonts;

import com.etsy.android.iconsy.AbstractFontIcon;

public enum StandardFontIcon implements AbstractFontIcon {
    SEND("\ue350"),
    MAIL("\u2709"),
    CHAT("\ud83d\udcac"),
    ELLIPSES_CHAT("\ue399"),
    ELLIPSES("\u2026"),
    AVATAR_MAN("\ud83d\udc64"),
    AVATAR_FEMME("\ud83d\udc67"),
    USERS("\ud83d\udc65"),
    MICROPHONE("\ud83c\udfa4"),
    CAMERA("\ud83d\udcf7"),
    NAVIGATE_DOWN("\uf501"),
    NAVIGATE_UP("\uf500"),
    GRID("\ue9a0"),
    THUMBNAILS("\ue9a3"),
    PICTURE("\ud83c\udf04"),
    PAPERCLIP("\ud83d\udcce"),
    PUBLIC("\ud83d\udd13"),
    PRIVATE("\ud83d\udd12"),
    FILTER("\ue9b0"),
    PLUS("+"),
    DELETE("\u2421"),
    ALERT("\u26a0"),
    DATABASE_DISK("\ue7a0"),
    ROWS("\ue9a1"),
    UP_ARROW("\u2b06"),
    NAVIGATE_LEFT("\u25c5"),
    NAVIGATE_RIGHT("\u25bb"),
    HOME("\u2302"),
    FLAG("\u2691"),
    LINK("\ud83d\udd17"),
    PLAY("\u25b6"),
    CLOCK("\u23f2"),
    INFO("\u2139"),
    CELL("\ud83d\udcf1"),
    LOCATION("\ue6d0"),
    TRASH("\ue0d0"),
    CHECK("\u2713");
    
    private String mIcon;

    private StandardFontIcon(String str) {
        this.mIcon = str;
    }

    public static String getTypefaceName() {
        return "ss-standard.ttf";
    }

    public String toString() {
        return this.mIcon;
    }
}
