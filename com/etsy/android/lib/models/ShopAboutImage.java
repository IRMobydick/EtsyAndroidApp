package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import android.util.Pair;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.apiv3.Image.Source;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.ab;
import com.etsy.android.lib.util.bh;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ShopAboutImage extends BaseModelImage {
    public static final Pair<Integer, String>[] IMG_SIZES_ARRAY;
    public static final Pair<Integer, String> IMG_SIZE_178;
    public static final Pair<Integer, String> IMG_SIZE_545;
    public static final Pair<Integer, String> IMG_SIZE_760;
    private static final long serialVersionUID = 5450494864709068116L;
    protected String mCaption;
    protected Image mImage;
    protected EtsyId mImageId;
    protected String mImageUrl178x178;
    protected String mImageUrl545xN;
    protected String mImageUrl760xN;
    protected String mKey;
    protected int mRank;
    protected List<Source> mSources;
    protected String mUrl;

    static {
        IMG_SIZE_178 = new Pair(Integer.valueOf(178), "178x178");
        IMG_SIZE_545 = new Pair(Integer.valueOf(545), "545xN");
        IMG_SIZE_760 = new Pair(Integer.valueOf(760), "760xN");
        IMG_SIZES_ARRAY = new Pair[]{IMG_SIZE_178, IMG_SIZE_545, IMG_SIZE_760};
    }

    public ShopAboutImage() {
        this.mImageUrl178x178 = StringUtils.EMPTY;
        this.mImageUrl545xN = StringUtils.EMPTY;
        this.mImageUrl760xN = StringUtils.EMPTY;
        this.mCaption = StringUtils.EMPTY;
        this.mSources = new ArrayList(0);
        this.mImageId = new EtsyId();
    }

    public String getImageUrl() {
        return getImageUrl545xN();
    }

    public String getImageUrlForPixelWidth(int i) {
        if (this.mImage != null) {
            return ImageBatch.m1557a(i, 0, this.mImage);
        }
        int a = ab.m3172a(i);
        if (bh.m3340a(this.mImageUrl178x178)) {
            return this.mImageUrl178x178.replace((CharSequence) IMG_SIZE_178.second, getReplaceDimensionForWidth(a));
        }
        if (bh.m3340a(this.mImageUrl545xN)) {
            return this.mImageUrl545xN.replace((CharSequence) IMG_SIZE_545.second, getReplaceDimensionForWidth(a));
        }
        return ImageBatch.m1557a(i, 0, getImage());
    }

    protected Pair<Integer, String>[] getImageSizesArray() {
        return IMG_SIZES_ARRAY;
    }

    protected String getLargestDimension() {
        return (String) IMG_SIZE_545.second;
    }

    public String getImageUrl178x178() {
        return this.mImageUrl178x178;
    }

    public String getImageUrl545xN() {
        return this.mImageUrl545xN;
    }

    public String getmImageUrl760xN() {
        return this.mImageUrl760xN;
    }

    public String getCaption() {
        return this.mCaption;
    }

    public int getRank() {
        return this.mRank;
    }

    public EtsyId getImageId() {
        return this.mImageId;
    }

    @Nullable
    public Image getImage() {
        if (this.mImage != null) {
            return this.mImage;
        }
        if (this.mKey == null || this.mSources == null || this.mUrl == null) {
            return null;
        }
        return new Image(this.mKey, this.mUrl, this.mSources);
    }

    public int getImageColor() {
        return BaseModelImage.DEFAULT_LOADING_COLOR;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -2021876808:
                        if (currentName.equals(ResponseConstants.SOURCES)) {
                            obj = 9;
                            break;
                        }
                        break;
                    case -859601281:
                        if (currentName.equals(ResponseConstants.IMAGE_ID)) {
                            obj = 5;
                            break;
                        }
                        break;
                    case -70422532:
                        if (currentName.equals(ResponseConstants.URL_545xN)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case -68520713:
                        if (currentName.equals(ResponseConstants.URL_760XN)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case 106079:
                        if (currentName.equals(ResponseConstants.KEY)) {
                            obj = 7;
                            break;
                        }
                        break;
                    case 116079:
                        if (currentName.equals(ResponseConstants.URL)) {
                            obj = 8;
                            break;
                        }
                        break;
                    case 3492908:
                        if (currentName.equals(ResponseConstants.RANK)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case 100313435:
                        if (currentName.equals(ResponseConstants.IMAGE)) {
                            obj = 6;
                            break;
                        }
                        break;
                    case 552573414:
                        if (currentName.equals(ResponseConstants.CAPTION)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case 1877007964:
                        if (currentName.equals(ResponseConstants.URL_178x178)) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mImageUrl178x178 = BaseModel.parseStringURL(jsonParser);
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mImageUrl545xN = BaseModel.parseStringURL(jsonParser);
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mImageUrl760xN = BaseModel.parseStringURL(jsonParser);
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mCaption = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.mRank = jsonParser.getValueAsInt();
                        break;
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        this.mImageId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                        break;
                    case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                        this.mImage = (Image) BaseModel.parseObject(jsonParser, Image.class);
                        break;
                    case CommonStatusCodes.NETWORK_ERROR /*7*/:
                        this.mKey = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                        this.mUrl = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SERVICE_INVALID /*9*/:
                        this.mSources = BaseModel.parseArray(jsonParser, Source.class);
                        Collections.sort(this.mSources, Image.SOURCE_COMPARATOR);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    public String getFullCardImageUrlForPixelWidth(int i) {
        if (this.mImage != null) {
            return ImageBatch.m1557a(i, 0, this.mImage);
        }
        return getImageUrlForPixelWidth(i);
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.IMAGE_ID, this.mImageId.getId());
        return hashMap;
    }
}
