package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.editable.EditableListing;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;

public class ShippingTemplateEntry extends BaseModel implements Comparable<ShippingTemplateEntry> {
    private static final String TAG;
    protected String mCurrencyCode;
    protected Country mDestinationCountry;
    protected Region mDestinationRegion;
    protected EtsyId mId;
    protected Country mOriginCountry;
    protected String mPrimaryCost;
    protected int mPrimaryCostInPennies;
    protected String mSecondaryCost;
    protected int mSecondaryCostInPennies;
    protected EtsyId mTemplateId;

    static {
        TAG = EtsyDebug.m1891a(ShippingTemplateEntry.class);
    }

    public ShippingTemplateEntry() {
        this.mId = new EtsyId();
        this.mTemplateId = new EtsyId();
        this.mCurrencyCode = StringUtils.EMPTY;
        this.mPrimaryCost = StringUtils.EMPTY;
        this.mSecondaryCost = StringUtils.EMPTY;
        this.mPrimaryCostInPennies = 0;
        this.mSecondaryCostInPennies = 0;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if ("shipping_template_entry_id".equals(currentName)) {
                    this.mId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                }
                if (EditableListing.FIELD_SHIPPING_TEMPLATE_ID.equals(currentName)) {
                    this.mTemplateId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.CURRENCY_CODE.equals(currentName)) {
                    this.mCurrencyCode = BaseModel.parseString(jsonParser);
                } else if ("primary_cost".equals(currentName)) {
                    this.mPrimaryCost = BaseModel.parseString(jsonParser);
                    setPrimaryCostInPennies();
                } else if ("secondary_cost".equals(currentName)) {
                    this.mSecondaryCost = BaseModel.parseString(jsonParser);
                    setSecondaryCostInPennies();
                } else if ("OriginCountry".equals(currentName)) {
                    this.mOriginCountry = (Country) BaseModel.parseObject(jsonParser, Country.class);
                } else if ("DestinationCountry".equals(currentName)) {
                    this.mDestinationCountry = (Country) BaseModel.parseObject(jsonParser, Country.class);
                } else if ("DestinationRegion".equals(currentName)) {
                    this.mDestinationRegion = (Region) BaseModel.parseObject(jsonParser, Region.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public EtsyId getTemplateId() {
        return this.mTemplateId;
    }

    public EtsyId getId() {
        return this.mId;
    }

    public String getCurrencyCode() {
        return this.mCurrencyCode;
    }

    public void setCurrencyCode(String str) {
        this.mCurrencyCode = str;
    }

    public String getPrimaryCost() {
        return this.mPrimaryCost;
    }

    protected void setPrimaryCostInPennies() {
        try {
            this.mPrimaryCostInPennies = (int) (Double.parseDouble(this.mPrimaryCost) * 100.0d);
        } catch (Throwable e) {
            EtsyDebug.m1897a(TAG, "Couldn't parse primary cost to pennies", e);
        }
    }

    public int getPrimaryCostInPennies() {
        return this.mPrimaryCostInPennies;
    }

    public String getSecondaryCost() {
        return this.mSecondaryCost;
    }

    public int getSecondaryCostInPennies() {
        return this.mSecondaryCostInPennies;
    }

    protected void setSecondaryCostInPennies() {
        try {
            this.mSecondaryCostInPennies = (int) (Double.parseDouble(this.mSecondaryCost) * 100.0d);
        } catch (Throwable e) {
            EtsyDebug.m1897a(TAG, "Couldn't parse primary cost to pennies", e);
        }
    }

    public Country getOriginCountry() {
        return this.mOriginCountry;
    }

    public EtsyId getOriginCountryId() {
        if (this.mOriginCountry != null) {
            return new EtsyId((long) this.mOriginCountry.getCountryId());
        }
        return new EtsyId();
    }

    public Country getDestinationCountry() {
        return this.mDestinationCountry;
    }

    public EtsyId getDestinationCountryId() {
        if (this.mDestinationCountry != null) {
            return new EtsyId((long) this.mDestinationCountry.getCountryId());
        }
        return new EtsyId();
    }

    public Region getDestinationRegion() {
        return this.mDestinationRegion;
    }

    public EtsyId getDestinationRegionId() {
        if (this.mDestinationRegion != null) {
            return new EtsyId((long) this.mDestinationRegion.getRegionId());
        }
        return new EtsyId();
    }

    public boolean isDestinationRegionSet() {
        return this.mDestinationRegion != null;
    }

    public boolean isDestinationCountrySet() {
        return this.mDestinationCountry != null;
    }

    public boolean shipsEverywhere() {
        return this.mDestinationCountry == null && this.mDestinationRegion == null;
    }

    public boolean isDomestic() {
        return (this.mDestinationCountry == null || this.mOriginCountry == null || this.mDestinationCountry.getCountryId() != this.mOriginCountry.getCountryId()) ? false : true;
    }

    public int compareTo(ShippingTemplateEntry shippingTemplateEntry) {
        if (shipsEverywhere()) {
            return 1;
        }
        if (shippingTemplateEntry.shipsEverywhere()) {
            return -1;
        }
        if (isDomestic() && !isDestinationRegionSet()) {
            return -1;
        }
        if (isDestinationRegionSet()) {
            if (shippingTemplateEntry.isDestinationRegionSet()) {
                return bh.m3330a(getDestinationRegion().getRegionName(), shippingTemplateEntry.getDestinationRegion().getRegionName());
            }
            return 1;
        } else if (shippingTemplateEntry.isDestinationRegionSet()) {
            return -1;
        } else {
            return bh.m3330a(getDestinationCountry().getName(), shippingTemplateEntry.getDestinationCountry().getName());
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ShippingTemplateEntry)) {
            return false;
        }
        if (this.mId.hasId() && ((ShippingTemplateEntry) obj).getId().hasId()) {
            return this.mId.equals(((ShippingTemplateEntry) obj).getId());
        }
        if (this.mId.hasId() || ((ShippingTemplateEntry) obj).getId().hasId()) {
            return false;
        }
        return this == obj;
    }

    public int hashCode() {
        if (this.mId.hasId()) {
            return (int) this.mId.getIdAsLong();
        }
        return super.hashCode();
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.SHIPPING_TEMPLATE_ID, this.mTemplateId.getId());
        hashMap.put(AnalyticsLogAttribute.SHIPPING_TEMPLATE_ENTRY_ID, this.mId.getId());
        return hashMap;
    }
}
