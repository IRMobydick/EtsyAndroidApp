package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ListingRearrangeStatus extends BaseModel {
    private static final String STATUS_OK = "ok";
    private static final String STATUS_QUEUED = "queued";
    protected String mInfo;
    protected String mStatus;

    public ListingRearrangeStatus() {
        this.mStatus = StringUtils.EMPTY;
        this.mInfo = StringUtils.EMPTY;
    }

    public boolean isStatusOk() {
        return STATUS_OK.equals(this.mStatus);
    }

    public String getStatus() {
        return this.mStatus;
    }

    public String getInfoMessage() {
        return this.mInfo;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -892481550:
                        if (currentName.equals(ResponseConstants.STATUS)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 3237038:
                        if (currentName.equals(BaseMessage.TYPE_INFO)) {
                            obj = 1;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mStatus = BaseModel.parseString(jsonParser);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mInfo = BaseModel.parseString(jsonParser);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
