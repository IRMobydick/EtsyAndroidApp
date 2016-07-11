package com.etsy.android.lib.convos;

import android.database.Cursor;
import com.etsy.android.lib.models.Conversation;
import com.etsy.android.lib.models.ConversationUser;

/* renamed from: com.etsy.android.lib.convos.l */
public class ConvoUtil {
    public static Conversation m1006a(Cursor cursor) {
        boolean z = true;
        if (cursor == null || cursor.isBeforeFirst() || cursor.isAfterLast()) {
            return null;
        }
        Conversation conversation = new Conversation();
        conversation.setConversationId(cursor.getLong(1));
        conversation.setMessageCount(cursor.getInt(5));
        conversation.setTitle(cursor.getString(7));
        if (cursor.getInt(2) != 1) {
            z = false;
        }
        conversation.setIsRead(Boolean.valueOf(z));
        conversation.setLastUpdateDate(cursor.getLong(3));
        conversation.setLastMessage(cursor.getString(4));
        ConversationUser conversationUser = new ConversationUser();
        conversationUser.setUserId(cursor.getLong(8));
        conversationUser.setUserName(cursor.getString(9));
        conversationUser.setDisplayName(cursor.getString(10));
        conversationUser.setAvatarUrl(cursor.getString(11));
        conversation.setOtherUser(conversationUser);
        return conversation;
    }
}
