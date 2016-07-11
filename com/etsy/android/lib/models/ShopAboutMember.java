package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.parceler.Parcel;

@Parcel
public class ShopAboutMember extends BaseModel {
    public static final String DEFAULT_IMAGE_AVATAR_PATH_190 = "/images/avatars/default_avatar_190x190.png";
    public static final String OWNER_ROLE = "owner";
    private static final long serialVersionUID = 5450494864709068115L;
    String mBio;
    EtsyId mId;
    @Nullable
    Image mImage;
    String mImageUrl190x190;
    String mImageUrl90x90;
    String mName;
    String mRole;

    public ShopAboutMember() {
        this.mName = StringUtils.EMPTY;
        this.mRole = StringUtils.EMPTY;
        this.mBio = StringUtils.EMPTY;
        this.mImageUrl90x90 = StringUtils.EMPTY;
        this.mImageUrl190x190 = StringUtils.EMPTY;
        this.mId = new EtsyId();
    }

    public EtsyId getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getRole() {
        return this.mRole;
    }

    public String[] getRoleList() {
        return this.mRole.split(",");
    }

    public String[] getCapitalizedRoleList() {
        return WordUtils.capitalize(this.mRole, new char[]{','}).split(",");
    }

    public String getBio() {
        return this.mBio;
    }

    public String getImageUrl90x90() {
        return this.mImageUrl90x90;
    }

    public String getImageUrl190x190() {
        return this.mImageUrl190x190;
    }

    @Nullable
    public Image getImage() {
        return this.mImage;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.SHOP_ABOUT_MEMBER_ID.equals(currentName)) {
                    this.mId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                } else if (ResponseConstants.NAME.equals(currentName)) {
                    this.mName = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.ROLE.equals(currentName)) {
                    this.mRole = BaseModel.parseString(jsonParser).trim();
                } else if (ResponseConstants.BIO.equals(currentName)) {
                    this.mBio = BaseModel.parseString(jsonParser).trim();
                } else if ("url_90x90".equals(currentName)) {
                    this.mImageUrl90x90 = BaseModel.parseStringURL(jsonParser);
                } else if ("url_190x190".equals(currentName)) {
                    this.mImageUrl190x190 = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.IMAGE.equals(currentName)) {
                    this.mImage = (Image) BaseModel.parseObject(jsonParser, Image.class);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.SHOP_ID, this.mId.getId());
        return hashMap;
    }
}
