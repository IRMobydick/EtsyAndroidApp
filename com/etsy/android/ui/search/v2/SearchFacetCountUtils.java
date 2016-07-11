package com.etsy.android.ui.search.v2;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import com.etsy.android.lib.models.apiv3.FacetCount;
import com.etsy.android.lib.util.fonts.StandardFontIcon;
import com.etsy.android.uikit.text.typeface.TypefaceCache;
import java.util.List;

/* renamed from: com.etsy.android.ui.search.v2.g */
public final class SearchFacetCountUtils {
    public static CharSequence m4919a(Context context, String str, int i) {
        String str2 = "\u2002" + StandardFontIcon.NAVIGATE_RIGHT.toString() + "\u2002";
        CharSequence spannableStringBuilder = new SpannableStringBuilder();
        String[] split = str.split("\\|");
        int length = split.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 > 0) {
                CharSequence a = TypefaceCache.m5414a().m5417a(context, StandardFontIcon.getTypefaceName(), str2);
                a.setSpan(new RelativeSizeSpan(0.6f), 0, str2.length(), 33);
                a.setSpan(new ForegroundColorSpan(i), 0, str2.length(), 33);
                spannableStringBuilder.append(a);
            }
            spannableStringBuilder.append(split[i2]);
        }
        return spannableStringBuilder;
    }

    public static String m4920a(List<FacetCount> list) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuilder.append("|");
            }
            stringBuilder.append(((FacetCount) list.get(i)).getName());
        }
        return stringBuilder.toString();
    }
}
