package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.gcm.Task;
import org.parceler.Parcel;

@Parcel
public class ListingMemberData extends BaseModel {
    protected boolean mHasCollections;
    protected boolean mIsFavorite;
    protected EtsyId mListingId;

    public ListingMemberData(EtsyId etsyId, boolean z, boolean z2) {
        this.mListingId = new EtsyId();
        this.mIsFavorite = z;
        this.mHasCollections = z2;
        this.mListingId = etsyId;
    }

    public ListingMemberData() {
        this.mListingId = new EtsyId();
    }

    public EtsyId getListingId() {
        return this.mListingId;
    }

    public boolean hasCollections() {
        return this.mHasCollections;
    }

    public boolean isFavorite() {
        return this.mIsFavorite;
    }

    public void setHasCollections(boolean z) {
        this.mHasCollections = z;
    }

    public void setIsFavorite(boolean z) {
        this.mIsFavorite = z;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -2072707216:
                        if (currentName.equals(ResponseConstants.HAS_COLLECTIONS)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 315759889:
                        if (currentName.equals(ResponseConstants.IS_FAVORITE)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 988969142:
                        if (currentName.equals(ResponseConstants.LISTING_ID)) {
                            obj = 2;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mIsFavorite = jsonParser.getValueAsBoolean();
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mHasCollections = jsonParser.getValueAsBoolean();
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        this.mListingId.setId(BaseModel.parseStringIdOrNumericValue(jsonParser));
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }
}
