package com.etsy.android.lib.core.http.p008a;

import android.os.Handler;
import android.os.Looper;
import com.etsy.android.lib.core.http.HttpResult;
import com.etsy.android.lib.core.http.request.BaseHttpRequestJob;

/* renamed from: com.etsy.android.lib.core.http.a.e */
public class UIResultExecutor implements BaseHttpRequestJob {
    private static Handler f1529a;

    /* renamed from: com.etsy.android.lib.core.http.a.e.1 */
    class UIResultExecutor implements Runnable {
        final /* synthetic */ BaseHttpRequestJob f1538a;
        final /* synthetic */ HttpResult f1539b;
        final /* synthetic */ UIResultExecutor f1540c;

        UIResultExecutor(UIResultExecutor uIResultExecutor, BaseHttpRequestJob baseHttpRequestJob, HttpResult httpResult) {
            this.f1540c = uIResultExecutor;
            this.f1538a = baseHttpRequestJob;
            this.f1539b = httpResult;
        }

        public void run() {
            this.f1538a.m830a(this.f1539b);
        }
    }

    static {
        f1529a = new Handler(Looper.getMainLooper());
    }

    public <RequestResult extends HttpResult> void m1332a(BaseHttpRequestJob<RequestResult> baseHttpRequestJob, RequestResult requestResult) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            baseHttpRequestJob.m830a(requestResult);
        } else {
            f1529a.post(new UIResultExecutor(this, baseHttpRequestJob, requestResult));
        }
    }
}
