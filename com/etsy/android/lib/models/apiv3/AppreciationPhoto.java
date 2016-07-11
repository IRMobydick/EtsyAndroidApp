package com.etsy.android.lib.models.apiv3;

import android.support.annotation.Nullable;
import android.util.Pair;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShortenedUrl;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.AppreciationPhotoLike;
import com.etsy.android.lib.util.ab;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class AppreciationPhoto extends BaseModelImage implements AppreciationPhotoLike {
    private static final String ACTIVE = "active";
    protected static final Pair<Integer, String>[] IMG_SIZES_ARR;
    public static final Pair<Integer, String> IMG_SIZE_640;
    public static final Pair<Integer, String> IMG_SIZE_FULL;
    private static final long serialVersionUID = -4615967532699006555L;
    protected Date mCreateDate;
    protected EtsyId mImageId;
    protected boolean mIsSellerApproved;
    protected String mListingTitle;
    protected String mSellerAvatarUrl;
    protected String mShopName;
    protected ShortenedUrl mShortenedShareUrl;
    protected String mStatus;
    protected EtsyId mTransactionId;

    static {
        IMG_SIZE_640 = new Pair(Integer.valueOf(640), "640x640");
        IMG_SIZE_FULL = new Pair(Integer.valueOf(0), "fullxfull");
        IMG_SIZES_ARR = new Pair[]{IMG_SIZE_75, IMG_SIZE_300, IMG_SIZE_640, IMG_SIZE_FULL};
    }

    public AppreciationPhoto() {
        this.mShopName = StringUtils.EMPTY;
        this.mSellerAvatarUrl = StringUtils.EMPTY;
        this.mListingTitle = StringUtils.EMPTY;
        this.mImageId = new EtsyId();
        this.mTransactionId = new EtsyId();
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
                            obj = 4;
                            break;
                        }
                        break;
                    case -1131866526:
                        if (currentName.equals(ResponseConstants.IS_SELLER_APPROVED)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case -892481550:
                        if (currentName.equals(ResponseConstants.STATUS)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case -859601281:
                        if (currentName.equals(ResponseConstants.IMAGE_ID)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case -494058223:
                        if (currentName.equals(ResponseConstants.CREATE_DATE)) {
                            obj = 5;
                            break;
                        }
                        break;
                    case 1010584092:
                        if (currentName.equals(ResponseConstants.TRANSACTION_ID)) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mTransactionId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mImageId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mStatus = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mIsSellerApproved = jsonParser.getValueAsBoolean();
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.mUrlFullxFull = BaseModel.parseStringURL(jsonParser);
                        break;
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        this.mCreateDate = BaseModel.parseIntoDate(jsonParser);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    public boolean isActive() {
        return this.mStatus != null && this.mStatus.equals(ACTIVE);
    }

    public boolean isSellerApproved() {
        return this.mIsSellerApproved;
    }

    public String getShareImageUrl() {
        return getImageUrl();
    }

    public String getImageUrl() {
        return replaceImageUrlWithSize((String) IMG_SIZE_640.second);
    }

    public EtsyId getTransactionId() {
        return this.mTransactionId;
    }

    public void setIsSellerApproved(boolean z) {
        this.mIsSellerApproved = z;
    }

    public String getImageUrlForPixelWidth(int i) {
        int a = ab.m3172a(i);
        for (Pair pair : getImageSizesArray()) {
            if (a <= ((Integer) pair.first).intValue()) {
                return replaceImageUrlWithSize((String) pair.second);
            }
        }
        return this.mUrlFullxFull;
    }

    protected Pair<Integer, String>[] getImageSizesArray() {
        return IMG_SIZES_ARR;
    }

    protected String getLargestDimension() {
        return (String) IMG_SIZE_FULL.second;
    }

    public static String buildShareUrl(EtsyId etsyId) {
        return EtsyConfig.m837a().m869d().m883b(EtsyConfigKeys.cv) + "/transaction/" + etsyId.getId() + "/buyer-photo";
    }

    public ShortenedUrl getShortenedShareUrl() {
        return this.mShortenedShareUrl;
    }

    public void setShortenedShareUrl(ShortenedUrl shortenedUrl) {
        this.mShortenedShareUrl = shortenedUrl;
    }

    public String getShopName() {
        return this.mShopName;
    }

    public void setShopName(String str) {
        this.mShopName = str;
    }

    public String getSellerAvatarUrl() {
        return this.mSellerAvatarUrl;
    }

    public void setSellerAvatarUrl(String str) {
        this.mSellerAvatarUrl = str;
    }

    public String getListingTitle() {
        return this.mListingTitle;
    }

    public void setListingTitle(String str) {
        this.mListingTitle = str;
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.TRANSACTION_ID, this.mTransactionId.getId());
        hashMap.put(AnalyticsLogAttribute.IMAGE_ID, this.mImageId.getId());
        return hashMap;
    }
}
