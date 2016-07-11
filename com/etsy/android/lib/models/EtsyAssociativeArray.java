package com.etsy.android.lib.models;

import android.support.annotation.NonNull;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class EtsyAssociativeArray extends BaseFieldModel {
    HashMap<String, Object> mHashMap;

    public EtsyAssociativeArray() {
        this.mHashMap = new HashMap();
    }

    protected boolean parseField(JsonParser jsonParser, @NonNull String str) {
        if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            try {
                this.mHashMap.put(str, BaseModel.parseRawStringArray(jsonParser));
            } catch (UnsupportedFormatException e) {
                List arrayList = new ArrayList();
                while (jsonParser.getCurrentToken() != JsonToken.END_ARRAY) {
                    arrayList.add(BaseModel.parseObject(jsonParser, EtsyAssociativeArray.class));
                    jsonParser.nextToken();
                }
                this.mHashMap.put(str, arrayList);
            }
        } else if (JsonToken.START_OBJECT.equals(jsonParser.getCurrentToken())) {
            this.mHashMap.put(str, BaseModel.parseObject(jsonParser, EtsyAssociativeArray.class));
        } else {
            this.mHashMap.put(str, BaseModel.parseString(jsonParser));
        }
        return false;
    }

    public String optString(String str, String str2) {
        if (this.mHashMap.containsKey(str)) {
            Object obj = this.mHashMap.get(str);
            if (obj instanceof String) {
                return (String) obj;
            }
        }
        return str2;
    }

    public String getString(String str) {
        return (String) this.mHashMap.get(str);
    }

    public List<?> getList(String str) {
        if (this.mHashMap.containsKey(str)) {
            Object obj = this.mHashMap.get(str);
            if (obj instanceof List) {
                return (List) obj;
            }
            if (obj instanceof EtsyAssociativeArray) {
                return (List) obj;
            }
        }
        return null;
    }

    public EtsyAssociativeArray getHashMap(String str) {
        if (this.mHashMap.containsKey(str)) {
            Object obj = this.mHashMap.get(str);
            if (obj instanceof EtsyAssociativeArray) {
                return (EtsyAssociativeArray) obj;
            }
        }
        return null;
    }
}
