package com.appboy.models;

import bo.app.fg;
import com.appboy.enums.ErrorType;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import org.json.JSONObject;

public final class ResponseError {
    private final ErrorType f948a;
    private final String f949b;

    public ResponseError(JSONObject jSONObject) {
        this.f948a = (ErrorType) fg.m338a(jSONObject, FindsModule.FIELD_TYPE, ErrorType.class);
        this.f949b = jSONObject.getString(ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE);
    }

    public ResponseError(ErrorType errorType, String str) {
        this.f948a = errorType;
        this.f949b = str;
    }

    public final ErrorType getType() {
        return this.f948a;
    }

    public final String getMessage() {
        return this.f949b;
    }
}
