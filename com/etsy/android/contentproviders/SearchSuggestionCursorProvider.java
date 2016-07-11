package com.etsy.android.contentproviders;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.text.TextUtils;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyNetworkJob;
import com.etsy.android.lib.core.EtsyRequestJob;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.HttpRequestJobBuilder;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.SearchSuggestion;
import com.etsy.android.lib.models.apiv3.SearchSuggestions;
import com.foresee.mobileReplay.perfLog.PerfDb;
import java.util.List;

/* renamed from: com.etsy.android.contentproviders.k */
public class SearchSuggestionCursorProvider {
    private static final SearchSuggestionCursorProvider f1109a;
    private static String[] f1110b;
    private static Cursor f1111c;

    /* renamed from: com.etsy.android.contentproviders.k.1 */
    final class SearchSuggestionCursorProvider implements EtsyJobResponse<SearchSuggestions> {
        final /* synthetic */ Cursor[] f1103a;
        final /* synthetic */ Context f1104b;
        final /* synthetic */ String f1105c;

        SearchSuggestionCursorProvider(Cursor[] cursorArr, Context context, String str) {
            this.f1103a = cursorArr;
            this.f1104b = context;
            this.f1105c = str;
        }

        public void m758a(List<SearchSuggestions> list, int i, EtsyResult<SearchSuggestions> etsyResult) {
            if (!list.isEmpty()) {
                this.f1103a[0] = SearchSuggestionCursorProvider.m765b(this.f1104b, ((SearchSuggestions) list.get(0)).getResults(), this.f1105c, 6, true);
            }
        }
    }

    /* renamed from: com.etsy.android.contentproviders.k.2 */
    final class SearchSuggestionCursorProvider implements EtsyJobResponse<SearchSuggestions> {
        final /* synthetic */ SearchSuggestionCursorProvider f1106a;
        final /* synthetic */ Context f1107b;
        final /* synthetic */ String f1108c;

        SearchSuggestionCursorProvider(SearchSuggestionCursorProvider searchSuggestionCursorProvider, Context context, String str) {
            this.f1106a = searchSuggestionCursorProvider;
            this.f1107b = context;
            this.f1108c = str;
        }

        public void m759a(List<SearchSuggestions> list, int i, EtsyResult<SearchSuggestions> etsyResult) {
            if (!list.isEmpty()) {
                this.f1106a.m766a(SearchSuggestionCursorProvider.m765b(this.f1107b, ((SearchSuggestions) list.get(0)).getResults(), this.f1108c, 6, false));
            }
        }
    }

    static {
        f1109a = new SearchSuggestionCursorProvider();
        f1110b = new String[]{PerfDb.COL_ID, "suggest_text_1", "suggest_intent_data", "suggest_intent_extra_data", "suggest_flags"};
        f1111c = new MatrixCursor(f1110b, 0);
    }

    private static Cursor m765b(Context context, List<SearchSuggestion> list, String str, int i, boolean z) {
        int min = Math.min(list.size(), i);
        Cursor matrixCursor = new MatrixCursor(f1110b, min + 1);
        for (int i2 = 0; i2 < min; i2++) {
            String query = ((SearchSuggestion) list.get(i2)).getQuery();
            matrixCursor.addRow(new Object[]{Integer.valueOf(i2 + 1), query, query, query, Integer.valueOf(1)});
        }
        if (z && str.length() >= 3) {
            r0 = new Object[5];
            r0[1] = context.getString(R.shops_or_users_containing, new Object[]{str});
            r0[2] = "ETSY_SHOP_USER";
            r0[3] = str;
            r0[4] = Integer.valueOf(0);
            matrixCursor.addRow(r0);
        }
        return matrixCursor;
    }

    public static Cursor m760a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return f1111c;
        }
        Cursor[] cursorArr = new Cursor[]{f1111c};
        aj.m1101a().m1123i().m1703b(SearchSuggestionCursorProvider.m762a(str, new SearchSuggestionCursorProvider(cursorArr, context, str.toLowerCase())));
        return cursorArr[0];
    }

    public static void m764a(Context context, String str, SearchSuggestionCursorProvider searchSuggestionCursorProvider) {
        if (!TextUtils.isEmpty(str)) {
            String toLowerCase = str.toLowerCase();
            EtsyNetworkJob a = SearchSuggestionCursorProvider.m762a(toLowerCase, new SearchSuggestionCursorProvider(searchSuggestionCursorProvider, context, toLowerCase));
            SearchSuggestionCursorProvider.m763a();
            aj.m1101a().m1123i().m1697a(f1109a, a);
        }
    }

    public static void m763a() {
        aj.m1101a().m1123i().m1700a(f1109a);
    }

    private static EtsyRequestJob m762a(String str, EtsyJobResponse<SearchSuggestions> etsyJobResponse) {
        return HttpRequestJobBuilder.m1712a(SearchSuggestions.class, "/etsyapps/v3/public/search/suggestions").m1744a(ResponseConstants.QUERY, str).m1743a((EtsyJobResponse) etsyJobResponse).m1737a();
    }
}
