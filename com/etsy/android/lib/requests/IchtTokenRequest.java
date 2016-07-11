package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.icht.IchtToken;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.Map;

public class IchtTokenRequest extends EtsyRequest<IchtToken> {
    private static final String TOKENIZE = "/payments/tokenize.php";

    public IchtTokenRequest() {
        super(TOKENIZE, RequestMethod.POST, IchtToken.class, EndpointType.I_CAN_HAZ_TOKEN);
    }

    public static IchtTokenRequest getTokenizeRequest(Map map) {
        IchtTokenRequest ichtTokenRequest = new IchtTokenRequest();
        map.put("_is_jsonp", "false");
        ichtTokenRequest.addParams(map);
        return ichtTokenRequest;
    }
}
