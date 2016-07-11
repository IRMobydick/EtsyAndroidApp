package com.etsy.android.lib.models;

import android.util.Pair;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.apiv3.Image;
import com.fasterxml.jackson.core.JsonParser;
import org.parceler.Parcel;

@Parcel
public class BaseModelImageWrapper extends BaseModelImage {
    private static final long serialVersionUID = -2449543120721865761L;
    Image mImage;

    public BaseModelImageWrapper(Image image) {
        this.mImage = image;
    }

    public String getImageUrl() {
        return this.mImage.getUrl();
    }

    public String getImageUrlForPixelWidth(int i) {
        return ImageBatch.m1557a(i, 0, this.mImage);
    }

    protected Pair<Integer, String>[] getImageSizesArray() {
        return null;
    }

    protected String getLargestDimension() {
        return null;
    }

    public Image getWrappedImage() {
        return this.mImage;
    }

    public void parseData(JsonParser jsonParser) {
    }
}
