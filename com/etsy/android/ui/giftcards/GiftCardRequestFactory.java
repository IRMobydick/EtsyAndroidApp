package com.etsy.android.ui.giftcards;

import android.text.TextUtils;
import com.etsy.android.lib.config.InstallInfo;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.core.http.request.EtsyApiV2Request;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.EtsyArray;
import com.etsy.android.lib.models.GiftCardDesign;
import com.etsy.android.lib.models.ResponseConstants;

/* renamed from: com.etsy.android.ui.giftcards.a */
public class GiftCardRequestFactory {
    public EtsyApiV2Request<EmptyResult> m4263a(int i, String str, String str2, String str3, int i2, String str4) {
        String str5;
        boolean z = false;
        if (aj.m1101a().m1118d()) {
            str5 = "/users/__SELF__/carts/giftcards";
        } else {
            str5 = String.format("/guests/%s/carts/giftcards", new Object[]{InstallInfo.m919a().m928e()});
        }
        if (!TextUtils.isEmpty(str)) {
            z = true;
        }
        EtsyApiV2Request a = EtsyApiV2Request.m1435a(EmptyResult.class, str5);
        ((EtsyApiV2Request) ((EtsyApiV2Request) ((EtsyApiV2Request) ((EtsyApiV2Request) ((EtsyApiV2Request) ((EtsyApiV2Request) a.m1382a(1)).m1385a("recipient_name", str2)).m1385a("sender_name", str3)).m1385a(ResponseConstants.AMOUNT, Integer.toString(i))).m1385a(ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE, str4)).m1385a("is_email", Boolean.toString(z))).m1385a("design_id", Integer.toString(i2));
        if (z) {
            a.m1385a("recipient_email", str);
        }
        return a.m1444h();
    }

    public EtsyApiV2Request<GiftCardDesign> m4262a() {
        EtsyApiV2Request a = EtsyApiV2Request.m1435a(GiftCardDesign.class, "/giftcards/designs");
        a.m1382a(0);
        return a.m1444h();
    }

    public EtsyApiV2Request<EtsyArray> m4264b() {
        EtsyApiV2Request a = EtsyApiV2Request.m1435a(EtsyArray.class, "/types/enum/giftcard_amount");
        a.m1382a(0);
        return a.m1444h();
    }
}
