package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.editable.EditableListingV3;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class FAQ extends BaseModel {
    public static String STATE_ACTIVE = null;
    public static String STATE_DELETED = null;
    private static final long serialVersionUID = -4592502502388303727L;
    protected String mAnswer;
    protected EtsyId mFaqId;
    protected String mLanguage;
    protected String mQuestion;
    protected int mRank;
    protected boolean mShowTranslatedFAQ;
    protected String mState;
    protected String mTranslatedAnswer;
    protected String mTranslatedQuestion;

    public FAQ() {
        this.mFaqId = new EtsyId();
        this.mQuestion = StringUtils.EMPTY;
        this.mTranslatedQuestion = StringUtils.EMPTY;
        this.mAnswer = StringUtils.EMPTY;
        this.mTranslatedAnswer = StringUtils.EMPTY;
        this.mState = StringUtils.EMPTY;
        this.mShowTranslatedFAQ = false;
        this.mLanguage = StringUtils.EMPTY;
    }

    static {
        STATE_ACTIVE = EditableListingV3.LISTING_STATE_ACTIVE;
        STATE_DELETED = "deleted";
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -1613589672:
                        if (currentName.equals(EtsyRequest.PARAM_LANGUAGE)) {
                            obj = 5;
                            break;
                        }
                        break;
                    case -1412808770:
                        if (currentName.equals(ResponseConstants.ANSWER)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case -1281751324:
                        if (currentName.equals(ResponseConstants.FAQ_ID)) {
                            obj = null;
                            break;
                        }
                        break;
                    case -1165870106:
                        if (currentName.equals(ResponseConstants.QUESTION)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 3492908:
                        if (currentName.equals(ResponseConstants.RANK)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case 109757585:
                        if (currentName.equals(ResponseConstants.STATE)) {
                            obj = 3;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mFaqId.setId(BaseModel.parseString(jsonParser));
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mQuestion = BaseModel.parseString(jsonParser);
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mAnswer = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mState = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.mRank = jsonParser.getValueAsInt();
                        break;
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        this.mLanguage = BaseModel.parseString(jsonParser);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    public EtsyId getFaqId() {
        return this.mFaqId;
    }

    public void setFaqId(EtsyId etsyId) {
        this.mFaqId = etsyId;
    }

    public String getQuestion() {
        if (this.mShowTranslatedFAQ && bh.m3343b(this.mTranslatedQuestion)) {
            return this.mTranslatedQuestion;
        }
        return this.mQuestion;
    }

    public void setQuestion(String str) {
        this.mQuestion = str;
    }

    public void setTranslatedQuestion(String str) {
        this.mTranslatedQuestion = str;
    }

    public String getAnswer() {
        if (this.mShowTranslatedFAQ && bh.m3343b(this.mTranslatedAnswer)) {
            return this.mTranslatedAnswer;
        }
        return this.mAnswer;
    }

    public void setAnswer(String str) {
        this.mAnswer = str;
    }

    public void setTranslatedAnswer(String str) {
        this.mTranslatedAnswer = str;
    }

    public void setShowTranslatedFAQ(boolean z) {
        this.mShowTranslatedFAQ = z;
    }

    @NonNull
    public String getLanguage() {
        return this.mLanguage;
    }
}
