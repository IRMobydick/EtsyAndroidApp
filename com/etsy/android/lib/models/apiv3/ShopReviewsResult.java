package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ReceiptReview;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class ShopReviewsResult extends BaseModel {
    protected int mCount;
    protected List<ReceiptReview> mReviews;

    public List<ReceiptReview> getReviews() {
        return this.mReviews;
    }

    public int getCount() {
        return this.mCount;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case 94851343:
                        if (currentName.equals(ResponseConstants.COUNT)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 1099953179:
                        if (currentName.equals(ResponseConstants.REVIEWS)) {
                            obj = 1;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mCount = jsonParser.getValueAsInt();
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mReviews = BaseModel.parseArray(jsonParser, ReceiptReview.class);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }
}
