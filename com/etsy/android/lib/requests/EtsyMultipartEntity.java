package com.etsy.android.lib.requests;

import android.os.AsyncTask;
import com.etsy.android.lib.models.BaseModel;

/* renamed from: com.etsy.android.lib.requests.a */
class EtsyMultipartEntity<ResultType extends BaseModel, RequestType extends EtsyRequest<ResultType>> extends AsyncTask<Void, Void, RequestType> {
    private final AsyncMultipartRequestCallback<ResultType, RequestType> f1928a;
    private final RequestType f1929b;
    private final EtsyMultipartEntity f1930c;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m2985a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m2986a((EtsyRequest) obj);
    }

    public EtsyMultipartEntity(AsyncMultipartRequestCallback<ResultType, RequestType> asyncMultipartRequestCallback, RequestType requestType, EtsyMultipartEntity etsyMultipartEntity) {
        this.f1928a = asyncMultipartRequestCallback;
        this.f1929b = requestType;
        this.f1930c = etsyMultipartEntity;
    }

    protected RequestType m2985a(Void... voidArr) {
        return this.f1930c.toEtsyRequest(this.f1929b);
    }

    protected void m2986a(RequestType requestType) {
        if (this.f1928a == null) {
            return;
        }
        if (requestType != null) {
            this.f1928a.onRequestCreated(requestType);
        } else {
            this.f1928a.onRequestCreationFailed();
        }
    }
}
