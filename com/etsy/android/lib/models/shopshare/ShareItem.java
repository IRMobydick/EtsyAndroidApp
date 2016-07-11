package com.etsy.android.lib.models.shopshare;

import android.support.annotation.Nullable;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.models.interfaces.ShareItemLike;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ShareItem extends BaseModel implements ShareItemLike {
    protected Date mCreateDate;
    protected List<ShareMedia> mMedia;
    protected EtsyId mShareId;
    protected String mText;
    protected String mUrl;

    public ShareItem() {
        this.mText = StringUtils.EMPTY;
        this.mUrl = StringUtils.EMPTY;
        this.mShareId = new EtsyId();
    }

    public EtsyId getShareId() {
        return this.mShareId;
    }

    public String getText() {
        return this.mText;
    }

    public void setText(String str) {
        this.mText = str;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public Date getCreateDate() {
        return this.mCreateDate;
    }

    public List<ShareMedia> getMedia() {
        return this.mMedia;
    }

    public ShareMedia getPrimaryMedia() {
        return (ShareMedia) this.mMedia.iterator().next();
    }

    public ShareAnnotation getPrimaryAnnotation() {
        return getPrimaryMedia().getPrimaryAnnotation();
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.END_ARRAY) {
                String currentName = jsonParser.getCurrentName();
                jsonParser.nextToken();
                if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                    Object obj = -1;
                    switch (currentName.hashCode()) {
                        case -743759493:
                            if (currentName.equals(ResponseConstants.SHARE_ID)) {
                                obj = null;
                                break;
                            }
                            break;
                        case -494058223:
                            if (currentName.equals(ResponseConstants.CREATE_DATE)) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 116079:
                            if (currentName.equals(ResponseConstants.URL)) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 3556653:
                            if (currentName.equals(FindsModule.FIELD_TEXT)) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 103772132:
                            if (currentName.equals(ResponseConstants.MEDIA)) {
                                obj = 4;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case Task.NETWORK_STATE_CONNECTED /*0*/:
                            this.mShareId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                            break;
                        case Task.NETWORK_STATE_UNMETERED /*1*/:
                            this.mText = BaseModel.parseString(jsonParser);
                            break;
                        case Task.NETWORK_STATE_ANY /*2*/:
                            this.mUrl = BaseModel.parseStringURL(jsonParser);
                            break;
                        case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                            this.mCreateDate = BaseModel.parseIntoDate(jsonParser);
                            break;
                        case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                            if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY && jsonParser.nextToken() != JsonToken.END_ARRAY) {
                                this.mMedia = BaseModel.parseArray(jsonParser, ShareMedia.class);
                                break;
                            }
                        default:
                            jsonParser.skipChildren();
                            break;
                    }
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.SHARE_ID, this.mShareId.getId());
        ShareAnnotation primaryAnnotation = getPrimaryAnnotation();
        if (primaryAnnotation != null) {
            EtsyId objectId = primaryAnnotation.getObjectId();
            if (objectId != null) {
                hashMap.put(AnalyticsLogAttribute.ANNOTATION_OBJECT_ID, objectId.getId());
                hashMap.put(AnalyticsLogAttribute.LISTING_ID, objectId.getId());
            }
        }
        return hashMap;
    }

    public static EtsyApiV3Request<ShareItem> createRequestBuilder(String str, String str2) {
        return new EtsyApiV3Request(ShareItem.class, String.format("/public/shops/%s/shares/%s", new Object[]{str, str2}));
    }
}
