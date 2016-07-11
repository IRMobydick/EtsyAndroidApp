package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.editable.EditableListing;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class ShippingTemplate extends BaseModel {
    private static final long serialVersionUID = -8635377627779223753L;
    protected List<ShippingTemplateEntry> mAllEntries;
    protected List<ShippingTemplateEntry> mCountryEntries;
    protected ShippingTemplateEntry mEverywhereEntry;
    public EtsyId mId;
    protected int mMaxProcessingDays;
    protected int mMinProcessingDays;
    protected Country mOriginCountry;
    protected EtsyId mOriginCountryId;
    protected String mProcessingDaysDisplayLabel;
    protected Map<Region, ShippingTemplateEntry> mRegionalEntries;
    protected String mTitle;
    private EtsyId mUserId;

    public ShippingTemplate() {
        this.mId = new EtsyId();
        this.mTitle = StringUtils.EMPTY;
        this.mProcessingDaysDisplayLabel = StringUtils.EMPTY;
        this.mUserId = new EtsyId();
        this.mOriginCountryId = new EtsyId();
        this.mAllEntries = new ArrayList(1);
        this.mRegionalEntries = new HashMap();
        this.mCountryEntries = new ArrayList();
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (EditableListing.FIELD_SHIPPING_TEMPLATE_ID.equals(currentName)) {
                    this.mId.setId(BaseModel.parseString(jsonParser));
                } else if (EditableListing.FIELD_SHIPPING_PROFILE_ID.equals(currentName)) {
                    this.mId.setId(BaseModel.parseString(jsonParser));
                } else if (ResponseConstants.USER_ID.equals(currentName)) {
                    this.mUserId.setId(BaseModel.parseString(jsonParser));
                } else if (FindsModule.FIELD_TITLE.equals(currentName)) {
                    this.mTitle = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.NAME.equals(currentName)) {
                    this.mTitle = BaseModel.parseString(jsonParser).trim();
                } else if ("origin_country_id".equals(currentName)) {
                    this.mOriginCountryId.setId(BaseModel.parseString(jsonParser));
                } else if ("min_processing_days".equals(currentName)) {
                    this.mMinProcessingDays = jsonParser.getValueAsInt();
                } else if ("max_processing_days".equals(currentName)) {
                    this.mMaxProcessingDays = jsonParser.getValueAsInt();
                } else if ("processing_days_display_label".equals(currentName)) {
                    this.mProcessingDaysDisplayLabel = BaseModel.parseString(jsonParser);
                } else if ("Entries".equals(currentName)) {
                    this.mAllEntries = BaseModel.parseArray(jsonParser, ShippingTemplateEntry.class);
                    sortOutRegionalEntries();
                    pruneRegionals();
                    Collections.sort(this.mAllEntries);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public Country getOriginCountry() {
        if (this.mOriginCountry != null) {
            return this.mOriginCountry;
        }
        if (this.mAllEntries.size() > 0) {
            ShippingTemplateEntry shippingTemplateEntry = (ShippingTemplateEntry) this.mAllEntries.get(0);
            if (shippingTemplateEntry != null) {
                this.mOriginCountry = shippingTemplateEntry.getOriginCountry();
            }
        }
        return this.mOriginCountry;
    }

    public EtsyId getId() {
        return this.mId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public EtsyId getUserId() {
        return this.mUserId;
    }

    public int getMinProcessingDays() {
        return this.mMinProcessingDays;
    }

    public int getMaxProcessingDays() {
        return this.mMaxProcessingDays;
    }

    public List<ShippingTemplateEntry> getAllEntries() {
        return this.mAllEntries;
    }

    public String getProcessingDaysDisplayLabel() {
        return this.mProcessingDaysDisplayLabel;
    }

    public ShippingTemplateEntry getDomesticEntry() {
        for (ShippingTemplateEntry shippingTemplateEntry : this.mAllEntries) {
            if (shippingTemplateEntry.isDomestic()) {
                return shippingTemplateEntry;
            }
        }
        return null;
    }

    public ShippingTemplateEntry getEverywhereEntry() {
        return this.mEverywhereEntry;
    }

    public boolean shipsEverywhere() {
        return this.mEverywhereEntry != null;
    }

    private void sortOutRegionalEntries() {
        this.mRegionalEntries.clear();
        this.mCountryEntries.clear();
        this.mEverywhereEntry = null;
        for (ShippingTemplateEntry shippingTemplateEntry : this.mAllEntries) {
            if (shippingTemplateEntry.isDestinationRegionSet()) {
                if (!this.mRegionalEntries.containsKey(shippingTemplateEntry.getDestinationRegion()) || shippingTemplateEntry.isDomestic()) {
                    this.mRegionalEntries.put(shippingTemplateEntry.getDestinationRegion(), shippingTemplateEntry);
                }
            } else if (shippingTemplateEntry.isDestinationCountrySet()) {
                this.mCountryEntries.add(shippingTemplateEntry);
            } else {
                this.mEverywhereEntry = shippingTemplateEntry;
            }
        }
    }

    private void pruneRegionals() {
        this.mAllEntries.clear();
        if (this.mRegionalEntries.size() > 0) {
            this.mAllEntries.addAll(this.mRegionalEntries.values());
        }
        if (this.mCountryEntries.size() > 0) {
            this.mAllEntries.addAll(this.mCountryEntries);
        }
        if (this.mEverywhereEntry != null) {
            this.mAllEntries.add(this.mEverywhereEntry);
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.SHIPPING_TEMPLATE_ID, this.mId.getId());
        return hashMap;
    }
}
