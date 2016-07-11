package com.etsy.android.lib.core.http.p008a;

import com.etsy.android.lib.core.ao;
import com.etsy.android.lib.core.http.HttpResult;
import com.etsy.android.lib.core.http.request.BaseHttpRequestJob;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.etsy.android.lib.core.http.a.b */
public class BackgroundResultExecutor implements BaseHttpRequestJob {
    private final Executor f1535a;

    /* renamed from: com.etsy.android.lib.core.http.a.b.1 */
    class BackgroundResultExecutor implements Runnable {
        final /* synthetic */ BaseHttpRequestJob f1532a;
        final /* synthetic */ HttpResult f1533b;
        final /* synthetic */ BackgroundResultExecutor f1534c;

        BackgroundResultExecutor(BackgroundResultExecutor backgroundResultExecutor, BaseHttpRequestJob baseHttpRequestJob, HttpResult httpResult) {
            this.f1534c = backgroundResultExecutor;
            this.f1532a = baseHttpRequestJob;
            this.f1533b = httpResult;
        }

        public void run() {
            this.f1532a.m830a(this.f1533b);
        }
    }

    public BackgroundResultExecutor() {
        this(5, 30);
    }

    public BackgroundResultExecutor(int i, long j) {
        this.f1535a = new ThreadPoolExecutor(0, i, j, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ao(10));
    }

    public <RequestResult extends HttpResult> void m1334a(BaseHttpRequestJob<RequestResult> baseHttpRequestJob, RequestResult requestResult) {
        this.f1535a.execute(new BackgroundResultExecutor(this, baseHttpRequestJob, requestResult));
    }
}
