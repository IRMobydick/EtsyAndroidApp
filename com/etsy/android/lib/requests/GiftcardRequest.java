package com.etsy.android.lib.requests;

import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.GiftCard;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.io.File;
import org.apache.http.entity.mime.content.FileBody;

public class GiftcardRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    private static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(GiftcardRequest.class);
    }

    public GiftcardRequest(String str, RequestMethod requestMethod, Class<Result> cls) {
        super(str, requestMethod, cls);
    }

    public static GiftcardRequest<GiftCard> getGiftcardBalances() {
        String str = "/member/giftcard-balances";
        return new GiftcardRequest("/member/giftcard-balances", RequestMethod.GET, GiftCard.class);
    }

    public static GiftcardRequest unlockGiftcardRequest(File file) {
        String str = "/member/giftcard-balances";
        EtsyRequest giftcardRequest = new GiftcardRequest("/member/giftcard-balances", RequestMethod.POST, GiftCard.class);
        try {
            EtsyMultipartEntity etsyMultipartEntity = new EtsyMultipartEntity();
            etsyMultipartEntity.addPart(ResponseConstants.IMAGE, new FileBody(file, "jpeg"));
            etsyMultipartEntity.toEtsyRequest(giftcardRequest);
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "Issue uploading giftcard image", e);
        }
        giftcardRequest.setTimeout(25000);
        return giftcardRequest;
    }
}
