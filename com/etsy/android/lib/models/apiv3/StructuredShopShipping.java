package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class StructuredShopShipping extends BaseModel {
    private static final long serialVersionUID = -2994509211380642461L;
    protected List<StructuredShopShippingEstimate> mEstimates;
    protected boolean mHasShippingUpgrades;
    protected String mProcessingTimeText;
    protected boolean mShipsInternational;

    public StructuredShopShipping() {
        this.mEstimates = new ArrayList();
        this.mProcessingTimeText = StringUtils.EMPTY;
    }

    public StructuredShopShipping(StructuredShopShipping structuredShopShipping) {
        this.mEstimates = new ArrayList();
        this.mProcessingTimeText = StringUtils.EMPTY;
        for (StructuredShopShippingEstimate structuredShopShippingEstimate : structuredShopShipping.getEstimates()) {
            this.mEstimates.add(new StructuredShopShippingEstimate(structuredShopShippingEstimate));
        }
        this.mHasShippingUpgrades = structuredShopShipping.hasShippingUpgrades();
        this.mShipsInternational = structuredShopShipping.shipsInternational();
        this.mProcessingTimeText = structuredShopShipping.getProcessingTimeText();
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -1371807226:
                        if (currentName.equals(ResponseConstants.SHIPS_INTERNATIONAL)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case -623607733:
                        if (currentName.equals(ResponseConstants.ESTIMATES)) {
                            obj = null;
                            break;
                        }
                        break;
                    case -538635981:
                        if (currentName.equals(ResponseConstants.PROCESSING_TIME_TEXT)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case -486847773:
                        if (currentName.equals(ResponseConstants.HAS_SHIPPING_UPGRADES)) {
                            obj = 1;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mEstimates = BaseModel.parseArray(jsonParser, StructuredShopShippingEstimate.class);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mHasShippingUpgrades = jsonParser.getValueAsBoolean();
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mShipsInternational = jsonParser.getValueAsBoolean();
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mProcessingTimeText = BaseModel.parseString(jsonParser);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    @NonNull
    public List<StructuredShopShippingEstimate> getEstimates() {
        return this.mEstimates;
    }

    public void setEstimates(@NonNull List<StructuredShopShippingEstimate> list) {
        this.mEstimates = list;
    }

    @JsonIgnore
    public boolean hasSetEstimates() {
        for (StructuredShopShippingEstimate isSet : this.mEstimates) {
            if (isSet.isSet()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasShippingUpgrades() {
        return this.mHasShippingUpgrades;
    }

    public boolean shipsInternational() {
        return this.mShipsInternational;
    }

    @NonNull
    public String getProcessingTimeText() {
        if (!bh.m3343b(this.mProcessingTimeText)) {
            this.mProcessingTimeText = EtsyApplication.get().getResources().getString(R.structured_shipping_processing_time_message_default);
        }
        return this.mProcessingTimeText;
    }

    public boolean equals(Object obj) {
        return (obj instanceof StructuredShopShipping) && this.mShipsInternational == ((StructuredShopShipping) obj).shipsInternational() && this.mHasShippingUpgrades == ((StructuredShopShipping) obj).hasShippingUpgrades() && ObjectUtils.equals(this.mEstimates, ((StructuredShopShipping) obj).getEstimates()) && ObjectUtils.equals(this.mProcessingTimeText, ((StructuredShopShipping) obj).getProcessingTimeText());
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        if (this.mShipsInternational) {
            i = 0;
        } else {
            i = 1;
        }
        i = (i + 527) * 31;
        if (!this.mHasShippingUpgrades) {
            i2 = 1;
        }
        return ((((i + i2) * 31) + ObjectUtils.hashCode(this.mEstimates)) * 31) + ObjectUtils.hashCode(this.mProcessingTimeText);
    }
}
