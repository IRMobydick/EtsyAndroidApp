package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import org.parceler.Parcel;

@Parcel
public class Offering extends BaseFieldModel {
    private static final long serialVersionUID = -638200119124130429L;
    protected EtsyId mOfferingId;
    protected OfferingPrice mPrice;
    protected EtsyId mProductId;
    protected int mQuantity;

    public Offering() {
        this.mOfferingId = new EtsyId();
        this.mProductId = new EtsyId();
        this.mPrice = new OfferingPrice();
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        boolean z = true;
        switch (str.hashCode()) {
            case -1285004149:
                if (str.equals(ResponseConstants.QUANTITY)) {
                    z = true;
                    break;
                }
                break;
            case 106934601:
                if (str.equals(ResponseConstants.PRICE)) {
                    z = true;
                    break;
                }
                break;
            case 997792756:
                if (str.equals(ResponseConstants.OFFERING_ID)) {
                    z = false;
                    break;
                }
                break;
            case 1753008747:
                if (str.equals(ResponseConstants.PRODUCT_ID)) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                this.mOfferingId.setId(BaseModel.parseString(jsonParser));
                break;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                this.mProductId.setId(BaseModel.parseString(jsonParser));
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                this.mQuantity = jsonParser.getValueAsInt();
                break;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                this.mPrice = (OfferingPrice) BaseModel.parseObject(jsonParser, OfferingPrice.class);
                break;
            default:
                return false;
        }
        return true;
    }

    @NonNull
    public EtsyId getOfferingId() {
        return this.mOfferingId;
    }

    @NonNull
    public EtsyId getProductId() {
        return this.mProductId;
    }

    public int getQuantity() {
        return this.mQuantity;
    }

    @NonNull
    public OfferingPrice getPrice() {
        return this.mPrice;
    }
}
