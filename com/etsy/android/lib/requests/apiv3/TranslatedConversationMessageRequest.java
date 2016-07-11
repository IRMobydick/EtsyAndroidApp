package com.etsy.android.lib.requests.apiv3;

import com.etsy.android.lib.models.apiv3.TranslatedConversationMessage;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.Map;

public class TranslatedConversationMessageRequest extends EtsyRequest<TranslatedConversationMessage> {
    public TranslatedConversationMessageRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, TranslatedConversationMessage.class, EndpointType.APIv3);
    }

    public static TranslatedConversationMessageRequest getTranslatedConversationMessage(EtsyId etsyId, int i, String str) {
        TranslatedConversationMessageRequest translatedConversationMessageRequest = new TranslatedConversationMessageRequest("/translations/conversations/" + etsyId + "/messages/" + i, RequestMethod.GET);
        Map hashMap = new HashMap();
        hashMap.put(EtsyRequest.PARAM_LANGUAGE, str);
        translatedConversationMessageRequest.addParams(hashMap);
        translatedConversationMessageRequest.setV3Scope(APIv3Scope.MEMBER);
        return translatedConversationMessageRequest;
    }
}
