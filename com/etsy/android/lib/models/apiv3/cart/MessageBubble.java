package com.etsy.android.lib.models.apiv3.cart;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.commons.lang3.StringEscapeUtils;
import org.parceler.Parcel;

@Parcel
public class MessageBubble extends BaseMessage {
    private static final long serialVersionUID = 6439797631362249870L;
    private final String TAG;
    protected String mIconChar;

    public MessageBubble() {
        this.TAG = EtsyDebug.m1891a(MessageBubble.class);
        this.mIconChar = null;
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (!ResponseConstants.ICON_CHAR.equals(str)) {
            return super.parseField(jsonParser, str);
        }
        this.mIconChar = StringEscapeUtils.unescapeJava(BaseModel.parseStringPreserveHTMLEscapeEncoding(jsonParser));
        return true;
    }

    @Nullable
    public String getIconChar() {
        return this.mIconChar;
    }
}
