package bo.app;

import android.support.v4.os.EnvironmentCompat;
import com.etsy.android.lib.models.ShopAboutVideo;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum ah {
    UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN),
    NONE(ShopAboutVideo.PROVIDER_STATUS_NONE),
    TWO_G("2g"),
    THREE_G("3g"),
    FOUR_G("4g"),
    WIFI("wifi");
    
    private static final Map<String, ah> f64g;
    private final String f66h;

    static {
        f64g = new HashMap();
        Iterator it = EnumSet.allOf(ah.class).iterator();
        while (it.hasNext()) {
            ah ahVar = (ah) it.next();
            f64g.put(ahVar.f66h, ahVar);
        }
    }

    private ah(String str) {
        this.f66h = str;
    }
}
