package bo.app;

import com.appboy.Constants;
import com.appboy.models.IPutIntoJson;
import com.etsy.android.lib.models.apiv3.cart.PaymentMethod;
import java.util.HashMap;
import java.util.Map;

public enum ae implements IPutIntoJson<String> {
    LOCATION_RECORDED("lr"),
    CUSTOM_EVENT("ce"),
    PURCHASE(Constants.APPBOY_PUSH_PRIORITY_KEY),
    PUSH_NOTIFICATION_TRACKING("pc"),
    PUSH_NOTIFICATION_ACTION_TRACKING("ca"),
    INTERNAL("i"),
    INTERNAL_ERROR("ie"),
    CARD_IMPRESSION("ci"),
    CARD_CLICK(PaymentMethod.TYPE_CC),
    USER_TRANSITION("ut"),
    INCREMENT("inc"),
    ADD_TO_CUSTOM_ATTRIBUTE_ARRAY("add"),
    REMOVE_FROM_CUSTOM_ATTRIBUTE_ARRAY("rem"),
    SET_CUSTOM_ATTRIBUTE_ARRAY("set"),
    INAPP_MESSAGE_IMPRESSION("si"),
    INAPP_MESSAGE_CLICK("sc"),
    INAPP_MESSAGE_BUTTON_CLICK("sbc");
    
    private static final Map<String, ae> f51s;
    public final String f53r;

    static {
        Map hashMap = new HashMap();
        ae[] values = values();
        int length = values.length;
        int i;
        while (i < length) {
            ae aeVar = values[i];
            hashMap.put(aeVar.f53r, aeVar);
            i++;
        }
        f51s = new HashMap(hashMap);
    }

    private ae(String str) {
        this.f53r = str;
    }

    public static ae m17a(String str) {
        if (f51s.containsKey(str)) {
            return (ae) f51s.get(str);
        }
        throw new IllegalArgumentException("Unknown String Value: " + str);
    }
}
