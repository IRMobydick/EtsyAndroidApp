package com.etsy.android.lib.models;

import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class ExternalAccountResult extends BaseModel {
    private String mEmail;
    private String mErrorCode;
    private String mErrorMessage;
    private String mErrorType;
    private boolean mLoginPossible;
    private String mRegisterMessage;
    private boolean mSignInPossible;
    private boolean mSuccess;

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (ResponseConstants.EMAIL.equals(currentName)) {
                this.mEmail = BaseModel.parseString(jsonParser);
            } else if ("errorMsg".equals(currentName)) {
                this.mErrorMessage = BaseModel.parseString(jsonParser);
            } else if ("errorType".equals(currentName)) {
                this.mErrorType = BaseModel.parseString(jsonParser);
            } else if ("error_code".equals(currentName)) {
                this.mErrorCode = BaseModel.parseString(jsonParser);
            } else if ("registerMsg".equals(currentName)) {
                this.mRegisterMessage = BaseModel.parseString(jsonParser);
            } else if ("loginPossible".equals(currentName)) {
                this.mLoginPossible = jsonParser.getBooleanValue();
            } else if ("signinPossible".equals(currentName)) {
                this.mSignInPossible = jsonParser.getBooleanValue();
            } else if (BaseMessage.TYPE_SUCCESS.equals(currentName)) {
                this.mSuccess = jsonParser.getBooleanValue();
            } else {
                jsonParser.skipChildren();
            }
        }
    }

    public boolean isLoginPossible() {
        return this.mLoginPossible;
    }

    public boolean isSignInPossible() {
        return this.mSignInPossible;
    }

    public String getEtsyEmail() {
        return this.mEmail;
    }
}
