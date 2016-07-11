package com.etsy.android.lib.p002a;

import com.etsy.android.lib.core.EtsyJobBuilder;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyRequestQueue;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.Payment;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.apiv3.PaymentRequest;
import java.util.List;

/* renamed from: com.etsy.android.lib.a.a */
public class RefundHelper {
    private static final String f1135a;
    private final EtsyRequestQueue f1136b;
    private final EtsyId f1137c;
    private final EtsyId f1138d;
    private final APIv3Scope f1139e;
    private final RefundHelper f1140f;
    private Payment f1141g;

    /* renamed from: com.etsy.android.lib.a.a.1 */
    class RefundHelper implements EtsyJobResponse<Payment> {
        final /* synthetic */ RefundHelper f1132a;

        RefundHelper(RefundHelper refundHelper) {
            this.f1132a = refundHelper;
        }

        public void m789a(List<Payment> list, int i, EtsyResult<Payment> etsyResult) {
            this.f1132a.f1141g = (Payment) list.get(0);
            EtsyDebug.m1906b(RefundHelper.f1135a, "Fetched payment with id: " + this.f1132a.f1141g.getId());
            this.f1132a.f1140f.updateRefundStatus(this.f1132a.f1141g);
        }
    }

    /* renamed from: com.etsy.android.lib.a.a.2 */
    class RefundHelper implements EtsyJobResponse {
        final /* synthetic */ RefundHelper f1133a;

        RefundHelper(RefundHelper refundHelper) {
            this.f1133a = refundHelper;
        }

        public void m790a(EtsyResult etsyResult) {
            EtsyDebug.m1906b(RefundHelper.f1135a, "No payment information for receipt: " + this.f1133a.f1137c);
        }
    }

    /* renamed from: com.etsy.android.lib.a.a.3 */
    class RefundHelper implements EtsyJobResponse {
        final /* synthetic */ RefundHelper f1134a;

        RefundHelper(RefundHelper refundHelper) {
            this.f1134a = refundHelper;
        }

        public void m791a(int i, String str, EtsyResult etsyResult) {
            EtsyDebug.m1906b(RefundHelper.f1135a, "Error fetching payment information for receipt: " + this.f1134a.f1137c);
        }
    }

    static {
        f1135a = EtsyDebug.m1891a(RefundHelper.class);
    }

    public RefundHelper(RefundHelper refundHelper, EtsyId etsyId, EtsyId etsyId2, boolean z) {
        this.f1140f = refundHelper;
        this.f1136b = aj.m1101a().m1123i();
        this.f1137c = etsyId;
        this.f1138d = etsyId2;
        this.f1139e = z ? APIv3Scope.SHOP : APIv3Scope.MEMBER;
    }

    public void m798a(boolean z) {
        if (!z || this.f1141g == null) {
            m797a();
        } else {
            this.f1140f.updateRefundStatus(this.f1141g);
        }
    }

    public void m797a() {
        EtsyJobBuilder a = EtsyJobBuilder.m1307a(PaymentRequest.getPaymentsByReceiptIds(this.f1138d, this.f1139e));
        a.m1323a(ResponseConstants.RECEIPT_IDS, this.f1137c.getId());
        a.m1321a(new RefundHelper(this));
        a.m1319a(new RefundHelper(this));
        a.m1320a(new RefundHelper(this));
        this.f1136b.m1697a((Object) this, a.m1324a());
    }

    public boolean m799b() {
        if (this.f1141g != null) {
            return this.f1141g.hasRefund();
        }
        return false;
    }

    public void m800c() {
        this.f1136b.m1700a((Object) this);
    }
}
