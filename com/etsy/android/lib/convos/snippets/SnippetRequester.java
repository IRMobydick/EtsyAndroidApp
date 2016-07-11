package com.etsy.android.lib.convos.snippets;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.etsy.android.lib.convos.contentprovider.ConvoDatabaseUtil;
import com.etsy.android.lib.core.EtsyJobBuilder;
import com.etsy.android.lib.core.EtsyJobResponse;
import com.etsy.android.lib.core.EtsyResult;
import com.etsy.android.lib.core.aj;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.Snippet;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.etsy.android.lib.requests.SnippetRequest;
import java.util.List;

/* renamed from: com.etsy.android.lib.convos.snippets.c */
public class SnippetRequester {
    private static final String f1383a;

    /* renamed from: com.etsy.android.lib.convos.snippets.c.1 */
    final class SnippetRequester implements EtsyJobResponse {
        final /* synthetic */ Context f1379a;

        SnippetRequester(Context context) {
            this.f1379a = context;
        }

        public void m1021a(int i, String str, EtsyResult etsyResult) {
            LocalBroadcastManager.getInstance(this.f1379a).sendBroadcast(SnippetRequester.m1029b(i, str));
            EtsyDebug.m1912c(SnippetRequester.f1383a, "Snippets failed to load entirely!!");
        }
    }

    /* renamed from: com.etsy.android.lib.convos.snippets.c.2 */
    final class SnippetRequester implements EtsyJobResponse<Snippet> {
        final /* synthetic */ Context f1380a;

        SnippetRequester(Context context) {
            this.f1380a = context;
        }

        public void m1022a(List<Snippet> list, int i, EtsyResult<Snippet> etsyResult) {
            EtsyDebug.m1912c(SnippetRequester.f1383a, "Snippets fetched successfully!!");
            ConvoDatabaseUtil.m981c(this.f1380a.getApplicationContext(), etsyResult.m1056g());
            LocalBroadcastManager.getInstance(this.f1380a).sendBroadcast(new Intent("com.etsy.android.convos.SNIPPETS_LOADED"));
        }
    }

    /* renamed from: com.etsy.android.lib.convos.snippets.c.3 */
    final class SnippetRequester implements EtsyJobResponse {
        final /* synthetic */ Context f1381a;

        SnippetRequester(Context context) {
            this.f1381a = context;
        }

        public void m1023a(EtsyResult etsyResult) {
            LocalBroadcastManager.getInstance(this.f1381a).sendBroadcast(new Intent("com.etsy.android.convos.SNIPPETS_NO_RESULTS"));
            EtsyDebug.m1912c(SnippetRequester.f1383a, "there were no snippets to load!!");
        }
    }

    /* renamed from: com.etsy.android.lib.convos.snippets.c.4 */
    final class SnippetRequester implements EtsyJobBuilder {
        final /* synthetic */ Context f1382a;

        SnippetRequester(Context context) {
            this.f1382a = context;
        }

        public void m1025a() {
            LocalBroadcastManager.getInstance(this.f1382a).sendBroadcast(new Intent("com.etsy.android.convos.SNIPPETS_LOADING"));
            EtsyDebug.m1912c(SnippetRequester.f1383a, "Snippets load began!!");
        }
    }

    static {
        f1383a = EtsyDebug.m1891a(SnippetRequester.class);
    }

    public static void m1028a(Context context) {
        aj.m1101a().m1123i().m1697a((Object) context, EtsyJobBuilder.m1307a(SnippetRequest.getConvoSnippets()).m1318a(new SnippetRequester(context)).m1319a(new SnippetRequester(context)).m1321a(new SnippetRequester(context)).m1320a(new SnippetRequester(context)).m1324a());
    }

    private static Intent m1029b(int i, String str) {
        Intent intent = new Intent("com.etsy.android.convos.SNIPPETS_LOAD_FAILED");
        intent.putExtra(BaseMessage.TYPE_ERROR, str);
        intent.putExtra("error_code", i);
        return intent;
    }
}
