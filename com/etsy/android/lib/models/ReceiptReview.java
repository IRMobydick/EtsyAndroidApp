package com.etsy.android.lib.models;

import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ReceiptReview extends BaseModel {
    private static final long serialVersionUID = 5105434964070945926L;
    protected Date mDate;
    protected EtsyId mReceiptId;
    protected List<Review> mReviews;
    protected String mUserAvatarUrl;
    protected EtsyId mUserId;
    protected String mUserLoginName;
    protected String mUserRealName;

    public ReceiptReview() {
        this.mUserAvatarUrl = StringUtils.EMPTY;
        this.mUserRealName = StringUtils.EMPTY;
        this.mUserLoginName = StringUtils.EMPTY;
        this.mReceiptId = new EtsyId();
        this.mUserId = new EtsyId();
        this.mReviews = new ArrayList(0);
    }

    public EtsyId getReceiptId() {
        return this.mReceiptId;
    }

    public Date getDate() {
        return this.mDate;
    }

    public List<Review> getReviews() {
        return this.mReviews;
    }

    public EtsyId getUserId() {
        return this.mUserId;
    }

    public String getUserAvatarUrl() {
        return this.mUserAvatarUrl;
    }

    public String getUserLoginName() {
        return this.mUserLoginName;
    }

    public String getUserDisplayName() {
        if (bh.m3343b(this.mUserRealName)) {
            return this.mUserRealName;
        }
        return this.mUserLoginName;
    }

    public static ReceiptReview cloneWithSingleReview(ReceiptReview receiptReview, Review review) {
        if (receiptReview == null || review == null) {
            return null;
        }
        ReceiptReview receiptReview2 = (ReceiptReview) SerializationUtils.clone(receiptReview);
        receiptReview2.mReviews = new ArrayList();
        receiptReview2.mReviews.add(SerializationUtils.clone(review));
        return receiptReview2;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.RECEIPT_ID.equals(currentName)) {
                    this.mReceiptId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.BUYER_USER_ID.equals(currentName)) {
                    this.mUserId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.BUYER_REAL_NAME.equals(currentName)) {
                    this.mUserRealName = BaseModel.parseString(jsonParser);
                } else if ("buyer_login_name".equals(currentName)) {
                    this.mUserLoginName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.BUYER_AVATAR_URL.equals(currentName)) {
                    this.mUserAvatarUrl = BaseModel.parseStringURL(jsonParser);
                } else if ("date".equals(currentName)) {
                    this.mDate = BaseModel.parseIntoDate(jsonParser);
                } else if (ResponseConstants.REVIEWS.equals(currentName)) {
                    this.mReviews = BaseModel.parseArray(jsonParser, Review.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
