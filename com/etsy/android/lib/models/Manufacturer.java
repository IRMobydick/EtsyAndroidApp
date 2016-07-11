package com.etsy.android.lib.models;

import com.etsy.android.lib.logger.EtsyDebug;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class Manufacturer extends BaseModel {
    private static final String TAG;
    protected String mDescription;
    protected String mLocation;
    protected String mName;

    static {
        TAG = EtsyDebug.m1891a(Manufacturer.class);
    }

    public Manufacturer() {
        this.mName = StringUtils.EMPTY;
        this.mDescription = StringUtils.EMPTY;
        this.mLocation = StringUtils.EMPTY;
    }

    public String getName() {
        return this.mName;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getLocation() {
        return this.mLocation;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ResponseConstants.NAME.equals(currentName)) {
                    this.mName = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.DESCRIPTION.equals(currentName)) {
                    this.mDescription = BaseModel.parseString(jsonParser);
                } else if (ResponseConstants.LOCATION.equals(currentName)) {
                    this.mLocation = BaseModel.parseString(jsonParser);
                } else {
                    EtsyDebug.m1908b(TAG, "Field %s not found on Manufacturer Model", currentName);
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
