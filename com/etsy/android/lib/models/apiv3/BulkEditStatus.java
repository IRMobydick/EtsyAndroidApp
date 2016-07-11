package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.requests.ReceiptsRequest;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class BulkEditStatus extends BaseModel {
    private int completed;
    private int status;
    private int total;

    public int getStatus() {
        return this.status;
    }

    public int getCompleted() {
        return this.completed;
    }

    public int getTotal() {
        return this.total;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.STATUS.equals(currentName)) {
                    this.status = jsonParser.getValueAsInt();
                } else if (ReceiptsRequest.STATUS_COMPLETED.equals(currentName)) {
                    this.status = jsonParser.getValueAsInt();
                } else if (ResponseConstants.TOTAL.equals(currentName)) {
                    this.total = jsonParser.getValueAsInt();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
