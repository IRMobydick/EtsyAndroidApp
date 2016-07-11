package com.etsy.android.lib.requests;

import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.Conversation;
import com.etsy.android.lib.models.ConversationMessage;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.requests.EtsyMultipartEntity.AsyncMultipartRequestCallback;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

public class ConversationRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    private static final String LIMIT_KEYWORD = "limit";
    private static final int NO_LIMIT = -1;
    private static final int NO_OFFSET = -1;
    private static final String OFFSET_KEYWORD = "offset";
    private static final String TAG;
    private static final long serialVersionUID = 4578979798761513013L;

    static {
        TAG = EtsyDebug.m1891a(ConversationRequest.class);
    }

    public ConversationRequest(String str, RequestMethod requestMethod, Class<Result> cls) {
        super(str, requestMethod, cls);
    }

    public static ConversationRequest<Conversation> getConversations() {
        return new ConversationRequest("/conversations", RequestMethod.GET, Conversation.class);
    }

    public static ConversationRequest<Conversation> getConversations(int i, int i2) {
        ConversationRequest<Conversation> conversations = getConversations();
        Map hashMap = new HashMap();
        if (i > NO_OFFSET) {
            hashMap.put(LIMIT_KEYWORD, String.valueOf(i));
        }
        if (i2 > NO_OFFSET) {
            hashMap.put(OFFSET_KEYWORD, String.valueOf(i2));
        }
        conversations.addParams(hashMap);
        return conversations;
    }

    public static ConversationRequest<ConversationMessage> getMessagesForConversations() {
        return new ConversationRequest("/conversations/messages", RequestMethod.GET, ConversationMessage.class);
    }

    public static void createNewConversation(String str, String str2, String str3, List<File> list, AsyncMultipartRequestCallback<EmptyResult, ConversationRequest<EmptyResult>> asyncMultipartRequestCallback) {
        EtsyRequest conversationRequest = new ConversationRequest("/conversations", RequestMethod.POST, EmptyResult.class);
        if (list == null || list.size() <= 0) {
            Map hashMap = new HashMap();
            hashMap.put("to_username", str);
            hashMap.put(FindsModule.FIELD_TITLE, str2);
            hashMap.put(ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE, str3);
            conversationRequest.addParams(hashMap);
            asyncMultipartRequestCallback.onRequestCreated(conversationRequest);
            return;
        }
        try {
            EtsyMultipartEntity etsyMultipartEntity = new EtsyMultipartEntity();
            etsyMultipartEntity.addPart("to_username", new StringBody(str, EtsyMultipartEntity.UTF_8));
            etsyMultipartEntity.addPart(FindsModule.FIELD_TITLE, new StringBody(str2, EtsyMultipartEntity.UTF_8));
            etsyMultipartEntity.addPart(ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE, new StringBody(str3, EtsyMultipartEntity.UTF_8));
            int i = 0;
            while (i < list.size()) {
                etsyMultipartEntity.addPart(ResponseConstants.IMAGE + (i == 0 ? StringUtils.EMPTY : Integer.valueOf(i + 1)), new FileBody((File) list.get(i), "jpeg"));
                i++;
            }
            etsyMultipartEntity.createMultipartAsync(conversationRequest, asyncMultipartRequestCallback);
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "Failed to encode multipart for convo", e);
            asyncMultipartRequestCallback.onRequestCreationFailed();
        }
    }

    public static void replyToConversation(long j, String str, List<File> list, AsyncMultipartRequestCallback<EmptyResult, ConversationRequest<EmptyResult>> asyncMultipartRequestCallback) {
        EtsyRequest conversationRequest = new ConversationRequest("/conversations/" + j + "/messages", RequestMethod.POST, EmptyResult.class);
        if (list == null || list.size() <= 0) {
            Map hashMap = new HashMap();
            hashMap.put(ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE, str);
            conversationRequest.addParams(hashMap);
            asyncMultipartRequestCallback.onRequestCreated(conversationRequest);
            return;
        }
        try {
            EtsyMultipartEntity etsyMultipartEntity = new EtsyMultipartEntity();
            etsyMultipartEntity.addPart(ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE, new StringBody(str, EtsyMultipartEntity.UTF_8));
            int i = 0;
            while (i < list.size()) {
                etsyMultipartEntity.addPart(ResponseConstants.IMAGE + (i == 0 ? StringUtils.EMPTY : Integer.valueOf(i + 1)), new FileBody((File) list.get(i), "jpeg"));
                i++;
            }
            etsyMultipartEntity.createMultipartAsync(conversationRequest, asyncMultipartRequestCallback);
        } catch (Throwable e) {
            EtsyDebug.m1917d(TAG, "Failed to encode multipart for convo", e);
        }
    }

    public static ConversationRequest<Conversation> getConversation(String str) {
        return new ConversationRequest("/conversations/" + str, RequestMethod.GET, Conversation.class);
    }

    public static ConversationRequest<EmptyResult> deleteConvo(long j) {
        return new ConversationRequest("/conversations/" + j, RequestMethod.DELETE, EmptyResult.class);
    }

    public static ConversationRequest<EmptyResult> markConvoRead(long j, Boolean bool) {
        ConversationRequest<EmptyResult> conversationRequest = new ConversationRequest("/conversations/" + j, RequestMethod.PUT, EmptyResult.class);
        Map hashMap = new HashMap();
        hashMap.put("mark_as_read", bool.toString());
        conversationRequest.addParams(hashMap);
        return conversationRequest;
    }

    public static ConversationRequest<EmptyResult> markConvoSpam(long j, Boolean bool) {
        ConversationRequest<EmptyResult> conversationRequest = new ConversationRequest("/conversations/" + j, RequestMethod.PUT, EmptyResult.class);
        Map hashMap = new HashMap();
        hashMap.put("mark_as_spam", bool.toString());
        conversationRequest.addParams(hashMap);
        return conversationRequest;
    }

    public static ConversationRequest<EmptyResult> markConvoTrash(long j, Boolean bool) {
        ConversationRequest<EmptyResult> conversationRequest = new ConversationRequest("/conversations/" + j, RequestMethod.PUT, EmptyResult.class);
        Map hashMap = new HashMap();
        hashMap.put("mark_as_trash", bool.toString());
        conversationRequest.addParams(hashMap);
        return conversationRequest;
    }
}
