package com.etsy.android.lib.core.http.request;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.http.request.BasePersistentHttpRequest.OnPersistentResultHandler;
import com.etsy.android.lib.core.http.url.IchtApiUrl;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.models.BaseModel;
import java.util.ArrayList;
import java.util.List;

public class PersistentIchtApiRequest<ResultType extends BaseModel> extends BasePersistentHttpRequest<IchtApiRequest<ResultType>, EtsyV3Result<ResultType>, IchtApiUrl> {
    private static final long serialVersionUID = 1208991459097342809L;

    public abstract class PersistentIchtResultHandler<ResultType extends BaseModel> extends OnPersistentResultHandler<EtsyV3Result<ResultType>> {
        private static final long serialVersionUID = -8393779309186736699L;

        public abstract boolean onError(int i, @Nullable String str, @Nullable EtsyV3Result<ResultType> etsyV3Result);

        public abstract void onSuccess(@NonNull List<ResultType> list, int i, @NonNull EtsyV3Result<ResultType> etsyV3Result);

        public final boolean onPersistentResult(@NonNull Context context, @Nullable EtsyV3Result<ResultType> etsyV3Result) {
            if (etsyV3Result != null && etsyV3Result.m1049a()) {
                onSuccess(etsyV3Result.m1058i() ? etsyV3Result.m1056g() : new ArrayList(), etsyV3Result.m1055f(), etsyV3Result);
                return false;
            } else if (etsyV3Result != null) {
                return onError(etsyV3Result.m1036q(), etsyV3Result.m1052c(), etsyV3Result);
            } else {
                return onError(0, null, null);
            }
        }
    }

    private PersistentIchtApiRequest(ac<ResultType> acVar) {
        super(acVar);
    }
}
