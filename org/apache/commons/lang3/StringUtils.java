package org.apache.commons.lang3;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.foresee.sdk.configuration.MeasureConfiguration;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class StringUtils {
    public static final String EMPTY = "";
    public static final int INDEX_NOT_FOUND = -1;
    private static final int PAD_LIMIT = 8192;
    private static final Pattern WHITESPACE_BLOCK;

    static {
        WHITESPACE_BLOCK = Pattern.compile("\\s+");
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence charSequence) {
        return !isEmpty(charSequence);
    }

    public static boolean isBlank(CharSequence charSequence) {
        if (charSequence != null) {
            int length = charSequence.length();
            if (length != 0) {
                for (int i = 0; i < length; i++) {
                    if (!Character.isWhitespace(charSequence.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }

    public static boolean isNotBlank(CharSequence charSequence) {
        return !isBlank(charSequence);
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static String trimToNull(String str) {
        String trim = trim(str);
        return isEmpty(trim) ? null : trim;
    }

    public static String trimToEmpty(String str) {
        return str == null ? EMPTY : str.trim();
    }

    public static String strip(String str) {
        return strip(str, null);
    }

    public static String stripToNull(String str) {
        if (str == null) {
            return null;
        }
        String strip = strip(str, null);
        if (strip.length() != 0) {
            return strip;
        }
        return null;
    }

    public static String stripToEmpty(String str) {
        return str == null ? EMPTY : strip(str, null);
    }

    public static String strip(String str, String str2) {
        return isEmpty(str) ? str : stripEnd(stripStart(str, str2), str2);
    }

    public static String stripStart(String str, String str2) {
        if (str == null) {
            return str;
        }
        int length = str.length();
        if (length == 0) {
            return str;
        }
        int i = 0;
        if (str2 == null) {
            while (i != length && Character.isWhitespace(str.charAt(i))) {
                i++;
            }
        } else if (str2.length() == 0) {
            return str;
        } else {
            while (i != length && str2.indexOf(str.charAt(i)) != INDEX_NOT_FOUND) {
                i++;
            }
        }
        return str.substring(i);
    }

    public static String stripEnd(String str, String str2) {
        if (str == null) {
            return str;
        }
        int length = str.length();
        if (length == 0) {
            return str;
        }
        if (str2 == null) {
            while (length != 0 && Character.isWhitespace(str.charAt(length + INDEX_NOT_FOUND))) {
                length += INDEX_NOT_FOUND;
            }
        } else if (str2.length() == 0) {
            return str;
        } else {
            while (length != 0 && str2.indexOf(str.charAt(length + INDEX_NOT_FOUND)) != INDEX_NOT_FOUND) {
                length += INDEX_NOT_FOUND;
            }
        }
        return str.substring(0, length);
    }

    public static String[] stripAll(String... strArr) {
        return stripAll(strArr, null);
    }

    public static String[] stripAll(String[] strArr, String str) {
        if (strArr == null) {
            return strArr;
        }
        int length = strArr.length;
        if (length == 0) {
            return strArr;
        }
        String[] strArr2 = new String[length];
        for (int i = 0; i < length; i++) {
            strArr2[i] = strip(strArr[i], str);
        }
        return strArr2;
    }

    public static String stripAccents(String str) {
        if (str == null) {
            return null;
        }
        try {
            if (InitStripAccents.access$000() != null) {
                return removeAccentsJava6(str);
            }
            if (InitStripAccents.access$100() != null) {
                return removeAccentsSUN(str);
            }
            throw new UnsupportedOperationException("The stripAccents(CharSequence) method requires at least Java6, but got: " + InitStripAccents.access$200() + "; or a Sun JVM: " + InitStripAccents.access$300());
        } catch (Throwable e) {
            throw new RuntimeException("IllegalArgumentException occurred", e);
        } catch (Throwable e2) {
            throw new RuntimeException("IllegalAccessException occurred", e2);
        } catch (Throwable e22) {
            throw new RuntimeException("InvocationTargetException occurred", e22);
        } catch (Throwable e222) {
            throw new RuntimeException("SecurityException occurred", e222);
        }
    }

    private static String removeAccentsJava6(CharSequence charSequence) {
        if (InitStripAccents.access$000() == null || InitStripAccents.access$400() == null) {
            throw new IllegalStateException("java.text.Normalizer is not available", InitStripAccents.access$200());
        }
        return InitStripAccents.access$500().matcher((String) InitStripAccents.access$000().invoke(null, new Object[]{charSequence, InitStripAccents.access$400()})).replaceAll(EMPTY);
    }

    private static String removeAccentsSUN(CharSequence charSequence) {
        if (InitStripAccents.access$100() == null) {
            throw new IllegalStateException("sun.text.Normalizer is not available", InitStripAccents.access$300());
        }
        return InitStripAccents.access$600().matcher((String) InitStripAccents.access$100().invoke(null, new Object[]{charSequence, Boolean.FALSE, Integer.valueOf(0)})).replaceAll(EMPTY);
    }

    public static boolean equals(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null) {
            return charSequence2 == null;
        } else {
            return charSequence.equals(charSequence2);
        }
    }

    public static boolean equalsIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence != null && charSequence2 != null) {
            return CharSequenceUtils.regionMatches(charSequence, true, 0, charSequence2, 0, Math.max(charSequence.length(), charSequence2.length()));
        } else if (charSequence == charSequence2) {
            return true;
        } else {
            return false;
        }
    }

    public static int indexOf(CharSequence charSequence, int i) {
        if (isEmpty(charSequence)) {
            return INDEX_NOT_FOUND;
        }
        return CharSequenceUtils.indexOf(charSequence, i, 0);
    }

    public static int indexOf(CharSequence charSequence, int i, int i2) {
        if (isEmpty(charSequence)) {
            return INDEX_NOT_FOUND;
        }
        return CharSequenceUtils.indexOf(charSequence, i, i2);
    }

    public static int indexOf(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            return INDEX_NOT_FOUND;
        }
        return CharSequenceUtils.indexOf(charSequence, charSequence2, 0);
    }

    public static int indexOf(CharSequence charSequence, CharSequence charSequence2, int i) {
        if (charSequence == null || charSequence2 == null) {
            return INDEX_NOT_FOUND;
        }
        return CharSequenceUtils.indexOf(charSequence, charSequence2, i);
    }

    public static int ordinalIndexOf(CharSequence charSequence, CharSequence charSequence2, int i) {
        return ordinalIndexOf(charSequence, charSequence2, i, false);
    }

    private static int ordinalIndexOf(CharSequence charSequence, CharSequence charSequence2, int i, boolean z) {
        int i2 = INDEX_NOT_FOUND;
        if (charSequence == null || charSequence2 == null || i <= 0) {
            return INDEX_NOT_FOUND;
        }
        if (charSequence2.length() != 0) {
            if (z) {
                i2 = charSequence.length();
            }
            int i3 = 0;
            while (true) {
                int lastIndexOf;
                if (z) {
                    lastIndexOf = CharSequenceUtils.lastIndexOf(charSequence, charSequence2, i2 + INDEX_NOT_FOUND);
                } else {
                    lastIndexOf = CharSequenceUtils.indexOf(charSequence, charSequence2, i2 + 1);
                }
                if (lastIndexOf < 0) {
                    return lastIndexOf;
                }
                i2 = i3 + 1;
                if (i2 >= i) {
                    return lastIndexOf;
                }
                i3 = i2;
                i2 = lastIndexOf;
            }
        } else if (z) {
            return charSequence.length();
        } else {
            return 0;
        }
    }

    public static int indexOfIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        return indexOfIgnoreCase(charSequence, charSequence2, 0);
    }

    public static int indexOfIgnoreCase(CharSequence charSequence, CharSequence charSequence2, int i) {
        if (charSequence == null || charSequence2 == null) {
            return INDEX_NOT_FOUND;
        }
        if (i < 0) {
            i = 0;
        }
        int length = (charSequence.length() - charSequence2.length()) + 1;
        if (i > length) {
            return INDEX_NOT_FOUND;
        }
        if (charSequence2.length() == 0) {
            return i;
        }
        for (int i2 = i; i2 < length; i2++) {
            if (CharSequenceUtils.regionMatches(charSequence, true, i2, charSequence2, 0, charSequence2.length())) {
                return i2;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int lastIndexOf(CharSequence charSequence, int i) {
        if (isEmpty(charSequence)) {
            return INDEX_NOT_FOUND;
        }
        return CharSequenceUtils.lastIndexOf(charSequence, i, charSequence.length());
    }

    public static int lastIndexOf(CharSequence charSequence, int i, int i2) {
        if (isEmpty(charSequence)) {
            return INDEX_NOT_FOUND;
        }
        return CharSequenceUtils.lastIndexOf(charSequence, i, i2);
    }

    public static int lastIndexOf(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            return INDEX_NOT_FOUND;
        }
        return CharSequenceUtils.lastIndexOf(charSequence, charSequence2, charSequence.length());
    }

    public static int lastOrdinalIndexOf(CharSequence charSequence, CharSequence charSequence2, int i) {
        return ordinalIndexOf(charSequence, charSequence2, i, true);
    }

    public static int lastIndexOf(CharSequence charSequence, CharSequence charSequence2, int i) {
        if (charSequence == null || charSequence2 == null) {
            return INDEX_NOT_FOUND;
        }
        return CharSequenceUtils.lastIndexOf(charSequence, charSequence2, i);
    }

    public static int lastIndexOfIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            return INDEX_NOT_FOUND;
        }
        return lastIndexOfIgnoreCase(charSequence, charSequence2, charSequence.length());
    }

    public static int lastIndexOfIgnoreCase(CharSequence charSequence, CharSequence charSequence2, int i) {
        if (charSequence == null || charSequence2 == null) {
            return INDEX_NOT_FOUND;
        }
        int length;
        if (i > charSequence.length() - charSequence2.length()) {
            length = charSequence.length() - charSequence2.length();
        } else {
            length = i;
        }
        if (length < 0) {
            return INDEX_NOT_FOUND;
        }
        if (charSequence2.length() == 0) {
            return length;
        }
        while (length >= 0) {
            if (CharSequenceUtils.regionMatches(charSequence, true, length, charSequence2, 0, charSequence2.length())) {
                return length;
            }
            length += INDEX_NOT_FOUND;
        }
        return INDEX_NOT_FOUND;
    }

    public static boolean contains(CharSequence charSequence, int i) {
        if (!isEmpty(charSequence) && CharSequenceUtils.indexOf(charSequence, i, 0) >= 0) {
            return true;
        }
        return false;
    }

    public static boolean contains(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null || CharSequenceUtils.indexOf(charSequence, charSequence2, 0) < 0) {
            return false;
        }
        return true;
    }

    public static boolean containsIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            return false;
        }
        int length = charSequence2.length();
        int length2 = charSequence.length() - length;
        for (int i = 0; i <= length2; i++) {
            if (CharSequenceUtils.regionMatches(charSequence, true, i, charSequence2, 0, length)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsWhitespace(CharSequence charSequence) {
        if (isEmpty(charSequence)) {
            return false;
        }
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (Character.isWhitespace(charSequence.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static int indexOfAny(CharSequence charSequence, char... cArr) {
        if (isEmpty(charSequence) || ArrayUtils.isEmpty(cArr)) {
            return INDEX_NOT_FOUND;
        }
        int length = charSequence.length();
        int i = length + INDEX_NOT_FOUND;
        int length2 = cArr.length;
        int i2 = length2 + INDEX_NOT_FOUND;
        int i3 = 0;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            int i4 = 0;
            while (i4 < length2) {
                if (cArr[i4] == charAt && (i3 >= i || i4 >= i2 || !Character.isHighSurrogate(charAt) || cArr[i4 + 1] == charSequence.charAt(i3 + 1))) {
                    return i3;
                }
                i4++;
            }
            i3++;
        }
        return INDEX_NOT_FOUND;
    }

    public static int indexOfAny(CharSequence charSequence, String str) {
        if (isEmpty(charSequence) || isEmpty(str)) {
            return INDEX_NOT_FOUND;
        }
        return indexOfAny(charSequence, str.toCharArray());
    }

    public static boolean containsAny(CharSequence charSequence, char... cArr) {
        if (isEmpty(charSequence) || ArrayUtils.isEmpty(cArr)) {
            return false;
        }
        int length = charSequence.length();
        int length2 = cArr.length;
        int i = length + INDEX_NOT_FOUND;
        int i2 = length2 + INDEX_NOT_FOUND;
        int i3 = 0;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            int i4 = 0;
            while (i4 < length2) {
                if (cArr[i4] == charAt) {
                    if (!Character.isHighSurrogate(charAt) || i4 == i2) {
                        return true;
                    }
                    if (i3 < i && cArr[i4 + 1] == charSequence.charAt(i3 + 1)) {
                        return true;
                    }
                }
                i4++;
            }
            i3++;
        }
        return false;
    }

    public static boolean containsAny(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence2 == null) {
            return false;
        }
        return containsAny(charSequence, CharSequenceUtils.toCharArray(charSequence2));
    }

    public static int indexOfAnyBut(CharSequence charSequence, char... cArr) {
        if (isEmpty(charSequence) || ArrayUtils.isEmpty(cArr)) {
            return INDEX_NOT_FOUND;
        }
        int length = charSequence.length();
        int i = length + INDEX_NOT_FOUND;
        int length2 = cArr.length;
        int i2 = length2 + INDEX_NOT_FOUND;
        int i3 = 0;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            int i4 = 0;
            while (i4 < length2) {
                if (cArr[i4] != charAt || (i3 < i && i4 < i2 && Character.isHighSurrogate(charAt) && cArr[i4 + 1] != charSequence.charAt(i3 + 1))) {
                    i4++;
                } else {
                    i3++;
                }
            }
            return i3;
        }
        return INDEX_NOT_FOUND;
    }

    public static int indexOfAnyBut(CharSequence charSequence, CharSequence charSequence2) {
        if (isEmpty(charSequence) || isEmpty(charSequence2)) {
            return INDEX_NOT_FOUND;
        }
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            int i2;
            char charAt = charSequence.charAt(i);
            if (CharSequenceUtils.indexOf(charSequence2, charAt, 0) >= 0) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (i + 1 < length && Character.isHighSurrogate(charAt)) {
                charAt = charSequence.charAt(i + 1);
                if (i2 != 0 && CharSequenceUtils.indexOf(charSequence2, charAt, 0) < 0) {
                    return i;
                }
            } else if (i2 == 0) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static boolean containsOnly(CharSequence charSequence, char... cArr) {
        if (cArr == null || charSequence == null) {
            return false;
        }
        if (charSequence.length() == 0) {
            return true;
        }
        if (cArr.length == 0) {
            return false;
        }
        if (indexOfAnyBut(charSequence, cArr) != INDEX_NOT_FOUND) {
            return false;
        }
        return true;
    }

    public static boolean containsOnly(CharSequence charSequence, String str) {
        if (charSequence == null || str == null) {
            return false;
        }
        return containsOnly(charSequence, str.toCharArray());
    }

    public static boolean containsNone(CharSequence charSequence, char... cArr) {
        if (charSequence == null || cArr == null) {
            return true;
        }
        int length = charSequence.length();
        int i = length + INDEX_NOT_FOUND;
        int length2 = cArr.length;
        int i2 = length2 + INDEX_NOT_FOUND;
        int i3 = 0;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            int i4 = 0;
            while (i4 < length2) {
                if (cArr[i4] == charAt) {
                    if (!Character.isHighSurrogate(charAt) || i4 == i2) {
                        return false;
                    }
                    if (i3 < i && cArr[i4 + 1] == charSequence.charAt(i3 + 1)) {
                        return false;
                    }
                }
                i4++;
            }
            i3++;
        }
        return true;
    }

    public static boolean containsNone(CharSequence charSequence, String str) {
        if (charSequence == null || str == null) {
            return true;
        }
        return containsNone(charSequence, str.toCharArray());
    }

    public static int indexOfAny(CharSequence charSequence, CharSequence... charSequenceArr) {
        if (charSequence == null || charSequenceArr == null) {
            return INDEX_NOT_FOUND;
        }
        int i = MeasureConfiguration.DISABLED;
        for (CharSequence charSequence2 : charSequenceArr) {
            if (charSequence2 != null) {
                int indexOf = CharSequenceUtils.indexOf(charSequence, charSequence2, 0);
                if (indexOf != INDEX_NOT_FOUND && indexOf < i) {
                    i = indexOf;
                }
            }
        }
        return i == MeasureConfiguration.DISABLED ? INDEX_NOT_FOUND : i;
    }

    public static int lastIndexOfAny(CharSequence charSequence, CharSequence... charSequenceArr) {
        int i = INDEX_NOT_FOUND;
        if (!(charSequence == null || charSequenceArr == null)) {
            for (CharSequence charSequence2 : charSequenceArr) {
                if (charSequence2 != null) {
                    int lastIndexOf = CharSequenceUtils.lastIndexOf(charSequence, charSequence2, charSequence.length());
                    if (lastIndexOf > i) {
                        i = lastIndexOf;
                    }
                }
            }
        }
        return i;
    }

    public static String substring(String str, int i) {
        if (str == null) {
            return null;
        }
        int length;
        if (i < 0) {
            length = str.length() + i;
        } else {
            length = i;
        }
        if (length < 0) {
            length = 0;
        }
        if (length > str.length()) {
            return EMPTY;
        }
        return str.substring(length);
    }

    public static String substring(String str, int i, int i2) {
        int i3 = 0;
        if (str == null) {
            return null;
        }
        int length;
        if (i2 < 0) {
            length = str.length() + i2;
        } else {
            length = i2;
        }
        if (i < 0) {
            i += str.length();
        }
        if (length > str.length()) {
            length = str.length();
        }
        if (i > length) {
            return EMPTY;
        }
        if (i < 0) {
            i = 0;
        }
        if (length >= 0) {
            i3 = length;
        }
        return str.substring(i, i3);
    }

    public static String left(String str, int i) {
        if (str == null) {
            return null;
        }
        if (i < 0) {
            return EMPTY;
        }
        return str.length() > i ? str.substring(0, i) : str;
    }

    public static String right(String str, int i) {
        if (str == null) {
            return null;
        }
        if (i < 0) {
            return EMPTY;
        }
        return str.length() > i ? str.substring(str.length() - i) : str;
    }

    public static String mid(String str, int i, int i2) {
        if (str == null) {
            return null;
        }
        if (i2 < 0 || i > str.length()) {
            return EMPTY;
        }
        if (i < 0) {
            i = 0;
        }
        if (str.length() <= i + i2) {
            return str.substring(i);
        }
        return str.substring(i, i + i2);
    }

    public static String substringBefore(String str, String str2) {
        if (isEmpty(str) || str2 == null) {
            return str;
        }
        if (str2.length() == 0) {
            return EMPTY;
        }
        int indexOf = str.indexOf(str2);
        return indexOf != INDEX_NOT_FOUND ? str.substring(0, indexOf) : str;
    }

    public static String substringAfter(String str, String str2) {
        if (isEmpty(str)) {
            return str;
        }
        if (str2 == null) {
            return EMPTY;
        }
        int indexOf = str.indexOf(str2);
        if (indexOf == INDEX_NOT_FOUND) {
            return EMPTY;
        }
        return str.substring(indexOf + str2.length());
    }

    public static String substringBeforeLast(String str, String str2) {
        if (isEmpty(str) || isEmpty(str2)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(str2);
        return lastIndexOf != INDEX_NOT_FOUND ? str.substring(0, lastIndexOf) : str;
    }

    public static String substringAfterLast(String str, String str2) {
        if (isEmpty(str)) {
            return str;
        }
        if (isEmpty(str2)) {
            return EMPTY;
        }
        int lastIndexOf = str.lastIndexOf(str2);
        if (lastIndexOf == INDEX_NOT_FOUND || lastIndexOf == str.length() - str2.length()) {
            return EMPTY;
        }
        return str.substring(lastIndexOf + str2.length());
    }

    public static String substringBetween(String str, String str2) {
        return substringBetween(str, str2, str2);
    }

    public static String substringBetween(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            return null;
        }
        int indexOf = str.indexOf(str2);
        if (indexOf == INDEX_NOT_FOUND) {
            return null;
        }
        int indexOf2 = str.indexOf(str3, str2.length() + indexOf);
        if (indexOf2 != INDEX_NOT_FOUND) {
            return str.substring(str2.length() + indexOf, indexOf2);
        }
        return null;
    }

    public static String[] substringsBetween(String str, String str2, String str3) {
        if (str == null || isEmpty(str2) || isEmpty(str3)) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        int length2 = str3.length();
        int length3 = str2.length();
        List arrayList = new ArrayList();
        int i = 0;
        while (i < length - length2) {
            i = str.indexOf(str2, i);
            if (i >= 0) {
                i += length3;
                int indexOf = str.indexOf(str3, i);
                if (indexOf < 0) {
                    break;
                }
                arrayList.add(str.substring(i, indexOf));
                i = indexOf + length2;
            } else {
                break;
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String[] split(String str) {
        return split(str, null, INDEX_NOT_FOUND);
    }

    public static String[] split(String str, char c) {
        return splitWorker(str, c, false);
    }

    public static String[] split(String str, String str2) {
        return splitWorker(str, str2, INDEX_NOT_FOUND, false);
    }

    public static String[] split(String str, String str2, int i) {
        return splitWorker(str, str2, i, false);
    }

    public static String[] splitByWholeSeparator(String str, String str2) {
        return splitByWholeSeparatorWorker(str, str2, INDEX_NOT_FOUND, false);
    }

    public static String[] splitByWholeSeparator(String str, String str2, int i) {
        return splitByWholeSeparatorWorker(str, str2, i, false);
    }

    public static String[] splitByWholeSeparatorPreserveAllTokens(String str, String str2) {
        return splitByWholeSeparatorWorker(str, str2, INDEX_NOT_FOUND, true);
    }

    public static String[] splitByWholeSeparatorPreserveAllTokens(String str, String str2, int i) {
        return splitByWholeSeparatorWorker(str, str2, i, true);
    }

    private static String[] splitByWholeSeparatorWorker(String str, String str2, int i, boolean z) {
        int i2 = 0;
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        if (str2 == null || EMPTY.equals(str2)) {
            return splitWorker(str, null, i, z);
        }
        int length2 = str2.length();
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            i3 = str.indexOf(str2, i4);
            if (i3 <= INDEX_NOT_FOUND) {
                arrayList.add(str.substring(i4));
                i3 = length;
            } else if (i3 > i4) {
                int i5 = i2 + 1;
                if (i5 == i) {
                    arrayList.add(str.substring(i4));
                    i3 = length;
                    i2 = i5;
                } else {
                    arrayList.add(str.substring(i4, i3));
                    i4 = i3 + length2;
                    i2 = i5;
                }
            } else {
                if (z) {
                    i2++;
                    if (i2 == i) {
                        arrayList.add(str.substring(i4));
                        i3 = length;
                    } else {
                        arrayList.add(EMPTY);
                    }
                }
                i4 = i3 + length2;
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String[] splitPreserveAllTokens(String str) {
        return splitWorker(str, null, INDEX_NOT_FOUND, true);
    }

    public static String[] splitPreserveAllTokens(String str, char c) {
        return splitWorker(str, c, true);
    }

    private static String[] splitWorker(String str, char c, boolean z) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        List arrayList = new ArrayList();
        Object obj = null;
        Object obj2 = null;
        int i = 0;
        int i2 = 0;
        while (i2 < length) {
            if (str.charAt(i2) == c) {
                if (obj2 != null || z) {
                    arrayList.add(str.substring(i, i2));
                    obj = 1;
                    obj2 = null;
                }
                i = i2 + 1;
                i2 = i;
            } else {
                obj2 = 1;
                i2++;
                obj = null;
            }
        }
        if (obj2 != null || (z && r0 != null)) {
            arrayList.add(str.substring(i, i2));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String[] splitPreserveAllTokens(String str, String str2) {
        return splitWorker(str, str2, INDEX_NOT_FOUND, true);
    }

    public static String[] splitPreserveAllTokens(String str, String str2, int i) {
        return splitWorker(str, str2, i, true);
    }

    private static String[] splitWorker(String str, String str2, int i, boolean z) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        int i2;
        int i3;
        int i4;
        List arrayList = new ArrayList();
        int i5;
        int i6;
        int i7;
        int i8;
        if (str2 == null) {
            i5 = 0;
            i2 = 0;
            i6 = 0;
            i3 = 0;
            i7 = 1;
            while (i3 < length) {
                if (Character.isWhitespace(str.charAt(i3))) {
                    if (i2 != 0 || z) {
                        i2 = i7 + 1;
                        if (i7 == i) {
                            i3 = 0;
                            i5 = length;
                        } else {
                            i5 = i3;
                            i3 = 1;
                        }
                        arrayList.add(str.substring(i6, i5));
                        i7 = i2;
                        i2 = i5;
                        i5 = 0;
                    } else {
                        i8 = i5;
                        i5 = i2;
                        i2 = i3;
                        i3 = i8;
                    }
                    i6 = i2 + 1;
                    i2 = i5;
                    i5 = i3;
                    i3 = i6;
                } else {
                    i3++;
                    i5 = 0;
                    i2 = 1;
                }
            }
            i4 = i3;
            i3 = i6;
        } else if (str2.length() == 1) {
            char charAt = str2.charAt(0);
            i5 = 0;
            i2 = 0;
            i6 = 0;
            i3 = 0;
            i7 = 1;
            while (i3 < length) {
                if (str.charAt(i3) == charAt) {
                    if (i2 != 0 || z) {
                        i2 = i7 + 1;
                        if (i7 == i) {
                            i3 = 0;
                            i5 = length;
                        } else {
                            i5 = i3;
                            i3 = 1;
                        }
                        arrayList.add(str.substring(i6, i5));
                        i7 = i2;
                        i2 = i5;
                        i5 = 0;
                    } else {
                        i8 = i5;
                        i5 = i2;
                        i2 = i3;
                        i3 = i8;
                    }
                    i6 = i2 + 1;
                    i2 = i5;
                    i5 = i3;
                    i3 = i6;
                } else {
                    i3++;
                    i5 = 0;
                    i2 = 1;
                }
            }
            i4 = i3;
            i3 = i6;
        } else {
            i5 = 0;
            i2 = 0;
            i6 = 0;
            i3 = 0;
            i7 = 1;
            while (i3 < length) {
                if (str2.indexOf(str.charAt(i3)) >= 0) {
                    if (i2 != 0 || z) {
                        i2 = i7 + 1;
                        if (i7 == i) {
                            i3 = 0;
                            i5 = length;
                        } else {
                            i5 = i3;
                            i3 = 1;
                        }
                        arrayList.add(str.substring(i6, i5));
                        i7 = i2;
                        i2 = i5;
                        i5 = 0;
                    } else {
                        i8 = i5;
                        i5 = i2;
                        i2 = i3;
                        i3 = i8;
                    }
                    i6 = i2 + 1;
                    i2 = i5;
                    i5 = i3;
                    i3 = i6;
                } else {
                    i3++;
                    i5 = 0;
                    i2 = 1;
                }
            }
            i4 = i3;
            i3 = i6;
        }
        if (i2 != 0 || (z && r3 != 0)) {
            arrayList.add(str.substring(i3, i4));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String[] splitByCharacterType(String str) {
        return splitByCharacterType(str, false);
    }

    public static String[] splitByCharacterTypeCamelCase(String str) {
        return splitByCharacterType(str, true);
    }

    private static String[] splitByCharacterType(String str, boolean z) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        char[] toCharArray = str.toCharArray();
        List arrayList = new ArrayList();
        int i = 0;
        int type = Character.getType(toCharArray[0]);
        for (int i2 = 1; i2 < toCharArray.length; i2++) {
            int type2 = Character.getType(toCharArray[i2]);
            if (type2 != type) {
                if (z && type2 == 2 && type == 1) {
                    type = i2 + INDEX_NOT_FOUND;
                    if (type != i) {
                        arrayList.add(new String(toCharArray, i, type - i));
                        i = type;
                    }
                } else {
                    arrayList.add(new String(toCharArray, i, i2 - i));
                    i = i2;
                }
                type = type2;
            }
        }
        arrayList.add(new String(toCharArray, i, toCharArray.length - i));
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static <T> String join(T... tArr) {
        return join((Object[]) tArr, null);
    }

    public static String join(Object[] objArr, char c) {
        if (objArr == null) {
            return null;
        }
        return join(objArr, c, 0, objArr.length);
    }

    public static String join(Object[] objArr, char c, int i, int i2) {
        if (objArr == null) {
            return null;
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            return EMPTY;
        }
        StringBuilder stringBuilder = new StringBuilder(i3 * 16);
        for (i3 = i; i3 < i2; i3++) {
            if (i3 > i) {
                stringBuilder.append(c);
            }
            if (objArr[i3] != null) {
                stringBuilder.append(objArr[i3]);
            }
        }
        return stringBuilder.toString();
    }

    public static String join(Object[] objArr, String str) {
        if (objArr == null) {
            return null;
        }
        return join(objArr, str, 0, objArr.length);
    }

    public static String join(Object[] objArr, String str, int i, int i2) {
        if (objArr == null) {
            return null;
        }
        if (str == null) {
            str = EMPTY;
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            return EMPTY;
        }
        StringBuilder stringBuilder = new StringBuilder(i3 * 16);
        for (i3 = i; i3 < i2; i3++) {
            if (i3 > i) {
                stringBuilder.append(str);
            }
            if (objArr[i3] != null) {
                stringBuilder.append(objArr[i3]);
            }
        }
        return stringBuilder.toString();
    }

    public static String join(Iterator<?> it, char c) {
        if (it == null) {
            return null;
        }
        if (!it.hasNext()) {
            return EMPTY;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return ObjectUtils.toString(next);
        }
        StringBuilder stringBuilder = new StringBuilder(AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
        if (next != null) {
            stringBuilder.append(next);
        }
        while (it.hasNext()) {
            stringBuilder.append(c);
            next = it.next();
            if (next != null) {
                stringBuilder.append(next);
            }
        }
        return stringBuilder.toString();
    }

    public static String join(Iterator<?> it, String str) {
        if (it == null) {
            return null;
        }
        if (!it.hasNext()) {
            return EMPTY;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return ObjectUtils.toString(next);
        }
        StringBuilder stringBuilder = new StringBuilder(AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
        if (next != null) {
            stringBuilder.append(next);
        }
        while (it.hasNext()) {
            if (str != null) {
                stringBuilder.append(str);
            }
            next = it.next();
            if (next != null) {
                stringBuilder.append(next);
            }
        }
        return stringBuilder.toString();
    }

    public static String join(Iterable<?> iterable, char c) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), c);
    }

    public static String join(Iterable<?> iterable, String str) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), str);
    }

    public static String deleteWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }
        int length = str.length();
        char[] cArr = new char[length];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3;
            if (Character.isWhitespace(str.charAt(i))) {
                i3 = i2;
            } else {
                i3 = i2 + 1;
                cArr[i2] = str.charAt(i);
            }
            i++;
            i2 = i3;
        }
        return i2 != length ? new String(cArr, 0, i2) : str;
    }

    public static String removeStart(String str, String str2) {
        if (isEmpty(str) || isEmpty(str2) || !str.startsWith(str2)) {
            return str;
        }
        return str.substring(str2.length());
    }

    public static String removeStartIgnoreCase(String str, String str2) {
        if (isEmpty(str) || isEmpty(str2) || !startsWithIgnoreCase(str, str2)) {
            return str;
        }
        return str.substring(str2.length());
    }

    public static String removeEnd(String str, String str2) {
        if (isEmpty(str) || isEmpty(str2) || !str.endsWith(str2)) {
            return str;
        }
        return str.substring(0, str.length() - str2.length());
    }

    public static String removeEndIgnoreCase(String str, String str2) {
        if (isEmpty(str) || isEmpty(str2) || !endsWithIgnoreCase(str, str2)) {
            return str;
        }
        return str.substring(0, str.length() - str2.length());
    }

    public static String remove(String str, String str2) {
        return (isEmpty(str) || isEmpty(str2)) ? str : replace(str, str2, EMPTY, INDEX_NOT_FOUND);
    }

    public static String remove(String str, char c) {
        if (isEmpty(str) || str.indexOf(c) == INDEX_NOT_FOUND) {
            return str;
        }
        char[] toCharArray = str.toCharArray();
        int i = 0;
        for (int i2 = 0; i2 < toCharArray.length; i2++) {
            if (toCharArray[i2] != c) {
                int i3 = i + 1;
                toCharArray[i] = toCharArray[i2];
                i = i3;
            }
        }
        return new String(toCharArray, 0, i);
    }

    public static String replaceOnce(String str, String str2, String str3) {
        return replace(str, str2, str3, 1);
    }

    public static String replace(String str, String str2, String str3) {
        return replace(str, str2, str3, INDEX_NOT_FOUND);
    }

    public static String replace(String str, String str2, String str3, int i) {
        int i2 = 64;
        if (isEmpty(str) || isEmpty(str2) || str3 == null || i == 0) {
            return str;
        }
        int indexOf = str.indexOf(str2, 0);
        if (indexOf == INDEX_NOT_FOUND) {
            return str;
        }
        int length = str2.length();
        int length2 = str3.length() - length;
        if (length2 < 0) {
            length2 = 0;
        }
        if (i < 0) {
            i2 = 16;
        } else if (i <= 64) {
            i2 = i;
        }
        StringBuilder stringBuilder = new StringBuilder((i2 * length2) + str.length());
        i2 = 0;
        while (indexOf != INDEX_NOT_FOUND) {
            stringBuilder.append(str.substring(i2, indexOf)).append(str3);
            i2 = indexOf + length;
            i += INDEX_NOT_FOUND;
            if (i == 0) {
                break;
            }
            indexOf = str.indexOf(str2, i2);
        }
        stringBuilder.append(str.substring(i2));
        return stringBuilder.toString();
    }

    public static String replaceEach(String str, String[] strArr, String[] strArr2) {
        return replaceEach(str, strArr, strArr2, false, 0);
    }

    public static String replaceEachRepeatedly(String str, String[] strArr, String[] strArr2) {
        return replaceEach(str, strArr, strArr2, true, strArr == null ? 0 : strArr.length);
    }

    private static String replaceEach(String str, String[] strArr, String[] strArr2, boolean z, int i) {
        if (str == null || str.length() == 0 || strArr == null || strArr.length == 0 || strArr2 == null || strArr2.length == 0) {
            return str;
        }
        if (i < 0) {
            throw new IllegalStateException("Aborting to protect against StackOverflowError - output of one loop is the input of another");
        }
        int length = strArr.length;
        int length2 = strArr2.length;
        if (length != length2) {
            throw new IllegalArgumentException("Search and Replace array lengths don't match: " + length + " vs " + length2);
        }
        boolean[] zArr = new boolean[length];
        int i2 = 0;
        length2 = INDEX_NOT_FOUND;
        int i3 = INDEX_NOT_FOUND;
        while (i2 < length) {
            int indexOf;
            if (!(zArr[i2] || strArr[i2] == null || strArr[i2].length() == 0 || strArr2[i2] == null)) {
                indexOf = str.indexOf(strArr[i2]);
                if (indexOf == INDEX_NOT_FOUND) {
                    zArr[i2] = true;
                } else if (i3 == INDEX_NOT_FOUND || indexOf < i3) {
                    length2 = i2;
                    i3 = indexOf;
                }
            }
            i2++;
        }
        if (i3 == INDEX_NOT_FOUND) {
            return str;
        }
        i2 = 0;
        indexOf = 0;
        while (i2 < strArr.length) {
            int length3;
            if (!(strArr[i2] == null || strArr2[i2] == null)) {
                length3 = strArr2[i2].length() - strArr[i2].length();
                if (length3 > 0) {
                    indexOf += length3 * 3;
                }
            }
            i2++;
        }
        StringBuilder stringBuilder = new StringBuilder(Math.min(indexOf, str.length() / 5) + str.length());
        i2 = length2;
        length2 = 0;
        while (i3 != INDEX_NOT_FOUND) {
            while (length2 < i3) {
                stringBuilder.append(str.charAt(length2));
                length2++;
            }
            stringBuilder.append(strArr2[i2]);
            length3 = i3 + strArr[i2].length();
            i2 = 0;
            length2 = INDEX_NOT_FOUND;
            i3 = INDEX_NOT_FOUND;
            while (i2 < length) {
                if (!(zArr[i2] || strArr[i2] == null || strArr[i2].length() == 0 || strArr2[i2] == null)) {
                    indexOf = str.indexOf(strArr[i2], length3);
                    if (indexOf == INDEX_NOT_FOUND) {
                        zArr[i2] = true;
                    } else if (i3 == INDEX_NOT_FOUND || indexOf < i3) {
                        length2 = i2;
                        i3 = indexOf;
                    }
                }
                i2++;
            }
            i2 = length2;
            length2 = length3;
        }
        i2 = str.length();
        while (length2 < i2) {
            stringBuilder.append(str.charAt(length2));
            length2++;
        }
        str = stringBuilder.toString();
        return z ? replaceEach(str, strArr, strArr2, z, i + INDEX_NOT_FOUND) : str;
    }

    public static String replaceChars(String str, char c, char c2) {
        if (str == null) {
            return null;
        }
        return str.replace(c, c2);
    }

    public static String replaceChars(String str, String str2, String str3) {
        Object obj = null;
        if (isEmpty(str) || isEmpty(str2)) {
            return str;
        }
        if (str3 == null) {
            str3 = EMPTY;
        }
        int length = str3.length();
        int length2 = str.length();
        StringBuilder stringBuilder = new StringBuilder(length2);
        for (int i = 0; i < length2; i++) {
            char charAt = str.charAt(i);
            int indexOf = str2.indexOf(charAt);
            if (indexOf >= 0) {
                obj = 1;
                if (indexOf < length) {
                    stringBuilder.append(str3.charAt(indexOf));
                }
            } else {
                stringBuilder.append(charAt);
            }
        }
        if (obj != null) {
            return stringBuilder.toString();
        }
        return str;
    }

    public static String overlay(String str, String str2, int i, int i2) {
        if (str == null) {
            return null;
        }
        int i3;
        int i4;
        if (str2 == null) {
            str2 = EMPTY;
        }
        int length = str.length();
        if (i < 0) {
            i3 = 0;
        } else {
            i3 = i;
        }
        if (i3 > length) {
            i3 = length;
        }
        if (i2 < 0) {
            i4 = 0;
        } else {
            i4 = i2;
        }
        if (i4 > length) {
            i4 = length;
        }
        if (i3 <= i4) {
            int i5 = i4;
            i4 = i3;
            i3 = i5;
        }
        return new StringBuilder((((length + i4) - i3) + str2.length()) + 1).append(str.substring(0, i4)).append(str2).append(str.substring(i3)).toString();
    }

    public static String chomp(String str) {
        if (isEmpty(str)) {
            return str;
        }
        if (str.length() == 1) {
            char charAt = str.charAt(0);
            if (charAt == CharUtils.CR || charAt == '\n') {
                return EMPTY;
            }
            return str;
        }
        int length = str.length() + INDEX_NOT_FOUND;
        char charAt2 = str.charAt(length);
        if (charAt2 == '\n') {
            if (str.charAt(length + INDEX_NOT_FOUND) == CharUtils.CR) {
                length += INDEX_NOT_FOUND;
            }
        } else if (charAt2 != CharUtils.CR) {
            length++;
        }
        return str.substring(0, length);
    }

    @Deprecated
    public static String chomp(String str, String str2) {
        return removeEnd(str, str2);
    }

    public static String chop(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length < 2) {
            return EMPTY;
        }
        int i = length + INDEX_NOT_FOUND;
        String substring = str.substring(0, i);
        if (str.charAt(i) == '\n' && substring.charAt(i + INDEX_NOT_FOUND) == CharUtils.CR) {
            return substring.substring(0, i + INDEX_NOT_FOUND);
        }
        return substring;
    }

    public static String repeat(String str, int i) {
        int i2 = 0;
        if (str == null) {
            return null;
        }
        if (i <= 0) {
            return EMPTY;
        }
        int length = str.length();
        if (i == 1 || length == 0) {
            return str;
        }
        if (length == 1 && i <= PAD_LIMIT) {
            return repeat(str.charAt(0), i);
        }
        int i3 = length * i;
        switch (length) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return repeat(str.charAt(0), i);
            case Task.NETWORK_STATE_ANY /*2*/:
                char charAt = str.charAt(0);
                char charAt2 = str.charAt(1);
                char[] cArr = new char[i3];
                for (i2 = (i * 2) - 2; i2 >= 0; i2 = (i2 + INDEX_NOT_FOUND) + INDEX_NOT_FOUND) {
                    cArr[i2] = charAt;
                    cArr[i2 + 1] = charAt2;
                }
                return new String(cArr);
            default:
                StringBuilder stringBuilder = new StringBuilder(i3);
                while (i2 < i) {
                    stringBuilder.append(str);
                    i2++;
                }
                return stringBuilder.toString();
        }
    }

    public static String repeat(String str, String str2, int i) {
        if (str == null || str2 == null) {
            return repeat(str, i);
        }
        return removeEnd(repeat(str + str2, i), str2);
    }

    public static String repeat(char c, int i) {
        char[] cArr = new char[i];
        for (int i2 = i + INDEX_NOT_FOUND; i2 >= 0; i2 += INDEX_NOT_FOUND) {
            cArr[i2] = c;
        }
        return new String(cArr);
    }

    public static String rightPad(String str, int i) {
        return rightPad(str, i, ' ');
    }

    public static String rightPad(String str, int i, char c) {
        if (str == null) {
            return null;
        }
        int length = i - str.length();
        if (length <= 0) {
            return str;
        }
        if (length > PAD_LIMIT) {
            return rightPad(str, i, String.valueOf(c));
        }
        return str.concat(repeat(c, length));
    }

    public static String rightPad(String str, int i, String str2) {
        int i2 = 0;
        if (str == null) {
            return null;
        }
        if (isEmpty(str2)) {
            str2 = " ";
        }
        int length = str2.length();
        int length2 = i - str.length();
        if (length2 <= 0) {
            return str;
        }
        if (length == 1 && length2 <= PAD_LIMIT) {
            return rightPad(str, i, str2.charAt(0));
        }
        if (length2 == length) {
            return str.concat(str2);
        }
        if (length2 < length) {
            return str.concat(str2.substring(0, length2));
        }
        char[] cArr = new char[length2];
        char[] toCharArray = str2.toCharArray();
        while (i2 < length2) {
            cArr[i2] = toCharArray[i2 % length];
            i2++;
        }
        return str.concat(new String(cArr));
    }

    public static String leftPad(String str, int i) {
        return leftPad(str, i, ' ');
    }

    public static String leftPad(String str, int i, char c) {
        if (str == null) {
            return null;
        }
        int length = i - str.length();
        if (length <= 0) {
            return str;
        }
        if (length > PAD_LIMIT) {
            return leftPad(str, i, String.valueOf(c));
        }
        return repeat(c, length).concat(str);
    }

    public static String leftPad(String str, int i, String str2) {
        int i2 = 0;
        if (str == null) {
            return null;
        }
        if (isEmpty(str2)) {
            str2 = " ";
        }
        int length = str2.length();
        int length2 = i - str.length();
        if (length2 <= 0) {
            return str;
        }
        if (length == 1 && length2 <= PAD_LIMIT) {
            return leftPad(str, i, str2.charAt(0));
        }
        if (length2 == length) {
            return str2.concat(str);
        }
        if (length2 < length) {
            return str2.substring(0, length2).concat(str);
        }
        char[] cArr = new char[length2];
        char[] toCharArray = str2.toCharArray();
        while (i2 < length2) {
            cArr[i2] = toCharArray[i2 % length];
            i2++;
        }
        return new String(cArr).concat(str);
    }

    public static int length(CharSequence charSequence) {
        return charSequence == null ? 0 : charSequence.length();
    }

    public static String center(String str, int i) {
        return center(str, i, ' ');
    }

    public static String center(String str, int i, char c) {
        if (str == null || i <= 0) {
            return str;
        }
        int length = str.length();
        int i2 = i - length;
        if (i2 > 0) {
            return rightPad(leftPad(str, length + (i2 / 2), c), i, c);
        }
        return str;
    }

    public static String center(String str, int i, String str2) {
        if (str == null || i <= 0) {
            return str;
        }
        if (isEmpty(str2)) {
            str2 = " ";
        }
        int length = str.length();
        int i2 = i - length;
        if (i2 > 0) {
            return rightPad(leftPad(str, length + (i2 / 2), str2), i, str2);
        }
        return str;
    }

    public static String upperCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    public static String upperCase(String str, Locale locale) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase(locale);
    }

    public static String lowerCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase();
    }

    public static String lowerCase(String str, Locale locale) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase(locale);
    }

    public static String capitalize(String str) {
        if (str == null) {
            return str;
        }
        int length = str.length();
        return length == 0 ? str : Character.toTitleCase(str.charAt(0)) + str.substring(1);
    }

    public static String uncapitalize(String str) {
        if (str == null) {
            return str;
        }
        int length = str.length();
        return length == 0 ? str : Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    public static String swapCase(String str) {
        if (isEmpty(str)) {
            return str;
        }
        char[] toCharArray = str.toCharArray();
        for (int i = 0; i < toCharArray.length; i++) {
            char c = toCharArray[i];
            if (Character.isUpperCase(c)) {
                toCharArray[i] = Character.toLowerCase(c);
            } else if (Character.isTitleCase(c)) {
                toCharArray[i] = Character.toLowerCase(c);
            } else if (Character.isLowerCase(c)) {
                toCharArray[i] = Character.toUpperCase(c);
            }
        }
        return new String(toCharArray);
    }

    public static int countMatches(CharSequence charSequence, CharSequence charSequence2) {
        int i = 0;
        if (isEmpty(charSequence) || isEmpty(charSequence2)) {
            return 0;
        }
        int i2 = 0;
        while (true) {
            i = CharSequenceUtils.indexOf(charSequence, charSequence2, i);
            if (i == INDEX_NOT_FOUND) {
                return i2;
            }
            i2++;
            i += charSequence2.length();
        }
    }

    public static boolean isAlpha(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() == 0) {
            return false;
        }
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isLetter(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlphaSpace(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            if (!Character.isLetter(charSequence.charAt(i)) && charSequence.charAt(i) != ' ') {
                return false;
            }
            i++;
        }
        return true;
    }

    public static boolean isAlphanumeric(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() == 0) {
            return false;
        }
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isLetterOrDigit(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlphanumericSpace(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            if (!Character.isLetterOrDigit(charSequence.charAt(i)) && charSequence.charAt(i) != ' ') {
                return false;
            }
            i++;
        }
        return true;
    }

    public static boolean isAsciiPrintable(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!CharUtils.isAsciiPrintable(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumeric(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() == 0) {
            return false;
        }
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isDigit(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumericSpace(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            if (!Character.isDigit(charSequence.charAt(i)) && charSequence.charAt(i) != ' ') {
                return false;
            }
            i++;
        }
        return true;
    }

    public static boolean isWhitespace(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAllLowerCase(CharSequence charSequence) {
        if (charSequence == null || isEmpty(charSequence)) {
            return false;
        }
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isLowerCase(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAllUpperCase(CharSequence charSequence) {
        if (charSequence == null || isEmpty(charSequence)) {
            return false;
        }
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isUpperCase(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String defaultString(String str) {
        return str == null ? EMPTY : str;
    }

    public static String defaultString(String str, String str2) {
        return str == null ? str2 : str;
    }

    public static <T extends CharSequence> T defaultIfBlank(T t, T t2) {
        return isBlank(t) ? t2 : t;
    }

    public static <T extends CharSequence> T defaultIfEmpty(T t, T t2) {
        return isEmpty(t) ? t2 : t;
    }

    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }

    public static String reverseDelimited(String str, char c) {
        if (str == null) {
            return null;
        }
        Object[] split = split(str, c);
        ArrayUtils.reverse(split);
        return join(split, c);
    }

    public static String abbreviate(String str, int i) {
        return abbreviate(str, 0, i);
    }

    public static String abbreviate(String str, int i, int i2) {
        if (str == null) {
            return null;
        }
        if (i2 < 4) {
            throw new IllegalArgumentException("Minimum abbreviation width is 4");
        } else if (str.length() <= i2) {
            return str;
        } else {
            if (i > str.length()) {
                i = str.length();
            }
            if (str.length() - i < i2 - 3) {
                i = str.length() - (i2 - 3);
            }
            String str2 = "...";
            if (i <= 4) {
                return str.substring(0, i2 - 3) + "...";
            }
            if (i2 < 7) {
                throw new IllegalArgumentException("Minimum abbreviation width with offset is 7");
            } else if ((i + i2) - 3 < str.length()) {
                return "..." + abbreviate(str.substring(i), i2 - 3);
            } else {
                return "..." + str.substring(str.length() - (i2 - 3));
            }
        }
    }

    public static String abbreviateMiddle(String str, String str2, int i) {
        if (isEmpty(str) || isEmpty(str2) || i >= str.length() || i < str2.length() + 2) {
            return str;
        }
        int length = i - str2.length();
        int i2 = (length / 2) + (length % 2);
        length = str.length() - (length / 2);
        StringBuilder stringBuilder = new StringBuilder(i);
        stringBuilder.append(str.substring(0, i2));
        stringBuilder.append(str2);
        stringBuilder.append(str.substring(length));
        return stringBuilder.toString();
    }

    public static String difference(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        int indexOfDifference = indexOfDifference(str, str2);
        if (indexOfDifference == INDEX_NOT_FOUND) {
            return EMPTY;
        }
        return str2.substring(indexOfDifference);
    }

    public static int indexOfDifference(CharSequence charSequence, CharSequence charSequence2) {
        int i = 0;
        if (charSequence == charSequence2) {
            return INDEX_NOT_FOUND;
        }
        if (charSequence == null || charSequence2 == null) {
            return 0;
        }
        while (i < charSequence.length() && i < charSequence2.length() && charSequence.charAt(i) == charSequence2.charAt(i)) {
            i++;
        }
        if (i < charSequence2.length() || i < charSequence.length()) {
            return i;
        }
        return INDEX_NOT_FOUND;
    }

    public static int indexOfDifference(CharSequence... charSequenceArr) {
        if (charSequenceArr == null || charSequenceArr.length <= 1) {
            return INDEX_NOT_FOUND;
        }
        int length = charSequenceArr.length;
        int i = MeasureConfiguration.DISABLED;
        int i2 = 0;
        int i3 = 0;
        int i4 = 1;
        int i5 = 0;
        while (i2 < length) {
            if (charSequenceArr[i2] == null) {
                i5 = i4;
                i = 1;
                i4 = 0;
            } else {
                i4 = Math.min(charSequenceArr[i2].length(), i);
                i3 = Math.max(charSequenceArr[i2].length(), i3);
                i = i5;
                i5 = 0;
            }
            i2++;
            int i6 = i4;
            i4 = i5;
            i5 = i;
            i = i6;
        }
        if (i4 != 0 || (i3 == 0 && i5 == 0)) {
            return INDEX_NOT_FOUND;
        }
        if (i == 0) {
            return 0;
        }
        int i7;
        i4 = INDEX_NOT_FOUND;
        for (i5 = 0; i5 < i; i5++) {
            char charAt = charSequenceArr[0].charAt(i5);
            for (i2 = 1; i2 < length; i2++) {
                if (charSequenceArr[i2].charAt(i5) != charAt) {
                    i4 = i5;
                    break;
                }
            }
            if (i4 != INDEX_NOT_FOUND) {
                i7 = i4;
                break;
            }
        }
        i7 = i4;
        if (i7 != INDEX_NOT_FOUND || i == i3) {
            return i7;
        }
        return i;
    }

    public static String getCommonPrefix(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return EMPTY;
        }
        int indexOfDifference = indexOfDifference(strArr);
        if (indexOfDifference == INDEX_NOT_FOUND) {
            if (strArr[0] == null) {
                return EMPTY;
            }
            return strArr[0];
        } else if (indexOfDifference == 0) {
            return EMPTY;
        } else {
            return strArr[0].substring(0, indexOfDifference);
        }
    }

    public static int getLevenshteinDistance(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }
        int length = charSequence.length();
        int length2 = charSequence2.length();
        if (length == 0) {
            return length2;
        }
        if (length2 == 0) {
            return length;
        }
        int i;
        if (length > length2) {
            length = charSequence.length();
        } else {
            int i2 = length2;
            length2 = length;
            length = i2;
            CharSequence charSequence3 = charSequence2;
            charSequence2 = charSequence;
            charSequence = charSequence3;
        }
        int[] iArr = new int[(length2 + 1)];
        int[] iArr2 = new int[(length2 + 1)];
        for (i = 0; i <= length2; i++) {
            iArr[i] = i;
        }
        int i3 = 1;
        int[] iArr3 = iArr2;
        while (i3 <= length) {
            char charAt = charSequence.charAt(i3 + INDEX_NOT_FOUND);
            iArr3[0] = i3;
            for (i = 1; i <= length2; i++) {
                int i4;
                if (charSequence2.charAt(i + INDEX_NOT_FOUND) == charAt) {
                    i4 = 0;
                } else {
                    i4 = 1;
                }
                iArr3[i] = Math.min(Math.min(iArr3[i + INDEX_NOT_FOUND] + 1, iArr[i] + 1), i4 + iArr[i + INDEX_NOT_FOUND]);
            }
            i3++;
            int[] iArr4 = iArr;
            iArr = iArr3;
            iArr3 = iArr4;
        }
        return iArr[length2];
    }

    public static int getLevenshteinDistance(CharSequence charSequence, CharSequence charSequence2, int i) {
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("Strings must not be null");
        } else if (i < 0) {
            throw new IllegalArgumentException("Threshold must not be negative");
        } else {
            int length = charSequence.length();
            int length2 = charSequence2.length();
            if (length == 0) {
                if (length2 <= i) {
                    return length2;
                }
                return INDEX_NOT_FOUND;
            } else if (length2 == 0) {
                return length <= i ? length : INDEX_NOT_FOUND;
            } else {
                int i2;
                if (length > length2) {
                    length = length2;
                    length2 = charSequence.length();
                } else {
                    CharSequence charSequence3 = charSequence2;
                    charSequence2 = charSequence;
                    charSequence = charSequence3;
                }
                int[] iArr = new int[(length + 1)];
                int[] iArr2 = new int[(length + 1)];
                int min = Math.min(length, i) + 1;
                for (i2 = 0; i2 < min; i2++) {
                    iArr[i2] = i2;
                }
                Arrays.fill(iArr, min, iArr.length, MeasureConfiguration.DISABLED);
                Arrays.fill(iArr2, MeasureConfiguration.DISABLED);
                int[] iArr3 = iArr2;
                int i3 = 1;
                while (i3 <= length2) {
                    char charAt = charSequence.charAt(i3 + INDEX_NOT_FOUND);
                    iArr3[0] = i3;
                    i2 = Math.max(1, i3 - i);
                    int min2 = Math.min(length, i3 + i);
                    if (i2 > min2) {
                        return INDEX_NOT_FOUND;
                    }
                    if (i2 > 1) {
                        iArr3[i2 + INDEX_NOT_FOUND] = MeasureConfiguration.DISABLED;
                    }
                    while (i2 <= min2) {
                        if (charSequence2.charAt(i2 + INDEX_NOT_FOUND) == charAt) {
                            iArr3[i2] = iArr[i2 + INDEX_NOT_FOUND];
                        } else {
                            iArr3[i2] = Math.min(Math.min(iArr3[i2 + INDEX_NOT_FOUND], iArr[i2]), iArr[i2 + INDEX_NOT_FOUND]) + 1;
                        }
                        i2++;
                    }
                    i3++;
                    int[] iArr4 = iArr;
                    iArr = iArr3;
                    iArr3 = iArr4;
                }
                if (iArr[length] <= i) {
                    return iArr[length];
                }
                return INDEX_NOT_FOUND;
            }
        }
    }

    public static boolean startsWith(CharSequence charSequence, CharSequence charSequence2) {
        return startsWith(charSequence, charSequence2, false);
    }

    public static boolean startsWithIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        return startsWith(charSequence, charSequence2, true);
    }

    private static boolean startsWith(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        if (charSequence == null || charSequence2 == null) {
            if (charSequence == null && charSequence2 == null) {
                return true;
            }
            return false;
        } else if (charSequence2.length() > charSequence.length()) {
            return false;
        } else {
            return CharSequenceUtils.regionMatches(charSequence, z, 0, charSequence2, 0, charSequence2.length());
        }
    }

    public static boolean startsWithAny(CharSequence charSequence, CharSequence... charSequenceArr) {
        if (isEmpty(charSequence) || ArrayUtils.isEmpty(charSequenceArr)) {
            return false;
        }
        for (CharSequence startsWith : charSequenceArr) {
            if (startsWith(charSequence, startsWith)) {
                return true;
            }
        }
        return false;
    }

    public static boolean endsWith(CharSequence charSequence, CharSequence charSequence2) {
        return endsWith(charSequence, charSequence2, false);
    }

    public static boolean endsWithIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        return endsWith(charSequence, charSequence2, true);
    }

    private static boolean endsWith(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        if (charSequence == null || charSequence2 == null) {
            if (charSequence == null && charSequence2 == null) {
                return true;
            }
            return false;
        } else if (charSequence2.length() > charSequence.length()) {
            return false;
        } else {
            return CharSequenceUtils.regionMatches(charSequence, z, charSequence.length() - charSequence2.length(), charSequence2, 0, charSequence2.length());
        }
    }

    public static String normalizeSpace(String str) {
        if (str == null) {
            return null;
        }
        return WHITESPACE_BLOCK.matcher(trim(str)).replaceAll(" ");
    }

    public static boolean endsWithAny(CharSequence charSequence, CharSequence... charSequenceArr) {
        if (isEmpty(charSequence) || ArrayUtils.isEmpty(charSequenceArr)) {
            return false;
        }
        for (CharSequence endsWith : charSequenceArr) {
            if (endsWith(charSequence, endsWith)) {
                return true;
            }
        }
        return false;
    }

    public static String toString(byte[] bArr, String str) {
        return str == null ? new String(bArr) : new String(bArr, str);
    }
}
