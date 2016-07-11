package com.etsy.android.lib.models.homescreen;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import java.util.Map;
import org.parceler.Parcel;

@Parcel
public class MessageCard extends BaseModel {
    public static final int IMAGE_TYPE_EMPTY_BASKET = 5;
    public static final int IMAGE_TYPE_EMPTY_SHELVES = 4;
    public static final int IMAGE_TYPE_FACE_CHAIR = 2;
    public static final int IMAGE_TYPE_GENERATE_RECOMMENDATIONS = 1;
    public static final int IMAGE_TYPE_SHOP_SHARES_PROMO = 3;
    public static final Map<String, Integer> typeLookup;
    protected String mDeepLinkUrl;
    protected String mDescription;
    protected int mImageType;
    protected String mLink;
    protected String mLinkTitle;
    protected String mTitle;
    protected boolean mTryAgain;

    /* renamed from: com.etsy.android.lib.models.homescreen.MessageCard.1 */
    final class C04861 extends HashMap<String, Integer> {
        C04861() {
            put("empty_recommmendations", Integer.valueOf(MessageCard.IMAGE_TYPE_GENERATE_RECOMMENDATIONS));
            put("face_chair", Integer.valueOf(MessageCard.IMAGE_TYPE_FACE_CHAIR));
            put("shop_shares_promo", Integer.valueOf(MessageCard.IMAGE_TYPE_SHOP_SHARES_PROMO));
            put("empty_shelves", Integer.valueOf(MessageCard.IMAGE_TYPE_EMPTY_SHELVES));
            put("empty_basket", Integer.valueOf(MessageCard.IMAGE_TYPE_EMPTY_BASKET));
        }
    }

    public MessageCard() {
        this.mTryAgain = false;
        this.mImageType = 0;
    }

    static {
        typeLookup = new C04861();
    }

    public int getViewType() {
        return super.getViewType();
    }

    public int getImage() {
        return this.mImageType;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getLink() {
        return this.mLink;
    }

    public String getLinkTitle() {
        return this.mLinkTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public void setLink(String str) {
        this.mLink = str;
    }

    public void setLinkTitle(String str) {
        this.mLinkTitle = str;
    }

    public String getDeepLinkUrl() {
        return this.mDeepLinkUrl;
    }

    public boolean isTryAgain() {
        return this.mTryAgain;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (FindsModule.FIELD_TITLE.equals(currentName)) {
                    this.mTitle = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.SUB_TITLE.equals(currentName)) {
                    this.mDescription = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.LINK_TITLE.equals(currentName)) {
                    this.mLinkTitle = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.LINK_PATH.equals(currentName)) {
                    this.mLink = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.DEEP_LINK_URL.equals(currentName)) {
                    this.mDeepLinkUrl = BaseModel.parseStringURL(jsonParser);
                } else if (ResponseConstants.TRY_AGAIN.equals(currentName)) {
                    this.mTryAgain = jsonParser.getBooleanValue();
                } else if (ResponseConstants.IMAGE.equals(currentName)) {
                    currentName = BaseModel.parseString(jsonParser);
                    if (typeLookup.containsKey(currentName)) {
                        this.mImageType = ((Integer) typeLookup.get(currentName)).intValue();
                    }
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
