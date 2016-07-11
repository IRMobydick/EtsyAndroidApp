package com.etsy.android.lib.core;

import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.EmptyResult;
import java.util.List;

/* compiled from: PublicKeyGenerationTriggerJob */
public class ah {
    private static final String f1422a;

    /* renamed from: com.etsy.android.lib.core.ah.1 */
    class PublicKeyGenerationTriggerJob implements EtsyJobResponse {
        final /* synthetic */ ah f1419a;

        PublicKeyGenerationTriggerJob(ah ahVar) {
            this.f1419a = ahVar;
        }

        public void m1091a(int i, String str, EtsyResult etsyResult) {
            EtsyDebug.m1919e(ah.f1422a, "Unsuccessfully requested a PK for this user!!!");
        }
    }

    /* renamed from: com.etsy.android.lib.core.ah.2 */
    class PublicKeyGenerationTriggerJob implements EtsyJobResponse {
        final /* synthetic */ ah f1420a;

        PublicKeyGenerationTriggerJob(ah ahVar) {
            this.f1420a = ahVar;
        }

        public void m1092a(EtsyResult etsyResult) {
            EtsyDebug.m1906b(ah.f1422a, "Successfully requested a PK for this user");
        }
    }

    /* renamed from: com.etsy.android.lib.core.ah.3 */
    class PublicKeyGenerationTriggerJob implements EtsyJobResponse<EmptyResult> {
        final /* synthetic */ ah f1421a;

        PublicKeyGenerationTriggerJob(ah ahVar) {
            this.f1421a = ahVar;
        }

        public void m1093a(List<EmptyResult> list, int i, EtsyResult<EmptyResult> etsyResult) {
            EtsyDebug.m1906b(ah.f1422a, "Successfully requested a PK for this user");
        }
    }

    static {
        f1422a = EtsyDebug.m1891a(ah.class);
    }

    public EtsyRequestJob<EmptyResult> m1095a(String str) {
        if (str != null) {
            EtsyDebug.m1906b(f1422a, "Requesting PK from " + str);
        }
        return HttpRequestJobBuilder.m1712a(EmptyResult.class, "/etsyapps/v3/member/pki/pubkey").m1743a(new PublicKeyGenerationTriggerJob(this)).m1741a(new PublicKeyGenerationTriggerJob(this)).m1742a(new PublicKeyGenerationTriggerJob(this)).m1737a();
    }
}
