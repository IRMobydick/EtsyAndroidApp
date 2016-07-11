package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.requests.EtsyRequest;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class AWSSession extends BaseModel {
    protected String mAccessKeyId;
    protected String mBucketName;
    protected String mPath;
    protected String mRegion;
    protected String mSecretAccessKey;
    protected String mSessionToken;

    public AWSSession() {
        this.mAccessKeyId = StringUtils.EMPTY;
        this.mSecretAccessKey = StringUtils.EMPTY;
        this.mSessionToken = StringUtils.EMPTY;
        this.mBucketName = StringUtils.EMPTY;
        this.mRegion = StringUtils.EMPTY;
        this.mPath = StringUtils.EMPTY;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -934795532:
                        if (currentName.equals(EtsyRequest.PARAM_REGION)) {
                            obj = 5;
                            break;
                        }
                        break;
                    case -532231901:
                        if (currentName.equals(ResponseConstants.AWS_SESSION_TOKEN)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case 3433509:
                        if (currentName.equals(ResponseConstants.PATH)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case 616582326:
                        if (currentName.equals(ResponseConstants.AWS_ACCESS_KEY_ID)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 1117008789:
                        if (currentName.equals(ResponseConstants.AWS_BUCKET_NAME)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case 1364055403:
                        if (currentName.equals(ResponseConstants.AWS_SECRET_ACCESS_KEY)) {
                            obj = 1;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mAccessKeyId = BaseModel.parseStringPreserveHTMLEscapeEncoding(jsonParser);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mSecretAccessKey = BaseModel.parseStringPreserveHTMLEscapeEncoding(jsonParser);
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mSessionToken = BaseModel.parseStringPreserveHTMLEscapeEncoding(jsonParser);
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mBucketName = BaseModel.parseStringPreserveHTMLEscapeEncoding(jsonParser);
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.mPath = BaseModel.parseStringPreserveHTMLEscapeEncoding(jsonParser);
                        break;
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        this.mRegion = BaseModel.parseStringPreserveHTMLEscapeEncoding(jsonParser);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    public String getBucketName() {
        return this.mBucketName;
    }

    public String getFileName() {
        return this.mPath;
    }

    public String getAccessKeyId() {
        return this.mAccessKeyId;
    }

    public String getSecretAccessKey() {
        return this.mSecretAccessKey;
    }

    public String getSessionToken() {
        return this.mSessionToken;
    }
}
