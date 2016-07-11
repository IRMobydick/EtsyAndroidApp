package com.etsy.android.lib.requests;

import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.Guest;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.Map;

public class GuestRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    private static final String TAG;
    private static final long serialVersionUID = -4101265580290196817L;

    static {
        TAG = EtsyDebug.m1891a(GuestRequest.class);
    }

    public GuestRequest(String str, RequestMethod requestMethod, Class<Result> cls) {
        super(str, requestMethod, cls);
    }

    public static GuestRequest<Guest> mergeFromGuest(String str) {
        GuestRequest<Guest> guestRequest = new GuestRequest("/guests/" + str + "/merge", RequestMethod.POST, Guest.class);
        Map hashMap = new HashMap();
        hashMap.put("target_guest_id", InstallInfo.m919a().m928e());
        guestRequest.addParams(hashMap);
        return guestRequest;
    }
}
