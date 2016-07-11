package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.parceler.Parcel;

@Parcel
public class ShopVacation extends BaseModel {
    private static final long serialVersionUID = 2643630776118197144L;
    protected String mVacationAutoReply;
    protected Boolean mVacationEnabled;
    protected String mVacationMsg;

    public String getVacationMsg() {
        return this.mVacationMsg;
    }

    public String getVacationAutoReply() {
        return this.mVacationAutoReply;
    }

    public Boolean getVacationEnabled() {
        return this.mVacationEnabled;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (!(jsonParser.getCurrentToken() == JsonToken.VALUE_NULL || ResponseConstants.RESULTS.equals(currentName))) {
                if (ResponseConstants.IS_VACATION.equals(currentName)) {
                    this.mVacationEnabled = Boolean.valueOf(jsonParser.getValueAsBoolean());
                } else if (ResponseConstants.VACATION_MESSAGE.equals(currentName)) {
                    this.mVacationMsg = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.VACATION_AUTOREPLY.equals(currentName)) {
                    this.mVacationAutoReply = BaseModel.parseString(jsonParser).trim();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
