package android.support.v4.text;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.ui.shop.ShopHomeAdapter;
import com.google.android.gms.gcm.Task;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

public final class TextUtilsCompat {
    private static String ARAB_SCRIPT_SUBTAG;
    private static String HEBR_SCRIPT_SUBTAG;
    private static final TextUtilsCompatImpl IMPL;
    public static final Locale ROOT;

    class TextUtilsCompatImpl {
        private TextUtilsCompatImpl() {
        }

        @NonNull
        public String htmlEncode(@NonNull String str) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                switch (charAt) {
                    case ShopHomeAdapter.TYPE_FAQ_CONTENT /*34*/:
                        stringBuilder.append("&quot;");
                        break;
                    case ShopHomeAdapter.TYPE_LOADING_WITH_DESCRIPTION /*38*/:
                        stringBuilder.append("&amp;");
                        break;
                    case ShopHomeAdapter.TYPE_SHOP_FAQ_SUBSECTION_HEADING /*39*/:
                        stringBuilder.append("&#39;");
                        break;
                    case R.AppCompatTheme_popupMenuStyle /*60*/:
                        stringBuilder.append("&lt;");
                        break;
                    case R.AppCompatTheme_editTextColor /*62*/:
                        stringBuilder.append("&gt;");
                        break;
                    default:
                        stringBuilder.append(charAt);
                        break;
                }
            }
            return stringBuilder.toString();
        }

        public int getLayoutDirectionFromLocale(@Nullable Locale locale) {
            if (!(locale == null || locale.equals(TextUtilsCompat.ROOT))) {
                String maximizeAndGetScript = ICUCompat.maximizeAndGetScript(locale);
                if (maximizeAndGetScript == null) {
                    return getLayoutDirectionFromFirstChar(locale);
                }
                if (maximizeAndGetScript.equalsIgnoreCase(TextUtilsCompat.ARAB_SCRIPT_SUBTAG) || maximizeAndGetScript.equalsIgnoreCase(TextUtilsCompat.HEBR_SCRIPT_SUBTAG)) {
                    return 1;
                }
            }
            return 0;
        }

        private static int getLayoutDirectionFromFirstChar(@NonNull Locale locale) {
            switch (Character.getDirectionality(locale.getDisplayName(locale).charAt(0))) {
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                case Task.NETWORK_STATE_ANY /*2*/:
                    return 1;
                default:
                    return 0;
            }
        }
    }

    class TextUtilsCompatJellybeanMr1Impl extends TextUtilsCompatImpl {
        private TextUtilsCompatJellybeanMr1Impl() {
            super();
        }

        @NonNull
        public String htmlEncode(@NonNull String str) {
            return TextUtilsCompatJellybeanMr1.htmlEncode(str);
        }

        public int getLayoutDirectionFromLocale(@Nullable Locale locale) {
            return TextUtilsCompatJellybeanMr1.getLayoutDirectionFromLocale(locale);
        }
    }

    static {
        if (VERSION.SDK_INT >= 17) {
            IMPL = new TextUtilsCompatJellybeanMr1Impl();
        } else {
            IMPL = new TextUtilsCompatImpl();
        }
        ROOT = new Locale(StringUtils.EMPTY, StringUtils.EMPTY);
        ARAB_SCRIPT_SUBTAG = "Arab";
        HEBR_SCRIPT_SUBTAG = "Hebr";
    }

    @NonNull
    public static String htmlEncode(@NonNull String str) {
        return IMPL.htmlEncode(str);
    }

    public static int getLayoutDirectionFromLocale(@Nullable Locale locale) {
        return IMPL.getLayoutDirectionFromLocale(locale);
    }

    private TextUtilsCompat() {
    }
}
