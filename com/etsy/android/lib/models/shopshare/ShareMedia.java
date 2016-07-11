package com.etsy.android.lib.models.shopshare;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.List;

public class ShareMedia extends BaseModel {
    private List<ShareAnnotation> mAnnotations;
    private Image mImage;
    private EtsyId mMediaId;
    private int mMediaType;
    private int mOriginalHeight;
    private int mOriginalWidth;

    public ShareMedia() {
        this.mMediaId = new EtsyId();
    }

    public EtsyId getMediaId() {
        return this.mMediaId;
    }

    public int getMediaType() {
        return this.mMediaType;
    }

    public int getOriginalWidth() {
        return this.mOriginalWidth;
    }

    public int getOriginalHeight() {
        return this.mOriginalHeight;
    }

    public List<ShareAnnotation> getAnnotations() {
        return this.mAnnotations;
    }

    public ShareAnnotation getPrimaryAnnotation() {
        return (ShareAnnotation) this.mAnnotations.iterator().next();
    }

    public Image getImage() {
        return this.mImage;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.END_ARRAY) {
                String currentName = jsonParser.getCurrentName();
                jsonParser.nextToken();
                if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                    Object obj = -1;
                    switch (currentName.hashCode()) {
                        case -961709276:
                            if (currentName.equals(ResponseConstants.ANNOTATIONS)) {
                                obj = 4;
                                break;
                            }
                            break;
                        case -900774058:
                            if (currentName.equals(ResponseConstants.MEDIA_ID)) {
                                obj = null;
                                break;
                            }
                            break;
                        case -636516523:
                            if (currentName.equals(ResponseConstants.ORIGINAL_HEIGHT)) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 100313435:
                            if (currentName.equals(ResponseConstants.IMAGE)) {
                                obj = 5;
                                break;
                            }
                            break;
                        case 1933097432:
                            if (currentName.equals(ResponseConstants.ORIGINAL_WIDTH)) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 1939875509:
                            if (currentName.equals(ResponseConstants.MEDIA_TYPE)) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case Task.NETWORK_STATE_CONNECTED /*0*/:
                            this.mMediaId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                            break;
                        case Task.NETWORK_STATE_UNMETERED /*1*/:
                            this.mMediaType = jsonParser.getValueAsInt();
                            break;
                        case Task.NETWORK_STATE_ANY /*2*/:
                            this.mOriginalWidth = jsonParser.getValueAsInt();
                            break;
                        case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                            this.mOriginalHeight = jsonParser.getValueAsInt();
                            break;
                        case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                            if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY && jsonParser.nextToken() != JsonToken.END_ARRAY) {
                                this.mAnnotations = BaseModel.parseArray(jsonParser, ShareAnnotation.class);
                                break;
                            }
                        case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                            this.mImage = (Image) BaseModel.parseObject(jsonParser, Image.class);
                            break;
                        default:
                            jsonParser.skipChildren();
                            break;
                    }
                }
            }
        }
    }
}
