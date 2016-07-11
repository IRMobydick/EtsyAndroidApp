package com.etsy.android.lib.models.apiv3.ipp;

import com.etsy.android.lib.models.BaseModel;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class AuthStatus extends BaseModel {
    private static final String AUTH_FAILED = "AUTH_FAILED";
    private static final String AUTH_STATUS = "auth_status";
    private static final String AUTH_SUCCESS = "AUTHED";
    private static final String CASH_PAID = "CASH_PAID";
    private static final String REQUIRE_SIGNATURE = "require_signature";
    private static final long serialVersionUID = 7009248203741185804L;
    private boolean mWasSuccess;

    public boolean wasSuccess() {
        return this.mWasSuccess;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (AUTH_STATUS.equals(currentName)) {
                    boolean z = AUTH_SUCCESS.equalsIgnoreCase(BaseModel.parseString(jsonParser)) || CASH_PAID.equalsIgnoreCase(BaseModel.parseStringIdOrNumericValue(jsonParser));
                    this.mWasSuccess = z;
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
