package com.etsy.android.lib.models;

import android.support.annotation.Nullable;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.ITrackedObject;
import com.etsy.android.lib.util.Closure;
import com.etsy.android.uikit.cardview.ICardViewElement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringEscapeUtils;

public abstract class BaseModel implements ITrackedObject, ICardViewElement, Serializable {
    protected static final long CREATION_MILLIS_OFFSET = 1000;
    private static final long serialVersionUID = -4273544891826300016L;

    class UnsupportedFormatException extends IOException {
        public UnsupportedFormatException(String str) {
            super(str);
        }
    }

    public abstract void parseData(JsonParser jsonParser);

    @Nullable
    @JsonIgnore
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        return null;
    }

    public static String parseString(JsonParser jsonParser) {
        return parseString(jsonParser, false);
    }

    public static String parseStringURL(JsonParser jsonParser) {
        return parseString(jsonParser, true);
    }

    public static String parseStringIdOrNumericValue(JsonParser jsonParser) {
        return parseString(jsonParser, true);
    }

    public static String parseStringPreserveHTMLEscapeEncoding(JsonParser jsonParser) {
        return parseString(jsonParser, true);
    }

    private static String parseString(JsonParser jsonParser, boolean z) {
        String valueAsString = jsonParser.getValueAsString();
        if (z) {
            return valueAsString;
        }
        return StringEscapeUtils.unescapeHtml4(valueAsString);
    }

    public static <T extends BaseModel> List<T> parseArray(JsonParser jsonParser, Class<T> cls) {
        List arrayList = new ArrayList();
        BaseModel parseObject;
        if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                parseObject = parseObject(jsonParser, cls);
                if (parseObject != null) {
                    arrayList.add(parseObject);
                }
            }
        } else if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
            parseObject = parseObject(jsonParser, cls);
            if (parseObject != null) {
                arrayList.add(parseObject);
            }
        }
        return arrayList;
    }

    public static <T extends BaseModel> T parseObject(JsonParser jsonParser, Class<T> cls) {
        if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
            return ModelFactory.create(jsonParser, cls);
        }
        return null;
    }

    public static <K, V> Map<K, V> parseMap(JsonParser jsonParser, Closure<JsonParser, K> closure, Closure<JsonParser, V> closure2) {
        Map hashMap = new HashMap();
        if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                Object a = closure.m2775a(jsonParser);
                jsonParser.nextToken();
                Object a2 = closure2.m2775a(jsonParser);
                if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                    hashMap.put(a, a2);
                }
            }
        }
        return hashMap;
    }

    public static List<String> parseStringArray(JsonParser jsonParser) {
        return parseStringArray(jsonParser, false);
    }

    public static List<String> parseRawStringArray(JsonParser jsonParser) {
        return parseStringArray(jsonParser, true);
    }

    private static List<String> parseStringArray(JsonParser jsonParser, boolean z) {
        List arrayList = new ArrayList();
        Object parseStringURL;
        if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
                    throw new UnsupportedFormatException("Array of objects cannot be parsed as array of strings");
                }
                parseStringURL = z ? parseStringURL(jsonParser) : parseString(jsonParser);
                if (parseStringURL != null) {
                    arrayList.add(parseStringURL);
                }
            }
        } else if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
            parseStringURL = z ? parseStringURL(jsonParser) : parseString(jsonParser);
            if (parseStringURL != null) {
                arrayList.add(parseStringURL);
            }
        }
        return arrayList;
    }

    public static List<Integer> parseIntArray(JsonParser jsonParser) {
        List<Integer> arrayList = new ArrayList();
        if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                arrayList.add(Integer.valueOf(jsonParser.getValueAsInt()));
            }
        } else if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
            arrayList.add(Integer.valueOf(jsonParser.getValueAsInt()));
        }
        return arrayList;
    }

    protected static Date parseIntoDate(JsonParser jsonParser) {
        long valueAsLong = jsonParser.getValueAsLong();
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(valueAsLong * CREATION_MILLIS_OFFSET);
        return instance.getTime();
    }

    @JsonIgnore
    public int getViewType() {
        return -1;
    }
}
