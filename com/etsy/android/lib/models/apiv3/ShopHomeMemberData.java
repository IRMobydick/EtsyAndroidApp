package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class ShopHomeMemberData extends BaseModel {
    protected boolean mIsFavorer;
    protected boolean mIsSubscribedToVacationNotification;
    protected List<ListingMemberData> mListingsInfo;

    public ShopHomeMemberData() {
        this.mListingsInfo = new ArrayList(0);
    }

    public boolean isFavorer() {
        return this.mIsFavorer;
    }

    public boolean isSubscribedToVacationNotification() {
        return this.mIsSubscribedToVacationNotification;
    }

    public List<ListingMemberData> getListingsInfo() {
        return this.mListingsInfo;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -494126087:
                        if (currentName.equals(ResponseConstants.IS_SUBSCRIBED_TO_VACATION_NOTIFICATION)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 425827670:
                        if (currentName.equals(ResponseConstants.IS_FAVORER)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 1094969699:
                        if (currentName.equals(ResponseConstants.LISTINGS_MEMBER_INFO)) {
                            obj = 2;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mIsFavorer = jsonParser.getValueAsBoolean();
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mIsSubscribedToVacationNotification = jsonParser.getValueAsBoolean();
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mListingsInfo = BaseModel.parseArray(jsonParser, ListingMemberData.class);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }
}
