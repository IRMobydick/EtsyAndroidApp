package com.etsy.android.lib.models.shopshare;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.homescreen.LandingPageInfo;
import com.etsy.android.lib.models.homescreen.LandingPageInfoHolder;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class ShopShareCard extends BaseModel implements LandingPageInfoHolder {
    private static final long serialVersionUID = 5241891660245692250L;
    protected EtsyId mActivityId;
    protected LandingPageInfo mLandingPage;
    protected EtsyId mOwnerId;
    protected int mOwnerType;
    protected ShareItem mShareItem;
    protected String mShopAvatarUrl;
    protected String mShopDisplayName;
    protected int mViewType;

    public ShopShareCard() {
        this.mShopDisplayName = StringUtils.EMPTY;
        this.mShopAvatarUrl = StringUtils.EMPTY;
        this.mViewType = 17;
        this.mActivityId = new EtsyId();
    }

    public ShareItem getShareItem() {
        return this.mShareItem;
    }

    public EtsyId getActivityId() {
        return this.mActivityId;
    }

    public EtsyId getOwnerId() {
        return this.mOwnerId;
    }

    public int getOwnerType() {
        return this.mOwnerType;
    }

    public String getShopDisplayName() {
        return this.mShopDisplayName;
    }

    public String getShopAvatarUrl() {
        return this.mShopAvatarUrl;
    }

    public void setActivityId(EtsyId etsyId) {
        this.mActivityId = etsyId;
    }

    public void setOwnerId(EtsyId etsyId) {
        this.mOwnerId = etsyId;
    }

    public void setOwnerType(int i) {
        this.mOwnerType = i;
    }

    public void setShareItem(ShareItem shareItem) {
        this.mShareItem = shareItem;
    }

    public void setShopDisplayName(String str) {
        this.mShopDisplayName = str;
    }

    public void setShopAvatarUrl(String str) {
        this.mShopAvatarUrl = str;
    }

    public boolean isObjectOwnedByUser(EtsyId etsyId) {
        return etsyId.equals(getOwnerId());
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (!(jsonParser.getCurrentToken() == JsonToken.VALUE_NULL || currentName == null)) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -1986014575:
                        if (currentName.equals(ResponseConstants.SHOP_DISPLAY_NAME)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case -1023368385:
                        if (currentName.equals(ResponseConstants.OBJECT)) {
                            obj = null;
                            break;
                        }
                        break;
                    case -917278645:
                        if (currentName.equals(ResponseConstants.ACTIVITY_ID)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case 109400031:
                        if (currentName.equals(ResponseConstants.SHARE)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 286164594:
                        if (currentName.equals(ResponseConstants.SHOP_AVATAR_URL)) {
                            obj = 4;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        if (jsonParser.getCurrentToken() != JsonToken.START_ARRAY) {
                            break;
                        }
                        jsonParser.nextToken();
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mShareItem = (ShareItem) BaseModel.parseObject(jsonParser, ShareItem.class);
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mActivityId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mShopDisplayName = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.mShopAvatarUrl = BaseModel.parseStringURL(jsonParser);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    public int getViewType() {
        return this.mViewType;
    }

    public void setViewType(int i) {
        this.mViewType = i;
    }

    public boolean hasLandingPage() {
        return this.mLandingPage != null;
    }

    public LandingPageInfo getLandingPage() {
        return this.mLandingPage;
    }

    public void setLandingPage(LandingPageInfo landingPageInfo) {
        this.mLandingPage = landingPageInfo;
    }
}
