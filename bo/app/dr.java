package bo.app;

import android.net.Uri;
import com.appboy.Constants;
import com.appboy.models.ResponseError;
import java.util.HashMap;
import java.util.Map;

public final class dr extends eh {
    private static final String f346b;

    static {
        f346b = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, dr.class.getName()});
    }

    public dr(String str, String str2) {
        Uri parse = Uri.parse(str);
        Map hashMap = new HashMap();
        hashMap.put("DT", "GM");
        hashMap.put("DI", str2);
        super(parse, hashMap);
    }

    public final ag m210a() {
        return ag.GET;
    }

    public final void m211a(bc bcVar) {
    }

    public final void m213b(bc bcVar) {
    }

    public final void m212a(bc bcVar, ResponseError responseError) {
    }
}
