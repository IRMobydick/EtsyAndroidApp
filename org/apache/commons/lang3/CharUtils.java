package org.apache.commons.lang3;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;

public class CharUtils {
    private static final String[] CHAR_STRING_ARRAY;
    public static final char CR = '\r';
    public static final char LF = '\n';

    static {
        CHAR_STRING_ARRAY = new String[AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS];
        for (char c = '\u0000'; c < CHAR_STRING_ARRAY.length; c = (char) (c + 1)) {
            CHAR_STRING_ARRAY[c] = String.valueOf(c);
        }
    }

    @Deprecated
    public static Character toCharacterObject(char c) {
        return Character.valueOf(c);
    }

    public static Character toCharacterObject(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return Character.valueOf(str.charAt(0));
    }

    public static char toChar(Character ch) {
        if (ch != null) {
            return ch.charValue();
        }
        throw new IllegalArgumentException("The Character must not be null");
    }

    public static char toChar(Character ch, char c) {
        return ch == null ? c : ch.charValue();
    }

    public static char toChar(String str) {
        if (!StringUtils.isEmpty(str)) {
            return str.charAt(0);
        }
        throw new IllegalArgumentException("The String must not be empty");
    }

    public static char toChar(String str, char c) {
        return StringUtils.isEmpty(str) ? c : str.charAt(0);
    }

    public static int toIntValue(char c) {
        if (isAsciiNumeric(c)) {
            return c - 48;
        }
        throw new IllegalArgumentException("The character " + c + " is not in the range '0' - '9'");
    }

    public static int toIntValue(char c, int i) {
        return !isAsciiNumeric(c) ? i : c - 48;
    }

    public static int toIntValue(Character ch) {
        if (ch != null) {
            return toIntValue(ch.charValue());
        }
        throw new IllegalArgumentException("The character must not be null");
    }

    public static int toIntValue(Character ch, int i) {
        return ch == null ? i : toIntValue(ch.charValue(), i);
    }

    public static String toString(char c) {
        if (c < '\u0080') {
            return CHAR_STRING_ARRAY[c];
        }
        return new String(new char[]{c});
    }

    public static String toString(Character ch) {
        if (ch == null) {
            return null;
        }
        return toString(ch.charValue());
    }

    public static String unicodeEscaped(char c) {
        if (c < '\u0010') {
            return "\\u000" + Integer.toHexString(c);
        }
        if (c < '\u0100') {
            return "\\u00" + Integer.toHexString(c);
        }
        if (c < '\u1000') {
            return "\\u0" + Integer.toHexString(c);
        }
        return "\\u" + Integer.toHexString(c);
    }

    public static String unicodeEscaped(Character ch) {
        if (ch == null) {
            return null;
        }
        return unicodeEscaped(ch.charValue());
    }

    public static boolean isAscii(char c) {
        return c < '\u0080';
    }

    public static boolean isAsciiPrintable(char c) {
        return c >= ' ' && c < '\u007f';
    }

    public static boolean isAsciiControl(char c) {
        return c < ' ' || c == '\u007f';
    }

    public static boolean isAsciiAlpha(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    public static boolean isAsciiAlphaUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static boolean isAsciiAlphaLower(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static boolean isAsciiNumeric(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean isAsciiAlphanumeric(char c) {
        return (c >= 'A' && c <= 'Z') || ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'));
    }
}
