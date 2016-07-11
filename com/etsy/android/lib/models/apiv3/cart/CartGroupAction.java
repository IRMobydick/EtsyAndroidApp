package com.etsy.android.lib.models.apiv3.cart;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.models.BaseFieldModel;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ActivityFeedSubject;
import com.etsy.android.lib.models.finds.FindsModule;
import com.fasterxml.jackson.core.JsonParser;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class CartGroupAction extends BaseFieldModel {
    public static final String APPLY_COUPON = "apply_coupon";
    public static final String DISMISS = "dismiss";
    public static final String MESSAGE_TO_SELLER = "message_to_seller";
    public static final String REMOVE_CART_LISTING = "remove";
    public static final String SAVE_CART_LISTING = "save_for_later";
    public static final String SHIPPING_OPTION = "shipping_option";
    public static final String SHOULD_USE_GIFTCARD = "should_use_giftcard";
    public static final String UPDATE_OFFERING = "update_offering";
    private static final long serialVersionUID = -6151481434212641215L;
    protected String mDisplayName;
    protected HashMap<String, String> mParams;
    protected String mPath;
    protected boolean mRefreshNeeded;
    protected String mRequestMethod;
    protected String mType;

    public CartGroupAction() {
        this.mType = StringUtils.EMPTY;
        this.mRequestMethod = StringUtils.EMPTY;
        this.mPath = StringUtils.EMPTY;
        this.mRefreshNeeded = true;
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (ResponseConstants.METHOD.equals(str)) {
            this.mRequestMethod = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.PATH.equals(str)) {
            this.mPath = BaseModel.parseStringURL(jsonParser);
        } else if (FindsModule.FIELD_TYPE.equals(str)) {
            this.mType = BaseModel.parseString(jsonParser);
        } else if (ResponseConstants.REFRESH_NEEDED.equals(str)) {
            this.mRefreshNeeded = jsonParser.getBooleanValue();
        } else if (!ActivityFeedSubject.DISPLAY_NAME.equals(str)) {
            return false;
        } else {
            this.mDisplayName = BaseModel.parseString(jsonParser);
        }
        return true;
    }

    public String getType() {
        return this.mType;
    }

    public String getPath() {
        return this.mPath;
    }

    public String getRequestMethod() {
        return this.mRequestMethod;
    }

    public void setParams(HashMap<String, String> hashMap) {
        this.mParams = hashMap;
    }

    public HashMap<String, String> getParams() {
        return this.mParams;
    }

    public boolean getRefreshNeeded() {
        return this.mRefreshNeeded;
    }

    @Nullable
    public String getDisplayName() {
        return this.mDisplayName;
    }
}
