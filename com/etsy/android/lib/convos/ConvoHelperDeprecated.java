package com.etsy.android.lib.convos;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.requests.ConversationRequest;
import com.etsy.android.lib.requests.EtsyMultipartEntity.AsyncMultipartRequestCallback;
import com.etsy.android.lib.util.CameraUtils;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

@Deprecated
/* renamed from: com.etsy.android.lib.convos.d */
public class ConvoHelperDeprecated extends ConvoHelper {
    private static final String f1366d;
    private ConvoHelperDeprecated f1367c;

    static {
        f1366d = EtsyDebug.m1891a(ConvoHelperDeprecated.class);
    }

    public ConvoHelperDeprecated(Activity activity, ConvoHelperDeprecated convoHelperDeprecated) {
        super(activity);
        this.f1367c = convoHelperDeprecated;
    }

    public void m991a(Draft draft) {
        AsyncMultipartRequestCallback convoHelperDeprecated = new ConvoHelperDeprecated(this, draft);
        if (draft.getConvoId() > 0) {
            if (MessageValidator.m1007a(this.a.getBaseContext(), draft.getMessage())) {
                ConversationRequest.replyToConversation(draft.getConvoId(), draft.getMessage(), draft.getImages(), convoHelperDeprecated);
            }
        } else if (MessageValidator.m1008a(this.a.getBaseContext(), draft.getUserName(), draft.getSubject(), draft.getMessage())) {
            ConversationRequest.createNewConversation(draft.getUserName(), draft.getSubject(), draft.getMessage(), draft.getImages(), convoHelperDeprecated);
        }
    }

    @Deprecated
    public static void m986a(Context context, boolean z) {
        if (context != null) {
            Editor edit = context.getSharedPreferences("convo_prefs", 0).edit();
            edit.putBoolean("convo_is_sending", z);
            edit.apply();
        }
    }

    @Deprecated
    public static boolean m988a(Context context, long j) {
        if (context == null) {
            return false;
        }
        long j2;
        SharedPreferences sharedPreferences = context.getSharedPreferences("convo_prefs", 0);
        try {
            j2 = sharedPreferences.getLong("convo_id", 0);
        } catch (Throwable e) {
            EtsyDebug.m1917d(f1366d, "Class cast to long exception", e);
            j2 = sharedPreferences.getLong("convo_id", 0);
        }
        if (j2 == j) {
            return sharedPreferences.getBoolean("convo_is_sending", false);
        }
        return false;
    }

    @Deprecated
    public static Draft m989b(Context context) {
        Draft saveCursorPosition;
        SharedPreferences sharedPreferences = context.getSharedPreferences("convo_prefs", 0);
        try {
            saveCursorPosition = new Draft().message(sharedPreferences.getString(ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE, StringUtils.EMPTY)).userName(sharedPreferences.getString("username", StringUtils.EMPTY)).subject(sharedPreferences.getString(ResponseConstants.SUBJECT, StringUtils.EMPTY)).convoId(sharedPreferences.getLong("convo_id", 0)).saveCursorPosition(sharedPreferences.getInt("cursor_position_start", 0), sharedPreferences.getInt("cursor_position_end", 0));
        } catch (Throwable e) {
            EtsyDebug.m1917d(f1366d, "error converting long to string", e);
            saveCursorPosition = new Draft().message(sharedPreferences.getString(ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE, StringUtils.EMPTY)).userName(sharedPreferences.getString("username", StringUtils.EMPTY)).subject(sharedPreferences.getString(ResponseConstants.SUBJECT, StringUtils.EMPTY)).convoId(sharedPreferences.getLong("convo_id", 0)).saveCursorPosition(sharedPreferences.getInt("cursor_position_start", 0), sharedPreferences.getInt("cursor_position_end", 0));
        }
        saveCursorPosition.images(ConvoHelper.m956a(sharedPreferences.getStringSet(FindsModule.FIELD_IMAGES, null)));
        return saveCursorPosition;
    }

    @Deprecated
    public static void m985a(Context context, Draft draft) {
        if (context != null && draft != null) {
            Editor edit = context.getSharedPreferences("convo_prefs", 0).edit();
            edit.putString(ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE, draft.getMessage());
            edit.putString("username", draft.getUserName());
            edit.putString(ResponseConstants.SUBJECT, draft.getSubject());
            edit.putLong("convo_id", draft.getConvoId());
            edit.putInt("cursor_position_start", draft.getCursorStartPosition());
            edit.putInt("cursor_position_end", draft.getCursorEndPosition());
            if (draft.getImages() != null) {
                Set hashSet = new HashSet(draft.getImages().size());
                for (File absolutePath : draft.getImages()) {
                    hashSet.add(absolutePath.getAbsolutePath());
                }
                edit.putStringSet(FindsModule.FIELD_IMAGES, hashSet);
            }
            edit.apply();
        }
    }

    @Deprecated
    public static void m990c(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("convo_prefs", 0);
        ConvoHelperDeprecated.m987a(sharedPreferences);
        sharedPreferences.edit().remove(ResponseConstants.TRANSLATED_CONVERSATION_MESSAGE).remove("username").remove(ResponseConstants.SUBJECT).remove("convo_id").remove(FindsModule.FIELD_IMAGES).remove("cursor_position_start").remove("cursor_position_end").remove("convo_is_sending").apply();
    }

    private static void m987a(SharedPreferences sharedPreferences) {
        CameraUtils.m3392a(ConvoHelper.m956a(sharedPreferences.getStringSet(FindsModule.FIELD_IMAGES, null)));
    }
}
