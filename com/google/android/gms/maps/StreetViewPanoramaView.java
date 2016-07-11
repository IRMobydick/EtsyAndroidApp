package com.google.android.gms.maps;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class StreetViewPanoramaView extends FrameLayout {
    private s zzaZO;
    private final w zzbaa;

    public StreetViewPanoramaView(Context context) {
        super(context);
        this.zzbaa = new w(this, context, null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzbaa = new w(this, context, null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzbaa = new w(this, context, null);
    }

    public StreetViewPanoramaView(Context context, StreetViewPanoramaOptions streetViewPanoramaOptions) {
        super(context);
        this.zzbaa = new w(this, context, streetViewPanoramaOptions);
    }

    @Deprecated
    public final s getStreetViewPanorama() {
        if (this.zzaZO != null) {
            return this.zzaZO;
        }
        this.zzbaa.i();
        if (this.zzbaa.a() == null) {
            return null;
        }
        try {
            this.zzaZO = new s(((v) this.zzbaa.a()).h().getStreetViewPanorama());
            return this.zzaZO;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void getStreetViewPanoramaAsync(q qVar) {
        zzaa.zzdc("getStreetViewPanoramaAsync() must be called on the main thread");
        this.zzbaa.a(qVar);
    }

    public final void onCreate(Bundle bundle) {
        this.zzbaa.a(bundle);
        if (this.zzbaa.a() == null) {
            b.b(this);
        }
    }

    public final void onDestroy() {
        this.zzbaa.g();
    }

    public final void onLowMemory() {
        this.zzbaa.h();
    }

    public final void onPause() {
        this.zzbaa.d();
    }

    public final void onResume() {
        this.zzbaa.c();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        this.zzbaa.b(bundle);
    }
}
