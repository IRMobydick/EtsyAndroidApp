package com.google.android.gms.common.api;

public interface Api$ApiOptions {

    /* renamed from: com.google.android.gms.common.api.Api.ApiOptions.HasOptions */
    public interface HasOptions extends Api$ApiOptions {
    }

    /* renamed from: com.google.android.gms.common.api.Api.ApiOptions.NotRequiredOptions */
    public interface NotRequiredOptions extends Api$ApiOptions {
    }

    /* renamed from: com.google.android.gms.common.api.Api.ApiOptions.Optional */
    public interface Optional extends HasOptions, NotRequiredOptions {
    }
}
