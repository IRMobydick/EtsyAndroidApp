package com.etsy.android.lib.models.datatypes;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.apiv3.ShopV3;
import com.etsy.android.lib.models.interfaces.ShopHomeFilterOption;
import com.fasterxml.jackson.core.JsonParser;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ShopHomeSortOption extends BaseModel implements ShopHomeFilterOption {
    public static final String SORT_CUSTOM = "custom";
    public static final String SORT_DATE_DESC = "date_desc";
    public static final String SORT_PRICE_ASC = "price_asc";
    public static final String SORT_PRICE_DESC = "price_desc";
    protected CharSequence mDisplayLabel;
    protected String mOptionId;
    protected String mTitle;

    public ShopHomeSortOption(@NonNull String str, @NonNull String str2) {
        this.mOptionId = StringUtils.EMPTY;
        this.mTitle = StringUtils.EMPTY;
        this.mDisplayLabel = StringUtils.EMPTY;
        this.mTitle = str2;
        this.mOptionId = str;
    }

    ShopHomeSortOption() {
        this.mOptionId = StringUtils.EMPTY;
        this.mTitle = StringUtils.EMPTY;
        this.mDisplayLabel = StringUtils.EMPTY;
    }

    public String getOptionId() {
        return this.mOptionId;
    }

    public void parseData(JsonParser jsonParser) {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ShopHomeSortOption)) {
            return false;
        }
        return this.mOptionId.equals(((ShopHomeSortOption) obj).getOptionId());
    }

    public int hashCode() {
        return this.mOptionId.hashCode();
    }

    public String toString() {
        return this.mTitle;
    }

    public void setDisplayLabel(CharSequence charSequence) {
        this.mDisplayLabel = charSequence;
    }

    public CharSequence getDisplayLabel() {
        return this.mDisplayLabel;
    }

    public CharSequence getDropdownLabel() {
        return this.mTitle;
    }

    public static List<ShopHomeSortOption> defaultSortOptions(@NonNull Resources resources, @NonNull ShopV3 shopV3) {
        List<ShopHomeSortOption> arrayList = new ArrayList();
        if (shopV3.isListingRearrangeEnabled()) {
            arrayList.add(new ShopHomeSortOption(SORT_CUSTOM, resources.getString(R.order_custom)));
        }
        arrayList.add(new ShopHomeSortOption(SORT_DATE_DESC, resources.getString(R.order_most_recent)));
        arrayList.add(new ShopHomeSortOption(SORT_PRICE_DESC, resources.getString(R.order_highest_price)));
        arrayList.add(new ShopHomeSortOption(SORT_PRICE_ASC, resources.getString(R.order_lowest_price)));
        return arrayList;
    }
}
