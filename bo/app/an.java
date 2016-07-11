package bo.app;

import java.util.HashMap;
import java.util.Map;

public enum an {
    CUSTOM_EVENT,
    LOG_PURCHASE,
    LOG_PUSH_NOTIFICATION_OPENED,
    SUBMIT_FEEDBACK,
    ADD_TO_CUSTOM_ATTRIBUTE_ARRAY,
    INCREMENT_CUSTOM_ATTRIBUTE,
    REMOVE_FROM_CUSTOM_ATTRIBUTE_ARRAY,
    SET_CUSTOM_ATTRIBUTE_ARRAY,
    SET_CUSTOM_ATTRIBUTE,
    UNSET_CUSTOM_ATTRIBUTE,
    SET_CUSTOM_ATTRIBUTE_TO_NOW,
    SET_CUSTOM_ATTRIBUTE_TO_SECONDS_FROM_EPOCH,
    SET_LAST_KNOWN_LOCATION,
    SET_AVATAR_IMAGE_URL,
    SET_COUNTRY,
    SET_DATE_OF_BIRTH,
    SET_EMAIL,
    SET_FIRST_NAME,
    SET_GENDER,
    SET_HOME_CITY,
    SET_LAST_NAME,
    SET_PHONE_NUMBER,
    SEND_WEAR_DEVICE,
    UNSUPPORTED_SDK_ACTION;
    
    private static final Map<String, an> f107y;

    static {
        f107y = new HashMap();
        an[] values = values();
        int length = values.length;
        int i;
        while (i < length) {
            an anVar = values[i];
            f107y.put(anVar.name(), anVar);
            i++;
        }
    }

    public static an m18a(String str) {
        if (f107y.containsKey(str)) {
            return (an) f107y.get(str);
        }
        return UNSUPPORTED_SDK_ACTION;
    }
}
