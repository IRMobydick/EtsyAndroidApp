package com.etsy.android.lib.requests;

import com.etsy.android.lib.core.EtsyJobBuilder;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.models.ShopSection;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopSectionsRequest extends EtsyRequest<ShopSection> {
    private static final long serialVersionUID = 1235035283711425946L;

    /* renamed from: com.etsy.android.lib.requests.ShopSectionsRequest.1 */
    final class C04981 implements EtsyJobResponse<ShopSection> {
        final /* synthetic */ CreateCallback f1925a;

        C04981(CreateCallback createCallback) {
            this.f1925a = createCallback;
        }

        public void m2982a(List<ShopSection> list, int i, EtsyResult<ShopSection> etsyResult) {
            if (this.f1925a != null) {
                this.f1925a.onSuccess(etsyResult);
            }
        }
    }

    /* renamed from: com.etsy.android.lib.requests.ShopSectionsRequest.2 */
    final class C04992 implements EtsyJobResponse {
        final /* synthetic */ CreateCallback f1926a;

        C04992(CreateCallback createCallback) {
            this.f1926a = createCallback;
        }

        public void m2983a(EtsyResult etsyResult) {
            if (this.f1926a != null) {
                this.f1926a.onError(null);
            }
        }
    }

    /* renamed from: com.etsy.android.lib.requests.ShopSectionsRequest.3 */
    final class C05003 implements EtsyJobResponse {
        final /* synthetic */ CreateCallback f1927a;

        C05003(CreateCallback createCallback) {
            this.f1927a = createCallback;
        }

        public void m2984a(int i, String str, EtsyResult etsyResult) {
            if (this.f1927a != null) {
                this.f1927a.onError(str);
            }
        }
    }

    public interface CreateCallback {
        void onError(String str);

        void onSuccess(EtsyResult<ShopSection> etsyResult);
    }

    public ShopSectionsRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, ShopSection.class);
    }

    public static ShopSectionsRequest getMyShopSections() {
        return new ShopSectionsRequest("/shops/__SELF__/sections", RequestMethod.GET);
    }

    public static ShopSectionsRequest createShopSection(String str) {
        ShopSectionsRequest shopSectionsRequest = new ShopSectionsRequest("/shops/__SELF__/sections", RequestMethod.POST);
        Map hashMap = new HashMap();
        hashMap.put(FindsModule.FIELD_TITLE, str);
        shopSectionsRequest.addParams(hashMap);
        return shopSectionsRequest;
    }

    public static void create(String str, CreateCallback createCallback) {
        aj.m1101a().m1123i().m1699a(EtsyJobBuilder.m1307a(createShopSection(str)).m1320a(new C05003(createCallback)).m1319a(new C04992(createCallback)).m1321a(new C04981(createCallback)).m1324a());
    }
}
