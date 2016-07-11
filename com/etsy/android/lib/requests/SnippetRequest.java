package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.Snippet;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.Map;

public class SnippetRequest extends EtsyRequest<Snippet> {
    private static final String ENDPOINT = "/snippets";
    private static final long serialVersionUID = -4910262228307435102L;

    public SnippetRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, Snippet.class);
    }

    public static SnippetRequest getConvoSnippets() {
        String str = ENDPOINT;
        return new SnippetRequest(ENDPOINT, RequestMethod.GET);
    }

    public static SnippetRequest getConvoSnippet(EtsyId etsyId) {
        return new SnippetRequest("/snippets/" + etsyId, RequestMethod.GET);
    }

    public static SnippetRequest createConvoSnippet(String str, String str2) {
        String str3 = ENDPOINT;
        SnippetRequest snippetRequest = new SnippetRequest(ENDPOINT, RequestMethod.POST);
        Map hashMap = new HashMap();
        hashMap.put(FindsModule.FIELD_TITLE, str);
        hashMap.put("content", str2);
        snippetRequest.addParams(hashMap);
        return snippetRequest;
    }

    public static SnippetRequest updateConvoSnippet(Snippet snippet) {
        SnippetRequest snippetRequest = new SnippetRequest("/snippets/" + snippet.getId(), RequestMethod.PUT);
        Map hashMap = new HashMap();
        hashMap.put(FindsModule.FIELD_TITLE, snippet.getTitle());
        hashMap.put("content", snippet.getContent());
        snippetRequest.addParams(hashMap);
        return snippetRequest;
    }

    public static SnippetRequest deleteConvoSnippet(Snippet snippet) {
        return deleteConvoSnippet(snippet.getId().toString());
    }

    public static SnippetRequest deleteConvoSnippet(String str) {
        return new SnippetRequest("/snippets/" + str, RequestMethod.DELETE);
    }

    public static SnippetRequest getShippingNotificationMessage() {
        String str = "/shipping/notifications/message";
        return new SnippetRequest("/shipping/notifications/message", RequestMethod.GET);
    }
}
