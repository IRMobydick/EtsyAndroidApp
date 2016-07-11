package com.etsy.android.uikit.adapter;

import android.content.Context;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.Region;
import java.util.List;

public class ClearableCountryRegionAdapter extends CountryRegionAdapter {
    private static Country empty;

    static {
        empty = new Country();
    }

    public ClearableCountryRegionAdapter(Context context, List<Country> list, List<Region> list2, Country country) {
        super(context, (List) list, (List) list2, country, true);
    }

    protected void setupData() {
        internalAdd(empty);
        super.setupData();
    }
}
