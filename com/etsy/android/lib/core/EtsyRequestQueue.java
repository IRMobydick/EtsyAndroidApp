package com.etsy.android.lib.core;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.etsy.android.lib.core.http.HttpResult;
import com.etsy.android.lib.core.http.request.BaseHttpRequestJob;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.IntWrapper;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.lib.core.w */
public class EtsyRequestQueue {
    private final RequestQueue f1668a;
    private long f1669b;

    /* renamed from: com.etsy.android.lib.core.w.1 */
    class EtsyRequestQueue extends AsyncTask<String, Void, Void> {
        final /* synthetic */ EtsyRequestQueue f1664a;

        EtsyRequestQueue(EtsyRequestQueue etsyRequestQueue) {
            this.f1664a = etsyRequestQueue;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m1683a((String[]) objArr);
        }

        protected Void m1683a(String... strArr) {
            if (strArr.length > 0) {
                String str = strArr[0];
                EtsyDebug.m1912c("EtsyRequestQueue", "Removing Volley cache key: " + str);
                this.f1664a.f1668a.getCache().remove(str);
            }
            return null;
        }
    }

    /* renamed from: com.etsy.android.lib.core.w.2 */
    class EtsyRequestQueue extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ EtsyRequestQueue f1665a;

        EtsyRequestQueue(EtsyRequestQueue etsyRequestQueue) {
            this.f1665a = etsyRequestQueue;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m1684a((Void[]) objArr);
        }

