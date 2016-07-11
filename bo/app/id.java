package bo.app;

import com.adjust.sdk.Constants;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

public enum id {
    HTTP("http"),
    HTTPS(Constants.SCHEME),
    FILE(ResponseConstants.FILE),
    CONTENT("content"),
    ASSETS("assets"),
    DRAWABLE("drawable"),
    UNKNOWN(StringUtils.EMPTY);
    
    private String f765h;
    private String f766i;

    private id(String str) {
        this.f765h = str;
        this.f766i = str + "://";
    }

    public static id m526a(String str) {
        if (str != null) {
            for (id idVar : values()) {
                if (idVar.m527d(str)) {
                    return idVar;
                }
            }
        }
        return UNKNOWN;
    }

    private boolean m527d(String str) {
        return str.toLowerCase(Locale.US).startsWith(this.f766i);
    }

    public final String m528b(String str) {
        return this.f766i + str;
    }

    public final String m529c(String str) {
        if (m527d(str)) {
            return str.substring(this.f766i.length());
        }
        throw new IllegalArgumentException(String.format("URI [%1$s] doesn't have expected scheme [%2$s]", new Object[]{str, this.f765h}));
    }
}
