package com.etsy.android.lib.models.cardviewelement;

import android.support.annotation.NonNull;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class BaseMessage extends BaseFieldModel {
    public static final String TYPE_ERROR = "error";
    public static final String TYPE_INFO = "info";
    public static final String TYPE_NOTIFY = "notify";
    public static final String TYPE_SUCCESS = "success";
    public static final String TYPE_WARNING = "warning";
    private static final long serialVersionUID = 6439797631362249870L;
    private final String TAG;
    protected boolean mHasPointer;
    protected String mMessage;
    protected String mType;

    public BaseMessage() {
        this.TAG = EtsyDebug.m1891a(BaseMessage.class);
        this.mHasPointer = true;
        this.mMessage = StringUtils.EMPTY;
        this.mType = TYPE_INFO;
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE.equals(str)) {
            this.mMessage = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.HAS_POINTER.equals(str)) {
            this.mHasPointer = jsonParser.getBooleanValue();
        } else if (!FindsModule.FIELD_TYPE.equals(str)) {
            return false;
        } else {
            this.mType = BaseModel.parseString(jsonParser);
        }
        return true;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public boolean getHasPointer() {
        return this.mHasPointer;
    }

    @NonNull
    public String getType() {
        return this.mType;
    }
}