        protected Void m1684a(Void... voidArr) {
            EtsyDebug.m1912c("EtsyRequestQueue", "Clearing entire cache");
            this.f1665a.f1668a.getCache().clear();
            return null;
        }
    }

    /* renamed from: com.etsy.android.lib.core.w.3 */
    class EtsyRequestQueue extends EtsyRequestJob<IntWrapper> {
        final /* synthetic */ EtsyNetworkJob f1666a;
        final /* synthetic */ EtsyRequestQueue f1667c;

        EtsyRequestQueue(EtsyRequestQueue etsyRequestQueue, EtsyNetworkJob etsyNetworkJob) {
            this.f1667c = etsyRequestQueue;
            this.f1666a = etsyNetworkJob;
        }

        protected EtsyRequest<IntWrapper> m1685a() {
            EtsyRequest<IntWrapper> etsyRequest = new EtsyRequest("/server/epoch", RequestMethod.GET, IntWrapper.class);
            etsyRequest.setSigned(false);
            return etsyRequest;
        }

        protected void m1686a(EtsyResult<IntWrapper> etsyResult) {
            if (etsyResult.m1049a() && etsyResult.m1056g().size() == 1) {
                long j = ((IntWrapper) etsyResult.m1056g().get(0)).value;
                this.f1667c.m1690a(j);
                if (j > 0) {
                    this.f1667c.m1692d(this.f1666a);
                }
            }
            EtsyDebug.m1916d("EtsyRequestQueue", "failed, error=" + etsyResult.m1052c());
        }
    }

    public EtsyRequestQueue(Context context) {
        this.f1668a = ai.m1096a(context);
        EtsyDebug.m1892a(context);
    }

    public <Result> void m1699a(EtsyNetworkJob<Result> etsyNetworkJob) {
        m1697a(null, (EtsyNetworkJob) etsyNetworkJob);
    }

    public <Result> String m1697a(Object obj, EtsyNetworkJob<Result> etsyNetworkJob) {
        if (etsyNetworkJob == null) {
            EtsyDebug.m1916d("EtsyRequestQueue", "add() - You sent us a dud job!");
            return null;
        }
        if (obj != null) {
            etsyNetworkJob.m702b(obj);
        }
        etsyNetworkJob.m700a(this);
        return m1692d(etsyNetworkJob);
    }

    public String m1696a(Object obj, BaseHttpRequestJob<?, ?, ?> baseHttpRequestJob) {
        if (baseHttpRequestJob == null) {
            EtsyDebug.m1916d("EtsyRequestQueue", "add() - You sent us a dud job!");
            return null;
        }
        if (obj != null) {
            baseHttpRequestJob.setTag(obj);
        }
        this.f1668a.add(baseHttpRequestJob);
        return baseHttpRequestJob.getCacheKey();
    }

    public String m1695a(BaseHttpRequestJob<?, ?, ?> baseHttpRequestJob) {
        return m1696a(null, (BaseHttpRequestJob) baseHttpRequestJob);
    }

    public <Result> Result m1703b(EtsyNetworkJob<Result> etsyNetworkJob) {
        if (etsyNetworkJob == null) {
            EtsyDebug.m1916d("EtsyRequestQueue", "run() - You sent us a dud job!");
            return null;
        }
        etsyNetworkJob.m700a(this);
        return m1693e(etsyNetworkJob);
    }

    public void m1700a(Object obj) {
        this.f1668a.cancelAll(obj);
    }

    private <Result> String m1692d(EtsyNetworkJob<Result> etsyNetworkJob) {
        etsyNetworkJob.b_();
        Request c = etsyNetworkJob.m703c();
        if (c == null) {
            return null;
        }
        this.f1668a.add(c);
        return c.getCacheKey();
    }

    @NonNull
    public <Result extends HttpResult> Result m1694a(@NonNull BaseHttpRequestJob<?, Result, ?, ?, ?> baseHttpRequestJob) {
        RequestFuture newFuture = RequestFuture.newFuture();
        baseHttpRequestJob.m1420a(newFuture);
        BaseHttpRequestJob c = baseHttpRequestJob.m1426c();
        try {
            this.f1668a.add(baseHttpRequestJob.m1426c());
            return (HttpResult) newFuture.get((long) c.getTimeoutMs(), TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            EtsyDebug.m1917d("EtsyRequestQueue", "runJob InterruptedException", e);
            return c.m1411b(new VolleyError(e));
        } catch (Throwable e2) {
            EtsyDebug.m1917d("EtsyRequestQueue", "runJob ExecutionException", e2);
            if (e2.getCause() instanceof VolleyError) {
                return c.m1411b((VolleyError) e2.getCause());
            }
            return c.m1411b(new VolleyError(e2));
        } catch (Throwable e22) {
            EtsyDebug.m1917d("EtsyRequestQueue", "runJob TimeoutException", e22);
            return c.m1411b(new VolleyError(e22));
        }
    }

    private <Result> Result m1693e(EtsyNetworkJob<Result> etsyNetworkJob) {
        Object obj;
        etsyNetworkJob.b_();
        ErrorListener newFuture = RequestFuture.newFuture();
        etsyNetworkJob.m699a((Listener) newFuture);
        etsyNetworkJob.m698a(newFuture);
        Request c = etsyNetworkJob.m703c();
        if (c != null) {
            try {
                this.f1668a.add(c);
                obj = newFuture.get((long) c.getTimeoutMs(), TimeUnit.MILLISECONDS);
                etsyNetworkJob.m701a(obj);
                return obj;
            } catch (Throwable e) {
                EtsyDebug.m1917d("EtsyRequestQueue", "runJob InterruptedException", e);
            } catch (Throwable e2) {
                EtsyDebug.m1917d("EtsyRequestQueue", "runJob ExecutionException", e2);
                if (e2.getCause() instanceof VolleyError) {
                    VolleyError volleyError = (VolleyError) e2.getCause();
                    if (volleyError.networkResponse != null) {
                        String str = StringUtils.EMPTY;
                        if (volleyError.networkResponse.headers != null) {
                            str = (String) volleyError.networkResponse.headers.get("X-Error-Detail");
                        }
                        if (EtsyDebug.m1915c()) {
                            EtsyDebug.m1920e("EtsyRequestQueue", "Error Running Synchronous Job | \n code:%d message:%s errorheader:%s url:%s", Integer.valueOf(volleyError.networkResponse.statusCode), volleyError.getMessage(), str, c.getUrl());
                        }
                        Response parseNetworkResponse = c.parseNetworkResponse(volleyError.networkResponse);
                        if (parseNetworkResponse != null) {
                            obj = parseNetworkResponse.result;
                            etsyNetworkJob.m701a(obj);
                            return obj;
                        }
                    }
                }
            } catch (Throwable e22) {
                EtsyDebug.m1917d("EtsyRequestQueue", "runJob TimeoutException", e22);
            }
        }
        EtsyDebug.m1916d("EtsyRequestQueue", "runJob returning null");
        return null;
    }

    public void m1701a(String str) {
        new EtsyRequestQueue(this).execute(new String[]{str});
    }

    public void m1698a() {
        new EtsyRequestQueue(this).execute(new Void[0]);
    }

    public void m1705c(EtsyNetworkJob etsyNetworkJob) {
        try {
            m1699a(new EtsyRequestQueue(this, etsyNetworkJob));
        } catch (Throwable e) {
            EtsyDebug.m1899a("EtsyRequestQueue", e);
        }
    }

    public long m1702b() {
        return m1704c() + m1706d();
    }

    private void m1690a(long j) {
        this.f1669b = j - m1704c();
        EtsyDebug.m1906b("EtsyRequestQueue", String.format("serverTime=%d, serverTimeOffset=%d", new Object[]{Long.valueOf(j), Long.valueOf(this.f1669b)}));
    }

    public long m1704c() {
        return System.currentTimeMillis() / 1000;
    }

    public long m1706d() {
        return this.f1669b;
    }
}
