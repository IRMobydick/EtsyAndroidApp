package com.etsy.android.lib.models.view.shop.section;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.uikit.viewholder.j;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class ShopHomeLocalMarketsSectionViewModel extends ShopHomeBaseSectionViewModel {
    private List<LocalMarket> mLocalMarkets;

    public ShopHomeLocalMarketsSectionViewModel(@NonNull CharSequence charSequence, @NonNull List<LocalMarket> list) {
        super(charSequence);
        this.mLocalMarkets = list;
    }

    public List<LocalMarket> getLocalMarkets() {
        return this.mLocalMarkets;
    }

    public CharSequence getText() {
        return StringUtils.EMPTY;
    }

    public j getListener() {
        return null;
    }
}
