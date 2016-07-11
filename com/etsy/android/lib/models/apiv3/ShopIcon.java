package com.etsy.android.lib.models.apiv3;

import android.util.Pair;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.ab;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ShopIcon extends BaseModelImage {
    public static final String DEFAULT_SHOP_ICON_FULL = "https://www.etsy.com/images/avatars/default_shop_icon_fullxfull.png";
    protected static final Pair<Integer, String>[] IMG_SIZES_ARR;
    public static final Pair<Integer, String> IMG_SIZE_280;
    public static final Pair<Integer, String> IMG_SIZE_75;
    public static final Pair<Integer, String> IMG_SIZE_FULL;
    private static final long serialVersionUID = 8065727694601420688L;
    protected EtsyId mId;
    protected String mUrl280x280;

    static {
        IMG_SIZE_75 = new Pair(Integer.valueOf(75), "75x75");
        IMG_SIZE_280 = new Pair(Integer.valueOf(280), "280x280");
        IMG_SIZE_FULL = new Pair(Integer.valueOf(0), "fullxfull");
        IMG_SIZES_ARR = new Pair[]{IMG_SIZE_75, IMG_SIZE_280, IMG_SIZE_FULL};
    }

    public ShopIcon() {
        this.mUrl280x280 = StringUtils.EMPTY;
        this.mId = new EtsyId();
    }

    public ShopIcon(String str) {
        this.mUrl280x280 = StringUtils.EMPTY;
        this.mId = new EtsyId();
        this.mUrlFullxFull = str;
        if (str != null) {
            setUrls(this.mUrlFullxFull);
        }
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -859601281:
                        if (currentName.equals(ResponseConstants.IMAGE_ID)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 116079:
                        if (currentName.equals(ResponseConstants.URL)) {
                            obj = 1;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mUrlFullxFull = BaseModel.parseStringURL(jsonParser);
                        setUrls(this.mUrlFullxFull);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    private void setUrls(String str) {
        this.mUrl75x75 = str.replace((CharSequence) IMG_SIZE_FULL.second, (CharSequence) IMG_SIZE_75.second);
        this.mUrl280x280 = str.replace((CharSequence) IMG_SIZE_FULL.second, (CharSequence) IMG_SIZE_280.second);
    }

    public String getImageUrl() {
        return getImageUrlForPixelWidth(((Integer) IMG_SIZE_75.first).intValue());
    }

    public String getImageUrlForPixelWidth(int i) {
        int a = ab.m3172a(i);
        if (a == ((Integer) IMG_SIZE_FULL.first).intValue()) {
            return this.mUrlFullxFull;
        }
        if (a <= ((Integer) IMG_SIZE_75.first).intValue()) {
            return this.mUrl75x75;
        }
        if (a <= ((Integer) IMG_SIZE_280.first).intValue()) {
            return this.mUrl280x280;
        }
        return this.mUrlFullxFull;
    }

    public static String getDefaultShopUrlForPixelWidth(int i) {
        int a = ab.m3172a(i);
        if (a <= ((Integer) IMG_SIZE_75.first).intValue()) {
            return DEFAULT_SHOP_ICON_FULL.replace((CharSequence) IMG_SIZE_FULL.second, (CharSequence) IMG_SIZE_75.second);
        }
        if (a <= ((Integer) IMG_SIZE_280.first).intValue()) {
            return DEFAULT_SHOP_ICON_FULL.replace((CharSequence) IMG_SIZE_FULL.second, (CharSequence) IMG_SIZE_280.second);
        }
        return DEFAULT_SHOP_ICON_FULL;
    }

    protected Pair<Integer, String>[] getImageSizesArray() {
        return IMG_SIZES_ARR;
    }

    protected String getLargestDimension() {
        return (String) IMG_SIZE_FULL.second;
    }

    protected String getReplaceDimensionForWidth(int i) {
        return super.getReplaceDimensionForWidth(i);
    }

    public EtsyId getImageId() {
        return this.mId;
    }
}
