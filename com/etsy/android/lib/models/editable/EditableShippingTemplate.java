package com.etsy.android.lib.models.editable;

import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.ShippingTemplate;
import com.etsy.android.lib.models.ShippingTemplateEntry;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.ArrayList;
import java.util.List;

public class EditableShippingTemplate extends ShippingTemplate {
    public void setMinProcessingDays(int i) {
        this.mMinProcessingDays = i;
    }

    public void setMaxProcessingDays(int i) {
        this.mMaxProcessingDays = i;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setOriginCountry(Country country) {
        this.mOriginCountry = country;
        this.mOriginCountryId = new EtsyId((long) country.getCountryId());
    }

    public List<EditableShippingTemplateEntry> getEditableEntries() {
        List<EditableShippingTemplateEntry> arrayList = new ArrayList();
        for (ShippingTemplateEntry shippingTemplateEntry : getAllEntries()) {
            Object editableShippingTemplateEntry;
            if (shippingTemplateEntry instanceof EditableShippingTemplateEntry) {
                EditableShippingTemplateEntry editableShippingTemplateEntry2 = (EditableShippingTemplateEntry) shippingTemplateEntry;
            } else {
                editableShippingTemplateEntry = new EditableShippingTemplateEntry(shippingTemplateEntry);
            }
            arrayList.add(editableShippingTemplateEntry);
        }
        return arrayList;
    }

    public void clearEntries() {
        this.mAllEntries.clear();
        this.mEverywhereEntry = null;
        this.mRegionalEntries.clear();
        this.mCountryEntries.clear();
    }

    public void setEntries(List<EditableShippingTemplateEntry> list) {
        clearEntries();
        if (list != null) {
            for (EditableShippingTemplateEntry addTemplateEntry : list) {
                addTemplateEntry(addTemplateEntry);
            }
        }
    }

    public void addTemplateEntry(EditableShippingTemplateEntry editableShippingTemplateEntry) {
        if (editableShippingTemplateEntry != null) {
            this.mAllEntries.add(editableShippingTemplateEntry);
            if (editableShippingTemplateEntry.shipsEverywhere()) {
                this.mEverywhereEntry = editableShippingTemplateEntry;
            } else if (editableShippingTemplateEntry.isDestinationRegionSet()) {
                this.mRegionalEntries.put(editableShippingTemplateEntry.getDestinationRegion(), editableShippingTemplateEntry);
            } else {
                this.mCountryEntries.add(editableShippingTemplateEntry);
            }
        }
    }

    public void removeTemplateEntry(EditableShippingTemplateEntry editableShippingTemplateEntry) {
        this.mAllEntries.remove(editableShippingTemplateEntry);
        this.mCountryEntries.remove(editableShippingTemplateEntry);
        if (editableShippingTemplateEntry.isDestinationRegionSet()) {
            this.mRegionalEntries.remove(editableShippingTemplateEntry.getDestinationRegion());
        } else if (editableShippingTemplateEntry.shipsEverywhere()) {
            this.mEverywhereEntry = null;
        }
    }

    public void setProcessingDaysDisplayLabel(String str) {
        this.mProcessingDaysDisplayLabel = str;
    }

    public void setId(String str) {
        this.mId.setId(str);
    }
}
