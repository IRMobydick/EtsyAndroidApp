package com.etsy.android.lib.models.apiv3;

import android.support.annotation.NonNull;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.IFullImage;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class Image extends BaseFieldModel implements IFullImage {
    public static final Comparator<Source> SOURCE_COMPARATOR;
    protected String mKey;
    protected List<Source> mSources;
    protected String mUrl;

    /* renamed from: com.etsy.android.lib.models.apiv3.Image.1 */
    final class C04821 implements Comparator<Source> {
        C04821() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m2369a((Source) obj, (Source) obj2);
        }

        public int m2369a(Source source, Source source2) {
            return source.getWidth() == source2.getWidth() ? source.getHeight() - source2.getHeight() : source.getWidth() - source2.getWidth();
        }
    }

    @Parcel
    public class Source extends BaseModel {
        protected int height;
        protected String mUrl;
        protected int width;

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }

        public String getUrl() {
            return this.mUrl;
        }

        public void parseData(JsonParser jsonParser) {
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String currentName = jsonParser.getCurrentName();
                jsonParser.nextToken();
                if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                    Object obj = -1;
                    switch (currentName.hashCode()) {
                        case -1221029593:
                            if (currentName.equals(ResponseConstants.HEIGHT)) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 116079:
                            if (currentName.equals(ResponseConstants.URL)) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 113126854:
                            if (currentName.equals(ResponseConstants.WIDTH)) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case Task.NETWORK_STATE_CONNECTED /*0*/:
                            this.width = jsonParser.getValueAsInt();
                            break;
                        case Task.NETWORK_STATE_UNMETERED /*1*/:
                            this.height = jsonParser.getValueAsInt();
                            break;
                        case Task.NETWORK_STATE_ANY /*2*/:
                            this.mUrl = BaseModel.parseString(jsonParser);
                            break;
                        default:
                            jsonParser.skipChildren();
                            break;
                    }
                }
            }
        }
    }

    public Image() {
        this.mKey = StringUtils.EMPTY;
        this.mUrl = StringUtils.EMPTY;
        this.mSources = new ArrayList();
    }

    public Image(String str, String str2, List<Source> list) {
        this.mKey = StringUtils.EMPTY;
        this.mUrl = StringUtils.EMPTY;
        this.mSources = new ArrayList();
        this.mKey = str;
        this.mUrl = str2;
        this.mSources = list;
    }

    static {
        SOURCE_COMPARATOR = new C04821();
    }

    public String getKey() {
        return this.mKey;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public List<Source> getSources() {
        return this.mSources;
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        boolean z = true;
        switch (str.hashCode()) {
            case -2021876808:
                if (str.equals(ResponseConstants.SOURCES)) {
                    z = true;
                    break;
                }
                break;
            case 106079:
                if (str.equals(ResponseConstants.KEY)) {
                    z = false;
                    break;
                }
                break;
            case 116079:
                if (str.equals(ResponseConstants.URL)) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                this.mKey = BaseModel.parseString(jsonParser);
                return true;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                this.mUrl = BaseModel.parseString(jsonParser);
                return true;
            case Task.NETWORK_STATE_ANY /*2*/:
                this.mSources = BaseModel.parseArray(jsonParser, Source.class);
                Collections.sort(this.mSources, SOURCE_COMPARATOR);
                return true;
            default:
                return false;
        }
    }

    public String get4to3ImageUrlForPixelWidth(int i) {
        return ImageBatch.m1557a(i, 0, this);
    }

    public int getImageColor() {
        return BaseModelImage.DEFAULT_LOADING_COLOR;
    }

    public int getFullHeight() {
        if (this.mSources.isEmpty()) {
            return 0;
        }
        return ((Source) this.mSources.get(this.mSources.size() - 1)).height;
    }

    public int getFullWidth() {
        if (this.mSources.isEmpty()) {
            return 0;
        }
        return ((Source) this.mSources.get(this.mSources.size() - 1)).width;
    }

    public String getFullCardImageUrlForPixelWidth(int i) {
        return ImageBatch.m1557a(i, 0, this);
    }
}
