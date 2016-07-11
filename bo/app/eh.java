package bo.app;

import android.net.Uri;
import com.appboy.Constants;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public abstract class eh implements ec {
    private static final String f343b;
    public final Uri f344a;
    private Map<String, String> f345c;

    static {
        f343b = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, eh.class.getName()});
    }

    public eh(Uri uri, Map<String, String> map) {
        this.f345c = map;
        this.f344a = Uri.parse(uri + m208g());
    }

    public Uri m209b() {
        return this.f344a;
    }

    private String m208g() {
        if (this.f345c == null || this.f345c.size() == 0) {
            return StringUtils.EMPTY;
        }
        String str = "?";
        for (String str2 : this.f345c.keySet()) {
            str = str + str2 + "=" + ((String) this.f345c.get(str2)) + "&";
        }
        return str.substring(0, str.length() - 1);
    }
}
