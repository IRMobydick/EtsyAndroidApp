package com.etsy.android.lib.models;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.editable.EditableListing;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.models.interfaces.ShopHomeFilterOption;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.parceler.Parcel;

@Parcel
public class ShopSection extends BaseModel implements ShopHomeFilterOption {
    private static final long serialVersionUID = 5450494864709068115L;
    protected CharSequence mDisplayLabel;
    protected int mListingActiveCount;
    protected int mRank;
    protected EtsyId mShopSectionId;
    protected String mTitle;

    public static ShopSection allItemsSection(Resources resources) {
        return new ShopSection(resources.getString(R.all_items), StringUtils.EMPTY);
    }

    public ShopSection() {
        this.mTitle = StringUtils.EMPTY;
        this.mDisplayLabel = StringUtils.EMPTY;
        this.mShopSectionId = new EtsyId();
    }

    public ShopSection(String str, String str2) {
        this.mTitle = StringUtils.EMPTY;
        this.mDisplayLabel = StringUtils.EMPTY;
        this.mTitle = str;
        this.mShopSectionId = new EtsyId(str2);
    }

    public EtsyId getShopSectionId() {
        return this.mShopSectionId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int getListingActiveCount() {
        return this.mListingActiveCount;
    }

    public int getRank() {
        return this.mRank;
    }

    public boolean isAllItemsSection() {
        return !this.mShopSectionId.hasId();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ShopSection)) {
            return false;
        }
        ShopSection shopSection = (ShopSection) obj;
        if (shopSection.getTitle().equals(this.mTitle) && shopSection.getShopSectionId().equals(this.mShopSectionId)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return new HashCodeBuilder().append(this.mTitle).append(this.mShopSectionId).build().intValue();
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (EditableListing.FIELD_SHOP_SECTION_ID.equals(currentName)) {
                    this.mShopSectionId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.ACTIVE_LISTING_COUNT.equals(currentName)) {
                    this.mListingActiveCount = jsonParser.getValueAsInt();
                } else if (ResponseConstants.RANK.equals(currentName)) {
                    this.mRank = jsonParser.getValueAsInt();
                } else if (FindsModule.FIELD_TITLE.equals(currentName)) {
                    this.mTitle = BaseModel.parseString(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public String toString() {
        return this.mTitle;
    }

    public String getDropdownLabel() {
        return this.mTitle;
    }

    public CharSequence getDisplayLabel() {
        return this.mDisplayLabel;
    }

    public void setDisplayLabel(CharSequence charSequence) {
        this.mDisplayLabel = charSequence;
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.SHOP_ID, this.mShopSectionId.getId());
        return hashMap;
    }
}
