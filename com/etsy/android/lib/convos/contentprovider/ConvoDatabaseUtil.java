package com.etsy.android.lib.convos.contentprovider;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import com.etsy.android.lib.convos.ConvoUtil;
import com.etsy.android.lib.convos.Draft;
import com.etsy.android.lib.convos.Draft.Status;
import com.etsy.android.lib.convos.p004a.ConvoQuery;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.Conversation;
import com.etsy.android.lib.models.ConversationUser;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Snippet;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.etsy.android.lib.convos.contentprovider.b */
public class ConvoDatabaseUtil {
    static final /* synthetic */ boolean f1364a;
    private static final String f1365b;

    static {
        f1364a = !ConvoDatabaseUtil.class.desiredAssertionStatus();
        f1365b = EtsyDebug.m1891a(ConvoDatabaseUtil.class);
    }

    public static Loader<Cursor> m969a(Context context) {
        return new CursorLoader(context, ConvoAuthority.m962a(ConvoPaths.CONVO), ConvoQuery.f1308a, null, null, "last_updated_tsz DESC");
    }

    public static Cursor m968a(Context context, String str) {
        String str2 = str + "%";
        String str3 = "other_user_name_full LIKE ? OR other_user_name_user LIKE ?";
        return context.getContentResolver().query(ConvoAuthority.m962a(ConvoPaths.CONTACTS), ConvoQuery.f1309b, "other_user_name_full LIKE ? OR other_user_name_user LIKE ?", new String[]{str2, str2}, "other_user_name_full");
    }

    public static void m975a(Context context, List<Conversation> list) {
        ConvoDatabaseUtil.m977b(context);
        ConvoDatabaseUtil.m978b(context, (List) list);
    }

    public static void m977b(Context context) {
        context.getContentResolver().delete(ConvoAuthority.m962a(ConvoPaths.CONVO), null, null);
    }

    public static void m979c(Context context) {
        context.getContentResolver().delete(ConvoAuthority.m962a(ConvoPaths.ERASE), null, null);
    }

