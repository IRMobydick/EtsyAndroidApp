package com.appboy.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public enum CardCategory {
    ADVERTISING,
    ANNOUNCEMENTS,
    NEWS,
    SOCIAL,
    NO_CATEGORY;
    
    public static final EnumSet<CardCategory> ALL_CATEGORIES;
    private static final Map<String, CardCategory> f877a;

    static {
        f877a = new HashMap();
        ALL_CATEGORIES = EnumSet.allOf(CardCategory.class);
        Iterator it = EnumSet.allOf(CardCategory.class).iterator();
        while (it.hasNext()) {
            CardCategory cardCategory = (CardCategory) it.next();
            f877a.put(cardCategory.toString(), cardCategory);
        }
    }

    public static CardCategory get(String str) {
        return (CardCategory) f877a.get(str.toUpperCase(Locale.US));
    }
}
