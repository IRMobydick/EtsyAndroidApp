package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import com.google.android.gms.common.api.GoogleApiClient;

/* renamed from: com.google.android.gms.auth.api.signin.internal.a */
class C1146a implements LoaderCallbacks<Void> {
    final /* synthetic */ SignInHubActivity f4699a;

    private C1146a(SignInHubActivity signInHubActivity) {
        this.f4699a = signInHubActivity;
    }

    public void m6266a(Loader<Void> loader, Void voidR) {
        this.f4699a.setResult(this.f4699a.zzacY, this.f4699a.zzacZ);
        this.f4699a.finish();
    }

    public Loader<Void> onCreateLoader(int i, Bundle bundle) {
        return new zzb(this.f4699a, GoogleApiClient.zzrq());
    }

    public /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        m6266a(loader, (Void) obj);
    }

    public void onLoaderReset(Loader<Void> loader) {
    }
}