    public static void m978b(Context context, List<Conversation> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (Conversation a : list) {
                arrayList.add(ContentProviderOperation.newInsert(ConvoAuthority.m962a(ConvoPaths.CONVO)).withValues(ConvoDatabaseUtil.m966a(a)).build());
            }
            ConvoDatabaseUtil.m974a(context, arrayList);
        }
    }

    public static void m971a(Context context, long j, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_read", Boolean.valueOf(z));
        String str = "conversation_id = ? ";
        String[] strArr = new String[]{String.valueOf(j)};
        ArrayList arrayList = new ArrayList();
        arrayList.add(ContentProviderOperation.newUpdate(ConvoAuthority.m963a(ConvoPaths.CONVO, j)).withValues(contentValues).withSelection("conversation_id = ? ", strArr).build());
        ConvoDatabaseUtil.m974a(context, arrayList);
    }

    public static void m970a(Context context, long j) {
        if (context != null) {
            context.getContentResolver().delete(ConvoAuthority.m962a(ConvoPaths.CONVO), "conversation_id = ? ", new String[]{String.valueOf(j)});
        }
    }

    public static Conversation m976b(Context context, long j) {
        if (context == null) {
            return null;
        }
        String[] strArr = new String[]{String.valueOf(j)};
        Cursor query = context.getContentResolver().query(ConvoAuthority.m963a(ConvoPaths.CONVO, j), ConvoQuery.f1308a, "conversation_id = ? ", strArr, "last_updated_tsz DESC");
        if (query == null) {
            return null;
        }
        Conversation a;
        if (query.moveToFirst()) {
            a = ConvoUtil.m1006a(query);
        } else {
            a = null;
        }
        query.close();
        return a;
    }

    public static void m981c(Context context, List<Snippet> list) {
        ConvoDatabaseUtil.m982d(context);
        ConvoDatabaseUtil.m983d(context, list);
    }

    private static void m982d(Context context) {
        context.getContentResolver().delete(ConvoAuthority.m962a(ConvoPaths.SNIPPET), null, null);
    }

    public static void m983d(Context context, List<Snippet> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (Snippet a : list) {
                arrayList.add(ContentProviderOperation.newInsert(ConvoAuthority.m962a(ConvoPaths.SNIPPET)).withValues(ConvoDatabaseUtil.m967a(a)).build());
            }
            ConvoDatabaseUtil.m974a(context, arrayList);
        }
    }

    public static void m973a(Context context, EtsyId etsyId, Status status) {
        if (context != null && etsyId != null) {
            context.getContentResolver().update(ConvoAuthority.m962a(ConvoPaths.DRAFT), ConvoDatabaseUtil.m964a(status), "conversation_id = ? ", new String[]{etsyId.toString()});
        }
    }

    public static void m972a(Context context, Draft draft) {
        if (f1364a || draft != null) {
            String str = "conversation_id = ? ";
            new String[1][0] = String.valueOf(draft.getConvoId());
            ContentValues a = ConvoDatabaseUtil.m965a(draft);
            ArrayList arrayList = new ArrayList();
            arrayList.add(ContentProviderOperation.newInsert(ConvoAuthority.m962a(ConvoPaths.DRAFT)).withValues(a).build());
            ConvoDatabaseUtil.m974a(context, arrayList);
            return;
        }
        throw new AssertionError();
    }

    public static void m980c(Context context, long j) {
        if (context != null) {
            context.getContentResolver().delete(ConvoAuthority.m962a(ConvoPaths.DRAFT), "conversation_id = ? ", new String[]{String.valueOf(j)});
        }
    }

    private static ContentValues m966a(Conversation conversation) {
        if (f1364a || conversation != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ResponseConstants.CONVERSATION_ID, Long.valueOf(conversation.getConversationId()));
            contentValues.put("is_read", Boolean.valueOf(conversation.isRead()));
            contentValues.put("has_attachment", Boolean.valueOf(conversation.hasAttachments()));
            contentValues.put("last_message", conversation.getLastMessage());
            contentValues.put("last_updated_tsz", Long.valueOf(conversation.getLastUpdateDate()));
            contentValues.put("message_count", Integer.valueOf(conversation.getMessageCount()));
            contentValues.put(FindsModule.FIELD_TITLE, conversation.getTitle());
            ConversationUser otherUser = conversation.getOtherUser();
            if (f1364a || otherUser != null) {
                contentValues.put("other_user_id", Long.valueOf(otherUser.getUserId()));
                contentValues.put("other_user_name_user", otherUser.getUsername());
                contentValues.put("other_user_name_full", otherUser.getDisplayName());
                contentValues.put("other_user_avatar_url", otherUser.getAvatarUrl());
                contentValues.put("is_custom_shop", Boolean.valueOf(conversation.isCustomShop()));
                return contentValues;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    private static ContentValues m965a(Draft draft) {
        if (f1364a || draft != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ResponseConstants.CONVERSATION_ID, Long.valueOf(draft.getConvoId()));
            contentValues.put("addressee", draft.getUserName());
            contentValues.put(ResponseConstants.SUBJECT, draft.getSubject());
            contentValues.put(ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE, draft.getMessage());
            contentValues.put("send_status", draft.getStatus().name());
            boolean z = draft.getImages() != null && draft.getImages().size() > 0;
            contentValues.put("has_images", Boolean.valueOf(z));
            if (z) {
                contentValues.put("image_filenames", draft.imagePathsAsString());
            }
            return contentValues;
        }
        throw new AssertionError();
    }

    private static ContentValues m964a(Status status) {
        if (f1364a || status != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("send_status", status.name());
            return contentValues;
        }
        throw new AssertionError();
    }

    private static ContentValues m967a(Snippet snippet) {
        if (f1364a || snippet != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("snippet_id", snippet.getId().toString());
            contentValues.put(FindsModule.FIELD_TITLE, snippet.getTitle());
            contentValues.put("content", snippet.getContent());
            return contentValues;
        }
        throw new AssertionError();
    }

    private static void m974a(Context context, ArrayList<ContentProviderOperation> arrayList) {
        try {
            context.getContentResolver().applyBatch(EtsyApplication.get().getConvoAuthority(), arrayList);
        } catch (Throwable e) {
            EtsyDebug.m1917d(f1365b, "RemoteException on applyBatch", e);
        } catch (Throwable e2) {
            EtsyDebug.m1917d(f1365b, "OperationApplicationException on applyBatch", e2);
        }
    }
}
