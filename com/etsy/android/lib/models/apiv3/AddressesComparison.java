package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class AddressesComparison extends BaseModel {
    protected List<String> mDifferences;
    protected UserAddressV3 mOriginalAddress;
    protected UserAddressV3 mSuggestedAddress;

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.ORIGINAL_ADDRESS.equals(currentName)) {
                    this.mOriginalAddress = (UserAddressV3) BaseModel.parseObject(jsonParser, UserAddressV3.class);
                } else if (ResponseConstants.SUGGESTED_ADDRESS.equals(currentName)) {
                    this.mSuggestedAddress = (UserAddressV3) BaseModel.parseObject(jsonParser, UserAddressV3.class);
                } else if (ResponseConstants.DIFFERENCES.equals(currentName)) {
                    this.mDifferences = BaseModel.parseStringArray(jsonParser);
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }

    public UserAddressV3 getOriginalAddress() {
        return this.mOriginalAddress;
    }

    public UserAddressV3 getSuggestedAddress() {
        return this.mSuggestedAddress;
    }

    public List<String> getDifferences() {
        return this.mDifferences;
    }
}
