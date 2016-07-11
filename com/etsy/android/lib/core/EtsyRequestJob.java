package com.etsy.android.lib.core;

import com.android.volley.NetworkResponse;
import com.android.volley.Request.Priority;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.logger.p010a.EtsyGraphite;
import com.etsy.android.lib.logger.p010a.NetworkErrorLogger;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequestBatch;
import com.etsy.android.lib.requests.HttpUtil;
import com.etsy.android.lib.toolbar.AdminToolbar;
import java.math.BigDecimal;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.etsy.android.lib.core.u */
class EtsyRequestJob extends JsonRequest<EtsyResult<Result>> {
    final /* synthetic */ EtsyRequestJob f1659a;
    private EtsyRequest<Result> f1660b;

    public EtsyRequestJob(EtsyRequestJob etsyRequestJob, EtsyRequest<Result> etsyRequest, int i, String str) {
        this.f1659a = etsyRequestJob;
        super(i, str, null, etsyRequestJob.m704d(), etsyRequestJob.m705e());
        this.f1660b = etsyRequest;
    }

    public Response<EtsyResult<Result>> parseNetworkResponse(NetworkResponse networkResponse) {
        EtsyResult etsyResultBatch;
        EtsyDebug.m1914c(EtsyRequestJob.f1081a, "parseNetworkResponse - URL:%s", getUrl());
        if (EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.f1235b)) {
            m1678a(this.f1660b.getUrl(), networkResponse.headers);
        }
        if (this.f1660b instanceof EtsyRequestBatch) {
            etsyResultBatch = new EtsyResultBatch(networkResponse, ((EtsyRequestBatch) this.f1660b).getResponseTypeMap());
        } else if (this.f1660b.getEndpointType() == EndpointType.APIv3) {
            etsyResultBatch = new EtsyV3Result(networkResponse, this.f1660b.getResponseClass());
        } else if (this.f1660b.getEndpointType() == EndpointType.I_CAN_HAZ_TOKEN) {
            etsyResultBatch = new EtsyV3Result(networkResponse, this.f1660b.getResponseClass());
        } else {
            etsyResultBatch = new EtsyResult(networkResponse, this.f1660b.getResponseClass());
        }
        AdminToolbar.m2991a(getUrl(), networkResponse);
        if (!etsyResultBatch.m1049a()) {
            NetworkErrorLogger.m1819a(networkResponse, etsyResultBatch.m1052c(), this.f1660b.getResponseClass() != null ? this.f1660b.getResponseClass().getSimpleName() : StringUtils.EMPTY, this.f1659a.m710a(this.f1660b));
        }
        this.f1659a.m719b(etsyResultBatch);
        return Response.success(etsyResultBatch, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    private void m1678a(String str, Map<String, String> map) {
        try {
            BigDecimal bigDecimal = new BigDecimal((String) map.get("X-Android-Sent-Millis"));
            EtsyGraphite.m1809a("network_response." + str, new BigDecimal((String) map.get("X-Android-Received-Millis")).subtract(bigDecimal).doubleValue(), this.f1659a.f1083c);
        } catch (NumberFormatException e) {
        }
    }

    public Map<String, String> getHeaders() {
        Map<String, String> a = AuthHelper.m1162a(getUrl(), this.f1660b.getAllParameters(), HttpUtil.getVolleyRequestMethod(this.f1660b.getRequestMethod()), this.f1660b.getEndpointType() == EndpointType.APIv3, this.f1660b.isSigned());
        a.putAll(this.f1660b.getHeaders());
        return a;
    }

    public byte[] getBody() {
        return this.f1660b.getPayload();
    }

    public String getBodyContentType() {
        return this.f1660b.getContentType();
    }

    public Priority getPriority() {
        return this.f1660b.getPriority();
    }
}
