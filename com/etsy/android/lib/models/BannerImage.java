package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.util.ab;
import com.foresee.mobileReplay.recorder.ScreenRecorder;
import java.security.InvalidParameterException;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class BannerImage extends BaseFieldModel implements IFullImage {
    private final int OVERSIZE_THRESHOLD_PX;
    private int mFullHeight;
    private int mFullWidth;
    protected SortedMap<Integer, String> mImageUrls;
    protected SortedMap<Integer, String> mImageUrlsLandscape;
    private boolean mIsLandscape;

    public BannerImage() {
        this.mFullWidth = 1;
        this.mFullHeight = 1;
        this.mIsLandscape = false;
        this.OVERSIZE_THRESHOLD_PX = ScreenRecorder.DEFAULT_CAPTURE_FREQ;
        this.mImageUrls = new TreeMap();
        this.mImageUrlsLandscape = new TreeMap();
    }

    @Nullable
    public String getImageUrl() {
        return getImageUrlForPixelWidth(0, false);
    }

    @Nullable
    public String getImageUrlForPixelWidth(int i, boolean z) {
        int keyForWidth = getKeyForWidth(ab.m3172a(i), z);
        SortedMap imageUrls = getImageUrls(z);
        if (imageUrls.containsKey(Integer.valueOf(keyForWidth))) {
            return (String) imageUrls.get(Integer.valueOf(keyForWidth));
        }
        return null;
    }

    private int getKeyForWidth(int i, boolean z) {
        SortedMap imageUrls = getImageUrls(z);
        if (imageUrls.size() <= 0) {
            return i;
        }
        int intValue = ((Integer) imageUrls.firstKey()).intValue();
        int i2 = intValue;
        for (Integer intValue2 : imageUrls.keySet()) {
            intValue = intValue2.intValue();
            if (intValue <= i) {
                i2 = intValue;
            } else if (intValue - i <= ScreenRecorder.DEFAULT_CAPTURE_FREQ) {
                return intValue;
            } else {
                return i2;
            }
        }
        return i2;
    }

    protected SortedMap<Integer, String> getImageUrls(boolean z) {
        if (z) {
            return this.mImageUrlsLandscape;
        }
        return this.mImageUrls;
    }

    public int getImageColor() {
        return 0;
    }

    public int getFullHeight() {
        return this.mFullHeight;
    }

    public int getFullWidth() {
        return this.mFullWidth;
    }

    public String get4to3ImageUrlForPixelWidth(int i) {
        return getImageUrl();
    }

    public String getFullCardImageUrlForPixelWidth(int i) {
        return getImageUrlForPixelWidth(i, this.mIsLandscape);
    }

    public BannerImage withDisplayWidth(int i, boolean z) {
        int keyForWidth = getKeyForWidth(ab.m3172a(i), z);
        SortedMap imageUrls = getImageUrls(z);
        if (imageUrls.containsKey(Integer.valueOf(keyForWidth))) {
            String str = (String) imageUrls.get(Integer.valueOf(keyForWidth));
            try {
                this.mFullHeight = parseYValueFromImageURL(str);
                this.mFullWidth = keyForWidth;
                this.mIsLandscape = z;
            } catch (InvalidParameterException e) {
                EtsyDebug.m1919e(EtsyDebug.m1891a(BannerImage.class), "InvalidParameter used to fetch image dimensions in withDisplayWidth: " + str);
            }
        }
        return this;
    }

    private int parseYValueFromImageURL(String str) {
        int indexOf = str.indexOf(120);
        if (indexOf > -1) {
            int indexOf2 = str.indexOf(46, indexOf);
            if (indexOf2 > -1) {
                try {
                    return Integer.parseInt(str.substring(indexOf + 1, indexOf2));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    throw new InvalidParameterException();
                }
            }
        }
        throw new InvalidParameterException();
    }
}
