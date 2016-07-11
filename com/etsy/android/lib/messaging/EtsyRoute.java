package com.etsy.android.lib.messaging;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyDeepLinkId;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.ad;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.lib.messaging.c */
public class EtsyRoute {
    private static final Pattern f1887a;
    private static final Pattern f1888b;
    private EtsyEntity f1889c;
    private EtsyDeepLinkId f1890d;
    private EtsyAction f1891e;
    private EtsyId f1892f;
    private EtsyRoute f1893g;
    private HashMap<String, String> f1894h;

    /* renamed from: com.etsy.android.lib.messaging.c.1 */
    /* synthetic */ class EtsyRoute {
        static final /* synthetic */ int[] f1886a;

        static {
            f1886a = new int[EtsyEntity.values().length];
            try {
                f1886a[EtsyEntity.SHOP_POLICY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1886a[EtsyEntity.SHOP_ABOUT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1886a[EtsyEntity.SHOP_REVIEWS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    static {
        f1887a = Pattern.compile("your/purchases/[0-9]+/review/([0-9]+)");
        f1888b = Pattern.compile("transaction/([0-9]+)/buyer-photo");
    }

    public static EtsyRoute m2266a(Uri uri) {
        if (uri != null) {
            EtsyRoute etsyRoute = new EtsyRoute(uri);
            if (etsyRoute.m2275c() != null) {
                return etsyRoute;
            }
        }
        return null;
    }

    public static Uri m2264a(EtsyEntity etsyEntity, String str, EtsyAction etsyAction) {
        EtsyRoute etsyRoute = new EtsyRoute(etsyEntity, str, etsyAction);
        if (etsyRoute.m2275c() == null) {
            return null;
        }
        return etsyRoute.m2271a();
    }

    public EtsyRoute(EtsyEntity etsyEntity, String str, EtsyAction etsyAction) {
        this.f1890d = new EtsyDeepLinkId();
        this.f1892f = new EtsyId();
        this.f1894h = new HashMap();
        this.f1890d.checkIdTypeAndSet(str);
        this.f1889c = etsyEntity;
        this.f1891e = etsyAction;
    }

    private EtsyRoute(Uri uri) {
        this.f1890d = new EtsyDeepLinkId();
        this.f1892f = new EtsyId();
        this.f1894h = new HashMap();
        if (ad.m3190a(uri.getScheme())) {
            m2269b(uri);
        } else if (ad.m3191b(uri.getScheme())) {
            m2270c(uri);
        }
    }

    private void m2269b(Uri uri) {
        List pathSegments = uri.getPathSegments();
        this.f1889c = EtsyEntity.fromString(uri.getHost());
        if (pathSegments != null && pathSegments.size() > 0) {
            int a = m2263a(this.f1889c, pathSegments, 0);
            this.f1891e = EtsyAction.fromName(uri.getQueryParameter("app_action"));
            if (pathSegments.size() > a) {
                Builder builder = new Builder();
                builder.scheme("etsy").authority((String) pathSegments.get(a));
                for (a++; a < pathSegments.size(); a++) {
                    builder.appendPath((String) pathSegments.get(a));
                }
                builder.encodedQuery(uri.getEncodedQuery());
                this.f1893g = EtsyRoute.m2266a(builder.build());
            }
        }
        for (String str : uri.getQueryParameterNames()) {
            this.f1894h.put(str, uri.getQueryParameter(str));
        }
        if (this.f1893g != null && this.f1893g.m2275c() == EtsyEntity.LISTINGS_SIMILAR) {
            this.f1889c = EtsyEntity.LISTINGS_SIMILAR;
        }
    }

    private void m2270c(Uri uri) {
        if (ad.m3192c(uri.getHost())) {
            List pathSegments = uri.getPathSegments();
            if (pathSegments != null) {
                CharSequence join = StringUtils.join((Iterable) pathSegments, "/");
                Matcher matcher = f1887a.matcher(join);
                if (matcher.matches()) {
                    this.f1890d = new EtsyDeepLinkId(Long.parseLong(matcher.group(1)));
                    this.f1889c = EtsyEntity.COMPOSE_REVIEW;
                    return;
                }
                Matcher matcher2 = f1888b.matcher(join);
                if (matcher2.matches()) {
                    this.f1890d = new EtsyDeepLinkId(Long.parseLong(matcher2.group(1)));
                    this.f1889c = EtsyEntity.APPRECIATION_PHOTO_LANDING_PAGE;
                    return;
                }
                String str;
                if (pathSegments.size() > 0) {
                    int i;
                    EtsyEntity b;
                    EtsyEntity b2;
                    str = (String) pathSegments.get(0);
                    this.f1889c = EtsyEntity.fromString(str);
                    if (this.f1889c == null && ad.m3193d(str)) {
                        if (pathSegments.size() > 1) {
                            this.f1889c = EtsyEntity.fromString((String) pathSegments.get(1));
                            i = 2;
                            if (pathSegments.size() > i && this.f1889c != null) {
                                b = m2267b(this.f1889c, pathSegments, i);
                                if (b != null) {
                                    this.f1889c = b;
                                    i++;
                                }
                            }
                            if (pathSegments.size() > i) {
                                i = m2263a(this.f1889c, pathSegments, i);
                            }
                            if (pathSegments.size() > i && this.f1889c != null) {
                                b2 = m2267b(this.f1889c, pathSegments, i);
                                if (b2 != null) {
                                    this.f1889c = b2;
                                }
                            }
                            if (this.f1889c != null) {
                                b2 = m2265a(this.f1889c, uri);
                                if (b2 != null) {
                                    this.f1889c = b2;
                                }
                                str = m2268b(this.f1889c, uri);
                                if (str != null) {
                                    this.f1892f.setId(str);
                                }
                            }
                        } else {
                            this.f1889c = EtsyEntity.HOME;
                        }
                    }
                    i = 1;
                    b = m2267b(this.f1889c, pathSegments, i);
                    if (b != null) {
                        this.f1889c = b;
                        i++;
                    }
                    if (pathSegments.size() > i) {
                        i = m2263a(this.f1889c, pathSegments, i);
                    }
                    b2 = m2267b(this.f1889c, pathSegments, i);
                    if (b2 != null) {
                        this.f1889c = b2;
                    }
                    if (this.f1889c != null) {
                        b2 = m2265a(this.f1889c, uri);
                        if (b2 != null) {
                            this.f1889c = b2;
                        }
                        str = m2268b(this.f1889c, uri);
                        if (str != null) {
                            this.f1892f.setId(str);
                        }
                    }
                } else {
                    this.f1889c = EtsyEntity.HOME;
                }
                for (String str2 : uri.getQueryParameterNames()) {
                    this.f1894h.put(str2, uri.getQueryParameter(str2));
                }
            }
        }
    }

    private int m2263a(EtsyEntity etsyEntity, List<String> list, int i) {
        if (etsyEntity == EtsyEntity.BROWSE || etsyEntity == EtsyEntity.TAXONOMY_CATEGORY || etsyEntity == EtsyEntity.SEARCH) {
            StringBuilder stringBuilder = new StringBuilder();
            while (i < list.size()) {
                int i2 = i + 1;
                String str = (String) list.get(i);
                if (str.equals(ResponseConstants.HANDMADE) || str.equals(ResponseConstants.VINTAGE)) {
                    this.f1894h.put("marketplace", str);
                    i = i2;
                } else {
                    if (stringBuilder.length() != 0) {
                        stringBuilder.append(".");
                    }
                    stringBuilder.append(str);
                    i = i2;
                }
            }
            this.f1890d.setName(stringBuilder.toString());
            return i;
        }
        this.f1890d.checkIdTypeAndSet((String) list.get(i));
        return i + 1;
    }

    private EtsyEntity m2267b(EtsyEntity etsyEntity, List<String> list, int i) {
        EtsyEntity fromString = EtsyEntity.fromString((String) list.get(i));
        if (fromString != null) {
            if (etsyEntity == EtsyEntity.SHOP) {
                switch (EtsyRoute.f1886a[fromString.ordinal()]) {
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        return EtsyEntity.SHOP_POLICY;
                    case Task.NETWORK_STATE_ANY /*2*/:
                        return EtsyEntity.SHOP_ABOUT;
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        return EtsyEntity.SHOP_REVIEWS;
                    default:
                        return null;
                }
            } else if (etsyEntity == EtsyEntity.LOCAL && fromString == EtsyEntity.LOCAL_EVENT) {
                return EtsyEntity.LOCAL_EVENT;
            } else {
                if (etsyEntity == EtsyEntity.LOCAL && fromString == EtsyEntity.LOCAL_STORE) {
                    return EtsyEntity.LOCAL_STORE;
                }
                if (etsyEntity == EtsyEntity.LISTING && fromString == EtsyEntity.LISTINGS_SIMILAR) {
                    return EtsyEntity.LISTINGS_SIMILAR;
                }
                if (etsyEntity == EtsyEntity.YOUR && fromString == EtsyEntity.PURCHASES) {
                    return EtsyEntity.PURCHASES;
                }
            }
        }
        return null;
    }

    private EtsyEntity m2265a(EtsyEntity etsyEntity, Uri uri) {
        return null;
    }

    private String m2268b(EtsyEntity etsyEntity, Uri uri) {
        return null;
    }

    public Uri m2271a() {
        Builder builder = new Builder();
        builder.scheme("etsy").authority(this.f1889c.getName());
        if (this.f1889c != EtsyEntity.HOME && this.f1890d.hasId()) {
            if (this.f1890d.getIdAsLong() > 0) {
                builder.appendPath(this.f1890d.getId());
            } else if (this.f1889c.allowsStringIds() && !TextUtils.isEmpty(this.f1890d.getName())) {
                builder.appendPath(this.f1890d.getName());
            }
            EtsyRoute etsyRoute = this.f1893g;
            while (etsyRoute != null) {
                builder.appendPath(etsyRoute.m2275c().getName());
                if (!TextUtils.isEmpty(etsyRoute.m2276d())) {
                    builder.appendPath(etsyRoute.m2276d());
                }
                if (!(etsyRoute.m2274b() == null || etsyRoute.m2274b().getName() == null)) {
                    builder.appendQueryParameter("app_action", etsyRoute.m2274b().getName());
                }
                etsyRoute = etsyRoute.m2277e();
            }
            if (!(this.f1891e == null || this.f1891e.getName() == null)) {
                builder.appendQueryParameter("app_action", this.f1891e.getName());
            }
        }
        return builder.build();
    }

    public EtsyAction m2274b() {
        return this.f1891e;
    }

    public EtsyEntity m2275c() {
        return this.f1889c;
    }

    public String m2276d() {
        return this.f1890d.getId();
    }

    public EtsyRoute m2277e() {
        return this.f1893g;
    }

    public void m2273a(EtsyRoute etsyRoute) {
        this.f1893g = etsyRoute;
    }

    public String m2272a(String str) {
        return (String) this.f1894h.get(str);
    }

    public HashMap<String, String> m2278f() {
        return this.f1894h;
    }
}
