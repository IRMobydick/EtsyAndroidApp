package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.models.apiv3.ShopCard;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class LocalStore extends BaseModel {
    private static final long serialVersionUID = 6590641840629636326L;
    protected String mAbout;
    protected UserAddress mAddress;
    protected String mBusinessName;
    protected EtsyId mBuyerId;
    protected List<String> mCategories;
    protected String mEmail;
    protected List<LocalStoreImage> mImages;
    protected String mPhone;
    protected List<ShopCard> mStockedShops;
    protected String mWebsite;

    public LocalStore() {
        this.mBusinessName = StringUtils.EMPTY;
        this.mEmail = StringUtils.EMPTY;
        this.mWebsite = StringUtils.EMPTY;
        this.mAbout = StringUtils.EMPTY;
        this.mPhone = StringUtils.EMPTY;
        this.mBuyerId = new EtsyId();
        this.mCategories = new ArrayList();
        this.mImages = new ArrayList();
        this.mStockedShops = new ArrayList();
    }

    public EtsyId getBuyerId() {
        return this.mBuyerId;
    }

    public String getBusinessName() {
        return this.mBusinessName;
    }

    public String getPhoneNumber() {
        return this.mPhone;
    }

    public List<String> getCategories() {
        return this.mCategories;
    }

    public List<ShopCard> getStockedShops() {
        return this.mStockedShops;
    }

    public List<LocalStoreImage> getImages() {
        return this.mImages;
    }

    @Nullable
    public UserAddress getAddress() {
        return this.mAddress;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -1857175932:
                        if (currentName.equals(ResponseConstants.BUYER_IMAGES)) {
                            obj = 9;
                            break;
                        }
                        break;
                    case -1239502562:
                        if (currentName.equals(ResponseConstants.WEBSITE_LINK)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case -1147692044:
                        if (currentName.equals(ResponseConstants.ADDRESS)) {
                            obj = 7;
                            break;
                        }
                        break;
                    case -978637017:
                        if (currentName.equals(ResponseConstants.BUYER_ID)) {
                            obj = null;
                            break;
                        }
                        break;
                    case -612351174:
                        if (currentName.equals(ResponseConstants.PHONE_NUMBER)) {
                            obj = 5;
                            break;
                        }
                        break;
                    case -269663821:
                        if (currentName.equals(ResponseConstants.STOCKED_SHOPS)) {
                            obj = 8;
                            break;
                        }
                        break;
                    case 92611469:
                        if (currentName.equals(ResponseConstants.ABOUT)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case 96619420:
                        if (currentName.equals(ResponseConstants.EMAIL)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case 629885866:
                        if (currentName.equals(ResponseConstants.BUSINESS_NAME)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 1296516636:
                        if (currentName.equals(FindsModule.FIELD_CATEGORIES)) {
                            obj = 6;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mBuyerId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mBusinessName = BaseModel.parseString(jsonParser);
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mEmail = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mWebsite = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                        this.mAbout = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        this.mPhone = BaseModel.parseString(jsonParser);
                        break;
                    case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                        this.mCategories = BaseModel.parseStringArray(jsonParser);
                        break;
                    case CommonStatusCodes.NETWORK_ERROR /*7*/:
                        this.mAddress = (UserAddress) BaseModel.parseObject(jsonParser, UserAddress.class);
                        break;
                    case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                        this.mStockedShops = BaseModel.parseArray(jsonParser, ShopCard.class);
                        break;
                    case CommonStatusCodes.SERVICE_INVALID /*9*/:
                        this.mImages = BaseModel.parseArray(jsonParser, LocalStoreImage.class);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }
}
