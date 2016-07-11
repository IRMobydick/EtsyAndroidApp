package com.etsy.android.lib.logger;

import android.os.Bundle;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

/* compiled from: ViewAnalyticsParameters */
public class ac {
    private static final ab f1744a;
    private static final ab f1745b;
    private static final ab f1746c;
    private static final ab f1747d;
    private static final HashMap<String, aa> f1748e;

    /* renamed from: com.etsy.android.lib.logger.ac.1 */
    final class ViewAnalyticsParameters implements ab {
        ViewAnalyticsParameters() {
        }

        public Object m1828a(String str, Bundle bundle) {
            return bundle.getString(str);
        }
    }

    /* renamed from: com.etsy.android.lib.logger.ac.2 */
    final class ViewAnalyticsParameters implements ab {
        ViewAnalyticsParameters() {
        }

        public Object m1829a(String str, Bundle bundle) {
            return Integer.valueOf(bundle.getInt(str));
        }
    }

    /* renamed from: com.etsy.android.lib.logger.ac.3 */
    final class ViewAnalyticsParameters implements ab {
        ViewAnalyticsParameters() {
        }

        public Object m1830a(String str, Bundle bundle) {
            EtsyId etsyId = (EtsyId) bundle.getSerializable(str);
            if (etsyId != null) {
                return etsyId.toString();
            }
            return null;
        }
    }

    /* renamed from: com.etsy.android.lib.logger.ac.4 */
    final class ViewAnalyticsParameters implements ab {
        ViewAnalyticsParameters() {
        }

        public Object m1831a(String str, Bundle bundle) {
            return Boolean.valueOf(bundle.getBoolean(str));
        }
    }

    static {
        f1744a = new ViewAnalyticsParameters();
        f1745b = new ViewAnalyticsParameters();
        f1746c = new ViewAnalyticsParameters();
        f1747d = new ViewAnalyticsParameters();
        f1748e = new ViewAnalyticsParameters$5();
    }

    public static aa m1832a(String str) {
        return (aa) f1748e.get(str);
    }
}
