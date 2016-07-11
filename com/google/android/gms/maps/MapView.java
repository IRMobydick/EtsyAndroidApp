package com.google.android.gms.maps;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class MapView extends FrameLayout {
    private c zzaZt;
    private final n zzaZz;

    public MapView(Context context) {
        super(context);
        this.zzaZz = new n(this, context, null);
        zzDG();
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzaZz = new n(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        zzDG();
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzaZz = new n(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        zzDG();
    }

    public MapView(Context context, GoogleMapOptions googleMapOptions) {
        super(context);
        this.zzaZz = new n(this, context, googleMapOptions);
        zzDG();
    }

    private void zzDG() {
        setClickable(true);
    }

    @Deprecated
    public final c getMap() {
        if (this.zzaZt != null) {
            return this.zzaZt;
        }
        this.zzaZz.i();
        if (this.zzaZz.a() == null) {
            return null;
        }
        try {
            this.zzaZt = new c(((m) this.zzaZz.a()).h().getMap());
            return this.zzaZt;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void getMapAsync(C0739p c0739p) {
        zzaa.zzdc("getMapAsync() must be called on the main thread");
        this.zzaZz.a(c0739p);
    }

    public final void onCreate(Bundle bundle) {
        this.zzaZz.a(bundle);
        if (this.zzaZz.a() == null) {
            b.b(this);
        }
    }

    public final void onDestroy() {
        this.zzaZz.g();
    }

    public final void onEnterAmbient(Bundle bundle) {
        zzaa.zzdc("onEnterAmbient() must be called on the main thread");
        this.zzaZz.c(bundle);
    }

    public final void onExitAmbient() {
        zzaa.zzdc("onExitAmbient() must be called on the main thread");
        this.zzaZz.j();
    }

    public final void onLowMemory() {
        this.zzaZz.h();
    }

    public final void onPause() {
        this.zzaZz.d();
    }

    public final void onResume() {
        this.zzaZz.c();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        this.zzaZz.b(bundle);
    }
}
