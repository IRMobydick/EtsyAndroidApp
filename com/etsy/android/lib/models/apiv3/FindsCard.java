package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.requests.EtsyRequest;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class FindsCard extends BaseModel {
    protected EtsyId mFindsPageId;
    protected List<ListingImage> mImages;
    protected ListingImage mImg;
    protected boolean mIsPublic;
    protected String mLanguage;
    protected String mSlug;
    protected String mTitle;
    protected String mUrl;
    protected int mViewType;

    public FindsCard() {
        this.mViewType = 20;
        this.mFindsPageId = new EtsyId();
        this.mImages = new ArrayList(0);
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL || !parseField(jsonParser, currentName)) {
                jsonParser.skipChildren();
            }
        }
    }

    protected boolean parseField(JsonParser jsonParser, String str) {
        if (ResponseConstants.FINDS_PAGE_ID.equals(str)) {
            this.mFindsPageId.setId(BaseModel.parseString(jsonParser));
        } else if (ResponseConstants.SLUG.equals(str)) {
            this.mSlug = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.URL.equals(str)) {
            this.mUrl = BaseModel.parseString(jsonParser);
        } else if (FindsModule.FIELD_TITLE.equals(str)) {
            this.mTitle = BaseModel.parseString(jsonParser);
        } else if (EtsyRequest.PARAM_LANGUAGE.equals(str)) {
            this.mLanguage = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.IMG.equals(str)) {
            this.mImg = (ListingImage) BaseModel.parseObject(jsonParser, ListingImage.class);
        } else if (FindsModule.FIELD_IMAGES.equals(str)) {
            Collection parseArray = BaseModel.parseArray(jsonParser, ListingImage.class);
            if (parseArray != null) {
                this.mImages.addAll(parseArray);
            }
        } else if (!ResponseConstants.IS_PUBLIC.equals(str)) {
            return false;
        } else {
            this.mIsPublic = jsonParser.getValueAsBoolean();
        }
        return true;
    }

    public EtsyId getFindsPageId() {
        return this.mFindsPageId;
    }

    public String getSlug() {
        return this.mSlug;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getLanguage() {
        return this.mLanguage;
    }

    public ListingImage getImg() {
        return this.mImg;
    }

    public List<ListingImage> getImages() {
        return this.mImages;
    }

    public boolean isPublic() {
        return this.mIsPublic;
    }

    public void setViewType(int i) {
        this.mViewType = i;
    }

    public int getViewType() {
        return this.mViewType;
    }
}
