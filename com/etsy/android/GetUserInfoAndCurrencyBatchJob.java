package com.etsy.android;

import android.content.Context;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyRequestBatchJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.EtsyResultBatch;
import com.etsy.android.lib.core.HttpRequestJobBuilder;
import com.etsy.android.lib.core.ah;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.EtsyArray;
import com.etsy.android.lib.models.EtsyLocale;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequestBatch;
import com.etsy.android.lib.requests.LocaleRequest;
import com.etsy.android.lib.requests.UsersRequest;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.ui.user.UserBadgeCountManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.etsy.android.b */
public class GetUserInfoAndCurrencyBatchJob extends EtsyRequestBatchJob {
    private static final String f1087a;
    private Context f1088c;

    /* renamed from: com.etsy.android.b.1 */
    class GetUserInfoAndCurrencyBatchJob implements EtsyJobResponse {
        final /* synthetic */ GetUserInfoAndCurrencyBatchJob f1074a;

        GetUserInfoAndCurrencyBatchJob(GetUserInfoAndCurrencyBatchJob getUserInfoAndCurrencyBatchJob) {
            this.f1074a = getUserInfoAndCurrencyBatchJob;
        }

        public void m693a(int i, String str, EtsyResult etsyResult) {
            EtsyDebug.m1919e(GetUserInfoAndCurrencyBatchJob.f1087a, "Error retrieving the badge counts");
        }
    }

    /* renamed from: com.etsy.android.b.2 */
    class GetUserInfoAndCurrencyBatchJob implements EtsyJobResponse {
        final /* synthetic */ GetUserInfoAndCurrencyBatchJob f1075a;

        GetUserInfoAndCurrencyBatchJob(GetUserInfoAndCurrencyBatchJob getUserInfoAndCurrencyBatchJob) {
            this.f1075a = getUserInfoAndCurrencyBatchJob;
        }

        public void m695a(EtsyResult etsyResult) {
        }
    }

    /* renamed from: com.etsy.android.b.3 */
    class GetUserInfoAndCurrencyBatchJob implements EtsyJobResponse<EtsyArray> {
        final /* synthetic */ GetUserInfoAndCurrencyBatchJob f1076a;

        GetUserInfoAndCurrencyBatchJob(GetUserInfoAndCurrencyBatchJob getUserInfoAndCurrencyBatchJob) {
            this.f1076a = getUserInfoAndCurrencyBatchJob;
        }

        public void m697a(List<EtsyArray> list, int i, EtsyResult<EtsyArray> etsyResult) {
            if (list.size() > 0) {
                EtsyArray etsyArray = (EtsyArray) list.get(0);
                UserBadgeCountManager.m5064a(etsyArray.getData().optInt("open_reviews", 0));
                UserBadgeCountManager.m5067b(etsyArray.getData().optInt("new_convo_count", 0));
                UserBadgeCountManager.m5069c(this.f1076a.f1088c);
            }
        }
    }

    static {
        f1087a = EtsyDebug.m1891a(GetUserInfoAndCurrencyBatchJob.class);
    }

    public GetUserInfoAndCurrencyBatchJob(Context context) {
        this.f1088c = context;
    }

    protected EtsyRequestBatch a_() {
        EtsyRequestBatch etsyRequestBatch = new EtsyRequestBatch();
        etsyRequestBatch.addRequest(ActivityFeedEntity.USER, m726i());
        etsyRequestBatch.addRequest("locale_prefs", LocaleRequest.getLocale());
        return etsyRequestBatch;
    }

    private EtsyRequest<User> m726i() {
        EtsyRequest<User> self = UsersRequest.getSelf();
        Map hashMap = new HashMap();
        hashMap.put("fields", "user_id,login_name,awaiting_feedback_count,primary_email,user_pub_key");
        hashMap.put("includes", "Profile(image_url_75x75,city,is_seller,first_name,last_name,login_name)/Country(country_id,name),Shops(shop_id,shop_name,currency_code,icon_url_fullxfull),Addresses(zip,country_id,is_default_shipping)");
        self.addParams(hashMap);
        return self;
    }

    protected void m728a(EtsyResult<EmptyResult> etsyResult) {
        if (etsyResult == null || !etsyResult.m1049a()) {
            aj.m1101a().m1108a(new EtsyId());
            return;
        }
        EtsyResultBatch etsyResultBatch = (EtsyResultBatch) etsyResult;
        if (etsyResultBatch.m1710p() > 0) {
            m724c(etsyResultBatch.m1709b(ActivityFeedEntity.USER));
            m725d(etsyResultBatch.m1709b("locale_prefs"));
            return;
        }
        aj.m1101a().m1108a(new EtsyId());
    }

    private void m724c(EtsyResult<User> etsyResult) {
        if (etsyResult != null && etsyResult.m1049a() && etsyResult.m1058i()) {
            User user = (User) etsyResult.m1056g().get(0);
            if (user != null) {
                SharedPreferencesUtility.m3120a(this.f1088c, user);
                aj.m1101a().m1108a(user.getUserId());
                if (user.getPublicKey() == null) {
                    m707g().m1699a(new ah().m1095a(getClass().toString()));
                }
                m727j();
                return;
            }
            return;
        }
        aj.m1101a().m1108a(new EtsyId());
    }

    private void m725d(EtsyResult<EtsyLocale> etsyResult) {
        if (etsyResult != null && etsyResult.m1049a() && etsyResult.m1058i()) {
            EtsyLocale etsyLocale = (EtsyLocale) etsyResult.m1056g().get(0);
            if (etsyLocale != null && etsyLocale.getCurrency() != null) {
                CurrencyUtil.m3068a(this.f1088c, etsyLocale.getCurrency());
            }
        }
    }

    private void m727j() {
        m707g().m1697a((Object) this, HttpRequestJobBuilder.m1712a(EtsyArray.class, "/etsyapps/v3/bespoke/member/menucounts").m1746a(false).m1743a(new GetUserInfoAndCurrencyBatchJob(this)).m1741a(new GetUserInfoAndCurrencyBatchJob(this)).m1742a(new GetUserInfoAndCurrencyBatchJob(this)).m1737a());
    }
}
