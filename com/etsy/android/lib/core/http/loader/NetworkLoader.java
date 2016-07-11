package com.etsy.android.lib.core.http.loader;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.p005a.EtsyV3Result;
import com.etsy.android.lib.models.BaseModel;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.etsy.android.lib.core.http.loader.a */
public abstract class NetworkLoader<ResultModel extends BaseModel> extends NetworkLoader<EtsyV3Result<ResultModel>> {
    public abstract void m1366a(int i, @Nullable String str, @NonNull EtsyV3Result<ResultModel> etsyV3Result);

    public abstract void m1369a(@NonNull List<ResultModel> list, int i, @NonNull EtsyV3Result<ResultModel> etsyV3Result);

    public /* synthetic */ void m1368a(Object obj) {
        m1370b((EtsyV3Result) obj);
    }

    public /* synthetic */ void m1371b(Object obj) {
        m1367a((EtsyV3Result) obj);
    }

    public final void m1367a(@NonNull EtsyV3Result<ResultModel> etsyV3Result) {
        m1369a(etsyV3Result.m1058i() ? etsyV3Result.m1056g() : new ArrayList(), etsyV3Result.m1055f(), (EtsyV3Result) etsyV3Result);
    }

    public final void m1370b(@NonNull EtsyV3Result<ResultModel> etsyV3Result) {
        m1366a(etsyV3Result.m1036q(), etsyV3Result.m1052c(), (EtsyV3Result) etsyV3Result);
    }
}
