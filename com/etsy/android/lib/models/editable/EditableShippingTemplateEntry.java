package com.etsy.android.lib.models.editable;

import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.Region;
import com.etsy.android.lib.models.ShippingTemplateEntry;
import com.etsy.android.lib.models.datatypes.EtsyId;

public class EditableShippingTemplateEntry extends ShippingTemplateEntry {
    public EditableShippingTemplateEntry(ShippingTemplateEntry shippingTemplateEntry) {
        this.mId = shippingTemplateEntry.getId();
        this.mTemplateId = shippingTemplateEntry.getTemplateId();
        this.mPrimaryCost = shippingTemplateEntry.getPrimaryCost();
        this.mPrimaryCostInPennies = shippingTemplateEntry.getPrimaryCostInPennies();
        this.mSecondaryCost = shippingTemplateEntry.getSecondaryCost();
        this.mSecondaryCostInPennies = shippingTemplateEntry.getSecondaryCostInPennies();
        this.mCurrencyCode = shippingTemplateEntry.getCurrencyCode();
        this.mOriginCountry = shippingTemplateEntry.getOriginCountry();
        this.mDestinationCountry = shippingTemplateEntry.getDestinationCountry();
        this.mDestinationRegion = shippingTemplateEntry.getDestinationRegion();
    }

    public void setTemplateId(EtsyId etsyId) {
        this.mTemplateId = etsyId;
    }

    public void setPrimaryCost(String str) {
        this.mPrimaryCost = str;
        setPrimaryCostInPennies();
    }

    public void setSecondaryCost(String str) {
        this.mSecondaryCost = str;
        setSecondaryCostInPennies();
    }

    public void setOriginCountry(Country country) {
        this.mOriginCountry = country;
    }

    public void setDestinationCountry(Country country) {
        this.mDestinationCountry = country;
    }

    public void setDestinationRegion(Region region) {
        this.mDestinationRegion = region;
    }
}
