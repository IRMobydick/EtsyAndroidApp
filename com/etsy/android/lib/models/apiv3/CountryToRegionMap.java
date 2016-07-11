package com.etsy.android.lib.models.apiv3;

import android.util.SparseArray;
import com.etsy.android.lib.models.BaseModel;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.parceler.Parcel;

@Parcel
public class CountryToRegionMap extends BaseModel {
    private static final long serialVersionUID = 726257564519043197L;
    protected SparseArray<String> countryToRegionMap;

    public CountryToRegionMap() {
        this.countryToRegionMap = new SparseArray();
    }

    public void parseData(JsonParser jsonParser) {
        Integer num = null;
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                try {
                    num = Integer.valueOf(jsonParser.getCurrentName());
                } catch (NumberFormatException e) {
                    num = null;
                }
            } else if (jsonParser.getCurrentToken() != JsonToken.VALUE_STRING || num == null) {
                jsonParser.skipChildren();
            } else {
                this.countryToRegionMap.put(num.intValue(), BaseModel.parseStringPreserveHTMLEscapeEncoding(jsonParser));
                num = null;
            }
        }
    }

    public String getRegionByCountryId(int i) {
        return (String) this.countryToRegionMap.get(i);
    }
}
