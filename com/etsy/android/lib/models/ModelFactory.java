package com.etsy.android.lib.models;

import com.etsy.android.lib.logger.EtsyDebug;
import com.fasterxml.jackson.core.JsonParser;

public class ModelFactory {
    public static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(ModelFactory.class);
    }

    public static <T extends BaseModel> T create(JsonParser jsonParser, Class<T> cls) {
        if (cls != null) {
            try {
                BaseModel baseModel = (BaseModel) cls.newInstance();
                baseModel.parseData(jsonParser);
                return baseModel;
            } catch (Throwable e) {
                EtsyDebug.m1917d(TAG, "InstantiationException creating class=" + cls, e);
            } catch (Throwable e2) {
                EtsyDebug.m1917d(TAG, "IllegalAccessException creating class=" + cls, e2);
            }
        } else {
            EtsyDebug.m1919e(TAG, "CANT CREATE MODEL WITH NULL CLASS!!!");
            return null;
        }
    }
}
