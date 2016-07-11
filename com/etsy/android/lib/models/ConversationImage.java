package com.etsy.android.lib.models;

import android.util.Pair;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.ab;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.Date;
import org.parceler.Parcel;

@Parcel
public class ConversationImage extends BaseModelImage {
    private static final Pair[] IMG_SIZES_ARRAY;
    protected EtsyId mConversationMessageId;
    protected Date mCreateDate;
    protected EtsyId mImageId;
    protected int mRank;

    public ConversationImage() {
        this.mConversationMessageId = new EtsyId();
        this.mImageId = new EtsyId();
    }

    static {
        IMG_SIZES_ARRAY = new Pair[]{IMG_SIZE_75, IMG_SIZE_FULL};
    }

    public String getImageUrlForPixelWidth(int i) {
        if (ab.m3172a(i) <= ((Integer) IMG_SIZE_75.first).intValue()) {
            return this.mUrl75x75;
        }
        return this.mUrlFullxFull;
    }

    public Date getCreateDate() {
        return this.mCreateDate;
    }

    public EtsyId getConversationMessageId() {
        return this.mConversationMessageId;
    }

    public int getRank() {
        return this.mRank;
    }

    protected Pair<Integer, String>[] getImageSizesArray() {
        return IMG_SIZES_ARRAY;
    }

    public String getImageUrl() {
        return this.mUrlFullxFull;
    }

    public String getLargestDimension() {
        return (String) IMG_SIZE_FULL.second;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -2078324984:
                        if (currentName.equals(ResponseConstants.URL_FULLxFULL)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case -2048902769:
                        if (currentName.equals(ResponseConstants.CONVERSATION_MESSAGE_ID)) {
                            obj = 5;
                            break;
                        }
                        break;
                    case -68483352:
                        if (currentName.equals(ResponseConstants.URL_75x75)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 3492908:
                        if (currentName.equals(ResponseConstants.RANK)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case 360136379:
                        if (currentName.equals(ResponseConstants.CONVERSATION_IMAGE_ID)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case 1586354299:
                        if (currentName.equals(ResponseConstants.CREATION_TSZ)) {
                            obj = 2;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mUrl75x75 = BaseModel.parseStringURL(jsonParser);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mUrlFullxFull = BaseModel.parseStringURL(jsonParser);
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mCreateDate = BaseModel.parseIntoDate(jsonParser);
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mRank = jsonParser.getValueAsInt();
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.mConversationMessageId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                        break;
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        this.mConversationMessageId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }
}
