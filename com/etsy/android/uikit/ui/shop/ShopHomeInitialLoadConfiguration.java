package com.etsy.android.uikit.ui.shop;

import android.support.annotation.Nullable;
import java.util.Map;
import org.parceler.Parcel;

@Parcel
/* renamed from: com.etsy.android.uikit.ui.shop.a */
public class ShopHomeInitialLoadConfiguration {
    @Nullable
    public Map<String, String> f4114a;
    public int f4115b;

    ShopHomeInitialLoadConfiguration() {
    }

    public ShopHomeInitialLoadConfiguration(int i) {
        this(i, null);
    }

    public ShopHomeInitialLoadConfiguration(int i, @Nullable Map<String, String> map) {
        this.f4115b = i;
        this.f4114a = map;
    }
}
