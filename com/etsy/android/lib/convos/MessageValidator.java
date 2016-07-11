package com.etsy.android.lib.convos;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import com.etsy.android.lib.R;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.bl;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.lib.convos.m */
public class MessageValidator {
    public static boolean m1008a(Context context, String str, String str2, String str3) {
        return MessageValidator.m1010b(context, str) && MessageValidator.m1015f(context, str2) && MessageValidator.m1013d(context, str3);
    }

    public static boolean m1007a(Context context, String str) {
        return MessageValidator.m1013d(context, str);
    }

    public static boolean m1011b(Context context, String str, String str2, String str3) {
        return TextUtils.isEmpty(MessageValidator.m1012c(context, str)) && TextUtils.isEmpty(MessageValidator.m1016g(context, str2)) && TextUtils.isEmpty(MessageValidator.m1014e(context, str3));
    }

    public static boolean m1010b(Context context, String str) {
        String c = MessageValidator.m1012c(context, str);
        if (!TextUtils.isEmpty(c)) {
            bl.m3356a(context, c);
        }
        return TextUtils.isEmpty(c);
    }

    public static String m1012c(Context context, String str) {
        String str2 = StringUtils.EMPTY;
        if (TextUtils.isEmpty(str)) {
            return context.getString(R.convo_message_username_validation);
        }
        if (str.equalsIgnoreCase(SharedPreferencesUtility.m3140e(context))) {
            return context.getString(R.convo_message_username_to_yourself);
        }
        return str2;
    }

    public static boolean m1013d(Context context, String str) {
        String e = MessageValidator.m1014e(context, str);
        if (!TextUtils.isEmpty(e)) {
            bl.m3356a(context, e);
        }
        return TextUtils.isEmpty(e);
    }

    public static boolean m1009a(EditText editText) {
        return (editText == null || editText.getText() == null || !TextUtils.isEmpty(MessageValidator.m1014e(editText.getContext(), editText.getText().toString()))) ? false : true;
    }

    public static String m1014e(Context context, String str) {
        String str2 = StringUtils.EMPTY;
        if (TextUtils.isEmpty(str)) {
            return context.getString(R.convo_message_validation);
        }
        return str2;
    }

    public static boolean m1015f(Context context, String str) {
        String h = MessageValidator.m1017h(context, str);
        if (!TextUtils.isEmpty(h)) {
            bl.m3356a(context, h);
        }
        return TextUtils.isEmpty(h);
    }

    public static String m1016g(Context context, String str) {
        String str2 = StringUtils.EMPTY;
        if (TextUtils.isEmpty(str)) {
            return context.getString(R.convo_message_subject_validation);
        }
        return str2;
    }

    public static String m1017h(Context context, String str) {
        String str2 = StringUtils.EMPTY;
        if (TextUtils.isEmpty(str) || str.length() < 3) {
            return context.getString(R.convo_message_subject_validation);
        }
        return str2;
    }
}
