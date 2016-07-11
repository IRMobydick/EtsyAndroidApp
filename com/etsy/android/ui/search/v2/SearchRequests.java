package com.etsy.android.ui.search.v2;

import android.support.annotation.Nullable;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.HttpRequestJobBuilder;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.SearchSimplifiedQueries;
import com.etsy.android.lib.models.apiv3.SearchWithAds;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.ui.search.v2.n */
public class SearchRequests {
    public static final SearchRequests f3357a;

    /* renamed from: com.etsy.android.ui.search.v2.n.1 */
    final class SearchRequests implements EtsyJobResponse {
        final /* synthetic */ SearchRequests f3353a;

        SearchRequests(SearchRequests searchRequests) {
            this.f3353a = searchRequests;
        }

        public void m4937a(int i, String str, EtsyResult etsyResult) {
            this.f3353a.m4857a();
        }
    }

    /* renamed from: com.etsy.android.ui.search.v2.n.2 */
    final class SearchRequests implements EtsyJobResponse<SearchWithAds> {
        final /* synthetic */ SearchRequests f3354a;

        SearchRequests(SearchRequests searchRequests) {
            this.f3354a = searchRequests;
        }

        public void m4938a(List<SearchWithAds> list, int i, EtsyResult<SearchWithAds> etsyResult) {
            this.f3354a.m4858a((SearchWithAds) list.get(0));
        }
    }

    /* renamed from: com.etsy.android.ui.search.v2.n.3 */
    final class SearchRequests implements EtsyJobResponse {
        final /* synthetic */ SearchRequests f3355a;

        SearchRequests(SearchRequests searchRequests) {
            this.f3355a = searchRequests;
        }

        public void m4939a(int i, String str, EtsyResult etsyResult) {
            this.f3355a.m4845a();
        }
    }

    /* renamed from: com.etsy.android.ui.search.v2.n.4 */
    final class SearchRequests implements EtsyJobResponse<SearchSimplifiedQueries> {
        final /* synthetic */ SearchRequests f3356a;

        SearchRequests(SearchRequests searchRequests) {
            this.f3356a = searchRequests;
        }

        public void m4940a(List<SearchSimplifiedQueries> list, int i, EtsyResult<SearchSimplifiedQueries> etsyResult) {
            this.f3356a.m4846a(((SearchSimplifiedQueries) list.get(0)).getQueries());
        }
    }

    static {
        f3357a = new SearchRequests(false, false, false, true);
    }

    private static String m4943a(String str) {
        return str == null ? StringUtils.EMPTY : str;
    }

    public static EtsyRequestJob<SearchWithAds> m4942a(String str, @Nullable String str2, SearchOptions searchOptions, SearchRequests searchRequests, int i, int i2, SearchRequests searchRequests2) {
        HttpRequestJobBuilder a = HttpRequestJobBuilder.m1712a(SearchWithAds.class, "/etsyapps/v3/bespoke/member/search-with-ads").m1744a("keywords", SearchRequests.m4943a(str)).m1744a("offset", String.valueOf(i)).m1744a("limit", String.valueOf(i2)).m1743a(new SearchRequests(searchRequests2)).m1742a(new SearchRequests(searchRequests2));
        if (str2 != null && i == 0) {
            a.m1744a(ResponseConstants.ANCHOR_LISTING_ID, str2);
        }
        searchOptions.m4752a(a);
        searchRequests.m4944a(a);
        return a.m1737a();
    }

    public static EtsyRequestJob<SearchSimplifiedQueries> m4941a(String str, SearchRequests searchRequests) {
        return HttpRequestJobBuilder.m1712a(SearchSimplifiedQueries.class, "/etsyapps/v3/public/search/simplify").m1744a(ResponseConstants.QUERY, SearchRequests.m4943a(str)).m1743a(new SearchRequests(searchRequests)).m1742a(new SearchRequests(searchRequests)).m1737a();
    }
}
