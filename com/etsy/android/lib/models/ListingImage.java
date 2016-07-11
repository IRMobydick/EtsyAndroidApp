package com.etsy.android.lib.models;

import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Pair;
import com.etsy.android.lib.models.BaseModelImage.ImageOrientation;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.ab;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.parceler.Parcel;

@Parcel
public class ListingImage extends BaseModelImage {
    protected static final long serialVersionUID = -540648797371344942L;
    protected int mBlue;
    protected int mFullHeight;
    protected int mFullWidth;
    protected int mGreen;
    protected int mHexColor;
    protected int mHue;
    protected EtsyId mImageId;
    protected int mPostCalculatedColor;
    protected int mRank;
    protected int mRed;
    protected int mSaturation;

    public ListingImage() {
        this.mHexColor = ViewCompat.MEASURED_STATE_MASK;
        this.mImageId = new EtsyId();
    }

    public ListingImage(String str) {
        this();
        this.mUrl170x135 = str;
    }

    public ListingImage(String str, int i) {
        this();
        this.mUrl570xN = str;
        this.mPostCalculatedColor = i;
    }

    public ListingImage(String str, int i, String str2) {
        this(str, i);
        this.mImageId.setId(str2);
    }

    public static ListingImage get75x75(String str) {
        ListingImage listingImage = new ListingImage();
        listingImage.setUrl75x75(str);
        return listingImage;
    }

    public String getImageUrl() {
        return getUrl570xN();
    }

    public String getImageUrlForPixelWidth(int i) {
        return replaceImageUrlWithSize(getReplaceDimensionForWidth(ab.m3172a(i)));
    }

    protected Pair<Integer, String>[] getImageSizesArray() {
        return IMG_SIZES_ARRAY;
    }

    protected String getLargestDimension() {
        return (String) IMG_SIZE_FULL.second;
    }

    public void setUrl75x75(String str) {
        this.mUrl75x75 = str;
    }

    public EtsyId getImageId() {
        return this.mImageId;
    }

    public int getFullHeight() {
        return this.mFullHeight;
    }

    public int getFullWidth() {
        return this.mFullWidth;
    }

    public int getRank() {
        return this.mRank;
    }

    public ImageOrientation getImageOrientation() {
        return (this.mFullWidth <= 0 || this.mFullHeight <= 0 || ((double) this.mFullHeight) < ((double) this.mFullWidth) * this.PORTRAIT_HEIGHT_RATIO) ? ImageOrientation.LANDSCAPE : ImageOrientation.PORTRAIT;
    }

    public int getImageColor() {
        if (this.mPostCalculatedColor > 0) {
            return this.mPostCalculatedColor;
        }
        if (this.mHexColor != ViewCompat.MEASURED_STATE_MASK) {
            return this.mHexColor;
        }
        if (hasImageColor()) {
            return ab.m3173a(this.mRed, this.mGreen, this.mBlue);
        }
        if (this.mHue <= 0 || this.mSaturation <= 0) {
            return BaseModelImage.DEFAULT_LOADING_COLOR;
        }
        return Color.HSVToColor(16, new float[]{(float) this.mHue, (float) this.mSaturation, 0.5f});
    }

    public boolean hasImageColor() {
        return (this.mRed > 0 && this.mGreen > 0 && this.mBlue > 0) || this.mPostCalculatedColor != 0;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.LISTING_IMAGE_ID.equals(currentName) || ResponseConstants.IMAGE_ID.equals(currentName)) {
                    this.mImageId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.RED.equals(currentName)) {
                    this.mRed = jsonParser.getValueAsInt();
                } else if (ResponseConstants.GREEN.equals(currentName)) {
                    this.mGreen = jsonParser.getValueAsInt();
                } else if (ResponseConstants.BLUE.equals(currentName)) {
                    this.mBlue = jsonParser.getValueAsInt();
                } else if (ResponseConstants.HUE.equals(currentName)) {
                    this.mHue = jsonParser.getValueAsInt();
                } else if (ResponseConstants.SATURATION.equals(currentName)) {
                    this.mSaturation = jsonParser.getValueAsInt();
                } else if (ResponseConstants.RANK.equals(currentName)) {
                    this.mRank = jsonParser.getValueAsInt();
                } else if (ResponseConstants.URL_75x75.equals(currentName)) {
                    this.mUrl75x75 = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.URL_170x135.equals(currentName)) {
                    this.mUrl170x135 = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.URL_240xN.equals(currentName)) {
                    this.mUrl224xN = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.URL_340x270.equals(currentName)) {
                    this.mUrl340x270 = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.URL_300x300.equals(currentName)) {
                    this.mUrl300x300 = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.URL_570xN.equals(currentName)) {
                    this.mUrl570xN = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.URL_680X540.equals(currentName)) {
                    this.mUrl680x540 = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.URL_FULLxFULL.equals(currentName)) {
                    this.mUrlFullxFull = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.FULL_HEIGHT.equals(currentName)) {
                    this.mFullHeight = jsonParser.getValueAsInt();
                } else if (ResponseConstants.FULL_WIDTH.equals(currentName)) {
                    this.mFullWidth = jsonParser.getValueAsInt();
                } else if (ResponseConstants.HEIGHT.equals(currentName)) {
                    this.mFullHeight = jsonParser.getValueAsInt();
                } else if (ResponseConstants.WIDTH.equals(currentName)) {
                    this.mFullWidth = jsonParser.getValueAsInt();
                } else if (ResponseConstants.COLOR.equals(currentName)) {
                    this.mHexColor = ab.m3174a(BaseModel.parseString(jsonParser));
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public String get4to3ImageUrlForPixelWidth(int i) {
        Pair pair;
        int a = ab.m3172a(i);
        Pair pair2 = IMG_SIZE_340;
        if (a <= 0 || a > ((Integer) IMG_SIZE_170.first).intValue()) {
            if (a <= ((Integer) IMG_SIZE_170.first).intValue() || a > ((Integer) IMG_SIZE_340.first).intValue()) {
                if (a <= ((Integer) IMG_SIZE_340.first).intValue() || a > ((Integer) IMG_SIZE_680.first).intValue()) {
                    pair = pair2;
                } else if (!TextUtils.isEmpty(this.mUrl680x540)) {
                    return this.mUrl680x540;
                } else {
                    pair = IMG_SIZE_680;
                }
            } else if (!TextUtils.isEmpty(this.mUrl340x270)) {
                return this.mUrl340x270;
            } else {
                pair = IMG_SIZE_340;
            }
        } else if (!TextUtils.isEmpty(this.mUrl170x135)) {
            return this.mUrl170x135;
        } else {
            pair = IMG_SIZE_170;
        }
        return replaceImageUrlWithSize((String) pair.second);
    }

    public String getFullCardImageUrlForPixelWidth(int i) {
        Pair pair;
        int a = ab.m3172a(i);
        Pair pair2 = IMG_SIZE_570;
        if (a <= ((Integer) IMG_SIZE_224.first).intValue()) {
            if (!TextUtils.isEmpty(this.mUrl224xN)) {
                return this.mUrl224xN;
            }
            pair = IMG_SIZE_224;
        } else if (a > ((Integer) IMG_SIZE_570.first).intValue()) {
            pair = pair2;
        } else if (!TextUtils.isEmpty(this.mUrl570xN)) {
            return this.mUrl570xN;
        } else {
            pair = IMG_SIZE_570;
        }
        return replaceImageUrlWithSize((String) pair.second);
    }
}
