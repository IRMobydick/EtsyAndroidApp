package bo.app;

import com.etsy.android.lib.models.apiv3.StructuredShopShippingEstimate;
import com.etsy.android.lib.requests.EtsyRequest;

public enum ad {
    ANDROID_VERSION("android_version"),
    ABI("abi"),
    CARRIER("carrier"),
    MODEL("model"),
    LOCALE_LANGUAGE(EtsyRequest.PARAM_LANGUAGE),
    LOCALE_COUNTRY(StructuredShopShippingEstimate.TYPE_COUNTRY),
    LOCALE("locale"),
    TIMEZONE("time_zone"),
    DISPLAY("display"),
    DEVICE_IDENTIFIERS("device_identifiers"),
    PUSH_TOKEN("push_token"),
    CONNECTED_DEVICES("connected_devices");
    
    public String f33m;

    private ad(String str) {
        this.f33m = str;
    }
}
