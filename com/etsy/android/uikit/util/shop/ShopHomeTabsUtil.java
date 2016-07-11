package com.etsy.android.uikit.util.shop;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;

/* renamed from: com.etsy.android.uikit.util.shop.e */
public class ShopHomeTabsUtil {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m5617a(@android.support.annotation.NonNull android.support.design.widget.TabLayout r4, @android.support.annotation.NonNull android.content.res.Resources r5, java.lang.String r6, int r7) {
        /*
        r2 = -1;
        r3 = r4.getTabCount();
        r0 = r6.hashCode();
        switch(r0) {
            case -234430262: goto L_0x0060;
            case 3357525: goto L_0x007e;
            case 3529462: goto L_0x0038;
            case 92611469: goto L_0x0056;
            case 100526016: goto L_0x0042;
            case 103145323: goto L_0x006a;
            case 546894160: goto L_0x0074;
            case 1099953179: goto L_0x004c;
            default: goto L_0x000c;
        };
    L_0x000c:
        r0 = r2;
    L_0x000d:
        switch(r0) {
            case 0: goto L_0x0088;
            case 1: goto L_0x0090;
            case 2: goto L_0x0099;
            case 3: goto L_0x00a2;
            case 4: goto L_0x00ab;
            case 5: goto L_0x00b4;
            case 6: goto L_0x00bd;
            case 7: goto L_0x00c6;
            default: goto L_0x0010;
        };
    L_0x0010:
        r0 = "";
        r1 = r0;
    L_0x0013:
        if (r3 <= 0) goto L_0x0025;
    L_0x0015:
        r0 = r3 + -1;
        r0 = r4.getTabAt(r0);
        r0 = r0.getTag();
        r0 = (com.etsy.android.uikit.util.shop.ShopHomeTabsUtil) r0;
        r3 = r7 + -1;
        r0.f4188b = r3;
    L_0x0025:
        r0 = r4.newTab();
        r3 = new com.etsy.android.uikit.util.shop.f;
        r3.<init>(r7, r2, r6);
        r0.setTag(r3);
        r0.setText(r1);
        r4.addTab(r0);
        return;
    L_0x0038:
        r0 = "shop";
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x000c;
    L_0x0040:
        r0 = 0;
        goto L_0x000d;
    L_0x0042:
        r0 = "items";
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x000c;
    L_0x004a:
        r0 = 1;
        goto L_0x000d;
    L_0x004c:
        r0 = "reviews";
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x000c;
    L_0x0054:
        r0 = 2;
        goto L_0x000d;
    L_0x0056:
        r0 = "about";
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x000c;
    L_0x005e:
        r0 = 3;
        goto L_0x000d;
    L_0x0060:
        r0 = "updates";
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x000c;
    L_0x0068:
        r0 = 4;
        goto L_0x000d;
    L_0x006a:
        r0 = "local";
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x000c;
    L_0x0072:
        r0 = 5;
        goto L_0x000d;
    L_0x0074:
        r0 = "policies";
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x000c;
    L_0x007c:
        r0 = 6;
        goto L_0x000d;
    L_0x007e:
        r0 = "more";
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x000c;
    L_0x0086:
        r0 = 7;
        goto L_0x000d;
    L_0x0088:
        r0 = com.etsy.android.lib.R.shop;
        r0 = r5.getString(r0);
        r1 = r0;
        goto L_0x0013;
    L_0x0090:
        r0 = com.etsy.android.lib.R.items;
        r0 = r5.getString(r0);
        r1 = r0;
        goto L_0x0013;
    L_0x0099:
        r0 = com.etsy.android.lib.R.reviews;
        r0 = r5.getString(r0);
        r1 = r0;
        goto L_0x0013;
    L_0x00a2:
        r0 = com.etsy.android.lib.R.about;
        r0 = r5.getString(r0);
        r1 = r0;
        goto L_0x0013;
    L_0x00ab:
        r0 = com.etsy.android.lib.R.updates;
        r0 = r5.getString(r0);
        r1 = r0;
        goto L_0x0013;
    L_0x00b4:
        r0 = com.etsy.android.lib.R.tab_title_local;
        r0 = r5.getString(r0);
        r1 = r0;
        goto L_0x0013;
    L_0x00bd:
        r0 = com.etsy.android.lib.R.policies;
        r0 = r5.getString(r0);
        r1 = r0;
        goto L_0x0013;
    L_0x00c6:
        r0 = com.etsy.android.lib.R.shop_home_tab_title_more;
        r0 = r5.getString(r0);
        r1 = r0;
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.uikit.util.shop.e.a(android.support.design.widget.TabLayout, android.content.res.Resources, java.lang.String, int):void");
    }

    public static int m5615a(@Nullable Tab tab) {
        Object tag = tab != null ? tab.getTag() : null;
        if (tag != null) {
            return ((ShopHomeTabsUtil) tag).f4187a;
        }
        return 0;
    }

    public static int m5619b(@Nullable Tab tab) {
        Object tag = tab != null ? tab.getTag() : null;
        if (tag != null) {
            return ((ShopHomeTabsUtil) tag).f4188b;
        }
        return 0;
    }

    public static void m5618a(@NonNull TabLayout tabLayout, @Nullable Tab tab, int i) {
        Object tag = tab != null ? tab.getTag() : null;
        if (tag != null) {
            ShopHomeTabsUtil shopHomeTabsUtil = (ShopHomeTabsUtil) tag;
            int i2 = i - shopHomeTabsUtil.f4188b;
            shopHomeTabsUtil.f4188b = i;
            int position = tab.getPosition() + 1;
            int tabCount = tabLayout.getTabCount();
            for (int i3 = position; i3 < tabCount; i3++) {
                Tab tabAt = tabLayout.getTabAt(i3);
                if (tabAt != null) {
                    tag = tabAt.getTag();
                } else {
                    tag = null;
                }
                if (tag != null) {
                    shopHomeTabsUtil = (ShopHomeTabsUtil) tag;
                    shopHomeTabsUtil.f4187a += i2;
                    if (shopHomeTabsUtil.f4188b >= 0) {
                        shopHomeTabsUtil.f4188b += i2;
                    }
                }
            }
        }
    }

    @Nullable
    public static Tab m5616a(@NonNull TabLayout tabLayout, String str) {
        int tabCount = tabLayout.getTabCount();
        for (int i = 0; i < tabCount; i++) {
            Tab tabAt = tabLayout.getTabAt(i);
            if (((ShopHomeTabsUtil) tabAt.getTag()).f4189c.equals(str)) {
                return tabAt;
            }
        }
        return null;
    }

    @Nullable
    public static String m5620c(@NonNull Tab tab) {
        Object tag = tab.getTag();
        if (tag == null || !(tag instanceof ShopHomeTabsUtil)) {
            return null;
        }
        return ((ShopHomeTabsUtil) tag).f4189c;
    }
}
