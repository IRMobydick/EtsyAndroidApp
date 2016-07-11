package com.etsy.android.lib.models;

import android.util.Pair;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.bh;
import org.apache.commons.lang3.StringUtils;

public abstract class BaseModelImage extends BaseModel implements IFullImage {
    public static final int DEFAULT_LOADING_COLOR = -1842209;
    public static final String IMAGE_COLOR_ALPHA_HEX_PREFIX = "#59";
    protected static final Pair<Integer, String>[] IMG_SIZES_ARRAY;
    public static final Pair<Integer, String> IMG_SIZE_170;
    public static final Pair<Integer, String> IMG_SIZE_224;
    public static final Pair<Integer, String> IMG_SIZE_300;
    public static final Pair<Integer, String> IMG_SIZE_340;
    public static final Pair<Integer, String> IMG_SIZE_570;
    public static final Pair<Integer, String> IMG_SIZE_680;
    public static final Pair<Integer, String> IMG_SIZE_75;
    public static final Pair<Integer, String> IMG_SIZE_FULL;
    protected double PORTRAIT_HEIGHT_RATIO;
    protected String mUrl170x135;
    protected String mUrl224xN;
    protected String mUrl300x300;
    protected String mUrl340x270;
    protected String mUrl570xN;
    protected String mUrl680x540;
    protected String mUrl75x75;
    protected String mUrlFullxFull;

    public enum ImageOrientation {
        LANDSCAPE,
        PORTRAIT
    }

    protected abstract Pair<Integer, String>[] getImageSizesArray();

    public abstract String getImageUrl();

    public abstract String getImageUrlForPixelWidth(int i);

    protected abstract String getLargestDimension();

    public BaseModelImage() {
        this.mUrl75x75 = StringUtils.EMPTY;
        this.mUrl170x135 = StringUtils.EMPTY;
        this.mUrl224xN = StringUtils.EMPTY;
        this.mUrl340x270 = StringUtils.EMPTY;
        this.mUrl570xN = StringUtils.EMPTY;
        this.mUrlFullxFull = StringUtils.EMPTY;
        this.mUrl300x300 = StringUtils.EMPTY;
        this.mUrl680x540 = StringUtils.EMPTY;
        this.PORTRAIT_HEIGHT_RATIO = 1.3d;
    }

    static {
        IMG_SIZE_75 = new Pair(Integer.valueOf(75), "75x75");
        IMG_SIZE_170 = new Pair(Integer.valueOf(170), "170x135");
        IMG_SIZE_224 = new Pair(Integer.valueOf(224), "224xN");
        IMG_SIZE_300 = new Pair(Integer.valueOf(300), "300x300");
        IMG_SIZE_340 = new Pair(Integer.valueOf(340), "340x270");
        IMG_SIZE_570 = new Pair(Integer.valueOf(570), "570xN");
        IMG_SIZE_680 = new Pair(Integer.valueOf(680), "680x540");
        IMG_SIZE_FULL = new Pair(Integer.valueOf(0), "fullxfull");
        IMG_SIZES_ARRAY = new Pair[]{IMG_SIZE_75, IMG_SIZE_170, IMG_SIZE_224, IMG_SIZE_300, IMG_SIZE_340, IMG_SIZE_570, IMG_SIZE_680};
    }

    protected String getReplaceDimensionForWidth(int i) {
        String str;
        for (int i2 = 0; i2 < getImageSizesArray().length; i2++) {
            if (i <= ((Integer) getImageSizesArray()[i2].first).intValue()) {
                str = (String) getImageSizesArray()[i2].second;
                break;
            }
        }
        str = null;
        if (str == null) {
            return getLargestDimension();
        }
        return str;
    }

    protected String replaceImageUrlWithSize(String str) {
        if (bh.m3340a(this.mUrl75x75)) {
            return this.mUrl75x75.replace((CharSequence) IMG_SIZE_75.second, str);
        }
        if (bh.m3340a(this.mUrl170x135)) {
            return this.mUrl170x135.replace((CharSequence) IMG_SIZE_170.second, str);
        }
        if (bh.m3340a(this.mUrl570xN)) {
            return this.mUrl570xN.replace((CharSequence) IMG_SIZE_570.second, str);
        }
        if (bh.m3340a(this.mUrlFullxFull)) {
            return this.mUrlFullxFull.replace((CharSequence) IMG_SIZE_FULL.second, str);
        }
        return null;
    }

    public String getFullSizedImage() {
        return replaceImageUrlWithSize((String) IMG_SIZE_FULL.second);
    }

    public String getFullCardImageUrlForPixelWidth(int i) {
        return null;
    }

    public String get4to3ImageUrlForPixelWidth(int i) {
        return null;
    }

    public String getUrl75x75() {
        return this.mUrl75x75;
    }

    public String getUrl170x135() {
        return this.mUrl170x135;
    }

    public void setUrl170x135(String str) {
        this.mUrl170x135 = str;
    }

    public String getUrl340x270() {
        return this.mUrl340x270;
    }

    public String getUrl680x540() {
        return this.mUrl680x540;
    }

    public String getUrl570xN() {
        return this.mUrl570xN;
    }

    public String getUrl300x300() {
        return this.mUrl300x300;
    }

    public String getUrlFullxFull() {
        return this.mUrlFullxFull;
    }

    public int getFullHeight() {
        return -1;
    }

    public int getFullWidth() {
        return -1;
    }

    public int getImageColor() {
        return 0;
    }

    public EtsyId getImageId() {
        return null;
    }

    public ImageOrientation getImageOrientation() {
        return ImageOrientation.PORTRAIT;
    }
}
